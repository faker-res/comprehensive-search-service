import React, { Component } from 'react';
import { BackTop } from 'antd';
import { withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import ask from '../../../../../../lib/ask'
import moment from 'moment';
import {utils} from '../../../../../../lib/until'
import queryString from 'query-string';

import './index.scss';
@withRouter
class HtmlViewer extends Component {
    constructor(props) {
        super(props);
        let pathname = this.props.location.pathname
        let index = pathname.lastIndexOf("\/");
        pathname = pathname.substring(index + 1, pathname.length)
        const queryParams = queryString.parse(this.props.location.search);
        // if(!!queryParams.from){
        //     const pathType =queryParams.from
        // }
        this.state = {
            notice_title: {},
            pathname,
            pathType:queryParams.from ,
            newscontent: {},
            // loading:true
        }
        // console.log(this.state.pathType)
    }
    componentDidMount() {
        const { pathname, pathType } = this.state
        // console.log(pathname,pathType)
        if (pathType === 'notice') {
            ask('getNoticeHtml', {
                params: { noticeId: pathname, inlineContent: true }
            }).then((resp) => {
                let { data = {} } = resp;
                this.setState({
                    notice_html: data.body,
                    // loading:!!data.body
                })
            })
                .catch(() => {
                });
            ask('getNoticeTitle', {
                params: { noticeId: pathname }
            }).then((resp) => {
                let { code,data = {} } = resp;
                if (code === 200){
                    this.setState({
                        notice_title: resp.data
                    })
                } else {
                this.setState({
                    // loading:false
                })
                }
            })
                .catch(() => {
                });
        } else if (pathType === 'news') {
            ask('getNewsContent', {
                params: {
                    articleUrl: pathname,
                    page: 'news_search_result',
                    // userId: '80114542210289264',
                    // user_id: '80114542210289264',
                    // token: '$2a$10$hPcGU1dZ7aPIMjb31Pa5yez6uj4Qb7u',
                    // request_id: '0f562862-16ea-40be-8079-f4f7f4c2ed6f',
                    // device_info: 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99Safari/537.36'
                }
            }).then((resp) => {
                let { code,data = {} } = resp;
                let period_time = utils.periodFormat(resp.data.crawlTime)
                if (code === 200){
                    this.setState({
                        newscontent: resp.data,
                        period_time:period_time
                    })
                } else {
                    this.setState({
                        // loading:false
                    })
                    }
            })
                .catch(() => {
                });
        }
    }
    render() {
        let {
            url,
            className,
        } = this.props;
        const { notice_html, notice_title, pathType, newscontent,period_time } = this.state
        // console.log(pathType)
        return (
            <div className={`abc-html-viewer ${className}`} className="html-container ">
                {/* <iframe title="" style={{ width: '100%', height: '100%' }} src={url} >
                    <BackTop />
                </iframe> */}
                {pathType === 'notice' && <div className='html-outer'><div className='main-content'>
                    <div className='page-container' id='html-container'>
                        {!!notice_title.title && <div className='html-header small'>
                            <h1 className='html-title'>
                                <span>{notice_title.title}</span>
                            </h1>
                            <div className='html-baseinfo clearfix'>
                                <div className='file-baseinfo'>
                                    {!!notice_title.industry && <span>{notice_title.industry}</span>}
                                    {!!notice_title.category && <span>{notice_title.category}</span>}
                                    {!!notice_title.file_type && <span>{notice_title.file_type.toUpperCase()} {notice_title.page_count}页</span>}
                                    {!!notice_title.publish_at && <span>{moment(notice_title.publish_at).format('YYYY-MM-DD')}</span>}
                                </div>
                            </div>
                        </div>}
                        {!!notice_html && <div className='html-pagewrap'>
                            <div className='html-page'>
                                <div className='marker-wrap' dangerouslySetInnerHTML={{ __html: notice_html }}></div>
                            </div>
                        </div>}
                    </div>
                </div></div>}
                {pathType === 'news' &&
                    <div className='xui-detail-container'>
                        <div className='JS-news-detail news-detail'>
                            {!!newscontent.title && <h1>{newscontent.title}</h1>}
                            <div className='news-info'>
                                <span className='source'>{newscontent.source}</span>
                                <span className='time'>{period_time}</span>
                            </div>
                            {!!newscontent.title && <div className='news-summary'>
                            {newscontent.title}
                            </div>}
                            <div className='news-content' dangerouslySetInnerHTML={{ __html: newscontent.content }}></div>
                        </div>
                    </div>
                }
            </div>
        );
    }
}

HtmlViewer.defaultProps = {
    language: 'zh-CN',
    className: '',
};

HtmlViewer.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // 预览地址
    url: PropTypes.string
};

export default HtmlViewer;