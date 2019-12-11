package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.AbcIndustryModel;
import la.niub.abcapi.servicecompre.model.bo.ResearchIndustryBasicInfoBO;
import la.niub.abcapi.servicecompre.model.dao.AbcIndustryDaoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IAbcIndustryDao {

    List<AbcIndustryDaoModel> selectByInduCodes(@Param("induCodes") List<String> induCodes);

    int deleteByPrimaryKey(Long indu_uni_code);

    int insert(AbcIndustryModel record);

    int insertSelective(AbcIndustryModel record);

    AbcIndustryModel selectByPrimaryKey(Long indu_uni_code);

    int updateByPrimaryKeySelective(AbcIndustryModel record);

    int updateByPrimaryKey(AbcIndustryModel record);

    AbcIndustryModel selectByInduNameOf1001014(String induName);

    AbcIndustryModel selectByIndexCode(String indexCode);

    List<ResearchIndustryBasicInfoBO> selectResearchIndustryList();

    List<AbcIndustryModel> selectByIndexCodes(@Param("indexCodes") List<String> indexCodes);

    List<AbcIndustryModel> selectAllOf1001014();

    List<AbcIndustryModel> selectByParentIds(@Param("induCodes") List<String> induCodes);

    List<AbcIndustryModel> selectEntity(Map paras);
}
