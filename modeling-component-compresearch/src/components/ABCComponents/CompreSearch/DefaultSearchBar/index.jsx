/**
 * @description 默认搜索栏组件
 * @author jhqu
 * date: 2018-05-18
 */

import React, { Component } from 'react';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import queryString from 'query-string';
import { withRouter, Link } from 'react-router-dom';
import { Menu } from 'antd';

import Account from '../../../../containers/Layout/Account';
import SuggestedSearchBox from '../../SuggestedSearchBox';
// import LoginUserInfo from '../../components/LoginUserInfo';
// import LanguageToggle from '../../components/LanguageToggle';
// import ProductMenuButton from '../../components/ProductMenuButton';
import logo from '../../../../assets/image/logo-white-modeling.png';
// import { legacySiteOrigin } from '../../constants';

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
    this.state = {
      // 当前激活的导航菜单项
      currentNavItem: props.match.path.split('/').pop(),
      urlPara:this.props.location.search,
      inputValue : queryString.parse(props.location.search).keyword
    }
  }


  // 属性类型
  static propTypes = {
    // 当前激活的导航菜单项
    currentNavItem: PropTypes.string,
  }

  state = {
    // 当前激活的导航菜单项
    currentNavItem: this.props.currentNavItem,
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

    // 热门搜索映射
    // suggestStore.hotSearch.length > 0 && suggestions.default.push(
    //   SuggestedSearchBox.getNormalizedSuggestionData({
    //     sectionType: 'hotSearch',
    //     sectionTitle: t('DefaultSearchBar.hotSearchTitle', {defaultValue: '热门搜索'}),
    //     suggestions: suggestStore.hotSearch.toJS(),
    //     suggestionId: (suggestion) => `hotSearch-${suggestion.id}`,
    //     suggestionText: (suggestion) => suggestion.display_Name,
    //     suggestionQuery: (suggestion) => suggestion.query,
    //   })
    // );

    // 搜索建议映射
    // suggestStore.searchSuggest.length > 0 && suggestions.search.push(
    //   SuggestedSearchBox.getNormalizedSuggestionData({
    //     sectionType: 'searchSuggest',
    //     sectionTitle: t('DefaultSearchBar.searchSuggestTitle', {defaultValue: '相关搜索'}),
    //     suggestions: suggestStore.searchSuggest.toJS(),
    //     suggestionId: (suggestion) => `searchSuggest-${suggestion.id}`,
    //     suggestionText: (suggestion) => suggestion.content,
    //     suggestionQuery: (suggestion) => suggestion.content,
    //   })
    // );

    return suggestions;
  }


  render () {
    // const { t, match, userStore, suggestStore } = this.props;
    const { inputValue } = this.state;
    const { keyword } = queryString.parse(this.props.location.serach);
    const newKeyWord = inputValue || keyword;
    // const placeholderLabel = t('DefaultSearchBar.placeholderLabel', {defaultValue: '大家都在搜：'});
    // const placeholderQuery = suggestStore.hotKeyword.display_Name || '';
    // const placeholder = placeholderQuery ? `${placeholderLabel}${placeholderQuery}` : '';
    // const isLoadingUserInfo = userStore.state !== 'done';
    // const isShowComprehensive = userStore.userMenuShowItemKeys.includes('comprehensive');
    // const homePath = `/${match.params.lang || ''}`;
    const currentNavItem = this.state.currentNavItem || 'home';  // 默认home
    return (
      <section
       className={classNames('default-search-bar', this.props.className)}
       style={this.props.style}
      >
        <div className="default-search-bar__row-search">
          <div>
            <img className="default-search-bar__logo" src={logo} alt="ANALYST.AI" />
          </div>
          <SuggestedSearchBox
            className="default-search-bar__search-box"
            placeholder={"公司名称\关键字\作者"}
            defaultQuery={""}
            suggestions={this.getNormalizedSuggestions()}
            searchHistoryTitle={'最近搜过'}
            onRequestSearchSuggest={this.handleRequestSearchSuggest}
            onSearch={this.props.onSearch}
          />

          <div className="default-search-bar__side-actions">
            {false &&
              (<Link
                to={"/"}
                className="default-search-bar__side-actions-item default-search-bar__btn-home"
                title={'analyst首页'}
              >
                <i className="default-search-bar__icon-home"></i>
              </Link>)
            }

            {/* 临时隐藏语言切换选项 */}
            {/* <LanguageToggle className="default-search-bar__side-actions-item" /> */}

            {/* <ProductMenuButton
              className="default-search-bar__side-actions-item"
              loading={isLoadingUserInfo}
              showItemKeys={userStore.userMenuShowItemKeys}
            /> */}

            {/* <LoginUserInfo
              className="default-search-bar__side-actions-item"
              state={userStore.state}
              userInfo={userStore.userInfo}
              onLogout={this.handleLogout}
            /> */}
            {/* 泽畔用户菜单 */}
            <Account/>
          </div>
        </div>
        <div className="default-search-bar__row-nav">
          <Menu
            mode="horizontal"
            theme="light"
            className="default-search-bar__nav"
            selectedKeys={[currentNavItem]}
            onClick={this.handleNavItemChange}
          >
            <Menu.Item key="home" className="default-search-bar__nav-item">
              <Link to={`/home${newKeyWord ? `?keyword=${newKeyWord || ''}` : ''}`} replace={currentNavItem === 'home'}>
                {'综合'}
              </Link>
            </Menu.Item>
            <Menu.Item key="news" className="default-search-bar__nav-item">
              <Link to={`/news${newKeyWord ? `?keyword=${newKeyWord}` : ''}`} replace={currentNavItem === 'news'}>
                {'资讯'}
              </Link>
            </Menu.Item>
            <Menu.Item key="announcement" className="default-search-bar__nav-item">
              <Link to={`/announcement${newKeyWord ? `?keyword=${newKeyWord}` : ''}`} replace={currentNavItem === 'announcement'}>
                {'公告'}
              </Link>
            </Menu.Item>
            <Menu.Item key="report" className="default-search-bar__nav-item">
              <Link to={`/report${newKeyWord ? `?keyword=${newKeyWord}` : ''}`} replace={currentNavItem === 'report'}>
                {'研报'}
              </Link>
            </Menu.Item>
            
            {/* <Menu.Item key="dataModule" className="default-search-bar__nav-item">
              <Link to={`/dataModule${newKeyWord ? `?keyword=${newKeyWord}` : ''}`} replace={currentNavItem === 'dataModule'}>
                {'数据'}
              </Link>
            </Menu.Item>

            <Menu.Item key="chart" className="default-search-bar__nav-item">
              <Link to={`/chart${newKeyWord ? `?keyword=${newKeyWord}` : ''}`} replace={currentNavItem === 'chart'}>
                {'数据图'}
              </Link>
            </Menu.Item>

            <Menu.Item key="tableModule" className="default-search-bar__nav-item">
              <Link to={`/tableModule${newKeyWord ? `?keyword=${newKeyWord}` : ''}`} replace={currentNavItem === 'tableModule'}>
                {'数据表'}
              </Link>
            </Menu.Item> */}
              
            {/* <Menu.Item key="internal-report" className="default-search-bar__nav-item">
              <Link to={`/internal-report${newKeyWord ? `?keyword=${newKeyWord}` : ''}`} replace={currentNavItem === 'internal-report'}>
                {'内部研报'}
              </Link>
            </Menu.Item>
            <Menu.Item key="vender-report" className="default-search-bar__nav-item">
              <Link to={`/vender-report${newKeyWord ? `?keyword=${newKeyWord}` : ''}`} replace={currentNavItem === 'vender-report'}>
                {'外部研报'}
              </Link>
            </Menu.Item> */}
         
            {/* <Menu.Item key="activity" className="default-search-bar__nav-item">
              <Link to={`/activity${newKeyWord ? `?keyword=${newKeyWord}` : ''}`} replace={currentNavItem === 'activity'}>
                {'活动'}
              </Link>
            </Menu.Item> */}
          </Menu>
        </div>
      </section>
    );
  }
}
