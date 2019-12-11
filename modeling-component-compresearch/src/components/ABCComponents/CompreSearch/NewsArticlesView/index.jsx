/**
 * @description 新闻消息卡片，适用于“上市公司”实体、“基金”实体、
 * “基金公司”实体、“基金经理”实体、“分析师”实体、“行业”实体、“主题”实体的“新闻消息”组件
 * @author kygeng
 * date: 2018-06-20
 */
import React, { Component } from 'react';
import classNames from 'classnames';
import { Row } from 'antd';
import PropTypes from 'prop-types';
import compact from 'lodash/compact';
import NewsListCard from '../NewsListCard';
import Loading from './../General/Loading';
import ask from '../../../../lib/ask';
import {search,notice,report} from './mock'
import './style.scss';

const preFixCls = 'abcft-news-articles-view';

export default class NewsArticlesView extends Component {

    static defaultProps = {
        // 实体类型：
        type: '',
        // 实体关键字
        keyword: ''
    }

    static propTypes = {
        type: PropTypes.string,
        keyword: PropTypes.string.isRequired
    }

    constructor(props) {
        super(props);
        // console.log(report)
        this.state = {
            loadStatus: 'pending', // done, error
            news: [],
            notices: [],
            reports: []
        }
    }

    componentDidMount () {
        Promise.all([this._loadNews(), this._loadNotices(), this._loadReports()])
            .then((resp) => {
                if (compact(resp).length === 0) {
                    throw new Error(`Response Exception: fetch data failed`);
                }
                this.setState({
                    loadStatus: 'done',
                    news: resp[0] || [],
                    notices: resp[1] || [],
                    reports: resp[2] || []
                });
            })
            .catch((error) => {
                console.error(error);
                this.setState({ loadStatus: 'error' });
            })
    }
    

    _loadNews = () => {
        const { keyword } = this.props;

        let params = {
            keyword: keyword,
            offset: 0,
            limit: 6,
            prior: '',
            channel: '',
            device_info: 'UA'
        }

        return ask('NewsEntitySearch', { params: params })
            .then((resp) => {
                // resp=search
                const { code, data, message } = resp;
                if (code !== 200) {
                    throw new Error(`Response Exception: ${message};code: ${code}`);
                }
                return Promise.resolve(data.slice(0,6));
            })
            .catch((error) => {
                return Promise.resolve(null);
            });
    }

    _loadNotices = () => {
        const { keyword } = this.props;
        // console.log(keyword)
        let params = {
            keyword: keyword,
            offset: 0,
            limit: 6,
            startTime: '1990-01-01'
        }

        return ask('NoticesEntitySearch', { params: params })
            .then((resp) => {
                // resp=notice
                const { code, data, message } = resp;
                if (code !== 200) {
                    throw new Error(`Response Exception: ${message};code: ${code}`);
                }
                return Promise.resolve(data.items);
            })
            .catch((error) => {
                return Promise.resolve(null);
            });
    }

    _loadReports = () => {
        const { keyword } = this.props;

        let params = {
            keyword_filter: keyword,
            offset: 0,
            limit: 6,
            order_by: 'all_score',
            group_by: 'default'
        }

        return ask('ReportsEntitySearch', { params: params })
            .then((resp) => {
                // let resp=report
                const { code, data, message } = resp;
                if (code !== 200) {
                    throw new Error(`Response Exception: ${message};code: ${code}`);
                }
                return Promise.resolve(data.items);
            })
            .catch((error) => {
                return Promise.resolve(null);
            });
    }

    handleMore = (type) => {
        const { keyword } = this.props;
        if (type === 'news') {
            window.open(`/comprehensive-search/news?id=${keyword}`, '_blank');
            // window.open(`/report-detail/article/${id}?from=news`);
        } else if (type === 'notice') {
            window.open(`/comprehensive-search/notice?keyword=${keyword}`, '_blank');
        } else if (type === 'report') {
            window.open(`/comprehensive-search/report?keyword=${keyword}`, '_blank');
        }
    }
    
    render() {
        const { loadStatus, news, notices, reports } = this.state;
        // console.log(news)
        const { className } = this.props;
        return (
            <div className={classNames(`${preFixCls}`, className)}>
                <div className={`${preFixCls}-header`}>
                    <span className={`${preFixCls}-header-title`}>新闻消息</span>
                </div>
                <Row className={`${preFixCls}-content`} type="flex" justify="space-between" hidden={loadStatus !== 'done'}>
                    <NewsListCard title='新闻'
                        type="news" data={news}
                        onRightClick={() => this.handleMore('news')}
                        />
                    <NewsListCard title='公告'
                        type="notice" data={notices}
                        onRightClick={() => this.handleMore('notice')}
                        />
                    <NewsListCard title='研报'
                        type="report" data={reports}
                        onRightClick={() => this.handleMore('report')}
                        />
                </Row>
                { loadStatus === 'pending' && <Row className={`${preFixCls}-load`} type="flex" justify="center" align="middle"><Loading/></Row>}
                { loadStatus === 'error' && <Row className={`${preFixCls}-error`} type="flex" justify="center" align="middle">无数据</Row>}
            </div>
        )
    }
}