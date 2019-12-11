/**
 * @description 下拉选择组件
 * @author momoxsy
 * @Date 2018/6/6
 */

import React, { Component } from 'react';
import PropTypes, { func } from 'prop-types';
import classNames from 'classnames';
import { Checkbox, Menu, Dropdown, Icon,Input,Button } from 'antd';

import FilterTag from '../FilterTag';
import Loading from '../Loading';

const Search = Input.Search;
const CheckboxGroup = Checkbox.Group;

export default class SelectFilter extends Component{

    constructor(props) {
        super(props);
        this.state = {
            checkedList: [],
            indeterminate: false,
            checkAll: false
        };
        this.plainOptions = [];
    }

    componentWillReceiveProps(nextProps) {

        const data = nextProps.data;    
        this.plainOptions = [];
        let checkAll = this.state.checkAll;
        let checkedList = this.state.checkedList;

        data.data.map(d=> {
            if(!d.disabled) {
                this.plainOptions.push(d.value);   
            }
            if(checkAll) {
                this[d.key + '_checked'] = !d.disabled;
            }
        });

        if(nextProps.clear !== this.props.clear) {
            checkAll = true;
            checkedList = this.plainOptions;
        }else{
            //去掉置灰checked状态的filter
            const reCheckedList = [];
        
            this.plainOptions.map((p)=> {
    
                if(this[`${p}_checked`]) {
                    reCheckedList.push(p);
                }
    
            });

            checkedList = reCheckedList;

        }
        this.setState({
            checkAll,
            checkedList
        });

    }

    render() {

        const data = this.props.data;
        const showSearch = this.props.showSearch;

        this.plainOptions = [];
        data.data.map(d=> {
            if(!d.disabled) {
                this.plainOptions.push(d.value);   
            }
        });
        const loadingContainer = (<div selectable="true" focusable="true" className="filter-loading"><Loading title=""/></div>);
        return (
            <div style={{display:'inline-block'}}>
                <Dropdown 
                    overlay={this.props.loading ? loadingContainer : this.getDropDownList(data)}
                    onVisibleChange={this.handleVisibleChange}
                    visible={this.state.visible}
                    trigger={['click']}
                >
                    <a 
                        className="ant-dropdown-link" 
                        data-key={this.props.dataKey} 
                        href="#" 
                        onClick={(e)=> {this.props.onClickFun(e.target)}}
                        style={{display: 'block'}}
                    >
                        {this.props.title} 
                        <Icon
                            onClick={(e)=> {this.props.onClickFun(e.target.parentNode)}} 
                            type="down" 
                        />
                    </a>
                </Dropdown>
                <FilterTag
                    data={this.state.checkedList}
                    baseShow={this.baseShow}
                    checkedAll={this.state.checkAll}
                    clear={() => {
                        this.clearCheckList();
                        this.props.getFilters(this.props.dataKey, []);
                    }}
                ></FilterTag>
            </div>
        );

    }

    handleVisibleChange = (flag) => {
        
        this.setState({ visible: flag });
    
    }

    getDropDownList(data) {

        data.data = data.data.sort((a, b)=> {
            return b.count - a.count;
        });
        
        return (
            <Menu>
                <Menu.Item className="border-bottom" key="全部">
                    <Checkbox
                        indeterminate={this.state.indeterminate}
                        onChange={this.onCheckAllChange}
                        checked={this.state.checkAll}
                        data-value="全部" 
                    >
                        全部
                        <span className="text-right">{data.totalCount}</span>
                    </Checkbox> 
                </Menu.Item>       
                {data.data.map((d, index)=> {
                    return (
                        <Menu.Item key={d.key}>
                            <Checkbox disabled={d.disabled} onChange={this.onChange.bind(this)} data-value={d.value} checked={this[d.value + '_checked']}>
                                {d.value}
                                {d.count > 0&&<span className="text-right">{d.count}</span>}
                            </Checkbox>
                        </Menu.Item>
                    );
                })}
                <Menu.Item className="border-bottom" key="button">
                    <Button type="primary" onClick={this.onBtnClick.bind(this)}>确定</Button> 
                </Menu.Item>
            </Menu>
        );

    }

    onChange = (e) => {
        let value = e.target["data-value"];
        let list = this.state.checkedList.slice();

        this[value + '_checked'] = e.target.checked;
        if(e.target.checked) {
            list.push(value);
            this.setState({ 
                checkedList: list,
                indeterminate: list.length !== this.plainOptions.length,
                checkAll: list.length === this.plainOptions.length
            });

        }else{
            list.splice(list.indexOf(value), 1);
            this.setState({
                checkedList: list,
                indeterminate: list.length,
                checkAll: false
            });
        }
    }

    clearCheckList() {

        for(let k in this.plainOptions) {
            
            this[this.plainOptions[k] + '_checked'] = false;

        }

        this.setState({
            checkedList: [],
            indeterminate: false,
            checkAll: false
        });
        
    }

    onCheckAllChange = (e) => {
        
        for(let k in this.plainOptions) {

            this[this.plainOptions[k] + '_checked'] = e.target.checked;

        }

        this.setState({
            checkedList: e.target.checked ? this.plainOptions : [],
            indeterminate: false,
            checkAll: e.target.checked
        });
    }

    onBtnClick(e) {

        const checkedList = this.state.checkAll ? [] : this.state.checkedList;

        this.handleVisibleChange(false);
        this.props.getFilters(this.props.dataKey, checkedList);

    }

} 