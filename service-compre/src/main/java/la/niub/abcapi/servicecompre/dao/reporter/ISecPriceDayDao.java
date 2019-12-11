package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.SecPriceDayModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Primary
@Mapper
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

    BigDecimal getClosePriceBySecUniCode(@Param("sec_uni_code") Long sec_uni_code) throws Exception;

    List getDay(Map map);

    long getCount(Map map);
}