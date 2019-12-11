/**
 * @description 地区树形选择控件
 * @author momoxsy
 **/

import React, { Component } from 'react'
import { Tree,Radio,Dropdown,Row, Col, Input, Icon, Menu, Checkbox, List, Button } from 'antd';
import { Scrollbars } from 'react-custom-scrollbars';

import FilterTag from '../FilterTag';
import Loading from '../Loading';

import {areaData} from './data';

import './style.scss';

const TreeNode = Tree.TreeNode;
const Search = Input.Search;
let areaEnum = {};

export default class AreaFilter extends Component {

    constructor(props){
        super(props);
        this.baseShow = {
            key: '0-100000',
            value: '中国'
        };
        this.state = {
            visible: props.visible,
            showTree: true,
            checkedList: [this.baseShow.key],
            treeRadioChecked: {
                [this.baseShow.key] : true
            },
            filterAreaObj: {
            },
            filters: props.filters,
            areaData
        };

        this.getAreaMenu(areaData);        
        
        this.selectDataArr = [];
    }

    getAreaMenu(item) {

        item.map((i)=> {

            areaEnum[i.key] = i.label;
            if(i.children) {
                this.getAreaMenu(i.children);
            }

        })

    }

    render() {

        let TagList = this.state.checkedList.map((c)=> {
            return areaEnum[c];
        });

    const loadingContainer = (<div selectable="true" focusable="true" className="filter-loading"><Loading title=""/></div>);

        return (
            <div style={{display:'inline-block'}}>
                <Dropdown 
                    overlay={this.props.loading ? loadingContainer : this.renderContainer()}
                    onVisibleChange={this.handleVisibleChange}
                    visible={this.state.visible}
                    trigger={['click']}
                    
                >
                    <a 
                        className="ant-dropdown-link dropdownFilterList" 
                        data-key={this.props.dataKey} 
                        onClick={(e)=> {this.props.onClickFun(e.target)}}
                        href="#" 
                        style={{display: 'block'}}
                    >
                        国家或地区
                        <span>
                            <Icon
                                onClick={(e)=> {this.props.onClickFun(e.target.parentNode)}} 
                                type="down"
                            />
                        </span>
                    </a>

                </Dropdown>
                <FilterTag
                    data={TagList}
                    baseShow={this.baseShow}
                    clear={() => {
                        this.clearCheckList();
                        this.props.getFilters(this.props.dataKey, []);
//                        this.props.getFilters(this.props.dataKey, ["中国##null##null##null"]);
                    }}
                ></FilterTag>
          </div>
        );
    }

    renderContainer() {

        return ( 
            <Menu className="area-filter">
                <Menu.Item>
                    <Row>
                        <Col span={12} style={{borderBottom: '1px solid #eee' }}>
                            <Search
                                placeholder="搜索"
                                onFocus={this.openSearch.bind(this)}
                                onBlur={this.searchBlur.bind(this)}
                                onSearch={this.onSearch.bind(this)}
                                onInput={this.onInput.bind(this)}
                                value={this.state.searchAreaValue}
                                style={{ width: 280}}
                            />
                        </Col>
                        <Col span={12}>
                            已选择区域
                            <a
                                className="reset"
                                onClick={this.resetCheckList.bind(this)}
                            >重置</a>
                        </Col>
                    </Row>
                </Menu.Item>
                <Menu.Item>
                    <Row>
                        <Col span={12}>
                            {this.renderLeftSearch()}
                        </Col>
                        <Col span={12} style={{height: 310}}>
                            {this.renderRightResult()}
                            <Button
                                type="primary"
                                onClick={this.onBtnClick.bind(this)}
                            >确定</Button>
                        </Col>
                    </Row>
                </Menu.Item>
            </Menu>
        )

    }

    renderLeftSearch() {

        return (
            <Row>
                <Col span={24} style={{ width: 290, height: 300 }}>
                    <Scrollbars style={{ width: "100%", height: 300 }}>
                        {this.state.showTree ? this.renderTree(this.state.areaData) : this.renderSearchList(this.state.filterAreaObj)}
                    </Scrollbars>
                </Col>
            </Row>
        );

    }

    renderRightResult() {
        const checkedList = this.state.checkedList;

        return (
            <List>
                {this.getSelectedList(checkedList)}
            </List>
        );
    }

