package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowYearModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IIndexCaptitalFlowYearDao {
    int deleteByPrimaryKey(Long id);

    int insert(IndexCaptitalFlowYearModel record);

    int insertSelective(IndexCaptitalFlowYearModel record);

    IndexCaptitalFlowYearModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexCaptitalFlowYearModel record);

    int updateByPrimaryKey(IndexCaptitalFlowYearModel record);

    Date getLastEndDate();

    Date getLastBeginDate();

    Date getClosestBeginDate(Date date);

    List<IndexCaptitalFlowYearModel> selectHeatByIndexCodesAfterBeginDate(@Param("indexCodes") List<String> indexCodes, @Param("beginDate") Date beginDate, @Param("limit") Integer limit);
}