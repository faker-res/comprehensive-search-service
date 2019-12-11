/**
	@description 外部研究员卡片组件
	@author ypeng
**/

import React, { Component } from 'react'
// import { translate } from 'react-i18next';
import { Row, Col, Icon, Popover } from 'antd'
import PropTypes from 'prop-types'
// import { legacySiteOrigin, siteDomain } from '../../constants';
import { localClass } from './style.scss'

import ChartAnalystDetail from '../ChartAnalystDetail'
import RecentActivity from '../RecentActivity'

// @translate()
export default class IndustryTrendsDetail extends Component {
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
		const { type } = this.props;
		//同行业排名百分比
		const rate = basic_info.report_num_rate ? Math.round(parseFloat(basic_info.report_num_rate) * 10000) / 100 + '%' : '--'

		const url = 'http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/';

		// const oldWebUrl = `${legacySiteOrigin}/report-search/report-detail?report_id=`;
		const newWebUrl = `/report-detail/article/`

		const dynamic_addHref = dynamic.map( item=> Object.assign({},item,{href:'javascript:void(0)'/*newWebUrl + item.id*/}) );

		return (
			<div className={localClass} id='test'>
				{/* <div className={`recentAnalst clearfix${this.state.ChartAnalystVisiable ? '' : ' hideChartAnalyst'}`}> */}
					{/* <div className="fl">
						<ChartAnalystDetail title="研究覆盖股票"
							reportStocks={report_stocks}
							basicInfo={basic_info} />
					</div> */}
					<div className="fr">
						<RecentActivity
							title="最新动态"
							list={dynamic_addHref} />
					</div>
				{/* </div> */}
				{/* <div className="analyst-info-footer"> */}
					{/* <a href={`/entity-search/analyst?id=${basic_info.id}`}>查看更多信息<span></span></a> */}
					{/* <a href={`/entity-search/analyst`}>查看更多信息<span></span></a>
				</div> */}
			</div>
		)
	}
}

// props 类型
IndustryTrendsDetail.propTypes = {
	basic_info: PropTypes.object,
	dynamic: PropTypes.array,
	report_stocks : PropTypes.array
}
