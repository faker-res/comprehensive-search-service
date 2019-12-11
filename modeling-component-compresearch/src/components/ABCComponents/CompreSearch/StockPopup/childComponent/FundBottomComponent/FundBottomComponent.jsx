/**
 * @description FundBottomComponent 组件
 * @date 2018.05.23
 * @author david
 */

import React from 'react'
import PropTypes from 'prop-types'
import './FundBottomComponent.scss'
const judgeNum = (num)=>{
    if (num > 0){
        return '+' + num.toFixed(2);
    } else if (num < 0){
        return '-' + num.toFixed(2);
    } else {
        return num.toFixed(2);
    }
}

const FundBottomComponent = (props) => {
    return (
        <div className="FundBottomComponent">
            <section>
                {
                    props.type === 1 ?
                    <div className="FundBottomComponent">
                        <div className="conList"><p className="totalValue"><i>近七日年化收益率</i> <span>{!props.seven_day_yield && props.seven_day_yield !== 0 ? '--' : `${props.seven_day_yield}%`}</span></p></div>
                    </div> :
                    <div className="FundBottomComponent">
                        <div className="conList">
                            {props.totalValue ? <p className="totalValue"><i>累计净值</i> <span>{`${props.totalValue}`}</span></p> : ''}
                            {props.HigherSpeed ? <p className="totalValue"><i>涨速</i> <span>{judgeNum(props.HigherSpeed)}</span></p> : ''}
                            {props.recentlyDeal ? <p className="currentValue"><i>最近成交价(元股)</i><span>{judgeNum(props.recentlyDeal)}</span></p> : ''}
                            {props.currentHighest ? <p className="currentValue"><i>当日最高价(元股)</i><span>{judgeNum(props.currentHighest)}</span></p> : ''}
                            {props.currentLowest ? <p className="currentValue"><i>当日最低价(元股)</i><span>{judgeNum(props.currentLowest)}</span></p> : ''}
                            {props.totalDealValue ? <p className="currentValue"><i>当日累计成交金额(万元)</i><span>{judgeNum(props.totalDealValue)}</span></p> : ''}
                            {props.assignType ? <p className="currentValue"><i>转让方式</i><span>{props.assignType}</span></p> : ''}
                        </div>
                    </div>
                }
            </section>
        </div>
    )
}
// 默认props值
FundBottomComponent.defaultProps = {
    // totalValue: '2.1700亿',
    // HigherSpeed: 294.70,
    // recentlyDeal: 294.70,
    // currentHighest: 294.70,
    // currentLowest: 294.70,
    // totalDealValue: 58.94,
    // assignType: '协议'
}
// props 类型
FundBottomComponent.propTypes = {
    totalValue: PropTypes.string,
    HigherSpeed: PropTypes.number,
    recentlyDeal: PropTypes.number,
    currentHighest: PropTypes.number,
    currentLowest: PropTypes.number,
    totalDealValue: PropTypes.string,
    assignType: PropTypes.string
}
export default FundBottomComponent

