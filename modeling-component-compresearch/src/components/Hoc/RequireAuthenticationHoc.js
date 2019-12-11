import React from 'react'
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';
import Cookies from 'js-cookie';
import Store from './../../store';

const RequireAuthenticationHoc = (Component) => {
  // 组件有已登陆的模块 直接返回 (防止从新渲染)
  if (Component.AuthenticatedComponent) {
    return Component.AuthenticatedComponent
  }

  // 创建验证组件
  class AuthenticatedComponent extends React.Component {

    componentWillMount() {
      this.checkAuth();
    }

    componentWillReceiveProps(nextProps) {
      this.checkAuth();
    }

    checkAuth() {
      // 判断登陆
      const isLogin = Cookies.get('isLogin');
      // 未登陆重定向到登陆页面
      if (!isLogin) {
        let redirect = this.props.location.pathname + this.props.location.search;
        this.props.history.push('/login?redirect_uri=' + encodeURIComponent(redirect));
        return;
      }
    }

    render() {
      const isLogin = Cookies.get('isLogin');
      if (isLogin) {
        return <Component {...this.props} />
      }
      return null;
    }
  }

  Component.AuthenticatedComponent = withRouter(AuthenticatedComponent)
  return Component.AuthenticatedComponent;
}

export default RequireAuthenticationHoc;
