package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface INtfJzComInfoDao {

    Integer getComInfoCountByComIds(@Param("comIds") List<Integer> comIds) throws Exception;

    List<Map<String,Object>> getComInfoListByComIds(@Param("comIds") List<Integer> comIds, @Param("index") Integer index,
                                                    @Param("limit") Integer limit, @Param("financingAmountSort") Integer financingAmountSort) throws Exception;
}
