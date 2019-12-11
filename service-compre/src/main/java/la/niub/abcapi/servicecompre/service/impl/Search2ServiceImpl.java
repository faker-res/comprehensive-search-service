package la.niub.abcapi.servicecompre.service.impl;

import com.alibaba.fastjson.JSON;
import la.niub.abcapi.servicecompre.component.client.IApiNoticeSearch2Client;
import la.niub.abcapi.servicecompre.component.client.IApiReportSearch2Client;
import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.component.util.TimeUtil;
import la.niub.abcapi.servicecompre.config.code.SystemEnumCodeConfig;
import la.niub.abcapi.servicecompre.model.bo.ReportData;
import la.niub.abcapi.servicecompre.model.bo.ReportRequest;
import la.niub.abcapi.servicecompre.model.bo.SearchReportBO;
import la.niub.abcapi.servicecompre.model.request.NewsRequest;
import la.niub.abcapi.servicecompre.model.request.NoticeSearch2Request;
import la.niub.abcapi.servicecompre.model.request.client.ClientNoticeSearch2Request;
import la.niub.abcapi.servicecompre.model.request.client.ClientReportSearch2Request;
import la.niub.abcapi.servicecompre.model.response.MyStockDataResponse;
import la.niub.abcapi.servicecompre.model.response.MyStockListResponse;
import la.niub.abcapi.servicecompre.model.response.MyStockResponse;
import la.niub.abcapi.servicecompre.model.response.SearchGeneralResponse;
import la.niub.abcapi.servicecompre.model.response.client.ApiNoticeSearch2Response;
import la.niub.abcapi.servicecompre.model.response.client.ApiReportSearch2Response;
import la.niub.abcapi.servicecompre.model.response.client.Search2Option;
import la.niub.abcapi.servicecompre.model.response.client.notice.NoticeSearch2Data;
import la.niub.abcapi.servicecompre.service.IHistroyService;
import la.niub.abcapi.servicecompre.service.ISearch2Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Search2ServiceImpl implements ISearch2Service{

    @Autowired
    IApiNoticeSearch2Client noticeSearch2Client;

    @Autowired
    IApiReportSearch2Client reportSearch2Client;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    SearchServiceImpl searchService;

    @Autowired
    IHistroyService histroyService;

    @Value("${feign.client.apiNews.url}")
    private String newsSearch2Url;

    @Value("${feign.client.myStock.url}")
    private String myStockUrl;

    public String getUserAgent() {
        return httpServletRequest.getHeader("user-agent");
    }

    /**
     * 公告搜索2.0
     * @param request
     * @return
     * @throws CustomException
     */
    @Override
    public Map<String, Object> buildNoticeSearchResult(NoticeSearch2Request request) throws CustomException {
        ClientNoticeSearch2Request clientNoticeSearch2Request = new ClientNoticeSearch2Request();
        try {
            clientNoticeSearch2Request.setKeyword(URLEncoder.encode(request.getKeyword(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {

        }
        clientNoticeSearch2Request.setLimit(request.getLimit());
        clientNoticeSearch2Request.setOffset(request.getOffset());
        if(request.getIds() != null && !request.getIds().isEmpty()){
            clientNoticeSearch2Request.setIds(request.getIds());
        }
        // 排序
        if(request.getSort() != null){
            clientNoticeSearch2Request.setSort(request.getSort());
        }
        // 自定义时间
        if (request.getStart_time() != null) {
            clientNoticeSearch2Request.setStart_time(TimeUtil.strtotime(request.getStart_time() + " 00:00:00"));
        }
        if (request.getEnd_time() != null) {
            clientNoticeSearch2Request.setEnd_time(TimeUtil.strtotime(request.getEnd_time() + " 23:59:59"));
        }
        //日志参数
        clientNoticeSearch2Request.setUser_id(request.getUserId());
        clientNoticeSearch2Request.setRequest_id(request.getRequest_id());
        clientNoticeSearch2Request.setPage(request.getPage());
        clientNoticeSearch2Request.setInput_from(request.getInput_from());
        clientNoticeSearch2Request.setDevice_info(getUserAgent());
        clientNoticeSearch2Request.setKeyword(request.getKeyword());

        // UserSearchHistory
        histroyService.recordSearchHistory(request.getUserId(), request.getKeyword(),
                request.getInput_from(), request.getPage());

        List<Map<String, String>> selectedList = new LinkedList<>();
        if(request.getSelected() != null && !request.getSelected().isEmpty()){
            selectedList = parseSelectedTagIntoList(request.getSelected());
            Map<String, String> argsMap = getArgsMap(selectedList);
//            System.out.println(JSON.toJSONString(argsMap));
            for (String key : argsMap.keySet()) {
                clientNoticeSearch2Request.setFilter(key,argsMap.get(key));
            }
        }

//        System.out.println(clientNoticeSearch2Request.toString());
        ApiNoticeSearch2Response result = noticeSearch2Client.searchResult(clientNoticeSearch2Request);
        if (result == null || result.getData()== null){
            throw new CustomException(SystemEnumCodeConfig.SOLR_REQUEST_FAIL);
        }
        NoticeSearch2Data resultData = result.getData();
        Map<String, Object> ret = searchService.parseNoticeResult(resultData,request);
        parseTitleOrContentToTags(selectedList);
        ret.put("solrquery",resultData.getSolrquery());
        if(request.getRemovetags()!=null && !request.getRemovetags().isEmpty()){
            String tags=request.getRemovetags();
            List<Search2Option> options=(List<Search2Option >)ret.get("option");
            ret.put("option",options.stream()
                    .filter(s -> !tags.contains(s.getType())).collect(Collectors.toList()));
            selectedList=selectedList.stream().filter(s->!tags.contains(s.get("type"))).collect(Collectors.toList());
        }
        ret.put("selected",selectedList);
        return ret;
    }

    /**
     * 研报搜索2.0
     * @param request
     * @return
     * @throws CustomException
     */
    @Override
    public SearchReportBO buildReportSearchResult(ReportRequest request) throws CustomException{
        ClientReportSearch2Request clientReportSearch2Request = new ClientReportSearch2Request();
        try {
            clientReportSearch2Request.setKeyword(URLEncoder.encode(request.getKeyword_filter(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {

        }
        clientReportSearch2Request.setLimit(request.getLimit());
        clientReportSearch2Request.setOffset(request.getOffset());
        // 排序
        if(request.getOrder_by() != null){
            clientReportSearch2Request.setOrder_by(request.getOrder_by());
        }
        // 自定义时间
        if (request.getStart_time() != null) {
            clientReportSearch2Request.setStart_time(TimeUtil.strtotime(request.getStart_time() + " 00:00:00"));
        }
        if (request.getEnd_time() != null) {
            clientReportSearch2Request.setEnd_time(TimeUtil.strtotime(request.getEnd_time() + " 23:59:59"));
        }
        List<Map<String, String>> selectedList = new LinkedList<>();
        if(request.getSelected() != null && !request.getSelected().isEmpty()){
            selectedList = parseSelectedTagIntoList(request.getSelected());
            Map<String, String> argsMap = getArgsMap(selectedList);
            for (String key : argsMap.keySet()) {
                clientReportSearch2Request.setFilter(key,argsMap.get(key));
            }
        }
        //日志参数
        clientReportSearch2Request.setUser_id(request.getUserId());
        clientReportSearch2Request.setRequest_id(request.getRequest_id());
        clientReportSearch2Request.setPage(request.getPage());
        clientReportSearch2Request.setInput_from(request.getInput_from());
        clientReportSearch2Request.setDevice_info(getUserAgent());

        // UserSearchHistory
        histroyService.recordSearchHistory(request.getUserId(), request.getKeyword_filter(),
                request.getInput_from(), request.getPage());
//        System.out.println(clientReportSearch2Request.toString());
        ApiReportSearch2Response result = reportSearch2Client.searchResult(clientReportSearch2Request);
        if (result == null || result.getData()==null){
            throw new CustomException(SystemEnumCodeConfig.SOLR_REQUEST_FAIL);
        }
        ReportData resultData = result.getData();
        if (!StringUtil.isEmpty(result.getKeyword())) {
            resultData.setNlp_str(result.getKeyword());
        }
        SearchReportBO ret = searchService.parseRaaReportSearchResult(resultData,request,false);
        parseTitleOrContentToTags(selectedList);
        if(request.getRemovetags()!=null && !request.getRemovetags().isEmpty()){
            String tags=request.getRemovetags();
            ret.setOption(ret.getOption().stream()
                    .filter(s -> !tags.contains(s.getType())).collect(Collectors.toList()));
            selectedList=selectedList.stream().filter(s->!tags.contains(s.get("type"))).collect(Collectors.toList());
        }
        ret.setSelected(selectedList);
        return ret;

    }

    /**
     * 资讯搜索2.0
     * @param request
     * @return
     * @throws CustomException
     */
    @Override
    public Map<String,Object> buildNewsSearchResult(NewsRequest request) throws CustomException {
        Map<String, String> params = new HashMap<>();
        params.put("limit", String.valueOf(request.getLimit()));
        params.put("offset", String.valueOf(request.getOffset()));
        params.put("keyword", request.getKeyword());
        // 自定义时间
        if (request.getStart_time() != null) {
            params.put("start_time",TimeUtil.strtotime(request.getStart_time() + " 00:00:00").toString());
        }
        if (request.getEnd_time() != null) {
            params.put("end_time",TimeUtil.strtotime(request.getEnd_time() + " 23:59:59").toString());
        }
        if(request.getPrior() != null) {
            params.put("prior", request.getPrior());
        }
        params.put("channel", request.getChannel());
        // 日志参数
        params.put("user_id", request.getUserId());
        params.put("request_id", request.getRequest_id());
        params.put("input_from", request.getInput_from());
        params.put("page",request.getPage());
        params.put("device_info", getUserAgent());

        // UserSearchHistory
        histroyService.recordSearchHistory(request.getUserId(), request.getKeyword(),
                request.getInput_from(), request.getPage());

        List<Map<String, String>> selectedList = new LinkedList<>();
        if(request.getSelected() != null && !request.getSelected().isEmpty()){
            selectedList = parseSelectedTagIntoList(request.getSelected());
            Map<String, String> argsMap = getArgsMap(selectedList);
            for (String key : argsMap.keySet()) {
                if(key.equals("time_area") && argsMap.get(key).equals("自定义")){
                    continue;
                }
                params.put(key,argsMap.get(key));
            }
        }
        String resultStr = HttpUtil.post(newsSearch2Url, params, null);
//        System.out.println(newsSearch2Url+"|"+resultStr);
//        System.out.println(JSON.toJSONString(params));
        if (StringUtils.isEmpty(resultStr)) {
            throw new CustomException(SystemEnumCodeConfig.SOLR_REQUEST_FAIL);
        }
        SearchGeneralResponse resultObj = JSON.parseObject(resultStr,SearchGeneralResponse.class);
        // 对标题或者正文的特殊处理
        parseTitleOrContentToTags(selectedList);
        Map<String, Object> ret = resultObj.getData();
        if(request.getRemovetags()!=null && !request.getRemovetags().isEmpty()){
            String tags=request.getRemovetags();
            List<Map> options=(List<Map>)ret.get("option");
            ret.put("option",options.stream()
                    .filter(s -> !tags.contains(s.get("type").toString())).collect(Collectors.toList()));
            selectedList=selectedList.stream().filter(s->!tags.contains(s.get("type"))).collect(Collectors.toList());
        }
        ret.put("selected",selectedList);
        return ret;
    }

    /**
     * 获取选中tag转化为list
     * @param tags
     * @return
     */
    public  List<Map<String, String>> parseSelectedTagIntoList(String tags){
        List<Map<String, String>> selectedList = new LinkedList<>();
        List<String> optionList = Arrays.asList(tags.split(";"));
        if (optionList.size()>0){
            for (String tag : optionList){
                String[] tagArr = StringUtils.split(tag,",",2);
                if(tagArr.length>1){
                    Map<String, String> selectOption = new HashMap<>();
                    // 匹配标题，正文参数
                    if ("keywords".equals(tagArr[0])) {
                        parseTitleOrContentToArgs(tagArr[1], selectOption, selectedList);
                        continue;
                    }
                    // 匹配标题，正文参数
                    selectOption.put("type",tagArr[0]);
                    String name = tagArr[1];
                    if (tagArr[0].equals("time_area")) {
                        String[] timeArr = StringUtils.split(name,",",3);
                        if(timeArr.length>2){
                            name = timeArr[0];
                            selectOption.put("min",timeArr[1]);
                            selectOption.put("max",timeArr[2]);
                        }
                    }
                    selectOption.put("name",name);
                    selectedList.add(selectOption);
                }
            }
        }
        return selectedList;
    }

    /**
     * 合并参数值
     * @param selectedList
     * @return
     */
    public Map<String, String> getArgsMap(List<Map<String, String>> selectedList){
        Map<String, String> argsMap = new HashMap<>();
        for (Map<String,String> tag : selectedList){
            if(!tag.containsKey("type") || !tag.containsKey("name")){
                continue;
            }
            String argName = tag.get("type");
            String argValue;
            // 检查是否自选股
            if ("自选股".equals(tag.get("name")) && (argName.equals("company_s") || argName.equals("company")
                    || argName.equals("stockname") || argName.equals("alg_companies"))) {
                argsMap.put("selfSelectedStock", selfSelectedStockValue());
            }
            if(argsMap.containsKey(argName)){
                argValue =argsMap.get(argName) + "|"+tag.get("name");
            }else{
                argValue = tag.get("name");
            }
            argsMap.put(argName, argValue);
        }

        return argsMap;
    }

    /**
     * 自选股tag 拼接自选股股票代码
     * @return
     */
    public String selfSelectedStockValue() {
        String userId = httpServletRequest.getParameter("userId");
        String token = httpServletRequest.getParameter("token");
        if(userId.isEmpty() || token.isEmpty()){
            return null;
        }
        Map<String, String> stockParams = new HashMap<>();
        stockParams.put("userId", userId);
        stockParams.put("token", token);
        stockParams.put("limit", "1000");
        stockParams.put("offset", "0");
        String stockRet = HttpUtil.get(myStockUrl, stockParams,null);
//        System.out.println("data search api request :" + JSON.toJSONString(stockParams)+ " response :" + stockRet);
        if (stockRet == null || stockRet.isEmpty()) {
            return null;
        }
        MyStockResponse ret = JSON.parseObject(stockRet, MyStockResponse.class);
        MyStockDataResponse stockDataResponse = ret.getData();
        if(stockDataResponse == null || stockDataResponse.getList().size() == 0){
            return null;
        }
        StringBuilder myStock = new StringBuilder();
        for(MyStockListResponse v :stockDataResponse.getList()){
            if(myStock.length() > 0){
                myStock.append("|");
            }
            myStock.append(v.getStockCode());
        }
        return myStock.toString();
    }


    /**
     * 解析前端正文，标题，标题或正文参数为solr参数
     * @param keywords
     * @param map
     * @param list
     */
    private void parseTitleOrContentToArgs (String keywords, Map<String, String>map, List<Map<String, String>> list) {
        String typeName = StringUtils.substringBefore(keywords, "：");
        String typeVal = StringUtils.substringAfter(keywords, "：");
        if(typeName.isEmpty() || typeVal.isEmpty()){
            return ;
        }
        String typeArgs = "";
        if ("标题".equals(typeName)) {
            typeArgs = "title_include";
        }
        if ("正文".equals(typeName)) {
            typeArgs = "content_include";
        }
        if ("关键词".equals(typeName)) {
            typeArgs = "title_content_include";
        }
        map.put("type", typeArgs);
        map.put("name", typeVal);
        list.add(map);
    }

    /**
     * selected标签转换为前端的输出模式
     * @param selectedList
     */
    public void parseTitleOrContentToTags (List<Map<String, String>> selectedList) {
        for ( Map<String, String> map: selectedList) {
            if("title_include".equals(map.get("type"))) {
                map.put("type", "keywords");
                map.put("name", "标题："+ map.get("name"));
            }
            if("content_include".equals(map.get("type"))) {
                map.put("type", "keywords");
                map.put("name", "正文："+ map.get("name"));
            }
            if("title_content_include".equals(map.get("type"))) {
                map.put("type", "keywords");
                map.put("name", "关键词："+ map.get("name"));
            }
        }
    }
}
