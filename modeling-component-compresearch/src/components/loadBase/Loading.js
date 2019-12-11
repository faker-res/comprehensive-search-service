import React, { Component } from 'react';
import { Spin } from 'antd';
import './Loading.less';
import '../../common/common.less';
import Loading from '../Loading';

export default class Load extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  render() {
    const { isShow, content } = this.props;
    const antIcon = <Loading />;
    return (
      <div className={`center_middle loading_container`}>
        <Spin
          indicator={antIcon}
          className={`center_middle loading_content`}
          spinning={isShow}
          style={{ borderTop: '0' }}
        >
          {content || ''}
        </Spin>
      </div>
    );
  }
}
