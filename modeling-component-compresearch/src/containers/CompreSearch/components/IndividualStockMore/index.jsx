import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
// import { translate } from 'react-i18next';
import { Layout, Row, Col } from 'antd';
import ask from '../../../../lib/ask';
import Find from 'lodash/find';
import queryString from 'query-string';
import AnalystsSelectFilter from '../../../../components/ABCComponents/CompreSearch/AnalystsSelectFilter'
import ThreeLevelSearchBox from '../../../../components/ABCComponents/CompreSearch/ThreeLevelSearchBox'
import UnitStockList from '../../../../components/ABCComponents/CompreSearch/UnitStockList'
import './style.scss';

const preFixCls = 'abcft-entity-search-IndividualStockMore';

@withRouter
// @translate()
export default class IndividualStockMore extends Component {
    constructor(props) {
        super(props)
        this.state = {
           IndividualStockMoreState: 'pending',
           ListData:{},
           searchInputValue:'',
           inputSuggestList:[],
           selectedValue: '',
           limit:10,
           offset:0,
           order:'DESC',
           prior:'pe',
           type: '',
           indu_name: '',
        }
    }

    componentWillMount () {
      let {type,indu_name} = queryString.parse(this.props.location.search);
      let info = {
        type:type||null,
        indu_name: indu_name||null,
      }
      this.setState({
        type: info.type || '',
        selectedValue: info.indu_name || '',
      }, () => {
        this.loadList()
      })
    }



    componentDidMount = async () => {
    }

    loadList = () => {
      this.setState({ IndividualStockMoreState: 'pending' });
      let param = {}
      param.limit = this.state.limit;
      param.offset = this.state.offset;
      param.order = this.state.order;
      param.prior = this.state.prior;
      param.abc_code = this.state.searchInputValue;
      param.indu_name = this.state.selectedValue;
      param.type = this.state.type;
      ask('industryStockInfoList', { params: param})
      .then(resp => {
          const { code, data, message } = resp;
          if (code !== 200) {
              throw new Error(`Response Exception:${message},code:${code}`);
          }
          if(data&&data.items&&data.items.length>0){
              this.setState({ ListData: data, IndividualStockMoreState: 'done' });
          }else{
            this.setState({ ListData: data, IndividualStockMoreState: 'error' });
          }
          
      })
      .catch(error => {
          console.error(error);
          this.setState({ IndividualStockMoreState: 'error' });
      })
    }

    // 获取建议数据
    getSearchSuggestData=(keyword,cb)=>{
        ask('stockRecommendGetstockList', { params: {stockstrict:false,limit:100, keyword:keyword}})
      .then(resp => {
          const { code, data, message } = resp;
          if (code !== 200) {
              throw new Error(`Response Exception:${message},code:${code}`);
          }
          this.setState({ inputSuggestList: data},()=>{
              if(cb){
                   cb();
              }
          });
      })
      .catch(error => {
          console.error(error);
      })
    }


      // 获取搜索建议
    handleRequestSearchSuggest = (keyword) => {
        this.setState({
            searchInputValue:keyword
        })
      this.getSearchSuggestData(keyword);
    }
    // 获取标准化的建议数据
    getNormalizedSuggestions () {
      const { t, suggestStore } = this.props;
      const suggestions = {
        default: [],
        search: [],
      };
  
      // 搜索建议映射
      this.state.inputSuggestList.length > 0 && suggestions.search.push(
        ThreeLevelSearchBox.getNormalizedSuggestionData({
          sectionType: 'searchSuggest',
          // sectionTitle: t('DefaultSearchBar.searchSuggestTitle', {defaultValue: '相关搜索'}),
          sectionTitle: '相关搜索',
          suggestions: this.state.inputSuggestList,
          suggestionId: (suggestion) => `searchSuggest-${suggestion.id}`,
          suggestionText: (suggestion) => `${suggestion.content}-${suggestion.code}`,
          suggestionQuery: (suggestion) => `${suggestion.content}-${suggestion.code}`,
        })
      );
  
      return suggestions;
    }

    clearInput=()=>{
        this.setState({
            searchInputValue:null
        })
    }

    getSelectValue = (selectedValue) => {
        this.setState({
            selectedValue,
            offset:0,
        }, () => {
            this.loadList()
        })
    }

    getSearchInput = (searchInputValue) => { 
      let searchSelectedValue = searchInputValue.keyword
      if(searchSelectedValue) {
        this.getSearchSuggestData(searchSelectedValue,()=>{
            if(searchSelectedValue.indexOf('-') !== -1)  {
                searchSelectedValue = searchSelectedValue.split('-')[1];
              }else{
                searchSelectedValue = Find(this.state.inputSuggestList, () => { return {'content':searchInputValue.keyword}});
                searchSelectedValue = searchSelectedValue ? searchSelectedValue.code : searchInputValue.keyword;
              }
              this.setState({
                searchInputValue:searchSelectedValue,            
                offset:0
            }, () => {
                this.loadList()
            })
        });
      }else{
        this.loadList()
      }
    }

    getListParams = (ListParams) => {
        this.setState({
            offset:ListParams.offset,
            prior:ListParams.prior,
            order:ListParams.order,
            type: ListParams.type,
        }, () => {
             this.loadList()
        })
    }

    
    render() {
        const {selectedValue,page_num,limit,ListData,IndividualStockMoreState,offset,type} = this.state;
        let current;
        if(offset===0){
            current=1
        }else{
            current=(offset+10)/limit
        }
        return (
            <Layout className={`${preFixCls}`}>
                <Layout.Content>
                    <Row>
                        <Col span={6}>
                          <AnalystsSelectFilter
                          getSelectValue={this.getSelectValue}
                          label='行业'
                          type={'IndividualStockMore'} 
                          defaultValue={selectedValue} 
                          askApi='BrokerIndustryList'/>
                        </Col>
                        {/*<Col offset={19}>*/}
                        <Col style={{float:'right'}}>
                          <ThreeLevelSearchBox
                           style={{width:317, marginLeft:'-35px'}}
                           placeholder="请输入股票名称或代码"
                           suggestions={this.getNormalizedSuggestions()}
                           searchHistoryTitle="最近搜过"
                           onRequestSearchSuggest={this.handleRequestSearchSuggest}
                           clickClearBtnCallBack={()=>this.clearInput()}
                           onSearch={this.getSearchInput}
                          />
                        </Col>
                    </Row>
                    <Row style={{marginTop:'20px'}}>
                        <UnitStockList getListParams={this.getListParams} current={current} limit={limit} data={ListData} dataState={IndividualStockMoreState} prior={this.state.prior} order={this.state.order} type={type}/>
                    </Row>
                </Layout.Content>
            </Layout>
        )
    }
}