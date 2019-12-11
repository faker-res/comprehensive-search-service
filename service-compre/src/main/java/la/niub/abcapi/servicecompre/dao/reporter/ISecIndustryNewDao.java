package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.SecIndustryNewModel;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface ISecIndustryNewDao {
    int deleteByPrimaryKey(Long id);

    int insert(SecIndustryNewModel record);

    int insertSelective(SecIndustryNewModel record);

    SecIndustryNewModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecIndustryNewModel record);

    int updateByPrimaryKey(SecIndustryNewModel record);

    SecIndustryNewModel selectByCodeAndStandard(@Param("sec_uni_code") Long sec_uni_code, @Param("indu_standard") Integer indu_standard);

    SecIndustryNewModel buildInduBySecUniCode(Long secUniCode);

    List<SecIndustryNewModel> buildInduListBySecUniCode(Long secUniCode);

    List<SecIndustryNewModel> buildInduCompanyByTInduCode(String thirdInduCode);

    List<SecIndustryNewModel> buildInduCompanyByTInduCodes(@Param("thirdInduCodes") List<String> thirdInduCodes);

    List<SecIndustryNewModel> selectByFirstInduCode(String firstInduCode);

    List<SecIndustryNewModel> selectShenwanByFirstInduCode(String firstInduCode);

    String getThirdInduNameBySecUniCode(BigInteger secUniCode) throws Exception;

    List<String> getAbcCodeByInduNameAndAbcCode(@Param("abc_code") String abc_code, @Param("indu_name") String indu_name) throws Exception;

    String getInduNameBySecUniCode(@Param("secUniCode") Long secUniCode);

    List<SecIndustryNewModel> find(Map map);

    Long getCount(Map map);

    List<Map> getCodeIndu(@Param("abc_code") String abc_code);
}