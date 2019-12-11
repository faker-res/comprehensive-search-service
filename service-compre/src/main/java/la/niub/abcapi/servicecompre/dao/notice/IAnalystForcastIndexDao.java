package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.AnalystForcastIndexModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAnalystForcastIndexDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AnalystForcastIndexModel record);

    int insertSelective(AnalystForcastIndexModel record);

    AnalystForcastIndexModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnalystForcastIndexModel record);

    int updateByPrimaryKey(AnalystForcastIndexModel record);

    List<AnalystForcastIndexModel> getEps(@Param("stockcode") String stockcode);

}