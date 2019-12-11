/**
 * @description 投研活动搜索结果页
 * @author kygeng
 * date: 2018-08-20
 */
import React, { Component } from 'react';
import styled from 'styled-components';
import { Spin } from 'antd';
import { withRouter } from 'react-router-dom';
import ask from '../../../lib/ask';
import queryString from 'query-string';
import uuidv4 from 'uuid/v4';
import isEmpty from 'lodash/isEmpty';
import Cookies from 'js-cookie';
import ActivitySearchResults from '../../../components/ABCComponents/CompreSearch/ActivitySearchResults';
import AnalystSiderWrap from '../../../components/ABCComponents/CompreSearch/AnalystSiderWrap';

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
    flex-basis: 870px;
    width: 870px;
`;

const CompreSearchResultsRight = styled.div`
    flex-basis: 350px;
    width: 350px;
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
    width: 870px;
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
export default class Activity extends Component {

    constructor(props) {
        super(props)
        const queryParams = queryString.parse(props.location.search);
        const page = parseInt(queryParams.pageIndex || 1);
        this.state = {
            loadStatus: 'pending',
            activities: [],
            total: 0,
            offset: (20 * (page - 1)),
            limit: 20,
            sort: 'time'
        }
    }

    loadActivityResults = () => {
        const { keyword = "", order} = queryString.parse(this.props.location.search);
        const { limit, offset, sort } = this.state;
        this.setState({ loadStatus: 'pending' });
        ask('ActivitySearch', {
            data: {
                search_word: `keyword:${keyword}`,
                sort_pattern: sort || 'time',
                end_time: new Date().getTime(),
                page_size: limit,
                page_num: (offset / 20) + 1,
                prior: order,
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
        this.loadActivityResults();
    }

    componentDidUpdate(prevProps, prevState) {
        const oQueryParams = queryString.parse(prevProps.location.search);
        const nQueryParams = queryString.parse(this.props.location.search);
        if (nQueryParams.keyword !== oQueryParams.keyword || nQueryParams.order !== oQueryParams.order) {
          // if (this.props.location !== prevProps.location){
            // 重置页码
            let queryParams = Object.assign({}, nQueryParams, { pageIndex: 1 });
            this.props.history.push({
                pathname: this.props.location.pathname,
                search: `?${queryString.stringify(queryParams)}`
            });
            this.setState({
                limit: 20,
                offset: 0,
            }, () => {
                this.loadActivityResults();
            })
        }
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

    goToAnalyst = () => {
        const { keyword = "" } = queryString.parse(this.props.location.search);
        window.open(`https://www.analyst.ai/comprehensive-search?keyword=${keyword}`)
    }

    render() {
        const { loadStatus, activities, total } = this.state;
        const { keyword = "" } = queryString.parse(this.props.location.search);
        return (
            <CompreSearchResultsWrap>
                <CompreSearchResultsBody>
                    <CompreSearchResultsLeft>
                        {/* <GoToAnalystSearch>
                            <img src={require("./icon.png")} alt="logo" className="analystlogo" />
                            <div className="analystTitle">
                                <p className="titlename">Analyst.Ai</p>
                                <p className="subname">AI金融信息搜索引擎</p>
                            </div>
                            <span onClick={this.goToAnalyst} className="alink">更多搜索结果，点击进入 ></span>
                        </GoToAnalystSearch> */}
                        {/* 投研活动搜索结果 */}
                        <ActivitySearchResults
                            items={activities}
                            total={total}
                            onPageChange={this.handlePageChange}
                            onSortChange={this.handleSortChange}
                            loadStatus={loadStatus} />
                    </CompreSearchResultsLeft>
                    <CompreSearchResultsRight>
                        {/* 分析师侧边组件 */}
                        {/* <AnalystSiderWrap name={keyword} /> */}
                    </CompreSearchResultsRight>
                </CompreSearchResultsBody>
                <CompreSearchLoading hidden={loadStatus !== 'pending'}>
                    <Spin/>
                </CompreSearchLoading>
            </CompreSearchResultsWrap>
        )
    }
}