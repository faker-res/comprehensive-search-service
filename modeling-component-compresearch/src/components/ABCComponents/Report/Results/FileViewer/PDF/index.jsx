import React, { Component } from 'react';
import { Progress } from 'antd';
import PropTypes from 'prop-types';
import _ from 'lodash';

import './index.scss';

class PdfViewer extends Component {
    constructor(props) {
        super(props);

        this.state = {
            rendered: false,
            progress: 0,
        };

        this.onPDFMessage = this.onPDFMessage.bind(this);
    }
    
    componentDidMount() {
        window.addEventListener('message', this.onPDFMessage);
    }

    componentWillUnmount() {
        window.removeEventListener('message', this.onPDFMessage);
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.fileUrl !== this.props.fileUrl) {
            this.setState({ rendered: false });
        }
        if (nextProps.landScape != this.props.landScape) {
            let ifWind = document.getElementById('abc-pdf-viewer-reader').contentWindow;
            if (ifWind) {
                ifWind.postMessage({
                    eventType: 'scalechanged',
                    value: 'page-width'
                }, window.location.href);
            }
        }
    }

    onPDFMessage(event) {
        if (event.data.eventType === 'textlayerrendered'
            || event.data.eventType === 'rendererror') {
            this.setState({ rendered: true });
            if (event.data.eventType === 'rendererror') {
                this.props.onError();
            } else {
                this.props.onFinish();
            }
        } else if (event.data.eventType === 'progress') {
            this.props.onProgress(event.data.loaded, event.data.total);
            this.setState({ progress: event.data.loaded / event.data.total });
        } else if (event.data.eventType === 'pagerendered') {
        } else if (event.data.eventType === 'scroll') {
            this.props.onScroll(event.data.direction);
        }
    }

    render() {
        console.log(11111)
        let {
            className,
            fileUrl,
        } = this.props;
        let { rendered, progress } = this.state;

        let _url = /^https/.test(fileUrl) ? fileUrl : fileUrl.replace('http', 'https');
        // let {protocol = '', port = '', hostname = ''} = location;
        // _url = protocol + "//" + hostname + "/resfile" + _url.replace(/https?:\/\/[^\/]+/, "");
        let _fileUrl = '/static/vendor/pdfjs/1.10/web/viewer.html?file=' + encodeURIComponent(_url) + '&v=1823432523532';
        return (
            <div className={`abc-pdf-viewer ${className}`} id="pdfContainer">
                {!rendered && <Progress percent={progress * 100} showInfo={false} status="active"/>}
                {
                    !_.isEmpty(fileUrl) &&
                    <iframe id="abc-pdf-viewer-reader" name="abc-pdf-viewer-reader" src={_fileUrl}></iframe>
                }
            </div>
        );
    }
}

PdfViewer.defaultProps = {
    language: 'zh-CN',
    className: '',
    fileUrl: '',
    onError: () => {},
    onProgress: () => {},
    onFinish: () => {},
    onScroll: () => {}
};

PdfViewer.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // 预览地址
    fileUrl: PropTypes.string,
    // 
    landScape: PropTypes.bool,
    // 异常信息
    onError: PropTypes.func,
    // 加载进度
    onProgress: PropTypes.func,
    // 页面加载完成
    onFinish: PropTypes.func,
    // 滚动
    onScroll: PropTypes.func,
};

export default PdfViewer;