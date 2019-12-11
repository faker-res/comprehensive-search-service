/**
 * 综合搜索-外部研报搜索结果
 * @author lzhang@abcft.com
 * @date 2018-8-20
 */
import React from 'react';
import { withRouter } from 'react-router-dom';
import _ from 'lodash';
import queryString from 'query-string';
import PropTypes from 'prop-types';
import AnnouncementList from '../../Report/Results/AnnouncementList';
import Filters from './components/index'
import ResultsRanking from '../../../../components/ABCComponents/CompreSearch/CommonComponents/ResultsRanking'
import './index.scss';
// import MOCK_DATA from './mock/index.json';

@withRouter
class AnnouncementCompreResult extends React.Component {
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
            loadStatus,
            requireFilters
        } = this.props;
        let {
            defaultPageSize,
            defaultCurrent,
            total_count,
        } = this.state;

        return (
            <div className="abc-comprehensive-search-result-box internal-report">
              <div>
                <ResultsRanking
                    total={total_count}
                    // filters={
                    //   [{
                    //     text: '综合排序',
                    //     key: 'score'
                    //   }, {
                    //     text: '标题优先',
                    //     key: 'title'
                    //   }, {
                    //     text: '正文优先',
                    //     key: 'content'
                    //   }, {
                    //     text: '时间排序',
                    //     key: 'time'
                    //   }]
                    // }
                />
              </div>
                <AnnouncementList requireFilters={requireFilters} className={'search-result-list'}
                    loadInternalData={false}
                    onLoadSuccess={(resp, reload) => {
                        let {data: result = {}} = resp;
                        let {totalCount: total = 0} = result;
                        this.setState({
                            total_count: total
                        })
                    }}/>
            </div>
        );
    }
}

AnnouncementCompreResult.propTypes = {
    // 搜索结果
    data: PropTypes.array,
    // 数据加载状态
    loadStatus: PropTypes.string,
    //筛选条件获取
    requireFilters: PropTypes.func
};

AnnouncementCompreResult.defaultProps = {
    data: [],
    loadStatus: 'pending',
    requireFilters: (option, selected) => {}
};

export default AnnouncementCompreResult;