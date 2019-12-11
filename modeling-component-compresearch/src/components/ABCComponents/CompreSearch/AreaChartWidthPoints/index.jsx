/**
 * @description 分析师覆盖股票-图表组件
    @author ypeng
**/

import React, { Component } from 'react'
import Highcharts from 'highcharts'
import { localClass } from './style.scss'

export default class AreaChartWidthPoints extends Component {
	constructor(){
		super()
		this.state = {
			config : {
				title: false,
				chart: {
					height: 120,
					margin : 0,
					marginLeft : 5,
					marginRight : 5,
				},
				credits: {
					enabled: false
				},
				exporting: {
					enabled: false
				},
				navigator: {
					enabled: false
				},
				scrollbar: {
					enabled: false
				},
				legend: { enabled: false },
				xAxis: [{
					visiable: false,
					type: 'datetime',
					gridLineWidth: 0,
					lineWidth: 0,
					title: {
						enabled: false
					},
					labels : {
						enabled : false
					},
					showEmpty : false,
					tickLength : 0,
					tickPosition : 'inside',
					/* startOnTick : true,
					endOnTick : true,
					maxPadding : 0,
					minPadding : 0 */
					// minRange : 3600*1000*24*30
				}],
				yAxis: [{
					visiable: false,
					gridLineWidth: 0,
					labels : {
						enabled : false
					},
					title: {
						enabled: false
					},
					startOnTick : true,
					endOnTick : true,
					maxPadding : 0,
					minPadding : 0
				}],
				rangeSelector: {
					enabled: false,
				},
				tooltip: {
					split: false,
					shared: true,
					useHTML: true,
					backgroundColor: 'rgba(0,0,0,0.6)',
					borderWidth: 0,
					shadow: false,
					style: {
						color: '#fff',
						lineHeight: '18px'
					},
					// headerFormat: '{point.key} <span style="color:{point.color}">{point.typename}</span> <br/>',
					// pointFormat: '<p>最新价：{point.y}</p><p>涨跌价：{point.differ}</p><p>涨跌幅：{point.differ_range}</p>',
					// footerFormat: '当前价：{point.y}',
					formatter(){
						const cur_point = this.points[0].point;
						const time = new Date(cur_point.x);
						const M = time.getMonth() + 1 < 10 ? '0' + (time.getMonth() + 1) : time.getMonth() + 1;
						const D = time.getDate() < 10 ? '0' + time.getDate() : time.getDate();
						let html = '';
						if (cur_point.typename === undefined){
							html = `<div>
										<p>${M}-${D}</p>
										<p>最新价：${this.y}</p>
										<p>涨跌价：${cur_point.differ}</p>
										<p>涨跌幅：${cur_point.differ_range}</p>
									</div>`;
						} else {
							let counter = {};
							cur_point.typename.map((item,id)=>{
								if ( counter.hasOwnProperty(item) && item !== '--' ){
									counter[item]++;
								} else {
									counter[item] = 1;
								}
							});
							const renderTagCon = () => {
								let con = '';
								for (let key in counter){
									let color = '';
									switch (key){
										case '买入': color = '#ff0000'; break;
										case '增持': color = '#de9700'; break;
										default: color = '#abe201';
									}

									con += `<p>${M}-${D} <span style="color:${color}">${key}${counter[key] > 1 ? ` ${counter[key]}次` : ''}</span></p>`;
								}
								return con;
							}

							html = `<div>
										${renderTagCon()}
									</div>`;
						}
						return html;
					},
					xDateFormat: '%m-%d'
				},
				plotOptions: {
					series: {
						fillColor: {
							linearGradient: {
								x1: 0.5,
								x2: 0.5,
								y1: 0,
								y2: 1
							},
							stops: [
								[0, 'rgba(41,129,245,0.3)'],
								[1, 'rgba(41,129,245,0.1)']
							]
						}
					}
				},
				series: [{
					name: '价位',
					type: 'area',
					id: 'line',
					// data: points_arr,
					gapSize: 0,
					tooltip: {
						valueDecimals: 2
					},
					lineColor: 'rgba(41,129,245,1)',
					lineWidth: 1,
					connectNulls : true,//未知
					cropThreshold : 0,//未知
					getExtremesFromAll : true,//未知,
					turboThreshold : 0,//未知,
				}]
			}
		}
	}
	componentWillMount() {
		const points_arr = this.pointsPicker(this.props.data.stock_price, this.props.data.report_stocks);
		const series = this.state.config.series[0];
		series.data = points_arr;
		this.setState({series});
		
	}
	componentDidMount = ()=>{
		new Highcharts.Chart(this.chart,this.state.config)
	}
	pointsPicker(price, tag) {
		let arr = [], arr1 = [], arr2 = [];
		if ( Array.isArray(price) ) {
			price.forEach( (t, i)=>{
				const x = new Date(t.trade_date).getTime();
				const y = isNaN(parseFloat(t.close_price)) ? '' : parseFloat(t.close_price);
				const differ_range = isNaN(parseFloat(t.differ_range)) ? '' : Math.round(parseFloat(t.differ_range) * 100) / 100 + '%';
				arr1.push({
					x: x,
					y: y,
					differ : t.differ,
					differ_range,
					marker: {
						radius: 0,
					}
				});
			});
		}

		if ( Array.isArray(tag) ) {
			const newTags = this.mergeSameTimeObj(tag);
			// console.log(newTags);
			newTags.forEach( (t,i)=>{
				if ( isNaN(parseFloat(t.time)) ) return undefined;
				const x = parseFloat(t.time) * 1000;
				// const y = isNaN(parseFloat(t.close_price))? '' : parseFloat(t.close_price);
				let color = '', y;
				switch (t.rating[0]) {
					case '买入': color = '#ff0000'; break;
					case '增持': color = '#de9700'; break;
					default: color = '#abe201';
				}

				arr1.forEach(function (it, id) {
					if (x < arr1[0].x) {
						y = arr1[0].y;
					} else if ( id > 0 && x >= arr1[id - 1].x && x < it.x ){
						y = arr1[id - 1].y;
					} else if ( x >= arr1[arr1.length - 1].x ){
						y = arr1[arr1.length - 1].y;
					}
				});

				arr2.push({
					x,
					y,
					marker: {
						enabled: true,
						fillColor: color,
						radius: 5,
					},
					color: color,
					typename: t.rating,
				});
			} );

		}
		arr = arr1.concat(arr2).sort(function(a,b){
			return a.x - b.x;
		});

		return arr;
	}
	mergeSameTimeObj = (arr)=>{
		let newArr = [];
		arr.forEach((item,index)=>{
			const sameTime = newArr.some((it,id)=>{
				if (it.time === item.time){
					const rating = item.rating ? item.rating : '--';
					newArr[id].rating.push(rating);
					return true;
				}
				return false;
			});
			if (!sameTime){
				const rating = item.rating ? item.rating : '--';
				newArr.push(Object.assign({},item,{rating : [rating]}))
			}
		});
		return newArr;
	}
	render() {
		return (
			<div className={localClass} ref={(chart)=>{ this.chart = chart; }}></div>
		)
	}
}
