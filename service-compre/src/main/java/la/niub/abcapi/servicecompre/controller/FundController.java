package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;
import la.niub.abcapi.servicecompre.model.OrgBasicInfoModel;
import la.niub.abcapi.servicecompre.model.SecBasicInfoModel;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyDetailBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyManagerBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundDetailBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundDistributeBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundEarnBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundSubjectBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundTopInvestBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundWorthBO;
import la.niub.abcapi.servicecompre.model.request.fund.FundCompanyRequest;
import la.niub.abcapi.servicecompre.model.request.fund.FundDetailRequest;
import la.niub.abcapi.servicecompre.model.request.fund.FundDistributeRequest;
import la.niub.abcapi.servicecompre.model.request.fund.FundEarnRequest;
import la.niub.abcapi.servicecompre.model.request.fund.FundManagerRequest;
import la.niub.abcapi.servicecompre.model.request.fund.FundOtherRequest;
import la.niub.abcapi.servicecompre.model.request.fund.FundSubjectRequest;
import la.niub.abcapi.servicecompre.model.request.fund.FundTopInvestRequest;
import la.niub.abcapi.servicecompre.model.request.fund.FundWorthRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.fund.*;
import la.niub.abcapi.servicecompre.service.IFundService;
import la.niub.abcapi.servicecompre.service.IStockService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 基金
 */
@RestController
@RequestMapping(path = "/fund")
public class FundController {

    private static Logger logger = LogManager.getLogger(FundController.class);

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    IFundService fundService;

    @Autowired
    IStockService stockService;

    /**
     * 基本信息
     * @param params
     * @return
     * @throws CustomException
     */
    @GetMapping
    Response<FundDetailResponse> get(FundDetailRequest params) throws CustomException {
        if (params.getSec_uni_code() == null && params.getCode() != null){
            SecBasicInfoModel secBasicInfoModel = stockService.buildRecordByStockCode(params.getCode());
            if (secBasicInfoModel == null){
                return new Response<>(new FundDetailResponse());
            }
            params.setSec_uni_code(secBasicInfoModel.getSec_uni_code());
        }

        FundDetailBO fundDetailBO = fundService.getFundDetail(params.getSec_uni_code());

        FundDetailResponse fundDetailResponse = new FundDetailResponse();
        fundDetailResponse.setCode(fundDetailBO.getCode());
        fundDetailResponse.setName(fundDetailBO.getName());
        fundDetailResponse.setFullname(fundDetailBO.getFullname());
        fundDetailResponse.setType(fundDetailBO.getType());
        fundDetailResponse.setRedem(fundDetailBO.getRedem());
        fundDetailResponse.setMethod(fundDetailBO.getMethod());
        fundDetailResponse.setBenchmark(fundDetailBO.getBenchmark());
        fundDetailResponse.setRisk(fundDetailBO.getRisk());
        fundDetailResponse.setEstablish_time(fundDetailBO.getEstablishTime());
        fundDetailResponse.setIsExpired(fundDetailBO.getIsExpired());

        return new Response<>(fundDetailResponse);
    }

    /**
     * 行业配置
     * @param params
     * @return
     * @throws CustomException
     */
    @GetMapping("/distribute")
    Response<FundDistributeResponse> distribute(FundDistributeRequest params) throws CustomException {
        if (params.getSec_uni_code() == null && params.getCode() != null){
            SecBasicInfoModel secBasicInfoModel = stockService.buildRecordByStockCode(params.getCode());
            if (secBasicInfoModel == null){
                return new Response<>(new FundDistributeResponse());
            }
            params.setSec_uni_code(secBasicInfoModel.getSec_uni_code());
        }

        FundDistributeResponse fundDistributeResponse = new FundDistributeResponse();

        FundPeriodEnum period = FundPeriodEnum.valueOf(params.getPeriod());
        if (period == null){
            period = FundPeriodEnum.M3;
        }
        List<FundDistributeBO> fundDistributeBOList = fundService.getDistribute(params.getSec_uni_code(),period,params.getLimit());
        fundDistributeResponse.setLastDistribute(fundDistributeBOList);

        fundDistributeResponse.setPeriodDistribute(fundService.getMultiDistribute(params.getSec_uni_code(),4,params.getLimit()));

        if (!ObjectUtils.isEmpty(fundDistributeBOList)){
            fundDistributeResponse.setTime(fundDistributeBOList.get(0).getDate());
        }

        return new Response<>(fundDistributeResponse);
    }

    /**
     * 前十股票
     * @param params
     * @return
     * @throws CustomException
     */
    @GetMapping("/topinvest")
    Response<FundTopInvestResponse> topinvest(FundTopInvestRequest params) throws CustomException {
        if (params.getSec_uni_code() == null && params.getCode() != null){
            SecBasicInfoModel secBasicInfoModel = stockService.buildRecordByStockCode(params.getCode());
            if (secBasicInfoModel == null){
                return new Response<>(new FundTopInvestResponse());
            }
            params.setSec_uni_code(secBasicInfoModel.getSec_uni_code());
        }

        List<FundTopInvestBO> fundTopInvestBOList = fundService.getFundTopInvest(params.getSec_uni_code(),params.getLimit());

        FundTopInvestResponse fundTopInvestResponse = new FundTopInvestResponse();
        fundTopInvestResponse.setList(fundTopInvestBOList);
        return new Response<>(fundTopInvestResponse);
    }

