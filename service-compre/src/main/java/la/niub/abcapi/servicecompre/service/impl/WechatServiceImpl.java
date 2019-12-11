package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.config.enums.WechatPeriodEnum;
import la.niub.abcapi.servicecompre.dao.reporter.IIndexBasicInfoDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISearchIndexDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISearchIndexDataDao;
import la.niub.abcapi.servicecompre.dao.reporter.IThemeMappingDao;
import la.niub.abcapi.servicecompre.dao.reporter.IWechatHotKeysDao;
import la.niub.abcapi.servicecompre.dao.reporter.IWechatOperationDao;
import la.niub.abcapi.servicecompre.dao.reporter.IWechatPublicDao;
import la.niub.abcapi.servicecompre.dao.reporter.IWechatStatisticsDao;
import la.niub.abcapi.servicecompre.model.*;
import la.niub.abcapi.servicecompre.model.bo.wechat.*;
import la.niub.abcapi.servicecompre.model.request.wechat.*;
import la.niub.abcapi.servicecompre.model.response.ApiNewsDataItemResponse;
import la.niub.abcapi.servicecompre.model.response.client.ApiNewsResponse;
import la.niub.abcapi.servicecompre.model.response.client.WechatArticleResponse;
import la.niub.abcapi.servicecompre.model.response.client.wechat.WechatArticleDataItemResponse;
import la.niub.abcapi.servicecompre.model.response.message.ArticleListItemResponse;
import la.niub.abcapi.servicecompre.model.response.message.ArticleResponse;
import la.niub.abcapi.servicecompre.model.response.wechat.WechatMoreItemResponse;
import la.niub.abcapi.servicecompre.model.response.wechat.WechatNewsResponse;
import la.niub.abcapi.servicecompre.model.response.wechat.WechatOriArticleListItem;
import la.niub.abcapi.servicecompre.model.response.wechat.WechatTagIndexItemResponse;
import la.niub.abcapi.servicecompre.service.ICardService;
import la.niub.abcapi.servicecompre.service.IWechatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WechatServiceImpl implements IWechatService {

    @Value("${feign.client.wechatArticle.url}")
    private String wechatArticleUrl;

    @Autowired
    ICardService cardService;

    @Autowired
    IIndexBasicInfoDao indexBasicInfoDao;

    @Autowired
    IThemeMappingDao themeMappingDao;

    @Autowired
    IWechatPublicDao wechatPublicDao;

    @Autowired
    IWechatStatisticsDao wechatStatisticsDao;

    @Autowired
    IWechatOperationDao wechatOperationDao;

    @Autowired
    IWechatHotKeysDao wechatHotKeysDao;

    @Autowired
    ISearchIndexDao searchIndexDao;

    @Autowired
    ISearchIndexDataDao searchIndexDataDao;

    @Override
    public WechatBO getDetail(Long code) {
        WechatBO wechatBO = new WechatBO();

        WechatPublicModel wechatPublicModel = wechatPublicDao.selectByPrimaryKey(code);
        if (wechatPublicModel == null){
            return wechatBO;
        }

        wechatBO.setId(wechatPublicModel.getId());
        wechatBO.setName(wechatPublicModel.getPublic_name());
        wechatBO.setAccount(wechatPublicModel.getPublic_account());
        wechatBO.setImage(wechatPublicModel.getPublic_image());
        wechatBO.setSummary(wechatPublicModel.getPublic_summary());
        wechatBO.setAuthentication(wechatPublicModel.getPublic_authentication());
        String tags = wechatPublicModel.getPublic_tags();
        wechatBO.setTags(Arrays.asList(tags.split(",")));
        wechatBO.setQrCode(wechatPublicModel.getQr_code());
        wechatBO.setMonthCount(wechatPublicModel.getMonth_count());
        wechatBO.setRank(wechatPublicModel.getRanking());
        wechatBO.setFans(wechatPublicModel.getFollower());
        wechatBO.setRead(wechatPublicModel.getReading());

        String classify = wechatPublicModel.getClassify();
        if (StringUtil.isEmpty(classify)) {
            classify = "未知";
        }
        wechatBO.setClassify(classify);

        return wechatBO;
    }

    @Override
    public List<WechatHotBO> getHot(Long code, String type,Integer limit) {
        List<WechatHotBO> wechatHotBOList = new ArrayList<>();

        WechatPublicModel wechatPublicModel = wechatPublicDao.selectByPrimaryKey(code);
        if (wechatPublicModel == null){
            return wechatHotBOList;
        }

        List<WechatPublicModel> wechatPublicModelList = new ArrayList<>();
        if (type.equals("CATEGORY") && StringUtils.isNotEmpty(wechatPublicModel.getClassify())){
            List<WechatPublicModel> list = wechatPublicDao.getByClassifyOrderByFollower(wechatPublicModel.getClassify(),limit+1);
            for (WechatPublicModel item : list){
                if (item.getId().equals(code)){
                    continue;
                }
                if (wechatPublicModelList.size() >= limit){
                    break;
                }
                wechatPublicModelList.add(item);
            }
        }else if (type.equals("TAG") && StringUtils.isNotEmpty(wechatPublicModel.getTags())){
            String[] tags = wechatPublicModel.getTags().split(",");
            List<WechatPublicModel> list = wechatPublicDao.getLikeTags(Arrays.asList(tags));
            List<WechatHotByTagBO> wechatHotByTagBOList = new ArrayList<>();
            for (WechatPublicModel item : list){
                if (item.getId().equals(code)){
                    continue;
                }
                List<String> tagList = new ArrayList<>(Arrays.asList(tags));
                List<String> itemTagList = Arrays.asList(item.getTags().split(","));
                tagList.retainAll(itemTagList);

                WechatHotByTagBO wechatHotByTagBO = new WechatHotByTagBO();
                wechatHotByTagBO.setSimilarity(tagList.size());
                wechatHotByTagBO.setModel(item);
                wechatHotByTagBOList.add(wechatHotByTagBO);
            }

            Collections.sort(wechatHotByTagBOList,new Comparator<WechatHotByTagBO>(){
                @Override
                public int compare(WechatHotByTagBO o1, WechatHotByTagBO o2) {
                    if (o1.getSimilarity().equals(o2.getSimilarity())){
                        return o2.getModel().getFollower().compareTo(o1.getModel().getFollower());
                    }
                    return o2.getSimilarity().compareTo(o1.getSimilarity());
                }
            });
            for (WechatHotByTagBO item : wechatHotByTagBOList){
                if (wechatPublicModelList.size() >= limit){
                    break;
                }
                wechatPublicModelList.add(item.getModel());
            }
        }else{
            wechatPublicModelList = wechatPublicDao.getByRandom(code.intValue(),limit);
        }

        if (ObjectUtils.isEmpty(wechatPublicModelList)) {
            return wechatHotBOList;
        }

        Map<String, String> params = new HashMap<>();
        params.put("single", "true");
        params.put("core_name", "core_news");
        params.put("limit", "1");
        params.put("offset", "0");
        params.put("prior","time");

        for (WechatPublicModel item : wechatPublicModelList) {
            WechatHotBO wechatHotBO = new WechatHotBO();
            wechatHotBO.setId(item.getId());
            wechatHotBO.setName(item.getPublic_name());
            wechatHotBO.setAvatar(item.getPublic_image());
            wechatHotBO.setReads(item.getReading());
            wechatHotBO.setTag(item.getPublic_tags());
            wechatHotBO.setCategory(item.getClassify());
            wechatHotBO.setNewsTag(item.getTags());
            wechatHotBO.setFans(item.getFollower());

            try {
                params.put("keyword", URLEncoder.encode("$source_name:\"" + item.getPublic_name() + "\"","UTF-8"));
                String wechatArtilceRet = HttpUtil.get(wechatArticleUrl, params, null);
                if (wechatArtilceRet != null && !wechatArtilceRet.isEmpty()) {
                    WechatArticleResponse wechatArticleResponse = JSON.parseObject(wechatArtilceRet, WechatArticleResponse.class);
                    if (wechatArticleResponse.getData() != null) {
                        if ( wechatArticleResponse.getData().getItem() != null && !wechatArticleResponse.getData().getItem().isEmpty()) {
                            WechatArticleDataItemResponse wechatArticleDataItemResponse = wechatArticleResponse.getData().getItem().get(0);
                            wechatHotBO.setNewestNewsId(wechatArticleDataItemResponse.getId());
                            wechatHotBO.setNewestNewsTitle(wechatArticleDataItemResponse.getTitle());
                            wechatHotBO.setNewestNewsUrl(wechatArticleDataItemResponse.getUrl());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            wechatHotBOList.add(wechatHotBO);
        }

        return wechatHotBOList;
    }

    @Override
    public List<String> getArticleTag(Long code) {
        List<String> list = new ArrayList<>();

        WechatPublicModel wechatPublicModel = wechatPublicDao.selectByPrimaryKey(code);
        if (wechatPublicModel == null){
            return list;
        }

        String tags = wechatPublicModel.getTags();
        if (StringUtils.isEmpty(tags)){
            return list;
        }

        return new LinkedList<>(new LinkedHashSet<>(Arrays.asList(tags.split(","))));
    }

    @Override
    public WechatArticleTypeBO getArticleType(Long code) {
        WechatArticleTypeBO wechatArticleTypeBO = new WechatArticleTypeBO();

//        WechatPublicModel wechatPublicModel = wechatPublicDao.selectByPrimaryKey(code);
//        if (wechatPublicModel == null || wechatPublicModel.getPublic_account() == null){
//            return wechatArticleTypeBO;
//        }

        WechatStatisticsModel wechatStatisticsModel = wechatStatisticsDao.selectByPrimaryKey(code);
        if (wechatStatisticsModel != null){
            wechatArticleTypeBO.setTotal(wechatStatisticsModel.getArticle_num());
            wechatArticleTypeBO.setOriginal(wechatStatisticsModel.getOriginal_num());
        }

        return wechatArticleTypeBO;
    }

    @Override
    public WechatIndexBO getIndex(Long code, Date startTime) {
        WechatIndexBO wechatIndexBO = new WechatIndexBO();

//        WechatPublicModel wechatPublicModel = wechatPublicDao.selectByPrimaryKey(code);
//        if (wechatPublicModel == null || wechatPublicModel.getPublic_account() == null){
//            return wechatIndexBO;
//        }
        Integer date = startTime != null ? Long.valueOf(startTime.getTime()/1000).intValue() : 0;

        Map<Long,Integer> total = new TreeMap<>();
        Map<Long,Integer> top = new TreeMap<>();
        List<WechatOperationModel> wechatOperationModelList = wechatOperationDao.getByWechatIdAndDate(code,date);
        for (WechatOperationModel item : wechatOperationModelList){
            if (item.getDate() == null){
                continue;
            }
            total.put(item.getDate()*1000L,item.getTotal_read_num());
            top.put(item.getDate()*1000L,item.getTop_read_num());
        }
        wechatIndexBO.setTotal(total);
        wechatIndexBO.setTop(top);
        return wechatIndexBO;
    }

    @Override
    public WechatPublicModel getByName(String name) {
        return wechatPublicDao.selectByName(name.trim());
    }

    @Override
    public List<WechatHottagBO> getHottag(Long code, WechatPeriodEnum periodEnum) {
        List<WechatHottagBO> wechatHottagBOList = new ArrayList<>();

        WechatHotKeysModel wechatHotKeysModel = wechatHotKeysDao.selectByPrimaryKey(code);
        if (wechatHotKeysModel == null){
            return wechatHottagBOList;
        }
        String json = wechatHotKeysModel.getHot_keys();
//        String json = "{\"7\": {\"\\u65b0\\u89c4\": 0.0, \"\\u7406\\u8d22\": 0.0, \"\\u7406\\u8d22\\u4ea7\\u54c1\": 0.0, \"\\u75ab\\u82d7\": 0.0, \"\\u5238\\u5546\": -0.6363636363636364, \"\\u957f\\u751f\": 0.0, \"\\u751f\\u7269\": 0.0, \"\\u957f\\u6625\": 0.0}, " +
//                "\"30\": {\"\\u5238\\u5546\": 0.175, \"\\u7406\\u8d22\": 0.0, \"\\u65b0\\u89c4\": 0.0, \"\\u7406\\u8d22\\u4ea7\\u54c1\": 0.0, \"\\u75ab\\u82d7\": 0.0, \"\\u8bc1\\u76d1\\u4f1a\": 0.3333333333333333, \"\\u5185\\u5e55\": 0.0, \"\\u9999\\u6e2f\": 0.0}, " +
//                "\"90\": {\"\\u5238\\u5546\": -0.24516129032258063, \"\\u7406\\u8d22\": 0.0, \"\\u8bc1\\u76d1\\u4f1a\": 0.7272727272727273, \"\\u65b0\\u89c4\": 0.0, \"\\u7406\\u8d22\\u4ea7\\u54c1\": 0.0, \"\\u75ab\\u82d7\": 0.0, \"\\u9999\\u6e2f\": 0.2, \"\\u5185\\u5e55\": 0.0}}";
        Map<String, Map<String,Float>> parseRet = JSON.parseObject(json,new TypeReference<Map<String, Map<String,Float>>>(){});
        Map<String,Float> periodRet = null;
        switch (periodEnum){
            case W1:
                periodRet = parseRet.get("7");
                break;
            case M1:
                periodRet = parseRet.get("30");
                break;
            case M3:
                periodRet = parseRet.get("90");
                break;
            default:
                return wechatHottagBOList;
        }

        for (Map.Entry<String,Float> entry : periodRet.entrySet()){
            WechatHottagBO wechatHottagBO = new WechatHottagBO();
            wechatHottagBO.setName(entry.getKey());
            wechatHottagBO.setRate(entry.getValue());
            wechatHottagBOList.add(wechatHottagBO);
        }

        return wechatHottagBOList;
    }

    @Override
    public List<WechatOriArticleListItem> oriArticle(WechatOriArticleRequest wechatOriArticleRequest) throws Throwable {
        Long code = wechatOriArticleRequest.getCode();
        String tag = wechatOriArticleRequest.getTag();
        WechatPublicModel wechatPublicModel = wechatPublicDao.selectByPrimaryKey(code);
        if (wechatPublicModel == null) {
            return null;
        }
        if (tag == null || tag.isEmpty()) {
            tag = wechatPublicModel.getTags();
        }
        List<WechatOriArticleListItem> result =  new ArrayList<>();
        WechatArticleResponse wechatArticleResponse = wechatArticle(wechatPublicModel.getPublic_name(), tag, 0, 0 , 0, 6);
        if (wechatArticleResponse.getData() != null) {
            if (wechatArticleResponse.getData().getItem() != null && !wechatArticleResponse.getData().getItem().isEmpty()) {

                List<WechatArticleDataItemResponse> articleList = wechatArticleResponse.getData().getItem();
                if (articleList != null && !articleList.isEmpty()) {
                    for (WechatArticleDataItemResponse aItem : articleList) {
                        WechatOriArticleListItem rItem = new WechatOriArticleListItem();
                        rItem.setId(aItem.getId());
                        rItem.setTitle(aItem.getTitle());
                        rItem.setUrl(aItem.getUrl());
                        rItem.setTime(aItem.getTime());
                        result.add(rItem);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public Map<Integer, WechatTagIndexItemResponse> tagIndex(WechatTagIndexRequest params) throws Throwable {
        Long code = params.getCodes();
        String tag = params.getTags();

        WechatPublicModel wechatPublicModel = wechatPublicDao.selectByPrimaryKey(code);
        if (wechatPublicModel == null) {
            return null;
        }

        if (tag == null || tag.isEmpty()) {
            return null;
        }
        SearchIndexModel searchIndex = searchIndexDao.selectByKeyword(tag);
        Map<Integer, WechatTagIndexItemResponse> result = new LinkedHashMap<>();
        if (searchIndex != null) {
            Long keywordId = searchIndex.getKeyword_id();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String date = df.format(new Date());
            Date d = df.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            calendar.add(Calendar.MONTH, -3);
            Date beginDate = calendar.getTime();
            int timeUnix = (int)(beginDate.getTime() / 1000);
            List<SearchIndexDataModel> dataList = searchIndexDataDao.selectByKeywordIdAndDate(keywordId, beginDate);
            if (dataList != null && !dataList.isEmpty()) {
                for (SearchIndexDataModel dataItem : dataList) {
                    int dataTimeUnix = (int)(dataItem.getData_time().getTime() / 1000);
                    WechatTagIndexItemResponse mapItem = new WechatTagIndexItemResponse();
                    mapItem.setIndex(dataItem.getSearch_index());
                    result.put(dataTimeUnix, mapItem);
                }
            }
            WechatArticleResponse wechatArticleResponse = wechatArticle(wechatPublicModel.getPublic_name(), tag, timeUnix, 0 , 0, 100);
            if (wechatArticleResponse.getData() != null) {
                if ( wechatArticleResponse.getData().getItem() != null && !wechatArticleResponse.getData().getItem().isEmpty()) {

                    List<WechatArticleDataItemResponse> articleList =  wechatArticleResponse.getData().getItem();

                    if (articleList != null && !articleList.isEmpty()) {
                        SimpleDateFormat  adf = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar c =Calendar.getInstance();
                        for (WechatArticleDataItemResponse aItem :articleList) {
                            long millions=new Long(aItem.getTime()).longValue()*1000;
                            c.setTimeInMillis(millions);
                            String aDateStr = adf.format(c.getTime());
                            int aTime = (int)(adf.parse(aDateStr).getTime() / 1000);
                            if (result.containsKey(aTime)) {
                                WechatTagIndexItemResponse aVal = result.get(aTime);
                                aVal.setArticleCount(aVal.getArticleCount() + 1);
                                if (aVal.getArticleTitle() != null) {
                                    aVal.setArticleId(aItem.getId());
                                    aVal.setArticleTitle(aItem.getTitle());
                                    aVal.setArticleUrl(aItem.getUrl());
                                }
                                result.replace(aTime, aVal);
                            } else {
                                WechatTagIndexItemResponse aVal = new WechatTagIndexItemResponse();
                                aVal.setArticleCount(1);
                                aVal.setArticleId(aItem.getId());
                                aVal.setArticleTitle(aItem.getTitle());
                                aVal.setArticleUrl(aItem.getUrl());
                                result.put(aTime, aVal);
                            }
                        }

                        Map<Integer, WechatTagIndexItemResponse> sortMap = new TreeMap<Integer, WechatTagIndexItemResponse>(
                                new MapKeyComparator());

                        sortMap.putAll(result);
                        return sortMap;

                    }

                }
            }



        }


        return result;
    }

    class MapKeyComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer str1, Integer str2) {

            return str1.compareTo(str2);
        }
    }

    private WechatArticleResponse wechatArticle(String source, String tag, int startTime, int endTime, int offset, int limit) {
        Map<String, String> params = new HashMap<>();
        params.put("single", "true");
        params.put("core_name", "core_news");

        StringBuffer keywordBuffer = new StringBuffer();
        keywordBuffer.append("$channel:\"微信\"");
        if (source != null && !source.isEmpty()) {
            String[] ss = source.split(",");
            keywordBuffer.append(" AND ")
                        .append("source_name_s:(");
            for (String s : ss) {
                if (keywordBuffer.length() + s.length() > 250) {
                    break;
                }
                keywordBuffer.append(s).append(" ");
            }
            keywordBuffer.append(")");

        }
        if (tag != null && !tag.isEmpty()) {
            String[] tags = tag.split(",");
            keywordBuffer.append(" AND ")
                        .append("tags_mul:(");
            for (String t : tags) {
                if (keywordBuffer.length() + t.length() > 250) {
                    break;
                }
                keywordBuffer.append(t).append(" ");
            }
            keywordBuffer.append(")");

        }
        try {
            params.put("keyword",URLEncoder.encode(keywordBuffer.toString(),"UTF-8").replace("+", "%20"));
        } catch (UnsupportedEncodingException e) {

        }
        if (startTime > 0 ) {
            params.put("start_time", String.valueOf(startTime));
        }
        if (endTime > 0 ) {
            params.put("end_time", String.valueOf(endTime));
        }
        if (limit >= 0) {
            params.put("limit", String.valueOf(limit));
        }

        if (offset >= 0) {
            params.put("offset", String.valueOf(offset));
        }

        params.put("prior","time");

        String wechatArtilceRet = HttpUtil.get(wechatArticleUrl, params, null);

        if (wechatArtilceRet != null && !wechatArtilceRet.isEmpty()) {
            return JSON.parseObject(wechatArtilceRet, WechatArticleResponse.class);
        }
        return null;
    }


    public ArticleResponse article(WechatArticleRequest params) throws Throwable {
//        Long code = params.getCode();
        String codes = params.getCodes();
        String tag = params.getTags();
//        int page = params.getPage();
        if (tag == null || tag.isEmpty()) {
            return null;
        }
//        if (page <= 0) {
//            return null;
//        }
//        WechatPublicModel wechatPublicModel = null;
//        if (code > 0 ) {
//            wechatPublicModel = wechatPublicDao.selectByPrimaryKey(code);
//            if (wechatPublicModel == null) {
//                return null;
//            }
//        }
//        int pageLimit  = 6;
//        int pageOffset = (page - 1) * pageLimit;
        int total = 0;
        int pageCount = 0;
//        String source = wechatPublicModel == null ? "" : wechatPublicModel.getPublic_name();

        List<Long> ids = new ArrayList<>();

        if (codes != null && !codes.isEmpty()) {
            String[] cs = codes.split(",");
            for (String c : cs) {
                if (!c.isEmpty()) {
                    ids.add(Long.valueOf(c));
                }
            }
        }
        Map<String, WechatPublicModel> wechatPublicModelMap = new HashMap<>();
        StringBuffer cb = new StringBuffer();
        if (!ids.isEmpty()) {
            List<WechatPublicModel> list1 = wechatPublicDao.getListByIds(ids);
            if (list1 != null && !list1.isEmpty()) {
                for (WechatPublicModel item1 : list1) {
                    if (item1.getPublic_name() != null && !item1.getPublic_name().isEmpty()) {
                        cb.append(",").append(item1.getPublic_name());
                        if (!wechatPublicModelMap.containsKey(item1.getPublic_name())) {
                            wechatPublicModelMap.put(item1.getPublic_name(), item1);
                        }
                    }
                }
            }
        }

        String source = cb.toString();
        source = source.length() > 0 ? source.substring(1) : "";


        WechatArticleResponse wechatArticleResponse = wechatArticle(source, tag, 0, 0 , 0, 30);
        ArticleResponse result = new ArticleResponse();
        List<ArticleListItemResponse> articleResponseList = new ArrayList<>();
        if (wechatArticleResponse.getData() != null) {
            total = wechatArticleResponse.getData().getTotal_count();
//            pageCount = (int)Math.ceil((double) total / (double) pageLimit);
            if (wechatArticleResponse.getData().getItem() != null && !wechatArticleResponse.getData().getItem().isEmpty()) {

                List<String> names = new ArrayList<>();

                for (WechatArticleDataItemResponse aItem : wechatArticleResponse.getData().getItem()) {
                    ArticleListItemResponse articleItemResponse = new ArticleListItemResponse();
                    articleItemResponse.setId(aItem.getId());
                    articleItemResponse.setTitle(aItem.getTitle());
                    articleItemResponse.setUrl(aItem.getUrl());
                    articleItemResponse.setTime(aItem.getTime());
                    articleItemResponse.setRead(0);


                    List<String> atags = new ArrayList<>();
                    if (tag != null && !tag.isEmpty() && aItem.getTags_mul() != null && !aItem.getTags_mul().isEmpty()) {
                        String[] tags = tag.split(",");
                        for (String t : tags) {
                            for (String tm : aItem.getTags_mul()) {
                                if (t.trim().equals(tm.trim())) {
                                    atags.add(t.trim());
                                }
                            }
                        }
                    }

                    articleItemResponse.setTags(atags);
                    //查询微信公众号id
                    WechatPublicModel wechatPublicModel=wechatPublicDao.selectByName(aItem.getSource_name());
                    if(wechatPublicModel!=null){
                        articleItemResponse.setWechatid(wechatPublicModel.getId().toString());
                    }else{
                        articleItemResponse.setWechatid("");
                    }

                    if (aItem.getSource_name_s() != null && !aItem.getSource_name_s().isEmpty()){
                        if (!wechatPublicModelMap.containsKey(aItem.getSource_name_s())) {
                            names.add(aItem.getSource_name_s());
                        }
                        articleItemResponse.setSource(aItem.getSource_name_s());
                    }

                    articleResponseList.add(articleItemResponse);
                }

                if (!names.isEmpty()) {
                    List<WechatPublicModel> list2 = wechatPublicDao.getListByNames(names);
                    if (list2 != null && !list2.isEmpty()) {
                        for (WechatPublicModel item2 : list2) {
                            if (!wechatPublicModelMap.containsKey(item2.getPublic_name())) {
                                wechatPublicModelMap.put(item2.getPublic_name(), item2);
                            }
                        }
                    }
                }

                if (!wechatPublicModelMap.isEmpty()) {
                    for (ArticleListItemResponse aItem2: articleResponseList) {
                        if (wechatPublicModelMap.containsKey(aItem2.getSource())) {
                            WechatPublicModel wechatPublicModel1 = wechatPublicModelMap.get(aItem2.getSource());
                            aItem2.setSource_id(wechatPublicModel1.getId().intValue());
                            aItem2.setSource_image(wechatPublicModel1.getPublic_image());
                        }
                    }
                }
            }
        }



        result.setList(articleResponseList);
        result.setTotal(total);
        result.setPageCount(pageCount);
        return result;
    }

    public List<WechatSourceBO> articleSource(WechatArticleSourceRequest params) {
        String tag = params.getTags();
        if (tag == null || tag.isEmpty()) {
            return null;
        }
        List<WechatSourceBO> result = new ArrayList<>();
        WechatArticleResponse wechatArticleResponse = wechatArticle("", tag, 0, 0 , 0, 30);
        if (wechatArticleResponse.getData() != null) {
            if (wechatArticleResponse.getData().getItem() != null && !wechatArticleResponse.getData().getItem().isEmpty()) {
                List<String> names = new ArrayList<>();

                for (WechatArticleDataItemResponse aItem : wechatArticleResponse.getData().getItem()) {


                    if (aItem.getSource_name_s() != null && !aItem.getSource_name_s().isEmpty()){
                        if (!names.contains(aItem.getSource_name_s())) {
                            names.add(aItem.getSource_name_s());
                        }

                    }
                }

                if (!names.isEmpty()) {
                    List<WechatPublicModel> list2 = wechatPublicDao.getListByNames(names);
                    if (list2 != null && !list2.isEmpty()) {
                        for (WechatPublicModel item2 : list2) {
                            WechatSourceBO wechatSourceBO = new WechatSourceBO();
                            wechatSourceBO.setId(item2.getId().intValue());
                            wechatSourceBO.setName(item2.getPublic_name());
                            result.add(wechatSourceBO);
                        }
                    }
                }

            }

        }
        return result;
    }

    @Override
    public Set<String> getMoreTag(String tag_prefix) throws Exception {
        Set<String> result = new HashSet<>();

        List<String> tagList = wechatPublicDao.getTagByTagPrefix(tag_prefix);

        if (tagList == null || tagList.isEmpty()) {
            return result;
        }

        for (String tagStr : tagList) {
            if (!StringUtil.isEmpty(tagStr)) {
                if (StringUtil.isEmpty(tag_prefix)) {
                    result.addAll(Arrays.asList(tagStr.split(",")));
                } else {
                    List<String> tagStrList = Arrays.asList(tagStr.split(","));
                    for (String tag : tagStrList) {
                        if (tag.startsWith(tag_prefix)) {
                            result.add(tag);
                        }
                    }
                }
            }
        }

        return result;
    }

    @Override
    public List<String> getMoreClassify() throws Exception {
        return wechatPublicDao.getAllClassify();
    }

    @Override
    public Map<String, Object> getMoreWechat(WechatMoreRequest wechatMoreRequest) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<String> tagList = new ArrayList<>();
        String tags = wechatMoreRequest.getTags();
        if (!StringUtil.isEmpty(tags)) {
            tagList.addAll(Arrays.asList(tags.split(",")));
        }

        List<String> wechatAccountList = new ArrayList<>();
        if (!tagList.isEmpty()) {
            wechatAccountList = wechatPublicDao.getAccountByTagListAndClassiftAndName(tagList);
            if (wechatAccountList == null || wechatAccountList.isEmpty()) {
                result.put("total", 0);
                result.put("item", new ArrayList<>());
                return result;
            }
        }

        Integer count = wechatPublicDao.getCountByAccountList(wechatAccountList, wechatMoreRequest.getClassify(), wechatMoreRequest.getPublic_name());
        if (count == null || count == 0) {
            result.put("total", 0);
            result.put("item", new ArrayList<>());
            return result;
        }

        StringBuilder order = new StringBuilder();
        String orderField = wechatMoreRequest.getOrder_field();
        String orderType = wechatMoreRequest.getOrder_type();
        if (StringUtil.isEmpty(orderType) || (!"desc".equals(orderType) && !"asc".equals(orderType))) {
            orderType = "desc";
        }

        if (StringUtil.isEmpty(orderField) || (!"month_count".equals(orderField) && !"follower".equals(orderField))) {
            orderField = "month_count";
        }

        if ("month_count".equals(orderField)) {
            order.append(orderField).append(" ").append(orderType).append(",").append("follower ").append(orderType).append(",");
        } else {
            order.append(orderField).append(" ").append(orderType).append(",").append("month_count ").append(orderType).append(",");
        }

        if (StringUtil.isEmpty(order.toString().trim())) {
            order.append("month_count desc, follower desc, ");
        }
        order.append("id desc");

        Integer limit = wechatMoreRequest.getLimit();
        if (limit == null || limit < 1) {
            limit = 12;
        }
        Integer page_num = wechatMoreRequest.getPage_num();
        if (page_num == null || page_num < 1) {
            page_num = 1;
        }
        Integer index = (page_num - 1) * limit;

        if (index >= count) {
            result.put("item", new ArrayList<>());
            result.put("total", count);
            return result;
        }

        List<WechatPublicMoreModel> list = wechatPublicDao.getListByAccountList(wechatAccountList, wechatMoreRequest.getClassify(), wechatMoreRequest.getPublic_name(), order.toString(), index, limit);

        List<WechatMoreItemResponse> itemList = new ArrayList<>();
        for (WechatPublicMoreModel model : list) {
            WechatMoreItemResponse item = new WechatMoreItemResponse();
            item.setKey(model.getId());
            item.setId(model.getId());
            item.setPublic_name(model.getPublic_name());
            item.setPublic_account(model.getPublic_account());
            item.setPublic_image(model.getPublic_image());
            item.setClassify(model.getClassify());

            Set<String> itemTagList = new LinkedHashSet<>();
            String theme_name = model.getTheme_name();
            if (!StringUtil.isEmpty(theme_name)) {
                itemTagList.addAll(Arrays.asList(theme_name.split(",")));
            }

            String itemTags = model.getPublic_tags();
            if (!StringUtil.isEmpty(itemTags)) {
                itemTagList.addAll(Arrays.asList(itemTags.split(",")));
            }

            if (!tagList.isEmpty() && !itemTagList.isEmpty()) {
                List<String> tagListCopy = new ArrayList<>();
                tagListCopy.addAll(tagList);
                tagListCopy.retainAll(itemTagList);
                Set<String> itemTagSet = new LinkedHashSet<>(tagListCopy);
                itemTagSet.addAll(itemTagList);
                itemTagList = itemTagSet;
            }
            item.setTags(new ArrayList<>(itemTagList));

            item.setMonth_count(model.getMonth_count());
            item.setFollower(model.getFollower());
            item.setQr_code(model.getQr_code());

            String keyword = "$source_name_s:(\"" + model.getPublic_name().trim() + "\") AND channel:\"微信\"";
            Map<String, String> newsRequest = new HashMap<>();
            newsRequest.put("keyword", keyword);
            newsRequest.put("offset", "0");
            newsRequest.put("limit", "1");
            newsRequest.put("prior", "time");
            newsRequest.put("core_name", "core_news");
            newsRequest.put("single", "false");

            String apiNewsRet = HttpUtil.post(wechatArticleUrl, newsRequest, null);

            if (StringUtil.isEmpty(apiNewsRet)) {
                item.setNews(null);
                itemList.add(item);
                continue;
            }

            ApiNewsResponse apiNewsRetObject = JSON.parseObject(apiNewsRet, ApiNewsResponse.class);
            if (apiNewsRetObject == null || apiNewsRetObject.getErr_code() != 0) {
                item.setNews(null);
                itemList.add(item);
                continue;
            }

            if (apiNewsRetObject.getData() == null) {
                item.setNews(null);
                itemList.add(item);
                continue;
            }

            List<ApiNewsDataItemResponse> newsList = apiNewsRetObject.getData().getItem();

            if (newsList == null || newsList.isEmpty()) {
                item.setNews(null);
                itemList.add(item);
                continue;
            }

            ApiNewsDataItemResponse apiNewsDataItemResponse = newsList.get(0);
            if (apiNewsDataItemResponse != null ) {
                WechatNewsResponse news = new WechatNewsResponse();

                String title = apiNewsDataItemResponse.getTitle();
                if (!StringUtil.isEmpty(title)) {
                    title = StringUtil.stripHtml(title);
                }

                news.setId(apiNewsDataItemResponse.getId());
                news.setTitle(title);
                news.setUrl(apiNewsDataItemResponse.getUrl());

                item.setNews(news);
            } else {
                item.setNews(null);
            }

            itemList.add(item);

        }

        result.put("item", itemList);
        result.put("total", count);

        return result;
    }
}
