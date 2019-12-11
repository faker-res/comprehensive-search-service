import React from 'react'
import {Route, Switch, Redirect} from 'react-router-dom'
import Loadable from 'react-loadable'
import Loading from '../Loading'

//导入子组件-系统设置
const Setting = Loadable({
    loader: () => import("../../System/Setting"),
    loading: Loading
})

//导入子组件-用户管理
const User = Loadable({
    loader: () => import("../../System/User"),
    loading: Loading
})

// //导入子组件-角色管理
const Role = Loadable({
    loader: () => import("../../System/Role"),
    loading: Loading
})

// 微信设置
const Wechat = Loadable({
    loader: () => import('../../../components/ABCComponents/EventsCalendar/userInfo'),
    loading: Loading
})

// 邮箱设置
const Email = Loadable({
    loader: () => import('../../../components/ABCComponents/Email/emailSetting'),
    loading: Loading
})

const Index = ({match}) => (
    <div className="module-report">
        <Switch>
            {/* <Route exact path={`${match.url}/`} component={Setting}/> */}
            <Route path={`${match.url}/setting`} component={Setting}/>
            <Route path={`${match.url}/user`} component={User}/>
            <Route path={`${match.url}/role`} component={Role}/>
            <Route path={`${match.url}/wechat`} component={Wechat}/>
            <Route path={`${match.url}/email`} component={Email}/>
            <Redirect to={`${match.url}/setting`}/>
        </Switch>
    </div>
)

export default Index