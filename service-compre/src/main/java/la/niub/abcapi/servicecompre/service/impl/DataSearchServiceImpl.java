package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import la.niub.abcapi.servicecompre.component.client.IApiDataChartClient;
import la.niub.abcapi.servicecompre.component.client.IApiDataTableClient;
import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.ObjectUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.code.SystemEnumCodeConfig;
import la.niub.abcapi.servicecompre.model.bo.SearchChartBO;
import la.niub.abcapi.servicecompre.model.bo.SearchTableBO;
import la.niub.abcapi.servicecompre.model.request.DataChartRequest;
import la.niub.abcapi.servicecompre.model.request.DataTableRequest;
import la.niub.abcapi.servicecompre.model.request.UserChartRequest;
import la.niub.abcapi.servicecompre.model.request.client.ClientDataChartRequest;
import la.niub.abcapi.servicecompre.model.request.client.ClientDataTableRequest;
import la.niub.abcapi.servicecompre.model.request.client.ClientUserCenterChartRequest;
import la.niub.abcapi.servicecompre.model.request.client.DataSearchRequest;
import la.niub.abcapi.servicecompre.model.response.client.ClientDataChartResponse;
import la.niub.abcapi.servicecompre.model.response.client.ClientDataTableResponse;
import la.niub.abcapi.servicecompre.model.response.client.ClientUserChartResponse;
import la.niub.abcapi.servicecompre.model.response.client.UserChart.UserChartData;
import la.niub.abcapi.servicecompre.model.response.client.datachart.DataChartData;
import la.niub.abcapi.servicecompre.model.response.client.datachart.DataChartItem;
import la.niub.abcapi.servicecompre.model.response.client.datatable.DataTableData;
import la.niub.abcapi.servicecompre.model.response.client.datatable.DataTableItem;
import la.niub.abcapi.servicecompre.service.IDataSearchService;
import la.niub.abcapi.servicecompre.service.IHistroyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DataSearchServiceImpl implements IDataSearchService {

    @Value("${feign.client.dataSearch.url}")
    private String dataSearchUrl;

    @Value("${feign.client.edbSearch.url}")
    private String edbSearchUrl;

    @Autowired
    IApiDataChartClient dataChartClient;

    @Autowired
    IApiDataTableClient dataTableClient;

    @Value("${feign.client.userCenterChart.url}")
    private String userCenterChartUrl;

    @Autowired
    Search2ServiceImpl search2Service;

    @Autowired
    IHistroyService histroyService;

    /**
     * EDB创投搜索整合
     * @param dataSearchRequest
     * @return
     */
    @Override
    public Object buildDataSearch(DataSearchRequest dataSearchRequest) {
        Map<String, String> params = new HashMap<>();
        params.put("limit", String.valueOf(dataSearchRequest.getLimit()));
        params.put("offset", String.valueOf(dataSearchRequest.getOffset()));
        try {
            params.put("keyword", URLEncoder.encode(dataSearchRequest.getKeyword(),"UTF-8"));
            if(dataSearchRequest.getType() != null){
                params.put("type", URLEncoder.encode(dataSearchRequest.getType(),"UTF-8"));
            }
            if(dataSearchRequest.getData_type() != null){
                params.put("data_type", URLEncoder.encode(dataSearchRequest.getData_type(),"UTF-8"));
            }
            params.put("device_info", URLEncoder.encode(getUserAgent(),"UTF-8"));


        } catch (UnsupportedEncodingException e) {

        }
        params.put("user_id", dataSearchRequest.getUserId());
        params.put("request_id", dataSearchRequest.getRequest_id());
        params.put("input_from", dataSearchRequest.getInput_from());
        params.put("page",dataSearchRequest.getPage());

        // UserSearchHistory
        histroyService.recordSearchHistory(dataSearchRequest.getUserId(), dataSearchRequest.getKeyword(),
                dataSearchRequest.getInput_from(), dataSearchRequest.getPage());

        String dataSearchRet = HttpUtil.get(dataSearchUrl, params, null);
//        System.out.println("data search api request :" + JSON.toJSONString(params)+ " response :" + dataSearchRet);

        if (dataSearchRet != null && !dataSearchRet.isEmpty()) {
            return JSON.parseObject(dataSearchRet);
        }
        return null;
    }

    /**
     * EDB搜索
     * @param dataSearchRequest
     * @return
     */
    @Override
    public Object buildEdbSearch(DataSearchRequest dataSearchRequest) {
        Map<String, String> params = new HashMap<>();
        params.put("limit", String.valueOf(dataSearchRequest.getLimit()));
        params.put("offset", String.valueOf(dataSearchRequest.getOffset()));
        try {
            params.put("keyword", URLEncoder.encode(dataSearchRequest.getKeyword(),"UTF-8"));
            if(dataSearchRequest.getCtype() != null){
                params.put("ctype", URLEncoder.encode(dataSearchRequest.getCtype(),"UTF-8"));
            }
            if(dataSearchRequest.getCsource() != null){
                params.put("csource", URLEncoder.encode(dataSearchRequest.getCsource(),"UTF-8"));
            }
            if(dataSearchRequest.getCfrequency() != null){
                params.put("cfrequency", URLEncoder.encode(dataSearchRequest.getCfrequency(),"UTF-8"));
            }
            if(dataSearchRequest.getMul_address() != null){
                params.put("mul_address", URLEncoder.encode(dataSearchRequest.getMul_address(),"UTF-8"));
            }
            params.put("device_info", URLEncoder.encode(getUserAgent(),"UTF-8"));


        } catch (UnsupportedEncodingException e) {

        }
        params.put("user_id", dataSearchRequest.getUserId());
        params.put("request_id", dataSearchRequest.getRequest_id());
        params.put("input_from", dataSearchRequest.getInput_from());
        params.put("page",dataSearchRequest.getPage());

        // UserSearchHistory
        histroyService.recordSearchHistory(dataSearchRequest.getUserId(), dataSearchRequest.getKeyword(),
                dataSearchRequest.getInput_from(), dataSearchRequest.getPage());

        String dataSearchRet = HttpUtil.get(edbSearchUrl, params, null);

//        System.out.println("edb search api request :" + JSON.toJSONString(params)+ " response :" + dataSearchRet);

        if (dataSearchRet != null && !dataSearchRet.isEmpty()) {
            return JSON.parseObject(dataSearchRet);
        }
        return null;
    }

    @Autowired
    private HttpServletRequest httpServletRequest;

    public String getUserAgent() {
        return httpServletRequest.getHeader("user-agent");
    }

    @Override
    public SearchChartBO buildChartSearchResult(DataChartRequest request) throws CustomException {
        ClientDataChartRequest clientDataChartRequest = new ClientDataChartRequest();
        // 自定义时间
        if (request.getStart_time() != null) {
            clientDataChartRequest.setStart_time(TimeUtil.strtotime(request.getStart_time() + " 00:00:00"));
        }
        if (request.getEnd_time() != null) {
            clientDataChartRequest.setEnd_time(TimeUtil.strtotime(request.getEnd_time() + " 23:59:59"));
        }
        if (StringUtils.isNotEmpty(request.getKeyword())){
            clientDataChartRequest.setKeyword(request.getKeyword());
        }
        try {
            clientDataChartRequest.setKeyword(URLEncoder.encode(request.getKeyword(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {

        }
        clientDataChartRequest.setOffset(request.getOffset());
        clientDataChartRequest.setLimit(request.getLimit());
        clientDataChartRequest.setId(request.getId());
        clientDataChartRequest.setTitle_include(request.getTitle_include());
        clientDataChartRequest.setTitle_uninclude(request.getTitle_uninclude());
        clientDataChartRequest.setIndustry(request.getIndustry());
        clientDataChartRequest.setAuthor(request.getAuthor());
        clientDataChartRequest.setPublish(request.getPublish());
        clientDataChartRequest.setUser_id(request.getUserId());
        clientDataChartRequest.setRequest_id(request.getRequest_id());
        clientDataChartRequest.setDeviceInfo(getUserAgent());
        clientDataChartRequest.setInput_from(request.getInput_from());
        clientDataChartRequest.setPage(request.getPage());

        // UserSearchHistory
        histroyService.recordSearchHistory(request.getUserId(), request.getKeyword(),
                request.getInput_from(), request.getPage());

        // 搜索2.0
        List<Map<String, String>> selectedList = new LinkedList<>();
        if(request.getSelected() != null && !request.getSelected().isEmpty()){
            selectedList = search2Service.parseSelectedTagIntoList(request.getSelected());
            Map<String, String> argsMap = search2Service.getArgsMap(selectedList);
            for (String key : argsMap.keySet()) {
                clientDataChartRequest.setFilter(key,argsMap.get(key));
            }
        }
        ClientDataChartResponse clientDataChartResponse = dataChartClient.advancedSearch(clientDataChartRequest);
//        System.out.println(clientDataChartRequest.toString());
        if (clientDataChartResponse == null){
            throw new CustomException(SystemEnumCodeConfig.SOLR_REQUEST_FAIL);
        }

        parseSearchResult(clientDataChartResponse);
        DataChartData dataChartData = clientDataChartResponse.getData();
        SearchChartBO bo = new SearchChartBO();
        bo.setCurrent_page(request.getOffset()/request.getLimit()+1);
        bo.setTotal_count(dataChartData.getTotal_count() != null ? dataChartData.getTotal_count() : 0);
        bo.setTotal_page(dataChartData.getTotal_count() != null ? (int)Math.ceil(Float.valueOf(dataChartData.getTotal_count())/request.getLimit()) : 0);
        bo.setRequest_id(dataChartData.getRequest_id());
        bo.setHighlight_fields(dataChartData.getHighlight_fields() != null ? dataChartData.getHighlight_fields().replace("industry","industry_name") : "");
        bo.setOption(dataChartData.getOption());

        List<String> dynamic = new ArrayList<>();
        dynamic.add("publish");
        dynamic.add("company");
        dynamic.add("industry_name");
        if (!StringUtil.isEmpty(request.getIndustry())){
            dynamic.remove("industry_name");
        }
        if (!StringUtil.isEmpty(request.getPublish())){
            dynamic.remove("publish");
        }
        if (request.getKeyword().contains("$") && dataChartData.getHighlight_fields() != null){
            List<String> highlightFieldsList = Arrays.asList(dataChartData.getHighlight_fields().split(","));
            if (!highlightFieldsList.isEmpty()){
                for (String field : highlightFieldsList){
                    if (dynamic.contains(field)){
                        dynamic.remove(field);
                    }
                }
            }
        }
        String unhighlightFields = buildUnShowFile(dataChartData.getHighlight_fields(),dataChartData).replace("industry","industry_name");
        unhighlightFields += ","+String.join(",",dynamic);
        bo.setUnhighlight_fields(unhighlightFields);

        List<Map<String,Object>> items = new ArrayList<>();
        if (!StringUtil.isEmpty(dataChartData.getItem())){
            for (DataChartItem item : dataChartData.getItem()){
                items.add(buildChartSearchResultItem(item));
            }
        }

        bo.setItems(items);
        search2Service.parseTitleOrContentToTags(selectedList);
        bo.setSelected(selectedList);
        return bo;
    }

    private void parseSearchResult(ClientDataChartResponse response){
        if (response.getGraph() != null){
            return;
        }
        if (!StringUtil.isEmpty(response.getKeyword())){
            response.getData().setNlp_str(response.getKeyword());
        }
        if (!StringUtil.isEmpty(response.getSearchCompany())){
            response.getData().setSearchCompany(response.getSearchCompany());
        }
        if (!StringUtil.isEmpty(response.getSearchIndustry())){
            response.getData().setSearchIndustry(response.getSearchIndustry());
        }
    }

    private Map<String,Object> buildChartSearchResultItem(DataChartItem dataChartItem){
        Map<String,Object> item = new HashMap<>();
//        item.put("image_id",dataChartItem.getImage_id() != null ? dataChartItem.getImage_id() : "");
        item.put("image_url",dataChartItem.getImage_url() != null ? dataChartItem.getImage_url() : "");
        item.put("image_title",dataChartItem.getImage_title() != null ? dataChartItem.getImage_title() : "");
//        item.put("id",dataChartItem.getId() != null ? dataChartItem.getId() : "");
        item.put("title",dataChartItem.getTitle() != null ? dataChartItem.getTitle() : "");
        item.put("type",dataChartItem.getType() != null ? dataChartItem.getType() : "");
        item.put("summary",dataChartItem.getSummary() != null ? dataChartItem.getSummary() : "");
//        item.put("time",dataChartItem.getTime() != null ? dataChartItem.getTime() : "");
        item.put("publish",dataChartItem.getPublish() != null ? dataChartItem.getPublish() : "");
        item.put("industry_name",dataChartItem.getPublish() != null ? dataChartItem.getIndustry() : "");
        item.put("company",dataChartItem.getCompany() != null ? dataChartItem.getCompany() : "");
        item.put("author",dataChartItem.getAuthor() != null ? dataChartItem.getAuthor() : "");
        item.put("chart_type",dataChartItem.getChart_type() != null ? dataChartItem.getChart_type() : "");
        item.put("chart_data",dataChartItem.getChart_data() != null ? dataChartItem.getChart_data() : "");
        item.put("source_url",dataChartItem.getSource_url() != null ? dataChartItem.getSource_url() : "");
        item.put("thumbnailLink",dataChartItem.getThumbnailLink() != null ? dataChartItem.getThumbnailLink() : "");

        item.put("image_title_move",dataChartItem.getImage_title());

        item.put("file_url",dataChartItem.getFile_url() != null ? StringUtil.stripHtml(dataChartItem.getFile_url()) : "");
        item.put("owner_id",dataChartItem.getOwner_id() != null ? StringUtil.stripHtml(dataChartItem.getOwner_id()) : "");
        item.put("confidence",dataChartItem.getConfidence());
        item.put("tags",dataChartItem.getTags() != null ? StringUtil.stripHtml(dataChartItem.getTags()) : "");
        item.put("score",dataChartItem.getScore());
        item.put("index_time",dataChartItem.getIndex_time() != null ? StringUtil.stripHtml(dataChartItem.getIndex_time()) : "");
        item.put("file_id",dataChartItem.getFile_id());
        item.put("image_legends",dataChartItem.getImage_legends() != null ? StringUtil.stripHtml(dataChartItem.getImage_legends()) : "");
        item.put("id",dataChartItem.getId() != null ? StringUtil.stripHtml(dataChartItem.getId()) : "");
        item.put("time",dataChartItem.getTime());
        item.put("image_id",dataChartItem.getImage_id() != null ? StringUtil.stripHtml(dataChartItem.getImage_id()) : "");
        item.put("real_id",dataChartItem.getReal_id() != null ? StringUtil.stripHtml(dataChartItem.getReal_id()) : "");
        item.put("owner_type",dataChartItem.getOwner_type() != null ? StringUtil.stripHtml(dataChartItem.getOwner_type()) : "");
        item.put("honor",dataChartItem.getHonor() != null ? StringUtil.stripHtml(dataChartItem.getHonor()) : "");
        item.put("is_bitmap",dataChartItem.getIs_bitmap());
        item.put("bitmap_ver",dataChartItem.getBitmap_ver());

        item.put("data_file",dataChartItem.getData_file());
        item.put("pageIndex",dataChartItem.getPageIndex());

        return item;
    }

    private String buildUnShowFile(String highlightFields,DataChartData dataChartData){
        if (StringUtil.isEmpty(highlightFields) || dataChartData == null || ObjectUtils.isEmpty(dataChartData.getItem())){
            return "";
        }

        Set<String> highlightFieldsList = new HashSet<>();
        highlightFieldsList.addAll(Arrays.asList(highlightFields.split(",")));
        highlightFieldsList.add("type");
        highlightFieldsList.add("title");

        Map<String,Object> itemMap = null;
        for (DataChartItem item : dataChartData.getItem()){
            itemMap = ObjectUtil.removeNull(ObjectUtil.bean2Map(item,false));
            for (String field : highlightFieldsList){
                if (itemMap.containsKey(field)){
                    itemMap.remove(field);
                }
            }
            break;
        }

        return String.join(",",itemMap.keySet());
    }

    @Override
    public SearchTableBO buildTableSearchResult(DataTableRequest request) throws CustomException {
        ClientDataTableRequest clientDataTableRequest = new ClientDataTableRequest();
        // 自定义时间
        if (request.getStart_time() != null && !request.getStart_time().isEmpty()) {
            clientDataTableRequest.setStart_time(TimeUtil.strtotime(request.getStart_time() + " 00:00:00"));
        }
        if (request.getEnd_time() != null && !request.getEnd_time().isEmpty()) {
            clientDataTableRequest.setEnd_time(TimeUtil.strtotime(request.getEnd_time() + " 23:59:59"));
        }
        if (StringUtils.isNotEmpty(request.getKeyword())){
            try {
                clientDataTableRequest.setKeyword(URLEncoder.encode(request.getKeyword(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {

            }
        }
        clientDataTableRequest.setOffset(request.getOffset());
        clientDataTableRequest.setLimit(request.getLimit());
        clientDataTableRequest.setId(request.getId());
        clientDataTableRequest.setTitle_include(request.getTitle_include());
        clientDataTableRequest.setTitle_uninclude(request.getTitle_uninclude());
        clientDataTableRequest.setIndustry(request.getIndustry());
        clientDataTableRequest.setAuthor(request.getAuthor());
        clientDataTableRequest.setPublish(request.getPublish());
        clientDataTableRequest.setType(request.getType());
        clientDataTableRequest.setCompany(request.getCompany());
        clientDataTableRequest.setUser_id(request.getUserId());
        clientDataTableRequest.setRequest_id(request.getRequest_id());
        clientDataTableRequest.setDeviceInfo(getUserAgent());
        clientDataTableRequest.setInputFrom(request.getInput_from());
        clientDataTableRequest.setPage(request.getPage());

        // UserSearchHistory
        histroyService.recordSearchHistory(request.getUserId(), request.getKeyword(),
                request.getInput_from(), request.getPage());

        // 搜索2.0
        List<Map<String, String>> selectedList = new LinkedList<>();
        if(request.getSelected() != null && !request.getSelected().isEmpty()){
            selectedList = search2Service.parseSelectedTagIntoList(request.getSelected());
            Map<String, String> argsMap = search2Service.getArgsMap(selectedList);
            for (String key : argsMap.keySet()) {
                clientDataTableRequest.setFilter(key,argsMap.get(key));
            }
        }

        ClientDataTableResponse clientDataTableResponse = dataTableClient.advancedSearch(clientDataTableRequest);
//System.out.println(clientDataTableRequest.toString());
        if (clientDataTableResponse == null){
            throw new CustomException(SystemEnumCodeConfig.SOLR_REQUEST_FAIL);
        }

        parseSearchResult(clientDataTableResponse);
        DataTableData dataTableData = clientDataTableResponse.getData();

        SearchTableBO bo = new SearchTableBO();
        bo.setCurrent_page(request.getOffset()/request.getLimit()+1);
        bo.setTotal_count(dataTableData.getTotal_count() != null ? dataTableData.getTotal_count() : 0);
        bo.setTotal_page((int)Math.ceil(Float.valueOf(dataTableData.getTotal_count())/request.getLimit()));
        bo.setRequest_id(dataTableData.getRequest_id());
        bo.setOption(dataTableData.getOption());

        List<Map<String,Object>> items = new ArrayList<>();
        if (!StringUtil.isEmpty(dataTableData.getItem())){
            for (DataTableItem item : dataTableData.getItem()){
                items.add(buildTableSearchResultItem(item));
            }
        }

        bo.setItems(items);
        bo.setKeyword(dataTableData.getNlp_str());
        search2Service.parseTitleOrContentToTags(selectedList);
        bo.setSelected(selectedList);

        return bo;
    }

    private void parseSearchResult(ClientDataTableResponse response){
        if (response.getGraph() != null){
            return;
        }
        if (!StringUtil.isEmpty(response.getKeyword())){
            response.getData().setNlp_str(response.getKeyword());
        }
        if (!StringUtil.isEmpty(response.getSearchCompany())){
            response.getData().setSearchCompany(response.getSearchCompany());
        }
        if (!StringUtil.isEmpty(response.getSearchIndustry())){
            response.getData().setSearchIndustry(response.getSearchIndustry());
        }
    }

    @Override
    public Map<String,Object> buildTableSearchResultItem(DataTableItem record){
        Map<String,Object> item = new HashMap<>();
        item.put("table_id",record.getTable_id() != null ? record.getTable_id() : "");
        item.put("table_url",record.getTable_url() != null ? record.getTable_url() : "");
        item.put("table_title",record.getTable_title() != null ? record.getTable_title() : "");
        item.put("table_ori",record.getTable_ori() != null ? record.getTable_ori() : "");
        item.put("id",record.getId() != null ? record.getId() : "");
        item.put("title",record.getTitle() != null ? record.getTitle() : "");
        item.put("type",record.getType() != null ? record.getType() : "");
        item.put("summary",record.getSummary() != null ? record.getSummary() : "");
        item.put("time",record.getTime() != null ? record.getTime() : "");
        item.put("table_rowCount",record.getTable_rowCount() != null ? record.getTable_rowCount() : "");
        item.put("table_columnCount",record.getTable_columnCount() != null ? record.getTable_columnCount() : "");
        item.put("full_match",record.getFull_match() != null ? record.getFull_match() : "");
        item.put("score",record.getScore() != null ? record.getScore() : "");
        item.put("table_source",record.getTable_source() != null ? record.getTable_source() : "");
        item.put("table_data",record.getTable_data() != null ? record.getTable_data() : "");
        item.put("formula",record.getFormula() != null ? record.getFormula() : "");
        item.put("hash",record.getHash() != null ? record.getHash() : "");
        item.put("dupl",record.getDupl() != null ? record.getDupl() : "");
        item.put("dupl_count",record.getDupl_count() != null ? record.getDupl_count() : "");
        item.put("impfullscore",record.getImpfullscore() != null ? record.getImpfullscore() : "");
        item.put("fullscore",record.getFullscore() != null ? record.getFullscore() : "");
        item.put("termscore",record.getTermscore() != null ? record.getTermscore() : "");
        item.put("company",record.getCompany() != null ? record.getCompany() : "");
        item.put("publish",record.getPublish() != null ? record.getPublish() : "");
        item.put("industry_name",record.getIndustry_name() != null ? record.getIndustry_name() : "");
        item.put("type_id",record.getType_id() != null ? record.getType_id() : "");
        item.put("src_id",record.getSrc_id() != null ? record.getSrc_id() : "");

        return item;
    }

    /**
     * 资源库 数据图搜索
     * @param request
     * @return
     * @throws CustomException
     */
    @Override
    public SearchChartBO buildUserCenterChartSearchResult(UserChartRequest request) throws CustomException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer startTime = 0;
        if (!StringUtil.isEmpty(request.getStart_time())){
            try {
                startTime = (int)format.parse(request.getStart_time()+" 00:00:00").getTime()/1000;
            } catch (ParseException e) {
            }
        }
        Integer endTime = 0;
        if (!StringUtil.isEmpty(request.getEnd_time())){
            try {
                endTime = (int)format.parse(request.getEnd_time()+" 23:59:59").getTime()/1000;
            } catch (ParseException e) {
            }
        }
        ClientUserCenterChartRequest dataRequest = new ClientUserCenterChartRequest();
        if (StringUtils.isNotEmpty(request.getKeyword())){
            dataRequest.setKeyword(request.getKeyword());
        }
        dataRequest.setOffset(request.getOffset());
        dataRequest.setLimit(request.getLimit());
        dataRequest.setTitle_include(request.getTitle_include());
        dataRequest.setTitle_uninclude(request.getTitle_uninclude());
        dataRequest.setIndustry(request.getIndustry());
        dataRequest.setAuthor(request.getAuthor());
        dataRequest.setStart_time(startTime);
        dataRequest.setEnd_time(endTime);
        dataRequest.setPublish(request.getPublish());
        dataRequest.setOwner_id(request.getOwner_id());
        String ret = HttpUtil.postBody(userCenterChartUrl, dataRequest.getJson());

        System.out.println(dataRequest.getJson());
        if (ret == null || ret.isEmpty()) {
            throw new CustomException(SystemEnumCodeConfig.SOLR_REQUEST_FAIL);
        }

        ClientUserChartResponse clientUserChartResponse = JSON.parseObject(ret, ClientUserChartResponse.class);
        UserChartData dataChartData = clientUserChartResponse.getData();
        SearchChartBO bo = new SearchChartBO();
        bo.setCurrent_page(request.getOffset()/request.getLimit()+1);
        bo.setTotal_count(dataChartData.getTotal_count());
        bo.setTotal_page((int)Math.ceil(Float.valueOf(dataChartData.getTotal_count())/request.getLimit()));
        bo.setRequest_id(dataChartData.getRequest_id());
        bo.setHighlight_fields(dataChartData.getHighlight_fields());
        bo.setUnhighlight_fields(dataChartData.getUnhighlight_fields());

        List<Map<String,Object>> items = new ArrayList<>();
        if (!StringUtil.isEmpty(dataChartData.getItem())){
            for (DataChartItem item : dataChartData.getItem()){
                items.add(buildChartSearchResultItem(item));
            }
        }

        bo.setItems(items);
        return bo;
    }

}
