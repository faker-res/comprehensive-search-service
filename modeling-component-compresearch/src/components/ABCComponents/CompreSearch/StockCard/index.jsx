/**
 * @description 个股证券详情卡片
 * @author kygeng
 * date: 2018-05-15
 */
// lib
import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { withRouter } from 'react-router-dom'
import { Layout, Row, Col, Icon } from 'antd'
import _ from 'lodash'
import classNames from 'classnames'
import numeral from 'numeral'
// import { translate } from 'react-i18next';
// import moment from 'moment'
// import ask from '../../lib/ask'
// style
import './index.scss'
// component
import MarketAttentionCard from '../MarketAttentionCard/'
import StockKChartCard from '../StockKChartCard/'

import { time_data, five_data, thirty_data, day_data, month_data } from '../StockKChartCard/data'
// import { legacySiteOrigin, siteMainOrigin } from '../../constants';

// 域名链接到主站线上正式(重构临时方案)
// const origin = legacySiteOrigin;

class StockCard extends Component {
    constructor(props) {
        super(props)
    }

    unitsFormat(num){
        let oNum = isNaN(parseFloat(num)) ? 0 : parseFloat(num);
        let num_length = ( Math.floor(Math.abs(oNum)) + '').length;
        if (num_length < 5){
            return `${numeral(oNum).format('0,0.00')}`;
        } else if (num_length < 9){
            return `${numeral(oNum / 10000).format('0,0.00')}万`;
        } else if (num_length < 13){
            return `${numeral(oNum / 100000000).format('0,0.00')}亿`;
        } else if (num_length < 17){
            return `${numeral(oNum / 1000000000000).format('0,0.00')}万亿`;
        }
    }

