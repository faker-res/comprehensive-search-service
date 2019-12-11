package la.niub.abcapi.servicecompre.dao.reporter;

import java.util.List;
import java.util.Map;

public interface IPlateChainStockDao {

    List<Map<String, String>> getPlateChainStockByInduChainCode(Long induChainCode);
}
