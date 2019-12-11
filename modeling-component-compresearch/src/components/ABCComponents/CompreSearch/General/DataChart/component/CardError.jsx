/**
 * @description CardError
    @author jywang
**/

import React, { Component } from 'react'
import { Icon } from 'antd'
import './CardError.scss'

export class CardError extends Component {
    render() {
        return (
            <div className="card-error">
                <div className="card-error-con">
                    <Icon type="exclamation-circle-o" style={{marginRight:'10px',display:'inline-block'}}/>
                    <span>该数据图暂时无法修正</span>
                </div>
            </div>
        )
    }
}
