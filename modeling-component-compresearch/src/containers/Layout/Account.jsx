import React, { Component } from 'react';
import { Avatar, Popover, Button, Icon } from 'antd';
import { Link } from 'react-router-dom';
import classnames from 'classnames';
import Cookies from 'js-cookie';
import './index.scss';
import { inject, observer } from 'mobx-react';
import { toJS } from 'mobx';
import isEmpty from 'lodash/isEmpty';
import { ROLE_RESEARCHER, ROLE_FUNDMANAGER, ROLE_DIRECTOR, ROLE_RISKOFFICER } from '../../lib/constants';

const AccountWrapper = (props) => {
  const nameClass = classnames(['account__name'], {
    'account__name--special': !!props.type,
  });
  const titleClass = classnames(['account__title'], {
    'account__title--special': !!props.type,
  });
  const { avatar, userName, deptName, roleName } = props;
  return (
    <div className="account">
      <Avatar size="large" className="account__avatar" src={avatar} />
      <div className="account__desc">
        <div className={nameClass}>{userName}</div>
        <div className={titleClass} style={{ color: 'rgba(255, 255, 255, 0.65)' }} hidden={!deptName && !roleName}>{deptName} | {roleName}</div>
      </div>
    </div>
  );
};

const AccountPopover = ({ avatar, userName, deptName, roleName, onLogout,goSetting }) => (
  <div className="account__popover">
    <AccountWrapper type avatar={avatar} userName={userName} deptName={deptName} roleName={roleName}/>
    <ul className="account__list">
      {/* <li className="account__list-item">
        <Icon type="user" />个人资料
      </li>
      <li className="account__list-item">
        <Icon type="star-o" />我的收藏
      </li>
      <li className="account__list-item">
        <Icon type="inbox" />我的订阅
      </li>
      <li className="account__list-item">
        <Icon type="switcher" />我的资源库
      </li>
      <li className="account__list-item">
        <Icon type="setting" />系统管理
      </li> */}
      {/* <li className="account__list-item" onClick={goSetting}>
      <Icon type="setting" theme="outlined" />同步设置
      </li> */}
      <li className="account__list-item" onClick={onLogout}>
        <Icon type="logout" />退出登录
      </li>
    </ul>
  </div>
);

@inject('authStore')
@observer
class Index extends Component {
  constructor(props) {
    super(props);
    this.state = {
      avatar: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532680772728&di=1d61f9c4cfee9cb22cb8ae5cb3227c21&imgtype=0&src=http%3A%2F%2Fassets-glassx.qiniudn.com%2Fuploads%2Fuser%2Favatar%2F3570%2Fblue.png',
    };
  }

  componentDidMount() {
    const { authStore } = this.props;
    const { userInfo, userState } = authStore;
    if (userState === 'none' && isEmpty(toJS(userInfo))) {
        authStore.getUserInfo();
    }
  }

  handleLogout = () => {
    window.sso_logout && window.sso_logout();
  }
  handleSetting=()=>{
    window.location.href = '/emailSetting'
  }
  render() {
    const { avatar } = this.state;
    const { authStore } = this.props;
    const { userInfo, userState } = authStore;
    const _userInfo = toJS(userInfo);
    const _roleName = _userInfo.roles && _userInfo.roles[0] ? _userInfo.roles[0].name : '';
    localStorage.setItem('user_name', _userInfo.nickname)
    return (
      <Popover style={{ position: "fixed" }} placement="bottom" className='ava'
        content={
          <AccountPopover
              avatar={_userInfo.avatar || avatar}
              userName={_userInfo.nickname}
              deptName={_userInfo.department}
              roleName={_roleName}
              onLogout={() => this.handleLogout()}
              goSetting={() => this.handleSetting()}
              />
        }>
        <Button type="primary" size="small" style={{ display: 'none' }} />
        <AccountWrapper
          avatar={_userInfo.avatar || avatar}
          userName={_userInfo.nickname}
          deptName={_userInfo.department}
          roleName={_roleName}/>
      </Popover>
    );
  }
}

export default Index;
