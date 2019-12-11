/**
 * @description 自选股状态
 * @date 2018.05.07
 * @author zhhu
 */

import React, { Component } from "react";
import {
  Row,
  Col,
  Menu,
  Icon,
  Popover,
  Button,
  Card,
  Input,
  Table,
  Dropdown,
  Checkbox,
  Divider,
  Affix,
  message,
  Select,
  Popconfirm,
  Modal,
  Spin,
  Switch,
  Tooltip,
} from "antd";
import Scrollbar from "smooth-scrollbar";
import InfiniteScroll from "react-infinite-scroller";
import { withRouter, NavLink } from "react-router-dom";
import CreateGroupName from "./components/CreateGroupName";
import Cookies from "js-cookie";
import moment from "moment";

import 'intersection-observer';
import Observer from 'react-intersection-observer';

import "./main.scss";
import { observer, inject } from "mobx-react";
import SearchAddSingleInput from "./components/SearchAddSingleInput";
import CreateDealLog from "./components/CreateDealLog";
import BatchImport from "./components/BatchImport";
import Ellipsis from "./components/Ellipsis";
import TakeAPosition from "./components/TakeAPosition";
import Loading from "./components/Loading";
import {
  throttle, getStockIcon,
  getHost, toDecimal, getUnit, getUnroundedUnit
} from "./lib/utils";
import addMyStock from "./addMyStock.png";
const initDealLog = {
  id: '',
  // 红利	
  bonus: '',
  commissionView: 0.00,
  // 佣金	
  commission: '',
  // 佣金率 单位：‰	
  commissionRate: '',
  // 【必填】交易日期	
  dealDate: moment().format('YYYY-MM-DD'),
  // 送股	
  donateShare: '',
  // 转增股	
  intoShare: '',
  // 持仓模式（实际/模拟）0实际持仓，1模拟持仓
  mode: '0',
  // 股票价格
  price: '',
  // 备注	
  remark: '',
  //【必填】证券代码	
  stock: '',
  // 税费	
  taxView: 0.00,
  tax: '',
  // 税率 单位：‰	
  taxRate: '',
  //【必填】交易类型 1买入，2卖出，3补回，4卖空，5合股，6拆股，7除权除息	
  type: '1',
  // 更新commission
  updateCommission: 1,
  //更新tax
  updateTax: 1,
  // 股票数量
  volume: '',
  // 当前价格
  // current: '',
  // 佣金单位
  commissionUnit: '',
  // 税率单位
  taxUnit: '',
}
const userId = Cookies.get("userId");
const token = Cookies.get("token");
const Option = Select.Option;
const CheckboxGroup = Checkbox.Group;
const confirm = Modal.confirm;
const min = 10,
  max = 66;

const toFixed = (num, keep) => {
  const n = new Number(num);
  return parseFloat(n.toFixed(keep));
};

const getColor = differ => {
  if (differ > 0) {
    return {
      color: "#D9554E"
    };
  } else if (differ < 0) {
    return {
      color: "#4DA04C"
    };
  } else {
    return {
      color: "#9B9B9B"
    };
  }
};
const getBackgroundColor = differ => {
  if (differ > 0) {
    return {
      background: "#D9554E"
    };
  } else if (differ < 0) {
    return {
      background: "#4DA04C"
    };
  } else {
    return {
      background: "#9B9B9B"
    };
  }
};

