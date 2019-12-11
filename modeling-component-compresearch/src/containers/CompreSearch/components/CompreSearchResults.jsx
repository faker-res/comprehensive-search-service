/**
 * @description 综合搜索结果页
 * @author kygeng
 * date: 2018-08-20
 */
import React, { Component } from "react";
import styled from "styled-components";
import { withRouter } from "react-router-dom";
import ask from "../../../lib/ask";
import queryString from "query-string";
import isEmpty from "lodash/isEmpty";
import { Spin } from "antd";
import _ from 'lodash'
import StockCardWrap from "../../../components/ABCComponents/CompreSearch/StockCardWrap";
// import SideStockListWrap from '../../../components/ABCComponents/CompreSearch/SideStockListWrap';
import SearchResults from "../../../components/ABCComponents/CompreSearch/General";
// import StockAnalyst from '../../../components/ABCComponents/CompreSearch/StockAnalyst';
// import NoDataTip from '../../../components/ABCComponents/CompreSearch/NoDataTip';
import CardAnalystWrap from "../../../components/ABCComponents/CompreSearch/CardAnalystWrap";
import StockSiderWrap from "../../../components/ABCComponents/CompreSearch/StockSiderWrap";
import AnalystSiderWrap from "../../../components/ABCComponents/CompreSearch/AnalystSiderWrap";
import AnalystStockWrap from "../../../components/ABCComponents/CompreSearch/AnalystStockWrap";
import ThemeBriefIntroduction from "../../../components/ThemeBriefIntroduction";
import Theme from "../../../components/Theme";
import ThemeCard from "../../../components/ThemeCard";
import Cookies from "js-cookie";
import SideMembersCard from "../../../components/SideMembersCard";
import './index.scss'


// const CompreSearchEmpty = styled.div`
//   display: flex;
//   justify-content: center;
//   align-items: center;
//   min-height: 100%;
// `;

// const CardItemsWrap = styled.div`
//   padding: 16px 0;
//   width: 100%;
//   font-size: 14px;
//   color: #999;
// `;

const CardItem = styled.span`
  color: ${props => (props.active ? "#417cd5" : "#666")};
  cursor: pointer;
  &:hover {
    color: #417cd5;
  }
  > i {
    margin: 0 8px;
    font-style: normal;
  }
  &:last-child {
    > i {
      display: none;
    }
  }
`;

// // 跳转到主站的小模块
// const GoToAnalystSearch = styled.div`
//   margin-bottom: 10px;
//   box-sizing: border-box;
//   padding-top: 10px;
//   padding-left: 20px;
//   width: 870px;
//   height: 64px;
//   background-color: rgba(255, 255, 255, 1);
//   box-shadow: 0px 2px 6px 1px rgba(0, 0, 0, 0.03);
//   border: 1px solid rgba(238, 238, 238, 1);
//   .analystlogo {
//     margin-right: 10px;
//     display: inline-block;
//     width: 40px;
//     height: 40px;
//   }
//   .analystTitle {
//     display: inline-block;
//     vertical-align: middle;
//     p {
//       margin: 0;
//     }
//     .titlename {
//       line-height: 22px;
//       color: rgba(51, 51, 51, 1);
//       font-size: 16px;
//       text-align: left;
//       font-family: PingFangSC-Medium;
//     }
//     .subname {
//       color: rgba(140, 140, 140, 1);
//       font-size: 12px;
//       text-align: left;
//       font-family: PingFangSC-Regular;
//     }
//   }
//   .alink {
//     float: right;
//     margin-top: 14px;
//     margin-right: 25px;
//     color: rgba(140, 140, 140, 1);
//     font-size: 12px;
//     text-align: left;
//     font-family: PingFangSC-Regular;
//     cursor: pointer;
//   }
// `;

@withRouter
export default class CompreSearchResults extends Component {
  constructor(props) {
    super(props);
    const queryParams = queryString.parse(props.location.search);
    const page = parseInt(queryParams.pageIndex || 1);
    this.state = {
      analystSideData:{},//研究员侧边栏数据
      members:{},
      themeCardData: [], //主题卡片数据
      themeSideData: "", //侧边主题介绍
      CardType: "", //卡片类型
      curCard: {}, // 当前实体卡片
      curCardIdx: 0, // 当前实体卡片索引
      cards: [], // 实体卡片
      report: {}, //研报数据
      internal_report: {}, // 内部研报
      vender_reports: {}, // 外部研报
      activities: {}, // 投研活动
      charts: {}, // 数据图,
      table: {}, //数据表
      announcement: {}, //相关公告
      news: {}, //相关资讯
      limit: 10,
      page: page,
      offset: 10 * (page - 1),
      loadStatus: "pending", // done, error
      cardStatus: "pending"
    };
  }

