import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Modal
} from 'antd';
import { withRouter } from 'react-router-dom';

import './index.scss';

@withRouter
class FullScreen extends Component {
    constructor(props) {
        super(props);

        this.state = {

        };
    }

    render() {
        let {
            className,
            isShow,
            children = null
        } = this.props;

        return (
            <Modal
                closable={false}
                visible={isShow}
                destroyOnClose={false}
                footer={null}
                className={`abc-fullscrren-wrap ${className}`}>

                {children}
            </Modal>
        );
    }
}

FullScreen.defaultProps = {
    language: 'zh-CN',
    className: '',
    handleRef: () => {},
    isShow: false,
};

FullScreen.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // ref回调
    handleRef: PropTypes.func,
    // 是否全屏
    isShow: PropTypes.bool,
};

export default FullScreen;