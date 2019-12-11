package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.bo.card.CardStockNewssetBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockSharePriceChangeBO;

import java.util.Date;
import java.util.List;

public interface ICompanyManagerService {

    String getStockCategory(String secCode);

    /**
     * 实时行情
     * @param stockCode
     * @return
     */
    CardStockNewssetBO buildCompanyNewestStockInfo(String stockCode);

    CardStockSharePriceChangeBO buildSharePriceChange(String stockCode, Date startTime, String graphType);

    List<String> getStockRealTimePrice(String stockCode);

    Object getStockCard(String stock_code);
}
