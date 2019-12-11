package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.StockMarketWeekPriceModel;

public interface IStockMarketWeekPriceDao {
    int deleteByPrimaryKey(Long id);

    int insert(StockMarketWeekPriceModel record);

    int insertSelective(StockMarketWeekPriceModel record);

    StockMarketWeekPriceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarketWeekPriceModel record);

    int updateByPrimaryKey(StockMarketWeekPriceModel record);
}