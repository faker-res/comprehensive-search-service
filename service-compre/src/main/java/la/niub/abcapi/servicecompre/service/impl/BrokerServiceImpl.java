package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import la.niub.abcapi.servicecompre.component.client.IServiceNoticeClient;
import la.niub.abcapi.servicecompre.component.client.IServiceReportClient;
import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.component.exception.ValidatorException;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.code.BrokerEnumCode;
import la.niub.abcapi.servicecompre.config.map.IndustryCodeMap;
import la.niub.abcapi.servicecompre.config.map.ResearchIndustryMap;
import la.niub.abcapi.servicecompre.dao.market.IIndexCaptitalFlowDao;
import la.niub.abcapi.servicecompre.dao.market.IIndexPriceDayDao;
import la.niub.abcapi.servicecompre.dao.notice.IAnalystBasicinfoDao;
import la.niub.abcapi.servicecompre.dao.notice.IAnalystChanStatusDao;
import la.niub.abcapi.servicecompre.dao.notice.IAnalystHonorDao;
import la.niub.abcapi.servicecompre.dao.notice.IAnalystOverviewDao;
import la.niub.abcapi.servicecompre.dao.notice.IHiborDao;
import la.niub.abcapi.servicecompre.dao.notice.IOrganDao;
import la.niub.abcapi.servicecompre.dao.notice.IReportTypeChoiceDao;
import la.niub.abcapi.servicecompre.dao.notice.ISeccomActivitiesDao;
import la.niub.abcapi.servicecompre.dao.reporter.*;
import la.niub.abcapi.servicecompre.model.*;
import la.niub.abcapi.servicecompre.model.bo.AnalystHonorInfoBO;
import la.niub.abcapi.servicecompre.model.bo.LatestReportBO;
import la.niub.abcapi.servicecompre.model.bo.ReportCountBO;
import la.niub.abcapi.servicecompre.model.bo.ResearchIndustryBasicInfoBO;
import la.niub.abcapi.servicecompre.model.bo.StarAnalystInfoBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystReportStocksBO;
import la.niub.abcapi.servicecompre.model.dao.AbcIndustryDaoModel;
import la.niub.abcapi.servicecompre.model.dao.AnalystOverviewDaoModel;
import la.niub.abcapi.servicecompre.model.dao.HiborGroupByDaoModel;
import la.niub.abcapi.servicecompre.model.dao.IndexCaptitalFlowGroupByDaoModel;
import la.niub.abcapi.servicecompre.model.request.BrokerHeatMapRequest;
import la.niub.abcapi.servicecompre.model.request.BrokerNewsRequest;
import la.niub.abcapi.servicecompre.model.request.BrokerOverviewRequest;
import la.niub.abcapi.servicecompre.model.request.client.ClientNoticeRequest;
import la.niub.abcapi.servicecompre.model.request.client.ReportRequest;
import la.niub.abcapi.servicecompre.model.response.*;
import la.niub.abcapi.servicecompre.model.response.client.ApiNewsResponse;
import la.niub.abcapi.servicecompre.model.response.client.ClientNoticeResponse;
import la.niub.abcapi.servicecompre.model.response.client.ServiceSearchReportResponse;
import la.niub.abcapi.servicecompre.model.response.client.notice.NoticeItem;
import la.niub.abcapi.servicecompre.service.IBrokerService;
import la.niub.abcapi.servicecompre.service.ICardService;
import la.niub.abcapi.servicecompre.service.ICompanyManagerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BrokerServiceImpl implements IBrokerService {

    private final static Logger logger = LogManager.getLogger(BrokerServiceImpl.class);

    @Autowired
    IOrganDao organDao;

    @Autowired
    IHiborDao hiborDao;

    @Autowired
    IAnalystChanStatusDao analystChanStatusDao;

    @Autowired
    IAnalystBasicinfoDao analystBasicinfoDao;

    @Autowired
    IAnalystOverviewDao analystOverviewDao;

    @Autowired
    IAnalystHonorDao analystHonorDao;

    @Autowired
    IAbcIndustryDao abcIndustryDao;

    @Autowired
    ITransacDao transacDao;

    @Autowired
    IIndexPriceDayDao indexPriceDayDao;

    @Autowired
    IIndexBasicInfoDao indexBasicInfoDao;

    @Autowired
    IIndexCaptitalFlowDao indexCaptitalFlowDao;

    @Autowired
    IUserWechatAccountDao userWechatAccountDao;

    @Value("${feign.client.apiNews.url}")
    private String apiNewsUrl;

    @Value("${feign.client.noticeSearch2.url}")
    private String noticeSearch2;

    @Autowired
    IServiceNoticeClient serviceNoticeClient;

    @Autowired
    ICardService iCardService;

    @Autowired
    IReportTypeChoiceDao iReportTypeChoiceDao;


    private String ossHost = "http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/";

    @Autowired
    ISeccomActivitiesDao seccomActivitiesDao;

    @Autowired
    ISecIndustryNewDao iSecIndustryNewDao;

    @Autowired
    ICompanyManagerService iCompanyManagerService;

    @Autowired
    ISecBasicInfoDao iSecBasicInfoDao;

    @Value("${feign.client.solrReport.url}")
    private String solrReportUrl;

    @Autowired
    IServiceReportClient serviceReportClient;

    @Autowired
    HttpServletRequest servletRequest;


    /**
     * 券商总览
     */
    @Override
    public BrokerOverviewResponse overview(BrokerOverviewRequest brokerOverviewRequest) throws Throwable {

        int orgId = brokerOverviewRequest.getOrgId();

        // 基础信息
        OrganModel orgInfo = getOrgInfo(orgId);

        List<AnalystChanStatusModel> analystChanStatusList = analystChanStatusDao.getAnalystByOrg(orgId);

        // 分析师
        List<String> peoUniCodes = new ArrayList<>();
        if (analystChanStatusList != null && !analystChanStatusList.isEmpty()) {
            for (AnalystChanStatusModel analystChanStatusItem : analystChanStatusList) {
                String peoUniCode = analystChanStatusItem.getPeo_uni_code();
                if (peoUniCode != null && !peoUniCode.isEmpty()) {
                    peoUniCodes.add(peoUniCode);
                }
            }
        }

        // 研究领域
        List<String> directions = new ArrayList<>();
        if (!peoUniCodes.isEmpty()) {
            List<AnalystBasicinfoModel> analystBasicinfoList = analystBasicinfoDao.selectByPeoUniCodeList(peoUniCodes);
            if (analystBasicinfoList != null && !analystBasicinfoList.isEmpty()) {
                for (AnalystBasicinfoModel analystBasicinfoItem : analystBasicinfoList) {
                    String direction = analystBasicinfoItem.getDirection();
                    if (direction != null && !direction.isEmpty()) {
                        String[] directionSplits = direction.split("、");
                        if (directionSplits.length > 0) {
                            for (String directionItem : directionSplits) {
                                String directionTrim = directionItem.trim();
                                if (!directionTrim.isEmpty() && !directions.contains(directionTrim)) {
                                    directions.add(directionTrim);
                                }
                            }
                        }
                    }
                }
            }
        }

        AnalystOverviewDaoModel analystOverview = analystOverviewDao.selectByOrgId(orgId);

        SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        Date beginDate = df2.parse(date +"-01-01 00:00:00");

        int reportCount = hiborDao.countByPublishAndTime(orgInfo.getPublish(), beginDate);
        int honorCount = analystHonorDao.SelectNewWealthHonorCountByOrg(orgInfo.getPublish(), Integer.parseInt(date) - 5 );

        BrokerOverviewResponse brokerOverview = new BrokerOverviewResponse();
        brokerOverview.setOrgName(orgInfo.getPublish());
        brokerOverview.setOrgLogo(ossHost + orgInfo.getLogo());
        brokerOverview.setOrgDirectionCount(directions.size());
        brokerOverview.setOrgAnalystCount(analystOverview != null ? analystOverview.getSecuritiesAnalyst() : 0);
        brokerOverview.setOrgReportCount(reportCount);
        brokerOverview.setOrgHonorCount(honorCount);
        return brokerOverview;
    }

    /**
     * 热力图
     */
    @Override
    public List<BrokerHeatMapItemResponse> heatMap(BrokerHeatMapRequest brokerHeatMapRequest) throws Throwable {

        int orgId = brokerHeatMapRequest.getOrgId();
        int type = brokerHeatMapRequest.getType();

        List<Integer> allowType = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
        }};
        if (!allowType.contains(type)) {
            throw new ValidatorException(BrokerEnumCode.ERROR_HEATMAP_TYPE);
        }
        OrganModel orgInfo = getOrgInfo(orgId);

        // 时间处理
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        Date d = df.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DATE, -1);
        String endTime = df.format(calendar.getTime()) + " 23:59:59";
