package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import la.niub.abcapi.servicecompre.component.client.IApiBestNewsClient;
import la.niub.abcapi.servicecompre.component.client.IApiRecommendNewsClient;
import la.niub.abcapi.servicecompre.component.client.IServiceReportClient;
import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.config.enums.SearchTypeEnum;
import la.niub.abcapi.servicecompre.dao.notice.IHiborDao;
import la.niub.abcapi.servicecompre.dao.notice.IOrganDao;
import la.niub.abcapi.servicecompre.dao.reporter.IComprehensiveSearchesHotTipsDao;
import la.niub.abcapi.servicecompre.dao.reporter.IComprehensiveSearchesReportRecommendDao;
import la.niub.abcapi.servicecompre.dao.reporter.IComprehensiveSearchesTopDao;
import la.niub.abcapi.servicecompre.model.ComprehensiveSearchesHotTipsModel;
import la.niub.abcapi.servicecompre.model.ComprehensiveSearchesReportRecommendModel;
import la.niub.abcapi.servicecompre.model.ComprehensiveSearchesTopModel;
import la.niub.abcapi.servicecompre.model.HiborModel;
import la.niub.abcapi.servicecompre.model.OrganModel;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartGraphBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartGraphChartBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartGraphTableBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartUseBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveHotBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveNewsBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveNoticeBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveReportBO;
import la.niub.abcapi.servicecompre.model.bo.ItemAndLinkBO;
import la.niub.abcapi.servicecompre.model.bo.NoticeBO;
import la.niub.abcapi.servicecompre.model.bo.TableConfigBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardWeChatBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarAnalystBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarStockBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarWeChatBO;
import la.niub.abcapi.servicecompre.model.request.CardAnalystRequest;
import la.niub.abcapi.servicecompre.model.request.CardStockRequest;
import la.niub.abcapi.servicecompre.model.request.CardWechatRequest;
import la.niub.abcapi.servicecompre.model.request.SidebarAnalystRequest;
import la.niub.abcapi.servicecompre.model.request.SidebarStockRequest;
import la.niub.abcapi.servicecompre.model.request.SidebarWechatRequest;
import la.niub.abcapi.servicecompre.model.request.client.ClientBestNewsRequest;
import la.niub.abcapi.servicecompre.model.request.client.KeywordRequest;
import la.niub.abcapi.servicecompre.model.request.client.NoticeRequest;
import la.niub.abcapi.servicecompre.model.request.client.ReportRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.client.ApiBestNewsResponse;
import la.niub.abcapi.servicecompre.model.response.client.ApiRecommendNewsResponse;
import la.niub.abcapi.servicecompre.model.response.client.ServiceChartConfigResponse;
import la.niub.abcapi.servicecompre.model.response.client.ServiceSearchReportResponse;
import la.niub.abcapi.servicecompre.model.response.client.news.BestNewsData;
import la.niub.abcapi.servicecompre.model.response.client.news.RecommendNewsData;
import la.niub.abcapi.servicecompre.model.response.client.report.ReportItemResponse;
import la.niub.abcapi.servicecompre.service.ICardService;
import la.niub.abcapi.servicecompre.service.IComprehensiveService;
import la.niub.abcapi.servicecompre.service.IKeywordConfigService;
import la.niub.abcapi.servicecompre.service.INoticeMangerService;
import la.niub.abcapi.servicecompre.service.IReportManagerService;
import la.niub.abcapi.servicecompre.service.ISidebarService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComprehensiveServiceImpl implements IComprehensiveService {

    private static Logger logger = LogManager.getLogger(ComprehensiveServiceImpl.class);

    @Autowired
    HttpServletRequest servletRequest;

    @Autowired
    IReportManagerService reportManagerService;

    @Autowired
    IKeywordConfigService keywordConfigService;

    @Autowired
    INoticeMangerService noticeMangerService;

    @Autowired
    ICardService cardService;

    @Autowired
    ISidebarService sidebarService;

    @Autowired
    IComprehensiveSearchesHotTipsDao comprehensiveSearchesHotTipsDao;

    @Autowired
    IComprehensiveSearchesTopDao comprehensiveSearchesTopDao;

    @Autowired
    IComprehensiveSearchesReportRecommendDao comprehensiveSearchesReportRecommendDao;

    @Autowired
    IHiborDao hiborDao;

    @Autowired
    IOrganDao organDao;

    @Autowired
    IApiBestNewsClient apiNewsClient;

    @Autowired
    IApiRecommendNewsClient apiRecommendNewsClient;

    @Autowired
    IServiceReportClient serviceReportClient;

    @Override
    public ComprehensiveBO buildAllTypeResult() throws ServiceException {
        ComprehensiveBO data = new ComprehensiveBO();

        //热门推荐
        data.setHot(buildHotTips());

        data.setTop(buildTop(7,0));

        //数据图表
        data.setChart(buildReportChart());

        //公告
        Map<String,ComprehensiveNoticeBO> notice = new HashMap<>();
        notice.put("perspective",buildNotice("perspective",3));
        notice.put("events",buildNotice("events",4));
        data.setNotice(notice);

        //研报
        data.setReport(buildReport(2,0));

        //资讯
        data.setNews(buildNews());

        //热门推荐资讯改不读配置
        ComprehensiveHotBO hot = data.getHot();
        ComprehensiveNewsBO news = data.getNews();
        hot.setNews(news.getHot());
        data.setHot(hot);
        news.setHot(null);
        data.setNews(news);

        return data;
    }

    @Override
    public Object buildTipsAndTop() {
        Map<String, Object> result = new HashMap<>();
        ComprehensiveHotBO hot = buildHotTips();

        ComprehensiveNewsBO news = buildNews();
        hot.setNews(news.getHot());

        result.put("hot", hot);
        result.put("top", buildTop(7,0));
        return result;
    }

    @Override
    public ComprehensiveHotBO buildHotTips() {
        List<ComprehensiveSearchesHotTipsModel> list = comprehensiveSearchesHotTipsDao.buildRecords();

        ComprehensiveHotBO comprehensiveHotBO = new ComprehensiveHotBO();
        for (ComprehensiveSearchesHotTipsModel item : list){
            switch (item.getType()){
                case SearchTypeEnum.CHART:
                    List<ComprehensiveSearchesHotTipsModel> chartList = comprehensiveHotBO.getChart();
                    if (chartList == null){
                        chartList = new ArrayList<>();
                    }
                    chartList.add(item);
                    comprehensiveHotBO.setChart(chartList);
                    break;
                case SearchTypeEnum.TABLE:
                    List<ComprehensiveSearchesHotTipsModel> tableList = comprehensiveHotBO.getTable();
                    if (tableList == null){
                        tableList = new ArrayList<>();
                    }
                    tableList.add(item);
                    comprehensiveHotBO.setTable(tableList);
                    break;
                case SearchTypeEnum.NOTICE:
                    List<ComprehensiveSearchesHotTipsModel> noticeList = comprehensiveHotBO.getNotice();
                    if (noticeList == null){
                        noticeList = new ArrayList<>();
                    }
                    noticeList.add(item);
                    comprehensiveHotBO.setNotice(noticeList);
                    break;
                case SearchTypeEnum.REPORT:
                    List<ComprehensiveSearchesHotTipsModel> reportList = comprehensiveHotBO.getReport();
                    if (reportList == null){
                        reportList = new ArrayList<>();
                    }
                    reportList.add(item);
                    comprehensiveHotBO.setReport(reportList);
                    break;
                case SearchTypeEnum.NEWS:
//                    name = SearchTypeEnum.C_SEARCH_NEWS.getName();
                    break;
                default:
                    break;
            }
        }
        return comprehensiveHotBO;
    }

    @Override
    public List<ComprehensiveSearchesTopModel> buildTop(Integer limit, Integer offset) {
        return comprehensiveSearchesTopDao.buildRecordByLimit(limit,offset);
    }

    @Override
    public ComprehensiveChartBO buildReportChart() {
        ComprehensiveChartBO comprehensiveChartBO = new ComprehensiveChartBO();
        List<ComprehensiveSearchesReportRecommendModel> list = comprehensiveSearchesReportRecommendDao.buildRecords();

        List<ComprehensiveChartGraphBO> graph = new ArrayList<>();
        for (ComprehensiveSearchesReportRecommendModel report : list){
            ComprehensiveChartGraphBO comprehensiveChartGraphBO = new ComprehensiveChartGraphBO();

            HiborModel hibor = hiborDao.selectById(Long.valueOf(report.getReport_id()));
            if (StringUtils.isNotEmpty(hibor.getHonor())){
                hibor.setHonors(Arrays.asList(hibor.getHonor().split(",")));
            }
            comprehensiveChartGraphBO.setReport(hibor);

            comprehensiveChartGraphBO.setLink("/report-search/report-detail?report_id="+report.getReport_id()+"&tab=chartlistviewer");
            comprehensiveChartGraphBO.setChart_num(0);
            comprehensiveChartGraphBO.setTable_num(0);

            ComprehensiveChartGraphChartBO chartData = null;
            // 获取图的数量和推荐图
            List<ComprehensiveChartGraphChartBO> chartsRet = reportManagerService.getReportCharts(report.getReport_id());
            if (!ObjectUtils.isEmpty(chartsRet)){
                comprehensiveChartGraphBO.setChart_num(chartsRet.size());
                chartData = chartsRet.get(0);
                for (ComprehensiveChartGraphChartBO item : chartsRet){
                    if (item.getId().equals(report.getCharts_id())){
                        chartData = item;
                        break;
                    }
                }
            }

            // 获取表的数量
            List<ComprehensiveChartGraphTableBO> tablesRet = reportManagerService.getReportTables(report.getReport_id());
            if (!ObjectUtils.isEmpty(chartsRet)){
                comprehensiveChartGraphBO.setTable_num(tablesRet.size());
            }

            comprehensiveChartGraphBO.setChart_data(chartData);

            graph.add(comprehensiveChartGraphBO);
        }
        comprehensiveChartBO.setGraph(graph);

        ComprehensiveChartUseBO comprehensiveChartUseBO = keywordConfigService.getAllTableConfig(1);
        List<TableConfigBO> useList = comprehensiveChartUseBO.getUse();
        for (TableConfigBO item : useList){
            item.setLink("/comprehensive-search/list?secondary_nav=data&keyword="+item.getName()+"&tab_type=page-table");
        }
        comprehensiveChartUseBO.setUse(useList);
        comprehensiveChartBO.setUse(comprehensiveChartUseBO);

        return comprehensiveChartBO;
    }

    @Override
    public ComprehensiveNoticeBO buildNotice(String module, Integer limit) throws ServiceException {
        NoticeRequest noticeRequest = new NoticeRequest();
        noticeRequest.setTab_type("page-search");
        noticeRequest.setStart_time(FastDateFormat.getInstance("yyyy-MM-dd").format(new Date()));
        noticeRequest.setEnd_time(FastDateFormat.getInstance("yyyy-MM-dd").format(new Date()));
        noticeRequest.setOrder_by("all_score");
        noticeRequest.setOffset(0);
        noticeRequest.setLimit(limit);
        noticeRequest.setGroup_by("default");

        String link = null;
        if (module.equals("perspective")){
            noticeRequest.setPerspective(true);
            link = "/notice/notice-detail?tab=perspectiveviewer&src_id=";
        }else if (module.equals("events")){
            noticeRequest.setCategories("T004002004|T004002005|T004002002|T004002007|T004002012|T004002015|S004005|S004006|T004007002");
            link = "/notice/notice-html?src_id=";
        }

        //@todo uid
        NoticeBO noticeBO = noticeMangerService.buildNoticeReadingSearchResult(servletRequest.getParameter("userId"),noticeRequest,module,false);

        ComprehensiveNoticeBO bo = new ComprehensiveNoticeBO();
        bo.setItems(noticeBO.getItem());
        bo.setLink(link);
        return bo;
    }

    @Override
    public ComprehensiveReportBO buildReport(Integer limit, Integer terminal) {
        ComprehensiveReportBO comprehensiveReportBO = new ComprehensiveReportBO();
        // 热门机构
        List<OrganModel> organModelList = organDao.getTop6();
        for (OrganModel item : organModelList){
            item.setLink("/comprehensive-search/list?secondary_nav=report&source="+item.getPublish());
        }
        comprehensiveReportBO.setOrgan(organModelList);

        // 研报推荐专题
        KeywordRequest keywordRequest = new KeywordRequest();
        keywordRequest.setType(1);
        keywordRequest.setModule(30001);
        keywordRequest.setTerminal(terminal);
        keywordRequest.setLan(0);
        keywordRequest.setLimit(limit);
        keywordRequest.setOffset(0);
        Response<List<ServiceChartConfigResponse>> chartConfig = serviceReportClient.getChartConfig(keywordRequest);
        List<ServiceChartConfigResponse> chartConfigData = chartConfig.getData();
        for (ServiceChartConfigResponse item : chartConfigData){
            item.setLink("/comprehensive-search/list?secondary_nav=report&keyword="+item.getQuery()+"&subject_id="+item.getId());
        }
        comprehensiveReportBO.setTopic(chartConfigData);

        // 公告列表
        //@todo 模拟有uid
        String userId = StringUtils.defaultString(servletRequest.getParameter("userId"),"c9941cd943d74dda4b1fb51ce64d6b62");
        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setHonored(true);
        reportRequest.setCategories("沪深股研究");
        reportRequest.setFirst_category("R001");
        reportRequest.setOffset(0);
        reportRequest.setLimit(4);
        reportRequest.setUserId(userId);
        Response<ServiceSearchReportResponse> report = serviceReportClient.report(reportRequest);
        ItemAndLinkBO reportList = new ItemAndLinkBO<ReportItemResponse>();
        reportList.setItems(report.getData().getItems());
        reportList.setLink("/report-search/report-detail?report_id=");
        comprehensiveReportBO.setList(reportList);

        return comprehensiveReportBO;
    }

    @Override
    public ComprehensiveNewsBO buildNews() {
        ComprehensiveNewsBO comprehensiveNewsBO = new ComprehensiveNewsBO();

        ApiBestNewsResponse bestNews = apiNewsClient.bestNews(new ClientBestNewsRequest());

        List<BestNewsData> bestBestNewsData = bestNews.getData();
        for (BestNewsData item : bestBestNewsData){
//            item.setLink("/news/detail?id="+item.getArticle_id()+"&channel_id=0&channel_name=精选");
            item.setLink("https://news.analyst.ai/#/news/detail/"+item.getArticle_id()+"/精选");
        }
        comprehensiveNewsBO.setHot(bestBestNewsData.subList(0,Math.min(4,bestBestNewsData.size())));
        comprehensiveNewsBO.setBest(bestBestNewsData.subList(Math.min(3,bestBestNewsData.size()), bestBestNewsData.size()));

        ClientBestNewsRequest clientRecommendNewsRequest = new ClientBestNewsRequest();
        clientRecommendNewsRequest.setPageNo(0);
        clientRecommendNewsRequest.setPageSize(10);
        ApiRecommendNewsResponse recommendNews = apiRecommendNewsClient.recommendNews(clientRecommendNewsRequest);
        List<RecommendNewsData> recommendNewsData = recommendNews.getData();
        for (RecommendNewsData item : recommendNewsData){
//            item.setLink("/news/detail?id="+item.getArticle_id()+"&channel_id=0&channel_name=精选");
            item.setLink("https://news.analyst.ai/#/news/detail/"+item.getArticle_id()+"/精选");
        }
        comprehensiveNewsBO.setRecommend(recommendNewsData.subList(0,Math.min(5,recommendNewsData.size())));

        return comprehensiveNewsBO;
    }

    @Override
    public CardBO buildCardsResult(String type, String data) {
        CardBO ret = new CardBO();
        String type_replcae = type.replace(" ","");
        switch (type_replcae){
            case "Analyst":
                CardAnalystRequest cardAnalystRequest = JSON.parseObject(data,CardAnalystRequest.class);
                CardAnalystBO cardAnalystBO = cardService.buildAnalystCard(cardAnalystRequest.getCode(), cardAnalystRequest.getCname());
                List<CardAnalystBO> list1 = new ArrayList<>();
                list1.add(cardAnalystBO);
                ret.setResult(list1);
                break;
            case "Industry":
                CardStockRequest cardStockRequest = JSON.parseObject(data,CardStockRequest.class);
                CardStockBO cardStockBO = cardService.buildStockCard(cardStockRequest.getCode());
                List<CardStockBO> list = new ArrayList<>();
                list.add(cardStockBO);
                ret.setResult(list);
                break;
            case "WeChatAccount":
                CardWechatRequest cardWechatRequest = JSON.parseObject(data,CardWechatRequest.class);
                CardWeChatBO cardWeChat = cardService.buildWeChatCard(cardWechatRequest.getCode());
                List<CardWeChatBO> weChatList = new ArrayList<>();
                weChatList.add(cardWeChat);
                ret.setResult(weChatList);
                break;
            default:
                return null;
        }

        ret.setSource(type);
        ret.setFlag(ret.getResult() != null);

        return ret;
    }

    @Override
    public SidebarBO buildSideResult(String type, String data) {
        SidebarBO ret = new SidebarBO();
        String type_replcae = type.replace(" ","");
        switch (type_replcae){
            case "Analyst":
                SidebarAnalystRequest sidebarAnalystRequest = JSON.parseObject(data,SidebarAnalystRequest.class);
                SidebarAnalystBO sidebarAnalystBO = sidebarService.buildAnalyst(sidebarAnalystRequest.getCode(), 4);
                return sidebarAnalystBO;
            case "Industry":
                SidebarStockRequest sidebarStockRequest = JSON.parseObject(data,SidebarStockRequest.class);
                SidebarStockBO sidebarStockBO = sidebarService.buildStock(sidebarStockRequest.getCode(),8,4);
                return sidebarStockBO;
            case "WeChatAccount":
                SidebarWechatRequest sidebarWechatRequest = JSON.parseObject(data,SidebarWechatRequest.class);
                SidebarWeChatBO sidebarWeChatBO = sidebarService.buildWeChat(sidebarWechatRequest.getCode());
                return sidebarWeChatBO;
            default:
                return ret;
        }
    }
}
