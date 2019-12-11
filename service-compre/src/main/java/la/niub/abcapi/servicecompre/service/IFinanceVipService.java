package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.request.FinanceVipInfoRequest;
import la.niub.abcapi.servicecompre.model.request.FinanceVipMoreArticleRequest;
import la.niub.abcapi.servicecompre.model.request.FinanceVipMorePeopleRequest;
import la.niub.abcapi.servicecompre.model.request.FinanceVipSimilarRequest;
import la.niub.abcapi.servicecompre.model.response.FinanceVipHotTagItemResponse;
import la.niub.abcapi.servicecompre.model.response.FinanceVipInfoResponse;
import la.niub.abcapi.servicecompre.model.response.FinanceVipMorePeopleItemResponse;
import la.niub.abcapi.servicecompre.model.response.FinanceVipSimilarItemResponse;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IFinanceVipService {

    FinanceVipInfoResponse info(FinanceVipInfoRequest financeVipInfoRequest) throws Exception;

    List<FinanceVipSimilarItemResponse> similar(FinanceVipSimilarRequest financeVipSimilarRequest) throws Exception;

    List<Map<String, Object>> getDynamic(String fvName, Integer limit) throws Exception;

    List<FinanceVipHotTagItemResponse> getHotTagPeople(String fvId) throws Exception;

    Map<String, Object> getSameFieldLatestArticles(List<String> tagList, List<String> fvIdList) throws Exception;

    List<String> getTag(String fvId) throws Exception;

    Set<String> getMoreTag(String tag_prefix) throws Exception;

    Map<String, Object> getMorePeople(FinanceVipMorePeopleRequest financeVipMorePeopleRequest) throws Exception;

    List<Map<String,Object>> moreArticleSource(String tags) throws Exception;

    Map<String,Object> moreArticle(FinanceVipMoreArticleRequest financeVipMoreArticleRequest) throws Exception;
}
