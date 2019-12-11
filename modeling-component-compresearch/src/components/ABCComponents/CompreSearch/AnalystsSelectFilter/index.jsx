import React, { Component } from 'react'
import PropTypes from 'prop-types'
import ask from '../../../../lib/ask';
import { Select } from 'antd';
import {SelectListData} from './data.js'
import queryString from 'query-string';
import _ from 'lodash'
import { withRouter } from 'react-router-dom';
import './style.scss'
const Option = Select.Option;


@withRouter
class  AnalystsSelectFilter extends Component {
    constructor(props) {
        super(props)
        this.state = {
            selectListStatus: 'done',
            selectListData: [],
        }
    }


    componentDidMount() {
       this.loadRecommendGetautocompleteList()
    }
    


    loadRecommendGetautocompleteList = () => {
        this.setState({ selectListStatus: 'pending' });
        const api = this.props.askApi
        ask(api).then(resp => {
            const { code, data, message } = resp;
            if (code !== 200) {
                throw new Error(`Response Exception:${message},code:${code}`);
            }
            this.props.getSelectData&&this.props.getSelectData(data);
            this.setState({ selectListData: data, selectListStatus: 'done' });
        })
        .catch(error => {
            console.error(error);
            this.setState({ selectListStatus: 'error' });
        })
    }



    handleChange = value =>  {
      this.props.getSelectValue(value);
      if(this.props.type === 'OfficialAccountsMore') {      
        this.changeURLAndRresh('classify',value);
      } else if (this.props.type === 'IndividualStockMore') {
        this.changeURLAndRresh('indu_name',value);
      } else {    
        this.changeURLAndRresh('indu_name',value);
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
        const {label, defaultValue, type} = this.props
        const {selectListData} = this.state;
        let optionList = selectListData&&selectListData.map((item, index) => {
            return <Option key={item}>{item}</Option>
        })
        if(type !== 'OfficialAccountsMore') {
            optionList = selectListData&&selectListData.map((item, index) => {
                return <Option key={item.indu_uni_code} value={item.indu_name}>{item.indu_name}</Option>
            })
        } 

        const allOption = this.props.type === 'OfficialAccountsMore' ? <Option key={'all'} value={'全部分类'}>{'全部分类'}</Option> : null
       

        return (
            <div className='AnalystsSelectFilter-container'>
                <div className='filter-contaniner'>
                    <span className='label'>{label}</span>
                    {defaultValue&&<Select
                    defaultValue={defaultValue}
                    style={{ width: 192 }}
                    onSelect={this.handleChange}>
                      {allOption}
                      {optionList}
                    </Select>}
                    {!defaultValue&&<Select
                    defaultValue={defaultValue}
                    style={{ width: 192 }}
                    onSelect={this.handleChange}>
                      {allOption}
                      {optionList}
                    </Select>}
                </div>
                
            </div>
        )
    }
}







// 默认props值
AnalystsSelectFilter.defaultProps = {
    label: '行业',
    defaultValue: 'lucy',
    selectListData:SelectListData.data,
    askApi:'BrokerIndustryList'
}
// // props 类型
// AnalystsSelectFilter.propTypes = {
//     onClickMore: PropTypes.func.isRequired,
//     isShowMore: PropTypes.bool,
// }

export default AnalystsSelectFilter








