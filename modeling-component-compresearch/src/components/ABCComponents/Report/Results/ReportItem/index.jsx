import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Icon } from 'antd';
import { withRouter } from 'react-router-dom';
import classnames from 'classnames';
import numeral from 'numeral';
import moment from 'moment';

import { utils } from '../../../../../lib/until';
import './index.scss';
import { origin_report } from '../../../../../lib/constants'
import utils2 from '../lib/utils'
import dataSearch from '../../../../../config/dataSearch';

const ICON_ARROW = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAKCAYAAAHz3l9RAAAABGdBTUEAALGPC/xhBQAAAPlJREFUKBVjnL1g6XSG//8zGGCAcfb8Jf9hHAQ9Z8HSfzAZFCWMIDUgGSZGphCw+kWLVimCGGCZOfOXPvjP8F8eLAMluNmZROH8////g82bvWDJOpggigUwQTDNyDiDCUU7VJaRgfFhakJ0JlNUVNQbuEuAkoyMjP9TEqMVoOogFMgukJNBdqNIwDgwL8D4jGfOnGE9f/nmbgaG//YwQfw040FDXXVXuPFzF6/Q///3z9H//xm4sWlkZGT4ysjMYp0cG3ERJA/XCFM8Z8GSRqDmOhgfrIiRoSklIaYeRQyZA2OvWrWK5+PX38dAfH5uVquwsLAvMDmKaQDsZVqWzbRUQwAAAABJRU5ErkJggg==';
// 评估
const RATING_ENUM = {
    '推荐': 'buyin',
    '买入': 'buyin',
    '卖出': 'saleout',
    '增持': 'addbuy',
    '减持': 'removebuy',
    '中性': 'hold'
};
// 荣誉
let HONOR_MAP = {
    '新财富最佳分析师': '新财富',
    '中国证券业分析师金牛奖': '金牛奖'
};

@withRouter
class ReportItem extends Component {
    constructor(props) {
        super(props);

        this.state = {
            // 是否收起摘要
            hideSummary: true,
            // 默认展示摘要数
            defaultSummaryCount: 1, //默认只展示一条

        };

        this.toggleSummary = this.toggleSummary.bind(this);
        this.handleClick = this.handleClick.bind(this);
    }

    toggleSummary() {
        this.setState((prevState, props) => {
            return {
                hideSummary: !prevState.hideSummary
            };
        });
    }

    /**
     * 高亮文本中的关键词
     * @param {string} text 需要高亮的文本
     * @param {string} keyword 关键词（多个关键词用空格分隔）
     * @param {boolean} highlight 是否对text进行高亮
     * @author lzhang
     */
    highLightText(text = '', keyword = '', highlight = true) {
        // 高亮信息
        return highlight && keyword.trim() ? text.replace(new RegExp('(' + keyword.split(' ').join('|') + ')', 'ig'), '<font class="highlight-text">$1</font>') : text;
    }

    /**
     * 移除文本中的html标签
     * @param {string} htmlText 待清洗的html内容 demo: <font color="red">顺丰速递</font> => 顺丰速递
     */
    removeDomOfText(htmlText = '') {
        return htmlText ? htmlText.replace(/<\/?[^>]+>/g, '') : '';
    }

    /**
     * 格式化数字
     * @param num  {number} 数字
     * @param format  {string} 格式化字符串
     * @author lzhang
     * @address http://numeraljs.com/
     */
    formatNumber(num = 0, format = '0,0') {
        return numeral(num).format(format);
    }

    /**
     * 格式化时间
     * @param {Date} date 需要格式化的日期对象
     *  @param format  {string} 格式化字符串
     */
    formatDate(date = new Date(), format = 'YYYY.MM.DD') {
        return moment(date.getTime()).format(format);
    }

    /**
     * 获取兼容时间串
     */
    getSafeDataStr(dataStr = '') {
        return dataStr ? dataStr.replace(/\-/g, '/') : '';
    }

    handleClick() {
        let {
            data,
            onClick
        } = this.props;

        onClick && onClick(data);
    }

