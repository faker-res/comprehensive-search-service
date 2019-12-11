/**
 * @description 资讯组件
 * @date 2018.06.08
 * @author wsh
 */

import React, { Component }  from 'react'
import PropTypes from 'prop-types'
import moment from 'moment'
import queryString from 'query-string';
import { withRouter } from 'react-router-dom';
import _ from 'lodash'
import md5 from 'md5'
import { legacySiteOrigin } from '../constants';
import utils from '../lib/utils';
import './index.scss'
import { sidedata } from './data'
import  {newsOrigin} from '../constants.js'

@withRouter
class  ResultNews extends Component {
    constructor(props) {
        super(props)
    }
    render() {
        let UrlKeyword =  window.ParseKeyword( this.props);
        let { item,total_count} = this.props.data;
        let {className} = this.props;
        let style = "ResultNews " + className
        const NewsListView = <div className={style}>
            <div  className="ResultNewsCon">
                <ul>
                    {
                        item.map(function (item, idx) {
                          /* let url;
                          const stockcode = !item.stockcode? '' : Array.isArray(item.stockcode)? item.stockcode : JSON.parse(item.stockcode);
                          if(!_.isEmpty(stockcode)){
                            url= `${legacySiteOrigin}/nav-data/company-by-stock?stock_code=${stockcode[0][0]}&stock_name=${stockcode[0][1]}`
                          } */
                          let titlestr = utils.delHtmlTag(item.title);
                          return <li key={item.id}>
                                    <div className="newsListViewListCon">
                                        <h3>
                                            <a href={`${newsOrigin}/#/search/detail/${md5(item.url)}`}  target="_blank" title={titlestr}  dangerouslySetInnerHTML={{ __html: item.title }}></a>
                                        </h3>
                                        <div className={"currentNewsCon" + (item.first_image_oss ? ' hasImg' : '')}>
                                            {item.first_image_oss && <span className="currentNewsCon__pic" style={{backgroundImage : `url(${item.first_image_oss})`}}></span>}
                                            <div className="currentNewsCon__info">
                                                <p dangerouslySetInnerHTML={{ __html: item.content }}></p>
                                                <div className="sourcefoot">
                                                    <span dangerouslySetInnerHTML={{ __html: item.source_name_s || item.source_name }}></span>
                                                    {item.author && <span className="author" dangerouslySetInnerHTML={{__html:item.author}}></span>}
                                                    <span>{item.publish_time}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                        })
                    }
                </ul>
            </div>
        </div>
        return (
            <div>{NewsListView}</div>
        )
    }
}



// 默认props值
ResultNews.defaultProps = {
    title: '相关资讯',
    data:sidedata.result.data
}
// props 类型
ResultNews.propTypes = {
    title: PropTypes.string,
    data:PropTypes.any
}
export default ResultNews