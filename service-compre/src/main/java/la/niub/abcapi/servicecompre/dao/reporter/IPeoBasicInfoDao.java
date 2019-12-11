package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.PeoBasicInfoModel;
import la.niub.abcapi.servicecompre.model.PeoBasicInfoWithBLOBs;
import org.apache.ibatis.annotations.Param;

public interface IPeoBasicInfoDao {
    int deleteByPrimaryKey(Long peo_uni_code);

    int insert(PeoBasicInfoWithBLOBs record);

    int insertSelective(PeoBasicInfoWithBLOBs record);

    PeoBasicInfoWithBLOBs selectByPrimaryKey(Long peo_uni_code);

    int updateByPrimaryKeySelective(PeoBasicInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PeoBasicInfoWithBLOBs record);

    int updateByPrimaryKey(PeoBasicInfoModel record);

    String selectNameByPeoUniCode(@Param("peo_uni_code") Long peo_uni_code) throws Exception;
}