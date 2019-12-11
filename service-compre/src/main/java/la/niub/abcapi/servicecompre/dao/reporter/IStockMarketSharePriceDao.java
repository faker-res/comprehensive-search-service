package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.StockMarketSharePriceModel;

public interface IStockMarketSharePriceDao {
    int deleteByPrimaryKey(Long id);

    int insert(StockMarketSharePriceModel record);

    int insertSelective(StockMarketSharePriceModel record);

    StockMarketSharePriceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarketSharePriceModel record);

    int updateByPrimaryKey(StockMarketSharePriceModel record);
}