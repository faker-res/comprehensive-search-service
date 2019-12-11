package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComCashflowModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IComCashflowDao {

    List<ComCashflowModel> getTopNRecords(@Param("comUniCode") Long comUniCode, @Param("limit") Integer limit);

}