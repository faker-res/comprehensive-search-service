/**
 * @description 批量选择股票对话框
 * @date 2018.05.08
 * @author wxiong
 */

import React, { Component } from 'react';
import {
  Menu, Button, Icon, Input, AutoComplete, Tree, message, Checkbox
} from 'antd';
import { inject, observer } from 'mobx-react';
import * as mobx from 'mobx';
import { toJS } from 'mobx';
import { withRouter } from 'react-router-dom';
import './index.scss';
import Loading from "../Loading";

const TreeNode = Tree.TreeNode;
let getWaitStock;

@inject(stores => ({
  visible: stores.batchImport.visible,
  confirmLoading: stores.batchImport.confirmLoading,
  closeHandler: stores.batchImport.closeHandler,
  cancelHandler: stores.batchImport.cancelHandler,
  confirmHandler: stores.batchImport.saveSelectedStocks,
  headMenuList: stores.batchImport.headMenuList,
  status: stores.batchImport.state,
  switchTabList: stores.batchImport.switchTabList,
  activeSwitchTabKey:
    stores.batchImport.activeSwitchTabItem.plateCode,
  changeSwitchTab:
    stores.batchImport.changeSwitchTab,
  fetchMyStockGroupList: stores.myStock.fetchMyStockGroupList,
  getStockListByGroupId: stores.myStock.getStockListByGroupId,
  changeListPage: stores.myStock.changeListPage, //改变当前页  
  selectedStockes: stores.batchImport.toBeSavedStocks,
  gid: stores.myStock.currentGroupId,
}))
class BatchModal extends Component {

  closeHandler = () => {
    if (this.props.closeCallback) {
      this.props.closeCallback();
    } else {
      this.props.closeHandler();
    }
  }

  cancelHandler = () => {
    if (this.props.cancelCallback) {
      this.props.cancelCallback();
    } else {
      this.props.cancelHandler();
    }
  }

  confirmHandler = () => {
    const {
      selectedStockes, confirmCallback, gid
    } = this.props;
    if (this.props.confirmCallback) {
      this.props.confirmCallback();
    } else {
      if (selectedStockes.length) {
        const o = {
          data: {
            groupType: -1,
          },
          successCallback: (successArr) => {
            message.success("添加成功");
            this.saveSuccessCallback(successArr);
          },
          errorCallback: (res) => {
            if (res.code === 80304){
              message.error("添加自选股数量已经达到上限1200个，无法继续添加。");
            } else {
              message.error("添加失败，请稍后再试");
            }
          }
        }
        if (gid !== -1) {
          o.data.groupId = gid;
        }
        o.data.subList = toJS(selectedStockes).map((stock) => {
          return {
            marketCode: stock.sec_type,
            scene: 2,
            secUniCode: stock.sec_uni_code,
            target: stock.abc_code,
            targetInfo: stock.sec_name,
          };
        });
        this.props.confirmHandler(o);
      }
    }
  }

  saveSuccessCallback = (successArr) => {
    this.props.changeListPage(0);
    this.props.fetchMyStockGroupList('fetchMyStockGroupList', {
      groupType: -1
    });
    this.props.getStockListByGroupId('getStockListByGroupId', {
      params: {
        market: 0,
        groupId: this.props.gid,
        groupType: -1,
        limit: 20,
        offset: 0,
        sort: 'sort',
      }
    })

    this.props.successCallback(successArr); //是否切换分组回调
  }

