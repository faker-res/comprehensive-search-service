package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundSubRedemStatusModel;

public interface IFundSubRedemStatusDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundSubRedemStatusModel record);

    int insertSelective(FundSubRedemStatusModel record);

    FundSubRedemStatusModel selectByPrimaryKey(Long id);

    FundSubRedemStatusModel selectBySecUniCode(Long secUniCode);

    int updateByPrimaryKeySelective(FundSubRedemStatusModel record);

    int updateByPrimaryKey(FundSubRedemStatusModel record);
}