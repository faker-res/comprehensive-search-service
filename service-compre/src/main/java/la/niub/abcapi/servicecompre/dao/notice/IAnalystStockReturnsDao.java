package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.AnalystStockReturnsModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAnalystStockReturnsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AnalystStockReturnsModel record);

    int insertSelective(AnalystStockReturnsModel record);

    AnalystStockReturnsModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnalystStockReturnsModel record);

    int updateByPrimaryKey(AnalystStockReturnsModel record);

    List<AnalystStockReturnsModel> selectByPeoUniCodesAndStockCodesAndYear(@Param("peoUniCodes") List<String> peoUniCodes, @Param("stockCodes") List<String> stockCodes, @Param("year") Integer year);
}