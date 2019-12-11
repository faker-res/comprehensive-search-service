package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;
import la.niub.abcapi.servicecompre.dao.reporter.*;
import la.niub.abcapi.servicecompre.model.ComLogoModel;
import la.niub.abcapi.servicecompre.model.FundBasicInfoModel;
import la.niub.abcapi.servicecompre.model.FundBenchmarkModel;
import la.niub.abcapi.servicecompre.model.FundComHolderModel;
import la.niub.abcapi.servicecompre.model.FundComManagementInfoModel;
import la.niub.abcapi.servicecompre.model.FundInvestDetailModel;
import la.niub.abcapi.servicecompre.model.FundInvestIndustryModel;
import la.niub.abcapi.servicecompre.model.FundKeeperScaleModel;
import la.niub.abcapi.servicecompre.model.FundManagerBasicInfoModel;
import la.niub.abcapi.servicecompre.model.FundManagerChgModel;
import la.niub.abcapi.servicecompre.model.FundManagerPerfModel;
import la.niub.abcapi.servicecompre.model.FundManagerStatModel;
import la.niub.abcapi.servicecompre.model.FundNavModel;
import la.niub.abcapi.servicecompre.model.FundNavReturnModel;
import la.niub.abcapi.servicecompre.model.FundSubRedemStatusModel;
import la.niub.abcapi.servicecompre.model.OrgBasicInfoModel;
import la.niub.abcapi.servicecompre.model.OrgBasicInfoWithBLOBs;
import la.niub.abcapi.servicecompre.model.PeoImageModel;
import la.niub.abcapi.servicecompre.model.SecBasicInfoModel;
import la.niub.abcapi.servicecompre.model.SystemConstModel;
import la.niub.abcapi.servicecompre.model.VNewsHeatFundModel;
import la.niub.abcapi.servicecompre.model.VNewsSentimentFundModel;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyDetailBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyHolderBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyManagerBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundDetailBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundDistributeBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundEarnBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundLastDistributeBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundManagerBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundSubjectBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundTopInvestBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundWorthBO;
import la.niub.abcapi.servicecompre.model.request.fund.FundOtherHeatBO;
import la.niub.abcapi.servicecompre.model.request.fund.FundOtherSentimentBO;
import la.niub.abcapi.servicecompre.model.response.fund.FundRiseRankingItemResponse;
import la.niub.abcapi.servicecompre.service.IFundCompanyService;
import la.niub.abcapi.servicecompre.service.IFundService;
import la.niub.abcapi.servicecompre.service.IStockService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class FundServiceImpl implements IFundService {

    private static final Logger logger = LogManager.getLogger(FundServiceImpl.class);

    @Value("${oss.file_server_host}")
    private String oss_path_prefix;

    @Autowired
    IStockService stockService;

    @Autowired
    IFundBasicInfoDao fundBasicInfoDao;

    @Autowired
    IFundBenchmarkDao fundBenchmarkDao;

    @Autowired
    IFundSubRedemStatusDao fundSubRedemStatusDao;

    @Autowired
    ISecPlateInfoDao secPlateInfoDao;

    @Autowired
    ISystemConstDao systemConstDao;

    @Autowired
    IFundInvestDetailDao fundInvestDetailDao;

    @Autowired
    IFundInvestIndustryDao fundInvestIndustryDao;

    @Autowired
    IOrgBasicInfoDao orgBasicInfoDao;

    @Autowired
    IFundManagerChgDao fundManagerChgDao;

    @Autowired
    IFundComManagementInfoDao fundComManagementInfoDao;

    @Autowired
    IFundKeeperScaleDao fundKeeperScaleDao;

    @Autowired
    IFundComHolderDao fundComHolderDao;

    @Autowired
    IFundNavDao fundNavDao;

    @Autowired
    IFundNavReturnDao fundNavReturnDao;

    @Autowired
    IComLogoDao comLogoDao;

    @Autowired
    IVNewsSentimentFundDao newsSentimentFundDao;

    @Autowired
    IVNewsHeatFundDao newsHeatFundDao;

    @Autowired
    ISecBasicInfoDao secBasicInfoDao;

    @Autowired
    IFundManagerBasicInfoDao fundManagerBasicInfoDao;

    @Autowired
    IFundManagerStatDao fundManagerStatDao;

    @Autowired
    IFundManagerPerfDao fundManagerPerfDao;

    @Autowired
    IPeoImageDao peoImageDao;

    private String peoImageOssHost = "http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/";

    @Autowired
    IFundShareChangerDao iFundShareChangerDao;

    @Autowired
    IFundCompanyService iFundCompanyService;

    @Override
    public FundDetailBO getFundDetail(Long secUniCode) {
        FundDetailBO fundDetailBO = new FundDetailBO();

        SecBasicInfoModel secBasicInfoModel = secBasicInfoDao.selectByPrimaryKey(secUniCode);
        if (secBasicInfoModel == null){
            return fundDetailBO;
        }
        fundDetailBO.setCode(secBasicInfoModel.getAbc_code());
        fundDetailBO.setName(secBasicInfoModel.getSec_name());
        fundDetailBO.setFullname(secBasicInfoModel.getSec_fname());
        fundDetailBO.setIsExpired(secBasicInfoModel.getEnd_date() == null ? false : true);

        FundBasicInfoModel fundBasicInfoModel = fundBasicInfoDao.selectByPrimaryKey(secBasicInfoModel.getSec_uni_code());
        if (fundBasicInfoModel != null){
            fundDetailBO.setFullname(fundBasicInfoModel.getFund_name());
            fundDetailBO.setMethod(StringUtils.defaultString(fundBasicInfoModel.getFund_type(),"")+StringUtils.defaultString(fundBasicInfoModel.getFund_method(),""));
            fundDetailBO.setEstablishTime(fundBasicInfoModel.getFund_found_date());
        }

        fundDetailBO.setType(secPlateInfoDao.getFundType(secBasicInfoModel.getSec_uni_code()));
        fundDetailBO.setRisk(secPlateInfoDao.getFundRisk(secBasicInfoModel.getSec_uni_code()));

        FundBenchmarkModel fundBenchmarkModel = fundBenchmarkDao.selectBySecUniCode(secBasicInfoModel.getSec_uni_code());
        if (fundBenchmarkModel != null){
            fundDetailBO.setBenchmark(fundBenchmarkModel.getFund_benchmark());
        }

        FundSubRedemStatusModel fundSubRedemStatusModel = fundSubRedemStatusDao.selectBySecUniCode(secBasicInfoModel.getSec_uni_code());
        if (fundSubRedemStatusModel != null){
            SystemConstModel systemConstModel = systemConstDao.selectBySystemUniCode(fundSubRedemStatusModel.getSub_redem_status());
            if (fundSubRedemStatusModel != null){
                fundDetailBO.setRedem(systemConstModel.getSystem_name());
            }
        }

        return fundDetailBO;
    }

    @Override
    public List<FundTopInvestBO> getFundTopInvest(Long secUniCode, Integer limit) {
        List<FundTopInvestBO> fundTopInvestBOList = new ArrayList<>();

        Date lastEndDate = fundInvestDetailDao.getLastEndDate(secUniCode);
        List<FundInvestDetailModel>fundInvestDetailModelList = fundInvestDetailDao.selectTopByEndDate(secUniCode,lastEndDate,limit);

        List<Long> secUniCodes = new ArrayList<>();
        for (FundInvestDetailModel item : fundInvestDetailModelList){
            secUniCodes.add(item.getInv_sec_uni_code());
        }
        if (secUniCodes.isEmpty()){
            return fundTopInvestBOList;
        }
        List<SecBasicInfoModel> secBasicInfoModelList = stockService.getRecordsBySecUniCodes(secUniCodes);
        Map<Long,String> secCodes = new HashMap<>();
        for (SecBasicInfoModel item : secBasicInfoModelList){
            secCodes.put(item.getSec_uni_code(),item.getAbc_code());
        }

        for (FundInvestDetailModel item : fundInvestDetailModelList){
            FundTopInvestBO fundTopInvestBO = new FundTopInvestBO();
            fundTopInvestBO.setName(item.getInv_sec_name());
            fundTopInvestBO.setCode(secCodes.get(item.getInv_sec_uni_code()));
            fundTopInvestBO.setNetratio(item.getSec_value_net_ratio());
            fundTopInvestBO.setValue(item.getHold_sec_value());
            fundTopInvestBOList.add(fundTopInvestBO);
        }

        return fundTopInvestBOList;
    }

    @Override
    public FundLastDistributeBO getLastDistribute(String stockCode, Integer limit) {
        FundLastDistributeBO fundLastDistributeBO = new FundLastDistributeBO();

        SecBasicInfoModel secBasicInfoModel = stockService.buildRecordByStockCode(stockCode);
        if (secBasicInfoModel == null){
            return fundLastDistributeBO;
        }

        List<FundDistributeBO> fundDistributeBOList = new ArrayList<>();
        Date lastEndDate = fundInvestIndustryDao.getLastEndDate(secBasicInfoModel.getSec_uni_code());
        List<FundInvestIndustryModel> fundInvestIndustryModelList = fundInvestIndustryDao.selectByEndDate(secBasicInfoModel.getSec_uni_code(),lastEndDate,limit);
        for (FundInvestIndustryModel item : fundInvestIndustryModelList){
            FundDistributeBO fundDistributeBO = new FundDistributeBO();
            fundDistributeBO.setName(item.getIndu_published_name());
            fundDistributeBO.setInduStandard(item.getIndu_standard());
            fundDistributeBO.setCode(item.getIndu_published_code());
            fundDistributeBO.setValue(item.getIndu_value());
//            fundDistributeBO.setNetratio(item.getIndu_value_net_ratio());
//            fundDistributeBO.setChgRatio3m(item.getIndu_value_chg_ratio_3m());
//            fundDistributeBO.setChgRatio6m(item.getIndu_value_chg_ratio_6m());
//            fundDistributeBO.setChgRatio1y(item.getIndu_value_chg_ratio_1y());
            //--mock
//            fundDistributeBO.setChgRatio3m(new BigDecimal(RandomUtil.getRandom()).setScale(2,BigDecimal.ROUND_HALF_UP));
//            fundDistributeBO.setChgRatio6m(new BigDecimal(RandomUtil.getRandom()).setScale(2,BigDecimal.ROUND_HALF_UP));
//            fundDistributeBO.setChgRatio1y(new BigDecimal(RandomUtil.getRandom()).setScale(2,BigDecimal.ROUND_HALF_UP));
            //--mock
            fundDistributeBOList.add(fundDistributeBO);
        }
        fundLastDistributeBO.setDistributes(fundDistributeBOList);

        FundInvestIndustryModel fundInvestIndustryTotal = fundInvestIndustryDao.selectTotalByEndDate(secBasicInfoModel.getSec_uni_code(),lastEndDate);
        FundDistributeBO fundDistributeBO = new FundDistributeBO();
        fundDistributeBO.setName(fundInvestIndustryTotal.getIndu_published_name());
        fundDistributeBO.setInduStandard(fundInvestIndustryTotal.getIndu_standard());
        fundDistributeBO.setCode(fundInvestIndustryTotal.getIndu_published_code());
        fundDistributeBO.setValue(fundInvestIndustryTotal.getIndu_value());
//        fundDistributeBO.setNetratio(fundInvestIndustryTotal.getIndu_value_net_ratio());
//        fundDistributeBO.setChgRatio3m(fundInvestIndustryTotal.getIndu_value_chg_ratio_3m());
//        fundDistributeBO.setChgRatio6m(fundInvestIndustryTotal.getIndu_value_chg_ratio_6m());
//        fundDistributeBO.setChgRatio1y(fundInvestIndustryTotal.getIndu_value_chg_ratio_1y());
        //--mock
//        fundDistributeBO.setChgRatio3m(new BigDecimal(RandomUtil.getRandom()).setScale(2,BigDecimal.ROUND_HALF_UP));
//        fundDistributeBO.setChgRatio6m(new BigDecimal(RandomUtil.getRandom()).setScale(2,BigDecimal.ROUND_HALF_UP));
//        fundDistributeBO.setChgRatio1y(new BigDecimal(RandomUtil.getRandom()).setScale(2,BigDecimal.ROUND_HALF_UP));
        //--mock
        fundLastDistributeBO.setDistributeTotal(fundDistributeBO);

        return fundLastDistributeBO;
    }

    @Override
    public Map<String, List<FundDistributeBO>> getPeriodDistribute(String stockCode,List<String> industryCodes, Date endDate) {
        SecBasicInfoModel secBasicInfoModel = stockService.buildRecordByStockCode(stockCode);
        if (secBasicInfoModel == null){
            return new HashMap<>();
        }

        List<FundInvestIndustryModel> fundInvestIndustryModelList = fundInvestIndustryDao.getPeriodByIndustryCodes(secBasicInfoModel.getSec_uni_code(),industryCodes,endDate);

        Map<String, List<FundDistributeBO>> fundInvestIndustryModelMap = new TreeMap<>();
        for (FundInvestIndustryModel item : fundInvestIndustryModelList){
            String dateKey = TimeUtil.toString(item.getEnd_date(),"yyyy-MM-dd");
            List<FundDistributeBO> fundDistributeBOS;
            if (fundInvestIndustryModelMap.containsKey(dateKey)){
                fundDistributeBOS = fundInvestIndustryModelMap.get(dateKey);
            }else{
                fundDistributeBOS = new ArrayList<>();
            }

            FundDistributeBO fundDistributeBO = new FundDistributeBO();
            fundDistributeBO.setName(item.getIndu_published_name());
            fundDistributeBO.setInduStandard(item.getIndu_standard());
            fundDistributeBO.setCode(item.getIndu_published_code());
            fundDistributeBO.setValue(item.getIndu_value());
//            fundDistributeBO.setNetratio(item.getIndu_value_net_ratio());
            fundDistributeBOS.add(fundDistributeBO);

            fundInvestIndustryModelMap.put(dateKey,fundDistributeBOS);
        }
        for (Map.Entry<String, List<FundDistributeBO>> entry : fundInvestIndustryModelMap.entrySet()){
            Collections.sort(entry.getValue(), Comparator.comparing(o -> ((Integer) industryCodes.indexOf(o.getCode()))));
        }
        fundInvestIndustryModelMap = ((TreeMap) fundInvestIndustryModelMap).descendingMap();

        industryCodes.clear();
        industryCodes.add("TOT");
        List<FundInvestIndustryModel> fundInvestIndustryTotalList = fundInvestIndustryDao.getPeriodByIndustryCodes(secBasicInfoModel.getSec_uni_code(),industryCodes,endDate);
        List<FundDistributeBO> fundDistributeTotalBOS = new ArrayList<>();
        for (FundInvestIndustryModel item : fundInvestIndustryTotalList){
            FundDistributeBO fundDistributeBO = new FundDistributeBO();
            fundDistributeBO.setName(item.getIndu_published_name());
            fundDistributeBO.setInduStandard(item.getIndu_standard());
            fundDistributeBO.setCode(item.getIndu_published_code());
            fundDistributeBO.setValue(item.getIndu_value());
//            fundDistributeBO.setNetratio(item.getIndu_value_net_ratio());
            fundDistributeBO.setDate(item.getEnd_date());
            fundDistributeTotalBOS.add(fundDistributeBO);
        }
        fundInvestIndustryModelMap.put("TOT",fundDistributeTotalBOS);
        return fundInvestIndustryModelMap;
    }

    @Override
    public Date getLastDistributeDateBefore(Long secUniCode, Date date) {
        return fundInvestIndustryDao.getLastEndDateBefore(secUniCode,date);
    }

    @Override
    public List<FundDistributeBO> getDistribute(Long secUniCode, FundPeriodEnum fundPeriodEnum, Integer limit) {
        List<FundDistributeBO> fundDistributeBOList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.add(fundPeriodEnum.getField(),fundPeriodEnum.getAmount());
        calendar.add(Calendar.MONTH,1);

        Date endDate = getLastDistributeDateBefore(secUniCode,calendar.getTime());
        List<FundInvestIndustryModel> fundInvestIndustryModelList = fundInvestIndustryDao.selectByEndDate(secUniCode,endDate,limit);
        for (FundInvestIndustryModel item : fundInvestIndustryModelList){
            FundDistributeBO fundDistributeBO = new FundDistributeBO();
            fundDistributeBO.setName(item.getIndu_published_name());
            fundDistributeBO.setInduStandard(item.getIndu_standard());
            fundDistributeBO.setCode(item.getIndu_published_code());
            fundDistributeBO.setValue(item.getIndu_value());
            fundDistributeBO.setNetratio(item.getIndu_value_net_ratio() != null ? item.getIndu_value_net_ratio().doubleValue() : 0);
            fundDistributeBO.setDate(item.getEnd_date());

//            if (fundPeriodEnum == FundPeriodEnum.M3) {
//                fundDistributeBO.setChgRatio(item.getIndu_value_chg_ratio_3m() != null ? item.getIndu_value_chg_ratio_3m().doubleValue() : 0);
//            } else if (fundPeriodEnum == FundPeriodEnum.M6) {
//                fundDistributeBO.setChgRatio(item.getIndu_value_chg_ratio_6m() != null ? item.getIndu_value_chg_ratio_6m().doubleValue() : 0);
//            } else if (fundPeriodEnum == FundPeriodEnum.Y1) {
//                fundDistributeBO.setChgRatio(item.getIndu_value_chg_ratio_1y() != null ? item.getIndu_value_chg_ratio_1y().doubleValue() : 0);
//            } else {
//                continue;
//            }
            fundDistributeBO.setChgRatio(item.getIndu_value_chg_ratio_3m() != null ? item.getIndu_value_chg_ratio_3m().doubleValue() : 0);

            fundDistributeBOList.add(fundDistributeBO);
        }
        return fundDistributeBOList;
    }

    @Override
    public Map<String,List<FundDistributeBO>> getMultiDistribute(Long secUniCode, Integer quantity, Integer limit) {
        Map<String,List<FundDistributeBO>> fundDistributeBOMap = new TreeMap<>();

        List<Date> dateList = fundInvestIndustryDao.getLastMultiEndDate(secUniCode,quantity);
        for (Date endDate : dateList){
            List<FundInvestIndustryModel> fundInvestIndustryModelList = fundInvestIndustryDao.selectByEndDate(secUniCode,endDate,limit);

            List<FundDistributeBO> fundDistributeBOS = new ArrayList<>();
            for (FundInvestIndustryModel item : fundInvestIndustryModelList){
                FundDistributeBO fundDistributeBO = new FundDistributeBO();
                fundDistributeBO.setName(item.getIndu_published_name());
                fundDistributeBO.setInduStandard(item.getIndu_standard());
                fundDistributeBO.setCode(item.getIndu_published_code());
                fundDistributeBO.setValue(item.getIndu_value());
                fundDistributeBO.setNetratio(item.getIndu_value_net_ratio() != null ? item.getIndu_value_net_ratio().doubleValue() : 0);
                fundDistributeBO.setDate(item.getEnd_date());
                fundDistributeBOS.add(fundDistributeBO);
            }

            String dateKey = TimeUtil.toString(endDate,"yyyy-MM-dd");
            fundDistributeBOMap.put(dateKey,fundDistributeBOS);
        }

        fundDistributeBOMap = ((TreeMap) fundDistributeBOMap).descendingMap();
        return fundDistributeBOMap;
    }

    @Override
    public FundCompanyDetailBO getCompanyDetail(Long comUniCode) {
        FundCompanyDetailBO fundCompanyDetailBO = new FundCompanyDetailBO();

        OrgBasicInfoWithBLOBs orgBasicInfoModel = orgBasicInfoDao.selectByComUniCodeWithBLOBs(comUniCode);
        if (orgBasicInfoModel == null){
            return fundCompanyDetailBO;
        }
        //公司名称
        fundCompanyDetailBO.setCompany_name(orgBasicInfoModel.getOrg_name());
        //公司英文名称
        fundCompanyDetailBO.setCompany_en_name(orgBasicInfoModel.getOrg_ename());
        //公司简介
        fundCompanyDetailBO.setBrif(orgBasicInfoModel.getOrg_profile());
        //成立时间
        fundCompanyDetailBO.setEstablish_time(orgBasicInfoModel.getBuild_time());
        //官网
        fundCompanyDetailBO.setUrl(orgBasicInfoModel.getOrg_web());

        //公司logo
        ComLogoModel comLogoModel = comLogoDao.selectByComUniCode(comUniCode);
        if (comLogoModel != null){
            fundCompanyDetailBO.setImage(oss_path_prefix + "/" + comLogoModel.getOss_path());
        }

        //公司性质
        SystemConstModel comNatureModel = systemConstDao.selectBySystemUniCode(orgBasicInfoModel.getCom_nature());
        if (comNatureModel != null){
            fundCompanyDetailBO.setCompany_nature(comNatureModel.getSystem_name());
        }

        //基金经理数量
        FundManagerChgModel fundManagerChgModel = fundManagerChgDao.selectByFundKeeperCode(comUniCode);
        if (fundManagerChgModel != null){
            fundCompanyDetailBO.setManager_num(fundManagerChgModel.getFund_manager_quantity());
        }

        //基金管理规模
        FundKeeperScaleModel fundKeeperScaleModel = fundKeeperScaleDao.selectByFundKeeperCode(comUniCode);
        if (fundKeeperScaleModel != null){
            fundCompanyDetailBO.setCompany_scale(fundKeeperScaleModel.getFund_nav());
        }

        //基金管理规模排名
        List<Long> rankByFunNav = (List<Long>) ObjectUtils.defaultIfNull(fundKeeperScaleDao.getRankByFunNav(),new ArrayList<>());
        if (rankByFunNav.contains(comUniCode)){
            fundCompanyDetailBO.setRank(rankByFunNav.indexOf(comUniCode)+1);
        }

        List<FundManagerBO> fundManagerBOList = new ArrayList<>();
        /**
         * 董事长
         */
        FundComManagementInfoModel bigBoss = fundComManagementInfoDao.findBigBossByOrgUniCode(comUniCode);
        if (bigBoss != null){
            FundManagerBO fundManagerBO = new FundManagerBO();
            fundManagerBO.setId(bigBoss.getPeo_uni_code());
            fundManagerBO.setName(bigBoss.getName());
            fundManagerBO.setPosition("董事长");
            fundManagerBO.setCompany_name(orgBasicInfoModel.getOrg_name());
            fundManagerBO.setStart_time(bigBoss.getBegin_date());
            if (bigBoss.getBegin_date() != null){
                Integer totalDays = (int) ((System.currentTimeMillis() - bigBoss.getBegin_date().getTime()) / (1000*3600*24));
                fundManagerBO.setOffice_term(totalDays);
            }
            PeoImageModel peoImageModel = peoImageDao.selectByPeoUniCode(bigBoss.getPeo_uni_code());
            if (peoImageModel != null && StringUtils.isNotEmpty(peoImageModel.getOss_path())){
                fundManagerBO.setHead_img(oss_path_prefix + "/" +peoImageModel.getOss_path());
            }
            fundManagerBOList.add(fundManagerBO);
        }
        /**
         * 总经理
         */
        FundComManagementInfoModel boss = fundComManagementInfoDao.findBossByOrgUniCode(comUniCode);
        if (boss != null){
            FundManagerBO fundManagerBO = new FundManagerBO();
            fundManagerBO.setId(boss.getPeo_uni_code());
            fundManagerBO.setName(boss.getName());
            fundManagerBO.setPosition("总经理");
            fundManagerBO.setCompany_name(orgBasicInfoModel.getOrg_name());
            fundManagerBO.setStart_time(boss.getBegin_date());
            if (boss.getBegin_date() != null){
                Integer totalDays = (int) ((System.currentTimeMillis() - boss.getBegin_date().getTime()) / (1000*3600*24));
                fundManagerBO.setOffice_term(totalDays);
            }
            PeoImageModel peoImageModel = peoImageDao.selectByPeoUniCode(boss.getPeo_uni_code());
            if (peoImageModel != null && StringUtils.isNotEmpty(peoImageModel.getOss_path())){
                fundManagerBO.setHead_img(oss_path_prefix + "/" +peoImageModel.getOss_path());
            }
            fundManagerBOList.add(fundManagerBO);

            fundCompanyDetailBO.setBoss_name(boss.getName());
        }
        fundCompanyDetailBO.setBoss_list(fundManagerBOList);

        return fundCompanyDetailBO;
    }

    @Override
    public List<FundCompanyHolderBO> getHolder(Long comUniCode,Integer limit) {
        List<FundCompanyHolderBO> fundCompanyHolderBOList = new ArrayList<>();

        List<FundComHolderModel> fundComHolderModelList = fundComHolderDao.getLastByOrgUniCode(comUniCode,limit);
        for (FundComHolderModel item : fundComHolderModelList){
            FundCompanyHolderBO fundCompanyHolderBO = new FundCompanyHolderBO();
            fundCompanyHolderBO.setInvestor_name(item.getShareholder_name());
            fundCompanyHolderBO.setBegin_date(item.getBegin_date());
            fundCompanyHolderBO.setShareholding_ratio(item.getHold_shr_prop() != null ? item.getHold_shr_prop().doubleValue() : 0);
            String nature = "";
            if (item.getShareholder_type() != null && item.getShareholder_type().equals("1")){
                nature = "机构";
            }else if (item.getShareholder_type() != null && item.getShareholder_type().equals("2")){
                nature = "个人";
            }

            fundCompanyHolderBO.setNature(nature);
            fundCompanyHolderBOList.add(fundCompanyHolderBO);
        }

        return fundCompanyHolderBOList;
    }


    @Override
    public OrgBasicInfoModel getStockCompany(Long secUniCode) {
        FundBasicInfoModel fundBasicInfoModel = fundBasicInfoDao.selectByPrimaryKey(secUniCode);
        if (fundBasicInfoModel == null){
            return null;
        }
        return orgBasicInfoDao.selectByPrimaryKey(fundBasicInfoModel.getMana_uni_code());
    }

    @Override
    public List<FundSubjectBO> getSubject(Long secUniCode, FundPeriodEnum fundPeriodEnum, Integer limit) {
        List<FundSubjectBO> fundSubjectBOList = new ArrayList<>();

//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.set(Calendar.MILLISECOND,0);
//        calendar.set(Calendar.MINUTE,0);
//        calendar.set(Calendar.HOUR_OF_DAY,0);
//        calendar.add(fundPeriodEnum.getField(),fundPeriodEnum.getAmount());
//        calendar.add(Calendar.MONTH,1);

//        Date endDate = getLastDistributeDateBefore(secBasicInfoModel.getSec_uni_code(),calendar.getTime());
        Date endDate = fundInvestIndustryDao.getLastEndDate(secUniCode);
        List<FundInvestIndustryModel> fundInvestIndustryModelList = fundInvestIndustryDao.selectByEndDate(secUniCode, endDate, limit);
        for (FundInvestIndustryModel item : fundInvestIndustryModelList) {
            FundSubjectBO fundSubjectBO = new FundSubjectBO();
            fundSubjectBO.setName(item.getIndu_published_name());
            fundSubjectBO.setDate(item.getEnd_date());

            if (fundPeriodEnum == FundPeriodEnum.M3) {
                fundSubjectBO.setAmount(item.getIndu_value_chg_3m());
                fundSubjectBO.setRatio(item.getIndu_value_chg_ratio_3m() != null ? item.getIndu_value_chg_ratio_3m().doubleValue() : 0);
            } else if (fundPeriodEnum == FundPeriodEnum.M6) {
                fundSubjectBO.setAmount(item.getIndu_value_chg_6m());
                fundSubjectBO.setRatio(item.getIndu_value_chg_ratio_6m() != null ? item.getIndu_value_chg_ratio_6m().doubleValue() : 0);
            } else if (fundPeriodEnum == FundPeriodEnum.Y1) {
                fundSubjectBO.setAmount(item.getIndu_value_chg_1y());
                fundSubjectBO.setRatio(item.getIndu_value_chg_ratio_1y() != null ? item.getIndu_value_chg_ratio_1y().doubleValue() : 0);
            } else {
                continue;
            }

            //--mock
//            fundSubjectBO.setAmount(new BigDecimal(RandomUtil.getRandom()).setScale(2,BigDecimal.ROUND_HALF_UP));
//            fundSubjectBO.setRatio(new BigDecimal(RandomUtil.getRandom()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            //--mock

            Boolean income = fundSubjectBO.getAmount() != null
                    && fundSubjectBO.getAmount().compareTo(new BigDecimal(0)) > 0;
            fundSubjectBO.setIncome(income);
//            fundSubjectBO.setIncome(RandomUtil.getRandom() > 0.55);

            fundSubjectBOList.add(fundSubjectBO);
        }
        return fundSubjectBOList;
    }





    @Override
    public FundCompanyManagerBO getFundManager(Long secUniCode,Integer limit){
//        logger.info("FundServiceImpl.getFundManager for secUniCode {}",secUniCode);
        FundCompanyManagerBO fundCompanyManagerBO = new FundCompanyManagerBO();

        OrgBasicInfoModel orgBasicInfoModel = null;
        FundBasicInfoModel fundBasicInfoModel = fundBasicInfoDao.selectByPrimaryKey(secUniCode);
        if (fundBasicInfoModel != null){
            orgBasicInfoModel = orgBasicInfoDao.selectByPrimaryKey(fundBasicInfoModel.getMana_uni_code());
        }
//        logger.info("orgBasicInfoModel {}",orgBasicInfoModel);

        List<FundManagerBO> managerNowList = new ArrayList<>();
        List<FundManagerBO> managerHistoryList = new ArrayList<>();

//        logger.info("find for sec_uni_code {} limit {}",secUniCode,limit);
        List<Long> peoUniCodes = new ArrayList<>();

//        List<FundManagerBasicInfoModel> fundManagerBasicInfoNowList = fundManagerBasicInfoDao.selectBySecUniCodeAndNullEndDate(secUniCode,true,limit);
//        List<FundManagerBasicInfoModel> fundManagerBasicInfoHistoryList = fundManagerBasicInfoDao.selectBySecUniCodeAndNullEndDate(secUniCode,false,limit);
//        List<FundManagerBasicInfoModel> fundManagerBasicInfoModelList = new ArrayList<>();
//        if (fundManagerBasicInfoNowList != null && !fundManagerBasicInfoNowList.isEmpty()) {
//            for (FundManagerBasicInfoModel item : fundManagerBasicInfoNowList) {
//                peoUniCodes.add(item.getPeo_uni_code());
//            }
//            fundManagerBasicInfoModelList.addAll(fundManagerBasicInfoNowList);
//        }
//        if (fundManagerBasicInfoHistoryList != null && !fundManagerBasicInfoHistoryList.isEmpty()) {
//            for (FundManagerBasicInfoModel item : fundManagerBasicInfoHistoryList) {
//                peoUniCodes.add(item.getPeo_uni_code());
//            }
//            fundManagerBasicInfoModelList.addAll(fundManagerBasicInfoHistoryList);
//        }

        List<FundManagerBasicInfoModel> fundManagerBasicInfoModelList = fundManagerBasicInfoDao.selectBySecUniCode(secUniCode);
        if (fundManagerBasicInfoModelList != null && !fundManagerBasicInfoModelList.isEmpty()) {
            for (FundManagerBasicInfoModel item : fundManagerBasicInfoModelList) {
                peoUniCodes.add(item.getPeo_uni_code());
            }
        }

//        logger.info("peoUniCodes {}",peoUniCodes);
        if (peoUniCodes.isEmpty()){
            return fundCompanyManagerBO;
        }

        Map<Long, FundManagerStatModel> statMap = new HashMap<>();
        Map<Long, FundManagerPerfModel> perfMap = new HashMap<>();
        Map<Long, String> imageMap = new HashMap<>();

        //整体排名
        List<Long> rankByFundManageNav = (List<Long>) ObjectUtils.defaultIfNull(fundManagerStatDao.getRankByFundManageNav(),new ArrayList<>());

        // 任职起始日
        List<FundManagerStatModel> managerStatList = fundManagerStatDao.selectByPeoUniCodes(peoUniCodes);
        if (managerStatList != null && !managerStatList.isEmpty()) {
            for (FundManagerStatModel statItem : managerStatList) {
                statMap.put(statItem.getPeo_uni_code(), statItem);
            }
        }

        // 任期年化收益
        List<FundManagerPerfModel> managerPerfList = fundManagerPerfDao.getLastBySecUniCodeAndPeoUniCodes(secUniCode,peoUniCodes);
        if (managerPerfList != null && !managerPerfList.isEmpty()) {
            for (FundManagerPerfModel perfItem : managerPerfList) {
                perfMap.put(perfItem.getPeo_uni_code(), perfItem);
            }
        }

        // 头像
        List<PeoImageModel> imageList = peoImageDao.selectByPeoUniCodes(peoUniCodes);
        if (imageList != null && !imageList.isEmpty()) {
            for (PeoImageModel imageItem : imageList) {
                if (StringUtils.isNotEmpty(imageItem.getOss_path())) {
                    imageMap.put(imageItem.getPeo_uni_code(), peoImageOssHost + imageItem.getOss_path());
                }
            }
        }

        for (FundManagerBasicInfoModel item : fundManagerBasicInfoModelList) {
            if (item.getBegin_date() == null){
                continue;
            }
            if (item.getEnd_date() == null && managerNowList.size() >= limit){
                continue;
            }else if (item.getEnd_date() != null && managerHistoryList.size() >= limit){
                continue;
            }

            FundManagerBO fundManager = new FundManagerBO();
            Long peoUniCode = item.getPeo_uni_code();
            fundManager.setId(peoUniCode);
            fundManager.setName(item.getFund_manager_name());
            fundManager.setPosition("基金经理");
            fundManager.setStart_time(item.getBegin_date());
            fundManager.setEnd_time(item.getEnd_date());
            Integer totalDays = (int) ((System.currentTimeMillis() - item.getBegin_date().getTime()) / (1000*3600*24));
            fundManager.setOffice_term(totalDays);

            if (orgBasicInfoModel != null){
                fundManager.setCompany_name(orgBasicInfoModel.getOrg_name());
            }
            if (imageMap.containsKey(peoUniCode)) {
                fundManager.setHead_img(imageMap.get(peoUniCode));
            }
            if (statMap.containsKey(peoUniCode)) {
                FundManagerStatModel stat = statMap.get(peoUniCode);
                fundManager.setAmount(stat.getFund_manage_nav());
            }

            if (perfMap.containsKey(peoUniCode)) {
                fundManager.setReturn_ratio(perfMap.get(peoUniCode).getAnnual_yield() != null ? perfMap.get(peoUniCode).getAnnual_yield().doubleValue() : 0);
            }

            if (rankByFundManageNav.contains(peoUniCode)){
                fundManager.setRank(rankByFundManageNav.indexOf(peoUniCode)+1);
            }

            if (item.getEnd_date() == null){
                managerNowList.add(fundManager);
            }else{
                managerHistoryList.add(fundManager);
            }
        }

        SecBasicInfoModel secBasicInfoModel = secBasicInfoDao.selectByPrimaryKey(secUniCode);
        if (secBasicInfoModel.getEnd_date() == null) {
            fundCompanyManagerBO.setManager_now(managerNowList);
        }

        fundCompanyManagerBO.setManager_history(managerHistoryList);
        return fundCompanyManagerBO;

    }

    @Override
    public List<FundWorthBO> getWorth(Long secUniCode, Date endDate,String type) {
        List<FundWorthBO> fundWorthBOList = new ArrayList<>();

        List<FundNavModel> fundNavModelList = fundNavDao.selectByEndDate(secUniCode,endDate);
        for (FundNavModel item : fundNavModelList){
            FundWorthBO fundWorthBO = new FundWorthBO();
            if (type.equals("UNI")){
                fundWorthBO.setNum(item.getUnit_nav());
            }else if (type.equals("TOT")){
                fundWorthBO.setNum(item.getCumu_unit_nav());
            }else{
                continue;
            }

            fundWorthBO.setDate(item.getEnd_date());
            fundWorthBO.setTime(item.getEnd_date());
            fundWorthBOList.add(fundWorthBO);
        }
        return fundWorthBOList;
    }

    @Override
    public List<FundEarnBO> getEarn(Long secUniCode) {
        List<FundEarnBO> fundEarnBOList = new ArrayList<>();

        //SELECT sec_uni_code,end_date,nav_return_t_1y 今年以来,rise_rate 过去1天,nav_return_l_1w 过去1周,nav_return_l_1m 过去1月,
        //nav_return_l_3m 过去3月,nav_return_l_6m 过去6月,nav_return_l_1y 过去1年,nav_return_l_3y 过去3年,nav_return_l_5y 过去5年,
        //nav_return_found 成立以来 FROM fund_nav_return
        FundNavReturnModel fundNavReturnModel = fundNavReturnDao.getLastBySecUniCode(secUniCode);
        fundEarnBOList.add(new FundEarnBO("今年以来",fundNavReturnModel.getNav_return_t_1y() != null ? fundNavReturnModel.getNav_return_t_1y().doubleValue() : 0));
        fundEarnBOList.add(new FundEarnBO("过去1天",fundNavReturnModel.getRise_rate() != null ? fundNavReturnModel.getRise_rate().doubleValue() : 0));
        fundEarnBOList.add(new FundEarnBO("过去1周",fundNavReturnModel.getNav_return_l_1w() != null ? fundNavReturnModel.getNav_return_l_1w().doubleValue() : 0));
        fundEarnBOList.add(new FundEarnBO("过去1月",fundNavReturnModel.getNav_return_l_1m() != null ? fundNavReturnModel.getNav_return_l_1m().doubleValue() : 0));
        fundEarnBOList.add(new FundEarnBO("过去3月",fundNavReturnModel.getNav_return_l_3m() != null ? fundNavReturnModel.getNav_return_l_3m().doubleValue() : 0));
        fundEarnBOList.add(new FundEarnBO("过去6月",fundNavReturnModel.getNav_return_l_6m() != null ? fundNavReturnModel.getNav_return_l_6m().doubleValue() : 0));
        fundEarnBOList.add(new FundEarnBO("过去1年",fundNavReturnModel.getNav_return_l_1y() != null ? fundNavReturnModel.getNav_return_l_1y().doubleValue() : 0));
        fundEarnBOList.add(new FundEarnBO("过去3年",fundNavReturnModel.getNav_return_l_3y() != null ? fundNavReturnModel.getNav_return_l_3y().doubleValue() : 0));
        fundEarnBOList.add(new FundEarnBO("过去5年",fundNavReturnModel.getNav_return_l_5y() != null ? fundNavReturnModel.getNav_return_l_5y().doubleValue() : 0));
        fundEarnBOList.add(new FundEarnBO("成立以来",fundNavReturnModel.getNav_return_found() != null ? fundNavReturnModel.getNav_return_found().doubleValue() : 0));
        return fundEarnBOList;
    }

    @Override
    public List<FundOtherSentimentBO> getSentiment(Long secUniCode, Date endDate) {
        List<FundOtherSentimentBO> fundOtherSentimentBOList = new ArrayList<>();

        String endDateStr = TimeUtil.toString(endDate,"yyyyMMdd");
        List<VNewsSentimentFundModel> vNewsSentimentFundModelList = newsSentimentFundDao.selectBySecUniCode(secUniCode,endDateStr);
        for (VNewsSentimentFundModel item : vNewsSentimentFundModelList){
            if (item.getPolarity() == null){
                continue;
            }
            FundOtherSentimentBO fundOtherSentimentBO = new FundOtherSentimentBO();
            fundOtherSentimentBO.setTime(TimeUtil.parseDateStr(item.getPartition_time(),"yyyyMMdd"));
            fundOtherSentimentBO.setNum(item.getPolarity().doubleValue());
            fundOtherSentimentBOList.add(fundOtherSentimentBO);
        }
        return fundOtherSentimentBOList;
    }

    @Override
    public List<FundOtherHeatBO> getHeat(Long secUniCode, Date endDate) {
        List<FundOtherHeatBO> fundOtherHeatBOList = new ArrayList<>();

        String endDateStr = TimeUtil.toString(endDate,"yyyyMMdd");
        List<VNewsHeatFundModel> vNewsHeatFundModelList = newsHeatFundDao.selectBySecUniCode(secUniCode,endDateStr);
        for (VNewsHeatFundModel item : vNewsHeatFundModelList){
            if (item.getHeat() == null){
                continue;
            }
            FundOtherHeatBO fundOtherHeatBO = new FundOtherHeatBO();
            fundOtherHeatBO.setTime(TimeUtil.parseDateStr(item.getPartition_time(),"yyyyMMdd"));
            fundOtherHeatBO.setNum(item.getHeat().doubleValue());
            fundOtherHeatBOList.add(fundOtherHeatBO);
        }
        return fundOtherHeatBOList;
    }

    @Override
    public Map<String, Object> getSameFieldFund(Long sec_uni_code, String type, String fundType,
                                                      String prior, String order, Integer offset, Integer limit) throws Exception {

        Map<String, Object> result = new HashMap<>();

        if (offset == 0) {
            limit = limit - 1;
        }

        if (StringUtil.isEmpty(prior)) {
            prior = "cumu_unit_nav";
        }

        if (prior.equals("purchase")) {
            prior = "tot_app_share";
        }

        if (prior.equals("redemption")) {
            prior = "tot_redem_share";
        }

        if (StringUtil.isEmpty(order)) {
            order = "DESC";
        }

        if (StringUtil.isEmpty(fundType)) {
            fundType = null;
        }

        if (fundType == null && type.equals("DIFFERENT_COM") ) {
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> currentFund = getCurrentFundInfo(sec_uni_code);
            list.add(currentFund);
            result.put("items", list);
            result.put("totalCount", 1);
            return result;
        }

        Long com_uni_code = secBasicInfoDao.getComUniCodeBySecUniCode(sec_uni_code);
        List<Long> secUniCodeList = new ArrayList<>();
        switch (type) {
            case "SAME_COM":
                secUniCodeList = secBasicInfoDao.getSameComSecUniCodeListBySecUniCode(com_uni_code, sec_uni_code);
                if (!StringUtil.isEmpty(fundType)) {
                    secUniCodeList = secPlateInfoDao.getSecUniCodeListBySecUniCodeListAndPlateCode(secUniCodeList, fundType);
                }
                break;
            case "DIFFERENT_COM":
                Long comUniCode = Long.parseLong(fundType);
                secUniCodeList = secBasicInfoDao.getSameComSecUniCodeListBySecUniCode(comUniCode, null);
                break;
            case "ALL_MARKET":
                secUniCodeList = secBasicInfoDao.getAllFundCodeExceptSecUniCode(sec_uni_code);
                if (!StringUtil.isEmpty(fundType)) {
                    secUniCodeList = secPlateInfoDao.getSecUniCodeListBySecUniCodeListAndPlateCode(secUniCodeList, fundType);
                }
                break;
            default:
                return null;
        }

        if (StringUtil.isEmpty(secUniCodeList)) {
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> currentFund = getCurrentFundInfo(sec_uni_code);
            list.add(currentFund);
            result.put("items", list);
            result.put("totalCount", 1);
            return result;
        }


        result.put("totalCount", secUniCodeList.size() + 1);

        List<Map<String, Object>> fundInfoList = getFundInfoBySecUniCodeList(secUniCodeList, prior, order, offset, limit);
        if (offset == 0) {
            fundInfoList.add(0, getCurrentFundInfo(sec_uni_code));
        }

        result.put("items", fundInfoList);
        return result;
    }

    @Override
    public List<Map<String, String>> getFundType(Long sec_uni_code, String type) throws Exception {
        switch (type) {
            case "SAME_COM":
                Long com_uni_code = secBasicInfoDao.getComUniCodeBySecUniCode(sec_uni_code);
                return iFundCompanyService.getProductCategory(com_uni_code);
            default:
                List<Map<String, String>> allFundType = secPlateInfoDao.getAllFundType();
                if (allFundType == null) {
                    allFundType = new ArrayList<>();
                }

                Map<String, String> map = new HashMap<>();
                map.put("plate_name", "全部");
                map.put("plate_code", "");
                allFundType.add(0, map);
                return allFundType;
        }
    }

    @Override
    public Map<String, Object> getRiseRanking(Long sec_uni_code, Integer limit) throws Exception {
        Map<String, Object> result = new HashMap<>();

        Map<String, String> plateMap = secPlateInfoDao.getFundTypeCodeBySecUniCode(sec_uni_code);
        if (plateMap == null || StringUtil.isEmpty(plateMap.get("plate_code"))
                || StringUtil.isEmpty(plateMap.get("plate_name"))) {
            return null;
        }

        String plateName = plateMap.get("plate_name").toString();
        String plateCode = plateMap.get("plate_code").toString();

        List<Long> secUniCodeList = secPlateInfoDao.getSecUnICodeListByPlateCode(plateCode);

        if (secUniCodeList == null || secUniCodeList.isEmpty()) {
            result.put("plate_name", plateName);
            result.put("total", 0);
            result.put("rankingData", new ArrayList<>());
            return result;
        }

        secUniCodeList = secBasicInfoDao.getValidSecUniCodeBySecUniCodeList(secUniCodeList);

        List<Map<String, Object>> param = fundNavReturnDao.getMaxDateBySecUniCodeList(secUniCodeList);

        if (param == null || param.isEmpty()) {
            result.put("plate_name", plateName);
            result.put("total", 0);
            result.put("rankingData", new ArrayList<>());
            return result;
        }

        List<FundRiseRankingItemResponse> items = fundNavReturnDao.getNavReturnL1yBySecUniCodeAndEndDate(param, limit);

        if (items == null ||items.isEmpty()) {
            result.put("plate_name", plateName);
            result.put("total", 0);
            result.put("rankingData", new ArrayList<>());
            return result;
        }

        for (FundRiseRankingItemResponse item : items) {
            if (item != null && !StringUtil.isEmpty(item.getSec_uni_code())) {
                SecBasicInfoModel secBasicInfoModel = secBasicInfoDao.selectByPrimaryKey(item.getSec_uni_code());
                item.setCode(secBasicInfoModel.getAbc_code());
                item.setSec_name(secBasicInfoModel.getSec_name());
            }
        }

        result.put("plate_name", plateName);
        result.put("total", secUniCodeList.size());
        result.put("rankingData", items);

        return result;
    }


    private List<Map<String, Object>> getFundInfoBySecUniCodeList(List<Long> secUniCodeList, String prior,
                                                            String order, Integer offset, Integer limit) throws Exception {

        String priorStr = prior + " is null, " + prior + " " + order + ", sbi.sec_uni_code " + order;
        List<Map<String, Object>> resultList = new ArrayList<>();
        int key = offset;
        switch (prior) {
            case "tot_app_share":
            case "tot_redem_share":
                List<Map<String, Object>> purchaseAndredemptionInfoList = secBasicInfoDao.getRankingWhenTableIsFundShareChange(secUniCodeList, priorStr, offset, limit);
                for (Map<String, Object> item : purchaseAndredemptionInfoList) {
                    Map<String, Object> result = new HashMap<>();
                    String sec_name = item.get("sec_name").toString();
                    Long sec_uni_code = Long.parseLong(item.get("sec_uni_code").toString());
                    String sec_code = item.get("sec_code").toString();
                    result.put("sec_name", sec_name);
                    result.put("abc_code", item.get("abc_code"));
                    result.put("sec_uni_code", sec_uni_code);
                    result.put("sec_code", sec_code);
                    result.put("purchase", item.get("tot_app_share") == null ? null : new BigDecimal(item.get("tot_app_share").toString()));
                    result.put("redemption", item.get("tot_redem_share") == null ? null : new BigDecimal(item.get("tot_redem_share").toString()));

                    String fundManagerName = fundManagerBasicInfoDao.getFundMangerNameBySecUniCode(sec_uni_code);
                    result.put("fund_manager_name", fundManagerName);

                    Map<String, Object> navInfo = fundNavDao.getUnitNavBySecuniCode(sec_uni_code);
                    result.put("unit_nav", navInfo == null || navInfo.get("unit_nav") == null ? null : new BigDecimal(navInfo.get("unit_nav").toString()));
                    result.put("cumu_unit_nav", navInfo == null || navInfo.get("cumu_unit_nav") == null ? null : new BigDecimal(navInfo.get("cumu_unit_nav").toString()));
                    result.put("rise_rate", navInfo == null || navInfo.get("rise_rate") == null ? null : new BigDecimal(navInfo.get("rise_rate").toString()));


                    Map<String, Object> returnInfo = fundNavReturnDao.getReturnInfoBySecUniCode(sec_uni_code);
                    result.put("nav_return_l_1w", returnInfo == null || returnInfo.get("nav_return_l_1w") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_1w").toString()));
                    result.put("nav_return_l_1m", returnInfo == null || returnInfo.get("nav_return_l_1m") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_1m").toString()));
                    result.put("nav_return_l_3m", returnInfo == null || returnInfo.get("nav_return_l_3m") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_3m").toString()));
                    result.put("nav_return_l_6m", returnInfo == null || returnInfo.get("nav_return_l_6m") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_6m").toString()));
                    result.put("nav_return_t_1y", returnInfo == null || returnInfo.get("nav_return_t_1y") == null ? null : new BigDecimal(returnInfo.get("nav_return_t_1y").toString()));

                    key++;
                    result.put("key", offset == 0 ? key + 1 : key);

                    resultList.add(result);
                }
                return resultList;
            case "rise_rate":
            case "unit_nav":
            case "cumu_unit_nav":
                List<Map<String, Object>> navInfoList = secBasicInfoDao.getRankingWhenTableIsFundNav(secUniCodeList, priorStr, offset, limit);
                for (Map<String, Object> item : navInfoList) {
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

                    Map<String, Object> purchaseAndRedemptionInfo = iFundShareChangerDao.getPurchaseAndRedemptionBySecUniCode(sec_uni_code);
                    result.put("purchase", purchaseAndRedemptionInfo == null || purchaseAndRedemptionInfo.get("tot_app_share") == null ? null : new BigDecimal(purchaseAndRedemptionInfo.get("tot_app_share").toString()));
                    result.put("redemption", purchaseAndRedemptionInfo == null || purchaseAndRedemptionInfo.get("tot_redem_share") == null ? null : new BigDecimal(purchaseAndRedemptionInfo.get("tot_redem_share").toString()));

                    Map<String, Object> returnInfo = fundNavReturnDao.getReturnInfoBySecUniCode(sec_uni_code);
                    result.put("nav_return_l_1w", returnInfo == null || returnInfo.get("nav_return_l_1w") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_1w").toString()));
                    result.put("nav_return_l_1m", returnInfo == null || returnInfo.get("nav_return_l_1m") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_1m").toString()));
                    result.put("nav_return_l_3m", returnInfo == null || returnInfo.get("nav_return_l_3m") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_3m").toString()));
                    result.put("nav_return_l_6m", returnInfo == null || returnInfo.get("nav_return_l_6m") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_6m").toString()));
                    result.put("nav_return_t_1y", returnInfo == null || returnInfo.get("nav_return_t_1y") == null ? null : new BigDecimal(returnInfo.get("nav_return_t_1y").toString()));

                    key++;
                    result.put("key", offset == 0 ? key + 1 : key);

                    resultList.add(result);
                }
                return resultList;
            default:
                List<Map<String, Object>> returnInfoList = secBasicInfoDao.getRankingWhenTableIsFundNavReturn(secUniCodeList, priorStr, offset, limit);
                for (Map<String, Object> item : returnInfoList) {
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

                    Map<String, Object> purchaseAndRedemptionInfo = iFundShareChangerDao.getPurchaseAndRedemptionBySecUniCode(sec_uni_code);
                    result.put("purchase", purchaseAndRedemptionInfo == null || purchaseAndRedemptionInfo.get("tot_app_share") == null ? null : new BigDecimal(purchaseAndRedemptionInfo.get("tot_app_share").toString()));
                    result.put("redemption", purchaseAndRedemptionInfo == null || purchaseAndRedemptionInfo.get("tot_redem_share") == null ? null : new BigDecimal(purchaseAndRedemptionInfo.get("tot_redem_share").toString()));

                    Map<String, Object> navInfo = fundNavDao.getUnitNavBySecuniCode(sec_uni_code);
                    result.put("unit_nav", navInfo == null || navInfo.get("unit_nav") == null ? null : new BigDecimal(navInfo.get("unit_nav").toString()));
                    result.put("cumu_unit_nav", navInfo == null || navInfo.get("cumu_unit_nav") == null ? null : new BigDecimal(navInfo.get("cumu_unit_nav").toString()));
                    result.put("rise_rate", navInfo == null || navInfo.get("rise_rate") == null ? null : new BigDecimal(navInfo.get("rise_rate").toString()));

                    key++;
                    result.put("key", offset == 0 ? key + 1 : key);

                    resultList.add(result);
                }
                return resultList;
        }
    }

    @Override
    public Map<String, Object> getFundComInfo(Long sec_uni_code) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Long com_uni_code = secBasicInfoDao.getComUniCodeBySecUniCode(sec_uni_code);
        List<Map<String, Object>> topTenFundCom = fundKeeperScaleDao.getTopTenScaleCom(com_uni_code);
        List<Map<String, Object>> topTenList = new ArrayList<>();
        for (Map<String, Object> item : topTenFundCom) {
            Map<String, Object> topTenItem = new HashMap<>();
            topTenItem.put("com_uni_code", item.get("fund_keeper_code"));
            topTenItem.put("org_sname", item.get("org_sname"));
            topTenList.add(topTenItem);
        }
        result.put("topTen", topTenList);


        Integer ranking = fundKeeperScaleDao.getRankingByComUniCode(com_uni_code);
        if (ranking == null) {
            return result;
        }

        Integer totolCount = fundKeeperScaleDao.getFundComTotalCount();
        Integer offset = 0;
        Integer limit = 11;
        if (ranking >= 6 && ranking <= totolCount - 5 ) {
            offset = ranking - 6;
        } else if (ranking > totolCount - 5){
            offset = totolCount - 11;
        }

        List<Map<String, Object>> comparableFundCom = fundKeeperScaleDao.getComparableFundCom(com_uni_code, offset, limit);
        List<Map<String, Object>> comparableFundComList = new ArrayList<>();
        for (Map<String, Object> item : comparableFundCom) {
            Map<String, Object> comparableFundComItem = new HashMap<>();
            comparableFundComItem.put("com_uni_code", item.get("fund_keeper_code"));
            comparableFundComItem.put("org_sname", item.get("org_sname"));
            comparableFundComList.add(comparableFundComItem);
        }
        result.put("comparableFundCom", comparableFundComList);

        return result;
    }

//    @Override
    private Map<String, Object> getCurrentFundInfo(Long sec_uni_code) throws Exception {
        Map<String, Object> currentFund = new HashMap<>();
        Map secNameAndSecCodeInfo = secBasicInfoDao.getSecNameAndSecCodeBySecUniCode(sec_uni_code);
        currentFund.put("sec_uni_code", sec_uni_code);
        currentFund.put("sec_name", secNameAndSecCodeInfo.get("sec_name"));
        currentFund.put("sec_code", secNameAndSecCodeInfo.get("sec_code"));
        currentFund.put("abc_code", secNameAndSecCodeInfo.get("abc_code"));

        String fundManagerName = fundManagerBasicInfoDao.getFundMangerNameBySecUniCode(sec_uni_code);
        currentFund.put("fund_manager_name", fundManagerName);

        Map<String, Object> navInfo = fundNavDao.getUnitNavBySecuniCode(sec_uni_code);
        currentFund.put("unit_nav", navInfo == null || navInfo.get("unit_nav") == null ? null : new BigDecimal(navInfo.get("unit_nav").toString()));
        currentFund.put("cumu_unit_nav", navInfo == null || navInfo.get("cumu_unit_nav") == null ? null : new BigDecimal(navInfo.get("cumu_unit_nav").toString()));
        currentFund.put("rise_rate", navInfo == null || navInfo.get("rise_rate") == null ? null : new BigDecimal(navInfo.get("rise_rate").toString()));

        Map<String, Object> returnInfo = fundNavReturnDao.getReturnInfoBySecUniCode(sec_uni_code);
        currentFund.put("nav_return_l_1w", returnInfo == null || returnInfo.get("nav_return_l_1w") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_1w").toString()));
        currentFund.put("nav_return_l_1m", returnInfo == null || returnInfo.get("nav_return_l_1m") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_1m").toString()));
        currentFund.put("nav_return_l_3m", returnInfo == null || returnInfo.get("nav_return_l_3m") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_3m").toString()));
        currentFund.put("nav_return_l_6m", returnInfo == null || returnInfo.get("nav_return_l_6m") == null ? null : new BigDecimal(returnInfo.get("nav_return_l_6m").toString()));
        currentFund.put("nav_return_t_1y", returnInfo == null || returnInfo.get("nav_return_t_1y") == null ? null : new BigDecimal(returnInfo.get("nav_return_t_1y").toString()));

        Map<String, Object> purchaseAndRedemptionInfo = iFundShareChangerDao.getPurchaseAndRedemptionBySecUniCode(sec_uni_code);
        currentFund.put("purchase", purchaseAndRedemptionInfo == null || purchaseAndRedemptionInfo.get("tot_app_share") == null ? null : new BigDecimal(purchaseAndRedemptionInfo.get("tot_app_share").toString()));
        currentFund.put("redemption", purchaseAndRedemptionInfo == null || purchaseAndRedemptionInfo.get("tot_redem_share") == null ? null : new BigDecimal(purchaseAndRedemptionInfo.get("tot_redem_share").toString()));

        currentFund.put("key", 1);
        return currentFund;
    }

    @Override
    public Map<String, Object> getFundChart(String sec_uni_codes) throws Exception {
        String[] params = sec_uni_codes.split(",");
        Map<String, Object> result= new HashMap<>();
        for (String param : params) {
            Long sec_uni_code = Long.parseLong(param);

            Map<String, Object> secNameAndSecCode = secBasicInfoDao.getSecNameAndSecCodeBySecUniCode(sec_uni_code);
            if (org.springframework.util.ObjectUtils.isEmpty(secNameAndSecCode)) {
                return null;
            }
            Map<String, Object> chartDate = new LinkedHashMap<>();
            Date endDate = fundNavDao.getLatestDateBySecUniCode(sec_uni_code);
            if(endDate!=null){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(endDate);
                calendar.add(Calendar.YEAR, -1);
                Date startDate = calendar.getTime();
                List<Map<String, Object>> fundChart = fundNavDao.getFundChart(sec_uni_code, startDate);
                for (Map<String, Object> item : fundChart) {
                    Date end_date = (Date)item.get("end_date");
                    BigDecimal cumu_unit_nav = new BigDecimal(item.get("cumu_unit_nav") == null? "0" : item.get("cumu_unit_nav").toString());
                    chartDate.put(end_date.getTime() + "", cumu_unit_nav);
                }
            }
            result.put(secNameAndSecCode.get("sec_name") == null ? "" : secNameAndSecCode.get("sec_name").toString(), chartDate);
        }

        return result;
    }
}
