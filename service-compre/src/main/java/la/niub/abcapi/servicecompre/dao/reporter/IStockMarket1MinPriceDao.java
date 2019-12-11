package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.StockMarket1MinPriceModel;

public interface IStockMarket1MinPriceDao {
    int deleteByPrimaryKey(Long id);

    int insert(StockMarket1MinPriceModel record);

    int insertSelective(StockMarket1MinPriceModel record);

    StockMarket1MinPriceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarket1MinPriceModel record);

    int updateByPrimaryKey(StockMarket1MinPriceModel record);
}