    getSelectedList(checkAreaList) {

        return checkAreaList.map(a=> {
            return (
                <List.Item key={a}>
                    <span>{areaEnum[a]}</span>
                    <Icon
                        onClick={this.deleteCheckedArea.bind(this)}
                        data-key={a} 
                        type="close" 
                    />
                </List.Item>
            );
        });

    }

    renderTree(data) {

        return (
            <Tree
            // onCheck = {this.onSelect.bind(this)}
            // checkable= {true}
            onSelect={this.onSelect.bind(this)}
            selectedKeys={this.state.checkedList}
          >
            {this.renderTreeNodes(data)}
          </Tree>
        );

    }

    renderTreeNodes(data) {

        let selectDataArr = this.props.data || [];

        return data.map((item) => {

            item.selectable = (selectDataArr.indexOf(item.label) !== -1);
            if (item.children) {
                return (
                    <TreeNode
                        title={this.renderSingleTree(item)}
                        key={item.key} 
                        disabled = {!item.selectable}
                    >
                        {this.renderTreeNodes(item.children)}
                    </TreeNode>
                );
            }
            return <TreeNode
                        title={this.renderSingleTree(item)}
                        disabled = {!item.selectable} 
                        key={item.key} 
                    />;
        });
    }

    renderSingleTree(data) {

        return (
            <div>
                <span>{data.label}</span>
                <Radio
                    checked={this.state.treeRadioChecked[data.key]}
                ></Radio>
            </div>
        );

    }

    renderSearchList(obj) {
        
        const keys = Object.keys(obj);

        return (
            <List>
                {keys.map(k=> {
                    return (
                        <List.Item key={k} className="search-list">
                            <Row type="flex" justify="space-around" align="middle" style={{width: '100%'}}>
                                <Col span={3}>
                                    <Checkbox
                                        data-key={k}  
                                        onClick={this.onCheckboxClick.bind(this)}
                                        checked={obj[k].checked}
                                    >
                                    </Checkbox>
                                </Col>
                                <Col span={16}>
                                    <div>{obj[k].value}</div>
                                    <div className="parents">{obj[k].parents.slice().reverse().join('/')}</div>
                                </Col>
                                <Col span={5}>
                                    {/* <Icon type="up" /> */}
                                </Col>
                            </Row>

                        </List.Item>
                    );
                })}
            </List>
        );

    }

    handleVisibleChange = (flag) => {
        
        this.setState({ visible: flag });
    
    }

    onInput(e) {

        this.setState({
            searchAreaValue: e.target.value
        })

    }

    componentWillReceiveProps(nextProps) {
        if(nextProps.clear !== this.props.clear) {
            this.clearCheckList()
        }else if(nextProps.newRequest !== this.props.newRequest){
            const checkedList = this.state.checkedList.length
                                &&this.state.checkedList 
                            || [this.baseShow.key];
            const treeRadioChecked = !this.isDataObjectEmpty(this.state.treeRadioChecked)
                                      &&this.state.treeRadioChecked 
                                || {[this.baseShow.key]: true};
            this.setState({
                showTree: true,
                searchAreaValue: '',
                filterAreaObj: {},
                checkedList,
                treeRadioChecked
            });
        }else{
            this.setState({
                showTree: true,
                searchAreaValue: '',
                filterAreaObj: {}
            });
        }
    }

    isDataObjectEmpty(obj) {
        let bool = true;
        for(let k in obj) {
            bool = false;
            break;
        }
        return bool;
    }

    deleteCheckedArea(e) {

        let checkedKey = e.target.getAttribute('data-key');
        let checkAreaList = this.state.checkedList.slice();
        let checkedObj = {};
        let filterAreaObj = this.state.filterAreaObj;

        checkAreaList.splice(checkAreaList.indexOf(checkedKey), 1);

        //添加默认值
        //checkAreaList = checkAreaList.length ? checkAreaList : [this.baseShow.key];

        checkAreaList.map(k=> {
            checkedObj[k] = true;
        });

        filterAreaObj[checkedKey]&&(filterAreaObj[checkedKey].checked = false);

        this.setState({
            treeRadioChecked: checkedObj,
            checkedList: checkAreaList,
            filterAreaObj
        });

    }