  render() {
    const style = {
      display:
        this.props.visible === true ? 'block' : 'none'
    };
    const contentRender = () => {
      const { status } = this.props;
      if (status === 'pending') {
        return (
          <Loading height="100%" />
        );
      }
      else if (status === 'error') {
        return (
          <div className='error-wrap'>
            <div className='error-image'>
            </div>
            <p>请求错误，请稍后再试！</p>
          </div>
        );
      } else if (status === 'done') {
        return (
          <div className='modal-content'>
            <div className='modal-header'>
              <HeadMenu />
            </div>
            <div className='modal-body'>
              {<SwitchTab
                list={this.props.switchTabList}
                activeKey={
                  this.props.activeSwitchTabKey
                }
                changeHandler={
                  this.props.changeSwitchTab
                }
              />
              }
              <div className='modal-body-main'>
                <StockSearch />
                <StockCascader />
              </div>
              <ToBeSavedList />
            </div>
            <div className='modal-footer'>
              <Button
                onClick={this.props.cancelHandler}
              >
                取消
            </Button>
              <Button
                className='confirm-btn'
                onClick={this.confirmHandler}
                type="primary"
              >
                添加
            </Button>
            </div>
          </div>
        );
      } else {
        return null;
      }
    }
    return (
      <div
        className='modal'
        style={style}
      >
        <div className='modal-content-wrap'>
          <Button
            className='modal-close-btn'
            onClick={this.closeHandler}
            icon='close'
          />
          {contentRender()}
        </div>
      </div>
    );
  }
}

@inject(stores => ({
  list: stores.batchImport.headMenuList,
  activeKey: String(
    stores.batchImport.activeMenuItem.marketCode
  ),
  changeMarket: stores.batchImport.changeMarket,
  curMarket: stores.myStock.curMarket,
}))
class HeadMenu extends Component {
  constructor(props) {
    super(props);
    if (this.props.curMarket) {
      this.setMarket();
    }
  }

  setMarket = (str) => {
    const { changeMarket, curMarket } = this.props;
    if (str) {
      return changeMarket(`${str}`);
    }
    changeMarket(`${curMarket}`);
  }
  clickHandle = e => {
    this.props.changeMarket(
      e.key
    );
  }

  componentDidUpdate (prveP) {
    if (prveP.curMarket !== this.props.curMarket) {
      if (this.props.curMarket) {
        this.setMarket();
      } else {
        this.setMarket(1004001);  //恢复默认沪深市场
      }
    }
  }

  render() {
    const MenuItem = Menu.Item;
    const list = this.props.list;
    const { curMarket } = this.props;
    return (
      <Menu
        onClick={this.clickHandle}
        selectedKeys={[this.props.activeKey]}
        mode='horizontal'
        className='header-menu'
      >
        {
          mobx.toJS(this.props.list).slice().map((item) => {
            if (curMarket) {  //左侧分组带市场属性
              if (curMarket === item.marketCode) {
                return (
                  <MenuItem key={String(item.marketCode)}>
                    {item.marketName}
                  </MenuItem>
                )
              } else {
                return null;
              }
            }
            return (
              <MenuItem key={String(item.marketCode)}>
                {item.marketName}
              </MenuItem>
            );
          })
        }
      </Menu>
    )
  }
}

// @inject(stores => ({
//   list: stores.batchImport.switchTabList,
//   activeKey:
//     stores.batchImport.activeSwitchTabItem.plateCode,
// }))
class SwitchTab extends Component {

  clickHandle = (e) => {
    if (e.key !== this.props.activeKey) {
      if (getWaitStock) {
        getWaitStock.cancel();
      }
      this.props.changeHandler(e.key);
    }
  }

  render() {
    const MenuItem = Menu.Item;
    const className = this.props.className ?
      ' ' + this.props.className : '';
    return (
      <Menu
        onClick={this.clickHandle}
        selectedKeys={[String(this.props.activeKey)]}
        mode='vertical'
        theme='light'
        className={`switch-tab${className}`}
      >
        {
          this.props.list.map(
            (item) => {
              return (
                <MenuItem
                  key={String(item.plateCode)}
                >
                  {item.plateName}
                </MenuItem>
              )
            }
          )
        }
      </Menu>
    )
  }
}

