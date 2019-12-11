package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundManagerIndexModel;
import la.niub.abcapi.servicecompre.model.FundManagerInfoModel;
import la.niub.abcapi.servicecompre.model.FundManagerPositionDistributionModel;
import la.niub.abcapi.servicecompre.model.FundManagerStarModel;
import la.niub.abcapi.servicecompre.model.FundManagerStockModel;
import la.niub.abcapi.servicecompre.model.SecBasicInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFundManagerDao {

    FundManagerInfoModel selectFundManagerInfoByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code);

    List<FundManagerStarModel> selectFundManagerStarList();

    List<FundManagerIndexModel> selectFundManagerIndexByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code);

    List<FundManagerPositionDistributionModel> selectFundManagerPositionDistributionByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code);

    List<FundManagerStockModel> selectFundManagerStockBySecUniCode(@Param("sec_uni_code") Long sec_uni_code);

    SecBasicInfoModel selectStockCodeBySecUniCode(@Param("sec_uni_code") Long sec_uni_code);
}
