package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundKeeperScaleGroupByFundKeeperModel;
import la.niub.abcapi.servicecompre.model.FundKeeperScaleModel;
import la.niub.abcapi.servicecompre.model.response.FundCompanyAchievementsInfoItemResponse;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IFundKeeperScaleDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundKeeperScaleModel record);

    int insertSelective(FundKeeperScaleModel record);

    FundKeeperScaleModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundKeeperScaleModel record);

    int updateByPrimaryKey(FundKeeperScaleModel record);

    FundKeeperScaleModel selectByFundKeeperName(String fundKeeperName);

    FundKeeperScaleModel selectByFundKeeperCode(Long fundKeeperCode);

    List<Long> getRankByFunNav();

    List<FundKeeperScaleGroupByFundKeeperModel> selectByYearGroupByKeeper(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    List<Map<String, Object>> getTopTenScaleCom(@Param("comUniCode") Long comUniCode) throws Exception;

    Integer getRankingByComUniCode(@Param("comUniCode") Long comUniCode) throws Exception;

    Integer getFundComTotalCount() throws Exception;

    List<Map<String, Object>> getComparableFundCom(@Param("comUniCode") Long comUniCode,
                                                   @Param("offset") Integer offset,
                                                   @Param("limit") Integer limit) throws Exception;

    List<Map<String,String>> getPlateCodeAndNameByComUniCode(Long com_uni_code) throws Exception;

    List<FundCompanyAchievementsInfoItemResponse> getAllFundCompanyByPlateCode(String plate_code) throws Exception;

    List<FundKeeperScaleModel> getFundQuantityOrNavByTime(@Param("field") String field, @Param("com_uni_code") Long com_uni_code,
                                                          @Param("plate_code") String plate_code, @Param("startTime") Date startTime,
                                                          @Param("endTime") Date endTime) throws Exception;

    List<FundKeeperScaleModel> getFundCompanyByPlateCode(String plate_code) throws Exception;
}