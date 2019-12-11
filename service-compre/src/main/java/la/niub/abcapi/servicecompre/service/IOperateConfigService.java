package la.niub.abcapi.servicecompre.service;


import la.niub.abcapi.servicecompre.model.KeyWordQueryModel;
import la.niub.abcapi.servicecompre.model.FreqTablesDetailsModel;
import la.niub.abcapi.servicecompre.model.request.KeyWordRequest;
import java.util.List;

public interface IOperateConfigService {

    /**
     * 获取预置热词，热门搜索，热门推荐运营位配置服务
     * @param request
     * @return
     */
    List<KeyWordQueryModel> queryWithKeyWord(KeyWordRequest request);


    /**
     * 获取数据图运营位配置
     * @param request
     * @return
     */
    List<Object> getChartConfig(KeyWordRequest request);


    /**
     * 获取数据表运营位配置
     * @param type limit
     * @return
     */
    Object getConfTables(Integer type, Integer limit);


    /**
     * 数据表类型输入框检索
     * @param keyword limit
     * @return
     */
    List<FreqTablesDetailsModel> getTablesByKeyword(String keyword, Integer limit);

    /****
     * 通过id查询详情
     * @param id
     * @return
     */
    public Object getChartConfigone(String id);
}
