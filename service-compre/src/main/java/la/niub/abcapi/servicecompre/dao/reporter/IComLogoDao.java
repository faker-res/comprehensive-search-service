package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComLogoModel;

public interface IComLogoDao {
    int deleteByPrimaryKey(Long id);

    int insert(ComLogoModel record);

    int insertSelective(ComLogoModel record);

    ComLogoModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ComLogoModel record);

    int updateByPrimaryKey(ComLogoModel record);

    ComLogoModel selectByComUniCode(Long comUniCode);
}