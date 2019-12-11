/**
 * @description 数据模块
 * @author shlhe
 * date: 2018-11-14
 */

const dataSearch = {
    // EDB数据加载
    edbDataSearch: {
      method: 'GET',
      url: '/api/compre/data-search/edb'
    },
    //EDB数据-详情
    edbDataDetail:{
      method: 'GET',
      url: '/search-api/data/edbIndexLookUpByID'
    }
  }
  
  export default dataSearch