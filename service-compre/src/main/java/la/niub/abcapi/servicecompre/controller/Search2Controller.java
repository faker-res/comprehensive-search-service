package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.model.bo.ReportRequest;
import la.niub.abcapi.servicecompre.model.request.NewsRequest;
import la.niub.abcapi.servicecompre.model.request.NoticeSearch2Request;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.ISearch2Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公告研报搜索2.0
 */
@RestController
@RequestMapping(path = "/")
public class Search2Controller {
    private static final Logger logger = LogManager.getLogger(Search2Controller.class);

    @Autowired
    ISearch2Service search2Service;

    @RequestMapping(value = "/notice")
    Response<Object> noticeSearch(NoticeSearch2Request request) throws CustomException {
        return new Response(search2Service.buildNoticeSearchResult(request));
    }


    @RequestMapping(value = "/report")
    Response<Object> reportSearch(ReportRequest request) throws CustomException {
        return new Response(search2Service.buildReportSearchResult(request));
    }

    @RequestMapping(value = "/news")
    Response<Object> newsSearch(NewsRequest request) throws CustomException {
        return new Response(search2Service.buildNewsSearchResult(request));
    }


}
