/**
 * @description 综合搜索页
 * @author kygeng
 * date: 2018-08-20
 */
import React, { Component } from 'react';
import styled from 'styled-components';
import { withRouter, Switch, Route } from 'react-router-dom';
import { message, BackTop } from 'antd';
import isEmpty from 'lodash/isEmpty';
import queryString from 'query-string';
// import DefaultHeaderDetail from '../../containers/EntitySearch/components/DefaultHeader'
import DefaultHeader from '../../components/ABCComponents/CompreSearch/DefaultHeader';
import Footer from '../../components/ABCComponents/CompreSearch/Footer';
import Loadable from 'react-loadable';
import Loading from '../Layout/Loading';

// 综合首页
const CompreSearchResults = Loadable({
    loader: () => import('./components/CompreSearchResults'),
    loading: Loading
});
//新版研报
const Report = Loadable({
    loader: () => import('./components/Report'),
    loading: Loading
});
//相关公告
const Announcement = Loadable({
    loader: () => import('./components/Announcement'),
    loading: Loading
});
//相关资讯
const News = Loadable({
    loader: () => import('./components/News'),
    loading: Loading
});
// //数据
// const DataModule = Loadable({
//     loader: () => import('./components/DataModule/index'),
//     loading: Loading
// });
// //数据表模块
// const TableModule = Loadable({
//     loader: () => import('./components/TableModule'),
//     loading: Loading
// });
// const InternalReport = Loadable({
//     loader: () => import('./components/InternalReport'),
//     loading: Loading
// });

// const VenderReport = Loadable({
//     loader: () => import('./components/VenderReport'),
//     loading: Loading
// });

// const Activity = Loadable({
//     loader: () => import('./components/Activity'),
//     loading: Loading
// });

// const Charts = Loadable({
//     loader: () => import('./components/Charts'),
//     loading: Loading
// });
// const ChartDetail = Loadable({
//     loader: () => import('./components/ChartDetail/index'),
//     loading: Loading
// });
// const TableDetail = Loadable({
//     loader: () => import('./components/TableDetail/index'),
//     loading: Loading
// });
// //数据 经济数据详情
// const EdbDetail = Loadable({
//     loader: () => import('./components/EdbDetail/index'),
//     loading: Loading
// });

// //综合——同行业公司详情
// const IndividualStockMore = Loadable({
//   loader: () => import('./components/IndividualStockMore'),
//   loading: Loading
// });

// //综合——个股专家更多详情
// const AnalysisMore = Loadable({
//   loader: () => import('./components/AnalysisMore'),
//   loading: Loading
// });

const CompreSearchWrap = styled.div`
    width: 100%;
    height: 100%;
`;

const CompreSearchMain = styled.div`
    margin: 0 auto 45px;
    width: 80%;
    height: auto;
    min-height: 100%;
`;

const FooterWrap = styled(Footer)`
    /* position: fixed;
    left: 0;
    bottom: 0; */
    width: 100%;
`;

@withRouter
export default class CompreSearch extends Component {
    constructor(props) {
        super(props)
    }

    // 搜索处理
    handleSearch = ({keyword}) => {
        const { history, location } = this.props;
        let queryParams = queryString.parse(this.props.location.search);
        let _keyword = queryParams.keyword || '';
        if (isEmpty(keyword)) {
            message.warning('请输入关键词');
            return;
        }
        if (keyword.trim() === _keyword.trim()) return;
        queryParams.keyword = keyword;
        history.push({
            pathname: location.pathname,
            search: `?${queryString.stringify(queryParams)}`
        });
        // console.log('搜索',history);
    }

    render() {
        // const PATHURL =window.location.href;
        // let chart = PATHURL.indexOf('compre-search/chartDetail')>-1?true:false
        // let talbe = PATHURL.indexOf('compre-search/tableDetail')>-1?true:false
        // let individualStockMore = PATHURL.indexOf('compre-search/individual-stock-more')>-1?true:false
        // let analysisMore = PATHURL.indexOf('compre-search/analysis-more')>-1?true:false
        // let  edbDetail =PATHURL.indexOf('compre-search/edbDetail')>-1?true:false
        // let DetailTitle= chart?'数据图':talbe?'数据表':individualStockMore?'个股':analysisMore?'分析师':edbDetail?'经济数据详情':''
        return (
            <CompreSearchWrap>
                {/* {
                    (chart || talbe || individualStockMore || analysisMore ||edbDetail)?<DefaultHeaderDetail currentAsideName={DetailTitle}/>:<DefaultHeader onSearch={this.handleSearch}/>
                } */}
                <DefaultHeader onSearch={this.handleSearch}/>
                <CompreSearchMain>
                    <Switch>
                        {/* 综合搜索 */}
                        <Route exact path="/" component={CompreSearchResults}/>
                        <Route exact path="/home" component={CompreSearchResults}/>
                        {/* 综合搜索 最新研报 */}
                        <Route exact path="/report" component={Report}/>
                        {/* 综合搜索 相关公告 */}
                        <Route exact path="/announcement" component={Announcement}/>
                        {/* 综合搜索 相关资讯 */}
                        <Route exact path="/news" component={News}/>
                        {/* 综合搜索 数据表模块 */}
                        {/* <Route exact path="/tableModule" component={TableModule}/> */}
                        {/* 综合搜索 数据模块 */}
                        {/* <Route exact path="/dataModule" component={DataModule}/> */}
                        {/**数据图详情*/}
                        {/* <Route exact path="/chartDetail" component={ChartDetail}/> */}
                        {/**数据表详情*/}
                        {/* <Route exact path="/tableDetail" component={TableDetail}/> */}
                        {/**数据 经济数据详情*/}
                        {/* <Route exact path="/edbDetail" component={EdbDetail}/> */}
                        {/**综合—同行业公司详情*/}
                        {/* <Route exact path="/individual-stock-more" component={IndividualStockMore}/> */}
                        {/**综合—个股专家详情*/}
                        {/* <Route exact path="/analysis-more" component={AnalysisMore}/> */}
                        {/* 综合搜索 内部研报 */}
                        {/* <Route exact path="/internal-report" component={InternalReport}/> */}
                        {/* 综合搜索 外部研报 */}
                        {/* <Route exact path="/vender-report" component={VenderReport}/> */}
                        {/* 综合搜索 投研活动 */}
                        {/* <Route exact path="/activity" component={Activity}/> */}
                        {/* 综合搜索 数据图 */}
                        {/* <Route exact path="/chart" component={Charts}/> */}
                    </Switch>
                </CompreSearchMain>
                <BackTop/>
                <FooterWrap/>
            </CompreSearchWrap>
        )
    }
}