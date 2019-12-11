package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.SecCaptitalFlowMonthModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISecCaptitalFlowMonthDao {
    int deleteByPrimaryKey(Long id);

    int insert(SecCaptitalFlowMonthModel record);

    int insertSelective(SecCaptitalFlowMonthModel record);

    SecCaptitalFlowMonthModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecCaptitalFlowMonthModel record);

    int updateByPrimaryKey(SecCaptitalFlowMonthModel record);

    Date getLastEndDate();

    Date getLastBeginDate();

    List<SecCaptitalFlowMonthModel> selectHeatBySecUniCodesAfterBeginDate(@Param("secUniCodes") List<Long> secUniCodes, @Param("beginDate") Date beginDate, @Param("limit") Integer limit);
}