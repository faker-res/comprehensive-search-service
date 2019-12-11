package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.enums.DifferEnum;
import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;
import la.niub.abcapi.servicecompre.config.enums.PepbEnum;
import la.niub.abcapi.servicecompre.model.IndexBasicInfoModel;
import la.niub.abcapi.servicecompre.model.bo.fund.FundManagerBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyDetailBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyDifferBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyFundTrendBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyHeatBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyIndexBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyStockHeatBO;
import la.niub.abcapi.servicecompre.model.request.publiccompany.PublicCompanyDetailRequest;
import la.niub.abcapi.servicecompre.model.request.publiccompany.PublicCompanyDifferTopRequest;
import la.niub.abcapi.servicecompre.model.request.publiccompany.PublicCompanyFundAnalystRequest;
import la.niub.abcapi.servicecompre.model.request.publiccompany.PublicCompanyFundFlowRequest;
import la.niub.abcapi.servicecompre.model.request.publiccompany.PublicCompanyFundTrendRequest;
import la.niub.abcapi.servicecompre.model.request.publiccompany.PublicCompanyHeatRequest;
import la.niub.abcapi.servicecompre.model.request.publiccompany.PublicCompanyHeavilyFundRequest;
import la.niub.abcapi.servicecompre.model.request.publiccompany.PublicCompanyIndexRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.publiccompany.PublicCompanyFundFlowItemResponse;
import la.niub.abcapi.servicecompre.model.response.publiccompany.PublicCompanyFundTrendResponse;
import la.niub.abcapi.servicecompre.model.response.publiccompany.PublicCompanyHeavilyFundItemResponse;
import la.niub.abcapi.servicecompre.model.response.publiccompany.PublicCompanyIndexResponse;
import la.niub.abcapi.servicecompre.service.IAnalystService;
import la.niub.abcapi.servicecompre.service.IHeatService;
import la.niub.abcapi.servicecompre.service.IPublicCompanyService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 行业
 */
@RestController
@RequestMapping(path = "/publiccompany")
public class PublicCompanyController {

    private static Logger logger = LogManager.getLogger(PublicCompanyController.class);

    @Autowired
    IPublicCompanyService publicCompanyService;

    @Autowired
    IHeatService heatService;

    @Autowired
    IAnalystService analystService;

    /**
     * 基本信息
     * @param params
     * @return
     */
    @GetMapping
    Response<PublicCompanyDetailBO> get(PublicCompanyDetailRequest params){
        if (params.getIndex_uni_code() == null && params.getName() != null){
            IndexBasicInfoModel indexBasicInfoModel = publicCompanyService.getIndustryByName(params.getName());
            if (indexBasicInfoModel == null){
                return new Response<>(new PublicCompanyDetailBO());
            }
            params.setIndex_uni_code(indexBasicInfoModel.getIndex_uni_code());
        }

        PublicCompanyDetailBO publicCompanyResponse = publicCompanyService.getDetail(params.getIndex_uni_code());

        return new Response<>(publicCompanyResponse);
    }

    /**
     * 行业指数
     * @param params
     * @return
     */
    @GetMapping("/index")
    Response<PublicCompanyIndexResponse> get(PublicCompanyIndexRequest params){
        if (params.getIndex_uni_code() == null && params.getName() != null){
            IndexBasicInfoModel indexBasicInfoModel = publicCompanyService.getIndustryByName(params.getName());
            if (indexBasicInfoModel == null){
                return new Response<>(new PublicCompanyIndexResponse());
            }
            params.setIndex_uni_code(indexBasicInfoModel.getIndex_uni_code());
        }

        Date startTime = null;
        if (StringUtils.isNotEmpty(params.getStart_time())){
            startTime = TimeUtil.parseDateStr(params.getStart_time(),"yyyy-MM-dd");
        }
        if (startTime == null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.MILLISECOND,0);
            calendar.add(Calendar.MONTH,-6);
            startTime = calendar.getTime();
        }

        List<PublicCompanyIndexBO> publicCompanyIndexBOList = publicCompanyService.getIndex(params.getIndex_uni_code(),startTime);

