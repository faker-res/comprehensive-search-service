/**
 * @description 市场关注度
 * @author kygeng
 * date: 2018-05-16
 */
// lib
import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { Layout } from 'antd'
import Highcharts from 'highcharts'
import classNames from 'classnames'
// style
import './index.scss'

class MarketAttentionCard extends Component {
    constructor(props) {
        super(props)

        this.renderChart = this.renderChart.bind(this);
    }

    componentWillMount() {
        Highcharts.setOptions({
            credits: false
        });        
    }

    componentDidMount() {
        this.renderChart();
    }
    convertToTime(str){
        return new Date(str.replace(/-/g,'/')).getTime();
    }
    renderChart() {
        let _data = [];
        let { title, chart } = this.props;
        for (const time in chart) {
            if (chart.hasOwnProperty(time)) {
                const value = chart[time];
                _data.push([time, parseFloat(value)])   
            }
        };
        _data.sort((a,b)=>{ return this.convertToTime(a[0]) - this.convertToTime(b[0]) });
        let color = '#7cb5ec';
        Highcharts.chart(this.chartContainerId, {
            chart: {
                type: 'area',
                width: 260,
                height: 48,
                backgroundColor: 'transparent',
                margin: [0, 0, 0, 0],
                spacing: [0, 0, 0, 0]
            },
            exporting: {
                enabled: false
            },
            title: {
                text: null
            },
            subtitle: {
                text: null
            },
            yAxis: {
                visible: false
            },
            xAxis: {
                lineWidth: 0,
                tickInterval: 11,
                labels: {
                    align: 'left'
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                enabled: true
            },
            series: [{
                color: color,
                fillColor: {
                    linearGradient: {
                        x1: 0,
                        y1: 0,
                        x2: 0,
                        y2: 1
                    },
                    stops: [
                        [0, Highcharts.Color(color).setOpacity(0.2).get('rgba')],
                        [1, Highcharts.Color(color).setOpacity(0).get('rgba')]
                    ]
                },
                marker: { enabled: false },
                label: { enabled: false },
                name: title,
                lineWidth: 1,

                data: _data
            }]
        });
    }

    render() {
        
        let { type, title, titleColor, subTitle, value, percent, chart } = this.props;
        this.chartContainerId = `chart-container-${type}`;

        let diffStatus = percent === 0 ? 'suspend' : percent > 0 ? 'rise' : 'drop';
        let percentCls = classNames('percent', diffStatus);
        percent = `${percent > 0 ? '+':''}${percent}%`;

        return (
            <Layout className="abc-market-attention-card-wrap">
                <div className="abc-market-attention-body">
                    <span className="title" style={{color: titleColor}}>{title}</span>
                    <span className="value">{value||'--'}</span>
                    <span className="sub-info">
                        <span className="sub-title">{subTitle}</span>
                        <span className={percentCls}>{percent}</span>
                    </span>
                </div>
                <div id={this.chartContainerId} className="chart-container"></div>
            </Layout>
        )
    }
}

MarketAttentionCard.defaultProps = {
    type: '',
    title: '',
    titleColor: '#333',
    subTitle: '',
    value: 0,
    percent: 0,
    chart: {}
}

MarketAttentionCard.propTypes = {
    type: PropTypes.string,
    title: PropTypes.string,
    titleColor: PropTypes.string,
    subTitle: PropTypes.string,
    value: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number
    ]),
    percent: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number
    ]),
    chart: PropTypes.oneOfType([
        PropTypes.object,
        PropTypes.array
    ])
}

export default MarketAttentionCard;