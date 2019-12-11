package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import la.niub.abcapi.servicecompre.component.cache.KeyBuilder;
import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.ObjectUtil;
import la.niub.abcapi.servicecompre.component.util.RedisUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.dao.IFinanceJuchaoItemDao;
import la.niub.abcapi.servicecompre.dao.IJuchaoTextDao;
import la.niub.abcapi.servicecompre.dao.notice.IAnalystBasicinfoDao;
import la.niub.abcapi.servicecompre.dao.notice.IAnalystChanStatusDao;
import la.niub.abcapi.servicecompre.dao.reporter.ISecBasicInfoDao;
import la.niub.abcapi.servicecompre.model.AnalystBasicinfoModel;
import la.niub.abcapi.servicecompre.model.bo.*;
import la.niub.abcapi.servicecompre.model.cache.ReportCategoryFlatModel;
import la.niub.abcapi.servicecompre.model.nosql.FinanceJuchaoItemModel;
import la.niub.abcapi.servicecompre.model.nosql.JuchaoTextModel;
import la.niub.abcapi.servicecompre.model.request.NoticeSearch2Request;
import la.niub.abcapi.servicecompre.model.request.SearchFinanceCardRequest;
import la.niub.abcapi.servicecompre.model.request.SearchGeneralRequest;
import la.niub.abcapi.servicecompre.model.response.client.notice.NoticeSearch2Data;
import la.niub.abcapi.servicecompre.model.response.client.notice.NoticeSearch2Item;
import la.niub.abcapi.servicecompre.model.response.client.report.ReportAanlystHonorData;
import la.niub.abcapi.servicecompre.service.IHistroyService;
import la.niub.abcapi.servicecompre.service.IReportManagerService;
import la.niub.abcapi.servicecompre.service.ISearchService;
import la.niub.abcapi.servicecompre.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Service
public class SearchServiceImpl implements ISearchService {

    private static final Logger logger = LogManager.getLogger(SearchServiceImpl.class);

    @Value("${solr.common_search_api}")
    private String commonSearchApi;

    @Value("${solr.card_search_api}")
    private String cardSearchApi;

    @Value("${solr.card_finance_api}")
    private String cardFinanceApi;

    @Value("${solr.card_finance2_api}")
    private String cardFinance2Api;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IFinanceJuchaoItemDao financeJuchaoItemDao;

    @Autowired
    private IJuchaoTextDao juchaoTextDao;

    @Autowired
    IReportManagerService reportManagerService;

    @Autowired
    IUserService userService;

    @Autowired
    ISecBasicInfoDao secBasicInfoDao;

    @Autowired
    IHistroyService histroyService;

    @Autowired
    IAnalystChanStatusDao analystChanStatusDao;

    @Autowired
    IAnalystBasicinfoDao analystBasicinfoDao;

    @Value("${oss.file_server_host}")
    private String ossFileHost;


    @Autowired
    private HttpServletRequest httpServletRequest;


    public String getUserAgent() {
        return httpServletRequest.getHeader("user-agent");
    }

    @Override
    public List<Object> general(SearchGeneralRequest searchResultRequest) throws Throwable {

        Map<String, String> params = new HashMap<>();
        String keyword = searchResultRequest.getKeyword();
        keyword = keyword.replace("#", "%23");
        keyword = URLEncoder.encode(keyword,"utf-8");
        params.put("keyword", keyword);
        params.put("limit_chart", String.valueOf(searchResultRequest.getLimit_chart()));
        params.put("limit_table", String.valueOf(searchResultRequest.getLimit_table()));
        params.put("limit_report", String.valueOf(searchResultRequest.getLimit_report()));
        params.put("limit_notice", String.valueOf(searchResultRequest.getLimit_notice()));
        params.put("limit_news", String.valueOf(searchResultRequest.getLimit_news()));
        params.put("limit_edb", String.valueOf(searchResultRequest.getLimit_edb()));
        params.put("limit_stocks", String.valueOf(searchResultRequest.getLimit_stocks()));

        params.put("offset_news",String.valueOf(searchResultRequest.getOffset_news()));

        params.put("type",String.valueOf(searchResultRequest.getType()));

        params.put("code",String.valueOf((searchResultRequest.getCode())));



        //user_id
        String userId = searchResultRequest.getUserId();
        params.put("user_id", userId);


        //request_id
        String request_id = searchResultRequest.getRequest_id();
        params.put("request_id",request_id);

        //input_from
        params.put("input_from",searchResultRequest.getInput_from());

        //device_info
        String dervice_info = getUserAgent();
        dervice_info = URLEncoder.encode(dervice_info,"utf-8");
        params.put("device_info",dervice_info);

        //page
        params.put("page",searchResultRequest.getPage());

        // UserSearchHistory
        histroyService.recordSearchHistory( userId, searchResultRequest.getKeyword(),
                searchResultRequest.getInput_from(), "comprehensive_search");

        String resultStr = HttpUtil.get(commonSearchApi, params, null);
        if (StringUtils.isEmpty(resultStr)) {
            throw new ServiceException(500, "common search solr error");
        }
        SolrCommonSearchBo resultObj = JSON.parseObject(resultStr, SolrCommonSearchBo.class);

//        logger.info("common search api request :" + JSON.toJSONString(params)+ " response :" + resultStr);
        // solr异常
        if (resultObj.getError_code() != 0) {
            throw new ServiceException(500, "common search solr error");
        }

        List<Object> generalData = new ArrayList<>();
        List<SolrCommonSearchDataElementbo> data = resultObj.getData();
        if (data != null && !data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                SolrCommonSearchDataElementbo element = data.get(i);
                Object resultItem = null;
                if (element.getSource().equals("notice") && element.getResult().getData() != null) {
                    SolrCommonSearchDataElementbo obj = new SolrCommonSearchDataElementbo();
                    NoticeReadingBo noticeReading = new NoticeReadingBo();
                    noticeReading.setOrder_by("all_score");
                    noticeReading.setOffset(0);
                    noticeReading.setLimit(searchResultRequest.getLimit_notice());
                    noticeReading.setGroup_by("default");
                    NoticeReturnDataElementResultBo resObj = new NoticeReturnDataElementResultBo();
                    resObj.setData(processNotice(JSON.parseObject(JSON.toJSONString(element.getResult().getData()), SolrCommonSearchDataNoticeDataBo.class), noticeReading));
                    obj.setSource("notice");
                    obj.setScore(element.getScore());
                    obj.setResult(resObj);
                    resultItem = obj;
                } else if (element.getSource().equals("report") && element.getResult().getData() != null) {
                    SolrCommonSearchDataElementbo obj = new SolrCommonSearchDataElementbo();
                    ReportRequest request = new ReportRequest();
                    request.setLimit(searchResultRequest.getLimit_report());
                    request.setOffset(0);
                    NoticeReturnDataElementResultBo resObj = new NoticeReturnDataElementResultBo();
                    resObj.setData(parseRaaReportSearchResult(JSON.parseObject(JSON.toJSONString(element.getResult().getData()), ReportData.class), request, false));
                    obj.setSource("report");
                    obj.setScore(element.getScore());
                    obj.setResult(resObj);
                    resultItem = obj;
                } else {
                    resultItem = element;
                }

                generalData.add(resultItem);
            }
        }

