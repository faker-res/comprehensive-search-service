package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundKeeperHoldStkModel;

public interface IFundKeeperHoldStkDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundKeeperHoldStkModel record);

    int insertSelective(FundKeeperHoldStkModel record);

    FundKeeperHoldStkModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundKeeperHoldStkModel record);

    int updateByPrimaryKey(FundKeeperHoldStkModel record);
}