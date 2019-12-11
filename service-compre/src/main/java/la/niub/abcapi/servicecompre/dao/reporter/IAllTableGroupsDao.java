package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.AllTableGroupsModel;

import java.util.List;

public interface IAllTableGroupsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AllTableGroupsModel record);

    int insertSelective(AllTableGroupsModel record);

    AllTableGroupsModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AllTableGroupsModel record);

    int updateByPrimaryKey(AllTableGroupsModel record);

    List<AllTableGroupsModel> selectDisplay();

    List<AllTableGroupsModel> selectNotDisplay();
}