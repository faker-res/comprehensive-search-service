/**
 * @description 研报组件
 * @date 2018.06.08
 * @author wsh
 */

import React, { Component } from 'react'
import { Link, withRouter } from 'react-router-dom'
import PropTypes from 'prop-types';
import moment from 'moment'
import { origin_notice } from '../../../../../lib/constants';
import utils from '../lib/utils';
import utils2 from '../../../Report/Results/lib/utils';
import ListItem from '../ListItem';
import './index.scss'
import { transaction } from 'mobx';

// 研报资讯样式类型：1代表纯信息结构，2代表有底部标签结构
const listType = 2;
@withRouter
class ResultAnnouncement extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }
    gethonorListByName = (list, name) => {
        return list.filter((item) => {
            return item.name === utils.delHtmlTag(name);
        });
    }
    // 新版列表样式
    getListCon2 = (item = []) => {
        const { match } = this.props;
        const basePath = match.params.lang ? '/' + match.params.lang : '';
        let UrlKeyword = window.ParseKeyword(this.props);
        const clickableTagTypeMap = {
            'source': 'source',
            'industry': 'industry_txt',
            'category': 'category_name',
            'author': 'author',
        };
        return item.map(function (item, idx) {
            const stockCode = utils.delHtmlTag(item.stockcode);
            const stockName = utils.delHtmlTag(item.stockname);
            const headTag = [{
                stockCode,
                stockName,
                routeUrl: '',
                // routeUrl: `${basePath}/comprehensive-search/report?keyword=${UrlKeyword}${stockCode ? `&stock_code=${stockCode}` : ''}`,
            }];
            const headTitle = {
                text: item.title || '暂无数据',
                href: `${origin_notice}/notice/detail/html/?srcId=${item.srcId}`,
                rating: item.rating
            };
            const footer = [];
            const footTagName = ["source", "source_name","industry", "category", "author", "pageCount", "tags","fileType"];
            footTagName.forEach(str => {
                for (let key in item) {
                    if (str === key) {
                        let tag = {
                            type: 'text',
                            con: item[key],
                        };

                        if (key === "fileType" && item[key] === "网页"){
                            tag.type = 'intelPage';
                            tag.url = item.url;
                        } else if (key === "fileType" && item[key] !== "网页" ){ 
                            tag.type = 'download_s2';
                            tag.onItemClick = (e)=>{
                                e.preventDefault();
                                utils2.downloadfile(item.url.replace(/^http:\/\//g,'https://'),  item.title ? utils.delHtmlTag(item.title) : '未知');
                            }
                        }
                        // if (key === "page_count") {
                        //     const url_split = (item.url || "").split(".");
                        //     tag.url = `/api/usercenter/file/pdf?name=${encodeURIComponent(utils.delHtmlTag(item.title))}.${url_split[url_split.length - 1]}&url=${window.btoa(item.url)}`;
                        //     let page = item[key] ? item[key] + '页' : '0页';
                        //     tag.con = (<span style={{ border: '1px solid #BFBFBF', borderRadius: '2px', paddingRight: '3px' }}>
                        //         <i style={{
                        //             marginRight: '3px',
                        //             fontStyle: 'normal',
                        //             padding: '0px 3px',
                        //             backgroundColor: '#BFBFBF',
                        //             color: '#fff'
                        //         }}>{page}</i>{item['file_type']}
                        //     </span>)
                        // }
                        
                        else if (key === "pageCount"){
                            tag.con = item[key] ? item[key] + '页' : item[key] == 0 ? '1页' : ''
                        } else if (key === "tags") {
                            tag.type = "arr_dataFrom";
                        } else if (key === "author") {
                            tag.type = 'arr_author';
                            const authorList = [];
                            item[key].forEach(ele => {
                                let author = {
                                    name: ele,
                                    url: `${basePath}/report?keyword=${UrlKeyword}&selected=${clickableTagTypeMap[key]},${ele}`
                                };
                                // if (Array.isArray(item.analyst_honor) && item.analyst_honor.some(obj => obj.name === ele)) {
                                //     author.id = item.analyst_honor.filter(obj => obj.name === ele)[0].id;
                                // }
                                authorList.push(author);
                            });
                            tag.con = authorList;
                        } else if (key === "source" || key === "industry" || key === "category") {
                            tag.type = 'route';
                            tag.url = `${basePath}/comprehensive-search/report?keyword=${UrlKeyword}&selected=${clickableTagTypeMap[key]},${item[key]}`;
                        }
                        footer.push(tag);
                    }
                }
            });
            const date = moment(item.createAt * 1000).format('YYYY/MM/DD');
            return <li key={idx}>
                <ListItem headTag={headTag} headTitle={headTitle} footer={footer} date={date} />
            </li>
        })
    }
    render() {
        const {
            className,
            match,
            titleSuffix,
            hideTotal
        } = this.props;
        const basePath = match.params.lang ? '/' + match.params.lang : '';
        let UrlKeyword = window.ParseKeyword(this.props);
        let { item=[], total_count } = this.props.data;
        let items = item || []; // 之前的代码没有items的定义，也是服了
        let style = "ResultReport " + className;
        let listCon = '', listTypeClass = '';
        if (listType === 2) {
            listTypeClass = "listType2";
            listCon = this.getListCon2(item);
        } else {
            listTypeClass = "listType1";
            listCon = items.map(function (item, idx) {
                let titlestr = utils.delHtmlTag(item.title)
                return <li key={idx}>
                    <div className="ResultReportConlistCon">
                        <div className="leftbox">
                            <a href={`${origin_notice}/viewpoint/salerreport/article/${item.id}`} target="_blank" title={titlestr}><span dangerouslySetInnerHTML={{ __html: item.stockcode + item.stockname }}></span><i dangerouslySetInnerHTML={{ __html: item.title }}></i></a>
                        </div>
                        <div className="rightbox">
                            <span>{moment(item.time * 1000).format('YYYY-MM-DD')}</span>
                        </div>
                    </div>
                </li>
            });
        }
        const NewsListView = <div className={style}>
            {
                !hideTotal && (
                    <React.Fragment>
                        {
                            <a className="title" href={`/announcement?keyword=${UrlKeyword}`} >
                                <span className="highLight">{UrlKeyword ? UrlKeyword + '的' : '' }</span>
                                <span>{`${titleSuffix}搜索结果`}</span>
                            </a>
                        }

                        
                        <p className="result_num">
                            <a href={`/announcement?keyword=${UrlKeyword}`}>
                            全部结果 <span>{total_count}</span> 个
                            </a>
                        </p>
                    </React.Fragment>
                )
            }
            <div className="ResultReportCon">
                <ul className={listTypeClass}>
                    {listCon}
                </ul>
            </div>
        </div>
        return item && item.length ? (
            <div>{NewsListView}</div>
        ) : null
    }
}


// props 类型
ResultAnnouncement.propTypes = {
    title: PropTypes.string,
    data: PropTypes.any,
    // title后缀
    titleSuffix: PropTypes.string,
    // 是否隐藏统计信息
    hideTotal: PropTypes.bool
}

ResultAnnouncement.defaultProps = {
    title: "",
    data: [],
    titleSuffix: "",
    hideTotal: false
}
export default ResultAnnouncement