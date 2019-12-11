import React, { Component } from 'react';
import { Spin } from 'antd';
import './index.scss';
import '../../theme/common.less';

export default class Loading extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  render() {
    const { isShow, content, type, height } = this.props;
    const antIcon = (
      <div className={'center_middle loading_icon_container'}>
        {
          type == 'list' && (
            <div className='loading_icon_list'>
              <span className='spin-dot spin-dot-spin'>
                <i></i>
                <i></i>
                <i></i>
                <i></i>
              </span>
              <div style={{ fontSize: '14px', marginTop: '15px', color: '#666666' }}>正在加载</div>
            </div>
          )
        }
        {
          (!type || type == 'default') && (
            <div className='loading_icon_default'>
              <span className='spin-dot spin-dot-spin'>
                <i></i>
                <i></i>
                <i></i>
                <i></i>
              </span>
            </div>
          )
        }

      </div>
    );
    return (
      <div className={`center_middle loading_container`} style={{ minHeight: `${height ? height : '200px'}`, background: 'white' }}>
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
