package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.AnalystTeamModel;

import java.util.List;

public interface IAnalystTeamDao {
    int deleteByPrimaryKey(Long id);

    int insert(AnalystTeamModel record);

    int insertSelective(AnalystTeamModel record);

    AnalystTeamModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AnalystTeamModel record);

    int updateByPrimaryKey(AnalystTeamModel record);

    List<AnalystTeamModel> selectOrderByReportCount(Integer limit);
}