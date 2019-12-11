package la.niub.abcapi.servicecompre.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.component.util.ExcelUtil;
import la.niub.abcapi.servicecompre.component.util.HttpUtil;
import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.component.util.ZipUtil;
import la.niub.abcapi.servicecompre.model.request.BatchExportRequest;
import la.niub.abcapi.servicecompre.model.request.TableDataDownXlsRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.model.response.client.datachart.DataChartItem;
import la.niub.abcapi.servicecompre.model.response.client.datatable.DataTableItem;
import la.niub.abcapi.servicecompre.service.ICommonService;
import la.niub.abcapi.servicecompre.service.IDataDetailService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class CommonController {

    @Autowired
    ICommonService commonService;

    @Autowired
    IDataDetailService dataDetailService;

    @Autowired
    HttpServletResponse response;

    @Autowired
    HttpServletRequest request;

    @GetMapping("batch_export")
    Response batchExport(BatchExportRequest request) throws CustomException {
        return new Response(commonService.buildBatchExport(request));
    }

    /**
     * 数据表 批量导出多个sheet
     * @param ids
     * @param keyword
     */
    @GetMapping("download/table")
    void exportTable(String ids,String keyword) {
        List<DataTableItem> items = dataDetailService.buildTableDataByIds(ids);
        if(items != null){
            try {
                if(keyword.length() > 30){
                    keyword = keyword.substring(0,30);
                }
                String fileName;
                if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
                    // 火狐兼容问题
                    fileName = new String(keyword.getBytes(),"ISO-8859-1") + ".xls";
                } else {
                    fileName = new String(keyword.getBytes("gbk"), "iso-8859-1") + ".xls";
                }
                response.reset();
                response.setContentType("application/octet-stream");
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
                ServletOutputStream out = response.getOutputStream();
                ExcelUtil excelUtil = new ExcelUtil();
                HSSFWorkbook workbook = new HSSFWorkbook();
                // 获取数据表数据
                Integer sheetNum = 1;
                String illegal = ":\\/?*[]";
                for (DataTableItem item: items){
                    if(item.getTable_data() == null || item.getTable_data().isEmpty()){
                        continue;
                    }
                    String sheetTitle = (item.getTable_title() != null && !item.getTable_title().isEmpty()) ?
                            item.getTable_title() : "";
                    if (sheetTitle.length() > 20){
                        sheetTitle = sheetTitle.substring(0,20);
                    }
                    // 存在非法名称
                    if(StringUtil.validateLegalString(sheetTitle,illegal)){
                        sheetTitle = "";
                    }
                    sheetTitle += "("+sheetNum+")";
                    String tableData = item.getTable_data();
                    if(tableData.startsWith("http://") || tableData.startsWith("https://")) {
                        String jsonStr = HttpUtil.get(tableData, null, null);
                        if(jsonStr == null || jsonStr.isEmpty()) {
                            continue;
                        }
                        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
                        tableData = jsonObject.getString("data");
                    }
                    if(tableData == null || tableData.isEmpty()) {
                        continue;
                    }
                    List<TableDataDownXlsRequest> dataTable = JSON.parseObject( tableData,
                            new TypeReference<List<TableDataDownXlsRequest>>(){}
                    );
                    excelUtil.exportExcelByTableDate(workbook, sheetNum-1, sheetTitle, dataTable);
                    sheetNum++;
                }
                //将所有的数据一起写入，然后再关闭输入流。
                workbook.write(out);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 数据图批量导出多张表并压缩
     */
    @GetMapping("download/chart")
    void exportZipChart(String ids,String keyword){
        List<DataChartItem> items = dataDetailService.buildChartDataByIds(ids);
        if(items != null){
            List<String> srcFileUrls = new ArrayList<>();
            List<String> srcFilesName = new ArrayList<>();
            Integer i = 1 ;
            for (DataChartItem item : items) {
                if(item.getImage_url() == null || item.getImage_url().isEmpty()){
                    continue;
                }
                srcFileUrls.add(item.getImage_url());
                String sheetTitle = (item.getImage_title() != null && !item.getImage_title().isEmpty()) ?
                        item.getImage_title() : "";
                if (sheetTitle.length() > 20){
                    sheetTitle = sheetTitle.substring(0,20);
                }
                sheetTitle += "("+i+")";
                srcFilesName.add(sheetTitle);
                i++;
            }
            ZipUtil zipUtil = new ZipUtil();
            if(keyword.length() > 30){
                keyword = keyword.substring(0,30);
            }
            zipUtil.zip(request, response, srcFileUrls, srcFilesName, keyword+".zip");
        }
    }
}
