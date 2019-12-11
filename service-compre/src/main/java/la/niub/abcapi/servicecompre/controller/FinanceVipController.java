package la.niub.abcapi.servicecompre.controller;


import com.alibaba.fastjson.JSON;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.model.request.FinanceVipInfoRequest;
import la.niub.abcapi.servicecompre.model.request.FinanceVipMoreArticleRequest;
import la.niub.abcapi.servicecompre.model.request.FinanceVipMorePeopleRequest;
import la.niub.abcapi.servicecompre.model.request.FinanceVipSimilarRequest;
import la.niub.abcapi.servicecompre.model.response.*;
import la.niub.abcapi.servicecompre.service.IFinanceVipService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/financeVip")
public class FinanceVipController {

    private final static Logger logger = LogManager.getLogger(FinanceVipController.class);

    @Autowired
    IFinanceVipService financeVipService;

    @GetMapping("/info")
    public Response info (FinanceVipInfoRequest financeVipInfoRequest) {
        try {
            logger.info("FinanceVipController action info request : " + JSON.toJSONString(financeVipInfoRequest));
            if (StringUtil.isEmpty(financeVipInfoRequest.getFvId())) {
                logger.error("传入的参数fvId为null");
                return new Response(408, "传入的参数fvId为null");
            }
            FinanceVipInfoResponse financeVipInfoResponse = financeVipService.info(financeVipInfoRequest);
            logger.info("FinanceVipController action info response : " + JSON.toJSONString(financeVipInfoResponse));
            return new Response(financeVipInfoResponse);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取金融大V的基本信息失败：" + e.getMessage());
            return new Response(500, "获取金融大V的基本信息失败:" + e.getMessage());
        }

    }

    @GetMapping("/similar")
    public Response similar(FinanceVipSimilarRequest financeVipSimilarRequest) {
        try {
            logger.info("FinanceVipController action similar request : " + JSON.toJSONString(financeVipSimilarRequest));
            if (StringUtil.isEmpty(financeVipSimilarRequest.getFvId())) {
                logger.error("传入的参数fvId为null");
                return new Response(408, "传入的参数fvId为null");
            }
            List<FinanceVipSimilarItemResponse> financeVipSimilarResponse = financeVipService.similar(financeVipSimilarRequest);
            logger.info("FinanceVipController action similar response : " + JSON.toJSONString(financeVipSimilarResponse));
            return new Response(financeVipSimilarResponse);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取金融大V的同类热门人物失败：" + e.getMessage());
            return new Response(500, "获取金融大V的同类热门人物失败:" + e.getMessage());
        }
    }

    @GetMapping("/dynamic")
    public Response dynamic(@RequestParam("fvName") String fvName, @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        try {
            if (StringUtil.isEmpty(fvName)) {
                logger.error("传入的参数fvName为null");
                return new Response(408, "传入的参数fvName为null");
            }

            List<Map<String, Object>> result = financeVipService.getDynamic(fvName, limit);

            if (result == null || result.isEmpty()) {
                logger.warn("金融大V:" + fvName + "的最新动态无数据");
                return new Response(result, "金融大V:" + fvName + "的最新动态无数据");
            }

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取金融大V的最新动态失败：" + e.getMessage());
            return new Response(500, "获取金融大V的最新动态失败:" + e.getMessage());
        }
    }

    @GetMapping("/hotTagPeople")
    public Response hotTagPeople(@RequestParam("fvId") String fvId) {
        try {
            if (StringUtil.isEmpty(fvId)) {
                logger.error("传入的参数fvId为null");
                return new Response(408, "传入的参数fvId为null");
            }

            List<FinanceVipHotTagItemResponse> result = financeVipService.getHotTagPeople(fvId);

            if (result == null || result.isEmpty()) {
                logger.warn("金融大V:" + fvId + "的最热标签人物无数据");
                return new Response(result, "金融大V:" + fvId + "的最热标签人物无数据");
            }

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取金融大V的最热标签人物失败：" + e.getMessage());
            return new Response(500, "获取金融大V的最热标签人物失败:" + e.getMessage());
        }
    }

