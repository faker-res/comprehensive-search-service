package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.SecPriceDayModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("MarketISecPriceDayDao")
public interface ISecPriceDayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SecPriceDayModel record);

    int insertSelective(SecPriceDayModel record);

    SecPriceDayModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecPriceDayModel record);

    int updateByPrimaryKey(SecPriceDayModel record);

    SecPriceDayModel getNewestRecord(Long secUniCode);

    List<SecPriceDayModel> getRecordsBySecUniCode(@Param("secUniCode") Long secUniCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<SecPriceDayModel> getPriceList(@Param("secUniCode") Long secUniCode, @Param("start_time") String start_time);

    List<SecPriceDayModel> getRecords(@Param("secUniCode") Long secUniCode, @Param("startTime") Date start_time);

    List<SecPriceDayModel> selectBySecUniCodesAfterTradeDate(@Param("secUniCodes") List<Long> secUniCodes, @Param("tradeDate") Date tradeDate);

    List<SecPriceDayModel> selectBySecUniCodeAndTradeDate(@Param("secUniCodeAndDate") Map<Long,Date> secUniCodeAndDate);
}