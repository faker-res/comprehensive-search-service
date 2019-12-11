package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import freemarker.ext.beans.HashAdapter;
import la.niub.abcapi.servicecompre.component.client.IServiceReportClient;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.RedisUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.config.configuration.RedisMarketConfiguration;
import la.niub.abcapi.servicecompre.dao.IWeiboWaveNewDao;
import la.niub.abcapi.servicecompre.dao.notice.*;
import la.niub.abcapi.servicecompre.dao.reporter.*;
import la.niub.abcapi.servicecompre.model.*;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockNewssetBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockNewssetStockBO;
import la.niub.abcapi.servicecompre.model.nosql.WeiboWaveNewModel;
import la.niub.abcapi.servicecompre.model.ntfEntityLawsuitModel;
import la.niub.abcapi.servicecompre.model.ntfEntityPatentsModel;
import la.niub.abcapi.servicecompre.model.ntfEntityTradeModel;
import la.niub.abcapi.servicecompre.model.response.ApiNewsDataItemResponse;
import la.niub.abcapi.servicecompre.model.response.ApiReportItemResponse;
import la.niub.abcapi.servicecompre.model.response.client.ApiNewsResponse;
import la.niub.abcapi.servicecompre.model.response.client.ApiReportResponse;
import la.niub.abcapi.servicecompre.model.secHoldAgencyDetailModel;
import la.niub.abcapi.servicecompre.model.secMainCirHolderModel;
import la.niub.abcapi.servicecompre.service.ICardService;
import la.niub.abcapi.servicecompre.service.ICompanyManagerService;
import la.niub.abcapi.servicecompre.service.IIndustryService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.util.JedisURIHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Service
public class IndustryServiceImpl implements IIndustryService {
    private final static Logger logger = LogManager.getLogger(IndustryServiceImpl.class);

    @Autowired
    IsecMainCirHolderDao secMainCirHolderDao;

    @Autowired
    IComLedPositionDao comLedPositionDao;

    @Autowired
    IsecHoldAgencyDetailDao secHoldAgencyDetailDao;

    @Autowired
    IntfEntityLawsuitDao entityLawsuitDao;

    @Autowired
    IntfEntityPatentsDao entityPatentsDao;

    @Autowired
    IntfEntityTradeDao entityTradeDao;

    @Autowired
    IComProfitDao comProfitDao;

    @Autowired
    IComBalanceYoyDao comBalanceYoyDao;

    @Autowired
    IComBalanceDao comBalanceDao;

    @Autowired
    IComIndexAnaDao comIndexAnaDao;

    @Autowired
    IComProfitSheetYoyDao comProfitSheetYoyDao;

    @Autowired
    IComCashflowDao comCashflowDao;

    @Autowired
    IComSaleStructureDao comSaleStructureDao;

    @Autowired
    ISecIndustryNewDao secIndustryNewDao;

    @Value("${feign.client.searchTag.url}")
    private String searchTagUrl;

    @Autowired
    IFundHoldStkStatDao fundHoldStkStatDao;

    @Autowired
    IFundHoldStkChgDao fundHoldStkChgDao;

    @Autowired
    ISecBasicInfoDao secBasicInfoDao;

    @Autowired
    ICompanyManagerService companyManagerService;

    @Autowired
    IAnalystHonorDao analystHonorDao;

    @Autowired
    IAnalystBasicinfoDao analystBasicinfoDao;

    @Autowired
    IServiceReportClient serviceReportClient;

    @Autowired
    HttpServletRequest servletRequest;

    @Autowired
    ISearchIndexDao searchIndexDao;

    @Autowired
    ISearchIndexDataDao searchIndexDataDao;

    @Autowired
    IWeiboWaveNewDao weiboWaveNewDao;

    @Autowired
    IAnalystForcastDao analystForcastDao;

    @Autowired
    IAnalystForcastIndexDao analystForcastIndexDao;

    @Autowired
    IAnalystStatisticsDao analystStatisticsDao;

    @Autowired
    IHiborAnalystDao hiborAnalystDao;

    @Autowired
    IAbcIndustryDao abcIndustryDao;

    @Autowired
    ISystemConstDao systemConstDao;

    @Value("${feign.client.apiNews.url}")
    private String apiNewsUrl;

    @Value("${feign.client.solrReport.url}")
    private String solrReportUrl;

    @Autowired
    ICardService iCardService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    IAnalystChanStatusDao analystChanStatusDao;

    @Autowired
    @Qualifier(RedisMarketConfiguration.NAME)
    private RedisTemplate<String, ?> marketRedisTemplate;

    /**
     * 行业快讯
     */
    @Override
    public Object buildIndustryNews(Long secUniCode){
        List<SecIndustryNewModel> industryT = secIndustryNewDao.buildInduListBySecUniCode(secUniCode);
        String industryName = "";
        if(industryT != null){
            for (SecIndustryNewModel item : industryT){
                industryName = item.getSecond_indu_name();
                if (item.getIndu_standard() == 1001014){ // 优先展示申万
                    industryName = item.getSecond_indu_name();
                    break;
                }
            }
        }
        //新闻
        List<ApiNewsDataItemResponse> newsDataItemList = null;
        try {
            Map<String, String> newsRequest = new HashMap<>();
            newsRequest.put("keyword", industryName);
            newsRequest.put("offset", "0");
            newsRequest.put("limit", "5");
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
            logger.error("industry news get news error : " + e.getMessage());
        }
        Map<String, Object> ret = new HashMap<>();
        ret.put("item", newsDataItemList);
        return ret;
    }


    /**
     * 另类数据
     * @param keyword
     * @param days
     * @return
     */
    @Override
    public Object buildHeatIndex(String keyword, Integer days) {
        Map<String, Object> ret = new HashMap<>();
        Object baiduIndex = buildIndexByTypeAndDays(keyword,days,"baidu");
        Object weiboIndex = buildIndexByTypeAndDays(keyword,days,"weibo");
        Object googeleIndex = buildIndexByTypeAndDays(keyword,days,"google");
        Object tianmaoIndex = buildIndexByTypeAndDays(keyword,days,"tianmao");
        ret.put("baidu", baiduIndex);
        ret.put("weibo", weiboIndex);
        ret.put("google", googeleIndex);
        ret.put("tianmao", tianmaoIndex);
        ret.put("newsCount", null);
        ret.put("noticeCount", null);
        ret.put("reportCount", null);
        return ret;
    }