@inject(Stores => ({
  changeVisible: Stores.addGroupName.changeVisible,
  editGroup: Stores.addGroupName.editGroup,
  handleOkCallback: Stores.addGroupName.handleOkCallback,
  fetchMyStockGroupList: Stores.myStock.fetchMyStockGroupList,
  getStockListByGroupId: Stores.myStock.getStockListByGroupId,
  cancelStockListByStockCodesArr: Stores.myStock.cancelStockListByStockCodesArr,
  stockGroup: Stores.myStock.stockGroup,
  marketGroup: Stores.myStock.marketGroup,
  currentGroup: Stores.myStock.currentGroup,
  stockList: Stores.myStock.stockList,
  marketList: Stores.myStock.marketList,
  setTableProps: Stores.myStock.setTableProps,
  fetchMarketList: Stores.myStock.fetchMarketList,
  setCopyList: Stores.myStock.setCopyList,
  copyStock: Stores.myStock.copyStock,
  deleteStock: Stores.myStock.deleteStock,
  setCurrentGroupId: Stores.myStock.setCurrentGroupId,
  currentGroupId: Stores.myStock.currentGroupId,
  defaultGroupId: Stores.myStock.defaultGroupId,
  currentGroupName: Stores.myStock.currentGroupName,
  copyList: Stores.myStock.copyList,
  deleteGroup: Stores.myStock.deleteGroup,
  selectedMenu: Stores.myStock.selectedMenu,
  stockSetTop: Stores.myStock.stockSetTop,
  selectedMyStockKeys: Stores.myStock.selectedMyStockKeys,
  hasMore: Stores.myStock.hasMore,
  fetchOwnStockState: Stores.myStock.fetchOwnStockState,
  changeListPage: Stores.myStock.changeListPage, //改变当前页
  currentPage: Stores.myStock.currentPage,
  loadingState: Stores.myStock.loadingState,
  stepEnabled: Stores.myStock.stepEnabled,
  updateTimer: Stores.myStock.updateTimer, //定时刷新数据
  last_stockList: Stores.myStock.last_stockList, //上一次数据
  showLoading: Stores.myStock.showLoading,
  visible: Stores.addGroupName.visible,
  changeVisible: Stores.addGroupName.changeVisible,
  changeInputGroup: Stores.searchAddSingleInput.changeInputGroup,
  xhr_status: Stores.searchAddSingleInput.xhr_status,
  handleAddStockSelect: Stores.searchAddSingleInput.handleAddStockSelect,

  showBatchImport: Stores.batchImport.show,
  getStockById: Stores.ownStock.getStockById,
  profitState: Stores.myStock.profitState,
  getProfitData: Stores.myStock.getProfitData,
  profitSimulated: Stores.myStock.profitSimulated,
  profitActual: Stores.myStock.profitActual,
  profitIsempty: Stores.myStock.profitIsempty,

  searchStock: Stores.myStock.searchStock, //搜索股票
  stockDataSource: Stores.myStock.stockDataSource, //添加搜索股票数据来源
  resetFields: Stores.stockDealLog.resetFields,
  setProps: Stores.stockDealLog.setProps,
}))
@withRouter
class MyStock extends Component {
  constructor(props) {
    super(props);
    this.addGroupName = this.props;
  }
  state = {
    currentGroupName: "",
    operatioVisible: false,
    market: 0, //市场: 1沪深、2港股、3美股、4基金、5债券
    stockType: "0",
    isAll: "",
    showGuide: window.localStorage.getItem("showGuide") ? false : true,
    selectedRowKeys: [], // Check here to configure the default column
    stockTitle: "全部",
    delHockInfo: 0, //是否同时删除持仓记录
    delSubscribeInfo: 0, //是否同时删除其他组个股
    showNoHold: 1, //是否显示无持仓数据
    btnDisabled: () => {
      if (this.state.selectedRowKeys.length == 0) {
        return "disabled";
      }
    },
    copyVisible: false,
    cutVisible: false,
    holdstockVisible: false,
    curStockCode: 0,
    isSorter: false,
    //持有量单位换算
    unitTypes: {
      1: '股',
      100: '手',
    },
    // 默认显示股
    holdingType: 1,

    bargainType: 1,
    currentStockCode: ''
  };
  componentDidMount = () => {
    const {
      fetchMarketList,
      getProfitData,
      fetchMyStockGroupList,
      history,
      loadingState
    } = this.props;
    const params = {
      groupType: -1
    };
    fetchMyStockGroupList("fetchMyStockGroupList", params);
    fetchMarketList("fetchMarketList", {
      params: {
        level: 1
      }
    });
    getProfitData();
  };
  componentDidUpdate = () => {
    const { loadingState, stockList, hasMore, currentPage } = this.props;
    const options = {
      thumbMinSize: 20
    };

    // Scrollbar.init(document.querySelector('.ant-table-body'), options);
    Scrollbar.init(this.menuNode, options);
    // Scrollbar.init(document.body, options);
    // Scrollbar.init(document., options);
    if (loadingState === "done" &&
      !hasMore &&
      [...stockList].length === 0) {
      this.table = {
        head: document.querySelector(".nodata .thead .ant-table-scroll .ant-table-body"),
        body: document.querySelector(".nodata .tbody .ant-table-scroll .ant-table-body")
      };
    } else {
      this.table = {
        head: document.querySelector(".thead .ant-table-scroll .ant-table-body"),
        body: document.querySelector(".tbody .ant-table-scroll .ant-table-body")
      };
    }

    if (this.flagStr !== loadingState + currentPage) {
      if (currentPage == 0 && loadingState === 'done') {
        this.refreshAffix();
      }
      this.flagStr = loadingState + currentPage;
    }
  };
  componentWillUnmount = () => {
    const { setCurrentGroupId, defaultGroupId, cancelStockListByStockCodesArr } = this.props;
    setCurrentGroupId('0,0');
    this.resetState();
    cancelStockListByStockCodesArr();  //取消轮询实时刷新请求
    window.sessionStorage.removeItem('_last_list');
  };
  resetState = (callback) => {
    this.setState({
      selectedRowKeys: [],
      showNoHold: 1,
      market: 0, //市场: 1沪深、2港股、3美股、4基金、5债券
      stockType: "0",
      isAll: "",
      isSorter: false,
      sorterInfo: ''
    }, () => {
      if (callback) {
        callback();
      }
    });
  };
  getGroupList = (data) => {
    const { fetchMyStockGroupList } = this.props;
    const params = {
      groupType: -1,
      ...data
    };
    fetchMyStockGroupList("fetchMyStockGroupList", params);
  };
  renderList = (currentGroupId, market = 0) => {
    const {
      fetchMyStockGroupList,
      getStockListByGroupId,
      setCurrentGroupId,
      currentPage,
      currentGroup
    } = this.props;
    const { showNoHold } = this.state;
    getStockListByGroupId(
      "getStockListByGroupId",
      {
        params: {
          market: currentGroup.market,
          groupId: currentGroup.id,
          groupType: -1,
          showNoHold: showNoHold,
          offset: 0,
        }
      },
      0,
      true
    );
    this.setState({
      selectedRowKeys: []
    });
  };
  onSelectChange = selectedRowKeys => {
    this.setState({
      selectedRowKeys,
      isAll: ""
    });
  };
  showGroupNameModal = () => {
    this.addGroupName.changeVisible(true);
  };
  editGroupName = () => {
    const { currentGroupName, currentGroupId } = this.props;
    this.addGroupName.editGroup(currentGroupName, currentGroupId);
  };
  deleteGroupName = () => {
    const {
      fetchMyStockGroupList,
      deleteGroup,
      currentGroupId,
      currentGroup,
      setCurrentGroupId,
      currentGroupName
    } = this.props;

    confirm({
      title: "确定删除该分组以及分组内所有股票？",
      content: this.getConfirmContent(),
      okText: "确认",
      cancelText: "取消",
      onOk: () => {
        const params = {
          groupId: currentGroupId,
          delHockInfo: this.state.delHockInfo,
          delSubscribeInfo: this.state.delSubscribeInfo
        };
        deleteGroup(params, () => {
          message.success("删除成功");
          this.selectedMyStockKeys = ["-1"];
          setCurrentGroupId(`${currentGroup.id},${currentGroup.market}`, currentGroup.name, () => {
            this.getGroupList();
            // this.renderList(0);
          })
        });
      },
      onCancel() { }
    });
  };
  closeGuide = () => {
    window.localStorage.setItem("showGuide", "false");
    this.setState({
      showGuide: false
    });
  };
  getStockGroupMenu = stockGroup => {
    const menus = [];
    stockGroup.forEach(group => {
      let { title, id, market, count } = group;
      menus.push({
        name: title,
        key: this.getId(id, market),
        count: count
      });
    });
    return menus;
  };
  getMarketGroupMenu = stockGroup => {
    const menus = [];
    stockGroup.forEach(group => {
      let { id, title, market, count } = group;
      menus.push({
        name: title,
        key: this.getId(id, market),
        count: count
      });
    });
    return menus;
  };
  confirmCopy = e => {
    const { selectedRowKeys, isAll } = this.state;
    const {
      copyStock,
      currentGroup,
      copyList: [...copyList],
      setCurrentGroupId
    } = this.props;
    const params = {
      filterGid: currentGroup.id,
      groupType: -1,
      market: currentGroup.market,
      ids: selectedRowKeys,
      isAll: isAll ? isAll : 0,
      setType: e.currentTarget.value,
      toGidList: [...copyList].map((key) => {
        const [id, market] = key.split(",");
        return id;
      })
    };
    copyStock("copyStock", params, () => {
      setCurrentGroupId(copyList[0]);
      this.renderList(copyList[0]);
      this.getGroupList();
      this.setState({
        copyVisible: false,
        cutVisible: false,
        showNoHold: 1,
        stockType: '0',
      });
    });
  };
  clickCopy = e => {
    if (e.currentTarget.value == 1) {
      this.setState({
        cutVisible: true
      });
    } else if (e.currentTarget.value == 0) {
      this.setState({
        copyVisible: true
      });
    }
  };
  getCutContent = menus => {
    const { copyList } = this.props;
    const plainOptions = this.getPlainOptions(menus);
    return (
      <div className="cutContent">
        <div className="title">移动到</div>
        <div
          className="checkboxGroup"
          ref={node => {
            this.cutCheckboxGroup = node;
          }}
        >
          <CheckboxGroup
            options={plainOptions}
            value={[...copyList]}
            onChange={this.changeCopyValue}
          />
        </div>
        <Divider />
        <div>
          <Button
            type="primary"
            value={1}
            onClick={this.confirmCopy}
            disabled={[...copyList].length === 0}
            style={{ width: "100%" }}
          >
            移动
          </Button>
        </div>
      </div>
    );
  };
  getCopyContent = menus => {
    const { copyList } = this.props;
    const plainOptions = this.getPlainOptions(menus);
    return (
      <div className="cutContent">
        <div className="title">复制到</div>
        <div>
          <CheckboxGroup
            options={[...plainOptions]}
            value={[...copyList]}
            onChange={this.changeCopyValue}
          />
        </div>
        <Divider />
        <div>
          <Button
            type="primary"
            value={0}
            onClick={this.confirmCopy}
            disabled={[...copyList].length === 0}
            style={{ width: "100%" }}
          >
            复制
          </Button>
        </div>
      </div>
    );
  };
  getPlainOptions = menus => {
    const plainOptions = [];
    menus.forEach(menu => {
      plainOptions.push({
        label: menu.name,
        value: menu.key
      });
    });
    return [...plainOptions];
  };
  getMyStock = menus => {
    let arr = menus.filter(menu => {
      return !menu.id;
    });
    if (arr.length > 0) {
      return {
        name: arr[0].title,
        count: arr[0].count,
        group_name_id: arr[0].group_name_id
      };
    }
    return {
      name: "",
      count: 0
    };
  };
  getCurrentGroup = (menus, marketGroup = []) => {
    const { currentGroupId } = this.props;
    let arr = [...menus, ...marketGroup].filter(menu => {
      return currentGroupId > 0
        ? menu.id == currentGroupId || menu.market == currentGroupId
        : !menu.id;
    });
    if (arr.length > 0) {
      return {
        name: arr[0].title,
        count: arr[0].count,
        group_name_id: arr[0].group_name_id
      };
    }
    return {
      name: "",
      count: 0,
      group_name_id: "",
      market: ""
    };
  };
  handleCopyVisibleChange = visible => {
    this.setState({
      copyVisible: visible
    });
  };
  handleCutVisibleChange = visible => {
    this.setState({
      cutVisible: visible
    });
  };
  changeCopyValue = checkedValue => {
    this.props.setCopyList(checkedValue);
  };
  getId = (id, market) => {
    return `${id},${market ? market : 0}`;
  }
  selectMyStock = e => {
    //切换分组，删除搜索框中的输入
    let clearDom = document.querySelector('.ant-select-selection__clear');
    if (clearDom) clearDom.click();
    const {
      setCurrentGroupId,
      setCurrentGroupName,
      selectedMenu,
      changeInputGroup,
      getStockListByGroupId,
      changeListPage,
      setTableProps,
    } = this.props;
    const [id, market] = e.key.split(",");

    changeListPage(0);

    this.resetSorter();

    // setCopyList([]);
    setCurrentGroupId(e.key, e.item.props.name);
    changeInputGroup(id); //修改添加股票代码store
    this.resetState(() => {
      this.renderList(0, market, 'refresh');
    });
    this.table.scrollLeft = 0;
    document.scrollingElement.scrollTop = 0;
    // 埋点
    this.refreshAffix();
  };
  changeStockGroup = e => {
    //切换分组，删除搜索框中的输入
    let clearDom = document.querySelector('.ant-select-selection__clear');
    if (clearDom) clearDom.click();
    const {
      setCopyList,
      setCurrentGroupId,
      getStockListByGroupId,
      currentGroupId,
      setTableProps,
      currentGroupName,
      changeInputGroup,
      changeListPage,
      updateTimer
    } = this.props;
    clearTimeout(updateTimer);
    changeListPage(0);
    // setCopyList([]);

    this.resetSorter();
    setTableProps({}, {}, {
      columnKey: 'sort',
      order: 'descend'
    });
    setCurrentGroupId(e.key, e.item.props.name);
    const [id, market] = e.key.split(",");
    changeInputGroup(id); //修改添加股票代码store
    this.resetState(() => {
      this.renderList(id, market);
    });
    this.table.scrollLeft = 0;
    this.refreshAffix();
  };

