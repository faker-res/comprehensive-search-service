/**
 * @description 返回顶部按钮组件
    @author ypeng
**/

import React, { Component } from 'react'
import { BackTop, Icon } from 'antd';
import {localClass} from './style.scss'

export default class BackToTop extends Component {
    render(){
        let {wrapStyle, iconStyle, children, target, visibilityHeight=200, onClick} = this.props;
        return (
            <BackTop target={target} visibilityHeight={visibilityHeight} onClick={onClick} className={localClass} style={wrapStyle} >{children?children : <Icon className="icon" type="up" style={iconStyle}/>}</BackTop>
        )
    }
}
