package la.niub.abcapi.servicecompre.controller;

import com.alibaba.fastjson.JSON;
import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.model.bo.SearchChartBO;
import la.niub.abcapi.servicecompre.model.bo.SearchTableBO;
import la.niub.abcapi.servicecompre.model.request.ChartDetailRequest;
import la.niub.abcapi.servicecompre.model.request.DataChartRequest;
import la.niub.abcapi.servicecompre.model.request.DataDownXlsRequest;
import la.niub.abcapi.servicecompre.model.request.DataTableRequest;
import la.niub.abcapi.servicecompre.model.request.UserChartRequest;
import la.niub.abcapi.servicecompre.model.response.DataChartResponse;
import la.niub.abcapi.servicecompre.model.response.DataDetailResponse;
import la.niub.abcapi.servicecompre.model.response.DataTableResponse;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.IDataDetailService;
import la.niub.abcapi.servicecompre.service.IDataSearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 数据图/数据表
 */
@RestController
@RequestMapping(path = "/data")
public class DataController {

    private static Logger logger = LogManager.getLogger(DataController.class);

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    IDataSearchService searchService;

    @Autowired
    IDataDetailService dataDetailService;

//    @Autowired
//    IKeywordService keywordService;

    /**
     * 获取数据图
     * @param params
     * @return
     * @throws CustomException
     */
    @GetMapping("/chart")
    Response<DataChartResponse> getChart(DataChartRequest params) throws CustomException {
//        String origin = params.getKeyword();
//        String newKeyword = "";
//        if (params.getSuggest()){
//            newKeyword = keywordService.getOneKeywordSuggest(params.getKeyword());
//            if (!StringUtil.isEmpty(newKeyword)){
//                params.setKeyword(newKeyword);
//                logger.info("use suggest keyword: "+newKeyword);
//            }
//        }

//        if (!params.getNolog()){
//            logger.info("add search log of keyword: "+params.getKeyword());
//            searchLogService.addSearchlog("ai-search-chart",params.getKeyword(),params.getUserid());
//        }



        SearchChartBO searchChartBO = searchService.buildChartSearchResult(params);

        DataChartResponse dataChartResponse = new DataChartResponse();
        dataChartResponse.setCurrent_page(searchChartBO.getCurrent_page());
        dataChartResponse.setTotal_count(searchChartBO.getTotal_count());
        dataChartResponse.setTotal_page(searchChartBO.getTotal_page());
        dataChartResponse.setRequest_id(searchChartBO.getRequest_id());
        dataChartResponse.setUnhighlight_fields(searchChartBO.getUnhighlight_fields());
        dataChartResponse.setHighlight_fields(searchChartBO.getHighlight_fields());
        dataChartResponse.setKeyword_suggest(searchChartBO.getKeyword_suggest());

//        if (params.getSuggest()){
//            Map<String,String> keywordSuggest = new HashMap<>();
//            keywordSuggest.put("origin",origin);
//            keywordSuggest.put("suggest",newKeyword);
//            dataChartResponse.setKeyword_suggest(keywordSuggest);
//        }

        List<Map<String, Object>> items = searchChartBO.getItems();
//        if (!items.isEmpty() && !StringUtil.isEmpty(params.getUserId())){
//            items = favoriteService.addHasFavorites(items,"chart",params.getUserid());
//        }
        dataChartResponse.setItems(items);
        dataChartResponse.setSelected(searchChartBO.getSelected());
        dataChartResponse.setOption(searchChartBO.getOption());

        return new Response<>(dataChartResponse);
    }

