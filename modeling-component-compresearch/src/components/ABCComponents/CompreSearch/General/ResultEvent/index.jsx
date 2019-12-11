/**
 * @description 活动日历组件
 * @date 2018-8-21
 * @author lzhang@abcft.com
 */

import React, { Component } from 'react'
import { Link, withRouter } from 'react-router-dom'
import PropTypes from 'prop-types';
import moment from 'moment'
import { origin_report } from '../constants';
import utils from '../lib/utils';
import ListItem from '../ListItem';
import queryString from 'query-string';
import _ from 'lodash';
import './index.scss'

// 研报资讯样式类型：1代表纯信息结构，2代表有底部标签结构
const listType = 2;
@withRouter
class ResultEvent extends Component {
    constructor(props) {
        super(props);

        this.state = {};
    }
    gethonorListByName = (list, name) => {
        return list.filter((item) => {
            return item.name === utils.delHtmlTag(name);
        });
    }
    // 获取查询参数
    getQuery = (search) => {
        return queryString.parse(
            _.isUndefined(search)
                ? this.props.location.search
                : search
        );
    }
    // 新版列表样式
    getListCon2 = (items = []) => {
        const { match } = this.props;
        const basePath = match.params.lang ? '/' + match.params.lang : '';
        let UrlKeyword = this.getQuery().keyword;//window.ParseKeyword(this.props);
        const clickableTagTypeMap = {
            'source': 'source',
            'industry': 'industry_txt',
            'category': 'category_name',
            'author': 'author',
        };
        return items.map(function (item, idx) {
            const headTitle = {
                text: item.title,
                href: `javascript:void(0)`,
                rating: ''
            };
            const footer = [];
            const footTagName = ["send_user", "speakers", "sponsor", "place"];
            footTagName.forEach(str => {
                for (let key in item) {
                    if (str === key) {
                        let tag = {
                            type: 'text',
                            con: item[key],
                        };

                        if (key === "send_user") {
                            tag.con = <span>发布人: <i style={{fontStyle: 'normal', color: '#3D5C7F'}} dangerouslySetInnerHTML={{__html: item[key] || "无"}}></i></span>;
                        } else if (key === "speakers") {
                            tag.con = <span>嘉宾: <i style={{fontStyle: 'normal', color: '#3D5C7F'}} dangerouslySetInnerHTML={{__html: (item[key] || []).join(' ') || "无"}}></i></span>;
                        } else if (key === "sponsor") {
                            tag.con = <span>主办方: <i style={{fontStyle: 'normal', color: '#3D5C7F'}} dangerouslySetInnerHTML={{__html: item[key] || "无"}}></i></span>;
                        } else if (key === "place") {
                            tag.con = <span>活动地点: <i style={{fontStyle: 'normal', color: '#3D5C7F'}} dangerouslySetInnerHTML={{__html: item[key] || "无"}}></i></span>;
                        }
                        footer.push(tag);
                    }
                }
            });
            const date = moment(item.start_time).format('YYYY/MM/DD');
            return <li key={idx}>
                <ListItem headTitle={headTitle} footer={footer} date={date} body={(item['content'] || "").replace(/<br\s*\/?>/g, '')}/>
            </li>
        })
    }
    render() {
        const {
            className,
            match,
            titleSuffix
        } = this.props;
        const basePath = match.params.lang ? '/' + match.params.lang : '';
        let UrlKeyword = this.getQuery().keyword;//window.ParseKeyword(this.props);
        let { item: items = [], total_count } = this.props.data;
        let style = "ResultEvent " + className;
        let listCon = '', listTypeClass = '';
        if (listType === 2) {
            listTypeClass = "listType2";
            listCon = this.getListCon2(items);
        } else {
            listTypeClass = "listType1";
            listCon = items.map(function (item, idx) {
                let titlestr = utils.delHtmlTag(item.title)
                return <li key={idx}>
                    <div className="ResultEventConlistCon">
                        <div className="leftbox">
                            <a href={`${origin_report}/report/article/${item.id}`} target="_blank" title={titlestr}><span dangerouslySetInnerHTML={{ __html: item.stockcode + item.stockname }}></span><i dangerouslySetInnerHTML={{ __html: item.title }}></i></a>
                        </div>
                        <div className="rightbox">
                            <span>{moment(item.start_time * 1000).format('YYYY-MM-DD')}</span>
                        </div>
                    </div>
                </li>
            });
        }
        const NewsListView = <div className={style}>
            <Link className="title" to={`${basePath}/activity?keyword=${UrlKeyword}`} >
                <span className="highLight">{UrlKeyword}</span>
                <span>{`${titleSuffix}搜索结果`}</span>
            </Link>
            <Link className="title" to={`${basePath}/activity?keyword=${UrlKeyword}`}>
                <p className="result_num">全部结果 <span>{total_count}</span> 个</p>
            </Link>
            <div className="ResultEventCon">
                <ul className={listTypeClass}>
                    {listCon}
                </ul>
            </div>
        </div>
        return items && items.length ? (
            <div>{NewsListView}</div>
        ) : null
    }
}


// props 类型
ResultEvent.propTypes = {
    title: PropTypes.string,
    data: PropTypes.any,
    // title后缀
    titleSuffix: PropTypes.string,
}

ResultEvent.defaultProps = {
    title: "",
    data: [],
    titleSuffix: ""
}
export default ResultEvent