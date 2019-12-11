/**
 * @description 单支股票组件
    @author ypeng
**/

import React, { Component } from 'react'
// import { translate } from 'react-i18next';
import { Row, Col, Popover } from 'antd'
import ask from '../../../../lib/ask'
import { localClass } from './style.scss'
// import Loading from '../Loading'
import { Spin } from 'antd'
import StockPopup from '../StockPopup'

// @translate()
export default class StockInfo extends Component {
	constructor(props){
		super();
		this.state = Object.assign({},props,{
			stockPopData : {
				loaded : false,
				data : null
			}
		});
	}
	companyhover = (code)=>{
		if (!this.state.stockPopData.loaded){
			ask('AnalystStockInfo',{
				params : {
					stockCode : code
				}
			}).then(res => {
				this.setState( prevState => {
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
	render() {
		const {t} = this.props;
		const { code, name, price, date, count, zdp, zdf, zdf_p, stockPopData } = this.state;
		return (
			<div className={localClass}>
				<div className={parseFloat(zdf) > 0 ? 'up' : parseFloat(zdf) < 0 ? 'down' : ''}>
					<Row className="row" gutter={16}>
						<Col className="col" span={12}>
							<Popover placement="bottom"
								overlayClassName="ypeng-antd-StockPopup"
								onMouseEnter={ ()=>{this.companyhover(code);}}
								content={
									!stockPopData.loaded ? <div className="noDataTipWrap"><Spin /></div> :
									stockPopData.data ? <StockPopup data={stockPopData.data} /> :
									<div className="noDataTipWrap"><p className="noDataTips">{"暂无数据"}</p></div>
								}>
								<span className="name">{`${code} ${name}`}</span>
							</Popover>
						</Col>
						<Col className="col" span={12}><span className="dataTit">{"最新价"}</span><i className="dataNum">{price}</i></Col>
					</Row>
					<Row className="row" gutter={16}>
						<Col className="col" span={12}><div className="date">{date} | {count}</div></Col>
						<Col className="col" span={12}><span className="dataTit">{"涨跌幅"}</span><i className="dataNum">{zdp} / {zdf_p}%</i></Col>
					</Row>
				</div>
			</div>
		)
	}
}
