package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.Company1MinPriceModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ICompany1MinPriceDao {
    int deleteByPrimaryKey(Long id);

    int insert(Company1MinPriceModel record);

    int insertSelective(Company1MinPriceModel record);

    Company1MinPriceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Company1MinPriceModel record);

    int updateByPrimaryKey(Company1MinPriceModel record);

    List<Company1MinPriceModel> getRecords(@Param("stockCode") String stockCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}