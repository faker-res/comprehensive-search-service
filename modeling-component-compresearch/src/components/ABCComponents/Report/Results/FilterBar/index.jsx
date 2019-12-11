import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Checkbox,
    Select
} from 'antd';
import { withRouter } from 'react-router-dom';
import './index.scss';

const SelectOption = Select.Option;
const FILTER_TYPE_CHECKBOX = 'checkbox';
const FILTER_TYPE_SELECT   = 'select';

@withRouter
class SearchFilterBar extends Component {
    constructor(props) {
        super(props);

        this.state = {
            // 全部过滤条件
            allFilters: this.initAllFilterValues(props.filters || [])
        };

        this.handleRef = this.handleRef.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.initAllFilterValues = this.initAllFilterValues.bind(this);
    }

    initAllFilterValues(filters = []) {
        let allFilters = [];
        filters.forEach((filter, index) => {
            let {params = {}} = filter;

            allFilters.push({
                key: filter.key,
                type: filter.type,
                value: filter.type == FILTER_TYPE_CHECKBOX ? !!params.checked : params.defaultValue,
                params: filter.params,
                onChange: filter.onChange
            });
        });
        return allFilters;
    }

    handleRef(e) {
        let {
            handleRef
        } = this.props;

        this.refSearchBar = e;

        handleRef && handleRef(e);
    }
    
    handleChange(filter, data) {
        // 总的改变事件
        let {
            onChange
        } = this.props;

        onChange && onChange({
            filter,
            data,
        });
    }

    render() {
        let {
            className,
        } = this.props;
        let {
            filters
        } = this.props;

        return (
            <div ref={this.handleRef} className={`abc-search-filterbar ${className}`}>
                {
                    filters.map((filter, index) => {
                        let {params = {}, value} = filter;

                        return (
                            <div className={'filter-item'} key={index}>
                                {
                                    filter.type == FILTER_TYPE_CHECKBOX ?
                                    (
                                        <Checkbox checked={params.checked} onChange={(e) => {
                                            this.handleChange(filter, e.target.checked);
                                        }}>{params.text || ''}</Checkbox>
                                    )
                                    : filter.type == FILTER_TYPE_SELECT ?
                                    (
                                        <Select defaultValue={params.defaultValue} onChange={(value) => {
                                                this.handleChange(filter, value);
                                            }}>
                                            {
                                                (params.options || []).map((option, index) => {
                                                    return <SelectOption value={option.value} key={index}>{option.text || ''}</SelectOption>;
                                                })
                                            }
                                        </Select>
                                    )
                                    : null
                                }
                            </div>
                        );
                    })
                }
            </div>
        );
    }
}

SearchFilterBar.defaultProps = {
    language: 'zh-CN',
    pageSize: 20,
    className: '',
    handleRef: () => {},
    filters: []
};

SearchFilterBar.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 分页条数
    pageSize: PropTypes.number,
    // 附加类名
    className: PropTypes.string,
    // ref回调
    handleRef: PropTypes.func,
    // 过滤条件
    filters: PropTypes.arrayOf(PropTypes.shape({
        // 过滤条件key
        key: PropTypes.string,
        // 过滤类型
        type: PropTypes.oneOf(['checkbox', 'select']),
        // 附加数据
        params: PropTypes.object,
        // 过滤事件回调
        onChange: PropTypes.func
    })),
    // 变化事件
    onChange: PropTypes.func
};

export default SearchFilterBar;