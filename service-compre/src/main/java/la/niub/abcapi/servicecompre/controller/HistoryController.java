package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.model.request.HistoryRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.IHistroyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
/**
 * 搜索历史
 *
 * @author amen
 */
public class HistoryController {

    private static Logger logger = LogManager.getLogger(HistoryController.class);

    @Autowired
    IHistroyService iHistroyService;

    /**
     * 删除搜索历史
     * @param historyRequest
     * @return
     */
    @GetMapping("del")
    Response delete(HistoryRequest historyRequest){
        if(historyRequest.getUserId().isEmpty()){
            return new Response(201, "user_id is empty");
        }
        return new Response(iHistroyService.delRecords(historyRequest.getUserId(),historyRequest.getIds()));
    }

    /**
     * 获取搜索历史
     * @param historyRequest
     * @return
     */
    @GetMapping("show")
    Response show(HistoryRequest historyRequest){
        if(historyRequest.getUserId().isEmpty()){
            return new Response(201, "user_id is empty");
        }
        return new Response(iHistroyService.getRecords(historyRequest));
    }
}
