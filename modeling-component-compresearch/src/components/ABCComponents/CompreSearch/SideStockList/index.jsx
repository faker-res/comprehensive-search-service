/**
 * @description 个股证券列表组件
 * @author kygeng
 * date: 2018-05-17
 */
// lib
import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { Layout, List, Row, Col, Icon } from 'antd'
// import { translate } from 'react-i18next';
import classNames from 'classnames'
import numeral from 'numeral'
// style
import './index.scss'

class SideStockList extends Component {
    constructor(props) {
        super(props)

        this._onItemClick = this._onItemClick.bind(this);
    }

    _onItemClick(event, item) {
        if (this.props.onStockItemClick) {
            this.props.onStockItemClick(item);
        }
    }
    
    render() {
        // let { t } = this.props;
        let { title, stocks, more, UrlLink} = this.props;

        const Header = (
            <div className="side-stock-list-header">
                <span className="stock-name">股票</span>
                <span className="stock-price">当前价</span>
                <span className="stock-diff">涨跌幅</span>
            </div>
        )

        return (
            <Layout className="abc-side-stock-list-wrap">
                <Layout.Header className="abc-side-stock-list-header">
                    <span>{title}</span>
                    {more ? <a className='more' href={UrlLink} target='_blank'>更多<Icon type="right"/></a> : null}
                    {/*{more ? <a className='more' href={'/entity-search/analysis-more/?indu_name=非银金融'} target='_blank'>更多></a> : null}*/}
                </Layout.Header>
                <Layout.Content className="abc-side-stock-list-content">
                    <List className="side-stock-list-wrap"
                        header={Header}
                        itemLayout="vertical"
                        split={false}
                        dataSource={stocks} renderItem={(stock) => {
                            let { stock_data } = stock.stock_newsset || {};
                            let { current_price: curPrice, zdf: diffPercent, suspend } = stock_data;
                            curPrice = parseFloat(curPrice);
                            diffPercent = parseFloat(diffPercent);
                            let diffStatus = ( suspend || diffPercent === 0) ? 'suspend' : diffPercent > 0 ? 'rise' : 'drop';
                            let priceCls = classNames('price', diffStatus);
                            let diffCls = classNames('diff', diffStatus);
                            return (
                                <Row type="flex" justify="space-between" align="middle" className="side-stock-list-item-wrap" onClick={(e) => this._onItemClick(e, stock)}>
                                    <div className="name-code">
                                        <div className="name">{stock['sec_name']}</div>
                                        <div className="code">{stock['abc_code']}</div>
                                    </div>
                                    <div className={priceCls}>{numeral(curPrice).format('0,0.00')}</div>
                                    <div className={diffCls}>{numeral(diffPercent * 100).format('0.00') + '%'}</div>
                                </Row>
                            )
                        }}/>
                </Layout.Content>
            </Layout>
        )
    }
}

SideStockList.defaultProps = {
    title: '',
    stocks: [],
    onStockItemClick: null
}

SideStockList.propTypes = {
    title: PropTypes.string,
    stocks: PropTypes.oneOfType([
        PropTypes.array,
        PropTypes.object
    ]).isRequired,
    onStockItemClick: PropTypes.func
}

export default (SideStockList);

