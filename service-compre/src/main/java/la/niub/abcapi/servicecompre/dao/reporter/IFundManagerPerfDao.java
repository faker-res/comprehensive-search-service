package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundManagerPerfModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFundManagerPerfDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundManagerPerfModel record);

    int insertSelective(FundManagerPerfModel record);

    FundManagerPerfModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundManagerPerfModel record);

    int updateByPrimaryKey(FundManagerPerfModel record);

    List<FundManagerPerfModel> selectMaxAnnualYieldByPeoUniCodesGroupByPeoUniCodes (@Param("peoUniCodes") List<Long> peoUniCodes);

    List<FundManagerPerfModel> getLastBySecUniCodeAndPeoUniCodes(@Param("secUniCode") Long secUniCode,@Param("peoUniCodes") List<Long> peoUniCodes);
}