        return generalData;
    }

    @Override
    public Object card(SearchGeneralRequest searchResultRequest) throws Throwable {
        Map<String, String> params = new HashMap<>();
        String keyword = searchResultRequest.getKeyword();
        keyword = keyword.replace("#", "%23");
        keyword = URLEncoder.encode(keyword,"utf-8");
        params.put("keyword", keyword);
        params.put("type",String.valueOf(searchResultRequest.getType()));
        params.put("code",String.valueOf((searchResultRequest.getCode())));
        String resultStr = HttpUtil.get(cardSearchApi, params, null);
        if (StringUtils.isEmpty(resultStr)) {
            throw new ServiceException(500, "card search solr error");
        }
        SolrCardSearchBo resultObj = JSON.parseObject(resultStr, SolrCardSearchBo.class);
        // solr异常
        if (resultObj.getErr_code() != 0) {
            throw new ServiceException(500, "card search solr error");
        }
        return resultObj.getData();
    }

    /**
     * 南京团队 择股
     * @param searchResultRequest
     * @return
     * @throws Throwable
     */
    @Override
    public Object cardFinance(SearchGeneralRequest searchResultRequest) throws Throwable {
        Map<String, String> params = new HashMap<>();
        String keyword = searchResultRequest.getKeyword();
        keyword = keyword.replace("#", "%23");
        keyword = URLEncoder.encode(keyword,"utf-8");
        params.put("keyword", keyword);
        params.put("userId", searchResultRequest.getUserId());
        params.put("token", searchResultRequest.getToken());
        String resultStr = HttpUtil.get(cardFinanceApi, params, null);
        if (resultStr != null && !resultStr.isEmpty()) {
            return JSON.parseObject(resultStr);
        }
        return null;
    }

    /**
     * 南京团队 择股指标命中第2次调用
     * @param request
     * @return
     * @throws Throwable
     */
    @Override
    public Object cardFinanceShare(SearchFinanceCardRequest request) throws Throwable {
        if( request.getIndicators() == null || request.getBeginTime() == null || request.getEndTime()==null ||
            request.getCodes() == null || request.getTimeRangeList() == null) {
            return null;
        }
        Map<String, String> params = new HashMap<>();
        params.put("indicators", URLEncoder.encode(URLDecoder.decode(request.getIndicators(),"UTF-8"),"UTF-8"));
        params.put("beginTime", request.getBeginTime());
        params.put("endTime", request.getEndTime());
        params.put("codes", URLEncoder.encode(URLDecoder.decode(request.getCodes(),"UTF-8"),"utf-8"));
        params.put("timeRangeList", URLEncoder.encode(URLDecoder.decode(request.getTimeRangeList(),"UTF-8"),"UTF-8"));
        params.put("userId", request.getUserId());
        params.put("token", request.getToken());
        params.put("request_id", request.getRequest_id());
        String resultStr = HttpUtil.get(cardFinance2Api, params, null);
        if (resultStr != null && !resultStr.isEmpty()) {
            return JSON.parseObject(resultStr);
        }
        return null;
    }

    private String typeConfigName (String typeName) {

        Map<String, String> typeConfig = new HashMap<>();
        typeConfig.put("Industry", "上市公司");
        typeConfig.put("PublicCompany", "行业");
        typeConfig.put("FoundCompany", "基金公司");
        typeConfig.put("Theme", "主题");
        typeConfig.put("FoundManager", "基金经理");
        typeConfig.put("Analyst", "分析师");
        typeConfig.put("CorporateExecutive", "公司高管");
        typeConfig.put("Broker", "券商");
        typeConfig.put("WeChatAccount", "公众号");
        typeConfig.put("FinanceVIP", "金融大V");

        return typeConfig.get(typeName);
    }

    private NoticeReturnBo processNotice(SolrCommonSearchDataNoticeDataBo noticeData, NoticeReadingBo noticeReading) {

        NoticeReturnBo noticeReturn = new NoticeReturnBo();
        /* 综合搜索页面没发现用到newCount
        String categories = noticeReading.getCategories();
        int newCount;
        if (categories == null || categories.isEmpty()) {
            newCount = 0;
        } else {
            if (categories.split(",").length == 1) {

                newCount = getNewCountById(categories);
            } else {
                newCount = 0;
            }
        }
        noticeReturn.setNew_count(newCount);
        */
        noticeReturn.setRequest_id(noticeData.getRequest_id());
        noticeReturn.setCurrent_page(noticeReading.getOffset() /  noticeReading.getLimit() + 1);
        noticeReturn.setTotal_count(noticeData.getTotal_count());
        noticeReturn.setTotal_page((int)Math.ceil(noticeData.getTotal_count() / noticeReading.getLimit()));
        noticeReturn.setKeyword(noticeData.getNlp_str());
        noticeReturn.setAbcscore(noticeData.getAbcscore());

        if (noticeReading.getIfUnderstand() == 1 && noticeReturn.getKeyword().isEmpty() && !noticeReading.getStock_code().isEmpty()) {
            noticeReturn.setKeyword(noticeReading.getKeyword_filter());
            noticeReturn.setSearchCompany(noticeReading.getKeyword_filter());
        }

        if (noticeData.getItem() == null) {
            noticeReturn.setM_keyword(noticeReturn.getKeyword());
            if (noticeReading.getIfUnderstand() == 0) {
                noticeReturn.setKeyword("");
            }
        }

        List<String> srcIds = new ArrayList<>();
        for (SolrCommonSearchDataNoticeDataItemBo dataItem : noticeData.getItem()) {
            srcIds.add(dataItem.getSrc_id());
        }
        /**
         * 不需要额外取摘要
         */
/*        String delimiter;
        if (noticeReading.getDelimiter() == 1) {
            delimiter = "\n";
        } else {
            delimiter = "。";
        }

        Map<String, List<String>> paraFromMongo = getParaFromMongo(srcIds, delimiter);

        List<String> keywords = Arrays.asList(noticeReturn.getKeyword().split(","));
        */
        if (noticeData.getItem() != null) {
            List<NoticeReturnItemBo> noticeReturnItemList = new ArrayList<>();
            for (SolrCommonSearchDataNoticeDataItemBo item : noticeData.getItem()) {
                List<String> paragraph = new ArrayList<>();

          /*      List<String> match = matchedParagraph(paraFromMongo.get(item.getSrc_id()), keywords, item.getSummarynum());
                String summary = "";
                if (match.size() == 1) {
                    summary = match.get(0);
                } else {
                    paragraph = match;
                }*/

                String category = "";
                if (item.getCategory() != null && !item.getCategory().isEmpty()) {
                    category = item.getCategory().get(0);
                }
                String title = item.getTitle().replace("&nbsp;", "").replace("&<font color='red'>nbsp</font>;", "");



                String industry = "";
                if (item.getIndustry() != null && !item.getIndustry().isEmpty()) {
                    industry = item.getIndustry().get(0);
                }

                NoticeReturnItemBo noticeReturnItem = new NoticeReturnItemBo();
                noticeReturnItem.setUrl(ossFileHost + "/" + item.getOss_path());
                noticeReturnItem.setCategory(category);
                noticeReturnItem.setTitle(title);
                noticeReturnItem.setSrc_id(item.getSrc_id());
                noticeReturnItem.setParagraph(paragraph);
                noticeReturnItem.setSummary(item.getContent());
                noticeReturnItem.setIndustry(industry);
                noticeReturnItem.setFile_type(item.getFile_type());
                noticeReturnItem.setFile_size(item.getFile_size());
                noticeReturnItem.setPublish_at(item.getPublish_at());
                noticeReturnItem.setPage_count(item.getPage_count());
                noticeReturnItem.setStockcode(item.getStockcode());
                noticeReturnItem.setStockname(item.getStockname());
                //透视数据[1有透视，0没有透视]
                List dataId = item.getData_id();
                if(dataId !=null && dataId.size()!=0){
                    noticeReturnItem.setPerspective(1);
                }else{
                    noticeReturnItem.setPerspective(0);
                }
                noticeReturnItemList.add(noticeReturnItem);

            }
            noticeReturn.setItem(noticeReturnItemList);
        }

        noticeReturn.setM_keyword(noticeReturn.getKeyword());
        if (noticeReading.getIfUnderstand() == 0) {
            noticeReturn.setKeyword("");
        }
        return noticeReturn;
    }

    public Map parseNoticeResult(NoticeSearch2Data data, NoticeSearch2Request request) throws CustomException {
        Map parseRsult = new HashMap();
        List items = new ArrayList();
        parseRsult.put("totalCount", data.getTotal_count());
        parseRsult.put("totalPage", data.getTotal_count()/request.getLimit()+1);
        parseRsult.put("currentPage", request.getOffset()/request.getLimit() + 1 );
        parseRsult.put("option",data.getOption());

        for(NoticeSearch2Item item: data.getItem()){
            Map ritem = new HashMap();
            ritem.put("id", item.getId());
            ritem.put("srcId", item.getSrc_id());
            ritem.put("createAt", item.getCreate_at());
            ritem.put("publishAt", item.getPublish_at());
            ritem.put("stockCode", item.getStockcode());
            ritem.put("stockName", item.getStockname());
            ritem.put("fileType", item.getFile_type());
            ritem.put("fileSize", item.getFile_size());
            ritem.put("pageCount", item.getPage_count());
            ritem.put("ossPath", item.getOss_path());
            ritem.put("tags", item.getTag());
            ritem.put("url", ossFileHost+"/"+item.getOss_path());

            //透视数据[1有透视，0没有透视]
            List dataId = item.getData_id();
            ritem.put("dataId", dataId);
            if(dataId!=null && dataId.size()!=0){
                ritem.put("perspective", 1);
            }else{
                ritem.put("perspective", 0);
            }
            String title = item.getTitle();
            if(title != null && !title.isEmpty()){
                title = title.replace("&nbsp;", "");
                title = title.replace("&<font color='red'>nbsp</font>;", "");
            }else {
                title = "";
            }
            ritem.put("title", title);
            List<String> paragraph = new ArrayList<>();
            ritem.put("summary", item.getContent());
            ritem.put("paragraph", paragraph);
            String industry = "";
            if (item.getIndustry() != null && item.getIndustry().size()>0) {
                industry = item.getIndustry().get(0);
            }
            ritem.put("industry", industry);
            ritem.put("industryList", item.getIndustry());
            String categoty = "";
            if (item.getCategory() != null && item.getCategory().size()>0) {
//                categoty = item.getCategory().get(item.getCategory().size()-1);
                categoty = item.getCategory().get(0);
            }
            ritem.put("category", categoty);
            ritem.put("categoryList", item.getCategory());
            items.add(ritem);
        }
        parseRsult.put("items", items);
        return parseRsult;
    }





    private int getNewCountById(String categoryId) {
        String value = redisUtil.get(KeyBuilder.getNoticeNewCountKey());
        int ret = 0;
        if (value != null && !value.isEmpty()) {
            NoticeNewCountRedisBo noticeNewCountRedisValue = JSON.parseObject(value, NoticeNewCountRedisBo.class);
            if (categoryId == null || categoryId.isEmpty()) {
                ret = noticeNewCountRedisValue.getTotal();
            } else {
                for (Map.Entry<String, Integer> item : noticeNewCountRedisValue.getData().entrySet()) {
                    if (item.getKey().equals(categoryId)) {
                        ret = item.getValue();
                        break;
                    }
                }
            }
        }
        return ret;
    }


    private Map<String, String> getFlatCategory() {
        String value = redisUtil.get(KeyBuilder.getFlatNoticeCategoriesKey());
        Map<String, String> ret = new HashMap<>();
        if (value !=null && !value.isEmpty()) {
            ret = JSON.parseObject(value, Map.class);
        }
        return ret;
    }


    private Map<String, List<String>> getParaFromMongo(List<String> srcIds, String delimiter) {

        List<FinanceJuchaoItemModel> financeJuchaoItemList = financeJuchaoItemDao.getBySrcIds(srcIds);

        Map<String, List<String>> ret = new HashMap<>();
        if (financeJuchaoItemList != null) {
            List<ObjectId> ids = new ArrayList<>();
            Map<String, String> paraMap = new HashMap<>();
            for (FinanceJuchaoItemModel financeJuchaoItem : financeJuchaoItemList) {
                ids.add(new ObjectId(financeJuchaoItem.get_id()));
                paraMap.put(financeJuchaoItem.get_id(), financeJuchaoItem.getSrc_id());
            }

            Map<String, List<String>> fileConentsRet = getFileConents(ids, delimiter);

            for (Map.Entry<String, String> conentItem : paraMap.entrySet()) {
                if (!fileConentsRet.containsKey(conentItem.getKey())) {
                    ret.put(conentItem.getValue(), new ArrayList<String>());
                } else {
                    ret.put(conentItem.getValue(), fileConentsRet.get(conentItem.getKey()));
                }
            }
        }

        return ret;
    }


    private Map<String, List<String>> getFileConents(List<ObjectId> ids, String delimiter) {
        List<JuchaoTextModel> juchaoTextList = juchaoTextDao.getByFileIds(ids);
        Map<String, List<String>> ret = new HashMap<>();
        if (juchaoTextList != null) {
            Map<String, String> oss = new HashMap<>();
            for (JuchaoTextModel juchaoText : juchaoTextList) {
                String fileId = juchaoText.getFileId();
                if(juchaoText.getText_version() >= 21) {
                    oss.put(fileId,juchaoText.getText_file());
                    continue;
                } else {
                    ret.put(fileId, new ArrayList<String>());
                }
            }

            if (oss != null) {
                Map<String, String> ossRet = getFilesFormOssByUrl(oss);
                if (ossRet !=  null) {
                    for (Map.Entry<String, String>  ossItem : ossRet.entrySet()) {
                        if (ret.containsKey(ossItem.getKey())) {
                            ret.replace(ossItem.getKey(), Arrays.asList(ossItem.getValue().split(delimiter)));
                        } else {
                            ret.put(ossItem.getKey(), Arrays.asList(ossItem.getValue().split(delimiter)));
                        }
                    }
                }
            }
        }

        return ret;

    }


    private Map<String, String> getFilesFormOssByUrl(Map<String, String> oss) {
        Map<String, String> ret = new HashMap<>();
        for (Map.Entry<String, String> item : oss.entrySet()) {
            String ossString = HttpUtil.get(item.getValue(), null, null);
//            try {
//                ossRetModel ossObject = JSON.parseObject(ossString, ossRetModel.class);
//                ret.put(item.getKey(), ossObject.getRes());
//            } catch (JSONException e) {
//                logger.error("oss string json decode error :" + ossString);
//            }

            ret.put(item.getKey(), ossString);
        }
        return ret;
    }

