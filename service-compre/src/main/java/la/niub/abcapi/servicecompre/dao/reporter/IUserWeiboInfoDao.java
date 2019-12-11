package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.UserWeiboInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserWeiboInfoDao {

    List<UserWeiboInfoModel> getUserWeiboInfoByUserId(@Param("userId") Long userId) throws Exception;
}
