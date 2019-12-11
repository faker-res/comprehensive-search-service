package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.HiborAuthorCountModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IHiborAuthorCountDao {

    List<HiborAuthorCountModel> getReportNumByPeoUniCodesAndTime(@Param("peoUniCodes") List<String> peoUniCodes,
                                                                 @Param("r_time") Integer r_time,
                                                                 @Param("limit") Integer limit);

    Integer getReportNumByPeoUniCodeAndTime(@Param("peoUniCode") String peoUniCode, @Param("r_time") Integer r_time);
}