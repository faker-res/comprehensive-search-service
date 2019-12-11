package la.niub.abcapi.servicecompre.dao.reporter;
import la.niub.abcapi.servicecompre.model.ComProfitSheetYoyModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IComProfitSheetYoyDao {

    List<ComProfitSheetYoyModel> getTopNRecords(@Param("comUniCode") Long comUniCode, @Param("limit") Integer limit);

    List<Map<String,Object>>  financialSummary(Map map);
}