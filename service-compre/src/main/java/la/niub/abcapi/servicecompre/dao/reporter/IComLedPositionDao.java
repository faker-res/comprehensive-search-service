package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComLedPositionModel;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

public interface IComLedPositionDao {

    List<ComLedPositionModel> getRecordsByUniCodeAndPost(@Param("comUniCode") Long comUniCode,
                                                         @Param("postName")String postName);

    ComLedPositionModel getRecordByComUniCode(BigInteger comUniCode) throws Exception;
}