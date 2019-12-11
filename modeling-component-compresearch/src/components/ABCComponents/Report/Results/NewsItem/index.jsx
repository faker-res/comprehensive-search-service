import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {

} from 'antd';
import { withRouter } from 'react-router-dom';
import classnames from 'classnames';
import numeral from 'numeral';
import moment from 'moment';

import { utils } from '../../../../../lib/until';
import {formateTimebyTimebefore} from '../../../../../lib/utils.js'
import './index.scss';

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
class NewsItem extends Component {
    constructor(props) {
        super(props);

        this.state = {
            // 是否收起摘要
            hideSummary: true,
            // 默认展示摘要数
            defaultSummaryCount: 2,

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
        return highlight && keyword.length ? text.replace(new RegExp('(' + keyword.split(' ').join('|') + ')', 'ig'), '<font class="highlight-text">$1</font>') : text;
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

    render() {
        let {
            className,
            data,
            highlight
        } = this.props;
        // console.log(data)
        let {
            hideSummary,
            defaultSummaryCount
        } = this.state;

        let {
            content: paragraph = [],
            analyst_honor = [],
            content = '',
            alg_security_id = '',
            alg_security_name = '',
            rating = '',
            from = {}
        } = data;
        if(highlight.keyword){
            highlight = highlight.keyword.split(',').join(' ');
        }
        // console.log(highlight)
        if (utils.getObjType(paragraph) != 'array') {
            paragraph = [paragraph];
        } else {
            paragraph = paragraph || [];
        }

        // download key
        // let downloadKey = ((data.url.match(/[^\?\/]+\?/) || [])[0] || '').replace(/\?/, '');
        return (
            <div className={`abc-news-item ${className}`}>
                <div className={'item-header'}>
                    {/* {
                        alg_security_id || alg_security_name ? (<div className="item-stockname JS-stockname" onClick={this.handleClick} title={(alg_security_id && alg_security_id.replace(/<\/?[^>]+>/g, '')) + ' ' + (alg_security_name && alg_security_name.replace(/<\/?[^>]+>/g, ''))}>
                            <span className="stock-name JS-stockPopup JS-secondSearch">{(alg_security_id && alg_security_id.replace(/<\/?[^>]+>/g, '')) + ' ' + (alg_security_name && alg_security_name.replace(/<\/?[^>]+>/g, ''))}</span>
                        </div>) : null
                    } */}

                    <span className={'item-title'}
                        onClick={this.handleClick}
                        title={this.removeDomOfText(data.title || '')}
                        dangerouslySetInnerHTML={{
                            __html: `${this.highLightText(data.title, highlight)}`
                        }}
                        >
                    </span>

                    {/* {!!rating && <span onClick={this.handleClick} className={`rating JS-secondSearch ${RATING_ENUM[rating.replace(/<\/?[^>]+>/g, '')]}`}>{rating.replace(/<\/?[^>]+>/g, '') || ''}</span>} */}
                </div>

                <div className="item-body">
                    <div className="item-summary" onClick={this.handleClick}>
                    {/* <div className="match-img">
                            {
                                paragraph.map((item, index) => {
                                    return <p key={index} className={classnames('match-para', {
                                        hidden: index > defaultSummaryCount - 1 && hideSummary
                                    })}
                                        dangerouslySetInnerHTML={{
                                            __html: this.highLightText(item, highlight)
                                        }}></p>;
                                })
                            }
                    </div> */}
                        <div className="match-summary">
                            {
                                paragraph.map((item, index) => {
                                    return (
                                        <span key={index} className={"currentNewsCon" + (data.first_image_oss? ' hasImg' : '')}>
                                        {data.first_image_oss && <span className="currentNewsCon__pic" style={{backgroundImage : `url('${data.first_image_oss}')`}}></span>}
                                        <div className="news-content currentNewsCon__info">
                                          { typeof data.content === 'string' &&
                                            <p className="news-summary" dangerouslySetInnerHTML={{__html: data.content}}></p>
                                          }
                                          {/* <div className="news-info sourcefoot">
                                            <span className="source"  dangerouslySetInnerHTML={{__html:data.source_name_s || data.source_name}}></span>
                                            {data.author && <span className="author" dangerouslySetInnerHTML={{__html:data.author}}></span>}
                                            <span className="time">{data.publish_time}</span>
                                          </div> */}
                                        </div>
                                      </span>

                                        // <div className='match-container'>
                                        //     {!!data.first_image_oss && <span className="currentNewsCon__pic" style={{backgroundImage : `url(${data.first_image_oss})`}}></span>}
                                        //     {!!item && <p key={index} className={classnames('match-para', {
                                        //         hidden: index > defaultSummaryCount - 1 && hideSummary
                                        //     })}
                                        //         dangerouslySetInnerHTML={{
                                        //             __html: this.highLightText(item, highlight)
                                        //     }}></p>}
                                        // </div>
                                    );
                                })
                            }

                            {
                                paragraph.length > defaultSummaryCount && (
                                    <React.Fragment>
                                        <p className={classnames('view-open-container', {
                                            hidden: !hideSummary
                                        })}>
                                            <span className="view-open" onClick={this.toggleSummary}>
                                                <img className={'icon-arrow-down'} src={ICON_ARROW} />展开更多({paragraph.length - defaultSummaryCount})
                                            </span>
                                        </p>
                                        <p className={classnames('view-more', {
                                            hidden: hideSummary
                                        })}>
                                            <span className="view-open" onClick={this.toggleSummary}>
                                                <img className={'icon-arrow-down up'} src={ICON_ARROW} />收起
                                            </span>
                                            <a target="_blank" href={`/report-detail/article/${data.id}`}>查看全部</a>
                                        </p>
                                    </React.Fragment>
                                )
                            }
                        </div>
                    </div>
                </div>

                <div className="item-bottom">      
                    {!!data.source_name && <span>
                        <span className="JS-secondSearch" dangerouslySetInnerHTML={{
                            __html: data.source_name ? this.highLightText(data.source_name, highlight) : '--'
                        }}></span>
                    </span>}
                    {!!data.publish_time && <span className="item-author">
                        <span className="JS-secondSearch" dangerouslySetInnerHTML={{
                            __html: data.publish_time||'--'
                        }}></span>
                    </span>}
                    <div>
                    {/* {data.timebefore&&formateTimebyTimebefore(data.timebefore)} */}
                    </div>
                    {/* {!!data.alg_rating && <span dangerouslySetInnerHTML={{
                            __html: data.alg_rating ? this.highLightText(data.alg_rating, highlight) : '--'
                        }}></span>} */}
                    {/* {!!data.pageCount && <span className='item-page-file'>
                        <span className="item-page-count">
                            {data.pageCount || 0}页
                        </span>
                        <span className='source-type'>
                            <a href={`${data.url}`} target="_blank" title="点击下载源文件">
                                <i>{(data.fileType || '--').replace(/\./g, '').toUpperCase()}</i>
                                
                                </a>
                        </span>
                    </span>}
                    {!!data.createAt && <i className="item-date fr">{data.createAt ? this.formatDate(new Date(data.createAt * 1000), 'YYYY/MM/DD') : '--'}</i>} */}
                </div>
            </div>
        );
    }
}

NewsItem.defaultProps = {
    language: 'zh-CN',
    className: '',
    handleRef: () => { },
    data: {},
    highlight: ''
};

NewsItem.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // ref回调
    handleRef: PropTypes.func,
    // 研报数据
    data: PropTypes.object,
    // 需要高亮的关键词
    highlight: PropTypes.object,
};

export default NewsItem;