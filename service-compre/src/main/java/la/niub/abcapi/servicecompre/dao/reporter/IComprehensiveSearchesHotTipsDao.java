package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComprehensiveSearchesHotTipsModel;

import java.util.List;

public interface IComprehensiveSearchesHotTipsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComprehensiveSearchesHotTipsModel record);

    int insertSelective(ComprehensiveSearchesHotTipsModel record);

    ComprehensiveSearchesHotTipsModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComprehensiveSearchesHotTipsModel record);

    int updateByPrimaryKey(ComprehensiveSearchesHotTipsModel record);

    List<ComprehensiveSearchesHotTipsModel> buildRecords();
}