  refreshAffix() {
    // 重置浮钉
    document.scrollingElement.scrollTop = 1;
    setTimeout(() => {
      document.scrollingElement.scrollTop = 0;

      let stockTableContainer = document.getElementById('stock-table-container');
      if (stockTableContainer) {
        let [affix] = stockTableContainer.getElementsByClassName('ant-affix');
        if (affix) {
          affix.style = '';
          affix.className = '';
          if (affix.parentElement && affix.parentElement.style) {
            affix.parentElement.style = '';
          }
        }
      }
    }, 900)
  }

  resetSorter = () => {
    const activeSorterIcon = document.querySelector('th span.on');  //手动取消排序状态
    if (activeSorterIcon) {
      activeSorterIcon.click();
    }
  }

  changeDelHockInfo = e => {
    this.state.delHockInfo = e.target.checked ? 1 : 0;
  };
  changeDelSubscribeInfo = e => {
    this.state.delSubscribeInfo = e.target.checked ? 1 : 0;
  };
  getConfirmContent = () => {
    const { currentGroup } = this.props;
    let result = (
      <div>
        <div className="deleteDivision">
          <Checkbox onChange={this.changeDelSubscribeInfo}>
            同时删除其他分组内相同股票
          </Checkbox>
        </div>
        {/* <div>
          <Checkbox onChange={this.changeDelHockInfo}>
            <span style={{ color: "#666666" }}>同时删除持仓记录</span>
          </Checkbox>
        </div> */}
      </div>
    );
    if (false && (currentGroup.id === 0 || currentGroup.market > 0)) {
      result = (
        <div>
          <div className="deleteDivision">
            {/* 同时删除其他分组内相同股票 */}
            <Checkbox onChange={this.changeDelHockInfo}>
              同时删除持仓记录
            </Checkbox>
          </div>
        </div>
      );
    }
    return result;
  };
  isMarket = currentGroup => {
    return currentGroup.group_name_id == 'system';
  };
  deleteStock = record => {
    let { id, stockName } = {};
    if (record) {
      id = record.id;
      stockName = record.stockName;
    }
    const stockId = id;
    let { selectedRowKeys, isAll, stockType } = this.state;
    const {
      deleteStock,
      currentGroupId,
      currentGroup,
      setCurrentGroupId,
      fetchOwnStockState,
      currentGroupName
    } = this.props;
    isAll = stockId ? 0 : isAll;
    //删除菜单
    confirm({
      title:
        isAll > 0
          ? `确定删除当前分组及分组内所有股票？`
          : `确定删除当前已选股票？`,
      content: this.getConfirmContent(),
      okText: "确认",
      cancelText: "取消",
      onOk: () => {
        const params = {
          groupId: this.isMarket(currentGroup) ? 0 : currentGroupId,
          groupType: -1,
          isAll: isAll ? isAll : 0,
          ids: stockId ? [stockId] : selectedRowKeys,
          market: currentGroup.market || 0,
          delHockInfo: this.state.delHockInfo,
          delSubscribeInfo: this.isMarket(currentGroup)
            ? 1
            : this.state.delSubscribeInfo
        };
        deleteStock("deleteStock", params, () => {
          const { id, market } = currentGroup;
          setCurrentGroupId(this.getId(id, market), '', () => {
            this.getGroupList({ market: stockType > 0 ? stockType : currentGroup.market });
          })

          this.props.getProfitData();
          this.setState({
            showNoHold: 1,
            delHockInfo: 0,
            delSubscribeInfo: 0,
            selectedRowKeys: []
          })
        });
        this.setState({
          isAll: ""
        });
      },
      onCancel() { }
    });
  };
  handleTableChange = (pagination, filters, sorter) => {
    if (sorter.order) {
      this.setState({
        isSorter: true,
        sorterInfo: sorter,
        selectedRowKeys: [],
      })
    } else {
      this.setState({
        isSorter: false,
        sorterInfo: sorter,
        selectedRowKeys: [],
      })
    }
    const { stockType } = this.state;
    let {
      currentGroupId,
      currentGroup,
      getStockListByGroupId,
      setTableProps,
      currentPage
    } = this.props;
    setTableProps(pagination, filters, sorter, (currentPage = 0));
    let groupId = 0;
    if (currentGroupId > -1) {
      groupId = currentGroupId;
    }
    if (!sorter.columnKey) {
      sorter.columnKey = "sort";
    } else {
      sorter.order = sorter.order ? sorter.order.slice(0, -3) : "";
    }
    getStockListByGroupId(
      "getStockListByGroupId",
      {
        params: {
          market: currentGroup.market > 0 ? currentGroup.market : stockType,
          groupId: groupId,
          groupType: -1,
          sort: sorter.columnKey,
          sortType: sorter.order,
          showNoHold: this.state.showNoHold,
          // market: currentGroup.market,
        }
      },
      0,
      true
    );
  };
  handleVisibleChange = flag => {
    this.setState({ operationVisible: flag });
  };
  getExportUrl = () => {
    const { selectedRowKeys, isAll } = this.state;
    const { currentGroup } = this.props;
    const { host } = window.location;
    const path = "/api/usercenter/subscriptions/export";
    if (isAll === 1) {
      return (
        getHost() +
        path +
        "?groupId=" +
        currentGroup.id +
        (currentGroup.market ? `&market=${currentGroup.market}` : '') +
        "&userId=" +
        userId +
        "&token=" +
        token +
        "&groupType=-1&isAll=" +
        isAll
      );
    }
    return (
      getHost() +
      path +
      "?groupId=" +
      currentGroup.id +
      (currentGroup.market ? `&market=${currentGroup.market}` : '') +
      "&userId=" +
      userId +
      "&token=" +
      token +
      "&groupType=-1&idsString=" +
      selectedRowKeys.join(",")
    );
  };
  handleInfiniteOnLoad = () => {
    const { stockType, showNoHold } = this.state;
    const {
      getStockListByGroupId,
      currentGroupId,
      currentGroup,
      changeListPage,
      currentPage,
      loadingState,
      updateTimer
    } = this.props;
    if (loadingState == "pending") {
      clearTimeout(updateTimer);
      return;
    }
    let page = currentPage + 1;
    let params = {
      market: stockType,
      groupId: currentGroupId > 0 ? currentGroupId : 0,
      groupType: -1,
      showNoHold: showNoHold,
    };
    if (currentGroup.market) {
      params = {
        ...params,
        ...{ market: currentGroup.market, groupId: currentGroup.id }
      };
    }
    if (this.props.hasMore) {
      changeListPage(page);
      getStockListByGroupId(
        "getStockListByGroupId",
        {
          params: params
        },
        page
      );
    }
  };
  handleChange = key => {
    let groupId = 0;
    const {
      getStockListByGroupId,
      currentGroupId,
      changeListPage
    } = this.props;
    if (currentGroupId > -1) {
      groupId = currentGroupId;
    }
    getStockListByGroupId(
      "getStockListByGroupId",
      {
        params: {
          market: key,
          groupId: groupId,
          groupType: -1,
          sort: "sort"
        }
      },
      0,
      true
    );
    this.setState({
      stockType: key,
      selectedRowKeys: [],
    });
    changeListPage(-1);
  };
  getStockTitle = () => {
    const { marketList, currentGroup } = this.props;
    const { stockType } = this.state;
    if (currentGroup.group_name_id === 'system' && currentGroup.market) {
      return <div className="middle" style={{ height: '32px' }}>{currentGroup.title}</div>;


    } else {
      return (
        <Select
          className="stockTypes"
          value={stockType}
          style={{ width: "80px" }}
          onChange={this.handleChange}
        >
          <Option value="0">全部</Option>
          {marketList.map(m => {
            return (
              <Option key={m.marketCode} value={m.marketCode}>
                {m.marketName}
              </Option>
            );
          })}
        </Select>
      );
    }
  };
  handleAddStock = (value, option) => {
    const {
      currentGroupId,
      currentGroup,
      setCurrentGroupId,
      getStockListByGroupId,
      fetchMyStockGroupList,
      handleAddStockSelect,
      searchStock
    } = this.props;
    let stockCode = option.props.code;
    let stockName = option.props.text;
    let marketCode = option.props.marketcode;
    let secUniCode = option.props.secUniCode;
    let that = this;
    let suc_callback = () => {
      let clearDom = document.querySelector('.ant-select-selection__clear');
      if (clearDom) clearDom.click();
      if (currentGroup.group_name_id === 'system') {
        searchStock(stockName);
        setCurrentGroupId(that.getId(currentGroup.id, currentGroup.market), currentGroup.title, () => {
          // that.renderList(currentGroupId);
          that.getGroupList();
        });
      } else {
        searchStock(stockName);
        setCurrentGroupId(that.getId(currentGroup.id, currentGroup.market), currentGroup.title, () => {
          // that.renderList(currentGroupId);
          that.getGroupList();
        });
      }
    };
    handleAddStockSelect(value, marketCode, secUniCode, stockCode, stockName, suc_callback);
  };

