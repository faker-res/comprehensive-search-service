/**
 * @description 资讯模块
 * @author whm
 * date: 2019-4.12
 */

const report = {
    // 24小时热搜
    reportTopSearch: {
      method: 'GET',
      url: '/api/seller/mail/report/operate-config/keyword-query'
    },
    // 主要行业和报告类型
    getReportFilterConfig: {
      method: "GET",
      url: '/api/seller/mail/report/filter'
    },
    // 获取筛选面板数据
    getReportFilterData: {
      method: "POST",
      url: '/api/report/api/mailFilterItems'
    },
     // 研报数据
     getReportData: {
      method: "POST",
      url: '/api/report/api/mailReportList'
    },
  }
  
  export default report