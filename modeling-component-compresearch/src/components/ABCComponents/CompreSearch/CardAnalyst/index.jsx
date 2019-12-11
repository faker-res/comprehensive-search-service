/**
	@description 分析师卡片组件
	@author ypeng
**/

import React, { Component } from 'react'
// import { translate } from 'react-i18next';
import { Row, Col, Icon, Popover } from 'antd'
import PropTypes from 'prop-types'
// import { legacySiteOrigin, siteDomain } from '../../constants';
import { localClass } from './style.scss'

import ChartAnalyst from '../ChartAnalyst'
import RecentActivity from '../RecentActivity'

// @translate()
export default class CardAnalyst extends Component {
	constructor(props) {
		super();
		let {basic_info = {}, report_stocks = [], dynamic = [], t} = props;
		basic_info = basic_info === null ? {} : basic_info;
		report_stocks = report_stocks === null ? [] : report_stocks;
		dynamic = dynamic === null ? [] : dynamic;
		const ChartAnalystVisiable = !props.report_stocks || props.report_stocks.length === 0 ? false : true;
		this.state = {
			basic_info, //基本信息
			report_stocks,
			dynamic,
			t,
			btn_more_up : false,
			organImgLoad : true,
			ChartAnalystVisiable
		}
	}
	componentWillMount() {

	}
	imgError = ()=>{
		this.setState({
			organImgLoad : false
		});
	}
	render() {
		const {basic_info, report_stocks, dynamic, t, organImgLoad} = this.state;
		// console.log(basic_info)
		const { type } = this.props;
		//同行业排名百分比
		const rate = basic_info.report_num_rate ? Math.round(parseFloat(basic_info.report_num_rate) * 10000) / 100 + '%' : '--'

		const url = 'http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/';

		// const oldWebUrl = `${legacySiteOrigin}/report-search/report-detail?report_id=`;
		const newWebUrl = `/report-detail/article/`

		const dynamic_addHref = dynamic.map( item=> Object.assign({},item,{href:'javascript:void(0)'/*newWebUrl + item.id*/}) );

		return (
			<div className={localClass}>
				<div className="analysterInfo">
					<Row gutter={16}>
						<Col span={2} className="tl">
							<div className="picWrap">
								<div className="pic" style={{backgroundImage:basic_info.image ? "url(" + basic_info.image + ")" : ''}}></div>
								{/* <a className="btn_order">+ {t("CardAnalyst.order",{defaultValue:"订阅"})}</a> */}
							</div>
						</Col>
						<Col span={4} className="tl">
							<div className="con name">{basic_info.name ? basic_info.name : '--'}
								<Popover placement="bottom" content={(
									<div className="ypeng-antd-popover-contact">
										<div className="item"><Icon type="phone" /><span>{basic_info.tel ? basic_info.tel : '暂无电话'}</span></div>
										<div className="item"><Icon type="mail" /><span>{basic_info.email ? basic_info.email : '暂无邮箱'}</span></div>
									</div>
								)}><Icon className="idcard" type="idcard"/></Popover>
							</div>
							<p className="tit">{basic_info.direction ? basic_info.direction : '行业未知'}</p>
						</Col>
						<Col span={6}>
							{
								type === 'Analyst' && <div className="con">
								{
									organImgLoad ? <img onError={this.imgError} src={url + basic_info.organ_logo} alt="暂无图片" title={basic_info.organ} /> : basic_info.organ ? basic_info.organ : "暂无图片"
								}
								</div>
							}
							{
								type === 'InternalAlyst' && <div className="con">{basic_info.dept_name || ""}</div>
							}
							<p className="tit">{type === 'Analyst' ? '所在机构' : '所在部门'}</p>
						</Col>
						<Col span={6}>
							<div className="con">{basic_info.report_num || basic_info.report_num === 0 ? basic_info.report_num : '--'}{"篇"}</div>
							<p className="tit">数量击败同行业<span className="mark">{rate}</span>{"的分析师"}</p>
						</Col>
						{
							basic_info.rank || basic_info.honor_num ?
							<Col span={6}>
								<div className="con">{`第${basic_info.rank ? basic_info.rank : '--'}名`} { basic_info.time && <span className="mark">{basic_info.time}</span> }</div>
								<p className="tit">{"连续"}<span className="mark">{basic_info.honor_num ? basic_info.honor_num : '--'}</span>{"次登上新财富排行榜"}</p>
							</Col>
							: ''
						}
					</Row>
				</div>
				<div className={`recentAnalst clearfix${this.state.ChartAnalystVisiable ? '' : ' hideChartAnalyst'}`}>
					<div className="fl">
						<ChartAnalyst title="研究覆盖股票"
							reportStocks={report_stocks}
							basicInfo={basic_info} />
					</div>
					<div className="fr">
						<RecentActivity
							title="最新动态"
							list={dynamic_addHref} />
					</div>
				</div>
				<div className="analyst-info-footer">
					{/* <a href={`/entity-search/analyst?id=${basic_info.id}`}>查看更多信息<span></span></a> */}
					<a href={`/entity-search/analyst?type=${type}&id=${basic_info.id}`}>查看更多信息<span></span></a>
				</div>
			</div>
		)
	}
}

// props 类型
CardAnalyst.propTypes = {
	basic_info: PropTypes.object,
	dynamic: PropTypes.array,
	report_stocks : PropTypes.array
}