    /**
     * 获取各种类型指数
     * @param keyword
     * @param limit
     * @param indexType
     * @return
     */
    @Override
    public Object buildIndexByTypeAndDays(String keyword, Integer limit, String indexType){
        Map<String,Long> ret = new LinkedHashMap<>();
        switch (indexType){
            case "baidu":
                SearchIndexModel stockBaiduInfo = searchIndexDao.selectByKeyword(keyword);
                if (stockBaiduInfo != null){
                    List<SearchIndexDataModel> indexDataModelList = searchIndexDataDao.selectByKeywordId(stockBaiduInfo.getKeyword_id(),limit);
                    for (SearchIndexDataModel item : indexDataModelList){
                        String date = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getData_time());
                        ret.put(date,item.getSearch_index());
                    }
                }
                break;
            case "weibo":
                List<WeiboWaveNewModel> weiboRecords = weiboWaveNewDao.getByKey(keyword,limit);
                if (weiboRecords != null || !weiboRecords.isEmpty()){
                    for (WeiboWaveNewModel item : weiboRecords){
                        ret.put(item.getDate(),item.getValue());
                    }
                }
                break;
        }
        return ret;
    }


    /**
     * 搜索标签
     * @param keyword
     * @param limit
     * @return
     */
    @Override
    public Object buildSearchTag(String keyword, Integer limit) {
        Map<String, String> params = new HashMap<>();
        params.put("count", String.valueOf(limit));
        try {
            params.put("word", URLEncoder.encode(keyword,"UTF-8"));
            params.put("select", "");
        } catch (UnsupportedEncodingException e) {

        }
        String searchRet = HttpUtil.get(searchTagUrl, params, null);
//        System.out.println("data search api request :" + JSON.toJSONString(params)+ " response :" + searchRet);

        if (searchRet != null && !searchRet.isEmpty()) {
            return JSON.parseObject(searchRet);
        }
        return null;
    }

    /**
     * 个股分析师
     * @param stockCode
     * @return
     */
    @Override
    public Object buildAnalystInfo(String stockCode, String orderField) {
        Map<String, Object> ret = new HashMap<>();
        List<Map<String, Object>> items = new LinkedList<>();
        // 近半年
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -6);
        List<AnalystForcastModel> records = analystForcastDao.selectByStockCodeAndTime(stockCode, c.getTime());
        if(records != null){
            Map<String, Integer> reportMap = new HashMap<>();
            Map<String, String> rateMap = new HashMap<>();
            Map<String, String> organMap = new HashMap<>();
            Map<String, String> timeMap = new HashMap<>();
            List<String> peoRankList = new ArrayList<>();
//            List<Long> reportIds = new ArrayList<>();

            for(AnalystForcastModel item : records){
                String k = item.getPeo_uni_code();
                if(k == null || k.isEmpty()){
                    continue;
                }
                Integer count = reportMap.containsKey(k) ? reportMap.get(k)+1 : 1;
                reportMap.put(k,count);
                if(!rateMap.containsKey(k)){
                    rateMap.put(k,item.getRating());
                }
                if(!organMap.containsKey(k)){
                    organMap.put(k,item.getPublish());
                }
                if(!timeMap.containsKey(k)){
                    timeMap.put(k,FastDateFormat.getInstance("yyyy-MM-dd").format(item.getTime()));
                }
//                if(item.getReport_id() != null){
//                    reportIds.add(item.getReport_id());
//                }
                if(!peoRankList.contains(k)){
                    peoRankList.add(k);
                }
            }
/*            // 根据研报关联分析师
            if(reportIds.size()>0){
                List<HiborAnalystModel> hiborAnalyst = hiborAnalystDao.selectPeoByIds(reportIds);
                for(HiborAnalystModel item : hiborAnalyst){
                    if(item.getAnalyst() != null && !peoRankList.contains(item.getAnalyst())){
                        peoRankList.add(item.getAnalyst());
                    }
                }
            }
*/
            if(peoRankList.isEmpty() || peoRankList.size()==0){
                return null;
            }

            // 分析师荣誉信息
            Map<String, Map> honorMap = new HashMap<>();
            List<AnalystHonorModel> honorModels = analystHonorDao.getHonorByPeoUniCodes(peoRankList,0);
            if(honorModels != null){
                for(AnalystHonorModel item : honorModels){
                    String k = item.getPeo_uni_code();
                    if(!honorMap.containsKey(k)){
                        Map<String, Object> tmp = new HashMap<>();
                        tmp.put("rank",item.getRanking());
                        tmp.put("year",FastDateFormat.getInstance("yyyy").format(item.getTime()));
                        honorMap.put(k,tmp);
                    }
                }
            }

            // 分析师基本信息
            List<AnalystBasicinfoModel> peoList = analystBasicinfoDao.selectByPeoUniCodeList(peoRankList);
            Map<String,Map> analystMap = new HashMap<>();
            if (peoList != null && !peoList.isEmpty()) {
                for (AnalystBasicinfoModel item : peoList) {
                    String k = item.getPeo_uni_code();
                    if (!analystMap.containsKey(k)) {
                        Map<String, Object> tmp = new HashMap<>();
                        tmp.put("image",item.getImage());
                        tmp.put("name",item.getName());
                        analystMap.put(k,tmp);
                    }
                }
            }

            // 分析师平均达成天数
            List<AnalystStatisticsModel> statisticsModels = analystStatisticsDao.selectByPeoUniCodes(peoRankList);
            Map<String, String> daysMap = new HashMap<>();
            for(AnalystStatisticsModel item : statisticsModels){
                String k = item.getPeo_uni_code();
                if(!daysMap.containsKey(k)){
                    daysMap.put(k,item.getReachdays_analyst() == null ? null : item.getReachdays_analyst().toString());
                }
            }
            for(String k: peoRankList){
                Map<String, Object> tmp = new HashMap<>();
                if(honorMap.containsKey(k)){
                    Map<String, Object> honor = honorMap.get(k);
                    tmp.put("rank",honor.get("rank"));
                    tmp.put("year",honor.get("year"));
                }else{
                    tmp.put("rank",null);
                    tmp.put("year",null);
                }
                if(analystMap.containsKey(k)){
                    Map<String, Object> base = analystMap.get(k);
                    tmp.put("name",base.get("name"));
                    tmp.put("image",base.get("image"));
                }else{
                    tmp.put("name",null);
                    tmp.put("image",null);
                }
                if(daysMap.containsKey(k)){
                    tmp.put("days",daysMap.get(k));
                }else{
                    tmp.put("days",null);
                }
                tmp.put("publish_time",timeMap.get(k));
                tmp.put("rate",rateMap.get(k));
                tmp.put("broker",organMap.get(k));
                tmp.put("gross",reportMap.get(k));
                tmp.put("id",k);
                items.add(tmp);
            }
            // 对 items 排序
            switch (orderField){
                case "report":
                    Collections.sort(items, new Comparator<Map<String, Object>>() {
                        @Override
                        public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                            Integer v1 = o1.get("gross") != null ? (Integer) o1.get("gross") : 0;
                            Integer v2= o2.get("gross") != null ? (Integer)o2.get("gross") : 0;
                            return v2.compareTo(v1);
                        }
                    });
                    break;
                case "time":
                    Collections.sort(items, new Comparator<Map<String, Object>>() {
                        @Override
                        public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                            String v1 = o1.get("publish_time") != null ? (String)o1.get("publish_time") : "";
                            String v2= o2.get("publish_time") != null ? (String)o2.get("publish_time") : "";
                            return v2.compareTo(v1);
                        }
                    });
                    break;
                case "days":
                    Collections.sort(items, new Comparator<Map<String, Object>>() {
                        @Override
                        public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                            String v1 = o1.get("days") != null ? (String)o1.get("days") : "";
                            String v2= o2.get("days") != null ? (String)o2.get("days") : "";
                            return v2.compareTo(v1);
                        }
                    });
                    break;
                default:
                    Collections.sort(items, new Comparator<Map<String, Object>>() {
                        @Override
                        public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                            String v1 = o1.get("year") != null ? (String)o1.get("year") : "";
                            String v2= o2.get("year") != null ? (String)o2.get("year") : "";
                            if (v2.compareTo(v1) == 0) {
                                Integer r1 = o1.get("rank") != null ? (Integer)o1.get("rank") :999;
                                Integer r2 = o2.get("rank") != null ? (Integer)o2.get("rank") : 999;
                                return r1.compareTo(r2);
                            } else {
                                return v2.compareTo(v1);
                            }
                        }
                    });
            }
        }
        ret.put("item", items);
        return ret;
    }

    /**
     * 投资评级
     * @param stockCode
     * @return
     */
    @Override
    public Object buildAnalystRate(String stockCode){
        Map<String, Object> ret = new HashMap<>();
        List<Map<String, Object>> items = new ArrayList<>();
        //当前月第一天
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date cur_month = c.getTime();
        //上个月第一天
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date last_1 = c.getTime();
        //last2个月
        c = Calendar.getInstance();
        c.add(Calendar.MONTH, -2);
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date last_2 = c.getTime();
        //last3个月
        c = Calendar.getInstance();
        c.add(Calendar.MONTH, -3);
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date last_3 = c.getTime();
        //last4个月
        c = Calendar.getInstance();
        c.add(Calendar.MONTH, -4);
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date last_4 = c.getTime();
//        System.out.println("1|"+last_1.toString()+"2}"+last_2.toString()+"3}"+last_3.toString()+"4}"+last_4.toString());
        // last 1
        Map<String, Object> lastMap =  getRateByDate(stockCode, last_1, cur_month);
        if(lastMap != null){
            items.add(lastMap);
        }
        // last 2
        lastMap = getRateByDate(stockCode, last_2,last_1);
        if(lastMap != null){
            items.add(lastMap);
        }
        // last 3
        lastMap = getRateByDate(stockCode, last_3, last_2);
        if(lastMap != null){
            items.add(lastMap);
        }
        // last 4
        lastMap = getRateByDate(stockCode, last_4, last_3);
        if(lastMap != null){
            items.add(lastMap);
        }
        ret.put("item",items);
        return ret;
    }

    /**
     * 获取某个月投资评级
     * @param stockCode
     * @param startDate
     * @param endDate
     * @return
     */
    private Map<String, Object> getRateByDate(String stockCode, Date startDate, Date endDate){
        List<Map<String, Object>> rateList = new ArrayList<>();
        List<AnalystForcastModel> records = analystForcastDao.getRatingByDate(stockCode, startDate, endDate);
        if(records != null) {
            for (AnalystForcastModel item : records) {
                Map<String, Object> tmp = new HashMap<>();
                tmp.put("rating",item.getRating());
                tmp.put("count",item.getCount());
                rateList.add(tmp);
            }
        }
        if(rateList.size()<=0){
            return null;
        }
        Map<String, Object> rateMap = new HashMap<>();
        rateMap.put("rate", rateList);
        String month = FastDateFormat.getInstance("MM").format(startDate);
        rateMap.put("month", StringUtils.removeStart(month,"0"));
        return rateMap;
    }

    /**
     * 预测误差
     * @param stockCode
     * @return
     */
    @Override
    public Object buildAnalystEps(String stockCode){
        Map<String, Object> ret = new HashMap<>();
        SecBasicInfoModel secBasicInfoModel = secBasicInfoDao.selectByAbcCode(stockCode);
        if(secBasicInfoModel == null){
            return null;
        }
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.YEAR, -1);
        List<Map<String, Object>> epsList = new ArrayList<>();
        List<AnalystForcastIndexModel> records = analystForcastIndexDao.getEps(stockCode);
        if(records != null) {
            for (AnalystForcastIndexModel item : records) {
                Map<String, Object> tmp = new HashMap<>();
                tmp.put("eps",item.getEps_avg());
                tmp.put("year",item.getYear().substring(0,4));
                epsList.add(tmp);
            }
        }
        ret.put("eps",epsList);

        List<Map<String, Object>> incomeList = new ArrayList<>();
        List<ComProfitModel> profits = comProfitDao.getRecordsByReportType(secBasicInfoModel.getCom_uni_code(),1015004, 2);
        if(profits != null) {
            for (ComProfitModel item : profits) {
                Map<String, Object> tmp = new HashMap<>();
                tmp.put("basic_perstock_income",item.getBasic_perstock_income());
                tmp.put("year",FastDateFormat.getInstance("yyyy").format(item.getEnd_date()));
                incomeList.add(tmp);
            }
        }
        ret.put("real",incomeList);
        return ret;
    }

    /**
     * 行业分析
     * @param secUniCode
     * @return
     */
    @Override
    public Object buildIndustryInfo(Long secUniCode) {
        Map<String, Object> ret = new HashMap<>();
        String industryName = "";
        String firstInduCode = "";
        List<SecIndustryNewModel> industryT = secIndustryNewDao.buildInduListBySecUniCode(secUniCode);
        if(industryT != null){
            for (SecIndustryNewModel item : industryT){
                if (item.getIndu_standard() == 1001014){ // 优先展示申万
                    industryName = item.getFirst_indu_name();
                    firstInduCode = item.getFirst_indu_code();
                    break;
                }
            }
        }
        List<Map<String, Object>> items = new ArrayList<>();
        List<Map<String, Object>> analyst = new ArrayList<>();
        if(!firstInduCode.isEmpty()){
            List<SecIndustryNewModel> company = secIndustryNewDao.selectByFirstInduCode(firstInduCode);
            if(company != null){
                List<Long> secUniCodeList = new ArrayList<>();
                for (SecIndustryNewModel item : company){
                    secUniCodeList.add(item.getSec_uni_code());
                }
                List<SecBasicInfoModel> secBasicInfoModelList = secBasicInfoDao.getListBySecUniCodesNullEndDate(secUniCodeList);
                for (SecBasicInfoModel item : secBasicInfoModelList){
                    Map<String, Object> itemMap = new HashMap<>();
                    itemMap.put("secUniCode", item.getSec_uni_code());
                    itemMap.put("stockCode", item.getAbc_code());
                    itemMap.put("comUniCode", item.getCom_uni_code());
                    itemMap.put("stockName", item.getSec_name());
                    List<String> currentSharePriceInfoList = companyManagerService.getStockRealTimePrice(item.getAbc_code());
                    Float current = 0f;
                    Double zdf = 0d;
                    if (currentSharePriceInfoList.size() >= 32){
                        if(!currentSharePriceInfoList.get(1).isEmpty()){
                            current = Float.valueOf(currentSharePriceInfoList.get(1));
                        }
                        if(!currentSharePriceInfoList.get(3).isEmpty()){
                            zdf = Double.valueOf(currentSharePriceInfoList.get(3));
                        }
                    }
                    itemMap.put("theNewestPrice", current);
                    itemMap.put("riseAndFallPercent", zdf);
                    items.add(itemMap);
                }
            }
            //bugfix https://jira.niub.la/browse/BASH-661
            Collections.sort(items, (o1, o2) -> ((Double)(o2.get("riseAndFallPercent"))).compareTo((Double)(o1.get("riseAndFallPercent"))));

            // 获取行业分析师
            List<AnalystBasicinfoModel> analystList = analystBasicinfoDao.getAnalystByDirection(industryName);
            if (analystList != null && !analystList.isEmpty()) {
                List<String> peoRankList = new ArrayList<>();
                Map<String,String> imageMap = new HashMap<>();
                for (AnalystBasicinfoModel item : analystList) {
                    if (item.getPeo_uni_code() != null && !item.getPeo_uni_code().isEmpty()) {
                        peoRankList.add(item.getPeo_uni_code());
                        imageMap.put(item.getPeo_uni_code(), item.getImage());
                    }
                }
                List<AnalystChanStatusModel> peoResumeList = analystChanStatusDao.getResumesByPeoUniCodes(peoRankList);
                Map<String,AnalystChanStatusModel> peoResumeMap = new HashMap<>();
                if (peoResumeList != null && !peoResumeList.isEmpty()) {
                    for (AnalystChanStatusModel aItem : peoResumeList) {
                        String keyCode = String.valueOf(aItem.getPeo_uni_code());
                        if (!peoResumeMap.containsKey(keyCode)) {
                            peoResumeMap.put(keyCode, aItem);
                        }
                    }
                }
                // 获取获奖分析师
                List<AnalystHonorModel> honor_rank = analystHonorDao.buildRecordsByAnalyst(peoRankList,peoRankList.size());
                List<String> honorPeoList = new ArrayList<>();
                if(honor_rank != null){
                    for(AnalystHonorModel item : honor_rank){
                        Map<String, Object> itemMap = new HashMap<>();
                        String keyCode = String.valueOf(item.getPeo_uni_code());
                        itemMap.put("year", FastDateFormat.getInstance("yyyy").format(item.getTime()));
                        itemMap.put("rank", item.getRanking());
                        itemMap.put("analystName", item.getAnalyst());
                        itemMap.put("analystCompanyName", item.getOrgan());
                        itemMap.put("id", keyCode);

                        Object honor = item.getHonor();
                        if (honor != null && honor.toString().equals("新财富最佳分析师")) {
                            itemMap.put("honor", honor);
                        } else {
                            itemMap.put("honor", null);
                        }

                        itemMap.put("headPortraitUrl", "");
                        itemMap.put("analystCode", "");
                        if(imageMap.containsKey(keyCode)){
                            itemMap.put("headPortraitUrl", imageMap.get(keyCode));
                        }
                        if(peoResumeMap.containsKey(keyCode)){
                            AnalystChanStatusModel resume = peoResumeMap.get(keyCode);
                            if(resume.getAnalyst_code() != null){
                                itemMap.put("analystCode", resume.getAnalyst_code());
                            }
                            if(resume.getOrg_sname() != null){
                                itemMap.put("analystCompanyName", resume.getOrg_sname());
                            }
                        }
                        analyst.add(itemMap);
                        honorPeoList.add(item.getPeo_uni_code());
                    }
                }
                for (AnalystBasicinfoModel item : analystList) {
                    if (item.getPeo_uni_code() == null || item.getPeo_uni_code().isEmpty()
                            || honorPeoList.contains(item.getPeo_uni_code())) {
                        continue;
                    }
                    String keyCode = String.valueOf(item.getPeo_uni_code());
                    Map<String, Object> itemMap = new HashMap<>();
                    itemMap.put("year", "");
                    itemMap.put("rank", "");
                    itemMap.put("honor", "");
                    itemMap.put("analystName", item.getName());
                    itemMap.put("id", keyCode);
                    itemMap.put("headPortraitUrl", item.getImage());
                    itemMap.put("analystCompanyName", "");
                    itemMap.put("analystCode", "");
                    if(peoResumeMap.containsKey(keyCode)){
                        AnalystChanStatusModel resume = peoResumeMap.get(keyCode);
                        if(resume.getAnalyst_code() != null){
                            itemMap.put("analystCode", resume.getAnalyst_code());
                        }
                        if(resume.getOrg_sname() != null){
                            itemMap.put("analystCompanyName", resume.getOrg_sname());
                        }
                    }
                    analyst.add(itemMap);
                }
            }
        }
        ret.put("companyType", industryName);
        ret.put("industryRelatedCompaniesData",items);
        ret.put("industryAnalystsData",analyst);
        //获取行业研报
        ret.put("industryReportsData",buildIndustryReport(firstInduCode, industryName, 5));

        return ret;
    }

    private List<Map<String, Object>> buildIndustryReport(String firstInduCode, String industryName, Integer limit){
        List<Map<String, Object>> items = new ArrayList<>();
        String solrCode = StringUtil.recoverIndustry(firstInduCode);
        try {
            Map<String, String> reportRequest = new HashMap<>();
            reportRequest.put("keyword", "ALL");
            reportRequest.put("offset", "0");
            reportRequest.put("limit", limit.toString());
            reportRequest.put("industry_name", solrCode);
            String apiReportRet = HttpUtil.post(solrReportUrl, reportRequest, null);
            if (apiReportRet != null && !apiReportRet.isEmpty()) {
                ApiReportResponse apiReportRetObject = JSON.parseObject(apiReportRet, ApiReportResponse.class);
                if (apiReportRetObject != null) {
                    List<ApiReportItemResponse> reportItems = apiReportRetObject.getData().getItem();
                    for (ApiReportItemResponse item : reportItems){
                        Map<String, Object> itemMap = new HashMap<>();
                        itemMap.put("title", item.getTitle());
                        itemMap.put("companyName", item.getSource());
                        itemMap.put("companyType", industryName);
                        itemMap.put("name", item.getAuthor());
                        itemMap.put("time", item.getTime());
                        itemMap.put("id", item.getId());
                        itemMap.put("isTrophy", item.getHonor() != null && item.getHonor().size()>0 ? true : false);
                        items.add(itemMap);
                    }

                }
            }
        } catch (Exception e) {
            logger.error("industry report get error : " + e.getMessage());
        }

        return items;
    }

    /**
     * 基金信息
     * @param stockCode
     * @return
     */
    @Override
    public Object buildFundInfo(String stockCode, String orderField, Integer offset, Integer limit) {
        SecBasicInfoModel secBasicInfoModel = secBasicInfoDao.selectByAbcCode(stockCode);
        if(secBasicInfoModel == null){
            return null;
        }
        Long secUniCode = secBasicInfoModel.getSec_uni_code();
        Map<String, Object> ret = new HashMap<>();
        FundHoldStkStatModel record = fundHoldStkStatDao.getNewestRecords(secUniCode);
        Map<String, Object> map = new HashMap<>();
        if(record != null){
            map.put("name", secBasicInfoModel.getSec_name());
            map.put("amount", record.getHold_stk_fund_amt());
            map.put("share", record.getHold_tot_stk_amt());
            map.put("change", record.getHold_stk_amt_chg());
            map.put("market_value", record.getHold_tot_stk_value());
            map.put("ratio", record.getHold_stk_value_nav_ratio());
            map.put("update_date", FastDateFormat.getInstance("yyyy-MM-dd").format(record.getEnd_date()));
        }
        ret.put("base",map);
        Date lastEndDate = fundHoldStkChgDao.getLastEndDate(secUniCode);
        List<Map<String, Object>> items = new ArrayList<>();
        if(lastEndDate != null){
            if(orderField != null){
                // 排序字段
                switch (orderField){
                    case "f1":
                        orderField = "hold_sec_amount";
                        break;
                    case "f2":
                        orderField = "hold_stk_amt_chg";
                        break;
                    case "f3":
                        orderField = "hold_ratio_for_float_stk";
                        break;
                    case "f4":
                        orderField = "hold_sec_value";
                        break;
                    case "f5":
                        orderField = "sec_value_net_ratio";
                        break;
                    case "f6":
                        orderField = "hold_stk_value_stk_ratio";
                        break;
                    default:
                        orderField = null;
                }
            }
            List<FundHoldStkChgModel> records = fundHoldStkChgDao.getYearRecords(secUniCode, lastEndDate, orderField, offset, limit);
            if(records != null){
                for(FundHoldStkChgModel item: records){
                    Map<String, Object> fundMap = new HashMap<>();
                    fundMap.put("fund_name", item.getSec_name());
                    fundMap.put("fund_code", item.getSec_code());
                    fundMap.put("f1", item.getHold_sec_amount());
                    fundMap.put("f4", item.getHold_sec_value());
                    fundMap.put("f5", item.getSec_value_net_ratio());
                    fundMap.put("f2", item.getHold_stk_amt_chg());
                    fundMap.put("f3", item.getHold_ratio_for_float_stk());
                    fundMap.put("f6", item.getHold_stk_value_stk_ratio());
                    items.add(fundMap);
                }
            }
        }
        ret.put("items", items);
        return ret;
    }

    /**
     * 三大财务报表
     * @param comUniCode
     * @param type
     * @return
     */
    @Override
    public Object buildFinanceOverview(Long comUniCode, String type) {
        if(type == null || type.isEmpty()){
            type = "profit";
        }
        switch (type) {
            case "profit":// 利润表
                return buildProfitOverview(comUniCode);
            case "balance"://资产负债表
                return buildBalanceOverview(comUniCode);
            case "cash":// 现金流量表
                return buildCashOverview(comUniCode);
            default:
                return null;
        }
    }

    /**
     * 净利润报表
     * @param
     * @return
     */
    public Object buildProfitOverview(Long comUniCode) {
        List<Map<String, Object>> items = new ArrayList<>();
        Map<String, Object> ret = new HashMap<>();
        Map<String, String> config = new LinkedHashMap<>();
        config.put("营业总收入", "overall_income");
        config.put("营业总成本", "overall_cost");
        config.put("营业利润", "overall_profit");
        config.put("净利润", "netprofit");
        List<ComProfitModel> records = comProfitDao.getTopNRecords(comUniCode,5);
        String date = "";
        for (String key : config.keySet()) {
            Map<String, Object> configItem = new HashMap<>();
            Map<String, Object> map = new LinkedHashMap<>();
            if(records != null){
                for(ComProfitModel item: records){
                    String endDate = "";
                    if(item.getEnd_date() != null){
                        endDate = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getEnd_date());
                        if(date.isEmpty()){
                            date = endDate;
                        }
                    }
                    map.put(endDate, item.getValueByColumn(config.get(key)));
                }
            }
            configItem.put("legend", key);
            configItem.put("indicator_value",map);
            items.add(configItem);
        }
        ret.put("item", items);
        return ret;
    }

    /**
     * 资产负债表
     * @param
     * @return
     */
    public Object buildBalanceOverview(Long comUniCode) {
        List<Map<String, Object>> items = new ArrayList<>();
        Map<String, Object> ret = new HashMap<>();
        Map<String, String> config = new LinkedHashMap<>();
        config.put("流动资产", "total_current_asset");
        config.put("固定资产", "fix_asset");
        config.put("资产总计", "total_asset");
        config.put("负债合计", "total_liabilities");
        List<ComBalanceModel> records = comBalanceDao.getTopNRecords(comUniCode,5);
        String date = "";
        for (String key : config.keySet()) {
            Map<String, Object> configItem = new HashMap<>();
            Map<String, Object> map = new LinkedHashMap<>();
            if(records != null){
                for(ComBalanceModel item: records){
                    String endDate = "";
                    if(item.getEnd_date() != null){
                        endDate = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getEnd_date());
                        if(date.isEmpty()){
                            date = endDate;
                        }
                    }
                    map.put(endDate, item.getValueByColumn(config.get(key)));
                }
            }
            configItem.put("legend", key);
            configItem.put("indicator_value",map);
            items.add(configItem);
        }
        ret.put("item", items);
        return ret;
    }

    /**
     * 现金流量表
     * @param
     * @return
     */
    public Object buildCashOverview(Long comUniCode) {
        List<Map<String, Object>> items = new ArrayList<>();
        Map<String, Object> ret = new HashMap<>();
        Map<String, String> config = new LinkedHashMap<>();
        config.put("销售商品、提供劳务收到的现金", "sale_cash");
        config.put("经营活动现金流入小计", "bussiness_cash_total");
        config.put("经营活动现金流出小计", "bussiness_cash_output");
        config.put("经营现金流量净额", "bussiness_cash_netvalue");
        List<ComCashflowModel> records = comCashflowDao.getTopNRecords(comUniCode,5);
        String date = "";
        for (String key : config.keySet()) {
            Map<String, Object> configItem = new HashMap<>();
            Map<String, Object> map = new LinkedHashMap<>();
            if(records != null){
                for(ComCashflowModel item: records){
                    String endDate = "";
                    if(item.getEnd_date() != null){
                        endDate = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getEnd_date());
                        if(date.isEmpty()){
                            date = endDate;
                        }
                    }
                    map.put(endDate, item.getValueByColumn(config.get(key)));
                }
            }
            configItem.put("legend", key);
            configItem.put("indicator_value",map);
            items.add(configItem);
        }
        ret.put("item", items);
        return ret;
    }

    /**
     * 主营构成
     * @param comUniCode
     * @return
     */
    @Override
    public Object buildMajorBusiness(Long comUniCode) {
        Map<String, Object> ret = new HashMap<>();
        List<Map<String, Object>> items = new ArrayList<>();
        List<ComSaleStructureModel> dateRecords = comSaleStructureDao.getTopReportDate(comUniCode,3);
        Map<Long,String> system_const_map=new HashMap<Long,String>();
        if(dateRecords != null){
            for(ComSaleStructureModel dateItem: dateRecords){
                if(dateItem.getEnd_date() != null){
                    Map<String, Object> dateMap = new LinkedHashMap<>();
                    Map<String, List> dateValue = new LinkedHashMap<>();
                    List<ComSaleStructureModel> records = comSaleStructureDao.getRecordsByDate(comUniCode, dateItem.getEnd_date());
                    List<Map> incomeRate = new LinkedList<>();
                    List<Map> costRate = new LinkedList<>();
                    List<Map> grosProfRate = new LinkedList<>();
                    if(records != null){
                        for(ComSaleStructureModel item: records){
                            if(item.getProject_type()!=null) {
                                if (!system_const_map.containsKey(item.getProject_type())) {
                                    SystemConstModel systemConstModel = systemConstDao.selectBySystemUniCode(item.getProject_type());
                                    if (systemConstModel != null) {
                                        system_const_map.put(item.getProject_type(), systemConstModel.getSystem_name());
                                    }
                                }
                            }

                            if(item.getCost_rate() != null){
                                costRate.add(new HashMap<String, Object>(){{
                                    put("name",item.getProject_name());
                                    put("y",item.getCost_rate());
                                    put("type",system_const_map.get(item.getProject_type()));
                                }});
                            }
                            if(item.getIncome_rate() != null){
                                incomeRate.add(new HashMap<String, Object>(){{
                                    put("name",item.getProject_name());
                                    put("y",item.getIncome_rate());
                                    put("type",system_const_map.get(item.getProject_type()));
                                }});
                            }
                            if(item.getGros_prof_rate() != null){
                                grosProfRate.add(new HashMap<String, Object>(){{
                                    put("name",item.getProject_name());
                                    put("y",item.getGros_prof_rate());
                                    put("type",system_const_map.get(item.getProject_type()));
                                }});
                            }
                        }
                    }
                    dateValue.put("营业收入", incomeRate);
                    dateValue.put("营业成本", costRate);
                    dateValue.put("毛利", grosProfRate);
                    dateMap.put("indicator_value",dateValue);
                    dateMap.put("legend", FastDateFormat.getInstance("yyyy-MM-dd").format(dateItem.getEnd_date()));
                    items.add(dateMap);
                }

            }
            system_const_map.clear();
        }
        ret.put("item",items);
        return ret;
    }

    /**
     * 核心财务指标
     * @param
     * @return
     */
    @Override
    public Object buildKeyValueOverview(Long comUniCode) {
        List<Map<String, Object>> items = new ArrayList<>();
        Map<String, Object> ret = new HashMap<>();
        Map<String, Map> config = new LinkedHashMap<>();
        config.put("基本每股收益(元)", new HashMap<String, String>(){{
            put("column","basic_perstock_income");
            put("table","com_index_ana");
        }});
        config.put("每股净资产(元)",new HashMap<String, String>(){{
            put("column","netassets_ps");
            put("table","com_index_ana");
        }});
        config.put("营业收入(万元)",new HashMap<String, String>(){{
            put("column","overall_income");
            put("table","com_profit");
        }});
        config.put("营业利润(万元)",new HashMap<String, String>(){{
            put("column","overall_profit");
            put("table","com_profit");
        }});
        config.put("净利润(万元)",new HashMap<String, String>(){{
            put("column","netprofit");
            put("table","com_profit");
        }});
        List<ComProfitModel> profit_records = comProfitDao.getTopNRecords(comUniCode,3);
        List<ComIndexAnaModel> index_records = comIndexAnaDao.getTopNRecords(comUniCode,3);
        String date = "";
        for (String key : config.keySet()) {
            Map<String, Object> configItem = new HashMap<>();
            configItem.put("legend", key);
            Map<String, String> value = config.get(key);
            Map<String, Object> map = new LinkedHashMap<>();

            if(value.get("table").equals("com_profit")){
                if(profit_records != null){
                    for(ComProfitModel item: profit_records){
                        String endDate = "";
                        if(item.getEnd_date() != null){
                            endDate = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getEnd_date());
                            if(date.isEmpty()){
                                date = endDate;
                            }
                        }
                        map.put(endDate, item.getValueByColumn(value.get("column")));
                    }
                }
            }else{
                if(index_records != null){
                    for(ComIndexAnaModel item: index_records){
                        String endDate = "";
                        if(item.getEnd_date() != null){
                            endDate = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getEnd_date());
                        }
                        map.put(endDate, item.getValueByColumn(value.get("column")));
                    }
                }
            }
            configItem.put("indicator_value",map);
            items.add(configItem);
        }
        ret.put("item", items);
        ret.put("update_time", date);
        return ret;
    }

    /**
     * 财务能力
     * @param
     * @return
     */
    @Override
    public Object buildAbilityOverview(Long comUniCode) {
        List<Map<String, Object>> items = new ArrayList<>();
        Map<String, Object> ret = new HashMap<>();
        Map<String, Map> config = new LinkedHashMap<>();
        config.put("每股指标", new HashMap<String, String>(){{
            put("基本每股收益(元)","basic_perstock_income");
            put("每股净资产(元)","netassets_ps");
            put("每股经营活动产生的现金流量净额(元)","ocf_ps");
            put("每股营业总收入(元)","gr_ps");
        }});
        config.put("盈利能力",new HashMap<String, String>(){{
            put("净资产收益率（摊薄）(%)","roe_diluted");
            put("营业利润率(%)","op_per_gr");
            put("营业成本率(%)","gc_per_gr");
            put("销售净利率(%)","netprofit_margin");
        }});
        config.put("资本结构",new HashMap<String, String>(){{
            put("资产负债率(%)","roe_weigh");
            put("非流动负债权益比率(%)","currentdebt_equity");
            put("流动负债权益比率(%)","longdebt_equity");
            put("长期资本负债率(%)","longdebt_longcaptial");
        }});
        config.put("偿债能力",new HashMap<String, String>(){{
            put("流动比率","current_ratio");
            put("速动比率","quick_ratio");
            put("归属母公司股东的权益/负债合计","equity_per_debt");
            put("现金债务总额比率","ocf_per_longdebt");
        }});
        config.put("营运能力",new HashMap<String, String>(){{
            put("存货周转率(次)","inv_turndays");
            put("总资产周转率(次)","assets_turn");
            put("流动资产周转率(次)","ca_turn");
            put("固定资产周转率(次)","fa_turn");
        }});
        config.put("成长能力",new HashMap<String, String>(){{
            put("总资产同比增长率(%)","total_asset");
            put("净资产同比增长率(%)","total_account_parent_equity");
            put("总负债同比增长率(%)","total_liabilities");
            put("净利润同比增长率(%)","netprofit");
        }});
        List<ComIndexAnaModel> index_records = comIndexAnaDao.getTopNRecords(comUniCode,3);
        List<ComBalanceYoyModel> balance_records = comBalanceYoyDao.getTopNRecords(comUniCode,3);
        List<ComProfitSheetYoyModel> profit_records = comProfitSheetYoyDao.getTopNRecords(comUniCode,3);
        String date = "";
        for (String key : config.keySet()) {
            Map<String, Object> configItem = new HashMap<>();
            configItem.put("legend", key);
            Map<String, String> value = config.get(key);
            Map<String, Object> indicatorMap = new LinkedHashMap<>();
            for (String indicator : value.keySet()) {
                Map<String, Object> map = new LinkedHashMap<>();
                if(key.equals("成长能力")){
                    if(indicator.equals("净利润同比增长率(%)")){
                        for(ComProfitSheetYoyModel item: profit_records){
                            String endDate = "";
                            if(item.getEnd_date() != null){
                                endDate = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getEnd_date());
                            }
                            map.put(endDate, item.getValueByColumn(value.get(indicator)));
                        }
                    }else{
                        for(ComBalanceYoyModel item: balance_records){
                            String endDate = "";
                            if(item.getEnd_date() != null){
                                endDate = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getEnd_date());
                            }
                            map.put(endDate, item.getValueByColumn(value.get(indicator)));
                        }
                    }
                }else{
                    if(index_records != null){
                        for(ComIndexAnaModel item: index_records){
                            String endDate = "";
                            if(item.getEnd_date() != null){
                                endDate = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getEnd_date());
                                if(date.isEmpty()){
                                    date = endDate;
                                }
                            }
                            map.put(endDate, item.getValueByColumn(value.get(indicator)));
                        }
                    }
                }
                indicatorMap.put(indicator,map);
            }
            configItem.put("indicator_value",indicatorMap);
            items.add(configItem);
        }
        ret.put("item", items);
        ret.put("update_time", date);
        return ret;
    }

    /**
     * 十大流通股东
     * @param comUniCode
     * @return
     */
    @Override
    public Object buildCirculationShareholders(Long comUniCode) {
        Date endDate = secMainCirHolderDao.getMaxDate(comUniCode);
        Map<String, Object> ret = new HashMap<>();
        List<Map<String, Object>> items = new ArrayList<>();
        if(endDate != null){
            List<secMainCirHolderModel> records = secMainCirHolderDao.getRecords(comUniCode,endDate);
            if(records != null){
                for(secMainCirHolderModel item: records){
                    Map<String, Object> map = new HashMap<>();
                    map.put("ranking", item.getShare_rank());
                    map.put("name", item.getShr_hldr_name());
                    map.put("hold_amt", item.getHold_shr_vol());
                    map.put("hold_pct", item.getHold_shr_prop() == null ? 0 : item.getHold_shr_prop());
                    items.add(map);
                }
            }

        }
        ret.put("item", items);
        String date = FastDateFormat.getInstance("yyyy-MM-dd").format(endDate);
        ret.put("update_time", date);
        return ret;
    }

    /**
     * 现任高管
     * @param comUniCode
     * @return
     */
    @Override
    public Object buildCompanyManager(Long comUniCode) {
        Map<String, Object> ret = new HashMap<>();
        List<Map<String, Object>> items = new ArrayList<>();
        List<ComLedPositionModel> records = comLedPositionDao.getRecordsByUniCodeAndPost(comUniCode, null);
        if(records != null){
            for(ComLedPositionModel item: records){
                Map<String, Object> map = new HashMap<>();
                map.put("led_name", item.getLed_name());
                map.put("post_name", item.getPost_name());
                String inDate = "";
                if(item.getIn_date() != null){
                    inDate = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getIn_date());
                }
                map.put("in_date", inDate);
                map.put("peo_uni_code", item.getPeo_uni_code());
                items.add(map);
            }
        }
        ret.put("item", items);
        String date = FastDateFormat.getInstance("yyyy-MM-dd").format(new Date());
        ret.put("update_time", date);
        return ret;
    }

    /**
     * 持股机构
     * @param comUniCode
     * @return
     */
    @Override
    public Object buildAgencyHoldDetail(Long comUniCode) {
        Map<String, Object> ret = new HashMap<>();
        List<Map<String, Object>> items = new ArrayList<>();
        List<secHoldAgencyDetailModel> records = secHoldAgencyDetailDao.getRecordsByUniCode(comUniCode, 10);
        if(records != null){
            for(secHoldAgencyDetailModel item: records){
                Map<String, Object> map = new HashMap<>();
                map.put("org_name", item.getOrg_name());
                String endDate = "";
                if(item.getEnd_date() != null){
                    endDate = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getEnd_date());
                }
                map.put("end_date", endDate);
                map.put("hold_liq", item.getHold_liq());
                map.put("hold_pct", item.getHold_pct() != null ? item.getHold_pct().toString() : "");
                items.add(map);
            }
        }
        ret.put("item", items);
        String date = FastDateFormat.getInstance("yyyy-MM-dd").format(new Date());
        ret.put("update_time", date);
        return ret;
    }

    /**
     * 商标信息
     * @return
     */
    @Override
    public Object buildTrademark(Long comUniCode, Integer offset, Integer limit) {
        Map<String, Object> ret = new HashMap<>();
        List<Map<String, Object>> items = new ArrayList<>();
        Integer total = entityTradeDao.getCount(comUniCode);
        List<ntfEntityTradeModel> records = entityTradeDao.selectByComUniCode(comUniCode, limit, offset);
        if(records != null){
            for(ntfEntityTradeModel item: records){
                Map<String, Object> map = new HashMap<>();
                map.put("trade_pic", item.getTrade_pic());
                String endDate = "";
                if(item.getStat_date() != null){
                    endDate = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getStat_date());
                }
                map.put("stat_date", endDate);
                map.put("trade_name", item.getTrade_name());
                map.put("reg_no", item.getReg_no());
                map.put("trade_type", item.getTrade_type());
                map.put("trade_status", item.getTrade_status());
                items.add(map);
            }
        }
        ret.put("item", items);
        ret.put("total", total);
        return ret;
    }


    /**
     * 专利信息
     * @return
     */
    @Override
    public Object buildPatent(Long comUniCode, Integer offset, Integer limit) {
        Map<String, Object> ret = new HashMap<>();
        List<Map<String, Object>> items = new ArrayList<>();
        Integer total = entityPatentsDao.getCount(comUniCode);
        List<ntfEntityPatentsModel> records = entityPatentsDao.selectByComUniCode(comUniCode, limit, offset);
        if(records != null){
            for(ntfEntityPatentsModel item: records){
                Map<String, Object> map = new HashMap<>();
                map.put("patents_name", item.getPatents_name());
                String endDate = "";
                if(item.getApplication_publish_time() != null){
                    endDate = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getApplication_publish_time());
                }
                map.put("application_publish_time", endDate);
                map.put("patents_no", item.getPatents_no());
                map.put("patents_publish_no", item.getPatents_publish_no());
                map.put("patents_type", item.getPatents_type());
                items.add(map);
            }
        }
        ret.put("item", items);
        ret.put("total", total);
        return ret;
    }

    /**
     * 法律诉讼
     * @return
     */
    @Override
    public Object buildLawsuit(Long comUniCode, String caseType, Integer offset, Integer limit) {
        Map<String, Object> ret = new HashMap<>();
        List<Map<String, Object>> items = new ArrayList<>();
        if(caseType.isEmpty()){
            caseType = null;
        }
        Integer total = entityLawsuitDao.getCount(comUniCode,caseType);
        List<ntfEntityLawsuitModel> records = entityLawsuitDao.selectByComUniCode(comUniCode, caseType, limit, offset);
        if(records != null){
            for(ntfEntityLawsuitModel item: records){
                Map<String, Object> map = new HashMap<>();
                map.put("Judge_time", item.getJudgetime());
                map.put("title", item.getTitle());
                map.put("case_reason", item.getCasereason());
                map.put("party_info", item.getPartyInfo());
                map.put("case_no", item.getCaseno());
                items.add(map);
            }
        }
        ret.put("item", items);
        ret.put("total", total);
        return ret;
    }

    @Override
    public List<AbcIndustryModel> getChildIndustry(String induCode) {
        List<AbcIndustryModel> result = new ArrayList<>();

        List<String> firstInduCodes = new ArrayList<>();
        firstInduCodes.add(induCode);

        List<String> secondInduCodes = new ArrayList<>();
        List<AbcIndustryModel> secondAbcIndustryModelList = abcIndustryDao.selectByParentIds(firstInduCodes);
        if (secondAbcIndustryModelList == null || secondAbcIndustryModelList.isEmpty()){
            return result;
        }
        result.addAll(secondAbcIndustryModelList);
        for (AbcIndustryModel item : secondAbcIndustryModelList){
            secondInduCodes.add(item.getIndu_code());
        }

        List<AbcIndustryModel> thirdAbcIndustryModelList = abcIndustryDao.selectByParentIds(secondInduCodes);
        if (thirdAbcIndustryModelList == null || thirdAbcIndustryModelList.isEmpty()){
            return result;
        }
        result.addAll(thirdAbcIndustryModelList);

        return result;
    }

    @Override
    public Map<String, Object> getStockInfoList(String abc_code, String prior, final int isAsc, String indu_name, String type, Integer offset, Integer limit) throws Exception {
        if (StringUtil.isEmpty(indu_name)) {
            indu_name = null;
        }

        if (StringUtil.isEmpty(abc_code)) {
            abc_code = null;
        }

        Long sec_type = null;

        if (StringUtil.isEmpty(type)) {
            sec_type = null;
        } else {
            switch (type) {
                case "HS":
                    sec_type = 1004001l;
                    break;
                case "HK":
                    sec_type = 1004017l;
                    break;
                case "US":
                    sec_type = 1004018l;
                    break;
                default:
                    sec_type = null;
            }
        }


        List<String> abcCodeList = secIndustryNewDao.getAbcCodeByInduNameAndAbcCode(abc_code, indu_name);
        if (StringUtil.isEmpty(abcCodeList)) {
            return null;
        }

        abcCodeList = secBasicInfoDao.getAbcCodeByAbcCodeListAndSecType(abcCodeList, sec_type);
        if (StringUtil.isEmpty(abcCodeList)) {
            return null;
        }

//        List<String> list = new ArrayList<>();
//        for (String abcCode : abcCodeList) {
//            abcCode = "price_" + abcCode;
//            list.add(abcCode);
//        }
//
//        List<Object> list1 = marketRedisTemplate.executePipelined(new RedisCallback<String>() {
//
//            @Override
//            public String doInRedis(RedisConnection redisConnection) throws DataAccessException{
//                StringRedisConnection connection = (StringRedisConnection)redisConnection;
//                for (String key : list) {
//                    connection.get(key);
//                }
//                return null;
//            }
//
//        }, marketRedisTemplate.getStringSerializer());
//
//        String code = "price_" + abcCodeList.get(0);
//        String res = marketRedisTemplate.getStringSerializer().serialize(code).toString();
//        List<?> res1 = redisUtil.mget(abcCodeList);
//
//
//
//
////        List<?> resilt = marketRedisTemplate.opsForHash().multiGet(list);
////        for (Object item : resilt) {
////            String a = item.toString();
////        }

        int totalCount = abcCodeList.size();
        Integer nThreads = totalCount / 50 + 1;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        List<Future<String>> futures = new ArrayList<Future<String>>(nThreads);
        List<Map<String, Object>> stockInfoList = new ArrayList<>();
        List<Callable<String>> tasks = new ArrayList<Callable<String>>();
        for (int i = 0; i < nThreads; i++) {
            final List<String> subList = abcCodeList.subList(totalCount / nThreads * i, totalCount / nThreads * (i + 1));
            Callable<String> task = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    for (String abcCode : subList) {
                        SecBasicInfoModel secBasicInfoModel = secBasicInfoDao.selectByAbcCode(abcCode);
                        String sec_name = secBasicInfoModel.getSec_name();
                        Long sec_uni_code = secBasicInfoModel.getSec_uni_code();
                        Long com_uni_code = secBasicInfoModel.getCom_uni_code();
                        Map<String, Object> stockInfo = getStockInfo(abcCode, sec_name, sec_uni_code, com_uni_code);
                        stockInfoList.add(stockInfo);
////                        Map<String, Object> map = getAnalystInfoWhenPriorIsReportCount(peoUniCode, indu_name);
////                        reportCount.add(map);
                    }
                    System.out.println(Thread.currentThread().getName() + "////////////////////////////////////////////////////////////////////////////////////");
                    return null;
                }
            };
            tasks.add(task);
        }

        executorService.invokeAll(tasks);

        executorService.shutdown();

