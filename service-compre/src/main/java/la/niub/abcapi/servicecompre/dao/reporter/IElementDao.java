package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ElementModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IElementDao {
    int deleteByPrimaryKey(Integer ID);

    int insert(ElementModel record);

    int insertSelective(ElementModel record);

    ElementModel selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(ElementModel record);

    int updateByPrimaryKey(ElementModel record);

    List<ElementModel> getRecordsBySheetNameAndFileds(@Param("sheetName") String sheetName, @Param("fields") List<String> fields);
}