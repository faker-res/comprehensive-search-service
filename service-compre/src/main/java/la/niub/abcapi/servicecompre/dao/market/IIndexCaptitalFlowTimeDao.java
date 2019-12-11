package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowTimeModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IIndexCaptitalFlowTimeDao {
    int deleteByPrimaryKey(Long id);

    int insert(IndexCaptitalFlowTimeModel record);

    int insertSelective(IndexCaptitalFlowTimeModel record);

    IndexCaptitalFlowTimeModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexCaptitalFlowTimeModel record);

    int updateByPrimaryKey(IndexCaptitalFlowTimeModel record);

    Date getLastTradeDate();

    List<IndexCaptitalFlowTimeModel> selectHeatByIndexCodesAndTradeDate(@Param("indexCodes") List<String> indexCodes, @Param("tradeDate") Date tradeDate, @Param("limit") Integer limit);

    List<IndexCaptitalFlowTimeModel> selectRealtimeByIndexUniCode(@Param("indexUniCode") Long indexUniCode);

    List<IndexCaptitalFlowTimeModel> selectHeatByIndexCodesAfterTradeDate(@Param("indexCodes") List<String> indexCodes, @Param("tradeDate") Date tradeDate, @Param("limit") Integer limit);
}