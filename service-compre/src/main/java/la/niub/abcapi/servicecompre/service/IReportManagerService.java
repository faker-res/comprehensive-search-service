package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartGraphChartBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartGraphTableBO;
import la.niub.abcapi.servicecompre.model.bo.IndustryNameBO;
import la.niub.abcapi.servicecompre.model.cache.ReportCategoryFlatModel;
import la.niub.abcapi.servicecompre.model.cache.ReportCategoryModel;
import la.niub.abcapi.servicecompre.model.cache.SecIndustryFaltModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IReportManagerService {

    List<ComprehensiveChartGraphChartBO> getReportCharts(Integer reportId);

    List<ComprehensiveChartGraphTableBO> getReportTables(Integer reportId);

    /**
     * [getParaFromTextFile 从text_file获取研报段落数据]
     *
     * @param idTextFileMap {src_id, text_file}
     * @return
     */
    Map<String, List<String>> getParaFromTextFile(Map<String, String> idTextFileMap);

    /**
     * [getParaFromMongo 从mongo获取研报段落数据]
     * @param    ids       $srcIds [包含报告srcId的二维数组]
     * @return   [type]        [段落结果数组]
     */
    @Deprecated
    Map<String,List<String>> getParaFromMongo(List<Integer> ids);

    /**
     * [getCategory 从redis查询研报的分类]
     * @param    type                  $type [分类的类型默认tree树状结构
     *                                          'flat' 平的]
     * @return   array                      [结果数组]
     */
    List<ReportCategoryModel> getCategoryTree();

    /**
     * [getCategory 从redis查询研报的分类]
     * @param    type                  $type [分类的类型默认tree树状结构
     *                                          'flat' 平的]
     * @return   array                      [结果数组]
     */
    Map<String,ReportCategoryFlatModel> getCategoryFlat();

    /**
     * 获取最低级的分类id
     * @param list
     * @return
     */
    String getLowestReportType(List<String> list);

    /**
     * [getIndustryName 通过code查询行业名字]
     * @param    codeList                   $codelist [行业代码列表]
     * @return   [array]                   [行业名，行业代码]
     */
    IndustryNameBO getIndustryName(List<String> codeList) throws CustomException;

    /**
     * [getFlatIndustryFromRedis 从redis读取全部行业分类]
     * @return   [array]   [包含三级的行业分类]
     */
    SecIndustryFaltModel getFlatIndustryFromRedis();

    /**
     * 获取一级行业包括下属二、三级行业的研报数量
     * @param indexCode
     * @return
     */
    Integer getIndustryReportCount(String indexCode, Date startTime);

    /**
     * 获取一级行业包括下属二、三级行业的研报数量
     * @param indexCodes
     * @param startTime
     * @return
     */
    Map<String,Integer> getIndustryReportCount(List<String> indexCodes, Date startTime);
}
