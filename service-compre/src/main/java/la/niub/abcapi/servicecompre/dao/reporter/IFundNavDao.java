package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundNavModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IFundNavDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundNavModel record);

    int insertSelective(FundNavModel record);

    FundNavModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundNavModel record);

    int updateByPrimaryKey(FundNavModel record);

    FundNavModel selectBySecUniCode(Long sec_uni_code);

    List<FundNavModel> selectByEndDate(@Param("secUniCode") Long secUniCode, @Param("endDate") Date endDate);

    Map<String, Object> getUnitNavBySecuniCode(@Param("secUniCode") Long secUniCode) throws Exception;

    List<Map<String, Object>> getFundChart(@Param("secUniCode") Long secUniCode, @Param("startDate") Date startDate) throws Exception;

    Date getLatestDateBySecUniCode(@Param("secUniCode") Long secUniCode) throws Exception;

    List<FundNavModel> getRiseRateBySecUniCodeAndDate(@Param("sec_uni_code") Long sec_uni_code, @Param("startTime") Date startTime,
                                                      @Param("endTime") Date endTime) throws Exception;
}