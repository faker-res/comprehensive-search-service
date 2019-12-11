package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.IndexValuationAnaModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IIndexValuationAnaDao {
    int deleteByPrimaryKey(Long id);

    int insert(IndexValuationAnaModel record);

    int insertSelective(IndexValuationAnaModel record);

    IndexValuationAnaModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexValuationAnaModel record);

    int updateByPrimaryKey(IndexValuationAnaModel record);

    IndexValuationAnaModel getRecordsByDate(@Param("indexUniCode") Long indexUniCode,
                                            @Param("accountDate") Date accountDate);

    Date getLastAccountDate();

    List<IndexValuationAnaModel> selectHeatPelyrByIndexUniCodesAfterAccountDate(@Param("indexUniCodes") List<Long> indexUniCodes, @Param("accountDate") Date accountDate, @Param("limit") Integer limit);

    List<IndexValuationAnaModel> selectHeatPettmByIndexUniCodesAfterAccountDate(@Param("indexUniCodes") List<Long> indexUniCodes, @Param("accountDate") Date accountDate, @Param("limit") Integer limit);

    List<IndexValuationAnaModel> selectHeatPbmrqByIndexUniCodesAfterAccountDate(@Param("indexUniCodes") List<Long> indexUniCodes, @Param("accountDate") Date accountDate, @Param("limit") Integer limit);

    List<IndexValuationAnaModel> selectByIndexUniCodesAfterAccountDate(@Param("indexUniCodes") List<Long> indexUniCodes, @Param("accountDate") Date accountDate);

    List<IndexValuationAnaModel> selectByIndexUniCodeAndTradeDate(@Param("indexUniCodeAndDate") Map<Long,Date> indexUniCodeAndDate);
}