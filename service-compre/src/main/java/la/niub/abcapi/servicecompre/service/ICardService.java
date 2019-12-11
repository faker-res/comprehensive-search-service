package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.HiborAuthorCountModel;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystReportStockChartBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystReportStocksBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardStockBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardWeChatBO;

import java.util.List;

/**
 * 卡片
 */
public interface ICardService {

    /**
     * 证券卡片
     * @param code
     */
    CardStockBO buildStockCard(Integer code);

    /**
     * 分析师卡片
     * @param code
     * @return
     */
    CardAnalystBO buildAnalystCard(String code, String organ);

    /**
     * 获取分析师卡片研究覆盖股票最新|最多
     * @param Author
     * @param organ
     * @param tab
     * @return
     */
    List<CardAnalystReportStocksBO> buildAnalystReportStocks(String Author, String organ, Integer tab,Integer limit);

    /**
     * 获取分析师卡片研究的股票股价和研报评级曲线图
     * @param Author
     * @param organ
     * @param abc_code
     * @return
     */
    CardAnalystReportStockChartBO buildAnalystReportStockChart(String Author, String organ, String abc_code);


    CardWeChatBO buildWeChatCard(Integer id);

    /**
     * 统计该领域近一年分析师的研报数量
     * @param direction
     * @return
     */
    List<HiborAuthorCountModel> getAnalystReportsNum(String direction);

}
