import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react';
import DataSearchResult from '../DataList/';
import FilterBar from '../FilterBar/';
import '../index.scss';

@withRouter @inject('listStore', 'searchStore') @observer
class TabOfData extends Component {
    constructor() {
        super();
        this.state = {
            loading: true,
            list: [],
            totalCount: 0,
            moreResults: true,
            // 数据图二级过滤条件
            filtersData: [],
        };
    }

    static propTypes = {
        isSearched: PropTypes.bool
    }
    reflashPage = () => {
        window.location.reload();
    }

    componentWillReceiveProps(nextProps) {
        let {
            searchStore = {}
        } = nextProps;

        let searchTime = searchStore.search && searchStore.search.cur_time || '';
        let newOp = '' + searchTime;
        let oriOp = '' + this.oriOp;

        
        if (newOp != oriOp) {
            this.setState({
                orderReport: 'all_score'
            });
        }
        this.oriOp = newOp;
    }


    render() {
        const {
            curSearchType,
            filtersData,
            totalData,
        } = this.state;
        // const { listStore, searchStore } = this.props

        // 数据右侧信息
        const DataSearchTabbarRightContent = (props) => {
            return (
                <div className={'tabbar-right-content'}>
                    <p className={'tabbar-right-content-msg'}>
                        共找到相关结果<span>{props.total || 0}</span>个
                    </p>
                </div>
            );
        };

        return (
            <div className="mail-list-results">
                <div className="tab-detail-title">
                    <DataSearchTabbarRightContent
                    total={totalData} />
                    {/* 筛选条件 */}
                    <FilterBar filters={[]}
                        onChange={(filter, allFilters) => {
                        this.setState({
                            filtersData: allFilters
                        });
                    }}/>
                </div>
                
                {/* 数据搜索结果 */}
                <DataSearchResult filters={filtersData}
                    curSearchType={curSearchType}
                    onLoadFail={(reload)=> {
                        let state = {
                            totalData: 0,
                        };
                        this.setState(state);
                    }}
                    onLoadSuccess={(resp, reload) => {
                        let {data = {}} = resp;
                        let state = {
                            totalData: data.total_count || 0,
                        };
                        this.setState(state);
                    }}/>
            </div>
        );
    }
}

export default TabOfData;