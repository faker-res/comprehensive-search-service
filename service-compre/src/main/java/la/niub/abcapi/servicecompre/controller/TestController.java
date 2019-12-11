package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.client.IServiceNewsClient;
import la.niub.abcapi.servicecompre.dao.reporter.IWechatPublicDao;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.ILineService;
import la.niub.abcapi.servicecompre.service.IStockService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

/**
 * 测试用testest
 */
@RestController
@RequestMapping(path = "/test")
public class TestController {

    private final static Logger logger = LogManager.getLogger(TestController.class);

    @Value("${server.port}")
    private Integer serverPort;

    @Value("${feign.client.apiNews.url}")
    private String apiNewsUrl;

    @Autowired
    IStockService stockService;

    @Autowired
    ILineService lineService;

    @Autowired
    IServiceNewsClient serviceNewsClient;

    @Autowired
    IWechatPublicDao wechatPublicDao;

    public static void main(String[] args) throws ParseException, CloneNotSupportedException, IOException {
        ;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    String hello() {
        return "this is test "+serverPort;
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    String test() {
        return "this is test123321!!!!";
    }

    @RequestMapping(path = "/wechat", method = RequestMethod.GET)
    Response wechat(Long code) {
        return new Response(wechatPublicDao.selectByPrimaryKey(code));
    }

}
