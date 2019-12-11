package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundKeeperFundModel;

public interface IFundKeeperFundDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FundKeeperFundModel record);

    int insertSelective(FundKeeperFundModel record);

    FundKeeperFundModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundKeeperFundModel record);

    int updateByPrimaryKey(FundKeeperFundModel record);
}