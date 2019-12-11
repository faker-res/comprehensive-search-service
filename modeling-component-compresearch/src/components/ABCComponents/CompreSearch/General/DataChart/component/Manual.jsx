/**
 * @description Manual
    @author jywang
**/

import React, { Component } from 'react'
import { Icon } from 'antd'
import './Manual.scss'

export class Manual extends Component {
    render() {
        let {url,...other} = this.props;
        return (
            <div className="manual">
                <div className="manual-con">
                    <Icon type="exclamation-circle-o" style={{marginRight:'10px',display:'inline-block'}}/>
                    <span>该数据图自动修正失败</span>
                    <p>是否前往手动修正?</p>
                </div>
                <div className="manual-button">
                    <span>离开</span><span><a href={url}>前往</a></span>
                </div>
            </div>
        )
    }
}
