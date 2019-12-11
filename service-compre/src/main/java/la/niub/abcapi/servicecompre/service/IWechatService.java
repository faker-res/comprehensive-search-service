package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.config.enums.WechatPeriodEnum;
import la.niub.abcapi.servicecompre.model.WechatPublicModel;
import la.niub.abcapi.servicecompre.model.bo.wechat.*;
import la.niub.abcapi.servicecompre.model.request.wechat.*;
import la.niub.abcapi.servicecompre.model.response.message.ArticleResponse;
import la.niub.abcapi.servicecompre.model.response.wechat.WechatOriArticleListItem;
import la.niub.abcapi.servicecompre.model.response.wechat.WechatTagIndexItemResponse;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 公众号
 */
public interface IWechatService {

    /**
     * 基本信息
     * @param code
     * @return
     */
    WechatBO getDetail(Long code);

    /**
     * 相关热门公众号
     * @param code
     * @param type TYPE：分类，TAG：标签
     * @return
     */
    List<WechatHotBO> getHot(Long code,String type,Integer limit);

    /**
     * 获取公众号文章标签
     * @param code
     * @return
     */
    List<String> getArticleTag(Long code);

    /**
     * 获取公众号文章类型
     * @param code
     * @return
     */
    WechatArticleTypeBO getArticleType(Long code);

    /**
     * 获取运营指标
     * @param code
     * @return
     */
    WechatIndexBO getIndex(Long code, Date startTime);

    /**
     * 根据名称获取
     * @param name
     * @return
     */
    WechatPublicModel getByName(String name);

    /**
     * 获取热词
     * @param code
     * @return
     */
    List<WechatHottagBO> getHottag(Long code, WechatPeriodEnum periodEnum);


    List<WechatOriArticleListItem> oriArticle(WechatOriArticleRequest wechatOriArticleRequest) throws Throwable;

    Map<Integer, WechatTagIndexItemResponse> tagIndex(WechatTagIndexRequest params) throws Throwable;

    ArticleResponse article(WechatArticleRequest params) throws Throwable;


    List<WechatSourceBO> articleSource(WechatArticleSourceRequest params);

    Set<String> getMoreTag(String tag_prefix) throws Exception;

    List<String> getMoreClassify() throws Exception;

    Map<String,Object> getMoreWechat(WechatMoreRequest wechatMoreRequest) throws Exception;
}
