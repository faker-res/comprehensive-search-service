package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundInvestInduStatModel;

public interface IFundInvestInduStatDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundInvestInduStatModel record);

    int insertSelective(FundInvestInduStatModel record);

    FundInvestInduStatModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundInvestInduStatModel record);

    int updateByPrimaryKey(FundInvestInduStatModel record);
}