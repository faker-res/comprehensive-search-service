package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import la.niub.abcapi.servicecompre.component.client.IApiNewsClient;
import la.niub.abcapi.servicecompre.component.client.IKeywordClient;
import la.niub.abcapi.servicecompre.component.client.IServiceNewsClient;
import la.niub.abcapi.servicecompre.component.client.IServiceReportClient;
import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.component.util.ThreadHelper;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.map.ResearchIndustryMap;
import la.niub.abcapi.servicecompre.dao.notice.*;
import la.niub.abcapi.servicecompre.dao.reporter.*;
import la.niub.abcapi.servicecompre.model.*;
import la.niub.abcapi.servicecompre.model.bo.AnalystHonorInfoBO;
import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystCompetitionBO;
import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystDetailBO;
import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystHeatBO;
import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystSummaryBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystBasicInfoBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundManagerBO;
import la.niub.abcapi.servicecompre.model.request.client.ClientNewsRequest;
import la.niub.abcapi.servicecompre.model.request.client.ReportRequest;
import la.niub.abcapi.servicecompre.model.response.ApiNewsDataItemResponse;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.client.ApiNewsResponse;
import la.niub.abcapi.servicecompre.model.response.client.ServiceSearchReportResponse;
import la.niub.abcapi.servicecompre.model.response.client.report.ReportItemResponse;
import la.niub.abcapi.servicecompre.model.response.message.MessageResponse;
import la.niub.abcapi.servicecompre.service.IAnalystService;
import la.niub.abcapi.servicecompre.service.ICardService;
import la.niub.abcapi.servicecompre.service.ICompanyManagerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class AnalystServiceImpl implements IAnalystService {

    private final static Logger logger = LogManager.getLogger(AnalystServiceImpl.class);

    @Autowired
    HttpServletRequest servletRequest;

    @Value("${oss.file_server_host}")
    private String ossFileHost;

    @Value("${feign.client.apiNews.url}")
    private String apiNewsUrl;

    @Autowired
    ICardService cardService;

    @Autowired
    IAnalystBasicinfoDao analystBasicinfoDao;

    @Autowired
    IAnalystChanStatusDao analystChanStatusDao;

    @Autowired
    IAnalystStatisticsDao analystStatisticsDao;

    @Autowired
    IPeoBasicInfoDao peoBasicInfoDao;

    @Autowired
    IAnalystHonorDao analystHonorDao;

    @Autowired
    IOrganDao organDao;

    @Autowired
    IAnalystTeamDao analystTeamDao;

    @Autowired
    IAnalystForcastDao analystForcastDao;

    @Autowired
    IAnalystStockReturnsDao analystStockReturnsDao;

    @Autowired
    IUserWechatAccountDao userWechatAccountDao;

    @Autowired
    IIndexBasicInfoDao indexBasicInfoDao;

    @Autowired
    IHiborAuthorCountDao hiborAuthorCountDao;

    @Autowired
    IServiceReportClient serviceReportClient;

    @Autowired
    IServiceNewsClient serviceNewsClient;

    @Autowired
    IApiNewsClient apiNewsClient;

    @Autowired
    IHiborDao iHiborDao;

    @Autowired
    IKeywordClient iKeywordClient;
    @Autowired
    ICompanyManagerService companyManagerService;

    @Autowired
    IAbcIndustryDao abcIndustryDao;

    @Value("${feign.client.solrReport.url}")
    private String solrReportUrl;

//    @Value("${feign.client.suggestion.url}")
//    private static String SUGGESTION_URL = "https://api.analyst.ai/api/suggestion/recommend/getautocompletelist";

    @Autowired
    ISecBasicInfoDao iSecBasicInfoDao;

    @Override
    public AnalystDetailBO getAnalystDetail(String peoUniCode) {
        AnalystDetailBO analystDetailBO = new AnalystDetailBO();
        // 获取分析师基本信息
        AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peoUniCode);
        if (analystBasicinfoModel == null){
            return analystDetailBO;
        }
        Map paraMap=new HashMap();
        paraMap.put("honor","新财富最佳分析师");
        paraMap.put("peo_uni_code",peoUniCode);
        paraMap.put("order_by"," order by  time desc");
        List<AnalystHonorModel>  analystHonorModelList=analystHonorDao.selectHonor(paraMap);
        if(analystHonorModelList!=null && analystHonorModelList.size()>0){
            //如果分析师获奖，获取最新获奖领域
            analystDetailBO.setH_direction(analystHonorModelList.get(0).getDirection());
        }else{
            analystDetailBO.setH_direction("");
        }
        analystDetailBO.setSummary(analystBasicinfoModel.getBack_gro());

        AnalystChanStatusModel analystChanStatusModel = analystChanStatusDao.selectByPeoUniCode(peoUniCode);
        Long orgId = analystChanStatusModel != null ? analystChanStatusModel.getOrg_uni_code() : null;
        CardAnalystBasicInfoBO cardAnalystBasicInfoBO = buildAnalystBasicInfo(analystBasicinfoModel,orgId.intValue());
        if (cardAnalystBasicInfoBO != null){
            analystDetailBO.setId(cardAnalystBasicInfoBO.getId());
            analystDetailBO.setImage(cardAnalystBasicInfoBO.getImage());
            analystDetailBO.setName(cardAnalystBasicInfoBO.getName());
            analystDetailBO.setOrgan(cardAnalystBasicInfoBO.getOrgan());
            analystDetailBO.setC_id(cardAnalystBasicInfoBO.getC_id());
            analystDetailBO.setOrgan_logo(StringUtils.isNotEmpty(cardAnalystBasicInfoBO.getOrgan_logo()) ? ossFileHost+"/"+cardAnalystBasicInfoBO.getOrgan_logo() : null);
            analystDetailBO.setRank(cardAnalystBasicInfoBO.getRank());
            analystDetailBO.setReport_num(cardAnalystBasicInfoBO.getReport_num());
            analystDetailBO.setReport_num_rate(cardAnalystBasicInfoBO.getReport_num_rate());
            analystDetailBO.setHonor_num(cardAnalystBasicInfoBO.getHonor_num());
            analystDetailBO.setDirection(cardAnalystBasicInfoBO.getDirection());
            analystDetailBO.setEmail(cardAnalystBasicInfoBO.getEmail());
            analystDetailBO.setTel(cardAnalystBasicInfoBO.getTel());
            analystDetailBO.setTime(cardAnalystBasicInfoBO.getTime());
        }
        //平均达成天数/头部百分比
        AnalystStatisticsModel analystStatisticsModel = analystStatisticsDao.selectByPeoUniCode(peoUniCode);
        if (analystStatisticsModel != null){
            analystDetailBO.setAverage_days(analystStatisticsModel.getReachdays_analyst() != null ? analystStatisticsModel.getReachdays_analyst().intValue() : 0);
            analystDetailBO.setHead_percent(analystStatisticsModel.getReachdays_percent() != null ? analystStatisticsModel.getReachdays_percent().doubleValue() : 0);
        }
        //工作履历
        if (analystChanStatusModel != null){
            List<FundManagerBO> fundManagerBOList = new ArrayList<>();
            FundManagerBO fundManagerBO = new FundManagerBO();
            fundManagerBO.setId(Long.valueOf(analystChanStatusModel.getPeo_uni_code()));
            fundManagerBO.setPosition(analystChanStatusModel.getPractice_post());
            fundManagerBO.setName(analystDetailBO.getName());
            fundManagerBO.setCompany_name(analystChanStatusModel.getCurrent_org());
            fundManagerBO.setStart_time(analystChanStatusModel.getCertificate_issued_date());
            fundManagerBOList.add(fundManagerBO);

            analystDetailBO.setResume(fundManagerBOList);
        }

        return analystDetailBO;
    }

    @Override
    public List<MessageResponse> getDynamic(String peoUniCode, Integer limit) {
        List<MessageResponse> messageResponseList = new ArrayList<>();

        AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peoUniCode);
        if (analystBasicinfoModel == null){
            return messageResponseList;
        }
        String peoName = analystBasicinfoModel.getName();

        //新闻
        List<ApiNewsDataItemResponse> newsDataItemList = null;
        try {
            ClientNewsRequest clientNewsRequest = new ClientNewsRequest();
//            clientNewsRequest.setKeyword(analystBasicinfoModel.getName());
            clientNewsRequest.setKeyword("$author:\""+analystBasicinfoModel.getName()+"\"");
            clientNewsRequest.setOffset(0);
            clientNewsRequest.setLimit(limit);
//            Response<List<ApiNewsDataItemResponse>> serviceNewsResponse = serviceNewsClient.search(clientNewsRequest);
//            newsDataItemList = serviceNewsResponse.getData();
            ApiNewsResponse apiNewsResponse = apiNewsClient.news(clientNewsRequest);
            if (apiNewsResponse.getData() != null){
                newsDataItemList = apiNewsResponse.getData().getItem();
            }
        } catch (Exception e) {
            logger.error("analyst news get error : " + e.getMessage());
        }
        if (newsDataItemList != null && !newsDataItemList.isEmpty()) {
            for (ApiNewsDataItemResponse item : newsDataItemList) {
                MessageResponse messageResponse = new MessageResponse();
                messageResponse.setId(String.valueOf(item.getId()));
                messageResponse.setType("news");
                messageResponse.setAuthor(peoName);
                messageResponse.setTitle(StringUtil.stripHtml(item.getTitle()));
                messageResponse.setSource(item.getSource_name());
                messageResponse.setTime(TimeUtil.fromUnixtime(item.getTime()));
                messageResponse.setUrl(item.getUrl());
                messageResponseList.add(messageResponse);
            }
        }

        //研报
        List<ReportItemResponse> reportItems = null;
        String userId = StringUtils.defaultString(servletRequest.getParameter("userId"),"c9941cd943d74dda4b1fb51ce64d6b62");
        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setUserId(userId);
        reportRequest.setOffset(0);
        reportRequest.setLimit(limit);
        reportRequest.setOrder_by("all_score");
        reportRequest.setGroup_by("default");
