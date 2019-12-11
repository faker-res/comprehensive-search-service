/**
 * 综合搜索-数据表搜索结果
 * @author wang@abcft.com
 * @date 2018-11-5
 */
import React from 'react';
import { withRouter } from 'react-router-dom';
import _ from 'lodash';
import queryString from 'query-string';
import PropTypes from 'prop-types';
import ResultsRanking from '../../../../components/ABCComponents/CompreSearch/CommonComponents/ResultsRanking'
import TableList from './components/TableList'
import  MyDatabaseResult from './components/MyDatabaseResult'
import './index.scss';

@withRouter
class TableModuleCompreResult extends React.Component {
    constructor(props) {
        super(props);
        // this.pageIndexChange = this.pageIndexChange.bind(this);
        this.state = {
            // 搜索总数
            total_count: 0,
            //我的资源库总数
            innner_count:0,
        }
    }

    rulePageIndex = () => {
        // const query = this.getQuery();
        // const pageIndex = parseFloat(query.pageIndex);
        // return !isNaN(pageIndex) && pageIndex > 0 ? pageIndex : 1;
    }

    // 获取查询参数
    getQuery = (search) => {
        // return queryString.parse(
        //     _.isUndefined(search)
        //         ? this.props.location.search
        //         : search
        // );
    }

    pageIndexChange(pageIndex) {
        // const { history, onPageChange } = this.props;
        // const query = this.getQuery();
        // const index = query.pageIndex || 1;
        // const method = parseFloat(index) !== pageIndex ? 'push' : 'replace';
        // query.pageIndex = pageIndex;
        // const search = queryString.stringify(query, { encode: true });
        // history[method]({ search });
        // onPageChange && onPageChange(pageIndex);
    }

    render() {
        const { keyword = "" } = queryString.parse(this.props.location.search);
        let {
            data = [],
            loadStatus,
            requireFilters
        } = this.props;
        let {
            defaultPageSize,
            defaultCurrent,
            total_count,
            innner_count
        } = this.state;
        let isNotEmpty = false

        return (
            <div className="abc-comprehensive-search-result-box chart">
              <div>
                <ResultsRanking total={total_count + innner_count}/>
              </div>
                <MyDatabaseResult onLoadSuccess={(total)=>{
                  this.setState({
                    innner_count: total
                  })
                }} name={keyword} defaultLength={2}/>
                <TableList className={'search-result-list'}
                    loadInternalData={true}
                    requireFilters={requireFilters}
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

TableModuleCompreResult.propTypes = {
    // 搜索结果
    data: PropTypes.array,
    // 数据加载状态
    loadStatus: PropTypes.string,
    //筛选条件获取
    requireFilters: PropTypes.func
};

TableModuleCompreResult.defaultProps = {
    data: [],
    loadStatus: 'pending',
    requireFilters: (option, selected) => {}
};

export default TableModuleCompreResult;