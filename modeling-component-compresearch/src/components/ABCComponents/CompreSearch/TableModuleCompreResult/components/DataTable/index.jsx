import React, { Component } from 'react'
import PropTypes from 'prop-types'
import queryString from 'query-string';
import { inject, observer } from 'mobx-react'
import { withRouter, Link, Redirect } from 'react-router-dom';
import XLSX from 'xlsx';
import moment from 'moment'
import TBlist from './TBlist'
// import Favorite from '../Favorite';
import { Menu, Dropdown, Icon, Spin } from 'antd';
// import FileSaver from 'file-saver';
import _ from 'lodash'
import Highlighter from "react-highlight-words";
// import { origin_report, origin_notice, origin_charttable } from '../../constants';
import axios from 'axios';
import ejson from 'mongodb-extjson';
import {delHtmlTag} from '../../../../../../lib/utils'
import './style.scss'

// 配置环境
// const origin_data = origin_charttable;

@withRouter
// @inject('favoriteStore')
@observer
class DataTable extends Component {
    constructor(props) {
        super(props)
        this.state = {
            tableData: this.isTableDataUrl() ? null : props.data.table_data,
            isClickButtonNext: false,
            showRightIcon:false,
            // arr_2D : props.data? this.parseDataToArry(JSON.parse(props.data.table_data)) : []
        }
        this.tableWrap = React.createRef();
    }
    componentDidMount() {
        setTimeout(()=>{
            if(!this.tableWrap.current) return;
            const tag = this.tableWrap.current.querySelector('.gaoliang');
            if(tag){
                const left = tag.offsetLeft;
                const tdLeft = tag.offsetParent.offsetLeft;
                this.tableWrap.current.scrollLeft = left + tdLeft - 5;
            };
        },0);

        // this.props.favoriteStore.fetchFavoriteBySourceId(this.props.data.id, 'table');
        // console.log(tags);

        // 如果表格数据为URL，根据该URL获取表格数据
        this.isTableDataUrl() && this.fetchTableData();
    }

    // 判断表格数据是否为一个URL地址
    isTableDataUrl = () => {
        return /^https?:\/\//.test(this.props.data.table_data);
    }

    // 获取表格数据
    fetchTableData = async () => {
        const url = this.props.data.table_data.replace(/^http:/, 'https:');
        const res = await axios.get(url);
        const tableData = ejson.stringify(
            ejson.parse(res.request.responseText).data,
            {relaxed: true}
        );
        this.setState({tableData});
    }

    //解析数据成二维数组
    parseDataToArry = (rawData) => {
        let tableArr = [];
        rawData.forEach( item => {
            if( !Array.isArray(tableArr[item.row]) )  tableArr[item.row] = [];
            tableArr[item.row][item.column] = item;
        });
        return tableArr;
    }

    downloadTableData = (title,data) => {
        const arr_2D = data.map(item=>item.map(it=>it.text));
        const worksheet = XLSX.utils.aoa_to_sheet(arr_2D);
        const new_workbook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(new_workbook, worksheet, "SheetJS");
        XLSX.writeFile(new_workbook, `${title}.xlsx`);
    }

    exportExcle = (e,data)=>{
      let rawData=data.table_data;
      let title = delHtmlTag(data.table_title);
      let tableArr = this.parseDataToArry(rawData);
      this.downloadTableData(title,tableArr);

      // let str ;
      // for(let i=0;i<tableArr.length;i++){
      //  for(let j=0;j<tableArr[i].length;j++){
      //    str += tableArr[i][j].text+',';
      //  }
      //  str += '\n';
      // }
      //Excel打开后中文乱码添加如下字符串解决
      // let exportContent = "\uFEFF";
      // let blob = new Blob([exportContent + str], {
      //  type: "text/plain;charset=utf-8"
      // });
      // FileSaver.saveAs(blob, "List.csv");
    }

    mouseEnter = ()=>{
        this.setState({showRightIcon:true})
    }
    mouseLeave = ()=>{
        this.setState({showRightIcon:false})
    }
    golink = (e,id) =>{
        // let url=`${origin_data}/table/${encodeURIComponent(id)}`
        // window.open(url);
    }
    sourceLink = (val)=>{
        // let url;
        // if(parseFloat(val.table_ori) === 2){
        //     url = val.table_source;
        // }else if(val.table_source){
        //     const pageNum = this.getPageNum(val.src_id, val.id);
        //     if(val.table_source.indexOf('juchao_tables') > -1){//跳到公告
        //         const page = pageNum? `&page=${pageNum}` : '';
        //         url = `${origin_notice}/detail/text?srcId=${val.src_id}${page}`;
        //     }else if(val.table_source.indexOf('hb_tables') > -1){//跳到研报
        //         const page = pageNum? `?page=${pageNum}` : '';
        //         url = `${origin_report}/report/article/${val.src_id}${page}`;
        //     };
        // };
        // if(url) window.open(url);
    }
    getPageNum = (src_id, id)=>{
        return id.split(src_id)[1].match(/^_([0-9]*?)_/)[1];
    }
    stripHtml = (html) => {
        const tmp = document.createElement('div');
        tmp.innerHTML = html;
        return tmp.textContent || tmp.innerText || '';
    }

