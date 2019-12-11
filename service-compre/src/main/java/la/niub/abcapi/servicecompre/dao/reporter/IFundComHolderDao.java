package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundComHolderModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFundComHolderDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundComHolderModel record);

    int insertSelective(FundComHolderModel record);

    FundComHolderModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundComHolderModel record);

    int updateByPrimaryKey(FundComHolderModel record);

    List<FundComHolderModel> selectByOrgUniCode(@Param("orgUniCode") Long orgUniCode, @Param("limit") Integer limit);

    List<FundComHolderModel> getLastByOrgUniCode(@Param("orgUniCode") Long orgUniCode, @Param("limit") Integer limit);
}