/**
 * @description StockPopup 组件
 * @date 2018.05.22
 * @author David
 */

import React, { Component } from 'react'
import PropTypes from 'prop-types'
import SZBottomComponent from './childComponent/SZBottomComponent/SZBottomComponent'
import SZTopComponent from './childComponent/SZTopComponent/SZTopComponent'
import FundTopComponent from './childComponent/FundTopComponent/FundTopComponent'
import HKBottomComponent from './childComponent/HKBottomComponent/HKBottomComponent'
import FundBottomComponent from './childComponent/FundBottomComponent/FundBottomComponent'
import './index.scss'
import { data } from './data'

export default class StockPopup extends Component {
    constructor(props) {
        super(props);

    }

    componentDidMount() {

    }

    componentWillUnmount() {

    }

    formatMoney(money, decimal) {
        let reg = /(\d+)(\d{3})/;
        if (decimal) {
            money = parseFloat(money).toFixed(decimal);
        } else {
            money = String(money);
        }
        while (reg.test(money)) {
            money = money.replace(reg, '$1,$2');
        }
        return money;
    }

    parseFloatNum(val) {
        if (!(Math.abs(val) >= 0)) return "--";
        return this.formatMoney(parseFloat(val), 2);
    }

    render() {
        let { abc_code, sec_name, total_market_value, pe, suspend, stock_type, stock_price_icon, current_price, zdf, zg, zd, jk, zs, end_date, cumu_unit_nav,unit_nav,differ,rise_rate, is_money, income, seven_day_yield } = this.props.data;
        let pctchange = zdf ? zdf : '';
        total_market_value = total_market_value > 0 ? (total_market_value / 100000000).toFixed(2) + "亿" : "--";
        current_price = current_price > 0 ? parseFloat(current_price).toFixed(2) : "--";
        let pe_value = !isNaN(parseFloat(pe)) ? parseFloat(pe).toFixed(2) : "--";
        pctchange = pctchange ? (pctchange * 100).toFixed(2) : "--";
        pctchange = pctchange != 0 ? pctchange + "%" : pctchange;
        let zdNum = current_price && current_price !== '--' && parseFloat(zs) ? (current_price - parseFloat(zs)).toFixed(4) : this.parseFloatNum(parseFloat(current_price) * parseFloat(pctchange) / (parseFloat(pctchange) + 1));
        let isUp = pctchange == 0 ? "eq" : pctchange > 0 ? "up" : "down";
        // let url = window['GOTO_STOCK_BASEURL'] + '?stock_code=' + encodeURIComponent(abc_code) + '&stock_name=';
        // console.log(url);
        if (suspend) {
            current_price = '停牌';
        }
        cumu_unit_nav = cumu_unit_nav ? parseFloat(cumu_unit_nav).toFixed(4) : null;
        let stock_chart = stock_price_icon ? JSON.parse(stock_price_icon.indicator_value) : [];
        return (
            <div className="stockPopup">
                <section style={{ width: this.props.width }}>
                    <div className="stockPopupTop">
                        {stock_type === 'fund' ? <FundTopComponent type={is_money} time={end_date}  width={this.props.width} height={this.props.height} title={sec_name} code={abc_code} currentMoney={unit_nav} income={income} gains={differ} gainsRate={rise_rate} stock_chart = {stock_chart} /> :
                            <SZTopComponent width={this.props.width} height={this.props.height} title={sec_name} code={abc_code} currentMoney={current_price} gains={zdNum} gainsRate={pctchange} stock_chart = {stock_chart} />
                        }
                    </div>
                    <div className="stockPopupBottom">
                        {
                            stock_type === 'HS' ? <SZBottomComponent MarketValue={total_market_value} PE={pe_value} /> :
                                stock_type === 'fund' ? <FundBottomComponent type={is_money} seven_day_yield={seven_day_yield} totalValue={cumu_unit_nav} currentMoney={unit_nav}/> :
                                    <HKBottomComponent OpeningValue={this.props.OpeningValue} YesterdayCloseValue={this.props.YesterdayCloseValue} highestValue={this.props.highestValue} lowestValue={this.props.lowestValue} />
                        }
                    </div>
                </section>
            </div>
        );
    }
}



// 默认props值
StockPopup.defaultProps = {
    data: data.data,
    width: 257,
    height: 129, //上半层带有hchart的调试，并不是整体高度
/*     title: '久远银海',
    code: '00277.SZ',
    currentMoney: '35.00',
    gains: -1.09,
    gainsRate: 3.21,
    MarketValue: '1097.19亿',
    PE: 2.81,
    time: '3232',
    popupTopType: 2,   //1:股票类头部类型、2:非股票类头部类型（基金等）
    popupBottomType: 3,  //1:底部类型1（总市值、PE值） 2：底部类型2（今开、最高、昨收、最低） 3：底部类型3（坚列排版）
    OpeningValue: 407.80,
    YesterdayCloseValue: 405.80,
    highestValue: 407.80,
    lowestValue: 401.00,
    totalValue: '2.1700亿',
    HigherSpeed: 294.70,
    recentlyDeal: 294.70,
    currentHighest: 294.70,
    currentLowest: 294.70,
    totalDealValue: '58.94',
    assignType: '协议' */
}
// props 类型
StockPopup.propTypes = {
    width: PropTypes.number,
    height: PropTypes.number,
    title: PropTypes.string,
    code: PropTypes.string,
    currentMoney: PropTypes.string,
    gains: PropTypes.number,
    gainsRate: PropTypes.number,
    MarketValue: PropTypes.string,
    PE: PropTypes.number,
    time: PropTypes.string,
    popupTopType: PropTypes.number,
    popupBottomType: PropTypes.number,
    OpeningValue: PropTypes.number,
    YesterdayCloseValue: PropTypes.number,
    highestValue: PropTypes.number,
    lowestValue: PropTypes.number,
    totalValue: PropTypes.string,
    HigherSpeed: PropTypes.number,
    recentlyDeal: PropTypes.number,
    currentHighest: PropTypes.number,
    currentLowest: PropTypes.number,
    totalDealValue: PropTypes.string,
    assignType: PropTypes.string
}

