package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import la.niub.abcapi.servicecompre.component.exception.ValidatorException;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.config.code.FinanceVipEnumCode;
import la.niub.abcapi.servicecompre.dao.reporter.IFinancialUserDao;
import la.niub.abcapi.servicecompre.dao.reporter.IUserWechatAccountDao;
import la.niub.abcapi.servicecompre.dao.reporter.IUserWeiboAccountDao;
import la.niub.abcapi.servicecompre.dao.reporter.IWechatPublicDao;
import la.niub.abcapi.servicecompre.model.FinancialUserModel;
import la.niub.abcapi.servicecompre.model.UserWechatAccountModel;
import la.niub.abcapi.servicecompre.model.UserWeiboAccountModel;
import la.niub.abcapi.servicecompre.model.WechatPublicModel;
import la.niub.abcapi.servicecompre.model.bo.financevip.FinanceVipByTagBO;
import la.niub.abcapi.servicecompre.model.request.FinanceVipInfoRequest;
import la.niub.abcapi.servicecompre.model.request.FinanceVipMoreArticleRequest;
import la.niub.abcapi.servicecompre.model.request.FinanceVipMorePeopleRequest;
import la.niub.abcapi.servicecompre.model.request.FinanceVipSimilarRequest;
import la.niub.abcapi.servicecompre.model.response.*;
import la.niub.abcapi.servicecompre.model.response.client.ApiNewsDataResponse;
import la.niub.abcapi.servicecompre.model.response.client.ApiNewsResponse;
import la.niub.abcapi.servicecompre.service.IFinanceVipService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class FinanceVipServiceImpl implements IFinanceVipService {

    private final static Logger logger = LogManager.getLogger(FinanceVipServiceImpl.class);

    @Autowired
    IFinancialUserDao financialUserDao;

    @Autowired
    IUserWechatAccountDao userWechatAccountDao;

    @Autowired
    IUserWeiboAccountDao userWeiboAccountDao;

    @Value("${feign.client.apiNews.url}")
    private String apiNewsUrl;

    @Autowired
    private IWechatPublicDao wechatPublicDao;

    /**
     * 信息
     */
    @Override
    public FinanceVipInfoResponse info(FinanceVipInfoRequest financeVipInfoRequest) throws Exception {

        String fvId = financeVipInfoRequest.getFvId();
        FinancialUserModel userInfo = getUserInfo(fvId);

        FinanceVipInfoResponse financeVipInfoResponse = new FinanceVipInfoResponse();
        financeVipInfoResponse.setUserName(userInfo.getUser_name());
        financeVipInfoResponse.setForeignName(userInfo.getForeign_name());
        financeVipInfoResponse.setNationality(userInfo.getNationality());
        financeVipInfoResponse.setNation(userInfo.getNation());
        financeVipInfoResponse.setBirthplace(userInfo.getBirthplace());
        financeVipInfoResponse.setBirthdate(userInfo.getBirthdate());
        financeVipInfoResponse.setOccupation(userInfo.getOccupation());
        financeVipInfoResponse.setGraduateSchool(userInfo.getGraduate_school());
        financeVipInfoResponse.setNativePlace(userInfo.getNative_place());
        financeVipInfoResponse.setAchievement(userInfo.getAchievement());
        financeVipInfoResponse.setAvatar(userInfo.getHead());
        financeVipInfoResponse.setFansNumber(userInfo.getFollower() == null ? 0 : userInfo.getFollower());
        financeVipInfoResponse.setReadingNumber(userInfo.getReading() == null ? 0 : userInfo.getReading());

        List<String> tag = new ArrayList<>();
        if (!StringUtil.isEmpty(userInfo.getDomain())) {
            tag.add(userInfo.getDomain());
        }

        if (!StringUtil.isEmpty(userInfo.getTags())) {
            if (userInfo.getTags().contains(",")) {
                tag.addAll(Arrays.asList(userInfo.getTags().split(",")));
            } else {
                tag.add(userInfo.getTags());
            }
        }

        financeVipInfoResponse.setTag(tag);

        List<String> newsSource = new ArrayList<>();

        // wechat
        List<FinanceVipInfoWechatPublicItemResponse> financeVipInfoWechatPublicList = new ArrayList<>();
        List<UserWechatAccountModel> wechatAccountList = userWechatAccountDao.selectByUserIdAndType(fvId, "金融");
        if (wechatAccountList != null && !wechatAccountList.isEmpty()) {
            for (UserWechatAccountModel item : wechatAccountList) {
                FinanceVipInfoWechatPublicItemResponse wechatPublicItem = new FinanceVipInfoWechatPublicItemResponse();
                wechatPublicItem.setAvatar(item.getHead());
                wechatPublicItem.setAccountName(item.getAccount_name());
                wechatPublicItem.setAccountId(item.getAccount_id());
                wechatPublicItem.setBrief(item.getBrief());
                wechatPublicItem.setVerify(item.getVerify());

                WechatPublicModel wechatPublicModel = wechatPublicDao.getByAccount(item.getAccount_id());
                if (wechatPublicModel != null && !StringUtil.isEmpty(wechatPublicModel.getQr_code())) {
                    wechatPublicItem.setQrCode(wechatPublicModel.getQr_code());
                }
                financeVipInfoWechatPublicList.add(wechatPublicItem);
                String accountName = item.getAccount_name();
                if (accountName != null && !accountName.isEmpty()) {
                    newsSource.add(accountName);
                }

            }
        }

        // weibo
        List<FinanceVipInfoWeiboItemResponse> financeVipInfoWeiboList =  new ArrayList<>();
        List<UserWeiboAccountModel> weiboAccountList = userWeiboAccountDao.selectByUserId(fvId);
        if (weiboAccountList != null && !weiboAccountList.isEmpty()) {
            for (UserWeiboAccountModel item : weiboAccountList) {
                FinanceVipInfoWeiboItemResponse weiboItem = new FinanceVipInfoWeiboItemResponse();
                weiboItem.setAccountName(item.getAccount_name());
                weiboItem.setVerify(item.getVerify());
                weiboItem.setPosition(item.getPosition());
                weiboItem.setBrief(item.getBrief());
                weiboItem.setTags(item.getTags());
                weiboItem.setFollow(item.getFollow());
                weiboItem.setFollower(item.getFollower());
                weiboItem.setBlog_num(item.getBlog_num());
                weiboItem.setUrl(item.getUrl());
                financeVipInfoWeiboList.add(weiboItem);
            }
        }

        // news
        List<FinanceVipInfoNewsResponse> financeVipInfoNewsList = new ArrayList<>();

        if (!newsSource.isEmpty()) {
            List<ApiNewsDataItemResponse> newsDataItemList = null;
            try {
                String apiNewsRet = wechatPublicNews(newsSource);
                if (apiNewsRet != null && !apiNewsRet.isEmpty()) {
                    ApiNewsResponse apiNewsRetObject = JSON.parseObject(apiNewsRet, ApiNewsResponse.class);
                    if (apiNewsRetObject != null && apiNewsRetObject.getErr_code() == 0) {
                        newsDataItemList = apiNewsRetObject.getData().getItem();
                    }
                }
            }  catch (Exception e) {
                logger.error("finance vip info get wechat news error : " + e.getMessage());
            }

            if (newsDataItemList != null && !newsDataItemList.isEmpty()) {
                for (ApiNewsDataItemResponse item : newsDataItemList) {
                    FinanceVipInfoNewsResponse newsItem  = new FinanceVipInfoNewsResponse();
                    newsItem.setId(item.getId());
                    newsItem.setTitle(item.getTitle());
                    newsItem.setUrl(item.getUrl());
                    financeVipInfoNewsList.add(newsItem);
                }
            }

        }

        financeVipInfoResponse.setNews(financeVipInfoNewsList);
        financeVipInfoResponse.setWechatPublic(financeVipInfoWechatPublicList);
        financeVipInfoResponse.setWeibo(financeVipInfoWeiboList);
        return financeVipInfoResponse;
    }


    private FinancialUserModel getUserInfo(String userId) throws Exception {
        if (userId == null || userId.isEmpty()) {
            throw new ValidatorException(FinanceVipEnumCode.EMPTY_FV_ID);
        }
        FinancialUserModel userInfo = financialUserDao.selectByPrimaryKey(userId);
        if (userInfo == null) {
            throw new ValidatorException(FinanceVipEnumCode.ERROR_FV_ID);
        }
        return userInfo;

    }
    /**
     * 同类vip
     */
    @Override
    public List<FinanceVipSimilarItemResponse> similar(FinanceVipSimilarRequest financeVipSimilarRequest) throws Exception {

        String fvId = financeVipSimilarRequest.getFvId();
        FinancialUserModel userInfo = getUserInfo(fvId);
        List<FinancialUserModel> userList = financialUserDao.getFinanceVipListByUserIdAndDomain(fvId, userInfo.getDomain(),5);
        List<FinanceVipSimilarItemResponse> similarList = new ArrayList<>();
        if (userList != null && !userList.isEmpty()) {
            for (FinancialUserModel financialUserModel : userList) {
                if (financialUserModel == null) {
                    continue;
                }
                FinanceVipSimilarItemResponse similarItem = new FinanceVipSimilarItemResponse();
                List<FinanceVipInfoNewsResponse> news = new ArrayList<>();
                similarItem.setFvId(financialUserModel.getUser_id());
                similarItem.setAvatar(financialUserModel.getHead());
                similarItem.setUserName(financialUserModel.getUser_name());
                List<String> tag = new ArrayList<>();
                if (!StringUtil.isEmpty(financialUserModel.getTags())) {
                    if (financialUserModel.getTags().contains(",")) {
                        tag.addAll(Arrays.asList(financialUserModel.getTags().split(",")));
                    } else {
                        tag.add(financialUserModel.getTags());
                    }

                }
                similarItem.setTag(tag);
                Integer fansNumber = financialUserModel.getFollower() == null ? 0 : financialUserModel.getFollower();
                similarItem.setFansNumber(fansNumber);
                List<UserWechatAccountModel> wechatAccountList = userWechatAccountDao.selectByUserIdAndType(financialUserModel.getUser_id(), "金融");
                List<String> newsSource = new ArrayList<>();
                if (wechatAccountList != null && !wechatAccountList.isEmpty()) {
                    for (UserWechatAccountModel item : wechatAccountList) {
                        String accountName = item.getAccount_name();
                        if (accountName != null && !accountName.isEmpty()) {
                            newsSource.add(accountName);
                        }
                    }

                    List<ApiNewsDataItemResponse> userNewsList = null;
                    try {
                        String apiNewsRet = wechatPublicNews(newsSource);
                        if (apiNewsRet != null && !apiNewsRet.isEmpty()) {
                            ApiNewsResponse apiNewsRetObject = JSON.parseObject(apiNewsRet, ApiNewsResponse.class);
                            if (apiNewsRetObject != null && apiNewsRetObject.getErr_code() == 0) {
                                userNewsList = apiNewsRetObject.getData().getItem();
                            }
                        }
                    }  catch (Exception e) {
                        logger.error("finance vip similar get wechat news error : " + e.getMessage());
                    }

                    if (userNewsList != null && !userNewsList.isEmpty()) {
                        for (ApiNewsDataItemResponse item : userNewsList) {
                            FinanceVipInfoNewsResponse newsItem  = new FinanceVipInfoNewsResponse();
                            newsItem.setId(item.getId());
                            newsItem.setTitle(item.getTitle());
                            newsItem.setUrl(item.getUrl());
                            news.add(newsItem);
                        }
                    }
                }
                similarItem.setNews(news);
                similarList.add(similarItem);
            }
        }
        return similarList;

    }


    private String wechatPublicNews(List<String> newsSource) {

        StringBuilder names = new StringBuilder();
        for (String s : newsSource) {
            names.append(" ").append("\"").append(s).append("\"");
        }
        try {
            Map<String, String> newsRequest = new HashMap<>();
            //newsRequest.put("keyword", URLEncoder.encode("$source_name:(" + URLEncoder.encode(names.toString().substring(1), "UTF-8") + ")", "UTF-8"));
            newsRequest.put("keyword", "$source_name_s:(" + names.toString().substring(1) + ")");
            newsRequest.put("offset", "0");
            newsRequest.put("limit", "5");
            newsRequest.put("prior", "time");
            newsRequest.put("channel", "");
            newsRequest.put("core_name", "core_news");
            newsRequest.put("single", "true");

            return HttpUtil.post(apiNewsUrl, newsRequest, null);
        } catch (Exception e) {
            logger.error("fincance vip wechat urlencode error :" + e.getMessage());
        }
        return null;

    }

    @Override
    public List<Map<String, Object>> getDynamic(String fvName, Integer limit) throws Exception {
        Map<String, String> newsRequest = new HashMap<>();
        newsRequest.put("keyword", fvName);
        newsRequest.put("offset", "0");
        newsRequest.put("limit", limit.toString());
        newsRequest.put("prior", "time");
        newsRequest.put("channel", "");
        newsRequest.put("core_name", "core_news");
        newsRequest.put("single", "false");

        String apiNewsRet = HttpUtil.post(apiNewsUrl, newsRequest, null);

        if (StringUtil.isEmpty(apiNewsRet)) {
            return null;
        }

        ApiNewsResponse apiNewsRetObject = JSON.parseObject(apiNewsRet, ApiNewsResponse.class);
        if (apiNewsRetObject == null || apiNewsRetObject.getErr_code() != 0) {
            return null;
        }

        if (apiNewsRetObject.getData() == null) {
            return null;
        }

        List<ApiNewsDataItemResponse> newsList = apiNewsRetObject.getData().getItem();

        if (newsList == null || newsList.isEmpty()) {
            return null;
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (int i = 0; i < newsList.size(); i++) {
            ApiNewsDataItemResponse apiNewsDataItemResponse = newsList.get(i);
            if (apiNewsDataItemResponse != null ) {
                Map<String, Object> news = new HashMap<>();
                String title = apiNewsDataItemResponse.getTitle();
                if (!StringUtil.isEmpty(title)) {
                    title = title.replace("<font color='red'>", "")
                            .replace("</font>", "");
                }

                news.put("id", apiNewsDataItemResponse.getId());
                news.put("title", title);
                news.put("url", apiNewsDataItemResponse.getUrl());
                news.put("time", apiNewsDataItemResponse.getTime() * 1000L);
                result.add(news);
            }
        }

        return result;
    }

    @Override
    public List<FinanceVipHotTagItemResponse> getHotTagPeople(String fvId) throws Exception {
        FinancialUserModel userInfo = getUserInfo(fvId);

        List<FinancialUserModel> userList = new ArrayList<>();
        if (!StringUtil.isEmpty(userInfo.getTags())) {
            String[] tags;
            if (userInfo.getTags().contains(",")) {
                tags = userInfo.getTags().split(",");
            } else {
                tags = new String[]{userInfo.getTags()};
            }

            List<FinancialUserModel> list = financialUserDao.getLikeTags(fvId, Arrays.asList(tags));
            if (list != null && !list.isEmpty()) {
                List<FinanceVipByTagBO> financeVipByTagBOList = new ArrayList<>();
                for (FinancialUserModel item : list) {
                    if (item == null) {
                        continue;
                    }

                    FinanceVipByTagBO financeVipByTagBO = new FinanceVipByTagBO();
                    if (StringUtil.isEmpty(item.getTags())) {
                        financeVipByTagBO.setSimilarity(0);
                    } else {
                        List<String> tagList = new ArrayList<>(Arrays.asList(tags));

                        List<String> itemTagList = new ArrayList<>();
                        if (item.getTags().contains(",")) {
                            itemTagList.addAll(Arrays.asList(item.getTags().split(",")));
                        } else {
                            itemTagList.add(item.getTags());
                        }

                        tagList.retainAll(itemTagList);

                        financeVipByTagBO.setSimilarity(tagList.size());
                    }

                    financeVipByTagBO.setFinancialUserModel(item);
                    financeVipByTagBOList.add(financeVipByTagBO);
                }

                Collections.sort(financeVipByTagBOList, (o1, o2) -> {
                    if (o1.getSimilarity().equals(o2.getSimilarity())){
                        return o2.getFinancialUserModel().getFollower().compareTo(o1.getFinancialUserModel().getFollower());
                    }
                    return o2.getSimilarity().compareTo(o1.getSimilarity());
                });

                for (FinanceVipByTagBO item : financeVipByTagBOList){
                    if (userList.size() >= 5){
                        break;
                    }
                    userList.add(item.getFinancialUserModel());
                }
            }
        }

        if (userList.isEmpty()) {
            userList = financialUserDao.getFinanceVipListByUserIdAndDomain(fvId, userInfo.getDomain(), 5);
        }

        List<FinanceVipHotTagItemResponse> hotTagList = new ArrayList<>();
        for (FinancialUserModel financialUserModel : userList) {
            if (financialUserModel == null) {
                continue;
            }
            FinanceVipHotTagItemResponse hotTagItem = new FinanceVipHotTagItemResponse();
            List<FinanceVipInfoNewsResponse> news = new ArrayList<>();

            hotTagItem.setFvId(financialUserModel.getUser_id());
            hotTagItem.setAvatar(financialUserModel.getHead());
            hotTagItem.setFvName(financialUserModel.getUser_name());
            List<String> tag = new ArrayList<>();
            if (!StringUtil.isEmpty(financialUserModel.getTags())) {
                if (financialUserModel.getTags().contains(",")) {
                    tag.addAll(Arrays.asList(financialUserModel.getTags().split(",")));
                } else {
                    tag.add(financialUserModel.getTags());
                }

            }
            hotTagItem.setTag(tag);
            Integer fansNumber = financialUserModel.getFollower() == null ? 0 : financialUserModel.getFollower();
            hotTagItem.setFansNumber(fansNumber);
            List<UserWechatAccountModel> wechatAccountList = userWechatAccountDao.selectByUserIdAndType(financialUserModel.getUser_id(), "金融");
            List<String> newsSource = new ArrayList<>();
            if (wechatAccountList != null && !wechatAccountList.isEmpty()) {
                for (UserWechatAccountModel item : wechatAccountList) {
                    String accountName = item.getAccount_name();
                    if (accountName != null && !accountName.isEmpty()) {
                        newsSource.add(accountName);
                    }
                }

                List<ApiNewsDataItemResponse> userNewsList = null;
                try {
                    String apiNewsRet = wechatPublicNews(newsSource);
                    if (apiNewsRet != null && !apiNewsRet.isEmpty()) {
                        ApiNewsResponse apiNewsRetObject = JSON.parseObject(apiNewsRet, ApiNewsResponse.class);
                        if (apiNewsRetObject != null && apiNewsRetObject.getErr_code() == 0) {
                            userNewsList = apiNewsRetObject.getData().getItem();
                        }
                    }
                }  catch (Exception e) {
                    logger.error("finance vip similar get wechat news error : " + e.getMessage());
                }

                if (userNewsList != null && !userNewsList.isEmpty()) {
                    for (ApiNewsDataItemResponse item : userNewsList) {
                        FinanceVipInfoNewsResponse newsItem  = new FinanceVipInfoNewsResponse();
                        newsItem.setId(item.getId());
                        newsItem.setTitle(item.getTitle());
                        newsItem.setUrl(item.getUrl());
                        news.add(newsItem);
                    }
                }
            }
            hotTagItem.setNews(news);
            hotTagList.add(hotTagItem);
        }

        return hotTagList;
    }

    @Override
    public Map<String, Object> getSameFieldLatestArticles(List<String> tagList, List<String> fvIdList) throws Exception {
        List<UserWechatAccountModel> wechatAccountList = userWechatAccountDao.selectByUserIdListAndType(fvIdList, "金融");

        if (wechatAccountList == null || wechatAccountList.isEmpty()) {
            return null;
        }

        Map<String, String> wechatNameHeadMap = new HashMap<>();

        StringBuilder source_name_s = new StringBuilder();
        for (UserWechatAccountModel wechatItem : wechatAccountList) {
            if (wechatItem == null || StringUtil.isEmpty(wechatItem.getAccount_id())) {
                continue;
            }

            wechatNameHeadMap.put(wechatItem.getAccount_name(), wechatItem.getHead());

            source_name_s.append(" ").append("\"").append(wechatItem.getAccount_name().trim()).append("\"");
        }

        if (StringUtil.isEmpty(source_name_s.toString())) {
            return null;
        }

        StringBuilder keyword = new StringBuilder();
        if (tagList.isEmpty()) {
            keyword.append("$source_name_s:(").append(source_name_s.toString().substring(1)).append(")");
        } else {
            StringBuilder tags_mul = new StringBuilder();
            for (String  tag : tagList) {
                if (StringUtil.isEmpty(tag)) {
                    continue;
                }

                tags_mul.append(" ").append("\"").append(tag.trim()).append("\"");
            }

            if (StringUtil.isEmpty(tags_mul)) {
                keyword.append("$source_name_s:(").append(source_name_s.toString().substring(1)).append(")");
            } else {
                keyword.append("$tags_mul:(").append(tags_mul.toString().substring(1))
                        .append(") AND source_name_s:(").append(source_name_s.toString().substring(1)).append(")");
            }
        }

        Map<String, String> newsRequest = new HashMap<>();
        newsRequest.put("keyword", keyword.toString());
        newsRequest.put("offset", "0");
        newsRequest.put("limit", "30");
        newsRequest.put("prior", "time");
        newsRequest.put("channel", "微信");
        newsRequest.put("single", "false");

        String apiNewsRet = HttpUtil.post(apiNewsUrl, newsRequest, null);

        if (StringUtil.isEmpty(apiNewsRet)) {
            return null;
        }

        ApiNewsResponse apiNewsRetObject = JSON.parseObject(apiNewsRet, ApiNewsResponse.class);
        if (apiNewsRetObject == null || apiNewsRetObject.getErr_code() != 0) {
            return null;
        }

        if (apiNewsRetObject.getData() == null) {
            return null;
        }

        List<ApiNewsDataItemResponse> newsList = apiNewsRetObject.getData().getItem();

        if (newsList == null || newsList.isEmpty()) {
            return null;
        }

        List<Map<String, Object>> newList = new ArrayList<>();
        Integer total = apiNewsRetObject.getData().getTotal_count();

        for (int i = 0; i < newsList.size(); i++) {
            ApiNewsDataItemResponse apiNewsDataItemResponse = newsList.get(i);
            if (apiNewsDataItemResponse != null ) {
                Map<String, Object> news = new HashMap<>();

                String title = apiNewsDataItemResponse.getTitle();
                if (!StringUtil.isEmpty(title)) {
                    title = StringUtil.stripHtml(title);
                }

                String tags = apiNewsDataItemResponse.getTags();
                List<String> tag = new ArrayList<>();
                if (!StringUtil.isEmpty(tags)) {
                    if (tags.contains(",")) {
                        tag.addAll(Arrays.asList(tags.split(",")));
                    } else {
                        tag.add(tags);
                    }
                }

                String source_name = apiNewsDataItemResponse.getSource_name_s();
                if (!StringUtil.isEmpty(source_name)) {
                    source_name = StringUtil.stripHtml(source_name);
                }

                news.put("id", apiNewsDataItemResponse.getId());
                news.put("title", title);
                news.put("url", apiNewsDataItemResponse.getUrl());
                news.put("time", apiNewsDataItemResponse.getTime() * 1000L);
                news.put("tag", tag);
                news.put("source_name", source_name);
                news.put("source_image", wechatNameHeadMap.get(source_name));
                newList.add(news);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("item", newList);

        if (total == null) {
            total = 0;
        } else {
            total = total > 30 ? 30 : total;
        }
        result.put("total", total);

        return result;
    }

    @Override
    public List<String> getTag(String fvId) throws Exception {
        List<String> result = new ArrayList<>();

        FinancialUserModel userInfo = getUserInfo(fvId);
        if (!StringUtil.isEmpty(userInfo.getTags())) {
            if (userInfo.getTags().contains(",")) {
                result.addAll(Arrays.asList(userInfo.getTags().split(",")));
            } else {
                result.add(userInfo.getTags());
            }
        }

        return result;
    }

    @Override
    public Set<String> getMoreTag(String tag_prefix) throws Exception {
        Set<String> result = new HashSet<>();

        List<String> tagList = financialUserDao.getTagByTagPrefix(tag_prefix);

        if (tagList == null || tagList.isEmpty()) {
            return result;
        }

        for (String tagStr : tagList) {
            if (!StringUtil.isEmpty(tagStr)) {
                if (StringUtil.isEmpty(tag_prefix)) {
                    if (tagStr.contains(",")) {
                        result.addAll(Arrays.asList(tagStr.split(",")));
                    } else {
                        result.add(tagStr);
                    }
                } else {
                    if (tagStr.contains(",")) {
                        List<String> tagStrList = Arrays.asList(tagStr.split(","));
                        for (String tag : tagStrList) {
                            if (tag.startsWith(tag_prefix)) {
                                result.add(tag);
                            }
                        }
                    } else {
                        if (tagStr.startsWith(tag_prefix)) {
                            result.add(tagStr);
                        }
                    }
                }
            }
        }

        return result;
    }

    @Override
    public Map<String, Object> getMorePeople(FinanceVipMorePeopleRequest financeVipMorePeopleRequest) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<String> tagList = new ArrayList<>();
        String tagStr = financeVipMorePeopleRequest.getTags();
        if (!StringUtil.isEmpty(tagStr)) {
            if (tagStr.contains(",")) {
                tagList = Arrays.asList(tagStr.split(","));
            } else {
                tagList.add(tagStr);
            }
        }

        String name_py_prefix = financeVipMorePeopleRequest.getName_py_prefix();
        if (!StringUtil.isEmpty(name_py_prefix)) {
            name_py_prefix = name_py_prefix.toUpperCase();

            if (name_py_prefix.length() != 1 || !name_py_prefix.matches("[a-zA-Z]+")) {
                name_py_prefix = null;
            }
        }

        String name = financeVipMorePeopleRequest.getName();
        if (!StringUtil.isEmpty(name) && name.contains("・")) {
            name = name.replace("・", "·");
        }

        Integer count = financialUserDao.getListCountByTagAndNameAndNamePrefix(tagList, name, name_py_prefix);

        if (count == null || count == 0) {
            result.put("item", new ArrayList<>());
            result.put("total", 0);
            return result;
        }

        StringBuilder order = new StringBuilder();
        String orderField = financeVipMorePeopleRequest.getOrder_field();
        String orderType = financeVipMorePeopleRequest.getOrder_type();
        if (!StringUtil.isEmpty(orderField) && !StringUtil.isEmpty(orderType)) {
            boolean isValidField = ("reading".equals(orderField) || "follower".equals(orderField))
                    && ("desc".equals(orderType) || "asc".equals(orderType));
            if (isValidField) {
                if ("reading".equals(orderField)) {
                    order.append(orderField).append(" ").append(orderType).append(",").append("follower ").append(orderType).append(",");
                } else {
                    order.append(orderField).append(" ").append(orderType).append(",").append("reading ").append(orderType).append(",");
                }

            }
        }
        order.append("user_id desc");

        Integer limit = financeVipMorePeopleRequest.getLimit();
        Integer page_num = financeVipMorePeopleRequest.getPage_num() < 1 ? 1 : financeVipMorePeopleRequest.getPage_num();
        Integer index = (page_num - 1) * limit;

        List<FinancialUserModel> list = financialUserDao.getListByTagAndNameAndNamePrefix(tagList, name, name_py_prefix,
                index, limit, order.toString());

        List<FinanceVipMorePeopleItemResponse> itemList = new ArrayList<>();
        for (FinancialUserModel model : list) {
            FinanceVipMorePeopleItemResponse itemResponse = new FinanceVipMorePeopleItemResponse();
            itemResponse.setUserId(model.getUser_id());
            itemResponse.setUserName(model.getUser_name());
            itemResponse.setAvatar(model.getHead());

            List<String> itemTag = new ArrayList<>();
            if (!StringUtil.isEmpty(model.getDomain())) {
                itemTag.add(model.getDomain());
            }
            String tagsStr = model.getTags();
            if (!StringUtil.isEmpty(tagsStr)) {
                if (tagsStr.contains(",")) {
                    itemTag.addAll(Arrays.asList(tagsStr.split(",")));
                } else {
                    itemTag.add(tagsStr);
                }
            }

            if (!tagList.isEmpty() && !itemTag.isEmpty()) {
                List<String> tagListCopy = new ArrayList<>();
                tagListCopy.addAll(tagList);
                tagListCopy.retainAll(itemTag);
                Set<String> itemTagSet = new LinkedHashSet<>(tagListCopy);
                itemTagSet.addAll(itemTag);
                itemTag = new ArrayList<>(itemTagSet);
            }
            itemResponse.setTag(itemTag);

            itemResponse.setReading(model.getReading());
            itemResponse.setFollower(model.getFollower());

            List<UserWechatAccountModel> wechatAccountList = userWechatAccountDao.selectByUserIdAndType(model.getUser_id(), "金融");

            String keyword;
            if (wechatAccountList == null || wechatAccountList.isEmpty()) {
                keyword = model.getUser_name();
            } else {
                StringBuilder names = new StringBuilder();
                for (UserWechatAccountModel userWechatAccountModel : wechatAccountList) {
                    names.append(" ").append("\"").append(userWechatAccountModel.getAccount_name()).append("\"");
                }

                keyword = "$source_name_s:(" + names.toString().substring(1) + ") AND channel:\"微信\"";
            }

            Map<String, String> newsRequest = new HashMap<>();
            newsRequest.put("keyword", keyword);
            newsRequest.put("offset", "0");
            newsRequest.put("limit", "1");
            newsRequest.put("prior", "time");
            newsRequest.put("core_name", "core_news");
            newsRequest.put("single", "false");

            String apiNewsRet = HttpUtil.post(apiNewsUrl, newsRequest, null);

            if (StringUtil.isEmpty(apiNewsRet)) {
                itemResponse.setNews(null);
                itemList.add(itemResponse);
                continue;
            }

            ApiNewsResponse apiNewsRetObject = JSON.parseObject(apiNewsRet, ApiNewsResponse.class);
            if (apiNewsRetObject == null || apiNewsRetObject.getErr_code() != 0) {
                itemResponse.setNews(null);
                itemList.add(itemResponse);
                continue;
            }

            if (apiNewsRetObject.getData() == null) {
                itemResponse.setNews(null);
                itemList.add(itemResponse);
                continue;
            }

            List<ApiNewsDataItemResponse> newsList = apiNewsRetObject.getData().getItem();

            if (newsList == null || newsList.isEmpty()) {
                itemResponse.setNews(null);
                itemList.add(itemResponse);
                continue;
            }

            ApiNewsDataItemResponse apiNewsDataItemResponse = newsList.get(0);
            if (apiNewsDataItemResponse != null ) {
                FinanceVipInfoNewsResponse news = new FinanceVipInfoNewsResponse();

                String title = apiNewsDataItemResponse.getTitle();
                if (!StringUtil.isEmpty(title)) {
                    title = StringUtil.stripHtml(title);
                }

                news.setId(apiNewsDataItemResponse.getId());
                news.setTitle(title);
                news.setUrl(apiNewsDataItemResponse.getUrl());

                itemResponse.setNews(news);
            } else {
                itemResponse.setNews(null);
            }

            itemList.add(itemResponse);
        }

        result.put("item", itemList);
        result.put("total", count);

        return result;
    }

    // TODO 金融大V更多文章来源接口，等搜索接口上线,就可以使用以下注释的代码，现在的方法比较慢
    /*@Override
    public List<Map<String, Object>> moreArticleSource(String tags) throws Exception {
        if (isAllNull(tags, ",")) {
            return financialUserDao.getAllFinanceVip();
        } else {
            List<UserWechatAccountModel> userWechatAccountModelList = userWechatAccountDao.selectByType("金融");

            if (userWechatAccountModelList == null || userWechatAccountModelList.isEmpty()) {
                return null;
            }

            StringBuilder source_name_s = new StringBuilder();
            Map<String, String> userWechatAccountModelMap = new HashMap<>();
            for (UserWechatAccountModel model : userWechatAccountModelList) {
                String user_id = model.getUser_id();
                String account_name = model.getAccount_name();
                if (!StringUtil.isEmpty(user_id) && !StringUtil.isEmpty(account_name.trim())) {
                    if (!userWechatAccountModelMap.containsKey(account_name)) {
                        userWechatAccountModelMap.put(account_name, user_id);
                        source_name_s.append(" ").append("\"").append(model.getAccount_name().trim()).append("\"");
                    }
                }
            }

            List<String> tagList = Arrays.asList(tags.split(","));

            StringBuilder keyword = new StringBuilder();
            keyword.append("$channel:\"微信\"").append(" AND ")
                    .append("source_name_s:(").append(source_name_s.toString().substring(1)).append(")")
                    .append(" AND ").append("tags_mul:(");
            for (String  tag : tagList) {
                if (StringUtil.isEmpty(tag)) {
                    continue;
                }

                keyword.append("\"").append(tag.trim()).append("\"").append(" ");
            }
            keyword.deleteCharAt(keyword.length() - 1).append(")");

            ApiNewsDataResponse apiNewsResponse = getApiNewsResponse(keyword.toString(), "0", "1");

            List<ApiNewsDataOptionResponse> apiNewsDataOptionResponseList = apiNewsResponse.getOption();

            if (apiNewsDataOptionResponseList == null || apiNewsDataOptionResponseList.isEmpty()) {
                return null;
            }

            Set<String> userIdSet = new LinkedHashSet<>();

            for (ApiNewsDataOptionResponse apiNewsDataOptionResponse : apiNewsDataOptionResponseList) {
                if ("source_name_s".equals(apiNewsDataOptionResponse.getType())) {
                    List<ApiNewsDataOptionItemResponse> item = apiNewsDataOptionResponse.getItem();
                    if (item == null || item.isEmpty()) {
                        return null;
                    }

                    for (ApiNewsDataOptionItemResponse apiNewsDataOptionItemResponse : item) {
                        if (!StringUtil.isEmpty(apiNewsDataOptionItemResponse.getName())) {
                            String user_id = userWechatAccountModelMap.get(apiNewsDataOptionItemResponse.getName());
                            if (!StringUtil.isEmpty(user_id)) {
                                userIdSet.add(user_id);
                            }
                        }
                    }

                    break;
                }
            }

            if (userIdSet.isEmpty()) {
                return null;
            }

            return financialUserDao.getFinanceVipListByUserIdList(new ArrayList<String>(userIdSet));
        }
    }*/

    @Override
    public List<Map<String, Object>> moreArticleSource(String tags) throws Exception {
        if (isAllNull(tags, ",")) {
            return financialUserDao.getAllFinanceVip();
        } else {
            Set<String> userIdSet = new HashSet<>();

            List<UserWechatAccountModel> userWechatAccountModelList = userWechatAccountDao.selectByType("金融");

            if (userWechatAccountModelList == null || userWechatAccountModelList.isEmpty()) {
                return null;
            }

            StringBuilder source_name_s = new StringBuilder();
            Map<String, String> userWechatAccountModelMap = new HashMap<>();
            for (UserWechatAccountModel model : userWechatAccountModelList) {
                String user_id = model.getUser_id();
                String account_name = model.getAccount_name();
                if (!StringUtil.isEmpty(user_id) && !StringUtil.isEmpty(account_name)) {
                    if (!userWechatAccountModelMap.containsKey(account_name)) {
                        userWechatAccountModelMap.put(account_name, user_id);
                        source_name_s.append(" ").append("\"").append(model.getAccount_name().trim()).append("\"");
                    }
                }
            }

            List<String> tagList = Arrays.asList(tags.split(","));

            StringBuilder keyword = new StringBuilder();
            keyword.append("$channel:\"微信\"").append(" AND ")
                    .append("source_name_s:(").append(source_name_s.toString().substring(1)).append(")")
                    .append(" AND ").append("tags_mul:(");
            for (String  tag : tagList) {
                if (StringUtil.isEmpty(tag)) {
                    continue;
                }

                keyword.append("\"").append(tag.trim()).append("\"").append(" ");
            }
            keyword.deleteCharAt(keyword.length() - 1).append(")");

            ApiNewsDataResponse apiNewsDataResponse = getApiNewsResponse(keyword.toString(), "0", "1");
            if (apiNewsDataResponse == null) {
                return null;
            }

            Integer total = apiNewsDataResponse.getTotal_count();
            Integer count = total % 100 == 0 ? total / 100 : (total / 100) + 1;

            List<Future<Set<String>>> futures = new ArrayList<>();
            ExecutorService executor = Executors.newCachedThreadPool();
            for (int i = 0; i < count; i++) {
                final Integer index = i;
                Callable<Set<String>> task = new Callable<Set<String>>() {
                    @Override
                    public Set<String> call() throws Exception {
                        return getUserIdSet(index, keyword.toString(), userWechatAccountModelMap, 100);
                    }
                };
                futures.add(executor.submit(task));
            }

            for (Future<Set<String>> future : futures) {
                userIdSet.addAll(future.get());
            }

            if (userIdSet.isEmpty()) {
                return null;
            }

            return financialUserDao.getFinanceVipListByUserIdList(new ArrayList<String>(userIdSet));
        }
    }

    public Set<String> getUserIdSet(Integer index, String keyword, Map<String, String> userWechatAccountModelMap, Integer limit) throws Exception{
        Set<String> userIdSet = new HashSet<>();

        Integer offset = index * limit;
        ApiNewsDataResponse response = getApiNewsResponse(keyword.toString(), offset.toString(), limit.toString());
        if (response == null) {
            return userIdSet;
        }

        List<ApiNewsDataItemResponse> item = response.getItem();
        if (item == null || item.isEmpty()) {
            return userIdSet;
        }

        for (ApiNewsDataItemResponse apiNewsDataItemResponse : item) {
            String source_name_s = apiNewsDataItemResponse.getSource_name_s();
            if (!StringUtil.isEmpty(source_name_s)) {
                source_name_s = StringUtil.stripHtml(source_name_s);
                String user_id = userWechatAccountModelMap.get(source_name_s);
                if (!StringUtil.isEmpty(user_id)) {
                    userIdSet.add(user_id);
                }
            }
        }

        return userIdSet;
    }

    private ApiNewsDataResponse getApiNewsResponse(String keyword, String offset, String limit) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("offset", offset);
        params.put("limit", limit);
        params.put("prior", "time");
        params.put("core_name", "core_news");
        params.put("single", "false");

        String apiNewsRet = HttpUtil.post(apiNewsUrl, params, null);

        if (StringUtil.isEmpty(apiNewsRet)) {
            return null;
        }

        ApiNewsResponse apiNewsRetObject = JSON.parseObject(apiNewsRet, ApiNewsResponse.class);
        if (apiNewsRetObject == null || apiNewsRetObject.getErr_code() != 0) {
            return null;
        }

        if (apiNewsRetObject.getData() == null) {
            return null;
        }
        return apiNewsRetObject.getData();
    }

    private boolean isAllNull(String tags, String separator) {
        if (StringUtil.isEmpty(tags)) {
            return true;
        } else {
            if (!tags.contains(separator)) {
                return false;
            } else {
                List<String> tagList = Arrays.asList(tags.split(separator));
                for (String tag : tagList) {
                    if (!StringUtil.isEmpty(tag.trim())) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    @Override
    public Map<String, Object> moreArticle(FinanceVipMoreArticleRequest financeVipMoreArticleRequest) throws Exception {
        Map<String, Object> result = new HashMap<>();
        //微信公众号id
        String wbchatid="";
        String fvIds = financeVipMoreArticleRequest.getFvIds();
        String tags = financeVipMoreArticleRequest.getTags();
        List<String> userIdList = new ArrayList<>();
        if (!StringUtil.isEmpty(fvIds)) {
            if (fvIds.contains(",")) {
                userIdList = Arrays.asList(fvIds.split(","));
            } else {
                userIdList.add(fvIds);
            }
        } else {
            if (!StringUtil.isEmpty(tags)) {
                List<Map<String, Object>> list = moreArticleSource(tags);
                if (list != null && !list.isEmpty()) {
                    for (Map<String, Object> map : list) {
                        Object user_id = map.get("user_id");
                        if (!StringUtil.isEmpty(user_id)) {
                            userIdList.add(user_id.toString());
                        }
                    }
                }
            }
        }

        Map<String, String> accountNameAndHeadMap = new HashMap<>();
        StringBuilder source_name_s = new StringBuilder();
        if(financeVipMoreArticleRequest.getSourceName()!=null && !financeVipMoreArticleRequest.getSourceName().isEmpty()){
            source_name_s.append(" ").append("\"").append(financeVipMoreArticleRequest.getSourceName().trim()).append("\"");
            WechatPublicModel wechatPublicModel=wechatPublicDao.selectByName(financeVipMoreArticleRequest.getSourceName());
            if(wechatPublicModel!=null){
                wbchatid=wechatPublicModel.getId().toString();
            }
        }else{
            List<UserWechatAccountModel> userWechatAccountModelList = userWechatAccountDao.selectAllByUserIdListAndType(userIdList, "金融");
            if (userWechatAccountModelList == null || userWechatAccountModelList.isEmpty()) {
                result.put("total", 0);
                result.put("item", new ArrayList<>());
                return result;
            }
            for (UserWechatAccountModel model : userWechatAccountModelList) {
                if (StringUtil.isEmpty(model.getAccount_name())) {
                    continue;
                }

                if (!accountNameAndHeadMap.containsKey(model.getAccount_name())) {
                    accountNameAndHeadMap.put(model.getAccount_name(), model.getHead());
                    source_name_s.append(" ").append("\"").append(model.getAccount_name().trim()).append("\"");
                }
            }
        }

        if (StringUtil.isEmpty(source_name_s)) {
            result.put("total", 0);
            result.put("item", new ArrayList<>());
            return result;
        }

        StringBuilder keyword = new StringBuilder();
        keyword.append("$channel:\"微信\"").append(" AND ")
                .append("source_name_s:(").append(source_name_s.toString().substring(1)).append(")");

        List<String> tagList = new ArrayList<>();
        if (!StringUtil.isEmpty(tags)) {
            if (tags.contains(",")) {
                tagList = Arrays.asList(tags.split(","));
            } else {
                tagList.add(tags);
            }

            StringBuilder tag_mul = new StringBuilder();
            for (String tag : tagList) {
                if (!StringUtil.isEmpty(tag.trim())) {
                    tag_mul.append(" ").append("\"").append(tag.trim()).append("\"");
                }
            }

            if (!StringUtil.isEmpty(tag_mul)) {
                keyword.append(" AND ").append("tags_mul:(").append(tag_mul.toString().substring(1)).append(")");
            }
        }

        String title_keyword = financeVipMoreArticleRequest.getTitle_keyword();
        if (!StringUtil.isEmpty(title_keyword)) {
            keyword.append(" AND ").append("title:(").append("\"").append(title_keyword.trim()).append("\"").append(")");
        }

        String order_type = financeVipMoreArticleRequest.getOrder_type();
        Integer total = 0;
        List<FinanceVipMoreArticleItemResponse> item = new ArrayList<>();
        Integer pageNum = financeVipMoreArticleRequest.getPage_num();
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        Integer limit = financeVipMoreArticleRequest.getLimit();
        if (limit == null || limit < 1) {
            limit = 15;
        }

        List<ApiNewsDataItemResponse> apiNewsDataItemList;
        if (order_type == null || !"asc".equals(order_type)) {
            Integer offset = (pageNum - 1) * limit;
            ApiNewsDataResponse apiNewsResponse = getApiNewsResponse(keyword.toString(), offset.toString(), limit.toString());
            if (apiNewsResponse == null) {
                return null;
            }

            total = apiNewsResponse.getTotal_count();

            apiNewsDataItemList= apiNewsResponse.getItem();
        } else {
            ApiNewsDataResponse apiNewsResponse1 = getApiNewsResponse(keyword.toString(), "0", "1");
            if (apiNewsResponse1 == null) {
                return null;
            }

            total = apiNewsResponse1.getTotal_count();
            //Integer limit = financeVipMoreArticleRequest.getLimit();
            Integer offset = total - pageNum * limit;
            if (offset < 0) {
                limit = offset + limit;
                if (limit <= 0) {
                    return null;
                } 
                offset = 0;
            }

            ApiNewsDataResponse apiNewsResponse2 = getApiNewsResponse(keyword.toString(), offset.toString(), limit.toString());
            if (apiNewsResponse2 == null) {
                return null;
            }

            apiNewsDataItemList= apiNewsResponse2.getItem();
            if (apiNewsDataItemList != null && !apiNewsDataItemList.isEmpty()) {
                Collections.reverse(apiNewsDataItemList);
            }
        }

        if (apiNewsDataItemList != null && !apiNewsDataItemList.isEmpty()) {
            for (ApiNewsDataItemResponse apiNewsDataItem : apiNewsDataItemList) {
                if (apiNewsDataItem != null ) {
                    FinanceVipMoreArticleItemResponse response = new FinanceVipMoreArticleItemResponse();

                    String title = apiNewsDataItem.getTitle();
                    if (!StringUtil.isEmpty(title)) {
                        title = StringUtil.stripHtml(title);
                    }

                    List<String> tags_mul = apiNewsDataItem.getTags_mul();
                    if (tags_mul == null) {
                        tags_mul = new ArrayList<>();
                    }
                    if (!tags_mul.isEmpty() && !tagList.isEmpty()) {
                        tags_mul.retainAll(tagList);
                    }

                    String source_name = apiNewsDataItem.getSource_name_s();
                    if (!StringUtil.isEmpty(source_name)) {
                        source_name = StringUtil.stripHtml(source_name);
                    }

                    response.setId(apiNewsDataItem.getId());
                    response.setTitle(title);
                    response.setUrl(apiNewsDataItem.getUrl());
                    response.setTag(tags_mul);
                    response.setTime(apiNewsDataItem.getTime() * 1000L);
                    response.setSource_image(accountNameAndHeadMap.get(source_name));
                    response.setSource_name(source_name);
                    if(!wbchatid.isEmpty()){
                        response.setWechatid(wbchatid);
                    }else{
                        WechatPublicModel wechatPublicModel=wechatPublicDao.selectByName(source_name);
                        if(wechatPublicModel!=null){
                            response.setWechatid(wechatPublicModel.getId().toString());
                        }else{
                            response.setWechatid("");
                        }
                    }
                    item.add(response);
                }
            }
        }
        result.put("wechatid",wbchatid);
        result.put("total", total);
        result.put("item", item);
        return result;
    }
}
