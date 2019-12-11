package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.HiborAnalystModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IHiborAnalystDao {
    int deleteByPrimaryKey(Long added_id);

    int insert(HiborAnalystModel record);

    int insertSelective(HiborAnalystModel record);

    HiborAnalystModel selectByPrimaryKey(Long added_id);

    int updateByPrimaryKeySelective(HiborAnalystModel record);

    int updateByPrimaryKey(HiborAnalystModel record);

    List<HiborAnalystModel> selectPeoByIds(@Param("reportIds") List<Long> reportIds);
}