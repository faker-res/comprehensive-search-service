package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface INtfEntityComSubDao {
    Integer getInvestInfoCountByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;

    List<Map<String, Object>> getInvestInfoListByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code, @Param("index") Integer index,
                                                            @Param("limit") Integer limit, @Param("order") String order) throws Exception;

    Integer getBusinessRoleCountByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;

    List<Map<String, Object>> getBusinessRoleListByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code, @Param("index") Integer index,
                                                              @Param("limit") Integer limit, @Param("order") String order) throws Exception;
}
