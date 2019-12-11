/**
 * @description 综合搜索页
 * @author kygeng
 * date: 2018-08-20
 */
import React, { Component } from 'react';
import { Helmet } from "react-helmet";
import styled from 'styled-components';
import { withRouter, Switch, Route } from 'react-router-dom';
import { message, BackTop } from 'antd';
import DefaultHeader from './components/DefaultHeader';
import Footer from '../../components/ABCComponents/CompreSearch/Footer';
import Loadable from 'react-loadable';
import Loading from '../Layout/Loading';
import queryString from 'query-string';

//综合——同行业公司详情
const IndividualStockMore = Loadable({
    loader: () => import('./components/IndividualStockMore'),
    loading: Loading
});

//综合——个股专家更多详情
const AnalysisMore = Loadable({
    loader: () => import('./components/AnalysisMore'),
    loading: Loading
});

const CompreSearchWrap = styled.div`
    width: 100%;
    height: 100%;
    background-color: #F8F8F8;
`;

const CompreSearchMain = styled.div`
    width: 1360px;
    min-width: 1360px;
    max-width: 1360px;
    margin: 0 auto;
    height: auto;
    min-height: 100%;
    background: #f8f8f8;
`;

const FooterWrap = styled(Footer)`
    /* position: fixed;
    left: 0;
    bottom: 0; */
    width: 100%;
`;

@withRouter
export default class EntitySearch extends Component {
    constructor(props) {
        super(props)
    }

    parsePath = pathname => {
        if (pathname.startsWith("/entity-search/individual-stock-more")) {
            return '个股';
        } else if (pathname.startsWith("/entity-search/analysis-more")) {
            return '分析师';
        }
        // else if(pathname.startsWith("/entity-search/chartDetail")){
        //     return '数据图';
        // }
        // else if(pathname.startsWith("/entity-search/tableDetail")){
        //     return '数据表';
        // }
    }

    render() {
        const pathname = this.props.location.pathname
        return (
            <CompreSearchWrap>
                {/*<Helmet>*/}
                    {/*<title>{this.parsePath(pathname)}</title>*/}
                {/*</Helmet>*/}
                <DefaultHeader currentAsideName={this.parsePath(pathname)}/>
                <CompreSearchMain>
                    <Switch>
                        <Route exact path="/entity-search/individual-stock-more" component={IndividualStockMore}/>
                        <Route exact path="/entity-search/analysis-more" component={AnalysisMore}/>
                        {/* <Route exact path="/entity-search/chartDetail" component={ChartDetail}/>
                        <Route exact path="/entity-search/tableDetail" component={TableDetail}/> */}
                    </Switch>
                </CompreSearchMain>
                <BackTop/>
                <FooterWrap/>
            </CompreSearchWrap>
        )
    }
}