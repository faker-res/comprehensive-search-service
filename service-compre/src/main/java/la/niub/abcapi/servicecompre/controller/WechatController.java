package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.enums.WechatPeriodEnum;
import la.niub.abcapi.servicecompre.model.WechatPublicModel;
import la.niub.abcapi.servicecompre.model.bo.wechat.WechatArticleTypeBO;
import la.niub.abcapi.servicecompre.model.bo.wechat.WechatBO;
import la.niub.abcapi.servicecompre.model.bo.wechat.WechatHotBO;
import la.niub.abcapi.servicecompre.model.bo.wechat.WechatHottagBO;
import la.niub.abcapi.servicecompre.model.bo.wechat.WechatIndexBO;
import la.niub.abcapi.servicecompre.model.bo.wechat.WechatSourceBO;
import la.niub.abcapi.servicecompre.model.request.wechat.*;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.message.ArticleResponse;
import la.niub.abcapi.servicecompre.model.response.wechat.WechatOriArticleListItem;
import la.niub.abcapi.servicecompre.model.response.wechat.WechatTagIndexItemResponse;
import la.niub.abcapi.servicecompre.service.IWechatService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 公众号
 */
@RestController
@RequestMapping(path = "/wechat")
public class WechatController {

    private static Logger logger = LogManager.getLogger(WechatController.class);

    @Autowired
    IWechatService wechatService;

    /**
     * 公众号基本信息
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping
    Response<WechatBO> get(WechatRequest params) throws ServiceException {

        WechatBO wechatBO = wechatService.getDetail(params.getCode());

        return new Response(wechatBO);
    }

    /**
     * 公众号文章标签列表
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping("/article-tag")
    Response<List<String>> articleTag(WechatArticleTagRequest params) throws ServiceException {

        List<String> tags = wechatService.getArticleTag(params.getCode());

//        List<String> tags = new ArrayList<>();
//        tags.add("房地产");
//        tags.add("增值税");
//        tags.add("原油");
//        tags.add("个人所得税");

        return new Response(tags);
    }

    /**
     * 公众号文章来源列表
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping("/article-source")
    Response<List<WechatSourceBO>> articleSource(WechatArticleSourceRequest params) throws ServiceException {

//        List<WechatSourceBO> wechatSourceBOList = new ArrayList<>();
//        WechatSourceBO wechatSourceBO = new WechatSourceBO();
//        wechatSourceBO.setId(5456);
//        wechatSourceBO.setName("广元看经济");
//        wechatSourceBOList.add(wechatSourceBO);
//        wechatSourceBO = new WechatSourceBO();
//        wechatSourceBO.setId(45465);
//        wechatSourceBO.setName("叶檀财经");
//        wechatSourceBOList.add(wechatSourceBO);


        List<WechatSourceBO> wechatSourceBOList = wechatService.articleSource(params);
        return new Response(wechatSourceBOList);
    }

    /**
     * 文章列表
     * @param params
     * @return
     * @throws Throwable
     */
    @GetMapping("/article")
    Response<ArticleResponse> article(WechatArticleRequest params) throws Throwable {
        ArticleResponse articleResponse = wechatService.article(params);
        return new Response(articleResponse);
    }

    /**
     * 文章标签指数
     * @param params
     * @return
     * @throws Throwable
     */
    @GetMapping("/tagindex")
    Response<Map<Integer, WechatTagIndexItemResponse>> tagIndex(WechatTagIndexRequest params) throws Throwable {
        Map<Integer, WechatTagIndexItemResponse> result = wechatService.tagIndex(params);
        return new Response(result);
    }

