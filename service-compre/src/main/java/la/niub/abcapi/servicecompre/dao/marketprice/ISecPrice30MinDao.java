package la.niub.abcapi.servicecompre.dao.marketprice;

import la.niub.abcapi.servicecompre.model.SecPrice30MinModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISecPrice30MinDao {
    int deleteByPrimaryKey(Long id);

    int insert(SecPrice30MinModel record);

    int insertSelective(SecPrice30MinModel record);

    SecPrice30MinModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecPrice30MinModel record);

    int updateByPrimaryKey(SecPrice30MinModel record);

    List<SecPrice30MinModel> getRecords(@Param("stockCode") String stockCode, @Param("startTime") Date startTime);
}