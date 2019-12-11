package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.model.SecBasicInfoModel;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockBO;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.ICardService;
import la.niub.abcapi.servicecompre.service.IIndustryService;
import la.niub.abcapi.servicecompre.service.IStockService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 上市公司
 */
@RestController
@RequestMapping(path = "/industry")
public class IndustryController {

    private static final Logger logger = LoggerFactory.getLogger(IndustryController.class);

    @Autowired
    IIndustryService industryService;

    @Autowired
    ICardService cardService;

    @Autowired
    IStockService stockService;

    /**
     * 公司行情等
     * @param sec_uni_code
     * @param stock_code
     * @return
     * @throws ServiceException
     */
    @GetMapping
    Response<CardStockBO> get(Long sec_uni_code,String stock_code) throws ServiceException {
        if (sec_uni_code == null && StringUtils.isNotEmpty(stock_code)){
            SecBasicInfoModel secBasicInfoModel = stockService.buildRecordByStockCode(stock_code);
            if (secBasicInfoModel != null){
                sec_uni_code = secBasicInfoModel.getSec_uni_code();
            }
        }

        CardStockBO cardStockBO = cardService.buildStockCard(sec_uni_code.intValue());

        return new Response(cardStockBO);
    }

    /**
     * 另类数据
     * @param keyword
     * @param days
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/heat_index")
    Response<Object> getHeatIndex(String keyword,Integer days) throws ServiceException {
        if(days == null){
            days = 3;
        }
        return new Response(industryService.buildHeatIndex(keyword,days));
    }

    /**
     * 行业快讯
     * @param sec_uni_code
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/industry_news")
    Response<Object> getIndustryNews(String sec_uni_code) throws ServiceException {
        return new Response(industryService.buildIndustryNews(Long.valueOf(sec_uni_code)));
    }


    /**
     * 搜索标签
     * @param keyword
     * @param limit
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/search_tag")
    Response<Object> getSearchTag(String keyword,Integer limit) throws ServiceException {
        if(limit == null){
            limit = 10;
        }
        return new Response(industryService.buildSearchTag(keyword,limit));
    }

    /**
     * 行业分析
     * @param sec_uni_code
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/industry_info")
    Response<Object> getIndustryInfo(String sec_uni_code) throws ServiceException {
        return new Response(industryService.buildIndustryInfo(Long.valueOf(sec_uni_code)));
    }

    /**
     * 个股分析师排行
     * @param stock_code
     * @param order_field
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/analyst_info")
    Response<Object> getAnalystInfo(@RequestParam(value = "stock_code") String stock_code,
                                    @RequestParam(value = "order_field", defaultValue = "") String order_field) throws ServiceException {
        return new Response(industryService.buildAnalystInfo(stock_code,order_field));
    }

    /**
     * 投资评级
     * @param stock_code
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/analyst_rate")
    Response<Object> getAnalystRate(String stock_code) throws ServiceException {
        return new Response(industryService.buildAnalystRate(stock_code));
    }

    /**
     * 预测误差
     * @param stock_code
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/analyst_eps")
    Response<Object> getAnalystEps(String stock_code) throws ServiceException {
        return new Response(industryService.buildAnalystEps(stock_code));
    }

    /**
     * 基金信息
     * @param stock_code
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/fund_info")
    Response<Object> getFundInfo(
                                 @RequestParam(value = "stock_code") String stock_code,
                                 @RequestParam(value = "order_field", defaultValue = "") String order_field,
                                 @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                 @RequestParam(value = "limit", defaultValue = "0") Integer limit) throws ServiceException {
        return new Response(industryService.buildFundInfo(stock_code, order_field, offset, limit));
    }

    /**
     * 主营构成
     * @param com_uni_code
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/major_business")
    Response<Object> getMajorBusiness(String com_uni_code) throws ServiceException {
        return new Response(industryService.buildMajorBusiness(Long.valueOf(com_uni_code)));
    }

    /**
     * 三大财务报表
     * @param com_uni_code
     * @param type
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/finance_overview")
    Response<Object> getFinanceOverview(String com_uni_code, String type) throws ServiceException {
        return new Response(industryService.buildFinanceOverview(Long.valueOf(com_uni_code), type));
    }

    /**
     * 核心财务指标
     * @param com_uni_code
     * @return
     * @throws ServiceException
     */
    @GetMapping(value = "/key_value_overview")
    Response<Object> getKeyValueOverview(String com_uni_code) throws ServiceException {
        return new Response(industryService.buildKeyValueOverview(Long.valueOf(com_uni_code)));
    }

