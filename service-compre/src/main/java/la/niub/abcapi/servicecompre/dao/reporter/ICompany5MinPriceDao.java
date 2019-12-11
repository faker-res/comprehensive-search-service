package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.Company5MinPriceModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ICompany5MinPriceDao {
    int deleteByPrimaryKey(Long id);

    int insert(Company5MinPriceModel record);

    int insertSelective(Company5MinPriceModel record);

    Company5MinPriceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Company5MinPriceModel record);

    int updateByPrimaryKey(Company5MinPriceModel record);

    List<Company5MinPriceModel> getRecords(@Param("stockCode") String stockCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}