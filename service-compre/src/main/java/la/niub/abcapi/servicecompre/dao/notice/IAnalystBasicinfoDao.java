package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.AnalystBasicinfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface IAnalystBasicinfoDao {

    int deleteByPrimaryKey(Long id);

    int insert(AnalystBasicinfoModel record);

    int insertSelective(AnalystBasicinfoModel record);

    AnalystBasicinfoModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AnalystBasicinfoModel record);

    int updateByPrimaryKey(AnalystBasicinfoModel record);

    AnalystBasicinfoModel selectByPeoUniCode(String peo_uni_code);

    /**
     * 同领域分析师新财富名次排行
     * @param direction
     * @return
     */
    List<AnalystBasicinfoModel> getAnalystByDirection(String direction);

    /**
     * 批量获取多个分析师的信息
     * @param peoUniCodes
     * @return
     */
    List<AnalystBasicinfoModel> selectByPeoUniCodeList(@Param("peoUniCodes") List<String> peoUniCodes);

    /**
     * 找出分析师列表中同领域的
     * @param peoUniCodes
     * @return
     */
    List<AnalystBasicinfoModel> selectByPeoUniCodesAndDirection(@Param("peoUniCodes") List<String> peoUniCodes, @Param("direction") String direction);

    /**
     * 同领域分析师
     * @param direction
     * @return
     */
    List<AnalystBasicinfoModel> getAnalystByDirectionLimit(@Param("direction") String direction, @Param("limit") Integer limit);

    /**
     * 找出分析师列表中同领域的
     * @param peoUniCodes
     * @return
     */
    List<AnalystBasicinfoModel> selectByPeoUniCodesAndDirectionLimit(@Param("peoUniCodes") List<String> peoUniCodes, @Param("directionList") List<String> directionList, @Param("limit") Integer limit);

    String selectUniquePeoUniCodeByNameAndPeoUniCodeList(@Param("peo_uni_code_list") List<String> peo_uni_code_list, @Param("name") String name);

    List<String> selectAnalystNameByPeoUniCodeList(@Param("peo_uni_code_list") List<String> peo_uni_code_list);

    List<String> selectPeoUniCodeByNameListAndPeoUniCodeList(@Param("peo_uni_code_list") List<String> peo_uni_code_list, @Param("name_list") List<String> name_list);

    List<String> getAllAnalystName() throws Exception;

    List<Map<String, Object>> getPeoUniCodeByNameAndSpellAndOrgSnameAndDirectionListAndPrior(@Param("name") String name,
                                                                     @Param("spellPrefix") String spellPrefix,
                                                                     @Param("org_sname") String org_sname,
                                                                     @Param("directionList") List<String> directionList,
                                                                     @Param("offset") Integer offset,
                                                                     @Param("limit") Integer limit,
                                                                     @Param("prior") String prior) throws Exception;

    List<Map<String, Object>> getPeoUniCodeByNameAndSpellAndOrgSnameAndDirectionList(@Param("name") String name,
                                                                                     @Param("spellPrefix") String spellPrefix,
                                                                                     @Param("org_sname") String org_sname,
                                                                                     @Param("directionList") List<String> directionList) throws Exception;

    List<String> getPeoUniCodeByNameAndSpellAndPeoUniCodeList(@Param("name") String name,
                                                              @Param("spellPrefix") String spellPrefix,
                                                              @Param("peoUniCodeList") List<String> peoUniCodeList) throws Exception;

    List<String> getRankByPriorIsPrizeCount(@Param("peoUniCodeList") List<String> peoUniCodeList, @Param("offset") Integer offset, @Param("limit") Integer limit) throws Exception;

    List<String> getRankByPriorIsRanking(@Param("peoUniCodeList") List<String> peoUniCodeList, @Param("offset") Integer offset, @Param("limit") Integer limit) throws Exception;

    List<String> getRankByPriorIsDays(@Param("peoUniCodeList") List<String> peoUniCodeList, @Param("offset") Integer offset, @Param("limit") Integer limit) throws Exception;

    List<Map<String, Object>> getAnalystInfoByPeoUniCodeList(@Param("peoUniCodeList") List<String> peoUniCodeList,
                                                             @Param("prior") String prior) throws Exception;

    List<String> getPeoUniCodeByPeoUniCodeListAndDirections(@Param("peoUniCodeList") List<String> peoUniCodeList,
                                                            @Param("direction") String direction) throws Exception;

    List<AnalystBasicinfoModel> selectByPeoUniCodesAndDirectionAndLimit(@Param("peoUniCodes") List<String> peoUniCodes, @Param("direction") String direction, @Param("limit") Integer limit);




}