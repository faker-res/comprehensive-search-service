import React, { Component } from 'react'
import PropTypes from 'prop-types'
import queryString from 'query-string';
import { withRouter, Link, Redirect } from 'react-router-dom';
import XLSX from 'xlsx';
import moment from 'moment'
import TBlist from './TBlist'
import { Menu, Dropdown, Icon } from 'antd';
import FileSaver from 'file-saver';
import _ from 'lodash'
import Highlighter from "react-highlight-words";
import ask from '../../lib/ask'
import './style.scss'



@withRouter
class DataTable extends Component {
    constructor(props) {
        super(props)
        this.state = {
            isClickButtonNext: false,
            showRightIcon:false
        }
    }

    componentDidMount() {

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
        let title = data.table_title;
        let tableArr = this.parseDataToArry(rawData);
        this.downloadTableData(title,tableArr);
       /* let str ;

        for(let i=0;i<tableArr.length;i++){
          for(let j=0;j<tableArr[i].length;j++){
            str += tableArr[i][j].text+',';
          }
          str += '\n';
        }
        //Excel打开后中文乱码添加如下字符串解决
        let exportContent = "\uFEFF";
        let blob = new Blob([exportContent + str], {
          type: "text/plain;charset=utf-8"
        });
        FileSaver.saveAs(blob, "List.csv");*/
    }

    mouseEnter = ()=>{
      this.setState({showRightIcon:true})
    }
    mouseLeave = ()=>{
      this.setState({showRightIcon:false})
    }
    golink = (e,id) =>{
      let url=`/table/${encodeURIComponent(id)}`
      window.open(url);
    }

    //关键字标红
    parseRedText = (text,keyword) =>{
      text = text || '';
      keyword = keyword || '';
      let title = text.toString().toUpperCase();
      if (keyword) {
        let value  = keyword.toUpperCase();
        title = title.split(value).join('<span style="color:red;">' + value + '</span>');
      }
     
      return title;
    }

    render() {
        //let {data} = this.props
        //let TableData = data.item
        let {data,keyword = ''} = this.props
        let val = data || {}
        //标题关键字标红
        let title = this.parseRedText(val.table_title,keyword);
        //来源标红
        let sourcetitle = this.parseRedText(val.title,keyword);
        //类别标红
        let type = this.parseRedText(val.type,keyword);

        const menu = (
              <Menu >
                  <Menu.Item key="0" >
                      <span  onClick={(e)=>this.exportExcle(e,val)}>导出数据</span>
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
                                          <Dropdown overlay={menu} placement="bottomRight" >
                                            <a className="ant-dropdown-link" href="javascript:;">
                                              <Icon type="bars" />
                                            </a>
                                          </Dropdown>
                                        </div>
                                      }
                                        <div className="single-tab-chart-head">
                                            <div className="title ellipsis" title={val.table_title}>
                                              <Link to={`/table/${encodeURIComponent(val.id)}`}  target="_blank">
                                                <Highlighter
                                                  highlightClassName="gaoliang"
                                                  searchWords={keyword.split(" ")}
                                                  autoEscape={true}
                                                  textToHighlight={val.table_title}
                                                />
                                              </Link>
                                            </div>
                                            <div
                                                className="date font-12 color-grey">{moment(val.time * 1000).format('YYYY/MM/DD')}
                                            </div>
                                        </div>
                                        <div className="single-tab-table-show">
                                            <div className="table-fixed"  onClick={(e)=>{this.golink(e,val.id)}}>
                                                <TBlist data={val}  keyword={this.props.keyword}/>
                                            </div>
                                            <Link to={`/table/${encodeURIComponent(val.id)}`}  target="_blank">
                                              <div className="table-ellipsis">
                                                ...
                                              </div>
                                            </Link>
                                        </div>
                                        <div className="single-tab-table-desc">
                                            <p className="ellipsis">
                                                <span >来源:</span>
                                                <a className="color-blue" target="_blank"
                                                   href={`https://v1.analyst.ai/chart/details?id=${val.table_id}&type=table&tab=fileviewer&typesource=${val.table_source}`}
                                                   title={val.title} >
                                                  <Highlighter
                                                    highlightClassName="gaoliang"
                                                    searchWords={keyword.split(" ")}
                                                    autoEscape={true}
                                                    textToHighlight={val.title}
                                                  />
                                                </a>
                                            </p>
                                            <p >
                                                <span >类别:</span>
                                                <span>
                                                   <Highlighter
                                                     highlightClassName="gaoliang"
                                                     searchWords={keyword.split(" ")}
                                                     autoEscape={true}
                                                     textToHighlight={val.type}
                                                   />
                                                </span>
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