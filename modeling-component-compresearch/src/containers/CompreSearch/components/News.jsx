/**
 * @description 投研活动搜索结果页
 * @author kygeng
 * date: 2018-08-20
 */
import React, { Component } from 'react';
// import styled from 'styled-components';
import { Spin } from 'antd';
import { withRouter } from 'react-router-dom';
import ask from '../../../lib/ask';
import queryString from 'query-string';
import uuidv4 from 'uuid/v4';
import _ from 'lodash'
import {toJS} from 'mobx'
import isEmpty from 'lodash/isEmpty';
import Cookies from 'js-cookie';
import NewsCompreResult from '../../../components/ABCComponents/CompreSearch/NewsCompreResult';
import NewsSiderWrap from '../../../components/ABCComponents/CompreSearch/NewsSiderWrap';
import FilterPanel from 'abc-filter-panel'
import TopSearch from '../../../components/TopSearch'
import './index.scss'


@withRouter
export default class Activity extends Component {

    constructor(props) {
        super(props)
        const queryParams = queryString.parse(props.location.search);
        const page = parseInt(queryParams.pageIndex || 1);
        this.state = {
            hotSearchStatus:'pending',
            hotSearchData:[],
            loadStatus: 'pending',
            activities: [],
            total: 0,
            offset: (20 * (page - 1)),
            limit: 20,
            sort: 'time',
            filterCategorys: [],
            filterSelected: [],
            loading: false //筛选面板加载状态
        }
    }

    loadActivityResults = () => {
        const { keyword = "" } = queryString.parse(this.props.location.search);
        const { limit, offset, sort } = this.state;
        this.setState({ loadStatus: 'pending' });
        ask('ActivitySearch', {
            data: {
                search_word: `keyword:${keyword}`,
                sort_pattern: sort || 'time',
                end_time: new Date().getTime(),
                page_size: limit,
                page_num: (offset / 20) + 1,
                search_log: {
                    device_info: window.navigator.userAgent || '',
                    input_from: "direct",
                    page: 'wx_search',
                    request_id: uuidv4(),
                    user_id: Cookies.get('userId') || ''
                }
            }
        })
            .then(resp => {
                const { code, message, data } = resp;
                const { records = [], total = 0 } = data;
                if (code !== 0 || isEmpty(data) || isEmpty(records)) {
                    throw new Error(`Response Exception:${message},code:${code}`);
                }
                this.setState({
                    loadStatus: 'done',
                    activities: (records || []).map(item => {
                        item.speakers = (item.speakers || []).map(_item => _item.name);
                        item.content = item.detail && item.detail.content || '';
                        return item;
                    }),
                    total: total
                }, () => {
                    if (document.scrollingElement) {
                        document.scrollingElement.scrollTop = 0;
                    } else {
                        // 兼容IE
                        document.documentElement.scrollTop = 0;
                    }
                });
            })
            .catch(err => {
                this.setState({ loadStatus: 'error' });
                console.error(err);
            })
    }

    componentDidMount = async () => {
        // this.loadActivityResults();
        this.loadhotSearch();
    }

    componentDidUpdate(prevProps, prevState) {
        // const oQueryParams = queryString.parse(prevProps.location.search);
        // const nQueryParams = queryString.parse(this.props.location.search);
        // if (nQueryParams.keyword !== oQueryParams.keyword) {
        //     // 重置页码
        //     let queryParams = Object.assign({}, nQueryParams, { pageIndex: 1 });
        //     this.props.history.push({
        //         pathname: this.props.location.pathname,
        //         search: `?${queryString.stringify(queryParams)}`
        //     });
        //     this.setState({
        //         limit: 20,
        //         offset: 0,
        //     }, () => {
        //         this.loadActivityResults();
        //     })
        // }
    }

    handlePageChange = (page) => {
        this.setState({
            limit: 20,
            offset: (20 * (page - 1))
        }, () => {
            this.loadActivityResults();
        })
    }

    handleSortChange = (value) => {
        this.setState({
            sort: value
        }, () => {
            this.loadActivityResults();
        })
    }

    // goToAnalyst = () => {
    //     const { keyword = "" } = queryString.parse(this.props.location.search);
    //     window.open(`https://www.analyst.ai/comprehensive-search?keyword=${keyword}`)
    // }

