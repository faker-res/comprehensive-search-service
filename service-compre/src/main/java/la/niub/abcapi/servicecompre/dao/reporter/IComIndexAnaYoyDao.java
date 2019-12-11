package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComIndexAnaYoyModel;

import java.util.List;
import java.util.Map;

public interface IComIndexAnaYoyDao {
    public int insert(ComIndexAnaYoyModel com_index_ana_yoy);
    public int delete(ComIndexAnaYoyModel com_index_ana_yoy);
    public int update(ComIndexAnaYoyModel com_index_ana_yoy);
    public List<ComIndexAnaYoyModel> find(Map paramMap);
    public int getCount(Map paramMap);
}
