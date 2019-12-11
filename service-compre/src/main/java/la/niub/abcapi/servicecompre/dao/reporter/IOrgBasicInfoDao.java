package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.OrgBasicInfoModel;
import la.niub.abcapi.servicecompre.model.OrgBasicInfoWithBLOBs;

public interface IOrgBasicInfoDao {
    int deleteByPrimaryKey(Long org_uni_code);

    int insert(OrgBasicInfoWithBLOBs record);

    int insertSelective(OrgBasicInfoWithBLOBs record);

    OrgBasicInfoWithBLOBs selectByPrimaryKeyWithBLOBs(Long org_uni_code);

    int updateByPrimaryKeySelective(OrgBasicInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(OrgBasicInfoWithBLOBs record);

    int updateByPrimaryKey(OrgBasicInfoModel record);

    OrgBasicInfoModel selectByOrgName(String orgName);

    OrgBasicInfoWithBLOBs selectByOrgNameWithBLOBs(String orgName);

    OrgBasicInfoModel selectByPrimaryKey(Long org_uni_code);
    
    OrgBasicInfoModel selectByComUniCode(Long comUniCode);

    OrgBasicInfoWithBLOBs selectByComUniCodeWithBLOBs(Long comUniCode);
}