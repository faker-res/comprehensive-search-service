package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundManagerChgModel;
import la.niub.abcapi.servicecompre.model.FundMangerChgGroupByFundKeeperModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IFundManagerChgDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundManagerChgModel record);

    int insertSelective(FundManagerChgModel record);

    FundManagerChgModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundManagerChgModel record);

    int updateByPrimaryKey(FundManagerChgModel record);

    FundManagerChgModel selectByFundKeeperName(String fundKeeperName);

    FundManagerChgModel selectByFundKeeperCode(Long fundKeeperCode);

    List<FundMangerChgGroupByFundKeeperModel> selectByYearGroupByFundKeeper(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    List<FundManagerChgModel> getFundManagerQuantityByTime(@Param("com_uni_code") Long com_uni_code, @Param("plate_code") String plate_code,
                                                           @Param("startTime") Date startTime, @Param("endTime") Date endTime) throws Exception;

    List<FundManagerChgModel> getFundCompanyByPlateCode(String plate_code) throws Exception;
}