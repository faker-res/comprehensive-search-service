package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.AnalystBasicinfoModel;
import la.niub.abcapi.servicecompre.model.UserSubscribeInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IUserSubscribeInfoDao {

    /**
     * 获取每个分组的订阅个数，按update_at排序
     * @param
     * @return
     */
    List<UserSubscribeInfoModel> getSubscribeInfo(@Param("groupId") List<Integer> groupId, @Param("type") Integer type);

    /**
     * 获取每个分组的订阅个数
     * @param
     * @return
     */
    List<UserSubscribeInfoModel> getSubscribeInfoNoOrder(@Param("groupId") List<Integer> groupId, @Param("type") Integer type);

    /**
     * 获取订阅个数
     * @param groupId
     * @param type
     * @param id
     * @return
     */
    UserSubscribeInfoModel getSubscribeInfoOne(@Param("groupId") Integer groupId, @Param("type") Integer type, @Param("id") Integer id);


    /**
     * 获取分析师信息
     * @param ids
     * @return
     */
    List<AnalystBasicinfoModel> getAnalystBasicinfoFromIds(@Param("ids") List<Integer> ids);

}