import React, { Component } from 'react'

import Highcharts from 'highcharts/highstock'
import PropTypes from 'prop-types';
import cloneDeep from 'lodash/cloneDeep';
import moment from 'moment';
import numeral from 'numeral';
// import i18n from '../../../i18n';
import isEmpty from 'lodash/isEmpty';
import max from 'lodash/max';
import min from 'lodash/min';

const _formatVolume = (num = 0) => {
    let n = parseInt(num / 100);
    let N = 10000;
    if (n / (N * N) >= 1) {
        return numeral(n / (N * N)).format("0,0.00") + "亿手";
    } else if (n / (N) >= 1) {
        return numeral((n / N)).format("0,0.00") + "万手";
    } else {
        return numeral(n).format("0,0.00") + "手";
    }
}

class StockChartContainer extends Component {

    static defaultProps = {
        // k线类型
        rangeType: '',
        // 股票名称
        stockName: '',
        // 股票代码
        stockCode: '',
        // 数据
        chartData: {}
    }

    static propTypes = {
        rangeType: PropTypes.string,
        stockName: PropTypes.string,
        stockCode: PropTypes.string,
        chartData: PropTypes.oneOfType([
          PropTypes.object,
          PropTypes.array,
        ]).isRequired
    }

    constructor(props) {
        super(props)

        this.state = { containerId: 'abcft-stock-kchart-container' }
    }

    shouldComponentUpdate(nextProps, nextState) {
        if (JSON.stringify(nextProps.chartData) === JSON.stringify(this.chartData)
            && this.rangeType === nextProps.rangeType) {
            return false;
        }
        return true;
    }

    componentWillMount() {
        Highcharts.setOptions({
            credits: false
        })
        Highcharts.seriesTypes.line.prototype.drawLegendSymbol =
            Highcharts.seriesTypes.area.prototype.drawLegendSymbol;
    }

    componentDidUpdate(){
        this.updateChart();
    }
    componentDidMount() {
        this.updateChart();
    }

    _calculateMAVal = (data, dayCount) => {
        let end = data.length - 1;
        let sum = 0;
        for (let index = 0; index < dayCount; index++) {
            sum += data[end - index][4];// close price
        }
        return parseFloat((sum / dayCount).toFixed(2));
    }

    _formatVolume = (num = 0) => {
        let n = parseInt(num / 100);
        let N = 10000.0;
        if (n / (N * N) >= 1) {
            return numeral(num / (N * N)).format("0,0.00") + "亿股";
        } else if (n / (N) >= 1) {
            return numeral(num / (N)).format("0,0.00") + "万股";
        } else {
            return numeral(num).format("0,0.00") + "股";
        }
    }

