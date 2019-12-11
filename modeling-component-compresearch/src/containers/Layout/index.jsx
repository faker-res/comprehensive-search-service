/**
 * 布局
 */
import React, {Component} from 'react'
import {withRouter} from 'react-router-dom'
import {inject, observer} from 'mobx-react'

import LayoutHeader from './LayoutHeader'
import LayoutContent from './LayoutContent'
import LayoutFooter from "./LayoutFooter"

import "./index.scss"

@withRouter
@inject('defaultStore')
@observer
class Index extends Component {

    //构造器
    constructor(props) {
        super(props)
        this.state = {}
    }

    //渲染组件
    render() {
        return (
            <div className="invest-layout">
                {/*Sticky Layout*/}
                <div className="layout-wrapper">
                    {/*头部区域*/}
                    <LayoutHeader/>
                    {/*内容区域*/}
                    <LayoutContent/>
                </div>
                {/*footer底部区域*/}
                <LayoutFooter/>
            </div>
        )
    }
}

export default Index