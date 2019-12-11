package la.niub.abcapi.servicecompre.dao.reporter;

import java.util.List;
import java.util.Map;

public interface IPlateInduChainDao {

    List<Map<String, Object>> getInduChainCodeAndNameByPlateCode(String plateCode) throws Exception;
}
