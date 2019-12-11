package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.SecBasicInfoModel;
import la.niub.abcapi.servicecompre.model.bo.theme.ComCountInIpoSectorBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ISecBasicInfoDao {
    int deleteByPrimaryKey(Long sec_uni_code);

    int insert(SecBasicInfoModel record);

    int insertSelective(SecBasicInfoModel record);

    SecBasicInfoModel selectByPrimaryKey(Long sec_uni_code);

    int updateByPrimaryKeySelective(SecBasicInfoModel record);

    int updateByPrimaryKey(SecBasicInfoModel record);

    SecBasicInfoModel selectBySecUniCode(Long sec_uni_code);

    SecBasicInfoModel selectBySecCode(String sec_code);

    SecBasicInfoModel selectBySecCode2(String sec_code);

    SecBasicInfoModel selectByAbcCode(String abc_code);

    List<SecBasicInfoModel> getListBySecUniCodes(@Param("secUniCodes") List<Long> secUniCodes);

    List<SecBasicInfoModel> getListBySecUniCodesNullEndDate(@Param("secUniCodes") List<Long> secUniCodes);

    List<SecBasicInfoModel> getListByAbcCodes(@Param("abcCodes") List<String> abcCodes);

    List<Long> getComUniCodeBySecCodeList(@Param("sec_code_list") List<String> sec_code_list);

    List<ComCountInIpoSectorBO> selectComCountInIpoSectorBySecCodeList(@Param("sec_code_list") List<String> sec_code_list);

    List<Long> selectSecUniCodeBySecTypeAndSecCodeList(@Param("sec_code_list") List<String> sec_code_list);

    List<SecBasicInfoModel> selectBySecCodeList(@Param("sec_code_list") List<String> sec_code_list);

    List<Map<String,Object>> getListBySecCodeList(@Param("secCodeList") List<String> secCodeList) throws Exception;

    Map<String, Object> getAbcCodeAndSecUniCodeByComUniCode(@Param("com_uni_code") Long com_uni_code) throws Exception;

    Long getSecUniCodeByComUniCode(@Param("com_uni_code") Long com_uni_code) throws Exception;

    Long getSecTypeByComUniCode(@Param("com_uni_code") Long com_uni_code) throws Exception;

    List<String> getAbcCodeByComUniCode(@Param("com_uni_code") Long com_uni_code) throws Exception;

    List<Map<String, Object>> getSecNameAndAbcCodeAndComUniCodeAndSecUniCode() throws Exception;

    Map<String, Object> getSecNameAndAbcCodeAndSecUniCodeByComUniCodeByAbcCode(@Param("abc_code") String abc_code) throws Exception;

    List<String> getAbcCodeByAbcCodeListAndSecType(@Param("abcCodeList") List<String> abcCodeList, @Param("sec_type") Long sec_type) throws Exception;

    Long getComUniCodeBySecUniCode(@Param("sec_uni_code") Long sec_uni_code) throws Exception;

    List<Long> getSameComSecUniCodeListBySecUniCode(@Param("com_uni_code") Long com_uni_code, @Param("sec_uni_code") Long sec_uni_code) throws Exception;

    List<Map<String, Object>> getRankingWhenTableIsFundShareChange(@Param("secUniCodeList") List<Long> secUniCodeList,
                                                                   @Param("prior") String prior,
                                                                   @Param("offset") Integer offset,
                                                                   @Param("limit") Integer limit) throws Exception;

    List<Map<String, Object>> getRankingWhenTableIsFundNav(@Param("secUniCodeList") List<Long> secUniCodeList,
                                                           @Param("prior") String prior,
                                                           @Param("offset") Integer offset,
                                                           @Param("limit") Integer limit) throws Exception;

    List<Map<String, Object>> getRankingWhenTableIsFundNavReturn(@Param("secUniCodeList") List<Long> secUniCodeList,
                                                                 @Param("prior") String prior,
                                                                 @Param("offset") Integer offset,
                                                                 @Param("limit") Integer limit) throws Exception;

    List<Long> getAllFundCodeExceptSecUniCode(@Param("secUniCode") Long secUniCode) throws Exception;

    Map<String, Object> getSecNameAndSecCodeBySecUniCode(@Param("secUniCode") Long secUniCode) throws Exception;

    List<Long> getSecUniCodeByComUniCodeAndSecTypeAndSecSmallType(Long com_uni_code) throws Exception;

    List<Long> getValidSecUniCodeBySecUniCodeList(@Param("secUniCodeList") List<Long> secUniCodeList) throws Exception;

    public String getStockComCode(@Param("abc_code") String abc_code) ;

    SecBasicInfoModel selectBySecUniCodeHS(Long sec_uni_code);

    List<Map> getNotionDetail(Map map);

    long getNotionDetailCount(Map map);
}