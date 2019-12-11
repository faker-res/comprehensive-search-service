package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.OrganModel;

import java.util.List;

public interface IOrganDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrganModel record);

    int insertSelective(OrganModel record);

    OrganModel selectByPrimaryKey(Integer id);

    OrganModel selectByOrgId(int org_id);

    int updateByPrimaryKeySelective(OrganModel record);

    int updateByPrimaryKey(OrganModel record);

    List<OrganModel> getTop6();

    OrganModel selectByPublish(String publish);
}