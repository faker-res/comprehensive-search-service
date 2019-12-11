import React, { Component } from 'react';
import './index.scss'
import ask from '../../../../../../lib/ask'
import {Spin ,Icon, Row, Col} from 'antd'
import PropTypes from 'prop-types';
import moment from 'moment'
import ResultTable from '../DataTable';

class MyDatabaseResult extends Component {
    constructor(props){
        super(props)
        this.state={
            keyword:this.props.name,
            loading:true,
            closeNoData:true,
            myDatabaseStatus:true,
            showAll:false,
            loading: true,
            resultList: [],
            initMyDatabase:[],
            myDataBaseCount:0,
        }
    }
    componentDidMount = async () => {
		this.loadData(this.state.keyword);
    }
    componentDidUpdate=async(prevProps,prevState)=>{
        const { name } = this.props;
        if(name!==prevProps.name){
            this.setState({
                keyword:prevProps.name
            },()=>{
                this.loadData(name);
            })
        }
    }
    loadData(keyword) {
        this.setState({
            loading: true,
            resultList: [],
        });
        ask('TableModuleResult', {
          params: {
            end_time: new Date().getTime(),
            page: 'comprehensive_table_search' ,
            offset: 0,
            limit: 23,
            input_from: 'direct',
            keyword: keyword,
            // prior: 0,
          },
        }).then((resp) => {
          let { data = {} } = resp;
          let { resultList = [] } = this.state;
          let result = Object.assign([], resultList.concat(data.items || []));
          this.loading = false;
          this.setState({
            loading: false,
            myDataBaseCount:result.length,
            resultList: result,
          });
          if(result.length == 0){
              console.log('没有数据');
          }
          this.props.onLoadSuccess(result.length||0);
        }).catch(() => {
          this.loading = false;
          this.setState({ loading: false, loadMore: false });
        });
    }

    /**
     * 格式化时间
     * @param {Date} date 需要格式化的日期对象
     *  @param format  {string} 格式化字符串
     */
	formatDate(date = new Date(), format = 'YYYY.MM.DD') {
		return moment(date.getTime()).format(format);
    }
    //展开/收起我的资料库
    switchMyDatabase=()=>{
      //当没有数据关闭切换效果
      if (this.state.resultList.length <= this.props.defaultLength) {
        return
      }
      this.setState({
          myDatabaseStatus: !this.state.myDatabaseStatus,
          showAll: !this.state.showAll
      })
    }
    //关闭tip 无数据
    closeNoData=()=>{
        this.setState({
            closeNoData:!this.state.closeNoData
        })
    }
    render() {
        // let downloadKey = ((data.file_url.match(/[^\?\/]+\?/) || [])[0] || '').replace(/\?/, '');
        let {showAll, resultList: showResultList} = this.state;
        let {defaultLength} =this.props
        if(!showAll){
           showResultList = showResultList.slice(0,defaultLength);
        } else {
          showResultList = showResultList
        }
        const noData = (
            <label>
                <a/>
                <img src={require('../../../../../../assets/image/notice.png')}/>
                    暂无私有数据！
                <img onClick={()=>this.closeNoData()} src={require('../../../../../../assets/image/cancel.png')}/>
            </label>
        )
        return (
            <div className='MyTableDatabaseResult'>
                <div className='content'>
                    <div className='header'>
                        <div className='title'>
                            我的资源库里的结果
                            <span style={!this.state.myDatabaseStatus?{'display':'flex'}:{'display':'none'}}>{this.state.resultList.length||0}</span>
                            {
                                (this.state.closeNoData&&this.state.resultList&&this.state.resultList.length==0&&!this.state.loading)?noData:''
                            }
                        </div>
                      { <div className='filter'>| <img onClick={()=>this.switchMyDatabase()} className='icon-arrow-down' src={this.state.myDatabaseStatus && !showAll ? require('../../../../../../assets/image/downArrow.png'):
                          require('../../../../../../assets/image/upArrow.png')} /></div> }
                    </div>
                    <div className={'result-wrap'}>
                      <Row gutter={20}>
                        {/*<ResultTable data={data}/>*/}
                        {
                          showResultList.map((item, index) => {
                            return (
                                <Col span={12} key={index}>
                                  <ResultTable data={item} keyword={this.state.keywordServer}/>
                                </Col>
                            )
                          })
                        }
                      </Row>
                    </div>
                    {
                        this.state.loading?<div className='loadingData'><Spin/></div>:''
                    }
                    {   
                        (this.state.resultList.length > defaultLength && !showAll && this.state.myDatabaseStatus)?
                        <div onClick={()=>this.switchMyDatabase()} className='footer'>
                            查看全部
                          <span>{this.state.resultList.length||0}</span>条结果
                        </div>
                        :''
                    }
                   
                </div>
            </div>
        );
    }
}
MyDatabaseResult.defaultProps = {
    name: '',
    //数据源
    dataLists:[],
    //默认展示数据条数
    defaultLength: 2,
}
MyDatabaseResult.propTypes = {
    // 搜索关键字
    name: PropTypes.string,
    //数据源
    dataLists:PropTypes.array,
    //默认展示数据条数
    defaultLength:PropTypes.number,
};
export default MyDatabaseResult;