  saveFormRef = formRef => {
    this.formRef = formRef;
  };

  hideModal = () => {
    const form = this.formRef.wrappedInstance.getForm();
    form.resetFields();
    this.setState({ holdstockVisible: false });
  };

  handleCreate = () => {
    const { currentGroupId, history, resetFields } = this.props;
    const form = this.formRef.wrappedInstance.getForm();
    form.validateFields((err, values) => {
      if (err) {
        return;
      }
      this.renderList(currentGroupId);
      this.getGroupList();
      this.setState({ holdstockVisible: false });
      this.props.getProfitData();

      resetFields();
    });
  };
  getOperationMenu = record => {
    const inMarket = [1004001, 1004017, 1004018, 1004003001];
    const isMarket = inMarket.findIndex(m => {
      return m === record.sec_type || m === record.sec_small_type;
    });
    return (
      <Menu className="operationMenu" onClick={this.handleMenuClick}>
        {isMarket > -1 ? (
          <Menu.Item key="1" record={record}>
            <i className="iconfont small" style={{ marginRight: "10px" }}>&#xe63a;</i>添加交易
          </Menu.Item>
        ) : (
            ""
          )}
        <Menu.Item key="3" record={record}>
          <Icon type="to-top" style={{ marginRight: "10px" }} />置顶
        </Menu.Item>
        <Menu.Item key="2" record={record}>
          <Icon type="delete" style={{ marginRight: "10px" }} />删除
        </Menu.Item>
      </Menu>
    );
  };
  setTop = record => {
    const { stockSetTop, currentGroup } = this.props;
    let { market } = currentGroup;
    market = market || 0;
    stockSetTop(
      {
        groupId: currentGroup.id,
        infoId: record.id
      },
      () => {
        this.renderList(currentGroup.id, market);
      }
    );
    console.log("置顶操作");
  };
  handleMenuClick = e => {
    const { fetchOwnStockState, resetFields } = this.props;
    const { key, item } = e;
    const { record } = item.props;
    if (key === "1") {
      //持仓菜单
      const { getStockById } = this.props;
      const form = this.formRef.wrappedInstance.getForm();
      resetFields({ ...initDealLog, ...{ stock: record.stockCode, secUniCode: record.sec_uni_code, mode: '0' } });
      getStockById(record.stockCode, record.stockName, record.sec_uni_code, (stock) => {
        setProps({ mode: stock.mode == -1 ? '0' : stock.mode });
      });
      this.setState({ currentStockCode: record.stockCode, holdstockVisible: true });
    } else if (key === "2") {
      this.deleteStock(record);
    } else if (key === "3") {
      if (!this.state.isSorter) {
        this.setTop(record);
      } else {
        message.info('在排序状态下不支持置顶！');
      }
    }
  };
  getClosePrice = (close_price, market, length) => {
    if ([1004017].indexOf(market) > -1) {
      //港股保留3位小数
      return toDecimal(close_price, 3);
    } else if ([1004018].indexOf(market) > -1) {
      //美股保留
      return toDecimal(close_price, 2);
    } else {
      return toDecimal(close_price, length ? length : 2);
    }
  }

