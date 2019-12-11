package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystCompetitionBO;
import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystDetailBO;
import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystHeatBO;
import la.niub.abcapi.servicecompre.model.bo.analyst.AnalystSummaryBO;
import la.niub.abcapi.servicecompre.model.bo.fund.FundManagerBO;
import la.niub.abcapi.servicecompre.model.response.message.MessageResponse;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 分析师
 */
public interface IAnalystService {

    /**
     * 分析师详情
     * @param peoUniCode
     * @return
     */
    AnalystDetailBO getAnalystDetail(String peoUniCode);

    /**
     * 人物动态
     * @param peoUniCode
     * @return
     */
    List<MessageResponse> getDynamic(String peoUniCode,Integer limit);

    /**
     * 获取同领域分析师
     * @param peoUniCode
     * @param limit
     * @return
     */
    List<AnalystSummaryBO> getSameFieldAnalyst(String peoUniCode, Integer limit) throws ServiceException;

    /**
     * 获取明星分析师
     * @param peoUniCode
     * @param limit
     * @return
     */
    List<AnalystSummaryBO> getStarAnalyst(String peoUniCode, Integer limit);

    /**
     * 团队热力
     * @param peoUniCode
     * @param limit
     * @return
     */
    List<AnalystHeatBO> getHeat(String peoUniCode, Integer limit);

    /**
     * 团队竞争力分析
     * @param peoUniCode
     * @param limit
     * @return
     */
    List<AnalystCompetitionBO> getCompetition(String peoUniCode, Date startTime, Date endTime, Integer year, Integer limit);

    /**
     * 其他团队竞争力分析
     * @param peoUniCode
     * @param limit
     * @return
     */
    List<AnalystCompetitionBO> getOtherCompetition(String peoUniCode, Date startTime, Date endTime, Integer year, Integer limit);

    /**
     * 行业明星分析师
     * @param indexUniCode
     * @param limit
     * @return
     */
    List<FundManagerBO> getStarAnalystOfPublicCompany(Long indexUniCode, Integer limit);

    /**
     * 分析师三级详情页面
     */
    /**
     * 机构列表
     */
    List<Map<String, Object>> getOrgSnameList(String keyword, String userId, String token) throws Exception;

    //分析师列表
    Map<String, Object> getAnalystList(String indu_name, String org_sname,
                                             String name, String spellPrefix, String prior,
                                             Integer offset, Integer limit) throws Exception;

    //分析师预测能力图表数据
    Map<String, Object>  getAnalystPredictive(Map map);

    //分析师预测能力关注公司数据
   List<Map>  getAnalystStockCode(Map map);

   //分析师关注股票图表数据
   Map  getAnalystPayStockChart(Map paras);

   //分析师关注股票折线图数据
   Map getAnalystStockPayLine(Map paras);
}
