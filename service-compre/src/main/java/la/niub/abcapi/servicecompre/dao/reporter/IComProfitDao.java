package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComProfitModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IComProfitDao {
    int deleteByPrimaryKey(Long id);

    int insert(ComProfitModel record);

    int insertSelective(ComProfitModel record);

    ComProfitModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ComProfitModel record);

    int updateByPrimaryKey(ComProfitModel record);

    List<ComProfitModel> getTopNRecords(@Param("comUniCode") Long comUniCode, @Param("limit") Integer limit);


    List<ComProfitModel> getRecordsByReportType(@Param("comUniCode") Long comUniCode,
                                                @Param("reportType") Integer reportType,
                                                @Param("limit") Integer limit);
}