package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.IndexCaptitalFlowMonthModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IIndexCaptitalFlowMonthDao {
    int deleteByPrimaryKey(Long id);

    int insert(IndexCaptitalFlowMonthModel record);

    int insertSelective(IndexCaptitalFlowMonthModel record);

    IndexCaptitalFlowMonthModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexCaptitalFlowMonthModel record);

    int updateByPrimaryKey(IndexCaptitalFlowMonthModel record);

    Date getLastEndDate();

    Date getLastBeginDate();

    Date getClosestBeginDate(Date date);

    List<IndexCaptitalFlowMonthModel> selectHeatByIndexCodesAfterBeginDate(@Param("indexCodes") List<String> indexCodes, @Param("beginDate") Date beginDate, @Param("limit") Integer limit);
}