/**
 * @description 欢迎首页
 * @author kygeng
 * date: 2018-09-04
 */
import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { Spin } from 'antd';
import styled from 'styled-components';
import { inject, observer } from 'mobx-react';
import { toJS } from 'mobx';
import queryString from 'query-string';
import isEmpty from 'lodash/isEmpty';

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

@withRouter
@inject('authStore')
@observer
export default class Welcome extends Component {

    componentDidMount = async () => {
        const { authStore } = this.props;
        const { userInfo, userState } = authStore;
        if (userState === 'none' && isEmpty(toJS(userInfo))) {
            await authStore.getUserInfo();
        } else if (userState === 'done' && !isEmpty(toJS(userInfo))) {
            this.redirectTo(toJS(userInfo).domainName, toJS(userInfo).roleName);
        }
    }

    componentDidUpdate() {
        const { authStore } = this.props;
        const { userInfo, userState } = authStore;
        if (!isEmpty(toJS(userInfo)) && userState === 'done') {
            this.redirectTo(toJS(userInfo).domainName, toJS(userInfo).roleName);
        }
    }

    redirectTo = (hostname, role) => {
        const { history } = this.props;
        const queryParams = queryString.parse(this.props.location.search);
        // 存在自定义域名则跳转到自定义域名
        if (window.location.hostname !== hostname && !isEmpty(hostname) && window.location.hostname !== 'localhost') {
            window.location.href = `${window.location.protocol}//${hostname}/home`;
        } else {
            if (queryParams.redirect_url) {
                console.log('queryParams.redirect_url ',queryParams.redirect_url )
                window.location.href = (decodeURIComponent(queryParams.redirect_url));
                return;
            }
            window.location.href = '/home'
        }
    }

    render() {
        const { authStore } = this.props;
        const { userState } = authStore;
        return (
            <WelcomeWrap>
                <BgWrap src={require('../../assets/welcome/img-bg-1.png')} />
                <LogoWrap onClick={() => this.redirectTo()}>
                    <img src={require('../../assets/welcome/modeling-logo-w.png')} alt="Modeling.ai" />
                </LogoWrap>
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