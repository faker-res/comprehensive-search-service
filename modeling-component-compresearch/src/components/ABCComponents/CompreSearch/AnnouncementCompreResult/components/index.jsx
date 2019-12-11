/**
 * 综合搜索-内部研报搜索结果
 * @author lzhang@abcft.com
 * @date 2018-8-20
 */
import React from 'react';
import {
    Icon,
    Select,
    Button,
} from 'antd';
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
            pathname: '/announcement',
            search: `?${queryString.stringify(queryParams)}`
        });
    }

    render() {
        let {
            total,
            filters
        } = this.props;
        let [first = {}] = filters;
        return (
            <div className="abc-compre-result-list-filter">
                <div className="filter-item" style={{color: '#4a4a4a'}}>
                    为您找到相关结果约 <strong>{total}</strong> 个
                </div>
                <div className="filter-item">
                    <Select defaultValue={first.key || ""} onChange={this.handleFilterClick}>
                        {
                            filters.map((item, index) => {
                                return (
                                    <Select.Option key={index} value={item.key} className={"abc-select-option-item"}>{item.text || "全部类型"}</Select.Option>
                                )
                            })
                        }
                    </Select>
                </div>
                
                {total > 0  && <div className="fr">
                    <div className="filter-item">
                        <Button className={'batch-export-button report-batch-export-button'}>
                            <Icon className={'anticon anticon-export'}></Icon>
                            批量导出
                        </Button>
                    </div>
                </div>}
            </div>
        );
    }
}

ResultListFilter.propTypes = {
    // 搜索结果总数
    total: PropTypes.number,
    // 数据筛选条件
    filters: PropTypes.array,
};

ResultListFilter.defaultProps = {
    total: 0,
    filters: [],
};

export default ResultListFilter;