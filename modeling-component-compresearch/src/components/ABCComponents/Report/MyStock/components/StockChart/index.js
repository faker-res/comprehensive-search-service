/**
 * @description 显示股票当前价格和涨跌幅
 * @date 2018.05.08
 * @author shgli
 */

import React, { Component } from "react";
import { Row, Col, Icon } from "antd";
import ReactHighcharts from 'react-highcharts';
import "./index.scss";

const min =  0;
const max = 100;

class StockChart extends Component {
    static defaultProps = {
        defaultConfig: {
            chart: {
                type: "line",
                backgroundColor: 'transparent',
                margin: [0, 0, 0, 0],
                spacing: [0, 0, 0, 0],
                height: 45,
                width: 130,
            },
            exporting: {
                enabled: false
            },
            title: {
                text: null
            },
            legend: {
                enabled: false
            },
            tooltip: {
                enabled: false
            },
            xAxis: {
                visible: false
            },
            yAxis: {
                softMax: max,
                allowDecimals: true,
                min: 0,
                plotLines: [{
                    value: min,
                    width: 1,
                    color: '#4DA04C',
                    dashStyle: "longdash"
                }, {
                    value: max,
                    width: 1,
                    color: '#D9554E',
                    dashStyle: "longdash"
                }]
            },
            plotOptions: {
                series: {
                    marker: {
                        enabled: false
                    },
                    states: {
                        hover: {
                            enabled: false
                        }
                    }
                }
            },
            series: []
        }
    }
    shouldComponentUpdate = (nextProps) => {
        return true;

    }
    render() {
        const { defaultConfig,config } = this.props;
        const newConfig = {...defaultConfig,...config}
        return (
            <div className="stockChart">
                <ReactHighcharts config={newConfig} />
            </div>
        );
    }
}
export default StockChart;