  componentDidMount = async () => {
    // 研报，资讯，公告等列表数据
    this.loadCompreSearchList()
    //加载各类卡片数据
    // this.loadCompreSearchCard();
    // 研报，资讯，公告等列表数据
    // this.loadCompreSearchResults();
  };

  componentDidUpdate(prevProps, prevState) {
    const oQueryParams = queryString.parse(prevProps.location.search);
    const nQueryParams = queryString.parse(this.props.location.search);
    if (nQueryParams.keyword !== oQueryParams.keyword) {
      // 重置页码
      let queryParams = Object.assign({}, nQueryParams, { pageIndex: 1 });
      this.props.history.push({
        pathname: this.props.location.pathname,
        search: `?${queryString.stringify(queryParams)}`
      });
      this.setState(
        {
          limit: 10,
          offset: 0,
          page: 1,
          curCardIdx: 0
        },
        () => {
          // 研报，资讯，公告等列表数据
          this.loadCompreSearchList()
          //加载各类卡片数据
          // this.loadCompreSearchCard();
          // 研报，资讯，公告等列表数据
          // this.loadCompreSearchResults();
        }
      );
    }
  }
  
  processListData(key = "", data = {}) {
    // 转换综搜结果结构
    return {
      result: {
        data: {
          source: key,
          item: data.item || data.items || data.data,
          total_count: data.total || data.total_count || data.numFound || data.totalCount,
          highlight_fields: "",
          request_id: ""
        }
      },
      score: 0,
      source: key
    };
  }
  // 内外部等研报结果合并情况
  processListReport(key = "", data = {}, total_count = 0) {
    // 转换综搜结果结构
    return {
      result: {
        data: {
          source: key,
          item: data,
          total_count: total_count,
          highlight_fields: "",
          request_id: ""
        }
      },
      score: 0,
      source: key
    };
  }

  // 搜索结果--公告，研报，资讯等列表
  loadCompreSearchList = async () => {
    this.setState({ loadStatus: "pending" });
    const { limit, page, offset } = this.state
    const { keyword = "" } = queryString.parse(this.props.location.search);
    try {
      const [res, res2, res3] = await Promise.all([
        // 研报列表
        ask('getReportData', { data: {
          pageIndex: 1,
          pageSize: 5,
          keyword: keyword,
          sort: "compositive"
          // page_num: 0,
          // page_size: 5,
          // search_word: {keyword: [keyword]},
          // prior: 0, //排序
        }}),
        // 公告列表
        ask('noticeFilterAndData', { params: {
          keyword: keyword,
          limit: 5,
          page: 'filing_result' ,
          input_from: 'direct',
          offset: 0,
        }}),
         // 资讯列表
        ask('NewsInfo', { params: {
          keyword: keyword,
          offset: offset,
          limit: limit,
          page: 'news_search_home',
          input_from: 'direct'
        }})
      ])
      if (res.code === 200 || res2.code === 200 || res3.code === 200) {
        this.setState(state => {
            if (res.code === 200) {
              state.report = this.processListData("report", res.data)
            }
            if (res2.code === 200) {
              state.notice = this.processListData("notice", res2.data)
            }
            if (res3.code === 200) {
              state.news = this.processListData("news", res3.data)
            }
            state.loadStatus = "done"
            return state
        });
      } else {
        this.setState({
          loadStatus: "error"
        });
      }
    } catch(e) {
      this.setState({
        loadStatus: "error"
      });
    }
  }

