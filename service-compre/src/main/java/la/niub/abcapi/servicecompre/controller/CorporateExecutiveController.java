package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.model.CorporateExecutiveBasicInfoModel;
import la.niub.abcapi.servicecompre.model.CorporateExecutiveSameComModel;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.ICorporateExecutiveService;
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
@RequestMapping("/corporateExecutive")
public class CorporateExecutiveController {

    private static final Logger logger = LoggerFactory.getLogger(CorporateExecutiveController.class);

    @Autowired
    private ICorporateExecutiveService iCorporateExecutiveService;

    @GetMapping("/basicInfo")
    public Response basicInfo(@RequestParam("peo_uni_code") Long peo_uni_code) {
        if (StringUtil.isEmpty(peo_uni_code)) {
            logger.error("传入的peo_uni_code为null");
            return new Response(408, "传入的peo_uni_code为null");
        }

        try {
            CorporateExecutiveBasicInfoModel corporateExecutiveBasicInfoModel = iCorporateExecutiveService.selectCorporateExecutiveBasicInfoByPeoUniCode(peo_uni_code);
            return new Response(corporateExecutiveBasicInfoModel);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取公司高管信息失败：" + e.getMessage());
            return new Response(500, "获取公司高管信息失败：" + e.getMessage());
        }
    }