        PublicCompanyIndexResponse publicCompanyIndexResponse = new PublicCompanyIndexResponse();
        publicCompanyIndexResponse.setList(publicCompanyIndexBOList);
        return new Response<>(publicCompanyIndexResponse);
    }

    /**
     * 涨/跌幅榜
     * @param params
     * @return
     */
    @GetMapping("/differtop")
    Response<List<PublicCompanyDifferBO>> get(PublicCompanyDifferTopRequest params) {
        if (params.getIndex_uni_code() == null && params.getName() != null){
            IndexBasicInfoModel indexBasicInfoModel = publicCompanyService.getIndustryByName(params.getName());
            if (indexBasicInfoModel == null){
                return new Response<>(new ArrayList<>());
            }
            params.setIndex_uni_code(indexBasicInfoModel.getIndex_uni_code());
        }

        DifferEnum differEnum = DifferEnum.RISK;
        if (params.getType() != null){
            differEnum = DifferEnum.valueOf(params.getType().toUpperCase());
            if (differEnum == null){
                return new Response<>(new ArrayList<>());
            }
        }

        try {
            List<PublicCompanyDifferBO> publicCompanyDifferBOList = publicCompanyService.getDiffer(params.getIndex_uni_code(),differEnum,params.getLimit());
            return new Response<>(publicCompanyDifferBOList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(500, "获取失败：" + e.getMessage());
        }

    }

    @GetMapping("/heavilyFund")
    Response heavilyFund(PublicCompanyHeavilyFundRequest publicCompanyHeavilyFundRequest) {
        List<PublicCompanyHeavilyFundItemResponse>  heavilyFundList = publicCompanyService.heavilyFund(publicCompanyHeavilyFundRequest);
        return new Response(heavilyFundList);
    }

    @GetMapping("/fundFlow")
    Response fundFlow(PublicCompanyFundFlowRequest publicCompanyFundFlowRequest) throws Throwable {
        List<PublicCompanyFundFlowItemResponse> publicCompanyFundFlowResponse = publicCompanyService.fundFlow(publicCompanyFundFlowRequest);
        return new Response(publicCompanyFundFlowResponse);
    }

    /**
     * 全行业热力图
     * @param params
     * @return
     */
    @GetMapping("/heat")
    Response<List<PublicCompanyHeatBO>> heat(PublicCompanyHeatRequest params){
        PepbEnum pepb = null;
        if (params.getType() != null){
            pepb = PepbEnum.valueOf(params.getType());
        }

        List<PublicCompanyHeatBO> publicCompanyHeatBOList = null;
        if (pepb != null){
            publicCompanyHeatBOList = heatService.getPublicCompanyHeatByPepb(pepb,params.getLimit());
        }else{
            FundPeriodEnum period = FundPeriodEnum.valueOf(params.getPeriod());
            if (period == null){
                period = FundPeriodEnum.D1;
            }
            publicCompanyHeatBOList = heatService.getPublicCompanyHeatByPeriod(period,params.getLimit());
        }

        return new Response<>(publicCompanyHeatBOList);
    }

    /**
     * 行业成分股热力图
     * @param params
     * @return
     */
    @GetMapping("/stock/heat")
    Response<List<PublicCompanyStockHeatBO>> stockHeat(PublicCompanyHeatRequest params){
        FundPeriodEnum period = FundPeriodEnum.valueOf(params.getPeriod());
        if (period == null){
            period = FundPeriodEnum.D1;
        }

        List<PublicCompanyStockHeatBO> publicCompanyStockHeatBOList = heatService.getPublicCompanyStockHeat(params.getIndex_uni_code(),period,params.getLimit());

        return new Response<>(publicCompanyStockHeatBOList);
    }

    /**
     * 持仓基金走势
     * @param params
     * @return
     */
    @GetMapping("/fund/trend")
    Response<PublicCompanyFundTrendResponse> fundTrend(PublicCompanyFundTrendRequest params){
        PublicCompanyFundTrendResponse publicCompanyFundTrendResponse = new PublicCompanyFundTrendResponse();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        List<PublicCompanyFundTrendBO> publicCompanyFundTrendBOList = new ArrayList<>();
        PublicCompanyFundTrendBO publicCompanyFundTrendBO = new PublicCompanyFundTrendBO();
        publicCompanyFundTrendBO.setTime(calendar.getTime());
        publicCompanyFundTrendBO.setAmount(new BigDecimal(3000000000L));
        publicCompanyFundTrendBOList.add(publicCompanyFundTrendBO);
        publicCompanyFundTrendBO = new PublicCompanyFundTrendBO();
        calendar.add(Calendar.DAY_OF_YEAR,-6);
        publicCompanyFundTrendBO.setTime(calendar.getTime());
        publicCompanyFundTrendBO.setAmount(new BigDecimal(60099099));
        publicCompanyFundTrendBOList.add(publicCompanyFundTrendBO);
        publicCompanyFundTrendResponse.setMarket_value(publicCompanyFundTrendBOList);

        publicCompanyFundTrendBOList = new ArrayList<>();
        publicCompanyFundTrendBO = new PublicCompanyFundTrendBO();
        calendar.add(Calendar.DAY_OF_YEAR,3);
        publicCompanyFundTrendBO.setTime(calendar.getTime());
        publicCompanyFundTrendBO.setAmount(new BigDecimal(500));
        publicCompanyFundTrendBOList.add(publicCompanyFundTrendBO);
        publicCompanyFundTrendBO = new PublicCompanyFundTrendBO();
        calendar.add(Calendar.DAY_OF_YEAR,-11);
        publicCompanyFundTrendBO.setTime(calendar.getTime());
        publicCompanyFundTrendBO.setAmount(new BigDecimal(600));
        publicCompanyFundTrendBOList.add(publicCompanyFundTrendBO);
        publicCompanyFundTrendResponse.setAmount(publicCompanyFundTrendBOList);

        return new Response<>(publicCompanyFundTrendResponse);
    }

    /**
     * 行业明星分析师
     * @param params
     * @return
     */
    @GetMapping("/staranalyst")
    Response<List<FundManagerBO>> starAnalyst(PublicCompanyFundAnalystRequest params){
        List<FundManagerBO> fundManagerBOList = analystService.getStarAnalystOfPublicCompany(params.getIndex_uni_code(),params.getLimit());

        return new Response<>(fundManagerBOList);
    }
}
