import React, { Component } from 'react';
import { Collapse, List, Spin } from 'antd';
import { Link } from 'dva/router';
import ReportItem from './ReportItem';
import StockCard from './StockCard';
import styles from './GroupList.less';
// import common from '../../common/common.less';

const { Panel } = Collapse;
export default class GroupList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showKey: '',
      openedPanelSet: new Set([undefined]),
    };
  }
  getRatingColor = (rating) => {
    let color = '#ff0000';
    if (rating === '增持') color = '#de9700';
    if (rating === '中性') color = '#abe201';
    return color;
  }
  getHeader = (groupItem) => {
    const item = groupItem.data[0] || {};
    return (
      <div className={`${styles.panel_header} ${common.middle}`}>
        <div title={groupItem.name}>{groupItem.name}</div>
        <div className={`${styles.today_report} ${common.center_middle}`} title="今日研报">
          <font className={`${common.center_middle} ${groupItem.new_count_today > 0 ? '' : common.visibility_hidden}`}>{groupItem.new_count_today}</font>
        </div>
        <div className={`${styles.title_top} ${common.space_between}`}>
          <div className={`${styles.title_container} ${common.middle} ${this.state.showKey === groupItem.code ? common.visibility_hidden : ''}`}>
            <StockCard
              stockCode={item.stockcode || ''}
              stockName={item.stockname || ''}
              clickStockCallback={this.clickStockName || null}
            />
            <Link
              className={styles.title_name}
              onClick={this.routerDetail}
              dangerouslySetInnerHTML={{ __html: item.title }}
              // target="_blank"
              to={`/report-detail/article/${item.id}`}
            />
          </div>
          <div className={`${styles.group_report_num} ${this.state.showKey === groupItem.code ? '' : common.display_none}`}>共{groupItem.count}条</div>
        </div>
      </div>
    );
  }
  clickStockName = (stockName, stockCode) => {
    const { clickStockNameCallBack } = this.props;
    clickStockNameCallBack(stockName, stockCode);
  }
  changeShowKey = (key) => {
    const { loadItemListCallback, searchData } = this.props;
    const itemSearchData = {
      category: searchData.category,
      first_category: searchData.first_category,
      group_by: searchData.group_by,
      keyword_filter: searchData.keyword_filter,
      selected_limit: 9,
      selected_offset: 1,
    };
    if (this.state.openedPanelSet.has(key)) {
      this.setState({ showKey: key });
    } else {
      this.state.showKey = key;
      this.state.openedPanelSet.add(key);
      if (searchData.group_by === 'industry') {
        itemSearchData.selected_industry_name = key;
      } else if (searchData.group_by === 'company') {
        itemSearchData.selected_stock_code = key;
      }
      loadItemListCallback(itemSearchData);
    }
  }
  render() {
    const { groupList, searchData, clickCallBack, isShow, clickStockNameCallBack, isLoading, hasMore } = this.props;
    return (
      <Collapse accordion onChange={this.changeShowKey}>
        {
          groupList.map((groupItem) => {
            return (
              <Panel header={this.getHeader(groupItem)} key={groupItem.code}>
                <List
                  style={{ backgroundColor: 'white', padding: '0 24px' }}
                  dataSource={groupItem.data}
                  renderItem={item => (
                    <List.Item key={item.id} style={{ paddingLeft: '0', paddingRight: '0', width: '100%' }}>
                      <ReportItem
                        item={item}
                        searchData={searchData}
                        clickCallBack={clickCallBack}
                        isShow={isShow}
                        clickStockNameCallBack={clickStockNameCallBack}
                      />
                    </List.Item>
                  )}
                >
                  <div className={styles.load_more}>加载更多</div>
                </List>
              </Panel>
            );
          })
        }
        <div className={common.center_middle} style={{ width: '100%', padding: '20px 0' }}>
          {isLoading && hasMore && <Spin className="demo-loading" />}
          {isLoading === false && hasMore === false && groupList.length > 0 && <span style={{ color: '#999', fontSize: '12px' }}>没有更多了</span>}
        </div>
      </Collapse>
    );
  }
}
