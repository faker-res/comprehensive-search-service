import React, { Component } from "react";
import PropTypes from "prop-types";
import { withRouter } from "react-router-dom";
import ImageDefault from "../General/theme/default/images/icon-avatar-default.png";
import { Layout, Timeline, Icon } from "antd";
import NoDataTip from "../NoDataTip";
import moment from "moment";
import styled from 'styled-components';
import classnames from 'classnames'
import "./style.scss";
import MOCK_DATA from './mock.json';

const Divider = styled.div`
	width: 100%;
	height: 14px;
	background: transparent;
`;
export default class AnalystCard extends Component {
	constructor(props) {
		super(props);
		this.state = {
			// analystState: 'done', // pending,done, error
			data: {},
			isExternal: this.props.isExternal
		}
	}
	componentWillMount() {
	}
	componentDidMount() {
		
	}
	render() {
		const { t, title, analystCardData, analystActivityData, analystState } = this.props;
		const { isExternal } = this.state
		// console.log(analystCardData)
		return (
			<React.Fragment>
				<div className={classnames("AnalystCard-container", { 'AnalystCard-external': isExternal, 'AnalystCard-internal': !isExternal })}>
					{/* <p className="title">{title}</p> */}
					<div className="AnalystCard-content">
						<div className="AnalystCard-up">
							<div className="up-left">
								<div className="Analyst-image">
									<img
										src={
											(analystCardData.image === "" || analystCardData.image === null)
												? ImageDefault
												: analystCardData.image
										}
										alt=""
									/>
								</div>
								<div className="Analyst-info">
									<div className="Analyst-name-container">
										<span className="Analyst-name">
											{analystCardData.name}
										</span>
									</div>
									<div className="Analyst-info-item">
										{analystCardData.direction ? analystCardData.direction : '无'}
									</div>
								</div>
							</div>
							<div
								className="up-right"
							// style={{
							// 	marginRight: analystCardData.rank
							// 		? "none"
							// 		: "180px"
							// }}
							>
								<div className="up-right-item">
									<div className="Analyst-index-value">
										<div className="img">

											{/* <a href={`/entity-search/broker/${analystCardData.c_id}`} target='_blank'> */}
											{/* <a href='' target='_blank'> */}
											{analystCardData.organ_logo ? <img
													src={analystCardData.organ_logo}
													alt=""
													hidden={!analystCardData.organ_logo}
												/> : <p>{analystCardData.organ}</p>}
												
											{/* </a> */}
										</div>
									</div>
									<div
										className="Analyst-index-value"
									// style={{
									// 	display: analystCardData.rank
									// 		? "inline-block"
									// 		: "none"
									// }}
									>
										第{analystCardData.rank || "--"}名{isExternal && <span className="time">
											{analystCardData.time || "--"}
										</span>}
									</div>
									{isExternal ? <span className="Analyst-index-value">
										{analystCardData.average_days || "--"}天/{analystCardData.head_percent ||
											"--"}%
								</span> : <span className="Analyst-index-value">
											{analystCardData.average_days || "--"}天
									</span>
									}
								</div>
								<p className="up-right-item">
									{isExternal ? <span className="Analyst-index-name">
										所在机构
									</span> : <span className="Analyst-index-name">
											所在部门
									</span>}
									{isExternal ? <span
										className="Analyst-index-name"
									// style={{
									// 	display: analystCardData.rank
									// 		? "inline-block"
									// 		: "none"
									// }}
									>
										新财富榜单{analystCardData.h_direction ||
											"--"}
									</span> : <span
										className="Analyst-index-name"
										// style={{
										// 	display: analystCardData.rank
										// 		? "inline-block"
										// 		: "none"
										// }}
									>
											上传报告量
								</span>}
									{isExternal ? <span className="Analyst-index-name">
										平均达成天数/头部百分比
								</span> : <span className="Analyst-index-name">
											上传报告量
								</span>
									}
								</p>
							</div>
						</div>
						{isExternal && <div className="AnalystCard-down">
							<div className="down-left">
								<div className="down-left-up">
									<p className="down-title">人物介绍</p>
									<div className="introduce">
										{analystCardData.summary || (
											<NoDataTip style={{ width: "336px" }} />
										)}
									</div>
								</div>
							</div>
							<div className="down-right">
								<Layout>
									<Timeline>
										<Timeline.Item
											dot={
												<Icon
													type="clock-circle-o"
													style={{ fontSize: "16px" }}
												/>
											}
											color="#DCE2E9"
										>
											<p className="work-item active-title">
												工作履历
										</p>
										</Timeline.Item>
										{analystCardData.resume &&
											analystCardData.resume[0] ? (
												<p
													className="down-left-down-item"
													hidden={!analystCardData.resume}
												>
													{analystCardData.resume[0]
														.start_time !== null
														? moment(
															analystCardData.resume[0]
																.start_time
														).format("YYYY/MM/DD")
														: "--"}—
			{analystCardData.resume[0].end_time !==
														null
														? moment(
															analystCardData.resume[0]
																.end_time
														).format("YYYY/MM/DD")
														: "至今"}
												</p>
											) : (
												<NoDataTip style={{ width: "336px" }} />
											)}
										<p
											className="down-left-down-item"
											hidden={!analystCardData.resume}
										>
											{analystCardData.resume &&
												analystCardData.resume[0] &&
												analystCardData.resume[0].company_name}
										</p>
										<p
											className="down-left-down-item"
											hidden={!analystCardData.resume}
										>
											职位：{analystCardData.resume &&
												analystCardData.resume[0] &&
												analystCardData.resume[0].position}
										</p>
									</Timeline>
								</Layout>
							</div>
						</div>}
					</div>
				</div>
				{
					analystState === 'done' && <Divider />
				}
			</React.Fragment>
		);
	}
}

// 默认props值
AnalystCard.defaultProps = {
	analystCardData: MOCK_DATA.data,
	// analystActivityData: AnalystActivityData.data,
	title: "基本信息"
};
// props 类型
AnalystCard.propTypes = {
	title: PropTypes.string
};
// export default withRouter(translate()(AnalystCard));
