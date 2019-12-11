package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.SecCaptitalFlowWeekModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISecCaptitalFlowWeekDao {
    int deleteByPrimaryKey(Long id);

    int insert(SecCaptitalFlowWeekModel record);

    int insertSelective(SecCaptitalFlowWeekModel record);

    SecCaptitalFlowWeekModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecCaptitalFlowWeekModel record);

    int updateByPrimaryKey(SecCaptitalFlowWeekModel record);

    Date getLastEndDate();

    Date getLastBeginDate();

    List<SecCaptitalFlowWeekModel> selectHeatBySecUniCodesAfterBeginDate(@Param("secUniCodes") List<Long> secUniCodes, @Param("beginDate") Date beginDate, @Param("limit") Integer limit);
}