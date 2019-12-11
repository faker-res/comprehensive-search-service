package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundManagerPerfChgModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IFundManagerPerfChgDao {

    List<Map<String, Object>> getInfoByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate) throws Exception;

    FundManagerPerfChgModel getByPeoUniCodeAndSecUniCodeAndDate(@Param("peo_uni_code") Long peo_uni_code, @Param("sec_uni_code") Long sec_uni_code,
                                                                @Param("end_date") Date end_date) throws Exception;
}
