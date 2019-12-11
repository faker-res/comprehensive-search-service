package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IOvsecIpoInfoDao {

    List<Map<String, Object>> getFinanceAndStockCodeByComUniCodeList(@Param("com_uni_code_list")List<Long> com_uni_code_list) throws Exception;
}
