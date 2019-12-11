/**
 * 综合搜索-搜索结果
 * @author lzhang@abcft.com
 * @date 2018-8-20
 */
import React from 'react';
import { Pagination, Button } from 'antd';
import { withRouter } from 'react-router-dom';
import _ from 'lodash';
import classnames from 'classnames';
import queryString from 'query-string';
import PropTypes from 'prop-types';
import ResultReport from './ResultReport';
import ResultChart from './ResultChart';
import ResultEvent from './ResultEvent';
import ResultTNews from './ResultTNews';
import ResultAnnouncement from './ResultAnnouncement';
import ResultTable from './ResultTable';
import StandardEmpty from '../../StandardEmpty';

import './lib/ParseKeyword';
import './index.scss';
// import MOCK_DATA from './mock/index.json';

@withRouter
class SearchResultBox extends React.Component {
    constructor(props) {
        super(props);

        this.pageIndexChange = this.pageIndexChange.bind(this);

        this.state = {
            // 默认页码
            defaultCurrent: this.rulePageIndex(),
            // 默认每页显示10条,可不用配置
            defaultPageSize: 10,
            // 全部结果
            total_count: 100,
            // 
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

    pageIndexChange(pageIndex) {
        const { history, onPageChange } = this.props;
        const query = this.getQuery();
        const index = query.pageIndex || 1;
        const method = parseFloat(index) !== pageIndex ? 'push' : 'replace';
        query.pageIndex = pageIndex;
        const search = queryString.stringify(query, { encode: true });
        history[method]({ search });
        onPageChange && onPageChange(pageIndex);
    }

    getTotalCount(data = []) {
        let [first] = data.filter(v => v.source == 'news');
        if (first) {
            let count = isNaN(first.result.data.total_count) || !first.result.data.total_count ? 0 : parseInt(first.result.data.total_count, 10);
            return count;
        }
        return 0;
    }

    render() {
        let {
            data = [],
            loadStatus
        } = this.props;
        let {
            defaultPageSize,
            defaultCurrent,
            total_count,
        } = this.state;

        let pageIndex = this.rulePageIndex();
        total_count = this.getTotalCount(data);
        // console.log(data)
        
        let isNotEmpty = (data.map((v = {}) => ((v.result || {}).data || {}).total_count || 0).reduce((total, val) => total + val, 0));
        return (
            <div className="abc-comprehensive-search-result-box">
                {
                    isNotEmpty ? (
                        <React.Fragment>
                            <div className="search-result-list">
                                {
                                    data.map((item, index) => {
                                        // console.log(pageIndex)
                                        if (pageIndex === 1) {
                                            if (item.source === 'report') {
                                                // 研报
                                                return <ResultReport key={index}
                                                    className={"compwarp"}
                                                    title="相关研报"
                                                    titleSuffix={" 研报"}
                                                    data={item.result.data} />
                                            } else if (item.source === 'activity') {
                                                // 活动日历
                                                return <ResultEvent key={index}
                                                    className="compwarp"
                                                    title="相关公告"
                                                    titleSuffix={" 的活动"}
                                                    data={item.result.data} />
                                            } else if (item.source === 'chart') {
                                                // 数据图
                                                return <ResultChart key={index}
                                                    className="compwarp"
                                                    title="数据图"
                                                    titleSuffix={" 的最新数据图"}
                                                    data={item.result.data} />
                                            }
                                            //总搜结果新增数据表组件
                                            else if (item.source === "table") {
                                                return (
                                                  <ResultTable
                                                    key={index}
                                                    className="compwarp"
                                                    title="数据表"
                                                    titleSuffix={" 的最新数据表"}
                                                    data={item.result.data}
                                                  />
                                                );
                                            }
                                            else if (item.source === 'vender_report') {
                                                // 外部研报
                                                return <ResultReport key={index}
                                                    className="compwarp"
                                                    title="外部研报"
                                                    hideTotal={true}
                                                    titleSuffix={" 的外部研报"}
                                                    data={item.result.data} />
                                            } else if (item.source === 'notice') {
                                                // 相关公告
                                                return <ResultAnnouncement key={index}
                                                    className="compwarp"
                                                    title="相关公告"
                                                    // hideTotal={true}
                                                    titleSuffix={" 公告"}
                                                    data={item.result.data} />
                                            } else if (item.source === 'news') {
                                                // 相关资讯
                                                return <ResultTNews key={index}
                                                    className="compwarp"
                                                    title="相关资讯"
                                                    hideTotal={true}
                                                    titleSuffix={" 的资讯"}
                                                    data={item.result.data} />
                                            }
                                        } else if (item.source === 'news') {
                                            // 相关资讯
                                            return <ResultTNews key={index}
                                                className="compwarp"
                                                title="相关资讯"
                                                hideTotal={true}
                                                titleSuffix={" 的资讯"}
                                                data={item.result.data} />
                                        }
                                        return null;
                                    })
                                }
                            </div>
                            <Pagination
                                hideOnSinglePage={true}
                                defaultPageSize={10}
                                current={pageIndex}
                                total={total_count}
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
                        </React.Fragment>
                    ) : (
                            loadStatus == 'pending' ? null : (
                                <div className="search-result-empty">
                                    <StandardEmpty />
                                </div>
                            )
                        )
                }
            </div>
        );
    }
}

SearchResultBox.propTypes = {
    // 搜索结果
    data: PropTypes.array,
    // 分页切换事件
    onPageChange: PropTypes.func
};

SearchResultBox.defaultProps = {
    data: [],
    onPageChange: null
};

export default SearchResultBox;