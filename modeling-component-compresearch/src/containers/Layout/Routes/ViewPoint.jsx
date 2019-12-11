import React from 'react';
import { Route } from 'react-router-dom';
import Loadable from 'react-loadable';
import Loading from '../Loading';

const ViewPointHomePage = Loadable({
    loader: () => import('../../ViewPointHomePage'),
    loading: Loading
});

// 研报搜索结果
const ReportResults = Loadable({
    loader: () => import('../../ReportResults'),
    loading: Loading
});

const Index = ({ match }) => (
    <div className="module-report">
        <Route exact path={`${match.url}/`} component={ViewPointHomePage} />
        <Route exact path={`${match.url}/report-research`} component={ViewPointHomePage} />
        {/* 卖方观点研报搜索结果 */}
        <Route exact path={`${match.url}/report-research/report-results`} component={ReportResults}/>
    </div>
);

export default Index;