  getColumns = () => {
    const { stockList: [...stockList], } = this.props;
    const columns = [
      {
        title: this.getStockTitle(),
        width: 110,
        fixed: "left",
        dataIndex: "stockName",
        render: (text, record) => {
          const { sec_stat } = record;
          if (!sec_stat) {
            console.error('数据中为发现 sec_stat');
            return '--';
          }
          let { sec_type, margin_status } = sec_stat;
          return (
            <Observer
              onChange={(visible) => {
                record.visible = visible;
              }}
            >
              <div>
                {[1004001].indexOf(sec_type) > -1
                  ? <a
                    href={`https://v1.analyst.ai/nav-data/company-by-stock?stock_code=${record.stockCode}`}
                    target='_block'
                    className="stockName"
                    style={{ textDecoration: 'none' }}
                    onClick={(evt) => { evt.stopPropagation(); }}
                  >
                    <div className="middle">
                      {getStockIcon(sec_type, margin_status)}
                      <Ellipsis tooltip length={6}>
                        {record.stockName}
                      </Ellipsis>
                    </div>
                    <span className="stockCode">{record.stockCode}</span>
                  </a>
                  : <div className="stockName">
                    <div className="middle">
                      {getStockIcon(sec_type, margin_status)}
                      <Ellipsis tooltip length={6}>
                        {record.stockName}
                      </Ellipsis>
                    </div>
                    <span className="stockCode">{record.stockCode}</span>
                  </div>
                }

              </div>
            </Observer>
          );
        }
      },
      {
        title: (
          <span>
            现价<Button
              className="moveSlide"
              type="primary"
              onClick={this.changeRightScroll}
            >
              <Icon type="left" />
            </Button>
          </span>
        ),
        className: "currentPrice",
        dataIndex: "price",
        key: "price",
        width: 220,
        fixed: "left",
        sorter: true,
        render: (text, record) => {
          let { differ_range, price, differ, sec_stat, pre_close_price } = record;
          // differ_range = differ_range*100;
          if (!sec_stat) {
            console.error('数据中未发现 sec_stat');
            return '--'
          }
          const { sec_type,
            sec_small_type,
            fund_type,
            status } = sec_stat
          // 退市
          if (status === -2) {
            return <div className="delist">
              <span className="price">--</span>
              <span className="name">退市</span>
            </div>
          }
          // 停牌
          if (status === -1) {
            return <div className="suspended">
              <span className="price">{pre_close_price}</span>
              <span className="name">停牌</span>
            </div>
          }
          // 基金
          if (sec_type === 1004003) {
            // 场内基金 1004003001
            if (sec_small_type === 1004003001) {
              return (
                <div className="stock" style={getColor(differ)}>
                  <div className="middle">
                    {/* 现价 */}
                    <span className="price" style={{ fontSize: "14px" }}>
                      {price ? (
                        <span style={{ fontWeight: "500" }}>
                          {price ? this.getClosePrice(price, sec_type, 4) : "--"}
                        </span>
                      ) : (
                          "--"
                        )}
                    </span>
                    {/* 涨跌额 */}
                    <span className="floatPrice">
                      {differ ? differ > 0
                        ? '+' + this.getClosePrice(differ, sec_type, 4)
                        : this.getClosePrice(differ, sec_type, 4)
                        : "--"
                      }
                    </span>
                    {/* 涨跌幅 */}
                    <span
                      className="rate"
                      style={{ ...getBackgroundColor(differ), fontSize: '11px' }}
                    >
                      {
                        differ > 0
                          ? "+" + toDecimal(differ_range, 4)
                          : toDecimal(differ_range, 4)
                      }%
                    </span>
                  </div>
                  <div className="historyPrice">
                    <span className="yesterday">
                      昨收 {this.getClosePrice(record.pre_close_price, sec_type, 4)}
                    </span>
                    <span>今开 {this.getClosePrice(record.open_price, sec_type, 4)}</span>
                  </div>
                </div>
              );
              // 场外基金 1004003002 1004003003 
            } else {
              // 1 货币基金
              if (fund_type === 1) {
                const { ten_thousand_returns, seven_day_yield, trade_date } = record;
                return (
                  <div>
                    <div className="oneYear middle">
                      {/* 万份收益 */}
                      <span
                        className="value"
                        style={getColor(ten_thousand_returns)}
                      >
                        {toDecimal(ten_thousand_returns, 4)}
                      </span>
                      <div>
                        {/* 七日年化 */}
                        <span
                          className="rate"
                          style={getBackgroundColor(seven_day_yield)}
                        >
                          {toDecimal(seven_day_yield, 4)}%
                    </span>
                        <Tooltip title={`${moment(new Date(trade_date)).format('YYYY/MM/DD')} 更新`}><Icon type="clock-circle-o" /></Tooltip>
                      </div>

                    </div>
                    <div className="sevenDays middle">
                      <div className="name">万份收益</div>
                      <div className="name">七日年化</div>
                    </div>
                  </div>
                )
                // 0 非货币基金
              } else {
                const {
                  unit_nav, //单位净值
                  differ_range, //涨跌幅
                  cumu_unit_nav,  //累计净值
                  trade_date,
                } = record;
                return (
                  <div>
                    <div className="iopv middle">
                      <div style={getColor(differ_range)}>
                        <span className="value">{toDecimal(unit_nav, 4)}</span>
                        <Tooltip title={
                          <div>
                            涨跌幅：
                          <span style={getColor(differ_range)}>
                              {differ_range > 0
                                ? `+${differ_range}%`
                                : `${differ_range}%`}
                            </span>
                          </div>
                        }>
                          <i className="iconfont small" style={{ marginRight: "10px" }}>&#xe60e;</i>
                        </Tooltip>
                      </div>
                      <div>
                        <span
                          className="value"
                          style={getColor(differ_range)}
                        >{toDecimal(cumu_unit_nav, 4)}</span>
                        <Tooltip title={`${moment(new Date(trade_date)).format('YYYY/MM/DD')} 更新`}><Icon type="clock-circle-o" /></Tooltip>
                      </div>

                    </div>
                    <div className="ljjz middle">
                      <div className="name">单位净值</div>
                      <span className="name">累计净值</span>
                    </div>
                  </div >
                )
              }
            }
            // 指数
          } else if (sec_type === 1004005) {
            if (differ_range) {
              return (
                <div className="stock" style={getColor(differ_range)}>
                  <div className="middle">
                    <span className="price" style={{ fontSize: "14px" }}>
                      {differ_range ? (
                        <span style={{ fontWeight: "500" }}>
                          {price ? this.getClosePrice(price, sec_type) : "--"}
                        </span>
                      ) : (
                          "--"
                        )}
                    </span>
                    <span className="floatPrice">
                      {differ ? differ > 0
                        ? '+' + this.getClosePrice(differ, sec_type)
                        : this.getClosePrice(differ, sec_type)
                        : "--"
                      }
                    </span>
                    <span
                      className="rate"
                      style={getBackgroundColor(differ_range)}
                    >
                      {
                        differ_range > 0
                          ? "+" + toDecimal(differ_range, 2)
                          : toDecimal(differ_range, 2)
                      }%
                    </span>
                  </div>
                  <div className="historyPrice">
                    <span className="yesterday">
                      昨收 {this.getClosePrice(record.pre_close_price, sec_type)}
                    </span>
                    <span>今开 {this.getClosePrice(record.open_price, sec_type)}</span>
                  </div>
                </div>
              );
            } else {
              return "--";
            }
            // 股票
          } else {
            if (differ_range) {
              return (
                <div className="stock" style={getColor(differ_range)}>
                  <div className="middle">
                    {/* 现价 */}
                    <span className="price" style={{ fontSize: "14px" }}>
                      {differ_range ? (
                        <span style={{ fontWeight: "500" }}>
                          {price ? this.getClosePrice(price, sec_type) : "--"}
                        </span>
                      ) : (
                          "--"
                        )}
                    </span>
                    {/* 涨跌额 */}
                    <span className="floatPrice">
                      {differ ? differ > 0
                        ? '+' + this.getClosePrice(differ, sec_type)
                        : this.getClosePrice(differ, sec_type)
                        : "--"
                      }
                    </span>
                    {/* 涨跌幅 */}
                    <span
                      className="rate"
                      style={getBackgroundColor(differ_range)}
                    >
                      {
                        differ_range > 0
                          ? "+" + toDecimal(differ_range, 2)
                          : toDecimal(differ_range, 2)
                      }%
                    </span>
                  </div>
                  <div className="historyPrice">
                    <span className="yesterday">
                      昨收 {this.getClosePrice(record.pre_close_price, sec_type)}
                    </span>
                    <span>今开 {this.getClosePrice(record.open_price, sec_type)}</span>
                  </div>
                </div>
              );
            } else {
              return "--";
            }
          }
        }
      },
      {
        title: this.getBargainUnit('成交量'),
        align: 'right',
        dataIndex: "volume",
        width: 120,
        sorter: true,
        render: text => {
          const obj = getUnroundedUnit(text / this.state.bargainType);
          return text ? <Tooltip title={
            parseInt(Math.abs(text)) >= 10000
              ? `${obj.allValue}${obj.unit}`
              : ''}>{obj.value}{obj.unit} </Tooltip> : "--";
        }
      },
      {
        title: "成交额",
        align: 'right',
        dataIndex: "amount",
        width: 120,
        sorter: true,
        render: (text, record) => {
          const obj = getUnroundedUnit(text);
          return text ? <Tooltip title={
            parseInt(Math.abs(text)) >= 10000
              ? `${obj.allValue}${obj.unit}`
              : ''}>{obj.value}{obj.unit} </Tooltip> : "--";
        }
      },
      {
        title: this.getHoldingUnit('持有量'),
        width: 120,
        align: 'right',
        dataIndex: "holding",
        sorter: true,
        render: (text, item) => {
          const obj = getUnroundedUnit(text / this.state.holdingType);
          return (
            <span>
              {text
                ? <Tooltip title={
                  parseInt(Math.abs(text)) >= 10000
                    ? `${obj.allValue}${obj.unit}`
                    : ''}>{obj.value}{obj.unit} </Tooltip>
                : "--"}{" "}
              {text && item.mode == 1 ? (
                <font className="hold-mode moni">模拟</font>
              ) : text && item.mode == 0 ? (
                <font className="hold-mode">实际</font>
              ) : (
                    ""
                  )}
            </span>
          );
        }
      },
      {
        title: "累计盈亏额",
        align: 'right',
        dataIndex: "profitLoss",
        key: "profitLoss",
        width: 120,
        sorter: true,
        render: (text, item) => {
          const obj = getUnroundedUnit(text, 2);
          return text
            ? text > 0
              ? <span style={getColor(text)}>{
                <Tooltip title={
                  parseInt(Math.abs(text)) >= 10000
                    ? <span style={getColor(text)}>+{obj.allValue}{obj.unit}</span>
                    : ''}>+{obj.value}{obj.unit}</Tooltip>
              }
              </span>
              : <span style={getColor(text)}>{
                <Tooltip title={
                  parseInt(Math.abs(text)) >= 10000
                    ? <span style={getColor(text)}>{obj.allValue}{obj.unit}</span>
                    : ''}>{obj.value}{obj.unit}</Tooltip>
              }
              </span>
            : "--";
        }
      },
      {
        title: "换手率",
        align: 'right',
        dataIndex: "turnover_rate",
        key: "turnover_rate",
        width: 120,
        sorter: true,
        render: (text, item) => {
          return text
            ? <span>{`${toDecimal(text, 2)}%`} </span>
            : "--";
        }
      },
      {
        title: "量比",
        align: 'right',
        dataIndex: "volume_ratio",
        key: "volume_ratio",
        width: 120,
        sorter: true,
        render: (text, item) => {
          return text
            ? <span>{`${toDecimal(text, 2)}`} </span>
            : "--";
        }
      },
      {
        title: "委比",
        align: 'right',
        dataIndex: "commission_diff",
        key: "commission_diff",
        width: 120,
        sorter: true,
        render: (text, item) => {
          return text
            ? text > 0
              ? <span style={getColor(text)}>{`+${toDecimal(text, 2)}%`} </span>
              : <span style={getColor(text)}>{`${toDecimal(text, 2)}%`} </span>
            : "--";
        }
      },
      {
        title: "振幅",
        align: 'right',
        dataIndex: "swing",
        key: "swing",
        width: 120,
        sorter: true,
        render: (text, item) => {
          return text
            ? <span>{`${toDecimal(text, 2)}%`} </span>
            : "--";
        }
      },
      {
        title: "现手",
        align: 'right',
        dataIndex: "round_lot",
        key: "round_lot",
        width: 120,
        sorter: true,
        render: (text, item) => {
          return text
            ? <span>{`${toDecimal(text, 2)}`} </span>
            : "--";
        }
      },
      {
        title: "市盈率(动)",
        align: 'right',
        dataIndex: "pe",
        key: "pe",
        width: 120,
        sorter: true,
        render: (text, item) => {
          return text
            ? <span>{`${toDecimal(text, 2)}`} </span>
            : "--";
        }
      },
      {
        title: "市净率",
        align: 'right',
        dataIndex: "pb",
        key: "pb",
        width: 120,
        sorter: true,
        render: (text, item) => {
          return text
            ? <span>{`${toDecimal(text, 2)}`} </span>
            : "--";
        }
      },
      {
        title: "总市值",
        align: 'right',
        dataIndex: "total_market_value",
        key: "total_market_value",
        width: 120,
        sorter: true,
        render: (text, item) => {
          const obj = getUnroundedUnit(text);
          return text
            ? <span>
              <Tooltip title={
                parseInt(Math.abs(text)) >= 10000
                  ? `${obj.allValue}${obj.unit}`
                  : ''}>{obj.value}{obj.unit}</Tooltip>
            </span>
            : "--";
        }
      },
      {
        title: "流通市值",
        align: 'right',
        dataIndex: "freefloat_market_value",
        key: "freefloat_market_value",
        width: 120,
        sorter: true,
        render: (text, item) => {
          const obj = getUnroundedUnit(text);
          return text
            ? <span>
              <Tooltip title={
                parseInt(Math.abs(text)) >= 10000
                  ? `${obj.allValue}${obj.unit}`
                  : ''}>{obj.value}{obj.unit}</Tooltip>
            </span>
            : "--";
        }
      },
      {
        title: (
          <Button
            style={{ left: 0 }}
            className="moveSlide"
            type="primary"
            onClick={this.changeLeftScroll}
          >
            <Icon type="right" />
          </Button>
        ),
        dataIndex: "operation",
        width: 25,
        fixed: "right",
        render: (text, record) => {
          return null;
          return (
            <Dropdown
              overlay={this.getOperationMenu(record)}
              placement="bottomRight"
            >
              <Button className="operationBtn" style={{ marginLeft: 8 }}>
                <Icon type="ellipsis" />
              </Button>
            </Dropdown>
          );
        }
      }
    ];
    return {
      columns,
      width: 1880
    };
  };
  columnIndexs = [120, 120, 120, 120, 120, 120, 120, 60];
  cIndex = 0;
  changeLeftScroll = e => {
    this.table.head.scrollLeft += this.columnIndexs[this.cIndex];
    if (this.table.body) {
      this.table.body.scrollLeft += this.columnIndexs[this.cIndex];
    }
    if (this.cIndex < this.columnIndexs.length - 1) {
      this.cIndex += 1;
    }
  };
  changeRightScroll = e => {
    this.table.head.scrollLeft -= this.columnIndexs[this.cIndex];
    if (this.table.body) {
      this.table.body.scrollLeft -= this.columnIndexs[this.cIndex];
    }
    if (this.cIndex > 0) {
      this.cIndex -= 1;
    }
  };
  successCallback = () => {
    const { setCurrentGroupId, currentGroup } = this.props;
    if (currentGroup.group_name_id == 'system') {
      setCurrentGroupId(`${currentGroup.id},${currentGroup.market ? currentGroup.market : 0}`);
    }
    this.setState({
      stockType: '0',
    });
  }
  getOperationDrowpDown = stockGroup => {
    const { currentGroup } = this.props;
    let arr1 = [...stockGroup].filter(g => {
      return !g.group_name_id;
    });
    let menus = [];
    arr1.forEach(group => {
      let { title, id, market, count } = group;
      menus.push({
        name: title,
        key: this.getId(id, market),
        count: count
      });
    });
    if (!currentGroup) {
      return menus;
    }
    menus = [];
    const arr = [...stockGroup].filter(g => {
      return !g.group_name_id && g.id !== currentGroup.id;
    });
    arr.forEach(group => {
      let { title, id, market, count } = group;
      menus.push({
        name: title,
        key: this.getId(id, market),
        count: count
      });
    });
    return menus;
  };
  changeSwitch = checked => {
    const { currentGroup } = this.props;
    this.setState({
      showNoHold: checked ? 0 : 1,
    }, () => {
      this.renderList(currentGroup.id, currentGroup.market);
    });
  };
  setHighlightRow = (record, index) => {
    const { updateTimer } = this.props;
    let sesstionStorage = window.sessionStorage;
    let last_stockList = sesstionStorage.getItem("_last_list")
      ? JSON.parse(sesstionStorage.getItem("_last_list"))
      : [];
    if (updateTimer && last_stockList.length) {
      const targetStock = last_stockList.filter(item => item.id === record.id)[0];
      if (targetStock) {
        if (targetStock["close_price"] - record["close_price"] < 0) {
          return "highlight-up";
        } else if (
          targetStock["close_price"] - record["close_price"] >
          0) {
          return "highlight-down";
        } else {
          return "";
        }
      } else {
        return "";
      }
    }
  };