    /**
     * 获取数据表
     * @param params
     * @return
     * @throws CustomException
     */
    @GetMapping("/table")
    Response<DataTableResponse> getTable(DataTableRequest params) throws CustomException {
//        String origin = params.getKeyword();
//        String newKeyword = "";
//        if (params.getSuggest()){
//            newKeyword = keywordService.getOneKeywordSuggest(params.getKeyword());
//            System.out.println("use suggest keyword: "+newKeyword);
//            if (!StringUtil.isEmpty(newKeyword)){
//                params.setKeyword(newKeyword);
//                logger.info("use suggest keyword: {}",newKeyword);
//            }
//        }
//
//        if (!params.getNolog()){
//            logger.info("add search log of keyword: "+params.getKeyword());
//            searchLogService.addSearchlog("ai-search-table",params.getKeyword(),params.getUserid());
//        }

        SearchTableBO searchTableBO = searchService.buildTableSearchResult(params);

        DataTableResponse dataTableResponse = new DataTableResponse();
        dataTableResponse.setCurrent_page(searchTableBO.getCurrent_page());
        dataTableResponse.setTotal_count(searchTableBO.getTotal_count());
        dataTableResponse.setTotal_page(searchTableBO.getTotal_page());
        dataTableResponse.setRequest_id(searchTableBO.getRequest_id());
        dataTableResponse.setKeyword(searchTableBO.getKeyword());

//        if (params.getSuggest()){
//            Map<String,String> keywordSuggest = new HashMap<>();
//            keywordSuggest.put("origin",origin);
//            keywordSuggest.put("suggest",newKeyword);
//            dataTableResponse.setKeyword_suggest(keywordSuggest);
//        }

        List<Map<String, Object>> items = searchTableBO.getItems();
//        if (!items.isEmpty() && !StringUtil.isEmpty(params.getUserid())){
//            items = favoriteService.addHasFavorites(items,"table",params.getUserid());
//        }
        dataTableResponse.setItems(items);
        dataTableResponse.setSelected(searchTableBO.getSelected());
        dataTableResponse.setOption(searchTableBO.getOption());

        return new Response<>(dataTableResponse);
    }

