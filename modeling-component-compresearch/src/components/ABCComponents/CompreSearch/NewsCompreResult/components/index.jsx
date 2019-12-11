/**
 * 综合搜索-内部研报搜索结果
 * @author lzhang@abcft.com
 * @date 2018-8-20
 */
import React, {Fragment} from 'react';
import {Select} from 'antd';
import { withRouter } from 'react-router-dom';
import queryString from 'query-string';
import PropTypes from 'prop-types';
import './index.scss';

@withRouter
class ResultListFilter extends React.Component {
    constructor(props) {
        super(props);
        this.handleFilterClick = this.handleFilterClick.bind(this);
        this.state = {
        }
    }

    handleFilterClick(value) {
        let queryParams = queryString.parse(this.props.location.search);
        if (value) {
            queryParams.order = value;
        } else {
            if (typeof queryParams.order !== 'undefined') delete queryParams.order;
        }
        this.props.history.push({
            pathname: this.props.location.pathname,
            search: `?${queryString.stringify(queryParams)}`
        });
    }

    render() {
        let {order} = queryString.parse(this.props.location.search);
        let {total, filters, sortSwitch} = this.props;
        let [first = {}] = filters;
        return (
            <div className='compre-filter-tool clearfix'>
              <div className="news-count fl">为您找到相关结果约
                <strong> {total} </strong>个结果
              </div>
              {
                sortSwitch && <div className="news-priority fl">
                <Select style={{ width: 96, fontSize: 13}} defaultValue={order ||first.key || ""} onChange={this.handleFilterClick}>
                  {
                    filters.map((item, index) => {
                      return (
                          <Select.Option key={index} value={item.key}>{item.text || "全部类型"}</Select.Option>
                      )
                    })
                  }
                </Select>
              </div>
              }
            </div>
        );
    }
}

ResultListFilter.propTypes = {
    // 搜索结果总数
    total: PropTypes.number,
    // 数据筛选条件
    filters: PropTypes.array,
    // 排序开关
    sortSwitch: PropTypes.boolean,
};

ResultListFilter.defaultProps = {
    total: 0,
    filters: [],
    sortSwitch: true
};

export default ResultListFilter;