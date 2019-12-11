/**
 * @description 活动日历路由页面
 * @author kygeng
 * date: 2018-08-14
 */
import React from 'react';
import { Route } from 'react-router-dom';
import Loadable from 'react-loadable';
import Loading from '../Loading';

const BigCalendar = Loadable({
    loader: () => import('../../../components/ABCComponents/EventsCalendar/big-calendar'),
    loading: Loading
});


const Index = ({ match }) => (
    <div className="module-report">
        <Route exact path={`${match.url}/`} component={BigCalendar} />
        <Route exact path={`${match.url}/activity`} component={BigCalendar} />
    </div>
);

export default Index;