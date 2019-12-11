package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.WechatHotKeysModel;

public interface IWechatHotKeysDao {
    int deleteByPrimaryKey(Long wechat_id);

    int insert(WechatHotKeysModel record);

    int insertSelective(WechatHotKeysModel record);

    WechatHotKeysModel selectByPrimaryKey(Long wechat_id);

    int updateByPrimaryKeySelective(WechatHotKeysModel record);

    int updateByPrimaryKey(WechatHotKeysModel record);
}