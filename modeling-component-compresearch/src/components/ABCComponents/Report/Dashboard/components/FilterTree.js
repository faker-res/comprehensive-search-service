import React, { Component } from 'react';
import { Tree } from 'antd';
import Loading from '../Report/Loading';
import { concatArraysWithSingle } from '../../utils/utils';
import './FilterTree.less';
// import '../../common/common.less';

const { TreeNode } = Tree;
export default class Filter extends Component {
  constructor(props) {
    super(props);
    this.state = {
      expandedKeys: [],
    };
  }
  shouldComponentUpdate = (newProps, newState) => {
    const { treeData } = this.props;
    let propsExpandedKeys = [];
    if (newState.expandedKeys.length === this.state.expandedKeys.length) {
      // 父组件刷新后，找出需要展开的节点keys
      propsExpandedKeys = this.getPropsExpandedKeys(newProps.selectedMap, treeData);
    }
    // 合并props传入进来需要展开的父节点和自己点开的节点
    this.state.expandedKeys = concatArraysWithSingle([propsExpandedKeys, newState.expandedKeys]);
    return true;
  }

  onSelect = (selectedKeys, info) => {
    const { selectedMap, selectCallback, treeType } = this.props;
    selectedMap.set(info.node.props.eventKey, info.node.props.title);
    selectCallback(selectedMap, treeType);
  }
  onExpand = (expandedKeys) => {
    this.setState({
      expandedKeys,
    });
  }
  getPropsExpandedKeys = (selectedMap, treeData) => {
    const propsExpandedKeys = [];
    for (const parent of treeData) {
      if (parent.children) {
        for (const child of parent.children) {
          if (selectedMap.has(child.key)) {
            propsExpandedKeys.push(parent.key);
          }
        }
      }
    }
    return propsExpandedKeys;
  }
  getMapKeyList = (map) => {
    const keys = map.keys();
    const keyList = [];
    for (const key of keys) {
      keyList.push(key);
    }
    return keyList;
  }
  renderTreeNodes = (data) => {
    return data.map((item) => {
      if (item.children) {
        return (
          <TreeNode title={item.title} key={item.key} dataRef={item}>
            {this.renderTreeNodes(item.children)}
          </TreeNode>
        );
      }
      return <TreeNode {...item} />;
    });
  }
  render() {
    const { title, treeData, selectedMap, isLoading } = this.props;
    const bool = true;
    // this.state.expandedKeys = this.getexpandedKeys();
    return (
      <div className={'filter_tree_container'}>
        <div className={`tree_title middle`}>{title}</div>
        <Loading
          isShow={isLoading}
          content={(
            <div className={`tree_content`}>
              <Tree
                multiple={bool}
                onSelect={this.onSelect}
                onExpand={this.onExpand}
                selectedKeys={this.getMapKeyList(selectedMap)}
                expandedKeys={this.state.expandedKeys}
              >
                {this.renderTreeNodes(treeData)}
              </Tree>
            </div>
          )}
        />

      </div>
    );
  }
}
