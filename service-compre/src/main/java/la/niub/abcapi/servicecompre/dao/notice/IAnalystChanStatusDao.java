package la.niub.abcapi.servicecompre.dao.notice;


import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.model.AnalystChanStatusModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IAnalystChanStatusDao {
    
    int deleteByPrimaryKey(Integer id);

    int insert(AnalystChanStatusModel record);

    int insertSelective(AnalystChanStatusModel record);

    AnalystChanStatusModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnalystChanStatusModel record);

    int updateByPrimaryKey(AnalystChanStatusModel record);

    List<AnalystChanStatusModel> getListByPeoUniCodes(@Param("peoUniCodes") List<String> peoUniCodes);

    AnalystChanStatusModel selectByPeoUniCode(String peo_uni_code);

    List<AnalystChanStatusModel> getResumesByPeoUniCodes(@Param("peoUniCodes") List<String> peoUniCodes);

    List<AnalystChanStatusModel> getAnalystByOrg(@Param("orgId") int orgId);

    List<String> selectPeoUniCodeByOrgUniCode(@Param("org_uni_code") Long org_uni_code);

//    List<String> selectNameListByOrgUniCode(@Param("org_uni_code") Long org_uni_code);

    List<String> getAllAnalystByOrg(@Param("orgId")int orgId) throws Exception;

    List<AnalystChanStatusModel> getResumesByPeoUniCode(@Param("peoUniCode")String peoUniCode) throws Exception;

    List<Map<String, Object>> getOrgSnameList() throws Exception;

    List<String> getAllAnalystByOrgSname(@Param("org_sname") String org_sname) throws Exception;

    List<String> getAllAnalystPeoUniCode() throws ServiceException;
}
