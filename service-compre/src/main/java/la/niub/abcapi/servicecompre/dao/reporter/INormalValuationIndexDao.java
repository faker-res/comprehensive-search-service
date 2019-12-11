package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.NormalValuationIndexModel;
import la.niub.abcapi.servicecompre.model.bo.theme.PepbDistBO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface INormalValuationIndexDao {
    int deleteByPrimaryKey(Long id);

    int insert(NormalValuationIndexModel record);

    int insertSelective(NormalValuationIndexModel record);

    NormalValuationIndexModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NormalValuationIndexModel record);

    int updateByPrimaryKey(NormalValuationIndexModel record);

    Date getMaxData(Long secUniCode);

    NormalValuationIndexModel getRecord(@Param("secUniCode") Long secUniCode, @Param("accountDate") Date accountDate);

    List<NormalValuationIndexModel> buildRecordsByAccountDateWithSecUniCode(@Param("accountDate") Date accountDate, @Param("secUniCode") List<Long> secUniCode, @Param("limit") Integer limit);

    NormalValuationIndexModel getValuationBySecUniCode(@Param("sec_uni_code") Long sec_uni_code);

    List<Long> getSecUniCodeByAccountDateWithSecUniCode(@Param("accountDate") Date accountDate, @Param("secUniCode") List<Long> secUniCode, @Param("limit") Integer limit);

    List<PepbDistBO> selectPepbBySecUniCodeList(@Param("sec_uni_code_list") List<Long> sec_uni_code_list);

    Date selectAccountDateBySecUniCode(@Param("sec_uni_code") Long sec_uni_code);

    PepbDistBO selectPepbDistBySecUniCodeAndAccountDate(@Param("sec_uni_code") Long sec_uni_code, @Param("account_date") Date account_date);

    List<NormalValuationIndexModel> selectBySecUniCodesAfterAccountDate(@Param("secUniCodes") List<Long> secUniCodes, @Param("accountDate") Date accountDate);

    BigDecimal selectMarketValueBySecUniCodeAndAccountDate(@Param("sec_uni_code") Long sec_uni_code, @Param("account_date") Date account_date);

    /**
     * 获取市值前n的SecUniCode
     * @param secUniCodeList
     * @param limit
     * @return
     */
    List<BigInteger> getSecUniCodeForTopNBySecUniCodeList(@Param("secUniCodeList") List<BigInteger> secUniCodeList, @Param("limit") int limit);

    Date getLastAccountDateBefore(Date date);

    List<NormalValuationIndexModel> selectBySecUniCodeAndTradeDate(@Param("secUniCodeAndDate") Map<Long,Date> secUniCodeAndDate);

    List<NormalValuationIndexModel> find(Map map);
}