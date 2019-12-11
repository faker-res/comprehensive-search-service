/**
 * @description 基础图表
 * @date 2018.01.15
 * @author hhxu
 */

import React, { Component } from 'react'

// 1. 直接使用highcharts
import Highcharts from 'highcharts/highstock'
import HighchartsMore from 'highcharts/highcharts-more';
import treemaps from 'highcharts/modules/treemap';
import wordcloud from 'highcharts/modules/wordcloud';
import Refactor from './../../lib/refactor';
import './index.scss';
import cloneDeep from 'lodash/cloneDeep';
// HighchartsMore(Highcharts); // More
// treemaps(Highcharts); // 热力（矩形树）图
// wordcloud(Highcharts); //词云

// 2. 使用react-highcharts
//import ReactHighcharts from 'react-highcharts'


class StandardChart extends Component {
    constructor(props) {
        super(props)
        this.state = {
            containerId: 'basic-chart-' + this.props.chartId
        }
    }

    shouldComponentUpdate(nextProps, nextState) {
        if (JSON.stringify(nextProps.datas) === JSON.stringify(this.datas)
            && JSON.stringify(nextProps.seriesMapping) === JSON.stringify(this.seriesMapping)
            && JSON.stringify(nextProps.customConfig) === JSON.stringify(this.customConfig)) {
            return false;
        }
        return true;
    }

    componentDidUpdate(){
        this.updateChart();
    }
    componentDidMount() {
        this.updateChart();
    }

    updateChart = () => {
        const { containerId } = this.state;
        const { datas, seriesMapping, customConfig } = this.props;
        let seriesConfig = null;
        if (datas && seriesMapping){
            seriesConfig = {series: Refactor.datasToSeries(datas, seriesMapping)};
        }
        const baseCopy = JSON.parse(JSON.stringify(Refactor.baseConfig));
        let chartConfig = {};
        if (seriesConfig){
            chartConfig = Refactor.deepAssign({},
                baseCopy , seriesConfig, customConfig);
        } else {
            chartConfig = Object.assign({},
                baseCopy, customConfig);
        }

        // 避免父组件导致子组件重绘
        this.datas = cloneDeep(datas);
        this.seriesMapping = cloneDeep(seriesMapping);
        this.customConfig = cloneDeep(customConfig);
        const chart = Highcharts.stockChart(containerId, chartConfig);
    }

    render() {
        const { containerId} = this.state;
        // 使用react-highcharts的做法
        //return (<ReactHighcharts config={chartConfig}/>);
        return (<div id={containerId}/>);
    }
}

export default StandardChart