/**
 * store中心
 * @description 总 store 分配中心
 * @author dhhuang1
 * @date 2018/5/8 上午9:17:14
 */

import {
  observable,
  action,
  configure
} from 'mobx';
import Cookies from 'js-cookie';
import DefaultStore from './default';

// import SimulationGroupStore from './simulationGroupStore';
// import TasksStore from './tasksStore';
// import ReportGroupStore from './reportGroupStore';
// import DetailGroupStore from './detailGroupStore';
// import ActivityStore from './activityStore';
// import ResearchReportListStore from './ResearchReportListStore';
// import InvestReportListStore from './InvestReportListStore';
// import ListStore from './listStore';
import SearchStore from './searchStore';
// import ReportStore from './report';
// import ManagerReportStore from './managerReportStore';
// import StockStore from './stock'
// import RiskListStore from './RiskListStore';
// import AddGroupName from './addGroupName';
// import MyStock from './myStock';
// import SearchAddSingleInput from './addSingleStockInput';
// import StockDealLog from './stockDealLog'
// import OwnStock from './OwnStock';
// import BatchImport from './batchImport';
// import TaskListStore from './TaskListStore';
import honor from './honor';
import CuttingStore from './CuttingStore';
// import SimulationListStore from './SimulationListStore';
// import SimulationDetailStore from './SimulationDetailStore';
// import StockPoolOverviewStore from './StockPoolOverviewStore';
// import StockPollStore from './stockPollStore';
// import ApprovalListStore from './approvalListStore';
import AuthStore from './auth';

// 只允许 内部改变 state
configure({
  enforceActions: true
});

class Store {
  constructor() {
    this.defaultStore = new DefaultStore(this);
    // this.tasksStore = new TasksStore(this);
    // this.simulationGroupStore = new SimulationGroupStore(this);
    // this.reportGroupStore = new ReportGroupStore(this);
    // this.detailGroupStore = new DetailGroupStore(this);
    // this.activityStore = new ActivityStore(this);
    // this.ResearchReportListStore = new ResearchReportListStore(this);
    // this.InvestReportListStore = new InvestReportListStore(this);
    // this.listStore = new ListStore(this);
    this.searchStore = new SearchStore(this);
    // this.reportStore = new ReportStore(this);
    // this.stockStore = new StockStore(this);
    // this.RiskListStore = new RiskListStore(this);
    // this.ManagerReportStore = new ManagerReportStore(this);
    // this.addGroupName = AddGroupName;
    // this.myStock = MyStock;
    // this.searchAddSingleInput = SearchAddSingleInput;
    // this.stockDealLog = StockDealLog;
    // this.ownStock = OwnStock;
    // this.batchImport = BatchImport;
    // this.TaskListStore = new TaskListStore(this);
    this.honor = honor;
    this.cuttingStore = new CuttingStore(this);
    // this.simulationListStore = new SimulationListStore(this);
    // this.SimulationDetailStore = new SimulationDetailStore(this);
    // this.stockPoolOverviewStore = new StockPoolOverviewStore(this);
    // this.StockPollStore = new StockPollStore(this);
    // this.ApprovalListStore = new ApprovalListStore(this);
    this.authStore = new AuthStore(this);
  }
  @observable isLogin = false;

  @observable role = '';

  @action.bound
  setLoginStatus(status) {
    // 设置用户的登陆状态
    Cookies.set('isLogin',status);
  }

  @action.bound
  setRole(role) {
    Cookies.set('role',role);
  }

  @action.bound
  setName(name) {
    Cookies.set('name',name);
  }

  @action.bound
  getRole() {
    return this.role;
  }
}


export default new Store();