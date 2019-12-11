package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.SearchIndexModel;

public interface ISearchIndexDao {
    int deleteByPrimaryKey(Long keyword_id);

    int insert(SearchIndexModel record);

    int insertSelective(SearchIndexModel record);

    SearchIndexModel selectByPrimaryKey(Long keyword_id);

    int updateByPrimaryKeySelective(SearchIndexModel record);

    int updateByPrimaryKey(SearchIndexModel record);

    SearchIndexModel selectByKeyword(String keyword);
}