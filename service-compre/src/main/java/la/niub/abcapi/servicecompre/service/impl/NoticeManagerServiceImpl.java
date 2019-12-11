package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.component.client.IServiceNoticeClient;
import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.code.SystemEnumCodeConfig;
import la.niub.abcapi.servicecompre.model.bo.NoticeBO;
import la.niub.abcapi.servicecompre.model.request.client.ClientNoticeRequest;
import la.niub.abcapi.servicecompre.model.request.client.NoticeRequest;
import la.niub.abcapi.servicecompre.model.response.client.ClientNoticeResponse;
import la.niub.abcapi.servicecompre.model.response.client.notice.NoticeData;
import la.niub.abcapi.servicecompre.service.INoticeMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeManagerServiceImpl implements INoticeMangerService {

    @Autowired
    IServiceNoticeClient serviceNoticeClient;

    @Override
    public NoticeBO buildNoticeReadingSearchResult(String uid, NoticeRequest noticeRequest,String module,Boolean isCount) throws ServiceException {
        NoticeData searchResult = readingSearch(uid,noticeRequest);

        //@todo 结果整理
        NoticeBO noticeBO = new NoticeBO();
        noticeBO.setItem(searchResult.getItem());
        return noticeBO;
    }

    private NoticeData readingSearch(String uid, NoticeRequest noticeRequest) throws ServiceException {
        //@todo 个人搜索
        ClientNoticeResponse ret = null;
        ClientNoticeRequest clientNoticeRequest = buildClientReportRequest(noticeRequest,uid);
        if (noticeRequest.getGroup_by().equals("company") || noticeRequest.getGroup_by().equals("industry")){

        }else{
            ret = serviceNoticeClient.report(clientNoticeRequest);
        }
        return ret.getData();
    }

    private ClientNoticeRequest buildClientReportRequest(NoticeRequest request, String uid) throws ServiceException {
        ClientNoticeRequest clientReportRequest = new ClientNoticeRequest();

//        if (request.getSubscribed_keyword() != null) {//关键字过滤
//            SubscribeBO subscribeBO = subscribeService.getAllSubscribedKeywordWithGroupName(uid, request.getSubscribed_keyword(), request.getSub_id());
//            if (StringUtil.isEmpty(subscribeBO.getStockList())) {
//                throw new ServiceException(SystemEnumCodeConfig.NO_KEYWORD_IN_GROUP);
//            }
//            clientReportRequest.setSubscribed_keyword(String.join("|", subscribeBO.getStockList()));
//        }
//
//        if (request.getSubscribed_group() != null) {//自选股过滤
//            SubscribeBO subscribeBO = subscribeService.getSusbscribeStockv2(uid, request.getSubscribed_group(), request.getSub_id());
//            if (StringUtil.isEmpty(subscribeBO.getStockList())) {
//                throw new ServiceException(SystemEnumCodeConfig.NO_STOCK_IN_GROUP);
//            }
//            clientReportRequest.setSubscribed_stock_list(String.join("|", subscribeBO.getStockList()));
//        }

        if (request.getOrder_by() != null) {
            clientReportRequest.setOrder_by(request.getOrder_by());
        } else {
            clientReportRequest.setOrder_by("all_score");// 综合排序
        }

        if (request.getIfUnderstand() != null && request.getIfUnderstand() == false) {
            clientReportRequest.setIfUnderstand(false);
        } else {
            clientReportRequest.setIfUnderstand(true);
        }

        if (request.getGroup_by() != null) {//分组
            clientReportRequest.setGroup_by(request.getGroup_by());
        } else {
            clientReportRequest.setGroup_by("default");
        }

//        if (request.isOnly_subscribed_stock()) {//我的自选股
//            SubscribeBO subscribeBO = subscribeService.getAllSubscribedStockv2(uid, "report");
//            if (StringUtil.isEmpty(subscribeBO.getStockList())) {
//                throw new ServiceException(SystemEnumCodeConfig.NO_STOCK);
//            }
//            clientReportRequest.setSubscribed_stock_list(String.join("|", subscribeBO.getStockList()));
//        }
//
//        if (request.isOnly_subscribed_stock()) {//我的订阅关键词
//            SubscribeBO subscribeBO = subscribeService.getAllSubscribedKeyword(uid, "report");
//            if (StringUtil.isEmpty(subscribeBO.getStockList())) {
//                throw new ServiceException(SystemEnumCodeConfig.NO_KEYWORD);
//            }
//            clientReportRequest.setSubscribed_keyword(String.join("|", subscribeBO.getStockList()));
//        }

//        // 关键字和自选股哦之间的关系 AND OR
//        if (request.isOnly_subscribed_stock() && (request.getSubscribed_keyword() != null || request.isOnly_subscribed_keyword())) {
//            clientReportRequest.setBoolReleation(true);
//        } else {
//            clientReportRequest.setBoolReleation(false);
//        }
//
//        //查询我的所有订阅
//        if (request.getAll_subscribed() != null) {
//            SubscribeBO allSubscribedKeyword = subscribeService.getAllSubscribedKeyword(uid, "report");
//            if (!StringUtil.isEmpty(allSubscribedKeyword.getStockList())) {
//                clientReportRequest.setSubscribed_keyword(String.join("|", allSubscribedKeyword.getStockList()));
//            }
//
//            SubscribeBO allSubscribedStock = subscribeService.getAllSubscribedStockv2(uid, "report");
//            if (!StringUtil.isEmpty(allSubscribedStock.getStockList())) {
//                clientReportRequest.setSubscribed_stock_list(String.join("|", allSubscribedStock.getStockList()));
//            }
//
//            if (StringUtil.isEmpty(allSubscribedKeyword.getStockList()) && StringUtil.isEmpty(allSubscribedStock.getStockList())) {
//                throw new ServiceException(SystemEnumCodeConfig.NO_SUBSCRIBE);
//            }
//        }

        //按时间筛选
        if (request.getStart_time() != null) {
            clientReportRequest.setStart_time(TimeUtil.strtotime(request.getStart_time() + " 00:00:00"));
        }
        if (request.getEnd_time() != null) {
            clientReportRequest.setEnd_time(TimeUtil.strtotime(request.getEnd_time() + " 23:59:59"));
        }

        clientReportRequest.setStock_code(request.getStock_code());

        if (!StringUtil.isEmpty(request.getKeyword_filter())) {
            clientReportRequest.setKeyword_filter(request.getKeyword_filter());
        } else {
            clientReportRequest.setKeyword_filter("ALL");
        }

        //搜索时是否导航优先
        if (request.getIsNavigation() != null && request.getIsNavigation() == true) {
            clientReportRequest.setIsNavigation(true);
        }

        if (request.getIndustry_name() != null) {
            String[] names = request.getIndustry_name().trim().split("\\|");
            String[] txts = new String[]{};
            if (request.getIndustry_txt() != null) {
                txts = request.getIndustry_txt().trim().split("\\|");
            }

            List<String> true_name = new ArrayList<>();
            List<String> true_txt = new ArrayList<>();

            if (txts.length != names.length) {
                for (String name : names) {
                    if (!name.equals("text_industry")) {
                        true_name.add(name);
                    }
                }
            } else {
                Integer index = 0;
                for (String name : names) {
                    if (!name.equals("text_industry")) {
                        true_name.add(name);
                    } else {
                        true_txt.add(String.valueOf(index));
                    }
                    index++;
                }
            }

            clientReportRequest.setIndustry_name(String.join("|", true_name));
            clientReportRequest.setIndustry_txt(String.join("|", true_txt));
        }

        if (!StringUtil.isEmpty(request.getReport_type())) {
            clientReportRequest.setReport_type(request.getReport_type());
        }
        if (!StringUtil.isEmpty(request.getAuthor())) {
            clientReportRequest.setAuthor(request.getAuthor());
        }
        if (!StringUtil.isEmpty(request.getSource())) {
            clientReportRequest.setSource(request.getSource());
        }
        if (!StringUtil.isEmpty(request.getRating())) {
            clientReportRequest.setRating(request.getRating());
        }
        if (!StringUtil.isEmpty(request.getStock_filter())) {
            clientReportRequest.setStock_filter(request.getStock_filter());
        }
        if (!StringUtil.isEmpty(request.getFirst_category())) {
            clientReportRequest.setFirst_category(request.getFirst_category());
        }
        if (!StringUtil.isEmpty(request.getSelected_industry_name())) {
            clientReportRequest.setSelected_industry_name(request.getSelected_industry_name());
        }
        if (!StringUtil.isEmpty(request.getSelected_stock_code())) {
            clientReportRequest.setSelected_stock_code(request.getSelected_stock_code());
        }
        if (!StringUtil.isEmpty(request.getMin_file_pages())) {
            clientReportRequest.setMin_file_pages(request.getMin_file_pages());
        }
        if (!StringUtil.isEmpty(request.getMax_file_pages())) {
            clientReportRequest.setMax_file_pages(request.getMax_file_pages());
        }
        if (!StringUtil.isEmpty(request.getCount_only())) {
            clientReportRequest.setCount_only(request.getCount_only());
        }
        if (!StringUtil.isEmpty(request.getStockname())) {
            clientReportRequest.setStockname(request.getStockname());
        }
        if (!StringUtil.isEmpty(request.getCompany_search())) {
            clientReportRequest.setCompany_search(request.getCompany_search());
        }
        if (!StringUtil.isEmpty(request.getTitle_include())) {
            clientReportRequest.setTitle_include(request.getTitle_include());
        }
        if (!StringUtil.isEmpty(request.getTitle_not_include())) {
            clientReportRequest.setTitle_not_include(request.getTitle_not_include());
        }
        if (!StringUtil.isEmpty(request.getContent_include())) {
            clientReportRequest.setContent_include(request.getContent_include());
        }
        if (!StringUtil.isEmpty(request.getConecpt())) {
            clientReportRequest.setConecpt(request.getConecpt());
        }
        if (!StringUtil.isEmpty(request.getLang())) {
            clientReportRequest.setLang(request.getLang());
        }
        if (!StringUtil.isEmpty(request.getException_ids())) {
            clientReportRequest.setException_ids(request.getException_ids());
        }


        if (clientReportRequest.getRating() != null && clientReportRequest.getRating().equals("不限")) {
            clientReportRequest.setRating(null);
        }

        if (clientReportRequest.getTitle_include() != null && clientReportRequest.getTitle_not_include() != null && (clientReportRequest.getTitle_include().equals(clientReportRequest.getTitle_not_include()))) {
            throw new ServiceException(SystemEnumCodeConfig.INCLUDE_EQUALS_NOT_INCLUDE);
        }

        clientReportRequest.setOffset(request.getOffset());
        clientReportRequest.setLimit(request.getLimit());
        clientReportRequest.setSelected_offset(request.getSelected_offset());
        clientReportRequest.setSelected_limit(request.getSelected_limit());
        // 获奖
//        clientReportRequest.setHonored(request.getHonored());
        clientReportRequest.setPerspective(request.getPerspective());
        clientReportRequest.setCategories(request.getCategories());

        return clientReportRequest;
    }
}
