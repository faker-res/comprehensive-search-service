import React, { Component } from 'react'
import PropTypes from 'prop-types'
import './style.scss'
import isEmpty from 'lodash/isEmpty'
import axios from "axios/index";
import ejson from "mongodb-extjson";



class  Tablelist extends Component {
    constructor(props) {
       super(props)
       this.state={limitShowArr: []}
    }

    componentDidMount = () => {
      this.isTableDataUrl&& this.fetchTableData()
    }

    //关键字标红
    parseRedText = (text,keyword) =>{
      let title = text.toString();
      let value  = keyword;
      title = title.split(value).join('<span style="color:red;">' + value + '</span>');
      return title;
    }

    //解析数据成二维数组
    parseDataToArry = (rawData) => {
      let tableArr = [];
      rawData.forEach( item => {
        if ( !Array.isArray(tableArr[item.row]) )  tableArr[item.row] = [];
        tableArr[item.row][item.column] = item;
      });

      //对多单元格的条纹颜色处理
      let flag = 'even', tr_count = 0;
      const tableDataNew = [];
      tableArr.forEach( (item,index) => {
        if (item){
          const colorFlag = tr_count === 0 ? (flag === 'even' ? flag = 'odd' : flag = 'even') : flag;
          item.forEach( (it,id) => {
            if (it.rowSpan && it.rowSpan > 1 && tr_count < it.rowSpan) tr_count = it.rowSpan;
          } );
          if (tr_count > 0) tr_count--;
          tableDataNew.push({
            colorFlag,
            arr_trs : item
          })
        }
      } );

      for (let i = 0;i < tableDataNew.length;i++){
        if (i > 0 && tableDataNew[i].colorFlag === 'even') break;
        tableDataNew[i].colorFlag += ' head-flag';
      }

      return tableDataNew;
    }

    // 判断表格数据是否为一个URL地址
    isTableDataUrl = () => {
      return /^https?:\/\//.test(this.props.data.table_data);
    }

    // 获取表格数据
    fetchTableData = async () => {
      const url = this.props.data.table_data.replace(/^http:/, 'https:');
      const res = await axios.get(url);
      let tableData = ejson.stringify(
          ejson.parse(res.request.responseText).data,
          {relaxed: true}
      );
      tableData = tableData? JSON.parse(tableData) : [];
      let tableArr = this.parseDataToArry(tableData);
      const limitShowArr = tableArr.slice(0,5);//限制表格最多显示5行
      this.setState({
        limitShowArr: limitShowArr,
      })
    }

    render() {
      // const {data} = this.props
      // if (typeof data.table_data == "string"){
      //   data.table_data = data.table_data? JSON.parse(data.table_data) : [];
      // }
      // let rawData = data.table_data;
      // let tableArr = this.parseDataToArry(rawData);
      // const limitShowArr = tableArr.slice(0,5);//限制表格最多显示5行
      return (
        <table width="100%" border="0" cellSpacing="0" cellPadding="0">
          <tbody>
          {
           this.state.limitShowArr && this.state.limitShowArr.map((item,index)=>{
              return (
                <tr key={index} className={item.colorFlag}>
                  {item.arr_trs.map(t=>{
                    if (t.row === 0){
                      return (
                        <th key={`${t.row}-${t.column}`} colSpan={t.colSpan ? t.colSpan : ''} rowSpan={t.rowSpan ? t.rowSpan : ''} title={t.text}>{t.text}</th>
                      )
                    } else {
                      return (
                        <td key={`${t.row}-${t.column}`} colSpan={t.colSpan ? t.colSpan : ''} rowSpan={t.rowSpan ? t.rowSpan : ''} title={t.text}>{t.text}</td>
                      )
                    }
                  })}
                </tr>
              )
            } )
          }
          </tbody>
        </table>
      )
    }
}

// 默认props值
Tablelist.defaultProps = {
    data: {}
}
// props 类型
Tablelist.propTypes = {
    data: PropTypes.object
}

export default Tablelist