package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IComIpoInfoNewDao {

    BigDecimal getFinanceByComUniCodeList(@Param("com_uni_code_list") List<Long> com_uni_code_list) throws Exception;
}