//        endTime = "2018-06-21 23:59:59";
        calendar.add(Calendar.DATE, 1);
        String beginTime = "";
        if (type == 1) {
            calendar.add(Calendar.YEAR, -100);
            beginTime = df.format(calendar.getTime()) + " 00:00:00";
        } else if (type == 2) {
            calendar.add(Calendar.DATE, -7);
            beginTime = df.format(calendar.getTime()) + " 00:00:00";
        } else if (type == 3) {
            calendar.add(Calendar.MONTH, -1);
            beginTime = df.format(calendar.getTime()) + " 00:00:00";
        } else if (type == 4) {
            calendar.add(Calendar.YEAR, -1);
            beginTime = df.format(calendar.getTime()) + " 00:00:00";
        }

        Date beginTimeDate = df2.parse(beginTime);
        Date endTimeDate = df2.parse(endTime);

        // 获得交易日
        Date beginTradeDate = null;
        Date endTradeDate = null;
        Long secMar = 1003002L;
        if (type == 1) {
            // 取最近交易日
            TransacModel dayTransSac = transacDao.getEndTransDate( secMar, beginTimeDate, endTimeDate);
            if (dayTransSac != null) {
                beginTradeDate = dayTransSac.getEnd_date();
                endTradeDate = beginTradeDate;
            }

        } else {
            // 取区间能用的开始 结束交易日
            TransacModel beginTransac =  transacDao.getBeginTransDate( secMar, beginTimeDate, endTimeDate);
            if (beginTransac != null) {
                beginTradeDate = beginTransac.getEnd_date();
                TransacModel endTransSac = transacDao.getEndTransDate( secMar, beginTradeDate, endTimeDate);
                if (endTransSac != null) {
                    endTradeDate = endTransSac.getEnd_date();
                }
            }
        }

        if (beginTradeDate == null) {
            throw new ServiceException(BrokerEnumCode.ERROR_GET_BEGIN_TRADE_DATE);
        }
        if (endTradeDate == null) {
            throw new ServiceException(BrokerEnumCode.ERROR_GET_END_TRADE_DATE);
        }

        List<HiborGroupByDaoModel> hiborGroupByList = hiborDao.selectByPublishAndTimeGroupByIndustryId(orgInfo.getPublish(), beginTradeDate, new Date(endTradeDate.getTime()+ 86399000));

        List<BrokerHeatMapItemResponse> brokerHeatMap = new ArrayList<>();


        List<String> industryIds = new ArrayList<>();
        if (hiborGroupByList != null && !hiborGroupByList.isEmpty()){
            for (HiborGroupByDaoModel hiborGroupByItem : hiborGroupByList) {
                if(hiborGroupByItem.getIndustryId() != null && !hiborGroupByItem.getIndustryId().isEmpty()) {
                    industryIds.add(hiborGroupByItem.getIndustryId());
                }
            }

            if (!industryIds.isEmpty()) {
                List<AbcIndustryDaoModel> abcIndustryList = abcIndustryDao.selectByInduCodes(industryIds);
                if (abcIndustryList != null && !abcIndustryList.isEmpty()) {

                    // 获得hibor 每条数据industry_id 的顶级 id;
                    Map<String, String> topMap = new HashMap<>();
                    List<String> idsCache = new ArrayList<>();
                    List<String> topIds = new ArrayList<>();
                    for (AbcIndustryDaoModel aItem : abcIndustryList) {
                        String pId = aItem.getParentId();
                        topMap.put(aItem.getInduCode(), pId);
                        // null的 就是顶级id
                        if (pId == null || pId.isEmpty()) {
                            // 记录顶级id
                            if (!topIds.contains(aItem.getInduCode())) {
                                topIds.add(aItem.getInduCode());
                            }
                        } else {
                            // 需要2次处理的
                            idsCache.add(pId);
                        }

                    }

                    if (!idsCache.isEmpty()) {
                        List<AbcIndustryDaoModel> abcIndustryList2 = abcIndustryDao.selectByInduCodes(idsCache);
                        if (abcIndustryList2 != null && !abcIndustryList2.isEmpty()) {
                            for (AbcIndustryDaoModel aItem2 : abcIndustryList2) {
                                String iId2 = aItem2.getInduCode();
                                String pId2 = aItem2.getParentId();
                                if (pId2 == null || pId2.isEmpty()) {
                                    // 原数据是2级 父级就是1级  不用处理了
                                    if (!topIds.contains(iId2)) {
                                        topIds.add(iId2);
                                    }
                                } else {
                                    //原数据是三级 map存的val是2级的 替换成1级的
                                    for (Map.Entry<String, String> mItem : topMap.entrySet()) {
                                        if (mItem.getValue() != null && mItem.getValue().equals(iId2)) {
                                            topMap.replace(mItem.getKey(), pId2);
                                            if (!topIds.contains(pId2)) {
                                                topIds.add(pId2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // 处理完成  topMap  key => val   val为null 空的 key就是顶级id  否则val为顶级id

                    // 顶级id 对应的研报数量
                    Map<String, Integer> reportMap = new HashMap<>();
                    for (HiborGroupByDaoModel hItem2 : hiborGroupByList) {

                        String hIId2 = hItem2.getIndustryId();
                        // 无效id 处理
                        if (hIId2 != null && !hIId2.isEmpty()) {
                            Integer count2 = Integer.valueOf(hItem2.getCount());
                            String topId  = (topMap.get(hIId2) == null || topMap.get(hIId2).isEmpty()) ? hIId2 : topMap.get(hIId2);

                            if (!reportMap.containsKey(topId)) {
                                reportMap.put(topId, count2);
                            } else {
                                Integer val2 = reportMap.get(topId);
                                reportMap.replace(topId , Integer.valueOf( val2 + count2));
                            }
                        }

                    }






//                    Map<String, String> abcIndustryMap = new HashMap<>();
                    Map<String, String> abcInduStryIndexCodeMap = new HashMap<>();
                    List<String> indexCodes = new ArrayList<>();


                    // 顶级领域数据处理
                    List<AbcIndustryDaoModel> abcIndustryList3 = abcIndustryDao.selectByInduCodes(topIds);
                    for (AbcIndustryDaoModel aItem3 : abcIndustryList3) {

                        String iId3 = aItem3.getInduCode();

//                        abcIndustryMap.put(iId3, aItem3.getInduName().trim());

                        String indexCode = aItem3.getIndexCode();
                        if ( indexCode != null && !indexCode.isEmpty() && !abcInduStryIndexCodeMap.containsKey(iId3)) {
                            indexCodes.add(indexCode);
                            abcInduStryIndexCodeMap.put(iId3, indexCode);
                        }
                    }




                    List<IndexBasicInfoModel> basicList = indexBasicInfoDao.getByIndexCodes(indexCodes);
                    Map<Long, List<IndexPriceDayModel>> tradeCacheMap = new HashMap<>();
                    Map<String, Long> indexCodeIndexUniCodeMap = new HashMap<>();
                    Map<Long, Float> indexUniCodeFlowMap = new HashMap<>();
                    List<Long> priceDayIndexUniCodes = new ArrayList<>();



                    if (basicList != null && !basicList.isEmpty()) {

                        for (IndexBasicInfoModel basicItem : basicList) {
                            indexCodeIndexUniCodeMap.put(basicItem.getIndex_code(), basicItem.getIndex_uni_code());
                            priceDayIndexUniCodes.add(basicItem.getIndex_uni_code());
                        }


                        if (!priceDayIndexUniCodes.isEmpty()) {
                            if (beginTradeDate != null && endTradeDate != null) {

                                // 涨跌幅
                                List<IndexPriceDayModel> tradeList = indexPriceDayDao.selectByIndexUniCodesAndTradeDateBeginWithEnd( priceDayIndexUniCodes, beginTradeDate, endTradeDate);
                                if (tradeList != null && !tradeList.isEmpty()) {
                                    // 根据sql 交易按时间先后入list
                                    for (IndexPriceDayModel dayItem : tradeList) {
                                        Long indexUniCode = dayItem.getIndex_uni_code();
                                        if (tradeCacheMap.containsKey(indexUniCode)) {
                                            List<IndexPriceDayModel> indexCodeTradeList = tradeCacheMap.get(indexUniCode);
                                            indexCodeTradeList.add(dayItem);
                                            tradeCacheMap.replace(indexUniCode, indexCodeTradeList);
                                        } else {
                                            List<IndexPriceDayModel> indexCodeTradeList = new ArrayList<>();
                                            indexCodeTradeList.add(dayItem);
                                            tradeCacheMap.put(indexUniCode, indexCodeTradeList);
                                        }
                                    }
                                }

                                // 资金流入
                                List<IndexCaptitalFlowGroupByDaoModel> flowList = indexCaptitalFlowDao.getCountByIndexUniCodeAndDateBeginWithEnd(priceDayIndexUniCodes, beginTimeDate, endTimeDate);
                                if (flowList != null && !flowList.isEmpty()) {
                                    for (IndexCaptitalFlowGroupByDaoModel flowItem : flowList) {
                                        indexUniCodeFlowMap.put(flowItem.getIndexUniCode(), flowItem.getSum());
                                    }
                                }

                            }
                        }
                    }




                    for (AbcIndustryDaoModel aItem4 : abcIndustryList3) {
                        BrokerHeatMapItemResponse brokerHeatMapItem = new BrokerHeatMapItemResponse();

                        String iId4 = aItem4.getInduCode();
                        brokerHeatMapItem.setIndustryCode(iId4);
                        brokerHeatMapItem.setIndustryName(aItem4.getInduName());
                        brokerHeatMapItem.setReportCount(reportMap.containsKey(iId4) ? reportMap.get(iId4) : 0);

                        if (abcInduStryIndexCodeMap.containsKey(iId4)) {
                            String indexCode = abcInduStryIndexCodeMap.get(iId4);
                            if (indexCodeIndexUniCodeMap.containsKey(indexCode)) {
                                Long indexUniCode = indexCodeIndexUniCodeMap.get(indexCode);
                                // 涨跌幅计算
                                if (tradeCacheMap.containsKey(indexUniCode)) {
                                    List<IndexPriceDayModel> tradeList = tradeCacheMap.get(indexUniCode);
                                    if (tradeList != null && !tradeList.isEmpty()) {


                                        // 日记录只会有一条
                                        if (type == 1){
                                            IndexPriceDayModel trade = tradeList.get(0);
                                            brokerHeatMapItem.setIndustryPerformance(trade.getDiffer_range().floatValue());

                                        } else {
                                            float preTrade = 0L;
                                            float endTrade = 0L;
                                            // 只计算有开始 结束2个交易日的数据
                                            if (tradeList.size() == 2) {
                                                preTrade = tradeList.get(0).getPre_close().floatValue();
                                                endTrade = tradeList.get(1).getClose().floatValue();
                                            }
                                            if (preTrade > 0 && endTrade > 0) {

                                                float  f = (endTrade - preTrade) / preTrade ;
                                                BigDecimal b  =   new  BigDecimal(f);
                                                float   f1   =  b.setScale(4,  BigDecimal.ROUND_HALF_UP).floatValue();
                                                brokerHeatMapItem.setIndustryPerformance( f1);
                                            }
                                        }

                                    }
                                }
                                // 资金流入赋予
                                if (indexUniCodeFlowMap.containsKey(indexUniCode)) {
                                    brokerHeatMapItem.setMoneyFlow(indexUniCodeFlowMap.get(indexUniCode));
                                }
                            }
                        }

                        brokerHeatMap.add(brokerHeatMapItem);

//                        String industryId = hiborGroupByItem.getIndustryId();
//                        if (industryId != null &&
//                                !industryId.isEmpty() &&
//                                abcIndustryMap.containsKey(industryId)) {
//                            BrokerHeatMapItemResponse brokerHeatMapItem = new BrokerHeatMapItemResponse();
//                            brokerHeatMapItem.setIndustryCode(industryId);
//                            brokerHeatMapItem.setIndustryName(abcIndustryMap.get(industryId));
//                            brokerHeatMapItem.setReportCount(hiborGroupByItem.getCount());
//
//
//
//                            if (abcInduStryIndexCodeMap.containsKey(industryId)) {
//                                String indexCode = abcInduStryIndexCodeMap.get(industryId);
//                                if (indexCodeIndexUniCodeMap.containsKey(indexCode)) {
//                                    Long indexUniCode = indexCodeIndexUniCodeMap.get(indexCode);
//                                    // 涨跌幅计算
//                                    if (tradeCacheMap.containsKey(indexUniCode)) {
//                                        List<IndexPriceDayModel> tradeList = tradeCacheMap.get(indexUniCode);
//                                        if (tradeList != null && !tradeList.isEmpty()) {
//                                            float preTrade = 0L;
//                                            float endTrade = 0L;
//
//                                            // 日记录只会有一条
//                                            if (type == 1){
//                                                IndexPriceDayModel trade = tradeList.get(0);
//                                                preTrade = trade.getPre_close().floatValue();
//                                                endTrade = trade.getClose().floatValue();
//                                            } else {
//                                                // 只计算有开始 结束2个交易日的数据
//                                                if (tradeList.size() == 2) {
//                                                    preTrade = tradeList.get(0).getPre_close().floatValue();
//                                                    endTrade = tradeList.get(1).getClose().floatValue();
//                                                }
//                                            }
//
//
//
//                                            if (preTrade > 0 && endTrade > 0) {
//
//                                                float  f = (endTrade - preTrade) / preTrade ;
//                                                BigDecimal b  =   new  BigDecimal(f);
//                                                float   f1   =  b.setScale(4,  BigDecimal.ROUND_HALF_UP).floatValue();
//                                                brokerHeatMapItem.setIndustryPerformance( f1);
//                                            }
//                                        }
//                                    }
//                                    // 资金流入赋予
//                                    if (indexUniCodeFlowMap.containsKey(indexUniCode)) {
//                                        brokerHeatMapItem.setMoneyFlow(indexUniCodeFlowMap.get(indexUniCode));
//                                    }
//                                }
//                            }
//
//                            brokerHeatMap.add(brokerHeatMapItem);
//                        }
                    }
                }

            }

        }

        return brokerHeatMap;
    }


    /**
     * 资讯
     */
    @Override
    public BrokerNewsResponse news(BrokerNewsRequest brokerNewsRequest) throws Throwable {

        int orgId = brokerNewsRequest.getOrgId();
        OrganModel orgInfo = getOrgInfo(orgId);

        BrokerNewsResponse brokerNews = new BrokerNewsResponse();
        List<BrokerNewsItemResponse> newestList = new ArrayList<>();
        List<BrokerNewsItemResponse> newsList = new ArrayList<>();
        List<BrokerNewsItemResponse> noticeList = new ArrayList<>();
        List<BrokerNewsItemResponse> weiboList = new ArrayList<>();
        List<BrokerNewsItemResponse> wechatPublicList = new ArrayList<>();


        List<String> newestListIds = new ArrayList<>();

        //新闻
        List<ApiNewsDataItemResponse> newsDataItemList = null;
        try {
            Map<String, String> newsRequest = new HashMap<>();
            newsRequest.put("keyword", orgInfo.getPublish());
            newsRequest.put("offset", "0");
            newsRequest.put("limit", "20");
            newsRequest.put("prior", "");
            newsRequest.put("channel", "");
            newsRequest.put("core_name", "core_news");
            newsRequest.put("single", "true");

            String apiNewsRet = HttpUtil.post(apiNewsUrl, newsRequest, null);
            if (apiNewsRet != null && !apiNewsRet.isEmpty()) {
                ApiNewsResponse apiNewsRetObject = JSON.parseObject(apiNewsRet, ApiNewsResponse.class);
                if (apiNewsRetObject != null && apiNewsRetObject.getErr_code() == 0) {
                    newsDataItemList = apiNewsRetObject.getData().getItem();
                }
            }
        } catch (Exception e) {
            logger.error("broker news get news error : " + e.getMessage());
        }

        if (newsDataItemList != null && !newsDataItemList.isEmpty()) {
            for (ApiNewsDataItemResponse item : newsDataItemList) {
                BrokerNewsItemResponse brokerNewsItem = new BrokerNewsItemResponse();
                brokerNewsItem.setId(item.getId());
                brokerNewsItem.setTitle(item.getTitle());
                brokerNewsItem.setType("news");
                brokerNewsItem.setSource(item.getSource_name());
                brokerNewsItem.setUrl(item.getUrl());
                brokerNewsItem.setTime(item.getTime());
                newsList.add(brokerNewsItem);
                newestList.add(brokerNewsItem);
                newestListIds.add(item.getId());
            }
        }

        // 公告
        List<NoticeItem> noticeDataItemList = null;
        try {
//            ClientNoticeRequest request = new ClientNoticeRequest();
//            request.setKeyword_filter(orgInfo.getPublish());
//            request.setOffset(0);
//            request.setLimit(20);
//            ClientNoticeResponse noticeRet = serviceNoticeClient.report(request);
            Map<String,String> req=new HashMap<>();
            req.put("keyword",orgInfo.getPublish());
            req.put("Offset","0");
            req.put("limit","20");
            String noticeRet = HttpUtil.post(noticeSearch2, req, null);
            if (noticeRet != null ) {
                ClientNoticeResponse notice = JSON.parseObject(noticeRet, ClientNoticeResponse.class);
                if (notice != null && notice.getErr_code() == 0) {
                    noticeDataItemList = notice.getData().getItem();
                }
            }
        } catch (Exception e) {
            logger.error("broker news get notice error : " + e.getMessage());
        }

        if (noticeDataItemList != null && !noticeDataItemList.isEmpty()) {
            for (NoticeItem item : noticeDataItemList) {
                BrokerNewsItemResponse brokerNewsItem = new BrokerNewsItemResponse();
                brokerNewsItem.setId(item.getSrc_id());
                brokerNewsItem.setTitle(item.getTitle());
                brokerNewsItem.setType("notice");
                brokerNewsItem.setSource(item.getStockname());
                brokerNewsItem.setUrl("");
                brokerNewsItem.setTime(item.getPublish_at());
                noticeList.add(brokerNewsItem);
                newestList.add(brokerNewsItem);
            }
        }

        // 微博 不做

        // 公众号
        List<UserWechatAccountModel> wechatAccountList = userWechatAccountDao.selectByUserIdAndType(String.valueOf(orgId), "券商");
        if (wechatAccountList != null && !wechatAccountList.isEmpty()) {
            List<ApiNewsDataItemResponse> newsWechatDataItemList = null;
            try {
                StringBuilder names = new StringBuilder();
                for (UserWechatAccountModel wechatItem : wechatAccountList) {
                    // map string长度限制
                    if ((names.length() + wechatItem.getAccount_name().trim().length()) >= 720) {
                        break;
                    }
                    names.append(" ").append("\"").append(wechatItem.getAccount_name().trim()).append("\"");

                }

                Map<String, String> newsRequest = new HashMap<>();
                String sourceEncode = "$source_name:(" + URLEncoder.encode(names.toString().substring(1),"UTF-8")+ ")";
//                String kwEncode = URLEncoder.encode(sourceEncode, "UTF-8");
                newsRequest.put("keyword", sourceEncode);
                newsRequest.put("offset", "0");
                newsRequest.put("limit", "5");
                newsRequest.put("prior", "time");
                newsRequest.put("channel", "");
                newsRequest.put("core_name", "core_news");
                newsRequest.put("single", "true");

                String apiNewsRet = HttpUtil.get(apiNewsUrl, newsRequest, null);
                if (apiNewsRet != null && !apiNewsRet.isEmpty()) {
                    ApiNewsResponse apiNewsRetObject = JSON.parseObject(apiNewsRet, ApiNewsResponse.class);
                    if (apiNewsRetObject != null && apiNewsRetObject.getErr_code() == 0) {
                        newsWechatDataItemList = apiNewsRetObject.getData().getItem();
                    }
                }
            } catch (UnsupportedEncodingException e) {

            } catch (Exception e) {
                logger.error("broker news get wechat news error : " + e.getMessage());
            }

            if (newsWechatDataItemList != null && !newsWechatDataItemList.isEmpty()) {
                for (ApiNewsDataItemResponse item : newsWechatDataItemList) {
                    BrokerNewsItemResponse brokerNewsItem  = new BrokerNewsItemResponse();
                    brokerNewsItem.setId(item.getId());
                    brokerNewsItem.setTitle(item.getTitle());
                    brokerNewsItem.setType("wechat");
                    brokerNewsItem.setSource(item.getSource_name());
                    brokerNewsItem.setUrl(item.getUrl());
                    brokerNewsItem.setTime(item.getTime());
                    wechatPublicList.add(brokerNewsItem);
                    if (!newestListIds.contains(item.getId())) {
                        newestList.add(brokerNewsItem);
                    }
                }
            }
        }




        // 最新
        Collections.sort(newestList, new Comparator<BrokerNewsItemResponse>() {
            @Override
            public int compare(BrokerNewsItemResponse o1, BrokerNewsItemResponse o2) {
                return o2.getTime() - o1.getTime();
            }
        });

        brokerNews.setNewest(newestList);
        brokerNews.setNews(newsList);
        brokerNews.setNotice(noticeList);
        brokerNews.setWeibo(weiboList);
        brokerNews.setWechatPublic(wechatPublicList);


        return brokerNews;
    }

    @Override
    public List<ResearchIndustryBasicInfoBO> researchIndustryList(String broker_name) throws Exception {
        List<ResearchIndustryBasicInfoBO> researchIndustryBasicInfoResponsesList = new ArrayList<>();
        List<Map<String, Object>> swIndustryList = indexBasicInfoDao.selectSWIndustryList();
        List<Map<String, Object>> induInfoList = new ArrayList<>();
        for (Map<String, Object> swIndustry : swIndustryList) {
            Long index_uni_code = Long.parseLong(swIndustry.get("index_uni_code").toString());
            String index_name = swIndustry.get("index_sname").toString().trim();
            Map<String, Object> induInfo = new HashMap<>();
            induInfo.put("indu_name", index_name);
            induInfo.put("indu_uni_code", index_uni_code);
            if (StringUtils.isNotEmpty(broker_name)) {
                List<String> abcCodes = iSecIndustryNewDao.getAbcCodeByInduNameAndAbcCode(null, index_name);
                if (abcCodes.size() > 0) {
                    Map<String, String> reportRequest = new HashMap<>();
                    reportRequest.put("keyword", "ALL");
                    reportRequest.put("offset", "0");
                    reportRequest.put("limit", "0");

                    String induCode = IndustryCodeMap.getIndustryCode(index_name);
                    if (induCode.contains(",")) {
                        induCode = induCode.substring(0, induCode.indexOf(","));
                    }

                    reportRequest.put("industry_name", induCode);
                    reportRequest.put("source", broker_name);
//                    reportRequest.put("author", analystName);
                    String apiReportRet = HttpUtil.post(solrReportUrl, reportRequest, null);

                    if (apiReportRet != null && !apiReportRet.isEmpty()) {
                        JSONObject data = JSONObject.parseObject(apiReportRet).getJSONObject("data");
                        if (data != null) {
                            JSONArray reportItems = data.getJSONArray("item");
                            induInfo.put("reportCount", Integer.parseInt(data.get("total_count").toString()));
                        }
                    }
                } else {
                    induInfo.put("reportCount", 0);
                }
            }

            induInfoList.add(induInfo);
        }

        if (StringUtils.isNotEmpty(broker_name)) {
            induInfoList.sort((Map<String, Object> o1, Map<String, Object> o2) -> (Integer) o2.get("reportCount") - (Integer) o1.get("reportCount"));
        }

        for (Map<String, Object> item : induInfoList) {
            ResearchIndustryBasicInfoBO researchIndustryBasicInfoBO = new ResearchIndustryBasicInfoBO();
            researchIndustryBasicInfoBO.setIndu_uni_code((Long) item.get("indu_uni_code"));
            researchIndustryBasicInfoBO.setIndu_name(item.get("indu_name").toString());
            researchIndustryBasicInfoResponsesList.add(researchIndustryBasicInfoBO);
        }
        return researchIndustryBasicInfoResponsesList;
    }

    @Override
    public List<StarAnalystInfoBO> starAnalystList(Long org_uni_code, String indu_name) throws Exception {
        List<String> peoUniCodeList = analystChanStatusDao.selectPeoUniCodeByOrgUniCode(org_uni_code);
        if (StringUtil.isEmpty(peoUniCodeList)) {
            return null;
        }

        List<AnalystHonorInfoBO> analystHonorInfoBOList = analystHonorDao.selectAnalystHonorRankingByPeoUniCodeList(peoUniCodeList, StringUtil.isEmpty(indu_name) ? null : ResearchIndustryMap.getResearchIndustry(indu_name.replace("申万","")));
        if (StringUtil.isEmpty(analystHonorInfoBOList)) {
            return null;
        }

        List<StarAnalystInfoBO> starAnalystInfoBOList = new ArrayList<>();
        List<StarAnalystInfoBO> starAnalystInfoBORankingIsNullList = new ArrayList<>();
        for (AnalystHonorInfoBO analystHonorInfoBO : analystHonorInfoBOList) {
            StarAnalystInfoBO starAnalystInfoBO = new StarAnalystInfoBO();
            String peo_uni_code = analystHonorInfoBO.getPeo_uni_code();
            AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peo_uni_code);
            starAnalystInfoBO.setPeo_uni_code(peo_uni_code);
            starAnalystInfoBO.setName(analystBasicinfoModel.getName());
            starAnalystInfoBO.setImage(analystBasicinfoModel.getImage());
            starAnalystInfoBO.setIndu_name(indu_name);

            if (!analystHonorInfoBO.getHonor().equals("新财富最佳分析师")) {
                starAnalystInfoBORankingIsNullList.add(starAnalystInfoBO);
                continue;
            }

            if (StringUtil.isEmpty(analystHonorInfoBO.getRanking())) {
                starAnalystInfoBORankingIsNullList.add(starAnalystInfoBO);
                continue;
            }

            starAnalystInfoBO.setHonor(analystHonorInfoBO.getTime() + "新财富NO." + analystHonorInfoBO.getRanking());
            starAnalystInfoBO.setIsPrized(1);
            starAnalystInfoBOList.add(starAnalystInfoBO);
        }

        for (StarAnalystInfoBO starAnalystInfoBOItem : starAnalystInfoBORankingIsNullList) {
            starAnalystInfoBOItem.setIsPrized(0);
            starAnalystInfoBOList.add(starAnalystInfoBOItem);
        }

        return starAnalystInfoBOList;
    }

    @Override
    public List<StarAnalystInfoBO> starAnalystList2(Long org_uni_code, String indu_name) throws Exception {

        List<AnalystHonorInfoBO> analystHonorInfoBOList = analystHonorDao.selectAnalystHonorRankingByPeoUniCodeList2(org_uni_code, StringUtil.isEmpty(indu_name) ? null : ResearchIndustryMap.getResearchIndustry(indu_name.replace("申万","")));
        if (StringUtil.isEmpty(analystHonorInfoBOList)) {
            return null;
        }

        List<StarAnalystInfoBO> starAnalystInfoBOList = new ArrayList<>();
        List<StarAnalystInfoBO> starAnalystInfoBORankingIsNullList = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(50);
        CountDownLatch countDownLatch = new CountDownLatch(analystHonorInfoBOList.size());
        for (AnalystHonorInfoBO analystHonorInfoBO : analystHonorInfoBOList) {
            executor.submit(new Runnable() {
                public void run() {
                    try{
                        StarAnalystInfoBO starAnalystInfoBO = new StarAnalystInfoBO();
                        String peo_uni_code = analystHonorInfoBO.getPeo_uni_code();
                        AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peo_uni_code);
                        starAnalystInfoBO.setPeo_uni_code(peo_uni_code);
                        starAnalystInfoBO.setName(analystBasicinfoModel.getName());
                        starAnalystInfoBO.setImage(analystBasicinfoModel.getImage());
                        starAnalystInfoBO.setIndu_name(indu_name);

                        if (!analystHonorInfoBO.getHonor().equals("新财富最佳分析师")) {
                            starAnalystInfoBORankingIsNullList.add(starAnalystInfoBO);
                        }

                        if (StringUtil.isEmpty(analystHonorInfoBO.getRanking())) {
                            starAnalystInfoBORankingIsNullList.add(starAnalystInfoBO);
                        }

                        if(analystHonorInfoBO.getHonor().equals("新财富最佳分析师") && !StringUtil.isEmpty(analystHonorInfoBO.getRanking())){
                            starAnalystInfoBO.setHonor(analystHonorInfoBO.getTime() + "新财富NO." + analystHonorInfoBO.getRanking());
                            starAnalystInfoBO.setIsPrized(1);
                            starAnalystInfoBOList.add(starAnalystInfoBO);
                        }
                        countDownLatch.countDown();
                    }catch (Exception e){
                        logger.error(e.toString());
                    }
                }
            });
        }

        executor.shutdown();
        countDownLatch.await();

        for (StarAnalystInfoBO starAnalystInfoBOItem : starAnalystInfoBORankingIsNullList) {
            starAnalystInfoBOItem.setIsPrized(0);
            starAnalystInfoBOList.add(starAnalystInfoBOItem);
        }

        return starAnalystInfoBOList;
    }

    @Override
    public List<CardAnalystReportStocksBO> analystLatestResearchStocks(String peo_uni_code) throws Exception {
        String name = analystBasicinfoDao.selectByPeoUniCode(peo_uni_code).getName();
        String org_name = analystChanStatusDao.selectByPeoUniCode(peo_uni_code).getOrg_sname();
        return iCardService.buildAnalystReportStocks(name, org_name, 1,3);
    }

    @Override
    public List<RepostTypeModel> reportType() throws Exception {
        return iReportTypeChoiceDao.selectReportType();
    }

    @Override
    public List<ReportCountBO> reportCount(Long org_uni_code) throws Exception {
        List<ReportCountBO> reportCountBOList = new ArrayList<>();
        List<RepostTypeModel> repostTypeModelList = iReportTypeChoiceDao.selectReportType();

        for (RepostTypeModel repostTypeModel : repostTypeModelList) {
            ReportCountBO reportCountBO = new ReportCountBO();
            String type_id = repostTypeModel.getType_id();
            List<String> typeIdList = iReportTypeChoiceDao.selectSecondLevelReportTypeByParentId(type_id);
            typeIdList.add(type_id);
            OrganModel organModel = organDao.selectByOrgId(Integer.parseInt(org_uni_code.toString()));
            Integer count = hiborDao.selectCountByCategoryIdList(typeIdList, organModel.getPublish(), TimeUtil.getBeginDayOfYear());
            reportCountBO.setType_id(type_id);
            reportCountBO.setType_name(repostTypeModel.getType_name());
            reportCountBO.setCount(count);
            reportCountBOList.add(reportCountBO);
        }

        return reportCountBOList;
    }

    @Override
    public List<LatestReportBO> latestReport(Long org_uni_code, String type_id) throws Exception {
        List<LatestReportBO> latestReportBOList = new ArrayList<>();
        OrganModel organModel = organDao.selectByOrgId(Integer.parseInt(org_uni_code.toString()));
        List<String> typeIdList = iReportTypeChoiceDao.selectSecondLevelReportTypeByParentId(type_id);
        typeIdList.add(type_id);
        List<HiborModel> hiborModelList = hiborDao.selectLatestReportByOrgUniCodeAndTypeId(organModel.getPublish(), typeIdList);
        for (HiborModel hiborModel : hiborModelList) {
            LatestReportBO latestReportBO = new LatestReportBO();
            latestReportBO.setId(hiborModel.getId());
            latestReportBO.setFileType(hiborModel.getFiletype());
            latestReportBO.setSource(hiborModel.getSource());
            latestReportBO.setTime(hiborModel.getTime());
            latestReportBO.setTitle(hiborModel.getTitle());

            String source = iReportTypeChoiceDao.selectTypeNameByTypeId(type_id);
            latestReportBO.setSource(source);
            latestReportBO.setType("report");
            latestReportBO.setPageCounter(hiborModel.getFile_pages());

            String authors = hiborModel.getAuthor();
            if (!StringUtil.isEmpty(authors)) {
                String[] authorStrs = null;
                if (authors.contains(",")) {
                    authorStrs = authors.split(",");
                } else {
                    authorStrs = new String[]{authors};
                }

                JSONArray authorArray = new JSONArray();
                List<String> peoUniCodeList = analystChanStatusDao.selectPeoUniCodeByOrgUniCode(org_uni_code);
                for (String author : authorStrs) {
                    JSONObject authorJb = new JSONObject();
                    authorJb.put("name", author);
                    String peo_uni_code = analystBasicinfoDao.selectUniquePeoUniCodeByNameAndPeoUniCodeList(peoUniCodeList, author);
                    List<AnalystHonorModel> analystHonorModelList = analystHonorDao.judegeAnalystIsPrized(peo_uni_code);
                    if (analystHonorModelList.size() > 0) {
                        authorJb.put("isPrized", 1);
                    } else {
                        authorJb.put("isPrized", 0);
                    }
                    authorArray.add(authorJb);
                }
                latestReportBO.setAuthor(authorArray);
            }

            latestReportBOList.add(latestReportBO);
        }

        return latestReportBOList;
    }

    @Override
    public List<StarAnalystInfoBO> otherStarAnalystList(Long org_uni_code, String type_id) throws Exception {
        List<String> peoUniCodeList = analystChanStatusDao.selectPeoUniCodeByOrgUniCode(org_uni_code);
        if (StringUtil.isEmpty(peoUniCodeList)) {
            return null;
        }

        List<String> nameList = analystBasicinfoDao.selectAnalystNameByPeoUniCodeList(peoUniCodeList);


        OrganModel organModel = organDao.selectByOrgId(Integer.parseInt(org_uni_code.toString()));
        List<String> typeIdList = iReportTypeChoiceDao.selectSecondLevelReportTypeByParentId(StringUtil.isEmpty(type_id) ? null : type_id);
        if (StringUtil.isEmpty(type_id)) {
            typeIdList.add("S004001");
            typeIdList.add("S004021");
            typeIdList.add("S004022");
            typeIdList.add("S004019");
            typeIdList.add("S004009");
            typeIdList.add("S004020");
            typeIdList.add("S004005");
            typeIdList.add("S004006");
            typeIdList.add("S004007");
            typeIdList.add("S004018");
            typeIdList.add("S004014");
            typeIdList.add("S004016");
            typeIdList.add("S004015");
            typeIdList.add("S004011");
            typeIdList.add("S004012");
            typeIdList.add("S004013");
        } else {
            typeIdList.add(type_id);
        }

        List<String> authorList = hiborDao.selectAuthorByPublishAndCategoryIdListAndNameList(organModel.getPublish(), typeIdList, nameList);
        List<String> author_List = new ArrayList<>();
        for (String authors : authorList) {
            if (StringUtil.isEmpty(authors)) {
                continue;
            }

            if (authors.contains(",")) {
                String[] authorItems = authors.split(",");
                for (String item : authorItems) {
                    author_List.add(item);
                }
            } else {
                author_List.add(authors);
            }
        }

        List<String> peo_uni_code_list = new ArrayList<>();
        peo_uni_code_list = analystBasicinfoDao.selectPeoUniCodeByNameListAndPeoUniCodeList(peoUniCodeList, author_List);

        if (StringUtil.isEmpty(peo_uni_code_list)) {
            return null;
        }

        List<String> directionList = new ArrayList<>();
        directionList.add("金融工程");
        directionList.add("投资策略");
        directionList.add("衍生品研究");
        directionList.add("宏观经济");
        directionList.add("固定收益");
        directionList.add("策略研究");
        directionList.add("港股策略");
        directionList.add("中小市值研究");
        directionList.add("白金分析师");
        directionList.add("杰出研究领袖");
        directionList.add("最具潜力");
        directionList.add("最受保险欢迎");
        directionList.add("最受公募欢迎");
        directionList.add("最后私募欢迎");

        List<AnalystHonorInfoBO> analystHonorInfoBOList = analystHonorDao.selectAnalystHonorRankingByPeoUniCodeList(peo_uni_code_list, directionList);
        if (StringUtil.isEmpty(analystHonorInfoBOList)) {
            return null;
        }

        List<StarAnalystInfoBO> starAnalystInfoBOList = new ArrayList<>();
        List<StarAnalystInfoBO> starAnalystInfoBORankingIsNullList = new ArrayList<>();
        for (AnalystHonorInfoBO analystHonorInfoBO : analystHonorInfoBOList) {
            StarAnalystInfoBO starAnalystInfoBO = new StarAnalystInfoBO();
            String peo_uni_code = analystHonorInfoBO.getPeo_uni_code();
            AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peo_uni_code);
            starAnalystInfoBO.setPeo_uni_code(peo_uni_code);
            starAnalystInfoBO.setName(analystBasicinfoModel.getName());
            starAnalystInfoBO.setImage(analystBasicinfoModel.getImage());
            starAnalystInfoBO.setIndu_name(iReportTypeChoiceDao.selectTypeNameByTypeId(type_id));

            if (!analystHonorInfoBO.getHonor().equals("新财富最佳分析师")) {
                starAnalystInfoBORankingIsNullList.add(starAnalystInfoBO);
                continue;
            }

            if (StringUtil.isEmpty(analystHonorInfoBO.getRanking())) {
                starAnalystInfoBORankingIsNullList.add(starAnalystInfoBO);
                continue;
            }
            starAnalystInfoBO.setHonor(analystHonorInfoBO.getTime() + "新财富NO." + analystHonorInfoBO.getRanking());
            starAnalystInfoBO.setIsPrized(1);
            starAnalystInfoBOList.add(starAnalystInfoBO);
        }

        for (StarAnalystInfoBO starAnalystInfoBOItem : starAnalystInfoBORankingIsNullList) {
            starAnalystInfoBOItem.setIsPrized(0);
            starAnalystInfoBOList.add(starAnalystInfoBOItem);
        }

        return starAnalystInfoBOList;
    }

    /**
     * 券商基本信息
     */
    private OrganModel getOrgInfo(int orgId) throws Throwable {

        if (orgId <= 0) {
            throw new ValidatorException(BrokerEnumCode.EMPTY_ORG_ID);
        }

        OrganModel orgInfo = organDao.selectByOrgId(orgId);
        if (orgInfo == null) {
            throw new ValidatorException(BrokerEnumCode.ERROR_ORG_ID);
        }
        return orgInfo;
    }

    @Override
    public Map<String, Object> getActivities(Date startDate, Date endDate, Integer org_uni_code) throws Exception {
        OrganModel organModel = organDao.selectByOrgId(org_uni_code);
        List<SeccomActivities> list = seccomActivitiesDao.getActivitiesByTimeAndOrgId(startDate, endDate, organModel.getPublish());

        if (list == null || list.size() == 0) {
            return null;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("taskList", list);

        List<String> taskTime = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < list.size(); i++) {
            SeccomActivities seccomActivities = list.get(i);
            if (seccomActivities.getEndtime() == null) {
                if (!taskTime.contains(sdf.format(seccomActivities.getStarttime()))) {
                    taskTime.add(sdf.format(seccomActivities.getStarttime()));
                }
            } else {
                Date startTime = seccomActivities.getStarttime().getTime() < startDate.getTime() ? startDate : seccomActivities.getStarttime();
                Date endTime = seccomActivities.getEndtime().getTime() < endDate.getTime() ? seccomActivities.getEndtime() : endDate;
                if (!taskTime.contains(sdf.format(startTime))) {
                    taskTime.add(sdf.format(startTime));
                }

                while (true) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startTime);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    if (calendar.getTime().getTime() < endTime.getTime() && !taskTime.contains(sdf.format(calendar.getTime()))) {
                        taskTime.add(sdf.format(calendar.getTime()));
                    } else {
                        break;
                    }
                }

                if (!taskTime.contains(sdf.format(endDate))) {
                    taskTime.add(sdf.format(endDate));
                }

            }
        }

        result.put("taskTime", taskTime);

        return result;
    }

    @Override
    public Map<String, Object> getAnalystFlow(BigInteger org_uni_code) throws Exception {
        Map<String, Object> result = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        List<String> dateList = new ArrayList<>();

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 4; i++) {
            calendar.setTime(now);
            calendar.add(Calendar.MONTH, -i);
            dateList.add(0, sdf.format(calendar.getTime()));
        }

        // 设置开始时间
        calendar.setTime(now);
        calendar.add(Calendar.MONTH, -3);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND, 0);
        long startTime = calendar.getTime().getTime();

        // 设置结束时间
        calendar.setTime(now);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND, 0);
        long endTime = calendar.getTime().getTime();

        List<String> peoUniCodeList = analystChanStatusDao.getAllAnalystByOrg(Integer.parseInt(org_uni_code.toString()));

        // 四个月每个月的入职人数
        List<Integer> entryCount = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            entryCount.add(0);
        }

        // 四个月每个月的离职人数
        List<Integer> dimissionCount = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            dimissionCount.add(0);
        }

        // 四个月内具体的入职离职人员详细信息
        List<Map<String, Object>> persionInfoList = new ArrayList<>();

        for (String peoUniCode : peoUniCodeList) {
            List<AnalystChanStatusModel> analystChanStatusList = analystChanStatusDao.getResumesByPeoUniCode(peoUniCode);
            Date entryTime = null;
            Date dimissionTime = null;
            for (int i = 0; i < analystChanStatusList.size(); i++) {
                AnalystChanStatusModel analystChanStatusModel = analystChanStatusList.get(i);
                if (Long.parseLong(org_uni_code.toString()) != analystChanStatusModel.getOrg_uni_code()) {
                    continue;
                }

                if ("正常".equals(analystChanStatusModel.getCertificate_status()) ||
                        "机构内变更".equals(analystChanStatusModel.getCertificate_status())) {
                    if (entryTime == null) {
                        entryTime = analystChanStatusModel.getCertificate_issued_date();
                    }
                } else {
                    if (entryTime == null) {
                        entryTime = analystChanStatusModel.getCertificate_issued_date();
                    }
                    if (dimissionTime == null) {
                        if (i == analystChanStatusList.size() - 1) {
                            dimissionTime = analystChanStatusModel.getUpdatetime();
                        } else {
                            dimissionTime = analystChanStatusList.get(i + 1).getCertificate_issued_date();
                        }
                    }
                }

                if (entryTime != null && dimissionTime != null) {
                    if (entryTime.getTime() >= startTime && entryTime.getTime() < endTime) {
                        int index = dateList.indexOf(sdf.format(entryTime));
                        if (index != -1) {
                            entryCount.set(index, entryCount.get(index) + 1);

                            AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peoUniCode);
                            Map<String, Object> map = new HashMap<>();
                            map.put("date", entryTime.getTime());
                            map.put("name", analystBasicinfoModel.getName());
                            map.put("status", "入职");
                            persionInfoList.add(map);
                        }
                    }

                    if (dimissionTime.getTime() >= startTime && dimissionTime.getTime() < endTime) {
                        int index = dateList.indexOf(sdf.format(dimissionTime));
                        if (index != -1) {
                            dimissionCount.set(index, dimissionCount.get(index) + 1);

                            AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peoUniCode);
                            Map<String, Object> map = new HashMap<>();
                            map.put("date", dimissionTime.getTime());
                            map.put("name", analystBasicinfoModel.getName());
                            map.put("status", "离职");
                            persionInfoList.add(map);
                        }
                    }

                    entryTime = null;
                    dimissionTime = null;
                }

            }

            if (entryTime != null && dimissionTime == null) {
                if (entryTime.getTime() >= startTime && entryTime.getTime() < endTime) {
                    int index = dateList.indexOf(sdf.format(entryTime));
                    if (index != -1) {
                        entryCount.set(index, entryCount.get(index) + 1);

                        AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peoUniCode);
                        Map<String, Object> map = new HashMap<>();
                        map.put("date", entryTime.getTime());
                        map.put("name", analystBasicinfoModel.getName());
                        map.put("status", "入职");
                        persionInfoList.add(map);
                    }
                }
            }
        }

        persionInfoList.sort((a, b) -> ((Long)b.get("date")).compareTo((Long)a.get("date")));

        result.put("datetime", dateList);
        result.put("enrtyCount", entryCount);
        result.put("dimissionCount", dimissionCount);
        result.put("persionInfoList", persionInfoList);

        return result;
    }

    @Override
    public List<Map<String, Object>> getInduStock(String indu_name, String type, String broker_name, Integer limit) throws Exception {
        if (StringUtil.isEmpty(indu_name)) {
            indu_name = null;
        }

        String prior = null;
        if (type.equals("LATEST")) {
            prior = "create_at DESC";
        } else if (type.equals("MOST")) {
            prior = "researchTime DESC";
        } else {
            prior = "id ASC";
        }

        List<String> abcCodes = iSecIndustryNewDao.getAbcCodeByInduNameAndAbcCode(null, indu_name);
        List<Map<String, Object>> reportInfoList = hiborDao.getReportByAbcCodeAndPublishAndType(abcCodes, broker_name, prior, limit);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> reportInfo : reportInfoList) {
            Map<String, Object> item = new HashMap<>();

            String abc_code = reportInfo.get("stockcode").toString();
            Date last_time = (Date) reportInfo.get("create_at");
            Long researchTime = (Long) reportInfo.get("researchTime");
            item.put("abc_code", abc_code);
            item.put("last_time", last_time.getTime());
            item.put("researchTime", researchTime);

            SecBasicInfoModel secBasicInfoModel = iSecBasicInfoDao.selectByAbcCode(abc_code);
            item.put("sec_name", secBasicInfoModel.getSec_name());
            item.put("com_uni_code", secBasicInfoModel.getCom_uni_code());
            item.put("sec_uni_code", secBasicInfoModel.getSec_uni_code());

            List<String> currentSharePriceInfoList = iCompanyManagerService.getStockRealTimePrice(abc_code);
//            CardAnalystReportStocksBO analystReportStocksBO = new CardAnalystReportStocksBO();
            BigDecimal current_price = null;
            BigDecimal differ_range = null;
            BigDecimal differ = null;
            if (currentSharePriceInfoList.size() >= 33){
                if(!currentSharePriceInfoList.get(1).isEmpty()){
                    current_price = new BigDecimal(currentSharePriceInfoList.get(1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                }

                if(!currentSharePriceInfoList.get(3).isEmpty()){
                    differ_range = new BigDecimal(currentSharePriceInfoList.get(3)).setScale(4, BigDecimal.ROUND_HALF_UP);
                }

                if (!currentSharePriceInfoList.get(32).isEmpty()) {
                    differ = new BigDecimal(currentSharePriceInfoList.get(32)).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }

            item.put("current_price", current_price);
            item.put("differ_range", differ_range);
            item.put("differ", differ);

            result.add(item);
        }

        return result;
    }
}
