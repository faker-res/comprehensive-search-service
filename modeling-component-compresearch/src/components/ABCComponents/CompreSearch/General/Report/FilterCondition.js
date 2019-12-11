import React, { Component } from 'react';
import { Row } from 'antd';
import './FilterCondition.less';
import '../../common/common.less';
import del from '../../assets/image/icon_delete_big.png';
import empty from '../../assets/image/icon_trash_big.png';


export default class FilterCondition extends Component {
  getCondition = (dataMap) => {
    const condtionList = [];
    for (const [key1, value1] of dataMap) {
      for (const [key2, value2] of value1) {
        const arr = [key1, key2, value2];
        condtionList.push(arr);
      }
    }
    // 二重数组 [[类型,key,value],[...]]
    return condtionList;
  }
  emptyFilter = () => {
    const { delCallback } = this.props;
    // 无参删除所有的过滤条件
    delCallback();
  }
  deleteFilter = (e) => {
    const { delCallback } = this.props;
    const { key, type } = e.target.dataset;
    delCallback(key, type);
  }
  render() {
    const { isShowFilter, dataMap } = this.props;
    const condtionList = this.getCondition(dataMap);
    return (
      <Row>
        <div className={`filter_condition_container middle ${(isShowFilter && dataMap.size > 0) ? '' : 'display_none'}`}>
          {
            condtionList.map((arr) => {
              return (
                <div key={`${arr[1]}-${arr[2]}`} className={`condition middle`}>
                  <span>{`${arr[0]}：${arr[2]}`}</span>
                  <img src={del} alt={del} style={{ width: '10px', marginRight: '10px' }} data-type={arr[0]} data-key={arr[1]} onClick={this.deleteFilter} />
                </div>
              );
            })
          }
          <div className={`condition_empty middle`} onClick={this.emptyFilter}>
            <img src={empty} alt={empty} style={{ width: '12px', marginRight: '5px' }} />
            <span>清空筛选</span>
          </div>
        </div>
      </Row>
    );
  }
}
