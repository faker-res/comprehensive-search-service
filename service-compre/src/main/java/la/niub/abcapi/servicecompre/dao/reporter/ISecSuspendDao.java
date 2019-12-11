package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.SecSuspendModel;
import org.apache.ibatis.annotations.Param;

public interface ISecSuspendDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SecSuspendModel record);

    int insertSelective(SecSuspendModel record);

    SecSuspendModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecSuspendModel record);

    int updateByPrimaryKey(SecSuspendModel record);

    SecSuspendModel selectBySecUniCode(@Param("secUniCode") Long secUniCode, @Param("date") String date);

    SecSuspendModel getRecordBySecUniCode(@Param("secUniCode") Long secUniCode);
}