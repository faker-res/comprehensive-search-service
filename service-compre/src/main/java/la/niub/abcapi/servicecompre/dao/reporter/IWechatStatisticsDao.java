package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.WechatStatisticsModel;

public interface IWechatStatisticsDao {
    int deleteByPrimaryKey(Long wechat_id);

    int insert(WechatStatisticsModel record);

    int insertSelective(WechatStatisticsModel record);

    WechatStatisticsModel selectByPrimaryKey(Long wechat_id);

    int updateByPrimaryKeySelective(WechatStatisticsModel record);

    int updateByPrimaryKey(WechatStatisticsModel record);
}