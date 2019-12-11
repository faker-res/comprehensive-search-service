package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.StockMarket30MinPriceModel;

public interface IStockMarket30MinPriceDao {
    int deleteByPrimaryKey(Long id);

    int insert(StockMarket30MinPriceModel record);

    int insertSelective(StockMarket30MinPriceModel record);

    StockMarket30MinPriceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarket30MinPriceModel record);

    int updateByPrimaryKey(StockMarket30MinPriceModel record);
}