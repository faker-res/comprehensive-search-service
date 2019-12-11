import React, { Component } from 'react';
// import { Layout, List, BackTop, Icon } from 'antd';
import { inject, observer } from 'mobx-react';
import { withRouter } from 'react-router-dom'
import { Divider, Popover } from 'antd';
import './Honor.less';
// import '../../common/common.less';
import honorImg from '../../../../../assets/image/new-report/icon_honor.png';
import jinniu from '../../../../../assets/image/new-report/icon_jinniu.png';
import xincaifu from '../../../../../assets/image/new-report/icon_xincaifu.png';

const honorObj = {
  '2017新财富最佳分析师': xincaifu,
  '2017中国证券业分析师金牛奖': jinniu,
};

@withRouter
@inject('honorStore')
@observer
export default class Honor extends Component {
  getContent = () => {
    const { honorDetail } = this.props.honorStore.honorState;
    return (
      <div className={'honor_content'}>
        <div>{honorDetail.name}</div>
        <p><span>{honorDetail.organ}</span>|<span>{honorDetail.direction}</span></p>
        <Divider type="horizontal" style={{ margin: '15px 0 5px 0' }} />
        {
          (honorDetail.honor || []).map((honor) => {
            return (
              <div className={`middle honor_type`}>
                <img src={this.getHonor(honor)} alt="img" />
                {honor.honor}
              </div>
            );
          })
        }
      </div>
    );
  }
  getHonor = (honor) => {
    if (honor.honor.indexOf('新财富') > -1) return xincaifu;
    if (honor.honor.indexOf('金牛奖') > -1) return jinniu;
  }
  handleVisible = (visible) => {
    const { id } = this.props;
    if (visible) {
      this.props.fetchHonorDetail(
        'honor/fetchHonorDetail',
        { id }
      );
    }
  }
  render() {
    return (
      <div className={`honor_container`}>
        <Popover placement="bottomLeft" overlayClassName='honor_detail_container' content={this.getContent()} onVisibleChange={this.handleVisible}>
          <img src={honorImg} alt={honorImg} className={`cursor_pointer honor_img`} />
        </Popover>
      </div>
    );
  }
}
