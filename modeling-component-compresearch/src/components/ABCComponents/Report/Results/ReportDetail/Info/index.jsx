import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {

} from 'antd';
import { withRouter } from 'react-router-dom';
import classnames from 'classnames';
import EmptyView from './../../EmptyView/';
import BottomLoading from './../../BottomLoading/';

import { utils } from '../../../../../../lib/until';

import './index.scss';

@withRouter
class ReportInfo extends Component {
    constructor(props) {
        super(props);

        this.state = {

        };
    }

    render() {
        let {
            className,
            fileInfo,
            loading
        } = this.props;

        let {
            tags = [],
            from = {}
        } = fileInfo;

        return (
            <div className={`abc-report-detail-info ${className}`}>
                {
                    loading && <BottomLoading status={'loading'}/>
                }
                
                {
                    !loading && !Object.keys(fileInfo).length && <EmptyView />
                }

                {
                    !loading && Object.keys(fileInfo).length && (
                        <div className="JS-result d-tabs-content base-info-box clearfix">
                            <h3 className="content-title">{fileInfo.title || ''}</h3>
                            {
                                !!tags.length && (<div className="clearfix ">
                                    <span className="tag-tip">标签:</span>
                                        <ul className="tag-box">
                                            {
                                                tags.map((item, index) => {
                                                    return (<li key={index}>{item}</li>);
                                                })
                                            }
                                        </ul>
                                    }
                                </div>)
                            }

                            <p className="point-sub-title">研报信息</p>
                            <div className="report-info clearfix">
                                <ul className="info-list">
                                    <li><label>公司名称:</label><span className="commpany-name">{fileInfo.company || '--'}</span></li>
                                    <li><label>机构名称:</label><span className="code-name">{fileInfo.publish || '--'}</span></li>
                                    <li><label>分析师:</label><span className="analyst-user">{utils.getObjType(fileInfo.author) == 'array' ? fileInfo.author.join('、') : fileInfo.author || '--'}</span></li>
                                </ul>
                                <ul className="info-list">
                                    <li><label>所属行业:</label><span className="commpany-industry">{fileInfo.industry_name || '--'}</span></li>
                                    <li><label>报告类别:</label><span className="commpany-plate"><span target="_blank">{fileInfo.report_type || '--'}</span></span></li>
                                    <li><label>发件人:</label><span className="share-author">{from.name || '--'} {from.address || ''}</span></li>
                                </ul>
                                <ul className="info-list">
                                    <li><label>文件类型:</label><span className="file-type report-file-type">{(fileInfo.filetype || '--').replace(/\./g, '').toUpperCase()}</span></li>
                                    <li><label>文件页数:</label><span className="file-page">{fileInfo.file_pages || '--'}</span></li>
                                    <li><label>收件日期:</label><span className="pulish-date">{fileInfo.time ? utils.formatDate(new Date(fileInfo.time * 1000), 'YYYY/MM/DD') : '--'}</span></li>
                                </ul>
                            </div>
                        </div>
                    )
                }
            </div>
        );
    }
}

ReportInfo.defaultProps = {
    language: 'zh-CN',
    className: '',
    isShow: false,
    fileInfo: {},
    loading: true
};

ReportInfo.propTypes = {
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

export default ReportInfo;