    /**
     * 运营指标
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping("/index")
    Response<WechatIndexBO> index(WechatIndexRequest params) throws ServiceException {
        if (params.getCode() == null && StringUtils.isNotEmpty(params.getName())){
            WechatPublicModel wechatPublicModel = wechatService.getByName(params.getName());
            if (wechatPublicModel != null){
                params.setCode(wechatPublicModel.getId());
            }
        }

        Date startTime = null;
        if (StringUtils.isNotEmpty(params.getStart_time())){
            startTime = TimeUtil.parseDateStr(params.getStart_time(),"yyyy-MM-dd");
        }
        if (startTime == null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.MILLISECOND,0);
            calendar.add(Calendar.MONTH,-3);
            startTime = calendar.getTime();
        }

        WechatIndexBO wechatIndexBO = wechatService.getIndex(params.getCode(),startTime);

//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        wechatIndexBO.getTotal().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        wechatIndexBO.getTop().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        calendar.add(Calendar.DAY_OF_YEAR,-1);
//        wechatIndexBO.getTotal().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        wechatIndexBO.getTop().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        calendar.add(Calendar.DAY_OF_YEAR,-1);
//        wechatIndexBO.getTotal().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        wechatIndexBO.getTop().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        calendar.add(Calendar.DAY_OF_YEAR,-1);
//        wechatIndexBO.getTotal().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        wechatIndexBO.getTop().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        calendar.add(Calendar.DAY_OF_YEAR,-1);
//        wechatIndexBO.getTotal().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        wechatIndexBO.getTop().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        calendar.add(Calendar.DAY_OF_YEAR,-1);
//        wechatIndexBO.getTotal().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        wechatIndexBO.getTop().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        calendar.add(Calendar.DAY_OF_YEAR,-1);
//        wechatIndexBO.getTotal().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        wechatIndexBO.getTop().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        calendar.add(Calendar.DAY_OF_YEAR,-1);
//        wechatIndexBO.getTotal().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        wechatIndexBO.getTop().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        calendar.add(Calendar.DAY_OF_YEAR,-1);
//        wechatIndexBO.getTotal().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());
//        wechatIndexBO.getTop().put(calendar.getTime().getTime(),Long.valueOf(RandomUtil.getRandom(1000,10000)).intValue());

        return new Response(wechatIndexBO);
    }

    /**
     * 文章类型
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping("/article-type")
    Response<WechatArticleTypeBO> articleType(WechatArticleTypeRequest params) throws ServiceException {

        WechatArticleTypeBO wechatArticleTypeBO = wechatService.getArticleType(params.getCode());

        
//        wechatArticleTypeBO.setTotal(245);
//        wechatArticleTypeBO.setOriginal(114);
//        Map<String, Integer> type = new HashMap<>();
//        type.put("视频",114);
//        type.put("图文",245);
//        wechatArticleTypeBO.setType(type);

        return new Response(wechatArticleTypeBO);
    }

    /**
     * 相关热门公众号
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping("/hot")
    Response<List<WechatHotBO>> hot(WechatHotRequest params) throws ServiceException {

        List<WechatHotBO> wechatHotBOList = wechatService.getHot(params.getCode(),params.getType(),params.getLimit());

        return new Response(wechatHotBOList);
    }

    /**
     * 热词
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping("/hottag")
    Response<List<WechatHottagBO>> hottag(WechatHottagRequest params) throws ServiceException {
        WechatPeriodEnum period = WechatPeriodEnum.valueOf(params.getPeriod());
        if (period == null){
            period = WechatPeriodEnum.W1;
        }

        List<WechatHottagBO> wechatHottagBOList = wechatService.getHottag(params.getCode(),period);

//        List<WechatHottagBO> wechatHottagBOList = new ArrayList<>();
//        WechatHottagBO wechatHottagBO = new WechatHottagBO();
//        wechatHottagBO.setName("太阳能电池");
//        wechatHottagBO.setRate(6.29f);
//        wechatHottagBOList.add(wechatHottagBO);
//        wechatHottagBO = new WechatHottagBO();
//        wechatHottagBO.setName("商品期货");
//        wechatHottagBO.setRate(3.43f);
//        wechatHottagBOList.add(wechatHottagBO);

        return new Response(wechatHottagBOList);
    }


    @GetMapping("/oriArticle")
    Response<List<WechatOriArticleListItem>> oriArticle(WechatOriArticleRequest wechatOriArticleRequest) throws Throwable {
        List<WechatOriArticleListItem> oriArticleListItemList = wechatService.oriArticle(wechatOriArticleRequest);
        return new Response(oriArticleListItemList);
    }

    @GetMapping("moreTag")
    public Response moreTag(@RequestParam(value = "tag_prefix", required = false) String tag_prefix) {
        try {
            Set<String> result = wechatService.getMoreTag(tag_prefix);

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取公众号的更多标签下拉失败：" + e.getMessage());
            return new Response(500, "获取公众号的更多标签失败:" + e.getMessage());
        }
    }

    @GetMapping("moreClassify")
    public Response moreClassify() {
        try {
            List<String> result = wechatService.getMoreClassify();

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取公众号的分类下拉失败：" + e.getMessage());
            return new Response(500, "获取公众号的分类下拉失败:" + e.getMessage());
        }
    }

    @GetMapping("moreWechat")
    public Response moreWechat(WechatMoreRequest wechatMoreRequest) {
        try {
            Map<String, Object> result = wechatService.getMoreWechat(wechatMoreRequest);

            if (result == null || result.isEmpty()) {
                logger.error("更多公众号无数据");
                return new Response(result, "更多公众号无数据");
            }

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取更多公众号的失败：" + e.getMessage());
            return new Response(500, "获取更多公众号的失败:" + e.getMessage());
        }
    }
}