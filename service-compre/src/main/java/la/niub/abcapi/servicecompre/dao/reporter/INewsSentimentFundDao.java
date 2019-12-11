package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.NewsSentimentFundModel;

public interface INewsSentimentFundDao {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsSentimentFundModel record);

    int insertSelective(NewsSentimentFundModel record);

    NewsSentimentFundModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsSentimentFundModel record);

    int updateByPrimaryKey(NewsSentimentFundModel record);
}