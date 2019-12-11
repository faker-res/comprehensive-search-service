package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComRealControllerModel;

import java.util.List;
import java.util.Map;

public interface IComRealControllerDao {
    public int insert(ComRealControllerModel com_real_controller);
    public int delete(ComRealControllerModel com_real_controller);
    public int update(ComRealControllerModel com_real_controller);
    public List<ComRealControllerModel> find(Map paramMap);
    public int getCount(Map paramMap);
}
