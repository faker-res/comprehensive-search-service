import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import {
    message
} from 'antd';
import copy from 'copy-to-clipboard';
import QRCode  from 'qrcode-react';

import './index.scss';

@withRouter
class SharePopover extends Component {
	constructor(props) {
        super(props);
        
		this.state = {

        };

        this.handleRef = this.handleRef.bind(this);
    }
    
	componentWillMount() {

    }
    
    handleRef(e) {
        let {
            handleRef
        } = this.props;

        this.refSearchBar = e;

        handleRef && handleRef(e);
    }

	render() {
        let {
            className,
            shareText
        } = this.props;
        
		return (
			<div ref={this.handleRef} className={`abc-share-popover ${className}`}>
                <div className="share-content">
                    <div className="share-title">分享链接</div>
                    <input className="share-url-box" type="url" readOnly value={shareText} title={shareText} />
                    <div className="share-copy-button" onClick={() => {
                        copy(shareText);
                        message.info('复制成功');
                    }}>复制链接</div>
                </div>
                <div className="share-qrcode">
                    <div className="qrcode-box">
                        <div title={shareText}>
                            <QRCode value={shareText}
                                size={90}
                                logo={null}
                                logoWidth={0}
                                logoHeight={0}/>
                        </div>
                    </div>
                    <p className="qrcode-description">扫描二维码 分享到微信</p>
                </div>
			</div>
		);
	}
}

SharePopover.defaultProps = {
    language: 'zh-CN',
    className: '',
    handleRef: () => {},
    shareText: '' ||　window.location.href
};

SharePopover.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // ref回调
    handleRef: PropTypes.func,
    // 需要分享的内容
    shareText: PropTypes.string
};

export default SharePopover;