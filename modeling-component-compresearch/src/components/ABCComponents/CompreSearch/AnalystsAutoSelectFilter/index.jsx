import React, { Component } from 'react'
import { Select } from 'antd';
import PropTypes from 'prop-types'
import ask from '../../../../lib/ask';
import './style.scss'
import queryString from 'query-string';
import _ from 'lodash'
import { withRouter } from 'react-router-dom';
const Option = Select.Option;

@withRouter
class AnalystsAutoSelectFilter extends Component {
    constructor(props) {
        super(props)
        this.catchDate = []
        this.state = {
            SelectFilterListData: [],
            value: this.props.defaultValue || '',
        } 
    }

    componentDidMount = async () => {
        this.loadRecommendGetautocompleteList(this.state.value)
        this.props.getSelectValue(this.state.value)
    }

    loadRecommendGetautocompleteList = (value) => {
        value = value === '全部' ? '' : value
        const api = this.props.askApi
        ask(api, { params: {type:50003, limit:100, keyword:value}}).then(resp => {
            const { code, data, message } = resp;
            if (code !== 200 || !data) {
                throw new Error(`Response Exception:${message},code:${code}`);
            }
            this.catchDate = this.catchDate.length ? this.catchDate : data;
            this.setState({ SelectFilterListData: data});
        })
        .catch(error => {
            console.error(error);
        })
    }


    handleSearch = (value) => {
        this.setState({value},() => {
        this.loadRecommendGetautocompleteList(this.state.value)
        this.props.getSelectValue(this.state.value)
        })
    }

    handleChange = (value) => {
       this.changeURLAndRresh('org_sname',value);
       this.setState({ value },() => {
            this.loadRecommendGetautocompleteList(this.state.value)
            this.props.getSelectValue(this.state.value)
       });
    }

    handleFocus = () => {
        if(!this.catchDate.length) {
            this.loadRecommendGetautocompleteList(this.state.value)
        }
        this.setState({ SelectFilterListData: this.catchDate, value:''});
    }

    handleBlur =() => {
        if(!this.state.value) {
            this.setState({
                value: '全部'
            },() => {
                this.props.getSelectValue(this.state.value)
                this.changeURLAndRresh('org_sname',this.state.value);
            })
        }
    }

         // 获取查询参数
  getQuery = (search) => {
    return queryString.parse(
      _.isUndefined(search)
        ? this.props.location.search
        : search
    );
  }
    //改变路由
  changeURLAndRresh = (key,value) =>{
    const { history } = this.props;
    const query = this.getQuery();
    const keyword = value.toString().trim();
    query[key] = query[key] || '';
    if(query.pageIndex && query.pageIndex > 0 && keyword !== query[key]) delete query.pageIndex;
    const method = query[key].trim() !== keyword ? 'push' : 'replace';
    keyword ? (query[key] = keyword) : delete query[key];

    let search = queryString.stringify(query, {encode: true});
    
    history[method]({search});
  }

    


    render() {
        const options = this.state.SelectFilterListData.map((item, idx) => <Option key={idx} value={item}>{item}</Option>);
        return (
            <div className='AnalystsAutoSelectFilter-contaniner'>
                <span className='label'>{this.props.label}</span>
                <Select
                showSearch
                value={this.state.value}
                style={{ width: 192 }}
                defaultActiveFirstOption={false}
                showArrow={true}
                filterOption={false}
                onSearch={this.handleSearch}
                onChange={this.handleChange}
                onFocus={this.handleFocus}
                onBlur={this.handleBlur}
                notFoundContent={'暂无数据'}
                >
                <Option key="all" value='全部'>全部</Option>
                {options}
                </Select>
            </div>
        )
    }
}


// 默认props值
AnalystsAutoSelectFilter.defaultProps = {
    // selectFilterListData:SelectFilterListData.data,
    placeholder: '请输入机构名称',
    defaultValue: '全部',
    askApi: 'qq',
    label: '机构',
}

export default AnalystsAutoSelectFilter








