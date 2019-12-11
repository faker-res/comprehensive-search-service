package la.niub.abcapi.servicecompre.dao.marketprice;

import la.niub.abcapi.servicecompre.model.SecPrice15MinModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISecPrice15MinDao {
    int deleteByPrimaryKey(Long id);

    int insert(SecPrice15MinModel record);

    int insertSelective(SecPrice15MinModel record);

    SecPrice15MinModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecPrice15MinModel record);

    int updateByPrimaryKey(SecPrice15MinModel record);

    List<SecPrice15MinModel> getRecords(@Param("stockCode") String stockCode, @Param("startTime") Date startTime);
}