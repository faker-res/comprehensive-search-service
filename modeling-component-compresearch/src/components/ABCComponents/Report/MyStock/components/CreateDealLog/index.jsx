import React, { Component } from 'react';
import { observer, inject } from 'mobx-react';
import moment from 'moment';
import { withRouter } from 'react-router-dom';

import {
  Modal, DatePicker,
  Form, Input, Radio,
  Select, Checkbox,
  Row, Col, message,
  Alert,
} from 'antd';
import classNames from 'classnames';
import {
  toFixed, getPriceColor,
  stockDealTypes, currencyTypes
} from "../../lib/utils";
import './index.scss';
import Ellipsis from '../Ellipsis';

const FormItem = Form.Item;
const { TextArea } = Input;
const { Option } = Select;

const formItemLayout = {
  labelCol: { span: 3 },
  wrapperCol: { span: 21 },
};


const checkNumberLength = (number, length) => {
  return /^[1-9]\d{0,15}$/.test(number);
}

/* eslint react/no-did-mount-set-state: 0 */
/* eslint no-param-reassign: 0 */
@withRouter
@inject(stores => ({
  // stock: stores.ownStock.stock,
  // ownType: stores.ownStock.ownType,
  // getStockByDate: stores.ownStock.getStockByDate,
  // getCurrentPriceById: stores.ownStock.getCurrentPriceById,
  dealLog: stores.stockDealLog.dealLog,
  resetFields: stores.stockDealLog.resetFields,
  setProps: stores.stockDealLog.setProps,
  saveDealLog: stores.stockDealLog.saveDealLog,
  dealLogType: stores.stockDealLog.type,
}))
@Form.create()
export default class CreateDealLog extends Component {
  state = {
    confirmLoading: false,
    intoShare: '',
    donateShare: '',
    bonus: '',
    errorMsg: '',
    isFirst: true
  }
  componentWillUnmount = () => {
    this.timer && clearTimeout(this.timer);
  }
  componentWillReceiveProps = (nextProps) => {
    const { stock, getCurrentPriceById } = this.props;
    if (nextProps.stockCode && this.props.stockCode == '') {
      this.timer = setInterval(() => {
        getCurrentPriceById(nextProps.stockCode);
      }, 3000)
    }
    if (nextProps.stockCode && this.props.stockCode != '' && nextProps.visible && !this.props.visible) {
      this.timer = setInterval(() => {
        getCurrentPriceById(nextProps.stockCode);
      }, 3000)
    } else if (!nextProps.visible && this.props.visible) {
      clearTimeout(this.timer);
      this.timer = 0;
    }
  }
  formItems = {
    price: () => {
      const { form, dealLog, stock = {} } = this.props;
      const { price } = dealLog;
      const { getFieldDecorator } = form;
      return (
        <FormItem
          key="price"
          {...formItemLayout}
          label="价格">
          {getFieldDecorator('price', {
            initialValue: price,
            rules: [{ validator: this.checkPrice, required: true, }],
          })(
            // <PriceInput isInt={false} onChange={this.priceChange} />
            <Input
              type="text"
              placeholder={stock.isRealtime
                ? stock.current ? stock.current : '请输入价格'
                : !stock.current ? '请输入价格' : `收盘价${stock.current} ${stock.high ? `最高${stock.high}` : ''} ${stock.low ? `最低${stock.low}` : ''}`}
              onChange={this.priceChange}
              style={{ width: '75%' }}
            />
          )}
        </FormItem>
      );
    },
    volume: () => {
      const { form, dealLog, stock } = this.props;
      const { volume, type } = dealLog;
      const { getFieldDecorator } = form;
      return (
        <FormItem
          key="volume"
          {...formItemLayout}
          label="数量">
          {getFieldDecorator('volume', {
            initialValue: volume,
            rules: [{ validator: this.checkCount, required: true, }],
          })(
            // <PriceInput isInt={true} onChange={this.countChange} />
            <Input
              type="text"
              // placeholder={type != 2 ? "请输入数量" : `请输入数量（可卖出：${stock.stockTotal} 股）`}
              placeholder="请输入数量"
              onChange={this.countChange}
              suffix={<div className="unitColor">股</div>}
              style={{ width: '75%' }}
            />
          )}
        </FormItem>);
    },
    commission: () => {
      let { form, dealLog, stock = {} } = this.props;
      dealLog = this.setDealUnit(stock, dealLog);
      const { commission, commissionRate,
        updateCommission, commissionUnit, commissionView } = dealLog;
      const { getFieldDecorator } = form;
      const selectCommissionUnit = (
        <Select
          value={[commissionUnit]}
          // defaultValue={}
          style={{ width: 100 }}
          onChange={this.handleCommissionUnitChange}
        >
          <Option value="rate">‰</Option>
          {stock.unit === 'CN' ? <Option value="CN">人民币</Option> : ''}
          {stock.unit === 'US' ? <Option value="US">美元</Option> : ''}
          {stock.unit === 'HK' ? <Option value="HK">港币</Option> : ''}
        </Select>
      );
      return (
        <FormItem
          key="commission"
          {...formItemLayout}
          label="佣金">
          {getFieldDecorator('佣金', {
            initialValue: commissionView,
            rules: [{ validator: this.checkBrokerage }],
          })(
            <div>
              <Input
                type="text"
                placeholder="请输入佣金"
                value={commissionView}
                addonAfter={selectCommissionUnit}
                onChange={this.handleBrokerageChange}
                style={{ width: '75%', marginRight: '10px' }}
              />
              <Checkbox defaultChecked={true} onChange={this.setUsualUnit}>设为常用</Checkbox>
              {/* <Row gutter={16}>
                <Col span={18}>
                </Col>
                <Col span={6}>
                </Col>
              </Row> */}
            </div>
          )}
        </FormItem>);
    },
    tax: () => {
      let { form, dealLog, stock = {} } = this.props;
      dealLog = this.setDealUnit(stock, dealLog);
      const { tax, updateTax, taxRate, taxUnit, taxView } = dealLog;
      const { getFieldDecorator } = form;
      const selectTaxUnit = (
        <Select
          // value={state.currency}
          value={[taxUnit]}
          style={{ width: 100 }}
          onChange={this.handleTaxUnitChange}
        >
          <Option value="rate">‰</Option>
          {stock.unit === 'CN' ? <Option value="CN">人民币</Option> : ''}
          {stock.unit === 'US' ? <Option value="US">美元</Option> : ''}
          {stock.unit === 'HK' ? <Option value="HK">港币</Option> : ''}
        </Select>
      );
      return (
        <FormItem
          key="tax"
          {...formItemLayout}
          label="税率">
          {getFieldDecorator('税率', {
            initialValue: taxView,
            rules: [{ validator: this.checkTaxRate }],
          })(
            <div>
              <Input
                type="text"
                value={taxView}
                placeholder="请输入税率"
                addonAfter={selectTaxUnit}
                onChange={this.handleTaxRateChange}
                style={{ width: '75%', marginRight: '10px' }}
              />
              <Checkbox defaultChecked={true} onChange={this.setUsualUnit} >设为常用</Checkbox>

              {/* <Row gutter={16}>
                <Col span={18}>

                </Col>
                <Col span={6}>
                </Col>
              </Row> */}
            </div>
          )}
        </FormItem>);
    },
    everyTenShares: () => {
      return (
        <FormItem
          key="tenStock"
          {...formItemLayout}
          colon={false}
          label="10股">
        </FormItem>);
    },
    intoShare: () => {
      const { form, dealLog } = this.props;
      const { intoShare } = dealLog;
      const { getFieldDecorator } = form;
      return (
        <FormItem
          key="intoShare"
          {...formItemLayout}
          label="转增">
          {getFieldDecorator('转增', {
            initialValue: intoShare,
            rules: [{ validator: this.checkIntoShare }],
          })(
            <Input
              type="text"
              placeholder="请输入数量"
              min={0}
              // value={intoShare}
              style={{ width: '75%' }}
              suffix={<div className="unitColor">股</div>}
              // addonAfter={'股'}
              onChange={this.handleIntoShare}
            />
          )}
        </FormItem>);
    },
    donateShare: () => {
      const { form, dealLog } = this.props;
      const { donateShare } = dealLog;
      const { getFieldDecorator } = form;
      return (
        <FormItem
          key="donateShare"
          {...formItemLayout}
          label="送股">
          {getFieldDecorator('送股', {
            initialValue: donateShare,
            rules: [{ validator: this.checkDonateShare }],
          })(
            <Input
              type="text"
              placeholder="请输入数量"
              // value={donateShare}
              style={{ width: '75%' }}
              // addonAfter={'股'}
              suffix={<div className="unitColor">股</div>}
              onChange={this.handleDonateShare}
            />
          )}
        </FormItem>);
    },
    bonus: () => {
      const { form, dealLog, stock } = this.props;
      const { bonus } = dealLog;
      const { getFieldDecorator } = form;
      return (
        <FormItem
          key="bonus"
          {...formItemLayout}
          label="红利">
          {getFieldDecorator('红利', {
            initialValue: bonus,
            rules: [{ validator: this.checkBonus }],
          })(
            <Input
              type="text"
              placeholder="请输入红利"
              style={{ width: '75%' }}
              // value={bonus}
              suffix={<div className="unitColor">{currencyTypes[stock.unit]}</div>}
              // addonAfter={currencyTypes[stock.unit]}
              onChange={this.handleBonusChange}
            />
          )}
        </FormItem>);
    }
  }
  setDealUnit = (stock, dealLog) => {
    if (dealLog.id) {
      if (dealLog.commissionRate !== null && dealLog.commissionRate !== '' && dealLog.commissionRate >= 0) {
        dealLog.commissionUnit = 'rate';
        dealLog.commissionView = dealLog.commissionRate;
      } else {
        dealLog.commissionUnit = stock.unit;
        dealLog.commissionView = dealLog.commission;
      }
      if (dealLog.taxRate !== null && dealLog.taxRate !== '' && dealLog.taxRate >= 0) {
        dealLog.taxUnit = 'rate';
        dealLog.taxView = dealLog.taxRate;
      } else {
        dealLog.taxUnit = stock.unit;
        dealLog.taxView = dealLog.tax;
      }
    } else {
      //新增
      //如果
      if (dealLog.commissionUnit === null || dealLog.commissionUnit === '') {
        if (stock.commissionRate !== null && stock.commissionRate !== '' && stock.commissionRate >= 0) {
          dealLog.commissionUnit = 'rate';
          dealLog.commissionView = dealLog.commissionRate || stock.commissionRate;
        } else {
          dealLog.commissionUnit = stock.unit;
          dealLog.commissionView = dealLog.commission || stock.commission;
        }

      } else {

      }
      if (dealLog.taxUnit === null || dealLog.taxUnit === '') {
        if (stock.taxRate !== null && stock.taxRate !== '' && stock.taxRate >= 0) {
          dealLog.taxUnit = 'rate';
          dealLog.taxView = dealLog.taxRate || stock.taxRate;
        } else {
          dealLog.taxUnit = stock.unit;
          dealLog.taxView = dealLog.tax || stock.tax;
        }
      }

    }
    return dealLog;
  }
  priceChange = (e) => {
    const { setProps } = this.props
    setProps({
      price: e.target.value,
    });
  }
  countChange = (e) => {
    const { setProps } = this.props
    setProps({
      volume: e.target.value,
    });
  }
  handleBrokerageChange = (e) => {
    const { setProps, dealLog } = this.props
    setProps({ commissionView: e.target.value });
  }
  handleTaxRateChange = (e) => {
    const { setProps } = this.props;
    setProps({ taxView: e.target.value });
  }
  handleIntoShare = (e) => {
    const { setProps } = this.props;
    setProps({ intoShare: e.target.value });
  }

