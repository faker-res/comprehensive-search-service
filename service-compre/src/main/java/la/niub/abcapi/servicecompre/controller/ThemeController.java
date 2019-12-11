package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyStockHeatBO;
import la.niub.abcapi.servicecompre.model.bo.theme.FinancialStatisticsBO;
import la.niub.abcapi.servicecompre.model.bo.theme.MarketStatisticsBO;
import la.niub.abcapi.servicecompre.model.bo.theme.PlateDistBO;
import la.niub.abcapi.servicecompre.model.request.theme.ThemeWechatPublicRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.theme.ThemeWechatPublicItemResponse;
import la.niub.abcapi.servicecompre.service.IThemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 主题
 */
@RestController
@RequestMapping(path = "/theme")
public class ThemeController {

    private static final Logger logger = LoggerFactory.getLogger(ThemeController.class);

    @Autowired
    IThemeService themeService;

    @RequestMapping(value = "/card")
    Response<Object> getThemeCard(String index_code) throws ServiceException {
        return new Response(themeService.buildThemeCard(index_code));
    }


    @RequestMapping(value = "/sidebar")
    Response<Object> getThemeSide(String index_code) throws ServiceException {
        return new Response(themeService.buildThemeSide(index_code));
    }

    @GetMapping("induAnalysis/plateDist")
    public Response getInduAnalysisPalteDist(@RequestParam("index_code") String index_code) {
        if (StringUtil.isEmpty(index_code)) {
            logger.error("传入的index_code为null");
            return new Response(408, "传入的index_code为null");
        }

        try {
            PlateDistBO plateDistBO = themeService.getInduAnalysisPalteDist(index_code);
            return new Response(plateDistBO);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取板块分布数据失败：" + e.getMessage());
            return new Response(500, "获取板块分布数据失败：" + e.getMessage());
        }
    }

    @GetMapping("induAnalysis/financialStatistics")
    public Response getInduAnalysisFinancialStatistics(@RequestParam("index_code") String index_code) {
        if (StringUtil.isEmpty(index_code)) {
            logger.error("传入的index_code为null");
            return new Response(408, "传入的index_code为null");
        }

        try {
            FinancialStatisticsBO financialStatisticsBO = themeService.getInduAnalysisFinancialStatistics(index_code);
            return new Response(financialStatisticsBO);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取财务统计数据失败：" + e.getMessage());
            return new Response(500, "获取财务统计数据失败：" + e.getMessage());
        }
    }

    @GetMapping("induAnalysis/marketStatistics")
    public Response getInduAnalysisMarketStatistics(@RequestParam("index_code") String index_code) {
        if (StringUtil.isEmpty(index_code)) {
            logger.error("传入的index_code为null");
            return new Response(408, "传入的index_code为null");
        }

        try {
            MarketStatisticsBO marketStatisticsBO = themeService.getInduAnalysisMarketStatistics(index_code);
            return new Response(marketStatisticsBO);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取行情统计数据失败：" + e.getMessage());
            return new Response(500, "获取行情统计数据失败：" + e.getMessage());
        }
    }

    @GetMapping("/wechatPublic")
    Response wechatPublic(ThemeWechatPublicRequest themeWechatPublicRequest) {
        List<ThemeWechatPublicItemResponse> wechatPublicList = themeService.wechatPublic(themeWechatPublicRequest);
        return new Response(wechatPublicList);
    }

    /**
     * 相关热门人物
     * @param theme_code
     * @return
     * @throws ServiceException
     */
    @GetMapping(value = "/hotPeople")
    public Response getHotPeoList(@RequestParam(value = "theme_code") String theme_code) {
        try {
            if (StringUtil.isEmpty(theme_code)) {
                logger.error("传入的参数theme_code为null");
                return new Response(408, "传入的参数theme_code为null");
            }

            List<Map<String, Object>> result = themeService.getHotPeoList(theme_code);

            if (result == null || result.isEmpty()) {
                logger.warn("主题:" + theme_code + "的相关热门人物,无数据");
                return new Response(result, "主题:" + theme_code + "的相关热门人物,无数据");
            }

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取主题的相关热门人物失败：" + e.getMessage());
            return new Response(500, "获取主题的相关热门人物:" + e.getMessage());
        }
    }

    /**
     * 产业链图谱
     * @param theme_code
     * @return
     */
    @GetMapping(value = "/industryChainMap")
    public Response getIndustryChainMap(@RequestParam(value = "theme_code") String theme_code) {
        try {
            if (StringUtil.isEmpty(theme_code)) {
                logger.error("传入的参数theme_code为null");
                return new Response(408, "传入的参数theme_code为null");
            }

            Map<String, Object> result = themeService.getIndustryChainMap(theme_code);

            if (result == null || result.isEmpty()) {
                logger.warn("主题:" + theme_code + "的产业链无数据");
                return new Response(result, "主题:" + theme_code + "的产业链无数据");
            }

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取主题的产业链图谱失败：" + e.getMessage());
            return new Response(500, "获取主题的产业链图谱失败:" + e.getMessage());
        }
    }

    /**
     * 一级市场
     * @param theme_code
     * @param currentPage
     * @param limit
     * @param financingAmountSort  融资金额排序 0：正序 1：倒序
     * @return
     */
    @GetMapping(value = "/primaryMarket")
    public Response getPrimaryMarket(@RequestParam(value = "theme_code") String theme_code,
                                      @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                      @RequestParam(value = "limit", defaultValue = "6") Integer limit,
                                      @RequestParam(value = "financingAmountSort", defaultValue = "1") Integer financingAmountSort) {
        try {
            if (StringUtil.isEmpty(theme_code)) {
                logger.error("传入的参数theme_code为null");
                return new Response(408, "传入的参数theme_code为null");
            }

            Map<String, Object> result = themeService.getPrimaryMarket(theme_code, currentPage, limit, financingAmountSort);

            if (result == null || result.isEmpty()) {
                logger.warn("主题:" + theme_code + "的一级市场,无公司数据");
                return new Response(result, "主题:" + theme_code + "的一级市场,无公司数据");
            }

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取主题的一级市场失败：" + e.getMessage());
            return new Response(500, "获取主题的一级市场失败:" + e.getMessage());
        }
    }

    @GetMapping("thermodynamicChart")
    public Response getThermodynamicChart(@RequestParam("index_code") String index_code, FundPeriodEnum period, Integer limit) {
        if (StringUtil.isEmpty(index_code)) {
            logger.error("传入的index_code为null");
            return new Response(408, "传入的index_code为null");
        }

        try {
            List<PublicCompanyStockHeatBO> publicCompanyStockHeatBOList = themeService.getThermodynamicChart(index_code, period, limit);
            return new Response(publicCompanyStockHeatBOList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取主题热力图数据失败：" + e.getMessage());
            return new Response(500, "获取主题热力图数据失败：" + e.getMessage());
        }
    }
}
