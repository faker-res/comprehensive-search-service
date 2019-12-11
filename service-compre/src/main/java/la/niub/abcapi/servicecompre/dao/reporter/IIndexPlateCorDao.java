package la.niub.abcapi.servicecompre.dao.reporter;

import java.math.BigInteger;

public interface IIndexPlateCorDao {
    String getPlateCodeByIndexCode(BigInteger indexCode) throws Exception;
}