    /**
     * 财务能力
     * @param com_uni_code
     * @return
     * @throws ServiceException
     */
    @GetMapping(value = "/ability_overview")
    Response<Object> getAbilityOverview(String com_uni_code) throws ServiceException {
        return new Response(industryService.buildAbilityOverview(Long.valueOf(com_uni_code)));
    }

    /**
     * 十大流通股东
     * @param com_uni_code
     * @return
     * @throws ServiceException
     */
    @GetMapping(value = "/circulation_shareholders")
    Response<Object> getCirculationShareholders(String com_uni_code) throws ServiceException {
        return new Response(industryService.buildCirculationShareholders(Long.valueOf(com_uni_code)));
    }

    /**
     * 公司现任高管
     * @param com_uni_code
     * @return
     * @throws ServiceException
     */
    @GetMapping(value = "/company_manager")
    Response<Object> getCompanyManager(String com_uni_code) throws ServiceException {
        return new Response(industryService.buildCompanyManager(Long.valueOf(com_uni_code)));
    }

    /**
     * 机构持股
     * @param sec_uni_code
     * @return
     * @throws ServiceException
     */
    @GetMapping(value = "/agency_hold_detail")
    Response<Object> getAgencyHoldDetail(String sec_uni_code) throws ServiceException {
        return new Response(industryService.buildAgencyHoldDetail(Long.valueOf(sec_uni_code)));
    }

    /**
     * 商标信息
     * @param com_uni_code
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/trademark")
    Response<Object> getTrademark(@RequestParam(value = "com_uni_code") String com_uni_code,
                                  @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                  @RequestParam(value = "limit", defaultValue = "6") Integer limit) throws ServiceException {
        return new Response(industryService.buildTrademark(Long.valueOf(com_uni_code),offset,limit));
    }

    /**
     * 专利信息
     * @param com_uni_code
     * @return
     * @throws ServiceException
     */
    @GetMapping(value = "/patent")
    Response<Object> getPatent(@RequestParam(value = "com_uni_code") String com_uni_code,
                               @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                               @RequestParam(value = "limit", defaultValue = "3") Integer limit) throws ServiceException {
        return new Response(industryService.buildPatent(Long.valueOf(com_uni_code),offset,limit));
    }

    /**
     * 法律诉讼
     * @param com_uni_code
     * @return
     * @throws ServiceException
     */
    @GetMapping(value = "/lawsuit")
    Response<Object> buildLawsuit(@RequestParam(value = "com_uni_code") String com_uni_code,
                                  @RequestParam(value = "case_type", defaultValue = "") String case_type,
                                  @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                  @RequestParam(value = "limit", defaultValue = "5") Integer limit) throws ServiceException {
        return new Response(industryService.buildLawsuit(Long.valueOf(com_uni_code),case_type,offset,limit));
    }

    /**
     * 个股列表三级页面
     *
     */
    @GetMapping("/stockInfoList")
    public Response getStockInfoList(@RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit,
                                     @RequestParam("order") String order,
                                     @RequestParam("prior") String prior,
                                     String indu_name,
                                     String type,
                                     String abc_code) {
        if (offset == null || StringUtil.isEmpty(limit)) {
            logger.error("传入的offset或者limit为null");
            return new Response(408, "传入的offset或者limit为null");
        }

        int isAsc = 1;
        if (order.equals("DESC")) {
            isAsc = -1;
        }

        try {
            Map<String, Object> stockInfo = industryService.getStockInfoList(abc_code, prior, isAsc, indu_name, type, offset, limit);
            return new Response(stockInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取个股三级页面列表失败：" + e.getMessage());
            return new Response(500, "获取个股三级页面列表失败：" + e.getMessage());
        }
    }
}
