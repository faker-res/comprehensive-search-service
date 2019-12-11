/**
 * @description 资讯模块
 * @author shlhe
 * date: 2018-11-8
 */

const news = {
  // 24小时热搜
  hotSearchLoad: {
    method: 'GET',
    url: '/api/news/keywordQuery'
  },
  newsSearch:{
    url:'/api/news/news-top',
    method:'GET'
  },
  // https://api-invest.modeling.ai/api/news/news-top
  // 数据图详情
  // chartDetailLoad: {
  //   method: 'GET',
  //   url: '/api/compre/data/chart/detail'
  // },
  // 数据图详情
  chartDetailLoad: {
    method: 'GET',
    url: '/search-api/chart/detail'
  },
  // 我的资源库数据图详情
  myChartDetailLoad: {
    method: 'GET',
    url: '/search-api/chart/get'
  },
  //数据表详情
  getTableDataDetail: {
    method: "GET",
    url: "/api/compre/data/table/detail"
    },
}

export default news