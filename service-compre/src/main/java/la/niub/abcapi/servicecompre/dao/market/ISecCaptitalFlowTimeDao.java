package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.SecCaptitalFlowTimeModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISecCaptitalFlowTimeDao {
    int deleteByPrimaryKey(Long id);

    int insert(SecCaptitalFlowTimeModel record);

    int insertSelective(SecCaptitalFlowTimeModel record);

    SecCaptitalFlowTimeModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecCaptitalFlowTimeModel record);

    int updateByPrimaryKey(SecCaptitalFlowTimeModel record);

    Date getLastTradeDate();

    List<SecCaptitalFlowTimeModel> selectHeatBySecUniCodesAfterTradeDate(@Param("secUniCodes") List<Long> secUniCodes, @Param("tradeDate") Date tradeDate, @Param("limit") Integer limit);
}