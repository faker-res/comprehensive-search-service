package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.component.exception.ValidatorException;
import la.niub.abcapi.servicecompre.config.code.PublicCompanyEnumCodeConfig;
import la.niub.abcapi.servicecompre.config.enums.DifferEnum;
import la.niub.abcapi.servicecompre.dao.market.IIndexCaptitalFlowDao;
import la.niub.abcapi.servicecompre.dao.market.IIndexCaptitalFlowTimeDao;
import la.niub.abcapi.servicecompre.dao.market.IIndexPriceDayDao;
import la.niub.abcapi.servicecompre.dao.marketprice.ISecPriceTimeDao;
import la.niub.abcapi.servicecompre.dao.reporter.*;
import la.niub.abcapi.servicecompre.model.AbcIndustryModel;
import la.niub.abcapi.servicecompre.model.IndexBasicInfoModel;
import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowModel;
import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowTimeModel;
import la.niub.abcapi.servicecompre.model.IndexPriceDayModel;
import la.niub.abcapi.servicecompre.model.IndexRangeChanModel;
import la.niub.abcapi.servicecompre.model.IndexValuationAnaModel;
import la.niub.abcapi.servicecompre.model.SecBasicInfoModel;
import la.niub.abcapi.servicecompre.model.SecIndustryNewModel;
import la.niub.abcapi.servicecompre.model.SecPriceTimeModel;
import la.niub.abcapi.servicecompre.model.TransacModel;
import la.niub.abcapi.servicecompre.model.bo.line.PublicCompanyTimeBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyDetailBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyDifferBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyIndexBO;
import la.niub.abcapi.servicecompre.model.request.publiccompany.PublicCompanyFundFlowRequest;
import la.niub.abcapi.servicecompre.model.request.publiccompany.PublicCompanyHeavilyFundRequest;
import la.niub.abcapi.servicecompre.model.response.publiccompany.PublicCompanyFundFlowItemResponse;
import la.niub.abcapi.servicecompre.model.response.publiccompany.PublicCompanyHeavilyFundItemResponse;
import la.niub.abcapi.servicecompre.service.ILineService;
import la.niub.abcapi.servicecompre.service.IPublicCompanyService;
import la.niub.abcapi.servicecompre.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PublicCompanyServiceImpl implements IPublicCompanyService {

    @Autowired
    IAbcIndustryDao abcIndustryDao;

    @Autowired
    IIndexPriceDayDao indexPriceDayDao;

    @Autowired
    IIndexBasicInfoDao indexBasicInfoDao;

    @Autowired
    IIndexValuationAnaDao indexValuationAnaDao;

    @Autowired
    IIndexRangeChanDao indexRangeChanDao;

    @Autowired
    IIndexCaptitalFlowDao indexCaptitalFlowDao;

    @Autowired
    ISecIndustryNewDao secIndustryNewDao;

    @Autowired
    ISecPriceTimeDao secPriceTimeDao;

    @Autowired
    ITransacDao transacDao;

    @Autowired
    IStockService stockService;

    @Autowired
    IIndexCaptitalFlowTimeDao indexCaptitalFlowTimeDao;

    @Autowired
    ILineService lineService;

    @Autowired
    ISecBasicInfoDao iSecBasicInfoDao;

    @Override
    public PublicCompanyDetailBO getDetail(Long indexUniCode) {
        PublicCompanyDetailBO publicCompanyDetailBO = new PublicCompanyDetailBO();

        IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoDao.selectByPrimaryKey(indexUniCode);
        if (indexBasicInfoModel == null){
            return publicCompanyDetailBO;
        }
        publicCompanyDetailBO.setName(indexBasicInfoModel.getIndex_name());
        publicCompanyDetailBO.setCode(indexBasicInfoModel.getIndex_code());

        IndexPriceDayModel indexPriceDayModel = indexPriceDayDao.getNewestRecord(indexUniCode);
        if (indexPriceDayModel != null){
            publicCompanyDetailBO.setTime(indexPriceDayModel.getTrade_date());
            publicCompanyDetailBO.setDate(indexPriceDayModel.getTrade_date());
            publicCompanyDetailBO.setAmount(indexPriceDayModel.getAmount() != null ? indexPriceDayModel.getAmount().doubleValue() : 0);
            publicCompanyDetailBO.setVolume(indexPriceDayModel.getVolume() != null ? indexPriceDayModel.getVolume().doubleValue() : 0);
            publicCompanyDetailBO.setOpen(indexPriceDayModel.getOpen() != null ? indexPriceDayModel.getOpen().doubleValue() : 0);
            publicCompanyDetailBO.setHigh(indexPriceDayModel.getHigh() != null ? indexPriceDayModel.getHigh().doubleValue() : 0);
            publicCompanyDetailBO.setLow(indexPriceDayModel.getLow() != null ? indexPriceDayModel.getLow().doubleValue() : 0);
            publicCompanyDetailBO.setPrice(indexPriceDayModel.getClose() != null ? indexPriceDayModel.getClose().doubleValue() : 0);
            publicCompanyDetailBO.setTurnover_rate(indexPriceDayModel.getTurnover_rate() != null ? indexPriceDayModel.getTurnover_rate().doubleValue() : 0);
            publicCompanyDetailBO.setDiffer(indexPriceDayModel.getDiffer() != null ? indexPriceDayModel.getDiffer().doubleValue() : 0);
            publicCompanyDetailBO.setDiffer_range(indexPriceDayModel.getDiffer_range() != null ? indexPriceDayModel.getDiffer_range().doubleValue() : 0);
            publicCompanyDetailBO.setRise(indexPriceDayModel.getRise_num() != null ? indexPriceDayModel.getRise_num().intValue() : 0);
            publicCompanyDetailBO.setFall(indexPriceDayModel.getFall_num() != null ? indexPriceDayModel.getFall_num().intValue() : 0);
            publicCompanyDetailBO.setFair(indexPriceDayModel.getFair_num() != null ? indexPriceDayModel.getFair_num().intValue() : 0);
        }

        IndexCaptitalFlowModel indexCaptitalFlowModel = indexCaptitalFlowDao.getRecordsByDate(indexUniCode,indexPriceDayModel.getTrade_date());
        if (indexCaptitalFlowModel != null){
            publicCompanyDetailBO.setInflow(indexCaptitalFlowModel.getMain_in_flow() != null ? indexCaptitalFlowModel.getMain_in_flow().doubleValue() : 0);
        }

        IndexValuationAnaModel indexValuationAnaModel = indexValuationAnaDao.getRecordsByDate(indexUniCode,indexPriceDayModel.getTrade_date());
        if (indexValuationAnaModel != null){
            publicCompanyDetailBO.setPe_ratio(indexValuationAnaModel.getPelyr() != null ? indexValuationAnaModel.getPelyr().doubleValue() : 0);
            publicCompanyDetailBO.setPb_ratio(indexValuationAnaModel.getPbmrq() != null ? indexValuationAnaModel.getPbmrq().doubleValue() : 0);
        }
        PublicCompanyTimeBO publicCompanyTimeBO = lineService.getPublicCompanyRealTime(indexUniCode);
        if (publicCompanyTimeBO != null){
            if (publicCompanyDetailBO.getPe_ratio() == null || publicCompanyDetailBO.getPe_ratio() == 0){
                publicCompanyDetailBO.setPe_ratio(publicCompanyTimeBO.getPe());
            }
            if (publicCompanyDetailBO.getPb_ratio() == null || publicCompanyDetailBO.getPb_ratio() == 0){
                publicCompanyDetailBO.setPb_ratio(publicCompanyTimeBO.getPb());
            }
        }

        IndexRangeChanModel indexRangeChanModel5Day = indexRangeChanDao.getRecord5Day(indexUniCode,indexPriceDayModel.getTrade_date());
        if (indexRangeChanModel5Day != null){
            Double day5 = indexRangeChanModel5Day.getDiffer_range() != null
                    ? indexRangeChanModel5Day.getDiffer_range().doubleValue() : 0;
            publicCompanyDetailBO.setDay5(day5);
        }
        IndexRangeChanModel indexRangeChanModel20day = indexRangeChanDao.getRecord20Day(indexUniCode,indexPriceDayModel.getTrade_date());
        if (indexRangeChanModel20day != null){
            Double day20 = indexRangeChanModel20day.getDiffer_range() != null
                    ? indexRangeChanModel20day.getDiffer_range().doubleValue() : 0;
            publicCompanyDetailBO.setDay20(day20);
        }

        return publicCompanyDetailBO;
    }

    @Override
    public List<PublicCompanyIndexBO> getIndex(Long indexUniCode, Date endDate) {
        List<PublicCompanyIndexBO> publicCompanyIndexBOList = new ArrayList<>();

        List<IndexPriceDayModel> indexPriceDayModelList = indexPriceDayDao.getRecordsByDates(indexUniCode,endDate,new Date());
        for (IndexPriceDayModel item : indexPriceDayModelList){
            PublicCompanyIndexBO publicCompanyIndexBO = new PublicCompanyIndexBO();
            publicCompanyIndexBO.setTrade_date(item.getTrade_date());
            publicCompanyIndexBO.setDate(item.getTrade_date());
            publicCompanyIndexBO.setTurn(item.getTurnover_rate());
            publicCompanyIndexBO.setDiffer(item.getDiffer());
            publicCompanyIndexBO.setDiffer_range(item.getDiffer_range());
            publicCompanyIndexBO.setAmount(item.getAmount());
            publicCompanyIndexBO.setVolume(item.getVolume());
            publicCompanyIndexBO.setOpen(item.getOpen());
            publicCompanyIndexBO.setHigh(item.getHigh());
            publicCompanyIndexBO.setLow(item.getLow());
            publicCompanyIndexBO.setClose(item.getClose());

            publicCompanyIndexBOList.add(publicCompanyIndexBO);
        }
//        Collections.reverse(publicCompanyIndexBOList);
        return publicCompanyIndexBOList;
    }

    @Override
    public List<PublicCompanyDifferBO> getDiffer(Long indexUniCode, DifferEnum differEnum, Integer limit) throws Exception {
        List<PublicCompanyDifferBO> publicCompanyDifferBOList = new ArrayList<>();

        IndexBasicInfoModel indexBasicInfoModel = indexBasicInfoDao.selectByPrimaryKey(indexUniCode);
        if (indexBasicInfoModel == null){
            return publicCompanyDifferBOList;
        }
        AbcIndustryModel abcIndustryModel = abcIndustryDao.selectByIndexCode(indexBasicInfoModel.getIndex_code());
        if (abcIndustryModel == null){
            return publicCompanyDifferBOList;
        }

        List<SecIndustryNewModel> secIndustryNewModelList = secIndustryNewDao.selectShenwanByFirstInduCode(abcIndustryModel.getIndu_code());
        if (ObjectUtils.isEmpty(secIndustryNewModelList)){
            return publicCompanyDifferBOList;
        }

        List<String> secCodes = new ArrayList<>();
        for (SecIndustryNewModel item : secIndustryNewModelList){
            secCodes.add(item.getStk_code());
        }

        List<SecBasicInfoModel> secBasicInfoModelList = stockService.getRecordsBySecCodes(secCodes);
        Map<String,String> secNames = new HashMap<>();
        for (SecBasicInfoModel item : secBasicInfoModelList){
            secNames.put(item.getAbc_code(),item.getSec_name());
        }

        Date now = new Date();
        Date startTime = null;
//        Date lastTradeDate = secPriceTimeDao.getLastTradeDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        calendar.set(Calendar.HOUR_OF_DAY,9);
        calendar.set(Calendar.MINUTE,30);
        if (now.after(calendar.getTime())){
//            calendar.setTime(now);
//            calendar.add(Calendar.HOUR_OF_DAY,-3);
            startTime = calendar.getTime();
        }else{
            //沪深市场今天之前的最后交易日
            TransacModel transacModel = transacDao.getLastTransDateBeforeToday(1003001L);
            startTime = transacModel.getEnd_date();
        }

        List<SecPriceTimeModel> secPriceTimeModelList = null;
        if (differEnum == DifferEnum.RISK){
            secPriceTimeModelList = secPriceTimeDao.selectMostRisk(secCodes,startTime,limit);
        }else if (differEnum == DifferEnum.FALL){
            secPriceTimeModelList = secPriceTimeDao.selectMostFall(secCodes,startTime,limit);
        }
        if (!ObjectUtils.isEmpty(secPriceTimeModelList)){
            for (SecPriceTimeModel item : secPriceTimeModelList){
                PublicCompanyDifferBO publicCompanyDifferBO = new PublicCompanyDifferBO();
                publicCompanyDifferBO.setName(secNames.get(item.getSec_code()));
                publicCompanyDifferBO.setCode(item.getSec_code());
                Long sec_uni_code = item.getSec_uni_code();
                publicCompanyDifferBO.setSec_uni_code(sec_uni_code);
                Long com_uni_code = iSecBasicInfoDao.getComUniCodeBySecUniCode(sec_uni_code);
                publicCompanyDifferBO.setCom_uni_code(com_uni_code);
                publicCompanyDifferBO.setPrice(item.getOpen() != null ? item.getOpen().doubleValue() : 0);
                publicCompanyDifferBO.setDiffer_range(item.getDiffer_range() != null ? item.getDiffer_range().doubleValue() : 0);
                publicCompanyDifferBO.setDate(item.getTrade_date());
                publicCompanyDifferBOList.add(publicCompanyDifferBO);
            }
        }

        return publicCompanyDifferBOList;
    }

    @Override
    public IndexBasicInfoModel getIndustryByName(String industryName) {
        AbcIndustryModel abcIndustryModel = abcIndustryDao.selectByInduNameOf1001014(industryName.trim());
        if (abcIndustryModel == null){
            return null;
        }
        return indexBasicInfoDao.selectByIndexCode(abcIndustryModel.getIndex_code());
    }

    @Override
    public List<PublicCompanyHeavilyFundItemResponse> heavilyFund(PublicCompanyHeavilyFundRequest publicCompanyHeavilyFundRequest) {
        List<PublicCompanyHeavilyFundItemResponse> result = new ArrayList<>();
        PublicCompanyHeavilyFundItemResponse resultItem = new PublicCompanyHeavilyFundItemResponse();
        resultItem.setFundName("天弘中证全指");
        resultItem.setFundCode("001554");
        resultItem.setPosition(0.8689F);
        resultItem.setDiff(-0.003F);
        resultItem.setPrice(0.6322F);
        resultItem.setType("股票");
        List<Float[]> trend = new ArrayList<>();
        Float[] floats1 = new Float[2];
        floats1[0] = 1514736000F;
        floats1[1] = 0.712F;
        Float[] floats2 = new Float[2];
        floats2[0] = 1522512000F;
        floats2[1] = 0.925F;
        Float[] floats3 = new Float[2];
        floats3[0] = 1530374400F;
        floats3[1] = 0.855F;
        trend.add(floats1);
        trend.add(floats2);
        trend.add(floats3);
        resultItem.setTrend(trend);
        result.add(resultItem);
        return  result;
    }

    @Override
    public List<PublicCompanyFundFlowItemResponse> fundFlow(PublicCompanyFundFlowRequest publicCompanyFundFlowRequest) throws Throwable {

        long code = publicCompanyFundFlowRequest.getCode();

        int type = publicCompanyFundFlowRequest.getType();

        if (code <= 0) {
            throw new ValidatorException(PublicCompanyEnumCodeConfig.EMPTY_PUBLIC_COMPANY_CODE);
        }

        if (type != 1 && type != 2) {
            throw new ValidatorException(PublicCompanyEnumCodeConfig.ERROR_PUBLIC_COMPANY_FUND_FLOW_TYPE);
        }

        List<PublicCompanyFundFlowItemResponse> result = new ArrayList<>();


        if (type == 1) {
            List<IndexCaptitalFlowTimeModel> timeFlowList = indexCaptitalFlowTimeDao.selectRealtimeByIndexUniCode(code);
            if (timeFlowList != null && !timeFlowList.isEmpty()) {
                for (IndexCaptitalFlowTimeModel timeFlowItem : timeFlowList) {
                    PublicCompanyFundFlowItemResponse resultItem = new PublicCompanyFundFlowItemResponse();
                    resultItem.setId(timeFlowItem.getId());
                    Long timeLong = timeFlowItem.getTrade_date().getTime() / 1000;
                    resultItem.setTime(timeLong.intValue());
                    resultItem.setDate(timeFlowItem.getTrade_date());
                    resultItem.setMainFlow(timeFlowItem.getMain_netin_flow() != null ? timeFlowItem.getMain_netin_flow().floatValue() : 0F);
                    resultItem.setMainPro(timeFlowItem.getMain_net_rate() != null ? timeFlowItem.getMain_net_rate().floatValue() : 0F);
                    resultItem.setSupInFlow(timeFlowItem.getSup_in_flow() != null ? timeFlowItem.getSup_in_flow().floatValue() : 0F);
                    resultItem.setSupOutFlow(timeFlowItem.getSup_out_flow() != null ? timeFlowItem.getSup_out_flow().floatValue() : 0F);
                    resultItem.setSupFlow(timeFlowItem.getSup_netin_flow() != null ? timeFlowItem.getSup_netin_flow().floatValue() : 0F);
                    resultItem.setSupPro(timeFlowItem.getSup_net_rate() != null ? timeFlowItem.getSup_net_rate().floatValue() : 0F);
                    resultItem.setBigInFlow(timeFlowItem.getBig_in_flow() != null ? timeFlowItem.getBig_in_flow().floatValue() : 0F);
                    resultItem.setBigOutFlow(timeFlowItem.getBig_out_flow() != null ? timeFlowItem.getBig_out_flow().floatValue() : 0F);
                    resultItem.setBigFlow(timeFlowItem.getBig_netin_flow() != null ? timeFlowItem.getBig_netin_flow().floatValue() : 0F);
                    resultItem.setBigPro(timeFlowItem.getBig_net_rate() != null ? timeFlowItem.getBig_net_rate().floatValue() : 0F);
                    resultItem.setMidInFlow(timeFlowItem.getMid_in_flow() != null ? timeFlowItem.getMid_in_flow().floatValue() : 0F);
                    resultItem.setMidOutFlow(timeFlowItem.getMid_out_flow() != null ? timeFlowItem.getMid_out_flow().floatValue() : 0F);
                    resultItem.setMidFlow(timeFlowItem.getMid_netin_flow() !=null ? timeFlowItem.getMid_netin_flow().floatValue() : 0F);
                    resultItem.setMidPro(timeFlowItem.getMid_net_rate() != null ? timeFlowItem.getMid_net_rate().floatValue() : 0F);
                    resultItem.setSmInFlow(timeFlowItem.getSma_in_flow() != null ? timeFlowItem.getSma_in_flow().floatValue() : 0F);
                    resultItem.setSmOutFlow(timeFlowItem.getSma_out_flow() != null ? timeFlowItem.getSma_out_flow().floatValue() : 0F);
                    resultItem.setSmFlow(timeFlowItem.getSma_netin_flow() != null ? timeFlowItem.getSma_netin_flow().floatValue() : 0F);
                    resultItem.setSmPro(timeFlowItem.getSma_net_rate() != null ? timeFlowItem.getSma_net_rate().floatValue() : 0F);
                    result.add(resultItem);
                }
            }
        } else if (type == 2){
            List<IndexCaptitalFlowModel> flowList = indexCaptitalFlowDao.getListByIndexUniCodeAndLimit(code, 7);
            if (flowList != null && !flowList.isEmpty()) {
                for (IndexCaptitalFlowModel flowItem : flowList) {
                    PublicCompanyFundFlowItemResponse resultItem = new PublicCompanyFundFlowItemResponse();
                    resultItem.setId(flowItem.getId());
                    Long timeLong = flowItem.getTrade_date().getTime() / 1000;
                    resultItem.setTime(timeLong.intValue());
                    resultItem.setDate(flowItem.getTrade_date());
                    resultItem.setMainFlow(flowItem.getMain_netin_flow() != null ? flowItem.getMain_netin_flow().floatValue() : 0F);
                    resultItem.setMainPro(flowItem.getMain_net_rate() != null ? flowItem.getMain_net_rate().floatValue() : 0F);
                    resultItem.setSupInFlow(flowItem.getSup_in_flow() != null ? flowItem.getSup_in_flow().floatValue() : 0F);
                    resultItem.setSupOutFlow(flowItem.getSup_out_flow() != null ? flowItem.getSup_out_flow().floatValue() : 0F);
                    resultItem.setSupFlow(flowItem.getSup_netin_flow() != null ? flowItem.getSup_netin_flow().floatValue() : 0F);
                    resultItem.setSupPro(flowItem.getSup_net_rate() != null ? flowItem.getSup_net_rate().floatValue() : 0F);
                    resultItem.setBigInFlow(flowItem.getBig_in_flow() != null ? flowItem.getBig_in_flow().floatValue() : 0F);
                    resultItem.setBigOutFlow(flowItem.getBig_out_flow() != null ? flowItem.getBig_out_flow().floatValue() : 0F);
                    resultItem.setBigFlow(flowItem.getBig_netin_flow() != null ? flowItem.getBig_netin_flow().floatValue() : 0F);
                    resultItem.setBigPro(flowItem.getBig_net_rate() != null ? flowItem.getBig_net_rate().floatValue() : 0F);
                    resultItem.setMidInFlow(flowItem.getMid_in_flow() != null ? flowItem.getMid_in_flow().floatValue() : 0F);
                    resultItem.setMidOutFlow(flowItem.getMid_out_flow() != null ? flowItem.getMid_out_flow().floatValue() : 0F);
                    resultItem.setMidFlow(flowItem.getMid_netin_flow() !=null ? flowItem.getMid_netin_flow().floatValue() : 0F);
                    resultItem.setMidPro(flowItem.getMid_net_rate() != null ? flowItem.getMid_net_rate().floatValue() : 0F);
                    resultItem.setSmInFlow(flowItem.getSma_in_flow() != null ? flowItem.getSma_in_flow().floatValue() : 0F);
                    resultItem.setSmOutFlow(flowItem.getSma_out_flow() != null ? flowItem.getSma_out_flow().floatValue() : 0F);
                    resultItem.setSmFlow(flowItem.getSma_netin_flow() != null ? flowItem.getSma_netin_flow().floatValue() : 0F);
                    resultItem.setSmPro(flowItem.getSma_net_rate() != null ? flowItem.getSma_net_rate().floatValue() : 0F);
                    result.add(resultItem);
                }

                Collections.reverse(result);
            }

        }

        return result;
    }
}
