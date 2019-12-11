package la.niub.abcapi.servicecompre.controller;


import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.model.request.ParsingRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.IParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequestMapping(path = "/parsing")

/**
 * Parsing 解析
 *
 * @author amen
 */
public class ParsingController {

    @Autowired
    IParsingService iParsingService;


    private static Logger logger = LogManager.getLogger(ParsingController.class);


    /**
     * 插队解析
     * bitmap/import_task
     * @param params
     * @return
     * @throws Throwable
     */
    @GetMapping("bitmap/import/task")
    Response send(ParsingRequest params) throws Throwable {
        String image_id = params.getImageId();
        if (StringUtil.isEmpty(image_id)) {
            logger.error("传入的imageId为null");
            return new Response(5008, "传入的imageId为null");
        }

        return new Response(iParsingService.send(params));
    }

    /**
     * 查询状态
     * bitmap/parse_state
     * @param params
     * @return
     * @throws Throwable
     */
    @GetMapping("bitmap/parse/state")
    Response getAnalystResult(ParsingRequest params) throws Throwable {
        String image_id = params.getImageId() ;
        if (StringUtil.isEmpty(image_id)) {
            logger.error("params imageId error");
            return new Response(5008,"params imageId is null");
        }

        return new Response(iParsingService.parseState(params));

    }

    /**
     * 推送数据到 SOLR
     * @param params
     * @return
     * @throws Throwable
     */
    @GetMapping("push/solr")
    Response post(ParsingRequest params) throws  Throwable {
        String image_id = params.getImageId();
        if (StringUtil.isEmpty(image_id)) {
            logger.error("Parsing push solr image_id is error");
            return new Response(5008,"push imageId is null");
        }

        return  new Response(iParsingService.post(params));
    }

    /**
     * 获取最新版本号
     * @param params
     * @return
     * @throws Throwable
     */
    @GetMapping("bitmap/version")
    Response version(ParsingRequest params){
        String type = params.getType();
        if (StringUtil.isEmpty(type)) {
            logger.error("type params is error");
            return new Response(5008,"type imageId is null");
        }
        return  new Response(iParsingService.version(params));
    }
}
