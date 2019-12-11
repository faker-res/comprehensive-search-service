package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.StockMarketMonthPriceModel;

public interface IStockMarketMonthPriceDao {
    int deleteByPrimaryKey(Long id);

    int insert(StockMarketMonthPriceModel record);

    int insertSelective(StockMarketMonthPriceModel record);

    StockMarketMonthPriceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarketMonthPriceModel record);

    int updateByPrimaryKey(StockMarketMonthPriceModel record);
}