package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.MarketReportModel;

public interface IMarketReportDao {
    int deleteByPrimaryKey(Long added_id);

    int insert(MarketReportModel record);

    int insertSelective(MarketReportModel record);

    MarketReportModel selectByPrimaryKey(Long added_id);

    int updateByPrimaryKeySelective(MarketReportModel record);

    int updateByPrimaryKey(MarketReportModel record);

    MarketReportModel selectById(Long id);
}