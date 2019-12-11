package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;

public interface IFundAssetAllocationDao {
    BigDecimal getTotFundNavBySecUniCodeAndDate(@Param("sec_uni_code") Long sec_uni_code, @Param("end_date") Date end_date) throws Exception;
}
