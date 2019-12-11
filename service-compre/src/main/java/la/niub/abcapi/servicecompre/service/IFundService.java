package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;
import la.niub.abcapi.servicecompre.model.OrgBasicInfoModel;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyDetailBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyHolderBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyManagerBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundDetailBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundDistributeBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundEarnBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundLastDistributeBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundSubjectBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundTopInvestBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundWorthBO;
import la.niub.abcapi.servicecompre.model.request.fund.FundOtherHeatBO;
import la.niub.abcapi.servicecompre.model.request.fund.FundOtherSentimentBO;
import la.niub.abcapi.servicecompre.model.response.fund.FundRiseRankingItemResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 基金
 */
public interface IFundService {

    /**
     * 基金基本信息
     * @param stockCode
     * @return
     */
    FundDetailBO getFundDetail(Long secUniCode);

    /**
     * 前X名股票配置
     * @param stockCode
     * @param limit
     * @return
     */
    List<FundTopInvestBO> getFundTopInvest(Long secUniCode, Integer limit);

    /**
     * 最新一期行业配置
     * @deprecated
     * @param stockCode
     * @param limit
     * @return
     */
    FundLastDistributeBO getLastDistribute(String stockCode, Integer limit);

    /**
     * 获取某个时段的行业配置
     * @deprecated
     * @param industryCodes
     * @param endDate
     * @return
     */
    Map<String,List<FundDistributeBO>> getPeriodDistribute(String stockCode,List<String> industryCodes, Date endDate);

    /**
     * 某个时间点以前的最后一期数据日期
     * @param date
     * @return
     */
    Date getLastDistributeDateBefore(Long secUniCode,Date date);

    /**
     * 某时段以前的行业配置
     * @param stockCode
     * @param limit
     * @return
     */
    List<FundDistributeBO> getDistribute(Long secUniCode, FundPeriodEnum fundPeriodEnum, Integer limit);

    /**
     * 最近几期的行业配置
     * @param stockCode
     * @param limit
     * @return
     */
    Map<String,List<FundDistributeBO>> getMultiDistribute(Long secUniCode, Integer quantity, Integer limit);

    /**
     * 获取基金公司基本信息
     * @param orgName
     * @return
     */
    FundCompanyDetailBO getCompanyDetail(Long comUniCode);


    /**
     * 获取投资方
     * @param orgName
     * @return
     */
    List<FundCompanyHolderBO> getHolder(Long comUniCode,Integer limit);

    /**
     * 获取基金所在基金公司
     * @param stockCode
     * @return
     */
    OrgBasicInfoModel getStockCompany(Long secUniCode);

    /**
     * 主题热力
     * @param stockCode
     * @param fundPeriodEnum
     * @param limit
     * @return
     */
    List<FundSubjectBO> getSubject(Long secUniCode, FundPeriodEnum fundPeriodEnum, Integer limit);

   // FundCompanyKpiResponse kpi(FundCompanyKpiRequest fundCompanyKpiRequest) throws Throwable;



    /**
     * 基金的基金经理
     * @return
     * @throws Throwable
     */
    FundCompanyManagerBO getFundManager(Long secUniCode,Integer limit);

    /**
     * 基金净值
     * @param stockCode
     * @param endDate
     * @return
     */
    List<FundWorthBO> getWorth(Long secUniCode,Date endDate,String type);

    /**
     * 收益表现
     * @param stockCode
     * @return
     */
    List<FundEarnBO> getEarn(Long secUniCode);

    /**
     * 情绪指数
     * @param stockCode
     * @return
     */
    List<FundOtherSentimentBO> getSentiment(Long secUniCode, Date endDate);

    /**
     * 舆论热度
     * @param stockCode
     * @return
     */
    List<FundOtherHeatBO> getHeat(Long secUniCode, Date endDate);

    /**
     * 同类基金
     */
    Map<String, Object> getSameFieldFund(Long sec_uni_code, String type, String fundType, String prior, String order, Integer offset, Integer limit) throws Exception;

    Map<String, Object> getRiseRanking(Long sec_uni_code, Integer limit) throws Exception;
    /**
     * 基金类型
     */
    List<Map<String, String>> getFundType(Long sec_uni_code, String type) throws Exception;

    /**
     * 行业前十和可比基金公司
     * @param sec_uni_code
     * @return
     * @throws Exception
     */
    Map<String, Object> getFundComInfo(Long sec_uni_code) throws Exception;

//    /**
//     * 当前基金信息
//     * @param sec_uni_code
//     * @return
//     * @throws Exception
//     */
//    Map<String, Object> getCurrentFundInfo(Long sec_uni_code) throws Exception;

    /**
     *基金累计净值走势图
     */
    Map<String, Object> getFundChart(String sec_uni_codes) throws Exception;
}
