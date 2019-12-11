package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.AnalystStatisticsModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAnalystStatisticsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AnalystStatisticsModel record);

    int insertSelective(AnalystStatisticsModel record);

    AnalystStatisticsModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnalystStatisticsModel record);

    int updateByPrimaryKey(AnalystStatisticsModel record);

    List<AnalystStatisticsModel> selectByPeoUniCodes(@Param("peoUniCodes") List<String> peoUniCodes);

    AnalystStatisticsModel selectByPeoUniCode(String peoUniCodes);

}