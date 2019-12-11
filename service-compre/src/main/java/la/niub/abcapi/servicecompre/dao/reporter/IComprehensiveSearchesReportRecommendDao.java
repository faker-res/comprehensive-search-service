package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComprehensiveSearchesReportRecommendModel;

import java.util.List;

public interface IComprehensiveSearchesReportRecommendDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComprehensiveSearchesReportRecommendModel record);

    int insertSelective(ComprehensiveSearchesReportRecommendModel record);

    ComprehensiveSearchesReportRecommendModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComprehensiveSearchesReportRecommendModel record);

    int updateByPrimaryKey(ComprehensiveSearchesReportRecommendModel record);

    List<ComprehensiveSearchesReportRecommendModel> buildRecords();
}