  //加载搜索结果--公告，研报，资讯等列表
  // loadCompreSearchResults = () => {
  //   const { keyword = "" } = queryString.parse(this.props.location.search);
  //   const { limit, offset, announcement, news } = this.state;
  //   this.setState({ loadStatus: "pending" });
  //   ask("CompreSearchResults", { params: { keyword, limit, offset } })
  //     .then(resp => {
  //       const { code, message, data } = resp;
  //       const {
  //         card = [],
  //         report = {},
  //         internal_report = {},
  //         vender_report = {},
  //         activity = {},
  //         chart = {},
  //         table = {}
  //       } = data;
  //       if (
  //         code !== 200 ||
  //         isEmpty(data) ||
  //         (isEmpty(card) &&
  //           isEmpty(internal_report) &&
  //           isEmpty(vender_report) &&
  //           isEmpty(activity) &&
  //           isEmpty(chart) &&
  //           isEmpty(announcement) &&
  //           isEmpty(table) &&
  //           isEmpty(news))
  //       ) {
  //         throw new Error(`Response Exception:${message},code:${code}`);
  //       }
  //       let cards =
  //         card &&
  //         card.filter(item => {
  //           return (
  //             item.type === "Industry" ||
  //             item.type === "Analyst" ||
  //             item.type === "InternalAlyst"
  //           );
  //         });
  //         cards = cards || [];
  //         Cookies.set("curIdx", 0);
  //         let final_limit = 20;
  //         // if (vender_report.items.length == 20) {
  //         //   final_limit = 0;
  //         // } else {
  //         //   final_limit = 20;
  //         // }
  //         ask("ReportSearch", { params: { keyword, limit: final_limit, offset } })
  //           .then(resp => {
  //             let reports = vender_report.items.concat(resp.data.items);
  //             //model和analyst外部研报总数
  //             let total_count = vender_report.total + resp.data.total_count;
  //             this.setState({
  //               vender_reports: this.processListReport(
  //                 "vender_report",
  //                 reports,
  //                 total_count
  //               )
  //             });
  //           })
  //           .catch(err => {
  //             this.setState({ loadStatus: "error" });
  //             console.error(err);
  //           });

  //         this.setState(
  //           {
  //             loadStatus: "done",
  //             curCard: cards[0] || {},
  //             curCardIdx: 0,
  //             cards: cards,
  //             report: this.processListData("report", report),
  //             internal_report: this.processListData("internal_report", internal_report),
  //             // vender_reports: this.processListData('vender_report', vender_report),
  //             activities: this.processListData("activity", activity),
  //             charts: this.processListData("chart", chart),
  //             table: this.processListData("table", table),
  //           },
  //           () => {
  //             if (document.scrollingElement) {
  //               document.scrollingElement.scrollTop = 0;
  //             } else {
  //               // 兼容IE
  //               document.documentElement.scrollTop = 0;
  //             }
  //           }
  //         );
  //       })
  //     .catch(err => {
  //       this.setState({ loadStatus: "error" });
  //       console.error(err);
  //     });

