package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.Company30MinPriceModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ICompany30MinPriceDao {
    int deleteByPrimaryKey(Long id);

    int insert(Company30MinPriceModel record);

    int insertSelective(Company30MinPriceModel record);

    Company30MinPriceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Company30MinPriceModel record);

    int updateByPrimaryKey(Company30MinPriceModel record);

    List<Company30MinPriceModel> getRecords(@Param("stockCode") String stockCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}