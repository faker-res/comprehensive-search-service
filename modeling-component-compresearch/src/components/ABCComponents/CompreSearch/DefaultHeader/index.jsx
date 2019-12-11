/**
 * @description 默认头部组件
 * @author jhqu
 * date: 2018-05-18
 */

import React, { Component } from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { Helmet } from 'react-helmet';
import _ from 'lodash';
import DefaultSearchBar from '../DefaultSearchBar';
import './style.scss';

export default class DefaultHeader extends Component {
  // 默认属性
  static defaultProps = {
    // 头部组件是否固定在页面顶部，默认固定
    fixed: true,
  }

  // 属性类型
  static propTypes = {
    fixed: PropTypes.bool,
  }

  render () {
    const { fixed } = this.props;
    const className = classNames(
      'default-header',
      {'default-header--fixed': fixed},
      this.props.className,
    );

    // 添加到body元素上的固定头部状态类
    const bodyClass = classNames(
      _.uniq(document.body.className.split(' ').concat('has-fixed-header')),
    );

    return (
      <header className={className} style={this.props.style}>
        {fixed && <Helmet><body className={bodyClass} /></Helmet>}
        <DefaultSearchBar className="default-header__item" onSearch={this.props.onSearch} />
      </header>
    );
  }
}