    // 获取筛选条件
    requireFilters = (option, selected) => {
        this.setState({
            filterCategorys : option,
            filterSelected : selected,
            loading: false
        })
    }
    //筛选面板拓展内容
    handleRenderExtraHeader = (categories, selectedTags) => {}
    //筛选
    onFilterChange = (tags) => {
        this.setState({tags: tags, loading: true})
        const { history, location } = this.props;
        console.log('URL---',history,location);
        let time_area;
        const filterStr = tags.map(item=>{
            if(item.type === 'time_area'){
                time_area = (item.min || item.max) && [item.min,item.max];
                const min = item.min? `,${item.min}` : '';
                const max = item.max? `,${item.max}` : '';
                return `${item.type},${item.name}${min}${max}`;
            };
            return `${item.type},${item.name}`;
        }).join(';');
        const oSearch = queryString.parse(location.search);
        let search;
        if(filterStr){
            oSearch.selected = filterStr;
            //时间范围字段
            if(time_area){
                oSearch.start_time = time_area[0];
                oSearch.end_time = time_area[1];
            }else{
                delete oSearch.start_time;
                delete oSearch.end_time;
            };

            search = queryString.stringify(oSearch);
        }else{
            search = queryString.stringify({keyword : oSearch.keyword});
        };

        history['push']({search});
    }
    loadhotSearch=()=>{
        ask('newsSearch',{ params: {
            page:'news_search_home'
        } })
        .then(resp=>{
            const { code, message, data } = resp;
            if (code !== 200 || isEmpty(data)) {
                throw new Error(`Response Exception:${message},code:${code}`);
              }
            this.setState({
                hotSearchData:data,
                hotSearchStatus:'done'
            })
        })
        .catch(error=>{
            console.log(error);
            this.setState({
                hotSearchStatus:'error'
            })
        })
    }
    clickHotWordCallback = (value)=>{
        const { history, location } = this.props;
        const oSearch = queryString.parse(location.search);
        const keyword = value.trim();
        const method = oSearch.keyword.trim() !== keyword ? 'push' : 'replace';
        oSearch.keyword = keyword || oSearch.keyword || '';
        const search = queryString.stringify({keyword : oSearch.keyword});
        history[method]({search});
    }
    render() {
        const { loadStatus, activities, total, filterCategorys, filterSelected ,hotSearchStatus,hotSearchData,tags} = this.state;
        const { keyword = "" } = queryString.parse(this.props.location.search);
        return (
            <div className="compre-search-results-wrap">
                <div className="compre-search-body">
                    <div className="compre-search-left">
                        {/* <GoToAnalystSearch>
                            <img src={require("./icon.png")} alt="logo" className="analystlogo" />
                            <div className="analystTitle">
                                <p className="titlename">Analyst.Ai</p>
                                <p className="subname">AI金融信息搜索引擎</p>
                            </div>
                            <span onClick={this.goToAnalyst} className="alink">更多搜索结果，点击进入 ></span>
                        </GoToAnalystSearch>
                        {/*筛选组件*/}
                        {
                            <FilterPanel
                                loading={this.state.loading}
                                renderExtraHeader={this.handleRenderExtraHeader}
                                categorys={toJS(filterCategorys)}
                                selectedTags={toJS(filterSelected)}
                                onFilterChange={this.onFilterChange}
                                style={{marginBottom: 10}}
                            />
                        }
                        {/* 资讯搜索结果 */}
                        <NewsCompreResult
                            items={activities}
                            tags={tags}
                            total={total}
                            onPageChange={this.handlePageChange}
                            onSortChange={this.handleSortChange}
                            loadStatus={loadStatus}
                            requireFilters={this.requireFilters}/>
                    </div>
                    <div className="compre-search-right">
                        {/**24小时热搜榜 */}
                        {/* {  hotSearchStatus==='done' && hotSearchData && hotSearchData.length>0 &&
                            <TopSearch  clickCallback={this.clickHotWordCallback} title={'24小时热搜榜'} list={hotSearchData} isLoading={hotSearchStatus==='pending'} ></TopSearch>
                        }     */}
                        {/* 24小时最热资讯 */}
                        <NewsSiderWrap name={keyword} />
                    </div>
                </div>
                {/* <CompreSearchLoading hidden={loadStatus !== 'pending'}>
                    <Spin size="large" />
                </CompreSearchLoading> */}
            </div>
        )
    }
}