package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.SystemConstModel;

public interface ISystemConstDao {
    int deleteByPrimaryKey(Long id);

    int insert(SystemConstModel record);

    int insertSelective(SystemConstModel record);

    SystemConstModel selectByPrimaryKey(Long id);

    SystemConstModel selectBySystemUniCode(Long systemUniCode);

    int updateByPrimaryKeySelective(SystemConstModel record);

    int updateByPrimaryKey(SystemConstModel record);

    SystemConstModel selectBySystemName(String systemName);
}