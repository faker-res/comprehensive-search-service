package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.IndexPriceMonthModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IIndexPriceMonthDao {
    int deleteByPrimaryKey(Long id);

    int insert(IndexPriceMonthModel record);

    int insertSelective(IndexPriceMonthModel record);

    IndexPriceMonthModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexPriceMonthModel record);

    int updateByPrimaryKey(IndexPriceMonthModel record);

    List<IndexPriceMonthModel> selectByIndexUniCodesAfterTime(@Param("indexUniCodes") List<Long> indexUniCodes, @Param("time") Date time);
}