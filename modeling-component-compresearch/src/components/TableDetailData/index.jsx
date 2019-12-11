/**
 * @description 数据表
    @author ypeng
**/

import React, { Component } from 'react'
import {localClass} from './style.scss'

export default class TableDetailData extends Component {
    render(){
        const {data} = this.props;
        return (
            <div className={localClass}>
                <table width="100%" border="0" cellSpacing="0" cellPadding="0">
                    <tbody>
                        {
                            data.map( (item,index)=>{
                                return (
                                    <tr key={index} className={item.colorFlag}>
                                        {item.arr_trs.map(t=>{
                                            if(t.row === 0){
                                                return (
                                                    <th key={`${t.row}-${t.column}`} colSpan={t.colSpan? t.colSpan:''} rowSpan={t.rowSpan?t.rowSpan:''}>{t.text}</th>
                                                )
                                            }else{
                                                return (
                                                    <td key={`${t.row}-${t.column}`} colSpan={t.colSpan? t.colSpan:''} rowSpan={t.rowSpan?t.rowSpan:''}>{t.text}</td>
                                                )
                                            }
                                        })}
                                    </tr>
                                )
                            } )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}
