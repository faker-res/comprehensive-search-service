/**
 * @description Process
    @author jywang
**/

import React, { Component } from 'react'
import { Icon } from 'antd'
import './Process.scss'

export class Process extends Component {
    render() {
        return (
            <div className="process">
                <div className="process-con">
                    <Icon type="exclamation-circle-o" style={{marginRight:'10px',display:'inline-block'}}/>
                    <span>该数据图正在审核中...</span>
                </div>
            </div>
        )
    }
}
