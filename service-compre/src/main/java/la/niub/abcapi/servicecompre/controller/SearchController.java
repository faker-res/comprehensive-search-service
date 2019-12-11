package la.niub.abcapi.servicecompre.controller;


import com.alibaba.fastjson.JSON;
import la.niub.abcapi.servicecompre.model.request.SearchFinanceCardRequest;
import la.niub.abcapi.servicecompre.model.request.SearchGeneralRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.ISearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private static final Logger logger = LogManager.getLogger(SearchController.class);

    @Autowired
    ISearchService searchService;

    @GetMapping("/general")
    public Response general(SearchGeneralRequest searchResultRequest) throws Throwable{
//        logger.info("searchController action result request : " + JSON.toJSONString(searchResultRequest));
        List<Object> searchResultResponse = searchService.general(searchResultRequest);
//        logger.info("searchController action result response : " + JSON.toJSONString(searchResultResponse));
        return new Response(searchResultResponse);
    }

    @GetMapping("/card")
    public Response card(SearchGeneralRequest searchResultRequest) throws Throwable{
        Object searchResultResponse = searchService.card(searchResultRequest);
        return new Response(searchResultResponse);
    }

    @GetMapping("/finance")
    public Response financeCard(SearchGeneralRequest searchResultRequest) throws Throwable{
        return new Response(searchService.cardFinance(searchResultRequest));
    }

    @GetMapping("/finance2")
    public Response cardFinanceShare(SearchFinanceCardRequest request) throws Throwable{
        return new Response(searchService.cardFinanceShare(request));
    }

    public Response news() {
        return null;
    }

    public Response chart() {
        return null;
    }

    public Response notice() {
        return null;
    }

    public Response report() {
        return null;
    }
}
