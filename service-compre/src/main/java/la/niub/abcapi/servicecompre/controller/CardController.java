package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystReportStockChartBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystReportStocksBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardBO;
import la.niub.abcapi.servicecompre.model.request.CardRequest;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.ICardService;
import la.niub.abcapi.servicecompre.service.IComprehensiveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 卡片
 */
@RestController
@RequestMapping(path = "/card")
public class CardController {

    private static Logger logger = LogManager.getLogger(CardController.class);

    @Autowired
    IComprehensiveService comprehensiveService;

    @Autowired
    ICardService cardService;

    /**
     * 获取
     * @param params
     * @return
     * @throws ServiceException
     */
    @GetMapping
    Response<CardBO> get(CardRequest params) throws ServiceException {

//        CardStockRequest cardStockRequest = params.parseData(CardStockRequest.class);
        CardBO cardBO = comprehensiveService.buildCardsResult(params.parseType(),params.getData());

        return new Response(cardBO);
    }

    /**
     * 获取分析师卡片研究覆盖股票最新|最多
     * @param author
     * @param organ
     * @param tab
     * @return
     * @throws ServiceException
     */
    @GetMapping(value = "/analyst-report-stocks")
    Response<CardAnalystReportStocksBO> getAnalystReportStocks(String author, String organ, Integer tab,Integer limit) throws ServiceException {
        if (limit == null){
            limit = 3;
        }
        return new Response(cardService.buildAnalystReportStocks(author, organ, tab,limit));
    }

    /**
     * 获取分析师卡片研究的股票股价和研报评级曲线图
     * @param author
     * @param organ
     * @param abc_code
     * @return
     * @throws ServiceException
     */

    @GetMapping(value = "/analyst-report-stock-chart")
    Response<CardAnalystReportStockChartBO> getAnalystReportStockChart(String author, String organ, String abc_code)  throws ServiceException{
         return new Response(cardService.buildAnalystReportStockChart(author, organ, abc_code));
    }

}