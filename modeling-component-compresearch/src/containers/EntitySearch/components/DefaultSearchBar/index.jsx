import React, { Component } from 'react';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import queryString from 'query-string';
import { withRouter, Link } from 'react-router-dom';
import { Menu } from 'antd';
import Account from '../../../../containers/Layout/Account';
import logo from '../../../../assets/image/logo-white-modeling.png';

import './style.scss';

// 域名链接到主站线上正式(重构临时方案)
// const origin = legacySiteOrigin;

@withRouter
// @translate()
// @observer
export default class DefaultSearchBar extends Component {
  // 默认属性
  constructor(props){
    super(props);
  }
  componentDidMount () {
    // 获取用户信息数据
    // this.props.userStore.fetchUserInfo();

    // 获取预制热词（搜索框placeholder）
    // this.props.suggestStore.fetchHotKeyword();

    // 获取热门搜索列表
    // this.props.suggestStore.fetchHotSearch();
  }

  // TODO 注释By hhxu， 不清楚用途(啥时候会更新keyword和tab选项)
  componentWillReceiveProps(nextProps){
    const {location} = nextProps;
    const search = queryString.parse(location.search);
    this.setState({inputValue : search.keyword, currentNavItem: nextProps.match.path.split('/').pop()}, () => {
      if (document.scrollingElement) {
        document.scrollingElement.scrollTop = 0;
      } else {
        document.documentElement.scrollTop = 0;
      }
    });
  }

  // 退出登录处理
  handleLogout = () => {
    // window.sso_logout && window.sso_logout();
  }

  // 导航菜单项切换
  handleNavItemChange = ({key}) => {
    this.setState({currentNavItem: key});
  }

  // 获取搜索建议
  handleRequestSearchSuggest = (keyword) => {
    this.setState({inputValue : keyword});
    // this.props.suggestStore.fetchSearchSuggest({keyword});
  }

  // 获取标准化的建议数据
  getNormalizedSuggestions () {
    // const { t, suggestStore } = this.props;
    const suggestions = {
      default: [],
      search: [],
    };
    return suggestions;
  }


  render () {
    return (
      <section
       className={classNames('entity-search-bar', this.props.className)}
       style={this.props.style}
      >
        <div className="entity-search-bar__row-search">
          <div>
            <img className="entity-search-bar__logo" src={logo} alt="ANALYST.AI" />
            <span className="entity-search-bar_title">{this.props.currentAsideName}</span>
          </div>

          <div className="entity-search-bar__side-actions">
            {false &&
              (<Link
                to={"/"}
                className="entity-search-bar__side-actions-item entity-search-bar__btn-home"
                title={'analyst首页'}
              >
                <i className="entity-search-bar__icon-home"></i>
              </Link>)
            }

            {/* 临时隐藏语言切换选项 */}
            {/* <LanguageToggle className="entity-search-bar__side-actions-item" /> */}

            {/* <ProductMenuButton
              className="entity-search-bar__side-actions-item"
              loading={isLoadingUserInfo}
              showItemKeys={userStore.userMenuShowItemKeys}
            /> */}

            {/* <LoginUserInfo
              className="entity-search-bar__side-actions-item"
              state={userStore.state}
              userInfo={userStore.userInfo}
              onLogout={this.handleLogout}
            /> */}
            {/* 泽畔用户菜单 */}
            <Account/>
          </div>
        </div>
      </section>
    );
  }
}