    @GetMapping("/tag")
    public Response tag(@RequestParam("fvId") String fvId) {
        try {
            if (StringUtil.isEmpty(fvId)) {
                logger.error("传入的参数fvId为null");
                return new Response(408, "传入的参数fvId为null");
            }

            List<String> result = financeVipService.getTag(fvId);

            if (result == null || result.isEmpty()) {
                logger.warn("金融大V:" + fvId + "的标签无数据");
                return new Response(result, "金融大V:" + fvId + "的标签无数据");
            }

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取金融大V的标签失败：" + e.getMessage());
            return new Response(500, "获取金融大V的标签失败:" + e.getMessage());
        }
    }

    @GetMapping("/sameFieldLatestArticles")
    public Response sameFieldLatestArticles(@RequestParam(value = "tag", required = false) String tag,
                                            @RequestParam("fvId") String fvId) {
        try {
            if (StringUtil.isEmpty(fvId)) {
                logger.error("传入的参数fvId为null");
                return new Response(408, "传入的参数fvId为null");
            }

            List<String> tagList = new ArrayList<>();
            if (!StringUtil.isEmpty(tag)) {
                if (tag.contains(",")) {
                    tagList.addAll(Arrays.asList(tag.split(",")));
                } else {
                    tagList.add(tag);
                }
            }

            List<String> fvIdList = new ArrayList<>();
            if (fvId.contains(",")) {
                fvIdList.addAll(Arrays.asList(fvId.split(",")));
            } else {
                fvIdList.add(fvId);
            }

            Map<String, Object> result = financeVipService.getSameFieldLatestArticles(tagList, fvIdList);

            if (result == null || result.isEmpty()) {
                logger.warn("金融大V:" + fvId + "," + tag + ",同领域最新发文无数据");
                return new Response(result, "金融大V:" + fvId + "," + tag + ",同领域最新发文无数据");
            }

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取金融大V的同领域最新发文失败：" + e.getMessage());
            return new Response(500, "获取金融大V的同领域最新发文失败:" + e.getMessage());
        }

    }

    @GetMapping("moreTag")
    public Response moreTag(@RequestParam(value = "tag_prefix", required = false) String tag_prefix) {
        try {
            Set<String> result = financeVipService.getMoreTag(tag_prefix);

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取金融大V的更多标签下拉失败：" + e.getMessage());
            return new Response(500, "获取金融大V的更多标签失败:" + e.getMessage());
        }
    }

    @GetMapping("morePeople")
    public Response morePeople(FinanceVipMorePeopleRequest financeVipMorePeopleRequest) {
        try {

            Map<String, Object> result = financeVipService.getMorePeople(financeVipMorePeopleRequest);

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取金融大V的更多人物失败：" + e.getMessage());
            return new Response(500, "获取金融大V的更多人物失败:" + e.getMessage());
        }
    }

    @GetMapping("moreArticleSource")
    public Response moreArticleSource(@RequestParam(value = "tags", required = false) String tags) {
        try {

            List<Map<String, Object>> result = financeVipService.moreArticleSource(tags);

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取金融大V的更多文章来源失败：" + e.getMessage());
            return new Response(500, "获取金融大V的更多文章来源失败:" + e.getMessage());
        }
    }

    @GetMapping("moreArticle")
    public Response moreArticle(FinanceVipMoreArticleRequest financeVipMoreArticleRequest) {
        try {

            Map<String, Object> result = financeVipService.moreArticle(financeVipMoreArticleRequest);

            if (result == null) {
                logger.error("获取金融大V的更多文章出错");
                return new Response(result, "获取金融大V的更多文章出错");
            }

            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取金融大V的更多文章失败：" + e.getMessage());
            return new Response(500, "获取金融大V的更多文章失败:" + e.getMessage());
        }
    }
}
