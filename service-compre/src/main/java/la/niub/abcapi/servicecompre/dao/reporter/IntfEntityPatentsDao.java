package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ntfEntityPatentsModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntfEntityPatentsDao {

    List<ntfEntityPatentsModel> selectByComUniCode(@Param("comUniCode") Long comUniCode,
                                                   @Param("limit")Integer limit,
                                                   @Param("offset")Integer offset);

    Integer getCount(@Param("comUniCode") Long comUniCode);

}