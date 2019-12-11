package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;
import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystCompetitionBO;
import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystDetailBO;
import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystHeatBO;
import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystSummaryBO;
import la.niub.abcapi.servicecompre.model.request.analyst.AnalystCompetitionRequest;
import la.niub.abcapi.servicecompre.model.request.analyst.AnalystDetailRequest;
import la.niub.abcapi.servicecompre.model.request.analyst.AnalystDynamicRequest;
import la.niub.abcapi.servicecompre.model.request.analyst.AnalystSameFieldRequest;
import la.niub.abcapi.servicecompre.model.request.analyst.AnalystStarRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.analyst.AnalystCompetitionResponse;
import la.niub.abcapi.servicecompre.model.response.message.MessageResponse;
import la.niub.abcapi.servicecompre.service.IAnalystService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 分析师
 */
@RestController
@RequestMapping(path = "/analyst")
public class AnalystController {

    private static Logger logger = LogManager.getLogger(AnalystController.class);

    @Autowired
    IAnalystService analystService;

    /**
     * 详情
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping
    Response<AnalystDetailBO> get(AnalystDetailRequest params) throws ServiceException {
        AnalystDetailBO analystDetailBO = analystService.getAnalystDetail(params.getPeo_uni_code());

        return new Response(analystDetailBO);
    }

    /**
     * 分析师动态
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping("/dynamic")
    Response<List<MessageResponse>> dynamic(AnalystDynamicRequest params) throws ServiceException {
        List<MessageResponse> messageResponseList = analystService.getDynamic(params.getPeo_uni_code(),params.getLimit());

        return new Response(messageResponseList);
    }

    /**
     * 行业热力
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping("/heat")
    Response<List<AnalystHeatBO>> heat(AnalystDynamicRequest params) throws ServiceException {
        List<AnalystHeatBO> analystHeatBOList = analystService.getHeat(params.getPeo_uni_code(),params.getLimit());

        return new Response(analystHeatBOList);
    }

    /**
     * 同领域分析师
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping("/samefield")
    Response<List<AnalystSummaryBO>> sameField(AnalystSameFieldRequest params) throws ServiceException {
        List<AnalystSummaryBO> analystSummaryBOList = analystService.getSameFieldAnalyst(params.getPeo_uni_code(),params.getLimit());

        return new Response(analystSummaryBOList);
    }

    /**
     * 明星分析师
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping("/star")
    Response<List<AnalystSummaryBO>> star(AnalystStarRequest params) throws ServiceException {
        List<AnalystSummaryBO> analystSummaryBOList = analystService.getStarAnalyst(params.getPeo_uni_code(),params.getLimit());

        return new Response(analystSummaryBOList);
    }

    /**
     * 团队竞争力分析
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping("/competition")
    Response<AnalystCompetitionResponse> competition(AnalystCompetitionRequest params) throws ServiceException {
        AnalystCompetitionResponse analystCompetitionResponse = new AnalystCompetitionResponse();
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Integer year = params.getYear() != null ?
                (params.getYear().toUpperCase().equals("ALL") ? -1 : Integer.valueOf(params.getYear()))
                : now.get(Calendar.YEAR);

        FundPeriodEnum period = FundPeriodEnum.valueOf(params.getPeriod());
        if (period == null){
            period = FundPeriodEnum.M1;
        }

        Date startTime = null;
        Date endTime = null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now.getTime());
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        if (year.equals(calendar.get(Calendar.YEAR))){
            calendar.add(period.getField(),period.getAmount());
            startTime = calendar.getTime();
            endTime = now.getTime();
            year = now.get(Calendar.YEAR);
        }else if (year.equals(-1)){
            endTime = now.getTime();
            year = now.get(Calendar.YEAR);
        }else{
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.DAY_OF_YEAR,1);
            startTime = calendar.getTime();
            calendar.add(Calendar.YEAR,1);
            endTime = calendar.getTime();
            year = year;
        }

        List<AnalystCompetitionBO> analystCompetitionBOList = analystService.getCompetition(params.getPeo_uni_code(),startTime,endTime,year,params.getLimit());
        analystCompetitionResponse.setAnalyst(analystCompetitionBOList);

        params.setLimit(500);
        List<AnalystCompetitionBO> otherAnalystCompetitionBOList = analystService.getOtherCompetition(params.getPeo_uni_code(),startTime,endTime,year,params.getLimit());
        analystCompetitionResponse.setOther_analyst(otherAnalystCompetitionBOList);

        return new Response(analystCompetitionResponse);
    }

    //三级列表页面
    //机构列表
    @GetMapping("orgSnameList")
    public Response orgSnameList(String keyword, String userId, String token) {
        try {
            List<Map<String, Object>> orgSnameList = analystService.getOrgSnameList(keyword, userId, token);
            return new Response(orgSnameList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取机构列表失败：" + e.getMessage());
            return new Response(500, "获取机构列表失败：" + e.getMessage());
        }
    }

    //分析师列表
    @GetMapping("analystList")
    public Response getAnalystList(String indu_name,
                                   String org_sname,
                                   String name,
                                   String prior,
                                   String spellPrefix,
                                   @RequestParam("limit") Integer limit,
                                   @RequestParam("offset") Integer offset) {
        if (StringUtil.isEmpty(limit) || offset == null || StringUtil.isEmpty(prior)) {
            logger.error("传入的limit或者offset或者prior为null");
            return new Response(408, "传入的limit或者offset或者prior为null");
        }

        if (StringUtil.isEmpty(indu_name)) {
            indu_name = null;
        }

        try {
            Map<String, Object> analystList = analystService.getAnalystList(indu_name, org_sname, name, spellPrefix, prior, offset, limit);
            return new Response(analystList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取分析师列表失败：" + e.getMessage());
            return new Response(500, "获取分析师列表失败：" + e.getMessage());
        }
    }
}