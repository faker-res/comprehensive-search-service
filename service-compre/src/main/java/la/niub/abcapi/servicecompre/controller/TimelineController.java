package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.model.bo.line.TimelineBO;
import la.niub.abcapi.servicecompre.model.bo.line.TimelineCompanyBO;
import la.niub.abcapi.servicecompre.model.request.TimelineRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.ICompanyManagerService;
import la.niub.abcapi.servicecompre.service.ILineService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 分时线
 */
@RestController
@RequestMapping(path = "/timeline")
public class TimelineController {

    private static Logger logger = LogManager.getLogger(TimelineController.class);

    @Autowired
    ICompanyManagerService companyManagerService;

    @Autowired
    ILineService lineService;

    /**
     * 获取
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping
    Response<TimelineBO> get(TimelineRequest params) throws ServiceException {
        TimelineBO timelineBO = new TimelineBO();
        Map<String,TimelineCompanyBO> companyBOMap = new TreeMap<>();

        //默认日期
//        String startTime = StringUtils.defaultString(params.getStart_time(),"2017-10-20");
//        String graphType = "t";
//        CardStockSharePriceChangeBO cardStockSharePriceChangeBO = companyManagerService.buildSharePriceChange(params.getStock_code(),
//                TimeUtil.parseDateStr(startTime,"yyyy-MM-dd"),graphType);
//
//
//        Map<String, CardStockSharePriceChangeCompanyBO> companyMap =  cardStockSharePriceChangeBO.getCompany();
//        for (Map.Entry<String,CardStockSharePriceChangeCompanyBO> entry : companyMap.entrySet()){
//            CardStockSharePriceChangeCompanyBO item = entry.getValue();
//
//            TimelineCompanyBO timelineCompanyBO = new TimelineCompanyBO();
//            timelineCompanyBO.setTrade_date(item.getTrade_date());
//            timelineCompanyBO.setTurn(item.getTurn());
//            timelineCompanyBO.setDiffer_range(item.getDiffer_range());
//            timelineCompanyBO.setAmount(item.getAmount());
//            timelineCompanyBO.setAvg_price(item.getAvg_price());
//            timelineCompanyBO.setDiffer(item.getDiffer());
//            timelineCompanyBO.setOpen(item.getOpen());
//            timelineCompanyBO.setVolume(item.getVolume());
//            timelineCompanyBO.setAmount_unit(item.getAmount_unit());
//            timelineCompanyBO.setAvg_price_unit(StringUtils.defaultString(item.getAvg_price_unit(),"元"));
//            timelineCompanyBO.setDiffer_unit(item.getDiffer_unit());
//            timelineCompanyBO.setOpen_unit(item.getOpen_unit());
//            timelineCompanyBO.setVolume_unit(item.getVolume_unit());
//
//            companyBOMap.put(entry.getKey(),timelineCompanyBO);
//        }
//        timelineBO.setCompany(companyBOMap);


        Date startTime = new Date();
        if (StringUtils.isNotEmpty(params.getStart_time())){
            startTime = TimeUtil.parseDateStr(params.getStart_time(),"HHmm");
        }else{
            //默认时间
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.setTime(startTime);
            calendar.add(Calendar.HOUR, -2);
            startTime = calendar.getTime();
        }

        List<TimelineCompanyBO> timelineCompanyBOList = lineService.getTimeline(startTime,params.getStock_code());
        for (TimelineCompanyBO item : timelineCompanyBOList){
            String timestampStr = TimeUtil.toString(item.getTrade_date(),"yyyy-MM-dd HH:mm:ss");
            companyBOMap.put(timestampStr,item);
        }
        timelineBO.setCompany(companyBOMap);

        return new Response(timelineBO);
    }
}
