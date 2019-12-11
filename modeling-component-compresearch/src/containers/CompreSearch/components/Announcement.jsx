/**
 * @description相关资讯搜索结果页
 * @author whm
 * date: 2019-4-11
 */
import React, { Component } from 'react';
import styled from 'styled-components';
import { withRouter } from 'react-router-dom';
import queryString from 'query-string';
import isEmpty from 'lodash/isEmpty';
import AnnouncementCompreResult from '../../../components/ABCComponents/CompreSearch/AnnouncementCompreResult';
import AnnouncementSiderWrap from '../../../components/ABCComponents/CompreSearch/AnnouncementSiderWrap';
import FilterPanel from 'abc-filter-panel'
// mock
import {options} from './announcementMock'
import './index.scss'


@withRouter
export default class Notice extends Component {
    constructor(props) {
        super(props);
        this.state = {
            filterCategorys: [],
            filterSelected: undefined,
            loading: false //筛选面板加载状态
        }
    }
    // goToAnalyst = () => {
    //     const { keyword = "" } = queryString.parse(this.props.location.search);
    //     window.open(`https://www.analyst.ai/comprehensive-search?keyword=${keyword}`)
    // }
    // 获取筛选条件
    requireFilters = (option, selected) => {
        this.setState({
            filterCategorys: option,
            filterSelected: selected ? selected : undefined,
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
                return `${item.type},${item.id ? item.id : item.name}${min}${max}`;
            };
            // 行业、分类通过id过滤
            if (item.type === 'industryIds' || item.type === 'categoryIds') {
                return `${item.type},${item.id}`;
            }
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

    render() {
        const { keyword = "" } = queryString.parse(this.props.location.search);
        // console.log('!isEmpty(this.state.filterCategorys) ', !isEmpty(this.state.filterCategorys))
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
                                categorys={this.state.filterCategorys}
                                selectedTags={this.state.filterSelected}
                                onFilterChange={this.onFilterChange}
                                showKeywordsCategory={true}
                                style={{marginBottom: 10}}
                            />
                        }
                        {/* 公告搜索结果 */}
                        <AnnouncementCompreResult requireFilters={this.requireFilters} />
                    </div>
                    <div className="compre-search-right">
                        {/* 24小时热搜榜 */}
                        {
                            <AnnouncementSiderWrap name={keyword} />
                        }
                    </div>
                </div>
            </div>
        )
    }
}