package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ThemeMappingModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IThemeMappingDao {
    List<ThemeMappingModel> selectByThemeNameAndLimit(@Param("themeName") String themeName/*, @Param("limit") int limit*/);
}
