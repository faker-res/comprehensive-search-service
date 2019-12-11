/**
 * @description CardLoading
    @author jywang
**/

import React, { Component } from 'react'
import { Icon } from 'antd'
import './CardLoading.scss'

export class CardLoading extends Component {
    render() {
        return (
            <div className="card-loading">
                <div className="card-loading-con">
                    <Icon type="loading" />
                    <span>数据图修正中，请稍候...</span>
                    <p>修正完毕数据图将自动刷新显示</p>
                </div>
            </div>
        )
    }
}
