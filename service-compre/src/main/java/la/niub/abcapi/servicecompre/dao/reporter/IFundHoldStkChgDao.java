package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundHoldStkChgModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IFundHoldStkChgDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundHoldStkChgModel record);

    int insertSelective(FundHoldStkChgModel record);

    FundHoldStkChgModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundHoldStkChgModel record);

    int updateByPrimaryKey(FundHoldStkChgModel record);

    Date getLastEndDate(Long secUniCode);

    List<FundHoldStkChgModel> getYearRecords(@Param("secUniCode") Long secUniCode,
                                             @Param("endDate") Date endDate,
                                             @Param("orderField") String orderField,
                                             @Param("offset") Integer offset,
                                             @Param("limit") Integer limit
    );

}