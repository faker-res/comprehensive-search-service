package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.model.ComprehensiveSearchesTopModel;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveHotBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveNewsBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveNoticeBO;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveReportBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardBO;
import la.niub.abcapi.servicecompre.model.bo.sidebar.SidebarBO;

import java.util.List;

/**
 * 综合搜索
 * @author wuxiao
 */
public interface IComprehensiveService {

    /**
     * 综合搜索首页所有类型推荐
     * @return
     */
    ComprehensiveBO buildAllTypeResult() throws ServiceException;

    /**
     * 综合搜索第一屏热门推荐和搜索
     * @return
     */
    Object buildTipsAndTop();

    /**
     * 热门推荐
     * @return
     */
    ComprehensiveHotBO buildHotTips();

    List<ComprehensiveSearchesTopModel> buildTop(Integer limit, Integer offset);

    /**
     * 综合搜索首页研报分析数据图表推荐
     * @return
     */
    ComprehensiveChartBO buildReportChart();

    /**
     * 综合搜索首页公告推荐
     * @param module : perspective|events  透视|重要事件
     * @return array
     */
    ComprehensiveNoticeBO buildNotice(String module, Integer limit) throws ServiceException;

    /**
     * 综合搜索首页研报推荐
     * @param limit
     * @param terminal
     * @return
     */
    ComprehensiveReportBO buildReport(Integer limit, Integer terminal);

    /**
     * 综合搜索首页资讯推荐
     * @return
     */
    ComprehensiveNewsBO buildNews();

    /**
     * 卡片数据规整化
     * @param type
     * @param data
     * @return
     */
    CardBO buildCardsResult(String type, String data);

    /**
     * 综合搜索侧边栏规整化
     * @param type
     * @param data
     * @return
     */
    SidebarBO buildSideResult(String type, String data);

}