  checkIntoShare = (rule, value, callback) => {
    const { donateShare } = this.state;
    const reg = /^[0-9]*[0-9][0-9]*$/
    if (value >= 0 && reg.test(parseFloat(value))) {
      this.setState({
        intoShare: value,
      })
      callback();
      return;
    }
    callback('转增数量格式不正确。');
  }

  checkDonateShare = (rule, value, callback) => {
    const reg = /^[0-9]*[0-9][0-9]*$/
    if (value >= 0 && reg.test(parseFloat(value))) {
      this.setState({
        donateShare: value,
      })
      callback();
      return;
    }
    callback('送股数量格式不正确。');
  }

  checkBonus = (rule, value, callback) => {
    if (value >= 0 && /^(0|[1-9]\d*)(\.\d{0,4})?$/.test(value)) {
      this.setState({
        bonus: value,
      })
      callback();
      return;
    }
    callback('红利格式不正确。');
  }

  handleDonateShare = (e) => {
    const { setProps } = this.props;
    setProps({ donateShare: e.target.value });
  }

  handleBonusChange = (e) => {
    const { setProps } = this.props;
    setProps({ bonus: e.target.value });
  }

  checkFloat = (rule, value, callback) => {
    if (value >= 0) {
      callback();
      return;
    }
    callback(`输入${rule.field}有误，请重新输入。`);
  }

