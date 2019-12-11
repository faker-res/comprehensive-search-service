package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.IAnalystService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 预测能力
 */
@RestController
@RequestMapping(path = "/predictive")
public class PredictiveController {
    private static Logger logger = LogManager.getLogger(AnalystController.class);

    @Autowired
    IAnalystService analystService;

    //分析师预测头部返回年份
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
            logger.error("获取分析师预测年份失败：" + e.getMessage());
            return new Response(500, "获取分析师预测年份失败:" + e.getMessage());
        }
    }

    //分析师能力预测头部关注公司数据
    @GetMapping("stockInfo")
    public Response stockInfo(HttpServletRequest request){
        try{
            Map paraMap=transParamMap(request);
            logger.info("前台传入参数为："+paraMap.toString());
            if(paraMap.containsKey("time") && !paraMap.get("time").toString().isEmpty()){
                paraMap.put("startDate",paraMap.get("time"));
                paraMap.put("endDate",(Integer.parseInt(paraMap.get("time").toString())+1)+"");
            }
            List<Map> lists= analystService.getAnalystStockCode(paraMap);
            return new Response(lists);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取分析师关注股票信息失败：" + e.getMessage());
            return new Response(500, "获取分析师关注股票信息失败:" + e.getMessage());
        }
    }

    //分析师能力预测图表数据
    @GetMapping("predictiveChart")
    public Response findDutyInfo(HttpServletRequest request){
        try{
            Response response=new Response();
            Map paramMap=transParamMap(request);
            logger.info("前台传入参数为："+paramMap.toString());
            if(paramMap.containsKey("time") && !paramMap.get("time").toString().isEmpty()){
                paramMap.put("startDate",paramMap.get("time"));
                paramMap.put("endDate",(Integer.parseInt(paramMap.get("time").toString())+1)+"");
            }
            response.setData(analystService.getAnalystPredictive(paramMap));
            return response;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取分析师图表数据失败：" + e.getMessage());
            return new Response(500, "获取分析师图表数据失败:" + e.getMessage());
        }
    }

    private Map transParamMap(HttpServletRequest request) {
        Map paramMap = new HashMap();
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paramName = (String) enums.nextElement();
            String paramValue = request.getParameter(paramName);
            if(!paramValue.isEmpty()){
                //形成键值对应的map
                paramMap.put(paramName, paramValue);
            }
        }
        return paramMap;
    }
}
