/**
 * @description 数据表详情页面
 * @author ypeng
 * date: 2018-06-07
 */

import React, { Component } from 'react';
import { Helmet } from 'react-helmet';
import XLSX from 'xlsx';
// import { translate } from 'react-i18next';
import moment from 'moment';
import { withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react'
import axios from 'axios';
import ejson from 'mongodb-extjson';

import ClipboardJS from 'clipboard'; 
import {message, Spin } from 'antd';
import ask from '../../../../lib/ask'
import DataDetailWrap from '../../../../components/DataDetailWrap';
import TableDetailData from '../../../../components/TableDetailData';
import {localClass} from './index.scss'
// import {Share, origin_report, origin_notice} from '../../../../../constants.js'
import DefaultHeader from '../../../EntitySearch/components/DefaultHeader';
import {GetUrlParam} from '../../../../lib/utils';

import mockData from './mockData.json';

const origin = 'https://www.analyst.ai';
const origin_v1 = 'https://v1.analyst.ai';

@withRouter
// @translate()
@inject((store) => ({
  favoriteStore: store.favoriteStore,
}))
@observer
export default class TableDetail extends Component {
  constructor(props){
    super();
    const url = props.location.pathname + props.location.search;
    const str = url.replace(/(\/table\/)(.*)$/,($0,$1,$2)=>{return $2});
    const index = str.lastIndexOf('?') > -1? str.lastIndexOf('?') : undefined;
    const id = str.substring(0,index);
    this.state = {
      id : id,
      oData : props.oData,
      nData : null,
      clipboarTableData : null,
      loaded : false,
      topBarHideItems : ['nav','languageToggle', 'smallSearch', 'productButton', 'userInfo']
    };
  }
  componentDidMount = () => {
    // this.loginState();

    ask('getTableDataDetail',{
        params : {
            id : decodeURIComponent(GetUrlParam('id')||0),
            page: 'table_search_detail'
        }
    }).then( res => {
        // this.setState({loaded:true});
        if(res.data) return this.getTableData(res.data);
        // this.getTableData(mockData); //模拟数据
    });
    //表格复制按钮初始化
    // if(!Share.isSharePage) this.initClipbord();

    // this.props.favoriteStore.fetchFavoriteBySourceId(this.state.id, 'table');
  }
  componentWillUnMount(){
    //移除表格复制事件
    this.clipboard.destory();
  }
  
  // 判断表格数据是否为一个URL地址
  isTableDataUrl = (data) => {
    return /^https?:\/\//.test(data);
  }

  // 获取表格数据
  fetchTableData = async (url) => {
    url = url.replace(/^http:/, 'https:');
    const res = await axios.get(url);
    const tableData = JSON.parse(ejson.stringify(
      ejson.parse(res.request.responseText).data,
      {relaxed: true}
    ));
    
    return tableData;
  }

  getTableData = async (resData) => {
    const oData = JSON.parse(JSON.stringify(resData));
    this.setState({
        oData
    });

    const tableData = [];
    let table_data = this.isTableDataUrl(oData.table_data) ? null : JSON.parse(oData.table_data);
    if (this.isTableDataUrl(oData.table_data)) {
      table_data = await this.fetchTableData(oData.table_data);
    }
    
    this.setState({loaded:true});

    table_data.forEach( item => {
        if( !Array.isArray(tableData[item.row]) )  tableData[item.row] = [];
        tableData[item.row][item.column] = item;
    } );

    //对多单元格的条纹颜色处理
    let flag = 'even', tr_count = 0;
    const tableDataNew = []; 
    tableData.forEach( (item,index) => {
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

    const {table_title, title, time, type, table_ori, src_id, table_source='', card_title, colSpan} = oData;
    const date = moment(time*1000).format('YYYY/MM/DD');
    let mainTitLink;
    // const mainTitLink = parseFloat(table_ori) === 2? table_source : table_source? origin_v1+`/chart/details?id=${table_id}&type=table&tab=fileviewer&typesource=${table_source}` : undefined;
    if(parseFloat(table_ori) === 2){
      mainTitLink = table_source;
    }else if(table_source){
    //   if(table_source.indexOf('juchao_tables') > -1){//跳到公告
    //     mainTitLink = `${origin_notice}/detail/text?srcId=${src_id}`;
    //   }else if(table_source.indexOf('hb_tables') > -1){//跳到研报
    //     // mainTitLink = `${origin_report}/report/article/${src_id}`;
    //   };
    };
    this.setState({
        nData:{
            webTit : table_title,
            mainTit : table_title,
            mainTitLink,
            date,
            type,
            subTit : title,
            subTitLink : mainTitLink,
            data : tableDataNew,
            card_title,
            colSpan
        }
    });
    // if(tableData.length>0) this.copyTableData(tableData);
  }
  downloadTableData = () => {
    const {t} = this.props;
    const {mainTit, data} = this.state.nData;
    const tit = !mainTit?  t("DataDetailWrap.ExcelDefaultTitle",{defaultValue:"导出表格数据"}) : mainTit.length > 32? mainTit.substring(0,32) + '...' : mainTit;
    const arr_2D = data.map(item=>item.arr_trs.map(it=>it.text));
    const worksheet = XLSX.utils.aoa_to_sheet(arr_2D);
    const new_workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(new_workbook, worksheet, "SheetJS");
    XLSX.writeFile(new_workbook, tit+'.xlsx');

  }
  initClipbord = ()=>{
    const {t} = this.props;
    var _this = this;
    this.clipboard = new ClipboardJS('.copyBtn',{text : function(){
      return _this.state.clipboarTableData;
    }});
    this.clipboard.on('success', function(e) {
      message.success( t("DataDetailWrap.copyTip_success",{defaultValue:"复制成功！"}),3);
      e.clearSelection();
    });
    
    this.clipboard.on('error', function(e) {
      message.success( t("DataDetailWrap.copyTip_error",{defaultValue:"复制失败！"}),3);
    });
  }
  copyTableData = (tableData)=>{
    const arr_2D = tableData.map(item=>item.map(it=>it.text));
    const str = arr_2D.map(item=>{
      return item.join('\t');
    }).join('\n');
    this.setState({
      clipboarTableData : str
    });
  }
  loginState = ()=>{
    // if(!Share.isSharePage){
      const topBarHideItems = ['nav','languageToggle'];
      this.setState({
        topBarHideItems
      });
    // };
  }

  render () {
    const {nData, topBarHideItems} = this.state;
    // let { saveFavorite, favorite, removeFavorite } = this.props.favoriteStore;
    // favorite.type = 'table';

    // const link = ultis.getNoUserInfoLocationHref();

    // // 微信分享配置参数
    // const wechatConfig = {
    //   title: nData && nData.webTit ? nData.webTit : document.title,
    //   desc: nData && nData.subTit ? `类别：${nData.type || '--'}\n来源：${nData.subTit || '--'}` : '',
    //   imgUrl: defaultShareIcon,
    //   link,
    // };
    
    return (
      <div className={localClass}>
        <Helmet><title>{nData&&nData.webTit? nData.webTit : ''}</title></Helmet>
        <div className="middleConWrap">
          { !this.state.loaded? <Spin/> : 
            !nData? '' :
            <DataDetailWrap 
            //   saveFavorite={saveFavorite}
            //   favorite={favorite}
            //   cancelFavorite={removeFavorite}
              wrapStyle="type-table" 
              oData={nData} 
              downloadTableData={()=>{this.downloadTableData();}}>
              { nData.data && <TableDetailData data={nData.data} /> }
            </DataDetailWrap>
          }
        </div>
      </div>
    );
  }
}
