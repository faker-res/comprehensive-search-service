package la.niub.abcapi.servicecompre.dao.notice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IOperateConfigFromNoticeDao {

    /**
     * 数据图运营位配置,从notice的subject_tag表查
     * @param
     * @return
     */
    List<Object> getChartConfigFromSubjectTag(@Param("module") String module,
                                              @Param("terminal") Integer terminal,
                                              @Param("lan") Integer lan,
                                              @Param("limit") Integer limit);
}