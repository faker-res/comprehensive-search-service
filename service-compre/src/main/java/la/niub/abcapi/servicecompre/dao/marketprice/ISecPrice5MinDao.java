package la.niub.abcapi.servicecompre.dao.marketprice;

import la.niub.abcapi.servicecompre.model.SecPrice5MinModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISecPrice5MinDao {
    int deleteByPrimaryKey(Long id);

    int insert(SecPrice5MinModel record);

    int insertSelective(SecPrice5MinModel record);

    SecPrice5MinModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecPrice5MinModel record);

    int updateByPrimaryKey(SecPrice5MinModel record);

    List<SecPrice5MinModel> getRecords(@Param("stockCode") String stockCode, @Param("startTime") Date startTime);
}