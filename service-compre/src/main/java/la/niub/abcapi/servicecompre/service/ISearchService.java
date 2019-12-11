package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.request.SearchFinanceCardRequest;
import la.niub.abcapi.servicecompre.model.request.SearchGeneralRequest;

import java.util.List;

public interface ISearchService {
    List<Object> general(SearchGeneralRequest searchResultRequest) throws Throwable;

    Object card(SearchGeneralRequest searchResultRequest) throws Throwable;

    /**
     * 南京团队 择股
     * @param searchResultRequest
     * @return
     * @throws Throwable
     */
    Object cardFinance(SearchGeneralRequest searchResultRequest) throws Throwable;

    /**
     * 南京团队 择股指标命中第2次调用
     * @param searchFinanceCardRequest
     * @return
     * @throws Throwable
     */
    Object cardFinanceShare(SearchFinanceCardRequest searchFinanceCardRequest) throws Throwable;

}
