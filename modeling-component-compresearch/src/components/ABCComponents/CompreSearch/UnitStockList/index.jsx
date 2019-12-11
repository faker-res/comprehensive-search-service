/**
 * @description desc
 * @date 2018-08-07
 * @author jywang
 */

import React, { Component } from 'react'
import { Card, Table, Row, Icon } from 'antd'
import PropTypes from 'prop-types'
// import { siteDomain, newsOrigin } from '../../constants';
import './index.scss';
import Loading from '../Loading';
import numeral from 'numeral'

class UnitStockList extends Component {
    numColor = (num, type) => {
        num = Number(num);
        if (isNaN(num)) {
            if (type === 'bg') {
                return 'nullbg'
            } else {
                return 'nullFontColor'
            }
        } else {
            if (type === 'bg') {
                if (num > 0) {
                    return 'redbg'
                } else if (num === 0) {
                    return 'nullbg'
                } else {
                    return 'greenbg'
                }
            } else {
                if (num > 0) {
                    return 'redFontColor'
                } else if (num === 0) {
                    return 'nullFontColor'
                } else {
                    return 'greenFontColor'
                }
            }
        }

    }
    numUnit = (num, unit) => {
        if (num) {
            num = Number(num)
            if (isNaN(num)) {
                return '--'
            } else {
                unit = unit || '';
                let num_length = (Math.floor(Math.abs(num)) + '').length;
                if (num_length < 5) {
                    return `${numeral(num).format('0,0.00')}` + unit;
                } else if (num_length < 9) {
                    return `${numeral(num / 10000).format('0,0.00')}万` + unit;
                } else if (num_length < 13) {
                    return `${numeral(num / 100000000).format('0,0.00')}亿` + unit;
                } else if (num_length < 17) {
                    return `${numeral(num / 1000000000000).format('0,0.00')}万亿` + unit;
                };
            }
        } else {
            return '--'
        }
    }
    filterVisible = () => {
        this.setState({
            filterDropdownVisible: true
        })
    }
    filterCheck = (type) => {
        this.setState({
            filterDropdownVisible: false
        })
        this.info = Object.assign({}, this.info || {});
        this.info.type = type;
        this.info.prior = this.props.prior;
        this.info.order = this.props.order;
        this.info.offset = (this.props.current - 1) * this.props.limit;
        this.props.getListParams(this.info);
    }
    handleChange = (pagination, filters, sorter) => {
        // console.log('Various parameters', pagination, filters, sorter);
        let { currentFilter } = this.state;
        this.info = Object.assign({}, this.info || {});
        this.info.offset = (pagination.current - 1) * this.props.limit;
        if (sorter.field) {
            this.info.prior = sorter.field;
            this.info.order = sorter.order === 'descend' ? 'DESC' : 'ASC';
        } else {
            this.info.prior = this.props.prior;
            this.info.order = this.props.order;
        }
        this.info.type = currentFilter || this.props.type;
        this.props.getListParams(this.info);
        this.setState({
            filteredInfo: filters,
            sortedInfo: sorter,
            filterDropdownVisible: false
        });
    }
    getColumns = () => {
        let { sortedInfo, filteredInfo, filterDropdownVisible } = this.state;
        let { filterList } = this.props;
        sortedInfo = sortedInfo || {};
        filteredInfo = filteredInfo || {};
        const columns = [
            {
            title: '全部',
            dataIndex: 'sec_name',
            key: 'sec_name',
            width: "8%",
            filterDropdown: <div className="filterDropdownBox">
                <ul>
                    {
                        filterList.map((item, idx) => {
                            return <li className={this.props.type === item.type ? 'active' : null} onClick={() => this.filterCheck(item.type)} key={idx}>{item.text}</li>
                        })
                    }
                </ul>
            </div>,
            filterIcon: <Icon type="filter" style={{ color: '#D8D8D8' }} onClick={() => this.filterVisible()} />,
            filterDropdownVisible: filterDropdownVisible,
            filteredValue: filteredInfo.name || null,
            onFilter: (value, record) => record.name.includes(value),
            render: (text, record) => {
                let dom = <div className="tablePublicName">
                    <div className="publicInfo">
                        {/*<h3><a href={`/entity-search/listed-company?stock_code=${record.abc_code}&stock_name=${record.sec_name}&com_uni_code=${record.com_uni_code}&sec_uni_code=${record.sec_uni_code}`} target="_blank">{text}</a></h3>*/}
                        <h3><a>{text}</a></h3>
                        <p>{record.abc_code}</p>
                    </div>
                </div>

                return dom;
            }
        }, {
            title: '现价',
            dataIndex: 'now',
            key: 'now',
            width: "13%",
            sorter: (a, b) => a.now - b.now,
            sortOrder: sortedInfo.columnKey === 'now' && sortedInfo.order,
            render: (text, record) => {
                let dom;
                if (record.stop_sign) {
                    dom = <div className="delist">
                        <span>{this.numUnit(text)}</span>
                        <span className={this.numColor(0, 'bg')}>停牌</span>
                    </div>
                } else {
                    dom = <div className="nowPrice">
                        <div className="todayPrice">
                            <span className={this.numColor(record.differ, 'font')}>{this.numUnit(text)}</span>
                            <span className={this.numColor(record.differ, 'font')}>{this.numUnit(record.differ)}</span>
                            <span className={this.numColor(record.differ_range, 'bg')}>{this.numUnit(record.differ_range, '%')}</span>
                        </div>
                        <div className="yesterdayPrice">
                            <span>昨收:{this.numUnit(record.yesterday_close)}</span><span>今开:{this.numUnit(record.today_open)}</span>
                        </div>
                    </div>
                }

                return dom;
            }
        },
        {
            title: '最高',
            dataIndex: 'high',
            key: 'high',
            width: "7%",
            sorter: (a, b) => a.high - b.high,
            sortOrder: sortedInfo.columnKey === 'high' && sortedInfo.order,
            render: (text, record) => {
                return <span style={{ color: '#DD4B39' }}>{this.numUnit(text)}</span>
            }
        }, {
            title: '最低',
            dataIndex: 'low',
            key: 'low',
            width: "7%",
            sorter: (a, b) => a.low - b.low,
            sortOrder: sortedInfo.columnKey === 'low' && sortedInfo.order,
            render: (text, record) => {
                return <span style={{ color: '#40A176' }}>{this.numUnit(text)}</span>
            }
        }, {
            title: '成交量',
            dataIndex: 'deal_amount',
            key: 'deal_amount',
            width: "8%",
            sorter: (a, b) => a.deal_amount - b.deal_amount,
            sortOrder: sortedInfo.columnKey === 'deal_amount' && sortedInfo.order,
            render: (text, record) => {
                return <span style={{ color: '#333333' }}>{this.numUnit(text, '手')}</span>
            }
        }, {
            title: '成交额',
            dataIndex: 'deal_vol',
            key: 'deal_vol',
            width: "8%",
            sorter: (a, b) => a.deal_vol - b.deal_vol,
            sortOrder: sortedInfo.columnKey === 'deal_vol' && sortedInfo.order,
            render: (text, record) => {
                return <span style={{ color: '#333333' }}>{this.numUnit(text)}</span>
            }
        }, {
            title: '总市值',
            dataIndex: 'total_market_value',
            key: 'total_market_value',
            width: "8%",
            sorter: (a, b) => a.total_markert_value - b.total_markert_value,
            sortOrder: sortedInfo.columnKey === 'total_markert_value' && sortedInfo.order,
            render: (text, record) => {
                return <span style={{ color: '#333333' }}>{this.numUnit(text)}</span>
            }
        }, {
            title: '总股本',
            dataIndex: 'total_stock_value',
            key: 'total_stock_value',
            width: "8%",
            sorter: (a, b) => a.total_stock_value - b.total_stock_value,
            sortOrder: sortedInfo.columnKey === 'total_stock_value' && sortedInfo.order,
            render: (text, record) => {
                return <span style={{ color: '#333333' }}>{this.numUnit(text)}</span>
            }
        }, {
            title: '流通市值',
            dataIndex: 'cir_market_value',
            key: 'cir_market_value',
            width: "9%",
            sorter: (a, b) => a.total_stock_value - b.total_stock_value,
            sortOrder: sortedInfo.columnKey === 'cir_market_value' && sortedInfo.order,
            render: (text, record) => {
                return <span style={{ color: '#333333' }}>{this.numUnit(text)}</span>
            }
        }, {
            title: <div className="tableTitleUnit"><span>市净率</span><span>MRQ</span></div>,
            dataIndex: 'pb',
            key: 'pb',
            sorter: (a, b) => a.pb - b.pb,
            width: "9%",
            sortOrder: sortedInfo.columnKey === 'pb' && sortedInfo.order,
            render: (text, record) => {
                return <span style={{ color: '#333333' }}>{this.numUnit(text)}</span>
            }
        }, {
            title: <div className="tableTitleUnit"><span>市盈率</span><span>TTM</span></div>,
            dataIndex: 'pe',
            key: 'pe',
            width: "9%",
            sorter: (a, b) => a.pe - b.pe,
            sortOrder: sortedInfo.columnKey === 'pe' && sortedInfo.order,
            render: (text, record) => {
                return <span style={{ color: '#333333' }}>{this.numUnit(text)}</span>
            }
        }, {
            title: <div className="tableTitleUnit"><span>市销率</span><span>TTM</span></div>,
            dataIndex: 'ps',
            key: 'ps',
            width: "9%",
            sorter: (a, b) => a.total_stock_value - b.total_stock_value,
            sortOrder: sortedInfo.columnKey === ' ps' && sortedInfo.order,
            render: (text, record) => {
                return <span style={{ color: '#333333' }}>{this.numUnit(text)}</span>
            }
        }];

        return columns;

    }
    constructor(props) {
        super(props)
        this.state = {
            filteredInfo: null,
            sortedInfo: null,
            currentFilter: null,
            filterDropdownVisible: false,
        }
    }
    componentDidMount() {
        console.log(this.props.type);
    }

