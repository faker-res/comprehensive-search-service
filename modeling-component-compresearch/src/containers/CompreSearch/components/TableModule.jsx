/**
 * 综合搜索-数据表搜索结果
 * @author wang@abcft.com
 * @date 2018-11-5
 */
import React, { Component } from 'react';
import styled from 'styled-components';
import { withRouter } from 'react-router-dom';
import ask from '../../../lib/ask';
import queryString from 'query-string';
import TableModuleCompreResult from '../../../components/ABCComponents/CompreSearch/TableModuleCompreResult';
import FilterPanel from 'abc-filter-panel'
import _ from 'lodash'
import {toJS} from 'mobx'

const CompreSearchResultsWrap = styled.div`
    padding: 10px 0;
    width: 100%;
    height: auto;
    min-height: 100%;
    background: #f8f8f8;
`;

const CompreSearchResultsBody = styled.div`
    display: flex;
    justify-content: space-between;
    width: 100%;
    background: #f8f8f8;
`;

const CompreSearchResultsLeft = styled.div`
    flex-basis: 1198px;
    width: 1198px;
`;


const CompreSearchLoading = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    left: 0;
    right: 0;
    bottom: 0;
    top: 0;
    z-index: 400;
`;
// 跳转到主站的小模块
const GoToAnalystSearch = styled.div`
    margin-bottom: 10px;
    box-sizing: border-box;
    padding-top: 10px;
    padding-left: 20px;
    width: 100%;
    height: 64px;
    background-color: rgba(255, 255, 255, 1);
    box-shadow: 0px 2px 6px 1px rgba(0, 0, 0, 0.03);
    border: 1px solid rgba(238, 238, 238, 1);
    .analystlogo{
        margin-right: 10px;
        display: inline-block;
        width: 40px;
        height: 40px;
    }
    .analystTitle{
        display: inline-block;
        vertical-align: middle;
        p{
            margin: 0;
        }
        .titlename{
            line-height: 22px;
            color: rgba(51, 51, 51, 1);
            font-size: 16px;
            text-align: left;
            font-family: PingFangSC-Medium;
        }
        .subname{
            color: rgba(140, 140, 140, 1);
            font-size: 12px;
            text-align: left;
            font-family: PingFangSC-Regular;
        }
       
    }
    .alink{
            float: right;
            margin-top: 14px;
            margin-right: 25px;
            color: rgba(140, 140, 140, 1);
            font-size: 12px;
            text-align: left;
            font-family: PingFangSC-Regular;
            cursor: pointer;
        }
`

@withRouter
export default class Charts extends Component {
    constructor(props) {
        super(props);

        const queryParams = queryString.parse(props.location.search);
        const page = parseInt(queryParams.pageIndex || 1, 10);

        this.state = {
            // 搜索结果数据
            data: [],
            // 分页数
            limit: 20,
            //
            offset: (20 * (page - 1)),
            loadStatus: 'pending', // done, error
            cardStatus: 'pending',
            filterCategorys: [],
            filterSelected: [],
            loading: false //筛选面板加载状态
        }
    }

    loadCompreSearchResults = () => {
        // const { keyword = "" } = queryString.parse(this.props.location.search);
        // const { limit, offset } = this.state;
        // this.setState({ loadStatus: 'pending' });
        // ask('CompreSearchResults', { params: { keyword, limit, offset }})
        //     .then(resp => {
        //         const { code, message, data } = resp;
        //         if (code !== 200 || isEmpty(data)) {
        //             throw new Error(`Response Exception:${message},code:${code}`);
        //         }
        //         this.setState({
        //             loadStatus: 'done',
        //             data: data,
        //         });
        //     })
        //     .catch(err => {
        //         this.setState({ loadStatus: 'error' });
        //         console.error(err);
        //     })
    }

    handleChangeCardItem = (curCardIdx, curCard) => {
        // this.setState({ curCardIdx, curCard });
    }

    handlePageChange = (page) => {
        // this.setState({
        //     limit: 20,
        //     offset: (20 * (page - 1))
        // }, () => {
        //     this.loadCompreSearchResults();
        // })
    }

    componentDidMount = async () => {
        // this.loadCompreSearchResults();
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
        //         limit: 12,
        //         offset: 0,
        //         curCardIdx: 0
        //     },() => {
        //         this.loadCompreSearchResults();
        //     })
        // }
    }
    goToAnalyst = () => {
        const { keyword = "" } = queryString.parse(this.props.location.search);
        window.open(`https://www.analyst.ai/comprehensive-search?keyword=${keyword}`)
    }

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

    render() {
        const {
            loadStatus,
            data = [],
            filterCategorys,
            filterSelected
        } = this.state;

        return (
            <CompreSearchResultsWrap>
                <CompreSearchResultsBody>
                    <CompreSearchResultsLeft>
                        <GoToAnalystSearch>
                            <img src={require("./icon.png")} alt="logo" className="analystlogo" />
                            <div className="analystTitle">
                                <p className="titlename">Analyst.Ai</p>
                                <p className="subname">AI金融信息搜索引擎</p>
                            </div>
                            <span onClick={this.goToAnalyst} className="alink">更多搜索结果，点击进入 ></span>
                        </GoToAnalystSearch>
                        {/*筛选组件*/}
                        {
                            !_.isEmpty(filterCategorys) && <FilterPanel
                                loading={this.state.loading}
                                renderExtraHeader={this.handleRenderExtraHeader}
                                categorys={toJS(filterCategorys)}
                                selectedTags={toJS(filterSelected)}
                                onFilterChange={this.onFilterChange}
                                showKeywordsCategory={false}
                                style={{marginBottom: 10}}
                            />
                        }
                        {/* 数据表搜索结果 */}
                        <TableModuleCompreResult
                            data={data}
                            requireFilters={this.requireFilters}
                            onPageChange={this.handlePageChange}
                            loadStatus={loadStatus}/>
                    </CompreSearchResultsLeft>
                </CompreSearchResultsBody>
            </CompreSearchResultsWrap>
        )
    }
}