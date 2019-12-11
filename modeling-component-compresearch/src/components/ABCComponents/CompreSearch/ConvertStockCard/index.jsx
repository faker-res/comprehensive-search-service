/**
 * @description 转换个股证券数据为标准的组件数据
 * @author kygeng
 * date: 2018-05-16
 */

import React from 'react'

export default class ConvertStockCard extends React.Component {

    shouldComponentUpdate(nextProps, nextState) {
        if (JSON.stringify(this.params) === JSON.stringify(nextProps.params)) {
            return false;
        }
        return true;
    }

    render() {
        let { WrapComponent, params } = this.props;
        let { stock_category, unit, stock_info, stock_newsset, suspend_info, transac_info } = params || {};
        let { company_data, stock_data } = stock_newsset || {};
        let { baidu, weibo, xueqiu } = params;

        this.params = JSON.parse(JSON.stringify(params));

        let _companyData = Object.assign({}, company_data, stock_info);

        // 今日开盘价、昨日收盘价、当前最高价、当前最低价
        let { jk: tdOpeningRate, zs: ytdCloseingRate, zg: highRate, zd: lowRate } = stock_data || {};
        // 成交量、成交额、总市值、总股本、流通市值、市净率、市盈率、市销率
        let { cjl: volume, cje: amount, zsz: mktCap, zgb: capitalization, ltsz: tradedMarketValue,
            sjl: mostRecentQuarter, syl: ttm, sxl: salesRatio } = stock_data || {};
        // 当前价、跌幅、是否停牌
        let { current_price: curPrice, zdf: diffPercent, suspend } = stock_data || {};
        let _stockData = { tdOpeningRate, ytdCloseingRate, highRate, lowRate,
            volume, amount, mktCap, capitalization, tradedMarketValue, mostRecentQuarter, ttm, salesRatio,
            curPrice, diffPercent, suspend, unit };
        
        let _markets = [];
        if (baidu) {
            _markets.push({
                type: 'baidu',
                title: '百度指数',
                titleColor: '#407cd5',
                subTitle: '日环比',
                value: parseFloat(baidu.cur_index || 0),
                percent: parseFloat(baidu.rate || 0).toFixed(2),
                chart: baidu.item || {}
            })
        }
        if (weibo) {
            _markets.push({
                type: 'weibo',
                title: '微博指数',
                titleColor: '#04a92b',
                subTitle: '日环比',
                value: parseFloat(weibo.cur_index || 0),
                percent: parseFloat(weibo.rate || 0).toFixed(2),
                chart: weibo.item || {}
            })
        }
        if (xueqiu) {
            _markets.push({
                type: 'xueqiu',
                title: '雪球周关注度',
                titleColor: '#4a90e2',
                subTitle: '周环比',
                value: parseFloat(xueqiu.cur_index || 0),
                percent: parseFloat(xueqiu.rate || 0).toFixed(2),
                chart: xueqiu.item || {}
            })
        }
        
        return (
            <WrapComponent companyData={_companyData} stockData={_stockData} markets={_markets} transacInfo={transac_info} />
        )
    }
}