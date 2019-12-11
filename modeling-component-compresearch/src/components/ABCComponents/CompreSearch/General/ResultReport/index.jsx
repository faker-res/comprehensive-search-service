/**
 * @description 研报组件
 * @date 2018.06.08
 * @author wsh
 */

import React, { Component } from 'react'
import { Link, withRouter } from 'react-router-dom'
import PropTypes from 'prop-types';
import moment from 'moment'
import utils from '../lib/utils';
import utils2 from '../../../Report/Results/lib/utils';
import ListItem from '../ListItem';
import './index.scss'
import { origin_report } from '../../../../../lib/constants'

// 研报资讯样式类型：1代表纯信息结构，2代表有底部标签结构
const listType = 2;
@withRouter
class ResultReport extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }
    gethonorListByName = (list, name) => {
        return list.filter((item) => {
            return item.name === utils.delHtmlTag(name);
        });
    }

    // translationData
    translationData = (data) => {
        return {
            id: data.id, //研报ID
            alg_security_id: data.reportSecurityId, // 公司ID
            alg_security_name: data.reportSecurityName, // 	公司名称
            rating: data.reportRating, //股票评级
            summary:data.reportSummary, //摘要
            file_url: data.reportFileUrl, //文件地址
            filetype: data.reportFileType, //文件类型
            source_title: data.reportTitle, //研报主题
            time: data.reportPublishTime, //发布时间
            alg_publish_name: data.reportPublishName, //发表机构
            report_type: data.reportType, //研报类型
            industry_name: data.reportIndustryType, //行业
            file_pages: data.reportPageCount, //研报页数
            category_name: '', //所属类别
            stockcode: '',
            stockname: '',
            author: '', //作者
            from: '', //来源
        }
    }
    
    /**
     * 移除文本中的html标签
     * @param {string} htmlText 待清洗的html内容 demo: <font color="red">顺丰速递</font> => 顺丰速递
     */
    removeDomOfText(htmlText = '') {
        return htmlText ? htmlText.replace(/<\/?[^>]+>/g, '') : '';
    }

    // 新版列表样式
    getListCon2 = (items = [], source = '') => {
        const { match } = this.props;
        const basePath = match.params.lang ? '/' + match.params.lang : '';
        let UrlKeyword = window.ParseKeyword(this.props);
        // const clickableTagTypeMap = {
        //     'source': 'source',
        //     'industry': 'industry_txt',
        //     'category': 'category_name',
        //     'author': 'author',
        // };
        return items.map((it, idx) => {
            const item = this.translationData(it)
            const stockCode = utils.delHtmlTag(item.stockcode);
            const stockName = utils.delHtmlTag(item.stockname);
            const headTag = [{
                stockCode,
                stockName,
                routeUrl: '',
                // routeUrl: `${basePath}/comprehensive-search/report?keyword=${UrlKeyword}${stockCode ? `&stock_code=${stockCode}` : ''}`,
            }];
            const headTitle = {
                text: item.source_title || '暂无数据',
                href: `${origin_report}/viewpoint/salerreport/report-detail/article/${item.id}`,
                rating: item.rating
            };
            const footer = [];
            // publish：发布机构 industry_name： 行业 category：类别 author：作者 file_pages：页数 
            const footTagName = ["alg_publish_name", "industry_name", "category_name", "author", "file_pages", "tag", 'filetype'];
            footTagName.forEach(str => {
                for (let key in item) {
                    if (str === key) {
                        let tag = {
                            type: 'text',
                            con: item[key],
                        };
                        if (key === "filetype" && item[key] === "网页"){
                            tag.type = 'intelPage';
                            tag.con = (item[key] || '--').replace(/\./g, '')
                        } else if (key === "filetype" && item[key] !== "网页" ){
                            tag.type = 'download_s2';
                            tag.con = (item[key] || '--').replace(/\./g, '')
                            tag.onItemClick = (e)=>{
                                e.preventDefault();
                                utils2.downloadfile(item.file_url.replace(/^http:\/\//g,'https://'), item.source_title ? this.removeDomOfText(item.source_title) : '未知');
                            }
                        }
                        else if (key === "file_pages") {
                            tag.con = item[key] ? item[key] + '页' : item[key] == 0 ? '1页' : ''
                            // const url_split = (item.url || "").split(".");
                            // // tag.url = `/api/usercenter/file/pdf?name=${encodeURIComponent(utils.delHtmlTag(item.title))}.${url_split[url_split.length - 1]}&url=${window.btoa(item.url)}`;
                            // let page = item[key] ? item[key] + '页' : '0页';
                            // tag.con = (<span style={{ border: '1px solid #BFBFBF', borderRadius: '2px', paddingRight: '3px' }}>
                            //     <i style={{
                            //         marginRight: '3px',
                            //         fontStyle: 'normal',
                            //         padding: '0px 3px',
                            //         backgroundColor: '#BFBFBF',
                            //         color: '#fff'
                            //     }}>{page}</i>{item['filetype'].replace('.', '').toUpperCase()}
                            // </span>)
                        } else if (key === "tag") {
                            tag.type = "arr_dataFrom";
                        } else if (key === "author" && item[key]) {
                            tag.type = 'arr_author';
                            const authorList = [];
                            item[key].forEach(ele => {
                                let author = {
                                    name: ele,
                                    url: `${basePath}/report?keyword=${UrlKeyword}`
                                };
                                // if (Array.isArray(item.analyst_honor) && item.analyst_honor.some(obj => obj.name === ele)) {
                                //     author.id = item.analyst_honor.filter(obj => obj.name === ele)[0].id;
                                // }
                                authorList.push(author);
                            });
                            tag.con = authorList;
                        } else if (key === "alg_publish_name" || key === "industry_name" || key === "category_name") {
                            tag.type = 'route';
                            tag.url = `${basePath}/report?keyword=${UrlKeyword}`;
                        }
                        footer.push(tag);
                    }
                }
            });
            const date = moment(item.time).format('YYYY/MM/DD');
            return <li key={idx}>
                <ListItem headTag={headTag} headTitle={headTitle} footer={footer} date={date} source={source} type={source}/>
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
        let { item: items = [], total_count, source = '' } = this.props.data;
        // console.log('this.props.data--resultReport ', this.props.data)
        let style = "ResultReport " + className;
        let listCon = '', listTypeClass = '';
        // 根据不同类型选择列样式
        if (listType === 2) {
            listTypeClass = "listType2";
            listCon = this.getListCon2(items, source);
        } else {
            listTypeClass = "listType1";
            listCon = items.map(function (item, idx) {
                let titlestr = utils.delHtmlTag(item.title)
                return <li key={idx}>
                    <div className="ResultReportConlistCon">
                        <div className="leftbox">
                            <a href={`${origin_report}/report/article/${item.id}`} target="_blank" title={titlestr}><span dangerouslySetInnerHTML={{ __html: item.stockcode + item.stockname }}></span><i dangerouslySetInnerHTML={{ __html: item.title }}></i></a>
                        </div>
                        <div className="rightbox">
                            <span>{moment(item.time * 1000).format('YYYY-MM-DD')}</span>
                        </div>
                    </div>
                </li>
            });
        }
        // 研报列表容器
        const NewsListView = <div className={style}>
            {
                !hideTotal && (
                    <React.Fragment>
                        {/*<Link className="title" to={`${basePath}/${hideTotal ? 'vender' : 'internal'}-report?keyword=${UrlKeyword}`} >*/}
                        <Link className="title" to={`report?keyword=${UrlKeyword}`} >
                            <span className="highLight">{UrlKeyword ? UrlKeyword + '的' : ''}</span>
                            <span>{`${titleSuffix}搜索结果`}</span>
                        </Link>
                        <div>
                            <Link className="title" to={`report?keyword=${UrlKeyword}`}>
                                <span className="result_num">全部结果 <span>{total_count}</span> 个</span>
                            </Link>
                        </div>
                    </React.Fragment>
                )
            }
            <div className="ResultReportCon">
                <ul className={listTypeClass}>
                    {listCon}
                </ul>
            </div>
        </div>
        // 最终返回视图
        return items && items.length ? (
            <div>{NewsListView}</div>
        ) : null
    }
}


// props 类型
ResultReport.propTypes = {
    title: PropTypes.string,
    data: PropTypes.any,
    // title后缀
    titleSuffix: PropTypes.string,
    // 是否隐藏统计信息
    hideTotal: PropTypes.bool
}

ResultReport.defaultProps = {
    title: "",
    data: [],
    titleSuffix: "",
    hideTotal: false
}
export default ResultReport