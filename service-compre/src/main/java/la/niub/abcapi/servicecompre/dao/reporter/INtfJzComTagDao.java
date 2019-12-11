package la.niub.abcapi.servicecompre.dao.reporter;

import java.util.List;

public interface INtfJzComTagDao {
    List<Integer> getComIdListByTagName(String tagName) throws Exception;
}
