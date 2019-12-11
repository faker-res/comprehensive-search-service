/**
 * @description 最新动态组件
    @author ypeng
**/

import React, { Component } from 'react'
import { Icon } from 'antd';
import PropTypes from 'prop-types'
import {localClass} from './style.scss'

export default class RecentActivity extends Component {
    delTag(str){ // 去除字符串html标签
		return str && str.replace(/(<[^>]*>)([^(</)]*)(<\/[^>]*>)/g,function(math,p1,p2,p3){
			return p2;
		}) || '';
	}
    dateFormat(val,timeFromNow){
		let str = val + '', date = '', time;
		if ( (str.indexOf('-') > -1 || str.indexOf('/') > -1) ){
			time = new Date(str.replace(/-/g,'/')).getTime();
		} else if ( !isNaN(parseFloat(val)) && parseFloat(val) !== 0 ){
			const num = parseFloat(val);
			if ((num + '').length === 10){
				time = num * 1000;
			} else if ((num + '').length === 13){
				time = num;
			}
		}
		date = this.timeFromNowFormat(time,timeFromNow);
		return date;
	}
    timeFromNowFormat(oldTime,timeFromNow){
		const now = new Date();
		const now_time = new Date().getTime();
		const now_Y = now.getFullYear();
		const now_M = now.getMonth();
		const now_D = now.getDate();
		const today = `${now_Y}/${now_M + 1}/${now_D} 00:00:00`;
        const today_time = new Date(today).getTime();
        const interval = now_time - oldTime;
        
        if (interval < 0){
            return "未来";
        } else if ( timeFromNow === 's' && interval < 60000 ){//1分钟内
            return '刚刚';
        } else if ( timeFromNow === 's' && interval < 3600000 ){//1小时内
            return `${ Math.floor(interval / 60000) }分钟前`;
        } else if ( (timeFromNow === 's' || timeFromNow === 'd')  && oldTime >= today_time ){
            return '今天';
        } else if ( (timeFromNow === 's' || timeFromNow === 'd')  && oldTime >= today_time - 3600000 * 24 ){
            return `昨天`;
        } else if ( (timeFromNow === 's' || timeFromNow === 'd')  && oldTime >= today_time - 3600000 * 24 * 2 ){
            return `前天`;
        } else {
            const oldDate = new Date(oldTime);
            const Y = oldDate.getFullYear() < 10 ? '0' + oldDate.getFullYear() : oldDate.getFullYear();
            const M = oldDate.getMonth() + 1 < 10 ? '0' + (oldDate.getMonth() + 1) : oldDate.getMonth() + 1;
            const D = oldDate.getDate() < 10 ? '0' + oldDate.getDate() : oldDate.getDate();
            return `${Y}-${M}-${D}`;
        }
	}
    render(){
        const _this = this;
        let {list,titleClick,subTitClick,moreBtnClick = ()=>{},moreBtnHref,timeFromNow,t} = this.props;
        //最新动态
		const new_list = list.map(item=>{
			return Object.assign({},item,{source: _this.delTag(item.source)});
		});

        return (
            <div className={localClass}>
                <div className="timeBarWrap">
                    { new_list.length > 0 && <a className="more fr" target="_blank" href={moreBtnHref} onClick={(e)=>{ moreBtnClick(e); }} hidden>更多 <Icon type="right" /></a>}
                    { this.props.title && <h3 className="title"><em className="icon_clock"><Icon type="clock-circle-o" /></em>{this.props.title}</h3>}
                    <div className="itemListWrap">
                        {
                            new_list.map( (item,index) => (
                                <div className="item" key={index}>
                                    <p className="itemDate"><em className="snaps"></em>{timeFromNow === 'o' ? item.time : this.dateFormat(item.time, timeFromNow) }</p>
                                    <p className="itemDetail"><a href={item.href} target="_blank" title={item.title} onClick={(e) =>titleClick({index,item},e)} >{item.title}</a></p>
                                    <p className="itemSource" onClick={(e) =>subTitClick({index,item},e)}>{item.source}</p>
                                </div>
                            ) )
                        }
                        {
                            list.length === 0 && <div className="item">{"暂无动态"}</div>
                        }
                    </div>
                </div>
            </div>
        )
    }
}

RecentActivity.defaultProps = {
    title : '',
    list : [],
    titleClick(){},
    subTitClick(){},
    moreBtnClick(){}
}
