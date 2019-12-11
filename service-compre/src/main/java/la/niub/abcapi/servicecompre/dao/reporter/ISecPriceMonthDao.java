package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.SecPriceMonthModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.util.Date;
import java.util.List;

@Primary
@Mapper
public interface ISecPriceMonthDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SecPriceMonthModel record);

    int insertSelective(SecPriceMonthModel record);

    SecPriceMonthModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecPriceMonthModel record);

    int updateByPrimaryKey(SecPriceMonthModel record);

    List<SecPriceMonthModel> getRecordsBySecUniCode(@Param("secUniCode") Long secUniCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<SecPriceMonthModel> getRecords(@Param("secUniCode") Long secUniCode, @Param("startTime") Date startTime);
}