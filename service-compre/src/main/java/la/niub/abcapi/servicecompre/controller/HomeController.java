package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveBO;
import la.niub.abcapi.servicecompre.model.request.Request;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.IComprehensiveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 综合搜索首页
 */
@RestController
@RequestMapping(path = "/home")
public class HomeController {

    private static Logger logger = LogManager.getLogger(HomeController.class);

    @Autowired
    IComprehensiveService comprehensiveService;

    /**
     * 获取
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping
    Response<ComprehensiveBO> get(Request params) throws ServiceException {
        ComprehensiveBO comprehensiveBO = comprehensiveService.buildAllTypeResult();

        return new Response(comprehensiveBO);
    }

    @GetMapping(value = "/tips-top")
    Response firstScreen() throws ServiceException {
        return new Response(comprehensiveService.buildTipsAndTop());
    }

}
