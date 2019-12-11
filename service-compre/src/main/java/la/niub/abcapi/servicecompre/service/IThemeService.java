package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyStockHeatBO;
import la.niub.abcapi.servicecompre.model.bo.theme.FinancialStatisticsBO;
import la.niub.abcapi.servicecompre.model.bo.theme.MarketStatisticsBO;
import la.niub.abcapi.servicecompre.model.bo.theme.PlateDistBO;
import la.niub.abcapi.servicecompre.model.bo.theme.ThemeCardBO;
import la.niub.abcapi.servicecompre.model.request.theme.ThemeWechatPublicRequest;
import la.niub.abcapi.servicecompre.model.response.theme.ThemeWechatPublicItemResponse;

import java.util.List;
import java.util.Map;

/**
 * 主题
 */
public interface IThemeService {

    /**
     * 主题指数卡片
     * @param index_code
     * @return
     */
    ThemeCardBO buildThemeCard(String index_code);

    /**
     * 主题指数侧边栏
     * @param index_code
     * @return
     */
    Object buildThemeSide(String index_code);

    PlateDistBO getInduAnalysisPalteDist(String index_code) throws Exception;

    FinancialStatisticsBO getInduAnalysisFinancialStatistics(String index_code) throws Exception;

    MarketStatisticsBO getInduAnalysisMarketStatistics(String index_code) throws Exception;

    List<ThemeWechatPublicItemResponse> wechatPublic(ThemeWechatPublicRequest themeWechatPublicRequest);


    /**
     * 相关热门人物
     * @param theme_code
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> getHotPeoList(String theme_code) throws Exception;

    /**
     * 产业链图谱
     * @param theme_code
     * @return
     * @throws Exception
     */
    Map<String,Object> getIndustryChainMap(String theme_code) throws Exception;

    /**
     * 一级市场
     * @param theme_code
     * @param currentPage
     * @param limit
     * @param financingAmountSort
     * @return
     * @throws Exception
     */
    Map<String,Object> getPrimaryMarket(String theme_code, Integer currentPage, Integer limit, Integer financingAmountSort) throws Exception;

    List<PublicCompanyStockHeatBO> getThermodynamicChart(String index_code, FundPeriodEnum period, Integer limit) throws Exception;

}
