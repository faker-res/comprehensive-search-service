package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.secMainCirHolderModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IsecMainCirHolderDao {

    Date getMaxDate(Long comUniCode);

    List<secMainCirHolderModel> getRecords(@Param("comUniCode") Long comUniCode, @Param("endDate")Date endDate);

    List<Map<String, Object>> findbysql(@Param(value="sql")String sql);

    List<Map> find(Map map);

    long getCount(Map map);
}