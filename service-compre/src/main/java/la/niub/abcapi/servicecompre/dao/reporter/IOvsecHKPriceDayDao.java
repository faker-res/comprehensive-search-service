package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface IOvsecHKPriceDayDao {

    BigDecimal getClosePriceBySecUniCode(@Param("sec_uni_code") Long sec_uni_code) throws Exception;
}
