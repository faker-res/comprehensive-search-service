/**
 * 分析师详情-团队竞争力分析组件
 * @author lzhang
 * @date 2018-6-20
 */

import React, { Component } from 'react';
// import { translate } from 'react-i18next';
import { withRouter } from 'react-router-dom';
import classnames from 'classnames';
import styled from 'styled-components';
import {
    Row,
    Col,
    Icon,
    Select,
    Radio
} from 'antd';
import PropTypes from 'prop-types';
import ask from '../../../../lib/ask';

import CardBox from '../../CardBox';
// import TableBox from '../TableBox';
import ChartBox from '../../ChartBox';

import "./index.scss";
import MOCK_DATA from './mock.json';
import chartOpt from './chartOpt';

// 时间段列表
const PERIOD_LIST = [{
    name: "一个月",
    key: "M1"
}, {
    name: "三个月",
    key: "M3"
}, {
    name: "半年",
    key: "M6"
}, {
    name: "一年",
    key: "Y1"
}];


const Divider = styled.div`
	width: 100%;
	height: 14px;
	background: transparent;
`;

export default class AnalyzerCompetitionPower extends Component {
    constructor(props) {
        super(props);
        this.fetchData = this.fetchData.bind(this);
        this.handlePeriodFilterClick = this.handlePeriodFilterClick.bind(this);
        this.handleYearFilterClick = this.handleYearFilterClick.bind(this);
        this.getYearList = this.getYearList.bind(this);

        let yearList = this.getYearList(4);

        this.state = {
			loadStatus: 'done', // pending,done, error
            // 数据加载状态
            loading: true,
            // 分析师数据
            dataSource: [],
            // 其他分析师数据
            dataSourceOther: [],
            // 年份列表
            yearList: yearList,
            // 当前年份
            curYear: yearList[0],
            // 时间段列表
            periodList: PERIOD_LIST,
            // 当前时间段
            curPeriod: PERIOD_LIST[3].key

        }
    }

    getYearList(count = 3) {
        // 获取年份列表
        let now = new Date();
        let year = now.getFullYear();
        let date = [];
        for (let i = 0; i < count; i++) {
            date.push(String(year - i));
        }
        date.push("ALL");
        return date;
    }

    fetchData(opts = {}) {
        let {
            aId,aName,aorgan
        } = this.props;
        let {
            curYear,
            curPeriod
        } = this.state;
        
        if (!aId || !aId.trim()) return;

        let params = {
            limit: 100,
            // peo_uni_code: aId,
            analystName:aName,
            organ:aorgan,
            period: curPeriod,
            year: curYear
        };

        this.setState({
            loading: true,
            dataSource: [],
            dataSourceOther: []
        });

        ask("AnalyzerCompetitionChart", {
            params: Object.assign({}, params, opts)
        }).then((resp) => {
            // let resp = MOCK_DATA;
            let { code, data = {} } = resp;
            // console.log(resp)
            if (code == 200) {
                this.setState({
                    loading: false,
                    dataSource: data.analyst || [],
                    dataSourceOther: data.other_analyst || []
                })
            } else {
                this.setState({
                    loading: false,
                });
            }
        }, (err) => {
            console.error(err);
            this.setState({
                loading: false,
            });
        }).catch((error) => {
            console.error(error);
            this.setState({
                loading: false,
            });
        })
    }

    handleYearFilterClick({target = {}}) {
        this.setState({
            curYear: target.value
        }, () => {
            this.fetchData({
                year: target.value
            });
        })
    }

    handlePeriodFilterClick({target = {}}) {
        this.setState({
            curPeriod: target.value
        }, () => {
            this.fetchData({
                period: target.value
            });
        })
    }