@inject(stores => ({
  selectedStockes: stores.batchImport.toBeSavedStocks,
  searchSuggestStock: stores.batchImport.searchHandler,
  value: stores.batchImport.searchValue,
  clearInputZone: stores.batchImport.clearInputZone,
  results: stores.batchImport.searchedStocks,
  marketCode:
    stores.batchImport.activeMenuItem.marketCode,
  gid:
    stores.myStock.currentGroupId,
  addStock:
    stores.batchImport.addSelectedStock,
  isSearched:
    stores.batchImport.isSearched,
}))
@observer
class StockSearch extends Component {
  constructor(props) {
    super(props);
    this.state = {
      value: this.props.value,
    };
  }

  searchHandler = (value) => {
    const o = {};
    const {
      gid, marketCode, searchSuggestStock
    } = this.props;
    if (gid !== -1) {
      o.groupId = gid;
    }
    Object.assign(o, {
      keyword: value,
      groupType: -1,
      marketCodes: String(marketCode),
    });
    searchSuggestStock(o);
  }

  selectHandler = (v, option) => {
    const { results, addStock } = this.props;
    const o = option;
    const code = o.key;
    results.forEach(item => {
      if (item.sec_uni_code === +code) {
        addStock(item);
        return false;
      }
    });
  }

  render() {
    const {
      value, results, gid, selectedStockes, isSearched
    } = this.props;
    let dataSource;
    if (results.length) {
      dataSource = results.map((item, index) => {
        const Option = AutoComplete.Option;
        let iconProp, optionProp, str = '';
        if (
          item.substatus !== 0 ||
          checkInselectedStock(item.sec_uni_code, selectedStockes)
        ) {
          iconProp = {
            type: 'check',
            className: 'disabled',
          };
          optionProp = {
            className: 'stock-search-item',
            key: item.sec_uni_code + index,
            disabled: true,
            text: item.sec_name,
          };
        } else {
          iconProp = {
            type: 'plus',
          };
          optionProp = {
            className: 'stock-search-item',
            key: item.sec_uni_code + index,
            disabled: false,
            text: item.sec_name,
          };
        }
        if (item.status !== 0) {
          switch (item.status) {  //获取状态文案
            case -2:
              str = "(退市)";
              break;
            case -1:
              str = "(停牌)";
              break;
            default:
              str = "";
              break;
          }
        } else if (item.substatus !== 0) {
          switch (item.substatus) {  //获取状态文案
            case 1:
              str = "(已添加)";
              break;
            case 2:
              str = "(实际持仓)";
              break;
            case 3:
               str = "(模拟持仓)";
            default:
              str = "";
              break;
          }
        }

        return (
          <Option
            {...optionProp}
          >
            <span className='stock-info'>
              {`${item.abc_code} ${item.sec_name} ${str}`}
            </span>
            <Icon
              {...iconProp}
            />
          </Option>
        );
      });
    } else if (isSearched) {
      dataSource = [
        <AutoComplete.Option key='0'>
          {'对不起，没有找到相关内容'}
        </AutoComplete.Option>
      ];
    }
    return (
      <div
        className='search-box-wrap'>
        <AutoComplete
          onSelect={
            this.selectHandler
          }
          className='stock-search'
          style={
            {
              width: '100%',
            }
          }
          allowClear={true}
          optionLabelProp='text'
          dataSource={dataSource}
          onChange={this.searchHandler}
          placeholder='代码/简拼/名称'
          backfill={false}
          value={value}
          children={
            <Input
              prefix={
                <Icon
                  type='search'
                  style={
                    { color: 'rgba(0,0,0,.25)' }
                  }
                />
              }
              onFocus={this.handleFocus}
              onBlur={this.handleBlur}
            />
          }
        >
        </AutoComplete>
      </div>
    );
  }
}


