/**
 * @description 个股证券K线图组件
 * @author kygeng
 * date: 2018-05-17
 */
// lib
import React, { Component } from 'react'
import PropTypes from 'prop-types'
// import Highcharts from 'highcharts/highstock'
import moment from 'moment'
// import numeral from 'numeral'
import ask from '../../../../lib/ask';
// import { translate } from 'react-i18next';
import { Row, Spin } from 'antd'
import classNames from 'classnames'
import _ from 'lodash'
import isEmpty from 'lodash/isEmpty';
// import i18n from '../../i18n'
// style
import './index.scss'
// component
// import Loading from '../../components/Loading/'
import StockChartContainer from './StockChartContainer';
// import NoDataTip from '../../components/NoDataTip';

class StockKChartCard extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // 分时数据
            _stockTimeData: {},
            // 5分钟数据
            _stockFiveData: {},
            // 30分钟数据
            _stockThirtyData: {},
            // 日数据
            _stockDayData: {},
            // 月数据
            _stockMonthData: {},
            // 当前数据
            _chartData: {},
            _stockCode: props.stockCode,
            _stockName: props.stockName,
            _curRange: 'day',// time, five, thirty, day, month
            _loadStatus: 'loading',//success, failed
            _width: props.width || 500,
            _height: props.height || 300
        }
    }

    componentDidMount() {
        this._loadStockChartData(this.state._curRange)
    }

    componentDidUpdate(prevProps, prevState) {
        if (this.props.stockCode !== this.state._stockCode && !isEmpty(this.props.stockCode)) {
            this.setState({
                // 分时数据
                _stockTimeData: {},
                // 5分钟数据
                _stockFiveData: {},
                // 30分钟数据
                _stockThirtyData: {},
                // 日数据
                _stockDayData: {},
                // 月数据
                _stockMonthData: {},
                _stockCode: this.props.stockCode
            },() => {
                this._loadStockChartData(this.state._curRange);
            });
        }
    }

    _onRangeSelect = (rangeType) => {
        if (rangeType === this.state._curRange) return;
        this._loadStockChartData(rangeType);
    }

    _loadStockChartData = (rangeType) => {
        let self = this;

        let { stockCode } = this.props;
        let method = rangeType === 'time' ? 'StockTimeLine' : 'StockKLine';
        let _params = { stock_code: stockCode };
        if (rangeType !== 'time') {
            _params.graph_type = rangeType === 'five' ? 5 :
                rangeType === 'thirty' ? 30 :
                rangeType === 'day' ? 'd' : 'm';
        }

        // 判断是否有缓存数据
        let now = new Date().getTime();
        if (rangeType === 'time') {
            if (!_.isEmpty(this.state._stockTimeData)) {
                this.setState({ _curRange: rangeType,  _chartData: Object.assign({}, this.state._stockTimeData)});
                return;
            }
            // 个股分时股票价格数据为当天数据，从上午开市时间算起
            _params.start_time = '0930';
        } else if (rangeType === 'five') {
            if (!_.isEmpty(this.state._stockFiveData)) {
                this.setState({ _curRange: rangeType, _chartData: Object.assign({}, this.state._stockFiveData) });
                return;
            }
            // 5分钟展示最近三个交易日数据，避免出现非交易情况，例如假期等，起始时间向前14天
            _params.start_time = moment().subtract(14, 'days').format('YYYY-MM-DD');
        } else if (rangeType === 'thirty') {
            if (!_.isEmpty(this.state._stockThirtyData)) {
                this.setState({ _curRange: rangeType, _chartData: Object.assign({}, this.state._stockThirtyData) });
                return;
            }
            // 30分钟展示最近十个交易日数据，避免出现非交易情况，例如假期等，起始时间向前30天
            _params.start_time = moment().subtract(30, 'days').format('YYYY-MM-DD');
        } else if (rangeType === 'day') {
            if (!_.isEmpty(this.state._stockDayData)) {
                this.setState({ _curRange: rangeType, _chartData: Object.assign({}, this.state._stockDayData) });
                return;
            }
            // 日线展示最近180个交易日数据
            _params.start_time = moment().subtract(180, 'days').format('YYYY-MM-DD');
        } else if (rangeType === 'month') {
            if (!_.isEmpty(this.state._stockMonthData)) {
                this.setState({ _curRange: rangeType, _chartData: Object.assign({}, this.state._stockMonthData) });
                return;
            }
            // 月线线展示最近10年数据
            _params.start_time = moment().subtract(10, 'years').format('YYYY-MM-DD');
        }
        this.setState({ _loadStatus: 'loading' });
        ask(method, {
            params: _params
        })
        .then((resp) => {
            let { code, data, message } = resp;
            if (code === 200 && !isEmpty(data) && !isEmpty(data.company)) {
                let { company } = data || {};
                if (rangeType === 'time') {
                    this.setState({
                        _loadStatus: 'success',
                        _curRange: rangeType,
                        _chartData: Object.assign({}, company),
                        _stockTimeData: Object.assign({}, company)
                    })
                } else if (rangeType === 'five') {
                    this.setState({
                        _loadStatus: 'success',
                        _curRange: rangeType,
                        _chartData: Object.assign({}, company),
                        _stockFiveData: Object.assign({}, company)
                    })
                } else if (rangeType === 'thirty') {
                    this.setState({
                        _loadStatus: 'success',
                        _curRange: rangeType,
                        _chartData: Object.assign({}, company),
                        _stockThirtyData: Object.assign({}, company)
                    })
                } else if (rangeType === 'day') {
                    this.setState({
                        _loadStatus: 'success',
                        _curRange: rangeType,
                        _chartData: Object.assign({}, company),
                        _stockDayData: Object.assign({}, company)
                    })
                } else if (rangeType === 'month') {
                    this.setState({
                        _loadStatus: 'success',
                        _curRange: rangeType,
                        _chartData: Object.assign({}, company),
                        _stockMonthData: Object.assign({}, company)
                    })
                }
            } else {
                throw new Error(`Response Exception:${message},code:${code}`);
            }
        },(err) => {
            console.error(err);
            this.setState({
                _curRange: rangeType,
                _chartData: {},
                _loadStatus: 'failed'
            })
        })
        .catch((error) => {
            console.error(error);
            this.setState({ _loadStatus: 'failed', _curRange: rangeType, _chartData: {} });
        })
    }

    render() {
        let { _stockCode, _curRange, _chartData ,_loadStatus, _width, _height } = this.state;
        let { t, chartData, stockCode, stockName } = this.props;

        let cStyle = { width: `${_width}px`, height: `${_height}px` };
        let rangeCls = classNames('time-range-wrap', _curRange);
        return (
            <div className="abc-stock-kchart-card-wrap" style={cStyle}>
                <Row type="flex" justify="space-between" align="middle" className="abc-stock-kchart-card-toolbar">
                    <div className={rangeCls}>
                        <span onClick={(e) => this._onRangeSelect('time')}
                            className={classNames('range-item', {'active':_curRange === 'time'})}>分时</span>
                        <span onClick={(e) => this._onRangeSelect('five')}
                            className={classNames('range-item', {'active':_curRange === 'five'})}>5分钟</span>
                        <span onClick={(e) => this._onRangeSelect('thirty')}
                            className={classNames('range-item', {'active':_curRange === 'thirty'})}>30分钟</span>
                        <span onClick={(e) => this._onRangeSelect('day')}
                            className={classNames('range-item', {'active':_curRange === 'day'})}>日线</span>
                        <span onClick={(e) => this._onRangeSelect('month')}
                            className={classNames('range-item', {'active':_curRange === 'month'})}>月线</span>
                    </div>
                </Row>
                {/* k线chart容器 */}
                <StockChartContainer
                    rangeType={_curRange}
                    stockName={stockName}
                    stockCode={stockCode}
                    chartData={_chartData}/>
                {/* 数据有误 */}
                {
                    _loadStatus === 'failed' &&
                    <div className="abc-stock-kchart-card-empty">
                        <span>数据加载失败 :(</span>
                    </div>
                }
                {/* 正在更新数据 */}
                {
                    _loadStatus === 'loading' &&
                    <Row className="abc-stock-kchart-card-loading" type="flex" justify="center" align="middle">
                        <Spin/>
                    </Row>
                }
            </div>
        )
    }
    
}

StockKChartCard.defaultProps = {
    stockCode: '',
    stockName: '',
    rangeType: 'day',
    showRangeSelector: false,
    width: 500,
    height: 300,
    chartData: {}
}

StockKChartCard.propTypes = {
    stockCode: PropTypes.string.isRequired,
    stockName: PropTypes.string.isRequired,
    rangeType: PropTypes.string.isRequired,
    showRangeSelector: PropTypes.bool,
    width: PropTypes.number,
    height: PropTypes.number,
    chartData: PropTypes.oneOfType([
        PropTypes.object,
        PropTypes.array
    ]),
    onSelectRange: PropTypes.func
}

export default (StockKChartCard);