/**
 * @description edb-table
 * @author momoxsy
 * date: 2018-05-29
 */

import React, { Component } from 'react';
import PropTypes, { func } from 'prop-types';
import { Switch, Route, withRouter } from 'react-router-dom';
import classNames from 'classnames';
import { Table, Tooltip,Select } from 'antd';
import queryString from 'query-string';
import _ from 'lodash';
import ask from '../../lib/ask';

import SelectFilter from '../SelectFilter';
import AreaFilter from '../AreaFilter';
import TableFilter from '../TableFilter';


import './style.scss';

//import {testData} from '../EdbTable/data';

const Option = Select.Option;

@withRouter
class EdbTable extends Component{

    //添加默认搜索词GDP
    constructor(props) {
        super(props);
        this.defaultFilter = {
            mul_address: '中国##null##null##null'
        }
        this.state = {
            loading: true,
            filterLoading: true,
            pagination: {
                defaultPageSize: 20,
                current: 1
            },
            item: [],
            filterEnum: this.getFilterEnums(),
            filters: this.getDefaultFilters(),
            filtersHandles: [],
            total_count: 0,
            keyword: this.getQuery(props.location.search).keyword
        }
    }

    getFilterEnums() {

        const typeData = [{
                key: "当期值",
                value: "当期值",
                count: 0,
                disabled: false
            },{
                key: "累计值",
                value: "累计值",
                count: 0,
                disabled: false
            },{
                key: "同比",
                value: "同比",
                count: 0,
                disabled: false
            },{
                key: "环比",
                value: "环比",
                count: 0,
                disabled: false
            }];
        const frequencyData = [
            {
                key: "日",
                value: "日",
                count: 0,
                disabled: false
            },{
                key: "周",
                value: "周",
                count: 0,
                disabled: false
            },{
                key: "月",
                value: "月",
                count: 0,
                disabled: false
            },{
                key: "季",
                value: "季",
                count: 0,
                disabled: false
            },{
                key: "半年",
                value: "半年",
                count: 0,
                disabled: false
            },{
                key: "年",
                value: "年",
                count: 0,
                disabled: false
            }];

        return {
            type: {
                totalCount: 0,
                data: typeData
            },
            frequency: {
                totalCount: 0,
                data: frequencyData
            },
            mul_address: ["中国"]
        };
        
    }

    handleData(data = [], keyword) {

        let resData = [];
        data.map((d, i)=> {

            let len = d.doclist.docs.length;
            let obj = {};

            d.doclist.docs.map((dl, index)=> {

                dl.isConcat = !!index;
                dl.rowSpan = len;
                dl.area = this.getArea(dl);
                dl.last_value = this.formatNumber(dl.last_value);
                dl.uri = '/edbDetail?id='+ dl.id;
        
                if(index) {
                    dl.key = i + '_' + index;
                    obj.children = obj.children || [];
                    dl.area = '';
                    dl.name = '';
                    obj.children.push(dl);
                }else{
                    dl.key = i;
                    dl.name = `<span isActive=${i === 0} class='showMore ${i === 0 &&len ? "active" : ""} ${len > 1 ? "" : "zero"}'>${len > 1 ? len : ''}</span><span class="title-name">${dl.name}</span>`;
                    obj = JSON.parse(JSON.stringify(dl));
                }

            });

            resData.push(obj);

        })
        
        return resData;

    }

    handleFilterData(filters) {

        let baseFilter = this.getFilterEnums();

        return {
            frequency: this.handleSingleFilter(baseFilter.frequency, filters.cfrequency),
            type: this.handleSingleFilter(baseFilter.type, filters.ctype),
            mul_address: this.handleAreaFilter(filters.mul_address),
            source: filters.csource
        };

    }

    handleSingleFilter(baseData, filters=[]) {
        
        let totalCount = 0;
        const data =  baseData.data.map(b=> {

            for(let i = 0; i < filters.length; i++) {

                let f = filters[i];

                b.disabled = true;

                if(b.key === f.type) {

                    b.count = f.value;
                    b.disabled = false;
                    totalCount += b.count;
                    break;

                }

            }

            return b;

        })

        return {
            data,
            totalCount
        };

    }
    