@inject(stores => ({
  list: stores.batchImport.casecaderList,
  activeKey:
    stores.batchImport.activeCascaderKey,
  columnNum:
    stores.batchImport.casecaderColums,
  changeCategory:
    stores.batchImport.changeActiveCascaderKey,
  waitZoneList:
    stores.batchImport.waitZoneStocks,
  addedNum:
    stores.batchImport.addedNum,
  addableNum:
    stores.batchImport.addableNum,
  removeAll:
    stores.batchImport.cancelAddAll,
  addAllWaitStock:
    stores.batchImport.addAllToSelected,
}))
@observer
class StockCascader extends Component {
  constructor(props) {
    super(props);
  }

  changeCategory = (key) => {
    if (getWaitStock) {
      getWaitStock.cancel();
    }
    this.props.changeCategory(key);
  }

  addAll = () => {
    this.props.addAllWaitStock();
  }
  changeAddAll = (e) => {
    if (e.target.checked) {
      this.props.addAllWaitStock();
    } else {
      this.props.removeAll();
    }
  }
  render() {
    const {
      columnNum,
      list,
      activeKey,
      waitZoneList,
      addedNum,
      addableNum,
    } = this.props;
    const treeList =
      getTreeData(list, activeKey, columnNum);
    const anableSelected = waitZoneList.some((item) => {
      return item.status == 0
    });
    return (
      <div className={`cascader-wrap total${columnNum}`}>
        {
          columnNum > 2 ?
            <div
              className='first-switcher-wrap'
            >
              <h3>行业标准</h3>
              <SwitchTab className='first-switcher'
                list={list}
                activeKey={toJS(activeKey)}
                changeHandler={this.changeCategory}
              />
            </div> :
            null
        }
        <div className="tree-selector-wrap">
          <h3>分类</h3>
          <TreeSelector
            list={treeList}
          />
        </div>
        <div className="multiple-selector-wrap">
          <h3>
            证券&nbsp;
            <span className='sel-info'>
              (
                <i className='sel-num'>
                {addedNum}
              </i>
              /{waitZoneList.length})
            </span>
            {
              // addableNum > 0 ?
              //   <Icon
              //     style={
              //       {
              //         fontSize: '14px'
              //       }
              //     }
              //     type='plus'
              //     className='add-all-btn'
              //     onClick={this.addAll}
              //   /> :
              //   null
              waitZoneList.length > 0 && anableSelected && (
                <Checkbox className='add-all-btn'
                  checked={addableNum > 0 ? false : true}
                  onChange={this.changeAddAll}
                >
                  <span style={{ fontSize: '12px', color: '#333333' }}>全选</span>
                </Checkbox>
              )
            }
            {
              waitZoneList.length > 0 && !anableSelected && (
                <Checkbox className='add-all-btn'
                  checked={addableNum > 0 ? false : true}
                  disabled
                  onChange={this.changeAddAll}
                >
                  <span style={{ fontSize: '12px', color: '#333333' }}>全选</span>
                </Checkbox>
              )
            }
          </h3>
          <MultipleSelector />
        </div>
      </div>
    );
  }
}

@inject(stores => ({
  changeCategory: stores.batchImport.getWaitZoneStocks,
  gid: stores.myStock.currentGroupId,
}))
class TreeSelector extends Component {

  clickHandler = (keys) => {
    const code = keys[0];
    const o = {
      groupType: -1,
      plateCode: code,
    };
    if (this.props.gid !== -1) {
      o.groupId = this.props.gid;
    }
    if (getWaitStock) {
      getWaitStock.cancel();
    }
    getWaitStock = this.props.changeCategory({
      params: o,
    });
    getWaitStock.catch(() => {
    });
  }

  render() {
    const loop = data => data.map((item) => {
      const title = <span>{item.title}</span>;
      if (item.children) {
        return (
          <TreeNode
            className='tree-node'
            key={item.plateCode} title={item.plateName}
          >
            {loop(item.children)}
          </TreeNode>
        );
      }
      return (<TreeNode
        key={item.plateCode}
        title={item.plateName} />);
    });
    return (
      <Tree
        onSelect={this.clickHandler}
      >
        {loop(this.props.list)}
      </Tree>
    );
  }
}

