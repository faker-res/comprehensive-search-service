package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundBenchmarkModel;

public interface IFundBenchmarkDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundBenchmarkModel record);

    int insertSelective(FundBenchmarkModel record);

    FundBenchmarkModel selectByPrimaryKey(Long id);

    FundBenchmarkModel selectBySecUniCode(Long secUniCode);

    int updateByPrimaryKeySelective(FundBenchmarkModel record);

    int updateByPrimaryKey(FundBenchmarkModel record);
}