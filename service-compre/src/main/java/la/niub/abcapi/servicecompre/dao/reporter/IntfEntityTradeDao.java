package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ntfEntityTradeModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntfEntityTradeDao {
    List<ntfEntityTradeModel> selectByComUniCode(@Param("comUniCode") Long comUniCode,
                                                 @Param("limit")Integer limit,
                                                 @Param("offset")Integer offset);

    Integer getCount(@Param("comUniCode") Long comUniCode);
}