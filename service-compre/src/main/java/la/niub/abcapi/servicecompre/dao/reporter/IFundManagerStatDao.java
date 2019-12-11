package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundManagerStatModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFundManagerStatDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundManagerStatModel record);

    int insertSelective(FundManagerStatModel record);

    FundManagerStatModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundManagerStatModel record);

    int updateByPrimaryKey(FundManagerStatModel record);

    List<FundManagerStatModel> selectByPeoUniCodes(@Param("peoUniCodes") List<Long> peoUniCodes);

    List<Long> getRankByFundManageNav();
}