package la.niub.abcapi.servicecompre.service;


import la.niub.abcapi.servicecompre.model.AbcIndustryModel;

import java.util.List;
import java.util.Map;

public interface IIndustryService {

    /**
     * 行业快讯
     * @param secUniCode
     * @return
     */
    Object buildIndustryNews(Long secUniCode);

    /**
     * 另类数据
     * @param keyword
     * @param days
     * @return
     */
    Object buildHeatIndex(String keyword, Integer days);

    /**
     * 获取各种类型指数
     * @param keyword
     * @param limit
     * @param indexType
     * @return
     */
    Object buildIndexByTypeAndDays(String keyword, Integer limit, String indexType);

    /**
     * 搜索标签
     * @return
     */
    Object buildSearchTag(String keyword, Integer limit);

    /**
     * 行业分析
     * @param secUniCode
     * @return
     */
    Object buildIndustryInfo(Long secUniCode);

    /**
     * 个股分析师
     * @param stockCode
     * @return
     */
    Object buildAnalystInfo(String stockCode, String orderField);

    /**
     * 个股分析师投资评级
     * @param stockCode
     * @return
     */
    Object buildAnalystRate(String stockCode);

    /**
     * 个股分析师预测误差
     * @param stockCode
     * @return
     */
    Object buildAnalystEps(String stockCode);

    /**
     * 基金信息
     * @param stockCode
     * @return
     */
    Object buildFundInfo(String stockCode, String order_field, Integer offset, Integer limit);

    /**
     * 主营构成
     * @param comUniCode
     * @return
     */
    Object buildMajorBusiness(Long comUniCode);

    /**
     * 三大财务报表
     * @param comUniCode
     * @param type
     * @return
     */
    Object buildFinanceOverview(Long comUniCode, String type);

    /**
     * 核心财务指标
     * @param
     * @return
     */
    Object buildKeyValueOverview(Long comUniCode);

   /**
     * 财务能力
     * @param
     * @return
     */
    Object buildAbilityOverview(Long comUniCode);

    /**
     * 十大流通股东
     * @param comUniCode
     * @return
     */
    Object buildCirculationShareholders (Long comUniCode);

    /**
     * 现任高管
     * @param comUniCode
     * @return
     */
    Object buildCompanyManager (Long comUniCode);

    /**
     * 持股机构
     * @param comUniCode
     * @return
     */
    Object buildAgencyHoldDetail (Long comUniCode);

    /**
     * 商标信息
     * @return
     */
    Object buildTrademark(Long comUniCode, Integer offset, Integer limit);

    /**
     * 专利信息
     * @return
     */
    Object buildPatent(Long comUniCode, Integer offset, Integer limit);

    /**
     * 法律诉讼
     * @return
     */
    Object buildLawsuit(Long comUniCode, String caseType, Integer offset, Integer limit);

    /**
     * 获取下属所有子行业
     * @param induCode
     * @return
     */
    List<AbcIndustryModel> getChildIndustry(String induCode);

    /**
     * 个股列表三级页面
     */
    Map<String, Object> getStockInfoList(String abc_code, String prior, final int isAsc, String indu_name, String type, Integer offset, Integer limit) throws Exception;
}