    handleAreaFilter(addressArea={}) {

        let areaArr = [];

        for(let k in addressArea) {

            if(k !== 'total_count') {

                areaArr.push(k);

                if(addressArea[k]) {

                    areaArr = areaArr.concat(this.handleAreaFilter(addressArea[k]));
                
                }

            }

        }

        return areaArr;

    }

    renderColumn() {

        const columnList = this.getColumnList();
        
        return columnList.map((column)=> {

            return {
                title: column.value,
                dataIndex: column.key,
                width: column.width,
                className: column.className,
                render: column.render
            };

        });

    }

    render() {

        let { item, filterEnum, filtersHandles } = this.state;
        const columns = this.renderColumn();
        let filters = filtersHandles;

        return (
            <div className="container">
                {/* <div className="title">
                    <strong>经济数据</strong>
                </div> */}
                <div className="filters">
                    <AreaFilter 
                        loading={this.state.filterLoading} 
                        visible={this.state['mul_address_visible']} 
                        getFilters={this.getSelectFilter.bind(this)} 
                        data={filters.mul_address || filterEnum.mul_address} 
                        onClickFun={this.dropDownListClick} 
                        dataKey="mul_address" 
                        clear={this.state['mul_address_clear']}
                        newRequest={this.state.newRequest}
                    />
                    <SelectFilter 
                        loading={this.state.filterLoading} 
                        visible={this.state['ctype_visible']} 
                        getFilters={this.getSelectFilter.bind(this)} 
                        data={filters.type || filterEnum.type} 
                        title="统计方式" 
                        dataKey="ctype" 
                        clear={this.state['ctype_clear']}
                        onClickFun={this.dropDownListClick}
                    />
                    <SelectFilter 
                        loading={this.state.filterLoading} 
                        visible={this.state['cfrequency_visible']} 
                        getFilters={this.getSelectFilter.bind(this)} 
                        data={filters.frequency || filterEnum.frequency} 
                        title="频度" 
                        dataKey="cfrequency" 
                        clear={this.state['cfrequency_clear']}
                        onClickFun={this.dropDownListClick}
                    />
                    <TableFilter 
                        loading={this.state.filterLoading} 
                        visible={this.state['csource_visible']} 
                        getFilters={this.getSelectFilter.bind(this)} 
                        Filterdata={filters.source || []} 
                        title="来源" 
                        dataKey="csource" 
                        clear={this.state['csource_clear']}
                        onClickFun={this.dropDownListClick}
                    />
                    <div className="clearAll clear-all" onClick={this.onClearAllFilter.bind(this)}><a>清除全部</a></div>
                </div>
                <div className="content">
                    <div className="total_count">为您找到相关结果<a>{this.formatNumber(this.state.total_count).split('.')[0]}</a>条</div>
                    <Table
                        columns={columns} 
                        rowClassName={(record, index)=> index%2 === 0 ? 'color-grey': 'color-white'}
                        onRow={this.rowFun.bind(this)}
                        dataSource={item} 
                        pagination={this.state.pagination}
                        loading={this.state.loading}
                        onChange={this.handleTableChange.bind(this)}
                        defaultExpandedRowKeys={[0]}
                    />
                </div>
        </div>);
        
    }
    dropDownListClick = (target) => {

        if(!target.draggable) {
            return false;
        }

        let filterKey = target.getAttribute('data-key');
        let {filters} = this.state;

        //filters = this.addDefaultFilters(filters);

        filters[filterKey] = '';

        this.setState({
            [filterKey + '_visible']: false,
            filterLoading: true
        });
        this.fetchFilter({
            results: 0,
            page: 1,
            filters,
            keyword: this.state.keyword,
            currentKey: filterKey
        });

    }

