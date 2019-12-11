package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.SecCaptitalFlowYearModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISecCaptitalFlowYearDao {
    int deleteByPrimaryKey(Long id);

    int insert(SecCaptitalFlowYearModel record);

    int insertSelective(SecCaptitalFlowYearModel record);

    SecCaptitalFlowYearModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecCaptitalFlowYearModel record);

    int updateByPrimaryKey(SecCaptitalFlowYearModel record);

    Date getLastEndDate();

    Date getLastBeginDate();

    List<SecCaptitalFlowYearModel> selectHeatBySecUniCodesAfterBeginDate(@Param("secUniCodes") List<Long> secUniCodes, @Param("beginDate") Date beginDate, @Param("limit") Integer limit);
}