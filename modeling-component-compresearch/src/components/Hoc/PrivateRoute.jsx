/**
 * @description 检查用户登录状态及Token有效性
 * @author kygeng
 * date: 2018-0904
 */

import React, { Component } from 'react';
import { inject, observer } from 'mobx-react';
import { toJS } from 'mobx';
import queryString from 'query-string';
import { withRouter, Route } from 'react-router-dom';
import { Row, Spin } from 'antd';
import isEmpty from 'lodash/isEmpty';

const SSOMap = {
    dev:  "https://passport-pre.abcfintech.com",
    pre: "https://passport-pre.abcfintech.com",
    prod: "https://passport.abcfintech.com",
    local: "https://passport-pre.abcfintech.com" // ！！！ 指定localhost为线上预发布，若代理地址为其他环境请修改此处，但不要提交此处修改
}

@withRouter
@inject('authStore')
@observer
export default class PrivateRoute extends Component {

    componentDidMount = async () => {
        const { authStore } = this.props;
        const { userInfo, userState } = authStore;
        if (userState === 'none' && isEmpty(toJS(userInfo))) {
            authStore.getUserInfo();
        }
    }

    handleLogin = () => {
        // 客户自定义域名使用sso正式环境地址
        let sso_host = SSOMap.local;
        if (window.abc_sso_env) {
            sso_host = SSOMap[window.abc_sso_env] || SSOMap.local;
        } else {
            if (window.location.host.indexOf('localhost') !== -1) {
                sso_host = SSOMap.local;
            } else if (window.location.host.indexOf('-dev') !== -1) {
                sso_host = SSOMap.dev;
            } else if (window.location.host.indexOf('-pre') !== -1){
                sso_host = SSOMap.pre
            } else {
                sso_host = SSOMap.prod;
            }
        }
        
        let queryParams = queryString.parse(this.props.location.search);
        if (queryParams.userId || queryParams.ticket) {
            delete queryParams.userId;
            delete queryParams.ticket;
        }
        // 默认从sso返回欢迎页，拿到用户信息
        let host_path = `${window.location.host}/welcome`;
        // 存在重定向参数则进入sso携带地址不再是默认首页
        if (queryParams.redirect_url) {
            host_path = `${host_path}?redirect_url=${queryParams.redirect_url}`;
        }
        let backUrl = `${window.location.protocol}//${host_path}`;
        window.location.href = `${sso_host}/sso-login.html?back=${encodeURIComponent(backUrl)}&sso_terminal=${window.abc_sso_terminal}`;
    }

    render() {
        const { authStore } = this.props;
        const { userState } = authStore;
        const { component: Component, ...rest } = this.props;

        return (
            userState === 'done' ?
            <Route {...rest} render={(props) => (
                <Component {...props}/>
            )}/> :
            userState === 'error' ?
            <Row type="flex" justify="center" align="middle" style={{width:'100%',height:'100%'}}>
                <a onClick={this.handleLogin}>登录</a>
            </Row> :
            <Row type="flex" justify="center" align="middle" style={{width:'100%',height:'100%'}}><Spin/></Row>
            
        )
    }
}