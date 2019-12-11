import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Icon,
    Popover
} from 'antd';
import { withRouter } from 'react-router-dom';
import PDFViewer from './../../FileViewer/PDF/';
import HTMLViewer from './../../FileViewer/HTML/';
import EmptyView from './../../EmptyView/';
import SharePopover from './../../SharePopover/';
import BottomLoading from './../../BottomLoading/';
// import { getAttachReportDetail } from '../../../../config/attach'
import ask from '../../../../../../lib/ask';
import './index.scss';
import { attach_data } from './mock';
import { attach_data2 } from './mock2';
import queryString from 'query-string';

@withRouter
class ReportViewer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            fileType: 0,
            fileViewUrl: '',
            fileCanView: true,
            // 当前预览的研报ID
            viewId: '',
            // 分享面板是否可见
            shareVisible: false,
            loadStatus: 'pending'
        };

        this.handleShareVisibleChange = this.handleShareVisibleChange.bind(this);
    }

    handleShareVisibleChange(visible) {
        this.setState({
            shareVisible: visible
        });
    }
    componentDidMount() {
        const queryParams = queryString.parse(this.props.location.search);
        console.log(this.props.location.pathname.lastIndexOf("/"))
        if (queryParams.from === 'report') {
            ask('getAnalystReport', {
                params: {
                    // report_id: 52114366,
                    // userId: '80114542210289264',
                    // token: '$2a$10$XskRlwU/zc/mFbGsW9gVPO580mONPa0',
                    // request_id: '92b112e6-19d5-414c-b9ac-5f6a0fd1d822',
                    report_id: this.props.location.pathname.substring(this.props.location.pathname.lastIndexOf("/") + 1)
                }
            }).then((resp) => {
                // resp=attach_data2
                if ((resp && resp.message === 'success') || (resp && resp.code === 200)) {
                    this.setState({
                        loadStatus: 'done',
                        fileType: resp.data.file_type === 'pdf' ? 2 : 0,
                        file_suffix: resp.data.file_type === 'pdf' ? '.pdf' : '',
                        fileViewUrl: resp.data.url,
                        fileCanView: 'true'
                    });
                } else {
                    this.setState({ loadStatus: 'error' });
                }
            })
        }
    }
    async componentWillReceiveProps(props) {
        if (!props.fileInfo.id || this.state.viewId === props.fileInfo.id) {
            return;
        }
        this.setState({
            viewId: props.fileInfo.id
        });
        this.setState({ loadStatus: 'pending' });
        let respData = {};
        const queryParams = queryString.parse(this.props.location.search);
        if (queryParams.from /*&& queryParams.from !== 'news' || queryParams.from !== 'notice'*/) {
            respData = await ask('ReportDetailForTask', {
                params: {
                    record_id: props.fileInfo.id,
                    source: queryParams.from
                }
            });
        } else if (!queryParams.from) {
            respData = await ask('ReportDetailgetAttachReportDetail', {
                data: {
                    report_id: props.fileInfo.id,
                }
            });
        }
        if ((respData && respData.success) || (respData && respData.code === 200)) {
            this.setState({
                loadStatus: 'done',
                fileType: respData.data.file_type,
                file_suffix: respData.data.file_type === 1 ? '.xls' : respData.data.file_type === 2 ? '.pdf' :
                    respData.data.file_type === 3 ? '.png' : respData.data.file_type === 4 ? '.doc' : respData.data.file_type === 5 ? '.txt' :
                        respData.data.file_type === 6 ? '.html' : '',
                fileViewUrl: respData.data.file_url,
                fileCanView: respData.data.preview
            });
        } else {
            this.setState({ loadStatus: 'error' });
        }
    }
    render() {
        
        let {
            className,
            fileInfo = {},
            loading,

        } = this.props;
        let {
            shareVisible,
            fileCanView,
            fileType,
            fileViewUrl,
            file_suffix,
            loadStatus
        } = this.state;
        // download key
        let downloadKey = (((fileViewUrl || '').match(/[^\?\/]+\?/) || [])[0] || '').replace(/\?/, '');
        // console.log('loading', loading, 'fileType', fileType, 'fileCanView', fileCanView,'fileViewUrl',fileViewUrl,'file_suffix',file_suffix)
        return (
            <div className={`abc-report-detail-viewer ${className}`}>
                <div className={'left-side-bar'}>
                    <div className={'left-sidebar-wrap'}>
                        <ul className="left-sidenav" id="JS-leftnav">
                            <li>
                                <div className="ui-share-container icon-style align-bottom-left">
                                    <a href={`/search-api/download/report?file_key=${downloadKey}&file_name=${fileInfo.title}&file_type=${file_suffix}`} target="_blank">
                                        <Icon type="download" />
                                        <div className="share-button">下载</div>
                                    </a>
                                </div>
                            </li>
                            <li hidden>
                                <Popover
                                    placement={'bottomRight'}
                                    content={<SharePopover />}
                                    trigger="click"
                                    visible={shareVisible}
                                    onVisibleChange={this.handleShareVisibleChange} >

                                    <div className="ui-share-container icon-style align-bottom-left">
                                        <Icon type="share-alt" />
                                        <div className="share-button">分享</div>
                                    </div>
                                </Popover>
                            </li>
                        </ul>
                    </div>
                </div>
                {!loading && fileType === 6 && <HTMLViewer url={fileViewUrl} />}

                {!loading && fileType !== 6 && fileCanView && fileType !== 2 && <HTMLViewer url={fileViewUrl} />}

                {
                    !loading && fileCanView && fileType === 2 && <PDFViewer fileUrl={fileViewUrl}
                        landScape={false}
                        onError={() => {
                            console.log('onError');
                        }}
                        onProgress={() => {
                            console.log('onProgress');
                        }}
                        onFinish={() => {
                            console.log('onFinish');
                        }}
                        onScroll={() => {
                            console.log('onScroll');
                        }} />
                }

                {
                    !loading && loadStatus === 'error' && <EmptyView />
                }

                {
                    loading && <BottomLoading status={'loading'} />
                }
            </div>
        );
    }
}

ReportViewer.defaultProps = {
    language: 'zh-CN',
    className: '',
    isShow: false,
    fileInfo: {},
    loading: true
};

ReportViewer.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // 是否可见
    isShow: PropTypes.bool,
    // 文件基本信息
    fileInfo: PropTypes.object,
    // 是否正在加载
    loading: PropTypes.bool
};

export default ReportViewer;