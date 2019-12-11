package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.SearchIndexDataModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISearchIndexDataDao {
    int deleteByPrimaryKey(Long id);

    int insert(SearchIndexDataModel record);

    int insertSelective(SearchIndexDataModel record);

    SearchIndexDataModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SearchIndexDataModel record);

    int updateByPrimaryKey(SearchIndexDataModel record);

    List<SearchIndexDataModel> selectByKeywordId(@Param("keywordId") Long keywordId, @Param("limit") Integer limit);

    List<SearchIndexDataModel> selectByKeywordIdAndDate(@Param("keywordId") Long keywordId, @Param("beginDate") Date beginDate);

    List<SearchIndexDataModel> selectByKeywordIdAndStartDate(@Param("keywordId") Long keywordId, @Param("startDate") Date startDate) throws Exception;
}