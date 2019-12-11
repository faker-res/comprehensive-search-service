import React, { Component } from 'react';
import { Select, Icon, Input, Dropdown, Menu, Row, Col } from 'antd';
import { BrowserRouter, Route, Switch, NavLink, withRouter } from 'react-router-dom';
import './Search.less';
// import '../../common/common.less';
// import logo from '../../assets/image/common/header_logo_big.png';
import logo from '../../assets/image/common/home.svg';

const { Option } = Select;
const { Search } = Input;
export default class SearchComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      // searchValue: '',
    };
  }
  componentDidUpdate = () => {
    const { stockSearchData } = this.props;
    this.stockInput.input.input.value = stockSearchData.keyword;
    // this.stockInput.focus();
  }
  handleChangeCategory = (value, option) => {
    const { changeCategory } = this.props;
    changeCategory(value, option);
  }
  emitEmpty = () => {
    // this.stockInput.focus();
    const { changeStock } = this.props;
    changeStock('');
  }
  handleChangeStock = (e) => {
    const { value } = e.target;
    const { changeStock } = this.props;
    //防止多次触发，
    this.isPushChangeStock(changeStock, null, 500, value);
  }
  isPushChangeStock = (fn, context, delay, value) => {
    clearTimeout(fn.timeoutId);
    fn.timeoutId = setTimeout(function () {
      fn.call(context, value);
    }, delay);
  }
  clickButton = (value) => {
    const { clickButtonCallback } = this.props;
    clickButtonCallback(value);
  }
  clickMenu = (item) => {
    const { selectedStock } = this.props;
    selectedStock(item.key);
  }
  transformStockList = () => {
    const { stockList, stockSearchData, tordayHotList } = this.props;
    const historyList = localStorage.historyKeywordList ? JSON.parse(localStorage.historyKeywordList) : [];
    const historyKeywordList = historyList.reverse();
    return (
      <div className="stock_list" style={{ width: '670px' }}>
        {
          stockSearchData.keyword.length > 0 && stockList.length > 0 && (
            <div>
              <div className={`stock_list_title middle`}>
                你可能搜索以下关键词
              </div>
              <Menu onClick={this.clickMenu}>
                {
                  stockList.map((stock) => {
                    return (
                      <Menu.Item key={stock.content}>
                        <div className={`stock_content middle`}>
                          <Col span={8}>
                            <span style={{ width: '200px', marginLeft: '10px' }}>{stock.content}</span>
                          </Col>
                          {stock.searchType == 'STOCK' && (
                            <Col span={8}>
                              <span style={{ width: '200px', marginLeft: '10px' }}>{stock.extra}</span>
                            </Col>
                          )}
                        </div>
                      </Menu.Item>
                    );
                  })
                }
              </Menu>
            </div>
          )}
        {
          stockSearchData.keyword.length === 0 && stockList.length === 0 && (
            <div>
              {historyKeywordList.length > 0 && (
                <div className={'history_container'}>
                  <div className={`stock_list_title middle space_between`}>
                    <span>最近搜过</span>
                    <span data-keyword="all__all" style={{ color: '#999999', fontSize: '12px', cursor: 'pointer', marginRight: '15px' }} onClick={this.deleteKeyword}>全部删除</span>
                  </div>
                  <Menu onClick={this.clickMenu}>
                    {
                      historyKeywordList.slice(0, 5).map((data) => {
                        return (
                          <Menu.Item key={data}>
                            <div className={`history_content middle space_between`}>
                              <span style={{ width: '200px', marginLeft: '10px', color: '#287ddc' }}>{data}</span>
                              <span data-keyword={data} style={{ color: '#999999', fontSize: '12px', cursor: 'pointer', display: 'none' }} onClick={this.deleteKeyword}>删除</span>
                            </div>
                          </Menu.Item>
                        );
                      })
                    }
                  </Menu>
                </div>
              )}
              <div className={`stock_list_title middle`}>
                热门搜索
              </div>
              <Menu onClick={this.clickMenu}>
                {
                  tordayHotList.slice(0, 5).map((data) => {
                    return (
                      <Menu.Item key={data.query}>
                        <div className={`stock_content middle`}>
                          <span style={{ width: '200px', marginLeft: '10px' }}>{data.display_Name}</span>
                        </div>
                      </Menu.Item>
                    );
                  })
                }
              </Menu>
            </div>
          )}
      </div>
    );
  }
  deleteKeyword = (e) => {
    const { stockSearchData, changeStock } = this.props;
    const historyList = localStorage.historyKeywordList ? JSON.parse(localStorage.historyKeywordList) : [];
    const { keyword } = e.target.dataset;
    if (keyword !== 'all__all') {
      const i = historyList.indexOf(keyword);
      if (i > -1) historyList.splice(i, 1);
      localStorage.historyKeywordList = JSON.stringify(historyList);
    } else {
      localStorage.historyKeywordList = JSON.stringify([]);
    }
    changeStock(stockSearchData.keyword);
    e.stopPropagation();
  }
  render() {
    const { categoryList, searchData, defaultValue } = this.props;
    const keyword = searchData.keyword_filter;
    const categoryValue = searchData.first_category;
    const suffix = keyword.length > 0 ? <Icon type="close-circle" onClick={this.emitEmpty} style={{ color: '#d7d7d7', marginRight: '15px' }} /> : null;
    return (
      <div className={`search_container center_middle`}>
        <div className={'center_middle'} style={{ width: '1260px' }}>
          <NavLink to="/" className={'logo'} key="logo">
            <img src={logo} alt="logo" width="168" height='26' />
          </NavLink>
          <Select value={categoryValue} style={{ width: 180, marginRight: 10 }} onChange={this.handleChangeCategory}>
            <Option value="">全部</Option>
            {
              categoryList.map((category) => {
                return <Option key={category.id} value={category.id}>{category.name}</Option>;
              })
            }
          </Select>
          <div className={'search_keyword_container'}>
            <Dropdown overlay={this.transformStockList()} trigger={['focus']} placement="bottomLeft">
              <Search
                ref={
                  (input) => {
                    this.stockInput = input;
                  }
                }
                placeholder={defaultValue || '请输入关键字'}
                enterButton="搜索"
                // value={keyword}
                suffix={suffix}
                onChange={this.handleChangeStock}
                onSearch={this.clickButton}
                style={{ width: '670px' }}
              />
            </Dropdown>
          </div>
        </div>
      </div>
    );
  }
}
