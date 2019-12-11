package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.WechatPublicModel;
import la.niub.abcapi.servicecompre.model.WechatPublicMoreModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IWechatPublicDao {
    int deleteByPrimaryKey(Long id);

    int insert(WechatPublicModel record);

    int insertSelective(WechatPublicModel record);

    WechatPublicModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WechatPublicModel record);

    int updateByPrimaryKey(WechatPublicModel record);

    WechatPublicModel getByAccount(@Param("account") String account);

    List<WechatPublicModel> getByRandom(@Param("id") int id,@Param("limit") int limit);

    WechatPublicModel selectByName(String name);

    List<WechatPublicModel> getLikeTags(@Param("tags") List<String> tags);

    List<WechatPublicModel> getByClassifyOrderByFollower(@Param("classify") String classify,@Param("limit") Integer limit);

    List<WechatPublicModel> getListByIds(@Param("ids") List<Long> ids);

    List<WechatPublicModel> getListByNames(@Param("names") List<String> names);

    List<String> getTagByTagPrefix(String tag_prefix) throws Exception;

    List<String> getAllClassify() throws Exception;

    List<String> getAccountByTagListAndClassiftAndName(@Param("tagList") List<String> tagList) throws Exception;

    Integer getCountByAccountList(@Param("wechatAccountList") List<String> wechatAccountList, @Param("classify") String classify, @Param("public_name") String public_name) throws Exception;

    List<WechatPublicMoreModel> getListByAccountList(@Param("wechatAccountList") List<String> wechatAccountList, @Param("classify") String classify,
                                                    @Param("public_name") String public_name, @Param("order") String order,
                                                    @Param("index") Integer index, @Param("limit") Integer limit) throws Exception;
}