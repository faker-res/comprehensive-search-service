package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface IFundShareChangerDao {
    Map<String, Object> getPurchaseAndRedemptionBySecUniCode(@Param("secUniCode") Long secUniCode) throws Exception;
}
