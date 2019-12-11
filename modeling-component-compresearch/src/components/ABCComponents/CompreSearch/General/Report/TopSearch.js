import React, { Component } from 'react';
import Loading from '../Report/Loading';
import './TopSearch.less';
import '../../common/common.less';

export default class TopSearch extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  ClickHandle = (e) => {
    const { clickCallback } = this.props;
    const { query } = e.currentTarget.dataset;
    clickCallback(query);
  }
  render() {
    const { list, title, isLoading } = this.props;
    const content = (
      <div className={'tree_content'}>
        {
          list.map((ele, i) => {
            return (
              <div key={i} data-query={ele.query} className={`tree_node middle`} onClick={this.ClickHandle}>
                <span className={`top${i + 1} center`}>{i + 1}</span>
                <div>{ele.display_Name}</div>
              </div>
            );
          })
        }
      </div>
    );
    return (
      <div className={'top_tree_container'}>
        <div className={`tree_title middle`}>{title}</div>
        {isLoading === false ? content : (
          <Loading
            isShow={isLoading}
            content={content}
          />
        )}
      </div>
    );
  }
}
