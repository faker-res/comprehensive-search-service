import React, { Component } from "react";
import PropTypes from "prop-types";
import { withRouter } from "react-router-dom";
import ImageDefault from "../General/theme/default/images/icon-avatar-default.png";
import { Layout, Timeline, Icon } from "antd";
import { mock_data } from "./data.js";
import NoDataTip from "../NoDataTip";
import ask from '../../../../lib/ask';
import classnames from 'classnames';
import moment from "moment";
import styled from 'styled-components';
import isEmpty from 'lodash/isEmpty';
import uuidv4 from 'uuid/v4';
// import { siteDomain } from "../../constants";
import "./style.scss";
import { utils } from '../../../../lib/until';

const Divider = styled.div`
	width: 100%;
	height: 14px;
	background: transparent;
`;
// 评估
const RATING_ENUM = {
	'推荐': 'buyin',
	'买入': 'buyin',
	'卖出': 'saleout',
	'增持': 'addbuy',
	'减持': 'removebuy',
	'中性': 'hold'
};
export default class ResearchAnalystReport extends Component {
	constructor(props) {
		super(props);
		this.state = {
			loadStatus: 'done', // pending,done, error
			data: [],
			// 默认展示摘要数
			defaultSummaryCount: 2
		}
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
     * 移除文本中的html标签
     * @param {string} htmlText 待清洗的html内容 demo: <font color="red">顺丰速递</font> => 顺丰速递
     */
	removeDomOfText(htmlText = '') {
		return htmlText ? htmlText.replace(/<\/?[^>]+>/g, '') : '';
	}
	componentDidMount = async () => {
		this.loadData();
	}
	loadData() {
		ask('PrivateReportSearch', {
			data: {
				end_time: new Date().getTime(),
				page_num: 0,
				page_size: 8,
				search_word: { 'keyword': this.props.aName },
				involves: [],
				prior: 0,
				// search_log: window.search_log
			},
		}).then((resp) => {
			// console.log(resp)
			// resp = mock_data;
			let { data = {} } = resp;
			let { resultList = [] } = this.state;
			let result = Object.assign([], resultList.concat(data.item || []));

			this.loading = false;
			this.setState({
				loading: false,
				resultList: result,
				// curPage: reload ? 1 : curPage + 1,
				// loadMore: (data.item || []).length >= pageSize
			});

			// onLoadSuccess && onLoadSuccess(resp, reload);
		}).catch(() => {
			this.loading = false;
			this.setState({ loading: false, loadMore: false });

			// onLoadFail && onLoadFail(reload);
		});
	}



	render() {
		const { t, title, analystCardData, analystActivityData } = this.props;
		const { loadStatus, data = [], resultList, defaultSummaryCount } = this.state
		// console.log(resultList)
		return (
			<React.Fragment>
				<div className="ResearchReport-container">
					<p className="title">{title}</p>
					<div className='ResearchReport-content-container'>
						{
							!!resultList && resultList.map((item, index) => {
								let {
									summary: paragraph = [],
									analyst_honor = [],
									content = '',
									alg_security_id = '',
									alg_security_name = '',
									rating = '',
									from = {}
								} = item;
								// download key
								let downloadKey = ((item.file_url.match(/[^\?\/]+\?/) || [])[0] || '').replace(/\?/, '');
								if (utils.getObjType(paragraph) != 'array') {
									paragraph = [paragraph];
								} else {
									paragraph = paragraph || [];
								}
								// console.log(item)
								return <div className="ResearchReport-content">
									<div className={'item-header'}>
										{
											item.alg_security_id || item.alg_security_name ? (<div className="item-stockname JS-stockname" onClick={this.handleClick} title={(item.alg_security_id && item.alg_security_id.replace(/<\/?[^>]+>/g, '')) + ' ' + (item.alg_security_name && item.alg_security_name.replace(/<\/?[^>]+>/g, ''))}>
												<span className="stock-name JS-stockPopup JS-secondSearch">{(item.alg_security_id && item.alg_security_id.replace(/<\/?[^>]+>/g, '')) + ' ' + (item.alg_security_name && item.alg_security_name.replace(/<\/?[^>]+>/g, ''))}</span>
											</div>) : null
										}

										<span className={'item-title'}
											onClick={this.handleClick}
											title={this.removeDomOfText(item.source_title || '')}
											dangerouslySetInnerHTML={{
												__html: item.source_title
											}}>
										</span>

										{!!item.rating && <span onClick={this.handleClick} className={`rating JS-secondSearch ${RATING_ENUM[item.rating.replace(/<\/?[^>]+>/g, '')]}`}>{item.rating.replace(/<\/?[^>]+>/g, '') || ''}</span>}
									</div>

									<div className="item-bottom">
										{!!item.alg_publish_name && <span className="source-tag">
											<span className="JS-secondSearch" dangerouslySetInnerHTML={{
												__html: item.alg_publish_name ? item.alg_publish_name : '--'
											}}></span>
										</span>}
										{!!item.industry_name && <span>
											<span className="JS-secondSearch" dangerouslySetInnerHTML={{
												__html: item.industry_name ? item.industry_name : '--'
											}}></span>
										</span>}
										{!!item.report_type && <span className="notice_type">
											<span className="JS-secondSearch" dangerouslySetInnerHTML={{
												__html: item.report_type ? item.report_type : '--'
											}}></span>
										</span>}
										{!!item.alg_author_name && <span className="item-author">
											<span className="JS-secondSearch" dangerouslySetInnerHTML={{
												__html: item.alg_author_name ? item.alg_author_name : '--'
											}}></span>
										</span>}
										{!!item.alg_rating && <span dangerouslySetInnerHTML={{
											__html: item.alg_rating ? item.alg_rating : '--'
										}}></span>}


										{!!item.file_pages && <span className='item-page-file'>
											<span className="item-page-count">
												{item.file_pages || 0}页
                        					</span>
											<span className='source-type'>
												<a className='sourceDown' title="点击下载源文件" href={`/search-api/download/report?file_key=${downloadKey}&file_name=${item.source_title && item.source_title.replace(/<\/?[^>]+>/g, '')}&file_type=${item.filetype}`}>
													<i className='sourceType'>{(item.filetype || '--').replace(/\./g, '').toUpperCase()}</i><Icon type="download" />
												</a>
											</span>
										</span>}

										{/* <span className='filePages_Type'>
											{!!item.file_pages && <span>{item.file_pages || 0}页</span>}
											<span>
												<a href={`/search-api/download/report?file_key=${downloadKey}&file_name=${item.source_title && item.source_title.replace(/<\/?[^>]+>/g, '')}&file_type=${item.filetype}`} target="_blank" title="点击下载源文件">{(item.filetype || '--').replace(/\./g, '').toUpperCase()}
												</a>
											</span>
										</span> */}

										{!!from.name && <span>发件人：<span dangerouslySetInnerHTML={{
											__html: from.name ? from.name : '--'
										}}></span>&nbsp;&nbsp;
                                        <span dangerouslySetInnerHTML={{
												__html: from.address ? from.address : ''
											}}></span>
										</span>}
										{!!item.time && <i className="item-date fr">{item.time ? this.formatDate(new Date(item.time * 1000), 'YYYY/MM/DD') : '--'}</i>}
									</div>
								</div>
							})}
						{
							(!resultList || resultList.length === 0) &&
							<NoDataTip />
						}
					</div>
				</div>
				{
					loadStatus === 'done' && <Divider />
				}
			</React.Fragment>
		);
	}
}

// 默认props值
ResearchAnalystReport.defaultProps = {
	// analystCardData: AnalystCardData.data,
	// analystActivityData: AnalystActivityData.data,
	title: "相关研报"
};
// props 类型
ResearchAnalystReport.propTypes = {
	title: PropTypes.string
};
// export default withRouter(translate()(AnalystCard));
