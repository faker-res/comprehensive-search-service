package la.niub.abcapi.servicecompre.controller;

import com.alibaba.fastjson.JSON;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.model.RepostTypeModel;
import la.niub.abcapi.servicecompre.model.bo.LatestReportBO;
import la.niub.abcapi.servicecompre.model.bo.ReportCountBO;
import la.niub.abcapi.servicecompre.model.bo.ResearchIndustryBasicInfoBO;
import la.niub.abcapi.servicecompre.model.bo.StarAnalystInfoBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystReportStocksBO;
import la.niub.abcapi.servicecompre.model.request.BrokerHeatMapRequest;
import la.niub.abcapi.servicecompre.model.request.BrokerNewsRequest;
import la.niub.abcapi.servicecompre.model.request.BrokerOverviewRequest;
import la.niub.abcapi.servicecompre.model.response.BrokerHeatMapItemResponse;
import la.niub.abcapi.servicecompre.model.response.BrokerNewsResponse;
import la.niub.abcapi.servicecompre.model.response.BrokerOverviewResponse;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.IBrokerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
@RequestMapping("/broker")
public class BrokerController {

    private final static Logger logger = LogManager.getLogger(BrokerController.class);

    @Autowired
    IBrokerService brokerService;

    @GetMapping("/overview")
    public Response overview(BrokerOverviewRequest brokerOverviewRequest) throws Throwable {
//        logger.info("BrokerController action overview request : " + JSON.toJSONString(brokerOverviewRequest));
        BrokerOverviewResponse brokerOverviewResponse = brokerService.overview(brokerOverviewRequest);
//        logger.info("BrokerController action overview response : " + JSON.toJSONString(brokerOverviewResponse));
        return new Response(brokerOverviewResponse);
    }

    @GetMapping("/heatMap")
    public Response heatMap(BrokerHeatMapRequest brokerHeatMapRequest) throws Throwable {
//        logger.info("BrokerController action heatMap request : " + JSON.toJSONString(brokerHeatMapRequest));
        List<BrokerHeatMapItemResponse> brokerHeatMapResponse = brokerService.heatMap(brokerHeatMapRequest);
//        logger.info("BrokerController action heatMap response : " + JSON.toJSONString(brokerHeatMapResponse));
        return new Response(brokerHeatMapResponse);
    }


    @GetMapping("/news")
    public Response news(BrokerNewsRequest brokerNewsRequest) throws Throwable {
//        logger.info("BrokerController action news request : " + JSON.toJSONString(brokerNewsRequest));
        BrokerNewsResponse brokerNewsResponse = brokerService.news(brokerNewsRequest);
//        logger.info("BrokerController action news response : " + JSON.toJSONString(brokerNewsResponse));
        return new Response(brokerNewsResponse);
    }

    @GetMapping("/activities")
    public Response getActivities(@RequestParam(value = "time", required = false) String time, @RequestParam("org_uni_code") Integer org_uni_code) {
        try {
            if (StringUtil.isEmpty(org_uni_code)) {
                logger.error("传入的参数org_uni_code为null");
                return new Response(408, "传入的参数org_uni_code为null");
            }

            Date date;
            if (StringUtil.isEmpty(time)) {
                date = new Date();
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                date = sdf.parse(time);
            }

            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(date);
            //设置为1号,当前日期既为本月第一天
            startCalendar.set(Calendar.DAY_OF_MONTH, 1);
            //将小时至0
            startCalendar.set(Calendar.HOUR_OF_DAY, 0);
            //将分钟至0
            startCalendar.set(Calendar.MINUTE, 0);
            //将秒至0
            startCalendar.set(Calendar.SECOND,0);
            //将毫秒至0
            startCalendar.set(Calendar.MILLISECOND, 0);
            Date startDate = startCalendar.getTime();

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(date);
            //设置为当月最后一天
            endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            //将小时至23
            endCalendar.set(Calendar.HOUR_OF_DAY, 23);
            //将分钟至59
            endCalendar.set(Calendar.MINUTE, 59);
            //将秒至59
            endCalendar.set(Calendar.SECOND,59);
            //将毫秒至999
            endCalendar.set(Calendar.MILLISECOND, 999);
            Date endDate = endCalendar.getTime();

            Map<String, Object> result = brokerService.getActivities(startDate, endDate, org_uni_code);

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取券商" + time +"内活动日期失败:" + e.getMessage());
            return new Response(500, "获取券商" + time +"内活动日期失败:" + e.getMessage());
        }

    }

