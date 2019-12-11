package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.model.OrgBasicInfoModel;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyDetailBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyHolderBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyManagerBO;
import la.niub.abcapi.servicecompre.model.request.*;
import la.niub.abcapi.servicecompre.model.request.fund.FundCompanyDetailRequest;
import la.niub.abcapi.servicecompre.model.request.fund.FundCompanyHolderRequest;
import la.niub.abcapi.servicecompre.model.response.FundCompanyKpiResponse;
import la.niub.abcapi.servicecompre.model.response.FundCompanyLawsuitResponse;
import la.niub.abcapi.servicecompre.model.response.FundCompanyNewsResponse;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.fund.FundCompanyHolderResponse;
import la.niub.abcapi.servicecompre.service.IFundCompanyService;
import la.niub.abcapi.servicecompre.service.IFundService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 基金公司
 */
@RestController
@RequestMapping(path = "/fundcompany")
public class FundCompanyController {

    private static Logger logger = LogManager.getLogger(FundCompanyController.class);

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    IFundService fundService;

    @Autowired
    IFundCompanyService fundCompanyService;

    /**
     * 基本信息
     * @param params
     * @return
     * @throws CustomException
     */
    @GetMapping
    Response<FundCompanyDetailBO> get(FundCompanyDetailRequest params) throws CustomException {
        if (params.getCom_uni_code() == null && params.getName() != null){
            OrgBasicInfoModel orgBasicInfoModel = fundCompanyService.getCompanyByOrgName(params.getName());
            if (orgBasicInfoModel == null){
                return new Response<>(new FundCompanyDetailBO());
            }
            params.setCom_uni_code(orgBasicInfoModel.getCom_uni_code());
        }

        FundCompanyDetailBO fundCompanyDetailBO = fundService.getCompanyDetail(params.getCom_uni_code());

        return new Response<>(fundCompanyDetailBO);
    }


    /**
     * 投资方/被投资方
     * @param params
     * @return
     * @throws CustomException
     */
    @GetMapping("/stockholder")
    Response<FundCompanyHolderResponse> holder(FundCompanyHolderRequest params) throws CustomException {
        if (params.getCom_uni_code() == null && params.getName() != null){
            OrgBasicInfoModel orgBasicInfoModel = fundCompanyService.getCompanyByOrgName(params.getName());
            if (orgBasicInfoModel == null){
                return new Response<>(new FundCompanyHolderResponse());
            }
            params.setCom_uni_code(orgBasicInfoModel.getCom_uni_code());
        }

        List<FundCompanyHolderBO> fundCompanyHolderBOList = fundService.getHolder(params.getCom_uni_code(),params.getLimit());

        FundCompanyHolderResponse fundCompanyHolderResponse = new FundCompanyHolderResponse();
        fundCompanyHolderResponse.setHolder(fundCompanyHolderBOList);

        return new Response<>(fundCompanyHolderResponse);
    }



    @GetMapping("/kpi")
    Response kpi(FundCompanyKpiRequest fundCompanyKpiRequest) throws Throwable {
        FundCompanyKpiResponse fundCompanyKpiResponse = fundCompanyService.kpi(fundCompanyKpiRequest);
        return new Response(fundCompanyKpiResponse);
    }

    @GetMapping("/manager")
    Response manager(FundCompanyManagerRequest fundCompanyManagerRequest) throws Throwable {
        FundCompanyManagerBO fundCompanyManage = fundCompanyService.manager(fundCompanyManagerRequest);
        return new Response(fundCompanyManage);
    }

    Response alternativeData() throws Throwable {
        return new Response();
    }

    @GetMapping("/lawsuit")
    Response lawsuit(FundCompanyLawsuitRequest fundCompanyLawsuitRequest) throws Throwable {
        FundCompanyLawsuitResponse fundCompanyLawsuitResponse = fundCompanyService.lawsuit(fundCompanyLawsuitRequest);
        return new Response(fundCompanyLawsuitResponse);
    }

    @GetMapping("/news")
    Response news(FundCompanyNewsRequest fundCompanyNewsRequest) throws Throwable {
        FundCompanyNewsResponse fundCompanyNewsResponse = fundCompanyService.news(fundCompanyNewsRequest);
        return new Response(fundCompanyNewsResponse);
    }

    @GetMapping("productCategory")
    public Response productCategory(Long com_uni_code) {
        try {
            if (StringUtil.isEmpty(com_uni_code)) {
                logger.error("com_uni_code为null");
                return new Response(408, "com_uni_code为null");
            }

            List<Map<String, String>> result = fundCompanyService.getProductCategory(com_uni_code);

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金公司的产品信息分类失败：" + e.getMessage());
            return new Response(500, "获取基金公司的产品信息分类失败:" + e.getMessage());
        }
    }

    @GetMapping("productInfo")
    public Response productInfo(FundCompanyProductInfoRequest request) {
        try {
            if (StringUtil.isEmpty(request.getCom_uni_code())) {
                logger.error("com_uni_code为null");
                return new Response(408, "com_uni_code为null");
            }

            Map<String, Object> result = fundCompanyService.getProductInfo(request);

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金公司的产品信息失败：" + e.getMessage());
            return new Response(500, "获取基金公司的产品信息失败:" + e.getMessage());
        }
    }

    @GetMapping("achievementsCategory")
    public Response achievementsCategory(Long com_uni_code) {
        try {
            if (StringUtil.isEmpty(com_uni_code)) {
                logger.error("com_uni_code为null");
                return new Response(408, "com_uni_code为null");
            }

            List<Map<String, String>> result = fundCompanyService.getAchievementsCategory(com_uni_code);

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金公司的公司业绩分类失败：" + e.getMessage());
            return new Response(500, "获取基金公司的公司业绩分类失败:" + e.getMessage());
        }
    }

    @GetMapping("achievementsInfo")
    public Response achievementsInfo(FundCompanyAchievementsInfoRequest request) {
        try {
            if (StringUtil.isEmpty(request.getCom_uni_code())) {
                logger.error("com_uni_code为null");
                return new Response(408, "com_uni_code为null");
            }

            Map<String, Object> result = fundCompanyService.getAchievementsInfo(request);

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金公司的公司业绩列表失败：" + e.getMessage());
            return new Response(500, "获取基金公司的公司业绩列表失败:" + e.getMessage());
        }
    }

    @GetMapping("achievementsChart")
    public Response achievementsChart(FundCompanyAchievementsChartRequest request) {
        try {
            if (StringUtil.isEmpty(request.getCom_uni_codes())) {
                logger.error("com_uni_codes为null");
                return new Response(408, "com_uni_codes为null");
            }

            Map<String, Object> result = fundCompanyService.getAchievementsChart(request);
            if (result == null) {
                logger.error("基金公司的com_uni_code获取不到name");
                return new Response(result, "基金公司的com_uni_code获取不到name");
            }

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金公司的公司业绩图表失败：" + e.getMessage());
            return new Response(500, "获取基金公司的公司业绩图表失败:" + e.getMessage());
        }
    }

    @GetMapping("achievementsTime")
    public Response achievementsTime(@RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit) {
        try {
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);

            List<Map<String, String>> result = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                Map<String, String> map = new HashMap<>();
                int year = currentYear - i;
                map.put("time", year + "");
                map.put("text", year + "");
                result.add(map);
            }

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金公司的公司业绩年份失败：" + e.getMessage());
            return new Response(500, "获取基金公司的公司业绩年份失败:" + e.getMessage());
        }
    }
}
