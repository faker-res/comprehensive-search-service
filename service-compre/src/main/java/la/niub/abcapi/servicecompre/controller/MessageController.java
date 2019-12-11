package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.model.request.message.MessageRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.message.MessageResponse;
import la.niub.abcapi.servicecompre.service.IFundCompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/message")
public class MessageController {

    private static Logger logger = LogManager.getLogger(MessageController.class);

    @Autowired
    private IFundCompanyService iFundCompanyService;

    @GetMapping
    Response<List<MessageResponse>> get(MessageRequest params){
        List<MessageResponse> messageResponseList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setId("123");
        messageResponse.setTitle("标题1");
        messageResponse.setType("weibo");
        messageResponse.setSource("南方财富网");
        messageResponse.setUrl("https://weibo.com/");
        calendar.setTime(new Date());
        calendar.add(Calendar.WEEK_OF_YEAR,-1);
        messageResponse.setTime(calendar.getTime());
        messageResponse.setRead(123400);
        messageResponse.setAuthor("张三");
        messageResponse.setHonor(true);
        messageResponseList.add(messageResponse);

        messageResponse = new MessageResponse();
        messageResponse.setId("absdf_123");
        messageResponse.setTitle("标题2");
        messageResponse.setType("wechat");
        messageResponse.setSource("中金公司研究");
        messageResponse.setUrl("http://weixin.qq.com/");
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,-1);
        messageResponse.setTime(calendar.getTime());
        messageResponse.setRead(354332);
        messageResponse.setAuthor("李四");
        messageResponse.setHonor(false);
        messageResponseList.add(messageResponse);

        messageResponse = new MessageResponse();
        messageResponse.setId("report_aaaaa777");
        messageResponse.setTitle("标题3");
        messageResponse.setType("message");
        messageResponse.setSource("北方财务网");
//        messageResponse.setUrl();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR,-1);
        messageResponse.setTime(calendar.getTime());
        messageResponse.setRead(55666);
        messageResponse.setAuthor("王五");
        messageResponse.setHonor(false);
        messageResponseList.add(messageResponse);

        return new Response<>(messageResponseList);
    }

    @GetMapping("wechat/fundCompany")
    public Response<List<MessageResponse>> getFundCompanyWechatList(@RequestParam("com_uni_code") Long com_uni_code) {
        if (StringUtil.isEmpty(com_uni_code)) {
            logger.error("传入的基金公司的com_uni_code为null");
            return new Response(408, "传入的基金公司的com_uni_code为null");
        }

        try {
            List<MessageResponse> messageResponseList = iFundCompanyService.getFundCompanyWechatList(com_uni_code);
            return new Response(messageResponseList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取基金公司的微信公众号文章列表失败:" + e.getMessage());
            return new Response(500, "获取基金公司的微信公众号文章列表失败:" + e.getMessage());
        }
    }
}
