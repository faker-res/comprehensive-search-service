package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundInvestIndustryModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IFundInvestIndustryDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundInvestIndustryModel record);

    int insertSelective(FundInvestIndustryModel record);

    FundInvestIndustryModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundInvestIndustryModel record);

    int updateByPrimaryKey(FundInvestIndustryModel record);

    Date getLastEndDate(Long secUniCode);

    List<FundInvestIndustryModel> selectByEndDate(@Param("secUniCode") Long secUniCode, @Param("endDate") Date endDate, @Param("limit") Integer limit);

    FundInvestIndustryModel selectTotalByEndDate(@Param("secUniCode") Long secUniCode, @Param("endDate") Date endDate);

    List<FundInvestIndustryModel> getPeriodByIndustryCodes(@Param("secUniCode") Long secUniCode, @Param("industryCodes") List<String> industryCodes, @Param("endDate") Date endDate);

//    List<FundInvestIndustryModel> getPeriodByEndDate(@Param("secUniCode") Long secUniCode, @Param("endDate") Date endDate, @Param("limit") Integer limit);

    Date getLastEndDateBefore(@Param("secUniCode") Long secUniCode, @Param("endDate") Date endDate);

    List<Date> getLastMultiEndDate(@Param("secUniCode") Long secUniCode, @Param("limit") Integer limit);
}