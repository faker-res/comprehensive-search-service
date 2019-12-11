package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComprehensiveSearchesTopModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IComprehensiveSearchesTopDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComprehensiveSearchesTopModel record);

    int insertSelective(ComprehensiveSearchesTopModel record);

    ComprehensiveSearchesTopModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComprehensiveSearchesTopModel record);

    int updateByPrimaryKey(ComprehensiveSearchesTopModel record);

    List<ComprehensiveSearchesTopModel> buildRecordByLimit(@Param("limit") Integer limit, @Param("offset") Integer offset);
}