/**
    @author ypeng
**/

import React, { Component } from 'react'
// import { translate } from 'react-i18next'
import {localClass} from './style.scss'

// @translate()
export default class Footer extends Component {
    render(){
        const {t, className, style} = this.props;
        const propsName = className ? ' ' + className : '';
        const year = new Date().getFullYear();
        return (
            <div className={localClass + propsName} style={style}>
                <div className="midArea clearfix">
                    <span className="linkpl fl">
                        <a href="/">{"首页"}</a><em></em>
                        <a href="http://www.abcfintech.com/#_aboutus">{"关于我们"}</a><em></em>
                        <a href="http://www.abcfintech.com/#_contectus">{"联系我们"}</a><em></em>
                        <a>{"使用条款"}</a></span>
                    <span className="copyright fr">Copyright © 2016-{year}&nbsp;&nbsp;&nbsp;{"北京阿博茨科技有限公司"}</span>
                </div>
            </div>
        )
    }
}