  //   ask("CompreSearchResultsNewsAnnounce", {
  //     params: {
  //       keyword,
  //       offset_news: 0,
  //       page: "comprehensive_search",
  //       input_from: "direct"
  //     }
  //   })
  //     .then(resp => {
  //       const { code, message, data } = resp;
  //       let dataObj = {};
  //       let dataListNotice = [];
  //       let dataListNews = [];
  //       data.map((item, index) => {
  //         // let dataItemArr=[]
  //         if (item.source === "notice") {
  //           item.result.data.item.map((item1, index1) => {
  //             dataListNotice.push(item1);
  //           });
  //           // dataListNotice.push(item.result.data.item)
  //           data.notice = Object.assign(
  //             {},
  //             { items: dataListNotice, total: item.result.data.total_count }
  //           );
  //         }
  //         if (item.source === "news") {
  //           item.result.data.item.map((item1, index1) => {
  //             dataListNews.push(item1);
  //           });
  //           // dataListNews.push(item.result.data.item)
  //           data.news = Object.assign(
  //             {},
  //             { items: dataListNews, total: item.result.data.total_count }
  //           );
  //         }
  //       });
  //       const { notice = {}, news = {} } = data;
  //       if (
  //         code !== 200 ||
  //         isEmpty(data) ||
  //         (isEmpty(notice) && isEmpty(news))
  //       ) {
  //         throw new Error(`Response Exception:${message},code:${code}`);
  //       }
  //       // let cards = card && card.filter(item => {
  //       //     return item.type === 'Industry' || item.type === 'Analyst' || item.type === 'InternalAlyst';
  //       // });
  //       // cards = cards || [];
  //       // Cookies.set('curIdx', 0);
  //       this.setState(
  //         {
  //           // loadStatus: 'done',
  //           // curCard: cards[0] || {},
  //           // curCardIdx: 0,
  //           // cards: cards,
  //           // internal_report: this.processListData('internal_report', internal_report),
  //           // vender_reports: this.processListData('vender_report', vender_report),
  //           // activities: this.processListData('activity', activity),
  //           // charts: this.processListData('chart', chart),
  //           notice: this.processListData("notice", notice),
  //           news: this.processListData("news", news)
  //         },
  //         () => {
  //           // if (document.scrollingElement) {
  //           //     document.scrollingElement.scrollTop = 0;
  //           // } else {
  //           //     // 兼容IE
  //           //     document.documentElement.scrollTop = 0;
  //           // }
  //         }
  //       );
  //     })
  //     .catch(err => {
  //       this.setState({ loadStatus: "error" });
  //       console.error(err);
  //     });
  // };
  // 各类卡片结果
  // loadCompreSearchCard = () => {
  //   const { keyword = "" } = queryString.parse(this.props.location.search);
  //   this.setState({ cardStatus: "pending" });
  //   ask("CompreSearchCard", { params: { keyword } })
  //     .then(resp => {
  //       const { code, message, data } = resp;
  //       if (code !== 200 || isEmpty(data)) {
  //         throw new Error(`Response Exception:${message},code:${code}`);
  //       }
  //       let cards = data.filter(item => {
  //         return (
  //           item.type === "Industry" ||
  //           item.type === "Analyst" ||
  //           item.type === "InternalAlyst" ||
  //           item.type === "Theme" ||
  //           item.type === "Public Company"
  //         );
  //       });
  //       //卡片 code
  //       let index_code = cards[0].code;
  //       if (cards[0].type === "Theme") {
  //         this.setState({
  //           CardType: "Theme"
  //         });
  //         this.loadThemeModule(index_code);
  //       }
  //       else if(cards[0].type === "Industry"){
  //           this.setState({
  //               CardType: "Industry"
  //             });
  //           this.loadStockExport(index_code);
  //       }
  //       else if(cards[0].type==="Analyst"){
  //         this.setState({
  //           CardType: "Analyst"
  //         });
  //         this.analystSideCardLoad(index_code);
  //       }
  //       else{
  //           this.setState({
  //               CardType: cards[0].type
  //           });
  //       }
  //       console.log('卡片类别',cards[0].type);
  //       this.setState({
  //         curCard: cards[0] || {},
  //         curCardIdx: 0,
  //         cards: cards,
  //         cardStatus: "done"
  //       });
  //     })
  //     .catch(err => {
  //       console.error(err);
  //       this.setState({ cardStatus: "error" });
  //     });
  // };
  //加载主题模块
  // loadThemeModule = index_code => {
  //   ask("ThemeIntroduction", { params: { index_code } })
  //     .then(resp => {
  //       const { code, message, data } = resp;
  //       if (code !== 200 || isEmpty(data)) {
  //         throw new Error(`Response Exception:${message},code:${code}`);
  //       }
  //       this.setState({
  //         themeSideData: resp.data.Introduction
  //       });
  //     })
  //     .catch(error => {
  //       console.error(error);
  //       this.setState({ cardStatus: "error" });
  //     });
  //   ask("ThemeCard", { params: { index_code } })
  //     .then(resp => {
  //       const { code, message, data } = resp;
  //       if (code !== 200 || isEmpty(data)) {
  //         throw new Error(`Response Exception:${message},code:${code}`);
  //       }
  //       this.setState(
  //         {
  //           themeCardData: resp.data
  //         }
  //       );
  //     })
  //     .catch(error => {
  //       console.error(error);
  //       this.setState({ cardStatus: "error" });
  //     });
  // };
 
  //个股专家加载
  // loadStockExport=(limit,secUniCode)=>{
  //   ask('StockExpert',{ params: {limit,secUniCode } })
  //   .then(resp=>{
  //       console.log('个股专家数据加载',resp.data);
  //       this.setState({
  //           members:resp.data
  //       })
  //   })
  //   .catch(error=>{
  //       console.log(error);
  //   })
  // }
  handleChangeCardItem = (curCardIdx, curCard) => {
    this.setState({ curCardIdx, curCard });
    Cookies.set("curIdx", curCardIdx);
  };

