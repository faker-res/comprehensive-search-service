package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import la.niub.abcapi.servicecompre.model.KeyWordQueryModel;
import la.niub.abcapi.servicecompre.model.CateCategoriesModel;
import la.niub.abcapi.servicecompre.model.FreqTablesDetailsModel;
import java.util.List;

@Mapper
public interface IOperateConfigDao {

    /**
     * 获取预置热词
     *
     * @param
     * @return
     */
    List<KeyWordQueryModel> queryWithKeyWordpresetQuery(@Param("module") String module,
                                                        @Param("terminal") Integer terminal,
                                                        @Param("lan") Integer lan,
                                                        @Param("limit") Integer limit);

    /**
     * 获取热门搜索
     *
     * @param
     * @return
     */
    List<KeyWordQueryModel> queryWithKeyWordhotSearch(@Param("module") String module,
                                                      @Param("terminal") Integer terminal,
                                                      @Param("lan") Integer lan,
                                                      @Param("limit") Integer limit);


    /**
     * 获取热门推荐运营位
     *
     * @param
     * @return
     */
    List<KeyWordQueryModel> queryWithKeyWordpromoteWords(@Param("module") String module,
                                                         @Param("terminal") Integer terminal,
                                                         @Param("lan") Integer lan,
                                                         @Param("limit") Integer limit);


    /**
     * 数据图运营位配置,从reporter的op_Topic表查
     *
     * @param
     * @return
     */
    List<Object> getChartConfigFromOpTopic(@Param("module") String module,
                                           @Param("terminal") Integer terminal,
                                           @Param("lan") Integer lan,
                                           @Param("limit") Integer limit);


    /**
     * 数据图运营位配置,从reporter的op_selectedIMG表查
     *
     * @param
     * @return
     */
    List<Object> getChartConfigFromSelectedIMG(@Param("module") String module,
                                               @Param("terminal") Integer terminal,
                                               @Param("lan") Integer lan,
                                               @Param("limit") Integer limit);

    /**
     * 数据图运营位配置,从reporter的op_selectedIMG表查
     *
     * @param
     * @return
     */
    Object getChartConfigFromSelectedIMGONE(@Param("id") String id);


    /**
     * 获取最常使用的表
     *
     * @param
     * @return
     */
    List<String> getTabIdsFromFreqSearchTables(@Param("limit") Integer limit);


    /**
     * 获取最常表的基本信息
     *
     * @param
     * @return
     */
    List<Object> getTablesDetails(@Param("tab_IDs") List<String> tab_IDs);


    /**
     * 获取主要分类
     *
     * @param
     * @return
     */
    List<CateCategoriesModel> getCateCategories(@Param("limit") Integer limit);


    /**
     * 获取其他分类
     *
     * @param
     * @return
     */
    List<Object> getOtherCategories(@Param("limit") Integer limit);


    /**
     * 获取其他分类
     *
     * @param
     * @return
     */
    List<Object> getTablesFromCategoryCode(@Param("categoryCode") String categoryCode);


    /**
     * 获取其他分类
     *
     * @param
     * @return
     */
    List<Object> getOtherCategoryCodeFromTable();


    /**
     * 数据表类型输入框检索
     *
     * @param
     * @return
     */
    List<FreqTablesDetailsModel> getTablesByKeyword(@Param("keyword") String keyword,
                                                    @Param("limit") Integer limit);

}
