package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.StockMarket5MinPriceModel;

public interface IStockMarket5MinPriceDao {
    int deleteByPrimaryKey(Long id);

    int insert(StockMarket5MinPriceModel record);

    int insertSelective(StockMarket5MinPriceModel record);

    StockMarket5MinPriceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarket5MinPriceModel record);

    int updateByPrimaryKey(StockMarket5MinPriceModel record);
}