  checkInteger = (rule, value, callback) => {
    if (!(value > 0)) {
      callback(`${rule.field}必须为大于零的数字`);
      return;
    }
    const reg = /^[0-9]*[1-9][0-9]*$/
    if (!reg.test(parseFloat(value))) {
      callback(`${rule.field}必须为大于零的整数`);
      return;
    }
    callback();

  }

  checkPrice = (rule, value, callback) => {
    if (value > 0 && /^(0|[1-9]\d{0,5})(\.\d{0,4})?$/.test(value)) {
      callback();
      return;
    }
    callback('输入价格有误，请重新输入！');
  }
  checkCount = (rule, value, callback) => {
    const { stock, dealLog } = this.props;
    const { type } = dealLog;
    // if (value > stock.stockTotal) {
    //   if (type == '2') {
    //     callback(`用于交易得股份不足`);
    //     return;
    //   }
    // }
    if (value > 0 && /^[1-9]\d{0,15}$/.test(value)) {
      callback();
      return;
    }

    callback('输入数量格有误，请重新输入！');
  }
  checkBrokerage = (rule, value, callback) => {
    if (value >= 0) {
      callback();
      return;
    }
    callback('输入佣金有误，请重新输入！');
  }
  checkTaxRate = (rule, value, callback) => {
    if (value >= 0) {
      callback();
      return;
    }
    callback('输入税率有误，请重新输入！');
  }
  onCancel = (e) => {

    const { onCancel, resetFields } = this.props;
    this.timer && clearTimeout(this.timer);
    resetFields();
    onCancel(e);
    this.setState({
      confirmLoading: false,
      errorMsg: '',
    });

  }
  checkXdXr = () => {
    const { intoShare, donateShare, bonus } = this.state;
    const count = intoShare + donateShare + bonus;
    if (parseFloat(count) > 0) {
      return true;
    }
    return false;

  }
  onCreate = () => {
    const { onCreate, saveDealLog,
      resetFields, dealLog, stock, form, history, dealLogType,
      setProps } = this.props;
    this.timer && clearTimeout(this.timer);
    this.setState({
      confirmLoading: true,
    });
    form.validateFields((err, values) => {
      if (err) {
        this.setState({
          confirmLoading: false,
        });
        return;
      }
      if (!this.checkXdXr() && dealLog.type === '7') {
        this.setState({
          errorMsg: "转增数量、送股数量、红利不能同时为0，禁止提交！",
          confirmLoading: false,
        });
        return;
      }

      dealLog.stock = stock.code;
      dealLog.mode = stock.mode > -1 ? stock.mode : dealLog.mode;
      saveDealLog(dealLog, (value) => {
        //通过搜索添加交易记录
        if (dealLogType === 'bySearch') {
          gtag('event', 'add_search', {
            'event_category': 'hold',
            'event_label': 'add_search'
          });
        }

        //埋点
        const { mode } = dealLog;
        let tagStr = "";
        if (mode === 0) {
          tagStr = "add_hold_real"
        } else {
          tagStr = "add_hold_simu"
        }
        gtag('event', tagStr, {
          'event_category': 'hold',
          'event_label': tagStr
        });


        setProps(null, 'byAll');  //添加交易记录入口状态重置

        if (value.code == 200) {
          let clearDom = document.querySelector('.ant-select-selection__clear');
          if (clearDom) clearDom.click();
          if (dealLog.id) {
            message.success(`股票 ${stock.name} 交易记录更新成功！`);
          } else {
            message.success(`股票 ${stock.name} 交易记录添加成功！`);
            //如果当前在自选股页面，添加持仓记录成功后跳转到自选股持仓页面
            if (history.location.pathname === '/') {
              history.push({
                pathname: 'mystock-owned',
                query: {
                  ownType: dealLog.mode,
                  moneyType: this.props.stock.unit,
                  stockCode: dealLog.stock,
                }
              })
            }
          }
          onCreate();
          resetFields();
        } else {
          message.error(value.message);
        }
        this.setState({
          confirmLoading: false,
          errorMsg: '',
        });
      });
    });

  }
  changeMode = (e) => {
    const { setProps } = this.props
    setProps({
      mode: e.target.value,
    });
  }
  changeType = (e) => {
    const { setProps, form } = this.props
    setProps({
      type: e.target.value,
    });
    // form.resetFields();
  }
  changeDealDate = (dataString, string) => {
    const { stock, setProps, getStockByDate, getCurrentPriceById } = this.props
    let today = moment().format('l');
    if (moment(string).isSame(today)) {
      if (!this.timer) {
        this.timer = setInterval(() => {
          getCurrentPriceById(this.props.stockCode);
        }, 3000)
      }
    }
    else {
      clearTimeout(this.timer);
      this.timer = 0;
    }

    setProps({
      dealDate: dataString,
    });
    // fetchStackTotal(stock.code, dataString);
    if (dataString) {
      getStockByDate({ stock: stock.code, priceDate: dataString.toISOString() })
    }
  }
  setUsualUnit = (e) => {
    const { setProps } = this.props;
    setProps({
      updateCommission: e.target.checked ? 1 : 0
    })
    e.stopPropagation();
  }
  handleRemarkChange = (e) => {
    const { setProps } = this.props
    setProps({
      remark: e.target.value,
    });
  }
  handleCommissionUnitChange = (key) => {
    const { setProps, dealLog } = this.props;
    const { commission, commissionRate } = dealLog;
    setProps({ commissionUnit: key });
  }
  handleTaxUnitChange = (key) => {
    const { setProps, dealLog } = this.props;
    const { tax, taxRate, taxUnit } = dealLog;
    setProps({ taxUnit: key });
  }
  getOwnType = (type) => {
    let result = (< Radio.Group onChange={this.changeMode}>
      <Radio value="0">实际持仓</Radio>
      <Radio value="1">模拟持仓</Radio>
    </Radio.Group>);
    switch (type) {
      case 0:
        result = (
          <div>实际持仓</div>
        )
        break;
      case 1:
        result = (
          <div>模拟持仓</div>
        )
        break;
      default:
        break;
    }
    return result;

  }

