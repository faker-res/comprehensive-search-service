package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.PeoImageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPeoImageDao {
    int deleteByPrimaryKey(Long id);

    int insert(PeoImageModel record);

    int insertSelective(PeoImageModel record);

    PeoImageModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PeoImageModel record);

    int updateByPrimaryKey(PeoImageModel record);

    List<PeoImageModel> selectByPeoUniCodes(@Param("peoUniCodes") List<Long> peoUniCodes);

    PeoImageModel selectByPeoUniCode(@Param("peoUniCode") Long peoUniCode);
}