/**
 * 股票图表组件
 * @author lzhang
 * @description 股票图表组件
 */
import React from 'react';
import PropTypes from 'prop-types';
import HighCharts from 'highcharts';

class Chart extends React.Component {
    constructor(props) {
        super(props);

        this.onClick = this.onClick.bind(this);

        this.state = {
            // 当前图表ID
            chartId: new Date().getTime(),
            // 图表信息
            data: this.generateChartConfig(props).config,
            // 图表尺寸
            size: props.size || {}
        };
    }

    /**
     * 获取指定对象的数据类型
     * 
     * @param {any} obj 
     */
    getObjType(obj) {
        if (typeof obj === 'undefined') {
            return 'undefined';
        }
        let type = Object.prototype.toString.call(obj);
        if (type.indexOf('String') != -1) {
            return 'string';
        } else if (type.indexOf('Number') != -1) {
            return 'number';
        } else if (type.indexOf('Array') != -1) {
            return 'array';
        } else if (type.indexOf('Null') != -1) {
            return 'null';
        } else if (type.indexOf('Boolean') != -1) {
            return 'boolean';
        } else if (type.indexOf('Object') != -1) {
            return 'object';
        }
        return 'unknown';
    }

    onClick() {
        let {onClick} = this.props;
        let {data = {}} = this.state;

        if (onClick) {
            onClick({
                type: 'chart',
                config: data
            });
        }
    }

    generateChartConfig(props = {}, options = {}) {
        // 覆盖图表的一些配置参数
        let boxSize = props.size;
        let result  = Object.assign({}, props.config);

        let chartData = typeof result.chart_data === 'string' ? JSON.parse(result.chart_data || '{}') : result.chart_data;
        chartData = Object.assign({}, chartData, {
            rangeSelector: {
                enabled: false
            },
            title: {
                text: null
            },
            subtitle: {
                text: null
            },
            scrollbar: {
                enabled: false
            },
            navigator: {
                enabled: false
            },
            credits: {
                enabled: false
            },
            exporting: {
                enabled: false
            }
        }, options);

        // 强制覆盖图表尺寸配置
        chartData.chart = chartData.chart || {};
        if (typeof boxSize.width !== 'undefined') {
            chartData.chart.width  = boxSize.width;
        }
        if (typeof boxSize.height !== 'undefined') {
            chartData.chart.height = boxSize.height;
        }
        // 图表增加chart_opts属性
        result.chartConfig = chartData;
        return {
            config: result
        };
    }

    renderChart() {
        let {chartId} = this.state;
        let {chartType = ''} = this.props;
        let chartContainer = this.chartBox || document.getElementById(`chart-${chartId}`);
        
        if (chartContainer) {
            let data = this.generateChartConfig(this.oldProps).config;

            HighCharts.chart(chartContainer, data.chartConfig);
        }
    }

    strDiff(str1, str2) {
        let type1 = this.getObjType(str1),
            type2 = this.getObjType(str2);

        if (type1 === type2 && type2 === 'string') {
            let min = Math.min(str1.length, str2.length),
                max = Math.max(str1.length, str2.length);

            for (let i = 0; i < min; i++) {
                if (str1[i] != str2[i]) {
                    return {code: -3,
                        msg: `str1中的第${i + 1}个字符:${str1.slice(Math.max(i - 10, 0), i)}(${str1[i]})${str1.slice(i + 1, Math.min(i + 10, min))}和str2中的第${i + 1}个字符:${str2.slice(Math.max(i - 10, 0), i)}(${str2[i]})${str2.slice(i + 1, Math.min(i + 10, min))}不一致`};
                }
            }
            if (min !== max) {
                return {code: -2, msg: '字符串长度不一致'};
            } else {
                return {code: 0, msg: '字符串相等'};
            }
            
        } else {
            return {code: -1, msg: '数据类型不一致'};
        }
    }

    componentWillMount() {
        HighCharts.setOptions({
            global: {
                useUTC: false
            }
        });
    }

    shouldComponentUpdate(nextProps, nextState) {
        // 比对两次图表配置和尺寸是否相同，不同则更新
        let prev = JSON.stringify((this.oldProps || {}).config || {});
        let cur  = JSON.stringify(nextProps.config || {});

        if (prev != cur) {
            // 图表进行重绘
            this.oldProps = Object.assign({}, nextProps);
            this.renderChart();
            return true;
        }
        return false;
    }

    componentDidMount() {
        // 初始化oldProps
        this.oldProps = Object.assign({}, this.props);
        this.renderChart();
    }

    render() {
        let {size = {}} = this.props;
        let {data = {}, chartId} = this.state;

        return (
            <div id={`chart-${chartId}`}
                ref={e => this.chartBox = e}
                className={`${this.props.clsPre}-chart`}
                style={{
                    height: isNaN(size.height) ? size.height : size.height + 'px',
                }}
                onClick={this.onClick}>
            </div>
        );
    }
}

Chart.propTypes = {
    // 自定义组件cls前缀
    clsPre: PropTypes.string,
    // 附加组件cls
    appendCls: PropTypes.string,
    // 自定义样式
    style: PropTypes.object,
    // 图表类型
    chartType: PropTypes.string,
    // 图表配置
    config: PropTypes.object,
    // 图表尺寸
    size: PropTypes.shape({
        width: PropTypes.oneOfType([PropTypes.number, PropTypes.string]),
        height: PropTypes.oneOfType([PropTypes.number, PropTypes.string])
    })
};

Chart.defaultProps = {
    clsPre: 'abc-c',
    appendCls: '',
    chartType: 'chart',
    config: {},
    size: {
        width: 0,
        height: 0
    }
};

export default Chart;