//        reportRequest.setKeyword_filter(peoName);
        reportRequest.setAuthor(peoName);
        Response<ServiceSearchReportResponse> report = serviceReportClient.report(reportRequest);
        if(report != null && report.getData() != null){
            reportItems = report.getData().getItems();
        }
        if (reportItems != null && !reportItems.isEmpty()) {
            for (ReportItemResponse item : reportItems) {
                MessageResponse messageResponse = new MessageResponse();
                messageResponse.setId(String.valueOf(item.getId()));
                messageResponse.setType("report");
                messageResponse.setAuthor(peoName);
                messageResponse.setTitle(item.getTitle());
                messageResponse.setSource(item.getSource());
                messageResponse.setTime(TimeUtil.fromUnixtime(item.getTime()));
//                messageResponse.setUrl(item.getUrl());
                messageResponseList.add(messageResponse);
            }
        }

        // 公众号
        List<UserWechatAccountModel> wechatAccountList = userWechatAccountDao.selectByAuthorAndType(peoName, "券商");
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
                names.append(" ").append("\"").append(peoName).append("\"");

                String keyword = "$source_name:(" + URLEncoder.encode(names.toString().substring(1),"UTF-8")+ ")";
                ClientNewsRequest clientNewsRequest = new ClientNewsRequest();
                clientNewsRequest.setKeyword(keyword);
                clientNewsRequest.setOffset(0);
                clientNewsRequest.setLimit(limit);
                clientNewsRequest.setPrior("time");
                Response<List<ApiNewsDataItemResponse>> apiNewsResponse = serviceNewsClient.search(clientNewsRequest);
                newsWechatDataItemList = apiNewsResponse.getData();
            } catch (Exception e) {
                logger.error("analyst wechat get error : " + e.getMessage());
            }

            if (newsWechatDataItemList != null && !newsWechatDataItemList.isEmpty()) {
                for (ApiNewsDataItemResponse item : newsWechatDataItemList) {
                    MessageResponse messageResponse = new MessageResponse();
                    messageResponse.setId(String.valueOf(item.getId()));
                    messageResponse.setType("wechat");
                    messageResponse.setAuthor(peoName);
                    messageResponse.setTitle(StringUtil.stripHtml(item.getTitle()));
                    messageResponse.setSource(StringUtil.stripHtml(item.getSource_name()));
                    messageResponse.setTime(TimeUtil.fromUnixtime(item.getTime()));
                    messageResponse.setUrl(item.getUrl());
                    messageResponseList.add(messageResponse);
                }
            }
        }

        //去重排序
        Collections.sort(messageResponseList, new Comparator<MessageResponse>() {
            @Override
            public int compare(MessageResponse o1, MessageResponse o2) {
                return TimeUtil.unixTimestamp(o2.getTime()) - TimeUtil.unixTimestamp(o1.getTime());
            }
        });

        return messageResponseList;
//        return messageResponseList.subList(0,Math.min(limit,messageResponseList.size()));
    }

    /**
     * 分析师基本信息
     * @param analystBasicinfoModel
     * @param orgId
     * @return
     */
    private CardAnalystBasicInfoBO buildAnalystBasicInfo(AnalystBasicinfoModel analystBasicinfoModel,
                                                         Integer orgId){
        String peo_uni_code = analystBasicinfoModel.getPeo_uni_code();
        CardAnalystBasicInfoBO cardAnalystBasicInfoBO = new CardAnalystBasicInfoBO();

        OrganModel organModel = organDao.selectByOrgId(orgId);
        if(organModel != null){
            String organ = organModel.getPublish();
            cardAnalystBasicInfoBO.setOrgan(organ);
            cardAnalystBasicInfoBO.setC_id(organModel.getOrg_id());
            String organ_logo = "";
            // 获取机构logo
//            OrganModel organModel = organDao.selectByPublish(organ);
            if (organModel.getLogo() != null){
                organ_logo = organModel.getLogo();
                cardAnalystBasicInfoBO.setOrgan_logo(organ_logo);
            }
        }

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
            List<HiborAuthorCountModel> reportNumList = cardService.getAnalystReportsNum(direction);
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

        return cardAnalystBasicInfoBO;
    }

    @Override
    public List<AnalystSummaryBO> getSameFieldAnalyst(String peoUniCode, Integer limit) throws ServiceException {
        List<AnalystSummaryBO> analystSummaryBOList = new ArrayList<>();
        // 获取分析师基本信息
        AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peoUniCode);
        if (analystBasicinfoModel == null){
            return analystSummaryBOList;
        }
//        if (StringUtils.isEmpty(analystBasicinfoModel.getDirection())){
//            return analystSummaryBOList;
//        }
        List<String> directions = null;
        if (StringUtils.isNotEmpty(analystBasicinfoModel.getDirection())){
            directions = Arrays.asList(analystBasicinfoModel.getDirection().split("、"));
        }

        AnalystChanStatusModel analystChanStatusModel = analystChanStatusDao.selectByPeoUniCode(peoUniCode);
        Long orgUniCode = analystChanStatusModel != null ? analystChanStatusModel.getOrg_uni_code() : null;

        List<String> peoUniCodes = analystChanStatusDao.getAllAnalystPeoUniCode();
        if (StringUtil.isEmpty(peoUniCodes)) {
            return null;
        }
//        if (orgUniCode != null){
//            List<AnalystChanStatusModel> analystChanStatusModels = analystChanStatusDao.getAnalystByOrg(orgUniCode.intValue());
//            peoUniCodes = new ArrayList<>();
//            for (AnalystChanStatusModel item : analystChanStatusModels){
//                peoUniCodes.add(item.getPeo_uni_code());
//            }
//        }
        String direction = null;
        if (directions.size() != 0) {
            direction = directions.get(0);
        }

        List<AnalystBasicinfoModel> analystBasicinfoModelList = analystBasicinfoDao.selectByPeoUniCodesAndDirectionAndLimit(peoUniCodes, direction,limit+1);

//        List<String> peoUniCodes = new ArrayList<>();
        analystBasicinfoModelList = analystBasicinfoModelList.stream().filter((AnalystBasicinfoModel m) -> {
            if (m.getPeo_uni_code().equals(peoUniCode)){
                return false;
            }
//            if (!peoUniCodes.contains(m.getPeo_uni_code())){
//                peoUniCodes.add(m.getPeo_uni_code());
//                return true;
//            }
            return true;
        }).collect(Collectors.toList());
        analystBasicinfoModelList = analystBasicinfoModelList.subList(0,Math.min(limit,analystBasicinfoModelList.size()));

        for (AnalystBasicinfoModel analystBasicinfoModel1 : analystBasicinfoModelList) {
            analystBasicinfoModel1.setDirection(direction);
        }

        //analystSummaryBOList = handleAnalystBasicinfo(analystBasicinfoModelList);
        analystSummaryBOList = handleAnalystBasicinfo2(analystBasicinfoModelList);
        return analystSummaryBOList;
    }

    @Override
    public List<AnalystSummaryBO> getStarAnalyst(String peoUniCode, Integer limit) {
        List<AnalystSummaryBO> analystSummaryBOList = new ArrayList<>();
        // 获取分析师基本信息
        AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peoUniCode);
        if (analystBasicinfoModel == null){
            return analystSummaryBOList;
        }
        List<String> directions = null;
        if (StringUtils.isNotEmpty(analystBasicinfoModel.getDirection())){
            directions = Arrays.asList(analystBasicinfoModel.getDirection().split("、"));
        }

        // 同领域上榜次数排行
        List<String> peoUniCodes = new ArrayList<>();

        //analyst_honor的direction需要映射
        List<String> directionList = new ArrayList<>();
//        for (String direction : directions) {
        if (!StringUtil.isEmpty(directions)) {
            try {
                List<String> list = ResearchIndustryMap.getResearchIndustry(directions.get(0));
                for (String item : list) {
                    directionList.add(item);
                }
            } catch (Exception e) {
                directionList.add(directions.get(0));
            }
        }
