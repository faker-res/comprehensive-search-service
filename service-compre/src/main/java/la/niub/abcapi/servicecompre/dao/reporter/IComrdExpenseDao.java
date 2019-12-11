package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComrdExpenseModel;

import java.util.List;
import java.util.Map;

public interface IComrdExpenseDao {
    public List<ComrdExpenseModel> find(Map paramMap);
    public int getCount(Map paramMap);
}
