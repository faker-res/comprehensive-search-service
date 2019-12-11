package la.niub.abcapi.servicecompre.dao.market;

import la.niub.abcapi.servicecompre.model.IndexPriceYearModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IIndexPriceYearDao {
    int deleteByPrimaryKey(Long id);

    int insert(IndexPriceYearModel record);

    int insertSelective(IndexPriceYearModel record);

    IndexPriceYearModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexPriceYearModel record);

    int updateByPrimaryKey(IndexPriceYearModel record);

    List<IndexPriceYearModel> selectByIndexUniCodesAfterTime(@Param("indexUniCodes") List<Long> indexUniCodes, @Param("time") Date time);
}