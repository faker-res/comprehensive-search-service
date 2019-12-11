package la.niub.abcapi.servicecompre.controller;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.model.response.Response;
import la.niub.abcapi.servicecompre.service.ICompanyManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/stock")
public class StockInfoController {

    @Autowired
    ICompanyManagerService companyManagerService;

    /**
     * 获取股票卡片行情
     * @param stock_code
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/stock-card", method = RequestMethod.GET)
    Response getStockCard(String stock_code) throws ServiceException {
        if(stock_code == null || stock_code.isEmpty()){
            return new Response("Error Params");
        }
        return new Response(companyManagerService.getStockCard(stock_code));

    }

}
