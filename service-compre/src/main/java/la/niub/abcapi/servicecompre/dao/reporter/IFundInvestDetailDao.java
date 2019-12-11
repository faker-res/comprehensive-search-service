package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundInvestDetailModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IFundInvestDetailDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundInvestDetailModel record);

    int insertSelective(FundInvestDetailModel record);

    FundInvestDetailModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundInvestDetailModel record);

    int updateByPrimaryKey(FundInvestDetailModel record);

    Date getLastEndDate(Long secUniCode);

    List<FundInvestDetailModel> selectTopByEndDate(@Param("secUniCode") Long secUniCode, @Param("endDate") Date endDate, @Param("limit") Integer limit);
}