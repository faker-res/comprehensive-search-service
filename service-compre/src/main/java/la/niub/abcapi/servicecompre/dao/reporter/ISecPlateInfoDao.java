package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.SecPlateInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISecPlateInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(SecPlateInfoModel record);

    int insertSelective(SecPlateInfoModel record);

    SecPlateInfoModel selectByPrimaryKey(Long id);

    SecPlateInfoModel selectBySecUniCode(Long secUniCode);

    int updateByPrimaryKeySelective(SecPlateInfoModel record);

    int updateByPrimaryKey(SecPlateInfoModel record);

    /**
     * 获取基金类型
     * @param secUniCode
     * @return
     */
    String getFundType(Long secUniCode);

    /**
     * 获取基金风险等级
     * @param secUniCode
     * @return
     */
    String getFundRisk(Long secUniCode);

    List<Map<String,String>> getPlateNameAndCodeBySecUniCodeList(@Param("secUniCodeList") List<Long> secUniCodeList) throws Exception;

    Map<String, String> getFundTypeCodeBySecUniCode(Long sec_uni_code) throws Exception;

    List<Long> getSecUnICodeListByPlateCode(String plateCode) throws Exception;

    List<Long> getSecUniCodeListBySecUniCodeListAndPlateCode(@Param("secUniCodeList") List<Long> secUniCodeList, @Param("fundType") String fundType) throws Exception;

    List<Map<String, String>> getAllFundType() throws Exception;

    String getPlateNameBySecUniCode(Long sec_uni_code) throws Exception;
}