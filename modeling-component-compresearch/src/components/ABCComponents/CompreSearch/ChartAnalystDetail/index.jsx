/**
 * @description 分析师覆盖股票组件
    @author ypeng
**/

import React, { Component } from 'react'
import PropTypes from 'prop-types'
// import { translate } from 'react-i18next';
import { Row, Col, Icon, Popover, Spin } from 'antd'
import ask from '../../../../lib/ask'
import { localClass } from './style.scss'
import AreaChartWidthPoints from '../AreaChartWidthPoints'
import NoDataTip from '../NoDataTip'
// import Loading from '../Loading'
// import ErrorTips from '../ErrorTips'
// import StockPopup from '../StockPopup'	
import StockInfoDetail from '../StockInfoDetail'
import { reaction } from 'mobx';

// @translate()
export default class ChartAnalystDetail extends Component {

	static defaultProps = {
		title: "",
		reportStocks: {},
		basicInfo: {}
	}

	static propTypes = {
		title: PropTypes.string,
		reportStocks: PropTypes.array,
		basicInfo: PropTypes.object,
	}
	constructor(props) {
		super();
		this.state = {
			basic_info: props.basicInfo,
			index: props.index || 0,
			title: '研究覆盖股票',
			tabList: [{
				name: '最新研究',
				index: 0,
				loaded: false, //默认加载最新研究
				companyList: []
			}, {
				name: '最多研究',
				index: 0,
				loaded: false,
				companyList: []
			}],
			visiable: true
		}
	}
	componentDidMount() {
		//国际化
		this.setLanguage();

		//整理各选项卡数据
		this.setTabCon(this.state.index, this.props);
		//加载当前图标数据
		this.setCurChart(this.state.index, this.state.tabList[this.state.index].index);

	}
	componentWillReceiveProps(nextProps) {
		//国际化 - 收到新的props时
		this.setLanguage();
	}
	setLanguage = () => {
		this.setState((prevState, { t }) => {
			prevState.title = "研究覆盖股票";
			prevState.tabList[0].name = "最新研究";
			prevState.tabList[1].name = "最多研究";
			return {
				title: prevState.title,
				tabList: prevState.tabList
			}
		});
	}
	setCurChart = (pIndex, cIndex) => {
		let _this = this;
		this.setState((prevState) => {
			const tabList = prevState.tabList;
			// const company_index = tabList[pIndex].index;
			const companyList = tabList[pIndex].companyList;
			if (companyList.length === 0 || companyList[cIndex].chartData.loaded) return false;
			ask('AnalystStockChart', {
				params: {
					// author : prevState.basic_info.name,
					// organ : prevState.basic_info.organ,
					stockCode: companyList[cIndex].code,
				}
			}).then((res) => {
				if (res.code === 200) {
					companyList[cIndex].chartData.data = res.data;
					companyList[cIndex].chartData.loaded = true;
					_this.setState({
						tabList
					});
				}
			}, (msg) => { console.log(msg) });

		});
	}
	setTabCon = (index, { reportStocks = [] }) => {
		this.setState((prevState) => {
			const tabList = prevState.tabList;
			if (Array.isArray(reportStocks)) tabList[index].companyList = this.stockListDataPicker(reportStocks);
			tabList[index].loaded = true;
			return {
				tabList
			}
		});
	}
	stockListDataPicker = (oData) => {
		//研究覆盖股票list
		let _this = this, arr = [];
		oData.forEach((item) => {
			let code = item.abc_code;
			let name = item.sec_name;
			let date = _this.dateFormat(item.last_time);
			let count = `研究${isNaN(parseFloat(item.total)) ? '0' : parseFloat(item.total)}次`;

			let price = isNaN(parseFloat(item.current_price)) ? '--' : parseFloat(item.current_price);
			let zdf = isNaN(parseFloat(item.zdf)) ? '--' : parseFloat(item.zdf);
			let zdf_str = '--', zdp_str = '--';

			if (zdf !== '--') {
				let zdf_fix = (Math.round(zdf * 10000) / 100).toFixed(2);
				zdf_str = zdf > 0 ? `+${zdf_fix}` : `${zdf_fix}`;

				if (price !== '--') {
					let zdp = zdf === -1 ? -price : price * zdf / (1 + zdf);
					let zdp_fix = (Math.round(zdp * 100) / 100).toFixed(2);
					zdp_str = zdf > 0 ? `+${zdp_fix}` : `${zdp_fix}`;
				}
			}

			arr.push({
				code,
				name,
				date,
				count,
				price,
				zdp: zdp_str,
				zdf_p: zdf_str,
				zdf,
				chartData: {
					loaded: false,
					data: {}
				},
				stockPopData: {
					loaded: false,
					data: null
				}
			});
		});
		return arr;
	}
	handleTab = (id) => {
		if (id !== this.state.index) {
			this.setState({ index: id });

			//选项卡中数据若没有加载则请求数据
			if (!this.state.tabList[id].loaded) {
				ask('AnalystStock', {
					params: {
						id: this.state.basic_info.id,
						tab: id + 1,
					}
				}).then((res) => {
					if (res.code === 200) {
						let reportStocks = res.data;
						this.setTabCon(id, { reportStocks });
						this.setCurChart(id, this.state.tabList[id].index);
					}
				}, (msg) => { console.log(msg) });
			}

			this.setCurChart(id, this.state.tabList[id].index);

		}
	}
	handleLiTab = (index, id) => {
		let curList = this.state.tabList[this.state.index];
		if (id !== curList.index) {
			curList.index = id;
			this.setState({ curList });
			this.setCurChart(index, id);
		}
	}
	dateFormat(val, timeFromNow) {
		let str = val + '', date = '', time;
		if ((str.indexOf('-') > -1 || str.indexOf('/') > -1)) {
			time = new Date(str.replace(/-/g, '/')).getTime();
		} else if (!isNaN(parseFloat(val)) && parseFloat(val) !== 0) {
			const num = parseFloat(val);
			if ((num + '').length === 10) {
				time = num * 1000;
			} else if ((num + '').length === 13) {
				time = num;
			}
		}
		date = this.timeFromNowFormat(time, timeFromNow);
		return date;
	}
	timeFromNowFormat(oldTime, timeFromNow) {
		const now = new Date();
		const now_time = new Date().getTime();
		const now_Y = now.getFullYear();
		const now_M = now.getMonth();
		const now_D = now.getDate();
		const today = `${now_Y}/${now_M + 1}/${now_D} 00:00:00`;
		const today_time = new Date(today).getTime();
		const interval = now_time - oldTime;

		if (interval < 0) {
			return "未来";
		} else if (timeFromNow === 's' && interval < 60000) {//1分钟内
			return '刚刚';
		} else if (timeFromNow === 's' && interval < 3600000) {//1小时内
			return `${Math.floor(interval / 60000)}分钟前`;
		} else if ((timeFromNow === 's' || timeFromNow === 'd') && oldTime >= today_time) {
			return '今天';
		} else if ((timeFromNow === 's' || timeFromNow === 'd') && oldTime >= today_time - 3600000 * 24) {
			return `昨天`;
		} else if ((timeFromNow === 's' || timeFromNow === 'd') && oldTime >= today_time - 3600000 * 24 * 2) {
			return `前天`;
		} else {
			const oldDate = new Date(oldTime);
			const Y = oldDate.getFullYear() < 10 ? '0' + oldDate.getFullYear() : oldDate.getFullYear();
			const M = oldDate.getMonth() + 1 < 10 ? '0' + (oldDate.getMonth() + 1) : oldDate.getMonth() + 1;
			const D = oldDate.getDate() < 10 ? '0' + oldDate.getDate() : oldDate.getDate();
			return `${Y}-${M}-${D}`;
		}
	}
	companyhover = (pIndex, cIndex, code) => {
		// if(!this.state.tabList[pIndex].companyList[cIndex].stockPopData.loaded){
		// 	ask('stockPopApi',{
		// 		params : {
		// 			stock_code : code
		// 		}
		// 	}).then(res => {
		// 		this.setState( prevState => {
		// 			const tabList = prevState.tabList;
		// 			const companyList = tabList[pIndex].companyList;
		// 			companyList[cIndex].stockPopData.loaded = true;
		// 			companyList[cIndex].stockPopData.data = res.data;
		// 			return {
		// 				tabList
		// 			}
		// 		} )
		// 	})
		// };
	}
	render() {
		const { moreBtnClick = () => { }, moreBtnHref } = this.props;
		// console.log(this.state.tabList)
		return (
			<div className={localClass}>
				<div className="chartAnalyst clearfix">
					<a className="more fr" target="_blank" href={moreBtnHref} onClick={(e) => { moreBtnClick(e); }} hidden>更多 <Icon type="right" /></a>
					{/* <h3 className="title">{this.state.title}</h3> */}
					<div className="btnWrap">
						{
							this.state.tabList.map((tabCon, id) => <a key={id} onClick={() => this.handleTab(id)} className={id === this.state.index ? 'on' : ''}>{tabCon.name}</a>)
						}
					</div>
					{
						this.state.tabList.map((tabCon, index) => {
							return (
								<div className="analystListWrap" key={index} style={{ display: index === this.state.index ? '' : 'none' }}>
									{
										tabCon.companyList.length > 0 ?
											// <div>
											// 	{
													tabCon.companyList.map((item, id) =>
														<div className='area-container'>
															<div className="chartArea" style={{ display: tabCon.index === id ? '' : 'none' }}>
																{
																	!item.chartData.loaded ? <Spin />
																		: item.chartData.data && item.chartData.data.stock_price && item.chartData.data.stock_price.length > 0 ?
																			<AreaChartWidthPoints data={item.chartData.data} />
																			: <p className="noDataTips">暂无数据</p>}
															</div>
															<ul className="analystList">
																<li key={id} onClick={() => this.handleLiTab(index, id)} className={`index${id} ${id === tabCon.index ? 'on' : ''}`}>
																	<StockInfoDetail {...item} />
																</li>
															</ul>
														</div>
													)
											// 	}
											// </div>
											: tabCon.loaded ? <NoDataTip /> : <Row type="flex" justify="center"><Spin /></Row>
									}
								</div>
							)
						})
					}
				</div>
			</div>
		)
	}
}
