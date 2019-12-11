package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.StockCategoryModel;

public interface IStockCategoryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(StockCategoryModel record);

    int insertSelective(StockCategoryModel record);

    StockCategoryModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockCategoryModel record);

    int updateByPrimaryKey(StockCategoryModel record);

    StockCategoryModel selectBySecCode(String secCode);
}