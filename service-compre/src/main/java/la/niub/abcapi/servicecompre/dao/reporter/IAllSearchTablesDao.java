package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.AllSearchTablesModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAllSearchTablesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AllSearchTablesModel record);

    int insertSelective(AllSearchTablesModel record);

    AllSearchTablesModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AllSearchTablesModel record);

    int updateByPrimaryKey(AllSearchTablesModel record);

    List<AllSearchTablesModel> selectByTabIds(@Param("tabIds") List<String> tabIds);

    List<AllSearchTablesModel> selectByCateCodes(List<String> cateCodes);

    List<AllSearchTablesModel> selectOtherTables(List<String> cateCodes);
}