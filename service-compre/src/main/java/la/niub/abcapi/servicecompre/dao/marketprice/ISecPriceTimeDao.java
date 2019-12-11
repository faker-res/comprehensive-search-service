package la.niub.abcapi.servicecompre.dao.marketprice;

import la.niub.abcapi.servicecompre.model.SecPriceTimeModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISecPriceTimeDao {
    int deleteByPrimaryKey(Long id);

    int insert(SecPriceTimeModel record);

    int insertSelective(SecPriceTimeModel record);

    SecPriceTimeModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecPriceTimeModel record);

    int updateByPrimaryKey(SecPriceTimeModel record);

    Date getLastTradeDate();

    List<SecPriceTimeModel> selectMostRisk(@Param("secCodes") List<String> secCodes, @Param("startTime") Date startTime, @Param("limit") Integer limit);

    List<SecPriceTimeModel> selectMostFall(@Param("secCodes") List<String> secCodes, @Param("startTime") Date startTime, @Param("limit") Integer limit);
}