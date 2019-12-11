package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.AnalystForcastModel;
import la.niub.abcapi.servicecompre.model.AnalystForcastWithCount;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IAnalystForcastDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AnalystForcastModel record);

    int insertSelective(AnalystForcastModel record);

    AnalystForcastModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnalystForcastModel record);

    int updateByPrimaryKey(AnalystForcastModel record);

    List<AnalystForcastModel> selectByStockCodeAndTime(@Param("stockcode") String stockcode, @Param("startDate") Date startDate);

    List<AnalystForcastModel> getRatingByDate(@Param("stockcode") String stockcode,
                                              @Param("startDate") Date startDate,
                                              @Param("endDate") Date endDate);

    List<AnalystForcastWithCount> getByPeoUniCodeWithCount(@Param("peoUniCode") String peoUniCode,
                                                           @Param("startDate") Date startDate,
                                                           @Param("endDate") Date endDate,
                                                           @Param("limit") Integer limit);

    List<AnalystForcastWithCount> getOtherByPeoUniCodesAndStockCodesWithCount(
            @Param("peoUniCode") String peoUniCode,
            @Param("peoUniCodes") List<String> peoUniCodes,
            @Param("stockCodes") List<String> stockCodes,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("limit") Integer limit);

    List<AnalystForcastWithCount> getOtherByPeoUniCodeWithCount(@Param("peoUniCode") String peoUniCode,
                                                                @Param("startDate") Date startDate,
                                                                @Param("endDate") Date endDate,
                                                                @Param("limit") Integer limit);

    List<AnalystForcastModel> getByPeoUniCodePredictive(Map paras);

    List<String> getByPeoUniCodeStockCode(Map paras);

    List<Map> getPayStockChart(Map paras);



}