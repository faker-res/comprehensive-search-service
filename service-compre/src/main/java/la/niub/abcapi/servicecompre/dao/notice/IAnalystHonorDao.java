package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.AnalystHonorModel;
import la.niub.abcapi.servicecompre.model.bo.AnalystHonorInfoBO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IAnalystHonorDao {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(AnalystHonorModel record);
//
//    int insertSelective(AnalystHonorModel record);
//
//    AnalystHonorModel selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(AnalystHonorModel record);
//
//    int updateByPrimaryKey(AnalystHonorModel record);

    /**
     * 获取分析师的研究领域
     * @param peo_uni_code
     * @return
     */

    List<AnalystHonorModel> getDirectionList(@Param("peo_uni_code") String peo_uni_code);

    /**
     * 获取分析师荣誉
     * @param peo_uni_code
     * @param limit
     * @return
     */
    List<AnalystHonorModel> getHonor(@Param("peo_uni_code") String peo_uni_code, @Param("limit") Integer limit);

    /**
     * 获取分析师新财富总共次数
     * @param peo_uni_code
     * @return
     */
    Integer getHonorNum(@Param("peo_uni_code") String peo_uni_code);

    /**
     * 同领域分析师新财富上榜次数排行
     * @param direction_list
     * @param limit
     * @return
     */
    List<AnalystHonorModel> buildRecordsOrderByHonorNum(@Param("direction_list") List<String> direction_list,
                                                        @Param("limit") Integer limit);

    /**
     * 同领域分析师新财富名次排行
     * @param direction_list
     * @param limit
     * @return
     */
    List<AnalystHonorModel> buildRecordsOrderByHonorRank(@Param("direction_list") List<String> direction_list,
                                                         @Param("limit") Integer limit);

    Date buildMaxTimeByTime();

//    List<AnalystHonorModel> buildRecordsByAnalyst(@Param("analyst") List<String> analyst,
//                                                  @Param("maxYear") Date maxYear,
//                                                  @Param("limit") Integer limit);

    List<AnalystHonorModel> buildRecordsByAnalyst(@Param("analyst") List<String> analyst,
                                                  @Param("limit") Integer limit);


    int SelectNewWealthHonorCountByOrg(@Param("organ") String organ, @Param("year") int year);

    List<AnalystHonorModel> getHonorByPeoUniCodes(@Param("peoUniCodes") List<String> peoUniCodes, @Param("limit") Integer limit);

    List<AnalystHonorInfoBO> selectAnalystHonorRankingByPeoUniCodeList(@Param("peo_uni_code_list") List<String> peo_uni_code_list, @Param("indu_name_list") List<String> indu_name_list);

    List<AnalystHonorInfoBO> selectAnalystHonorRankingByPeoUniCodeList2(@Param("org_uni_code") Long org_uni_code, @Param("indu_name_list") List<String> indu_name_list);

    List<AnalystHonorModel> judegeAnalystIsPrized(@Param("peo_uni_code") String peo_uni_code);

    List<Long> getPeoUniCodeByDirectionAndPeoUniCodeList(@Param("peoUniCodeList") List<Long> peoUniCodeList, @Param("directionList") List<String> directionList) throws Exception;

    List<Map<String, Object>> getRankByPeoUniCode(@Param("peoUniCode") String peoUniCode) throws Exception;

    Integer getPrizeCountByPeoUniCode(@Param("peoUniCode") String peoUniCode) throws Exception;

    List<String> getPeoUniCodeByPeoUniCodeListAndDirectionList(@Param("peoUniCodeList") List<String> peoUniCodeList, @Param("directionList") List<String> directionList) throws Exception;

    List<String> getRankByPeoUniCode(@Param("peoUniCodeList") List<String> peoUniCodeList, @Param("offset") Integer offset, @Param("limit") Integer limit) throws Exception;

    List<AnalystHonorModel> selectHonor(Map map);

}