//        }

        List<AnalystHonorModel> analystHonorModelList = analystHonorDao.buildRecordsOrderByHonorNum(directionList, limit+1);
        for(AnalystHonorModel item : analystHonorModelList) {
            if(!item.getPeo_uni_code().equals(peoUniCode) && !peoUniCodes.contains(item.getPeo_uni_code())){
                peoUniCodes.add(item.getPeo_uni_code());
            }
        }
        List<String> peoUniCodesLimit = peoUniCodes.subList(0,Math.min(limit,peoUniCodes.size()));
        List<AnalystBasicinfoModel> analystBasicinfoModelList = analystBasicinfoDao.selectByPeoUniCodeList(peoUniCodesLimit);
        Collections.sort(analystBasicinfoModelList, Comparator.comparing(o -> ((Integer) peoUniCodesLimit.indexOf(o.getPeo_uni_code()))));

        analystSummaryBOList = handleAnalystBasicinfo2(analystBasicinfoModelList);
        return analystSummaryBOList;
    }

    private List<AnalystSummaryBO> handleAnalystBasicinfo(List<AnalystBasicinfoModel> analystBasicinfoModelList){
        List<AnalystSummaryBO> analystSummaryBOList = new ArrayList<>();

        List<String> peoUniCodes = new ArrayList<>();
        for (AnalystBasicinfoModel item : analystBasicinfoModelList){
            peoUniCodes.add(item.getPeo_uni_code());
        }

        Map<String,AnalystStatisticsModel> analystStatisticsModelMap = new HashMap<>();
        List<AnalystStatisticsModel> analystStatisticsModelList = analystStatisticsDao.selectByPeoUniCodes(peoUniCodes);
        for (AnalystStatisticsModel item : analystStatisticsModelList){
            analystStatisticsModelMap.put(item.getPeo_uni_code(),item);
        }

        Map<String,AnalystChanStatusModel> analystChanStatusModelMap = new HashMap<>();
        List<AnalystChanStatusModel> analystChanStatusModelList = analystChanStatusDao.getListByPeoUniCodes(peoUniCodes);
        for (AnalystChanStatusModel item : analystChanStatusModelList){
            analystChanStatusModelMap.put(item.getPeo_uni_code(),item);
        }

        for (AnalystBasicinfoModel item : analystBasicinfoModelList){
            AnalystSummaryBO analystSummaryBO = new AnalystSummaryBO();
            analystSummaryBO.setPeo_uni_code(item.getPeo_uni_code());
            analystSummaryBO.setName(item.getName());
            analystSummaryBO.setImage(item.getImage());
            analystSummaryBO.setDirection(item.getDirection());

            AnalystChanStatusModel analystChanStatusModel = analystChanStatusModelMap.get(item.getPeo_uni_code());
            if (analystChanStatusModel != null){
                analystSummaryBO.setOrgan(analystChanStatusModel.getOrg_sname());
            }

            AnalystStatisticsModel analystStatisticsModel = analystStatisticsModelMap.get(item.getPeo_uni_code());
            if (analystStatisticsModel != null){
                analystSummaryBO.setAverage_days(analystStatisticsModel.getReachdays_analyst() != null ? analystStatisticsModel.getReachdays_analyst().intValue() : 0);
                analystSummaryBO.setHead_percent(analystStatisticsModel.getReachdays_percent() != null ? analystStatisticsModel.getReachdays_percent().doubleValue() : 0);
                if (StringUtils.isEmpty(analystSummaryBO.getOrgan())){
                    analystSummaryBO.setOrgan(analystStatisticsModel.getCurrent_org());
                }
            }

            analystSummaryBOList.add(analystSummaryBO);
        }

        ClientNewsRequest clientNewsRequest = new ClientNewsRequest();
        clientNewsRequest.setOffset(0);
        clientNewsRequest.setLimit(1);
        String userId = StringUtils.defaultString(servletRequest.getParameter("userId"),"c9941cd943d74dda4b1fb51ce64d6b62");
        clientNewsRequest.setUserId(userId);
        clientNewsRequest.setRequest_id(StringUtils.defaultString(servletRequest.getParameter("request_id"),""));
        clientNewsRequest.setDevice_info(StringUtils.defaultString(servletRequest.getParameter("device_info"),""));

//        if (analystBasicinfoModelList.size() <= 5){
        if (true){
            Map<String,Future<ApiNewsDataItemResponse>> futureMap = new HashMap<>();
            for (AnalystSummaryBO item : analystSummaryBOList){
                Future<ApiNewsDataItemResponse> future = ThreadHelper.run(() -> {
                    try{
//                        clientNewsRequest.setKeyword(item.getName());
//                        Response<List<ApiNewsDataItemResponse>> apiNewsResponse = serviceNewsClient.search(clientNewsRequest);
//                        if (apiNewsResponse != null && !ObjectUtils.isEmpty(apiNewsResponse.getData())){
//                            return apiNewsResponse.getData().get(0);
//                        }
                        clientNewsRequest.setKeyword(item.getOrgan() != null ? "$content:(\""+item.getName()+"\" AND \""+item.getOrgan()+"\")" : item.getName());
                        ApiNewsResponse apiNewsResponse = apiNewsClient.news(clientNewsRequest);
                        if (apiNewsResponse != null && apiNewsResponse.getData() != null &&
                                !ObjectUtils.isEmpty(apiNewsResponse.getData().getItem())){
                            return apiNewsResponse.getData().getItem().get(0);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                    }
                    return null;
                });
                futureMap.put(item.getPeo_uni_code(),future);
            }

            Map<String,ApiNewsDataItemResponse> newsDataItemResponseMap = new HashMap<>();
            for (Map.Entry<String,Future<ApiNewsDataItemResponse>> entry : futureMap.entrySet()){
                try {
                    newsDataItemResponseMap.put(entry.getKey(),entry.getValue().get(2000, TimeUnit.MILLISECONDS));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            for (AnalystSummaryBO item : analystSummaryBOList){
                if (newsDataItemResponseMap.get(item.getPeo_uni_code()) != null){
                    ApiNewsDataItemResponse newsDataItemResponse = newsDataItemResponseMap.get(item.getPeo_uni_code());
                    item.setRecent_title(StringUtil.stripHtml(newsDataItemResponse.getTitle()));
                    item.setRecent_type("news");
                    item.setRecent_url(newsDataItemResponse.getUrl());
                    item.setRecent_id(newsDataItemResponse.getId());
                }
            }
        }else{
            for (AnalystSummaryBO item : analystSummaryBOList){
//                clientNewsRequest.setKeyword(item.getName());
//                Response<List<ApiNewsDataItemResponse>> apiNewsResponse = serviceNewsClient.search(clientNewsRequest);
//                if (!ObjectUtils.isEmpty(apiNewsResponse.getData())){
//                    ApiNewsDataItemResponse newsDataItemResponse = apiNewsResponse.getData().get(0);
//                    item.setRecent_title(newsDataItemResponse.getTitle());
//                    item.setRecent_type("news");
//                    item.setRecent_url(newsDataItemResponse.getUrl());
//                    item.setRecent_id(newsDataItemResponse.getId());
//                }
                clientNewsRequest.setKeyword(item.getOrgan() != null ? "$content:(\""+item.getName()+"\" AND \""+item.getOrgan()+"\")" : item.getName());
                ApiNewsResponse apiNewsResponse = apiNewsClient.news(clientNewsRequest);
                if (apiNewsResponse != null && apiNewsResponse.getData() != null &&
                        !ObjectUtils.isEmpty(apiNewsResponse.getData().getItem())){
                    ApiNewsDataItemResponse newsDataItemResponse = apiNewsResponse.getData().getItem().get(0);
                    item.setRecent_title(newsDataItemResponse.getTitle());
                    item.setRecent_type("news");
                    item.setRecent_url(newsDataItemResponse.getUrl());
                    item.setRecent_id(newsDataItemResponse.getId());
                }
            }
        }

        return analystSummaryBOList;
    }

    private List<AnalystSummaryBO> handleAnalystBasicinfo2(List<AnalystBasicinfoModel> analystBasicinfoModelList){
        List<AnalystSummaryBO> analystSummaryBOList = new ArrayList<>();

        List<String> peoUniCodes = new ArrayList<>();
        for (AnalystBasicinfoModel item : analystBasicinfoModelList){
            peoUniCodes.add(item.getPeo_uni_code());
        }

        Map<String,AnalystStatisticsModel> analystStatisticsModelMap = new HashMap<>();
        List<AnalystStatisticsModel> analystStatisticsModelList = analystStatisticsDao.selectByPeoUniCodes(peoUniCodes);
        for (AnalystStatisticsModel item : analystStatisticsModelList){
            analystStatisticsModelMap.put(item.getPeo_uni_code(),item);
        }

        Map<String,AnalystChanStatusModel> analystChanStatusModelMap = new HashMap<>();
        List<AnalystChanStatusModel> analystChanStatusModelList = analystChanStatusDao.getListByPeoUniCodes(peoUniCodes);
        for (AnalystChanStatusModel item : analystChanStatusModelList){
            analystChanStatusModelMap.put(item.getPeo_uni_code(),item);
        }

        for (AnalystBasicinfoModel item : analystBasicinfoModelList){
            AnalystSummaryBO analystSummaryBO = new AnalystSummaryBO();
            analystSummaryBO.setPeo_uni_code(item.getPeo_uni_code());
            analystSummaryBO.setName(item.getName());
            analystSummaryBO.setImage(item.getImage());
            analystSummaryBO.setDirection(item.getDirection());

            AnalystChanStatusModel analystChanStatusModel = analystChanStatusModelMap.get(item.getPeo_uni_code());
            if (analystChanStatusModel != null){
                analystSummaryBO.setOrgan(analystChanStatusModel.getOrg_sname());
            }

            AnalystStatisticsModel analystStatisticsModel = analystStatisticsModelMap.get(item.getPeo_uni_code());
            if (analystStatisticsModel != null){
                analystSummaryBO.setAverage_days(analystStatisticsModel.getReachdays_analyst() != null ? analystStatisticsModel.getReachdays_analyst().intValue() : 0);
                analystSummaryBO.setHead_percent(analystStatisticsModel.getReachdays_percent() != null ? analystStatisticsModel.getReachdays_percent().doubleValue() : 0);
                if (StringUtils.isEmpty(analystSummaryBO.getOrgan())){
                    analystSummaryBO.setOrgan(analystStatisticsModel.getCurrent_org());
                }
            }
            analystSummaryBOList.add(analystSummaryBO);
        }

        if (true){
            Map<String,MessageResponse> newsDataItemResponseMap = new HashMap<>();
//            CountDownLatch countDownLatch=new CountDownLatch(analystSummaryBOList.size());
//            ExecutorService executorService = Executors.newFixedThreadPool(10);
//            try {
//                for (int i = 0; i < analystSummaryBOList.size(); i++) {
//                    final int index=i;
//                    executorService.submit(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                List<MessageResponse> messageResponseList=getDynamic(analystSummaryBOList.get(index).getPeo_uni_code(), 1);
//                                if(messageResponseList!=null && messageResponseList.size()>0){
//                                    newsDataItemResponseMap.put(analystSummaryBOList.get(index).getPeo_uni_code(),messageResponseList.get(0));
//                                }
//                                countDownLatch.countDown();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//
//                }
//                executorService.shutdown();
//                countDownLatch.await(); //一直阻塞当前线程，直到计时器的值为0,保证同时并发
//            }catch (Exception e){
//                e.printStackTrace();
//            }

            for (int i = 0; i < analystSummaryBOList.size(); i++) {
                List<MessageResponse> messageResponseList=getDynamic(analystSummaryBOList.get(i).getPeo_uni_code(), 1);
                if(messageResponseList!=null && messageResponseList.size()>0){
                    newsDataItemResponseMap.put(analystSummaryBOList.get(i).getPeo_uni_code(),messageResponseList.get(0));
                }
            }

            for (AnalystSummaryBO item : analystSummaryBOList){
                if (newsDataItemResponseMap.get(item.getPeo_uni_code()) != null){
                    MessageResponse newsDataItemResponse = newsDataItemResponseMap.get(item.getPeo_uni_code());
                    item.setRecent_title(StringUtil.stripHtml(newsDataItemResponse.getTitle()));
                    item.setRecent_type(newsDataItemResponse.getType());
                    item.setRecent_url(newsDataItemResponse.getUrl());
                    item.setRecent_id(newsDataItemResponse.getId());
                }
            }
        }
        return analystSummaryBOList;
    }

    @Override
    public List<AnalystHeatBO> getHeat(String peoUniCode, Integer limit) {
        List<AnalystHeatBO> analystHeatBOList = new ArrayList<>();
        List<AnalystTeamModel> analystTeamModelList = analystTeamDao.selectOrderByReportCount(limit);
        for (AnalystTeamModel item : analystTeamModelList){
            AnalystHeatBO analystHeatBO = new AnalystHeatBO();
            analystHeatBO.setName(item.getAnalyst_team());
            analystHeatBO.setNum(item.getReport_count());
            analystHeatBO.setChg_ratio(item.getReport_change() != null ? item.getReport_change().doubleValue() : 0);
            analystHeatBOList.add(analystHeatBO);
        }
        return analystHeatBOList;
    }

    @Override
    public List<AnalystCompetitionBO> getCompetition(String peoUniCode, Date startTime, Date endTime, Integer year, Integer limit) {
        List<AnalystForcastWithCount> analystForcastWithCountList = analystForcastDao.getByPeoUniCodeWithCount(peoUniCode,startTime,endTime,limit);
        List<AnalystCompetitionBO> analystCompetitionBOList = new ArrayList<>();
        for (AnalystForcastWithCount item : analystForcastWithCountList){
            AnalystCompetitionBO analystCompetitionBO = new AnalystCompetitionBO();
            analystCompetitionBO.setName(item.getStockname());
            analystCompetitionBO.setCode(item.getStockcode());
            analystCompetitionBO.setNum(item.getCount());
            analystCompetitionBO.setPeo_uni_code(peoUniCode);
            analystCompetitionBOList.add(analystCompetitionBO);
        }
        analystCompetitionBOList = handleAnalystCompetitionBO(analystCompetitionBOList,year);
        return analystCompetitionBOList;
    }

    @Override
    public List<AnalystCompetitionBO> getOtherCompetition(String peoUniCode, Date startTime, Date endTime, Integer year, Integer limit) {
        List<AnalystCompetitionBO> analystCompetitionBOList = new ArrayList<>();
        // 获取分析师基本信息
        AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peoUniCode);
        if (analystBasicinfoModel == null){
            return analystCompetitionBOList;
        }
        List<String> directions = null;
        if (analystBasicinfoModel != null && StringUtils.isNotEmpty(analystBasicinfoModel.getDirection())){
            directions = Arrays.asList(analystBasicinfoModel.getDirection().split("、"));
        }

        List<String> peoUniCodes = new ArrayList<>();
        if (directions != null){
            List<AnalystBasicinfoModel> analystBasicinfoModelList = analystBasicinfoDao.selectByPeoUniCodesAndDirectionLimit(null,directions,null);
            for (AnalystBasicinfoModel item : analystBasicinfoModelList){
                if (!item.getPeo_uni_code().equals(peoUniCode)){
                    peoUniCodes.add(item.getPeo_uni_code());
                }
            }
        }

        List<String> stockCodes = new ArrayList<>();
        List<AnalystForcastWithCount> analystForcastWithCountList = analystForcastDao.getByPeoUniCodeWithCount(peoUniCode,startTime,endTime,limit);
        for (AnalystForcastWithCount item : analystForcastWithCountList){
            stockCodes.add(item.getStockcode());
        }

        List<AnalystForcastWithCount> otherAnalystForcastWithCountList = analystForcastDao.getOtherByPeoUniCodesAndStockCodesWithCount(peoUniCode,peoUniCodes,stockCodes,startTime,endTime,limit);

        for (AnalystForcastWithCount item : otherAnalystForcastWithCountList){
            AnalystCompetitionBO analystCompetitionBO = new AnalystCompetitionBO();
            analystCompetitionBO.setName(item.getStockname());
            analystCompetitionBO.setCode(item.getStockcode());
            analystCompetitionBO.setNum(item.getCount());
            analystCompetitionBO.setPeo_uni_code(item.getPeo_uni_code());
            analystCompetitionBOList.add(analystCompetitionBO);
        }
        analystCompetitionBOList = handleAnalystCompetitionBO(analystCompetitionBOList,year);
        return analystCompetitionBOList;
    }

    private List<AnalystCompetitionBO> handleAnalystCompetitionBO(List<AnalystCompetitionBO> analystCompetitionBOList,Integer year){
        List<String> peoUniCodes = new ArrayList<>();
        List<String> stockCodes = new ArrayList<>();
        for (AnalystCompetitionBO item : analystCompetitionBOList){
            if (!peoUniCodes.contains(item.getPeo_uni_code())){
                peoUniCodes.add(item.getPeo_uni_code());
            }
            if (!stockCodes.contains(item.getCode())){
                stockCodes.add(item.getCode());
            }
        }
        List<AnalystStockReturnsModel> analystStockReturnsModelList = analystStockReturnsDao.selectByPeoUniCodesAndStockCodesAndYear(peoUniCodes,stockCodes,year);
        Map<String,AnalystStockReturnsModel> analystStockReturnsModelMap = new HashMap<>();
        for (AnalystStockReturnsModel item : analystStockReturnsModelList){
            String key = item.getPeo_uni_code()+"_"+item.getStockcode();
            analystStockReturnsModelMap.put(key,item);
        }
        for (AnalystCompetitionBO item : analystCompetitionBOList){
            String key = item.getPeo_uni_code()+"_"+item.getCode();
            if (analystStockReturnsModelMap.containsKey(key)){
                AnalystStockReturnsModel analystStockReturnsModel = analystStockReturnsModelMap.get(key);
                //收益率
                item.setRatio(analystStockReturnsModel.getStock_returns() != null ? analystStockReturnsModel.getStock_returns().doubleValue() : 0);
            }
        }
        return analystCompetitionBOList;
    }

    @Override
    public List<FundManagerBO> getStarAnalystOfPublicCompany(Long indexUniCode, Integer limit) {
        List<FundManagerBO> fundManagerBOList = new ArrayList<>();

        IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoDao.selectByPrimaryKey(indexUniCode);
        if (indexBasicInfoModel == null){
            return fundManagerBOList;
        }
        String direction = indexBasicInfoModel.getIndex_name();

        List<AnalystBasicinfoModel> analystBasicinfoModelList = analystBasicinfoDao.getAnalystByDirection(direction);

        List<String> peoUniCodes = new ArrayList<>();
        Map<String,AnalystBasicinfoModel> analystBasicinfoModelMap = new HashMap<>();
        for (AnalystBasicinfoModel item : analystBasicinfoModelList){
            peoUniCodes.add(item.getPeo_uni_code());
            analystBasicinfoModelMap.put(item.getPeo_uni_code(),item);
        }

        Map<String,AnalystStatisticsModel> analystStatisticsModelMap = new HashMap<>();
        List<AnalystStatisticsModel> analystStatisticsModelList = analystStatisticsDao.selectByPeoUniCodes(peoUniCodes);
        for (AnalystStatisticsModel item : analystStatisticsModelList){
            analystStatisticsModelMap.put(item.getPeo_uni_code(),item);
        }

        Map<String,AnalystChanStatusModel> analystChanStatusModelMap = new HashMap<>();
        List<AnalystChanStatusModel> analystChanStatusModelList = analystChanStatusDao.getListByPeoUniCodes(peoUniCodes);
        for (AnalystChanStatusModel item : analystChanStatusModelList){
            analystChanStatusModelMap.put(item.getPeo_uni_code(),item);
        }

        //研报数量
        Map<String,Integer> reportNumMap = new HashMap<>();
        List<HiborAuthorCountModel> hiborAuthorCountModelList = hiborAuthorCountDao.getReportNumByPeoUniCodesAndTime(peoUniCodes,null,null);
        for (HiborAuthorCountModel item : hiborAuthorCountModelList){
            reportNumMap.put(item.getPeo_uni_code(),item.getReport_num());
        }

        //分析师获奖排名
        List<String> induNameList = new ArrayList<>();
        List<String> directionList = Arrays.asList(direction.split(","));
        for (String directionItem : directionList) {
            List<String> list = ResearchIndustryMap.getResearchIndustry(directionItem);
            for (String item : list) {
                induNameList.add(item);
            }
        }
        List<AnalystHonorInfoBO> analystHonorInfoBOList = analystHonorDao.selectAnalystHonorRankingByPeoUniCodeList(peoUniCodes, induNameList);

        if (!ObjectUtils.isEmpty(analystHonorInfoBOList)) {
//        if (false){
            for (AnalystHonorInfoBO item : analystHonorInfoBOList){
                FundManagerBO fundManagerBO = new FundManagerBO();
                fundManagerBO.setId(Long.valueOf(item.getPeo_uni_code()));
                fundManagerBO.setPosition("明星分析师");
                fundManagerBO.setHonor(item.getTime()+item.getHonor());
                fundManagerBO.setRank(item.getRanking());

                AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoModelMap.get(item.getPeo_uni_code());
                if (analystBasicinfoModel != null){
                    fundManagerBO.setName(analystBasicinfoModel.getName());
                    fundManagerBO.setHead_img(analystBasicinfoModel.getImage());
                }

                AnalystChanStatusModel analystChanStatusModel = analystChanStatusModelMap.get(item.getPeo_uni_code());
                if (analystChanStatusModel != null){
                    fundManagerBO.setCompany_name(analystChanStatusModel.getOrg_sname());
                }

                AnalystStatisticsModel analystStatisticsModel = analystStatisticsModelMap.get(item.getPeo_uni_code());
                if (analystStatisticsModel != null){
                    if (StringUtils.isEmpty(fundManagerBO.getCompany_name())){
                        fundManagerBO.setCompany_name(analystStatisticsModel.getCurrent_org());
                    }
                }

                Integer reportNum = reportNumMap.get(item.getPeo_uni_code());
                if (reportNum != null) {
                    fundManagerBO.setReport_num(reportNum);
                }

                fundManagerBOList.add(fundManagerBO);
            }
        }else{
            List<String> peoUniCodesOrderByReportNum = new ArrayList<>();
            for (HiborAuthorCountModel item : hiborAuthorCountModelList){
                peoUniCodesOrderByReportNum.add(item.getPeo_uni_code());
            }
            Collections.sort(analystBasicinfoModelList, (o1, o2) -> {
                if (peoUniCodesOrderByReportNum.contains(o1.getPeo_uni_code()) && peoUniCodesOrderByReportNum.contains(o2.getPeo_uni_code())){
                    return peoUniCodesOrderByReportNum.indexOf(o1.getPeo_uni_code()) - peoUniCodesOrderByReportNum.indexOf(o2.getPeo_uni_code());
                }else if (peoUniCodesOrderByReportNum.contains(o1.getPeo_uni_code()) && !peoUniCodesOrderByReportNum.contains(o2.getPeo_uni_code())){
                    return -1;
                }else if (!peoUniCodesOrderByReportNum.contains(o1.getPeo_uni_code()) && peoUniCodesOrderByReportNum.contains(o2.getPeo_uni_code())){
                    return 1;
                }else{
                    return 0;
                }
            });

            for (AnalystBasicinfoModel item : analystBasicinfoModelList){
                FundManagerBO fundManagerBO = new FundManagerBO();
                fundManagerBO.setId(Long.valueOf(item.getPeo_uni_code()));
                fundManagerBO.setPosition("明星分析师");
                fundManagerBO.setName(item.getName());
                fundManagerBO.setHead_img(item.getImage());

                AnalystChanStatusModel analystChanStatusModel = analystChanStatusModelMap.get(item.getPeo_uni_code());
                if (analystChanStatusModel != null){
                    fundManagerBO.setCompany_name(analystChanStatusModel.getOrg_sname());
                }

                AnalystStatisticsModel analystStatisticsModel = analystStatisticsModelMap.get(item.getPeo_uni_code());
                if (analystStatisticsModel != null){
                    if (StringUtils.isEmpty(fundManagerBO.getCompany_name())){
                        fundManagerBO.setCompany_name(analystStatisticsModel.getCurrent_org());
                    }
                }

                Integer reportNum = reportNumMap.get(item.getPeo_uni_code());
                if (reportNum != null){
                    fundManagerBO.setReport_num(reportNum);
                }

                fundManagerBOList.add(fundManagerBO);
            }
        }

        fundManagerBOList = fundManagerBOList.subList(0,Math.min(limit,fundManagerBOList.size()));
        return fundManagerBOList;
    }

    @Override
    public List<Map<String, Object>> getOrgSnameList(String keyword, String userId, String token) throws Exception {
        if (StringUtil.isEmpty(keyword)) {
            return analystChanStatusDao.getOrgSnameList();
        } else {

//            File file = new File("G:\\个股名称.txt");
//            FileOutputStream fw = new FileOutputStream(file);
//            List<Map<String, Object>> list = iSecBasicInfoDao.getSecNameAndAbcCode();
//            for (Map<String, Object> map : list) {
//                String sec_name = map.get("sec_name").toString();
//                String abc_code = map.get("abc_code").toString();
//                fw.write((abc_code + "," + sec_name).getBytes());
//                fw.write("\r\n".getBytes());
//            }

            List<Map<String, Object>> orgSnameList = new ArrayList<>();
//            Map<String, String> suggestionRequest = new HashMap<>();
//            suggestionRequest.put("type", "50003");
//            suggestionRequest.put("limit", "500");
//            suggestionRequest.put("keyword", keyword);
//            suggestionRequest.put("userId", userId);
//            suggestionRequest.put("token", token);
//            String apiSuggestionRet = HttpUtil.get(SUGGESTION_URL, suggestionRequest, null);
//            String apiSuggestionRet = HttpUtil.post(SUGGESTION_URL, suggestionRequest, null);
//            KeywordSuggestResponse keywordSuggestResponse = iKeywordClient.suggest(keyword, 10000, 50003);
            String keywordSuggestResponse = iKeywordClient.suggest(keyword, 500, 50003);
//            keywordSuggestResponse.getData();
            JSONObject suggestionObj = JSONObject.parseObject(keywordSuggestResponse);
//            Object data = keywordSuggestResponse.getData();
            if (suggestionObj.containsKey("data")) {
                Object data = suggestionObj.get("data");
                if (data instanceof JSONArray) {
                    for (Object item : (JSONArray) data) {
                        Map<String, Object> orgSname = new HashMap<>();
                        String content = JSONObject.parseObject(item.toString()).getString("content");
                        if (!StringUtil.isEmpty(content)) {
                            orgSname.put("org_sname", content);
                            orgSnameList.add(orgSname);
                        }
                    }
                }
            }

            return orgSnameList;
        }
    }

//    @Override
//    public Map<String, Object> getAnalystList(String indu_name, String org_sname, String name,
//                                                    String spellPrefix, String prior, Integer offset,
//                                                    Integer limit) throws Exception {
//        if (StringUtil.isEmpty(indu_name)) {
//            indu_name = null;
//        }
//
//        if (StringUtil.isEmpty(org_sname)) {
//            org_sname = null;
//        }
//
//        if (StringUtil.isEmpty(name)) {
//            name = null;
//        }
//
//        if (StringUtil.isEmpty(spellPrefix)) {
//            spellPrefix = null;
//        }
//
//        Map<String, Object> result = new HashMap<>();
//        if (prior.equals("reportCount")) {
//            List<Map<String, Object>> items = new ArrayList<>();
//            List<Map<String, Object>> nameInfoList = analystBasicinfoDao.getPeoUniCodeByNameAndSpellAndOrgSnameAndDirectionList(name, spellPrefix, org_sname,
//                    StringUtil.isEmpty(indu_name) ? null : ResearchIndustryMap.getResearchIndustry(indu_name));
////            if (!StringUtil.isEmpty(indu_name) || !StringUtil.isEmpty(org_sname) || !StringUtil.isEmpty(name) || !StringUtil.isEmpty(spellPrefix)) {
//                StringBuffer keyword = new StringBuffer("");
//                int flag = 0;
//                for (Map<String, Object> nameInfo : nameInfoList) {
//                    Map<String, Object> item = new HashMap<>();
//                    String analystName = nameInfo.get("name").toString();
//                    String peoUniCode = nameInfo.get("peo_uni_code").toString();
//                    Map<String, String> reportRequest = new HashMap<>();
//                    reportRequest.put("keyword", "ALL");
//                    reportRequest.put("offset", "0");
//                    reportRequest.put("limit", "1");
//                    reportRequest.put("author", analystName);
//                    String apiReportRet = HttpUtil.post(solrReportUrl, reportRequest, null);
//                    if (apiReportRet != null && !apiReportRet.isEmpty()) {
//                        JSONObject data = JSONObject.parseObject(apiReportRet).getJSONObject("data");
//                        if (data != null) {
//                            JSONArray reportItems = data.getJSONArray("item");
//                            item.put("reportCount", data.get("total_count"));
//                            if (reportItems != null) {
//                                for (Object reportItem : reportItems){
//                                    JSONObject reportItemObj = JSONObject.parseObject(reportItem.toString());
//                                    Map<String, Object> itemMap = new HashMap<>();
//                                    itemMap.put("title", reportItemObj.getString("title"));
//                                    itemMap.put("id", reportItemObj.get("id"));
//                                    itemMap.put("type", "report");
//                                    item.put("report", itemMap);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//
//                    AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peoUniCode);
//                    AnalystChanStatusModel analystChanStatusModel = analystChanStatusDao.selectByPeoUniCode(peoUniCode);
//                    AnalystStatisticsModel analystStatisticsModel = analystStatisticsDao.selectByPeoUniCode(peoUniCode);
//                    item.put("peo_uni_code", analystBasicinfoModel.getPeo_uni_code());
//                    item.put("days", analystStatisticsModel == null ? null : analystStatisticsModel.getReachdays_analyst());
//                    item.put("image", analystBasicinfoModel.getImage());
//                    item.put("name", analystName);
//                    item.put("indu_name", indu_name);
//                    item.put("org_sname", analystChanStatusModel == null ? null : analystChanStatusModel.getOrg_sname());
//                    Integer prizeCount = analystHonorDao.getPrizeCountByPeoUniCode(peoUniCode);
//                    item.put("prizeCount", prizeCount);
//
//                    List<Map<String, Object>> rankList = new ArrayList<>();
//                    List<Map<String, Object>> rankInfoList = analystHonorDao.getRankByPeoUniCode(peoUniCode);
//                    for (Map<String, Object> rankInfo : rankInfoList) {
//                        Map<String, Object> rankItem = new HashMap<>();
//                        rankItem.put("rankNum", rankInfo.get("ranking"));
//                        rankItem.put("rankYear", rankInfo.get("time") == null ? null : rankInfo.get("time").toString().substring(0, 4));
//                        rankList.add(rankItem);
//                    }
//                    item.put("rank", rankList);
//
//                    items.add(item);
//                }
//
//                Collections.sort(items, new Comparator<Map<String, Object>>() {
//                    @Override
//                    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
//                        return Integer.parseInt(o2.get("reportCount").toString()) - Integer.parseInt(o1.get("reportCount").toString());
//                    }
//                });
//
//
//                result.put("totalCount", items.size());
//                List<Map<String, Object>> resultItems = new ArrayList<>();
//                for (int i = offset; i < Math.min(offset + limit, items.size()); i++) {
//                    resultItems.add(items.get(i));
//                }
//
//                result.put("items", resultItems);
//
////                StringBuffer keyword = new StringBuffer("");
////                int flag = 0;
////                for (Map<String, Object> nameInfo : nameInfoList) {
////                    String name1 = nameInfo.get("name").toString();
////                    if (flag == 0) {
////                        keyword.append("作者:" + name1);
////                        flag = 1;
////                    } else {
////                        keyword.append(" OR 作者:" + name1);
////                    }
////                }
//
////                Map<String, String> reportRequest = new HashMap<>();
////                reportRequest.put("keyword", keyword.toString());
////                reportRequest.put("offset", "0");
////                reportRequest.put("limit", "1");
//////                reportRequest.put("author", analystName);
////                String apiReportRet = HttpUtil.post(solrReportUrl, reportRequest, null);
////                if (apiReportRet != null && !apiReportRet.isEmpty()) {
////                    JSONObject data = JSONObject.parseObject(apiReportRet).getJSONObject("data");
////                    if (data != null) {
////                        JSONArray optionItmes = data.getJSONArray("option");
//////                        item.put("reportCount", data.get("total_count"));
////                        if (optionItmes != null) {
////                            for (Object optionItem : optionItmes){
////                                JSONObject reportItemObj = JSONObject.parseObject(optionItem.toString());
////                                Map<String, Object> itemMap = new HashMap<>();
////                                itemMap.put("title", reportItemObj.getString("title"));
////                                itemMap.put("id", reportItemObj.get("id"));
////                                itemMap.put("type", "report");
////                                item.put("report", itemMap);
////                                break;
////                            }
////                        }
////                    }
////                }
//
////                if (StringUtil.isEmpty(keyword)) {
////                    keyword = new StringBuffer("ALL");
////                }
////
////                Map<String, String> reportRequest = new HashMap<>();
////                reportRequest.put("keyword", keyword.toString());
////                reportRequest.put("offset", offset + "");
////                reportRequest.put("limit", limit + "");
//////                reportRequest.put("order_by", "")
//////                reportRequest.put("author", analystName);
////                String apiReportRet = HttpUtil.post(solrReportUrl, reportRequest, null);
////                if (apiReportRet != null && !apiReportRet.isEmpty()) {
////                    JSONObject data = JSONObject.parseObject(apiReportRet).getJSONObject("data");
////                    if (data != null) {
////                        JSONArray optionArray = data.getJSONArray("option");
//////                        item.put("reportCount", data.get("total_count"));
////                        if (optionArray != null) {
////                            for (Object optionItem : optionArray){
////                                JSONObject optionItemObj = JSONObject.parseObject(optionItem.toString());
////                                Map<String, Object> itemMap = new HashMap<>();
//////                                itemMap.put("title", reportItemObj.getString("title"));
//////                                itemMap.put("id", reportItemObj.get("id"));
//////                                itemMap.put("type", "report");
//////                                item.put("report", itemMap);
////                                String showName = optionItemObj.getString("showname");
////                                if (showName.equals("分析师")) {
////
////
////                                }
////                                break;
////                            }
////                        }
////                    }
////                }
////            } else {
////                StringBuffer keyword = new StringBuffer("");
////                int flag = 0;
////                for (Map<String, Object> nameInfo : nameInfoList) {
////                    String name1 = nameInfo.get("name").toString();
////                    if (flag == 0) {
////                        keyword.append("作者:" + name1);
////                        flag = 1;
////                    } else {
////                        keyword.append(" OR 作者:" + name1);
////                    }
////                }
////
////                Map<String, String> reportRequest = new HashMap<>();
////                reportRequest.put("keyword", keyword.toString());
////                reportRequest.put("offset", "0");
////                reportRequest.put("limit", "1");
//////                reportRequest.put("author", analystName);
////                String apiReportRet = HttpUtil.post(solrReportUrl, reportRequest, null);
////                if (apiReportRet != null && !apiReportRet.isEmpty()) {
////                    JSONObject data = JSONObject.parseObject(apiReportRet).getJSONObject("data");
////                    if (data != null) {
////                        JSONArray optionItmes = data.getJSONArray("option");
//////                        item.put("reportCount", data.get("total_count"));
////                        if (optionItmes != null) {
////                            for (Object optionItem : optionItmes){
////                                JSONObject optionItemObj = JSONObject.parseObject(optionItem.toString());
////                                String showname = optionItemObj.getString("showname");
////                                if (!StringUtil.isEmpty(showname)) {
////                                    if (showname.equals("分析师")) {
////                                        JSONArray nameItems = optionItemObj.getJSONArray("item");
////                                        for (Object nameItem : nameItems) {
////
////                                        }
////                                    }
////                                }
////
////                            }
////                        }
////                    }
////                }
////            }
//
//            return result;
//        } else {
//            List<Map<String, Object>> analystInfoList = analystBasicinfoDao.getPeoUniCodeByNameAndSpellAndOrgSnameAndDirectionListAndPrior(name, spellPrefix,
//                    org_sname, StringUtil.isEmpty(indu_name) ? null : ResearchIndustryMap.getResearchIndustry(indu_name), offset, limit, prior);
//
//            List<Map<String, Object>> items = new ArrayList<>();
//            for (Map<String, Object> analystInfo : analystInfoList) {
//                Map<String, Object> item = new HashMap<>();
//                item.put("peo_uni_code", analystInfo.get("peo_uni_code"));
//                item.put("days", analystInfo.get("reachdays_analyst"));
//                item.put("image", analystInfo.get("image"));
//                item.put("name", analystInfo.get("name"));
//                item.put("indu_name", indu_name);
//                item.put("org_sname", analystInfo.get("org_sname"));
//                item.put("prizeCount", analystInfo.get("count1"));
//
//                List<Map<String, Object>> rankList = new ArrayList<>();
//                String peo_uni_code = analystInfo.get("peo_uni_code").toString();
//                List<Map<String, Object>> rankInfoList = analystHonorDao.getRankByPeoUniCode(peo_uni_code);
//                for (Map<String, Object> rankInfo : rankInfoList) {
//                    Map<String, Object> rankItem = new HashMap<>();
//                    rankItem.put("rankNum", rankInfo.get("ranking"));
//                    rankItem.put("rankYear", rankInfo.get("time") == null ? null : rankInfo.get("time").toString().substring(0, 4));
//                    rankList.add(rankItem);
//                }
//                item.put("rank", rankList);
//
//                String analystName = analystInfo.get("name").toString();
//                Map<String, String> reportRequest = new HashMap<>();
//                reportRequest.put("keyword", "ALL");
//                reportRequest.put("offset", "0");
//                reportRequest.put("limit", "1");
//                reportRequest.put("author", analystName);
//                String apiReportRet = HttpUtil.post(solrReportUrl, reportRequest, null);
//                if (apiReportRet != null && !apiReportRet.isEmpty()) {
//                    JSONObject data = JSONObject.parseObject(apiReportRet).getJSONObject("data");
//                    if (data != null) {
//                        JSONArray reportItems = data.getJSONArray("item");
//                        item.put("reportCount", data.get("total_count"));
//                        if (reportItems != null) {
//                            for (Object reportItem : reportItems){
//                                JSONObject reportItemObj = JSONObject.parseObject(reportItem.toString());
//                                Map<String, Object> itemMap = new HashMap<>();
//                                itemMap.put("title", reportItemObj.getString("title"));
//                                itemMap.put("id", reportItemObj.get("id"));
//                                itemMap.put("type", "report");
//                                item.put("report", itemMap);
//                                break;
//                            }
//                        }
//                    }
//                }
//
//                items.add(item);
//            }
//
//            result.put("items", items);
//            List<Map<String, Object>> nameInfoList = analystBasicinfoDao.getPeoUniCodeByNameAndSpellAndOrgSnameAndDirectionList(name, spellPrefix, org_sname,
//                    StringUtil.isEmpty(indu_name) ? null : ResearchIndustryMap.getResearchIndustry(indu_name));
//            result.put("totalCount", nameInfoList.size());
//
//            return result;
//        }
//    }

    @Override
    public Map<String, Object> getAnalystList(final String indu_name, String org_sname, String name,
                                              String spellPrefix, String prior, Integer offset,
                                              Integer limit) throws Exception {

        if (StringUtil.isEmpty(name)) {
            name = null;
        }

        if (StringUtil.isEmpty(org_sname)) {
            org_sname = null;
        }

        if (StringUtil.isEmpty(spellPrefix)) {
            spellPrefix = null;
        }

        List<String> peoUniCodeList = analystChanStatusDao.getAllAnalystByOrgSname(org_sname);

        if (StringUtil.isEmpty(peoUniCodeList)) {
            return null;
        }

        if (!StringUtil.isEmpty(name) || !StringUtil.isEmpty(spellPrefix)) {
            peoUniCodeList = analystBasicinfoDao.getPeoUniCodeByNameAndSpellAndPeoUniCodeList(name, spellPrefix, peoUniCodeList);
        }

        if (StringUtil.isEmpty(peoUniCodeList)) {
            return null;
        }

        if (!StringUtil.isEmpty(indu_name)) {
//            peoUniCodeList = analystHonorDao.getPeoUniCodeByPeoUniCodeListAndDirectionList(peoUniCodeList, StringUtil.isEmpty(indu_name) ? null : ResearchIndustryMap.getResearchIndustry(indu_name));
            peoUniCodeList = analystBasicinfoDao.getPeoUniCodeByPeoUniCodeListAndDirections(peoUniCodeList, indu_name/* StringUtil.isEmpty(indu_name) ? null : ResearchIndustryMap.getResearchIndustry(indu_name)*/);
        }

        if (StringUtil.isEmpty(peoUniCodeList)) {
            return null;
        }

        Map<String, Object> result = new HashMap<>();
        Integer totalCount = peoUniCodeList.size();
        result.put("totalCount", totalCount);
        switch (prior) {
            case "ranking":
                peoUniCodeList = analystBasicinfoDao.getRankByPriorIsRanking(peoUniCodeList, offset, limit);
                break;
            case "prizeCount":
                peoUniCodeList = analystBasicinfoDao.getRankByPriorIsPrizeCount(peoUniCodeList, offset, limit);
                break;
            case "days":
                peoUniCodeList = analystBasicinfoDao.getRankByPriorIsDays(peoUniCodeList, offset, limit);
                break;
            default:
                Integer nThreads = totalCount / 50 + 1;
                ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
                List<Future<String>> futures = new ArrayList<Future<String>>(nThreads);
                List<Map<String, Object>> reportCount = new ArrayList<>();
                List<Callable<String>> tasks = new ArrayList<Callable<String>>();
                for (int i = 0; i < nThreads; i++) {
                    final List<String> subList = peoUniCodeList.subList(totalCount / nThreads * i, totalCount / nThreads * (i + 1));

                    Callable<String> task = new Callable<String>() {
                        @Override
                        public String call() throws Exception {

                            for (String peoUniCode : subList) {
                                Map<String, Object> map = getAnalystInfoWhenPriorIsReportCount(peoUniCode, indu_name);
                                reportCount.add(map);
                            }
                            System.out.println(Thread.currentThread().getName() + "////////////////////////////////////////////////////////////////////////////////////");
                            return null;
                        }
                    };
                    tasks.add(task);
                }

                executorService.invokeAll(tasks);
//                for (Future<String> future : results) {
//                    System.out.println(future.get());
//                }

                executorService.shutdown();

                Collections.sort(reportCount, new Comparator<Map<String, Object>>() {
                    @Override
                    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                        return Integer.parseInt(o2.get("reportCount").toString()) - Integer.parseInt(o1.get("reportCount").toString());
                    }
                });

//                result.put("totalCount", items.size());
//                List<Map<String, Object>> resultItems = new ArrayList<>();
                List<String> peoCodeList = new ArrayList<>();
                for (int i = offset, max = Math.min(offset + limit, reportCount.size()); i < max; i++) {
                    Map<String, Object> item = reportCount.get(i);
                    String peoUniCode = item.get("peoUniCode").toString();
                    peoCodeList.add(peoUniCode);
                }

                List<Map<String, Object>> items = getAnalystInfoByPeoUniCodeList(peoCodeList, null, indu_name);
                Collections.sort(items, new Comparator<Map<String, Object>>() {
                    @Override
                    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                        return Integer.parseInt(o2.get("reportCount").toString()) - Integer.parseInt(o1.get("reportCount").toString());
                    }
                });

                result.put("items", items);

                return result;
        }

        List<Map<String, Object>> items = getAnalystInfoByPeoUniCodeList(peoUniCodeList, prior, indu_name);
//        for (String peoUniCode : peoUniCodeList) {
//            Map<String, Object> item = analystInfoByPeoUniCode(peoUniCode, indu_name);
//            items.add(item);
//        }
        result.put("items", items);

//        result.put("items", items1);
        return result;
    }

    private List<Map<String, Object>> getAnalystInfoByPeoUniCodeList(List<String> peoUniCodeList, String prior, String indu_name) throws Exception{
        List<Map<String, Object>> analystInfoList = analystBasicinfoDao.getAnalystInfoByPeoUniCodeList(peoUniCodeList, prior);
        List<Map<String, Object>> items = new ArrayList<>();
        for (Map<String, Object> analystInfo : analystInfoList) {
            Map<String, Object> item = new HashMap<>();
            item.put("peo_uni_code", analystInfo.get("peo_uni_code"));
            item.put("days", analystInfo.get("reachdays_analyst"));
            item.put("image", analystInfo.get("image"));
            item.put("name", analystInfo.get("name"));
            item.put("indu_name", indu_name);
//            item.put("org_sname", analystInfo.get("org_sname"));
            Integer orgId = Integer.parseInt(analystInfo.get("org_uni_code").toString());
            item.put("org_uni_code", orgId);
            OrganModel organModel = organDao.selectByOrgId(orgId);
            item.put("org_sname", organModel.getPublish());
            item.put("prizeCount", analystInfo.get("count1"));

            List<Map<String, Object>> rankList = new ArrayList<>();
            String peo_uni_code = analystInfo.get("peo_uni_code").toString();
            List<Map<String, Object>> rankInfoList = analystHonorDao.getRankByPeoUniCode(peo_uni_code);
            for (Map<String, Object> rankInfo : rankInfoList) {
                Map<String, Object> rankItem = new HashMap<>();
                rankItem.put("rankNum", rankInfo.get("ranking"));
                rankItem.put("rankYear", rankInfo.get("time") == null ? null : rankInfo.get("time").toString().substring(0, 4));
                rankList.add(rankItem);
            }
            item.put("rank", rankList);

            String analystName = analystInfo.get("name").toString();
            Map<String, String> reportRequest = new HashMap<>();
            reportRequest.put("keyword", "ALL");
            reportRequest.put("offset", "0");
            reportRequest.put("limit", "1");
            reportRequest.put("author", analystName);
            String apiReportRet = HttpUtil.post(solrReportUrl, reportRequest, null);
            if (apiReportRet != null && !apiReportRet.isEmpty()) {
                JSONObject data = JSONObject.parseObject(apiReportRet).getJSONObject("data");
                if (data != null) {
                    JSONArray reportItems = data.getJSONArray("item");
                    item.put("reportCount", data.get("total_count"));
                    if (reportItems != null) {
                        for (Object reportItem : reportItems){
                            JSONObject reportItemObj = JSONObject.parseObject(reportItem.toString());
                            Map<String, Object> itemMap = new HashMap<>();
                            itemMap.put("title", reportItemObj.getString("title"));
                            itemMap.put("id", reportItemObj.get("id"));
                            itemMap.put("type", "report");
                            item.put("report", itemMap);
                            break;
                        }
                    }
                }
            }

            items.add(item);
        }

        return items;
    }

    private Map<String, Object> analystInfoByPeoUniCode(String peoUniCode, String indu_name) throws Exception{
        Map<String, Object> item = new HashMap<>();
        AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peoUniCode);
        AnalystChanStatusModel analystChanStatusModel = analystChanStatusDao.selectByPeoUniCode(peoUniCode);
        AnalystStatisticsModel analystStatisticsModel = analystStatisticsDao.selectByPeoUniCode(peoUniCode);
        item.put("peo_uni_code", analystBasicinfoModel.getPeo_uni_code());
        item.put("days", analystStatisticsModel == null ? null : analystStatisticsModel.getReachdays_analyst());
        item.put("image", analystBasicinfoModel.getImage());

        String analystName = analystBasicinfoModel.getName();
        item.put("name", analystName);
        item.put("indu_name", indu_name);
        item.put("org_sname", analystChanStatusModel == null ? null : analystChanStatusModel.getOrg_sname());
        Integer prizeCount = analystHonorDao.getPrizeCountByPeoUniCode(peoUniCode);
        item.put("prizeCount", prizeCount);

        List<Map<String, Object>> rankList = new ArrayList<>();
        List<Map<String, Object>> rankInfoList = analystHonorDao.getRankByPeoUniCode(peoUniCode);
        for (Map<String, Object> rankInfo : rankInfoList) {
            Map<String, Object> rankItem = new HashMap<>();
            rankItem.put("rankNum", rankInfo.get("ranking"));
            rankItem.put("rankYear", rankInfo.get("time") == null ? null : rankInfo.get("time").toString().substring(0, 4));
            rankList.add(rankItem);
        }
        item.put("rank", rankList);

        Map<String, String> reportRequest = new HashMap<>();
        reportRequest.put("keyword", "ALL");
        reportRequest.put("offset", "0");
        reportRequest.put("limit", "1");
        reportRequest.put("author", analystName);
        String apiReportRet = HttpUtil.post(solrReportUrl, reportRequest, null);
        if (apiReportRet != null && !apiReportRet.isEmpty()) {
            JSONObject data = JSONObject.parseObject(apiReportRet).getJSONObject("data");
            if (data != null) {
                JSONArray reportItems = data.getJSONArray("item");
                item.put("reportCount", data.get("total_count"));
                if (reportItems != null) {
                    for (Object reportItem : reportItems){
                        JSONObject reportItemObj = JSONObject.parseObject(reportItem.toString());
                        Map<String, Object> itemMap = new HashMap<>();
                        itemMap.put("title", reportItemObj.getString("title"));
                        itemMap.put("id", reportItemObj.get("id"));
                        itemMap.put("type", "report");
                        item.put("report", itemMap);
                        break;
                    }
                }
            }
        }
        return item;
    }

    public Map<String, Object>  getAnalystPredictive(Map map){
        Map<String, Object> result=new HashMap<>();
        result.put("peo_uni_code",map.getOrDefault("peo_uni_code","").toString());
        result.put("analyst","");
        List<AnalystForcastModel> owns=analystForcastDao.getByPeoUniCodePredictive(map);
        List<Map> info_owns=new ArrayList<>();
        if(owns!=null && owns.size()>0){
            result.put("analyst",owns.get(0).getAnalyst());
            for(AnalystForcastModel AnalystForcastModel:owns){
                Map<String,Object> tempMap=new HashMap<>();
                tempMap.put("stockcode",AnalystForcastModel.getStockcode());
                tempMap.put("stockname",AnalystForcastModel.getStockname());
                tempMap.put("eps_ahead",AnalystForcastModel.getEps_ahead());
                tempMap.put("eps_difference",AnalystForcastModel.getEps_difference());
                tempMap.put("price_diff_day",AnalystForcastModel.getPrice_diff_day());
                tempMap.put("reach_days",AnalystForcastModel.getReach_days());
                info_owns.add(tempMap);
            }
        }
        map.put("peo_uni_code_other","and peo_uni_code is not null and peo_uni_code != '"+ map.getOrDefault("peo_uni_code","").toString()+"'");
        map.remove("peo_uni_code");
        List<AnalystForcastModel> others=analystForcastDao.getByPeoUniCodePredictive(map);
        List<Map>  info_others=new ArrayList<>();
        if(others!=null && others.size()>0){
            for(AnalystForcastModel AnalystForcastModel:others){
                Map<String,Object> tempMap_=new HashMap<>();
                tempMap_.put("stockcode",AnalystForcastModel.getStockcode());
                tempMap_.put("stockname",AnalystForcastModel.getStockname());
                tempMap_.put("eps_ahead",AnalystForcastModel.getEps_ahead());
                tempMap_.put("eps_difference",AnalystForcastModel.getEps_difference());
                tempMap_.put("price_diff_day",AnalystForcastModel.getPrice_diff_day());
                tempMap_.put("reach_days",AnalystForcastModel.getReach_days());
                info_others.add(tempMap_);
            }
        }
        result.put("info_own",info_owns);
        result.put("info_other",info_others);
        return result;
    }

    //分析师预测能力关注股票
    public   List<Map> getAnalystStockCode(Map map){
        List<Map> result=new ArrayList<>();
        List<String> stockCodes=analystForcastDao.getByPeoUniCodeStockCode(map);
        Map<String,Object> paraMap=new HashMap<>();
        if(map.containsKey("time")){
            paraMap.put("startDate",map.get("time"));
            paraMap.put("endDate",(Integer.parseInt(map.get("time").toString())+1)+"");
        }
        if(stockCodes!=null){
            for(String code:stockCodes){
                paraMap.put("stockcode",code);
                List<AnalystForcastModel> datas=analystForcastDao.getByPeoUniCodePredictive(paraMap);
                if(datas!=null && datas.size()>0){
                    boolean bn=false;
                    Map<String,Object> codeMap=new HashMap<>();
                    codeMap.put("stockcode",datas.get(0).getStockcode());
                    codeMap.put("stockname",datas.get(0).getStockname());
                    for(AnalystForcastModel aly:datas){
                        if(aly.getEps_ahead()!=null &&aly.getEps_difference()!=null){
                            bn=true;
                        }
                        if(aly.getPrice_diff_day()!=null &&aly.getReach_days()!=null){
                            bn=true;
                        }
                    }
                    codeMap.put("datasource",bn);
                    result.add(codeMap);
                }
            }
        }
        return result;
    }

    //分析师关注股票图表数据
    public  Map getAnalystPayStockChart(Map paras){
        boolean real_price=false;
        if(paras.getOrDefault("order_by","").toString().contains("real_price")){
            real_price=true;
            paras.remove("order_by");
        }
        Map result=new HashMap();
        result.put("total",0);
        List<Map> queryList=analystForcastDao.getPayStockChart(paras);
        List<String> currentSharePriceInfoList=new ArrayList<>();
        List<Map<String, Object>> item=new ArrayList<>();
        Map abcMap=new HashMap();
        if(queryList!=null){
            paras.remove("limits");
            result.put("total",analystForcastDao.getPayStockChart(paras)!=null?analystForcastDao.getPayStockChart(paras).size():0);
            List<AbcIndustryModel> abcIndustryModels=null;
            for(Map dataMap:queryList){
                currentSharePriceInfoList= companyManagerService.getStockRealTimePrice(dataMap.get("stockcode").toString());
                if(currentSharePriceInfoList!=null && currentSharePriceInfoList.size()>2){
                    dataMap.put("real_price",currentSharePriceInfoList.get(1));
                }else{
                    dataMap.put("real_price","0");
                }
                abcMap.put("indu_code",dataMap.get("industry"));
                abcIndustryModels=abcIndustryDao.selectEntity(abcMap);
                if(abcIndustryModels!=null && abcIndustryModels.size()>0){
                    dataMap.put("industry",abcIndustryModels.get(0).getIndu_name());
                }else{
                    dataMap.put("industry","");
                }
                item.add(dataMap);
            }
            if(real_price){
                Collections.sort(item, new Comparator<Map<String, Object>>(){
                    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                        Double name1 =Double.parseDouble(o1.get("real_price").toString()) ;//name1是从你list里面拿出来的一个
                        Double name2= Double.parseDouble(o2.get("real_price").toString()); //name1是从你list里面拿出来的第二个name
                        if(paras.getOrDefault("order_type","").toString().equals("asc")){
                            return name1.compareTo(name2);
                        }else{
                            return name2.compareTo(name1);
                        }
                    }
                });
            }
        }
        result.put("item",item);
        return  result;
    }

    //分析师关注股票折线图
    public Map getAnalystStockPayLine(Map paras){
        Map result=new HashMap();
        paras.put("group_by"," GROUP BY time ");
        List<String> currentSharePriceInfoList=new ArrayList<>();
        List<AnalystForcastModel> datas=analystForcastDao.getByPeoUniCodePredictive(paras);
        List<Map> real=new ArrayList<>();
        List<Map> target=new ArrayList<>();
        if(datas!=null){
            for(AnalystForcastModel data:datas){
                Map<String,Object> real_map=new HashMap<>();
                Map<String,Object> target_map=new HashMap<>();
                currentSharePriceInfoList= companyManagerService.getStockRealTimePrice(data.getStockcode());
                if(currentSharePriceInfoList!=null && currentSharePriceInfoList.size()>2){
                    real_map.put("real_price",Double.parseDouble(currentSharePriceInfoList.get(1)));
                }else{
                    real_map.put("real_price",0);
                }
                if(data.getTarget_price()!=null && data.getTarget_price_high()!=null){
                    target_map.put("target_price",(Double.parseDouble(data.getTarget_price()+"")+Double.parseDouble(data.getTarget_price_high()+""))/2);
                }else{
                    target_map.put("target_price",data.getTarget_price()!=null?data.getTarget_price():0);
                }
                target_map.put("reach_days",data.getReach_days()!=null?data.getReach_days():0);
                target_map.put("time",data.getTime());
                real_map.put("reach_days",data.getReach_days()!=null?data.getReach_days():0);
                real_map.put("time",data.getTime());
                real.add(real_map);
                target.add(target_map);
            }
        }
        result.put("real_line",real);
        result.put("target_line",target);
        return result;
    }

    @Async
    Map<String, Object> getAnalystInfoWhenPriorIsReportCount(String peoUniCode, String indu_name) throws Exception {
        Map<String, Object> item = new HashMap<>();
        String analystName = analystBasicinfoDao.selectByPeoUniCode(peoUniCode).getName();
        Map<String, String> reportRequest = new HashMap<>();
        reportRequest.put("keyword", "ALL");
        reportRequest.put("offset", "0");
        reportRequest.put("limit", "1");
        reportRequest.put("author", analystName);
        String apiReportRet = HttpUtil.post(solrReportUrl, reportRequest, null);
        if (apiReportRet != null && !apiReportRet.isEmpty()) {
            JSONObject data = JSONObject.parseObject(apiReportRet).getJSONObject("data");
            if (data != null) {
//                JSONArray reportItems = data.getJSONArray("item");
                item.put("reportCount", data.get("total_count"));
                item.put("peoUniCode", peoUniCode);
            }
        }

        return item;
    }
}
