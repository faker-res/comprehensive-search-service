package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.model.bo.SearchChartBO;
import la.niub.abcapi.servicecompre.model.bo.SearchTableBO;
import la.niub.abcapi.servicecompre.model.request.DataChartRequest;
import la.niub.abcapi.servicecompre.model.request.DataTableRequest;
import la.niub.abcapi.servicecompre.model.request.UserChartRequest;
import la.niub.abcapi.servicecompre.model.request.client.DataSearchRequest;
import la.niub.abcapi.servicecompre.model.response.client.datatable.DataTableItem;

import java.util.Map;

/**
 * 数据搜索
 */
public interface IDataSearchService {

    /**
     * 创投数据搜索
     * @param dataSearchRequest
     * @return
     */
    Object buildDataSearch(DataSearchRequest dataSearchRequest);

    /**
     * edb搜索
     * @param dataSearchRequest
     * @return
     */
    Object buildEdbSearch(DataSearchRequest dataSearchRequest);

    /**
     * 高级搜索
     * @param dataChartRequest
     * @return
     */
    SearchChartBO buildChartSearchResult(DataChartRequest dataChartRequest) throws CustomException;

    /**
     * 高级搜索
     * @param dataGraphRequest
     * @return
     */
    SearchTableBO buildTableSearchResult(DataTableRequest dataGraphRequest) throws CustomException;

    Map<String,Object> buildTableSearchResultItem(DataTableItem record);

    /**
     * 资源库 数据图
     * @param userChartRequest
     * @return
     * @throws CustomException
     */
    SearchChartBO buildUserCenterChartSearchResult(UserChartRequest userChartRequest) throws CustomException;

}