  // 获取持有量单位
  getBargainUnit = (name) => {
    const { unitTypes, bargainType } = this.state;
    return (
      <div
        style={{
          display: 'inline-block',
          cursor: 'pointer',
        }}
        onClick={() => this.onChnageBargainUnit(bargainType)}
      >
        {`${name}(${unitTypes[bargainType]})`}
      </div>
    );
  }
  // 获取持有量单位
  getHoldingUnit = (name, onChangeUnit) => {
    const { unitTypes, holdingType } = this.state;
    return (
      <div
        style={{
          display: 'inline-block',
          cursor: 'pointer',
        }}
        onClick={() => this.onChangeHoldingUnit(holdingType)}
      >
        {`${name}(${unitTypes[holdingType]})`}
      </div>
    );
  }

  onChnageBargainUnit = (bargainType) => {
    if (bargainType === 1) {
      this.setState({
        bargainType: 100,
      })
    } else if (bargainType === 100) {
      this.setState({
        bargainType: 1,
      })
    }
  }

  onChangeHoldingUnit = (holdingType) => {
    if (holdingType === 1) {
      this.setState({
        holdingType: 100,
      })
    } else if (holdingType === 100) {
      this.setState({
        holdingType: 1,
      })
    }
  }

  render() {
    const { selectedRowKeys } = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: this.onSelectChange,
      hideDefaultSelections: true,
      onSelectAll: (selected, selectedRows, changeRows) => {
        if (!selected) {
          this.setState({
            isAll: ""
          });
        }
      },
      selections: [
        {
          key: "all-page-data",
          text: (
            <div className={this.state.isAll === 0 ? "active" : ""}>
              选择当前页
            </div>
          ),
          onSelect: changableRowKeys => {
            this.setState({
              isAll: 0,
              selectedRowKeys: changableRowKeys
            });
          }
        },
        {
          key: "all-data",
          text: (
            <div className={this.state.isAll === 1 ? "active" : ""}>
              选择全部
            </div>
          ),
          onSelect: changableRowKeys => {
            this.setState({
              isAll: 1,
              selectedRowKeys: changableRowKeys
            });
          }
        }
      ]
    };
    const bool = true;
    const {
      stockGroup: [...stockGroup],
      marketGroup: [...marketGroup],
      copyList,
      currentGroupId,
      currentGroup,
      stockList: [...stockList],
      selectedMyStockKeys,
      selectedMenu,
      hasMore,
      showLoading,
      loadingState,
      stepEnabled,
      currentPage,
      profitState,
      profitActual,
      profitSimulated,
      profitIsempty,
      searchStock,
      stockDataSource: [...stockDataSource]
    } = this.props;

