package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.code.SystemEnumCodeConfig;
import la.niub.abcapi.servicecompre.config.enums.LineTypeEnum;
import la.niub.abcapi.servicecompre.model.bo.line.KlineBO;
import la.niub.abcapi.servicecompre.model.bo.line.KlineCompanyBO;
import la.niub.abcapi.servicecompre.model.request.KlineRequest;
import la.niub.abcapi.servicecompre.model.response.ErrorResponse;
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
 * K线
 */
@RestController
@RequestMapping(path = "/kline")
public class KlineController {

    private static Logger logger = LogManager.getLogger(KlineController.class);

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
    Response<KlineBO> get(KlineRequest params) throws ServiceException {
        logger.info("kline method come in!!");
        String keyPattern = "yyyy-MM-dd HH:mm:ss";
        Map<String,KlineCompanyBO> companyBOMap = new TreeMap<>();
        KlineBO klineBO = new KlineBO();

        logger.info("kline using new code");
        //默认时间
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(new Date());
        calendar.set(Calendar.MILLISECOND,0);

        LineTypeEnum lineTypeEnum;
        switch (params.getGraph_type()){
            case "d"://日
                calendar.add(Calendar.MONTH, -3);
                lineTypeEnum = LineTypeEnum.DAY;
                keyPattern = "yyyy-MM-dd 00:00:00";
                break;
            case "w"://周
//                calendar.add(Calendar.YEAR, -2);
                calendar.add(Calendar.MONTH, -6);
                lineTypeEnum = LineTypeEnum.WEEK;
                keyPattern = "yyyy-MM-dd 00:00:00";
                break;
            case "m"://月
                calendar.add(Calendar.YEAR, -5);
//                calendar.add(Calendar.MONTH, -6);
                lineTypeEnum = LineTypeEnum.MONTH;
                keyPattern = "yyyy-MM-dd 00:00:00";
                break;
            case "q"://季度
                calendar.add(Calendar.YEAR, -10);
                lineTypeEnum = LineTypeEnum.QUARTER;
                keyPattern = "yyyy-MM-dd 00:00:00";
                break;
            case "hy"://半年
                calendar.add(Calendar.HOUR, -2);
                lineTypeEnum = LineTypeEnum.HALFYEAR;
                keyPattern = "yyyy-MM-dd 00:00:00";
                break;
            case "y"://年
                calendar.add(Calendar.HOUR, -2);
                lineTypeEnum = LineTypeEnum.YEAR;
                keyPattern = "yyyy-MM-dd 00:00:00";
                break;
            case "1"://1分钟
                calendar.add(Calendar.HOUR, -4);
                lineTypeEnum = LineTypeEnum.MIN1;
                break;
            case "5"://5分钟
                calendar.add(Calendar.HOUR, -4);
                lineTypeEnum = LineTypeEnum.MIN5;
                break;
            case "15"://15分钟
                calendar.add(Calendar.HOUR, -5);
                lineTypeEnum = LineTypeEnum.MIN15;
                break;
            case "30"://30分钟
//                calendar.add(Calendar.HOUR, -5);
                calendar.add(Calendar.DAY_OF_YEAR, -8);
                lineTypeEnum = LineTypeEnum.MIN30;
                break;
            case "60"://60分钟
//                calendar.add(Calendar.HOUR, -5);
                calendar.add(Calendar.DAY_OF_YEAR, -16);
                lineTypeEnum = LineTypeEnum.MIN60;
                break;
            default:
                return new ErrorResponse<>(SystemEnumCodeConfig.UNSUPPORTED_TYPE);
        }

        Date startTime = StringUtils.isEmpty(params.getStart_time()) ? calendar.getTime() : TimeUtil.parseDateStr(params.getStart_time(),"yyyy-MM-dd");
        List<KlineCompanyBO> klineList = lineService.getKline(startTime,params.getStock_code(),lineTypeEnum);
        for (KlineCompanyBO item : klineList){
            String timestampStr = TimeUtil.toString(item.getTrade_date(),keyPattern);
            companyBOMap.put(timestampStr,item);
        }

        klineBO.setCompany(companyBOMap);
        return new Response(klineBO);
    }
}
