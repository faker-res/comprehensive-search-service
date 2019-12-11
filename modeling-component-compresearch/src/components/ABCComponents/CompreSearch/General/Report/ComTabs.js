import React, { Component } from 'react';
import { Tabs } from 'antd';
import Loading from './Loading';
import './ComTabs.less';
import '../../common/common.less';

const { TabPane } = Tabs;
export default class ComTabs extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  getShowTabPane = (tabContent, i) => {
    const { tabNameList, isLoading, disabledList } = this.props;
    if (disabledList && disabledList.length > 0 && disabledList[i] == true) {
      return (
        <TabPane tab={tabNameList[i]} key={`${i}`} disabled>
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
        <TabPane tab={tabNameList[i]} key={`${i}`}>
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
    const { title, tabNameList, tabContentList, selectedIndex, isLoading, disabledList } = this.props;
    return (
      <div className={'tabs_container'}>
        <div className={'tabs_title'}>{title}</div>
        <Tabs defaultActiveKey={selectedIndex || '0'}>
          {
            tabContentList.map((tabContent, i) => {
              return this.getShowTabPane(tabContent, i);
            })
          }
        </Tabs>

      </div>
    );
  }
}
