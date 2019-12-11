/**
 * 综合搜索-内部研报搜索结果
 * @author lzhang@abcft.com
 * @date 2018-8-20
 */
import React from 'react';
import { withRouter } from 'react-router-dom';
import _ from 'lodash';
import queryString from 'query-string';
import PropTypes from 'prop-types';
import ReportList from '../../Report/Results/ReportList';
import Filters from './components/index'
import './index.scss';
// import MOCK_DATA from './mock/index.json';

@withRouter
class InternalReportCompreResult extends React.Component {
    constructor(props) {
        super(props);

        this.pageIndexChange = this.pageIndexChange.bind(this);

        this.state = {
            // 搜索总数
            total_count: 0
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

        let isNotEmpty = false
        return (
            <div className="abc-comprehensive-search-result-box internal-report">
                <Filters
                    total={total_count}
                    size="small"
                    style={{ outline: 'none', border: 'none' }}
                    filters={[{
                        text: '综合排序',
                        key: 'all_score'
                    }, {
                        text: '时间升序',
                        key: 'time_asc'
                    }, {
                        text: '时间降序',
                        key: 'time_desc'
                    }]}/>
                <ReportList className={'search-result-list'}
                    loadInternalData={true}
                    onLoadSuccess={(resp, reload) => {
                        let {data: result = {}} = resp;
                        let {total_count: total = 0} = result;
                        this.setState({
                            total_count: total
                        })
                    }}/>
            </div>
        );
    }
}

InternalReportCompreResult.propTypes = {
    // 搜索结果
    data: PropTypes.array,
    // 数据加载状态
    loadStatus: PropTypes.string
};

InternalReportCompreResult.defaultProps = {
    data: [],
    loadStatus: 'pending'
};

export default InternalReportCompreResult;