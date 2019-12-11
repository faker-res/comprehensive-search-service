package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.WechatOperationKey;
import la.niub.abcapi.servicecompre.model.WechatOperationModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IWechatOperationDao {
    int deleteByPrimaryKey(WechatOperationKey key);

    int insert(WechatOperationModel record);

    int insertSelective(WechatOperationModel record);

    WechatOperationModel selectByPrimaryKey(WechatOperationKey key);

    int updateByPrimaryKeySelective(WechatOperationModel record);

    int updateByPrimaryKey(WechatOperationModel record);

    List<WechatOperationModel> getByWechatIdAndDate(@Param("wechatId") Long wechatId, @Param("date") Integer date);
}