    /**
     * 主题热力
     * @return
     * @throws CustomException
     */
    @GetMapping("/subject")
    Response<List<FundSubjectBO>> subject(FundSubjectRequest params) throws CustomException {
        if (params.getSec_uni_code() == null && params.getCode() != null){
            SecBasicInfoModel secBasicInfoModel = stockService.buildRecordByStockCode(params.getCode());
            if (secBasicInfoModel == null){
                return new Response<>(new ArrayList<>());
            }
            params.setSec_uni_code(secBasicInfoModel.getSec_uni_code());
        }

        FundPeriodEnum period = FundPeriodEnum.valueOf(params.getPeriod());
        if (period == null){
            period = FundPeriodEnum.M3;
        }

        List<FundSubjectBO> fundSubjectBOList = fundService.getSubject(params.getSec_uni_code(),period,params.getLimit());

        return new Response<>(fundSubjectBOList);
    }

    /**
     * 基金公司
     * @return
     * @throws CustomException
     */
    @GetMapping("/company")
    Response<FundCompanyDetailBO> company(FundCompanyRequest params) throws CustomException {
        if (params.getSec_uni_code() == null && params.getCode() != null){
            SecBasicInfoModel secBasicInfoModel = stockService.buildRecordByStockCode(params.getCode());
            if (secBasicInfoModel == null){
                return new Response<>(new FundCompanyDetailBO());
            }
            params.setSec_uni_code(secBasicInfoModel.getSec_uni_code());
        }

        FundCompanyDetailBO fundCompanyDetailBO = new FundCompanyDetailBO();
        OrgBasicInfoModel orgBasicInfoModel = fundService.getStockCompany(params.getSec_uni_code());
        if (orgBasicInfoModel == null){
            return new Response<>(fundCompanyDetailBO);
        }
        fundCompanyDetailBO = fundService.getCompanyDetail(orgBasicInfoModel.getCom_uni_code());

        return new Response<>(fundCompanyDetailBO);
    }

    /**
     * 基金经理
     * @param params
     * @return
     * @throws Throwable
     */
    @GetMapping("/manager")
    Response<FundCompanyManagerBO> manager(FundManagerRequest params) throws CustomException {
        if (params.getSec_uni_code() == null && params.getCode() != null){
            SecBasicInfoModel secBasicInfoModel = stockService.buildRecordByStockCode(params.getCode());
            if (secBasicInfoModel == null){
                return new Response<>(new FundCompanyManagerBO());
            }
            params.setSec_uni_code(secBasicInfoModel.getSec_uni_code());
        }

        FundCompanyManagerBO fundCompanyManagerBO = fundService.getFundManager(params.getSec_uni_code(),params.getLimit());

        return new Response<>(fundCompanyManagerBO);
    }

    /**
     * 另类数据
     * @return
     * @throws CustomException
     */
    @GetMapping("/other")
    Response<FundOtherResponse> other(FundOtherRequest params) throws CustomException {
        if (params.getSec_uni_code() == null && params.getCode() != null){
            SecBasicInfoModel secBasicInfoModel = stockService.buildRecordByStockCode(params.getCode());
            if (secBasicInfoModel == null){
                return new Response<>(new FundOtherResponse());
            }
            params.setSec_uni_code(secBasicInfoModel.getSec_uni_code());
        }

        Date startTime = null;
        if (StringUtils.isNotEmpty(params.getStart_time())){
            startTime = TimeUtil.parseDateStr(params.getStart_time(),"yyyy-MM-dd");
        }
        if (startTime == null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.MILLISECOND,0);
            calendar.add(Calendar.MONTH,-2);
            startTime = calendar.getTime();
        }

        FundOtherResponse fundOtherResponse = new FundOtherResponse();
        fundOtherResponse.setSentiment(fundService.getSentiment(params.getSec_uni_code(),startTime));
        fundOtherResponse.setHeat(fundService.getHeat(params.getSec_uni_code(),startTime));

