package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComManagerModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IComManagerDao {
    public List<ComManagerModel> find(Map paramMap);
    public int getCount(Map paramMap);
    List<Map<String, Object>> findbysql(@Param(value="sql")String sql);
    List<Map<String, Object>> selectManager(Map map);
    long getManagerCount(Map map);
}
