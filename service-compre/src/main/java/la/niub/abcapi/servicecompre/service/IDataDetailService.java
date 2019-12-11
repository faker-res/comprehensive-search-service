package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.model.request.ChartDetailRequest;
import la.niub.abcapi.servicecompre.model.response.client.datachart.DataChartItem;
import la.niub.abcapi.servicecompre.model.response.client.datatable.DataTableItem;

import java.util.List;
import java.util.Map;

/**
 * 获取数据详情
 */
public interface IDataDetailService {

    Map<String, Object> buildChartDataById(ChartDetailRequest params) throws CustomException;

    Map<String,Object> buildTableDataById(String id) throws CustomException;

    List<DataChartItem> buildChartDataByIds(String ids);

    List<DataTableItem> buildTableDataByIds(String ids);
}
