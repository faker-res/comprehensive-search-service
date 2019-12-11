package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComBalanceYoyModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IComBalanceYoyDao {

    List<ComBalanceYoyModel> getTopNRecords(@Param("comUniCode") Long comUniCode, @Param("limit") Integer limit);

}