    @GetMapping("/sameComCorporateExecutiveList")
    public Response sameComCorporateExecutiveList(@RequestParam("peo_uni_code") Long peo_uni_code) {
        if (StringUtil.isEmpty(peo_uni_code)) {
            logger.error("传入的peo_uni_code为null");
            return new Response(408, "传入的peo_uni_code为null");
        }

        try {
            List<CorporateExecutiveSameComModel> corporateExecutiveSameComModelList = iCorporateExecutiveService.selectCorporateExecutiveSameComList(peo_uni_code);
            return new Response(corporateExecutiveSameComModelList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取同公司高管信息失败：" + e.getMessage());
            return new Response(500, "获取同公司高管信息失败：" + e.getMessage());
        }
    }

    @GetMapping("peoComList")
    public Response getPeoComList(@RequestParam("peo_uni_code") Long peo_uni_code) {
        if (StringUtil.isEmpty(peo_uni_code)) {
            logger.error("传入的peo_uni_code为null");
            return new Response(408, "传入的peo_uni_code为null");
        }

        try {
            List<Map<String, Object>> peoComList = iCorporateExecutiveService.getPeoComList(peo_uni_code);
            return new Response(peoComList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取该高管所在公司列表失败：" + e.getMessage());
            return new Response(500, "获取该高管所在公司列表失败：" + e.getMessage());
        }
    }

    @GetMapping("peoNewsList")
    public Response getPeoNewsList(@RequestParam("peo_uni_code") Long peo_uni_code, Integer limit) {
        if (StringUtil.isEmpty(peo_uni_code)) {
            logger.error("传入的peo_uni_code为null");
            return new Response(408, "传入的peo_uni_code为null");
        }

        try {
            List<Map<String, Object>> peoNewsList = iCorporateExecutiveService.getPeoNews(peo_uni_code, limit);
            return new Response(peoNewsList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取人物新闻失败：" + e.getMessage());
            return new Response(500, "获取人物新闻失败：" + e.getMessage());
        }
    }

    @GetMapping("emolumentInfo")
    public Response getEmolumentInfo(@RequestParam("com_uni_code") Long com_uni_code) {
        if (StringUtil.isEmpty(com_uni_code)) {
            logger.error("传入的com_uni_code为null");
            return new Response(408, "传入的com_uni_code为null");
        }

        try {
            List<Map<String, Object>> emolumentInfoList = iCorporateExecutiveService.getEmolumentInfo(com_uni_code);
            return new Response(emolumentInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取公司高管的薪酬信息失败：" + e.getMessage());
            return new Response(500, "获取公司高管的薪酬信息失败：" + e.getMessage());
        }
    }

    @GetMapping("stockHoldInfo")
    public Response getStockHoldInfo(@RequestParam("com_uni_code") Long com_uni_code) {
        if (StringUtil.isEmpty(com_uni_code)) {
            logger.error("传入的com_uni_code为null");
            return new Response(408, "传入的com_uni_code为null");
        }

        try {
            List<Map<String, Object>> stockHoldInfoList = iCorporateExecutiveService.getStockHoldInfo(com_uni_code);
            return new Response(stockHoldInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取公司的持股信息失败：" + e.getMessage());
            return new Response(500, "获取公司的持股信息失败：" + e.getMessage());
        }
    }

    @GetMapping("achieveAndRisk")
    public Response getAchieveAndRiskData(@RequestParam("peo_uni_code") Long peo_uni_code, @RequestParam("com_uni_code") Long com_uni_code) {
        if (StringUtil.isEmpty(peo_uni_code) || StringUtil.isEmpty(com_uni_code)) {
            logger.error("传入的peo_uni_code或者com_uni-code为null");
            return new Response(408, "传入的peo_uni_code或者com_uni-code为null");
        }

        try {
            Map<String, Object> achieveAndRisk = iCorporateExecutiveService.getAchieveAndRiskData(peo_uni_code, com_uni_code);
            return new Response(achieveAndRisk);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取另类数据失败：" + e.getMessage());
            return new Response(500, "获取另类数据失败：" + e.getMessage());
        }
    }

    @GetMapping("heatIndex")
    public Response getHeatIndex(@RequestParam("peo_uni_code") Long peo_uni_code, @RequestParam("com_uni_code") Long com_uni_code, @RequestParam("days") Integer days) {
        if (StringUtil.isEmpty(peo_uni_code) || StringUtil.isEmpty(days) || StringUtil.isEmpty(com_uni_code)) {
            logger.error("传入的peo_uni_code, com_uni_code或者days为null");
            return new Response(408, "传入的peo_uni_code, com_uni_code或者days为null");
        }

        try {
            Object heatIndex = iCorporateExecutiveService.getHeatIndex(peo_uni_code, com_uni_code, days);
            return new Response(heatIndex);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取指数失败：" + e.getMessage());
            return new Response(500, "获取指数失败：" + e.getMessage());
        }
    }

    @GetMapping("peoWeiboList")
    public Response getPeoWeiboList(@RequestParam("peo_uni_code") Long peo_uni_code) {
        if (StringUtil.isEmpty(peo_uni_code)) {
            logger.error("传入的peo_uni_code为null");
            return new Response(408, "传入的peo_uni_code为null");
        }

        try {
            List<Map<String, Object>> peoWeiboList = iCorporateExecutiveService.getPeoWeiboList(peo_uni_code);
            return new Response(peoWeiboList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取该该高管的微博失败：" + e.getMessage());
            return new Response(500, "获取该该高管的微博失败：" + e.getMessage());
        }
    }

    @GetMapping("investInfo")
    public Response getInvestInfo(@RequestParam("peo_uni_code") Long peo_uni_code,
                                  @RequestParam(value = "page_num", defaultValue = "1") Integer page_num,
                                  @RequestParam(value = "limit", defaultValue = "6") Integer limit,
                                  @RequestParam(value = "order_field", required = false) String order_field,
                                  @RequestParam(value = "order_type", required = false) String order_type) {
        try {
            if (StringUtil.isEmpty(peo_uni_code)) {
                logger.error("传入的peo_uni_code为null");
                return new Response(408, "传入的peo_uni_code为null");
            }

            Map<String, Object> result = iCorporateExecutiveService.getInvestInfo(peo_uni_code, page_num, limit, order_field, order_type);

            if (result == null || result.isEmpty()) {
                logger.warn("高管:" + peo_uni_code + "的投资信息无数据");
                return new Response(result, "高管:" + peo_uni_code + "的投资信息无数据");
            }

            return new Response(result);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取高管:" + peo_uni_code + "的投资信息失败：" + e.getMessage());
            return new Response(500, "获取高管:" + peo_uni_code + "的投资信息失败：" + e.getMessage());
        }
    }

    @GetMapping("businessRole")
    public Response getBusinessRole(@RequestParam("peo_uni_code") Long peo_uni_code,
                                  @RequestParam(value = "page_num", defaultValue = "1") Integer page_num,
                                  @RequestParam(value = "limit", defaultValue = "6") Integer limit,
                                  @RequestParam(value = "order_field", required = false) String order_field,
                                  @RequestParam(value = "order_type", required = false) String order_type) {
        try {
            if (StringUtil.isEmpty(peo_uni_code)) {
                logger.error("传入的peo_uni_code为null");
                return new Response(408, "传入的peo_uni_code为null");
            }

            Map<String, Object> result = iCorporateExecutiveService.getBusinessRole(peo_uni_code, page_num, limit, order_field, order_type);

            if (result == null || result.isEmpty()) {
                logger.warn("高管:" + peo_uni_code + "的商业角色无数据");
                return new Response(result, "高管:" + peo_uni_code + "的商业角色无数据");
            }

            return new Response(result);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取高管:" + peo_uni_code + "的商业角色失败：" + e.getMessage());
            return new Response(500, "获取高管:" + peo_uni_code + "的商业角色失败：" + e.getMessage());
        }
    }
}
