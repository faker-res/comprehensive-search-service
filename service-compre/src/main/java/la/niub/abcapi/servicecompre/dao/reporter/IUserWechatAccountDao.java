package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.UserWechatAccountModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserWechatAccountDao {
    int deleteByPrimaryKey(String account_id);

    int insert(UserWechatAccountModel record);

    int insertSelective(UserWechatAccountModel record);

    UserWechatAccountModel selectByPrimaryKey(String account_id);

    int updateByPrimaryKeySelective(UserWechatAccountModel record);

    int updateByPrimaryKey(UserWechatAccountModel record);

    List<UserWechatAccountModel> selectByUserIdAndType(@Param("userId") String userId, @Param("type") String type);

    List<UserWechatAccountModel> selectByAuthorAndType(@Param("author") String author, @Param("type") String type);

    List<UserWechatAccountModel> selectByUserIdListAndType(@Param("fvIdList") List<String> fvIdList, @Param("type") String type) throws Exception;

    List<UserWechatAccountModel> selectByType(String type) throws Exception;

    List<UserWechatAccountModel> selectAllByUserIdListAndType(@Param("userIdList") List<String> userIdList, @Param("type") String type);
}