import React from 'react';
import { Route } from 'react-router-dom';
import Loadable from 'react-loadable';
import Loading from '../Loading';

const IndustryReport = Loadable({
    loader: () => import('../../IndustryReport'),
    loading: Loading
});

// 导入子组件-个股首页
const StockPage = Loadable({
    loader: () => import('../../StockPage'),
    loading: Loading
});

// 导入子组件-分析师
const AnalystReport = Loadable({
    loader: () => import('../../AnalystReport'),
    loading: Loading
});

// 导入子组件-报告统计分析
const ReportAnalysis = Loadable({
    loader: () => import('../../ReportAnalysis'),
    loading: Loading
});

// 导入子组件-研报管理
const ManageReport = Loadable({
    loader: () => import('../../ManageReport'),
    loading: Loading
});


// 导入子组件-上传研报
const UploadReport = Loadable({
    loader: () => import('../../UploadReport'),
    loading: Loading
});

// 研报搜索结果
const ReportResults = Loadable({
    loader: () => import('../../ReportResults'),
    loading: Loading
});

// 内部填补
const InterNalFillReport = Loadable({
    loader: () => import('../../InternalFillReport'),
    loading: Loading
});

const Index = ({ match }) => (
    <div className="module-report">
        <Route exact path={`${match.url}/`} component={IndustryReport} />
        <Route exact path={`${match.url}/secret`} component={IndustryReport} />
        <Route exact path={`${match.url}/secret/industry/:industryId`} component={IndustryReport} />
        <Route exact path={`${match.url}/secret/analyst/:analystId`} component={AnalystReport} />
        {/* 个股首页 */}
        <Route exact path={`${match.url}/stock`} component={StockPage} />
        {/* 分析师 */}
        <Route exact path={`${match.url}/analysts`} component={AnalystReport} />
        {/* 研报分析 */}
        <Route exact path={`${match.url}/statistics`} component={ReportAnalysis} />
        {/* 研报管理 */}
        <Route exact path={`${match.url}/manage`} component={ManageReport} />
        {/* 上传研报 */}
        <Route exact path={`${match.url}/upload`} component={UploadReport} />
        <Route exact path={`${match.url}/internalfillreport`} component={InterNalFillReport} />
        {/* 内部研报搜索结果 */}
        <Route exact path={`${match.url}/secret/report-results`} component={ReportResults} />
    </div>
);
export default Index;