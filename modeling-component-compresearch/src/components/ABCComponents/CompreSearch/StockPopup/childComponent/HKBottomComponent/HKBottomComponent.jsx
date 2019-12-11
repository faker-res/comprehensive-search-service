/**
 * @description HKBottomComponent 组件
 * @date 2018.05.23
 * @author david
 */

import React from 'react'
import PropTypes from 'prop-types'
import './HKBottomComponent.scss'
const toFixedNum = (num)=>{
    return num.toFixed(2);
}
const HKBottomComponent = (props) => {
    return (
        <div className="HKBottomComponent">
            <section>
                <div className="HKBottomComponent">
                    <span>
                        <p><i>今开</i>{toFixedNum(props.OpeningValue)}</p>
                        <p><i>昨收</i>{toFixedNum(props.YesterdayCloseValue)}</p>
                    </span>
                    <span></span>
                    <span>
                        <p><i>最高</i>{toFixedNum(props.highestValue)}</p>
                        <p><i>最低</i>{toFixedNum(props.lowestValue)}</p>
                    </span>
                </div>
            </section>
        </div>
    )
}
// 默认props值
HKBottomComponent.defaultProps = {
    OpeningValue: 407.80,
    YesterdayCloseValue: 405.80,
    highestValue: 407.80,
    lowestValue: 401.00
}
// props 类型
HKBottomComponent.propTypes = {
    OpeningValue: PropTypes.number,
    YesterdayCloseValue: PropTypes.number,
    highestValue: PropTypes.number,
    lowestValue: PropTypes.number
}
export default HKBottomComponent

