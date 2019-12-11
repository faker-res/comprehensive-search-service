package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FreqSearchTablesModel;

import java.util.List;

public interface IFreqSearchTablesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FreqSearchTablesModel record);

    int insertSelective(FreqSearchTablesModel record);

    FreqSearchTablesModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FreqSearchTablesModel record);

    int updateByPrimaryKey(FreqSearchTablesModel record);

    List<FreqSearchTablesModel> selectLimit(Integer limit);
}