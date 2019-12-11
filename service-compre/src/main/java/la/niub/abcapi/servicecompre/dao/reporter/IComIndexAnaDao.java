package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComIndexAnaModel;
import la.niub.abcapi.servicecompre.model.bo.theme.BalanceDistBO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IComIndexAnaDao {

    List<ComIndexAnaModel> getTopNRecords(@Param("comUniCode") Long comUniCode, @Param("limit") Integer limit);

    List<BalanceDistBO> selectBalanceDistByComUniCodeList(@Param("com_uni_code_list") List<Long> com_uni_code_list);

    Date selectMaxEndDateByComUniCode(@Param("com_uni_code") Long com_uni_code);

    BalanceDistBO selectBalanceDistByEndDateAndComUniCode(@Param("com_uni_code") Long com_uni_code, @Param("end_date") Date end_date);
}