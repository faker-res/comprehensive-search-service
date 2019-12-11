/**
 * @description MiniChart 组件
 * @date 2018.01.16
 * @author wsh
 */

import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import classnames from 'classnames'
import { Icon, Popover } from 'antd'
import StockPopup from '../StockPopup';
import Loading from '../Loading';
import _ from 'lodash'
import PropTypes from 'prop-types'
import { localClass } from './style.scss'
import ask from '../../../../../lib/ask'
import Honor from '../Report/Honor';
import { Tag } from 'antd';
import resources from '../assets/image/icons/my_resources.png'

export default class ListItem extends Component {
	constructor(props) {
		super();
		this.state = {
			tagType: 'text',
			stockPopData: {
				load: false,
				data: null
			}
		}
		// console.log('this.props.source' + this.props.source)
	}
	static defaultProps = {
		headTag: [],
		headTitle: {},
		footer: [],
	}
	static propTypes = {
		headTag: PropTypes.array,
		headTitle: PropTypes.object,
		footer: PropTypes.array,
	}
	delHtmlTag = (str) => {
		return str.replace(/<.*>(.*?)<\/.*?>/gi, function ($1, $2) { return $2; });
	}
	//headTag-股票弹出卡片
	companyhover = (code) => {
    if (!this.state.stockPopData.loaded){
      ask('AnalystStockInfo',{
        params : {
          stockCode : code
        }
      }).then(res => {
        this.setState(prevState => {
          return {
            stockPopData : {
              loaded : true,
              data : res.data
            }
          }
        } )
      })
    }
	}
	itemClick = (e, param, callBack) => {
		typeof callBack === "function" && callBack(e, param);
	}
	renderByTagType = (item) => {// 底部标签根据类型渲染不同的样式
		const { type, con, url, onItemClick } = item;
		let html = '';
		switch (type) {
			case "text": html = <span>{con}</span>; break;
			case "intelPage": html = <a href={url} target='_blank'>{con}</a>; break;
			case "link": html = <a href={url} dangerouslySetInnerHTML={{ __html: con }} onClick={e => { this.itemClick(e, item, onItemClick) }}></a>; break;
			case "download": html = this.renderTagDownload(item); break;
			case "download_s2": html = <a href={url} onClick={e => { this.itemClick(e, item, onItemClick) }}>{con.toUpperCase()}</a>; break;
			case "route": html = <span dangerouslySetInnerHTML={{ __html: con }}></span>; break;
			case "arr_author": html = this.renderTagAuthor(item); break;
			case "arr_dataFrom": html = this.renderTagDataFrom(item); break;
			default: html = '';
		}
		return html;
	}
	renderTagDownload = (item) => {
		const { con, url, onItemClick } = item;
		return <a className="tagDownload" href={url} onClick={e => { this.itemClick(e, item, onItemClick) }}><span>{con[0]}页</span><span>{con[1].toUpperCase()}</span></a>;
	}
	renderTagDataFrom = (item) => {
		const { con } = item;
		return con.map((it, id) => <span key={id} className="dataFrom">{it}</span>);
	}
	renderTagAuthor = (item) => {
		const { con } = item;
		return con.map((it, id) =>
		it.name && (<span className="author" key={id}>
				{it.id && <Honor id={it.id} />}
				<span dangerouslySetInnerHTML={{ __html: it.name }}></span>
				{/* <Link dangerouslySetInnerHTML={{__html:it.name}} to={it.url}></Link> */}
			</span>)
		);
	}
	getRatingType = (rating) => {
		let str = '';
		switch (rating) {
			case "增持": str = "holdup"; break;
			case "中性": str = "holdsmooth"; break;
			case "减持": str = "holddown"; break;
			case "卖出": str = "sale"; break;
			default: str = '';
		}
		return str
	}
	render() {
		const { headTag, headTitle, footer, date, body,imgItem, source, type } = this.props;
		const { stockPopData } = this.state;
		return (
			<div className={localClass}>
				<div className="headWrap">
					{/* 股票弹出卡片 */}
					{
						// headTag.map((tag, index) =>
						// (tag.stockCode || tag.stockName) &&
						// <Popover key={index} placement="bottom" overlayClassName="ypeng-antd-StockPopup" onMouseEnter={() => { this.companyhover(tag.stockCode); }} content={
						// 	!stockPopData.loaded ? <div className="noDataTipWrap"><Loading /></div> : stockPopData.data ? <StockPopup data={stockPopData.data} /> : <div className="noDataTipWrap"><p className="noDataTips">暂无数据</p></div>
						// }>
						// 	{/* <span className="name">{`${tag.stockCode} ${tag.stockName}`}</span> */}
						// 	{tag.routeUrl ? <Link className="name" to={tag.routeUrl}>{`${tag.stockCode} ${tag.stockName}`}</Link> : <a className="name">{`${tag.stockCode} ${tag.stockName}`}</a>}
						// </Popover>
						// )
					}
					<a className="headTitle" target="_blank" href={headTitle.href} onClick={e => { this.itemClick(e, { href: headTitle.href, text: headTitle.text }, headTitle.onTitleClick) }} dangerouslySetInnerHTML={{ __html: headTitle.text }} title={this.delHtmlTag(headTitle.text)}></a>
					{/* 研报波动标签 */}
					{headTitle.rating && <span className={classnames("rating", this.getRatingType(headTitle.rating))}>{headTitle.rating}</span>}
					{/* 公报透视标签 */}
					{headTitle.xRay && <a className="xRay iconfont_notice" href={headTitle.xRay} dangerouslySetInnerHTML={{ __html: '&#xe60b; 公告透视' }} target="_blank"></a>}
				</div>
				<div className={"currentNewsCon" + (imgItem ? ' hasImg' : '')}>
					{!!imgItem && <span className="currentNewsCon__pic" style={{ backgroundImage: `url(${imgItem})` }}></span>}
					<div className='currentNewsCon__info'>
						{!!body &&
							<div className="bodyCntWrap" dangerouslySetInnerHTML={{ __html: body }}></div>
						}
						<div className="footWrap">
							{date && !body && <span className="dateright">{date}</span>}
							<ul className="tagList">
								{footer.map((tag, index) =>!_.isEmpty(tag.con) && <li key={index} className={tag.type === "arr_dataFrom" ? "noborder" : tag.type === "text" ? "text" : tag.type === "link" || tag.type === "intelPage" || tag.type === "download_s2" ? "link" : ""}>{this.renderByTagType(tag)}</li>)}
                {type && type == 'internal_report' && <li className='my_resources'><img src={resources}/><span>我的资源</span></li>}
                {/*{source && source == 'internal_report' ? <li><Tag>我的资源库</Tag></li> : ''}*/}
								{body && <li><span>{date}</span></li>}
							</ul>
						</div>
					</div>
				</div>
			</div>
		);
	}
}

