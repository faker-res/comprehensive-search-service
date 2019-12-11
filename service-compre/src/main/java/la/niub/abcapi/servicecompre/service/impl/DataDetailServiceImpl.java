package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.component.client.IApiDataChartClient;
import la.niub.abcapi.servicecompre.component.client.IApiDataTableClient;
import la.niub.abcapi.servicecompre.component.client.IApiDataTableSingleClient;
import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.model.bo.SearchChartBO;
import la.niub.abcapi.servicecompre.model.request.ChartDetailRequest;
import la.niub.abcapi.servicecompre.model.request.DataChartRequest;
import la.niub.abcapi.servicecompre.model.request.client.ClientDataChartRequest;
import la.niub.abcapi.servicecompre.model.request.client.ClientDataTableRequest;
import la.niub.abcapi.servicecompre.model.response.client.ClientDataChartResponse;
import la.niub.abcapi.servicecompre.model.response.client.ClientDataTableResponse;
import la.niub.abcapi.servicecompre.model.response.client.datachart.DataChartItem;
import la.niub.abcapi.servicecompre.model.response.client.datatable.DataTableItem;
import la.niub.abcapi.servicecompre.service.IDataDetailService;
import la.niub.abcapi.servicecompre.service.IDataSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class DataDetailServiceImpl implements IDataDetailService {

    @Autowired
    IApiDataChartClient dataChartClient;

    @Autowired
    IApiDataTableClient dataTableClient;

    @Autowired
    IApiDataTableSingleClient dataTableSingleClient;

    @Autowired
    IDataSearchService dataSearchService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    public String getUserAgent() {
        return httpServletRequest.getHeader("user-agent");
    }

    @Override
    public Map<String, Object> buildChartDataById(ChartDetailRequest params) throws CustomException {
//        String keyword = "$id:"+params.getId();
        String keyword = "$real_id:"+params.getId();

        DataChartRequest dataChartRequest = new DataChartRequest();
        dataChartRequest.setKeyword(keyword);
        dataChartRequest.setOffset(0);
        dataChartRequest.setLimit(20);
        dataChartRequest.setTitle_include("");
        dataChartRequest.setTitle_uninclude("");
        dataChartRequest.setIndustry("");
        dataChartRequest.setAuthor("");
        dataChartRequest.setStart_time("");
        dataChartRequest.setEnd_time("");
        dataChartRequest.setId("");
        dataChartRequest.setPublish("");
        dataChartRequest.setUserId(params.getUserId());
        dataChartRequest.setRequest_id(params.getRequest_id());
        dataChartRequest.setDevice_info(getUserAgent());
        dataChartRequest.setInput_from(params.getInput_from());
        dataChartRequest.setPage(params.getPage());

        SearchChartBO bo = dataSearchService.buildChartSearchResult(dataChartRequest);
        List<Map<String, Object>> items = bo.getItems();
        if (items == null || items.isEmpty()){
            return null;
        }

        return items.get(0);
    }

    @Override
    public Map<String,Object> buildTableDataById(String id) throws CustomException {
//        String keyword = "ID:\""+id+"\"";
        String keyword = "ID:"+id;

        ClientDataTableRequest clientDataTableRequest = new ClientDataTableRequest();
        clientDataTableRequest.setKeyword(keyword);
        clientDataTableRequest.setOffset(0);
        clientDataTableRequest.setLimit(16);

//        ClientDataTableResponse clientDataTableResponse = dataTableSingleClient.advancedSearch(keyword,0,16);
        ClientDataTableResponse clientDataTableResponse = dataTableClient.advancedSearch(clientDataTableRequest);

        if (clientDataTableResponse.getData() == null){
            return null;
        }
        List<DataTableItem> items = clientDataTableResponse.getData().getItem();
        if (items == null || items.isEmpty()){
            return null;
        }

        Map<String,Object> item = dataSearchService.buildTableSearchResultItem(items.get(0));
        return item;
    }

    /**
     * 获取多个id数据
     * @param ids
     * @return
     */
    @Override
    public List<DataChartItem> buildChartDataByIds(String ids){
        if(ids.isEmpty()){
            return null;
        }
        List<String> idList = Arrays.asList(ids.split(","));
        String keyword = "$real_id:("+String.join(" ",idList)+")";
        ClientDataChartRequest clientDataChartRequest = new ClientDataChartRequest();
        clientDataChartRequest.setKeyword(keyword);
        clientDataChartRequest.setOffset(0);
        clientDataChartRequest.setLimit(idList.size());
        ClientDataChartResponse clientDataChartResponse = dataChartClient.advancedSearch(clientDataChartRequest);
        if (clientDataChartResponse == null || clientDataChartResponse.getData() == null) {
            return null;
        }
        List<DataChartItem> items = clientDataChartResponse.getData().getItem();
        if (items == null || items.isEmpty()){
            return null;
        }
        return items;
    }

    /**
     * 获取多个id数据
     * @param ids
     * @return
     */
    @Override
    public List<DataTableItem> buildTableDataByIds(String ids) {
        if(ids.isEmpty()){
            return null;
        }
        List<String> idList = Arrays.asList(ids.split(","));
        String keyword = "ID:" + String.join(" OR  ID:",idList);
        ClientDataTableRequest clientDataTableRequest = new ClientDataTableRequest();
        clientDataTableRequest.setKeyword(keyword);
        clientDataTableRequest.setOffset(0);
        clientDataTableRequest.setLimit(idList.size());
        ClientDataTableResponse clientDataTableResponse = dataTableClient.advancedSearch(clientDataTableRequest);
        if (clientDataTableResponse == null || clientDataTableResponse.getData() == null){
            return null;
        }
        List<DataTableItem> items = clientDataTableResponse.getData().getItem();
        if (items == null || items.isEmpty()){
            return null;
        }
        return items;
    }
}
