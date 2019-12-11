package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.IAnalystService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 关注股票
 */
@RestController
@RequestMapping(path = "/payStock")
public class PayStockController {
    private static Logger logger = LogManager.getLogger(PayStockController.class);

    @Autowired
    IAnalystService analystService;

    //关注股票图表
    @GetMapping("payStockTable")
    public Response getAnalystPayStockChart(HttpServletRequest request){
        Map paraMap=transParamMap(request);
        logger.info("前台传入参数为："+paraMap.toString());
        if(paraMap.containsKey("time") && !paraMap.get("time").toString().isEmpty()){
            paraMap.put("startDate",paraMap.get("time"));
            paraMap.put("endDate",(Integer.parseInt(paraMap.get("time").toString())+1)+"");
        }
        if(paraMap.containsKey("limit")){
            long offset=0;
            long limit=Long.parseLong(paraMap.get("limit").toString());
            if(paraMap.containsKey("page_num")){
                offset=(Long.parseLong(paraMap.get("page_num").toString())-1) *limit;
            }
            paraMap.put("limits","LIMIT "+limit+" OFFSET "+offset);
        }
        if(paraMap.containsKey("order_field")){
             String order_field=paraMap.get("order_field").toString();
             if(order_field.equals("industry")){
                 order_field="industry_id";
             }
             String order_type=" desc";
             if(paraMap.containsKey("order_type")){
                 order_type=" "+paraMap.get("order_type").toString();
             }
            paraMap.put("order_by","ORDER BY "+ order_field+ order_type);
        }else{
            paraMap.put("order_by","ORDER BY time desc");
        }
        return new Response(analystService.getAnalystPayStockChart(paraMap));
    }

    //关注股票折线图
    @GetMapping("payStockline")
    public Response getAnalystPayStocLine(HttpServletRequest request){
        Map paraMap=transParamMap(request);
        logger.info("前台传入参数为："+paraMap.toString());
        if(paraMap.containsKey("time") && !paraMap.get("time").toString().isEmpty()){
            paraMap.put("startDate",paraMap.get("time"));
            paraMap.put("endDate",(Integer.parseInt(paraMap.get("time").toString())+1)+"");
        }
        if(paraMap.containsKey("order_field")){
            String order_field=paraMap.get("order_field").toString()+" ";
            String order_type="desc";
            if(paraMap.containsKey("order_type")){
                order_type=paraMap.get("order_type").toString();
            }
            paraMap.put("order_by","ORDER BY "+ order_field+ order_type);
        }else{
            paraMap.put("order_by","ORDER BY time asc");
        }
        return new Response(analystService.getAnalystStockPayLine(paraMap));
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
