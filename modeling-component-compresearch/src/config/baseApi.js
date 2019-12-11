/**
 * @description 基础API
 * @author --
 * date: --
 */

const baseApi = {


  wikiLogin: {
    method: 'POST',
    url: 'http://10.11.255.44:8090/dologin.action',
    right: undefined,
  },
  
  wikiAccess: {
    method: 'GET',
    url: 'http://10.11.255.44:8090/rest/api/space',
    right: undefined,
  },
  // 邮件搜索 模糊
  ResapiSearchFuzzyHint: {
    method: 'GET',
    url: '/resapi/search/fuzzy/hint',
    right: undefined,
  },
  // 搜索 高级
  ResapiSearchExactHint: {
    method: 'POST',
    url: '/resapi/search/exact/hint',
    right: undefined,
  },
  //股票搜索
  StockSearchSuggest: {
    method: 'GET',
    url: '/api/mystock/suggest/security'
  },
  //市场多级分类
  MarketCategories: {
    method: 'GET',
    url: '/api/usercenter/market/categories'
  },
  //市场分类下股票
  StocksInCategory: {
    method: 'GET',
    url: '/api/mystock/market/categories/items'
  },
  //添加股票
  AddStock: {
    method: "POST",
    url: "/api/mystock/subscriptions"
  },
  //导入EXCEL文件
  ImportExcel: {
    method: "POST",
    url: "/api/mystock/subscriptions/import",
  },
  //获取收益率走势
  getRate: {
    method: "GET",
    url: "/api/mystock/holdstock/rate",
  },
  //获取盈亏
  getProfit: {
    method: "GET",
    url: "/api/mystock/holdstock/profit",
  },
  //获取全部盈亏
  getAllProfit: {
    method: "GET",
    url: "/api/mystock/holdstock/all",
  },
  //获取全部市值
  getAllMarketValue: {
    method: "GET",
    url: "/api/mystock/holdstock/all2",
  },
  //添加本金
  editPrincipal: {
    method: "POST",
    url: "/api/mystock/holdstock/capital",
  },
  //获取币种相关指数
  getIndex: {
    method: "GET",
    url: "/api/mystock/holdstock/index",
  },
  // 获取用户信息
  getUserInfo: {
    method: 'GET',
    url: '/api/platform/account/info'
  },
  // 根据域名获取公司信息
  getCompanyInfo: {
    method: 'GET',
    url: '/uc/getUserInfoByDomain'
  },
  // 研报阅读明细
  getReportLogDetail:{
    method: 'GET',
    url: '/invest-research-api/report/log/detail'
  },
  // 累计阅读量、累计阅读用户量、平均阅读时长
  getReportLogCumulativeReading: {
    method: 'GET',
    url: '/invest-research-api/report/log/cumulativeReading'
  },
  enableEmail: {
    method: 'POST',
    url: '/resapi/mail/enable',
    right: undefined
},
disableEmail: {
    method: 'POST',
    url: '/resapi/mail/disable',
    right: undefined
}
};


export default baseApi;
