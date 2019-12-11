package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowModel;
import la.niub.abcapi.servicecompre.model.dao.IndexCaptitalFlowGroupByDaoModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IIndexCaptitalFlowDao {
    int deleteByPrimaryKey(Long id);

    int insert(IndexCaptitalFlowModel record);

    int insertSelective(IndexCaptitalFlowModel record);

    IndexCaptitalFlowModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexCaptitalFlowModel record);

    int updateByPrimaryKey(IndexCaptitalFlowModel record);

    IndexCaptitalFlowModel getRecordsByDate(@Param("indexUniCode") Long indexUniCode,
                                            @Param("tradeDate") Date tradeDate);

   List<IndexCaptitalFlowGroupByDaoModel> getCountByIndexUniCodeAndDateBeginWithEnd(@Param("indexUniCodes") List<Long> indexUniCodes,
                                                                                    @Param("beginDate") Date beginDate,
                                                                                    @Param("endDate") Date endDate);


    List<IndexCaptitalFlowModel> getListByIndexUniCodeAndLimit(@Param("indexUniCode") Long indexUniCode, @Param("limit") int limit );

    List<IndexCaptitalFlowModel> selectByIndexUniCodesAfterTradeDate(@Param("indexUniCodes") List<Long> indexUniCodes, @Param("tradeDate") Date tradeDate);

    List<IndexCaptitalFlowModel> selectByIndexUniCodeAndTradeDate(@Param("indexUniCodeAndDate") Map<Long,Date> indexUniCodeAndDate);
}