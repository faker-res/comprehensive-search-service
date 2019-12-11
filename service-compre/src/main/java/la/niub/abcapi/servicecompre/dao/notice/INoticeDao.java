package la.niub.abcapi.servicecompre.dao.notice;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface INoticeDao {

    List<Map<String, Object>> getNoticeCountByStockCodeListAndStartDate(@Param("stockCodeList") List<String> stockCodeList, @Param("startDate") Date startDate);
}
