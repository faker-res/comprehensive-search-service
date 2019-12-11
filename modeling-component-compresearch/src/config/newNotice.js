/**
 * @description 资讯模块
 * @author shlhe
 * date: 2018-11-8
 */

const news = {
    // 24小时热搜
    noticeTopSearch: {
      method: 'GET',
      url: '/api/notice/operate-config/keyword-query'
    },
    noticeFilterAndData: {
        method: 'GET',
         url: '/api/notice/searchV3'
    }
  }
  
  export default news