package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.model.FundManagerInfoModel;
import la.niub.abcapi.servicecompre.model.FundManagerStockModel;
import la.niub.abcapi.servicecompre.model.request.FundManagerFundChartRequest;
import la.niub.abcapi.servicecompre.model.request.FundManagerFundInfoRequest;
import la.niub.abcapi.servicecompre.model.response.*;
import la.niub.abcapi.servicecompre.service.IFundManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fundManager")
public class FundManagerController {

    private static final Logger logger = LoggerFactory.getLogger(FundManagerController.class);

    @Autowired
    private IFundManagerService iFundManagerService;

    @GetMapping("basicInfo")
    public Response basicInfo(@RequestParam("peo_uni_code") Long peo_uni_code) {
        if (StringUtil.isEmpty(peo_uni_code)) {
            logger.error("传入的参数peo_uni_code为null");
            return new Response(408, "传入的参数peo_uni_code为null");
        }

        try {
            FundManagerInfoModel fundManagerBasicInfoModel = iFundManagerService.selectFundManagerInfoByPeoUniCode(peo_uni_code);
            return new Response(fundManagerBasicInfoModel);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金经理的基本信息失败:" + e.getMessage());
            return new Response(500, "获取基金经理的基本信息失败:" + e.getMessage());
        }
    }

    @GetMapping("starFundManagerList")
    public Response starFundManagerList() {
        try {
            List<HotPeoListResponse> hotPeoListResponseList = iFundManagerService.selectFundManagerStarList();
            return new Response(hotPeoListResponseList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取明星基金经理列表失败:" + e.getMessage());
            return new Response(500, "获取明星基金经理列表失败:" + e.getMessage());
        }
    }

    @GetMapping("fundManagerIndex")
    public Response fundManagerIndex(@RequestParam("peo_uni_code") Long peo_uni_code) {
        if (StringUtil.isEmpty(peo_uni_code)) {
            logger.error("传入的参数peo_uni_code为null");
            return new Response(408, "传入的参数peo_uni_code为null");
        }

        try {
            FundManagerIndexResponse fundManagerIndexResponse = iFundManagerService.selectFundManagerIndexByPeoUniCode(peo_uni_code);
            return new Response(fundManagerIndexResponse);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金经理指数失败：" + e.getMessage());
            return new Response(500, "获取基金经理指数失败：" + e.getMessage());
        }
    }

    @GetMapping("positionDistribution")
    public Response positionDistribution(@RequestParam("peo_uni_code") Long peo_uni_code) {
        if (StringUtil.isEmpty(peo_uni_code)) {
            logger.error("传入的参数peo_uni_code为null");
            return new Response(408, "传入的参数peo_uni_code为null");
        }

        try {
            FundManagerPositionDistributionResponse fundManagerPositionDistributionResponse = iFundManagerService.selectFundManagerPositionDistributionByPeoUniCode(peo_uni_code);
            return new Response(fundManagerPositionDistributionResponse);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取仓位分布失败：" + e.getMessage());
            return new Response(500, "获取仓位分布失败：" + e.getMessage());
        }
    }

    @GetMapping("fundTenStock")
    public Response tenStock(@RequestParam("sec_uni_code") Long sec_uni_code) {
        if (StringUtil.isEmpty(sec_uni_code)) {
            logger.error("传入的参数sec_uni_code为null");
            return new Response(408, "传入的参数sec_uni_code为null");
        }

        try {
            List<FundManagerStockModel> fundManagerStockModelList = iFundManagerService.selectFundManagerStockBySecUniCode(sec_uni_code);
            return new Response(fundManagerStockModelList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取失败基金十大股票失败：" + e.getMessage());
            return new Response(500, "获取失败基金十大股票失败：" + e.getMessage());
        }
    }

    @GetMapping("fundInfo")
    public Response fundInfo(FundManagerFundInfoRequest request) {
        try {
            if (StringUtil.isEmpty(request.getPeo_uni_code())) {
                logger.error("传入的参数peo_uni_code为null");
                return new Response(408, "传入的参数peo_uni_code为null");
            }

            List<FundManagerFundInfoResponse> result = iFundManagerService.getFundInfo(request);

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金经理管理的基金列表失败：" + e.getMessage());
            return new Response(500, "获取基金经理管理的基金列表失败：" + e.getMessage());
        }
    }

    @GetMapping("fundChart")
    public Response fundChart(FundManagerFundChartRequest request) {
        try {
            if (StringUtil.isEmpty(request.getPeo_uni_code()) || StringUtil.isEmpty(request.getSec_uni_codes())) {
                logger.error("传入的参数peo_uni_code/sec_uni_codes为null");
                return new Response(408, "传入的参数peo_uni_code/sec_uni_codes为null");
            }

            Map<String, Object> result = iFundManagerService.getFundChart(request);

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金经理管理的基金图表失败：" + e.getMessage());
            return new Response(500, "获取基金经理管理的基金图表失败：" + e.getMessage());
        }
    }

    @GetMapping("competitiveStrengthAnalysis")
    public Response competitiveStrengthAnalysis(@RequestParam("peo_uni_code") Long peo_uni_code) {
        try {
            if (StringUtil.isEmpty(peo_uni_code)) {
                logger.error("传入的参数peo_uni_code为null");
                return new Response(408, "传入的参数peo_uni_code为null");
            }

            FundManagerCompetitiveStrengthAnalysisResponse result = iFundManagerService.getCompetitiveStrengthAnalysis(peo_uni_code);
            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金经理管理的竞争力分析失败：" + e.getMessage());
            return new Response(500, "获取基金经理管理的竞争力分析失败：" + e.getMessage());
        }
    }
}
