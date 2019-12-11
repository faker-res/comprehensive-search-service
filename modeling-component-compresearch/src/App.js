import React, { Component } from 'react';
import 'url-search-params-polyfill';
import { Switch, Route, Redirect, withRouter } from 'react-router-dom';
// import Layout from './containers/Layout';
import { inject, observer } from 'mobx-react';
import Loadable from 'react-loadable';
import Loading from './containers/Layout/Loading';
import PrivateRoute from './components/Hoc/PrivateRoute';
import './theme/common.scss';
import './theme/wechat-emoji/emoji.scss';

// 综合搜索
const CompreSearch = Loadable({
  loader: () => import('./containers/CompreSearch'),
  loading: Loading
})

// // 实体搜索
// const EntitySearch = Loadable({
//   loader: () => import('./containers/EntitySearch'),
//   loading: Loading
// })

// // 研究员
// const Analyst = Loadable({
//   loader: () => import('./containers/Analyst'),
//   loading: Loading
// })

//未登录欢迎页
const Welcome = Loadable({
  loader: () => import('./containers/Welcome/index'),
  loading: Loading
});

//已登录欢迎页
const loggedWelcome = Loadable({
  loader: () => import('./containers/Welcome/Welcome'),
  loading: Loading
});

@withRouter
@inject('defaultStore')
@observer
class App extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="App">
        <Switch>
          {/* 综合搜索 首页*/}
          <Route exact path="/" component={Welcome}></Route>
          <Route path='/welcome' component={loggedWelcome}></Route>
          <PrivateRoute path={'/home'} component={(CompreSearch)} />
          {/* 综合搜索 研报 */}
          <PrivateRoute path={'/report'} component={(CompreSearch)} />
          {/* 综合搜索 相关公告 */}
          <PrivateRoute path={'/announcement'} component={(CompreSearch)} />
          {/* 综合搜索 相关资讯 */}
          <PrivateRoute path={'/news'} component={(CompreSearch)} />
          {/* 综合搜索 数据模块 */}
          {/* <PrivateRoute exact path="/dataModule" component={(CompreSearch)} />  */}
          {/* 综合搜索 数据表 */}
          {/* <PrivateRoute exact path="/tableModule" component={(CompreSearch)} />  */}
          {/* 综合搜索 内部研报 */}
          {/* <PrivateRoute exact path="/internal-report" component={(CompreSearch)} /> */}
          {/* 综合搜索 外部研报 */}
          {/* <PrivateRoute exact path="/vender-report" component={(CompreSearch)} /> */}
          {/* 综合搜索 投研活动 */}
          {/* <PrivateRoute exact path="/activity" component={(CompreSearch)} /> */}
          {/* 综合搜索 数据图 */}
          {/* <PrivateRoute exact path="/chart" component={(CompreSearch)} /> */}
          {/* 综合搜索 数据表详情 */}
          {/* <PrivateRoute exact path="/tableDetail" component={(CompreSearch)} /> */}
          {/* 综合搜索 数据图详情 */}
          {/* <PrivateRoute exact path="/chartDetail" component={(CompreSearch)} /> */}
          {/* 综合搜索 数据-经济数据详情*/}
          {/* <PrivateRoute exact path="/edbDetail" component={(CompreSearch)} /> */}
          {/* 综合搜索 同行业公司 */}
          {/* <PrivateRoute exact path="/individual-stock-more" component={(CompreSearch)} /> */}
          {/* 综合搜索 个股专家 */}
          {/* <PrivateRoute exact path="/analysis-more" component={(CompreSearch)} /> */}
          {/* 研究员卡片 */}
          {/* <PrivateRoute exact path="/entity-search/analyst" component={(Analyst)} /> */}
          {/*实体搜索*/}
          {/* <PrivateRoute path="/entity-search" component={(EntitySearch)} /> */}
          {/* Layout 是啥 */}
          {/* <PrivateRoute path="/:item" component={(Layout)} /> */}
          <Redirect to="/" />
        </Switch>
        {/* {process.env.NODE_ENV === 'development' && <DevTools />} */}
      </div>
    );
  }
}
export default App;