    render() {
        const rootClass = `unitStockList`;
        let { data, current, limit, dataState } = this.props

        let pagination = {
            total: data && data.totalCount,
            defaultCurrent: current,
            defaultPageSize: limit
        }
        let dataSource = data && data.items || null;
        return (

            <Card title="全部股票" bordered={false} className={rootClass}>
                <Row hidden={dataState !== 'done' && dataState !== 'error'}>
                    <Table locale={{ emptyText: '暂无数据' }} pagination={pagination} className={`${rootClass}-table`} columns={this.getColumns()} dataSource={dataSource} onChange={this.handleChange} />
                </Row>
                <Row
                    type="flex"
                    justify="center"
                    style={{
                        minHeight: '900px'
                    }}
                    hidden={dataState !== 'pending'}>
                    <Loading />
                </Row>
            </Card>

        );
    }
}

export default UnitStockList

//props默认值
UnitStockList.defaultProps = {
    dataState: 'pending',
    cruuent: 1,
    limit: 10,
    filterList: [{ type: '', text: '全部' }, { type: 'HS', text: '沪深' }, { type: 'HK', text: '港股' }, { type: 'US', text: '美股' }],
    data: {
        "items": [
            {
                "key": 1,
                "com_uni_code": 50037,
                "sec_name": "国金鑫新",
                "ps": 213212,
                "now": 12.36,
                "high": 122.5,
                "total_markert_value": 132132,
                "yesterday_close": 15.36,
                "cir_market_value": 12313,
                "pe": 13212,
                "differ": -0.36,
                "abc_code": "501000.SH",
                "sec_uni_code": 16546123,
                "total_stock_value": 123123,
                "pb": 212,
                "deal_vol": 21211,
                "today_open": 12.56,
                "differ_range": 2.6,
                "low": 54.23,
                "deal_amount": 212
            },
            {
                "key": 2,
                "com_uni_code": 50037,
                "sec_name": "国金鑫新",
                "ps": 213212,
                "now": 12.36,
                "high": 122.5,
                "total_markert_value": 132132,
                "yesterday_close": 15.36,
                "cir_market_value": 12313,
                "pe": 13212,
                "differ": -0.36,
                "abc_code": "501000.SH",
                "sec_uni_code": 16546123,
                "total_stock_value": 123123,
                "pb": 212,
                "deal_vol": 21211,
                "today_open": 12.56,
                "differ_range": 2.6,
                "low": 54.23,
                "deal_amount": 212
            },
            {
                "key": 3,
                "com_uni_code": 50037,
                "sec_name": "国金鑫新",
                "ps": 213212,
                "now": 12.36,
                "high": 122.5,
                "total_markert_value": 132132,
                "yesterday_close": 15.36,
                "cir_market_value": 12313,
                "pe": 13212,
                "differ": -0.36,
                "abc_code": "501000.SH",
                "sec_uni_code": 16546123,
                "total_stock_value": 123123,
                "pb": 212,
                "deal_vol": 21211,
                "today_open": 12.56,
                "differ_range": 2.6,
                "low": 54.23,
                "deal_amount": 212
            },
            {
                "key": 4,
                "com_uni_code": 50037,
                "sec_name": "国金鑫新",
                "ps": 213212,
                "now": 12.36,
                "high": 122.5,
                "total_markert_value": 132132,
                "yesterday_close": 15.36,
                "cir_market_value": 12313,
                "pe": 13212,
                "differ": -0.36,
                "abc_code": "501000.SH",
                "sec_uni_code": 16546123,
                "total_stock_value": 123123,
                "pb": 212,
                "deal_vol": 21211,
                "today_open": 12.56,
                "differ_range": 2.6,
                "low": 54.23,
                "deal_amount": 212
            }
        ],
        "totalCount": 1245
    }

}

// props 类型

UnitStockList.propTypes = {


}
