package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.model.bo.ReportRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.IRCompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/rcompany")
public class RCompanyController {
    private static Logger logger = LogManager.getLogger(RCompanyController.class);
    @Autowired
    IRCompanyService rCompanyService;

    //获取公司介绍
    @GetMapping("/introduce/brief")
    Response brief(HttpServletRequest request)  {
        Map<String,String> params ;
        try {
            params=transParamMap(request);
            logger.info("******************:"+params.toString()+":*****************");
            if(!params.isEmpty()){
                return new Response(rCompanyService.brief(params));
            }else{
                return new Response(408, "请求参数为null");
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取公司简介失败:" + e.getMessage());
        }
    }

    //获取公司董事与高管
    @GetMapping("/introduce/directors")
    Response directors(HttpServletRequest request)  {
        Map<String,String> params ;
        try {
            params=transParamMap(request);
            logger.info("******************:"+params.toString()+":*****************");
            if(!params.isEmpty()){
                return new Response(rCompanyService.directors(params));
            }else{
                return new Response(408, "请求参数为null");
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取公司董事与高管:" + e.getMessage());
        }
    }

    //获取公司行业
    @GetMapping("/datum/industry")
    Response industry(HttpServletRequest request)  {
        Map<String,String> params ;
        try {
            params=transParamMap(request);
            logger.info("******************:"+params.toString()+":*****************");
            if(!params.isEmpty()){
                return new Response(rCompanyService.industry(params));
            }else{
                return new Response(408, "请求参数为null");
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取公司行业:" + e.getMessage());
        }
    }

    //获取公司所属指数
    @GetMapping("/datum/indexmark")
    Response indexmark(HttpServletRequest request)  {
        Map<String,String> params ;
        try {
            params=transParamMap(request);
            logger.info("******************:"+params.toString()+":*****************");
            if(!params.isEmpty()){
                return new Response(rCompanyService.indexmark(params));
            }else{
                return new Response(408, "请求参数为null");
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取公司所属指数:" + e.getMessage());
        }
    }

    //获取公司所属概念
    @GetMapping("/datum/notion")
    Response notion(HttpServletRequest request)  {
        Map<String,String> params ;
        try {
            params=transParamMap(request);
            logger.info("******************:"+params.toString()+":*****************");
            if(!params.isEmpty()){
                return new Response(rCompanyService.notion(params));
            }else{
                return new Response(408, "请求参数为null");
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取公司所属概念:" + e.getMessage());
        }
    }

    //获取公司同概念个股
    @GetMapping("/datum/notionDetail")
    Response notionDetail(HttpServletRequest request)  {
        Map<String,String> params ;
        try {
            params=transParamMap(request);
            logger.info("******************:"+params.toString()+":*****************");
            if(!params.isEmpty()){
                return new Response(rCompanyService.notionDetail(params));
            }else{
                return new Response(408, "请求参数为null");
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取公司同概念个股:" + e.getMessage());
        }
    }

    //获取公司股东十大股东明细
    @GetMapping("/partner/detail")
    Response detail(HttpServletRequest request)  {
        Map<String,String> params ;
        try {
            params=transParamMap(request);
            logger.info("******************:"+params.toString()+":*****************");
            if(!params.isEmpty()){
                return new Response(rCompanyService.partner_detail(params));
            }else{
                return new Response(408, "请求参数为null");
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取公司股东十大股东明细:" + e.getMessage());
        }
    }


    //获取公司股东十大流通股东明细
    @GetMapping("/partner/circulate")
    Response circulate(HttpServletRequest request)  {
        Map<String,String> params ;
        try {
            params=transParamMap(request);
            logger.info("******************:"+params.toString()+":*****************");
            if(!params.isEmpty()){
                return new Response(rCompanyService.partner_circulate(params));
            }else{
                return new Response(408, "请求参数为null");
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取公司股东十大流通股东明细:" + e.getMessage());
        }
    }

    //获取公司交易行情统计分析数据
    @GetMapping("/trade/markettotal")
    Response markettotal(HttpServletRequest request)  {
        Map<String,String> params ;
        try {
            params=transParamMap(request);
            logger.info("******************:"+params.toString()+":*****************");
            if(!params.isEmpty()){
                return new Response(rCompanyService.markettotal(params));
            }else{
                return new Response(408, "请求参数为null");
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取公司交易行情统计分析数据:" + e.getMessage());
        }
    }

    //获取公司财务摘要
    @GetMapping("/finance/summary")
    Response summary(HttpServletRequest request)  {
        Map<String,String> params ;
        try {
            params=transParamMap(request);
            logger.info("******************:"+params.toString()+":*****************");
            if(!params.isEmpty()){
                return new Response(rCompanyService.summary(params));
            }else{
                return new Response(408, "请求参数为null");
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取公司财务摘要数据失败:" + e.getMessage());
        }
    }

    //获取公司财务资产负债
    @GetMapping("/finance/liabilities")
    Response liabilities(HttpServletRequest request)  {
        Map<String,String> params ;
        try {
            params=transParamMap(request);
            logger.info("******************:"+params.toString()+":*****************");
            if(!params.isEmpty()){
                return new Response(rCompanyService.liabilities(params));
            }else{
                return new Response(408, "请求参数为null");
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取公司财务资产负债数据失败:" + e.getMessage());
        }
    }

    //获取公司行业研报
    @GetMapping("/finance/industryReport")
    Response<Object> industryReport(ReportRequest request)  {
        Map<String,String> params ;
        try {
             return new Response(rCompanyService.industryReport(request));
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取获取公司行业研报失败:" + e.getMessage());
        }
    }

    //获取公司财务业绩预告
    @GetMapping("/finance/foreshow")
    Response foreshow(HttpServletRequest request)  {
        Map<String,String> params ;
        try {
            params=transParamMap(request);
            logger.info("******************:"+params.toString()+":*****************");
            if(!params.isEmpty()){
                return new Response(rCompanyService.foreshow(params));
            }else{
                return new Response(408, "请求参数为null");
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取公司财务业绩预告失败:" + e.getMessage());
        }
    }

    private Map transParamMap(HttpServletRequest request) {
        Map paramMap = new HashMap();
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paramName = (String) enums.nextElement();
            Object paramValue = request.getParameter(paramName);
            //形成键值对应的map
            paramMap.put(paramName, paramValue);
        }
        return paramMap;
    }


}
