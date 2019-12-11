import React from 'react';
import { Route, Switch, Redirect, withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react';
import { toJS } from 'mobx';
import Loadable from 'react-loadable';
import Loading from '../Loading';
import { ROLE_RESEARCHER, ROLE_FUNDMANAGER, ROLE_DIRECTOR, ROLE_RISKOFFICER } from '../../../lib/constants';

const TaskListHome = Loadable({
  loader: () => import("../../TaskListHome"),
  loading: Loading
});
const ApprovalList = Loadable({
  loader: () => import("../../ApprovalList"),
  loading: Loading
});
const InvestReportList = Loadable({
  loader: () => import("../../InvestReportListHome"),
  loading: Loading
});
const RiskListHome = Loadable({
  loader: () => import("../../RiskListHome"),
  loading: Loading
});
const RiskUploadReport = Loadable({
  loader: () => import("../../RiskUploadReport"),
  loading: Loading
});

@withRouter
@inject('authStore')
@observer
export default class Index extends React.Component {

  render() {
    const { authStore, match } = this.props;
    return (
      <div className="module-report">
        <Route exact path={`${match.url}/`} render={(props) => {
          return authStore.roleName === ROLE_RISKOFFICER ? <Redirect to={`${match.url}/risk-list`}/> :
            <TaskListHome {...props}/>
        }} />
        <Route exact path={`${match.url}/task-index`} render={(props) => {
          return authStore.roleName === ROLE_RISKOFFICER ? <Redirect to={`${match.url}/risk-list`}/> :
            <TaskListHome {...props}/>
        }} />
        <Route exact path={`${match.url}/stock-position-apply-list`} component={ApprovalList} />
        <Route exact path={`${match.url}/invest-report-apply-list`} component={InvestReportList} />
        <Route exact path={`${match.url}/risk-list`} component={RiskListHome} />
        <Route exact path={`${match.url}/risk-upload-report`} component={RiskUploadReport} />
      </div>
    )
  }
}