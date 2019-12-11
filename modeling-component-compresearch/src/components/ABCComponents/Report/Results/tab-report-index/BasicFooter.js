import React, { Component } from 'react';
import './BasicFooter.less';
// import '../../common/common.less';

export default class BasicFooter extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  render() {
    return (
      <div className={`footer_container space_between middle`}>
        <div className={'footer_nva'}>
          <a>首页</a>|
          <a>关于我们</a>|
          <a>联系我们</a>|
          <a>使用条款</a>
        </div>
        <span className={'footer_company'}>Copyright © 2016-2017&nbsp;&nbsp;&nbsp;北京阿博茨科技有限公司</span>
      </div>
    );
  }
}
