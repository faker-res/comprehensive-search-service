/**
 * @description 分析师行业热力
 * @date 2018.06.23
 * @author bkhao
 */

import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Button } from 'antd';
import StandardChart from './../StandardChart';
import ask from '../../../../lib/ask';
import numeral from 'numeral';
import isEmpty from 'lodash/isEmpty';
import NoDataTip from '../NoDataTip';
import {heatMap} from './data'
import styled from 'styled-components';

import './index.scss';

const Divider = styled.div`
    width: 100%;
    height: 14px;
    background: transparent;
`;
const dustRed = {
    1: '#483139',
    2: '#633E4A',
    3: '#78434F',
    4: '#92444C',
    5: '#A64348',
    6: '#B03E44',
    7: '#C53D43',
    8: '#CE4646',
    9: '#DF5751',
    10: '#F15952'
}

const geekBlue = {
    1: '#354B3F',
    2: '#385B48',
    3: '#36694E',
    4: '#378059',
    5: '#389362',
    6: '#3D916B',
    7: '#40A176',
    8: '#11AB66',
    9: '#0AB86A',
    10: '#14B958'
}

const dayBreakBlue = {
    1: '#06337F',
    2: '#003A8C',
    3: '#0C4AA3',
    4: '#0C51B4',
    5: '#0F54B8',
    6: '#125AC4',
    7: '#0559C2',
    8: '#0A61CE',
    9: '#096DD9',
    10:'#0C6FD9',
}

const dayBreakBlueLower = {
    1: '#1A79E0',
    2: '#2484EB',
    3: '#087CE9',
    4: '#0D84F3',
    5: '#158CFA',
    6: '#40A9FF',
    7: '#69C0FF',
    8: '#91D5FF',
    9: '#BAE7FF',
    10:'#E6F7FF'
}

const calDataLabelSize = ({level = 1, width = 0, height = 0}) => {
    // 计算标签宽度，字体尺寸
    let maxFontSize = 24;
    let minFontSize = 12;
    let baseFontSize = 0;
    let cntMaxWidth = 90;
    let cntMinWidth = 61.8;
    let cntWidth = cntMaxWidth;
    // 不参与比例宽度计算的最小宽度
    let baseNoCalWidth = 230;

    // 根据level和width计算基础字体尺寸
    baseFontSize = (Math.min(level, 10) - 1) * ((maxFontSize - minFontSize) / 10) + minFontSize;

    if (width <= baseNoCalWidth) {
        // 矩形尺寸宽度小于200不予设置比例宽度
        cntWidth = cntMaxWidth;
    } else {
        // 计算比例宽度
        cntWidth = (11 - Math.min(level, 10)) * ((cntMaxWidth - cntMinWidth) / 10) + cntMinWidth;
    }

    return {
        // 基础字体尺寸
        baseFontSize: minFontSize,
        // 数字字体尺寸
        numFontSize: level >= 4 ? minFontSize * 1.5 : minFontSize,
        // 标题字体尺寸
        titleFontSize: Math.max(baseFontSize, minFontSize),
        // 内容区域宽度
        width: cntWidth
    }
}

class AnalystIndustryHeat extends Component {

    static defaultProps = {
        id: 0
    }

    static propTypes = {
        id: PropTypes.number.isRequired
    }

    constructor(props) {
        super(props)
        this.state = {
            loadStatus: 'done',// pending,done, error
            _firstData: [],
            noData: false,
            backFirstFlag: false,
            firstName: '',
            customConfig: {
                chart: {
                    height: 400,
                    margin: 0,
                    renderTo: 'container'
                },
                plotOptions: {
                    series: {
                        dataLabels: {
                            styles: {
                                color: 'white',
                                fontWeight: 'normal',
                                textOutline: null
                            }
                        },
                    },
                },
                tooltip: {
                    backgroundColor: null,
                    borderWidth: 0,
                    padding: 0,
                    shadow: false,
                    useHTML: true,
                    style: {
                        padding: 10,
                        color: '#fff',
                        lineHeight: '18px'
                    },
                    formatter: function() {
                        return `<div style="padding: 10px;background-color:rgba(0,0,0,.6)">
                            ${this.point.tooltip}
                        </div>`
                    }
                },
                navigation: {
                    buttonOptions: {
                        enabled: false
                    }
                },
                series: [{
                    type: "treemap",
                    layoutAlgorithm: 'squarified',
                    borderWidth: 1,
                    borderColor: '#fff',
                    dataLabels: {
                        crop: false,
                        inside: true,
                        defer: true,
                        padding: 0,
                        allowOverlap: true,
                        useHTML: true,
                        style: {
                            color: "#fff",
                            textAlign: 'center'
                        },
                        align: 'center'
                    },
                    data: []
                }]
            }
        }
    }

    componentDidMount() {
        this.getAnalystHeat();
    }

    getAnalystHeat = () => {
        let { id } = this.props;
        ask('AnalystIndustryMap', { params: { peo_uni_code: '19905144' } })
        .then((resp) => {
            // let data=heatMap.data
            const { code , data, message } = resp;
            if (code !== 200) {
                throw new Error(`Response Exception: ${message};code: ${code}`);
            }
            if (isEmpty(data)){
                this.setState({
                    noData: true
                });
                return;
            }
            this.setHeatData(data);
        })
        .catch((error) => {
            this.setState({
                noData: true
            });
            console.error(error);
        })
    }