    delSpace = (str)=>{
      return str.replace(/\s+/g,'');
    }

    getShowParagraph (item) {
      let {paragraph, summary} = item
      if (paragraph && paragraph.length) {
        let results = []
        paragraph.forEach(item => {
          if (this.delSpace(item).length < 16) return;
          else {
            results.push(item)
          }
        })
        return results
      } else if(summary && summary.length) {
        return summary
      } else {
        return []
      }
    }

    // download
    downloadFile = (url, name) => {
        utils2.downloadfile(url, name)
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

    render() {
        let {
            className,
            highlight
        } = this.props;
// console.log(data,highlight)
        let {
            hideSummary,
            defaultSummaryCount
        } = this.state;
        // 就这么玩，不准乱七八糟定义字段了
        const data = this.translationData(this.props.data)
        let {
            alg_security_id = '',
            alg_security_name = '',
            rating = '',
            from = {}
        } = data;

        let paragraph = this.getShowParagraph(data)
        highlight = highlight.split(',').join(' ');
        if (utils.getObjType(paragraph) != 'array') {
            paragraph = [paragraph];
        } else {
            paragraph = paragraph || [];
        }

        // download key
        // console.log('参数',data.file_url);
        let fileUrl = data.file_url
        let downloadKey = ((fileUrl.match(/[^\?\/]+\?/) || [])[0] || '').replace(/\?/, '');

        return (
            <div className={`abc-report-item ${className}`}>
                <div className={'item-header'} onClick={() => {window.open(`${origin_report}/viewpoint/salerreport/report-detail/article/${data.id}`);}}>
                    {
                        alg_security_id || alg_security_name ? (<div className="item-stockname JS-stockname" onClick={this.handleClick} title={(alg_security_id && alg_security_id.replace(/<\/?[^>]+>/g, '')) + ' ' + (alg_security_name && alg_security_name.replace(/<\/?[^>]+>/g, ''))}>
                            <span className="stock-name JS-stockPopup JS-secondSearch">{(alg_security_id && alg_security_id.replace(/<\/?[^>]+>/g, '')) + ' ' + (alg_security_name && alg_security_name.replace(/<\/?[^>]+>/g, ''))}</span>
                        </div>) : ''
                    }
                    <span className={'item-title'}
                        onClick={this.handleClick}
                        title={this.removeDomOfText(data.source_title || '')}
                        dangerouslySetInnerHTML={{
                            __html: this.highLightText(data.source_title, highlight)
                        }}>
                    </span>
                    {!!rating && <span onClick={this.handleClick} className={`rating JS-secondSearch ${RATING_ENUM[rating.replace(/<\/?[^>]+>/g, '')]}`}>{rating.replace(/<\/?[^>]+>/g, '') || ''}</span>}
                </div>
                            
                <div className="item-body">
                    <div className="item-summary" onClick={this.handleClick}>
                        <div className="match-summary">
                            {
                                paragraph.map((item, index) => {
                                    return <div className='match-para-wrap'> 
                                        <p key={index} onClick={() => {window.open(`${origin_report}/viewpoint/salerreport/report-detail/article/${data.id}`);}} 
                                            className={classnames('report-match-para', {
                                            hidden: index > defaultSummaryCount - 1 && hideSummary
                                        })}
                                        dangerouslySetInnerHTML={{
                                            __html: this.highLightText(item, highlight)
                                        }}></p>
                                    </div>;
                                })
                            }
                            {/* 暂时不考虑多条情况 */}
                            {
                                // paragraph.length > defaultSummaryCount && (
                                //     <React.Fragment>
                                //         <p className={classnames('view-open-container', {
                                //             hidden: !hideSummary
                                //         })}>
                                //             <span className="view-open" onClick={this.toggleSummary}>
                                //                 <img className={'icon-arrow-down'} src={ICON_ARROW} />展开更多({paragraph.length - defaultSummaryCount})
                                //             </span>
                                //         </p>
                                //         <p className={classnames('view-more', {
                                //             hidden: hideSummary
                                //         })}>
                                //             <span className="view-open" onClick={this.toggleSummary}>
                                //                 <img className={'icon-arrow-down up'} src={ICON_ARROW} />收起
                                //             </span>
                                //             <a target="_blank" href={`${origin_report}/viewpoint/salerreport/report-detail/article/${data.id}`}>查看全部</a>
                                //         </p>
                                //     </React.Fragment>
                                // )
                            }
                        </div>
                    </div>
                </div>

                <div className="item-bottom">
                    {!!data.alg_publish_name && <span className="source-tag" hidden={this.props.location.pathname === '/report/secret/report-results'}>
                        <span className="JS-secondSearch" dangerouslySetInnerHTML={{
                            __html: data.alg_publish_name ? this.highLightText(data.alg_publish_name, highlight) : '--'
                        }}></span>
                    </span>}
                    {data.industry_name && <span>
                        <span className="JS-secondSearch" dangerouslySetInnerHTML={{
                            __html: data.industry_name ? this.highLightText(data.industry_name, highlight) : '--'
                        }}></span>
                    </span>}
                    {data.category_name && <span>
                        <span className="JS-secondSearch" dangerouslySetInnerHTML={{
                            __html: data.category_name? this.highLightText(data.category_name, highlight) : '--'
                    }}></span>
                    </span>}
                    {!!data.report_type && <span className="notice_type">
                        <span className="JS-secondSearch" dangerouslySetInnerHTML={{
                            __html: data.report_type ? this.highLightText(data.report_type, highlight) : '--'
                        }}></span>
                    </span>}
                    {data.author && <span className="item-author">
                        <span className="JS-secondSearch" dangerouslySetInnerHTML={{
                            __html: data.author ? this.highLightText(data.author.join(' '), highlight) : '--'
                        }}></span>
                    </span>}
                    {data.rating && <span dangerouslySetInnerHTML={{
                            __html: data.rating ? this.highLightText(data.rating, highlight) : '--'
                        }}></span>}
                    {
                    data.file_pages && <span className='item-page-file'>
                        <span className="item-page-count">
                            {data.file_pages || 0}页
                        </span>
                        <span className='source-type'>
                            <a className='sourceDown' title="点击下载源文件" onClick={() => this.downloadFile(fileUrl.replace(/^http:\/\//g, 'https://'), data.source_title ? this.removeDomOfText(data.source_title) : '未知')}>
                                <i className='sourceType'>{(data.filetype || '--').replace(/\./g, '').toUpperCase()}</i><Icon type="download" />
                            </a>
                        </span>
                    </span>
                    }
                    {/* {!!data.file_pages && <span>{data.file_pages || 0}页</span>} */}
                    {/* <span>
                        <span onClick={() => this.downloadFile(fileUrl, data.source_title ? this.removeDomOfText(data.source_title) : '未知')} title="点击下载源文件">{(data.filetype || '--').replace(/\./g, '').toUpperCase()}</span>
                    </span> */}
                    {(!!from.name)&& <span>分享者：<span dangerouslySetInnerHTML={{
                                            __html: from.name ? this.highLightText(from.name, highlight) : '--'
                                        }}></span>&nbsp;&nbsp;
                                        <span dangerouslySetInnerHTML={{
                                            __html: from.address ? this.highLightText(from.address, highlight) : ''
                                        }}></span>
                                        </span>}
                    {!!data.time && <i className="item-date fr">{data.time ? this.formatDate(new Date(data.time), 'YYYY/MM/DD') : '--'}</i>}
                </div>
            </div>
        );
    }
}

ReportItem.defaultProps = {
    language: 'zh-CN',
    className: '',
    handleRef: () => { },
    data: {},
    highlight: ''
};

ReportItem.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // ref回调
    handleRef: PropTypes.func,
    // 研报数据
    data: PropTypes.object,
    // 需要高亮的关键词
    highlight: PropTypes.string,
    //筛选数据
    requireFilters:PropTypes.func
};

export default ReportItem;