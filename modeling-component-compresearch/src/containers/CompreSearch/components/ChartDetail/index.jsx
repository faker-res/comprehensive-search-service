/**
 * @description 数据图详情页面
 * @author ypeng
 * date: 2018-06-08
 */

import React, { Component } from 'react';
import { Helmet } from 'react-helmet';
import moment from 'moment';
import { withRouter } from 'react-router-dom';
import ejson from 'mongodb-extjson';
import axios from 'axios';
// import { inject, observer } from 'mobx-react'
import ask from '../../../../lib/ask'
import DataDetailWrap from '../../../../components/DataDetailWrap';
import ChartDetailData from '../../../../components/ChartDetailData';
import {Share, origin_report} from '../../../../lib/constants'
import {localClass} from './index.scss'
// import defaultShareIcon from '../../theme/default/images/default-share-icon.png';
import qs from 'query-string'
import {GetUrlParam} from '../../../../lib/utils'
import { Spin } from 'antd';


@withRouter
// @inject((store) => ({
//   favoriteStore: store.favoriteStore,
// }))
// @observer
export default class ChartDetail extends Component {
  constructor(props){
    super();
    const url = props.location.pathname + props.location.search;
    const str = url.replace(/(\/chart\/)(.*)$/,($0,$1,$2)=>{return $2});
    const index = str.lastIndexOf('?') > -1? str.lastIndexOf('?') : undefined;
    const id = str.substring(0,index);
    this.state = {
      id,
      oData : props.oData,
      nData : null,
      loaded : false,
      chartDataLoaded : false,
      topBarHideItems : ['nav','languageToggle', 'smallSearch', 'productButton', 'userInfo'],
    };
  }
  componentDidMount() {
    // const fileviewer_url = `${origin_report}/report/article/${this.state.id}`;
    // this.setState({fileviewer_url});

    // this.loginState();

    const api = Share.isSharePage? 'getChartDataDetail_share':'getChartDataDetail';
    const {type} = qs.parse(this.props.location.search)
    ask('chartDetailLoad',{
        params : {
            id : decodeURIComponent(GetUrlParam('id')||0),
            type: type,
            page: 'image_search_detail'
        }
    }).then( res => {
        this.setState({loaded:true});
        if(res.data) return this.getChartData(res.data);
        // this.getChartData(demoData)
        // this.getChartData(mockDataChart.chartInfo);
    });

    // this.props.favoriteStore.fetchFavoriteBySourceId(this.state.id, 'chart');
  }
  getChartData(resData){
    const oData = JSON.parse(JSON.stringify(resData));
    this.setState({
        oData
    });
    const fileviewer_url = `${origin_report}/report/article/${oData.file_id}`;
      if(oData.hasOwnProperty('img_Category')){// 精选图
      const {img_Title, img_Source, update_Time, img_Url,source_url,pageIndex} = oData;
      const date = moment(update_Time).format('YYYY/MM/DD');
      const mainTitLink = fileviewer_url;
      this.setState({
          chartDataLoaded : true,
          wrapStyle : 'type-bigPic',
          nData : {
              webTit : img_Title,
              mainTit : img_Title,
              mainTitLink,
              date,
              subTit : img_Source,
              subTitLink : mainTitLink,
              image_url : img_Url,
              data : null,
              source_url:source_url,
              pageIndex:pageIndex,
          }
      });
    }else{// highchart 或 预览图
      const {author, time, image_title, type, title, image_url, chart_data, data_file, source_url, pageIndex, confidence, file_id, id} = oData;
      const date = moment(time*1000).format('YYYY/MM/DD');
      const mainTitLink = fileviewer_url;
      
      let data;

      const source_url_split = source_url? source_url.split('.') : [];
      // const parsingUrl = source_url_split[source_url_split.length-1]==='pdf'? `http://p.analyst.ai/console/api/material/parse/chartByUrlAndPageIndex?fileName=${image_title}&pageIndex=${pageIndex||0}&url=${encodeURIComponent(source_url)}`:undefined;
      const parsingUrl = source_url_split[source_url_split.length-1]==='pdf'? `http://p.analyst.ai/console/api/parse/v1/papaparsing?fileId=${file_id}&source=Analyst&type=2&code=hb&itemId=${id}&pageIndex=${pageIndex||0}`:undefined;

      this.setState({
        nData : {
            author,
            webTit : image_title,
            mainTit : image_title,
            mainTitLink,
            date,
            type,
            subTit : title,
            subTitLink : mainTitLink,
            image_url,
            parsingUrl,
            confidence
        }
    });

      axios.get(data_file.replace("http:","https:").replace('-internal.', '.')).then((response)=>{
        const dataJson = response.request? JSON.parse(ejson.stringify(ejson.parse(response.request.responseText), {relaxed: true})).data : undefined;
        data = chart_data? JSON.parse(chart_data) : dataJson;

        this.setState((preveState)=>{
          const nData = preveState.nData;
          const wrapStyle = nData.confidence <= 0.5? 'type-smallPic' : data? "type-chart" : "type-smallPic";
          nData.data =  nData.confidence <= 0.5? '' : data;
          return {
            chartDataLoaded : true,
            wrapStyle,
            nData
          }
        });
      }).catch((msg)=>{
        this.setState((preveState)=>{
          const nData = preveState.nData;
          nData.data =  '';
          return {
            chartDataLoaded : true,
            wrapStyle : 'type-smallPic',
            nData
          }
        });
      });
    };
  }
  downloadChartData = ()=>{
    this.chart.exportData();
  }
  exportPic = () => {
    this.chart.exportPic(this.state.nData.mainTit.substring(0,32));
  }
  loginState = ()=>{
    if(!Share.isSharePage){
      const topBarHideItems = ['nav','languageToggle'];
      this.setState({
        topBarHideItems
      });
    };
  }

  render () {
    const {nData, topBarHideItems} = this.state;
    // let {  removeFavorite } = this.props.favoriteStore;
    // favorite.type = 'chart';

    // const link = ultis.getNoUserInfoLocationHref();

    // 微信分享配置参数
    const wechatConfig = {
      title: nData && nData.webTit ? nData.webTit : document.title,
      desc: nData && nData.subTit ? `类别：${nData.type || '--'}\n作者：${nData.author || '--'}\n来源：${nData.subTit || '--'}` : '',
      imgUrl: nData && nData.image_url ? nData.image_url + '?x-oss-process=image/resize,m_fill,h_300,w_300' : '',
    };

    return (
        <div className={localClass}>
        <Helmet><title>{nData&&nData.webTit? nData.webTit : ''}</title></Helmet>
        <div className="middleConWrap">
          { !this.state.loaded || !nData? <Spin/> : 
            <DataDetailWrap 
            //   saveFavorite={saveFavorite}
            //   favorite={favorite}
            //   cancelFavorite={removeFavorite}
              oData={nData} 
              wrapStyle={this.state.wrapStyle} 
              downloadChartData={()=>{this.downloadChartData();}} 
              exportPic={()=>{this.exportPic();}}>
              {/* { !this.state.chartDataLoaded? <Spin/> : nData.data.length>0? <ChartDetailData chartTitle={nData.webTit} config={nData.data} ref={chart=>{this.chart = chart}}/> : 
                  'noData'
              } */}
            </DataDetailWrap>
          }
          { nData && nData.data &&
            <ChartDetailData chartTitle={nData.webTit} config={nData.data} ref={chart=>{this.chart = chart}}/> 
          }  
        </div>
      </div>
    );
  }
}