    const operationDrowpDown = this.getOperationDrowpDown(stockGroup);
    const menus = this.getStockGroupMenu(stockGroup);
    const marketMenus = this.getMarketGroupMenu(marketGroup);
    const { group_name_id } = currentGroup;
    const { title, count } = currentGroup;
    const setting_menu = (
      <Menu className="ms-setting-dropmenu">
        <Menu.Item onClick={this.editGroupName}>
          <Icon type="edit" style={{ marginRight: "5px" }} />重命名
        </Menu.Item>
        {group_name_id && group_name_id.indexOf("system") > -1 ? (
          ""
        ) : (
            <Menu.Item onClick={this.deleteGroupName}>
              <Icon type="delete" style={{ marginRight: "5px" }} />删除分组
          </Menu.Item>
          )}
      </Menu>
    );
    const { columns, width } = this.getColumns();
    return (
      <div className="ms-container">
        <div className="myStock">
          <CreateGroupName />
          {false && profitState !== "pending" &&
            profitState !== "error" &&
            !profitIsempty ? (
              <Row gutter={10} style={{ marginBottom: "10px" }}>
                <Col span={12}>
                  <TakeAPosition
                    name="实际持仓"
                    data={profitActual}
                    state={profitState}
                  />
                </Col>
                <Col span={12}>
                  <TakeAPosition
                    name="模拟持仓"
                    data={profitSimulated}
                    state={profitState}
                    style={{ background: "#74B6F8" }}
                  />
                </Col>
              </Row>
            ) : null}
          <Row gutter={10}>
            <Col span={3}>
              <Affix style={{ background: "#F0F2F5" }} offsetTop={104}>
                <div className="guide-wrap">
                  <Button
                    className="addStockGroup step-guid"
                    type="dashed"
                    icon="plus"
                    onClick={this.showGroupNameModal}
                    id="step3"
                  >
                    添加分组
                  </Button>
                </div>
                <div
                  style={{ maxHeight: "calc(100vh - 240px)" }}
                  ref={node => {
                    this.menuNode = node;
                  }}
                >
                  <Menu
                    className="menu"
                    onSelect={this.changeStockGroup}
                    selectedKeys={[String(selectedMenu)]}
                  >
                    {menus.map(menu => {
                      return (
                        <Menu.Item
                          className="item"
                          key={menu.key}
                          name={menu.name}
                        >
                          <Ellipsis tooltip length={9}>
                            {menu.name}
                          </Ellipsis>
                          <span className="badge">（{menu.count}）</span>
                        </Menu.Item>
                      );
                    })}
                  </Menu>
                  <div className="marketMenus">
                    <Menu
                      className="menu"
                      onSelect={this.selectMyStock}
                      selectedKeys={[String(selectedMenu)]}
                    >
                      {marketMenus.map(menu => {
                        return (
                          <Menu.Item
                            className="item"
                            key={menu.key}
                            name={menu.name}
                          >
                            <Ellipsis tooltip length={9}>
                              {menu.name}
                            </Ellipsis>
                            <span className="badge">（{menu.count}）</span>
                          </Menu.Item>
                        );
                      })}
                    </Menu>
                  </div>
                </div>
              </Affix>
            </Col>
            <Col span={21}>
              <Card
                title={
                  <div>
                    <span style={{ fontWeight: "bold" }}>
                      {title} ({count})
                    </span>
                  </div>
                }
                bordered={false}
                className="card"
                extra={
                  <div className="title_right middle">
                    {false && <span className="middle">
                      仅显示我的持仓<Switch
                        checked={this.state.showNoHold === 1 ? false : true}
                        onChange={this.changeSwitch}
                        style={{ marginLeft: "7px" }}
                        size="small"
                      />
                    </span>}
                    {this.props.currentGroup.group_name_id != 'system' ? (
                      <Dropdown placement="bottomRight" overlay={setting_menu}>
                        <Icon type="setting" className="editGroup" />
                      </Dropdown>
                    ) : (
                        ""
                      )}
                  </div>
                }
              >

                <Spin
                  spinning={
                    currentPage == 0 && loadingState === "pending"
                  }
                  indicator={
                    <Loading type='list' height={'100%'} />
                  }
                >
                  <div
                    className={`stockTable ${
                      profitState !== "pending" &&
                        profitState !== "error" &&
                        !profitIsempty
                        ? ""
                        : "profitIsempty"
                      }`}
                    id="stock-table-container"
                  >
                    <InfiniteScroll
                      initialLoad={false}
                      loadMore={this.handleInfiniteOnLoad}
                      hasMore={loadingState === "done" && hasMore}
                    >
                      <Affix
                        offsetTop={104}
                        onChange={(affixed) => {
                          let stockTableContainer = document.getElementById('stock-table-container');
                          if (stockTableContainer) {
                            let [, tbody] = stockTableContainer.getElementsByClassName('ant-table-wrapper');
                            if (tbody) {
                              if (affixed) {
                                // tbody.style.paddingTop = '97px';
                              } else {
                                tbody.style.paddingTop = '0px';
                              }
                            }
                          }
                        }}
                        className={
                          count >= [...stockList].length &&
                            [...stockList].length > 0
                            ? ""
                            : "hidden"
                        } style={{ height: "94px" }}
                      >
                        <Table
                          className="thead"
                          title={() => {
                            return <div className="toolBar">
                              <Row>
                                <Col span={8}>
                                  <div className="guide-wrap small-single-search" id="step1-none">
                                    <SearchAddSingleInput
                                      dataSource={stockDataSource}
                                      onSearch={searchStock}
                                      placeholder="请输入股票代码或名称添加"
                                      handleAddStockSelect={this.handleAddStock}
                                      page='myStock'
                                    />
                                  </div>
                                </Col>

                                <Col span={10} offset={6} style={{ textAlign: "right" }}>
                                  <Popover
                                    placement="bottomLeft"
                                    content={this.getCopyContent(operationDrowpDown)}
                                    trigger="click"
                                    visible={this.state.copyVisible}
                                    onVisibleChange={this.handleCopyVisibleChange}
                                  >
                                    <Button
                                      value={0}
                                      onClick={this.clickCopy}
                                      disabled={this.state.btnDisabled()}
                                      icon="copy"
                                    >
                                      复制
                                  </Button>
                                  </Popover>

                                  <a href={this.getExportUrl()} target="_blank">
                                    <Button
                                      disabled={this.state.btnDisabled()}
                                      style={{ marginRight: "0" }}
                                      icon="export"
                                    >
                                      导出
                                    </Button>
                                  </a>
                                  <Button
                                    disabled={this.state.btnDisabled()}
                                    icon="delete"
                                    onClick={() => this.deleteStock()}
                                  >
                                    删除
                                </Button>
                                </Col>
                              </Row>
                            </div>
                          }}
                          rowSelection={rowSelection}
                          columns={columns}
                          dataSource={[...stockList]}
                          pagination={false}
                          onChange={this.handleTableChange}
                          rowKey={(record, i) => record.id}
                          scroll={{ x: width }}
                        />
                      </Affix>
                      <Table
                        className={
                          count >= [...stockList].length &&
                            [...stockList].length > 0
                            ? "tbody"
                            : "hidden"
                        }
                        rowSelection={rowSelection}
                        columns={columns}
                        dataSource={[...stockList]}
                        pagination={false}
                        rowKey={(record, i) => record.id}
                        scroll={{ x: width }}
                        rowClassName={(record, index) => {
                          return this.setHighlightRow(record, index);
                        }}
                      />
                    </InfiniteScroll>
                    {loadingState == "pending" && !showLoading && hasMore ? (
                      <div className="loading-more" key="loading0">
                        <Loading height={50} />
                      </div>
                    ) : (
                        ""
                      )}
                    <div
                      className={
                        [...stockList].length > 0 &&
                          [...stockList].length === count
                          ? "loading-end"
                          : "hidden"
                      }
                    >
                      没有更多了
                    </div>

                    {
                      loadingState === "done" && !count
                        ? <div className="nodata">
                          <Table
                            className="thead"
                            title={() => {
                              return <div className="toolBar">
                                <Row>
                                  <Col span={8}>
                                    <div className="guide-wrap small-single-search" id="step1">
                                      <SearchAddSingleInput
                                        dataSource={stockDataSource}
                                        onSearch={searchStock}
                                        placeholder="代码/简拼/名称"
                                        handleAddStockSelect={this.handleAddStock}
                                        page='myStock'
                                      />
                                    </div>
                                  </Col>
                                </Row>
                              </div>
                            }}
                            rowSelection={rowSelection}
                            columns={columns}
                            dataSource={[...stockList]}
                            pagination={false}
                            onChange={this.handleTableChange}
                            rowKey={(record, i) => record.id}
                            scroll={{ x: width }}
                          />
                          <div className="newStock Absolute-Center" style={{ top: "180px" }}>
                            <p>
                              <img src={addMyStock} />
                            </p>
                            <div className="addMsg">{this.state.showNoHold === 1 ? '当前分组下暂无自选股' : '当前分组下暂无持仓自选股'}</div>
                            {
                              (true || this.state.showNoHold === 1)
                                ? <Button
                                  className="addMyStockBtn"
                                  icon="plus"
                                  onClick={this.props.showBatchImport}
                                >
                                  添加自选股
                            </Button>
                                : null
                            }

                          </div>
                        </div>
                        : ''
                    }

                  </div>
                </Spin>
              </Card>
            </Col>
          </Row>
          <BatchImport successCallback={this.successCallback} />
          <CreateDealLog
            wrappedComponentRef={this.saveFormRef}
            visible={this.state.holdstockVisible}
            stockCode={this.state.currentStockCode}
            onCancel={this.hideModal}
            onCreate={this.handleCreate}
            maskClosable={false}
          />
        </div>
      </div>
    );
  }
}
export default withRouter(MyStock);
