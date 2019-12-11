package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.NewsHeatFundModel;

public interface INewsHeatFundDao {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsHeatFundModel record);

    int insertSelective(NewsHeatFundModel record);

    NewsHeatFundModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsHeatFundModel record);

    int updateByPrimaryKey(NewsHeatFundModel record);
}