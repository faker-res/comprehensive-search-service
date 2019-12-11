/**
 * @description 内部研报搜索结果页
 * @author kygeng
 * date: 2018-08-20
 */
import React, { Component } from 'react';
import styled from 'styled-components';
import { withRouter } from 'react-router-dom';
import ask from '../../../lib/ask';
import queryString from 'query-string';
import isEmpty from 'lodash/isEmpty';
import { Spin } from 'antd';
import _ from 'lodash'
import VenderReportCompreResult from '../../../components/ABCComponents/CompreSearch/VenderReportCompreResult';
import TopSearch from '../../../components/TopSearch'
// import CategoryMenu from 'abc-category-menu';
import StandardEmpty from '../../../components/ABCComponents/StandardEmpty'
import FilterPanel from 'abc-filter-panel'
import indu from '../../../assets/image/icon_indu.svg';
import category from '../../../assets/image/icon_type.svg';
import './index.scss'


@withRouter
export default class Report extends Component {
    constructor(props) {
        super(props);
        this.state={
            hotSearchStatus: "pending",
            hotSearchData:[],
            filterCategorys: [],
            filterSelected: [],
            menuList: [], //主要行业和报告类型数据
            isMenuLoading: false, //条件面板  
            loading: false //筛选面板加载状态
        }
    }

    componentDidMount=()=>{
        // category数据
        // this.getMenuList();
        // 热搜数据
        // this.loadhotSearch();
    }

    // 获取主要行业和报告类型
    // getMenuList = () => {
    //     var self = this;
    //     // 获取顶上 报告类型和主要行业
    //     // let url = flag ? 'getFilterConfig' : '';
    //     this.setState({
    //         isMenuLoading: true
    //     }, () => {
    //         ask('getReportFilterConfig', {
    //             params: {
    //                 app: 'report',
    //                 filter: 'industry,category',
    //             }
    //         }).then(res => {
    //             console.log('行业筛选',res);
    //             if (res.code === 200 && res.data) {
    //                 const menuList = [];
    //                 menuList.push({ title: '主要行业', icon: <img src={indu} alt='主要行业' />, typeList: res.data.industry || [], type: 'industry' });
    //                 menuList.push({ title: '报告类型', icon: <img src={category} alt='报告类型' />, typeList: res.data.category || [], type: 'category' });
    //                 self.setState({
    //                     menuList: menuList,
    //                     isMenuLoading: false
    //                 })
    //             } else {
    //                 self.setState({
    //                     isMenuLoading: false
    //                 })
    //             }
    //         }).catch(error => {
    //             self.setState({
    //                 isMenuLoading: false
    //             })
    //             console.log(error);
    //         });;
    //     })
    // }
    // // 判断所属类别 
    // categoryJudgement = (name) => {
    //     const menuList = this.state.menuList
    //     for(let i in menuList) {
    //         let element = menuList[i].typeList.find(item => item.name === name)
    //         if (element) return menuList[i].type === 'category' ? 'report' : 'industry'
    //     }
    // }
    // // 主要行业和报告类型点击项
    // clickSearchType(t) {
    //     console.log('report.js ', t)
    //     const { history, location } = this.props;
    //     const oSearch = queryString.parse(location.search);
    //     const value = t.name.trim();
    //     const type = this.categoryJudgement(value)
    //     const method = !oSearch[type] || oSearch[type].trim() !== value ? 'push' : 'replace';
    //     oSearch[type] = value || oSearch[type] || '';
    //     const search = queryString.stringify(oSearch);
    //     history[method]({search});
    // }

    filterOption = (options) => {
        return options.filter( item => item.item && item.item.length )
    }

    // 获取筛选条件
    requireFilters= (options, selected) => {
        const option = this.filterOption(options)
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
    
    // 点击hot
    clickHotWordCallback = (value)=>{
        const { history, location } = this.props;
        const oSearch = queryString.parse(location.search);
        const keyword = value.trim();
        const method = !oSearch.keyword || oSearch.keyword.trim() !== keyword ? 'push' : 'replace';
        oSearch.keyword = keyword || oSearch.keyword || '';
        const search = queryString.stringify(oSearch);
        history[method]({search});
        //更改日志-搜索来源
        // this.props.suggestStore.input_from = 'rank';
    }
    
    // 24小时热搜
    loadhotSearch=()=>{
        // reportTopSearch
        ask('reportTopSearch',{ params: {
            type: 2,
            module: 30001,
            cate: 30001,
            terminal: 0,
            lan: 0,
            offset: 5,
            limit: 10} })
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
    render() {
        const { hotSearchData,hotSearchStatus, menuList, isMenuLoading, filterCategorys, filterSelected} = this.state;
        const { keyword = "" } = queryString.parse(this.props.location.search);
        return (
            <div className="compre-search-results-wrap">
                <div className="compre-search-body">
                    <div className="compre-search-left">
                        {/*筛选组件*/}
                        {
                            <FilterPanel
                                loading={this.state.loading}
                                renderExtraHeader={this.handleRenderExtraHeader}
                                categorys={filterCategorys}
                                selectedTags={filterSelected}
                                onFilterChange={this.onFilterChange}
                                showKeywordsCategory={false}
                                style={{marginBottom: 10}}
                            />
                        }
                        {/* 筛选目录 */}
                        {/* <div style={{ marginBottom: 10 }}>
                            {menuList.length > 0 ? <CategoryMenu
                                menuList={menuList}
                                onItemClick={(item) => this.clickSearchType(item)}
                                isLoading={isMenuLoading}
                            /> : 
                            <div style={{ height: 200 }}> <StandardEmpty title="暂无内容" /> </div>
                            }
                        </div> */}
                        {/* 综合搜索结果 */}
                        <VenderReportCompreResult requireFilters={this.requireFilters}/>
                    </div>
                    <div className="compre-search-right">
                        {/* 分析师相关行业 */}
                        {
                            // <AnalystSiderWrap name={keyword} />
                        }
                        {/**24小时热搜榜 */}
                        { 
                            //  hotSearchStatus==='done' && hotSearchData && hotSearchData.length>0 &&
                            // <TopSearch  clickCallback={this.clickHotWordCallback} title={'24小时热搜榜'} list={hotSearchData} isLoading={hotSearchStatus==='pending'} ></TopSearch>
                        }
                    </div>
                </div>
            </div>
        )
    }
}