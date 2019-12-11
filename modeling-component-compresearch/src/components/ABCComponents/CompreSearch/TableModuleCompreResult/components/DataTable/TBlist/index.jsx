import React, { Component } from 'react'
import PropTypes from 'prop-types'
import './style.scss'
import Highlighter from "react-highlight-words";

class Table extends Component {
    constructor(props) {
        super(props)
    }

    //解析数据成二维数组
    parseDataToArry = (rawData) => {
    let tableArr = [];
    rawData.forEach( item => {
      if( !Array.isArray(tableArr[item.row]) )  tableArr[item.row] = [];
      tableArr[item.row][item.column] = item;
    });
    //对多单元格的条纹颜色处理
    let flag = 'even', tr_count = 0;
    const tableDataNew = []; 
    tableArr.forEach( (item,index) => {
      if(item){
        const colorFlag = tr_count===0? (flag === 'even'? flag = 'odd':flag = 'even') : flag;
        item.forEach( (it,id) => {
          if(it.rowSpan && it.rowSpan > 1 && tr_count < it.rowSpan) tr_count = it.rowSpan;
        } );
        if(tr_count > 0) tr_count--;
        tableDataNew.push({
          colorFlag,
          arr_trs : item
        })
      };
    } );
    for(let i=0;i<tableDataNew.length;i++){
      if(i > 0 && tableDataNew[i].colorFlag === 'even') break;
      tableDataNew[i].colorFlag += ' head-flag';
    };

    return tableDataNew;
  }

  render() {
    const {data,keyword} = this.props
    if(typeof data.table_data === "string"){
      data.table_data = data.table_data? JSON.parse(data.table_data) : [];
    }
    let rawData = data.table_data;
    let tableArr = this.parseDataToArry(rawData);

    return (
      <table width="100%" border="0" cellSpacing="0" cellPadding="0">
        <tbody>
        {
          tableArr.map( (item,index)=>{
            return (
              <tr key={index} className={item.colorFlag}>
                {item.arr_trs.map(t=>{
                  if(t.row === 0){
                    return (
                      <th key={`${t.row}-${t.column}`} colSpan={t.colSpan? t.colSpan:''} rowSpan={t.rowSpan?t.rowSpan:''} title={t.text}>
                        <Highlighter
                          highlightClassName="gaoliang"
                          searchWords={keyword.split(" ")}
                          autoEscape={true}
                          textToHighlight={t.text}
                        />
                      </th>
                    )
                  }else{
                    return (
                      <td key={`${t.row}-${t.column}`} colSpan={t.colSpan? t.colSpan:''} rowSpan={t.rowSpan?t.rowSpan:''} title={t.text}>
                        <Highlighter
                          highlightClassName="gaoliang"
                          searchWords={keyword.split(" ")}
                          autoEscape={true}
                          textToHighlight={t.text}
                        />
                      </td>
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
Table.defaultProps = {
    data: {},
    keyword: ''
}
// props 类型
Table.propTypes = {
    data: PropTypes.object,
    keyword: PropTypes.string
}

export default Table