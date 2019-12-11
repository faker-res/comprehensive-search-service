package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarBO;
import la.niub.abcapi.servicecompre.model.request.SidebarRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.IComprehensiveService;
import la.niub.abcapi.servicecompre.service.ISidebarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 侧边栏
 */
@RestController
@RequestMapping(path = "/sidebar")
public class SidebarController {

    private static Logger logger = LogManager.getLogger(SidebarController.class);

    @Autowired
    IComprehensiveService comprehensiveService;

    @Autowired
    ISidebarService sidebarService;

    /**
     * 获取
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping
    Response<SidebarBO> get(SidebarRequest params) throws ServiceException {
//        SidebarStockRequest sidebarStockRequest = params.parseData(SidebarStockRequest.class);
        SidebarBO sidebarBO = comprehensiveService.buildSideResult(params.parseType(),params.getData());

        return new Response(sidebarBO);
    }

}
