import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Loadable from 'react-loadable';
import Loading from './Loading';

// 首页
const Home = Loadable({
    loader: () => import('./../Home'),
    loading: Loading
});

// 股票研究
const Stock = Loadable({
    loader: () => import('./Routes/Stock'),
    loading: Loading
});

// ViewPoint
const ViewPoint = Loadable({
    loader: () => import('./Routes/ViewPoint'),
    loading: Loading
});

//任务管理
const TaskManagement = Loadable({
    loader: () => import('./Routes/Task'),
    loading: Loading
});

//绩效考核
const Performance = Loadable({
    loader: () => import('../Performance'),
    loading: Loading
});
// 404
const NotFound = Loadable({
    loader: () => import('./../NotFound'),
    loading: Loading
});

const NicolasDemo = Loadable({
    loader: () => import('../NicolasDemo'),
    loading: Loading
});


const CyDemo = Loadable({
    loader: () => import('../CyDemo'),
    loading: Loading
});

const WyxDemo = Loadable({
    loader: () => import('../WyxDemo'),
    loading: Loading
});

// 研究报告
const Report = Loadable({
    loader: () => import('./Routes/Report'),
    loading: Loading
});

//导入子组件-搜索结果页
const SearchResult = Loadable({
    loader: () => import('../SearchResult'),
    loading: Loading
});

const System = Loadable({
    loader: () => import('./Routes/System'),
    loading: Loading
})

//投研总监
const InvestmentHome = Loadable({
    loader: () => import('./../InvestmentHome'),
    loading: Loading
});

//风控人员
const RiskManagementPersonnel = Loadable({
    loader: () => import('./../RiskManagementPersonnel'),
    loading: Loading
});

// 活动日历
const EventsCalendar = Loadable({
    loader: () => import('./Routes/Calendar'),
    loading: Loading
})

// 研报统计
const ReportStatistical = Loadable({
    loader: () => import('../ReportStatistical'),
    loading: Loading
})

const Router = () => {
    const parentUrl = '/';
    return (
        <Switch>
            {/* <PrivateRoute exact path={`/reportstatistical`} component={(ReportStatistical)} /> */}
            {/*首页*/}
            <Route exact path={`${parentUrl}home`} component={Home} />
            {/*研究报告*/}
            <Route path={`${parentUrl}report`} component={Report}></Route>
            {/*股票研究*/}
            <Route path={`${parentUrl}research`} component={Stock} />
            {/*卖方观点*/}
            <Route path={`${parentUrl}viewpoint`} component={ViewPoint} />
            {/* 活动日历 */}
            <Route path={`${parentUrl}activity`} component={EventsCalendar} />
            {/*任务管理*/}
            <Route path={`${parentUrl}task`} component={TaskManagement} />

            {/*研报统计*/}
            <Route path={`${parentUrl}reportstatistical`} component={ReportStatistical} />

            {/*绩效考核*/}
            <Route path={`${parentUrl}performance`} component={Performance} />

            <Route path={`${parentUrl}system`} component={System} />

            {/*搜索结果页，非显式暴露入口*/}
            <Route exact path={`${parentUrl}search`} component={SearchResult} />

            {/*投研总监*/}
            <Route exact path={`${parentUrl}investmentHome`} component={InvestmentHome} />

            {/*风控人员*/}
            <Route exact path={`${parentUrl}riskManagement`} component={RiskManagementPersonnel} />


            {/*组件Demo页，请无视*/}
            <Route exact path={`${parentUrl}cydemo`} component={CyDemo} />
            <Route exact path={`${parentUrl}nicolas-demo`} component={NicolasDemo} />
            <Route exact path={`${parentUrl}wyx`} component={WyxDemo} />

            {/* 404 */}
            <Route component={NotFound} />
        </Switch>
    );
};

export default Router;
