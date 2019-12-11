import React, { Component } from 'react';
// import { Layout, List, BackTop, Icon } from 'antd';
import { inject, observer } from 'mobx-react';
import { withRouter } from 'react-router-dom'
import { Divider, Popover } from 'antd';
import ReactHighcharts from 'react-highcharts';
import { formatMoney } from '../../../../../lib/utils';
import './StockCard.less';
// import '../../common/common.less';
import up from '../../../../../assets/image/up.png';
import down from '../../../../../assets/image/down.png';

@withRouter
@inject('stockStore')
@observer
export default class StockCard extends Component {
  getStockChart = () => {
    const resp = this.props.stockStore.stockState.stockDetail;
    const data = resp.stock_price_icon;
    resp.pctchange = resp.zdf ? resp.zdf : '';
    const isUp = resp.pctchange === 0 ? 'eq' : resp.pctchange > 0 ? 'up' : 'down';

    let basicColor = '#717D8C';
    if (isUp === 'up') {
      basicColor = '#F97F6C';
    } else if (isUp === 'down') {
      basicColor = '#71DE9D';
    }
    const seriesArr = [];
    const basicConfig = {
      chart: {
        backgroundColor: 'transparent',
        margin: [0, 0, 0, 0],
        spacing: [0, 0, 0, 0],
        height: '80px',
      },
      credits: {
        enabled: false,
      },
      exporting: {
        enabled: false,
      },
      title: {
        text: null,
      },
      legend: {
        enabled: false,
      },
      tooltip: {
        enabled: false,
      },
      xAxis: {
        visible: false,
      },
      yAxis: {
        visible: false,
      },
      plotOptions: {
        series: {
          marker: {
            enabled: false,
          },
          states: {
            hover: {
              enabled: false,
            },
          },
        },
      },
      series: [{
        type: 'area',
        color: basicColor,
        fillColor: {
          linearGradient: {
            x1: 0,
            y1: 0,
            x2: 0,
            y2: 1,
          },
          stops: [
            [0, ReactHighcharts.Highcharts.Color(basicColor).setOpacity(0.2).get('rgba')],
            [1, ReactHighcharts.Highcharts.Color(basicColor).setOpacity(0).get('rgba')],
          ],
        },
        data: seriesArr,
      }],
    };
    if (data == null || data.indicator_value == null) {
      return basicConfig;
    }
    const rawchartJson = JSON.parse(data.indicator_value);
    let timearr = [];

    for (const key in rawchartJson) {
      if (Object.prototype.hasOwnProperty.call(rawchartJson, key)) {
        timearr.push(key);
      }
    }
    timearr = timearr.sort();
    for (const k of timearr) {
      const value = parseFloat(rawchartJson[k]);
      seriesArr.push(value);
    }
    return basicConfig;
  }
  getContent = () => {
    const { stockName, stockCode } = this.props;
    const resp = this.props.stockStore.stockState.stockDetail;
    resp.pctchange = resp.zdf ? resp.zdf : '';
    const totalMarketValue = resp.total_market_value > 0 ? `${(resp.total_market_value / 100000000).toFixed(2)}亿` : '--';
    let currentPrice = resp.current_price > 0 ? parseFloat(resp.current_price).toFixed(2) : '--';
    const peValue = !isNaN(parseFloat(resp.pe)) ? parseFloat(resp.pe).toFixed(2) : '--';
    let pctchange = resp.pctchange ? (resp.pctchange * 100).toFixed(2) : '--';
    pctchange = resp.pctchange !== 0 ? `${pctchange}%` : pctchange;
    const zdNum = this.parseFloatNum(parseFloat(currentPrice) * (parseFloat(resp.pctchange) * 100) / (parseFloat(resp.pctchange) * 100 + 100));
    let isUp = resp.pctchange === 0 ? 'eq' : resp.pctchange > 0 ? 'up' : 'down';
    if (resp.suspend) {
      currentPrice = '停牌';
      isUp = 'eq';
    }
    const configData = this.getStockChart();
    return (
      <div className={`stock_popover ${isUp}`}>
        <div>
          <ReactHighcharts config={configData} />
        </div>
        <div className={'sotck_title'}>
          <a href={`https://v1.analyst.ai/nav-data/company-by-stock?stock_code=${stockCode}`} target="_blank">
            {`${stockName}(${stockCode})`}
          </a>
        </div>
        <div className={`up_or_down`}>
          <div className={`center_middle`}>{currentPrice}</div>
          <div className={`center_middle`}>
            <div className={`middle right`} style={{ width: '50%' }}>
              <img className={isUp === 'up' ? '' : 'display_none'} src={up} alt={up} style={{ width: '7px', height: '11px' }} />
              <img className={isUp === 'down' ? '' : 'display_none'} src={down} alt={down} style={{ width: '7px', height: '11px' }} />
              <span style={{ paddingLeft: '12px' }}>{zdNum}</span>
            </div>
            <Divider type="vertical" style={{ marginTop: '3px' }} />
            <span style={{ width: '50%' }}>{pctchange}</span>
          </div>
        </div>
        <div className={`info center_middle`}>
          <div className={'center_middle'} style={{ width: '50%' }}>
            <div>
              <div className={`center info_num`}>{totalMarketValue}</div>
              <div className={`center info_title`}>总市值(亿)</div>
            </div>
          </div>
          <Divider type="vertical" style={{ color: '#ddd', height: '30px', marginTop: '3px' }} />
          <div className={'center_middle'} style={{ width: '50%' }}>
            <div>
              <div className={`center info_num`}>{peValue}</div>
              <div className={`center info_title`}>PE值</div>
            </div>
          </div>
        </div>
      </div>
    );
  }
  handleVisible = (visible) => {
    const { stockCode } = this.props;
    if (visible) {
      this.props.stockStore.fetchStockDetail(
        'stock/fetchStockDetail',
        { stockCode },
      );
    }
  }
  handleClick = (sname, tab) => {
    let obj = {"select":'company',"value":sname}
    let search = encodeURIComponent(JSON.stringify([obj]));
    // window.open('/index/report?search=' + search);
    window.open(`/${tab}/report-results?company=${encodeURIComponent(sname)}`);
    // const { stockCode, stockName, clickStockCallback } = this.props;
    // if (clickStockCallback) clickStockCallback(stockName, stockCode);
  }
  parseFloatNum = (val) => {
    if (!(Math.abs(val) >= 0)) return '--';
    return formatMoney(parseFloat(val), 2);
  }

  render() {
    const { stockName, stockCode, titleBold, tab } = this.props;
    return (
      <div className={`stock_container ${stockCode.length > 0 ? '' : 'display_none'}`}>
        {/* <Popover placement="bottomLeft" content={this.getContent()} onVisibleChange={this.handleVisible} overlayClassName={`stock_card`}>
          <div className={'stock_name'} onClick={this.handleClick} style={{ fontWeight: titleBold ? 'bold' : 'initial' }}>{`${stockCode} ${stockName}`}</div>
        </Popover> */}
        <div className={'stock_name'} onClick={() => {this.handleClick(stockName, tab)}} style={{ fontWeight: titleBold ? 'bold' : 'initial' }}>{`${stockCode} ${stockName}`}</div>
      </div>
    );
  }
}
