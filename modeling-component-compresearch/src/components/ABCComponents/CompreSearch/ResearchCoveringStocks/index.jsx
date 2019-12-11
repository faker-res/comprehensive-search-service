import React, { Component } from "react";
import PropTypes from "prop-types";
import { withRouter } from "react-router-dom";
// import { translate } from "react-i18next";
// import { legacySiteOrigin } from "../../constants";
// import RecentActivity from "../../components/RecentActivity";
import ImageDefault from "../General/theme/default/images/icon-avatar-default.png";
import { Layout, Timeline, Icon } from "antd";
// import { AnalystCardData, AnalystActivityData } from "./data.js";
import ChartAnalystDetail from '../ChartAnalystDetail';
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
export default class ResearchCoveringStocks extends Component {
	constructor(props) {
		super();
		// console.log(props)
		let {basic_info = {}, report_stocks = [], dynamic = []} = props;
		basic_info = basic_info === null ? {} : basic_info;
		report_stocks = report_stocks === null ? [] : report_stocks;
		// console.log(basic_info,report_stocks)
		this.state = {
			loadStatus: 'done', // pending,done, error
			data: {},
			basic_info, //基本信息
			report_stocks
		}
	}


	render() {
		const { t, title, analystCardData, analystActivityData } = this.props;
		const { loadStatus,report_stocks,basic_info } = this.state
		// console.log(basic_info)
		return (
			<React.Fragment>
			<div className="ResearchCoveringStocks-container">
				<p className="title">{title}</p>
				<div className="AnalystCard-content">
				<ChartAnalystDetail
							// title="研究覆盖股票"
							reportStocks={report_stocks}
							basicInfo={basic_info} />
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
ResearchCoveringStocks.defaultProps = {
	// analystCardData: AnalystCardData.data,
	// analystActivityData: AnalystActivityData.data,
	title: "研究覆盖股票"
};
// props 类型
ResearchCoveringStocks.propTypes = {
	title: PropTypes.string,
	basic_info: PropTypes.object,
	dynamic: PropTypes.array,
	report_stocks : PropTypes.array
};
// export default withRouter(translate()(AnalystCard));
