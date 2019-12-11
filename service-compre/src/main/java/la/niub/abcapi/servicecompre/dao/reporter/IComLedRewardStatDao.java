package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IComLedRewardStatDao {

    List<Date> getLatestFourDateByComUniCode(@Param("com_uni_code") Long com_uni_code, @Param("peo_uni_code") Long peo_uni_code) throws Exception;

    List<Map<String, Object>> getStockHolderInfo(@Param("com_uni_code") Long com_uni_code, @Param("peo_uni_code") Long peo_uni_code, @Param("latestFourDateList") List<Date> latestFourDateList) throws Exception;
}
