/**
 * @description 研报搜索API
 * @author kygeng
 * date: 2018-08-09
 */
const ReportResultsApi = {
    // 内部研报搜索
    PrivateReportSearch: {
        url: '/search-api/report/upload',
        method: 'post'
    },
    VenderReportSearch: {
        url: '/search-api/report/mail',
        method: 'post'
    },
    ReportSearch:{
        url:'/api/compre/report',
        method:'GET'
    },
    //研报24小时热搜
    hotSearchReport:{
        url:'/api/news/keywordQuery',
        method:'GET' 
    }
};
export default ReportResultsApi;