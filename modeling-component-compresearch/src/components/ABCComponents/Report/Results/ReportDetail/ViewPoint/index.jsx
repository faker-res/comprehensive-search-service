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
class ReportViewPoint extends Component {
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

        return (
            <div className={`abc-report-detail-viewpoint ${className}`}>
                <div className={'base-info-box'}>
                    {
                        loading && <BottomLoading status={'loading'}/>
                    }
                    {
                        !loading && !Object.keys(fileInfo).length && <EmptyView />
                    }
                    {
                        !loading && !!Object.keys(fileInfo).length && (
                            <React.Fragment>
                                <ul className="grade-box">
                                    <li>投资评级：<span className="grade-text">{fileInfo.rating || '--'}</span></li>
                                    <li>评级机构：<span>{fileInfo.source || '--'}</span></li>
                                    <li>时间：<span>{fileInfo.time ? utils.formatDate(new Date(fileInfo.time * 1000), 'YYYY/MM/DD HH:mm:ss') : '--'}</span></li>
                                </ul>

                                <EmptyView />
                            </React.Fragment>
                        )
                    }
                </div>
            </div>
        );
    }
}

ReportViewPoint.defaultProps = {
    language: 'zh-CN',
    className: '',
    isShow: false,
    fileInfo: {},
    loading: true
};

ReportViewPoint.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // 是否可见
    isShow: PropTypes.bool,
    // 文件基本信息
    fileInfo: PropTypes.object,
    // 是否在加载
    loading: PropTypes.bool
};

export default ReportViewPoint;