    clearCheckList() {

        this.setState({
            showTree: true,
            searchAreaValue: '',
            checkedList: [],
            treeRadioChecked: {},
//            checkedList: [this.baseShow.key],
            // treeRadioChecked: {
            //     [this.baseShow.key]: true
            // },
            filterAreaObj: {}
        });

    }

    resetCheckList() {

        this.setState({
            showTree: true,
            searchAreaValue: '',
            checkedList: [this.baseShow.key],
            treeRadioChecked: {
                [this.baseShow.key]: true
            },
            filterAreaObj: {}
        })

    }

    openSearch(e) {

        this.setState({
            showTree: false
        });

    }

    searchBlur(e) {

        if(e.target.value) {

            this.onSearch(e.target.value);
        
        }else{
        
            let checkedObj = {};
            
            this.state.checkedList.map(checked=> {
                checkedObj[checked] = true;
            })

            this.setState({
                treeRadioChecked: checkedObj,
                showTree: true
            });
        
        }
    }

    onSearch(value) {

        let showObj = this.getCurrentShowObj(value);

        this.setState({
            searchAreaValue: value,
            filterAreaObj: showObj
        });

    }

    getCurrentShowObj(value) {

        let currentShowObj = {};

        for(let k in areaEnum){

            const index = areaEnum[k].indexOf(value);
            let obj = {};
            if(index !== -1) {
                obj = {
                    "key": k,
                    "value": areaEnum[k],
                    "index": index,
                    "parents": []
                }
                let areaKeyArr = k.split('-');
                areaKeyArr.pop();
                let parentArea = areaEnum[areaKeyArr.join('-')];
                
                while(parentArea) {

                    obj.parents.push(parentArea);
                    areaKeyArr.pop();
                    parentArea = areaEnum[areaKeyArr.join('-')];

                }

                currentShowObj[k] = JSON.parse(JSON.stringify(obj));
            }

        }

        return currentShowObj;

    }

    onBtnClick() {

        this.handleVisibleChange(false);
        let areaFilter = this.getAreaFilters(this.state.checkedList);
        this.props.getFilters(this.props.dataKey, areaFilter);

    }

    getAreaFilters(checkedList) {

        //checkedList = checkedList.length ? checkedList : [this.baseShow.key];
        let arr = checkedList.map(checked=> {

            let areaArr = [];
            areaArr.push(areaEnum[checked]);
            let areaKeyArr = checked.split('-');
            areaKeyArr.pop();
            let parentArea = areaEnum[areaKeyArr.join('-')];
            
            while(parentArea) {

                areaArr.push(parentArea);
                areaKeyArr.pop();
                parentArea = areaEnum[areaKeyArr.join('-')];

            }

            areaArr = areaArr.reverse();
            for(let i = 0; i < 4; i++) {

                areaArr[i] = areaArr[i] || "null";

            }

            return areaArr.join('##');
            
        })

        return arr;

    }

    onSelect(selectedKeys, e) {
        let checkedObj = {};
        let checkedList = this.state.checkedList.slice();
        if(selectedKeys.length) {
            
            selectedKeys = checkedList.indexOf(selectedKeys[0]) === -1 ? 
                    checkedList.concat(selectedKeys) 
                    : checkedList;

            selectedKeys.map(k=> {
                checkedObj[k] = true;
            });
    
            this.setState({
                treeRadioChecked: checkedObj,
                checkedList: selectedKeys
            });

        }else{
            //Radio取消事件
        }

    }

    onCheckboxClick(e) {
        
        const checked = e.target.checked;
        const key = e.target.getAttribute('data-key');
        let checkedList = this.state.checkedList.slice();
        const index = checkedList.indexOf(key);
        let filterAreaObj = this.state.filterAreaObj;
        let checkedObj = {};

        if(checked) {

            index === -1 ? checkedList.push(key) : '';

        }else{

            (index !== -1)&&checkedList.splice(index, 1);

        }

        filterAreaObj[key].checked = checked;
        
        checkedList.map(k=> {
            checkedObj[k] = true;
        });

        this.setState({
            checkedList,
            filterAreaObj,
            treeRadioChecked: checkedObj
        });

    }
}
