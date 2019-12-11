/**
 * @description 解析插队
 * @author jywang
 * date: 2018-06-13
 */

import { observable, flow,action } from "mobx";
import _ from 'lodash';
import ask from "../lib/ask";

class CuttingStore {
   // 判断解析插队结果
   @observable hasCuttingInfo = {};
   @observable hasArtificialInfo = {};
   @observable getCuttingResultInfo = {};
   @observable getVersionInfo = {};

   // 判断解析插队结果的状态：pending / done / error
  @observable state = "pending";

  constructor(rootStore) {
    this.rootStore = rootStore;
  }



  // 判断24小时解析插队
  fetchHasCutting = flow(function * (info) {
     //set priority is max
    info = Object.assign({priority:10},info)
    if (!_.isEmpty(this.hasCuttingInfo)) {
      return this.hasCuttingInfo;
    }
    this.hasCuttingInfo = {};
    this.state = "pending";
    try {
     
      const { code, data, message } = yield ask("fetchHasCutting",{params:info});
      // 响应异常
      if (code !== 200) {
        throw new Error(`Response Exception: ${message};code: ${code}`);
      }

      // 获取成功
      this.hasCuttingInfo = data;
      this.state = "done";
    } catch (error) {
      // 获取失败
      this.state = "error";
    }
  });

  // 判断人工标注
  fetchHasArtificial = flow(function * (info) {
    if (!_.isEmpty(this.hasArtificialInfo)) {
      return this.hasArtificialInfo;
    }
    this.hasArtificialInfo = {};
    this.state = "pending";
    try {
      const { code, data, message } = yield ask("fetchHasArtificial",{params:info});
      // 响应异常
      if (code !== 200) {
        throw new Error(`Response Exception: ${message};code: ${code}`);
      }

      // 获取成功
      this.hasArtificialInfo = data;
      this.state = "done";
    } catch (error) {
      // 获取失败
      this.state = "error";
    }
  });

  // 获取插队解析结果
  fetchGetCuttingResultInfo = flow(function * (info) {
    if (!_.isEmpty(this.getCuttingResultInfo)) {
      return this.getCuttingResultInfo;
    }
    this.getCuttingResultInfo = {};
    this.state = "pending";
    try {
      const { code, data, message } = yield ask("fetchGetCuttingResult",{params:info});
      // 响应异常
      if (code !== 200) {
        throw new Error(`Response Exception: ${message};code: ${code}`);
      }

      // 获取成功
      this.getCuttingResultInfo = data;
      this.state = "done";
    } catch (error) {
      // 获取失败
      this.state = "error";
    }
  });
 
  // 获取搜图最新版本号
  fetchGetVersionInfo = flow(function * () {
    if (!_.isEmpty(this.getVersionInfo)) {
      return this.getVersionInfo;
    }
    this.getVersionInfo = {};
    this.state = "pending";
    try {
      const { code, data, message } = yield ask("fetchGetVersion");
      // 响应异常
      if (code !== 200) {
        throw new Error(`Response Exception: ${message};code: ${code}`);
      }

      // 获取成功
      this.getVersionInfo = data[0][0].bitmap_ver;
      this.state = "done";
    } catch (error) {
      // 获取失败
      this.state = "error";
    }
  });

}

export default CuttingStore;
