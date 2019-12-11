/**
 * @description tag简单封装
 * @author momoxsy
 **/

import React, { Component } from 'react'
import { Tag } from 'antd';

import './style.scss';
export default class FilterTag extends Component{

    constructor(props){
        super(props);
        let data = this.props.data;
        let close = this.props.close || this.close;
        let value = data.length > 1 ? `已选${data.length}项` : (data[0] || '全选');
        this.state = {
            value,
            close
        };
    }

    render() {

        return (
            <Tag 
                closable={this.state.value !== '全部'}
                onClose={this.props.close || this.close.bind(this)}
            >{this.state.value}</Tag>
        );

    }

    componentWillReceiveProps(nextProps) {

        let data = nextProps.data;
        let value = data.length > 1 ? `已选${data.length}项` : (data[0] || '全部');

        value = nextProps.checkedAll ? '全部' : value;
        this.setState({
            value
        });


    }

    close(e) {

        e.preventDefault();
        this.props.clear();

    }

}