  getDealType = (dealLog) => {
    let result = '';
    if (!dealLog.id) {
      result = (
        < Radio.Group onChange={this.changeType} >
          <Radio value="1">买入</Radio>
          <Radio value="2">卖出</Radio>
          <Radio value="7">除权除息</Radio>
        </Radio.Group>
      )
    } else {
      result = (
        <div>{stockDealTypes[dealLog.type]}</div>
      );
    }
    return result;
  }
  // 弹出时间窗口后，停止轮询，关闭时间窗口后，如果选中时间是当天，启动轮询
  openChange = (status) => {
    const { dealLog: { dealDate }, stockCode, getCurrentPriceById } = this.props;
    if (status) {
      if (this.timer) {
        clearTimeout(this.timer);
        this.timer = 0;
      }
    } else {
      let today = moment().format('l');
      if (moment(dealDate).isSame(today) && !this.timer) {
        this.timer = setInterval(() => {
          getCurrentPriceById(stockCode);
        }, 3000)
      }
    }

  }
  render() {
    const types = {
      1: ['price', 'volume', 'commission', 'tax'],
      2: ['price', 'volume', 'commission', 'tax'],
      7: ['everyTenShares', 'intoShare', 'donateShare', 'bonus', 'tax']
    }
    const unitObj = { US: '$', CN: '¥', HK: 'HK$' };
    const { errorMsg, isFirst } = this.state;
    const { visible, onCreate, form, maskClosable, ownType } = this.props;
    const { getFieldDecorator } = form;

    const { dealLog = {}, stockCode, stock = {}, getCurrentPriceById } = this.props;
    const { price, volume, commission, dealDate,
      tax, remark, type, updateCommission, updateTax } = dealLog;
    const config = {
      initialValue: dealDate ? moment(new Date(dealDate)) : moment(),
      rules: [{ type: 'object', required: true, message: '日期不能为空!' }],
    };
    return (
      <Modal
        className="createDealModal"
        visible={visible}
        confirmLoading={this.state.confirmLoading}
        title={dealLog.id ? "编辑交易记录" : "添加交易记录"}
        okText="保存"
        cancelText="取消"
        onCancel={this.onCancel}
        maskClosable={maskClosable}
        onOk={this.onCreate}
      >
        <div className="createDealLog">
          <div className={`dealErrorMessage ${errorMsg ? '' : 'hidden'}`}>
            <Alert type="error" message={errorMsg} banner />
          </div>
          {/* {stock.current && stock.isRealtime */}
          {true
            ? <div className="stockInfo">
              <Row>
                <div className='middle' style={{ marginBottom: '5px' }}>
                  <div className="stockName">
                    {stock.name || '--'}
                  </div>
                  <div className="stockCode">{stock.code} </div>
                </div>
              </Row>
              <Row>
                <Col span={8}>
                  现价 <span className="value">
                    {stock.current ? unitObj[stock.unit] + stock.current : '--'}
                  </span>
                </Col>
                <Col span={8}>
                  最高 <span className="value">
                    {stock.high ? unitObj[stock.unit] + stock.high : '--'}
                  </span>
                </Col>
                <Col span={8}>
                  最低 <span className="value">
                    {stock.low ? unitObj[stock.unit] + stock.low : '--'}
                  </span>
                </Col>
              </Row>
            </div>
            : <div className="stockInfo">
              <Row>
                <Col span={8}>
                  <div className="stockName">
                    <Ellipsis tooltip length={7}>
                      {stock.name}
                    </Ellipsis>
                  </div>
                  <div className="stockCode">{stock.code} </div>
                </Col>
                <Col span={16}>
                  <div className="date">{stock.priceDate ? moment(stock.priceDate).format('YYYY-MM-DD') : '--'}</div>
                  <Row>
                    <Col span={8}>
                      收盘价 <span className="value">
                        {stock.closePrice ? stock.closePrice : '--'}
                      </span>
                    </Col>
                    <Col span={8}>
                      最高 <span className="value">
                        {stock.high ? stock.high : '--'}
                      </span>
                    </Col>
                    <Col span={8}>
                      最低 <span className="value">
                        {stock.low ? stock.low : '--'}
                      </span>
                    </Col>
                  </Row>
                </Col>
              </Row>
            </div>
          }


          <Form>
            <FormItem
              {...formItemLayout}
              label="持仓">
              {getFieldDecorator('持仓', { initialValue: stock.mode == -1 ? '0' : stock.mode })(
                this.getOwnType(stock.mode)
              )}
            </FormItem>
            <FormItem
              {...formItemLayout}
              label="类型">
              {getFieldDecorator('type', { initialValue: type })(
                this.getDealType(dealLog)
              )}
            </FormItem>
            <FormItem
              key="remark"
              {...formItemLayout}
              label="日期">
              {!dealLog.id ? getFieldDecorator('日期',
                { ...config })(
                  <DatePicker
                    onChange={this.changeDealDate}
                    onOpenChange={this.openChange}
                    disabledDate={(currentDate) => {
                      if (!currentDate) {
                        return;
                      }
                      return currentDate.valueOf() > moment().valueOf()
                    }}
                    style={{ width: '75%' }}
                  />
                ) : (<div>{dealLog.dealDate}</div>)}
            </FormItem>
            {(types[type] || []).map((item) => {
              return this.formItems[item]();
            })}
            <FormItem
              {...formItemLayout}
              label="备注">
              {getFieldDecorator('remark', {
                initialValue: remark,
              })(
                <TextArea
                  onChange={this.handleRemarkChange}
                  style={{ width: '75%' }}
                  maxLength="200"
                  rows={4} />
              )}
            </FormItem>

          </Form>
        </div>
      </Modal>
    );
  }

}
