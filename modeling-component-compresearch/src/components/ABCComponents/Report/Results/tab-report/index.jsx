import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Select
} from 'antd';
import { withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react';
import ReportSearchResult from '../ReportList/';
import FilterBar from '../FilterBar/';
import queryString from 'query-string';
import isEmpty from 'lodash/isEmpty';
import styled from 'styled-components';
import FilterTags from '../../FilterTags';
import '../index.scss';

const SelectOption = Select.Option;

const TabOfReportWrap = styled.div`
    min-height: 800px;
`;

const FilterBarWrap = styled(FilterBar)`
    width: auto !important;
`

const FilterTagsWrap = styled(FilterTags)`
    float: left;
`;

@withRouter @inject('listStore', 'searchStore') @observer
class TabOfReport extends Component {
    constructor() {
        super();
        const queryParams = queryString.parse(window.location.search);
        this.state = {
            loading: true,
            list: [],
            totalCount: 0,
            moreResults: true,
            // 研报二级过滤条件
            filtersReport: [{
                key: 'stock',
                value: queryParams.stock_filter || ''
            }],
            // 数据图搜索总条数
            totalData: 0,
            // 研报搜索总条数
            totalReport: 0,
            // 研报排序条件
            orderReport: 'all_score',
            // 研报自选股筛选
            myStockFilterOn: false
        };
    }

    static propTypes = {
        isSearched: PropTypes.bool
    }
    reflashPage = () => {
        window.location.reload();
    }

    handleFilterChange = ({filter, data}) => {
        let queryParams = queryString.parse(this.props.location.search);
        if (data) {
            queryParams.filters = 'stock';
        } else {
            if (typeof queryParams.filters !== 'undefined') delete queryParams.filters;
        }
        this.props.history.push({
            pathname: this.props.location.pathname,
            search: `?${queryString.stringify(queryParams)}`
        });
    }

    handleOrderChange = (order) => {
        let queryParams = queryString.parse(this.props.location.search);
        queryParams.order = order;
        this.props.history.push({
            pathname: this.props.location.pathname,
            search: `?${queryString.stringify(queryParams)}`
        });
    }

    render() {
        const {
            curSearchType,
            filtersReport,
            totalReport,
            orderReport,
        } = this.state;

        // 研报右侧信息
        const ReportSearchTabbarRightContent = (props) => {
            return (
                <div className={'tabbar-right-content'}>
                    <p className={'tabbar-right-content-msg'}>
                        共找到相关结果<span>{props.total || 0}</span>个
                    </p>
                    <Select defaultValue={props.defaultValue || 'all_score'}
                        size="small"
                        style={{ width: 100, outline: 'none', border: 'none' }}
                        onChange={(value) => {
                            props.onChange && props.onChange(value);
                        }}>

                        <SelectOption value={'all_score'} className="sort-select-option-item">综合排序</SelectOption>
                        <SelectOption value={'time_asc'} className="sort-select-option-item">日期升序</SelectOption>
                        <SelectOption value={'time_desc'} className="sort-select-option-item">日期降序</SelectOption>
                    </Select>
                </div>
            );
        };

        const queryParams = queryString.parse(window.location.search);
        return (
            <TabOfReportWrap className="mail-list-results">
                {/* 筛选条件 */}
                
                <div className="tab-detail-title">
                    <FilterBarWrap
                        filters={[
                            {
                                key: 'stock',
                                type : 'checkbox',
                                params:{
                                    text: '自选股',
                                    checked: !isEmpty(queryParams.filters)
                                }
                            }
                        ]}
                        onChange={this.handleFilterChange} />
                    <FilterTagsWrap/>
                    <ReportSearchTabbarRightContent
                        total={totalReport}
                        defaultValue={queryParams.order}
                        onChange={this.handleOrderChange} />
                </div>

                {/* 研报搜索结果 */}
                <ReportSearchResult filters={filtersReport}
                    order={orderReport}
                    curSearchType={curSearchType}
                    onLoadFail={(reload) => {
                        let state = {
                            totalReport: 0,
                        };
                        this.setState(state);
                    }}
                    onLoadSuccess={(resp, reload) => {
                        let { data = {} } = resp;
                        let state = {
                            totalReport: data.total_count || 0,
                        };
                        this.setState(state);
                    }} />
            </TabOfReportWrap>
        );
    }
}

export default TabOfReport;