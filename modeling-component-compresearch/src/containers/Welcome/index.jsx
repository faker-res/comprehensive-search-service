/**
 * @description 欢迎首页
 * @author kygeng
 * date: 2018-09-04
 */
import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { Spin, Button } from 'antd';
import styled from 'styled-components';
import { inject, observer } from 'mobx-react';
import queryString from 'query-string';
import { checkSession } from '../../utils/check';

const WelcomeWrap = styled.div`
    position: fixed;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
`;

const BgWrap = styled.img`
    position: fixed;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    width: 100%;
    height: 100%;
`;

// const StartBtn = styled.div`
//     position: absolute;
//     top: 0;
//     right: 112px;
//     background: transparent;
//     cursor: pointer;
// `;

const Loading = styled.div`
    position: absolute;
    left: 50%;
    top: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    transform: translate(-50%, -50%);
`;

const LogoWrap = styled.div`
    position: absolute;
    left: 88px;
    top: 44px;
    background: transparent;
`;

const ModelingTextWrap = styled.div`
    position: absolute;
    left: 308px;
    top: 355px;
    background: transparent;
`;

const ModelingDescWrap = styled.div`
    position: absolute;
    left: 308px;
    top: 288px;
    font-size: 32px;
    line-height: 1.5;
    font-weight: bold;
    color: #fff;
`;

const LoginWrap = styled.div`
    position: absolute;
    top: 44px;
    right: 88px;
    i {
        margin: 0 22px;
        color: #fff;
        font-size: 14px;
        font-style: normal;
    }
    .ant-btn {
        width: 105px;
        height: 45px;
        font-size: 18px;
        border: none;
        outline: none;
    }
    .btn-login {
        color: #fff;
        opacity: 0.8;
        border-radius: 6px;
        background-color: rgba(36, 110, 193, 1);
        box-shadow: 0px 13px 20px 0px rgba(15, 119, 255, 0.32);
    }
    .btn-register {
        opacity: 0.8;
        border-radius: 6px;
        background-color: rgba(255, 255, 255, 1);
        box-shadow: 0px 13px 20px 0px rgba(15, 119, 255, 0.32);
    }
`;


@withRouter
@inject('authStore')
@observer
export default class Welcome extends Component {

    componentDidMount(){
        checkSession(this.props.history)
    }

    handleStartClick = () => {
        // 客户自定义域名使用sso正式环境地址
        if (window.loginType === 'sso') {
            const SSOMap = {
                dev: "https://passport-dev.abcfintech.com",
                pre: "https://passport-pre.abcfintech.com",
                prod: "https://passport.abcfintech.com",
                local: "https://passport-pre.abcfintech.com" // ！！！ 指定localhost为线上预发布，若代理地址为其他环境请修改此处，但不要提交此处修改
            }
            let sso_host = SSOMap.local;
            if (window.abc_sso_env) {
                sso_host = SSOMap[window.abc_sso_env] || SSOMap.local;
            } else {
                if (window.location.host.indexOf('localhost') !== -1) {
                    sso_host = SSOMap.local;
                } else if (window.location.host.indexOf('-dev') !== -1) {
                    sso_host = SSOMap.dev;
                } else if (window.location.host.indexOf('-pre') !== -1) {
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
    }

    render() {
        const { authStore } = this.props;
        const { userState } = authStore;
        return (
            <WelcomeWrap>
                <BgWrap src={require('../../assets/welcome/img-bg-1.png')} />
                <LogoWrap>
                    <img src={require('../../assets/welcome/modeling-logo-w.png')} alt="Modeling.ai" />
                </LogoWrap>
                <LoginWrap onClick={this.handleStartClick}>
                    <Button className="btn-login">登录</Button>
                    <i>|</i>
                    <Button className="btn-register">注册</Button>
                </LoginWrap>
                <ModelingDescWrap>
                    <span>谋 定 而 后 动&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;知 止 而 有 得</span>
                </ModelingDescWrap>
                <ModelingTextWrap>
                    <img src={require('../../assets/welcome/img-modeling-text.png')} alt="谋定投研" />
                </ModelingTextWrap>
                <Loading hidden={userState !== 'pending'}><Spin size="large" /></Loading>
            </WelcomeWrap>
        )
    }
}