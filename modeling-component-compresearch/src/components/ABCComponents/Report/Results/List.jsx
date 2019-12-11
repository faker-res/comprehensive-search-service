import React, { Component } from 'react';
import { List } from 'antd';
import { withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import { inject, observer } from 'mobx-react';
import ListItem from './ListItem';
// import EmptyView from './EmptyView/';
import './list.scss';

@withRouter @inject('listStore') @observer
class MailList extends Component {
  constructor() {
    super();
    this.state = {
        dataset:{}
    };
    this.onScroll = this.onScroll.bind(this);
}
  static propTypes = {
    dataset: PropTypes.array,
    loading: PropTypes.bool,
    showLoadingMore: PropTypes.bool,
    onLoadMore: PropTypes.func,
    moreResults: PropTypes.bool
  }
  componentDidMount() {
    this.bindScrollLoad();
  }
  componentWillReceiveProps(newProps){
    
  }
  onScroll(event = {}) {
    let {
        scrollLoadDistance
    } = this.props;
    const { target: container = {} } = event;
    const { scrollingElement: target = {} } = container;
    // 计算滚动距离差
    let diff = target.scrollTop - (this._scrollTop || 0);
    let {scrollHeight, clientHeight, scrollTop, } = target;
    let {loadMore} = this.props;
    
    if (Math.abs(diff) >= 20) {
        // 每20px打点一次
        this._scrollTop = target.scrollTop;
    }
    // 计算是否需要触底加载
    if (diff > 0
        && scrollHeight <= clientHeight + scrollTop + scrollLoadDistance && loadMore) {
        
        this.props.onLoadMore();
        // 距底300px 开启自动加载
    }
  }

  bindScrollLoad() {
    let {
        scrollLoad,
        scrollLoadBox
    } = this.props;

    if (!scrollLoad) {
        return;
    }
    scrollLoadBox.addEventListener('scroll', this.onScroll);
  }

  unBindScrollLoad() {
    let {
        scrollLoad,
        scrollLoadBox
    } = this.props;
    
    if (!scrollLoad) {
        return;
    }
    // 移除滚动监听事件
    scrollLoadBox.removeEventListener('scroll', this.onScroll);
  }
  componentWillUnmount() {
    this.unBindScrollLoad();
  }
  render () {
    const { loading, dataset } = this.props;
    return (
      <div>
      <List
        className="mail-list"
        itemLayout="vertical"
        dataSource={dataset}
        renderItem={(item, index) => <ListItem item={item} index={index} {...this.props} />}
      >
            { loading 
              && 
              this.props.loadMore ? (
              <div className="abc-bottom-loading">
                <div className="loading-animate-line-scale">
                <div className="layui-layer-content">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
              </div>
          </div>
            ) : <div className="abc-bottom-loading">到底了</div>}
        </List>
      </div>
    );
  }
}
MailList.defaultProps = {
  scrollLoad: true,
  scrollLoadDistance: 300,
  scrollLoadBox: window.document,
};

MailList.propTypes = {
  scrollLoad: PropTypes.bool,
  // 滚动加载距离
  scrollLoadDistance: PropTypes.number,
  // 滚动加载监听容器
  scrollLoadBox: PropTypes.object,
};
export default MailList;