    updateChart = () => {
        const { rangeType, stockName, stockCode, chartData } = this.props;
        const { containerId } = this.state;
        // 避免父组件导致子组件重绘
        this.chartData = cloneDeep(chartData);
        this.rangeType = rangeType;

        let kData = [],
            volume = [],
            lineData = [],
            linePercentData = [],
            ma5 = [],
            ma10 = [],
            ma20 = [],
            colorUp = '#dc2841',
            colorDown = '#4ca04c';

        // 检测没有绘图数据直接设置失败标识      
        if (isEmpty(chartData) || chartData.length === 0) {
            return {};
        }

        let yAxisMax = null, // 分时曲线y轴最大值
            yAxisMin = null, // 分时曲线y轴最小值
            yAxisPctMax = null, // 分时曲线y轴最大涨幅
            yAxisPctMin = null; // 分时曲线y轴最大跌幅

        // 清洗数据
        let idx = 0,
            len = Object.keys(chartData).length;
        let maxyAxis = -Infinity, minyAxis = Infinity;
        for (let time in chartData) {
            if (chartData.hasOwnProperty(time)) {
                const item = chartData[time];
                // 兼容safari
                const _time = time.replace(/\-/g, '/');
                const _timeDate = new Date(_time).getTime();
                // k线图数据
                kData.push([
                    _timeDate,
                    parseFloat(item.open),
                    parseFloat(item.high),
                    parseFloat(item.low),
                    parseFloat(item.close_price),
                    parseFloat(item.differ_range),
                    parseFloat(item.differ),
                    parseFloat(item.volume)
                ]);
                // 计算y轴最大值与最小值
                maxyAxis = max([item.close_price, maxyAxis]);
                minyAxis = min([item.close_price, minyAxis]);
                // 成交量数据
                let _color = parseFloat(item.differ_range)  > 0 ? colorUp : colorDown;
                volume.push({
                    x: _timeDate,
                    y: parseFloat(item.volume),
                    color: _color
                });
                // 计算分时曲线的价格范围与涨跌幅范围
                if (idx === 0) {
                    yAxisMax = parseFloat(item.open);
                    yAxisMin = parseFloat(item.open);
                    yAxisPctMax = parseFloat(item.differ_range);
                    yAxisPctMin = parseFloat(item.differ_range);
                } else {
                    yAxisMax = parseFloat(item.open) > yAxisMax ? parseFloat(item.open) : yAxisMax;
                    yAxisMin = parseFloat(item.open) < yAxisMin ? parseFloat(item.open) : yAxisMin;
                    yAxisPctMax = parseFloat(item.differ_range) > yAxisPctMax ? parseFloat(item.differ_range) : yAxisPctMax;
                    yAxisPctMin = parseFloat(item.differ_range) < yAxisPctMin ? parseFloat(item.differ_range) : yAxisPctMin;
                }
                // 分时曲线数据
                lineData.push({
                    x: _timeDate,
                    y: parseFloat(item.open)
                });
                // 分时涨跌幅数据
                linePercentData.push({
                    x: _timeDate,
                    y: parseFloat(item.differ_range)
                });
                idx++;
                ma5.push({
                    x: _timeDate,
                    y: idx < 5 ? null : this._calculateMAVal(kData, 5)
                })
                ma10.push({
                    x: _timeDate,
                    y: idx < 10 ? null : this._calculateMAVal(kData, 10)
                })
                ma20.push({
                    x: _timeDate,
                    y: idx < 20 ? null : this._calculateMAVal(kData, 20)
                })
            }
        }

        // 计算分时数据 上一个交易日收盘价
        let lastTimeVal = 0;
        if (rangeType === 'time') {
            let firstPoint = chartData[moment(lineData[0].x).format('YYYY-MM-DD HH:mm:ss')]
            lastTimeVal = parseFloat(firstPoint.open) / (1 + parseFloat(firstPoint.differ_range / 100));
        }

        // 补全分时数据
        let now = new Date(lineData[lineData.length - 1].x);
        now.setHours(9, 30, 0, 0);
        let amStart = now.getTime();
        now.setHours(10, 30, 0, 0);
        let amMiddle = now.getTime();
        now.setHours(11, 30, 0, 0);
        let amEnd = now.getTime();

        now.setHours(13, 0, 0, 0);
        let pmStart = now.getTime();
        now.setHours(14, 0, 0, 0);
        let pmMiddle = now.getTime();
        now.setHours(15, 0, 0, 0);
        let pmEnd = now.getTime();
        
        if (rangeType === 'time') {
            for (let index = 0; index < 240; index++) {
                const el = lineData[index];
                if (el) continue;
                const pre = lineData[index - 1];
                let time = pre.x;
                if (time < amEnd) {
                    time = time + 60 * 1000;
                } else if (time === amEnd) {
                    time = pmStart
                } else if (time < pmEnd) {
                    time = time + 60 * 1000;
                }
                lineData.push({ x: time, y: null });
                linePercentData.push({ x: time, y: null });
                volume.push({ x: time, y: null });
            }
        }

        // 所有数据排序，避免数据时间混乱
        kData.sort((pre, next) => {
            return pre[0] - next[0];
        });
        lineData.sort((pre, next) => {
            return pre.x - next.x;
        });
        linePercentData.sort((pre, next) => {
            return pre.x - next.x;
        });
        volume.sort((pre, next) => {
            return pre.x - next.x;
        });
        ma5.sort((pre, next) => {
            return pre.x - next.x;
        });
        ma10.sort((pre, next) => {
            return pre.x - next.x;
        });
        ma20.sort((pre, next) => {
            return pre.x - next.x;
        });

        // 30分钟k线取10个交易日数据
        let thirtyTickPositions;
        let fiveTickPositons;
        let dayTickPositions;
        let monthTickPositions;
        let isNewStock = false;
        if (rangeType === 'thirty') {
            let lastIndex = kData.length - 1;
            let curDay = moment().format('YYYY-MM-DD');
            for (let index = kData.length - 1; index >= 0; index--) {
                if (curDay !== moment(kData[index][0]).format('YYYY-MM-DD')) {
                    lastIndex = index;
                    break;
                }
            }
            if (lastIndex - 70 >= 0) {
                thirtyTickPositions = [];
                kData = kData.slice(lastIndex - 70);
                maxyAxis = -Infinity;
                minyAxis = Infinity;
                kData.forEach(item => {
                    // 计算y轴最大值与最小值
                    maxyAxis = max([item[4], maxyAxis]);
                    minyAxis = min([item[4], minyAxis]);
                    if (moment(item[0]).format('HH:mm') === '10:00') {
                        thirtyTickPositions.push(item[0])
                    }
                });
                let len = thirtyTickPositions.length;
                thirtyTickPositions = thirtyTickPositions.filter((item, _idx) => {
                    return len % 2 === 0 ? _idx % 2 !== 0 : _idx % 2 === 0;
                })
                volume = volume.slice(lastIndex - 70);
                ma5 = ma5.slice(lastIndex - 70);
                ma10 = ma10.slice(lastIndex - 70);
                ma20 = ma20.slice(lastIndex - 70);
            } else if (kData.length > 10){
                thirtyTickPositions = [];
                maxyAxis = -Infinity;
                minyAxis = Infinity;
                kData.forEach(item => {
                    // 计算y轴最大值与最小值
                    maxyAxis = max([item[4], maxyAxis]);
                    minyAxis = min([item[4], minyAxis]);
                });
                let step = Math.floor(kData.length / 4);
                for (let index = 0; index <= 4; index++) {
                    if (index === 4) {
                        thirtyTickPositions.push(kData[kData.length - 5][0]);
                    } else {
                        thirtyTickPositions.push(kData[index * step][0]);
                    }
                }
            } else {
                isNewStock = true;
            }
        } else if (rangeType === 'five') {
            let lastIndex = kData.length - 1;
            let curDay = moment().format('YYYY-MM-DD');
            for (let index = kData.length - 1; index >= 0; index--) {
                if (curDay !== moment(kData[index][0]).format('YYYY-MM-DD')) {
                    lastIndex = index;
                    break;
                }
            }
            if (lastIndex - 96 >= 0) {
                fiveTickPositons = [];
                kData = kData.slice(lastIndex - 96);
                maxyAxis = -Infinity;
                minyAxis = Infinity;
                kData.forEach(item => {
                    // 计算y轴最大值与最小值
                    maxyAxis = max([item[4], maxyAxis]);
                    minyAxis = min([item[4], minyAxis]);
                    if (moment(item[0]).format('HH:mm') === '15:00') {
                        fiveTickPositons.push(item[0])
                    }
                });
                if (fiveTickPositons.length < 4) {
                    let now = new Date().getTime();
                    if (now > amStart && now < pmEnd) {
                        fiveTickPositons.push(pmStart);
                    }
                }
                volume = volume.slice(lastIndex - 96);
                ma5 = ma5.slice(lastIndex - 96);
                ma10 = ma10.slice(lastIndex - 96);
                ma20 = ma20.slice(lastIndex - 96);
            } else if (kData.length > 48) {
                fiveTickPositons = [];
                maxyAxis = -Infinity;
                minyAxis = Infinity;
                kData.forEach(item => {
                    // 计算y轴最大值与最小值
                    maxyAxis = max([item[4], maxyAxis]);
                    minyAxis = min([item[4], minyAxis]);
                });
                let step = Math.floor(kData.length / 4);
                for (let index = 0; index <= 4; index++) {
                    if (index === 4) {
                        fiveTickPositons.push(kData[kData.length - 5][0]);
                    } else {
                        fiveTickPositons.push(kData[index * step][0]);
                    }
                }
            } else {
                isNewStock = true;
            }
        } else if (rangeType === 'day') {
            // 日线数据少于60个交易日，补充数据，保持图形
            if (kData.length <= 60) {
                isNewStock = true;
                let start = kData[0][0];
                for (let index = 0; index < 60; index++) {
                    let item = kData[index];
                    if (item) continue;
                    let preItem = kData[index - 1];
                    kData.push([
                        moment(preItem[0]).add(1, 'days').valueOf(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                    ])
                }
            }
            dayTickPositions = [];
            let step = Math.floor(kData.length / 4);
            for (let index = 0; index <= 4; index++) {
                if (index === 4) {
                    dayTickPositions.push(kData[kData.length - 5][0]);
                } else {
                    dayTickPositions.push(kData[index * step][0]);
                }
            }
        } else if (rangeType === 'month') {
            if (kData.length > 60) {
                monthTickPositions = [];
                let step = Math.floor(kData.length / 4);
                for (let index = 0; index <= 4; index++) {
                    if (index === 4) {
                        monthTickPositions.push(kData[kData.length - 1][0]);
                    } else {
                        monthTickPositions.push(kData[index * step][0]);
                    }
                }
            } else {
                isNewStock = true;
            }
        }
        // isNewStock标识是否是新上市股票，新上市股票的y轴和x轴需要特殊处理

        // 计算日线与月线最小时间范围
        let xAxisMin = null,
            xAxisMax = null,
            oneHour = 3600 * 1000,
            oneDay = 24 * 3600 * 1000;
        if (rangeType === 'day'
            || rangeType === 'month'
            || rangeType === 'five'
            || rangeType === 'thirty') {
            xAxisMax = kData[kData.length - 1][0];
            if (rangeType === 'five') {
                xAxisMin = kData[0][0];
            } else if (rangeType === 'thirty') {
                xAxisMin = kData[0][0];
            } else if (rangeType === 'day') {
                xAxisMin = kData[0][0];
                // xAxisMin = kData.length > 100 ? kData[Math.floor(kData.length/2)][0] : kData[0][0];
            } else if (rangeType === 'month') {
                xAxisMin = kData[0][0];
                // xAxisMin = kData.length > 100 ? kData[Math.floor(kData.length/2)][0] : kData[0][0];
            }
        }

        let xAxisTickInterval = null;
        if (rangeType === 'five') {
            xAxisTickInterval = null;
        } else if (rangeType === 'thirty') {
            xAxisTickInterval = null;
        } else if (rangeType === 'day') {
            xAxisTickInterval = null;
            // xAxisTickInterval = kData.length > 100 ? 30 * oneDay : null;
        } else if (rangeType === 'month') {
            xAxisTickInterval = null;
            // xAxisTickInterval = kData.length > 100 ? 300 * oneDay : null;
        }

        var chart = Highcharts.stockChart(containerId,{
            chart: {
                // 隐藏商标
                credits: false,
                height: 200
            },
            exporting: {
                // 不显示导出按钮
                enabled: false
            },
            rangeSelector: {
                // 隐藏时间范围选择器
                enabled: false
            },
            scrollbar: {
                // 隐藏滚动条
                enabled: false
            },
            navigator: {
                // 隐藏
                enabled: false,
            },
            title: {
                text: null
            },
            legend: {
                enabled: rangeType !== 'time',
                verticalAlign: 'top',
                align: 'center',
                margin: 5,
                itemStyle: {
                    fontWeight: 'normal'
                },
                labelFormatter: function() {
                    return `<span style="color:${this.color}">${this.name}</span>`
                },
                borderWidth: 0,
                y: -30,
                x: 0
            },
            xAxis: {
                tickLength: 0,
                tickPositions: rangeType === 'time' ?
                    [amStart, amMiddle, pmStart, pmMiddle, pmEnd] :
                    rangeType === 'thirty' ? thirtyTickPositions :
                    rangeType === 'five' ? fiveTickPositons :
                    rangeType === 'day' ? dayTickPositions :
                    rangeType === 'month' ? monthTickPositions : null,
                tickInterval: xAxisTickInterval,
                // gridLineWidth: isNewStock ? 0 : 1,
                labels: {
                    align: 'center',
                    // overflow: false,
                    padding: 0,
                    zIndex: 100,
                    formatter: function () {
                        if (rangeType === 'time') {
                            let times = ['09:30','10:30','13:00','14:00','15:00'];
                            let time = moment(this.value).format('HH:mm');
                            if (times.indexOf(time) !== -1) {
                                return time;
                            } else {
                                return '';
                            }
                        } else if (rangeType === 'five') {
                            return moment(this.value).format('M/DD HH:mm');
                        } else if (rangeType === 'thirty') {
                            if (isNewStock) {
                                return moment(this.value).format('M/DD HH:mm');
                            }
                            return moment(this.value).format('M/DD');
                        } else if (rangeType === 'day') {
                            return moment(this.value).format('YYYY/M/DD');
                        } else if (rangeType === 'month') {
                            return moment(this.value).format('YYYY/M');
                        }
                    }
                },
                showLastLabel: true,
                endOnTick: rangeType === 'time',
                min: xAxisMin,
                max: xAxisMax
            },
            plotOptions: {
                series: {
                    point: {
                        events: {
                            mouseOver: rangeType === 'time' ? null : function(event) {
                                let index = this.index || -1;
                                if (index === -1) return;
                                let ma5Point = ma5[index];
                                let ma10Point = ma10[index];
                                let ma20Point = ma20[index];
                                chart.legend.allItems[0].update({ name: ma5Point.y ? `MA5:${numeral(ma5Point.y).format('0,0.00')}` : 'MA5' });
                                chart.legend.allItems[1].update({ name: ma10Point.y ? `MA10:${numeral(ma10Point.y).format('0,0.00')}` : 'MA10' });
                                chart.legend.allItems[2].update({ name: ma20Point.y ? `MA20:${numeral(ma20Point.y).format('0,0.00')}` : 'MA20' });
                            }
                        }
                    }
                }
            },
            tooltip: {
                split: false,
                shared: true,
                useHTML: true,
                backgroundColor: 'rgba(0,0,0,0.6)',
                borderWidth: 0,
                shadow: false,
                style: {
                    color: '#fff',
                    lineHeight: '18px'
                },
                formatter: function () {
                    let _time = moment(this.x).format('YYYY-MM-DD HH:mm:ss');
                    let _data = chartData[_time];
                    if (!_data) return false;
                    let { open, close_price, high, low, differ, differ_range, differ_range_unit, volume, avg_price } = _data || {};
                    if (rangeType === 'time') {
                        _time = moment(this.x).format('HH:mm');
                    } else if (rangeType === 'five') {
                        _time = moment(this.x).format('MM.DD HH:mm');
                    } else if (rangeType === 'thirty') {
                        _time = moment(this.x).format('MM.DD HH:mm');
                    } else if (rangeType === 'day') {
                        _time = moment(this.x).format('YYYY.MM.DD')
                    } else if (rangeType === 'month') {
                        _time = moment(this.x).format('YYYY.MM.DD');
                    }
                    let _content = '';
                    _content += `<div class="abc-stock-kchart-card-highcharts-tooltip">${_time}<br/>`;
                    if (rangeType === 'time') {
                        _content += `${'价格'}:${numeral(open).format('0,0.00')}<br/>`;
                        _content += `${'均价'}:${avg_price ? numeral(avg_price).format('0,0.00') : '--'}<br/>`;
                    } else {
                        _content += `${'开盘价'}:${numeral(open).format('0,0.00')}<br/>`;
                        _content += `${'最高价'}:${numeral(high).format('0,0.00')}<br/>`;
                        _content += `${'最低价'}:${numeral(low).format('0,0.00')}<br/>`;
                        _content += `${'收盘价'}:${numeral(close_price).format('0,0.00')}<br/>`;
                    }
                    differ_range = parseFloat(differ_range);
                    let color = differ_range > 0 ? colorUp : colorDown;
                    _content += `${'涨跌幅'}:<span style="color:${color};">${differ_range > 0 ? '+' : ''}${numeral(differ_range).format('0,0.00')}%</span><br/>`;
                    _content += `${'涨跌额'}:<span style="color:${color};">${differ_range > 0 ? '+' : ''}${numeral(differ).format('0,0.00')}</span><br/>`;
                    _content += `${'成交量'}:${_formatVolume(volume)}<br/></div>`;
                    return _content;
                }
            },
            yAxis: [{// 主轴
                opposite: false,
                showFirstLabel: true,
                showLastLabel: true,
                max: (rangeType === 'time' || isNewStock) ? null : maxyAxis,
                min: (rangeType === 'time' || isNewStock) ? null : minyAxis,
                endOnTick: rangeType === 'time' || isNewStock,
                title: {
                    text: null
                },
                height: '70%',
                lineWidth: 1,
                lineColor: '#e6e6e6',
                plotLines: rangeType === 'time' ? [{
                    color: '#FEBEBF',
                    dashStyle: 'shortdash',
                    width: 2,
                    label: {
                        text: null
                    },
                    value: parseFloat(lastTimeVal.toFixed(2))// 昨日收盘价价格
                }] : [],
                // 分时曲线，以昨日收盘价格为中间线，向上下步进
                tickPositioner: rangeType === 'time' ? function() {
                    let positions = [
                        parseFloat((lastTimeVal - Math.max(Math.abs(yAxisMax - lastTimeVal), Math.abs(yAxisMin - lastTimeVal))).toFixed(2)),
                        parseFloat((lastTimeVal - Math.max(Math.abs(yAxisMax - lastTimeVal), Math.abs(yAxisMin - lastTimeVal)) / 2).toFixed(2)),
                        parseFloat(lastTimeVal.toFixed(2)),
                        parseFloat((lastTimeVal + Math.max(Math.abs(yAxisMax - lastTimeVal), Math.abs(yAxisMin - lastTimeVal)) / 2).toFixed(2)),
                        parseFloat((lastTimeVal + Math.max(Math.abs(yAxisMax - lastTimeVal), Math.abs(yAxisMin - lastTimeVal))).toFixed(2))
                    ]
                    return positions;
                } : !isNewStock ? function() {
                    // 分钟、日k等曲线，分别取区间最大值、中间值和最小值
                    return [minyAxis, parseFloat(((minyAxis + maxyAxis) / 2).toFixed(2)), maxyAxis];
                } : null,
                labels: rangeType === 'time' ? {
                    formatter: function() {
                        return `<span style="color:${this.value > parseFloat(lastTimeVal.toFixed(2)) ? colorUp : this.value === parseFloat(lastTimeVal.toFixed(2)) ? '#666' : colorDown}">${this.value}</span>`
                    }
                } : {}
            }, { // 成交量y坐标轴
                opposite: false,
                title: {
                    text: null
                },
                labels: {
                    enabled: false
                },
                top: '75%',
                height: '25%',
                offset: 0,
                lineWidth: 1,
                lineColor: '#e6e6e6',
            }, { // 分时曲线百分比y轴
                opposite: true,
                showFirstLabel: true,
                showLastLabel: true,
                top: '0%',
                height: '70%',
                lineWidth: 1,
                gridLineWidth: 0,
                lineColor: '#e6e6e6',
                // 分时曲线，以昨日收盘价格为中间线，向上下步进
                tickPositioner: rangeType === 'time' ? function () {
                    let positions = [
                        0 - parseFloat((Math.max(Math.abs(yAxisPctMax), Math.abs(yAxisPctMin))).toFixed(2)),
                        0 - parseFloat(((Math.max(Math.abs(yAxisPctMax), Math.abs(yAxisPctMin))) / 2).toFixed(2)),
                        0,
                        parseFloat(((Math.max(Math.abs(yAxisPctMax), Math.abs(yAxisPctMin))) / 2).toFixed(2)),
                        parseFloat((Math.max(Math.abs(yAxisPctMax), Math.abs(yAxisPctMin))).toFixed(2))
                    ]
                    return positions;
                } : null,
                labels: {
                    align: 'left',
                    x: 5,
                    y: 0,
                    formatter: rangeType === 'time' ? function() {
                        return `<span style="color:${this.value > 0 ? colorUp : this.value === 0 ? '#666' : colorDown}">${this.value}%</span>`;
                    } : null
                }
            }],
            series: [{
                type: 'candlestick',
                name: stockName,
                color: colorDown,
                lineColor: colorDown,
                upColor: colorUp,
                upLineColor: colorUp,
                data: kData,
                dataGrouping: {
                    enabled: false
                },
                showInLegend: false,
                yAxis: 0,
                visible: rangeType !== 'time'
            }, {
                type: 'column',
                data: volume,
                dataGrouping: {
                    enabled: false
                },
                name: '成交量',
                showInLegend: false,
                yAxis: 1
            },{
                type: 'line',
                name: stockName,
                dataGrouping: {
                    enabled: false
                },
                color: '#7cb5ec',
                data: lineData,
                showInLegend: false,
                visible: rangeType === 'time',
                yAxis: 0
            }, {
                type: 'line',
                name: stockName,
                color: 'transparent',
                dataGrouping: {
                    enabled: false
                },
                data: linePercentData,
                showInLegend: false,
                visible: rangeType === 'time',
                yAxis: 2
            },{
                type: 'line',
                color: '#FDB527',
                lineWidth: 1,
                data: ma5,
                name: 'MA5',
                dataGrouping: {
                    enabled: false
                },
                marker: {
                    symbol: 'circle',
                    lineWidth: 3,
                    fillColor: '#FDB527',
                    lineColor: '#FDB527'
                },
                yAxis: 0,
                visible: rangeType !== 'time'
            }, {
                type: 'line',
                color: '#6FCEF2',
                lineWidth: 1,
                data: ma10,
                name: 'MA10',
                dataGrouping: {
                    enabled: false
                },
                marker: {
                    symbol: 'circle',
                    lineWidth: 3,
                    fillColor: '#6FCEF2',
                    lineColor: '#6FCEF2'
                },
                yAxis: 0,
                visible: rangeType !== 'time'
            }, {
                type: 'line',
                color: '#F83FBC',
                lineWidth: 1,
                data: ma20,
                name: 'MA20',
                dataGrouping: {
                    enabled: false
                },
                marker: {
                    symbol: 'circle',
                    lineWidth: 3,
                    fillColor: '#F83FBC',
                    lineColor: '#F83FBC'
                },
                yAxis: 0,
                visible: rangeType !== 'time'
            }]
        });
    }

    render() {
        const { containerId } = this.state;
        return (<div id={containerId}/>);
    }
}

export default StockChartContainer;