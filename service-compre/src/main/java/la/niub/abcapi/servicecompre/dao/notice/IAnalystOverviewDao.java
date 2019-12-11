package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.dao.AnalystOverviewDaoModel;
import org.apache.ibatis.annotations.Param;

public interface IAnalystOverviewDao {

    AnalystOverviewDaoModel selectByOrgId(@Param("orgUniCode") int orgId);

}
