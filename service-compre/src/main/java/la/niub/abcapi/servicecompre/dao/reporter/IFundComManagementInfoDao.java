package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundComManagementInfoModel;

public interface IFundComManagementInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundComManagementInfoModel record);

    int insertSelective(FundComManagementInfoModel record);

    FundComManagementInfoModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundComManagementInfoModel record);

    int updateByPrimaryKeyWithBLOBs(FundComManagementInfoModel record);

    int updateByPrimaryKey(FundComManagementInfoModel record);

    /**
     * 董事长
     * @param orgUniCode
     * @return
     */
    FundComManagementInfoModel findBigBossByOrgUniCode(Long orgUniCode);

    /**
     * 总经理
     * @param orgUniCode
     * @return
     */
    FundComManagementInfoModel findBossByOrgUniCode(Long orgUniCode);
}