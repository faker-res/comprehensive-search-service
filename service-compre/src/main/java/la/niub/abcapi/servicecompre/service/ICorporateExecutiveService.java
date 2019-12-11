package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.CorporateExecutiveBasicInfoModel;
import la.niub.abcapi.servicecompre.model.CorporateExecutiveSameComModel;

import java.util.List;
import java.util.Map;

public interface ICorporateExecutiveService {

    CorporateExecutiveBasicInfoModel selectCorporateExecutiveBasicInfoByPeoUniCode(Long peo_uni_code) throws Exception;

    List<CorporateExecutiveSameComModel> selectCorporateExecutiveSameComList(Long peo_uni_code) throws Exception;

    List<Map<String, Object>> getPeoComList(Long peo_uni_code) throws Exception;

    List<Map<String, Object>> getPeoNews(Long peo_uni_code, Integer limit) throws Exception;

    List<Map<String, Object>> getEmolumentInfo(Long com_uni_code) throws Exception;

    List<Map<String, Object>> getStockHoldInfo(Long com_uni_code) throws Exception;

    Map<String, Object> getAchieveAndRiskData(Long peo_uni_code, Long com_uni_code) throws Exception;

//    Object getHeatIndex(Long peo_uni_code, Long com_uni_code, Integer days) throws Exception;

    Map<String, Object> getInvestInfo(Long peo_uni_code, Integer page_num, Integer limit, String order_field, String order_type) throws Exception;

    Map<String,Object> getBusinessRole(Long peo_uni_code, Integer page_num, Integer limit, String order_field, String order_type) throws Exception;

    Object getHeatIndex(Long peo_uni_code, Long com_uni_code, Integer days) throws Exception;

    List<Map<String, Object>> getPeoWeiboList(Long peo_uni_code) throws Exception;
}
