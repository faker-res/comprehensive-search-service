package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ILedAchievementRiskDao {

    List<Map<String, Object>> getPostInfoByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;

    Integer getListedComCountByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;

    List<Long> getComUniCodeListByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;

    BigDecimal getComTotalIncomeByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;

    Map<String, Object> getIncomeAndIncomeYoyByComUniCode(@Param("peo_uni_code") Long peo_uni_code, @Param("com_uni_code") Long com_uni_code) throws Exception;

    Integer getHurunRankByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;

    Integer getTotalLawsuitNumByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;

    BigDecimal getGrowthByComUniCode(@Param("com_uni_code") Long com_uni_code) throws Exception;

}
