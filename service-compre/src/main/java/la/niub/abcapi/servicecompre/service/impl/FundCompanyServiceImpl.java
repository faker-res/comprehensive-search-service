package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import la.niub.abcapi.servicecompre.component.cache.KeyBuilder;
import la.niub.abcapi.servicecompre.component.client.IServiceNoticeClient;
import la.niub.abcapi.servicecompre.component.client.IServiceReportClient;
import la.niub.abcapi.servicecompre.component.exception.ValidatorException;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.RedisUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.config.code.FundCompanyEnumCode;
import la.niub.abcapi.servicecompre.dao.reporter.*;
import la.niub.abcapi.servicecompre.model.*;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyManagerBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundManagerBO;
import la.niub.abcapi.servicecompre.model.request.*;
import la.niub.abcapi.servicecompre.model.request.client.ClientNoticeRequest;
import la.niub.abcapi.servicecompre.model.request.client.ReportRequest;
import la.niub.abcapi.servicecompre.model.response.*;
import la.niub.abcapi.servicecompre.model.response.client.ApiNewsResponse;
import la.niub.abcapi.servicecompre.model.response.client.ClientNoticeResponse;
import la.niub.abcapi.servicecompre.model.response.client.ServiceSearchReportResponse;
import la.niub.abcapi.servicecompre.model.response.client.notice.NoticeItem;
import la.niub.abcapi.servicecompre.model.response.client.report.ReportItemResponse;
import la.niub.abcapi.servicecompre.model.response.message.MessageResponse;
import la.niub.abcapi.servicecompre.service.IFundCompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FundCompanyServiceImpl implements IFundCompanyService{

    @Autowired
    private IFundCompanyDao iFundCompanyDao;

    @Autowired
    IOrgBasicInfoDao orgBasicInfoDao;

    @Autowired
    IFundBasicInfoDao fundBasicInfoDao;

    @Autowired
    IFundManagerBasicInfoDao fundManagerBasicInfoDao;

    @Autowired
    IFundManagerStatDao fundManagerStatDao;

    @Autowired
    IFundManagerPerfDao fundManagerPerfDao;

    @Autowired
    IPeoImageDao peoImageDao;

    @Autowired
    IntfEntityLawsuitDao ntfEntityLawsuitDao;

    private String peoImageOssHost = "http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/";

    @Value("${feign.client.apiNews.url}")
    private String wechatSearch;

    @Value("${feign.client.apiNews.url}")
    private String apiNewsUrl;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    IServiceNoticeClient serviceNoticeClient;

    @Autowired
    IServiceReportClient serviceReportClient;

    @Autowired
    IFundManagerChgDao fundManagerChgDao;

    @Autowired
    IFundKeeperScaleDao fundKeeperScaleDao;

    @Autowired
    private ISecBasicInfoDao secBasicInfoDao;

    @Autowired
    private ISecPlateInfoDao secPlateInfoDao;

    @Autowired
    private IFundNavDao fundNavDao;

    @Autowired
    private IFundNavReturnDao fundNavReturnDao;

    private static final Logger logger = LogManager.getLogger(FundCompanyServiceImpl.class);

    @Override
    public List<MessageResponse> getFundCompanyWechatList(Long com_uni_code) throws Exception {
        List<MessageResponse> messageResponseList = new ArrayList<>();
        List<String> wechatSubscriptionNameList = iFundCompanyDao.getWechatSubscriptionNameByComUniCode(com_uni_code);
        if (wechatSubscriptionNameList.size() != 0) {
            StringBuffer source_name = new StringBuffer("$source_name:(");
            for (int i = 0; i < wechatSubscriptionNameList.size(); i++) {
                source_name.append((i == 0 ? "\"" : ",\"" ) + wechatSubscriptionNameList.get(i) + "\"");
            }
            source_name.append(")");

            Map<String, String> wechatSubscriptionRequest = new HashMap<>();
            wechatSubscriptionRequest.put("keyword", source_name.toString());
            wechatSubscriptionRequest.put("offset", "0");
            wechatSubscriptionRequest.put("limit", "10");
            wechatSubscriptionRequest.put("prior", "time");
            wechatSubscriptionRequest.put("channel", "");
            wechatSubscriptionRequest.put("core_name", "core_news");
            wechatSubscriptionRequest.put("single", "false");

            String result = HttpUtil.post(wechatSearch, wechatSubscriptionRequest, null);
            JSONObject resultObj = JSONObject.parseObject(result);
            JSONObject data = resultObj.getJSONObject("data");
            if (!StringUtil.isEmpty(data)) {
                JSONArray items = data.getJSONArray("item");
                if (!StringUtil.isEmpty(items)) {
                    for (Object itemObj : items) {
                        MessageResponse messageResponse = new MessageResponse();
                        JSONObject item = JSONObject.parseObject(itemObj.toString());
                        if (!StringUtil.isEmpty(item)) {
                            String title = item.getString("title");
                            if (!StringUtil.isEmpty(title)) {
                                title = title.replace("<font color='red'>", "")
                                        .replace("</font>", "");
                            }

                            String url = item.getString("url");
                            Long time = item.getLong("time");

                            messageResponse.setTitle(title);
                            messageResponse.setUrl(url);
                            messageResponse.setTime(new Date(time * 1000));
                            messageResponseList.add(messageResponse);
                        }
                    }
                }
            }
        }

        return messageResponseList;
    }

    @Override
    @Cacheable(cacheNames = KeyBuilder.KEY_ORG)
    public OrgBasicInfoModel getCompanyByOrgName(String orgName) {
        return orgBasicInfoDao.selectByOrgName(orgName.trim());
    }


    public FundCompanyManagerBO manager(FundCompanyManagerRequest fundCompanyManagerRequest) throws Throwable {

        Long comId = fundCompanyManagerRequest.getComId();
        int limit = fundCompanyManagerRequest.getLimit();
        if (limit <= 0 ) {
            limit = 3;
        }
        OrgBasicInfoModel orgBasicInfo = getOrgBasicInfo(comId);

        // 公司下基金
        List<FundBasicInfoModel> fundInfoList = fundBasicInfoDao.selectByManaUniCode(comId);

        FundCompanyManagerBO fundCompanyManager = new FundCompanyManagerBO();
        List<FundManagerBO> managerNowList = new ArrayList<>();
        List<FundManagerBO> managerHistoryList = new ArrayList<>();

        if (fundInfoList != null && !fundInfoList.isEmpty()) {
            List<Long> fundIds = new ArrayList<>();
            for (FundBasicInfoModel fundItem : fundInfoList) {
                fundIds.add(fundItem.getSec_uni_code());
            }

            if (!fundIds.isEmpty()) {
                List<FundManagerBasicInfoModel> managerInfoList = fundManagerBasicInfoDao.selectBySecUniCodes(fundIds);

                if (managerInfoList != null && !managerInfoList.isEmpty()) {
                    List<Long> peoUniCodes = new ArrayList<>();
                    List<FundManagerBasicInfoModel> basicList = new ArrayList<>();
                    // 在职
                    int i = 0;
                    for (FundManagerBasicInfoModel managerItem : managerInfoList) {
                        if (i>=limit) {
                            break;
                        }
                        if (!peoUniCodes.contains(managerItem.getPeo_uni_code()) && managerItem.getEnd_date() == null) {
                            basicList.add(managerItem);
                            peoUniCodes.add(managerItem.getPeo_uni_code());
                            i++;
                        }
                    }

                    Map<Long, FundManagerStatModel> statMap = new HashMap<>();
                    Map<Long, Double> perfMap = new HashMap<>();
                    Map<Long, String> imageMap = new HashMap<>();

                    if (!peoUniCodes.isEmpty()) {

                        // 任职起始日
                        List<FundManagerStatModel> managerStatList = fundManagerStatDao.selectByPeoUniCodes(peoUniCodes);
                        if (managerStatList != null && !managerStatList.isEmpty()) {
                            for (FundManagerStatModel statItem : managerStatList) {
                                statMap.put(statItem.getPeo_uni_code(), statItem);
                            }
                        }

                        // 任期年化收益
                        List<FundManagerPerfModel> managerPerfList = fundManagerPerfDao.selectMaxAnnualYieldByPeoUniCodesGroupByPeoUniCodes(peoUniCodes);
                        if (managerPerfList != null && !managerPerfList.isEmpty()) {
                            for (FundManagerPerfModel perfItem : managerPerfList) {
                                perfMap.put(perfItem.getPeo_uni_code(), perfItem.getAnnual_yield() != null ? perfItem.getAnnual_yield().doubleValue() : (double)0);
                            }
                        }

                        // 头像
                        List<PeoImageModel> imageList = peoImageDao.selectByPeoUniCodes(peoUniCodes);
                        if (imageList != null && !imageList.isEmpty()) {
                            for (PeoImageModel imageItem : imageList) {
                                String ossPath = imageItem.getOss_path();
                                if (ossPath != null && !ossPath.isEmpty()) {
                                    imageMap.put(imageItem.getPeo_uni_code(), peoImageOssHost + ossPath);
                                }
                            }
                        }


                        for (FundManagerBasicInfoModel basicItem : basicList) {
                            FundManagerBO fundManager = new FundManagerBO();
                            Long peoUniCode = basicItem.getPeo_uni_code();
                            fundManager.setId(peoUniCode);
                            fundManager.setName(basicItem.getFund_manager_name());
                            fundManager.setCompany_name(orgBasicInfo.getOrg_name());
                            fundManager.setPosition("基金经理");
                            if (imageMap.containsKey(peoUniCode)) {
                                fundManager.setHead_img(imageMap.get(peoUniCode));
                            }
                            if (statMap.containsKey(peoUniCode)) {
                                FundManagerStatModel stat = statMap.get(peoUniCode);
                                fundManager.setStart_time(stat.getFund_manager_beg_date());
                                fundManager.setOffice_term(stat.getFund_manager_tot_days() != null ?stat.getFund_manager_tot_days().intValue() : 0);
                                fundManager.setAmount(stat.getFund_manage_nav());
                            }

                            if (perfMap.containsKey(peoUniCode)) {
                                fundManager.setReturn_ratio(perfMap.get(peoUniCode));
                            }

                            managerNowList.add(fundManager);
                        }
                    }




                }


            }
        }


        fundCompanyManager.setManager_now(managerNowList);
        fundCompanyManager.setManager_history(managerHistoryList);
        return fundCompanyManager;

    }

    private OrgBasicInfoModel getOrgBasicInfo(Long comId) throws Throwable {
        if (comId <= 0) {
            throw new ValidatorException(FundCompanyEnumCode.EMPTY_COM_ID);
        }
        OrgBasicInfoModel orgBasicInfo = orgBasicInfoDao.selectByComUniCode(comId);
        if (orgBasicInfo == null) {
            throw new ValidatorException(FundCompanyEnumCode.ERROR_COM_ID);
        }
        return orgBasicInfo;
    }

    public FundCompanyLawsuitResponse lawsuit(FundCompanyLawsuitRequest fundCompanyLawsuitRequest) throws Throwable {
        Long comId = fundCompanyLawsuitRequest.getComId();
        getOrgBasicInfo(comId);

        List<String> caseTypes = new ArrayList<String>(){{
            add("民事案件");
            add("执行案件");
            add("刑事案件");
            add("行政案件");
            add("赔偿案件");
            add("知识产权");
            add("驳回案件");
        }};


        List<FundCompanyLawsuitListItemResponse> lawsuitItemList = new ArrayList<>();
        for (String t : caseTypes) {
            List<ntfEntityLawsuitModel> lawsuitList = ntfEntityLawsuitDao.selectByComUniCode(comId, t, 4, 0);
            if (lawsuitList != null &&! lawsuitList.isEmpty()) {
                for (ntfEntityLawsuitModel item : lawsuitList) {
                    FundCompanyLawsuitListItemResponse lawsuitItem = new FundCompanyLawsuitListItemResponse();
                    lawsuitItem.setJudgeTime(item.getJudgetime());
                    lawsuitItem.setTitle(item.getTitle());
                    lawsuitItem.setUrl(item.getUrl());
                    lawsuitItem.setCaseNo(item.getCaseno());
                    lawsuitItem.setInfo(item.getPartyInfo());
                    lawsuitItem.setCourt(item.getCourt());
                    lawsuitItem.setCaseType(t);
                    lawsuitItemList.add(lawsuitItem);
                }
            }
        }

        if (!lawsuitItemList.isEmpty()) {
            Collections.sort(lawsuitItemList, new Comparator<FundCompanyLawsuitListItemResponse>() {
                @Override
                public int compare(FundCompanyLawsuitListItemResponse o1, FundCompanyLawsuitListItemResponse o2) {
                    return Integer.valueOf(o2.getJudgeTime().replace("-", "")) -
                            Integer.valueOf(o1.getJudgeTime().replace("-", ""));
                }
            });
        }

        // 柱状图
        List<FundCompanyLawsuitColumnItemResponse> column= new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        int year = Integer.valueOf(df.format(new Date()));
        int beginYear = year - 2;
        Date beginDate =  df2.parse(String.valueOf(beginYear) + "-01-01");
        Date endDate = df2.parse(String.valueOf(year) + "-12-31");
        List<NtfEntityLawsuitGroupByCaseTypeAndYearModel> lawsuitCountList =ntfEntityLawsuitDao.selectByComIdAndYearGroupByCaseTypeAndYear(comId, beginDate, endDate );
        Map<String , Map<Integer, Integer>> countCacheMap = new HashMap<>();
        if (lawsuitCountList != null && !lawsuitCountList.isEmpty()) {
            for (NtfEntityLawsuitGroupByCaseTypeAndYearModel countItem : lawsuitCountList) {
                String caseType = countItem.getCasetype();
                int judgeYear = countItem.getJudgeYear();
                int count = countItem.getCount();
                if (countCacheMap.containsKey(caseType)) {
                    Map<Integer, Integer> mVal = countCacheMap.get(caseType);
                    mVal.put(judgeYear, count);
                    countCacheMap.replace(caseType, mVal);
                } else {
                    Map<Integer, Integer> mVal = new HashMap<>();
                    mVal.put(judgeYear, count);
                    countCacheMap.put(caseType, mVal);
                }
            }
        }

        for (String cT : caseTypes) {
            FundCompanyLawsuitColumnItemResponse cItem = new FundCompanyLawsuitColumnItemResponse();

            Map<Integer, Integer> dataMap= new HashMap<>();
            for (int i = year; i >= beginYear; i--) {
                if (countCacheMap.containsKey(cT) && countCacheMap.get(cT).containsKey(i)) {
                    dataMap.put(i, countCacheMap.get(cT).get(i));
                } else {
                    dataMap.put(i, 0);
                }
            }
            cItem.setName(cT);
            cItem.setData(dataMap);
            column.add(cItem);
        }



        FundCompanyLawsuitResponse fundCompanyLawsuitResponse = new FundCompanyLawsuitResponse();
        fundCompanyLawsuitResponse.setCaseTypes(caseTypes);
        fundCompanyLawsuitResponse.setLawsuitList(lawsuitItemList);
        fundCompanyLawsuitResponse.setColumn(column);
        return fundCompanyLawsuitResponse;
    }

    @Override
    public FundCompanyNewsResponse news(FundCompanyNewsRequest fundCompanyNewsRequest) throws Throwable {
        Long comId = fundCompanyNewsRequest.getComId();
        int limitNews = fundCompanyNewsRequest.getLimitNews();
        if (limitNews <= 0 ) {
            limitNews = 6;
        }
        int limitNotice = fundCompanyNewsRequest.getLimitNotice();
        if (limitNotice <= 0 ) {
            limitNotice = 6;
        }
        int limitReport = fundCompanyNewsRequest.getLimitReport();
        if (limitReport <= 0 ) {
            limitReport = 5;
        }
        OrgBasicInfoModel orgInfo = getOrgBasicInfo(comId);

        List<FundCompanyNewsNewsItemResponse> newsList = new ArrayList<>();

        //新闻
        List<ApiNewsDataItemResponse> newsDataItemList = null;
        try {
            Map<String, String> newsRequest = new HashMap<>();
            newsRequest.put("keyword", orgInfo.getOrg_sname());
            newsRequest.put("offset", "0");
            newsRequest.put("limit", String.valueOf(limitNews));
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
            logger.error("fund company news get news error : " + e.getMessage());
        }

        if (newsDataItemList != null && !newsDataItemList.isEmpty()) {
            for (ApiNewsDataItemResponse item : newsDataItemList) {
                FundCompanyNewsNewsItemResponse newsItem = new FundCompanyNewsNewsItemResponse();
                newsItem.setId(item.getId());
                newsItem.setTitle(item.getTitle());
                newsItem.setUrl(item.getUrl());
                newsItem.setAuthor(item.getAuthor());
                newsItem.setSourceName(item.getSource_name());
                newsItem.setTime(item.getTime());
                newsList.add(newsItem);
            }
        }

        // 公告
        List<FundCompanyNewsNoticeItemResponse> noticeList = new ArrayList<>();

        List<NoticeItem> noticeDataItemList = null;
        try {
            ClientNoticeRequest request = new ClientNoticeRequest();
            request.setKeyword_filter(orgInfo.getOrg_sname());
            request.setOffset(0);
            request.setLimit(limitNotice);
            ClientNoticeResponse noticeRet = serviceNoticeClient.report(request);
            if (noticeRet != null && noticeRet.getErr_code() == 0) {
                noticeDataItemList = noticeRet.getData().getItem();
            }
        } catch (Exception e) {
            logger.error("fund company news get notice error : " + e.getMessage());
        }

        if (noticeDataItemList != null && !noticeDataItemList.isEmpty()) {
            Map<String, String> allCategory = getFlatCategory();
            for (NoticeItem item : noticeDataItemList) {
                FundCompanyNewsNoticeItemResponse noticeItem = new FundCompanyNewsNoticeItemResponse();
                noticeItem.setSrc_id(item.getSrc_id());
                noticeItem.setTitle(item.getTitle());
                noticeItem.setTime(item.getPublish_at());

                String category = "";
                List<String> categories = item.getCategory();
                if (categories != null && !categories.isEmpty()) {
                    String lowestCategory = getLowest(categories);
                    if (lowestCategory != "" && !lowestCategory.isEmpty()) {
                        if (allCategory != null && !allCategory.isEmpty() && allCategory.containsKey(lowestCategory)) {
                            category = allCategory.get(lowestCategory);
                        }
                    }
                }
                noticeItem.setCategory(category);
                noticeList.add(noticeItem);
            }
        }


        // 研报
        List<FundCompanyNewsReportItemResponse> reportList = new ArrayList<>();
        List<ReportItemResponse> reportDataItemList = null;
        try {
            ReportRequest reportRequest = new ReportRequest();
            reportRequest.setUserId(fundCompanyNewsRequest.getUser_id());
            reportRequest.setOffset(0);
            reportRequest.setLimit(limitReport);
            reportRequest.setOrder_by("all_score");
            reportRequest.setGroup_by("default");
            reportRequest.setKeyword_filter(orgInfo.getOrg_sname());
            Response<ServiceSearchReportResponse>  reportRet = serviceReportClient.report(reportRequest);
            if (reportRet !=null && reportRet.getCode() == 200) {
                if (reportRet.getData() != null ){
                    List<ReportItemResponse> reportDataItems = reportRet.getData().getItems();
                    if (reportDataItems != null && !reportDataItems.isEmpty()) {
                        reportDataItemList = reportDataItems;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("fund company news get report error : " + e.getMessage());
        }

        if (reportDataItemList != null && !reportDataItemList.isEmpty()) {
            for (ReportItemResponse item : reportDataItemList) {
                FundCompanyNewsReportItemResponse reportItem = new FundCompanyNewsReportItemResponse();
                reportItem.setId(item.getId());
                reportItem.setTitle(item.getTitle());
                reportItem.setUrl(item.getUrl());
                reportItem.setCategory(item.getCategory());
                reportItem.setReport_type(item.getReport_type());
                reportItem.setTime(item.getTime());
                reportItem.setAnalyst_honor(item.getAnalyst_honor());
                reportList.add(reportItem);
            }
        }


        FundCompanyNewsResponse fundCompanyNewsResponse = new FundCompanyNewsResponse();
        fundCompanyNewsResponse.setComId(comId);
        fundCompanyNewsResponse.setOrgName(orgInfo.getOrg_name());
        fundCompanyNewsResponse.setOrgSname(orgInfo.getOrg_sname());
        fundCompanyNewsResponse.setNews(newsList);
        fundCompanyNewsResponse.setNotice(noticeList);
        fundCompanyNewsResponse.setReport(reportList);
        return fundCompanyNewsResponse;
    }


    public static String getLowest(List<String> induCodeList){
        if(induCodeList == null || induCodeList.size()==0){
            return "";
        }
        String[] allCode = new String[4];

        for(String induCode: induCodeList){
            if(induCode==null || induCode.length()==0){
                continue;
            }
            String firstLetter = induCode.substring(0, 1);
            if(firstLetter.equals("F")){
                allCode[0] = induCode;
            }else if(firstLetter.equals("S")){
                allCode[1] = induCode;
            }else if(firstLetter.equals("T")){
                allCode[2] = induCode;
            }else {
                allCode[3] = induCode;
            }
        }

        for(Integer i=2; i>=0; i--){
            if(allCode[i]!=null && allCode[i]!=""){
                return allCode[i];
            }
        }

        return "";
    }

    private Map<String, String> getFlatCategory() {
        String value = redisUtil.get(KeyBuilder.getFlatNoticeCategoriesKey());
        Map<String, String> ret = new HashMap<>();
        if (value !=null && !value.isEmpty()) {
            ret = JSON.parseObject(value, Map.class);
        }
        return ret;
    }


    @Override
    public FundCompanyKpiResponse kpi(FundCompanyKpiRequest fundCompanyKpiRequest) throws Throwable {
        Long comId = fundCompanyKpiRequest.getComId();
        OrgBasicInfoModel orgInfo = getOrgBasicInfo(comId);

        SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        Map<Long, List<Map<Integer, FundCompanyKpiItemResponse>>> info = new HashMap<>();
        int year = Integer.valueOf(df.format(new Date()));
        for (int i = 0; i<4 ;i ++) {
            Date beginDate =  df2.parse(String.valueOf(year) + "-01-01");
            Date endDate = df2.parse(String.valueOf(year) + "-12-31");

//            String cacheChgKey = KeyBuilder.getFundManagerChgByYearGroupByFundKeeperKey(String.valueOf(beginDate.getTime()), String.valueOf(endDate.getTime()));
//            String cacheChgValue = redisUtil.get(cacheChgKey);
//            List<FundMangerChgGroupByFundKeeperModel> managerChaList = null;
//            if (cacheChgValue != null && !cacheChgValue.isEmpty()) {
//                try {
//                    managerChaList = JSON.parseArray(cacheChgValue, FundMangerChgGroupByFundKeeperModel.class);
//                } catch (JSONException e) {
//
//                }
//            }
//
//            if (managerChaList == null) {
//                managerChaList = fundManagerChgDao.selectByYearGroupByFundKeeper(beginDate, endDate);
//
//                redisUtil.set(cacheChgKey, JSON.toJSONString(managerChaList),3600L);
//            }

            List<FundMangerChgGroupByFundKeeperModel> managerChaList = fundManagerChgDao.selectByYearGroupByFundKeeper(beginDate, endDate);



            if (managerChaList != null && !managerChaList.isEmpty()) {
                for (FundMangerChgGroupByFundKeeperModel chaItem : managerChaList) {
                    Long cId = chaItem.getFund_keeper_code();
                    if (cId > 0 ){
                        if (info.containsKey(cId)) {
                            List<Map<Integer, FundCompanyKpiItemResponse>> infoVal = info.get(cId);
                            boolean exist = false;
                            for (Map<Integer, FundCompanyKpiItemResponse> item1 : infoVal) {
                                for (Map.Entry<Integer, FundCompanyKpiItemResponse> mItem1 : item1.entrySet()) {
                                    if (mItem1.getKey() == year) {
                                        FundCompanyKpiItemResponse mVal1 = mItem1.getValue();
                                        mVal1.setManagerCount(chaItem.getFund_manager_quantity());
                                        exist = true;
                                        item1.replace(year, mVal1);
                                        break;
                                    }
                                }
                            }

                            if (exist == false) {
                                FundCompanyKpiItemResponse kItem = new FundCompanyKpiItemResponse();
                                kItem.setManagerCount(chaItem.getFund_manager_quantity());
                                Map<Integer, FundCompanyKpiItemResponse> mNItem = new HashMap<>();
                                mNItem.put(year, kItem);
                                infoVal.add(mNItem);
                                info.replace(cId, infoVal);
                            }
                        } else {
                            // 不存在
                            List<Map<Integer, FundCompanyKpiItemResponse>> infoValList = new ArrayList<>();
                            Map<Integer, FundCompanyKpiItemResponse> newMap = new HashMap<>();
                            FundCompanyKpiItemResponse newKpiItem = new FundCompanyKpiItemResponse();
                            newKpiItem.setManagerCount(chaItem.getFund_manager_quantity());
                            newMap.put(year, newKpiItem);
                            infoValList.add(newMap);
                            info.put(cId, infoValList);
                        }
                    }

                }
            }


//            String cacheScaleKey = KeyBuilder.getFundManagerScaleByYearGroupByFundKeeperKey(String.valueOf(beginDate.getTime()), String.valueOf(endDate.getTime()));
//            String cacheScaleValue = redisUtil.get(cacheScaleKey);
//            List<FundKeeperScaleGroupByFundKeeperModel> scaleList = null;
//            if (cacheScaleValue != null && !cacheScaleValue.isEmpty()) {
//                try {
//                    scaleList = JSON.parseArray(cacheScaleValue, FundKeeperScaleGroupByFundKeeperModel.class);
//                } catch (JSONException e) {
//
//                }
//            }
//
//            if (scaleList == null) {
//                scaleList = fundKeeperScaleDao.selectByYearGroupByKeeper(beginDate, endDate);
//                redisUtil.set(cacheScaleKey, JSON.toJSONString(scaleList),3600L);
//            }

            List<FundKeeperScaleGroupByFundKeeperModel> scaleList = fundKeeperScaleDao.selectByYearGroupByKeeper(beginDate, endDate);


            if (scaleList != null && !scaleList.isEmpty()) {
                for (FundKeeperScaleGroupByFundKeeperModel scaleItem : scaleList) {
                    Long cId = scaleItem.getFund_keeper_code();
                    if (cId > 0 ){
                        if (info.containsKey(cId)) {
                            List<Map<Integer, FundCompanyKpiItemResponse>> infoVal = info.get(cId);
                            boolean exist = false;
                            for (Map<Integer, FundCompanyKpiItemResponse> item1 : infoVal) {
                                for (Map.Entry<Integer, FundCompanyKpiItemResponse> mItem1 : item1.entrySet()) {
                                    if (mItem1.getKey() == year) {
                                        FundCompanyKpiItemResponse mVal1 = mItem1.getValue();
                                        mVal1.setFundCount(scaleItem.getFund_quantity());
                                        mVal1.setFundMoney(scaleItem.getFund_share().floatValue());
                                        exist = true;
                                        item1.replace(year, mVal1);
                                        break;
                                    }
                                }
                            }

                            if (exist == false) {
                                FundCompanyKpiItemResponse kItem = new FundCompanyKpiItemResponse();
                                kItem.setFundCount(scaleItem.getFund_quantity());
                                kItem.setFundMoney(scaleItem.getFund_share().floatValue());
                                Map<Integer, FundCompanyKpiItemResponse> mNItem = new HashMap<>();
                                mNItem.put(year, kItem);
                                infoVal.add(mNItem);
                                info.replace(cId, infoVal);
                            }
                        } else {
                            // 不存在
                            List<Map<Integer, FundCompanyKpiItemResponse>> infoValList = new ArrayList<>();
                            Map<Integer, FundCompanyKpiItemResponse> newMap = new HashMap<>();
                            FundCompanyKpiItemResponse newKpiItem = new FundCompanyKpiItemResponse();
                            newKpiItem.setFundCount(scaleItem.getFund_quantity());
                            newKpiItem.setFundMoney(scaleItem.getFund_share().floatValue());
                            newMap.put(year, newKpiItem);
                            infoValList.add(newMap);
                            info.put(cId, infoValList);
                        }
                    }

                }
            }

            year--;
        }


        FundCompanyKpiResponse fundCompanyKpiResponse = new FundCompanyKpiResponse();
        fundCompanyKpiResponse.setComId(comId);
        fundCompanyKpiResponse.setOrg_name(orgInfo.getOrg_name());
        fundCompanyKpiResponse.setOrg_sname(orgInfo.getOrg_sname());
        fundCompanyKpiResponse.setInfo(info);
        return fundCompanyKpiResponse;
    }

    @Override
    public List<Map<String, String>> getProductCategory(Long com_uni_code) throws Exception {
        List<Long> secUniCodeList = secBasicInfoDao.getSecUniCodeByComUniCodeAndSecTypeAndSecSmallType(com_uni_code);
        if (secUniCodeList == null || secUniCodeList.isEmpty()) {
            return null;
        }

        List<Map<String, String>> result = secPlateInfoDao.getPlateNameAndCodeBySecUniCodeList(secUniCodeList);

        if (result == null) {
            result = new ArrayList<>();
        }

        Map<String, String> map = new HashMap<>();
        map.put("plate_name", "全部");
        map.put("plate_code", "");
        result.add(0, map);

        return result;
    }

    @Override
    public Map<String, Object> getProductInfo(FundCompanyProductInfoRequest request) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<Long> secUniCodeList = secBasicInfoDao.getSecUniCodeByComUniCodeAndSecTypeAndSecSmallType(request.getCom_uni_code());

        if (secUniCodeList == null || secUniCodeList.isEmpty()) {
            result.put("total", 0);
            result.put("item", new ArrayList<>());
            return result;
        }

        if (!StringUtil.isEmpty(request.getPlate_code())) {
            secUniCodeList = secPlateInfoDao.getSecUniCodeListBySecUniCodeListAndPlateCode(secUniCodeList, request.getPlate_code());
        }

        if (secUniCodeList == null || secUniCodeList.isEmpty()) {
            result.put("total", 0);
            result.put("item", new ArrayList<>());
            return result;
        }

        String oder_field = request.getOrder_field();
        boolean isValidOrderField = !StringUtil.isEmpty(oder_field) && ("unit_nav".equals(oder_field) || "cumu_unit_nav".equals(oder_field)
                || "rise_rate".equals(oder_field) || "nav_return_l_1w".equals(oder_field) || "nav_return_l_1m".equals(oder_field)
                || "nav_return_l_3m".equals(oder_field) || "nav_return_l_6m".equals(oder_field) || "nav_return_t_1y".equals(oder_field));
        if (!isValidOrderField) {
            oder_field = "unit_nav";
        }

        String order_type = request.getOrder_type();
        boolean isValidOrderType = !StringUtil.isEmpty(order_type) && ("desc".equals(order_type) || "asc".equals(order_type));
        if (!isValidOrderType) {
            order_type = "desc";
        }

        Integer page_num = request.getPage_num();
        if (page_num == null || page_num < 1) {
            page_num = 1;
        }

        Integer limit = request.getLimit();
        if (limit == null || limit < 1) {
            limit = 9;
        }

        Integer offset = (page_num - 1) * limit;

        List<Map<String, Object>> item = getFundInfoBySecUniCodeList(secUniCodeList, oder_field, order_type, offset, limit);

        result.put("total", secUniCodeList.size());
        result.put("item", item);

        return result;
    }

    private List<Map<String, Object>> getFundInfoBySecUniCodeList(List<Long> secUniCodeList, String order_field,
                                                                  String order_type, Integer offset, Integer limit) throws Exception {

        String order = order_field + " is null, " + order_field + " " + order_type + ", " + "sbi.sec_uni_code" + " " + order_type;
        List<Map<String, Object>> resultList = new ArrayList<>();
        int key = offset;
        switch (order_field) {
            case "rise_rate":
            case "unit_nav":
            case "cumu_unit_nav":
                List<Map<String, Object>> navInfoList = secBasicInfoDao.getRankingWhenTableIsFundNav(secUniCodeList, order, offset, limit);
                for (Map<String, Object> item : navInfoList) {
                    if (item == null) {
                        continue;
                    }

                    Map<String, Object> result = new HashMap<>();
                    String sec_name = item.get("sec_name").toString();
                    Long sec_uni_code = Long.parseLong(item.get("sec_uni_code").toString());
                    String sec_code = item.get("sec_code").toString();

                    result.put("sec_name", sec_name);
                    result.put("sec_uni_code", sec_uni_code);
                    result.put("sec_code", sec_code);
                    result.put("abc_code", item.get("abc_code"));
                    result.put("unit_nav", item.get("unit_nav") == null ? null : new BigDecimal(item.get("unit_nav").toString()));
                    result.put("cumu_unit_nav", item.get("cumu_unit_nav") == null ? null : new BigDecimal(item.get("cumu_unit_nav").toString()));
                    result.put("rise_rate", item.get("rise_rate") == null ? null : new BigDecimal(item.get("rise_rate").toString()));

                    String fundManagerName = fundManagerBasicInfoDao.getFundMangerNameBySecUniCode(sec_uni_code);
                    result.put("fund_manager_name", fundManagerName);

                    Map<String, Object> returnInfo = fundNavReturnDao.getReturnInfoBySecUniCode(sec_uni_code);
                    result.put("nav_return_l_1w", returnInfo == null || returnInfo.get("nav_return_l_1w") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_1w").toString()));
                    result.put("nav_return_l_1m", returnInfo == null || returnInfo.get("nav_return_l_1m") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_1m").toString()));
                    result.put("nav_return_l_3m", returnInfo == null || returnInfo.get("nav_return_l_3m") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_3m").toString()));
                    result.put("nav_return_l_6m", returnInfo == null || returnInfo.get("nav_return_l_6m") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_6m").toString()));
                    result.put("nav_return_t_1y", returnInfo == null || returnInfo.get("nav_return_t_1y") == null ? null : new BigDecimal(returnInfo.get("nav_return_t_1y").toString()));

                    key++;
                    result.put("key", key);

                    resultList.add(result);
                }
                return resultList;
            default:
                List<Map<String, Object>> returnInfoList = secBasicInfoDao.getRankingWhenTableIsFundNavReturn(secUniCodeList, order, offset, limit);
                for (Map<String, Object> item : returnInfoList) {
                    if (item == null) {
                        continue;
                    }

                    Map<String, Object> result = new HashMap<>();

                    String sec_name = item.get("sec_name").toString();
                    Long sec_uni_code = Long.parseLong(item.get("sec_uni_code").toString());
                    String sec_code = item.get("sec_code").toString();
                    result.put("sec_name", sec_name);
                    result.put("sec_uni_code", sec_uni_code);
                    result.put("sec_code", sec_code);
                    result.put("abc_code", item.get("abc_code"));

                    result.put("nav_return_l_1w", item.get("nav_return_l_1w") == null ? null : new BigDecimal(item.get("nav_return_l_1w").toString()));
                    result.put("nav_return_l_1m", item.get("nav_return_l_1m") == null ? null : new BigDecimal(item.get("nav_return_l_1m").toString()));
                    result.put("nav_return_l_3m", item.get("nav_return_l_3m") == null ? null : new BigDecimal(item.get("nav_return_l_3m").toString()));
                    result.put("nav_return_l_6m", item.get("nav_return_l_6m") == null ? null : new BigDecimal(item.get("nav_return_l_6m").toString()));
                    result.put("nav_return_t_1y", item.get("nav_return_t_1y") == null ? null : new BigDecimal(item.get("nav_return_t_1y").toString()));

                    String fundManagerName = fundManagerBasicInfoDao.getFundMangerNameBySecUniCode(sec_uni_code);
                    result.put("fund_manager_name", fundManagerName);

                    Map<String, Object> navInfo = fundNavDao.getUnitNavBySecuniCode(sec_uni_code);
                    result.put("unit_nav", navInfo == null || navInfo.get("unit_nav") == null ? null : new BigDecimal(navInfo.get("unit_nav").toString()));
                    result.put("cumu_unit_nav", navInfo == null || navInfo.get("cumu_unit_nav") == null ? null : new BigDecimal(navInfo.get("cumu_unit_nav").toString()));
                    result.put("rise_rate", navInfo == null || navInfo.get("rise_rate") == null ? null : new BigDecimal(navInfo.get("rise_rate").toString()));

                    key++;
                    result.put("key", key);

                    resultList.add(result);
                }
                return resultList;
        }
    }

    @Override
    public List<Map<String, String>> getAchievementsCategory(Long com_uni_code) throws Exception {
        return fundKeeperScaleDao.getPlateCodeAndNameByComUniCode(com_uni_code);
    }

    @Override
    public Map<String, Object> getAchievementsInfo(FundCompanyAchievementsInfoRequest request) throws Exception {
        Map<String, Object> result = new HashMap<>();

        String plateCode = request.getPlate_code();
        if (StringUtil.isEmpty(plateCode)) {
            plateCode = "1001001";
        }
        List<FundCompanyAchievementsInfoItemResponse> list = fundKeeperScaleDao.getAllFundCompanyByPlateCode(plateCode);

        for (int i = 0; i < list.size(); i++) {
            FundCompanyAchievementsInfoItemResponse item = list.get(i);

            if (!"1001001".equals(plateCode)) {
                if (item == null || (item.getFund_quantity() == null && item.getFund_manager_quantity() == null && item.getFund_nav() == null)) {
                    list.remove(i);
                    i--;
                }
            }
        }

        // TODO 如果这个接口太慢,注释上面一段，放开下面的注释
        /*List<FundCompanyAchievementsInfoItemResponse> list = fundBasicInfoDao.getAllFunCompany();

        List<FundKeeperScaleModel> fundKeeperScaleModelList = fundKeeperScaleDao.getFundCompanyByPlateCode(plateCode);

        List<FundManagerChgModel> fundManagerChgModelList = fundManagerChgDao.getFundCompanyByPlateCode(plateCode);

        for (int i = 0; i < list.size(); i++) {
            FundCompanyAchievementsInfoItemResponse item = list.get(i);

            if (item == null) {
                list.remove(i);
                i--;
            }

            for (FundKeeperScaleModel model : fundKeeperScaleModelList) {
                if (!StringUtil.isEmpty(item.getCom_uni_code()) && !StringUtil.isEmpty(model.getFund_keeper_code())
                        && item.getCom_uni_code().equals(model.getFund_keeper_code())) {
                    item.setFund_quantity(model.getFund_quantity());
                    item.setFund_nav(model.getFund_nav());
                    break;
                }
            }

            for (FundManagerChgModel model : fundManagerChgModelList) {
                if (!StringUtil.isEmpty(item.getCom_uni_code()) && !StringUtil.isEmpty(model.getFund_keeper_code())
                        && item.getCom_uni_code().equals(model.getFund_keeper_code())) {
                    item.setFund_manager_quantity( model.getFund_manager_quantity());
                    break;
                }
            }

            if (!"1001001".equals(plateCode)) {
                if (item.getFund_quantity() == null && item.getFund_manager_quantity() == null && item.getFund_nav() == null) {
                    list.remove(i);
                    i--;
                }
            }
        }*/

        if (list == null || list.isEmpty()) {
            return null;
        }

        Integer count = list.size();

        List<FundCompanyAchievementsInfoItemResponse> fundQuantitySortList = new ArrayList<>(list);
        /*Collections.sort(fundQuantitySortList, (o1, o2) -> {
            if (o2.getFund_quantity().compareTo(o1.getFund_quantity()) == 0) {
                return o2.getCom_uni_code().compareTo(o1.getCom_uni_code());
            }
            return o2.getFund_quantity().compareTo(o1.getFund_quantity());
        });*/

        Collections.sort(fundQuantitySortList, (o1, o2) -> {
            if (o2.getFund_quantity() == null && o1.getFund_quantity() == null) {
                return o2.getCom_uni_code().compareTo(o1.getCom_uni_code());
            } else {
                if (o2.getFund_quantity() != null && o1.getFund_quantity() != null) {
                    if (o2.getFund_quantity().compareTo(o1.getFund_quantity()) == 0) {
                        return o2.getCom_uni_code().compareTo(o1.getCom_uni_code());
                    }
                    return o2.getFund_quantity().compareTo(o1.getFund_quantity());
                } else {
                    if (o1.getFund_quantity() == null) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });
        Map<Long, Integer> fundQuantitySortMap = getRnaking(fundQuantitySortList, "fund_quantity_sort");


        List<FundCompanyAchievementsInfoItemResponse> fundManagerQuantitySortList = new ArrayList<>(list);
        /*Collections.sort(fundManagerQuantitySortList, (o1, o2) -> {
            if (o2.getFund_manager_quantity().compareTo(o1.getFund_manager_quantity()) == 0) {
                return o2.getCom_uni_code().compareTo(o1.getCom_uni_code());
            }
            return o2.getFund_manager_quantity().compareTo(o1.getFund_manager_quantity());
        });*/

        Collections.sort(fundManagerQuantitySortList, (o1, o2) -> {
            if (o2.getFund_manager_quantity() == null && o1.getFund_manager_quantity() == null) {
                return o2.getCom_uni_code().compareTo(o1.getCom_uni_code());
            } else {
                if (o2.getFund_manager_quantity() != null && o1.getFund_manager_quantity() != null) {
                    if (o2.getFund_manager_quantity().compareTo(o1.getFund_manager_quantity()) == 0) {
                        return o2.getCom_uni_code().compareTo(o1.getCom_uni_code());
                    }
                    return o2.getFund_manager_quantity().compareTo(o1.getFund_manager_quantity());
                } else {
                    if (o1.getFund_manager_quantity() == null) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });
        Map<Long, Integer> fundManagerQuantitySortMap = getRnaking(fundManagerQuantitySortList, "fund_manager_quantity_sort");

        List<FundCompanyAchievementsInfoItemResponse> fundNavSortList = new ArrayList<>(list);
        /*Collections.sort(fundNavSortList, (o1, o2) -> {
            if (o2.getFund_nav().compareTo(o1.getFund_nav()) == 0) {
                return o2.getCom_uni_code().compareTo(o1.getCom_uni_code());
            }
            return o2.getFund_nav().compareTo(o1.getFund_nav());
        });*/

        Collections.sort(fundNavSortList, (o1, o2) -> {
            if (o2.getFund_nav() == null && o1.getFund_nav() == null) {
                return o2.getCom_uni_code().compareTo(o1.getCom_uni_code());
            } else {
                if (o2.getFund_nav() != null && o1.getFund_nav() != null) {
                    if (o2.getFund_nav().compareTo(o1.getFund_nav()) == 0) {
                        return o2.getCom_uni_code().compareTo(o1.getCom_uni_code());
                    }
                    return o2.getFund_nav().compareTo(o1.getFund_nav());
                } else {
                    if (o1.getFund_nav() == null) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });
        Map<Long, Integer> fundNavSortMap = getRnaking(fundNavSortList, "fund_nav_sort");

        List<FundCompanyAchievementsInfoItemResponse> items;
        String order_field = request.getOrder_field();
        String order_type = request.getOrder_type();
        if (StringUtil.isEmpty(order_field) ||
                (!"fund_quantity".equals(order_field) && !"fund_manager_quantity".equals(order_field)
                        && !"fund_nav".equals(order_field))) {
            order_field = "fund_quantity";
        }

        if (StringUtil.isEmpty(order_type) || (!"desc".equals(order_type) && !"asc".equals(order_type))) {
            order_type = "desc";
        }

        switch (order_field) {
            case "fund_quantity":
                if ("asc".equals(order_type)) {
                    Collections.reverse(fundQuantitySortList);
                }
                items = fundQuantitySortList;
                break;
            case "fund_manager_quantity":
                if ("asc".equals(order_type)) {
                    Collections.reverse(fundManagerQuantitySortList);
                }
                items = fundManagerQuantitySortList;
                break;
            case "fund_nav":
                if ("asc".equals(order_type)) {
                    Collections.reverse(fundNavSortList);
                }
                items = fundNavSortList;
                break;
            default:
                if ("asc".equals(order_type)) {
                    Collections.reverse(fundQuantitySortList);
                }
                items = fundQuantitySortList;
                break;
        }

        Integer currentFundCompanyIndex = 0;
        for (int i = 0; i < items.size(); i++) {
            FundCompanyAchievementsInfoItemResponse item = items.get(i);
            if (request.getCom_uni_code().equals(item.getCom_uni_code())) {
                currentFundCompanyIndex = i;
            }
            String fund_quantity_ranking = fundQuantitySortMap.get(item.getCom_uni_code()) + "/" + count;
            String fund_manager_quantity_ranking = fundManagerQuantitySortMap.get(item.getCom_uni_code()) + "/" + count;
            String fund_nav_ranking = fundNavSortMap.get(item.getCom_uni_code()) + "/" + count;

            item.setFund_quantity_ranking(fund_quantity_ranking);
            item.setFund_manager_quantity_ranking(fund_manager_quantity_ranking);
            item.setFund_nav_ranking(fund_nav_ranking);

            item.setKey(i + 1);
        }

        if (currentFundCompanyIndex > 0) {
            items.add(0, items.get(currentFundCompanyIndex));
            items.remove(currentFundCompanyIndex + 1);
        }

        Integer limit = request.getLimit();
        if (limit == null || limit < 1) {
            limit = 9;
        }

        Integer page_num = request.getPage_num();
        if (page_num == null || page_num < 1) {
            page_num = 1;
        }

        Integer startIndex = (page_num - 1) * limit;
        Integer endIndex = page_num * limit;
        if (startIndex > count - 1) {
            result.put("total", count);
            result.put("item", new ArrayList<>());
            return result;
        }

        if (endIndex > count) {
            endIndex = count;
        }

        result.put("total", count);
        result.put("item", items.subList(startIndex, endIndex));

        return result;
    }

    private Map<Long, Integer> getRnaking(List<FundCompanyAchievementsInfoItemResponse> list, String type) {
        Map<Long, Integer> map = new LinkedHashMap<>();
        map.put(list.get(0).getCom_uni_code(), 1);

        switch (type) {
            case "fund_quantity_sort":
                for (int i= 1; i < list.size(); i++) {
                    FundCompanyAchievementsInfoItemResponse item1 = list.get(i);
                    FundCompanyAchievementsInfoItemResponse item2 = list.get(i - 1);
                    if (item1.getFund_quantity() == null && item2.getFund_quantity() == null) {
                        map.put(item1.getCom_uni_code(), map.get(item2.getCom_uni_code()));
                    } else {
                        if (item1.getFund_quantity() != null && item2.getFund_quantity() != null) {
                            if (item1.getFund_quantity().equals(item2.getFund_quantity())) {
                                map.put(item1.getCom_uni_code(), map.get(item2.getCom_uni_code()));
                            } else {
                                map.put(item1.getCom_uni_code(), i + 1);
                            }
                        } else {
                            map.put(item1.getCom_uni_code(), i + 1);
                        }
                    }
                }

                break;
            case "fund_manager_quantity_sort":
                for (int i= 1; i < list.size(); i++) {
                    FundCompanyAchievementsInfoItemResponse item1 = list.get(i);
                    FundCompanyAchievementsInfoItemResponse item2 = list.get(i - 1);
                    if (item1.getFund_manager_quantity() == null && item2.getFund_manager_quantity() == null) {
                        map.put(item1.getCom_uni_code(), map.get(item2.getCom_uni_code()));
                    } else {
                        if (item1.getFund_manager_quantity() != null && item2.getFund_manager_quantity() != null) {
                            if (item1.getFund_manager_quantity().equals(item2.getFund_manager_quantity())) {
                                map.put(item1.getCom_uni_code(), map.get(item2.getCom_uni_code()));
                            } else {
                                map.put(item1.getCom_uni_code(), i + 1);
                            }
                        } else {
                            map.put(item1.getCom_uni_code(), i + 1);
                        }
                    }
                }
                break;
            case "fund_nav_sort":
                for (int i= 1; i < list.size(); i++) {
                    FundCompanyAchievementsInfoItemResponse item1 = list.get(i);
                    FundCompanyAchievementsInfoItemResponse item2 = list.get(i - 1);
                    if (item1.getFund_nav() == null && item2.getFund_nav() == null) {
                        map.put(item1.getCom_uni_code(), map.get(item2.getCom_uni_code()));
                    } else {
                        if (item1.getFund_nav() != null && item2.getFund_nav() != null) {
                            if (item1.getFund_nav().equals(item2.getFund_nav())) {
                                map.put(item1.getCom_uni_code(), map.get(item2.getCom_uni_code()));
                            } else {
                                map.put(item1.getCom_uni_code(), i + 1);
                            }
                        } else {
                            map.put(item1.getCom_uni_code(), i + 1);
                        }
                    }
                }
                break;
            default:
                break;
        }

        return map;
    }

    @Override
    public Map<String, Object> getAchievementsChart(FundCompanyAchievementsChartRequest request) throws Exception {
        Map<String, Object> result = new LinkedHashMap<>();

        String field = request.getField();
        if (StringUtil.isEmpty(field) || (!"fund_quantity".equals(field) && !"fund_manager_quantity".equals(field)
                && !"fund_nav".equals(field))) {
            field = "fund_quantity";
        }

        int year;
        if (StringUtil.isEmpty(request.getYear())) {
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
        } else {
            year = Integer.valueOf(request.getYear());
        }

        Date startTime = getYearFirst(year);
        Date endTime = getYearFirst(year + 1);

        List<String> comUniCodeList = Arrays.asList(request.getCom_uni_codes().split(","));
        for (String comUniCodeStr : comUniCodeList) {
            if (StringUtil.isEmpty(comUniCodeStr)) {
                continue;
            }

            Long comUniCode = Long.valueOf(comUniCodeStr);
            OrgBasicInfoModel orgBasicInfoModel = orgBasicInfoDao.selectByComUniCode(comUniCode);
            if (orgBasicInfoModel == null || StringUtil.isEmpty(orgBasicInfoModel.getCom_uni_code())) {
                continue;
            }

            String name = orgBasicInfoModel.getOrg_name();

            String plateCode = request.getPlate_code();
            if (StringUtil.isEmpty(plateCode)) {
                plateCode = "1001001";
            }
            Map<String, Object> chartData = new LinkedHashMap<>();
            if ("fund_quantity".equals(field) || "fund_nav".equals(field)) {
                List<FundKeeperScaleModel> list = fundKeeperScaleDao.getFundQuantityOrNavByTime(field, comUniCode,
                        plateCode, startTime, endTime);

                if (list != null && !list.isEmpty()) {
                    for (FundKeeperScaleModel model : list) {
                        if ("fund_quantity".equals(field)) {
                            chartData.put(model.getEnd_date().getTime() + "", model.getFund_quantity());
                        } else {
                            chartData.put(model.getEnd_date().getTime() + "", model.getFund_nav());
                        }
                    }
                }

            } else {
                List<FundManagerChgModel> list = fundManagerChgDao.getFundManagerQuantityByTime(comUniCode, plateCode,
                        startTime, endTime);
                if (list != null && !list.isEmpty()) {
                    for (FundManagerChgModel model : list) {
                        chartData.put(model.getEnd_date().getTime() + "", model.getFund_manager_quantity());
                    }
                }
            }

            result.put(name, chartData);
        }

        return result;
    }

    private Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }
}
