package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.SecPriceWeekModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("MarketISecPriceWeekDao")
public interface ISecPriceWeekDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SecPriceWeekModel record);

    int insertSelective(SecPriceWeekModel record);

    SecPriceWeekModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecPriceWeekModel record);

    int updateByPrimaryKey(SecPriceWeekModel record);

    List<SecPriceWeekModel> getRecords(@Param("secUniCode") Long secUniCode, @Param("startTime") Date startTime);

    List<SecPriceWeekModel> selectBySecUniCodesAfterTime(@Param("secUniCodes") List<Long> secUniCodes, @Param("time") Date time);
}