import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Spin
} from 'antd';
import { withRouter } from 'react-router-dom';

import './index.scss';

// 加载中
const STATUS_LOADING   = 'loading';
// 加载失败
const STATUS_LOADFAIL  = 'fail';
// 加载更多
const STATUS_LOAD_MORE = 'load_more';
// 全部加载完成
const STATUS_LOADED    = 'loaded';
// 自定义
const STATUS_CUSTOME   = 'custom';

@withRouter
class BottomLoading extends Component {
    constructor(props) {
        super(props);

        this.state = {
        };

        this.onClickItem = this.onClickItem.bind(this);
    }

    onClickItem() {
        let {onClick, status} = this.props;

        if (onClick) {
            onClick(status);
        }
    }

    render() {
        let {
            className,
            status,
            text
        } = this.props;

        return (
            <div className={`abc-bottom-loading ${className}`} onClick={this.onClickItem}>
                
                {status === STATUS_LOADING && (
                    <div className="loading-animate-line-scale">
                        <div className="layui-layer-content">
                            <div></div>
                            <div></div>
                            <div></div>
                            <div></div>
                            <div></div>
                        </div>
                    </div>
                )}
                {status === STATUS_LOADFAIL && '加载失败 :('}
                {status === STATUS_LOAD_MORE && '加载更多'}
                {status === STATUS_LOADED && '到底了'}
                {status === STATUS_CUSTOME && text}
            </div>
        );
    }
}

BottomLoading.defaultProps = {
    language: 'zh-CN',
    className: '',
    handleRef: () => {},
    data: {},
    highlight: ''
};

BottomLoading.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // ref回调
    handleRef: PropTypes.func,
    // 研报数据
    data: PropTypes.object,
    // 需要高亮的关键词
    highlight: PropTypes.string,
};

export default BottomLoading;