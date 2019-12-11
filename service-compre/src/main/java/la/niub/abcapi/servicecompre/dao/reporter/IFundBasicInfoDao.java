package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundBasicInfoModel;
import la.niub.abcapi.servicecompre.model.FundBasicInfoWithBLOBs;
import la.niub.abcapi.servicecompre.model.response.FundCompanyAchievementsInfoItemResponse;

import java.util.List;

public interface IFundBasicInfoDao {
    int deleteByPrimaryKey(Long sec_uni_code);

    int insert(FundBasicInfoWithBLOBs record);

    int insertSelective(FundBasicInfoWithBLOBs record);

    FundBasicInfoWithBLOBs selectByPrimaryKeyWithBLOBs(Long sec_uni_code);

    int updateByPrimaryKeySelective(FundBasicInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(FundBasicInfoWithBLOBs record);

    int updateByPrimaryKey(FundBasicInfoModel record);

    FundBasicInfoModel selectByPrimaryKey(Long sec_uni_code);

    List<FundBasicInfoModel> selectByManaUniCode(Long mana_uni_code);

    List<FundCompanyAchievementsInfoItemResponse> getAllFunCompany() throws Exception;
}