    render() {
        let { t } = this.props;
        const {date} = this.props.transacInfo;
        let { stock_name, stock_code, abc_code, com_uni_code, sec_uni_code } = this.props.companyData;
        // 今日开盘价、昨日收盘价、当前最高价、当前最低价
        let { tdOpeningRate, ytdCloseingRate, highRate, lowRate} = this.props.stockData;
        tdOpeningRate = numeral(tdOpeningRate).format('0,0.00');
        ytdCloseingRate = numeral(ytdCloseingRate).format('0,0.00');
        highRate = numeral(highRate).format('0,0.00');
        lowRate = numeral(lowRate).format('0,0.00');

        // 成交量、成交额、总市值、总股本、流通市值、市净率、市盈率、市销率
        let { volume, amount, mktCap, capitalization, tradedMarketValue,
            mostRecentQuarter, ttm, salesRatio } = this.props.stockData;
        volume = `${this.unitsFormat(volume / 100)}手`;//一手 = 100股
        amount = this.unitsFormat(amount);
        mktCap = this.unitsFormat(mktCap);
        capitalization = this.unitsFormat(capitalization);
        tradedMarketValue = this.unitsFormat(tradedMarketValue);
        mostRecentQuarter = numeral(mostRecentQuarter).format('0.00');
        ttm = numeral(ttm).format('0.00');
        salesRatio = numeral(salesRatio).format('0.00');
        
        let { curPrice, diffPercent, suspend, unit } = this.props.stockData;
        let diffPrice = 0;
        diffPercent = parseFloat(diffPercent);
        curPrice = parseFloat(curPrice);
        // diffPrice = diffPercent > 0 ? (curPrice - curPrice / (1 + diffPercent)) : (curPrice - curPrice / (1 + diffPercent));
        diffPrice = curPrice - curPrice / (1 + diffPercent);
        curPrice = curPrice.toFixed(2);
        diffPrice = diffPrice.toFixed(2);
        let diffStatus = (suspend || diffPercent === 0) ? 'suspend' : diffPercent > 0 ? 'rise' : 'drop';
        let headerClass = classNames('abc-stock-card-header', diffStatus);
        const diffPercent_str = diffPercent > 0 ? `+${(diffPercent * 100).toFixed(2)}%` : `${(diffPercent * 100).toFixed(2)}%`;
        
        let { markets } = this.props;
        const MarketCards = markets.map(_marketItem => {
            return (
                <MarketAttentionCard key={_marketItem.type} type={_marketItem.type}
                    title={_marketItem.title} titleColor={_marketItem.titleColor}
                    subTitle={_marketItem.subTitle} value={_marketItem.value}
                    percent={_marketItem.percent} chart={_marketItem.chart} />
            )
        });

        return (
            <Layout className="abc-stock-card-wrap">
                <Layout.Header className={headerClass}>
                    <span className="card-date">{date.substring(0,10)}</span>
                    <span className="name">{stock_name}</span>
                    <span className="code">（{abc_code}）</span>
                    <span className="price">{`${unit}${curPrice}`}</span>
                    <Icon type="arrow-up" />
                    <Icon type="arrow-down" />
                    <span className="diff">{diffPrice}({diffPercent_str})</span>
                </Layout.Header>
                <Row type="flex" justify="space-between" align="middle" className="abc-stock-card-content">
                    <Col className="chart-wrap">
                        <StockKChartCard
                            stockCode={abc_code}
                            stockName={stock_name}
                            width={530} height={240} />
                    </Col>
                    <Col className="info-wrap">
                        <Row type="flex" justify="space-between" align="middle" className="item-wrap">
                            <Row type="flex" justify="space-between" align="middle" className="sub-item-wrap">
                                <Col className="label">今开</Col>
                                <Col className="value rise">{tdOpeningRate}</Col>
                            </Row>
                            <Row type="flex" justify="space-between" align="middle" className="sub-item-wrap">
                                <Col className="label">昨收</Col>
                                <Col className="value">{ytdCloseingRate}</Col>
                            </Row>
                        </Row>
                        <Row type="flex" justify="space-between" align="middle" className="item-wrap">
                            <Row type="flex" justify="space-between" align="middle" className="sub-item-wrap">
                                <Col className="label">最高</Col>
                                <Col className="value rise">{highRate}</Col>
                            </Row>
                            <Row type="flex" justify="space-between" align="middle" className="sub-item-wrap">
                                <Col className="label">最低</Col>
                                <Col className="value drop">{lowRate}</Col>
                            </Row>
                        </Row>
                        <div className="divider"></div>
                        <Row type="flex" justify="space-between" align="middle" className="item-wrap">
                            <Row type="flex" justify="space-between" align="middle" className="sub-item-wrap">
                                <Col className="label">成交量</Col>
                                <Col className="value">{volume}</Col>
                            </Row>
                            <Row type="flex" justify="space-between" align="middle" className="sub-item-wrap">
                                <Col className="label">流通市值</Col>
                                <Col className="value">{tradedMarketValue}</Col>
                            </Row>
                        </Row>
                        <Row type="flex" justify="space-between" align="middle" className="item-wrap">
                            <Row type="flex" justify="space-between" align="middle" className="sub-item-wrap">
                                <Col className="label">成交额</Col>
                                <Col className="value">{amount}</Col>
                            </Row>
                            <Row type="flex" justify="space-between" align="middle" className="sub-item-wrap">
                                <Col className="label">
                                    市净率
                                    {/* <i className="iconfont" dangerouslySetInnerHTML={{__html:'&#xe622;'}}></i> */}
                                </Col>
                                <Col className="value">{mostRecentQuarter}</Col>
                            </Row>
                        </Row>
                        <Row type="flex" justify="space-between" align="middle" className="item-wrap">
                            <Row type="flex" justify="space-between" align="middle" className="sub-item-wrap">
                                <Col className="label">总市值</Col>
                                <Col className="value">{mktCap}</Col>
                            </Row>
                            <Row type="flex" justify="space-between" align="middle" className="sub-item-wrap">
                                <Col className="label">
                                    市盈率
                                    {/* <i className="iconfont" dangerouslySetInnerHTML={{ __html: '&#xe623;' }}></i> */}
                                </Col>
                                <Col className="value">{ttm}</Col>
                            </Row>
                        </Row>
                        <Row type="flex" justify="space-between" align="middle" className="item-wrap">
                            <Row type="flex" justify="space-between" align="middle" className="sub-item-wrap">
                                <Col className="label">总股本</Col>
                                <Col className="value">{capitalization}</Col>
                            </Row>
                            <Row type="flex" justify="space-between" align="middle" className="sub-item-wrap">
                                <Col className="label">
                                    市销率
                                    {/* <i className="iconfont" dangerouslySetInnerHTML={{ __html: '&#xe623;' }}></i> */}
                                </Col>
                                <Col className="value">{salesRatio}</Col>
                            </Row>
                        </Row>
                    </Col>
                </Row>
                <Layout.Content className="abc-stock-card-market">
                    <div className="header-title">
                    市场关注度
                    {/* {t('StockCard.t_market_attention', { defaultValue: '市场关注度'})} */}
                    </div>
                    <Row type="flex" justify="space-between" align="middle">
                        {MarketCards}
                    </Row>
                </Layout.Content>
                <Layout.Content className="abc-stock-card-footer">
                    {/* <a href={`${window.location.origin}/entity-search/listed-company?stock_code=${abc_code}&stock_name=${stock_name}&com_uni_code=${com_uni_code}&sec_uni_code=${sec_uni_code}`}> */}
                    <a>
                    查看更多信息
                    <img src={require('../../../../assets/image/cscard.png')} alt=""/>
                    {/* {t('StockCard.t_read_more', { defaultValue: '查看更多信息 >' })} */}
                    </a>
                </Layout.Content>
            </Layout>
        )
    }
}

StockCard.defaultProps = {
    companyData: {},
    stockData: {},
    markets: []
}

StockCard.propTypes = {
    companyData: PropTypes.object,
    stockData: PropTypes.object,
    markets: PropTypes.array
}

export default withRouter(StockCard);