    updateYAxisOffset() {
        let [box] = document.getElementsByClassName("abc-analyzer-competition-power-box");
        if (!box) return;
        let [labels] = box.getElementsByClassName("highcharts-xaxis-labels");
        if (!labels) return;
        let x = parseFloat(((labels.innerHTML || "").match(/<text[^>]+>0.00%<\/text>/) || [""])[0].replace(/<text x="/, ""));
        let [yAxis] = box.getElementsByClassName("highcharts-axis highcharts-yaxis");
        if (!yAxis) return;

        x -= 0.5;
        for (let i = 0; i < yAxis.childNodes.length; i++) {
            let item = yAxis.childNodes[i];
            if (item.nodeName == "path") {
                let d = item.getAttribute("d");
                let svg = d.split(/\s+/);
                if (i < yAxis.childNodes.length - 1) {
                    let diff = parseFloat(svg[1]) - parseFloat(svg[4]);
                    svg[1] = x;
                    svg[4] = x - diff;
                } else {
                    svg[1] = svg[4] = x;
                }
                item.setAttribute("d", svg.join(" "));
            } else if (item.nodeName == "text") {
                item.setAttribute("x", x + 20);
            }
        }
    }

    getChartData(chartSource = [], dataSourceOther = [], size = {}) {
        let {
            aName
        } = this.props;

        let chart = { ...chartOpt };
        let maxX = 0;
        let allX = [...chartSource.map(v => Math.abs(v.ratio || 0)), ...dataSourceOther.map(v => Math.abs(v.ratio || 0))];
        maxX = Math.max(...allX);

        chart.xAxis.min = -maxX;
        chart.xAxis.max = maxX;
        chart.yAxis.offset = -((size.width - 42) / 2.0 - 24.5);

        chart.series[0].data = chartSource.map(item => ({
            x: item.ratio || 0,
            y: item.num || 0,
            code: item.code || "",
            name: item.name
        }));
        chart.series[0].name = aName;

        chart.series[1].data = dataSourceOther.map(item => ({
            x: item.ratio || 0,
            y: item.num || 0,
            code: item.code || "",
            name: item.name
        }));

        chart.chart.animation = false;
        chart.chart.events = chart.chart.events || {};
        chart.chart.events = {
            redraw: () => {
                setTimeout(() => {
                    this.updateYAxisOffset();
                }, 100);
            },
            render: () => {
                setTimeout(() => {
                    this.updateYAxisOffset();
                }, 100);
            }
        }
        return chart;
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.aId != this.props.aId) {
            this.fetchData();
        }
    }

    componentDidMount() {

        this.fetchData();
    }

    render() {
        let {
            t,
            anchor,
            size,
        } = this.props;
        let {
            dataSource,
            dataSourceOther,
            loading,
            yearList,
            curYear,
            periodList,
            curPeriod,
            loadStatus
        } = this.state;

        let chartData = this.getChartData(dataSource, dataSourceOther, size);
        const CardTitle = (props) => {
            return (
                <React.Fragment>
                    <span className={"abc-cardbox-head-title-text"}>{props.title || ""}</span>
                </React.Fragment>
            )
        }

        return (
			<React.Fragment>
            <CardBox
                anchor={anchor}
                className={"abc-analyzer-competition-power-box"}
                title={<CardTitle title="团队竞争力分析" />}
                style={{ width: size.width }}
                loading={false}
                headerTitleStyle={{ padding: "13px 0px" }}
                bodyStyle={{ height: size.height }}
                showEmpty={false}>

                <div className={"abc-cardbox-filter-wrapper"}>
                    <div className={"filter-item abc-cardbox-radio-group"}>
                        <Radio.Group onChange={this.handleYearFilterClick} defaultValue={curYear} size="small">
                            {
                                yearList.map((item, index) => {
                                    return (
                                        <Radio.Button key={index} value={item}>{item == "ALL" ? "全部" : item}</Radio.Button>
                                    )
                                })
                            }
                        </Radio.Group>
                    </div>
                    <div className={classnames("filter-item abc-cardbox-radio-group fr", {hidden: curYear == "ALL"})}>
                        <span>选择时间</span>
                        <Radio.Group onChange={this.handlePeriodFilterClick} value={curPeriod}>
                            {
                                periodList.map((item, index) => {
                                    return (
                                        <Radio key={index} value={item.key}>{item.name}</Radio>
                                    )
                                })
                            }
                        </Radio.Group>
                    </div>
                </div>
                
                <CardBox
                    className={"abc-analyzer-competition-power-box-chart"}
                    style={{ width: size.width }}
                    loading={loading}
                    isHeaderVisible={false}
                    bordered={false}
                    style={{boxShadow: "none", padding: 0}}
                    bodyStyle={{ height: size.height - 32 - 34, padding: 0 }}
                    showEmpty={!loading && dataSource && !dataSource.length && dataSourceOther && !dataSourceOther.length}>

                    <ChartBox size={{ height: size.height - 32 - 34 }}
                        config={chartData}
                        chartHandle={(chart) => this.chartHandle = chart}/>
                </CardBox>
            </CardBox>
			{
				loadStatus === 'done' && <Divider />
			}
            </React.Fragment>
        )
    }
}

AnalyzerCompetitionPower.propTypes = {
    // 卡片锚点
    anchor: PropTypes.string,
    // 卡片尺寸
    size: PropTypes.shape({
        width: PropTypes.oneOfType([
            PropTypes.number,
            PropTypes.string
        ]),
        height: PropTypes.number,
    }),
    // 分析师ID
    aId: PropTypes.string,
    // 分析师名字
    aName: PropTypes.string,
}

AnalyzerCompetitionPower.defaultProps = {
    anchor: "",
    size: {
        width: 0,
        height: 0
    },
    aId: "",
    aName: ""
}

// export default withRouter(translate()(AnalyzerCompetitionPower));