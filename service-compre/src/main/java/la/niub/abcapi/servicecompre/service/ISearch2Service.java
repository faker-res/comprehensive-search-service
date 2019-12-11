package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.model.bo.ReportRequest;
import la.niub.abcapi.servicecompre.model.bo.SearchReportBO;
import la.niub.abcapi.servicecompre.model.request.NewsRequest;
import la.niub.abcapi.servicecompre.model.request.NoticeSearch2Request;

import java.util.Map;

public interface ISearch2Service {

    Map<String, Object> buildNoticeSearchResult(NoticeSearch2Request request) throws CustomException;

    SearchReportBO buildReportSearchResult(ReportRequest request) throws CustomException;

    Map<String,Object> buildNewsSearchResult(NewsRequest request) throws CustomException;

}
