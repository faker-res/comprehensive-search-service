package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComForecastModel;

import java.util.List;
import java.util.Map;

public interface IComForecastDao {
    public List<ComForecastModel> find(Map paramMap);
    public int getCount(Map paramMap);
}
