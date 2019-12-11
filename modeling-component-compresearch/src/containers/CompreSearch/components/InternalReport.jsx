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
import InternalReportCompreResult from '../../../components/ABCComponents/CompreSearch/InternalReportCompreResult';
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
export default class InternalReport extends Component {
    constructor(props) {
        super(props);
    }
    goToAnalyst = () => {
        const { keyword = "" } = queryString.parse(this.props.location.search);
        window.open(`https://www.analyst.ai/comprehensive-search?keyword=${keyword}`)
    }

    render() {
        const { keyword = "" } = queryString.parse(this.props.location.search);
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
                        {/* 综合搜索结果 */}
                        <InternalReportCompreResult />
                    </CompreSearchResultsLeft>
                    <CompreSearchResultsRight>
                        {/* 分析师相关行业 */}
                        {
                            <AnalystSiderWrap name={keyword} />
                        }
                    </CompreSearchResultsRight>
                </CompreSearchResultsBody>
            </CompreSearchResultsWrap>
        )
    }
}