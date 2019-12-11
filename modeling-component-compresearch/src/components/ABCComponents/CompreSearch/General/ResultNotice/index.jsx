/**
 * @description 公告组件
 * @date 2018.06.08
 * @author wsh
 */

import React, { Component } from 'react'
import { Link, withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import moment from 'moment';
import { legacySiteOrigin, origin_notice } from '../constants';
import utils from '../lib/utils';
import ListItem from '../ListItem';
import './index.scss'

// 公告资讯样式类型：1代表纯信息结构，2代表有底部标签结构
const listType = 2;
@withRouter
class ResultNotice extends Component {
    constructor(props) {
        super(props);

        this.state = {};
    }

    // 新版列表样式
    getListCon2 = (items) => {
        const UrlKeyword = window.ParseKeyword(this.props);
        const { match } = this.props;
        const basePath = match.params.lang ? '/' + match.params.lang : '';
        return items.map(function (item, idx) {
            const stockCode = utils.delHtmlTag(item.stockcode);
            const stockName = utils.delHtmlTag(item.stockname);
            const headTag = [{
                stockCode,
                stockName,
                routeUrl: `${basePath}/comprehensive-search/notice?keyword=${UrlKeyword}${stockCode ? `&securityCode=${stockCode}` : ''}`,
            }];
            const headTitle = {
                text: item.title,
                href: `${origin_notice}/detail/text?srcId=${utils.delHtmlTag(item.src_id)}`,
                xRay: item.perspective === 1 && `${legacySiteOrigin}/notice/notice-detail?tab=perspectiveviewer&src_id=${utils.delHtmlTag(item.src_id)}`,
            };
            const footer = [];
            const footShowTagNames = ["industry", "category", "file_type"];
            footShowTagNames.forEach(str => {
                for (let key in item) {
                    if (str === key) {
                        let tag = {
                            type: 'text',
                            con: item[key],
                        };
                        if (key === "file_type" && item[key] !== "网页") {
                            tag.type = 'download_s2';
                            tag.onItemClick = (e) => {
                                e.preventDefault();
                                utils.downloadfile(item.url.replace(/^http:\/\//g, 'https://'), utils.delHtmlTag(item.title));
                            }
                        } else if (key === "industry" || key === "category") {
                            tag.type = 'route';
                            tag.url = `${basePath}/comprehensive-search/notice?keyword=${UrlKeyword}&selected=${key},${item[key]}`
                        }
                        footer.push(tag);
                    }
                }
            });

            const date = moment(item.publish_at * 1000).format('YYYY/MM/DD');

            return <li key={idx}>
                <ListItem headTag={headTag} headTitle={headTitle} footer={footer} date={date} />
            </li>
        });
    }
    render() {
        let UrlKeyword = window.ParseKeyword(this.props);
        let { item: items, total_count } = this.props.data;
        let { className, match } = this.props;
        const basePath = match.params.lang ? '/' + match.params.lang : '';
        let style = "ResultNotice " + className;
        let listCon = '', listTypeClass = '';
        if (listType === 2) {
            listTypeClass = "listType2";
            listCon = this.getListCon2(items);
        } else {
            listTypeClass = "listType1";
            listCon = items.map(function (item, idx) {
                let titlestr = utils.delHtmlTag(item.title)
                return <li key={idx}>
                    <div className="ResultNoticeConlistCon">
                        <div className="leftbox">
                            <a href={`${legacySiteOrigin}/notice/notice-html?src_id=${item.src_id}`} target="_blank" title={titlestr}><span dangerouslySetInnerHTML={{ __html: item.stockcode + item.stockname }}></span><i dangerouslySetInnerHTML={{ __html: item.title }}></i></a>
                        </div>
                        <div className="rightbox">
                            <span>{moment(item.publish_at * 1000).format('YYYY-MM-DD')}</span>
                        </div>
                    </div>
                </li>
            })
        }
        const NewsListView = <div className={style}>
            {
                UrlKeyword && UrlKeyword.trim() &&
                <Link className="title" to={`${basePath}/comprehensive-search/notice?keyword=${UrlKeyword}`}><span className="highLight">{UrlKeyword}</span><span>公告搜索结果</span></Link>
            }
            {/* <Link className="title" to={`${basePath}/comprehensive-search/notice?keyword=${UrlKeyword}`}><p className="result_num">全部结果 <span>{total_count}</span> 个</p></Link> */}
            <div>
                <Link className="title" to={`${basePath}/comprehensive-search/notice?keyword=${UrlKeyword}`}>
                    <a className="result_num">全部结果 <span>{total_count}</span> 个</a>
                </Link>
            </div>
            <div className="ResultNoticeCon">
                <ul className={listTypeClass}>
                    {listCon}
                </ul>
            </div>
        </div>
        return (
            <div>{NewsListView}</div>
        )
    }
}


// props 类型
ResultNotice.propTypes = {
    title: PropTypes.string,
    data: PropTypes.any
}
export default ResultNotice