package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IFundUserNewsDao {

    List<Map<String, Object>> getNewCountByPeoUniCodeAndStartDate(@Param("peo_uni_code") Long peo_uni_code, @Param("startDate") Long startDate) throws Exception;
}
