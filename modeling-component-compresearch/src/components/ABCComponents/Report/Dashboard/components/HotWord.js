import React, { Component } from 'react';
import { Divider } from 'antd';
import './HotWord.less';
// import '../../common/common.less';

export default class HotWord extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  clickHotWord = (e) => {
    const { clickHotWordCallback } = this.props;
    const { value } = e.target.dataset;
    clickHotWordCallback(value);
  }
  render() {
    const { title, hotWordList } = this.props;
    return (
      <div className={`hot_word_container center_middle`}>
        <div className={`middle hot_search`} >
          <div style={{ color: '#a8b0bc' }}>{title || '搜索热词'}：</div>
          {
            hotWordList.map((hotWord, i) => {
              return (
                <div key={hotWord.id} className={`middle hot_word`}>
                  <span data-value={hotWord.display_Name} onClick={this.clickHotWord}>{hotWord.display_Name}</span>
                  <div className={i === 9 ? 'display_none' : ''}>
                    <Divider type="vertical" style={{ backgroundColor: '#a8b0bc' }} />
                  </div>
                </div>
              );
            })
          }
        </div>
      </div>
    );
  }
}
