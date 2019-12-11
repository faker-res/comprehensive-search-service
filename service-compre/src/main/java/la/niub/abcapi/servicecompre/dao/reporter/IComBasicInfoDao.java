package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.CombasicinfoModel;
import la.niub.abcapi.servicecompre.model.bo.theme.ComCountOfCityBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IComBasicInfoDao {

    List<ComCountOfCityBO> selectComCountOfCityAndProvCode(@Param("com_uni_code_list") List<Long> com_uni_code_list, @Param("prov_code") String prov_code) throws Exception;

    Integer selectComTotalCount(@Param("com_uni_code_list") List<Long> com_uni_code_list) throws Exception;

    List<String> selectProvNameListByComCountOfCity(@Param("com_uni_code_list") List<Long> com_uni_code_list) throws Exception;

    ComCountOfCityBO selectMaxCountInCity(@Param("com_uni_code_list") List<Long> com_uni_code_list) throws Exception;

    List<String> selectComSnameByComUniCodeList(@Param("com_uni_code_list") List<Long> com_uni_code_list) throws Exception;

    List<CombasicinfoModel>  find(Map params);
}
