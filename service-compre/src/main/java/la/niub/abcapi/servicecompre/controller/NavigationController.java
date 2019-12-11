package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.dao.reporter.INavigation_barDao;
import la.niub.abcapi.servicecompre.model.Navigation_bar;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.INavigationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导航栏
 */
@RestController
@RequestMapping(path = "/navigation")
public class NavigationController {
    private static Logger logger = LogManager.getLogger(NavigationController.class);

    @Autowired
    private INavigationService navigationService;

    @Autowired
    INavigation_barDao navigation_barDao;

    /*****
     * 获取导航栏数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/barlist")
    Response<Object> barlist(HttpServletRequest request)  {
        try{
            Map paraMap=transParamMap(request);
            List<Navigation_bar> lists=navigationService.queryMenuList(paraMap);
            return new Response(lists);
        }catch (Exception e){
            logger.error("获取导航栏数据失败：" + e.getMessage());
            return new Response(500, "获取导航栏数据失败:" + e.getMessage());
        }
    }

    /*****
     * 更新节点数据
     * @param navigation_bar
     * @return
     */
    @RequestMapping(value = "/barupdate")
    Response<Object> barupdate(Navigation_bar navigation_bar)  {
        try{
            return new Response(navigation_barDao.update(navigation_bar));
        }catch (Exception e){
            logger.error("更新节点数据失败：" + e.getMessage());
            return new Response(500, "更新节点数据:" + e.getMessage());
        }
    }

    /*****
     * 更新节点数据
     * @param navigation_bar
     * @return
     */
    @RequestMapping(value = "/bardelete")
    Response<Object> bardelete(Navigation_bar navigation_bar)  {
        try{
            return new Response(navigation_barDao.delete(navigation_bar));
        }catch (Exception e){
            logger.error("更新节点数据失败：" + e.getMessage());
            return new Response(500, "更新节点数据:" + e.getMessage());
        }
    }

    private Map transParamMap(HttpServletRequest request) {
        Map paramMap = new HashMap();
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paramName = (String) enums.nextElement();
            String paramValue = request.getParameter(paramName);
            if(!paramValue.isEmpty()){
                //形成键值对应的map
                paramMap.put(paramName, paramValue);
            }
        }
        return paramMap;
    }
}
