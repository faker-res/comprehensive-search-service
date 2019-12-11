package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.VNewsSentimentFundModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IVNewsSentimentFundDao {
    int insert(VNewsSentimentFundModel record);

    int insertSelective(VNewsSentimentFundModel record);

    List<VNewsSentimentFundModel> selectBySecUniCode(@Param("secUniCode") Long secUniCode, @Param("endDate") String endDate);
}