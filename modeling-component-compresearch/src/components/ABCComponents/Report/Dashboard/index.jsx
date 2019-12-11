/**
 * 研究报告-内部研报-首页看板
 * @author lzhang@abcft.com
 * @date 2018-8-9
 */
import React from 'react';
import Content from './components';
import styled from 'styled-components';
import { BackTop } from 'antd';
import { withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react';
import PropTypes from 'prop-types';
import SuggestedSearchBox from '../../SuggestedSearchBox';
import queryString from 'query-string';
import isEmpty from 'lodash/isEmpty';

import './index.scss';

const ReportSearchWrap = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 10px 0;
    width: 100%;
    background-color: transparent;
`;

const ReportSearch = styled(SuggestedSearchBox)`
    width: 700px;
    input {
        height: 40px;
    }
    button {
        height: 40px;
    }
`;


@inject('listStore', 'searchStore')
@observer
@withRouter
class Dashboard extends React.Component {
  constructor(props) {
    super(props);

    this.state = {}
  }

  // 搜索处理
  handleSearch = ({ keyword }) => {
    const { history, tab = "" } = this.props;
    const queryParams = queryString.parse(this.props.location.search);
    let _keyword = queryParams.keyword || '';
    if (isEmpty(keyword) || keyword.trim() === _keyword.trim()) return;
    history.push({
      pathname: `/${tab}/report-results`,
      search: `?keyword=${encodeURIComponent(keyword)}`
    });
  }

  // 获取搜索建议
  handleRequestSearchSuggest = (keyword) => {
    console.log(keyword);
  }

  render() {
    let {
      tab = "",
      panelTabs = []
    } = this.props;

    return (
      <div className="abc-report-dashboard">
        <BackTop />
        <ReportSearchWrap>
          {/* <ReportSearch
              placeholder="公司名称\关键字\作者"
              enterButton="搜索"
              onSearch={this.handleSearch}/> */}
          {/* 搜索框 */}
          {/* <ReportSearch
            className={`report-results__search-box`}
            placeholder="公司名称\关键字\作者"
            defaultQuery=""
            suggestions={{
              default: [],
              search: [],
            }}
            searchHistoryTitle="最近搜过"
            onRequestSearchSuggest={this.handleRequestSearchSuggest}
            onSearch={this.handleSearch}
          /> */}
        </ReportSearchWrap>

        <Content tab={tab} panelTabs={panelTabs}/>
      </div>
    )
  }
}

Dashboard.propTypes = {
  // 面板所处Tab
  tab: PropTypes.string,
  // 面板分类数组
  panelTabs: PropTypes.array
};

Dashboard.defaultProps = {
  tab: "report/secret",
  panelTabs: [
    [{
      category: '行业深度',
    }, {
      category: '公司深度',
    }, {
      category: '行业调研',
    }, {
      category: '公司调研',
    }],
    [{
      category: '晨会纪要',
    }, {
      category: '早间资讯',
    }],
    [{
      category: '港股研究',
    }, {
      category: '美股研究',
    }, {
      category: '台股研究',
    }],
    [{
      category: '基金研究',
    }, {
      category: '期货研究',
    }, {
      category: '债券研究',
    }, {
      category: '期权研究',
    }]
  ]
};

export default Dashboard;