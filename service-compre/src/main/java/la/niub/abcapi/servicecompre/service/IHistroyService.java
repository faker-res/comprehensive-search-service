package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.QueryHistoryModel;
import la.niub.abcapi.servicecompre.model.request.HistoryRequest;

import java.util.List;

public interface IHistroyService {

    /**
     * 添加搜索历史
     * @param userId
     * @param input_from
     * @param keyword
     * @param page
     * @return
     */
    Boolean recordSearchHistory(String userId, String keyword, String input_from, String page);

    /**
     * 获取搜索历史
     * @param request
     * @return
     */
    List<QueryHistoryModel> getRecords(HistoryRequest request);

    /**
     * 删除历史记录
     * @param userId
     * @param ids
     * @return
     */
    Boolean delRecords(String userId, String ids);
}