//    private class ossRetModel {
//        private String res;
//
//        public String getRes() {
//            return res;
//        }
//
//        public void setRes(String res) {
//            this.res = res;
//        }
//    }


    private List<String> matchedParagraph(List<String> paragraphs, List<String>keywords, int summaryNum) {
        List<String> ret = new ArrayList<>();

        if (paragraphs != null && !paragraphs.isEmpty()) {
            if (keywords == null || keywords.isEmpty() || summaryNum <= 0) {
                ret.add(matchedParagraphNull(paragraphs));
            } else {

                if (summaryNum > 1000) {
                    keywords = Arrays.asList(keywords.get(0));
                }

                int count = 0;
                for (String p : paragraphs) {
                    if (count >= 5) {
                        break;
                    }

                    for (String k : keywords) {
                        if (k == null || k.isEmpty()) {
                            continue;
                        }
                        if (p.indexOf(k, 0) != -1) {
                            count++;
                            ret.add(p);
                            break;
                        }
                    }
                }

                if(ret == null || ret.isEmpty()) {
                    ret.add(matchedParagraphNull(paragraphs));
                }
            }
        }
        return ret;

    }


    private String matchedParagraphNull(List<String> paragraphs) {
        String s = "";
        int len = 0;
        for (String p : paragraphs) {
            s += p;
            len += p.length();
            if (len >= 200) {
                break;
            }
        }
        return len > 500 ? s.substring(0, 500) + "..." : s;
    }


    private List<String> getFineCategory(Map<String, String> flatCategory, List<String> inputCategories, List<String> outputCategories) {
        String category = "";
        String categoryId = "";

        if (inputCategories == null || inputCategories.isEmpty()) {
            category = getLongestId(outputCategories);
            if (flatCategory.containsKey(category)) {
                categoryId = flatCategory.get(category);
            } else {
                category = "";
            }
        } else {
            if (outputCategories != null && !outputCategories.isEmpty()) {
                List<String> both = new ArrayList<>();
                for (String oc : outputCategories) {
                    for (String ic : inputCategories) {
                        String postK = ic.substring(1);
                        int len = postK.length();
                        String postKey = oc.substring(1, len);
                        if (postK == postKey) {
                            both.add(oc);
                        }
                    }
                }
                if (both != null && !both.isEmpty()) {
                    category = getLongestId(both);
                    if (flatCategory.containsKey(category)) {
                        categoryId = flatCategory.get(category);
                    } else {
                        category = "";
                    }
                }
            }
        }

        List<String> ret  = new ArrayList<>();
        ret.add(category);
        ret.add(categoryId);
        return ret;
    }


    private String getLongestId(List<String> arr) {
        String ret = "";
        if (arr != null && !arr.isEmpty()) {
            int ind = 0;
            for (int i = 0; i < arr.size(); i++) {
                int len = arr.get(i).length();
                if (len > Integer.MAX_VALUE) {
                    ind = i;
                }

            }
            ret = arr.get(ind);
        }
        return ret;
    }


    private List<String> getIndustryName(List<String> codeList) {
        String industry = "";
        String industryId = "";
        String value = redisUtil.get(KeyBuilder.getIndustryNameKey());
        if (value !=null && !value.isEmpty()) {
            IndustryNameRedisBo industryObj = JSON.parseObject(value, IndustryNameRedisBo.class);

            if (codeList.size() == 1) {
                industryId = codeList.get(0);
                String newCode = transferIndustry(industryId);
                industry = industryObj.getFirst().containsKey(newCode) ? industryObj.getFirst().get(newCode) : "";
            } else if(codeList.size() == 2) {
                for (String s : codeList) {
                    if (s.substring(0,1).equals("S")) {
                        industryId = s;
                        String newCode = transferIndustry(industryId);
                        industry = industryObj.getSecond().containsKey(newCode) ? industryObj.getSecond().get(newCode) : "";
                    }
                }
            } else if(codeList.size() == 3) {
                for (String s : codeList) {
                    if (s.substring(0,1).equals("T")) {
                        industryId = s;
                        String newCode = transferIndustry(industryId);
                        industry = industryObj.getThird().containsKey(newCode) ? industryObj.getThird().get(newCode) : "";
                    }
                }
            }
        }
        List<String> ret =  new ArrayList<>();
        ret.add(industry);
        ret.add(industryId);
        return ret;
    }


    private String transferIndustry(String code) {
        String level = code.substring(0,1);
        String last  = code.substring(1);
        String ret = "";
        switch (level) {
            case "F":
                ret = last + "000";
                break;
            case "S":
                ret = last + "00";
                break;
            case "T":
            default:
                ret = last;
                break;

        }
        return ret;
    }




    public SearchReportBO parseRaaReportSearchResult(ReportData reportData, ReportRequest request, Boolean isGroup) throws CustomException {
        SearchReportBO bo = new SearchReportBO();
        bo.setCurrent_page(request.getOffset() / request.getLimit() + 1);
        bo.setTotal_count(StringUtil.nullOrDefault(reportData.getTotal_count(),0));
        bo.setTotal_page((int) Math.ceil(Float.valueOf(reportData.getTotal_count()) / request.getLimit()));
        bo.setKeyword(StringUtil.nullOrDefault(reportData.getNlp_str(),""));
        bo.setRequest_id(StringUtil.nullOrDefault(reportData.getRequest_id(),""));
        bo.setAbcscore(StringUtil.nullOrDefault(reportData.getAbcscore(),0));
        bo.setOption(reportData.getOption());

        // 此时是直接在搜索框中选择股票代码，由于搜索的时候去掉部分信息，此时需要回填
        if (
                (request.getIfUnderstand() != null && request.getIfUnderstand())
                        && StringUtil.isEmpty(bo.getKeyword())
                        && !StringUtil.isEmpty(request.getStock_code())
                ) {
            bo.setKeyword(request.getKeyword_filter());
            bo.setSearchCompany(request.getKeyword_filter());
        }

        String[] keywords = new String[]{};
        if (bo.getKeyword() != null) {
            keywords = bo.getKeyword().split(",");
        }
        List<ReportItem> reportItems = reportData.getItem();

        if (reportItems == null || reportItems.isEmpty()) {
            List<SearchReportItemBO> items = new ArrayList<>();
            bo.setItems(items);
            bo.setM_keyword(bo.getKeyword());
            if (request.getIfUnderstand() != null && !request.getIfUnderstand()) {
                bo.setKeyword("");
            }
            return bo;
        }

        List<Integer> ids = new ArrayList<>();
        Map<String,List<String>> paragraphs = new HashMap<>();
        if (!isGroup) {//非归类搜索获取段落和摘要
            Map<String, String> idSummaryUrlMap = new HashMap<>();
            for (ReportItem item : reportItems) {
                ids.add(item.getId());
                idSummaryUrlMap.put(item.getId().toString(), item.getSummary_url());
            }
            paragraphs = reportManagerService.getParaFromTextFile(idSummaryUrlMap);
            //从mongodb取段落数据
//            paragraphs = reportManagerService.getParaFromMongo(ids);
        }

        //如果使用了关键词搜索
        List<String> trimedKeyword = new ArrayList<>();
        if (keywords.length > 0 && reportData.getParsed() != null) {
            //获取前端需要的关键词tag
            ParseKeywordBO parseKeywordBO = parseKeyword(reportData);
            bo.setSearchCompany(parseKeywordBO.getSearchCompany());
            bo.setSearchIndustry(parseKeywordBO.getSearchIndustry());
            bo.setParsed(parseKeywordBO.getParsed());
            //从关键词中剔除行业、公司和分类
            trimedKeyword = trimKeyword(keywords, parseKeywordBO.getTrim());
        }else {
            //修复结果中无段落信息，原始PHP见： controllers/helper/report/ReportHelper.php:528
            trimedKeyword = Arrays.asList(keywords);
        }

        //从redis获取研报分类
        Map<String, ReportCategoryFlatModel> categoryFlatResponse = reportManagerService.getCategoryFlat();

        List<SearchReportItemBO> items = new ArrayList<>();
        if (reportData.getItem() != null && !reportData.getItem().isEmpty()) {
            for (ReportItem item : reportData.getItem()) {
                SearchReportItemBO itemBO = ObjectUtil.copy(item, SearchReportItemBO.class);
                if (isGroup) {//归类搜索
                    if (request.getGroup_by() != null && request.getGroup_by().equals("company")) {//按公司归类
                        if (reportData.getNew_count() != null && reportData.getNew_count().get(item.getCode()) != null){
                            itemBO.setNew_count_today(reportData.getNew_count().get(item.getCode()));//今日新增数据
                        }
                    }else{//按行业归类
                        if (reportData.getNew_count() != null && reportData.getNew_count().get(item.getName()) != null){
                            itemBO.setNew_count_today(reportData.getNew_count().get(item.getName()));//今日新增数据
                        }
                        List<String> codeList = new ArrayList<>();
                        codeList.add(item.getName());
                        IndustryNameBO industryNameBO = reportManagerService.getIndustryName(codeList);
                        if (industryNameBO != null){
                            itemBO.setName(industryNameBO.getName());
                            itemBO.setCode(industryNameBO.getCode());
                        }
                    }

                    List<ReportItem> reportItemDataList = item.getData();

                    List<Integer> reportItemDataIds = new ArrayList<>();
                    for (ReportItem reportItemData : reportItemDataList){
                        reportItemDataIds.add(reportItemData.getId());
                    }

                    Map<Integer,String> reportUsers = userService.getUsersByReportIds(reportItemDataIds);
                    List<SearchReportItemBO> dataList = new ArrayList<>();
                    for (ReportItem reportItemData : reportItemDataList){
                        SearchReportItemBO dataBO = ObjectUtil.copy(reportItemData, SearchReportItemBO.class);
//                        SearchReportItemDataBO dataBO = new SearchReportItemDataBO();

                        dataBO.setUrl(ossFileHost+"/"+reportItemData.getUrl());
                        dataBO.setPublish_at(reportItemData.getTime());
                        dataBO.setTitle(reportItemData.getTitle().replace("&nbsp",""));

                        if (reportItemData.getCategory() != null){
                            String lowestType = reportManagerService.getLowestReportType(reportItemData.getCategory());
                            if (lowestType != null &&  categoryFlatResponse != null && !categoryFlatResponse.isEmpty() && categoryFlatResponse.containsKey(lowestType)
                                    && categoryFlatResponse.get(lowestType) != null
                                    && categoryFlatResponse.get(lowestType).getId() != null){
                                dataBO.setCategory_id(categoryFlatResponse.get(lowestType).getId());
                                dataBO.setCategory(categoryFlatResponse.get(lowestType).getName());
                            }else{
                                dataBO.setCategory("");
                            }
                        }

                        if (reportItemData.getIndustry_id() != null){
                            IndustryNameBO industryNameBO = reportManagerService.getIndustryName(reportItemData.getIndustry_id());
                            if (industryNameBO != null){
                                dataBO.setIndustry(industryNameBO.getName());
                                dataBO.setIndustry_id(industryNameBO.getCode());
                            }
                        }else{
                            dataBO.setIndustry("");
                        }
                        String reportUser = reportUsers.get(reportItemData.getId());
                        dataBO.setShare_author(reportUser != null ? reportUser : "fyl@tjsemi.com");

                        dataList.add(dataBO);
                    }

                    itemBO.setData(dataList);
                } else {//非归类搜索
                    Map<Integer, String> reportUsers = userService.getUsersByReportIds(ids);
                    itemBO.setUrl(ossFileHost + "/" + item.getUrl());
                    itemBO.setPublish_at(item.getTime());
                    itemBO.setTitle(item.getTitle().replace("&nbsp;", "")
                            .replace("&<font color='red'>nbsp</font>;", ""));
                    MatchParagraphBO matchParagraphBO = matchParagraph(item, paragraphs, trimedKeyword);
                    itemBO.setParagraph(matchParagraphBO.getParagraphs());
                    itemBO.setSummary(matchParagraphBO.getSummary());
                    if (item.getCategory() != null && !item.getCategory().isEmpty()){
                        String lowestType = reportManagerService.getLowestReportType(item.getCategory());
                        if (lowestType != null &&  categoryFlatResponse != null && !categoryFlatResponse.isEmpty() && categoryFlatResponse.containsKey(lowestType)
                                && categoryFlatResponse.get(lowestType) != null
                                && categoryFlatResponse.get(lowestType).getId() != null){
                            itemBO.setCategory_id(categoryFlatResponse.get(lowestType).getId());
                            itemBO.setCategory(categoryFlatResponse.get(lowestType).getName());
                        }else{
                            itemBO.setCategory("");
                        }
                    }
                    if (item.getIndustry_id() != null){
                        IndustryNameBO industryNameBO = reportManagerService.getIndustryName(item.getIndustry_id());
                        if (industryNameBO != null){
                            itemBO.setIndustry(industryNameBO.getName());
                            itemBO.setIndustry_id(industryNameBO.getCode());
                        }
                    }else{
                        if (!StringUtil.isEmpty(item.getIndustry1())){
                            itemBO.setIndustry(item.getIndustry1());// 新增了 industry1字段
                            itemBO.setIndustry_id("text_industry");// 标识使用industry1进行搜索
                        }else{
                            itemBO.setIndustry("");// 新增了 industry1字段
                        }
                    }
                    itemBO.setAuthor(item.getAuthor());
                    String reportUser = reportUsers.get(item.getId());
                    itemBO.setShare_author(reportUser != null ? reportUser : "fyl@tjsemi.com");
                }
                //获取solr中的analyst_honor列表解析成结果返回
                List<String> analyst_honors = new ArrayList<>();
                List<String> tmp_honors = item.getAnalyst_honor();
                if (tmp_honors != null && tmp_honors.size() >0){
                    for (String tmp_honor: tmp_honors) {
                        String[] h_arr = tmp_honor.split(",");
                        if (h_arr[0].equals("null")){
                            continue;
                        }else{
                            analyst_honors.add(tmp_honor);
                        }
                    }
                }
                String is_new = item.getIs_new();
                List<String> is_new_arr = new ArrayList<>();
                if (!Boolean.valueOf(is_new)) {
                    is_new_arr = Arrays.asList(is_new.split(";"));
                }
                if (analyst_honors != null && analyst_honors.size() >0) {
                    List<ReportAanlystHonorData> honors = new ArrayList();
                    for (int i = 0; i < analyst_honors.size(); i++) {
                        ReportAanlystHonorData res = new ReportAanlystHonorData();
                        String s_analyst_honor = analyst_honors.get(i);
                        String peo_uni_code = s_analyst_honor;
                        List<String> honor_list = new ArrayList();
                        String ana_honors = "";
                        if (s_analyst_honor.indexOf("#") > 0) {
                            String[] h_arr = s_analyst_honor.split("#");
                            peo_uni_code = h_arr[0];
                            ana_honors = h_arr[1];
                        }
                        res.setId(peo_uni_code);
                        //判断是否为新获奖
                        res.setIs_new(is_new_arr.contains(peo_uni_code));
                        //['1'=>'新财富','2'=>'金牛奖'] 约定
                        if (ana_honors != "" && ana_honors.indexOf("1") >= 0) {
                            honor_list.add("新财富");
                        }
                        if (ana_honors != "" && ana_honors.indexOf("2") >= 0) {
                            honor_list.add("金牛奖");
                        }
                        res.setHonor(honor_list);

                        AnalystBasicinfoModel basicinfo = analystBasicinfoDao.selectByPeoUniCode(peo_uni_code);
                        if (basicinfo != null) {
                            res.setName(basicinfo.getName());
                            res.setAvatar(basicinfo.getImage());
                        }

                        honors.add(res);
                    }
                    itemBO.setAnalyst_honor(honors);
                }
                items.add(itemBO);
            }
        }
        bo.setItems(items);

        bo.setSource(reportData.getSource());
        bo.setM_keyword(bo.getKeyword());
        if (request.getIfUnderstand() != null && !request.getIfUnderstand()){
            bo.setKeyword("");
        }

        return bo;
    }

    /**
     * [trimKeyword 从搜索关键词列表中删除tag属性]
     *
     * @param keywords $keywords [关键词列表]
     * @param tags     $tags    [所有tag标签]
     * @return [array]            [去除tag后的关键词]
     */
    private List<String> trimKeyword(String[] keywords, Set<String> tags) {
        if (StringUtil.isEmpty(keywords) || StringUtil.isEmpty(tags)) {
            return Arrays.asList(keywords);
        }
        List<String> trimKeywords = new ArrayList<>();
        for (String keyword : keywords) {
            if (!tags.contains(keyword)) {
                trimKeywords.add(keyword);
            }
        }
        return trimKeywords;
    }


    /**
     * [parseKeyword 使用关键词搜索时解析出关键词数据供前端使用，同时获取要剔除的关键词]
     *
     * @param reportData $result [查询出的结果信息]
     * @return [array]         [关键词的公司tag, 关键词中的行业tag, 关键词的其他tag, 正文搜索要剔除的关键词]
     */
    private ParseKeywordBO parseKeyword(ReportData reportData) {
        ReportDataParsed parsed = reportData.getParsed();
        Map<String, String> companyMap = parsed.getCompany_map();
        Map<String, String> industryMap = parsed.getIndustry_map();
        Map<String, String> categoryMap = parsed.getCategory_map();

        Set<String> keyCompany = new HashSet<>();
        Set<String> keyIndustry = new HashSet<>();
        String searchCompany = "";
        String searchIndustry = "";

        if (!StringUtil.isEmpty(companyMap)) {
            keyCompany = companyMap.keySet();
            String firstKey = keyCompany.iterator().next();
            searchCompany = firstKey + "#" + companyMap.get(firstKey);
        }
        if (!StringUtil.isEmpty(industryMap)) {
            keyIndustry = industryMap.keySet();
            String firstKey = keyIndustry.iterator().next();
            searchIndustry = firstKey + "#" + industryMap.get(firstKey);
        }

        Set<String> trim = new HashSet<>();
        trim.addAll(keyCompany);
        trim.addAll(keyIndustry);
        trim.addAll(categoryMap.keySet());

        ParseKeywordBO bo = new ParseKeywordBO();
        bo.setSearchCompany(searchCompany);
        bo.setSearchIndustry(searchIndustry);
        bo.setParsed(parsed);
        bo.setTrim(trim);
        return bo;
    }

    /**
     * 搜索所有段落返回前五个匹配的段落 或者 文章前500个字的摘要
     *
     * @param reportItem       搜索查询结果的子项
     * @param paragraphs 所有段落数据
     * @param keywords   array 搜索关键词
     * @return array [匹配的段落数组, 匹配的摘要信息]
     */
    private MatchParagraphBO matchParagraph(ReportItem reportItem, Map<String,List<String>> paragraphs, List<String> keywords) {
        Integer summaryNum = StringUtil.nullOrDefault(reportItem.getSummarynum(), 0);
        List<String> paragraph = paragraphs.get(String.valueOf(reportItem.getId()));

        MatchParagraphBO bo = new MatchParagraphBO();
        bo.setParagraphs(new ArrayList<>());
        bo.setSummary("");
        if (StringUtil.isEmpty(paragraph)) {
            return bo;
        }

        if (keywords.isEmpty() || summaryNum <= 0) {
            String summary = getSummary(paragraph);
            bo.setSummary(summary);
            return bo;
        }

        class Match {
            Integer count;
            String text;
        }
        Map<Integer, List<Match>> match = new HashMap<>();
        for (String text : paragraph) {
            if (StringUtil.isEmpty(text)) {
                continue;
            }
            Integer power = 0;//匹配权重
            Integer count = 0;//关键词匹配总次数
            for (String keyword : keywords) {
                if (StringUtil.isEmpty(keyword)) {
                    continue;
                }
                //关键词匹配次数
                Integer add = StringUtil.appearNumber(text, keyword);
                count += add;
                if (add > 0) {
                    power++;
                }
            }
            if (power > 0) {
                Match item = new Match();
                item.count = count;
                item.text = text;
                if (match.containsKey(power)) {
                    match.get(power).add(item);
                } else {
                    List<Match> matchList = new ArrayList<>();
                    matchList.add(item);
                    match.put(power, matchList);
                }
            }
        }

        Map<Integer, List<Match>> matchKrsort = new TreeMap<Integer, List<Match>>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer obj1, Integer obj2) {
                        // 降序排序
                        return obj2.compareTo(obj1);
                    }
                });
        matchKrsort.putAll(match);

        List<String> match5 = new ArrayList<>();
        Integer index = 0;
        for (Map.Entry<Integer, List<Match>> entry : matchKrsort.entrySet()) {
            if(index >= 5){
                break;
            }

            Collections.sort(entry.getValue(),new Comparator<Match>(){
                @Override
                public int compare(Match arg0, Match arg1) {
                    return arg1.count.compareTo(arg0.count);
                }
            });

            for (Match item : entry.getValue()){
                if(index >= 5){
                    break;
                }

                match5.add(item.text.length() > 500 ? item.text.substring(0,500)+"..." : item.text);
                index++;
            }
        }

        if (match5.isEmpty()){
            bo.setSummary(getSummary(paragraph));//摘要信息
        }else{
            bo.setParagraphs(match5);//段落信息
        }

        return bo;
    }


    /**
     * [getSummary 从段落中截取500字以内的摘要]
     *
     * @param paragraph $paragraphs [段落数据数组]
     * @return [string]              [摘要字符串]
     */
    private String getSummary(List<String> paragraph) {
        String summary = "";
        Integer len = 0;
        for (String text : paragraph) {
            summary += text;
            len += text.length();
            if (len >= 200) {
                return len >= 500 ? summary.substring(0, 500) + "..." : summary;
            }
        }
        return summary;
    }
}

