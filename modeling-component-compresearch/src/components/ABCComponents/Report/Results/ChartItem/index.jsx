/**
 * 图表组件
 * @author lzhang
 * @description 提供LiveChart、表格、图片的预览功能
 */
import React from 'react';
import PropTypes from 'prop-types';

// components
import ABCChart from './chart/';
import Template from './../../Template/';
import ErrorBoundary from './error';

// images
const ICON_LOADING = 'data:image/gif;base64,R0lGODlhDwAPAPfMAOPj4/f39+vr697e3vz8/PPz8/v7+/r6+uLi4rW1tezs7Orq6t/f3/T09MTExMDAwOnp6c7OzuXl5bS0tMvLy/n5+c3NzdHR0QAAALOzs729vcjIyLa2tqmpqfb29s/Pz8LCwszMzLy8vKysrLu7u8PDw/X19dTU1O3t7eHh4eDg4P///7q6utzc3O/v793d3be3t6enp+Tk5K6urufn58XFxdPT08nJya+vr6qqqqurq/Hx8dDQ0LGxsbi4uOjo6NXV1bKysoCAgHd3d9nZ2a2trbm5uZ2dncrKytvb26ioqHt7e9bW1qKiosfHxxsbG6Wlpebm5oGBgfDw8IaGhnV1dTMzM76+vqGhoY2NjXp6ep6eniAgIHR0dIODg3x8fEpKSjY2NkRERC4uLjIyMsHBwfj4+EtLS46OjlJSUpqampmZmVdXV2tra9LS0qampoWFhfLy8g4ODi0tLZKSkklJSUZGRmlpaU5OTm1tbdfX1319fYKCgpiYmGNjY6SkpMbGxpeXl35+fp+fn11dXSgoKGpqajg4OGFhYV5eXmVlZRQUFDQ0NAEBAb+/vxgYGEdHR3FxcaOjo3Nzc39/fx4eHjk5OdjY2EVFRYmJiUFBQYSEhIqKiiQkJJubm5CQkAMDA2dnZ09PT2RkZHZ2dllZWVVVVRYWFpycnC8vLxISEgUFBTc3NxMTEyEhIRUVFRcXF7CwsFtbW5GRkRkZGSMjIxAQECkpKV9fXx0dHTExMXBwcBoaGggICCsrKzU1NREREQwMDGBgYNra2khISO7u7lRUVJaWlqCgoCwsLP7+/v39/f///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH/C05FVFNDQVBFMi4wAwEAAAAh/wtYTVAgRGF0YVhNUDw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo5QzY3OUVCQ0UzQ0YxMUU3OUUwQ0Y5NkM4RTE5QTg1NiIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDo5QzY3OUVCREUzQ0YxMUU3OUUwQ0Y5NkM4RTE5QTg1NiI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjlDNjc5RUJBRTNDRjExRTc5RTBDRjk2QzhFMTlBODU2IiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjlDNjc5RUJCRTNDRjExRTc5RTBDRjk2QzhFMTlBODU2Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+Af/+/fz7+vn49/b19PPy8fDv7u3s6+rp6Ofm5eTj4uHg397d3Nva2djX1tXU09LR0M/OzczLysnIx8bFxMPCwcC/vr28u7q5uLe2tbSzsrGwr66trKuqqainpqWko6KhoJ+enZybmpmYl5aVlJOSkZCPjo2Mi4qJiIeGhYSDgoGAf359fHt6eXh3dnV0c3JxcG9ubWxramloZ2ZlZGNiYWBfXl1cW1pZWFdWVVRTUlFQT05NTEtKSUhHRkVEQ0JBQD8+PTw7Ojk4NzY1NDMy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';

// style
import './index.scss';

class ChartItem extends React.Component {

    constructor(props) {
        super(props);

        this.updatePrevProps = this.updatePrevProps.bind(this);
        
        this.updatePrevProps(props);

        // 图表是否已渲染
        this.has_rendered = props.manualRender ? props.render : true;

        this.state = {
            // 图表信息
            data: this.generateChartConfig(props).config
        };
    }

    isLiveChart(chart) {
        // 判断是否是图表
        return chart.chart_type !== 'BITMAP_CHART' && chart.chart_data && !!chart.chart_data.trim();
    }