@inject(stores => ({
  list:
    stores.batchImport.waitZoneStocks,
  selectedList:
    stores.batchImport.toBeSavedStocks,
  addSelectedItem:
    stores.batchImport.addSelectedStock,
  status:
    stores.batchImport.waitZoneState,
}))
@observer
class MultipleSelector extends Component {

  clickHandler = (item) => {
    const selectedList = this.props.selectedList;
    this.props.addSelectedItem(item);
  }

  render() {
    const { list, selectedList, status } = this.props;
    let addedNum = 0;
    if (status === 'pending') {
      return (
        <Loading
          type="list"
          height="416px">
        </Loading>
      );
    }
    return (
      <ul className='multiple-selector-list'>
        {list.map((item, index) => {
          let iconType, iconClassName = 'oper-btn';
          let statusStr = '', itmClassName = '';
          if (item.substatus !== 0) {
            iconClassName += ' disabled';
            itmClassName = 'disabled';
            iconType = 'check';
          } else {
            iconType = 'plus';
            if (selectedList.find(seledItem => (
              seledItem.sec_uni_code === item.sec_uni_code
            ))) {
              iconClassName += ' disabled';
            }
          }

          if (item.status === -1) {
            statusStr = ' (退市)';
          } else if (item.status === -2) {
            statusStr = ' (停牌)'
          }

          if (item.substatus === 1) {
            statusStr = ' (已添加)'
          }

          return (
            <li key={item.sec_uni_code + index}
              className={itmClassName}
              onClick={
                () => {
                  if (iconClassName.indexOf('disabled') === -1) {
                    this.clickHandler(item);
                  }
                }
              }
            >
              <p
                className='wait-zone-text'
                title={`${item.abc_code} ${item.sec_name} ${statusStr}`}
              >
                {item.abc_code}&nbsp;{item.sec_name}&nbsp;{statusStr}
              </p>
              <Icon
                style={
                  {
                    fontSize: '14px'
                  }
                }
                type={iconType}
                className={iconClassName}
              />
            </li>
          );
        })}
      </ul>
    );
  }
}


@inject(stores => ({
  list:
    stores.batchImport.toBeSavedStocks,
  removeHandler:
    stores.batchImport.removeSelectedStock,
  removeAll:
    stores.batchImport.clearSelectedStock,
}))
@observer
class ToBeSavedList extends Component {

  clickHandler = i => {
    this.props.removeHandler(i);
  }

  removeAllHandler = () => {
    this.props.removeAll();
  }

  render() {
    const { list } = this.props;
    const length = list.length;
    return (
      <div className='tobesaved-list'>
        <h3>
          已选&nbsp;
          <span className='sel-info'>
            ({length})
          </span>
          {
            length ?
              <span
                className='cancelall-btn'
                onClick={this.removeAllHandler}
              >
                清除
              </span> :
              null
          }
        </h3>
        <ul className='selected-list'>
          {
            this.props.list.map((item, index) => {
              return (
                <li
                  key={item.sec_uni_code}
                  onClick={() => {
                    this.clickHandler(index);
                  }}
                  title={`${item.sec_uni_code}  ${item.sec_name}`}
                >
                  <span>
                    {item.abc_code}&nbsp;&nbsp;{item.sec_name}
                  </span>
                  <Icon className='cancel-btn' type='close-circle' />
                </li>
              );
            })
          }
        </ul>
      </div>
    );
  }
}

function getTreeData(arr, key, num) {
  if (num > 2) {
    return arr.find(item => {
      return key === item.plateCode;
    }).children;
  } else {
    return arr;
  }
}

function checkInselectedStock(code, selectedList) {
  let bool = false;
  selectedList.forEach((stock) => {
    if (
      code === stock.sec_uni_code
    ) {
      bool = true;
      return false;
    }
  });
  return bool;
}

export default BatchModal;