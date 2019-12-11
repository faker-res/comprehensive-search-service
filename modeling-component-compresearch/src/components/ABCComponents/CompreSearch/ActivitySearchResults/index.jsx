/**
 * @description 活动日历搜索结果
 * @author kygeng
 * @date: 2018-08-29
 */

import React, { Component } from 'react';
import styled from 'styled-components';
import { withRouter } from 'react-router-dom';
import { Pagination, Select } from 'antd';
import ResultEvent from '../General/ResultEvent';
import queryString from 'query-string';
import _ from 'lodash';
import ResultsRanking from '../../../../components/ABCComponents/CompreSearch/CommonComponents/ResultsRanking'
import EmptyView from '../ChartCompreResult/components/EmptyView';

const ActivitySearchResultsWrap = styled.div`
    width: 100%;
`;

const Header = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 20px;
    width: 100%;
    height: 50px;
`;

const ResultTotal = styled.span`
    font-size: 12px;
    color: #a4a4a4;
    >strong {
        color: #333;
    }
`;

const FilterWrap = styled.div`
    margin-left: 16px;
    .ant-select {
        font-size: 12px;
    }
    .ant-select-selection {
        background: transparent;
        border: none;
        outline: none;
    }
`;

const Content = styled.div`
    padding: 16px 20px;
    width: 100%;
    height: auto;
    background: #fff;
    .ResultEvent > .title {
        display: none;
    }
    .ResultEvent .listType2 .bodyCntWrap {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
`;

const Footer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 30px 0 0 0;
    width: 100%;
    .ant-pagination {
        padding: 0 !important;
    }
    .ant-pagination-prev,
    .ant-pagination-next {
        .ant-pagination-item {
            padding: 0px 5px;
            margin-top: -2px;
        }
    }
`;

const EmptyWrap = styled.div`
    width: 100%;
`;

@withRouter
export default class ActivitySearchResults extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // 默认页码
            defaultCurrent: this.rulePageIndex(),
            // 默认每页显示20条,可不用配置
            defaultPageSize: 20,
            // 全部结果
            total_count: 100
        }
    }

    rulePageIndex = () => {
        const query = this.getQuery();
        const pageIndex = parseFloat(query.pageIndex);
        return !isNaN(pageIndex) && pageIndex > 0 ? pageIndex : 1;
    }

    // 获取查询参数
    getQuery = (search) => {
        return queryString.parse(
            _.isUndefined(search)
                ? this.props.location.search
                : search
        );
    }

    pageIndexChange = (pageIndex) => {
        const { history, onPageChange } = this.props;
        const query = this.getQuery();
        const index = query.pageIndex || 1;
        const method = parseFloat(index) !== pageIndex ? 'push' : 'replace';
        query.pageIndex = pageIndex;
        const search = queryString.stringify(query, { encode: true });
        history[method]({ search });
        onPageChange && onPageChange(pageIndex);
    }

    handleSortChange = (value) => {
        const { onSortChange } = this.props;
        onSortChange && onSortChange(value);
    }


    render() {
        const { items, total, loadStatus } = this.props;
        let pageIndex = this.rulePageIndex();
        return (
            <ActivitySearchResultsWrap>
              <div>
                <ResultsRanking
                    total={this.state.total_count}
                    filters={
                      [{
                        text: '综合排序',
                        key: 'score'
                      }, {
                        text: '标题优先',
                        key: 'title'
                      }, {
                        text: '正文优先',
                        key: 'content'
                      }, {
                        text: '时间排序',
                        key: 'time'
                      }]
                    }
                />
              </div>

                <Content hidden={loadStatus !== 'done'}>
                    <ResultEvent
                        className="compwarp"
                        title="相关活动"
                        titleSuffix={" 的活动"}
                        data={{ item: items, total_count: total }} />
                </Content>
                <Footer hidden={loadStatus !== 'done'}>
                    <Pagination
                        hideOnSinglePage={true}
                        defaultPageSize={20}
                        current={pageIndex}
                        total={total}
                        style={{ textAlign: 'center', paddingBottom: 20 }}
                        onChange={this.pageIndexChange}
                        itemRender={(current, type, originalElement) => {
                            if (type === 'prev') {
                                return <a className={"ant-pagination-item"}>上一页</a>;
                            } if (type === 'next') {
                                return <a className={"ant-pagination-item"}>下一页</a>;
                            }
                            return originalElement;
                        }} />
                </Footer>
                <EmptyWrap hidden={loadStatus !== 'error'}>
                    <EmptyView/>
                </EmptyWrap>
            </ActivitySearchResultsWrap>
        )
    }
}