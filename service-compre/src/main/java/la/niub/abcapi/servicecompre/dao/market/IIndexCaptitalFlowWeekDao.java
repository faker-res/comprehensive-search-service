package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowWeekModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IIndexCaptitalFlowWeekDao {
    int deleteByPrimaryKey(Long id);

    int insert(IndexCaptitalFlowWeekModel record);

    int insertSelective(IndexCaptitalFlowWeekModel record);

    IndexCaptitalFlowWeekModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexCaptitalFlowWeekModel record);

    int updateByPrimaryKey(IndexCaptitalFlowWeekModel record);

    Date getLastEndDate();

    Date getLastBeginDate();

    Date getClosestBeginDate(Date date);

    List<IndexCaptitalFlowWeekModel> selectHeatByIndexCodesAfterBeginDate(@Param("indexCodes") List<String> indexCodes, @Param("beginDate") Date beginDate, @Param("limit") Integer limit);
}