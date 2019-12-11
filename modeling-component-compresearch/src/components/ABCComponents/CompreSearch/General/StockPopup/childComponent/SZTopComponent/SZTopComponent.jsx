/**
 * @description SZTopComponent 组件
 * @date 2018.05.23
 * @author david
 */

import React from 'react'
import PropTypes from 'prop-types'
import './SZTopComponent.scss'
import MiniChart from '../../../MiniChart'
const SZTopComponent = (props) => {
    const num = parseFloat(props.gainsRate || props.gains);
    return (
        <div className={`SZTopComponent ${num > 0 ? 'stockUp' : num < 0 ? 'stockDown' : 'stockFlat'}`}>
            <MiniChart seriesArr = {props.stock_chart} showTooltip={false} width={props.width} height={props.height} color={num > 0 ? '#FB7D6A' : num < 0 ? '#71DE9D' : '#717D8C'} />
            <div className="stockNameIndexCon">
                <h3>{props.title}{`(${props.code})`}</h3>
                <p>{props.currentMoney}</p>
                <div className="gains">
                    <span>
                        <img src={num > 0 ? require('../../../theme/default/images/arrow_up.png') : require('../../../theme/default/images/arrow_down.png')} alt="" />
                    </span>
                    <span>{num > 0 ? '+' + props.gains : props.gains}</span>
                    <span>({num > 0 ? '+' + props.gainsRate : props.gainsRate})</span>
                </div>
            </div>
        </div>
    )
}
// 默认props值
SZTopComponent.defaultProps = {
    width: 257,
    height: 129,
    title: '久远银海',
    code:'00277.SZ',
    currentMoney:'35.00',
    gains:'1.09',
    gainsRate:'3.21',
    MarketValue:'1097.19亿',
    PE:2.81
}
// props 类型
SZTopComponent.propTypes = {
    width:PropTypes.number,
    height:PropTypes.number,
    title: PropTypes.string,
    code:PropTypes.string,
    currentMoney:PropTypes.string,
    gains:PropTypes.string,
    gainsRate:PropTypes.string,
    MarketValue:PropTypes.string,
    PE:PropTypes.number
}
export default SZTopComponent

