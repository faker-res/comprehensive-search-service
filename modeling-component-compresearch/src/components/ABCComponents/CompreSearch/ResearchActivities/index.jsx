import React, { Component } from "react";
import PropTypes from "prop-types";
import { withRouter } from "react-router-dom";
import ImageDefault from "../General/theme/default/images/icon-avatar-default.png";
import { Layout, Timeline, Icon } from "antd";
import { mock_data } from "./data.js";
import NoDataTip from "../NoDataTip";
import ask from '../../../../lib/ask';
import moment from "moment";
import styled from 'styled-components';
import isEmpty from 'lodash/isEmpty';
import uuidv4 from 'uuid/v4';
import "./style.scss";


const Divider = styled.div`
	width: 100%;
	height: 14px;
	background: transparent;
`;
export default class ResearchActivities extends Component {
	constructor(props) {
		super(props);
		this.state = {
			loadStatus: 'done', // pending,done, error
			data: []
		}
	}

	componentDidMount = async () => {
		this.loadActivityResults();
	}

	getWeek(time) {
		let sWeek = moment(time).format('dddd');

		switch (sWeek) {
			case 'Monday': sWeek = '星期一';
				break;
			case 'Tuesday': sWeek = '星期二';
				break;
			case 'Wednesday': sWeek = '星期三';
				break;
			case 'Thursday': sWeek = '星期四';
				break;
			case 'Friday': sWeek = '星期五';
				break;
			case 'Saturday': sWeek = '星期六';
				break;
			case 'Sunday': sWeek = '星期日';
				break;
			default:
				break;
		}
		return sWeek;
	}

	loadActivityResults = () => {
		this.setState({ loadStatus: 'pending' });
		const { aName } = this.props;
		ask('ActivitySearch', {
			data: {
				search_word: `keyword:${aName}`,
				sort_pattern: 'time',
				end_time: new Date().getTime(),
				page_size: 2,
				page_num: 1,
				search_log: {
					device_info: window.navigator.userAgent || '',
					input_from: "direct",
					page: 'wx_search',
					request_id: uuidv4(),
					user_id: Cookies.get('userId') || ''
				}
			}
		})
			.then(resp => {
				// let resp = mock_data
				const { code, message, data } = resp;
				// console.log(data)
				const { records = [], total = 0 } = data || {};
				if (code !== 0) {
					throw new Error(`Response Exception:${message},code:${code}`);
				}
				this.setState({
					loadStatus: 'done',
					data: (records || []).map(item => {
						item.speakers = (item.speakers || []).map(_item => _item.name);
						// item.content = item.detail && item.detail.content || '';
						return {
							speakers: (item.speakers || []).map(_item => _item.name),
							time: moment(item.start_time).format('HH:mm'),
							week: this.getWeek(item.start_time),
							title: item.title,
							content: item.detail && item.detail.content || '',
							messageList: [
								`发布人：无`,
								`嘉宾：${item.speakers.join(' ') || '无'}`,
								`主办方：${item.sponsor || '无'}`,
								`活动地点：${item.place || '无'}`,
							]
						}
					}),
				});
			})
			.catch(err => {
				this.setState({ loadStatus: 'error', data: [] });
				console.error(err);
			})
	}


	render() {
		const {  title } = this.props;
		const { loadStatus, data = [] } = this.state
		// console.log(loadStatus)
		return (
			<React.Fragment>
				<div className="ResearchActivities-container">
					<p className="title">{title}</p>
					<div className='AnalystCard-content-container'>
						{
							data.map((item, index) => {
								return <div className="AnalystCard-content">
									<div className='AnalystCard-content-left'>
										<span className='time'>{item.time}</span>
										<span className='line'></span>
										<span className='week'>{item.week}</span>
									</div>
									<div className='AnalystCard-content-right'>
										<ul>
											<li>
												<div className='headWrap'>
													<a href="javascript:void(0)" target='_blank' className='headTitle' title={item.title}>{item.title}</a>
												</div>
												<div className='currentNewsCon'>
													<div className='currentNewsCon__info'>
														<div className='bodyCntWrap' dangerouslySetInnerHTML={{ __html: item.content || "无" }}>
															{/* {item.content} */}
														</div>
														<div className='footWrap'>
															<ul className='tagList'>
																{item.messageList.map((item2) => {
																	return <li className='text'>
																		<span dangerouslySetInnerHTML={{ __html: item2 }}></span>
																	</li>
																})
																}
															</ul>
														</div>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</div>
							})}
						{
							(!data || data.length === 0) &&
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
ResearchActivities.defaultProps = {
	title: "投研活动"
};
// props 类型
ResearchActivities.propTypes = {
	title: PropTypes.string
};
