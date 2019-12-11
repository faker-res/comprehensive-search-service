import React, { Component } from 'react';
import { Divider, Tooltip, Collapse, Icon } from 'antd';
import { BrowserRouter, Route, Switch, NavLink, withRouter } from 'react-router-dom';
import _ from 'lodash';
import moment from 'moment';
import Highlighter from "react-highlight-words";
import StockCard from './StockCard';
import Honor from './Honor';
import './ReportItem.less';
import '../../common/common.less';
import BatchExportButton from '../BatchExportButton';

// 域名链接到主站线上正式(重构临时方案)
const origin = 'https://www.analyst.ai';
const origin_v1 = 'https://v1.analyst.ai';
const origin_report = 'https://report.analyst.ai';

const Panel = Collapse.Panel;
export default class ReportItem extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  gethonorListByName = (list, name) => {
    return list.filter((item) => {
      return item.name === this.delHtmlTag(name);
    });
  }
  delHtmlTag = (str)=>{
    return str.replace(/<.*?>(.*?)<\/.*?>/gi,function($1,$2){return $2});
  }
  getActionList = (actionObj) => {
    const { item, searchData, onActionTagClick } = this.props;
    let url = item.url;
    let url_split = url.split(".");
    let download_url =
      "/api/usercenter/file/pdf?name=" +
      encodeURIComponent(this.delHtmlTag(item.title)) +
      "." +
      url_split[url_split.length - 1] +
      "&url=" +
      window.btoa(item.url);
    const actionList = [];
    const clickableTagTypeMap = {
      'source': 'source',
      'industry': 'industry_txt',
      'category': 'category_name',
      'author': 'author',
    };
    for (const key in actionObj) {
      if (Object.prototype.hasOwnProperty.call(actionObj, key)) {
        const action = actionObj[key];
        let actionDom = null;
        if (key === 'author') {
          actionDom = (
            <div key={key} className={`${action.length > 0 ? '' : 'display_none'} middle`}>
              {
                action.map((ele, i) => {
                  const honorListByName = item.analyst_honor ? this.gethonorListByName(item.analyst_honor, ele) : [];
                  return (
                    <span key={i} className="middle">
                      <span className={i === 0 ? 'display_none' : ''}>，</span>
                      {honorListByName.length > 0 && (
                        <Honor
                          id={honorListByName[0].id}
                        />
                      )}
                      <span
                        data-type="author"
                        data-value={ele}
                        className={`report_filter cursor_pointer ${searchData.author === ele ? 'selected_author' : ''}`}
                        dangerouslySetInnerHTML={{ __html: ele }}
                        onClick={clickableTagTypeMap[key]
                          ? () => onActionTagClick({type: clickableTagTypeMap[key], name: this.delHtmlTag(ele)})
                          : null
                        }
                      />
                    </span>
                  );
                })
              }
              {/* <Divider type="vertical" style={{ backgroundColor: '#828A92', margin: '5px 5px 0' }} /> */}
            </div>
          );
        } else if (key === 'file_type') {
          if (action != '网页') {
            actionDom = (
              <div key={key} className={'middle docType'}>
                <Tooltip placement="top" title="下载">
                  <a href={download_url} >
                    <span className={`report_filter_read cursor_pointer`}>{action}</span>
                  </a>
                </Tooltip>
                {/* <Divider type="vertical" style={{ backgroundColor: '#828A92', margin: '5px 5px 0' }} /> */}
              </div>
            );
          } else {
            actionDom = (
              <div key={key} className={'middle docType'}>
                <span className={`report_filter_read`}>{action}</span>
                {/* <Divider type="vertical" style={{ backgroundColor: '#828A92', margin: '5px 5px 0' }} /> */}
              </div>
            );
          }
        } else if (key === 'tag') {
          actionDom = (
            <div key={key} className={`${action && action.length > 0 ? '' : 'display_none'} middle tagWrap`} >
              {
                action.map(item=><span key={item} className="tag">{item}</span>)
              }
              {/* <Divider type="vertical" style={{ backgroundColor: '#828A92', margin: '5px 5px 0' }} /> */}
            </div>
          );
        } else if (key === 'file_pages' || key === 'share_author') {
          actionDom = (
            <div key={key} className={'middle'}>
              <span className={`report_filter_read ${key === 'file_pages' ? '' : 'display_none'}`}>{`${action}页`}</span>
              <span className={`report_filter_read ${key === 'share_author' ? '' : 'display_none'}`}>{`分享者：${action}`}</span>
              {/* <Divider type="vertical" style={{ backgroundColor: '#828A92', margin: '5px 5px 0' }} /> */}
            </div>
          );
        } else {
          actionDom = (
            <div key={key} className={`middle ${(action === '' || action == undefined) ? 'display_none' : ''}`}>
              <span
                data-type={key}
                data-value={action}
                className={`report_filter cursor_pointer ${key === 'source' && searchData[key] === action ? 'selected_author' : ''}`}
                dangerouslySetInnerHTML={{ __html: action }}
                onClick={clickableTagTypeMap[key]
                  ? () => onActionTagClick({type: clickableTagTypeMap[key], name: this.delHtmlTag(action)})
                  : null
                }
              />
              {/* <Divider type="vertical" style={{ backgroundColor: '#828A92', margin: '5px 5px 0' }} /> */}
            </div>
          );
        }
        actionList.push(actionDom);
      }
    }
    return actionList;
  }
  clickDownload = (report) => {

  }
  getRatingColor = (rating) => {
    let color = '#ff0000';
    if (rating.indexOf('增持') > -1) color = '#de9700';
    if (rating.indexOf('中性') > -1) color = '#abe201';
    if (rating.indexOf('减持') > -1) color = '#13b5b1';
    if (rating.indexOf('卖出') > -1) color = '#328d00';
    return color;
  }
  handleVisible = (visible) => {
    const { stockCode } = this.props;
    if (visible) {
      // this.props.dispatch({
      //   type: 'stock/fetchStockDetail',
      //   payload: { stockCode },
      // });
    }
  }
  callBackHandle = (e) => {
    const { type, value } = e.target.dataset;
    const { clickCallBack, searchData, item } = this.props;
    if (clickCallBack) {
      if (type === 'industry') {
        searchData.industry_txt = item.industry1;
        searchData.industry_name = item.industry_id;
      } else if (type === 'category') {
        searchData.category = item.category;
        searchData.report_type = item.category_id;
      } else {
        searchData[type] = value;
      }
      searchData.page = 1;
      searchData.offset = 0;
      window.scrollTo(0, 0)
      // 需要把过滤类型返回到List，需要修改过滤Map
      clickCallBack(searchData, type);

    }
  }
  clickStockName = (stockName, stockCode) => {
    const { clickStockNameCallBack } = this.props;
    clickStockNameCallBack(stockName, stockCode);
  }
  clickRating = (rating) => {
    const { handleSelectRating } = this.props;
    if (handleSelectRating) handleSelectRating(rating);
  }
  getShowParagraph = (item, reportKeywords) => {
    // const { item, reportKeywords } = this.props;
    let match_keywords = reportKeywords;
    if (match_keywords && item["paragraph"] && item["paragraph"].length) {
      match_keywords = match_keywords.split(",");
      // 去除空串
      let match_set = new Set(match_keywords);
      match_set.delete('');
      // 去除‘|’
      match_set.delete('|');
      // 关键字去重
      match_keywords = [...new Set(match_set)];
      //匹配段落只高亮关键字，不包含匹配出的属性
      let highlight_all = true;
      if (item["summarynum"] >= 1000) {
        highlight_all = false;
      }
      const marked_paragraphArr = [];
      for (let j = 0; j < item["paragraph"].length; j++) {
        let paragraph = item["paragraph"][j];
        if (this.delSpace(paragraph).length < 16) continue;
        let marked_paragraph = '';
        if (highlight_all) {
          marked_paragraph =  <Highlighter
              highlightClassName="highlight"
              searchWords={match_keywords}
              autoEscape={true}
              textToHighlight={paragraph}
            />
        } else {
          marked_paragraph =  <Highlighter
              highlightClassName="highlight"
              searchWords={[match_keywords[0]]}
              autoEscape={true}
              textToHighlight={paragraph}
            />
        }
        marked_paragraphArr.push(marked_paragraph);
      }
      return marked_paragraphArr.map((paragraph,index) => {
        return (
          <div key={index}>
            <p className='report_item_paragraph'>{paragraph}</p>
            <Divider dashed style={{ margin: '0' }} />
          </div>
        )
      })
    } else if (item["summary"] && item["summary"].length && item["summary"].length > 15) {
      let desc = item["summary"].replace(/<[^>]*>/g, "");
      desc = desc.length > 220 ? (desc.slice(0, 220) + '……') : desc;
      return <p style={{ padding: '5px 0' }} dangerouslySetInnerHTML={{ __html: desc }} />
    }
  }
  delSpace = (str)=>{
    return str.replace(/\s+/g,'');
  }
  getShowTitle = (title) => {
    const reg1 = new RegExp("<font color='red'>", "gi");
    const reg2 = new RegExp("</font>", "gi");
    let newTitle = title.replace(reg1, '');
    newTitle = newTitle.replace(reg2, '');
    return newTitle;
  }
  getReportHeader = () => {
    const { item, clickStockNameCallBack, hideSummary, titleBold, reportKeywords, openReportIdList } = this.props;
    return (
      <div className={`title_container middle`}>
        {/* 批量导出项复选框 */}
        <BatchExportButton.ExportItemCheckbox
            className="report-item-export-checkbox"
            id={item.id + ''}
        />
        <StockCard
          stockCode={item.stockcode || ''}
          stockName={item.stockname || ''}
          clickStockCallback={clickStockNameCallBack ? this.clickStockName : null}
          titleBold={titleBold}
        />
        <a
          className={'title_name'}
          dangerouslySetInnerHTML={{ __html: item.title }}
          target="_blank"
          title={this.getShowTitle(item.title)}
          href={`${origin_report}/report/article/${item.id}`}
          style={{ fontSize: !hideSummary ? '18px' : '14px' }}
          onClick={e=>{e.stopPropagation();}}
        />
        <div className={`rating cursor_pointer ${item.rating.length > 0 ? '' : 'display_none'}`} style={{ marginLeft: '5px', backgroundColor: this.getRatingColor(item.rating) }}>{item.rating}</div>
        {
          (item.paragraph.length > 2 && !hideSummary) ? (
            <span style={{ color: '#4491f6', fontSize: '12px', position: 'absolute', right: '0' }}>
              <Icon type={`${openReportIdList.indexOf(item.id) > -1 ? 'up' : 'down'}`} style={{ marginRight: '3px' }} />
              {openReportIdList.indexOf(item.id) > -1 ? '收起' : '展开'}
            </span>
          ) : ''
        }
        {
          hideSummary ? (
            <span style={{ position: 'absolute', right: '0' }}>
              <span className='report_item_title_time' style={{ display: `${openReportIdList.indexOf(item.id) > -1 ? 'none' : 'block'}` }}>
                <div className={'report_filter_read'}>{moment.unix(item.publish_at || new Date()).format('YYYY/MM/DD')}</div>
              </span>
              <span className='report_item_title_open' style={{ color: '#4491f6', fontSize: '12px', display: `${openReportIdList.indexOf(item.id) > -1 ? 'block' : 'none'}` }}>
                <Icon type={`${openReportIdList.indexOf(item.id) > -1 ? 'up' : 'down'}`} style={{ marginRight: '3px' }} />
                {openReportIdList.indexOf(item.id) > -1 ? '收起' : '展开'}
              </span>
            </span>
          ) : ''
        }

      </div>
    )
  }
  changeOpen = (key, item) => {
    const { openReportIdList, changeOpenReportIdList } = this.props;
    const set = new Set(openReportIdList);
    if (key.length == 0) {
      set.delete(item.id);
    } else {
      set.add(item.id);
    }
    if (changeOpenReportIdList) changeOpenReportIdList([...set]);
  }
  render() {
    const { item, isShowProperty, clickStockNameCallBack, hideSummary, titleBold, reportKeywords, openReportIdList } = this.props;

    let actionObj = {
      source: item.source,
      industry: item.industry1,
      category: item.category,
      author: item.author,
      file_pages: item.file_pages,
      file_type: item.file_type,
      tag: item.tag,
      share_author: item.share_author,
    };

    if ( actionObj.industry && item.category && actionObj.industry.trim() === item.category.trim()) delete actionObj.category;

    const openSet = new Set(openReportIdList);
    const newItem = JSON.parse(JSON.stringify(item));
    newItem.paragraph = newItem.paragraph.slice(0, 2);
    return (
      <div className='report_item_container' style={{ width: '100%' }}>
        {/* 首页样式 */}
        {hideSummary == undefined && (
          <div key={item.id} className={'item_container'}>
            <div className={`title_container middle`}>
              <StockCard
                stockCode={item.stockcode || ''}
                stockName={item.stockname || ''}
                clickStockCallback={clickStockNameCallBack ? this.clickStockName : null}
                titleBold={titleBold}
              />
              <a
                className={'title_name'}
                dangerouslySetInnerHTML={{ __html: item.title }}
                title={item.title}
                target="_blank"
                href={`${origin_report}/report/article/${item.id}`}
                style={{ fontSize: isShowProperty ? '16px' : '14px' }}
              />
              <div className={`rating cursor_pointer ${item.rating.length > 0 ? '' : 'display_none'}`} style={{ marginLeft: '5px', backgroundColor: this.getRatingColor(item.rating) }}>{item.rating}</div>
              <span className={isShowProperty ? 'display_none' : ''} style={{ position: 'absolute', right: '0' }}>
                <div className={'report_filter_read'}>{moment.unix(item.publish_at || new Date()).format('YYYY/MM/DD')}</div>
              </span>
            </div>
            <p className={'display_none'} style={{ padding: '5px 1px' }} dangerouslySetInnerHTML={{ __html: item.paragraph.length > 0 ? item.paragraph[0] : item.summary }} />
            <div className={`item_footer space_between ${isShowProperty ? '' : 'display_none'}`}>
              <div className={`item_action middle`}>
                {this.getActionList(actionObj)}
              </div>
              <div className={'report_filter_read'}>{moment.unix(item.publish_at || new Date()).format('YYYY/MM/DD')}</div>
            </div>
          </div>
        )}
        {/* 列表页样式 --- 显示摘要 */}
        {hideSummary == false && (
          <div className={'item_container'}>
            {
              item.paragraph.length > 2 && (
                <Collapse onChange={(key) => { this.changeOpen(key, item) }}>
                  <Panel header={this.getReportHeader()} key={item.id}>
                    {this.getShowParagraph(item, reportKeywords)}
                  </Panel>
                </Collapse>
              )
            }
            {item.paragraph.length <= 2 && this.getReportHeader()}
            {
              item.paragraph.length > 2 && !openSet.has(item.id) && (
                <div style={{ padding: '5px 0' }}>
                  {
                    this.getShowParagraph(newItem, reportKeywords)
                  }
                </div>
              )
            }
            {
              item.paragraph.length <= 2 && (
                <div style={{ padding: '5px 0' }}>
                  {
                    this.getShowParagraph(newItem, reportKeywords)
                  }
                </div>
              )
            }
            <div className={`item_footer space_between`}>
              <div className={`item_action middle`}>
                {this.getActionList(actionObj)}
              </div>
              <div className={'report_filter_read'}>{moment.unix(item.publish_at || new Date()).format('YYYY/MM/DD')}</div>
            </div>
          </div>
        )}
        {/* 列表页样式 --- 隐藏摘要 */}
        {hideSummary == true && (
          <div className={'item_container'}>
            {
              <Collapse onChange={(key) => { this.changeOpen(key, item) }}>
                <Panel header={this.getReportHeader()} key={item.id}>
                  {this.getShowParagraph(item, reportKeywords)}
                  {
                    <div className={`item_footer space_between`}>
                      <div className={`item_action middle`}>
                        {this.getActionList(actionObj)}
                      </div>
                      <div className={'report_filter_read'}>{moment.unix(item.publish_at || new Date()).format('YYYY/MM/DD')}</div>
                    </div>
                  }
                </Panel>
              </Collapse>
            }
          </div>
        )}
      </div>
    );
  }
}