    generateChartConfig(props = {}) {
        // 覆盖图表的一些配置参数

        let box_size = props.size;
        let result   = Object.assign({}, props.config);

        if (!this.isLiveChart(result)) {
            // 图片直接返回
            return {
                config: result
            };
        }

        let chart_data = {};
        
        try {
            chart_data = typeof result.chart_data === 'string' ? JSON.parse(result.chart_data || '{}') : result.chart_data;
        } catch (e) {
            chart_data = {};
        }
        
        chart_data = Object.assign({}, chart_data, {
            title: {
                text: null
            },
            subtitle: {
                text: null
            },
            credits: {
                enabled: false
            },
            exporting: {
                enabled: false
            }
        });

        // 强制覆盖图表尺寸配置
        chart_data.chart = chart_data.chart || {};
        chart_data.chart.width  = box_size.width;
        chart_data.chart.height = box_size.height;

        // 图表增加chart_opts属性
        result.chart_opts = chart_data;

        return {
            config: result
        };
    }

    updatePrevProps(props) {
        let {
            // 待渲染的图表配置
            config = {},
            // 图表容器尺寸
            size = {
                width: 0,
                height: 0
            }
        } = Object.assign({}, props);

        this._prevProps = {
            config: Object.assign({}, config),
            size: size
        };
    }

    shouldComponentUpdate(nextProps, nextState) {
        // 强制每次渲染
        if (nextProps.renderAnyTime) {
            return true;
        }

        // 比对两次图表配置和尺寸是否相同，不同则更新
        let prev = JSON.stringify({cfg: this._prevProps.config, size: this._prevProps.size});
        let cur  = JSON.stringify({cfg: nextProps.config, size: nextProps.size});

        if (nextProps.manualRender && nextProps.render && !this.has_rendered) {
            // 当开启手动渲染开关后 当图表可见且未渲染时触发渲染逻辑
            this.has_rendered = true;

            // 缓存当前配置信息
            this.updatePrevProps(Object.assign({}, nextProps));
            return true;
        }

        if (prev !== cur) {
            // 图表进行重绘
            this.updatePrevProps(Object.assign({}, nextProps));
            return true;
        }
        return false;
    }

    componentWillReceiveProps(nextProps) {
        this.setState({
            // 图表信息
            data: this.generateChartConfig(nextProps).config
        });
    }

    render() {
        let isLiveChart = this.isLiveChart(this.props.config);
        let imgUrl = this.state.data.image_url;

        let confidence = this.state.data.confidence;
        return (
            <div className={`${this.props.clsPre}-chart-box`}
                style={{
                    height: this.props.size.height + 'px',
                    backgroundImage: `url(${this.has_rendered ? '' : ICON_LOADING})`
                }}
                onClick={(e) => {
                    if (typeof this.props.onClick === 'function') {
                        this.props.onClick({
                            type: isLiveChart ? 'chart' : 'img',
                            config: this.props.config
                        });
                    }
                }}>

                <Template isShow={this.has_rendered}>
                    {/* image */}
                    <Template isShow={!isLiveChart || (parseFloat(confidence || 0) < 0.5)}>
                        <img src={imgUrl}
                            alt={imgUrl}
                            style={{
                                width: '100%'
                            }}/>
                    </Template>
                    
                    {/* livechart */}
                    <Template isShow={isLiveChart && (parseFloat(confidence || 0) >= 0.5)}>
                        <ErrorBoundary errorView={this.state.data.image_url ? (
                            <img src={imgUrl} alt={this.state.data.image_url} style={{width: '100%'}} />
                        ) : null}>

                            <ABCChart size={this.props.size} config={{
                                chart_data: this.state.data.chart_opts
                            }} />
                        </ErrorBoundary>
                    </Template>
                </Template>
            </div>
        );
    }
}

ChartItem.propTypes = {
    // 自定义组件cls前缀
    clsPre: PropTypes.string,
    // 附加组件cls
    appendCls: PropTypes.string,
    // 自定义样式
    style: PropTypes.object,
    // 图表配置
    config: PropTypes.object,
    // 图表尺寸
    size: PropTypes.shape({
        width: PropTypes.oneOfType([PropTypes.number, PropTypes.string]),
        height: PropTypes.oneOfType([PropTypes.number, PropTypes.string])
    }),
    // 手动渲染图表
    manualRender: PropTypes.bool,
    // 是否需要渲染（manualRender开启后有效）
    render: PropTypes.bool,
    // 是否被点击
    onClick: PropTypes.func
};

ChartItem.defaultProps = {
    clsPre: 'abc-c',
    appendCls: '',
    config: {},
    size: {
        width: 0,
        height: 0
    },
    manualRender: false,
    render: false,
    onClick: () => {}
};

export default ChartItem;