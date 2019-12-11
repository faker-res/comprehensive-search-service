import React, { Component } from 'react';
import './index.scss'
import ask from '../../lib/ask'
import {Spin ,Icon} from 'antd'
import PropTypes from 'prop-types';
import moment from 'moment'
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
		ask('PrivateReportSearch', {
			data: {
				end_time: new Date().getTime(),
				page_num: 0,
				page_size: 999999,
				search_word: { 'keyword':keyword},
				involves: [],
				prior: 0,
				// search_log: window.search_log
			},
		}).then((resp) => {
			let { data = {} } = resp;
			let { resultList = [] } = this.state;
			let result = Object.assign([], resultList.concat(data.item || []));
			this.loading = false;
			this.setState({
                loading: false,
                myDataBaseCount:result.length,
				resultList: result,
            });
            if(result.length==0){
                console.log('没有数据');
            }
            this.props.onLoadSuccess(result.length||0);
		}).catch(() => {
			this.loading = false;
			this.setState({ loading: false, loadMore: false });
		});
    }
    //展示所有数据
    showAllMyDatabase=()=>{
        this.setState({
            showAll:true
        })
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
    switchMyDatabase=(length)=>{
        //当没有数据关闭切换效果
        if(length){
            this.setState({
                myDatabaseStatus:!this.state.myDatabaseStatus
            })
        }
    }
    //关闭tip 无数据
    closeNoData=()=>{
        this.setState({
            closeNoData:!this.state.closeNoData
        })
    }
    render() {
        // let downloadKey = ((data.file_url.match(/[^\?\/]+\?/) || [])[0] || '').replace(/\?/, '');
        let {showAll,resultList} = this.state;
        let {dataLists,defaultLength} =this.props
        if(!showAll){
            resultList = resultList.slice(0,3);
        }
        const noData =  (<label>
                            <a/>
                            <img src={require('../../assets/image/notice.png')}/>
                                暂无私有数据！
                            <img onClick={()=>this.closeNoData()} src={require('../../assets/image/cancel.png')}/>
                        </label>)
        return (
            <div className='MyDatabaseResult'>
                <div className='content'>
                    <div className='header'>
                        <div className='title'>
                            我的资源库里的结果
                            <span style={!this.state.myDatabaseStatus?{'display':'flex'}:{'display':'none'}}>{this.state.resultList.length||0}</span>
                            {
                                (this.state.closeNoData&&this.state.resultList&&this.state.resultList.length==0&&!this.state.loading)?noData:''
                            }
                        </div>
                        <div className='filter'>| <img onClick={()=>this.switchMyDatabase(resultList.length)} className='icon-arrow-down' src={this.state.myDatabaseStatus?require('../../assets/image/downArrow.png'):require('../../assets/image/upArrow.png')} /></div>
                    </div>
                    <ul className='databaseList' style={(this.state.myDatabaseStatus&&resultList&&resultList.length>0)?{'display':'block'}:{'display':'none'}}>
                        {   
                                resultList.map((item,index)=>{
                                return (
                                    <li key={item.id}>
                                    <a href={`/report-detail/article/${item.id}`} target="_blank"  className='title' dangerouslySetInnerHTML = {{ __html: item.source_title }} />
                                    <div className='context' dangerouslySetInnerHTML = {{ __html: item.summary }}/>
                                    <div className='source-tag'>
                                        <div>
                                            <span dangerouslySetInnerHTML={{__html: item.publish}} />
                                            <span dangerouslySetInnerHTML={{__html: item.industry_name}} />
                                            <span>{item.report_type}</span>
                                            <span>{item.file_pages|| 0}页</span>
                                            <span>
                                                <a title="点击下载源文件" href={`/search-api/download/report?file_key=${((item.file_url.match(/[^\?\/]+\?/) || [])[0] || '').replace(/\?/, '')}&file_name=${item.source_title && item.source_title.replace(/<\/?[^>]+>/g, '')}&file_type=${item.filetype}`} target="_blank">{item.filetype.replace('.','').toUpperCase()}</a>
                                                <nav className='source_from'>
                                                    <Icon type="compass" theme="outlined" />
                                                    <label>来自</label>
                                                    <label>{item.from.name}</label>
                                                    <label>{item.from.address}</label>
                                                </nav>
                                            </span>
                                        </div>
                                        <label>{item.time ? this.formatDate(new Date(item.time * 1000), 'YYYY/MM/DD') : '--'}</label>
                                    </div>
                                </li>
                                )
                            })
                        }
                    </ul>
                    {
                        this.state.loading?<div className='loadingData'><Spin/></div>:''
                    }
                    {   
                        (this.state.resultList.length>3&&!showAll&&this.state.myDatabaseStatus)?
                        <div onClick={()=>this.showAllMyDatabase()} className='footer'>
                            查看全部<a>{this.state.resultList.length||0}</a>条结果
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
    defaultLength:'3',
}
MyDatabaseResult.propTypes = {
    // 搜索关键字
    name: PropTypes.string,
    //数据源
    dataLists:PropTypes.array,
    //默认展示数据条数
    defaultLength:PropTypes.string,
};
export default MyDatabaseResult;