package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.model.request.ChartDetailRequest;
import la.niub.abcapi.servicecompre.model.response.DataDetailResponse;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.IDataDetailService;
import la.niub.abcapi.servicecompre.service.IOperateConfigService;
import la.niub.abcapi.servicecompre.service.IShareService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/")
public class ShareController {

    @Autowired
    IShareService shareService;

    @Autowired
    IDataDetailService dataDetailService;

    @Autowired
    IOperateConfigService operateConfigService;

    /**
     * 获取分享链接
     * @param type
     * @param id
     * @return
     * @throws ServiceException
     */
    @GetMapping
    Response get(String type, Integer id) throws ServiceException {

        return new Response(shareService.buildShareUrl(type, id),"");
    }


    /**
     * 数据图详情
     * @param params
     * @return
     * @throws CustomException
     */
    @GetMapping("/na/chart/detail")
    DataDetailResponse<Map<String, Object>> chartDetail(ChartDetailRequest params) throws CustomException {
        if (!StringUtils.isEmpty(params.getId())){
            Map<String, Object> ret = dataDetailService.buildChartDataById(params);

            // 区分是否为静态数据图
            Boolean isChartImage = StringUtils.isNotEmpty((String)ret.get("chart_data"))
                    && (ret.get("confidence") != null && (Float)ret.get("confidence") > 0.5);

            DataDetailResponse response = new DataDetailResponse();
            response.setType("chart");
            response.setData(ret);
            response.setIsSharePage(true);
            response.setIsChartImage(isChartImage);
            return response;
        }
        return null;

    }

    /**
     * 精选图详情
     * @param params
     * @return
     * @throws CustomException
     */
    @GetMapping("/na/selected-drawing/detail")
    DataDetailResponse<Map<String, Object>> selectedDrawing(ChartDetailRequest params) throws CustomException {
        if (!StringUtils.isEmpty(params.getId())){
            DataDetailResponse response = new DataDetailResponse();
            response.setType("selected-drawing");
            response.setData( operateConfigService.getChartConfigone(params.getId()));
            response.setIsSharePage(true);
            response.setIsChartImage(false);
            return response;
        }
        return null;

    }

    /**
     * 数据表详情
     * @param id
     * @return
     * @throws CustomException
     */
    @GetMapping("/na/table/detail")
    DataDetailResponse<Map<String, Object>> tableDetail(String id) throws CustomException {
        if (!StringUtils.isEmpty(id)){

            Map<String, Object> ret = dataDetailService.buildTableDataById(id);

            DataDetailResponse response = new DataDetailResponse();
            response.setType("table");
            response.setData(ret);
            response.setIsSharePage(true);
            response.setIsChartImage(false);
            return response;
        }

        return null;
    }
}
