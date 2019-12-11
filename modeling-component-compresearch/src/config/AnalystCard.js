/**
 * @description 研究员卡片
 * @author wqw
 * date: 2018-10-18
 */

const AnalystCard = {
    // 基本信息
    AnalystBaseInfo: {
      method: 'GET',
      url: '/resourceapi/analyst/info'
    },
    //团队竞争力分析
    AnalyzerCompetitionChart:{
      method:'GET',
      url:'/invest-dc-inner-api/analyst/competition'
    },
    // 行业热力
    AnalystIndustryMap: {
      method: "GET",
      url: "/api/compre/analyst/heat"
    },
    //新闻消息
    NewsEntitySearch: {
        method: 'GET',
        url: '/api/news/search'
    },
    NoticesEntitySearch: {
        method: 'GET',
        url: '/api/compre/notice'
    },
    ReportsEntitySearch: {
        method: 'GET',
        url: '/api/report/'
    },
    //新闻消息-研报详情
    getAnalystReport: {
      method: 'GET',
      url: '/api/report/report-detail'
  },
    //分析师卡片侧边栏
    getAnalystSideCard:{
        method: 'GET',
        url: '/resourceapi/analyst/sidebar'
    }
  }
  
  export default AnalystCard