package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.IndexRangeChanModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IIndexRangeChanDao {
    int deleteByPrimaryKey(Long id);

    int insert(IndexRangeChanModel record);

    int insertSelective(IndexRangeChanModel record);

    IndexRangeChanModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndexRangeChanModel record);

    int updateByPrimaryKey(IndexRangeChanModel record);

    List<IndexRangeChanModel> getRecordsByDate(@Param("indexUniCode") Long indexUniCode,
                                               @Param("tradeDate") Date tradeDate,
                                               @Param("periods") List<Integer> periods);

    IndexRangeChanModel getRecord5Day(@Param("indexUniCode") Long indexUniCode,
                                               @Param("tradeDate") Date tradeDate);

    IndexRangeChanModel getRecord20Day(@Param("indexUniCode") Long indexUniCode,
                                      @Param("tradeDate") Date tradeDate);

    List<IndexRangeChanModel> find(Map paraMap);
}