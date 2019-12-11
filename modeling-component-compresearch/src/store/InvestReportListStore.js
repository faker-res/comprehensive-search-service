import { observable, action, runInAction } from 'mobx';
import service from './../service/index';
import Cookies from 'js-cookie';
import { stat } from 'fs';

export default class InvestReportListStore {
  constructor(rootStore) {
    this.rootStore = rootStore;
  }

  @observable investReportList = [];

  @observable currentRecord = {};

  @observable recordList = [];

  @observable modalTitle = '';

  @observable dataList = [];

  @observable pocFlowStatus = '';

  @observable modalDate = '';

  @observable loadingMore = false;

  @observable disables = false;

  @observable searchText = '';

  @observable role = '';

  @observable modalFiles = [];

  @action
  getData = (roleName) => {
    this.loadingMore = true;
    return service.getReportGroup(roleName).then((res) => {
      runInAction(() => {
        this.loadingMore = false;
        this.investReportList = [];
        res.data.forEach((item, index) => {
          item.index = index + 1;
          item.key = index;
          const status = Number(item.pocFlowStatus);
          if (status === 1) {
            item.cnStatus = '待审核';
          }
          // else if (status === 2) {
          //   item.cnStatus = '已审核';
          // } else if (status === 21) {
          //   item.cnStatus = '已驳回';
          // }

          //处理返回的stock内容
          //因为获取的stock数据是："{"code":"000066.OF","name":"诺安鸿鑫保本混合"}"
          //要做转换处理成：'{"code":"000066.OF","name":"诺安鸿鑫保本混合"}'
          // const jsonStr = "'"+item.stock.substring(1,item.stock-1)+""  //去掉第一个和最后一个双引号
          // const stock = JSON.parse(jsonStr)
          // console.log("获取的stock数据：", stock)
          // item.stockCode = stock.code
          this.investReportList.push(item);
        });
      });
    });
  }

  @action
  clearData =() => {
    this.investReportList = [];
  }

  @action.bound
  setCurrentRecord = (record) => {
    runInAction(() => {
      this.recordList = [];
      this.recordList = this.recordList.concat(record);
      console.log("当前数据为：" + this.recordList);
    });
  }

  @action.bound
  setModalTitle = (record) => {
    this.modalTitle = '';
    this.modalDate = '';
    this.modalSummary = '';
    this.modalFiles = [];
    this.modalTitle = record.title;
    this.modalDate = record.day;
    this.modalSummary = record.summary;
    this.modalFiles = JSON.parse(record.reportFiles);
    console.log(this.modalTitle);
  }

  @action.bound
  setPocFlowStatus = (data) => {
    this.pocFlowStatus = '';
    this.dataList = {};
    const posstatus = Number(data.pocFlowStatus);
    console.log(`数据为：${data.pocFlowStatus}`);
    if (posstatus === 1) {
      this.pocFlowStatus = 2;
      this.dataList = {
        id: data.id,
        pocFlowStatus: this.pocFlowStatus,
        userid: Cookies.get('userId'),
        token: Cookies.get('token')
      };

      console.log(`数据为：${this.dataList}`);
      service.setPocFlowStatus(this.dataList);
      // .then(res => {
    // });
    }
  }

  @action.bound
  refusePocFlowStatus = (data) => {
    this.pocFlowStatus = '';
    const posstatus = Number(data.pocFlowStatus);
    if (posstatus === 1) {
      this.pocFlowStatus = 21;
      this.dataList = {
        id: data.id,
        pocFlowStatus: this.pocFlowStatus,
      };
      console.log(`数据为：${this.dataList}`);
      service.setPocFlowStatus(this.dataList);
    }
  }

  @action.bound
  getApproval = (id) => {
    service.getApprovalMsg(id).then((result) => {
      console.log(result);
    });
  }

  @action
  setSearchText = (text) => {
    this.searchText = text;
  }

  @action
  addInvestApproval = (data) => {
      service.addInvestApproval(data).then(() => {});
  }
}

