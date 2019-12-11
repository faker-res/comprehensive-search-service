/**
 * @description 数据图表详情页
    @author ypeng
**/

import React, { Component } from 'react'
// import { observer, inject } from 'mobx-react';
import { translate } from 'react-i18next';
import { Menu, Dropdown, Icon } from 'antd';
import _ from 'lodash';
import {Share, isMobile} from '../../lib/constants'
import {localClass} from './style.scss'
import BackToTop from '../../components/BackToTop';
import ultis from '../../lib/utils';

// @inject('userStore')
// @translate()
export default class DataDetailWrap extends Component {
    constructor(props){
        super();
        const isSharePage = Share.isSharePage;
        // console.log('分享页:',isSharePage);
        this.state = {
            isSharePage,
            isMobile : isMobile()
        };
        // if(typeof props.loginState === 'function') props.loginState(isSharePage);
    }
    jumpToLogin = (e,url)=>{
        e.preventDefault();
        window.location.href = url;
    }
    render(){
        if(!this.props.oData) return null;
        const {isSharePage, isMobile} = this.state;
        let {t, oData, className, wrapStyle, downloadChartData, downloadTableData, exportPic, children, saveFavorite, favorite, cancelFavorite} = this.props;
        let {mainTit, mainTitLink, date, type, subTit, subTitLink, author, image_url, parsingUrl} = oData;
        let copyUrl;//定义复制按钮初始状态
        if(isSharePage){//若为分享页，则替换链接到注册登录页
            mainTitLink = subTitLink = undefined;
            image_url = copyUrl = Share.loginUrl;
            downloadChartData = downloadTableData = exportPic = (e)=>{ this.jumpToLogin(e,Share.loginUrl); };
        };

        // const shareLink = ultis.getNoUserInfoLocationHref();
        
        return (
            <div className={localClass+ (className? ' ' +className : '')}>
                <div className={`dataDetailWrap ${wrapStyle?wrapStyle:''} ${isSharePage?'isSharePage':''} ${isMobile?'isMobile':''}`}>
                    {!isSharePage && 
                        <div className="dataDetailSide">
                            <h1 className="mainTit">{t("DataDetailWrap.mainTit",{defaultValue:"原始图表"})}</h1>
                            <div className="sidePicShowArea">
                                <a href={image_url} target="_blank"><img src={image_url} alt=""/></a>
                                <p title={subTit?subTit:''}> <span>{t("DataDetailWrap.from",{defaultValue:"来源"})}：</span>{subTit? <a target="_blank" href={subTitLink}>{subTit}<Icon type="link" style={{color:'#1890ff',marginLeft:5}} /></a> : '--'}</p>
                                <p title={type?type:''}> <span>{t("DataDetailWrap.subTit_type",{defaultValue:"类别"})}：</span>{type?type:'--'}</p>
                                { author && <p title={author}> <span>{t("DataDetailWrap.author",{defaultValue:"作者"})}：</span>{author}</p> }
                            </div>
                        </div>
                    }
                    <main className="dataDetailCon" id='big-pic-container'>
                        <div className="funcBox">
                            {/* {   wrapStyle === 'type-smallPic' && 
                                <span>
                                    { parsingUrl && <a target={isSharePage?'_self':'_blank'} href={isSharePage? Share.loginUrl:parsingUrl}><i className="abc_iconfont ypeng-antd-icon">&#xe62d;</i> <i>{t("DataDetailWrap.btn_parsing",{defaultValue:"Parsing一下"})}</i></a>}
                                    <Dropdown overlay={
                                        <Menu className="ypeng-antd-dropMenu">
                                            <Menu.Item>
                                                <a target={isSharePage?'_self':'_blank'} href={image_url}>{t("DataDetailWrap.viewOriginPic",{defaultValue:"查看原图"})}</a>
                                            </Menu.Item>
                                        </Menu>
                                    }>
                                        <a><i className="abc_iconfont ypeng-antd-icon">&#xe627;</i> <i>{t("DataDetailWrap.btn_export",{defaultValue:"导出"})}</i> <Icon type="down" /></a>
                                    </Dropdown>
                                </span>
                            } */}
                            {/* {   wrapStyle === 'type-chart' && 
                                <span>
                                    { parsingUrl && <a target={isSharePage?'_self':'_blank'} href={isSharePage? Share.loginUrl:parsingUrl}><i className="abc_iconfont ypeng-antd-icon">&#xe62d;</i> <i>{t("DataDetailWrap.btn_parsing",{defaultValue:"Parsing一下"})}</i></a>}
                                    <Dropdown overlay={
                                        <Menu className="ypeng-antd-dropMenu">
                                        {
                                            !isMobile &&
                                            <Menu.Item>
                                                <a className='chart-export-data' onClick={(e)=>{downloadChartData(e);}}>{t("DataDetailWrap.btn_exportData",{defaultValue:"导出数据"})}</a>
                                            </Menu.Item>
                                        }
                                        
                                        <Menu.Item>
                                            <a target={isSharePage?'_self':'_blank'} href={image_url}>{t("DataDetailWrap.viewOriginPic",{defaultValue:"查看原图"})}</a>
                                        </Menu.Item>
                                        <Menu.Item>
                                            <a onClick={(e)=>{exportPic(e);}} >{t("DataDetailWrap.exportPic",{defaultValue:"导出图片"})}</a>
                                        </Menu.Item>
                                        </Menu>
                                    }>
                                        <a><i className="abc_iconfont ypeng-antd-icon">&#xe627;</i> <i>{t("DataDetailWrap.btn_export",{defaultValue:"导出"})}</i> <Icon type="down" /></a>
                                    </Dropdown>
                                </span>
                            } */}
                            {   wrapStyle === 'type-table' && 
                                <span>
                               
                                </span>
                            }
                            {/* { !_.isEmpty(favorite) &&
                                <Favorite
                                    onSave={saveFavorite}
                                    dataSource={favorite || {}}
                                    cancelFavorite={cancelFavorite}
                                    placement="bottomRight"
                                />
                            } */}
                            {/* { !isSharePage && <ShareButton shareLink={shareLink} />} */}
                        </div>
                        <h1 className="mainTit"><a target="_blank" href={mainTitLink} title={mainTit?mainTit:''}>{mainTit?mainTit:'--'}</a></h1>
                        <p className="titDate">{date?date:''}</p>
                        <div className="mainConWrap">
                            {/* {
                                (wrapStyle === 'type-table' || wrapStyle === 'type-bigPic') &&
                                <p className="subTit">
                                    <span title={type?type:''}> {t("DataDetailWrap.subTit_type",{defaultValue:"类别"})}：{type?type:'--'}</span>
                                    { author && <span title={author}> {t("DataDetailWrap.author",{defaultValue:"作者"})}：{author}</span> }
                                    <span title={subTit?subTit:''}> {t("DataDetailWrap.from",{defaultValue:"来源"})}：
                                        {
                                            subTitLink
                                            ? <a target="_blank" href={subTitLink}>{subTit? <i>{subTit}<Icon type="link" style={{color:'#1890ff',marginLeft:5}} /></i> :'--'}</a>
                                            : <span>{subTit? <i>{subTit}</i> :'--'}</span>
                                        }
                                    </span>
                                </p>
                            } */}
                            <div className="detailDataShowArea">
                                {children}
                            </div>
                        </div>
                    </main>
                </div>
                {/* {
                    isSharePage && isMobile &&
                    <div className="Guide-installApp"><a href={Share.AppDownloadUrl}>一键安装App,看更多数据图表</a></div>
                } */}
                {/* {
                    isSharePage &&
                    <div className="Guide-signUp">
                        <a className="Guide-signUp_btn" href={Share.loginUrl}>立即注册</a>
                        <div className="Guide-info">
                            <div className="Guide-signUp_tit">AI金融信息搜索引擎 <span className="Guide-signUp_subTit">找冷门数据、找分析观点、找公告研报</span></div>
                        </div>
                    </div>
                } */}


            </div>   
        )
    }
}
