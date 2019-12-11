package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.UserWeiboAccountModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserWeiboAccountDao {
    int deleteByPrimaryKey(String account_id);

    int insert(UserWeiboAccountModel record);

    int insertSelective(UserWeiboAccountModel record);

    UserWeiboAccountModel selectByPrimaryKey(String account_id);

    int updateByPrimaryKeySelective(UserWeiboAccountModel record);

    int updateByPrimaryKey(UserWeiboAccountModel record);

    List<UserWeiboAccountModel> selectByUserId(@Param("userId") String userId);
}