    formatterDataLabels() {
        // 格式化热力图标签
        let width  = this.point.shapeArgs.width || 0;
        let height = this.point.shapeArgs.height || 0;
        let x = this.point.shapeArgs.x || 0;
        let y = this.point.shapeArgs.y || 0;
        let level = this.point.value;
        let name = this.point.name;
        let reportCount = this.point.reportCount;
        let amount = this.point.amountStr;
        let differRange = this.point.differRangeStr;
        let size = calDataLabelSize({level, width, height});
        let styleOutSide = `left:${x}px;top:${y}px;width:${width}px;height:${height}px`;
        let styleCnt = "max-height: " + height + "px;max-width:" + width + "px" + (`;width:${size.width}%`);
        let styleCntTitle = `font-size:${size.titleFontSize}px;line-height:${size.titleFontSize * 1.2}px;font-weight:400;`;
        let styleBaseFontSize = `font-size:${size.baseFontSize}px;line-height:${size.baseFontSize * 1.2}px;font-weight:300;${level <= 1 ? "transform: scale(.9);" : ""}`;
        let shortName = "";
        let baseNoCalLevel = 3;

        if (level >= 4) {
            // 全部信息
            shortName = `<tr>
                <td colSpan="2">
                    <span class="treemap-datalabels-item-wrap-cnt-title" style="${styleCntTitle}">${name}</span>
                </td>
            </tr>
            <tr>
                <td><span>研报</span></td>
                <td><span>
                    <i style="font-style:normal;font-size:${size.numFontSize}px;">${reportCount}</i>篇
                </span></td>
            </tr>
            <tr>
                <td><span>相较上季度变化</span></td>
                <td><span>
                    <i style="font-style:normal;font-size:${size.numFontSize}px;">${parseFloat(differRange)}</i>%
                </span></td>
            </tr>`;
        } else {
            // 按研报数
            shortName = `<tr>
                <td colSpan="${level > baseNoCalLevel ? 2 : 1}">
                    <span class="treemap-datalabels-item-wrap-cnt-title" style="${styleCntTitle}">${name}</span>
                </td>
            </tr>
            <tr>
                ${level > baseNoCalLevel ? `<td><span>研报</span></td>` : ""}
                <td class="${level > baseNoCalLevel ? "" : "text-center"}"><span>
                    <i style="font-style:normal;font-size:${size.numFontSize}px;">${reportCount}</i>篇
                </span></td>
            </tr>`;
        }

        let dataLabel = shortName;
        return `<div class="treemap-datalabels-item" style="${styleOutSide}">
                    <div class="treemap-datalabels-item-wrap">
                        <div class="treemap-datalabels-item-wrap-cnt" style="${styleCnt}">
                            <table style="${styleBaseFontSize}">
                                ${dataLabel}
                            </table>
                        </div>
                    </div>
                </div>`;
    }

    setHeatData = (data) => {
        let standardColor = 'chg_ratio'; //热力图颜色标准
        let standard = 'num'; //热力图大小标准
        let { customConfig, _firstData } = this.state;
        customConfig.series[0].data = [];

        customConfig.series[0].dataLabels = customConfig.series[0].dataLabels || {};
        customConfig.series[0].dataLabels.formatter = this.formatterDataLabels;

        let allArea = 100;
        let allLevel = 0;

        data.map(item => {
            allLevel += this.getLevel(data,item[standard],standard);
        })

        data.map((item,index) => {
            let level = this.getLevel(data,item[standard],standard);
            let area = level / allLevel * allArea;
            let {
                name = "",
            } = item;
            
            let tooltip = `${item.name}<br/>研报: ${item.num}篇<br/>相较上季度变化: ${item.chg_ratio}%`;
            _firstData.push({
                name: name,
                value: area,
                tooltip: tooltip,
                color: this.getColor(data,item[standardColor],standardColor),
                // 研报数量
                reportCount: item.num,
                // 格式化后的研报数量
                reportCountStr: numeral(isNaN(item.num) || !item.num ? 0 : parseFloat(item.num)).format('0,0'),
                // 相较上季度变化
                differRange: item.chg_ratio,
                // 格式化后的相较上季度变化
                differRangeStr: numeral(isNaN(item.chg_ratio) || !item.chg_ratio ? 0 : parseFloat(item.chg_ratio)).format('0,0.00'),
            });
        })
        customConfig.series[0].data = _firstData;

        this.setState({
            customConfig: customConfig
        });
    }


    getExtreme = (arr,val) => {
        let max = arr[0][val] === null ? 0 : arr[0][val];
        let min = arr[0][val] === null ? 0 : arr[0][val];
        arr.map(item => {
            let t = item[val] === null ? 0 : item[val];
            if (t > max){
                max = t;
            }
            if (t < min){
                min = t;
            }
        })
        return {max:max,min:min};
    }

    getColor = (arr,val,standard) => {
        let level = this.getLevel(arr,val,standard);
        if (val >= 0) {
            return dayBreakBlue[level];
        }
        if (val < 0) {
            return dayBreakBlueLower[level];
        }
    }

    getLevel = (arr,val,standard) => {
        if (arr.length === 1){
            return 10;
        }
        let max = this.getExtreme(arr,standard).max;
        let min = this.getExtreme(arr,standard).min;

        let range = (max - min) / 10;
        val = val === null ? 0 : val;

        val = Math.abs(val);

        return Math.ceil((val - min) / range) === 0 ? 1 : Math.ceil((val - min) / range);

    }

    render() {
        let { customConfig, noData,loadStatus } = this.state;
        return (
            <React.Fragment>
            <div className="aih-wrap">
                <div className="aih-header">
                    <span>行业热力</span>
                </div>
                {
                    noData ? <div className="aih-noData"><NoDataTip style={{lineHeight:1,width:'100%',height:'100%'}}/></div> : <StandardChart chartId='FundManagerDistribute' customConfig={customConfig}/>
                }
                
            </div>
                {
                    loadStatus === 'done' && <Divider/>
                }
                </React.Fragment>
        )
    }
}

export default AnalystIndustryHeat