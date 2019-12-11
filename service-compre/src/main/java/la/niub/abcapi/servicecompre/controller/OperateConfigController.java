package la.niub.abcapi.servicecompre.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import la.niub.abcapi.servicecompre.service.IOperateConfigService;
import la.niub.abcapi.servicecompre.model.request.KeyWordRequest;
import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.ErrorResponse;
import la.niub.abcapi.servicecompre.component.util.RedisUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Base64;

/**
 * 运营配置,包括：获取预置热词，热门搜索，热门推荐运营位配置接口
 * 数据图运营位配置接口，数据表的运营位接口
 */
@RestController
@RequestMapping(path = "/operate-config")
public class OperateConfigController {

    private static Logger logger = LogManager.getLogger(OperateConfigController.class);

    @Autowired
    IOperateConfigService operateConfigService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取预置热词，热门搜索，热门推荐运营位配置接口
     *
     * @param request
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "/keyword-query", method = RequestMethod.GET)
    Response queryWithKeyWord(KeyWordRequest request) throws CustomException {
        return new Response(operateConfigService.queryWithKeyWord(request));
    }

    /**
     * 获取数据图运营位配置
     *
     * @param request
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "/chart-config", method = RequestMethod.GET)
    Response getChartConfig(KeyWordRequest request) throws CustomException {
        return new Response(operateConfigService.getChartConfig(request));
    }


    /**
     * 获取数据表的运营位
     *
     * @param type, limit
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "/table-conf", method = RequestMethod.GET)
    Response getConfTables(Integer type, Integer limit) throws CustomException {
        return new Response(operateConfigService.getConfTables(type, limit));
    }

    /**
     * 数据表类型输入框检索
     *
     * @param keyword, limit
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "/table-conf-search", method = RequestMethod.GET)
    Response getTablesByKeyword(String keyword, Integer limit) throws CustomException {
        return new Response(operateConfigService.getTablesByKeyword(keyword, limit));
    }

    /**
     * 精选图详情
     * @param
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "/selected-drawing/detail", method = RequestMethod.GET)
    Response getShare(HttpServletRequest request) throws CustomException {
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        return new Response(operateConfigService.getChartConfigone(id));
    }

}