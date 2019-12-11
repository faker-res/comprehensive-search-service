package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ReportShareUserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IReportDao {

    /**
     * 获取分享用户
     * @param ids
     * @return
     */
    List<ReportShareUserModel> getShareUser(@Param("ids") List<String> ids);
}
