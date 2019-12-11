package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IOvsecMainHoldDao {

    List<Date> getLatestFiveDateByComUniCode(@Param("com_uni_code") Long com_uni_code) throws Exception;

    List<Map<String, Object>> getMainHolderInfo(@Param("com_uni_code") Long com_uni_code, @Param("org_uni_code") Long org_uni_code, @Param("peo_uni_code") Long peo_uni_code, @Param("latestFiveDateList") List<Date> latestFiveDateList) throws Exception;

    List<Map<String, Object>> getMainHolderCode(@Param("com_uni_code") Long com_uni_code, @Param("latestDate") Date latestDate) throws Exception;
}
