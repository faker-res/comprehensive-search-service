package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.UserSubscribeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IUserSubscribeDao {

    /**
     * 获取分组，如果type 为 null 获取所有的分组
     * @param
     * @return
     */
    List<UserSubscribeModel> getRecords(@Param("uid") String uid, @Param("type") Integer type);

    /**
     * 根据类型获取所有的订阅
     * @param uid
     * @param groupName
     * @param type
     * @return
     */
    List<UserSubscribeModel> getAllSubscribeWithGroupName(@Param("uid") String uid, @Param("groupName") String groupName, @Param("type") Integer type);

    /**
     * 根据类型获取订阅
     * @param uid
     * @param groupName
     * @param type
     * @return
     */
    UserSubscribeModel getAllSubscribeWithGroupNameOne(@Param("uid") String uid, @Param("groupName") String groupName, @Param("type") Integer type);

}