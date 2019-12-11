package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundNavReturnModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import la.niub.abcapi.servicecompre.model.response.fund.FundRiseRankingItemResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IFundNavReturnDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundNavReturnModel record);

    int insertSelective(FundNavReturnModel record);

    FundNavReturnModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundNavReturnModel record);

    int updateByPrimaryKey(FundNavReturnModel record);

    FundNavReturnModel getLastBySecUniCode(Long secUniCode);

    List<Map<String, Object>> getCodeAndMatDateBySecUniCodeList(@Param("secUniCodeList") List<Long> secUniCodeList) throws Exception;

    Map<String, Object> getReturnInfoBySecUniCode(@Param("secUniCode") Long secUniCode) throws Exception;

    List<Map<String, Object>> getMaxDateBySecUniCodeList(@Param("secUniCodeList") List<Long> secUniCodeList) throws Exception;

    List<FundRiseRankingItemResponse> getNavReturnL1yBySecUniCodeAndEndDate(@Param("param") List<Map<String, Object>> param, @Param("limit") Integer limit) throws Exception;
}