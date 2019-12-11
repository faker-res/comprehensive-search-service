package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FinancialUserModel;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IFinancialUserDao {
    int deleteByPrimaryKey(String user_id);

    int insert(FinancialUserModel record);

    int insertSelective(FinancialUserModel record);

    FinancialUserModel selectByPrimaryKey(String user_id);

    int updateByPrimaryKeySelective(FinancialUserModel record);

    int updateByPrimaryKey(FinancialUserModel record);

    List<FinancialUserModel> getFinanceVipListByUserIdAndDomain(@Param("user_id") String user_id, @Param("domain") String domain,
                                                   @Param("limit") Integer limit) throws Exception;

    List<FinancialUserModel> getLikeTags(@Param("user_id") String user_id, @Param("tags") List<String> tags);

    List<String> getTagByTagPrefix(String tag_prefix) throws Exception;

    Integer getListCountByTagAndNameAndNamePrefix(@Param("tagList") List<String> tagList, @Param("name") String name,
                                                       @Param("name_py_prefix") String name_py_prefix) throws Exception;

    List<FinancialUserModel> getListByTagAndNameAndNamePrefix(@Param("tagList") List<String> tagList, @Param("name") String name,
                                                              @Param("name_py_prefix") String name_py_prefix, @Param("index") Integer index,
                                                              @Param("limit") Integer limit, @Param("order") String order) throws Exception;

    List<Map<String,Object>> getAllFinanceVip() throws Exception;

    List<Map<String,Object>> getFinanceVipListByUserIdList(@Param("userIdList") List<String> userIdList);
}