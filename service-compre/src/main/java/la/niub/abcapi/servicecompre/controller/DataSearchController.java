package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.model.request.client.DataSearchRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.IDataSearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据搜索
 */
@RestController
@RequestMapping(path = "/data-search")
public class DataSearchController {

    private static Logger logger = LogManager.getLogger(DataSearchController.class);


    @Autowired
    IDataSearchService dataSearchService;


    @RequestMapping
    Response<Object> dataSearch(DataSearchRequest dataSearchRequest) throws ServiceException {
        return new Response(dataSearchService.buildDataSearch(dataSearchRequest));
    }

    @RequestMapping(value = "/edb")
    Response<Object> edbSearch(DataSearchRequest dataSearchRequest) throws ServiceException {
        return new Response(dataSearchService.buildEdbSearch(dataSearchRequest));
    }

}
