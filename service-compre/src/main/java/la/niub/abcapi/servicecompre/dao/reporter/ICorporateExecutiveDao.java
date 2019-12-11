package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.CorporateExecutiveBasicInfoModel;
import la.niub.abcapi.servicecompre.model.CorporateExecutiveSameComModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICorporateExecutiveDao {

    List<CorporateExecutiveBasicInfoModel> selectCorporateExecutiveBasicInfoByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code);

    List<CorporateExecutiveSameComModel> selectCorporateExecutiveSameComByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code);

    String getImageByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;

    List<CorporateExecutiveSameComModel> getSameComWhenComIsInUk(@Param("peo_uni_code") Long peo_uni_code);

    List<CorporateExecutiveSameComModel> getSameComWhenComIsInUS(@Param("peo_uni_code") Long peo_uni_code);
}
