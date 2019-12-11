package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IPeoLeaderDao {

    List<Long> getComUniCodeByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;

    List<Map<String, Object>> getPeoComNameByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;

    List<Long> getPeoUniCodeListByComUniCode(@Param("com_uni_code") Long com_uni_code) throws Exception;

    String getLedNameByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;
}
