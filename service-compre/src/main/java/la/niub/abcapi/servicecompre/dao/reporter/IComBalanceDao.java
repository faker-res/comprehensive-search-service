package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComBalanceModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IComBalanceDao {
    int deleteByPrimaryKey(Long id);

    int insert(ComBalanceModel record);

    int insertSelective(ComBalanceModel record);

    ComBalanceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ComBalanceModel record);

    int updateByPrimaryKey(ComBalanceModel record);

    ComBalanceModel selectByComUniCode(Long comUniCode);

    List<ComBalanceModel> getTopNRecords(@Param("comUniCode") Long comUniCode, @Param("limit") Integer limit);

    List<ComBalanceModel> find(Map map);

}