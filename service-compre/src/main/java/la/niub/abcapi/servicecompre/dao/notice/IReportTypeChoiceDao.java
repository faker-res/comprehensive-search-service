package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.RepostTypeModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IReportTypeChoiceDao {

    List<RepostTypeModel> selectReportType();

    List<String> selectSecondLevelReportTypeByParentId(@Param("parent_id") String parent_id);

    String selectTypeNameByTypeId(@Param("type_id") String type_id);
}