    /**
     * 数据图详情
     * @param params
     * @return
     * @throws CustomException
     */
    @GetMapping("/chart/detail")
    DataDetailResponse<Map<String, Object>> chartDetail(ChartDetailRequest params) throws CustomException {
        if (StringUtils.isEmpty(params.getId())){
            return new DataDetailResponse();
        }

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

    /**
     * 数据表详情
     * @param id
     * @return
     * @throws CustomException
     */
    /**
     * 数据表详情
     * @param id
     * @return
     * @throws CustomException
     */
    @GetMapping("/table/detail")
    DataDetailResponse<Map<String, Object>> tableDetail(String id) throws CustomException {
        if (StringUtils.isEmpty(id)){
            return new DataDetailResponse();
        }

        Map<String, Object> ret = dataDetailService.buildTableDataById(id);

        DataDetailResponse response = new DataDetailResponse();
        response.setType("table");
        response.setData(ret);
        response.setIsSharePage(true);
        response.setIsChartImage(false);
        return response;
    }

    @PostMapping("/down/xls")
//    @RequestMapping(value = "/down/xls",method = {RequestMethod.GET,RequestMethod.POST})
    void downXls(String jsonstr) throws IOException {
        if (StringUtils.isEmpty(jsonstr)){
            return;
        }
//        jsonstr = "{\"title\":\"国内生产总值(累计值) Gross Domestic Product (Accumulated)\",\"data\":[[\"年\",\"季\",\"国内生产总值\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"按产业分 Grouped by Industry\",\"\",\"\",\"按行业分 Grouped by Sector\"],[\"\",\"\",\"\",\"第一产业\",\"第二产业\",\"第三产业\",\"农林牧渔业\",\"\",\"工业\"],[\"Year\",\"Quarter\",\"Gross Domestic Product\",\"Primary Industry\",\"Secondary Industry\",\"Tertiary Industry\",\"Agriculture, Forestry, Animal Husbandry and Fishing\",\"\",\"Industry\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"（亿元）    (100 million yuan)\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"2015\",\"I-IV\",\"689052.1\",\"60862.1\",\"282040.3\",\"346149.7\",\"62911.8\",\"\",\"236506.3\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"2016\",\"I\",\"161456.3\",\"8803.2\",\"61385.1\",\"91268.1\",\"\",\"9154.6\",\"54118.4\"],[\"\",\"I-II\",\"342071.4\",\"22097.3\",\"135115.8\",\"184858.3\",\"\",\"22936.3\",\"115612.9\"],[\"\",\"I-III\",\"532434\",\"40667.1\",\"210754.8\",\"281012.2\",\"\",\"42123.6\",\"178125.5\"],[\"\",\"I-IV\",\"743585.5\",\"63672.8\",\"296547.7\",\"383365\",\"\",\"65975.7\",\"247877.7\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"2017\",\"I\",\"180385.3\",\"8654\",\"70084.4\",\"101646.9\",\"\",\"9041\",\"61928.6\"],[\"\",\"I-II\",\"380944\",\"21987\",\"153212.9\",\"205744.1\",\"\",\"22929\",\"131299.6\"],[\"\",\"I-III\",\"592539.5\",\"41229.1\",\"238445.9\",\"312864.6\",\"\",\"42854.3\",\"201791.6\"],[\"\",\"I-IV\",\"827121.7\",\"65467.6\",\"334622.6\",\"427031.5\",\"\",\"68008.7\",\"279996.9\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"比上年同期增长（%）    Year-on-year Growth Rate (%)\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"2015\",\"I-IV\",\"6.9\",\"3.9\",\"6.2\",\"8.2\",\"\",\"4\",\"6\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"2016\",\"I\",\"6.7\",\"2.9\",\"6\",\"7.5\",\"\",\"3.1\",\"5.7\"],[\"\",\"I-II\",\"6.7\",\"3\",\"6.2\",\"7.5\",\"\",\"3.2\",\"5.9\"],[\"\",\"I-III\",\"6.7\",\"3.5\",\"6.3\",\"7.5\",\"\",\"3.6\",\"6\"],[\"\",\"I-IV\",\"6.7\",\"3.3\",\"6.3\",\"7.7\",\"\",\"3.5\",\"6\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"2017\",\"I\",\"6.9\",\"3\",\"6.4\",\"7.7\",\"\",\"3.2\",\"6.5\"],[\"\",\"I-II\",\"6.9\",\"3.5\",\"6.4\",\"7.7\",\"\",\"3.6\",\"6.5\"],[\"\",\"I-III\",\"6.9\",\"3.7\",\"6.3\",\"7.8\",\"\",\"3.8\",\"6.4\"],[\"\",\"I-IV\",\"6.9\",\"3.9\",\"6.1\",\"8\",\"\",\"4.1\",\"6.4\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"年\",\"季\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"按行业分 Grouped by Sector\"],[\"\",\"\",\"建筑业\",\"批发和零售业\",\"交通运输、仓储和邮政业\",\"住宿和餐饮业\",\"金融业\",\"房地产业\",\"其他\"],[\"Year\",\"Quarter\",\"Construction\",\"Wholesale and Retail Trade\",\"Transport, Storage and Postal Service\",\"Accomodation and Catering Trade\",\"Finance\",\"Real Estate\",\"Others\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"（亿元）   (100 million yuan)\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"2015\",\"I-IV\",\"46626.7\",\"66186.7\",\"30487.8\",\"12153.7\",\"57872.6\",\"41701\",\"134605.5\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"2016\",\"I\",\"7492.3\",\"16306.9\",\"7223\",\"3123.7\",\"15535.8\",\"10973.3\",\"37528.4\"],[\"\",\"I-II\",\"19984.6\",\"33386.7\",\"15476.1\",\"6178.9\",\"30618.4\",\"22774.7\",\"75102.6\"],[\"\",\"I-III\",\"33371.5\",\"51281.2\",\"24056.8\",\"9588.2\",\"45835.2\",\"34952.8\",\"113099.2\"],[\"\",\"I-IV\",\"49702.9\",\"71290.7\",\"33058.8\",\"13358.1\",\"61121.7\",\"48190.9\",\"153008.9\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"2017\",\"I\",\"8391.3\",\"17842.4\",\"7994.8\",\"3429\",\"16907.8\",\"12403.2\",\"42447.2\"],[\"\",\"I-II\",\"22407.8\",\"36475\",\"17255.8\",\"6764.4\",\"32918.9\",\"25686.3\",\"85207.2\"],[\"\",\"I-III\",\"37401.1\",\"55952.8\",\"26830.4\",\"10484.9\",\"49446.8\",\"38990.9\",\"128786.8\"],[\"\",\"I-IV\",\"55689\",\"77743.7\",\"36802.7\",\"14594.1\",\"65748.9\",\"53850.7\",\"174687.1\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"比上年同期增长（%）    Year-on-year Growth Rate (%)\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"2015\",\"I-IV\",\"6.8\",\"6.1\",\"4.1\",\"6.2\",\"16\",\"3.2\",\"9.3\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"2016\",\"I\",\"8.4\",\"6.2\",\"3.4\",\"7.5\",\"7\",\"9.1\",\"8.9\"],[\"\",\"I-II\",\"8.2\",\"6.6\",\"4.7\",\"7.4\",\"5.5\",\"9\",\"9\"],[\"\",\"I-III\",\"7.6\",\"6.9\",\"5.4\",\"7.2\",\"5.1\",\"8.9\",\"9\"],[\"\",\"I-IV\",\"7.2\",\"7.1\",\"6.6\",\"7.4\",\"4.5\",\"8.6\",\"9.4\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"2017\",\"I\",\"5.3\",\"7.4\",\"8.7\",\"7.4\",\"4.4\",\"7.8\",\"9.2\"],[\"\",\"I-II\",\"5.3\",\"7.3\",\"9.2\",\"7.2\",\"3.9\",\"7\",\"9.6\"],[\"\",\"I-III\",\"4.8\",\"7.2\",\"9.2\",\"7.2\",\"4.6\",\"5.9\",\"10\"],[\"\",\"I-IV\",\"4.3\",\"7.1\",\"9\",\"7.1\",\"4.5\",\"5.6\",\"10.4\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"注：①三次产业分类依据国家统计局2012年制定的《三次产业划分规定》（表2.1.3同）。\"],[\"②行业分类采用《国民经济行业分类（GB/T 4754－2011）》（表2.1.2同）。\"],[\"Notes: ① The classification of three industries is in accordance to the \\\"Division Standard of Three Industries\\\" adopted by the National Bureau of Statistics in 2012 (Also applicable to Table 2.1.3).\"],[\"② The sector classification is in accordance to the \\\"Sector Classification of the National Economy (GB/T 4754－2011)\\\" (Also applicable to Table 2.1.2).\"]]}";

        DataDownXlsRequest dataDownXlsRequest = JSON.parseObject(jsonstr,DataDownXlsRequest.class);
        List<List<String>> data = dataDownXlsRequest.getData();

        //创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表sheet
        HSSFSheet sheet = workbook.createSheet();
        Integer rowIndex = 0;
        for (List<String> cells : data){
            Integer cellIndex = 0;
            //创建行
            HSSFRow row = sheet.createRow(rowIndex);
            for (String cell : cells){
                row.createCell(cellIndex).setCellValue(cell);
                cellIndex++;
            }
            rowIndex++;
        }

        String title = dataDownXlsRequest.getTitle();
        title.replace("-","");
        if (title.length() > 30){
            title = title.substring(0,30);
        }
        String fileName = title+".xls";
//        fileName = ChangeCharset.toGBK(fileName);

//        response.setHeader("Content-Type","text/html; charset=utf8");
//        response.setHeader("Content-Type","application/octet-stream");
        String userAgent = request.getHeader("user-agent");
        if (userAgent.contains("Firefox") || userAgent.contains("")){
            response.setHeader("Content-Type","application/vnd.ms-excel");
        }else{
            response.setHeader("Content-Type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        }
//        response.setHeader("Content-Disposition","attachment; filename=\"" + fileName+"\"");
        response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName,"UTF-8"));
        response.setHeader("Content-Transfer-Encoding","binary");
        response.setHeader("Expires","0");
        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma","public");
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Length",String.valueOf(workbook.getBytes().length));
        workbook.write(response.getOutputStream());
        workbook.close();

//        //创建excel文件
//        File file=new File("/data/tmp/poi.xls");
//        try {
//            file.createNewFile();
//            //将excel写入
//            FileOutputStream stream= FileUtils.openOutputStream(file);
//            workbook.write(stream);
//            stream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        return JSON.toJSONString(request);
    }


    /**
     * 个人资源库：获取数据图
     * @param params
     * @return
     * @throws CustomException
     */
    @GetMapping("/user_chart")
    Response<DataChartResponse> getUserChart(UserChartRequest params) throws CustomException {
        SearchChartBO searchChartBO = searchService.buildUserCenterChartSearchResult(params);
        DataChartResponse dataChartResponse = new DataChartResponse();
        dataChartResponse.setCurrent_page(searchChartBO.getCurrent_page());
        dataChartResponse.setTotal_count(searchChartBO.getTotal_count());
        dataChartResponse.setTotal_page(searchChartBO.getTotal_page());
        dataChartResponse.setRequest_id(searchChartBO.getRequest_id());
        dataChartResponse.setUnhighlight_fields(searchChartBO.getUnhighlight_fields());
        dataChartResponse.setHighlight_fields(searchChartBO.getHighlight_fields());
        dataChartResponse.setKeyword_suggest(searchChartBO.getKeyword_suggest());

        List<Map<String, Object>> items = searchChartBO.getItems();

        dataChartResponse.setItems(items);

        return new Response<>(dataChartResponse);
    }

}
