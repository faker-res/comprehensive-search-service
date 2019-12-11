package la.niub.abcapi.servicecompre.dao;

import la.niub.abcapi.servicecompre.model.nosql.BackendChartsModel;
import la.niub.abcapi.servicecompre.model.nosql.HbChartsModel;
import la.niub.abcapi.servicecompre.model.nosql.HbTextModel;

import java.util.List;

public interface IParagraphDao {

    /**
     * [getParaFromMongo 从mongo获取研报段落数据]
     * @param    ids       $srcIds [包含报告srcId的二维数组]
     * @return   [type]        [段落结果数组]
     */
    List<HbTextModel> getParagraphs(List<Integer> ids);

    /**
     * [getParaFromMongo 从hb_charts获取图表内容]
     * @param    report_id      report_id
     * @return   [type]        [图表内容]
     */
    List<HbChartsModel> getRecordsFromHB_Charts(Integer report_id);

    /**
     * [getParaFromMongo 从backend_charts获取图表内容]
     * @param    report_id      report_id
     * @return   [type]        [图表内容]
     */
    List<BackendChartsModel> getRecordsFromBackend_Charts(Integer report_id);
}
