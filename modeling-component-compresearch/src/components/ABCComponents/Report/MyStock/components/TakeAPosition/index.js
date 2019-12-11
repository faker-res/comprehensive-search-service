import React, { Component } from 'react';
import { Row, Col } from 'antd';
import classNames from 'classnames';
import styles from './index.scss';
import truth from './truth.png';
import virtual from './virtual.png';
import { upOrDown, getPrefix, getNum } from '../../lib/numFormat';
import { withRouter } from 'react-router-dom';

/* eslint react/no-did-mount-set-state: 0 */
/* eslint no-param-reassign: 0 */
@withRouter
export default class TakeAPosition extends Component {
  state = {
    text: '',
    targetCount: 0,
  };

  componentDidMount() {
    if (this.node) {
      // this.computeLine();
    }
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.lines !== nextProps.lines) {
      // this.computeLine();
    }
  }
  handleRoot = n => {
    this.root = n;
  };

  handleContent = n => {
    this.content = n;
  };

  handleNode = n => {
    this.node = n;
  };

  handleShadow = n => {
    this.shadow = n;
  };

  handleShadowChildren = n => {
    this.shadowChildren = n;
  };

  getColor = (count) => {
    if (!count && count !== 0) {
      return {};
    }
    count = +(count.toFixed(2))
    if (count > 0) {
      return {
        color: '#D9554E',
      }
    } else if (count < 0) {
      return {
        color: '#4DA04C',
      }
    } else {
      return {
        color: '#ccc',
      }
    }
  }

  getIcon = (count) => {
    if (!count && count !== 0) {
      return '';
    }
    count = +(count.toFixed(2))
    if (count > 0) {
      return '&#xe638';
    } else if (count < 0) {
      return '&#xe636';
    } else {
      return '';
    }
  }

  getCountRender = (name, rate, count, path) => {
    const {history} = this.props;
    if (rate === null && count === null) {
      return (
              <div
                onClick={() => {
                  history.push(path);
                }}
                className="col_center_middle" style={{color: '#ccc'}}
              >
                <div className="name">{name}</div>
                <p className="noOwnMsg">暂无持仓</p>
              </div>
            )
      }
    return (
      <div
        onClick={() => {
          history.push(path);
        }}
        className="col_center_middle">
        <div className="name">{name}</div>
        <div className="rate">
          <span className="market-value">
            {getNum(count, 2)}
          </span>
          <span className="market-unit">
            {getPrefix(count)}
          </span>
        </div>
        <div className="count">
          日盈亏
          <span
            className={`profit-loss-num`}
            style={this.getColor(rate)}
          >
            {getNum(rate, 2)}
          </span>
          {
            this.getIcon(rate) && (
              <i
                className="iconfont"
                style={this.getColor(rate)}
                dangerouslySetInnerHTML={{
                  __html: this.getIcon(rate),
                }}
              />
            )
          }
        </div>
      </div>
    );
  }

  render() {
    const { name, style, data, state, history } = this.props;
    if (state === 'pending') {
      return (
        <div className='takeAPosition'>
          正在加载....
        </div>
      );
    }
    const rmb = data.rmb;
    const doller = data.doller;
    const hkDoller = data.hkDoller;
    const path = {
      pathname: 'mystock-owned',
      query: {},
    };
    let src = virtual;
    if (name === '实际持仓'){
      src = truth;
      path.query.ownType = '0';
    } else {
      path.query.ownType = '1';
    }
    return (
      <div className="takeAPosition">
        <div
          onClick={() => {
            history.push(path);
          }}
          className="title col_center_middle" style={style}>
          <img src={src} alt="" />
          <span>{name}</span>
        </div>
        <div className="data">
          <Row>
            <Col span={8}>
              {this.getCountRender('人民币总市值(￥)', rmb.dayProfitLoss, rmb.marketValue, {
                pathname: 'mystock-owned',
                query: {
                  ownType: path.query.ownType,
                  moneyType: 'CN',
                }
              })}
            </Col>
            <Col span={8}>
              {this.getCountRender('美元总市值($)', doller.dayProfitLoss, doller.marketValue, {
                pathname: 'mystock-owned',
                query: {
                  ownType: path.query.ownType,
                  moneyType: 'US',
                }
              })}
            </Col>
            <Col span={8}>
              {this.getCountRender('港币总市值(HK$)', hkDoller.dayProfitLoss, hkDoller.marketValue, {
                pathname: 'mystock-owned',
                query: {
                  ownType: path.query.ownType,
                  moneyType: 'HK',
                }
              })}
            </Col>
          </Row>
        </div>
      </div>
    );
  }
}
