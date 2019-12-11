package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.model.bo.ReportRequest;
import la.niub.abcapi.servicecompre.model.bo.SearchChartBO;
import la.niub.abcapi.servicecompre.model.bo.SearchReportBO;
import la.niub.abcapi.servicecompre.model.bo.SearchTableBO;
import la.niub.abcapi.servicecompre.model.request.BatchExportRequest;
import la.niub.abcapi.servicecompre.model.request.DataChartRequest;
import la.niub.abcapi.servicecompre.model.request.DataTableRequest;
import la.niub.abcapi.servicecompre.model.request.NewsRequest;
import la.niub.abcapi.servicecompre.model.request.NoticeSearch2Request;
import la.niub.abcapi.servicecompre.service.ICommonService;
import la.niub.abcapi.servicecompre.service.IDataSearchService;
import la.niub.abcapi.servicecompre.service.ISearch2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements ICommonService {

    @Autowired
    IDataSearchService dataSearchService;

    @Autowired
    ISearch2Service search2Service;

    @Override
    public Object buildBatchExport(BatchExportRequest request) throws CustomException {
        Map<String, Object> ret = new HashMap<>();
        String keyword = request.getKeyword();
        Integer limit = request.getLimit();
        if (!request.getIds().isEmpty()) {
            List<String> idList = Arrays.asList(request.getIds().split(","));
            limit = idList.size();
            if (request.getSearch_type().equals("chart") || request.getSearch_type().equals("news")) {
                StringBuilder sb = new StringBuilder();
                Integer index = limit;
                sb.append("$id:(");
                for (String id : idList) {
                    sb.append(id).append("^").append(index).append(" ");
                    index--;
                }
                sb.append(")");
                keyword = sb.toString();
            }
            if (request.getSearch_type().equals("report")) {
                keyword = "编号:" + String.join(" OR  编号:",idList);
            }
            if (request.getSearch_type().equals("notice")) {
                keyword = String.join(",",idList);
            }
            if(request.getSearch_type().equals("table")){
                keyword = "ID:" + String.join(" OR  ID:",idList);
            }
        }
        switch (request.getSearch_type()) {
            case "chart":
                DataChartRequest dataChartRequest = new DataChartRequest();
                dataChartRequest.setKeyword(keyword);
                dataChartRequest.setOffset(request.getOffset());
                dataChartRequest.setLimit(limit);
                dataChartRequest.setUserId(request.getUserId());
                dataChartRequest.setRequest_id(request.getRequest_id());
                dataChartRequest.setInput_from(request.getInput_from());
                dataChartRequest.setPage(request.getPage());
                SearchChartBO chartBO = dataSearchService.buildChartSearchResult(dataChartRequest);
                ret.put("items",chartBO.getItems());
                ret.put("total",chartBO.getTotal_count() != null ? chartBO.getTotal_count() : 0);
                break;
            case "table":
                DataTableRequest dataTableRequest = new DataTableRequest();
                dataTableRequest.setKeyword(keyword);
                dataTableRequest.setOffset(request.getOffset());
                dataTableRequest.setLimit(limit);
                // 日志
                dataTableRequest.setUserId(request.getUserId());
                dataTableRequest.setRequest_id(request.getRequest_id());
                dataTableRequest.setInput_from(request.getInput_from());
                dataTableRequest.setPage(request.getPage());
                SearchTableBO tableBO = dataSearchService.buildTableSearchResult(dataTableRequest);
                ret.put("items",tableBO.getItems());
                ret.put("total",tableBO.getTotal_count() != null ? tableBO.getTotal_count() : 0);
                break;
            case "news":
                NewsRequest newsRequest = new NewsRequest();
                newsRequest.setKeyword(keyword);
                newsRequest.setLimit(limit);
                newsRequest.setSelected(request.getSelected());
                newsRequest.setStart_time(request.getStart_time());
                newsRequest.setEnd_time(request.getEnd_time());
                // 日志
                newsRequest.setUserId(request.getUserId());
                newsRequest.setRequest_id(request.getRequest_id());
                newsRequest.setInput_from(request.getInput_from());
                newsRequest.setPage(request.getPage());
                Map<String,Object> newsRet = search2Service.buildNewsSearchResult(newsRequest);
                ret.put("items",newsRet != null && newsRet.containsKey("item") ? newsRet.get("item") : null);
                ret.put("total",newsRet != null && newsRet.containsKey("total_count") ? newsRet.get("total_count") : 0);
                break;
            case "report":
                ReportRequest reportRequest = new ReportRequest();
                reportRequest.setKeyword_filter(keyword);
                reportRequest.setLimit(limit);
                reportRequest.setOffset(request.getOffset());
                reportRequest.setSelected(request.getSelected());
                reportRequest.setStart_time(request.getStart_time());
                reportRequest.setEnd_time(request.getEnd_time());
                // 日志
                reportRequest.setUserId(request.getUserId());
                reportRequest.setRequest_id(request.getRequest_id());
                reportRequest.setInput_from(request.getInput_from());
                reportRequest.setPage(request.getPage());
                SearchReportBO reportRet = search2Service.buildReportSearchResult(reportRequest);
                ret.put("items",reportRet.getItems());
                ret.put("total",reportRet.getTotal_count() != null ? reportRet.getTotal_count() : 0);
                break;
            case "notice":
                NoticeSearch2Request noticeSearch2Request = new NoticeSearch2Request();
                if(!request.getIds().isEmpty()){
                    noticeSearch2Request.setIds(keyword);
                }else{
                    noticeSearch2Request.setKeyword(keyword);
                }
                noticeSearch2Request.setLimit(limit);
                noticeSearch2Request.setSelected(request.getSelected());
                noticeSearch2Request.setStart_time(request.getStart_time());
                noticeSearch2Request.setEnd_time(request.getEnd_time());
                // 日志
                noticeSearch2Request.setUserId(request.getUserId());
                noticeSearch2Request.setRequest_id(request.getRequest_id());
                noticeSearch2Request.setInput_from(request.getInput_from());
                noticeSearch2Request.setPage(request.getPage());
                Map<String, Object> noticeRet = search2Service.buildNoticeSearchResult(noticeSearch2Request);
                ret.put("items",noticeRet !=null && noticeRet.containsKey("items") ? noticeRet.get("items") : null);
                ret.put("total",noticeRet !=null && noticeRet.containsKey("totalCount") ? noticeRet.get("totalCount") : null);
                break;
            default : return ret;
        }
        ret.put("keyword",request.getKeyword());
        ret.put("source_type",request.getSearch_type());
        return ret;
    }
}