    render() {
        //let {data} = this.props
        //let TableData = data.item
        let {data, keyword, favoriteStore} = this.props
        // let { saveFavorite, favoriteList, removeFavorite } = favoriteStore;
        let val = _.cloneDeep(data || {});
        val.table_data = this.state.tableData;

        // const favorite = favoriteList.filter(item => item.source_id === data.id)[0] || {
        //     source_id: data.id,
        //     favorite_id: '',
        //     type: 'table',
        //     tags: [],
        // };

        const menu = (
            <Menu >
                <Menu.Item key="0" onClick={(e)=>this.exportExcle(e,val)} >
                    <span>导出数据</span>
                </Menu.Item>
            </Menu>
        );

        return (
            <div>
                <div className="DataTable-Container">
                    <div className="tableListView" >
                        <ul className="swiper-wrapper">
                            {
                                <li className="swiper-slide"   onMouseEnter={this.mouseEnter} onMouseLeave={this.mouseLeave}>
                                    <div className="single-tab single-tab-tableData">
                                        {
                                            this.state.showRightIcon &&
                                            <div className="pos-right">
                                                {/*<Favorite*/}
                                                    {/*onSave={saveFavorite}*/}
                                                    {/*dataSource={favorite}*/}
                                                    {/*cancelFavorite={removeFavorite}*/}
                                                    {/*placement="bottomRight"*/}
                                                    {/*btnText=""*/}
                                                    {/*style={{marginRight:10}}*/}
                                                {/*/>*/}
                                                <Dropdown overlay={menu} placement="bottomRight" >
                                                    <a className="ant-dropdown-link" href="javascript:;">
                                                        <Icon type="bars" />
                                                    </a>
                                                </Dropdown>
                                            </div>
                                        }
                                        <div className="single-tab-chart-head">
                                            <div className="title ellipsis" title={this.stripHtml(val.table_title)}>
                                                <a
                                                    target="_blank"
                                                    href={`/tableDetail?id=${val.id}`}
                                                    // href={`${origin_data}/table/${encodeURIComponent(val.id)}`}
                                                    dangerouslySetInnerHTML={{__html: val.table_title}}
                                                />
                                            </div>
                                            <div
                                                className="date font-12 color-gray">{moment(val.time * 1000).format('YYYY/MM/DD')}
                                            </div>
                                        </div>
                                        <a href={`/tableDetail?id=${val.id}`} target='_blank' className="single-tab-table-show">
                                            <Spin size="large" spinning={_.isEmpty(val.table_data)}>
                                                <div className="table-fixed"  onClick={(e)=>{this.golink(e,val.id)}} ref={this.tableWrap} >
                                                    {!_.isEmpty(val.table_data) &&
                                                    <TBlist data={val}  keyword={this.props.keyword} />
                                                    }
                                                </div>
                                            </Spin>
                                            {/* <a href={`${origin_data}/table/${encodeURIComponent(val.id)}`}  target="_blank">
                                              <div className="table-ellipsis">
                                                ...
                                              </div>
                                            </a> */}
                                        </a>
                                        <div className="single-tab-table-desc">
                                            {
                                                val.company &&
                                                <p>
                                                    <span >公司:</span>
                                                    <span dangerouslySetInnerHTML={{__html: val.company}} />
                                                </p>
                                            }
                                            <p className="ellipsis">
                                                <span >来源:</span>
                                                <a
                                                    className="color-blue"
                                                    target="_blank"
                                                    title={this.stripHtml(val.title)}
                                                    onClick={()=>{this.sourceLink(val);}}
                                                    dangerouslySetInnerHTML={{__html: val.title}}
                                                />
                                            </p>
                                            <p >
                                                <span >类别:</span>
                                                <span dangerouslySetInnerHTML={{__html: val.type}} />
                                            </p>
                                        </div>
                                    </div>
                                </li>
                            }
                        </ul>
                    </div>
                </div>
            </div>
        )
    }
}


// props 类型
DataTable.propTypes = {
    data: PropTypes.object
}


export default DataTable