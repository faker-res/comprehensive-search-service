package la.niub.abcapi.servicecompre.dao.marketprice;

import la.niub.abcapi.servicecompre.model.SecPrice60MinModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISecPrice60MinDao {
    int deleteByPrimaryKey(Long id);

    int insert(SecPrice60MinModel record);

    int insertSelective(SecPrice60MinModel record);

    SecPrice60MinModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecPrice60MinModel record);

    int updateByPrimaryKey(SecPrice60MinModel record);

    List<SecPrice60MinModel> getRecords(@Param("stockCode") String stockCode, @Param("startTime") Date startTime);
}