import React, { Component } from 'react';
import { Tabs } from 'antd';
import Loading from './Loading';

import './ComTabs.less';

const { TabPane } = Tabs;
export default class ComTabs extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  getShowTabPane = (tabContent, i) => {
    const { tabNameList, isLoading, disabledList, change } = this.props;
    if (disabledList && disabledList.length > 0 && disabledList[i] == true) {
      return (
        <TabPane tab={tabNameList[i]} key={`${tabNameList[i]}`} disabled>
          {isLoading == false ? tabContent : (
            <Loading
              isShow={isLoading}
              content={tabContent}
            />
          )}
        </TabPane>
      )
    } else {
      return (
        <TabPane tab={tabNameList[i]} key={`${tabNameList[i]}`} >
          {isLoading == false ? tabContent : (
            <Loading
              isShow={isLoading}
              content={tabContent}
            />
          )}
        </TabPane>
      )
    }
  }
  render() {
    // isLoading!==false表示有异步加载的情况，要显示loading状态，如果===false则直接显示
    const { title, tabNameList, tabContentList, selectedIndex, isLoading, disabledList, change } = this.props;
    // console.log(tabNameList)
    return (
      <div className={'tabs_container'}>
        <div className={'tabs_title'}>{title}</div>
        <Tabs defaultActiveKey={tabNameList[0] || '0'} onChange={change}>
          {
            tabContentList.map((tabContent, i) => {
              return this.getShowTabPane(tabContent, i);
            })
          }
        </Tabs>

      </div >
    );
  }
}