////        List<Map<String, Object>> stockInfoList = new ArrayList<>();
////        for (String abcCode : abcCodeList) {
////            SecBasicInfoModel secBasicInfoModel = secBasicInfoDao.selectByAbcCode(abcCode);
////            String sec_name = secBasicInfoModel.getSec_name();
////            Long sec_uni_code = secBasicInfoModel.getSec_uni_code();
////            Long com_uni_code = secBasicInfoModel.getCom_uni_code();
////            Map<String, Object> stockInfo = getStockInfo(abcCode, sec_name, sec_uni_code, com_uni_code);
////            stockInfoList.add(stockInfo);
////        }

        if (!StringUtil.isEmpty(prior)) {
            Collections.sort(stockInfoList, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    Double num1 = o1.get(prior) == null? 0.0 : Double.parseDouble(o1.get(prior).toString());
                    Double num2 = o2.get(prior) == null? 0.0 : Double.parseDouble(o2.get(prior).toString());
                    return (isAsc * (num1 - num2)) > 0 ? 1 : -1;
                }
            });
        }

        Map<String, Object> result = new HashMap<>();

        Integer count = stockInfoList.size();
        result.put("totalCount", count);

        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = offset, max = Math.min(count, limit + offset); i < max; i++) {
            items.add(stockInfoList.get(i));
        }

        result.put("items", items);

        return result;
    }
