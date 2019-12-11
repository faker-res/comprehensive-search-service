package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.NtfEntityLawsuitGroupByCaseTypeAndYearModel;
import la.niub.abcapi.servicecompre.model.ntfEntityLawsuitModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IntfEntityLawsuitDao {


    List<ntfEntityLawsuitModel> selectByComUniCode(@Param("comUniCode") Long comUniCode,
                                                   @Param("caseType")String caseType,
                                                   @Param("limit")Integer limit,
                                                   @Param("offset")Integer offset);

    Integer getCount(@Param("comUniCode") Long comUniCode, @Param("caseType")String caseType);

    List<NtfEntityLawsuitGroupByCaseTypeAndYearModel> selectByComIdAndYearGroupByCaseTypeAndYear(@Param("comUniCode") Long comUniCode, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate ) ;

}