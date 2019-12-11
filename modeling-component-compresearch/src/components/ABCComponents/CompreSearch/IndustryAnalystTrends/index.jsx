import React, { Component } from "react";
import PropTypes from "prop-types";
import { withRouter } from "react-router-dom";
// import { translate } from "react-i18next";
// import { legacySiteOrigin } from "../../constants";
import RecentAnalystActivity from "../../CompreSearch/RecentAnalystActivity";
import ImageDefault from "../General/theme/default/images/icon-avatar-default.png";
import { Layout, Timeline, Icon } from "antd";
import { AnalystCardData, AnalystActivityData } from "./data.js";
import NoDataTip from "../NoDataTip";
import moment from "moment";
import styled from 'styled-components';
// import { siteDomain } from "../../constants";
import "./style.scss";


const Divider = styled.div`
	width: 100%;
	height: 14px;
	background: transparent;
`;
export default class IndustryAnalystTrends extends Component {
	constructor(props) {
		super(props);
		// console.log(props)
		this.state = {
			loadStatus: 'done', // pending,done, error
			data: {}
		}
	}


	render() {
		const { t, title, analystCardData, analystActivityData,list } = this.props;
		// console.log(list)
		const { loadStatus } = this.state
		return (
			<React.Fragment>
			<div className="IndustryTrends-container">
				<p className="title">{title}</p>
				<div className="AnalystCard-content">
				<RecentAnalystActivity
							// title="最新动态"
							list={list}/>
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
IndustryAnalystTrends.defaultProps = {
	// analystCardData: AnalystCardData.data,
	// analystActivityData: AnalystActivityData.data,
	title: "最新动态"
};
// props 类型
IndustryAnalystTrends.propTypes = {
	title: PropTypes.string,
	list:PropTypes.array
};
// export default withRouter(translate()(AnalystCard));
