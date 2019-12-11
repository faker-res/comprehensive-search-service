package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.IndexBasicInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IIndexBasicInfoDao {
    int deleteByPrimaryKey(Long index_uni_code);

    int insert(IndexBasicInfoModel record);

    int insertSelective(IndexBasicInfoModel record);

    IndexBasicInfoModel selectByPrimaryKey(Long index_uni_code);

    int updateByPrimaryKeySelective(IndexBasicInfoModel record);

    int updateByPrimaryKey(IndexBasicInfoModel record);

    IndexBasicInfoModel selectByIndexCode(String index_code);

    List<IndexBasicInfoModel> getByIndexCodes(@Param("indexCodes") List<String> indexCodes);

    String getThemeNameByCode(String theme_code) throws Exception;

    List<IndexBasicInfoModel> getByIndexUniCodes(@Param("indexUniCodes") List<Long> indexUniCodes);

    List<Map<String, Object>> selectSWIndustryList();

}
