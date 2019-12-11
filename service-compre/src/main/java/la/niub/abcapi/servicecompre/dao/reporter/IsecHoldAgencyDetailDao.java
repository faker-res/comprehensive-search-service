package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.secHoldAgencyDetailModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IsecHoldAgencyDetailDao {
    List<secHoldAgencyDetailModel> getRecordsByUniCode(@Param("comUniCode") Long comUniCode,
                                                       @Param("limit") Integer limit);

}