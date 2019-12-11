package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import la.niub.abcapi.servicecompre.component.cache.KeyBuilder;
import la.niub.abcapi.servicecompre.component.client.IApiReportFieldFacetClient;
import la.niub.abcapi.servicecompre.component.client.IServiceReportClient;
import la.niub.abcapi.servicecompre.component.util.*;
import la.niub.abcapi.servicecompre.dao.IWeiboWaveNewDao;
import la.niub.abcapi.servicecompre.dao.notice.*;
import la.niub.abcapi.servicecompre.dao.reporter.INtfXuqiuAttentionDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISearchIndexDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISearchIndexDataDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISecBasicInfoDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISecPriceDayDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISecSuspendDao;
import la.niub.abcapi.servicecompre.dao.reporter.ITransacDao;
import la.niub.abcapi.servicecompre.dao.reporter.IWechatPublicDao;
import la.niub.abcapi.servicecompre.model.*;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystBasicInfoBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystDynamicBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystReportStockChartBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystReportStocksBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockBaiduBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockNewssetBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockWeiboBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockXueqiuBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardWeChatArticleBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardWeChatBO;
import la.niub.abcapi.servicecompre.model.nosql.WeiboWaveNewModel;
import la.niub.abcapi.servicecompre.model.request.client.ClientReportFieldFacetRequest;
import la.niub.abcapi.servicecompre.model.request.client.ReportRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.client.ApiReportFieldFacetResponse;
import la.niub.abcapi.servicecompre.model.response.client.ServiceSearchReportResponse;
import la.niub.abcapi.servicecompre.model.response.client.WechatArticleResponse;
import la.niub.abcapi.servicecompre.model.response.client.report.ReportFieldFacetData;
import la.niub.abcapi.servicecompre.model.response.client.report.ReportItemResponse;
import la.niub.abcapi.servicecompre.model.response.client.wechat.WechatArticleDataItemResponse;
import la.niub.abcapi.servicecompre.service.ICardService;
import la.niub.abcapi.servicecompre.service.ICompanyManagerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CardServiceImpl implements ICardService {

    private static final Logger logger = LogManager.getLogger(CardServiceImpl.class);

    @Autowired
    HttpServletRequest servletRequest;

    @Autowired
    ICompanyManagerService companyManagerService;

    @Autowired
    ISecBasicInfoDao secBasicInfoDao;

    @Autowired
    ITransacDao transacDao;

    @Autowired
    ISecSuspendDao secSuspendDao;

    @Autowired
    ISearchIndexDao searchIndexDao;

    @Autowired
    ISearchIndexDataDao searchIndexDataDao;

    @Autowired
    IWeiboWaveNewDao weiboWaveNewDao;

    @Autowired
    INtfXuqiuAttentionDao ntfXuqiuAttentionDao;

    @Autowired
    IAnalystHonorDao analystHonorDao;

    @Autowired
    IAnalystBasicinfoDao analystBasicinfoDao;

    @Autowired
    IOrganDao organDao;

    @Autowired
    IServiceReportClient serviceReportClient;

    @Autowired
    ISecPriceDayDao secPriceDayDao;

    @Autowired
    IWechatPublicDao wechatPublicDao;

    @Autowired
    IHiborAuthorCountDao hiborAuthorCountDao;

    @Autowired
    IAnalystChanStatusDao analystChanStatusDao;

    @Autowired
    IApiReportFieldFacetClient apiReportFieldFacetClient;

    @Value("${feign.client.wechatArticle.url}")
    private String wechatArticleUrl;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public CardStockBO buildStockCard(Integer code) {
        CardStockBO cardStockBO = new CardStockBO();
        //判断股票的交易市场
        SecBasicInfoModel stockInfo = secBasicInfoDao.selectBySecUniCode((long)code);
        if (stockInfo == null){
            return null;
        }

        //只显示沪深两个市场数据
        if (stockInfo.getSecurity_type() != 1003002 && stockInfo.getSecurity_type() != 1003001){
            //@todo
//            return null;
        }

        cardStockBO.setStock_info(stockInfo);
        cardStockBO.setUnit(buildStockUnit(stockInfo));
        cardStockBO.setStock_category(companyManagerService.getStockCategory(stockInfo.getSec_code()));

        //是否交易日
        Boolean currentDataIsOpen = buildCurrentDataIsOpen(stockInfo.getSecurity_type());
        Map<String, Object> transacInfo= new HashMap<>();
        transacInfo.put("transac",currentDataIsOpen);
//        transacInfo.put("date",FastDateFormat.getInstance("yyyy-MM-dd 00:00:00").format(new Date()));
        TransacModel lastTransac = transacDao.buildLastDayWithSecurityTypeAndDateAndNumberOne(stockInfo.getSecurity_type(),new Date());
        Date lastTransacDate = lastTransac != null && lastTransac.getEnd_date() != null ? lastTransac.getEnd_date() : new Date();
        transacInfo.put("date",FastDateFormat.getInstance("yyyy-MM-dd 00:00:00").format(lastTransacDate));
        cardStockBO.setTransac_info(transacInfo);

        //是否停牌
        SecSuspendModel secSuspendModel = buildIsSuspensionBySecUniCodeAndDate(stockInfo.getSec_uni_code());
        Map<String, Object> suspendInfo= new HashMap<>();
        suspendInfo.put("suspend",secSuspendModel != null);
        suspendInfo.put("info",secSuspendModel);
        cardStockBO.setSuspend_info(suspendInfo);

        //实时行情
        CardStockNewssetBO cardStockNewssetBO = companyManagerService.buildCompanyNewestStockInfo(stockInfo.getAbc_code());
        cardStockBO.setStock_newsset(cardStockNewssetBO);
        if (cardStockNewssetBO.getTime() != null){
            transacInfo.put("date",FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(cardStockNewssetBO.getTime()));
            cardStockBO.setTransac_info(transacInfo);
        }

        //行情曲线 默认日
        String startTime = "2017-10-20";
        String graphType = "d";
//        cardStockBO.setStock_share_price_change(companyManagerService.buildSharePriceChange(stockInfo.getSec_code(), TimeUtil.parseDateStr(startTime,"yyyy-MM-dd"),graphType));

        //市场 百度指数
        cardStockBO.setBaidu(buildBaiDuIndex(stockInfo.getSec_name()));

        //市场 微博指数
        cardStockBO.setWeibo(buildWeiBo(stockInfo.getSec_name()));

        //市场 雪球关注度
        cardStockBO.setXueqiu(buildXueQiao(stockInfo.getAbc_code()));

        return cardStockBO;
    }

    private String buildStockUnit(SecBasicInfoModel stockInfo){
        switch (stockInfo.getSecurity_type().intValue()){
            case 1003001:
                return "￥";
            case 1003002:
                return "￥";
            case 1003005:
                return "HK$";
            case 1003009:
                return "$";
            default:
                return "￥";
        }
    }

    private Boolean buildCurrentDataIsOpen(Long secMarket){
        String date = FastDateFormat.getInstance("yyyy-MM-dd 00:00:00").format(new Date());
        TransacModel model = transacDao.selectBySecurityType(secMarket,date);
        if(model == null){
            return false;
        }
        return model.getIf_trading_day().equals("是");
    }

    private SecSuspendModel buildIsSuspensionBySecUniCodeAndDate(Long secUniCode){
        String date = FastDateFormat.getInstance("yyyy-MM-dd 00:00:00").format(new Date());
        return secSuspendDao.selectBySecUniCode(secUniCode,date);
    }

    /**
     * 根据关键词获取百度指数
     * @param keyword
     * @return
     */
    private CardStockBaiduBO buildBaiDuIndex(String keyword){
        CardStockBaiduBO cardStockBaiduBO = new CardStockBaiduBO();
        SearchIndexModel stockBaiduInfo = searchIndexDao.selectByKeyword(keyword);
        if (stockBaiduInfo == null){
            return cardStockBaiduBO;
        }else{
            List<SearchIndexDataModel> indexDataModelList = searchIndexDataDao.selectByKeywordId(stockBaiduInfo.getKeyword_id(),30);
            Map<String,Long> ret = new TreeMap<>();
            for (SearchIndexDataModel item : indexDataModelList){
                String date = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getData_time());
                ret.put(date,item.getSearch_index());
            }
            Float rate = 0f;
            LinkedList<String> keys = new LinkedList<>(ret.keySet());
            if (keys.isEmpty()){
                return cardStockBaiduBO;
            }
            Long curIndex = ret.get(keys.removeLast());
            if (keys.size() >= 2){
                Long preIndex = ret.get(keys.removeLast());
                rate = (float)(curIndex - preIndex)/preIndex*100;
            }
            cardStockBaiduBO.setItem(ret);
            cardStockBaiduBO.setCur_index(curIndex);
            cardStockBaiduBO.setRate(rate);
        }
        return cardStockBaiduBO;
    }

    /**
     * 获取微博指数
     * @param keyword
     * @return
     */
    private CardStockWeiboBO buildWeiBo(String keyword){
        CardStockWeiboBO cardStockWeiboBO = new CardStockWeiboBO();
        List<WeiboWaveNewModel> weiboRecords = weiboWaveNewDao.getByKey(keyword,100);
        logger.info("buildWeiBo find for keyword {} result {}",keyword,weiboRecords);
        if (weiboRecords == null || weiboRecords.isEmpty()){
            return cardStockWeiboBO;
        }
        Map<String,Long> ret = new LinkedHashMap<>();
        for (WeiboWaveNewModel item : weiboRecords){
            ret.put(item.getDate(),item.getValue());
        }

        Float rate = 0f;
        LinkedList<String> keys = new LinkedList<>(ret.keySet());
        Long curIndex = ret.get(keys.removeLast());
        if (keys.size() >= 2){
            Long preIndex = ret.get(keys.removeLast());
            rate = (float)(curIndex - preIndex)/preIndex*100;
        }
        cardStockWeiboBO.setItem(ret);
        cardStockWeiboBO.setCur_index(curIndex);
        cardStockWeiboBO.setRate(rate);
        return cardStockWeiboBO;
    }

    private CardStockXueqiuBO buildXueQiao(String stockCode){
        CardStockXueqiuBO cardStockXueqiuBO = new CardStockXueqiuBO();
        List<NtfXuqiuAttentionModel> records = ntfXuqiuAttentionDao.buildRecordsByKyeword(stockCode);
        logger.info("buildXueQiao find for stockCode {} result {}",stockCode,records);
        Map<String,Long> ret = new LinkedHashMap<>();
        for (NtfXuqiuAttentionModel item : records){
            String date = FastDateFormat.getInstance("yyyy-MM-dd").format(item.getStat_date());
            ret.put(date,item.getIndex_value().longValue());
        }

        Float rate = 0f;
        LinkedList<String> keys = new LinkedList<>(ret.keySet());
        if (keys.isEmpty()){
            return cardStockXueqiuBO;
        }
        Long curIndex = ret.get(keys.removeLast());
        if (keys.size() >= 2){
            Long preIndex = ret.get(keys.removeLast());
            rate = (float)(curIndex - preIndex)/preIndex*100;
        }
        cardStockXueqiuBO.setItem(ret);
        cardStockXueqiuBO.setCur_index(curIndex);
        cardStockXueqiuBO.setRate(rate);
        return cardStockXueqiuBO;
    }

    @Override
    public CardAnalystBO buildAnalystCard(String peo_uni_code,String organ){

        CardAnalystBO cardAnalystBO = new CardAnalystBO();

        // 获取分析师基本信息
        AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peo_uni_code);
        if (analystBasicinfoModel == null){
            return null;
        }

        AnalystChanStatusModel presume = analystChanStatusDao.selectByPeoUniCode(peo_uni_code);
        Integer org_id = null;
        if(presume != null){
            if(presume.getOrg_sname() != null){
                organ = presume.getOrg_sname();
            }
            if(presume.getOrg_uni_code() != null){
                org_id = presume.getOrg_uni_code().intValue();
            }
        }
        cardAnalystBO.setBasic_info(buildAnalystBasicInfo(analystBasicinfoModel, organ, org_id));

        String author = analystBasicinfoModel.getName();

        if(StringUtils.isEmpty(organ) || StringUtils.isEmpty(author)){
            return cardAnalystBO;
        }
        // 获取分析师动态
        cardAnalystBO.setDynamic(buildAnalystDynamic(author, organ, 4));


        // 获取研究的股票
        cardAnalystBO.setReport_stocks(buildAnalystReportStocks(author, organ,1,3));

        return cardAnalystBO;

    }

    /**
     * 分析师基本信息
     * @param analystBasicinfoModel
     * @param organ
     * @return
     */
    private CardAnalystBasicInfoBO buildAnalystBasicInfo(AnalystBasicinfoModel analystBasicinfoModel,
                                                        String organ, Integer org_uni_code){
        String peo_uni_code = analystBasicinfoModel.getPeo_uni_code();
        String c_id="";
        if(organ!=null){
            OrganModel organModel = organDao.selectByPublish(organ);
            if(organModel!=null){
                c_id=organModel.getOrg_id();
            }
        }
        String cacheKey = KeyBuilder.getKeyAnalyst(peo_uni_code, "basic");
        if(!cacheKey.isEmpty()) {
            CardAnalystBasicInfoBO cardAnalystBasic = redisUtil.get(cacheKey, CardAnalystBasicInfoBO.class);
            if (cardAnalystBasic != null) {
//                System.out.println(cardAnalystBasicInfoBO.toString());
                cardAnalystBasic.setC_id(c_id);
                return cardAnalystBasic;
            }
        }
        CardAnalystBasicInfoBO cardAnalystBasicInfoBO = new CardAnalystBasicInfoBO();


        cardAnalystBasicInfoBO.setOrgan(organ);
        cardAnalystBasicInfoBO.setC_id(c_id);
        String organ_logo = "";
        if(org_uni_code != null){
            // 获取机构logo
//            OrganModel organModel = organDao.selectByPublish(organ);
            OrganModel organModel = organDao.selectByOrgId(org_uni_code);

            if (organModel != null && organModel.getLogo() != null){
                organ_logo = organModel.getLogo();

            }
        }
        cardAnalystBasicInfoBO.setOrgan_logo(organ_logo);

        cardAnalystBasicInfoBO.setId(peo_uni_code);

        String image = "";
        if (analystBasicinfoModel.getImage() != null){
            image = analystBasicinfoModel.getImage();
        }
        cardAnalystBasicInfoBO.setImage(image);

        String name = "";
        if (analystBasicinfoModel.getName() != null){
            name = analystBasicinfoModel.getName();
        }
        cardAnalystBasicInfoBO.setName(name);

        String direction = "";
        if (analystBasicinfoModel.getDirection() != null){
            direction = analystBasicinfoModel.getDirection();
        }
        cardAnalystBasicInfoBO.setDirection(direction);

        String email = "";
        if (analystBasicinfoModel.getEmail() != null){
            email = analystBasicinfoModel.getEmail();
        }
        cardAnalystBasicInfoBO.setEmail(email);

        String tel_phone = "";
        if (analystBasicinfoModel.getTel_phone() != null){
            tel_phone = analystBasicinfoModel.getTel_phone();
        }
        cardAnalystBasicInfoBO.setTel(tel_phone);

        cardAnalystBasicInfoBO.setSummary("");
        if(analystBasicinfoModel.getBack_gro()!=null){
            cardAnalystBasicInfoBO.setSummary(analystBasicinfoModel.getBack_gro());
        }

        Map paraMap=new HashMap();
        paraMap.put("honor","新财富最佳分析师");
        paraMap.put("peo_uni_code",analystBasicinfoModel.getPeo_uni_code());
        paraMap.put("order_by"," order by  time desc");
        List<AnalystHonorModel>  analystHonorModelList=analystHonorDao.selectHonor(paraMap);
        if(analystHonorModelList!=null && analystHonorModelList.size()>0){
            //如果分析师获奖，获取最新获奖领域
            cardAnalystBasicInfoBO.setH_direction(analystHonorModelList.get(0).getDirection());
        }else{
            cardAnalystBasicInfoBO.setH_direction("");
        }

        // 获奖数量
        Integer honor_num = analystHonorDao.getHonorNum(peo_uni_code);
        cardAnalystBasicInfoBO.setHonor_num(honor_num);

        // 最新新财富奖项信息
        Integer rank = 0;
        Date rank_time;
        List<AnalystHonorModel> honor_info = analystHonorDao.getHonor(peo_uni_code, 1);
        if (honor_info != null && !honor_info.isEmpty()){
            rank = honor_info.get(0).getRanking();
            cardAnalystBasicInfoBO.setRank(rank);

            rank_time = honor_info.get(0).getTime();
            cardAnalystBasicInfoBO.setTime(rank_time);

        }

        Integer report_num = 0;
        double report_num_rate = 0.00;
        // 同领域分析师研报数量
        if(StringUtils.isNotEmpty(direction)) {
            if (direction.indexOf("、") >= 0) {
                String[] directionArr = direction.split("、");
                direction = directionArr[0];
            }
        }
        if(StringUtils.isNotEmpty(direction)) {
            List<HiborAuthorCountModel> reportNumList = getAnalystReportsNum(direction);
            if(reportNumList != null && !reportNumList.isEmpty()){
                Integer i = 0;
                Integer the_rank = 0;
                for(HiborAuthorCountModel item : reportNumList){
                    i++;
                    if(peo_uni_code.equals(item.getPeo_uni_code())){
                        the_rank = i;
                        report_num = item.getReport_num();
                        break;
                    }
                }

                if(the_rank != 0){
                    report_num_rate = 1 - (double)the_rank/reportNumList.size();
                    report_num_rate = (double)Math.round(report_num_rate*100)/100;
                }
            }
        }
        cardAnalystBasicInfoBO.setReport_num(report_num);
        cardAnalystBasicInfoBO.setReport_num_rate(report_num_rate);

        redisUtil.set(cacheKey,cardAnalystBasicInfoBO,86400);
        return cardAnalystBasicInfoBO;
    }

    /**
     * 获取分析师最新动态
     * @param author
     * @param organ
     * @param limit
     * @return
     */
    private List<CardAnalystDynamicBO> buildAnalystDynamic(String author, String organ, Integer limit){
        String userId = StringUtils.defaultString(servletRequest.getParameter("userId"),"c9941cd943d74dda4b1fb51ce64d6b62");
        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setUserId(userId);
//        reportRequest.setCategories("沪深股研究");
//        reportRequest.setFirst_category("R001");
        reportRequest.setOffset(0);
        reportRequest.setLimit(limit);
        reportRequest.setOrder_by("all_score");
        reportRequest.setGroup_by("default");
        reportRequest.setSource(organ);
        reportRequest.setKeyword_filter(author);
        Response<ServiceSearchReportResponse> report = serviceReportClient.report(reportRequest);
        if(report == null || report.getData() == null){
            return null;
        }
        List<ReportItemResponse> reportItems = report.getData().getItems();

        List<CardAnalystDynamicBO> items = new ArrayList<>();

        for (ReportItemResponse item : reportItems){
            CardAnalystDynamicBO itemBO = ObjectUtil.copy(item, CardAnalystDynamicBO.class);
//            System.out.println(itemBO);
//            itemBO.setId(item.getId());
//            itemBO.setSource(item.getSource());
//            itemBO.setTime(TimeUtil.intDateToString(item.getPublish_at()));
//            itemBO.setTitle(item.getTitle());
//            itemBO.setCategory(item.getReport_type());
            items.add(itemBO);
        }

        return items;
    }

    public List<CardAnalystReportStocksBO> buildAnalystReportStocksOld(String author, String organ, Integer tab) {
        Integer limit = 3;

        List<CardAnalystReportStocksBO> reportStocksBO = new ArrayList<>();

        List<ReportItemResponse> reportItems = getAnalystReports(author, organ, "", 100, "");
        if(reportItems == null){
            return null;
        }
        Map<String, Integer> stockMap = new HashMap<String, Integer>();
        Map<String, Integer> stockTimeMap = new HashMap<String, Integer>();
        List<String> abcCodes = new ArrayList<>();

        for (ReportItemResponse item : reportItems){
            String stockCode = item.getStockcode();

            if(stockCode == null ||  stockCode.isEmpty()){
                continue;
            }
            // 股票研究次数
            if(stockMap.containsKey(stockCode)){
                stockMap.put(stockCode,stockMap.get(stockCode)+1) ;
            }else{
                stockMap.put(stockCode, 1);
            }

            // 股票研究最新时间
            if(!stockTimeMap.containsKey(stockCode)){
                stockTimeMap.put(stockCode,item.getPublish_at()) ;
            }

            if(tab == 1 && abcCodes.size()<limit && !abcCodes.contains(stockCode)){
                abcCodes.add(stockCode);
            }
//            System.out.println(stockCode+":"+stockMap.get(stockCode));
        }
        if(stockMap.isEmpty()){
            return null;
        }

        if(tab == 2){
            // 对次数进行排序
            List<Map.Entry<String,Integer>> stock_list = new ArrayList<Map.Entry<String, Integer>>(stockMap.entrySet());
            Collections.sort(stock_list, new Comparator<Map.Entry<String, Integer>>() {
                // 降序排序
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            for(Map.Entry<String,Integer> mapping:stock_list){
                if( abcCodes.size() == limit){
                    break;
                }
                abcCodes.add(mapping.getKey());
            }
//            System.out.println(stock_list.toString());
        }

//        System.out.println(reportItems.toString());
//        System.out.println(stockMap.toString());
//        System.out.println(stockTimeMap.toString());
//        System.out.println(abcCodes.toString());

        // 获取股票名称
        Map<String, String> secMap = new HashMap<>();
        if (abcCodes != null && !abcCodes.isEmpty()) {
            List<SecBasicInfoModel> secBasicInfoList = secBasicInfoDao.getListByAbcCodes(abcCodes);
            if (secBasicInfoList != null && !secBasicInfoList.isEmpty()) {
                for (SecBasicInfoModel sItem : secBasicInfoList) {
                    if (!secMap.containsKey(sItem.getAbc_code())) {
                        secMap.put(sItem.getAbc_code(), sItem.getSec_name());
                    }
                }
            }
        }

        for(String stockCode : abcCodes) {
            if(secMap.get(stockCode) == null || secMap.get(stockCode).isEmpty()){
                continue;
            }
            List<String> currentSharePriceInfoList = companyManagerService.getStockRealTimePrice(stockCode);
            CardAnalystReportStocksBO analystReportStocksBO = new CardAnalystReportStocksBO();
            if (currentSharePriceInfoList.size() < 32){
                analystReportStocksBO.setCurrent_price(0f);
                analystReportStocksBO.setZdf(0d);
            }else{
                if(!currentSharePriceInfoList.get(1).isEmpty()){
                    analystReportStocksBO.setCurrent_price(Float.valueOf(currentSharePriceInfoList.get(1)));
                }
                if(!currentSharePriceInfoList.get(3).isEmpty()){
                    analystReportStocksBO.setZdf(Double.valueOf(currentSharePriceInfoList.get(3)));
                }
            }
            analystReportStocksBO.setAbc_code(stockCode);
            analystReportStocksBO.setSec_name(secMap.get(stockCode));
            analystReportStocksBO.setLast_time(stockTimeMap.get(stockCode));
            analystReportStocksBO.setTotal(stockMap.get(stockCode));
            reportStocksBO.add(analystReportStocksBO);

        }

        return reportStocksBO;
    }

    /**
     * 分析师研究的股票
     * @param author
     * @param organ
     * @param tab 最新：1  最多：2
     * @return
     */
    @Override
    public List<CardAnalystReportStocksBO> buildAnalystReportStocks(String author, String organ, Integer tab,Integer limit) {
        //近3个月
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -3);
        String start_time = String.valueOf(c.getTimeInMillis()/1000);
        ClientReportFieldFacetRequest clientReportFieldFacetRequest = new ClientReportFieldFacetRequest();
        clientReportFieldFacetRequest.setAuthor(author);
        clientReportFieldFacetRequest.setStart_time(Integer.parseInt(start_time));
        clientReportFieldFacetRequest.setPublish(organ);

        ApiReportFieldFacetResponse reportFieldFacet = apiReportFieldFacetClient.reportFieldFacet(clientReportFieldFacetRequest);
        List<ReportFieldFacetData> reportStockData = reportFieldFacet.getData();
        if(reportStockData == null){
            return null;
        }

//        Integer limit = 3;
        Map<String, Integer> stockMap = new HashMap<String, Integer>();
        Map<String, Integer> stockTimeMap = new HashMap<String, Integer>();

        for (ReportFieldFacetData item : reportStockData){
            if(item.getCount() != null && item.getCount()>0 && !item.getName().isEmpty()){
                stockMap.put(item.getName(),item.getCount());
                stockTimeMap.put(item.getName(),item.getTime());
            }
        }

        List<String> abcCodes = new ArrayList<>();
        // 对次数|时间进行排序
        List<Map.Entry<String,Integer>> stock_list = new ArrayList<Map.Entry<String, Integer>>(
               tab == 2 ? stockMap.entrySet() : stockTimeMap.entrySet()
        );
        Collections.sort(stock_list, new Comparator<Map.Entry<String, Integer>>() {
            // 降序排序
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for(Map.Entry<String,Integer> mapping:stock_list){
            if( abcCodes.size() == limit){
                break;
            }
            abcCodes.add(mapping.getKey());
        }
//        System.out.println(stock_list.toString());
//        System.out.println(abcCodes.toString());
//        System.out.println(abcCodes.toString());

        // 获取股票名称
        Map<String, String> secMap = new HashMap<>();
        if (abcCodes != null && !abcCodes.isEmpty()) {
            List<SecBasicInfoModel> secBasicInfoList = secBasicInfoDao.getListByAbcCodes(abcCodes);
            if (secBasicInfoList != null && !secBasicInfoList.isEmpty()) {
                for (SecBasicInfoModel sItem : secBasicInfoList) {
                    if (!secMap.containsKey(sItem.getAbc_code())) {
                        secMap.put(sItem.getAbc_code(), sItem.getSec_name());
                    }
                }
            }
        }


        List<CardAnalystReportStocksBO> reportStocksBO = new ArrayList<>();
        for(String stockCode : abcCodes) {
            if(secMap.get(stockCode) == null || secMap.get(stockCode).isEmpty()){
                continue;
            }
            List<String> currentSharePriceInfoList = companyManagerService.getStockRealTimePrice(stockCode);
            CardAnalystReportStocksBO analystReportStocksBO = new CardAnalystReportStocksBO();
            if (currentSharePriceInfoList.size() < 32){
                analystReportStocksBO.setCurrent_price(0f);
                analystReportStocksBO.setZdf(0d);
            }else{
                if(!currentSharePriceInfoList.get(1).isEmpty()){
                    analystReportStocksBO.setCurrent_price(Float.valueOf(currentSharePriceInfoList.get(1)));
                }
                if(!currentSharePriceInfoList.get(3).isEmpty()){
                    analystReportStocksBO.setZdf(Double.valueOf(currentSharePriceInfoList.get(3)));
                }
            }
            analystReportStocksBO.setAbc_code(stockCode);
            analystReportStocksBO.setSec_name(secMap.get(stockCode));
            analystReportStocksBO.setLast_time(stockTimeMap.get(stockCode));
            analystReportStocksBO.setDate(TimeUtil.fromUnixtime(stockTimeMap.get(stockCode)));
            analystReportStocksBO.setTotal(stockMap.get(stockCode));
            reportStocksBO.add(analystReportStocksBO);

        }

        return reportStocksBO;
    }


    /**
     * 分析师股票评级曲线图
     * @param author
     * @param organ
     * @param abc_code
     * @return
     */
    @Override
    public CardAnalystReportStockChartBO buildAnalystReportStockChart(String author, String organ, String abc_code) {
        CardAnalystReportStockChartBO reportStockChartBO = new CardAnalystReportStockChartBO();

        SecBasicInfoModel secBasicInfo = secBasicInfoDao.selectByAbcCode(abc_code);
        if (secBasicInfo == null){
            return reportStockChartBO;
        }

        // 默认时间近3个月
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -3);
        String start_time = format.format(c.getTime());

        // 获取股票价格列表
        List<SecPriceDayModel> secPriceDayList = secPriceDayDao.getPriceList(secBasicInfo.getSec_uni_code(), start_time);
        reportStockChartBO.setStock_price(secPriceDayList);

        //获取近一年研究股票评级
        List<ReportItemResponse> reportItems = getAnalystReports(author, organ, start_time, 90, abc_code);
        if(reportItems == null){
            return null;
        }
        List<CardAnalystDynamicBO> items = new ArrayList<>();

//        System.out.println(reportItems.toString());

        for (ReportItemResponse item : reportItems){

            CardAnalystDynamicBO itemBO = ObjectUtil.copy(item, CardAnalystDynamicBO.class);

            itemBO.setId(item.getId());
            itemBO.setTime(item.getPublish_at());
            itemBO.setRating(item.getRating());
            items.add(itemBO);
        }
        reportStockChartBO.setReport_stocks(items);

        return reportStockChartBO;
    }

    /**
     * 获取分析师研报信息
     * @param author
     * @param organ
     * @param start_time
     * @param limit
     * @param stock_code
     * @return
     */
    private List<ReportItemResponse> getAnalystReports(String author,
                                                       String organ,
                                                       String start_time,
                                                       Integer limit,
                                                       String stock_code) {

        if (start_time == "") {
            // 近一年
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -3);
            start_time = format.format(c.getTime());
        }
        if(limit == 0){
            limit = 100;
        }

        String userId = StringUtils.defaultString(servletRequest.getParameter("userId"), "c9941cd943d74dda4b1fb51ce64d6b62");

        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setUserId(userId);
//        reportRequest.setCategories("沪深股研究");
//        reportRequest.setFirst_category("R001");
        reportRequest.setOffset(0);
        reportRequest.setLimit(limit);
        reportRequest.setOrder_by("all_score");
        reportRequest.setGroup_by("default");
        reportRequest.setSource(organ);
        reportRequest.setKeyword_filter(author);
        reportRequest.setStart_time(start_time);
        if(stock_code != ""){
            reportRequest.setStock_filter(stock_code);
        }
        Response<ServiceSearchReportResponse> report = serviceReportClient.report(reportRequest);

        if(report == null || report.getData() == null){
            return null;
        }
        List<ReportItemResponse> reportItems = report.getData().getItems();

        return reportItems;
    }



    @Override
    public CardWeChatBO buildWeChatCard(Integer id) {

        WechatPublicModel wechatPublicRet = wechatPublicDao.selectByPrimaryKey(id.longValue());

        CardWeChatBO cardWeChat = new CardWeChatBO();
        List<CardWeChatArticleBO> articleList = new ArrayList<>();
        if (wechatPublicRet != null ){
//            cardWeChat.setId(wechatPublicRet.getId());
//            cardWeChat.setName(wechatPublicRet.getPublicName());
//            cardWeChat.setAccount(wechatPublicRet.getPublicAccount());
//            cardWeChat.setImage(wechatPublicRet.getPublicImage());
//            cardWeChat.setSummary(wechatPublicRet.getPublicSummary());
//            cardWeChat.setAuthentication(wechatPublicRet.getPublicAuthentication());
//            cardWeChat.setTags(wechatPublicRet.getPublicTags());
//            cardWeChat.setQrCode(wechatPublicRet.getQrCode());
//            cardWeChat.setMonthCount(wechatPublicRet.getMonthCount());
//            cardWeChat.setSource(wechatPublicRet.getSource());
            cardWeChat.setId(wechatPublicRet.getId().intValue());
            cardWeChat.setName(wechatPublicRet.getPublic_name());
            cardWeChat.setAccount(wechatPublicRet.getPublic_account());
            cardWeChat.setImage(wechatPublicRet.getPublic_image());
            cardWeChat.setSummary(wechatPublicRet.getPublic_summary());
            cardWeChat.setAuthentication(wechatPublicRet.getPublic_authentication());
            cardWeChat.setTags(wechatPublicRet.getPublic_tags());
            cardWeChat.setQrCode(wechatPublicRet.getQr_code());
            cardWeChat.setMonthCount(wechatPublicRet.getMonth_count());
            cardWeChat.setSource(wechatPublicRet.getSource());

            String classify = wechatPublicRet.getClassify();
            if (StringUtil.isEmpty(classify)) {
                classify = "未知";
            }
            cardWeChat.setClassify(classify);


            Map<String, String> params = new HashMap<>();
            params.put("single", "true");
            params.put("core_name", "core_news");
            try {
                params.put("keyword", URLEncoder.encode("$source_name:\"" + wechatPublicRet.getPublic_name() + "\"","UTF-8"));
            } catch (UnsupportedEncodingException e) {

            }
            params.put("limit", "4");
            params.put("offset", "0");
            params.put("prior","time");

            String wechatArtilceRet = HttpUtil.get(wechatArticleUrl, params, null);

            if (wechatArtilceRet != null && !wechatArtilceRet.isEmpty()) {
                WechatArticleResponse wechatArticleResponse = JSON.parseObject(wechatArtilceRet, WechatArticleResponse.class);
                if (wechatArticleResponse.getData() != null) {
                    if ( wechatArticleResponse.getData().getItem() != null && !wechatArticleResponse.getData().getItem().isEmpty()) {

                        for (WechatArticleDataItemResponse item : wechatArticleResponse.getData().getItem()){
                            CardWeChatArticleBO artilceItem = new CardWeChatArticleBO();
                            artilceItem.setId(item.getId());
                            artilceItem.setName(item.getSource_name());
                            artilceItem.setTitle(item.getTitle());
                            artilceItem.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(item.getTime()) * 1000));
                            artilceItem.setUrl(item.getUrl());
                            articleList.add(artilceItem);
                        }

                    }
                }
            }
        }

        cardWeChat.setArtilceList(articleList);

        return cardWeChat;
    }

    /**
     * 统计该领域近一年分析师的研报数量
     * @param direction
     * @return
     */
    @Override
    public List<HiborAuthorCountModel> getAnalystReportsNum(String direction) {
        String cacheKey = KeyBuilder.getKeyAnalyst(direction, "report");
        String cacheResult = redisUtil.get(cacheKey);
        List<HiborAuthorCountModel> reportNumList = null;
        if (cacheResult != null) {
            return JSON.parseObject(cacheResult, new TypeReference<List<HiborAuthorCountModel>>() {});
        }
        // 同领域分析师研报数量
        List<AnalystBasicinfoModel> analystList = analystBasicinfoDao.getAnalystByDirection(direction);
        if (analystList != null && !analystList.isEmpty()) {
            List<String> peoUniCodes = new ArrayList<>();
            for (AnalystBasicinfoModel item : analystList) {
                if (item.getPeo_uni_code() != null && !item.getPeo_uni_code().isEmpty()) {
                    peoUniCodes.add(item.getPeo_uni_code());
                }
            }
            // 获取近一年研报数量
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, -1);
            String start_time = String.valueOf(c.getTimeInMillis() / 1000);
            try {
                reportNumList = hiborAuthorCountDao.getReportNumByPeoUniCodesAndTime(peoUniCodes, Integer.parseInt(start_time), 0);
            } catch (Exception e) {

            }
            if(reportNumList != null){
                redisUtil.set(cacheKey, JSON.toJSON(reportNumList), 86400);
            }
        }

        return reportNumList;
    }
}
