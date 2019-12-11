package la.niub.abcapi.servicecompre.dao;

import la.niub.abcapi.servicecompre.model.nosql.HbChartsModel;
import la.niub.abcapi.servicecompre.model.nosql.HbTablesModel;

import java.util.List;

public interface INoticeParsedDao {

    List<HbChartsModel> findChartsByFileId(Integer fileId);

    List<HbTablesModel> findTablesByFileId(Integer fileId);
}
