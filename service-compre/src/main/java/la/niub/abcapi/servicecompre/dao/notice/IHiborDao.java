package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.HiborModel;
import la.niub.abcapi.servicecompre.model.dao.HiborGroupByDaoModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IHiborDao {
    int deleteByPrimaryKey(Long added_id);

    int insert(HiborModel record);

    int insertSelective(HiborModel record);

    HiborModel selectByPrimaryKey(Long added_id);

    int updateByPrimaryKeySelective(HiborModel record);

    int updateByPrimaryKey(HiborModel record);

    HiborModel selectById(Long id);

    List<HiborModel> buildRecordsByStockWithAnalystAndLimit(@Param("stockCode") String stockCode, @Param("limit") Integer limit);

    List<String> findAnalyst(@Param("stockCode") String stockCode, @Param("limit") Integer limit);

    List<HiborGroupByDaoModel> selectByPublishAndTimeGroupByIndustryId(@Param("publish") String publish, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    int countByPublishAndTime(@Param("publish") String publish, @Param("beginTime") Date beginTime);

    Integer selectCountByCategoryIdList(@Param("categroy_id_list") List<String> categroy_id_list, @Param("publish") String publish, @Param("beginTime") Date beginTime);

    List<HiborModel> selectLatestReportByOrgUniCodeAndTypeId(@Param("publish") String publish, @Param("categroy_id_list") List<String> categroy_id_list);

    List<String> selectAuthorByPublishAndCategoryIdListAndNameList(@Param("publish") String publish, @Param("categroy_id_list") List<String> categroy_id_list, @Param("name_list") List<String> name_list);

    List<HiborGroupByDaoModel> selectByIndustryIdsAndTimeGroupByIndustryId(@Param("industryIds") List<String> industryIds, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    List<HiborGroupByDaoModel> selectByStockCodesAndTimeGroupByStockCode(@Param("stockCodes") List<String> stockCodes, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    Integer selectCountByIndustryIds(@Param("industryIds") List<String> industryIds, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    List<Map<String, Object>> getReportBasicInfoByStockCodeListAndTime(@Param("stockCodeList") List<String> stockCodeList, @Param("startDate") Date startDate) throws Exception;

    List<Map<String, Object>> getReportByAbcCodeAndPublishAndType(@Param("abcCodes") List<String> abcCodes,
                                                                  @Param("publish") String publish,
                                                                  @Param("prior") String prior,
                                                                  @Param("limit") Integer limit) throws Exception;

    Integer getReportCountByAbcCodesAndBrokerName(@Param("abcCodes") List<String> abcCodes, @Param("brokerName") String brokerName) throws Exception;
}