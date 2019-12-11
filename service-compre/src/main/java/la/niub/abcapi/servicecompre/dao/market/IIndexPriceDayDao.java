package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.IndexPriceDayModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IIndexPriceDayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(IndexPriceDayModel record);

    int insertSelective(IndexPriceDayModel record);

    IndexPriceDayModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndexPriceDayModel record);

    int updateByPrimaryKey(IndexPriceDayModel record);

    IndexPriceDayModel getNewestRecord(Long indexUniCode);

    List<IndexPriceDayModel> getRecordsByDates(@Param("indexUniCode") Long indexUniCode,
                                               @Param("startTime") Date startTime,
                                               @Param("endTime") Date endTime);

    List<IndexPriceDayModel> selectByIndexUniCodesAndTradeDateBeginWithEnd(@Param("indexUniCodes") List<Long> indexUniCodes,
                                               @Param("beginTime") Date beginTime,
                                               @Param("endTime") Date endTime);

    List<IndexPriceDayModel> selectByIndexUniCodesAfterTradeDate(@Param("indexUniCodes") List<Long> indexUniCodes,
                                               @Param("tradeDate") Date tradeDate);

    List<IndexPriceDayModel> selectByIndexUniCodeListAndStartDate(@Param("index_uni_code_list") List<Long> index_uni_code_list, @Param("startDate") Date startDate);

    List<IndexPriceDayModel> selectByIndexUniCodeAndTradeDate(@Param("indexUniCodeAndDate") Map<Long,Date> indexUniCodeAndDate);
}