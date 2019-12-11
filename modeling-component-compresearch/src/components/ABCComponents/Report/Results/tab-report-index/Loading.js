import React, { Component } from 'react';
import { Spin } from 'antd';
import './Loading.less';
// import '../../common/common.less';
import loading from '../../../assets/image/icons/loading1.gif';

export default class Loading extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  render() {
    const { isShow, content } = this.props;
    const antIcon = (
      <div className={'center_middle'} style={{ width: '120px' }}>
        <img src={loading} alt={loading} style={{ width: '25px' }} />
        <span style={{ fontSize: '12px', marginLeft: '15px', color: '#333333' }}>加载结果</span>
      </div>
    );
    return (
      <div className={`center_middle loading_container`}>
        <Spin
          indicator={antIcon}
          className={`center_middle loading_content`}
          spinning={isShow}
          style={{ borderTop: '0' }}
        >
          {/* {content || ''} */}
        </Spin>
      </div>
    );
  }
}