    @GetMapping("/industryList")
    public Response industryList(String broker_name) {
        try {
            List<ResearchIndustryBasicInfoBO> researchIndustryBasicInfoResponsesList = brokerService.researchIndustryList(broker_name);
            return new Response(researchIndustryBasicInfoResponsesList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取研究领域列表失败：" + e.getMessage());
            return new Response(500, "获取研究领域列表失败：" + e.getMessage());
        }
    }

    @GetMapping("/starAnalyst")
    public Response starAnalyst(@RequestParam("org_uni_code") Long org_uni_code, String indu_name) {
        if (StringUtil.isEmpty(org_uni_code)) {
            logger.error("传入的org_uni_code为null");
            return new Response(408, "传入的org_uni_code为null");
        }

        try {
            List<StarAnalystInfoBO> starAnalystInfoBOList = brokerService.starAnalystList(org_uni_code, indu_name);
            return new Response(starAnalystInfoBOList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取明星分析师失败：" + e.getMessage());
            return new Response(500, "获取明星分析师失败：" + e.getMessage());
        }
    }

    @GetMapping("/analystStocks")
    public Response analystStocks(@RequestParam("peo_uni_code") String peo_uni_code) {
        if (StringUtil.isEmpty(peo_uni_code)) {
            return new Response(408, "传入的peo_uni_code为null");
        }

        try {
            List<CardAnalystReportStocksBO> cardAnalystReportStocksBOList = brokerService.analystLatestResearchStocks(peo_uni_code);
            return new Response(cardAnalystReportStocksBOList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取分析师研究的最新股票失败：" + e.getMessage());
            return new Response(500, "获取分析师研究的最新股票失败：" + e.getMessage());
        }
    }

    @GetMapping("reportType")
    public Response reportType() {
        try {
            List<RepostTypeModel> reportTypeList = brokerService.reportType();
            return new Response(reportTypeList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取研报分类失败：" + e.getMessage());
            return new Response(500, "获取研报分类失败：" + e.getMessage());
        }
    }

    @GetMapping("reportCount")
    public Response reportCount(@RequestParam("org_uni_code") Long org_uni_code) {
        try {
            List<ReportCountBO> reportCountBOList = brokerService.reportCount(org_uni_code);
            return new Response(reportCountBOList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取各个类型的研报数量失败：" + e.getMessage());
            return new Response(500, "获取各个类型的研报数量：" + e.getMessage());
        }
    }

    @GetMapping("latestReport")
    public Response latestReport(@RequestParam("type_id") String type_id, @RequestParam("org_uni_code") Long org_uni_code) {
        if (StringUtil.isEmpty(type_id) || StringUtil.isEmpty(org_uni_code)) {
            logger.error("传入的参数type_id或者org_uni_code为null");
            return new Response(408, "传入的参数type_id或者org_uni_code为null");
        }

        try {
            List<LatestReportBO> latestReportBOList = brokerService.latestReport(org_uni_code, type_id);
            return new Response(latestReportBOList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取最新研报失败：" + e.getMessage());
            return new Response(500, "获取最新研报失败：" + e.getMessage());
        }
    }

    @GetMapping("/otherStarAnalyst")
    public Response otherStarAnalyst(@RequestParam("org_uni_code") Long org_uni_code, String type_id) {
        if (StringUtil.isEmpty(org_uni_code)) {
            logger.error("传入的org_uni_code为null");
            return new Response(408, "传入的org_uni_code为null");
        }

        try {
            List<StarAnalystInfoBO> starAnalystInfoBOList = brokerService.otherStarAnalystList(org_uni_code, type_id);
            return new Response(starAnalystInfoBOList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取其他明星分析师列表失败：" +e.getMessage());
            return new Response(500, "获取其他明星分析师列表失败：" +e.getMessage());
        }
    }


    @GetMapping("/analystFlow")
    public Response getAnalystFlow(@RequestParam("org_uni_code") BigInteger org_uni_code) {
        try {
            if (StringUtil.isEmpty(org_uni_code)) {
                logger.error("传入的参数org_uni_code为null");
                return new Response(408, "传入的参数org_uni_code为null");
            }

            Map<String, Object> result = brokerService.getAnalystFlow(org_uni_code);

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取券商分析师4个月内的入职和离职人数失败：" + e.getMessage());
            return new Response(500, "获取券商分析师4个月内的入职和离职人数失败:" + e.getMessage());
        }
    }
//
//    @GetMapping("/otherResearch")
//    public Response otherResearch() {
//        return null;
//    }
//
//
//
//    @GetMapping("/analystTurnover")
//    public Response analystTurnover() {
//        return null;
//    }
//
//    @GetMapping("/eventCalendar")
//    public Response eventCalendar() {
//        return null;
//    }

    /**
     * 行业个股
     */
    @GetMapping("induStock")
    public Response getInduStock(@RequestParam("indu_name") String indu_name,
                                 @RequestParam("type") String type,
                                 @RequestParam("limit") Integer limit,
                                 @RequestParam("broker_name") String broker_name) {
        if (StringUtil.isEmpty(broker_name) || StringUtil.isEmpty(limit) || StringUtil.isEmpty("type")) {
            logger.error("传入的broker_name, limit或者type为null");
            return new Response(408, "传入的broker_name, limit或者type为null");
        }

        try {
            List<Map<String, Object>> induStockList = brokerService.getInduStock(indu_name, type, broker_name, limit);
            return new Response(induStockList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取行业个股失败：" + e.getMessage());
            return new Response(500, "获取行业个股失败：" + e.getMessage());
        }
    }

    @GetMapping("industryHonor")
    public Response industryHonor(String broker_name,long org_uni_code) {
        try{
            Map<String,Object> honorMap=new HashMap<>();
            List<ResearchIndustryBasicInfoBO> researchIndustryBasicInfoBOs=brokerService.researchIndustryList(broker_name);
            if(researchIndustryBasicInfoBOs!=null){
                ExecutorService executor = Executors.newFixedThreadPool(50);
                CountDownLatch countDownLatch = new CountDownLatch(researchIndustryBasicInfoBOs.size());
                for(ResearchIndustryBasicInfoBO researchIndustryBasicInfoBO:researchIndustryBasicInfoBOs){
                    executor.submit(new Runnable() {
                        public void run() {
                            try{
                                List<StarAnalystInfoBO> starAnalystInfoBOList = brokerService.starAnalystList2(org_uni_code, researchIndustryBasicInfoBO.getIndu_name());
                                honorMap.put(researchIndustryBasicInfoBO.getIndu_name(),starAnalystInfoBOList);
                                countDownLatch.countDown();
                            }catch (Exception e){
                                logger.error(e.toString());
                            }
                        }
                    });
                }
                executor.shutdown();
                countDownLatch.await();
            }
            return new Response(honorMap);
        }catch (Exception e){
            logger.error(e.toString());
            return new Response(500, "获取行业分析师失败：" + e.getMessage());
        }
    }
}
