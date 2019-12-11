package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundHoldStkStatModel;

public interface IFundHoldStkStatDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundHoldStkStatModel record);

    int insertSelective(FundHoldStkStatModel record);

    FundHoldStkStatModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundHoldStkStatModel record);

    int updateByPrimaryKey(FundHoldStkStatModel record);

    FundHoldStkStatModel getNewestRecords(Long secUniCode);
}