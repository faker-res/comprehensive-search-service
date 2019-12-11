package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.SecStrucAlterModel;

public interface ISecStrucAlterDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SecStrucAlterModel record);

    int insertSelective(SecStrucAlterModel record);

    SecStrucAlterModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecStrucAlterModel record);

    int updateByPrimaryKey(SecStrucAlterModel record);

    SecStrucAlterModel selectByComUniCode(Long comUniCode);
}