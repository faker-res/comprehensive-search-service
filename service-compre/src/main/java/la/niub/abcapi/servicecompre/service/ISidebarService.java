package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarAnalystBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarStockBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarWeChatBO;

/**
 * 侧边栏
 */
public interface ISidebarService {

    /**
     * 证券侧边栏
     * @param stockCode
     * @param industryLimit
     * @param authorLimit
     * @return
     */
    SidebarStockBO buildStock(Integer stockCode, Integer industryLimit, Integer authorLimit);

    /**
     * 分析师侧边栏
     * @param code
     * @param limit
     * @return
     */
    SidebarAnalystBO buildAnalyst(String code, Integer limit);


    SidebarWeChatBO buildWeChat(Integer code);

}