  // 页码变化
  handlePageChange = page => {
    this.setState(
      {
        limit: 10,
        page: page,
        offset: 10 * (page - 1)
      },
      () => {
        this.loadCompreSearchList();
      }
    );
  };
  goToAnalyst = () => {
    const { keyword = "" } = queryString.parse(this.props.location.search);
    window.open(
      `https://www.analyst.ai/comprehensive-search?keyword=${keyword}`
    );
  };
  //证券卡片点击个股专家
  onStockExpert = item => {
    let str = `$key:${item.name} AND analyst:${item.analyst_code}`;
    this.setState({ keyword: str }, this.changeURLAndRresh);
  };
  //分析师侧边栏数据加载
  analystSideCardLoad=(id)=>{
    ask('getAnalystSideCard',{ params: {honorNumLimit:4,honorRankLimit:4,reportNumLimit:4,id } })
    .then(resp=>{
        this.setState({
          analystSideData:resp.data
        })
        console.log('分析师侧边栏数据加载',resp.data);
    })
    .catch(error=>{
        console.log(error);
    })
  }
  render() {
    const {
      loadStatus,
      CardType,
      curCard,
      curCardIdx,
      cards,
      report,
      internal_report = {},
      vender_reports = {},
      activities = {},
      charts = {},
      notice = {},
      news = {},
      table = {},
      offset,
      cardStatus,
      themeSideData,
      analystSideData,
      members,
      themeCardData
    } = this.state;
    const { keyword = "" } = queryString.parse(this.props.location.search);
    const CardItems = cards.map((item, index) => {
      return (
        <CardItem
          key={index}
          active={index === curCardIdx}
          onClick={() => this.handleChangeCardItem(index, item)}
        >
          {item.name}（{item.cName}）<i>|</i>
        </CardItem>
      );
    });

    let _curCard = Object.assign(
      {},
      {
        name: keyword,
        cName: "",
        code: "",
        type: ""
      },
      curCard
    );
      //分析师侧边栏数据
    let honor_rank_members = {}; //同领域新财富排行
    let honor_num_members = {}; //同领域上榜次数
    let report_num_members = {}; //同领域研报数量排行
    for (var key in analystSideData) {
      if (key === "honor_rank") {
        honor_rank_members = {
          title: "同领域新财富排行",
          titleDesc: analystSideData.direction || "",
          members: analystSideData[key].map(_item => {
            return {
              avatar: _item.image || "",
              name: _item.name || "",
              analyst_code: _item.id || "",
              orgSName: _item.org_sname || "",
              desc: _item.time + "新财富NO." + _item.ranking,
              peo_uni_code: _item.id || ""
            };
          })
        };
      } else if (key === "honor_num") {
        honor_num_members = {
          title: "同领域上榜次数",
          titleDesc: analystSideData.direction || "",
          members: analystSideData[key].map(_item => {
            return {
              avatar: _item.image || "",
              name: _item.name || "",
              analyst_code: _item.analyst_code || "",
              orgSName: _item.org_sname || "",
              desc: _item.honor_total + "次上榜新财富",
              peo_uni_code: _item.peo_uni_code || ""
            };
          })
        };
      } else if (key === "report_num") {
        report_num_members = {
          title: "同领域研报数量排行",
          titleDesc: analystSideData.direction || "",
          members: analystSideData[key].map(_item => {
            return {
              avatar: _item.image || "",
              name: _item.name || "",
              analyst_code: _item.analyst_code || "",
              orgSName: _item.org_sname || "",
              desc: "近一年研报数量" + _item.report_total + "篇",
              peo_uni_code: _item.peo_uni_code || ""
            };
          })
        };
      }
    }
    return (
      <div className="compre-search-results-wrap">
        {/* <CardItemsWrap
          hidden={cards.length <= 1 || offset > 0 || loadStatus === "error"}
        >
          <span>为您推荐：</span>
          {CardItems}
        </CardItemsWrap> */}
        <div className="compre-search-body">
          <div className="compre-search-left">
            {/* 18.10.17
                            临时添加 到主站的功能小模块 
                            dhhuang1
                         */}
            {/* <GoToAnalystSearch>
                            <img src={require("./icon.png")} alt="logo" className="analystlogo" />
                            <div className="analystTitle">
                                <p className="titlename">Analyst.Ai</p>
                                <p className="subname">AI金融信息搜索引擎</p>
                            </div>
                            <span onClick={this.goToAnalyst} className="alink">更多搜索结果，点击进入 ></span>
                        </GoToAnalystSearch> */}
            {/** 主题介绍*/}
            {CardType === "Theme" && themeCardData &&
              themeCardData.graph && (
                <ThemeCard data={themeCardData} hideMore="false" />
              )}
            {/* 个股卡片 */}
            {curCard.type === "Industry" &&
              loadStatus === "done" &&
              offset === 0 && (
                <StockCardWrap
                  stockCode={curCard.cName}
                  stockName={curCard.name}
                  code={curCard.code}
                />
              )}
            {/* 外部分析师卡片 */}
            {(curCard.type === "Analyst" || curCard.type === "InternalAlyst") &&
              loadStatus === "done" &&
              offset === 0 && (
                <CardAnalystWrap
                  code={curCard.code}
                  name={curCard.name}
                  cName={curCard.cName}
                  type={curCard.type}
                />
              )}
            {/* 综合搜索结果---研报公告资讯等 */}
            <SearchResults
              data={[
                report,
                notice,
                news,
                internal_report,
                activities,
                charts,
                table,
                vender_reports
              ]}
              onPageChange={this.handlePageChange}
              loadStatus={loadStatus}
            />
          </div>
          
          <div className="compre-search-right">
             {/**分析师侧边栏-同领域新财富排行*/}
             {!_.isEmpty(honor_rank_members) && CardType === "Analyst" && (
              <div>
               <SideMembersCard
                  title={honor_rank_members.title}
                  UrlLink={`/entity-search/analysis-more`}
                  headerExtrad={" "}
                  titleDesc={honor_rank_members.titleDesc}
                  members={honor_rank_members.members}
                  onItemClick={this.onHonorRank}
              />
              </div>
            )}
              {/**分析师侧边栏-同领域上榜次数*/}
              {!_.isEmpty(honor_num_members) && CardType === "Analyst" && (
              <div>
                <SideMembersCard
                  title={honor_num_members.title}
                  UrlLink={`/entity-search/analysis-more`}
                  headerExtrad={" "}
                  titleDesc={honor_num_members.titleDesc}
                  members={honor_num_members.members}
                  onItemClick={this.onHonorNum}
                />
              </div>
            )}
            {/**分析师侧边栏-同领域研报数量排行*/}
            {!_.isEmpty(report_num_members) && CardType === "Analyst" && (
              <div>
                <SideMembersCard
                  title={report_num_members.title}
                  UrlLink={`/entity-search/analysis-more`}
                  headerExtrad={" "}
                  titleDesc={report_num_members.titleDesc}
                  members={report_num_members.members}
                  onItemClick={this.onReportNum}
                />
              </div>
            )}
            {/*主题简介*/}
            {CardType === "Theme" &&
              themeSideData && <ThemeBriefIntroduction intro={themeSideData} />}
            {/* 个股侧边组件 */}
            {curCard.type === "Industry" &&
              loadStatus === "done" &&
              offset === 0 && <StockSiderWrap {...curCard} />}
            {/* 分析师研究公司 */}
            {curCard && (curCard.type === "Analyst" || curCard.type === "InternalAlyst") &&
              loadStatus === "done" && 
              offset === 0 && <AnalystStockWrap {...curCard} />}
            {/* 研究员组件 */}
            {curCard.type === "Analyst" &&
              loadStatus === "done" &&
              offset === 0 && <AnalystSiderWrap {..._curCard} />}
            {/*个股专家*/}
            {!_.isEmpty(members) && CardType === "Industry" && (
              <div>
                <SideMembersCard
                  title={'个股专家'}
                  UrlLink={`/analysis-more`}
                  headerExtrad={<div />}
                  titleDesc={members.industryName}
                  members={members.stockExpert}
                  onItemClick={this.onStockExpert}
                />
              </div>
            )}
          </div>
        </div>
        <div className="compre-search-loading" hidden={loadStatus !== "pending"}>
          <Spin />
        </div>
        {/* <CompreSearchEmpty hidden={loadStatus !== 'error'}>
                    <NoDataTip/>
                </CompreSearchEmpty> */}
      </div>
    );
  }
}
