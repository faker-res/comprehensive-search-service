/**
 * @description FundTopComponent 组件
 * @date 2018.05.23
 * @author david
 */

import React from 'react'
import PropTypes from 'prop-types'
import './FundTopComponent.scss'
import MiniChart from '../../../MiniChart'
const judgeNum = (num)=>{
    if (num > 0){
        return '+' + num.toFixed(2);
    } else {
        return num.toFixed(2);
    }
}

const FundTopComponent = (props) => {
    if ( props.type === 1){
        return (
            <div className="FundTopComponent stockUp" style={{height:props.height}}>
                <div className="stockNameIndexCon">
                    <h3>{props.title}{`(${props.code})`}</h3>
                    <p>最新净值 {props.time}</p>
                    <p>万份收益 {Number(props.income)}</p>
                </div>
            </div>
        )
    } else {
        return (
            <div className={`FundTopComponent ${props.gainsRate > 0 ? 'stockUp' : props.gainsRate < 0 ? 'stockDown' : 'stockFlat'}`}>
                <MiniChart seriesArr = {props.stock_chart} showTooltip={false} width={props.width + 3} height={props.height} color={props.gainsRate > 0 ? '#FB7D6A' : props.gainsRate < 0 ? '#71DE9D' : '#717D8C'} />
                <div className="stockNameIndexCon">
                    <h3>{props.title}{`(${props.code})`}</h3>
                    <p>最新净值 {props.time}</p>
                    <div className="gains">
                        <span>
                            {Number(props.currentMoney)}
                        </span>
                        <span>
                            <img src={props.gainsRate < 0 ? require('../../../../../../assets/image/arrow_down.png') : require('../../../../../../assets/image/arrow_up.png')} alt=""/>
                        </span>
                        <span>
                            <p>&nbsp;</p>
                            <p>{Number(props.gainsRate)}%</p>
                        </span>
                    </div>
                </div>
            </div>
        )
    }
}
// 默认props值
FundTopComponent.defaultProps = {
    width: 257,
    height: 129,
    color:'#72DE9E',
    title: '久远银海',
    code:'00277.SZ',
    time:'5121',
    currentMoney:'35.00',
    gains:1.09,
    gainsRate:3.21
}
// props 类型
FundTopComponent.propTypes = {
    width:PropTypes.number,
    height:PropTypes.number,
    color:PropTypes.string,
    title: PropTypes.string,
    code:PropTypes.string,
    time:PropTypes.string,
    currentMoney:PropTypes.string,
    gains:PropTypes.number,
    gainsRate:PropTypes.number
}
export default FundTopComponent

