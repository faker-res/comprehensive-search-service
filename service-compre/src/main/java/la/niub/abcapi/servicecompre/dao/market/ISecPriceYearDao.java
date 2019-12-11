package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.SecPriceMonthModel;
import la.niub.abcapi.servicecompre.model.SecPriceYearModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISecPriceYearDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SecPriceYearModel record);

    int insertSelective(SecPriceYearModel record);

    SecPriceYearModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecPriceYearModel record);

    int updateByPrimaryKey(SecPriceYearModel record);

    List<SecPriceYearModel> selectBySecUniCodesAfterTime(@Param("secUniCodes") List<Long> secUniCodes, @Param("time") Date time);

    List<SecPriceYearModel> getRecords(@Param("secUniCode") Long secUniCode, @Param("startTime") Date startTime);
}