    onClearAllFilter() {

        this.setState({
            ctype_clear: new Date().getTime(),
            cfrequency_clear: new Date().getTime(),
            csource_clear: new Date().getTime(),
            mul_address_clear: new Date().getTime(),
            filters: []
        });

        this.fetch({
            results: 20,
            page: 1,
            filters: [],
            keyword: this.state.keyword
        });

    }

    handleTableChange = (pagination, filters, sorter) => {
        const pager = this.state.pagination;
        const { keyword } = this.state;
    
        pager.current = pagination.current;
        this.setState({
            pagination: pager,
        });
        this.fetch({
            results: pagination.pageSize,
            page: pagination.current,
            sortField: sorter.field,
            sortOrder: sorter.order,
            filters: this.state.filters,
            keyword
        });
    }

    getSelectFilter(key, filters) {
        
        let currentFilters = this.state.filters;
        currentFilters[key] = filters.join('@@');
        this.fetch({
            results: 20,
            page: 1,
            filters: currentFilters,
            keyword: this.state.keyword
        });

    }

    fetch = (params = {}) => {
        let self = this;
        this.setState({ loading: true });

        let input_from = '';
        // try{
        //     input_from = JSON.parse(localStorage.getItem('logInfo')).input_from;
        // }catch(e) {
        //     console.log(e);
        // }
        let requestParams = {
            keyword: params.keyword,
            limit: params.results,
            offset: params.results*(params.page - 1 || 0),
            input_from: input_from || 'direct',
            pageSource: 'edb_result'
        };
        for(let k in params.filters) {

            params.filters[k]&&(requestParams[k] = params.filters[k]);

        }

        //requestParams = this.addDefaultFilters(requestParams);
        ask('edbDataSearch', {
            params: {...requestParams},
            headers:{
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        }).then((resp) => {
            resp = resp.data || resp;
            const pagination = { ...self.state.pagination };
            let items = [];
            let total_count = 0;
            let filtersHandles = [];
            try{

                const {data} = resp;
                items = self.handleData(data.item, params.keyword);
                filtersHandles = self.handleFilterData(data.filter);
                total_count = data.total_count;
                pagination.total = data.total_count_page;
                
            }catch(e) {

                console.log(e);
                pagination.total = 0;

            }
            this.setState({
                loading: false,
                item: items,
                filtersHandles,
                total_count,
                pagination
            });
        });
    }

    fetchFilter = (params = {}) => {
        let self = this;

        let requestParams = {
            keyword: params.keyword,
            limit: 0,
            offset: 0,
            input_from: 'direct',
            pageSource: 'edb_result'
        };
        let currentKey = params.currentKey;
        for(let k in params.filters) {

            params.filters[k]&&(requestParams[k] = params.filters[k]);

        }
        ask('edbDataSearch', {
            params: {...requestParams},
            headers:{
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        }).then((resp) => {
            resp = resp.data || resp;

            let filtersHandles = self.handleFilterData(resp.data&&resp.data.filter || {});

            this.setState({
                filtersHandles,
                filterLoading: false,
                [currentKey + '_visible']: true
            });
        });
    }

    componentDidMount() {

        this.fetch({
            keyword: this.state.keyword,
            results: this.state.pagination.defaultPageSize,
            page: this.state.pagination.current,
            filters: this.state.filters
        });

        let self = this;
        
        document.querySelector('body').removeEventListener('click', this.clickShowMoreFun);
        document.querySelector('body').addEventListener('click', this.clickShowMoreFun); 
    }

    clickShowMoreFun (e) {
        let target = e.target;
        if(target.className.indexOf('showMore') !== -1) {
            if(target.getAttribute('isActive') === "false") {
                target.className += ' active';
                target.parentNode.parentNode.parentNode.className += ' active';
                target.setAttribute('isActive', true);
            }else{
                target.className = target.className.split('active').join('').replace('  ', ' ');
                target.parentNode.parentNode.parentNode.className = target.parentNode.parentNode.parentNode.className.replace(/active/g, '').replace('  ', ' ');
                target.setAttribute('isActive', false);
            }
            target.parentNode.parentNode.querySelector('.ant-table-row-expand-icon').click();
        }

    }

    componentDidUpdate(prevProps) {

        // // 如果路由中的搜索关键字发生改变，将其同步到搜索框状态
        const preKeyword = prevProps.keyword || '';
        const keyword = this.props.keyword || '';
        // const filters = this.addDefaultFilters(this.state.filters);
        if(keyword !== preKeyword){
            const filters = this.addDefaultFilters(this.state.filters);
            this.setState({
                loading: true,
                pagination: {
                    defaultPageSize: 20,
                    current: 1
                },
                newRequest: new Date().getTime(),
                keyword
            });
            this.fetch({
                keyword,
                results: 20,
                page: 1,
                filters
                // filters: this.state.filters
            });
        }
        
    }


    rowFun(record) {

        return {
            onMouseEnter: (record)=> {
                record.currentTarget.querySelector('td>a.detail').className = 'detail';
            },
            onMouseLeave: (record)=> {
                record.currentTarget.querySelector('td>.detail').className += ' hide';
            }
        }

    }

    formatNumber(number) {

        number = parseFloat(number).toFixed(2);

        let returnNumber = 0;
        let numberObj = (number + '').split('.');
        let suffix = numberObj[1];
        let prefix = number - 0 > 0 ? '' : '-';
        numberObj[0] = numberObj[0].replace('-', '');
        let numberArr = (numberObj[0] + '').split('').reverse();
        let resArr = [];

        return prefix + (numberObj[0] + '').split('').reverse().map((n, index)=> {

            return (numberObj[0] + '').length > 3&&index&&index%3 == 0 ? (n + ',') : n;

        }).reverse().join('') + '.' + suffix;

    }

    //原有逻辑
    getArea(item) {

        return item.county_level_city || item.prefecture_level_city || item.province || item.country || item.area;
    
    }

    showHtml(record){
   
       var html = {__html: record};
   
       return <div dangerouslySetInnerHTML={html}></div> ;
   
    }

    getColumnList () {
        let tooltipText = '点击可查看数据来源';

        return [{
            key: "name",
            value: "指标",
            width: 200,
            render: text => this.showHtml(text)
        },{
            key: "area",
            value: "区域",
            width: 150,
            render: text => this.showHtml(text)
        },{
            key: "type",
            value: "统计方式",
            width: 110,
            render: text => this.showHtml(text)
        },{
            key: "frequency",
            value: "频度",
            width: 60,
            render: text => this.showHtml(text)
        },{
            key: "last_value",
            value: "最新值",
            className: "column-money",
            width: 190,
            render: text => this.showHtml(text)
        },{
            key: "unit",
            value: "单位",
            width: 80,
            render: text => this.showHtml(text)
        },{
            key: "last_date",
            value: "最新数据日期",
            width: 130,
            render: text => this.showHtml(text)
        },{
            key: "source",
            value: "数据来源",
            width: 140,
            render: (text, row)=> {
                
                if(row.source_url) {

                    text = <Tooltip placement="top" title={tooltipText}>
                                <a className="sourceUrl" href={row.source_url} target="_blank">{this.showHtml(text)}</a>
                            </Tooltip>;

                }

                return text;

            }

        },{
            key: "uri",
            value: "",
            render: text=> <a className="detail hide" href={text} target="_blank">查看</a>
        }];

    }

    // 获取查询参数
    getQuery = (search) => {
        return queryString.parse(
            _.isUndefined(search)
            ? this.props.location.search
            : search
        );
    }

    addDefaultFilters = (filters)=> {

        for(let k in this.defaultFilter) {

            filters[k] = filters[k] || this.defaultFilter[k];

        }

        return filters;

    }

    getDefaultFilters = ()=> {

        return JSON.parse(JSON.stringify(this.defaultFilter));

    }
    
}

// props 类型
EdbTable.propTypes = {
    title: PropTypes.string,
    keyword: PropTypes.string,
    data: PropTypes.object
}

export default EdbTable;