        return new Response<>(fundOtherResponse);
    }

    /**
     * 单位净值/累计净值
     * @return
     * @throws CustomException
     */
    @GetMapping("/worth")
    Response<List<FundWorthBO>> worth(FundWorthRequest params) throws CustomException {
        if (params.getSec_uni_code() == null && params.getCode() != null){
            SecBasicInfoModel secBasicInfoModel = stockService.buildRecordByStockCode(params.getCode());
            if (secBasicInfoModel == null){
                return new Response<>(new ArrayList<>());
            }
            params.setSec_uni_code(secBasicInfoModel.getSec_uni_code());
        }

        Date startTime = null;
        if (StringUtils.isNotEmpty(params.getStart_time())){
            startTime = TimeUtil.parseDateStr(params.getStart_time(),"yyyy-MM-dd");
        }
        if (startTime == null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.MILLISECOND,0);
            calendar.add(Calendar.MONTH,-6);
            startTime = calendar.getTime();
        }

        List<FundWorthBO> fundWorthBOList = fundService.getWorth(params.getSec_uni_code(),startTime,params.getType());

        return new Response<>(fundWorthBOList);
    }

    /**
     * 收益表现
     * @return
     * @throws CustomException
     */
    @GetMapping("/earn")
    Response<List<FundEarnBO>> earn(FundEarnRequest params) throws CustomException {
        if (params.getSec_uni_code() == null && params.getCode() != null){
            SecBasicInfoModel secBasicInfoModel = stockService.buildRecordByStockCode(params.getCode());
            if (secBasicInfoModel == null){
                return new Response<>(new ArrayList<>());
            }
            params.setSec_uni_code(secBasicInfoModel.getSec_uni_code());
        }

        List<FundEarnBO> fundEarnBOList = fundService.getEarn(params.getSec_uni_code());

        return new Response<>(fundEarnBOList);
    }

    /**
     * 同类基金
     */
    @GetMapping("sameFieldFund")
    public Response getSameFiledFund(@RequestParam("sec_uni_code") Long sec_uni_code,
                                     @RequestParam("type") String type,
                                     String fundType,
                                     String prior,
                                     String order,
                                     @RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit) {
        if (StringUtil.isEmpty(sec_uni_code)) {
            logger.error("传入的sec_uni_code为null");
            return new Response(408, "传入的sec_uni_code为null");
        }

        try {
            Map<String, Object> sameFieldFundInfo = fundService.getSameFieldFund(sec_uni_code, type, fundType, prior, order, offset, limit);
            return new Response(sameFieldFundInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取同类基金失败：" + e.getMessage());
            return new Response(500, "获取同类基金失败：" + e.getMessage());
        }
    }

    /**
     * 基金类型
     */
    @GetMapping("fundType")
    public Response getFundType(@RequestParam("sec_uni_code") Long sec_uni_code, @RequestParam("type") String type) {
        try {
            List<Map<String, String>> fundType = fundService.getFundType(sec_uni_code, type);
            return new Response(fundType);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金类型失败：" + e.getMessage());
            return new Response(500, "获取基金类型失败：" + e.getMessage());
        }
    }

    /**
     * 基金公司top10和可比基金公司
     */
    @GetMapping("topTenAndComparableFundCom")
    public Response getTopTenAndComparableFundCom(@RequestParam("sec_uni_code") Long sec_uni_code) {
        if (StringUtil.isEmpty(sec_uni_code)) {
            logger.error("传入的sec_uni_code为null");
            return new Response(408, "传入的sec_uni_code为null");
        }

        try {
            Map<String, Object> fundComInfo = fundService.getFundComInfo(sec_uni_code);
            return new Response(fundComInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取行业top10和可比基金公司失败：" + e.getMessage());
            return new Response(500, "获取行业top10和可比基金公司失败：" + e.getMessage());
        }
    }

//    /**
//     * 获取当前基金的信息
//     */
//    @GetMapping("currentFundInfo")
//    public Response getCurrentFundInfo(@RequestParam("sec_uni_code") Long sec_uni_code) {
//        if (StringUtil.isEmpty(sec_uni_code)) {
//            logger.error("传入的sec_uni_code为null");
//            return new Response(408, "传入的sec_uni_code为null");
//        }
//
//        try {
//            Map<String, Object> currentFundInfo = fundService.getCurrentFundInfo(sec_uni_code);
//            return new Response(currentFundInfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("获取当前基金信息失败：" + e.getMessage());
//            return new Response(500, "获取当前基金信息失败：" + e.getMessage());
//        }
//    }

    /**
     * 累计净值走势图
     */
    @GetMapping("fundChart")
    public Response getFundChart(@RequestParam("sec_uni_codes") String sec_uni_codes) {
        if (StringUtil.isEmpty(sec_uni_codes)) {
            logger.error("传入的sec_uni_code为null");
            return new Response(408, "传入的sec_uni_code为null");
        }

        try {
            Map<String, Object> fundChart = fundService.getFundChart(sec_uni_codes);
            return new Response(fundChart);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金累计净值走势图失败：" + e.getMessage());
            return new Response(500, "获取基金累计净值走势失败：" + e.getMessage());
        }
    }

    @GetMapping("/riseRanking")
    public Response riseRanking(@RequestParam("sec_uni_code") Long sec_uni_code,
                                @RequestParam(value = "limit", required = false, defaultValue = "7") Integer limit) {
        try {
            if (StringUtil.isEmpty(sec_uni_code)) {
                logger.error("sec_uni_code为null");
                return new Response(408, "sec_uni_code为null");
            }

            Map<String, Object> result = fundService.getRiseRanking(sec_uni_code, limit);

            if (result == null) {
                logger.error("sec_uni_code:" + sec_uni_code + "无分类");
                return new Response(result, "sec_uni_code:" + sec_uni_code + "无分类");
            }

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金的同类基金涨幅排行失败：" + e.getMessage());
            return new Response(500, "获取基金的同类基金涨幅排行失败:" + e.getMessage());
        }
    }
}
