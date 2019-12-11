/**
 * @description 综合搜索接口
 * @author kygeng
 * date: 2018-08-20
 */

const CompreSearchApi = {
    // 综合搜索结果
    CompreSearchResults: {
        url: '/search-api/compre',
        method: 'GET'
    },
    // 综合搜索结果/公告/资讯
    CompreSearchResultsNewsAnnounce:{
        url:'/api/compre/search/general',
        method:'GET'
    },
    // 综合搜索卡片
    CompreSearchCard: {
        url: '/search-api/compre/card',
        method: 'GET'
    },
    // 个股卡片
    StockCard: {
        url: '/invest-dc-inner-api/industry/card',
        method: 'GET'
    },
    // 个股分时曲线
    StockTimeLine: {
        url: '/invest-dc-inner-api/timeline',
        method: 'GET'
    },
    // 个股k线
    StockKLine: {
        url: '/invest-dc-inner-api/kline',
        method: 'GET'
    },
    // 个股侧边栏数据
    StockSider: {
        url: '/invest-dc-inner-api/industry/relatedCompany',
        method: 'GET'
    },
    // 个股相关股票
    StockSimilar: {
        url: '/search-api/stock-similar',
        method: 'GET'
    },
    // 个股相关研究员
    StockAnalyst: {
        url: '/search-api/stock-analyst',
        method: 'GET'
    },
    // 分析师卡片
    AnalystCard: {
        url: '/resourceapi/analyst/info',
        method: 'GET'
    },
    // 分析师研究股票数据
    AnalystStock: {
        url: '/resourceapi/analyst/researchStock',
        method: 'GET'
    },
    // 外部分析研究相关行业
    AnalystIndustry: {
        url: '/search-api/analyst-industry',
        method: 'GET'
    },
    // 外部分析师相关研究员
    VenderAnalystSimilar: {
        url: '/resourceapi/vender-analyst-similar',
        method: 'GET'
    },
    // 分析师研究股票图表数据
    AnalystStockChart: {
        method: 'GET',
        url:`/resourceapi/analyst/stockChart`,
        right: true
    },
    // 外部分析师研究股票实时行情数据
    AnalystStockInfo: {
        method: 'GET',
        url:`/resourceapi/analyst/stockCard`,
        right: true
    },
    // 内部相关研究员
    InternalAnalystSimilar: {
        method: 'GET',
        url: '/resourceapi/internal-analyst-similar'
    },
    // 投研活动搜索
    ActivitySearch: {
        method: 'POST',
        url: '/search-api/event/wechat'
    },
    // 分析师研究相关上市公司（侧边组件）
    AnalystStockCompany: {
        method: 'GET',
        url: '/resourceapi/analyst-industry'
    },
    // 首页个股市场关注度
    StockMarketAttention: {
        method: 'GET',
        url: '/api/compre/card'
    },
    // 我的模拟组合
    MyModelPorfolio: {
        method: 'GET',
        url: '/invest-research-api/stock/anaylstPortlioInfo'
    },
    // 内外部研究员（指定关键词）
    AllAnalystSimilar: {
        method: 'GET',
        url: '/resourceapi/interAndVender-analyst-similar'
    },
    //相关公告
    AnnouncementInfo: {
        method: 'GET',
        url: '/api/compre/notice'
    },
    //相关公告-搜索热词
    AnnouncementTopSearch: {
        method: 'GET',
        url: '/api/report/operate-config/keyword-query'
    },
    //相关资讯
    NewsInfo: {
        method: 'GET',
        url: '/api/news/searchV3'
    },
    //相关资讯--热搜
    NewsTopSearch: {
        method: 'GET',
        url: '/api/news/keywordQuery'
    },
    //相关资讯--24小时最热资讯
    NewsTopMostSearch: {
        method: 'GET',
        url: '/api/news/news-top'
    },

    //数据图
    ChartsInfo: {
        method: 'GET',
        url: '/api/compre/data/chart'
    },
    //数据图-我的资源库
    MyChartsInfo: {
        method: 'post',
        url: '/search-api/chart/tour'
    },

    //主题卡片
    ThemeCard:{
        method:'GET',
        url:'/invest-dc-inner-api/theme/card'
    },
    //主题介绍
    ThemeIntroduction:{
        method:'GET',
        url:' /invest-dc-inner-api/theme/sidebar'
    },
    //数据表搜索结果
    TableModuleResult: {
        method: 'GET',
        url: '/api/compre/data/table'
    },
    //个股列表
    industryStockInfoList: {
        method: 'GET',
        url: '/invest-dc-inner-api/industry/stockInfoList',
        right: undefined,
    },
    //个股列表搜索提示框
    stockRecommendGetstockList: {
        method: 'GET',
        url: '/api/suggestion/recommend/getstocklist',
        right: undefined,
    },
    // 研究领域下拉框
    BrokerIndustryList: {
        method: 'GET',
        url: '/invest-dc-inner-api/industry/industryList'
    },
    // 分析师列表接口
    AnalystList: {
        method: "GET",
        url: "/resourceapi/analyst/analystList"
    },
    // 人名搜索提示框接口
    AnalystRecommendGetAutocompleteList: {
        method: "GET",
        url: "/resourceapi/analyst/analystNameList"
    },
    // 机构搜索框
    AnalystorgSnameList: {
        method: "GET",
        url: "/resourceapi/analyst/orgSnameList"
    },
    //个股专家
    StockExpert:{
        method:'GET',
        url:'/resourceapi/stockExpert'
    }
}

export default CompreSearchApi;