//
    private Map<String, Object> getStockInfo(String abc_code, String sec_name, Long sec_uni_code, Long com_uni_code) {
        Map<String, Object> stockInfo = new HashMap<>();
        CardStockNewssetBO cardStockNewssetBO = companyManagerService.buildCompanyNewestStockInfo(abc_code);
////        CardStockNewssetCompanyBO companyBO = cardStockNewssetBO.getCompany_data();
        CardStockNewssetStockBO cardStockNewssetStockBO = (CardStockNewssetStockBO)cardStockNewssetBO.getStock_data();
////        JSONObject stockData = JSONObject.parseObject(cardStockNewssetBO.getStock_data().toString());
//
//
//
        stockInfo.put("abc_code", abc_code);
        stockInfo.put("sec_name", sec_name);
////        stockInfo.put("cir_market_value", stockData.getDouble("ltsz"));
////        stockInfo.put("deal_amount", stockData.getDouble("cjl"));
////        stockInfo.put("deal_vol", stockData.getDouble("cje"));
////        stockInfo.put("differ", stockData.getDouble(""));
////        stockInfo.put("differ_range", stockData.getDouble("zdf"));
////        stockInfo.put("high", stockData.getDouble("zg"));
////        stockInfo.put("low", stockData.getDouble("zd"));
////        stockInfo.put("now", stockData.getDouble("current_price"));
////        stockInfo.put("pb", stockData.getDouble("sjl"));
////        stockInfo.put("pe", stockData.getDouble("syl"));
////        stockInfo.put("ps", stockData.getDouble("sxl"));
////        stockInfo.put("sec_uni_code", sec_uni_code);
////        stockInfo.put("com_uni_code", com_uni_code);
////        stockInfo.put("today_open", stockData.getDouble("jk"));
////        stockInfo.put("total_market_value", stockData.getDouble("zsz"));
////        stockInfo.put("total_stock_value", stockData.getDouble("zgb"));
//        stockInfo.put("yesterday_close", stockData.getDouble("zs"));
//
        stockInfo.put("cir_market_value", cardStockNewssetStockBO.getLtsz());
        stockInfo.put("deal_amount", cardStockNewssetStockBO.getCjl());
        stockInfo.put("deal_vol", cardStockNewssetStockBO.getCje());
        stockInfo.put("differ", new BigDecimal(cardStockNewssetStockBO.getCurrent_price() - cardStockNewssetStockBO.getZs()).setScale(2, BigDecimal.ROUND_HALF_UP));
        stockInfo.put("differ_range", cardStockNewssetStockBO.getZdf());
        stockInfo.put("high", cardStockNewssetStockBO.getZg());
        stockInfo.put("low", cardStockNewssetStockBO.getZd());
        stockInfo.put("now", cardStockNewssetStockBO.getCurrent_price());
        stockInfo.put("pb", cardStockNewssetStockBO.getSjl());
        stockInfo.put("pe", cardStockNewssetStockBO.getSyl());
        stockInfo.put("ps", cardStockNewssetStockBO.getSxl());
        stockInfo.put("sec_uni_code", sec_uni_code);
        stockInfo.put("com_uni_code", com_uni_code);
        stockInfo.put("today_open", cardStockNewssetStockBO.getJk());
        stockInfo.put("total_market_value", cardStockNewssetStockBO.getZsz());
        stockInfo.put("total_stock_value", cardStockNewssetStockBO.getZgb());
        stockInfo.put("yesterday_close", cardStockNewssetStockBO.getZs());
        stockInfo.put("stop_sign",cardStockNewssetStockBO.getStopSign());
        stockInfo.put("key", sec_uni_code);

        return stockInfo;
    }
}
