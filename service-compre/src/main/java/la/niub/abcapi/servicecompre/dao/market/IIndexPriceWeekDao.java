package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.IndexPriceWeekModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IIndexPriceWeekDao {
    int deleteByPrimaryKey(Long id);

    int insert(IndexPriceWeekModel record);

    int insertSelective(IndexPriceWeekModel record);

    IndexPriceWeekModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexPriceWeekModel record);

    int updateByPrimaryKey(IndexPriceWeekModel record);

    List<IndexPriceWeekModel> selectByIndexUniCodesAfterTime(@Param("indexUniCodes") List<Long> indexUniCodes, @Param("time") Date time);
}