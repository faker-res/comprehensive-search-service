package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.RedisUtil;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.dao.notice.IAnalystBasicinfoDao;
import la.niub.abcapi.servicecompre.dao.notice.IAnalystChanStatusDao;
import la.niub.abcapi.servicecompre.dao.notice.IAnalystHonorDao;
import la.niub.abcapi.servicecompre.dao.notice.IHiborAuthorCountDao;
import la.niub.abcapi.servicecompre.dao.notice.IHiborDao;
import la.niub.abcapi.servicecompre.dao.reporter.INormalValuationIndexDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISecBasicInfoDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISecIndustryNewDao;
import la.niub.abcapi.servicecompre.dao.reporter.ITransacDao;
import la.niub.abcapi.servicecompre.dao.reporter.IWechatPublicDao;
import la.niub.abcapi.servicecompre.model.AnalystBasicinfoModel;
import la.niub.abcapi.servicecompre.model.AnalystChanStatusModel;
import la.niub.abcapi.servicecompre.model.AnalystHonorModel;
import la.niub.abcapi.servicecompre.model.HiborAuthorCountModel;
import la.niub.abcapi.servicecompre.model.SecBasicInfoModel;
import la.niub.abcapi.servicecompre.model.SecIndustryNewModel;
import la.niub.abcapi.servicecompre.model.TransacModel;
import la.niub.abcapi.servicecompre.model.WechatPublicModel;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockNewssetBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarAnalystBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarAnalystOrderBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarStockBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarStockCompanyBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarStockCompanyResultBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarStockExpertBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarStockExpertResultBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarWeChatBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarWeChatHotArticleBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarWeChatHotPublicBO;
import la.niub.abcapi.servicecompre.model.response.client.WechatArticleResponse;
import la.niub.abcapi.servicecompre.model.response.client.wechat.WechatArticleDataItemResponse;
import la.niub.abcapi.servicecompre.service.ICardService;
import la.niub.abcapi.servicecompre.service.ICompanyManagerService;
import la.niub.abcapi.servicecompre.service.ISidebarService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SidebarServiceImpl implements ISidebarService {

    private static Logger logger = LogManager.getLogger(SidebarServiceImpl.class);

    @Autowired
    ICompanyManagerService companyManagerService;

    @Autowired
    ISecBasicInfoDao secBasicInfoDao;

    @Autowired
    ISecIndustryNewDao secIndustryNewDao;

    @Autowired
    ITransacDao transacDao;

    @Autowired
    INormalValuationIndexDao normalValuationIndexDao;

    @Autowired
    IHiborDao hiborDao;

    @Autowired
    IAnalystHonorDao analystHonorDao;

    @Autowired
    IAnalystBasicinfoDao analystBasicinfoDao;

    @Autowired
    IHiborAuthorCountDao hiborAuthorCountDao;

    @Autowired
    IWechatPublicDao wechatPublicDao;

    @Autowired
    IAnalystChanStatusDao analystChanStatusDao;

    @Value("${feign.client.wechatArticle.url}")
    private String wechatArticleUrl;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ICardService cardService;

    @Override
    public SidebarStockBO buildStock(Integer stockCode, Integer industryLimit, Integer authorLimit) {
        SidebarStockBO sidebarStockBO = new SidebarStockBO();
        SecBasicInfoModel stockInfo = secBasicInfoDao.selectBySecUniCode((long)stockCode);
        if (stockInfo == null){
            return sidebarStockBO;
        }

        //同行业公司
        SidebarStockCompanyBO sidebarStockCompanyBO = new SidebarStockCompanyBO();
        sidebarStockCompanyBO.setSource("company");
        sidebarStockCompanyBO.setTitle("同行业公司");
        sidebarStockCompanyBO.setResult(buildIndustryCompanyTotal(stockInfo,industryLimit));
        Long sec_uni_code = stockInfo.getSec_uni_code();
        String indu_name = secIndustryNewDao.getInduNameBySecUniCode(sec_uni_code);
        sidebarStockCompanyBO.setIndu_name(indu_name);

        //个股专家
        SidebarStockExpertBO sidebarStockExpertBO = new SidebarStockExpertBO();
        sidebarStockExpertBO.setSource("stock_expert");
        sidebarStockExpertBO.setTitle("个股专家");
        sidebarStockExpertBO.setResult(buildStockExpert(stockInfo,authorLimit));

        sidebarStockBO.setCompany(sidebarStockCompanyBO);
        sidebarStockBO.setStock_expert(sidebarStockExpertBO);

        return sidebarStockBO;
    }

    private List<SidebarStockExpertResultBO> buildStockExpert(SecBasicInfoModel stockInfo, Integer authorLimit){
        Integer limit = 200;
//        List<HiborModel> hiborModelList = hiborDao.buildRecordsByStockWithAnalystAndLimit(stockInfo.getAbc_code(),limit);
//        Set<String> signAuthorList = new HashSet<>();
//        for (HiborModel item : hiborModelList){
//            String analyst = item.getAnalyst().trim();
//            String[] analystList = analyst.split(";");
//            for (String str : analystList){
//                if (StringUtils.isNotEmpty(str)){
//                    str = str.trim();
//                    str = str.contains("#") ? str.substring(0,str.indexOf("#")).trim() : str;
//                    if (StringUtils.isNotEmpty(str)){
//                        signAuthorList.add(str);
//                    }
//                }
//            }
//        }
//        signAuthorList.remove("null,null,null");
        List<String> analystList = hiborDao.findAnalyst(stockInfo.getAbc_code(),limit);
        Set<String> signAuthorList = new HashSet<>();
        for (String item : analystList){
            if (item.contains("null")){
                continue;
            }
            for (String str : item.trim().split(";")){
                str = str.trim();
                if (StringUtils.isNotEmpty(str)){
                    str = str.contains("#") ? str.substring(0,str.indexOf("#")).trim() : str;
                    if (StringUtils.isNotEmpty(str)){
                        signAuthorList.add(str);
                    }
                }
            }
        }

        List<SidebarStockExpertResultBO> list = new ArrayList<>();
        if (signAuthorList.isEmpty()){
            return list;
        }

        //最近的一届新财富分析师 是那一届
//        Date maxYear = analystHonorDao.buildMaxTimeByTime();
//        List<AnalystHonorModel> signHonorList = analystHonorDao.buildRecordsByAnalyst(new ArrayList<>(signAuthorList),maxYear,authorLimit);
        List<AnalystHonorModel> signHonorList = analystHonorDao.buildRecordsByAnalyst(new ArrayList<>(signAuthorList),authorLimit);
        if (signHonorList.size() < 2){
            return list;
        }

        for (AnalystHonorModel item : signHonorList){
            SidebarStockExpertResultBO sidebarStockExpertResultBO = new SidebarStockExpertResultBO();
            AnalystBasicinfoModel analystBasicinfoModel = analystBasicinfoDao.selectByPeoUniCode(item.getPeo_uni_code());
            sidebarStockExpertResultBO.setImage(analystBasicinfoModel.getImage());
            sidebarStockExpertResultBO.setName(item.getAnalyst());
            sidebarStockExpertResultBO.setDirection(item.getDirection());

            AnalystChanStatusModel analystChanStatusModel = analystChanStatusDao.selectByPeoUniCode(item.getPeo_uni_code());
            String analyst_code = "";
            String organ = item.getOrgan();
            if(analystChanStatusModel != null){
                if(analystChanStatusModel.getAnalyst_code() != null){
                    analyst_code = analystChanStatusModel.getAnalyst_code();
                }
                if(analystChanStatusModel.getOrg_sname() != null){
                    organ = analystChanStatusModel.getOrg_sname();
                }
            }
            sidebarStockExpertResultBO.setOrgan(organ);
            sidebarStockExpertResultBO.setAnalyst_code(analyst_code);
            sidebarStockExpertResultBO.setPeo_uni_code(item.getPeo_uni_code());

            list.add(sidebarStockExpertResultBO);
        }
        return list;
    }

    private List<SidebarStockCompanyResultBO> buildIndustryCompanyTotal(SecBasicInfoModel stockInfo, Integer industryLimit){
        List<SidebarStockCompanyResultBO> list = new ArrayList<>();

        //找出所属 申万三级行业
//        SecIndustryNewModel industryT = secIndustryNewDao.buildInduBySecUniCode(stockInfo.getSec_uni_code());
//        List<SecIndustryNewModel> industryCompany = secIndustryNewDao.buildInduCompanyByTInduCode(industryT.getThird_indu_code());

        List<SecIndustryNewModel> industryT = secIndustryNewDao.buildInduListBySecUniCode(stockInfo.getSec_uni_code());
        List<String> thirdInduCodes = new ArrayList<>();
        for (SecIndustryNewModel item : industryT){
            if (item.getIndu_standard() == 1001014 // 优先展示申万
                    && StringUtils.isNotEmpty(item.getThird_indu_code())){
                thirdInduCodes.add(item.getThird_indu_code());
            }
        }
        if (thirdInduCodes.isEmpty()){
            return list;
        }
        List<SecIndustryNewModel> industryCompany = secIndustryNewDao.buildInduCompanyByTInduCodes(thirdInduCodes);

//        List<SecIndustryNewModel> industryT = secIndustryNewDao.buildInduListBySecUniCode(stockInfo.getSec_uni_code());
//        String firstInduCode = "";
//        for (SecIndustryNewModel item : industryT){
//            if (item.getIndu_standard() == 1001014){ // 优先展示申万
//                firstInduCode = item.getFirst_indu_code();
//                break;
//            }
//        }
//        List<SecIndustryNewModel> industryCompany = secIndustryNewDao.selectByFirstInduCode(firstInduCode);

        //日期
        Date lday = buildLastDayForTransac(stockInfo);
//        Calendar calendar = Calendar.getInstance();
//        calendar.clear();
//        calendar.set(2011,0,4,0,0,0);
//        lday = calendar.getTime();
//        String ldayStr = TimeUtil.toString(lday,"yyyy-MM-dd HH:mm:ss");
        logger.info("find for lday {}",TimeUtil.toString(lday,"yyyy-MM-dd HH:mm:ss"));
        List<Long> secUniCodesOfAll = new ArrayList<>();
        for (SecIndustryNewModel item : industryCompany){
            secUniCodesOfAll.add(item.getSec_uni_code());
        }
        lday = normalValuationIndexDao.getLastAccountDateBefore(lday);
//        logger.info(secUniCodesOfAll);
//        industryLimit= 1000;
        List<Long> normalValuationIndexModelList = normalValuationIndexDao.getSecUniCodeByAccountDateWithSecUniCode(lday,secUniCodesOfAll,industryLimit);
        if (ObjectUtils.isEmpty(normalValuationIndexModelList)){
            return list;
        }

        List<Long> secUniCodes = new ArrayList<>();
        for (Long item : normalValuationIndexModelList){
            secUniCodes.add(item);
        }
//        secUniCode.add(169L);
        List<SecBasicInfoModel> secBasicInfoModelList = secBasicInfoDao.getListBySecUniCodesNullEndDate(secUniCodes);

//        for (SecBasicInfoModel item : secBasicInfoModelList){
//            if (!item.getSec_uni_code().equals(stockInfo.getSec_uni_code())){
//                SidebarStockCompanyResultBO sidebarStockCompanyResultBO = new SidebarStockCompanyResultBO();
//                sidebarStockCompanyResultBO.setSec_name(item.getSec_name());
//                sidebarStockCompanyResultBO.setAbc_code(item.getAbc_code());
//                sidebarStockCompanyResultBO.setSec_uni_code(item.getSec_uni_code());
//                CardStockNewssetBO cardStockNewssetBO = companyManagerService.buildCompanyNewestStockInfo(item.getAbc_code());
//                sidebarStockCompanyResultBO.setStock_newsset(cardStockNewssetBO);
//                list.add(sidebarStockCompanyResultBO);
//            }
//        }

        Map<Long,SecBasicInfoModel> secBasicInfoModelMap = new HashMap<>();
        for (SecBasicInfoModel item : secBasicInfoModelList){
            secBasicInfoModelMap.put(item.getSec_uni_code(),item);
        }
        for (Long secUniCode : normalValuationIndexModelList){
            SecBasicInfoModel secBasicInfoModel = secBasicInfoModelMap.get(secUniCode);
            if (secBasicInfoModel != null && !secUniCode.equals(stockInfo.getSec_uni_code())){
                SidebarStockCompanyResultBO sidebarStockCompanyResultBO = new SidebarStockCompanyResultBO();
                sidebarStockCompanyResultBO.setSec_name(secBasicInfoModel.getSec_name());
                sidebarStockCompanyResultBO.setAbc_code(secBasicInfoModel.getAbc_code());
                sidebarStockCompanyResultBO.setCom_uni_code(secBasicInfoModel.getCom_uni_code());
                sidebarStockCompanyResultBO.setSec_uni_code(secBasicInfoModel.getSec_uni_code());
                CardStockNewssetBO cardStockNewssetBO = companyManagerService.buildCompanyNewestStockInfo(secBasicInfoModel.getAbc_code());
                sidebarStockCompanyResultBO.setStock_newsset(cardStockNewssetBO);
                list.add(sidebarStockCompanyResultBO);
            }
        }

        return list;
    }

    private Date buildLastDayForTransac(SecBasicInfoModel stockInfo){
        //通过 市场 找出过往的60天 那一天最近就返回最近的一个交易日
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date date = calendar.getTime();
//        String lday = TimeUtil.toString(date,"yyyy-MM-dd 00:00:00");
//        List<TransacModel> transacModelList = transacDao.buildLastDayWithSecurityTypeAndDateAndNumber(stockInfo.getSecurity_type(),lday,60);
//        for (TransacModel item : transacModelList){
//            if (item.getIf_trading_day().equals("是")){
//                return item.getStart_trading_time();
//            }
//        }
//        return null;
        TransacModel transacModel = transacDao.buildLastDayWithSecurityTypeAndDateAndNumberOne(stockInfo.getSecurity_type(),calendar.getTime());
        return transacModel != null ? transacModel.getStart_trading_time() : null;
    }

    /**
     * 分析师侧边栏
     * @param peoUniCode
     * @param limit
     * @return
     */
    @Override
    public SidebarAnalystBO buildAnalyst(String peoUniCode, Integer limit) {
        AnalystBasicinfoModel basicinfoModel = analystBasicinfoDao.selectByPeoUniCode(peoUniCode);
        if (basicinfoModel.getDirection() == null){
            return null;
        }
        String direction = basicinfoModel.getDirection();
        if(direction.indexOf("、") >= 0 ){
            String[] directionArr = direction.split("、");
            direction = directionArr[0];
        }
        if(direction.isEmpty()){
            return null;
        }

        SidebarAnalystBO sidebarAnalystBO = new SidebarAnalystBO();
        sidebarAnalystBO.setDirection(direction);
        List<String> d = Arrays.asList(direction);
        List<String> peoRankList = new ArrayList<>();
        // 同领域财富排行
        List<AnalystHonorModel> honor_rank = analystHonorDao.buildRecordsOrderByHonorRank(d, limit+1);
        if(honor_rank != null){
            for(AnalystHonorModel item : honor_rank){
                peoRankList.add(item.getPeo_uni_code());
            }
        }
        // 同领域上榜次数排行
        List<AnalystHonorModel> honor_num= analystHonorDao.buildRecordsOrderByHonorNum(d, limit+1);
        if(honor_num != null){
            for(AnalystHonorModel item : honor_num) {
                if(!peoRankList.contains(item.getPeo_uni_code())){
                    peoRankList.add(item.getPeo_uni_code());
                }
            }
        }
        // 同领域分析师研报数量
        List<HiborAuthorCountModel> report_num = cardService.getAnalystReportsNum(direction);
        if(report_num != null){
            Integer i = 0;
            for(HiborAuthorCountModel item : report_num){
                if(i == limit+1){
                    break;
                }
                if(!peoRankList.contains(item.getPeo_uni_code())){
                    peoRankList.add(item.getPeo_uni_code());
                }
                i++;
            }
        }
        List<AnalystBasicinfoModel> peoList = analystBasicinfoDao.selectByPeoUniCodeList(peoRankList);
        Map<String,AnalystBasicinfoModel> peoMap = new HashMap<>();
        if (peoList != null && !peoList.isEmpty()) {
            for (AnalystBasicinfoModel aItem : peoList) {
                String keyCode = String.valueOf(aItem.getPeo_uni_code());
                if (!peoMap.containsKey(keyCode)) {
                    peoMap.put(keyCode, aItem);
                }
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

        List<SidebarAnalystOrderBO> list = new ArrayList<>();
        if(honor_rank != null){
            for(AnalystHonorModel item : honor_rank){
                if(item.getPeo_uni_code().equals(peoUniCode)){
                    continue;
                }
                if(list.size() == limit){
                    break;
                }
                SidebarAnalystOrderBO sidebarAnalystOrderBO = new SidebarAnalystOrderBO();
                sidebarAnalystOrderBO = buildSideItem(sidebarAnalystOrderBO,peoMap,peoResumeMap,item.getPeo_uni_code());
                sidebarAnalystOrderBO.setRanking(item.getRanking());
                sidebarAnalystOrderBO.setTime(item.getTime());
                list.add(sidebarAnalystOrderBO);
            }
        }
        sidebarAnalystBO.setHonor_rank(list);

        // 同领域上榜次数排行
        list = new ArrayList<>();
        if(honor_num != null){
            for(AnalystHonorModel item : honor_num){
                if(item.getPeo_uni_code().equals(peoUniCode)){
                    continue;
                }
                if(list.size() == limit){
                    break;
                }
                SidebarAnalystOrderBO sidebarAnalystOrderBO = new SidebarAnalystOrderBO();
                sidebarAnalystOrderBO = buildSideItem(sidebarAnalystOrderBO,peoMap,peoResumeMap,item.getPeo_uni_code());
                sidebarAnalystOrderBO.setHonor_total(item.getHonor_total());
                list.add(sidebarAnalystOrderBO);
            }
        }
        sidebarAnalystBO.setHonor_num(list);

        // 同领域分析师研报数量
        list = new ArrayList<>();
        if(report_num != null) {
            for (HiborAuthorCountModel item : report_num) {
                if (item.getPeo_uni_code().equals(peoUniCode)) {
                    continue;
                }
                if (list.size() == limit) {
                    break;
                }
                SidebarAnalystOrderBO sidebarAnalystOrderBO = new SidebarAnalystOrderBO();
                sidebarAnalystOrderBO = buildSideItem(sidebarAnalystOrderBO, peoMap, peoResumeMap, item.getPeo_uni_code());
                sidebarAnalystOrderBO.setReport_total(item.getReport_num());
                list.add(sidebarAnalystOrderBO);
            }
        }
        sidebarAnalystBO.setReport_num(list);

        return sidebarAnalystBO;
    }

    private SidebarAnalystOrderBO buildSideItem(SidebarAnalystOrderBO sidebarAnalystOrderBO,
                                                Map<String,AnalystBasicinfoModel> peoMap,
                                                Map<String,AnalystChanStatusModel> peoResumeMap,
                                                String peoUniCode){
        String image = "";
        String name = "";
        if(peoMap.containsKey(peoUniCode)){
            AnalystBasicinfoModel analystBasicinfoModel = peoMap.get(peoUniCode);
            if(analystBasicinfoModel.getImage() != null){
                image = analystBasicinfoModel.getImage();
            }
            if(analystBasicinfoModel.getName() != null){
                name = analystBasicinfoModel.getName();
            }
        }
        sidebarAnalystOrderBO.setImage(image);
        sidebarAnalystOrderBO.setPeo_uni_code(peoUniCode);
        sidebarAnalystOrderBO.setAnalyst(name);
        String analyst_code = "";
        String organ = "";
        if(peoResumeMap.containsKey(peoUniCode)){
            AnalystChanStatusModel analystChanStatusModel = peoResumeMap.get(peoUniCode);
            if(analystChanStatusModel != null){
                if(analystChanStatusModel.getAnalyst_code() != null){
                    analyst_code = analystChanStatusModel.getAnalyst_code();
                }
                if(analystChanStatusModel.getOrg_sname() != null){
                    organ = analystChanStatusModel.getOrg_sname();
                }
            }
        }
        sidebarAnalystOrderBO.setOrgan(organ);
        sidebarAnalystOrderBO.setAnalyst_code(analyst_code);
        return sidebarAnalystOrderBO;
    }





    @Override
    public SidebarWeChatBO buildWeChat(Integer code) {

        SidebarWeChatBO sidebarWeChatBO = new SidebarWeChatBO();
        List<SidebarWeChatHotArticleBO> hotArticleList = new ArrayList<>();
        List<SidebarWeChatHotPublicBO> hotPublicList = new ArrayList<>();

        List<WechatPublicModel> publicList = wechatPublicDao.getByRandom(code,5);
        if (publicList != null && !publicList.isEmpty()) {

            for (WechatPublicModel publicItem : publicList) {
//                SidebarWeChatHotPublicBO sidebarWeChatHotPublicItem = new SidebarWeChatHotPublicBO();
//                sidebarWeChatHotPublicItem.setId(publicItem.getId());
//                sidebarWeChatHotPublicItem.setName(publicItem.getPublicName());
//                sidebarWeChatHotPublicItem.setAccount(publicItem.getPublicAccount());
//                sidebarWeChatHotPublicItem.setImage(publicItem.getPublicImage());
//                sidebarWeChatHotPublicItem.setSummary(publicItem.getPublicSummary());
//                sidebarWeChatHotPublicItem.setAuthentication(publicItem.getPublicAuthentication());
//                sidebarWeChatHotPublicItem.setTags(publicItem.getPublicTags());
//                sidebarWeChatHotPublicItem.setQrCode(publicItem.getQrCode());
//                sidebarWeChatHotPublicItem.setMonthCount(publicItem.getMonthCount());
//                sidebarWeChatHotPublicItem.setSource(publicItem.getSource());
//                hotPublicList.add(sidebarWeChatHotPublicItem);
                SidebarWeChatHotPublicBO sidebarWeChatHotPublicItem = new SidebarWeChatHotPublicBO();
                sidebarWeChatHotPublicItem.setId(publicItem.getId().intValue());
                sidebarWeChatHotPublicItem.setName(publicItem.getPublic_name());
                sidebarWeChatHotPublicItem.setAccount(publicItem.getPublic_account());
                sidebarWeChatHotPublicItem.setImage(publicItem.getPublic_image());
                sidebarWeChatHotPublicItem.setSummary(publicItem.getPublic_summary());
                sidebarWeChatHotPublicItem.setAuthentication(publicItem.getPublic_authentication());
                sidebarWeChatHotPublicItem.setTags(publicItem.getPublic_tags());
                sidebarWeChatHotPublicItem.setQrCode(publicItem.getQr_code());
                sidebarWeChatHotPublicItem.setMonthCount(publicItem.getMonth_count());
                sidebarWeChatHotPublicItem.setSource(publicItem.getSource());
                hotPublicList.add(sidebarWeChatHotPublicItem);
            }
        }


        WechatPublicModel wechatPublicRet = wechatPublicDao.selectByPrimaryKey(code.longValue());

        if (wechatPublicRet != null) {
            Map<String, String> params = new HashMap<>();
            //params.put("single", "true");
            params.put("single", "false");
            params.put("core_name", "core_news");
            try {
                StringBuilder keyword = new StringBuilder();
                StringBuilder source_name_s = new StringBuilder();
                source_name_s.append(" ").append("\"").append(wechatPublicRet.getPublic_name().trim()).append("\"");
                keyword.append("$channel:\"微信\"").append(" AND ")
                        .append("source_name_s:(").append(source_name_s.toString().substring(1)).append(")");
                //params.put("keyword", URLEncoder.encode("$source_name:\"" + wechatPublicRet.getPublic_name() + "\"","UTF-8"));
                params.put("keyword", URLEncoder.encode(keyword.toString(),"UTF-8"));
            } catch (Exception e) {

            }
            params.put("limit", "5");
//          params.put("offset", "5");
            params.put("offset", "0");
            params.put("prior","time");

            String wechatArtilceRet = HttpUtil.get(wechatArticleUrl, params, null);

            if (wechatArtilceRet != null && !wechatArtilceRet.isEmpty()) {
                WechatArticleResponse wechatArticleResponse = JSON.parseObject(wechatArtilceRet, WechatArticleResponse.class);
                if (wechatArticleResponse.getData() != null) {
                    if ( wechatArticleResponse.getData().getItem() != null && !wechatArticleResponse.getData().getItem().isEmpty()) {

                        for (WechatArticleDataItemResponse item : wechatArticleResponse.getData().getItem()){
                            SidebarWeChatHotArticleBO artilceItem = new SidebarWeChatHotArticleBO();
                            artilceItem.setId(item.getId());
                            artilceItem.setName(item.getSource_name());
                            artilceItem.setTitle(item.getTitle());
                            artilceItem.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(item.getTime()) * 1000));
                            artilceItem.setUrl(item.getUrl());
                            hotArticleList.add(artilceItem);
                        }

                    }
                }
            }
        }

        
        sidebarWeChatBO.setHotArtice(hotArticleList);
        sidebarWeChatBO.setHotPublic(hotPublicList);
        return sidebarWeChatBO;
    }


}
