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
// import MyDatabaseResult from '../../../MyDatabaseResult'
import ReportList from '../../Report/Results/ReportList';
import ResultsRanking from '../../../../components/ABCComponents/CompreSearch/CommonComponents/ResultsRanking'
import './index.scss';
// import MOCK_DATA from './mock/index.json';

@withRouter
class VenderReportCompreResult extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            // 研报数量
            total_count: 0
        }
    }

    getTotalCount=(count)=>{
        this.setState({
           total_count:count 
        })
    }
    render() {
        const { keyword = "" } = queryString.parse(this.props.location.search);
        let {
            requireFilters
        } = this.props;
        let {
            total_count,
        } = this.state;

        return (
            <div className="abc-comprehensive-search-result-box internal-report">
              <div>
                <ResultsRanking
                    total={total_count}
                    filters={
                      [ {
                            text: '综合排序',
                            key: 'score'
                        }, {
                            text: '时间正序',
                            key: 'time_asc'
                        }, {
                            text: '时间逆序',
                            key: 'time_desc'
                        } ]
                    }
                />
              </div>

                {/*我的资源库结果  */}
                {/* <MyDatabaseResult onLoadSuccess={(total)=>{
                    this.setState({
                        innner_count: total
                    })
                }} name={keyword} defaultLength={'3'}/>     */}
                {/* 研报列表 */}
                <ReportList className={'search-result-list'}
                    requireFilters={requireFilters}
                    loadInternalData={false}
                    onLoadSuccess={(resp, reload, getTotalNumber) => {
                        let {data: result = {}} = resp;
                        let {total_count: total = 0} = result;
                        // 数据总条数
                        this.setState({
                            total_count: getTotalNumber
                        })
                    }}/>
            </div>
        );
    }
}

VenderReportCompreResult.propTypes = {
    // 搜索结果
    data: PropTypes.array,
    // 数据加载状态
    loadStatus: PropTypes.string,
    //回调筛选数据源
    requireFilters:PropTypes.func
};

VenderReportCompreResult.defaultProps = {
    data: [],
    loadStatus: 'pending'
};

export default VenderReportCompreResult;