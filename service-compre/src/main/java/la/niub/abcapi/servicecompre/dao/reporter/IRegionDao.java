package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

public interface IRegionDao {

    String selectNameByCode(@Param("code") String code);
}
