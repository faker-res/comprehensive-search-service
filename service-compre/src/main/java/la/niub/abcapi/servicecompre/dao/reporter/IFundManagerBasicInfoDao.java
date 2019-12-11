package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundManagerBasicInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IFundManagerBasicInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundManagerBasicInfoModel record);

    int insertSelective(FundManagerBasicInfoModel record);

    FundManagerBasicInfoModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundManagerBasicInfoModel record);

    int updateByPrimaryKey(FundManagerBasicInfoModel record);

    List<FundManagerBasicInfoModel> selectBySecUniCodes(@Param("secUniCodes") List<Long> secUniCodes);

//    List<FundManagerBasicInfoModel> selectBySecUniCodeAndNullEndDate(@Param("secUniCode") Long secUniCode, @Param("nullEndDate") Boolean nullEndDate, @Param("limit") Integer limit);

    List<FundManagerBasicInfoModel> selectBySecUniCode(@Param("secUniCode") Long secUniCode);

    String getFundMangerNameBySecUniCode(@Param("secUniCode") Long secUniCode) throws Exception;

    List<FundManagerBasicInfoModel> getFundManagerInfoByPeoUniCodeAndDate(@Param("peo_uni_code") Long peo_uni_code,
                                                                          @Param("startTime") Date startTime,
                                                                          @Param("endTime") Date endTime) throws Exception;

    String getFundManagerInfoBySecUniCodeAndDateAndPeoUniCode(@Param("peo_uni_code") Long peo_uni_code, @Param("sec_uni_code") Long sec_uni_code,
                                                              @Param("begin_date") Date begin_date, @Param("end_date") Date end_date) throws Exception;

    List<FundManagerBasicInfoModel> getEndDateByPeoUniCodeAndSecUniCodeAndDate(@Param("peo_uni_code") Long peo_uni_code, @Param("sec_uni_code") Long sec_uni_code,
                                                          @Param("begin_date") Date begin_date, @Param("end_date") Date end_date) throws Exception;
}