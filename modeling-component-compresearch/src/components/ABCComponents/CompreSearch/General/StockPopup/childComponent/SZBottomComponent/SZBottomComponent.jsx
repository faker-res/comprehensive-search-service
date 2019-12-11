/**
 * @description SZBottomComponent 组件
 * @date 2018.05.23
 * @author david
 */

import React from 'react'
import PropTypes from 'prop-types'
import './SZBottomComponent.scss'
const SZBottomComponent = (props) => {
    return (
        <div className="SZBottomComponent">
            <section>
                <div className="SZBottomComponent">
                    <span>
                        <p>{props.MarketValue}</p>
                        <p>总市值</p>
                    </span>
                    <span></span>
                    <span>
                        <p>{props.PE}</p>
                        <p>PE值</p>
                    </span>
                </div>
            </section>
        </div>
    )
}
// 默认props值
SZBottomComponent.defaultProps = {
    MarketValue: '100亿',
    PE: '3.5'
}
// props 类型
SZBottomComponent.propTypes = {
    MarketValue:PropTypes.string,
    PE:PropTypes.string
}
export default SZBottomComponent

