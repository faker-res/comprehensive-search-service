package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.TransacModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ITransacDao {
    int deleteByPrimaryKey(Long id);

    int insert(TransacModel record);

    int insertSelective(TransacModel record);

    TransacModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransacModel record);

    int updateByPrimaryKeyWithBLOBs(TransacModel record);

    int updateByPrimaryKey(TransacModel record);

    TransacModel selectBySecurityType(@Param("securityType") Long securityType, @Param("endDate") String endDate);

    List<TransacModel> buildLastDayWithSecurityTypeAndDateAndNumber(@Param("securityType") Long securityType, @Param("endDate") String endDate,@Param("limit") Integer limit);

    TransacModel buildLastDayWithSecurityTypeAndDateAndNumberOne(@Param("securityType") Long securityType, @Param("endDate") Date endDate);

    TransacModel getBeginTransDate(@Param("securityType") Long securityType, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    TransacModel getEndTransDate(@Param("securityType") Long securityType, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    TransacModel getLastTransDateBeforeToday(@Param("securityType") Long securityType);
}