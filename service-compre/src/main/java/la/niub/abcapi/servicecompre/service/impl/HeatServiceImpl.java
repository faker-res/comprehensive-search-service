package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;
import la.niub.abcapi.servicecompre.config.enums.PepbEnum;
import la.niub.abcapi.servicecompre.dao.market.IIndexCaptitalFlowDao;
import la.niub.abcapi.servicecompre.dao.market.IIndexCaptitalFlowMonthDao;
import la.niub.abcapi.servicecompre.dao.market.IIndexCaptitalFlowTimeDao;
import la.niub.abcapi.servicecompre.dao.market.IIndexCaptitalFlowWeekDao;
import la.niub.abcapi.servicecompre.dao.market.IIndexCaptitalFlowYearDao;
import la.niub.abcapi.servicecompre.dao.market.IIndexPriceDayDao;
import la.niub.abcapi.servicecompre.dao.market.IIndexPriceMonthDao;
import la.niub.abcapi.servicecompre.dao.market.IIndexPriceWeekDao;
import la.niub.abcapi.servicecompre.dao.market.IIndexPriceYearDao;
import la.niub.abcapi.servicecompre.dao.market.ISecCaptitalFlowMonthDao;
import la.niub.abcapi.servicecompre.dao.market.ISecCaptitalFlowTimeDao;
import la.niub.abcapi.servicecompre.dao.market.ISecCaptitalFlowWeekDao;
import la.niub.abcapi.servicecompre.dao.market.ISecCaptitalFlowYearDao;
import la.niub.abcapi.servicecompre.dao.market.ISecPriceDayDao;
import la.niub.abcapi.servicecompre.dao.market.ISecPriceMonthDao;
import la.niub.abcapi.servicecompre.dao.market.ISecPriceWeekDao;
import la.niub.abcapi.servicecompre.dao.market.ISecPriceYearDao;
import la.niub.abcapi.servicecompre.dao.notice.IHiborDao;
import la.niub.abcapi.servicecompre.dao.reporter.IAbcIndustryDao;
import la.niub.abcapi.servicecompre.dao.reporter.IIndexBasicInfoDao;
import la.niub.abcapi.servicecompre.dao.reporter.IIndexValuationAnaDao;
import la.niub.abcapi.servicecompre.dao.reporter.INormalValuationIndexDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISecIndustryNewDao;
import la.niub.abcapi.servicecompre.dao.reporter.ITransacDao;
import la.niub.abcapi.servicecompre.model.AbcIndustryModel;
import la.niub.abcapi.servicecompre.model.IndexBasicInfoModel;
import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowModel;
import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowMonthModel;
import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowTimeModel;
import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowWeekModel;
import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowYearModel;
import la.niub.abcapi.servicecompre.model.IndexPriceDayModel;
import la.niub.abcapi.servicecompre.model.IndexPriceMonthModel;
import la.niub.abcapi.servicecompre.model.IndexPriceWeekModel;
import la.niub.abcapi.servicecompre.model.IndexPriceYearModel;
import la.niub.abcapi.servicecompre.model.IndexValuationAnaModel;
import la.niub.abcapi.servicecompre.model.NormalValuationIndexModel;
import la.niub.abcapi.servicecompre.model.SecBasicInfoModel;
import la.niub.abcapi.servicecompre.model.SecCaptitalFlowMonthModel;
import la.niub.abcapi.servicecompre.model.SecCaptitalFlowTimeModel;
import la.niub.abcapi.servicecompre.model.SecCaptitalFlowWeekModel;
import la.niub.abcapi.servicecompre.model.SecCaptitalFlowYearModel;
import la.niub.abcapi.servicecompre.model.SecIndustryNewModel;
import la.niub.abcapi.servicecompre.model.SecPriceDayModel;
import la.niub.abcapi.servicecompre.model.SecPriceMonthModel;
import la.niub.abcapi.servicecompre.model.SecPriceWeekModel;
import la.niub.abcapi.servicecompre.model.SecPriceYearModel;
import la.niub.abcapi.servicecompre.model.bo.line.PublicCompanyTimeBO;
import la.niub.abcapi.servicecompre.model.bo.line.RealTimeBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyHeatBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyStockHeatBO;
import la.niub.abcapi.servicecompre.model.dao.HiborGroupByDaoModel;
import la.niub.abcapi.servicecompre.service.IHeatService;
import la.niub.abcapi.servicecompre.service.ILineService;
import la.niub.abcapi.servicecompre.service.IReportManagerService;
import la.niub.abcapi.servicecompre.service.IStockService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HeatServiceImpl implements IHeatService {

    private static final Logger logger = LogManager.getLogger(HeatServiceImpl.class);

    @Autowired
    IIndexCaptitalFlowTimeDao indexCaptitalFlowTimeDao;

    @Autowired
    IIndexCaptitalFlowWeekDao indexCaptitalFlowWeekDao;

    @Autowired
    IIndexCaptitalFlowMonthDao indexCaptitalFlowMonthDao;

    @Autowired
    IIndexCaptitalFlowYearDao indexCaptitalFlowYearDao;

    @Autowired
    ISecCaptitalFlowTimeDao secCaptitalFlowTimeDao;

    @Autowired
    ISecCaptitalFlowWeekDao secCaptitalFlowWeekDao;

    @Autowired
    ISecCaptitalFlowMonthDao secCaptitalFlowMonthDao;

    @Autowired
    ISecCaptitalFlowYearDao secCaptitalFlowYearDao;

    @Autowired
    IIndexPriceDayDao indexPriceDayDao;

    @Autowired
    IIndexPriceWeekDao indexPriceWeekDao;

    @Autowired
    IIndexPriceMonthDao indexPriceMonthDao;

    @Autowired
    IIndexPriceYearDao indexPriceYearDao;

    @Autowired
    IIndexBasicInfoDao indexBasicInfoDao;

    @Autowired
    ITransacDao transacDao;

    @Autowired
    IHiborDao hiborDao;

    @Autowired
    IAbcIndustryDao abcIndustryDao;

    @Autowired
    IIndexCaptitalFlowDao indexCaptitalFlowDao;

    @Autowired
    ISecIndustryNewDao secIndustryNewDao;

    @Autowired
    INormalValuationIndexDao normalValuationIndexDao;

    @Autowired
    IIndexValuationAnaDao indexValuationAnaDao;

    @Autowired
    ISecPriceDayDao secPriceDayDao;

    @Autowired
    ISecPriceWeekDao secPriceWeekDao;

    @Autowired
    ISecPriceMonthDao secPriceMonthDao;

    @Autowired
    ISecPriceYearDao secPriceYearDao;

    @Autowired
    IStockService stockService;

    @Autowired
    ILineService lineService;

    @Autowired
    IReportManagerService reportManagerService;

    @Override
    public List<PublicCompanyHeatBO> getPublicCompanyHeatByPeriod(FundPeriodEnum period, Integer limit) {
        List<PublicCompanyHeatBO> publicCompanyHeatBOList = new ArrayList<>();

        List<String> indexCodesOf1001014 = new ArrayList<>();
        List<AbcIndustryModel> abcIndustryModelOf1001014List = abcIndustryDao.selectAllOf1001014();
        for (AbcIndustryModel item : abcIndustryModelOf1001014List){
            if (item.getIndex_code() != null){
                indexCodesOf1001014.add(item.getIndex_code());
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.add(period.getField(),period.getAmount());
        Date startTime = calendar.getTime();

        List<Long> indexUniCodes = new ArrayList<>();
        List<String> indexCodes = new ArrayList<>();
//        Date lastBeginDate = null;
        switch (period){
            case D1:
//                Date lastBeginDate = indexCaptitalFlowTimeDao.getLastTradeDate();
                Calendar dayCalendar = Calendar.getInstance();
                dayCalendar.setTime(new Date());
                dayCalendar.set(Calendar.HOUR_OF_DAY,0);
                dayCalendar.set(Calendar.MINUTE,0);
                dayCalendar.set(Calendar.SECOND,0);
                dayCalendar.set(Calendar.MILLISECOND,0);
                logger.info("select startTime {}",dayCalendar.getTime());
                List<IndexCaptitalFlowTimeModel> indexCaptitalFlowTimeModelList = indexCaptitalFlowTimeDao.selectHeatByIndexCodesAfterTradeDate(indexCodesOf1001014,dayCalendar.getTime(),limit);
                for (IndexCaptitalFlowTimeModel item : indexCaptitalFlowTimeModelList){
                    PublicCompanyHeatBO publicCompanyHeatBO = new PublicCompanyHeatBO();
                    publicCompanyHeatBO.setIndex_uni_code(item.getIndex_uni_code());
                    publicCompanyHeatBO.setIndex_code(item.getIndex_code());
                    publicCompanyHeatBO.setAmount(item.getMain_netin_flow());
                    publicCompanyHeatBO.setTime(item.getTrade_date());
                    publicCompanyHeatBO.setDate(item.getTrade_date());
                    publicCompanyHeatBOList.add(publicCompanyHeatBO);

                    indexUniCodes.add(item.getIndex_uni_code());
                    indexCodes.add(item.getIndex_code());
                }
                break;
            case W1:
//                lastBeginDate = indexCaptitalFlowWeekDao.getLastBeginDate();
//                lastBeginDate = indexCaptitalFlowWeekDao.getClosestBeginDate(startTime);
                logger.info("select startTime {}",startTime);
                List<IndexCaptitalFlowWeekModel> indexCaptitalFlowWeekModelList = indexCaptitalFlowWeekDao.selectHeatByIndexCodesAfterBeginDate(indexCodesOf1001014,startTime,limit);
                for (IndexCaptitalFlowWeekModel item : indexCaptitalFlowWeekModelList){
                    PublicCompanyHeatBO publicCompanyHeatBO = new PublicCompanyHeatBO();
                    publicCompanyHeatBO.setIndex_uni_code(item.getIndex_uni_code());
                    publicCompanyHeatBO.setIndex_code(item.getIndex_code());
                    publicCompanyHeatBO.setAmount(item.getMain_netin_flow());
                    publicCompanyHeatBO.setTime(item.getBegin_date());
                    publicCompanyHeatBO.setDate(item.getBegin_date());
                    publicCompanyHeatBOList.add(publicCompanyHeatBO);

                    indexUniCodes.add(item.getIndex_uni_code());
                    indexCodes.add(item.getIndex_code());
                }
                break;
            case M1:
//                lastBeginDate = indexCaptitalFlowMonthDao.getLastBeginDate();
//                lastBeginDate = indexCaptitalFlowMonthDao.getClosestBeginDate(startTime);
                logger.info("select startTime {}",startTime);
                List<IndexCaptitalFlowMonthModel> indexCaptitalFlowMonthModelList = indexCaptitalFlowMonthDao.selectHeatByIndexCodesAfterBeginDate(indexCodesOf1001014,startTime,limit);
                for (IndexCaptitalFlowMonthModel item : indexCaptitalFlowMonthModelList){
                    PublicCompanyHeatBO publicCompanyHeatBO = new PublicCompanyHeatBO();
                    publicCompanyHeatBO.setIndex_uni_code(item.getIndex_uni_code());
                    publicCompanyHeatBO.setIndex_code(item.getIndex_code());
                    publicCompanyHeatBO.setAmount(item.getMain_netin_flow());
                    publicCompanyHeatBO.setTime(item.getBegin_date());
                    publicCompanyHeatBO.setDate(item.getBegin_date());
                    publicCompanyHeatBOList.add(publicCompanyHeatBO);

                    indexUniCodes.add(item.getIndex_uni_code());
                    indexCodes.add(item.getIndex_code());
                }
                break;
            case Y1:
//                lastBeginDate = indexCaptitalFlowYearDao.getLastBeginDate();
//                lastBeginDate = indexCaptitalFlowYearDao.getClosestBeginDate(startTime);
                logger.info("select startTime {}",startTime);
                List<IndexCaptitalFlowYearModel> indexCaptitalFlowYearModelList = indexCaptitalFlowYearDao.selectHeatByIndexCodesAfterBeginDate(indexCodesOf1001014,startTime,limit);
                for (IndexCaptitalFlowYearModel item : indexCaptitalFlowYearModelList){
                    PublicCompanyHeatBO publicCompanyHeatBO = new PublicCompanyHeatBO();
                    publicCompanyHeatBO.setIndex_uni_code(item.getIndex_uni_code());
                    publicCompanyHeatBO.setIndex_code(item.getIndex_code());
                    publicCompanyHeatBO.setAmount(item.getMain_netin_flow());
                    publicCompanyHeatBO.setTime(item.getBegin_date());
                    publicCompanyHeatBO.setDate(item.getBegin_date());
                    publicCompanyHeatBOList.add(publicCompanyHeatBO);

                    indexUniCodes.add(item.getIndex_uni_code());
                    indexCodes.add(item.getIndex_code());
                }
                break;
            default:
                return publicCompanyHeatBOList;
        }
//        if (lastBeginDate == null){
//            return publicCompanyHeatBOList;
//        }
        if (indexUniCodes.isEmpty()){
            return publicCompanyHeatBOList;
        }

        //行业信息
        List<IndexBasicInfoModel> indexBasicInfoModelList = indexBasicInfoDao.getByIndexUniCodes(indexUniCodes);
        Map<Long,IndexBasicInfoModel> indexBasicInfoModelMap = new HashMap<>();
        for (IndexBasicInfoModel item : indexBasicInfoModelList){
            indexBasicInfoModelMap.put(item.getIndex_uni_code(),item);
        }
        //研报数量
//        Map<String,Integer> reportNumMap = new HashMap<>();
//        List<String> induCodes = new ArrayList<>();
//        List<AbcIndustryModel> abcIndustryModelList = abcIndustryDao.selectByIndexCodes(indexCodes);
//        for (AbcIndustryModel item : abcIndustryModelList){
//            induCodes.add(item.getIndu_code());
//        }
//        Date hiborStartTime = startTime;
//        if (period == FundPeriodEnum.W1 || period == FundPeriodEnum.M1){
//            Calendar hiborCalendar = Calendar.getInstance();
//            hiborCalendar.setTime(new Date());
//            hiborCalendar.set(Calendar.HOUR_OF_DAY,0);
//            hiborCalendar.set(Calendar.MINUTE,0);
//            hiborCalendar.set(Calendar.SECOND,0);
//            hiborCalendar.set(Calendar.MILLISECOND,0);
//            hiborCalendar.add(Calendar.MONTH,-4);
//            hiborStartTime = hiborCalendar.getTime();
//        }
//        List<HiborGroupByDaoModel> hiborGroupByDaoModelList = hiborDao.selectByIndustryIdsAndTimeGroupByIndustryId(induCodes,hiborStartTime,new Date());
//        Map<String,Integer> hiborGroupByDaoModelMap = new HashMap<>();
//        for (HiborGroupByDaoModel item : hiborGroupByDaoModelList){
//            hiborGroupByDaoModelMap.put(item.getIndustryId(),item.getCount());
//        }
//        for (AbcIndustryModel item : abcIndustryModelList){
//            Integer count = hiborGroupByDaoModelMap.get(item.getIndu_code());
//            if (count != null){
//                reportNumMap.put(item.getIndex_code(),count);
//            }
//        }
        Map<String,Integer> reportNumMap = reportManagerService.getIndustryReportCount(indexCodes,startTime);

        for (PublicCompanyHeatBO item : publicCompanyHeatBOList){
            IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoModelMap.get(item.getIndex_uni_code());
            if (indexBasicInfoModel != null){
                item.setName(indexBasicInfoModel.getIndex_name());
            }
            Integer count = reportNumMap.get(item.getIndex_code());
            if (count != null){
                item.setReport_num(count);
            }
//            Integer reportCount = reportManagerService.getIndustryReportCount(item.getIndex_code(),startTime);
//            item.setReport_num(reportCount);
        }

        if (period == FundPeriodEnum.D1){
//        if (false){
            for (PublicCompanyHeatBO item : publicCompanyHeatBOList){
                PublicCompanyTimeBO publicCompanyTimeBO = lineService.getPublicCompanyRealTime(item.getIndex_uni_code());
                if (publicCompanyTimeBO != null){
                    item.setDiffer(publicCompanyTimeBO.getDiffer());
                    item.setDiffer_range(publicCompanyTimeBO.getDiffer_range());
                    item.setPe(publicCompanyTimeBO.getPe());
                    item.setPb(publicCompanyTimeBO.getPb());
                    item.setTotal_market_value(publicCompanyTimeBO.getMv());
                }
            }
        }else{
            Map<Long,Date> indexUniCodeAndDateMap = new HashMap<>();
            for (PublicCompanyHeatBO item : publicCompanyHeatBOList){
                indexUniCodeAndDateMap.put(item.getIndex_uni_code(),item.getDate());
            }

            //行业行情
//            List<IndexPriceDayModel> indexPriceDayModelList = indexPriceDayDao.selectByIndexUniCodesAfterTradeDate(indexUniCodes,startTime);
//            Map<Long,IndexPriceDayModel> indexPriceDayModelMap = new HashMap<>();
//            for (IndexPriceDayModel item : indexPriceDayModelList){
//                indexPriceDayModelMap.put(item.getIndex_uni_code(),item);
//            }
            //市盈率、市净率
            Map<Long,IndexValuationAnaModel> indexValuationAnaModelMap = new HashMap<>();
            List<IndexValuationAnaModel> indexValuationAnaModelListAfter = indexValuationAnaDao.selectByIndexUniCodesAfterAccountDate(indexUniCodes,startTime);
            for (IndexValuationAnaModel item : indexValuationAnaModelListAfter){
                indexValuationAnaModelMap.put(item.getIndex_uni_code(),item);
            }
            List<IndexValuationAnaModel> indexValuationAnaModelList = indexValuationAnaDao.selectByIndexUniCodeAndTradeDate(indexUniCodeAndDateMap);
            for (IndexValuationAnaModel item : indexValuationAnaModelList){
                indexValuationAnaModelMap.put(item.getIndex_uni_code(),item);
            }

            for (PublicCompanyHeatBO item : publicCompanyHeatBOList){
//                IndexPriceDayModel indexPriceDayModel = indexPriceDayModelMap.get(item.getIndex_uni_code());
//                if (indexPriceDayModel != null){
//                    item.setDiffer(indexPriceDayModel.getDiffer() != null ? indexPriceDayModel.getDiffer().doubleValue() : null);
//                    item.setDiffer_range(indexPriceDayModel.getDiffer_range() != null ? indexPriceDayModel.getDiffer_range().doubleValue() : null);
//                }
                IndexValuationAnaModel indexValuationAnaModel = indexValuationAnaModelMap.get(item.getIndex_uni_code());
                if (indexValuationAnaModel != null){
                    item.setPe(indexValuationAnaModel.getPettm() != null ? indexValuationAnaModel.getPettm().doubleValue() : null);
                    item.setPb(indexValuationAnaModel.getPbmrq() != null ? indexValuationAnaModel.getPbmrq().doubleValue() : null);
                    item.setTotal_market_value(indexValuationAnaModel.getTotal_market_value());
                }
            }

            //涨跌、涨跌幅
            switch (period){
                case W1:
                    List<IndexPriceWeekModel> indexPriceWeekModelList = indexPriceWeekDao.selectByIndexUniCodesAfterTime(indexUniCodes,startTime);
                    Map<Long,IndexPriceWeekModel> indexPriceWeekModelMap = new HashMap<>();
                    for (IndexPriceWeekModel item : indexPriceWeekModelList){
                        indexPriceWeekModelMap.put(item.getIndex_uni_code(),item);
                    }
                    for (PublicCompanyHeatBO item : publicCompanyHeatBOList){
                        IndexPriceWeekModel model = indexPriceWeekModelMap.get(item.getIndex_uni_code());
                        if (model != null){
                            item.setDiffer(model.getDiffer() != null ? model.getDiffer().doubleValue() : null);
                            item.setDiffer_range(model.getDiffer_range() != null ? model.getDiffer_range().doubleValue() : null);
                        }
                    }
                    break;
                case M1:
                    List<IndexPriceMonthModel> indexPriceMonthModelList = indexPriceMonthDao.selectByIndexUniCodesAfterTime(indexUniCodes,startTime);
                    Map<Long,IndexPriceMonthModel> indexPriceMonthModelMap = new HashMap<>();
                    for (IndexPriceMonthModel item : indexPriceMonthModelList){
                        indexPriceMonthModelMap.put(item.getIndex_uni_code(),item);
                    }
                    for (PublicCompanyHeatBO item : publicCompanyHeatBOList){
                        IndexPriceMonthModel model = indexPriceMonthModelMap.get(item.getIndex_uni_code());
                        if (model != null){
                            item.setDiffer(model.getDiffer() != null ? model.getDiffer().doubleValue() : null);
                            item.setDiffer_range(model.getDiffer_range() != null ? model.getDiffer_range().doubleValue() : null);
                        }
                    }
                    break;
                case Y1:
                    List<IndexPriceYearModel> indexPriceYearModelList = indexPriceYearDao.selectByIndexUniCodesAfterTime(indexUniCodes,startTime);
                    Map<Long,IndexPriceYearModel> indexPriceYearModelMap = new HashMap<>();
                    for (IndexPriceYearModel item : indexPriceYearModelList){
                        indexPriceYearModelMap.put(item.getIndex_uni_code(),item);
                    }
                    for (PublicCompanyHeatBO item : publicCompanyHeatBOList){
                        IndexPriceYearModel model = indexPriceYearModelMap.get(item.getIndex_uni_code());
                        if (model != null){
                            item.setDiffer(model.getDiffer() != null ? model.getDiffer().doubleValue() : null);
                            item.setDiffer_range(model.getDiffer_range() != null ? model.getDiffer_range().doubleValue() : null);
                        }
                    }
                    break;
                default:
                    List<IndexPriceDayModel> indexPriceDayModelList = indexPriceDayDao.selectByIndexUniCodeAndTradeDate(indexUniCodeAndDateMap);
                    Map<Long,IndexPriceDayModel> indexPriceDayModelMap = new HashMap<>();
                    for (IndexPriceDayModel item : indexPriceDayModelList){
                        indexPriceDayModelMap.put(item.getIndex_uni_code(),item);
                    }
                    for (PublicCompanyHeatBO item : publicCompanyHeatBOList){
                        IndexPriceDayModel model = indexPriceDayModelMap.get(item.getIndex_uni_code());
                        if (model != null){
                            item.setDiffer(model.getDiffer() != null ? model.getDiffer().doubleValue() : null);
                            item.setDiffer_range(model.getDiffer_range() != null ? model.getDiffer_range().doubleValue() : null);
                        }
                    }
            }
        }

        return publicCompanyHeatBOList;
    }

    @Override
    public List<PublicCompanyHeatBO> getPublicCompanyHeatByPepb(PepbEnum pepb, Integer limit) {
        List<PublicCompanyHeatBO> publicCompanyHeatBOList = new ArrayList<>();

        List<String> indexCodesOf1001014 = new ArrayList<>();
        List<AbcIndustryModel> abcIndustryModelOf1001014List = abcIndustryDao.selectAllOf1001014();
        for (AbcIndustryModel item : abcIndustryModelOf1001014List){
            if (item.getIndex_code() != null){
                indexCodesOf1001014.add(item.getIndex_code());
            }
        }
        List<Long> indexUniCodesOf1001014 = new ArrayList<>();
        //行业信息
        List<IndexBasicInfoModel> indexBasicInfoModelOf1001014List = indexBasicInfoDao.getByIndexCodes(indexCodesOf1001014);
        for (IndexBasicInfoModel item : indexBasicInfoModelOf1001014List){
            indexUniCodesOf1001014.add(item.getIndex_uni_code());
        }

        Date lastAccountDate = indexValuationAnaDao.getLastAccountDate();
        if (lastAccountDate == null){
            return publicCompanyHeatBOList;
        }

        List<Long> indexUniCodes = new ArrayList<>();
        List<String> indexCodes = new ArrayList<>();
        List<IndexValuationAnaModel> indexValuationAnaModelList = null;
        switch (pepb){
            case PELYR:
                indexValuationAnaModelList = indexValuationAnaDao.selectHeatPelyrByIndexUniCodesAfterAccountDate(indexUniCodesOf1001014,lastAccountDate,limit);
                for (IndexValuationAnaModel item : indexValuationAnaModelList){
                    PublicCompanyHeatBO publicCompanyHeatBO = new PublicCompanyHeatBO();
                    publicCompanyHeatBO.setIndex_uni_code(item.getIndex_uni_code());
                    publicCompanyHeatBO.setPe(item.getPelyr() != null ? item.getPelyr().doubleValue() : null);
                    publicCompanyHeatBO.setPb(item.getPbmrq() != null ? item.getPbmrq().doubleValue() : null);
                    publicCompanyHeatBO.setTime(item.getAccount_date());
                    publicCompanyHeatBO.setDate(item.getAccount_date());
                    publicCompanyHeatBO.setTotal_market_value(item.getTotal_market_value());
                    publicCompanyHeatBOList.add(publicCompanyHeatBO);

                    indexUniCodes.add(item.getIndex_uni_code());
                }
                break;
            case PETTM:
                indexValuationAnaModelList = indexValuationAnaDao.selectHeatPettmByIndexUniCodesAfterAccountDate(indexUniCodesOf1001014,lastAccountDate,limit);
                for (IndexValuationAnaModel item : indexValuationAnaModelList){
                    PublicCompanyHeatBO publicCompanyHeatBO = new PublicCompanyHeatBO();
                    publicCompanyHeatBO.setIndex_uni_code(item.getIndex_uni_code());
                    publicCompanyHeatBO.setPe(item.getPelyr() != null ? item.getPelyr().doubleValue() : null);
                    publicCompanyHeatBO.setPb(item.getPbmrq() != null ? item.getPbmrq().doubleValue() : null);
                    publicCompanyHeatBO.setTime(item.getAccount_date());
                    publicCompanyHeatBO.setDate(item.getAccount_date());
                    publicCompanyHeatBO.setTotal_market_value(item.getTotal_market_value());
                    publicCompanyHeatBOList.add(publicCompanyHeatBO);

                    indexUniCodes.add(item.getIndex_uni_code());
                }
                break;
            case PBMRQ:
                indexValuationAnaModelList = indexValuationAnaDao.selectHeatPbmrqByIndexUniCodesAfterAccountDate(indexUniCodesOf1001014,lastAccountDate,limit);
                for (IndexValuationAnaModel item : indexValuationAnaModelList){
                    PublicCompanyHeatBO publicCompanyHeatBO = new PublicCompanyHeatBO();
                    publicCompanyHeatBO.setIndex_uni_code(item.getIndex_uni_code());
                    publicCompanyHeatBO.setPe(item.getPelyr() != null ? item.getPelyr().doubleValue() : null);
                    publicCompanyHeatBO.setPb(item.getPbmrq() != null ? item.getPbmrq().doubleValue() : null);
                    publicCompanyHeatBO.setTime(item.getAccount_date());
                    publicCompanyHeatBO.setDate(item.getAccount_date());
                    publicCompanyHeatBO.setTotal_market_value(item.getTotal_market_value());
                    publicCompanyHeatBOList.add(publicCompanyHeatBO);

                    indexUniCodes.add(item.getIndex_uni_code());
                }
                break;
            default:
                return publicCompanyHeatBOList;
        }

        Map<Long,Date> indexUniCodeAndDateMap = new HashMap<>();
        for (PublicCompanyHeatBO item : publicCompanyHeatBOList){
            indexUniCodeAndDateMap.put(item.getIndex_uni_code(),item.getDate());
        }

        //行业信息
        List<IndexBasicInfoModel> indexBasicInfoModelList = indexBasicInfoDao.getByIndexUniCodes(indexUniCodes);
        Map<Long,IndexBasicInfoModel> indexBasicInfoModelMap = new HashMap<>();
        for (IndexBasicInfoModel item : indexBasicInfoModelList){
            indexBasicInfoModelMap.put(item.getIndex_uni_code(),item);
            indexCodes.add(item.getIndex_code());
        }
        //行业行情
//        List<IndexPriceDayModel> indexPriceDayModelList = indexPriceDayDao.selectByIndexUniCodesAfterTradeDate(indexUniCodes,lastAccountDate);
        List<IndexPriceDayModel> indexPriceDayModelList = indexPriceDayDao.selectByIndexUniCodeAndTradeDate(indexUniCodeAndDateMap);
        Map<Long,IndexPriceDayModel> indexPriceDayModelMap = new HashMap<>();
        for (IndexPriceDayModel item : indexPriceDayModelList){
            indexPriceDayModelMap.put(item.getIndex_uni_code(),item);
        }
        //资金流入流出
//        List<IndexCaptitalFlowModel> indexCaptitalFlowModelList = indexCaptitalFlowDao.selectByIndexUniCodesAfterTradeDate(indexUniCodes,lastAccountDate);
        List<IndexCaptitalFlowModel> indexCaptitalFlowModelList = indexCaptitalFlowDao.selectByIndexUniCodeAndTradeDate(indexUniCodeAndDateMap);
        Map<Long,IndexCaptitalFlowModel> indexCaptitalFlowModelMap = new HashMap<>();
        for (IndexCaptitalFlowModel item : indexCaptitalFlowModelList){
            indexCaptitalFlowModelMap.put(item.getIndex_uni_code(),item);
        }
        //研报数量
//        Map<String,Integer> reportNumMap = new HashMap<>();
//        List<String> induCodes = new ArrayList<>();
//        List<AbcIndustryModel> abcIndustryModelList = abcIndustryDao.selectByIndexCodes(indexCodes);
//        for (AbcIndustryModel item : abcIndustryModelList){
//            induCodes.add(item.getIndu_code());
//        }
        Calendar hiborCalendar = Calendar.getInstance();
        hiborCalendar.setTime(new Date());
        hiborCalendar.set(Calendar.HOUR_OF_DAY,0);
        hiborCalendar.set(Calendar.MINUTE,0);
        hiborCalendar.set(Calendar.SECOND,0);
        hiborCalendar.set(Calendar.MILLISECOND,0);
        hiborCalendar.add(Calendar.MONTH,-3);
        Date hiborStartTime = hiborCalendar.getTime();
//        List<HiborGroupByDaoModel> hiborGroupByDaoModelList = hiborDao.selectByIndustryIdsAndTimeGroupByIndustryId(induCodes,hiborStartTime,new Date());
//        Map<String,Integer> hiborGroupByDaoModelMap = new HashMap<>();
//        for (HiborGroupByDaoModel item : hiborGroupByDaoModelList){
//            hiborGroupByDaoModelMap.put(item.getIndustryId(),item.getCount());
//        }
//        for (AbcIndustryModel item : abcIndustryModelList){
//            Integer count = hiborGroupByDaoModelMap.get(item.getIndu_code());
//            if (count != null){
//                reportNumMap.put(item.getIndex_code(),count);
//            }
//        }
        Map<String,Integer> reportNumMap = reportManagerService.getIndustryReportCount(indexCodes,hiborStartTime);

        for (PublicCompanyHeatBO item : publicCompanyHeatBOList){
            IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoModelMap.get(item.getIndex_uni_code());
            if (indexBasicInfoModel != null){
                item.setName(indexBasicInfoModel.getIndex_name());
                item.setIndex_code(indexBasicInfoModel.getIndex_code());
            }
            IndexPriceDayModel indexPriceDayModel = indexPriceDayModelMap.get(item.getIndex_uni_code());
            if (indexPriceDayModel != null){
                item.setDiffer(indexPriceDayModel.getDiffer() != null ? indexPriceDayModel.getDiffer().doubleValue() : null);
                item.setDiffer_range(indexPriceDayModel.getDiffer_range() != null ? indexPriceDayModel.getDiffer_range().doubleValue() : null);
            }
            IndexCaptitalFlowModel indexCaptitalFlowModel = indexCaptitalFlowModelMap.get(item.getIndex_uni_code());
            if (indexCaptitalFlowModel != null){
                item.setAmount(indexCaptitalFlowModel.getMain_netin_flow());
            }
            Integer count = reportNumMap.get(item.getIndex_code());
            if (count != null){
                item.setReport_num(count);
            }
//            Integer reportCount = reportManagerService.getIndustryReportCount(item.getIndex_code(),hiborStartTime);
//            item.setReport_num(reportCount);
        }

        return publicCompanyHeatBOList;
    }

    @Override
    public List<PublicCompanyStockHeatBO> getPublicCompanyStockHeat(Long indexUniCode, FundPeriodEnum period, Integer limit) {
        IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoDao.selectByPrimaryKey(indexUniCode);
        if (indexBasicInfoModel == null){
            return new ArrayList<>();
        }
        AbcIndustryModel abcIndustryModel = abcIndustryDao.selectByIndexCode(indexBasicInfoModel.getIndex_code());
        if (abcIndustryModel == null){
            return new ArrayList<>();
        }

        List<SecIndustryNewModel> secIndustryNewModelList = secIndustryNewDao.selectShenwanByFirstInduCode(abcIndustryModel.getIndu_code());
        if (ObjectUtils.isEmpty(secIndustryNewModelList)){
            return new ArrayList<>();
        }

        List<Long> secUniCodesOfALL = new ArrayList<>();
        for (SecIndustryNewModel item : secIndustryNewModelList){
            if (item.getSec_uni_code() != null){
                secUniCodesOfALL.add(item.getSec_uni_code());
            }
        }

        return handleStockHeat(secUniCodesOfALL,period,limit);
    }

    @Override
    public List<PublicCompanyStockHeatBO> handleStockHeat(List<Long> secUniCodesOfALL, FundPeriodEnum period, Integer limit) {
        List<PublicCompanyStockHeatBO> publicCompanyStockHeatBOList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.add(period.getField(),period.getAmount());
        Date startTime = calendar.getTime();

        List<String> secCodes = new ArrayList<>();
        List<Long> secUniCodes = new ArrayList<>();
//        Date lastBeginDate = null;
        switch (period){
            case D1:
//                Date lastBeginDate = secCaptitalFlowTimeDao.getLastTradeDate();
                Calendar dayCalendar = Calendar.getInstance();
                dayCalendar.setTime(new Date());
                dayCalendar.set(Calendar.HOUR_OF_DAY,0);
                dayCalendar.set(Calendar.MINUTE,0);
                dayCalendar.set(Calendar.SECOND,0);
                dayCalendar.set(Calendar.MILLISECOND,0);
                logger.info("select startTime {}",dayCalendar.getTime());
                List<SecCaptitalFlowTimeModel> secCaptitalFlowTimeModelList = secCaptitalFlowTimeDao.selectHeatBySecUniCodesAfterTradeDate(secUniCodesOfALL,dayCalendar.getTime(),limit);
                for (SecCaptitalFlowTimeModel item : secCaptitalFlowTimeModelList){
                    PublicCompanyStockHeatBO publicCompanyStockHeatBO = new PublicCompanyStockHeatBO();
                    publicCompanyStockHeatBO.setSec_uni_code(item.getSec_uni_code());
                    publicCompanyStockHeatBO.setStock_code(item.getSec_code());
                    publicCompanyStockHeatBO.setAmount(item.getNetin_flow());
                    publicCompanyStockHeatBO.setTime(item.getTrade_date());
                    publicCompanyStockHeatBO.setDate(item.getTrade_date());
                    publicCompanyStockHeatBOList.add(publicCompanyStockHeatBO);

                    secCodes.add(item.getSec_code());
                    secUniCodes.add(item.getSec_uni_code());
                }
                break;
            case W1:
//                lastBeginDate = secCaptitalFlowWeekDao.getLastBeginDate();
                logger.info("select startTime {}",startTime);
                List<SecCaptitalFlowWeekModel> secCaptitalFlowWeekModelList = secCaptitalFlowWeekDao.selectHeatBySecUniCodesAfterBeginDate(secUniCodesOfALL,startTime,limit);
                for (SecCaptitalFlowWeekModel item : secCaptitalFlowWeekModelList){
                    PublicCompanyStockHeatBO publicCompanyStockHeatBO = new PublicCompanyStockHeatBO();
                    publicCompanyStockHeatBO.setSec_uni_code(item.getSec_uni_code());
                    publicCompanyStockHeatBO.setStock_code(item.getSec_code());
                    publicCompanyStockHeatBO.setAmount(item.getNetin_flow());
                    publicCompanyStockHeatBO.setTime(item.getBegin_date());
                    publicCompanyStockHeatBO.setDate(item.getBegin_date());
                    publicCompanyStockHeatBOList.add(publicCompanyStockHeatBO);

                    secCodes.add(item.getSec_code());
                    secUniCodes.add(item.getSec_uni_code());
                }
                break;
            case M1:
//                lastBeginDate = secCaptitalFlowMonthDao.getLastBeginDate();
                logger.info("select startTime {}",startTime);
                List<SecCaptitalFlowMonthModel> secCaptitalFlowMonthModelList = secCaptitalFlowMonthDao.selectHeatBySecUniCodesAfterBeginDate(secUniCodesOfALL,startTime,limit);
                for (SecCaptitalFlowMonthModel item : secCaptitalFlowMonthModelList){
                    PublicCompanyStockHeatBO publicCompanyStockHeatBO = new PublicCompanyStockHeatBO();
                    publicCompanyStockHeatBO.setSec_uni_code(item.getSec_uni_code());
                    publicCompanyStockHeatBO.setStock_code(item.getSec_code());
                    publicCompanyStockHeatBO.setAmount(item.getNetin_flow());
                    publicCompanyStockHeatBO.setTime(item.getBegin_date());
                    publicCompanyStockHeatBO.setDate(item.getBegin_date());
                    publicCompanyStockHeatBOList.add(publicCompanyStockHeatBO);

                    secCodes.add(item.getSec_code());
                    secUniCodes.add(item.getSec_uni_code());
                }
                break;
            case Y1:
//                lastBeginDate = secCaptitalFlowYearDao.getLastBeginDate();
                logger.info("select startTime {}",startTime);
                List<SecCaptitalFlowYearModel> secCaptitalFlowYearModelList = secCaptitalFlowYearDao.selectHeatBySecUniCodesAfterBeginDate(secUniCodesOfALL,startTime,limit);
                for (SecCaptitalFlowYearModel item : secCaptitalFlowYearModelList){
                    PublicCompanyStockHeatBO publicCompanyStockHeatBO = new PublicCompanyStockHeatBO();
                    publicCompanyStockHeatBO.setSec_uni_code(item.getSec_uni_code());
                    publicCompanyStockHeatBO.setStock_code(item.getSec_code());
                    publicCompanyStockHeatBO.setAmount(item.getNetin_flow());
                    publicCompanyStockHeatBO.setTime(item.getBegin_date());
                    publicCompanyStockHeatBO.setDate(item.getBegin_date());
                    publicCompanyStockHeatBOList.add(publicCompanyStockHeatBO);

                    secCodes.add(item.getSec_code());
                    secUniCodes.add(item.getSec_uni_code());
                }
                break;
            default:
                return publicCompanyStockHeatBOList;
        }
//        if (lastBeginDate == null){
//            return publicCompanyStockHeatBOList;
//        }
        if (secCodes.isEmpty()){
            return publicCompanyStockHeatBOList;
        }

        //个股信息
        List<SecBasicInfoModel> secBasicInfoModelList = stockService.getRecordsBySecCodes(secCodes);
        Map<String,SecBasicInfoModel> secBasicInfoModelMap = new HashMap<>();
        for (SecBasicInfoModel item : secBasicInfoModelList){
            secBasicInfoModelMap.put(item.getAbc_code(),item);
        }
        //研报数量
        Date hiborStartTime = startTime;
        if (period == FundPeriodEnum.W1 || period == FundPeriodEnum.M1){
            Calendar hiborCalendar = Calendar.getInstance();
            hiborCalendar.setTime(new Date());
            hiborCalendar.set(Calendar.HOUR_OF_DAY,0);
            hiborCalendar.set(Calendar.MINUTE,0);
            hiborCalendar.set(Calendar.SECOND,0);
            hiborCalendar.set(Calendar.MILLISECOND,0);
            hiborCalendar.add(Calendar.MONTH,-3);
            hiborStartTime = hiborCalendar.getTime();
        }
        List<HiborGroupByDaoModel> hiborGroupByDaoModelList = hiborDao.selectByStockCodesAndTimeGroupByStockCode(secCodes,hiborStartTime,null);
        Map<String,Integer> reportNumMap = new HashMap<>();
        for (HiborGroupByDaoModel item : hiborGroupByDaoModelList){
            reportNumMap.put(item.getStockcode(),item.getCount());
        }

        for (PublicCompanyStockHeatBO item : publicCompanyStockHeatBOList){
            SecBasicInfoModel secBasicInfoModel = secBasicInfoModelMap.get(item.getStock_code());
            if (secBasicInfoModel != null){
                item.setName(secBasicInfoModel.getSec_name());
            }
            Integer count = reportNumMap.get(item.getStock_code());
            if (count != null){
                item.setReport_num(count);
            }
        }

        if (period == FundPeriodEnum.D1){
            //涨跌、涨跌幅、市盈率、市净率
            for (PublicCompanyStockHeatBO item : publicCompanyStockHeatBOList){
                RealTimeBO realTimeBO = lineService.getRealTime(item.getStock_code());
                if (realTimeBO != null){
                    item.setDiffer(realTimeBO.getDiffer());
                    item.setDiffer_range(realTimeBO.getDiffer_range() != null ? realTimeBO.getDiffer_range() * 100 : null);
                    item.setPe(realTimeBO.getPe());
                    item.setPb(realTimeBO.getPb());
                    item.setTotal_market_value(realTimeBO.getMv());
                }
            }
        }else{
            Map<Long,Date> secUniCodeAndDateMap = new HashMap<>();
            for (PublicCompanyStockHeatBO item : publicCompanyStockHeatBOList){
                secUniCodeAndDateMap.put(item.getSec_uni_code(),item.getDate());
            }

            //涨跌、涨跌幅
//            List<SecPriceDayModel> secPriceDayModelList = secPriceDayDao.selectBySecUniCodesAfterTradeDate(secUniCodes,startTime);
//            Map<Long,SecPriceDayModel> secPriceDayModelMap = new HashMap<>();
//            for (SecPriceDayModel item : secPriceDayModelList){
//                secPriceDayModelMap.put(item.getSec_uni_code(),item);
//            }
            //市盈率、市净率
//            List<NormalValuationIndexModel> normalValuationIndexModelList = normalValuationIndexDao.selectBySecUniCodesAfterAccountDate(secUniCodes,startTime);
            List<NormalValuationIndexModel> normalValuationIndexModelList = normalValuationIndexDao.selectBySecUniCodeAndTradeDate(secUniCodeAndDateMap);
            Map<Long,NormalValuationIndexModel> normalValuationIndexModelMap = new HashMap<>();
            for (NormalValuationIndexModel item : normalValuationIndexModelList){
                normalValuationIndexModelMap.put(item.getSec_uni_code(),item);
            }

            for (PublicCompanyStockHeatBO item : publicCompanyStockHeatBOList){
//                SecPriceDayModel secPriceDayModel = secPriceDayModelMap.get(item.getSec_uni_code());
//                if (secPriceDayModel != null){
//                    item.setDiffer(secPriceDayModel.getDiffer() != null ? secPriceDayModel.getDiffer().doubleValue() : null);
//                    item.setDiffer_range(secPriceDayModel.getDiffer_range() != null ? secPriceDayModel.getDiffer_range().doubleValue() : null);
//                }
                NormalValuationIndexModel normalValuationIndexModel = normalValuationIndexModelMap.get(item.getSec_uni_code());
                if (normalValuationIndexModel != null){
                    BigDecimal pe = normalValuationIndexModel.getPettm() != null ? normalValuationIndexModel.getPettm() : normalValuationIndexModel.getPe();
                    item.setPe(pe != null ? pe.doubleValue() : null);
                    BigDecimal pb = normalValuationIndexModel.getPbmrq() != null ? normalValuationIndexModel.getPbmrq() : normalValuationIndexModel.getPb();
                    item.setPb(pb != null ? pb.doubleValue() : null);
                    item.setTotal_market_value(normalValuationIndexModel.getTotal_market_value());
                }
            }

            //涨跌、涨跌幅
            switch (period){
                case W1:
                    List<SecPriceWeekModel> secPriceWeekModelList = secPriceWeekDao.selectBySecUniCodesAfterTime(secUniCodes,startTime);
                    Map<Long,SecPriceWeekModel> secPriceWeekModelMap = new HashMap<>();
                    for (SecPriceWeekModel item : secPriceWeekModelList){
                        secPriceWeekModelMap.put(item.getSec_uni_code(),item);
                    }
                    for (PublicCompanyStockHeatBO item : publicCompanyStockHeatBOList){
                        SecPriceWeekModel model = secPriceWeekModelMap.get(item.getSec_uni_code());
                        if (model != null){
                            item.setDiffer(model.getDiffer() != null ? model.getDiffer().doubleValue() : null);
                            item.setDiffer_range(model.getDiffer_range() != null ? model.getDiffer_range().doubleValue() : null);
                        }
                    }
                    break;
                case M1:
                    List<SecPriceMonthModel> secPriceMonthModelList = secPriceMonthDao.selectBySecUniCodesAfterTime(secUniCodes,startTime);
                    Map<Long,SecPriceMonthModel> secPriceMonthModelMap = new HashMap<>();
                    for (SecPriceMonthModel item : secPriceMonthModelList){
                        secPriceMonthModelMap.put(item.getSec_uni_code(),item);
                    }
                    for (PublicCompanyStockHeatBO item : publicCompanyStockHeatBOList){
                        SecPriceMonthModel model = secPriceMonthModelMap.get(item.getSec_uni_code());
                        if (model != null){
                            item.setDiffer(model.getDiffer() != null ? model.getDiffer().doubleValue() : null);
                            item.setDiffer_range(model.getDiffer_range() != null ? model.getDiffer_range().doubleValue() : null);
                        }
                    }
                    break;
                case Y1:
                    List<SecPriceYearModel> secPriceYearModelList = secPriceYearDao.selectBySecUniCodesAfterTime(secUniCodes,startTime);
                    Map<Long,SecPriceYearModel> secPriceYearModelMap = new HashMap<>();
                    for (SecPriceYearModel item : secPriceYearModelList){
                        secPriceYearModelMap.put(item.getSec_uni_code(),item);
                    }
                    for (PublicCompanyStockHeatBO item : publicCompanyStockHeatBOList){
                        SecPriceYearModel model = secPriceYearModelMap.get(item.getSec_uni_code());
                        if (model != null){
                            item.setDiffer(model.getDiffer() != null ? model.getDiffer().doubleValue() : null);
                            item.setDiffer_range(model.getDiffer_range() != null ? model.getDiffer_range().doubleValue() : null);
                        }
                    }
                    break;
                default:
                    List<SecPriceDayModel> secPriceDayModelList = secPriceDayDao.selectBySecUniCodeAndTradeDate(secUniCodeAndDateMap);
                    Map<Long,SecPriceDayModel> secPriceDayModelMap = new HashMap<>();
                    for (SecPriceDayModel item : secPriceDayModelList){
                        secPriceDayModelMap.put(item.getSec_uni_code(),item);
                    }
                    for (PublicCompanyStockHeatBO item : publicCompanyStockHeatBOList){
                        SecPriceDayModel model = secPriceDayModelMap.get(item.getSec_uni_code());
                        if (model != null){
                            item.setDiffer(model.getDiffer() != null ? model.getDiffer().doubleValue() : null);
                            item.setDiffer_range(model.getDiffer_range() != null ? model.getDiffer_range().doubleValue() : null);
                        }
                    }
            }
        }

        return publicCompanyStockHeatBOList;
    }
}
