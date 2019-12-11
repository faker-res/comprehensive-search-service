package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IIndexIngreStockDao {

    List<String> selectSecCodeByIndexId(@Param("index_id") Long index_id);

    List<Map>  selectMarkBySecCode(Map map);

    List<Map>  selectNotionBySecCode(Map map);

    long getMarkCount(Map map);

    long getNotionCount(Map map);
}
