package la.niub.abcapi.servicecompre.dao.notice;

import la.niub.abcapi.servicecompre.model.SeccomActivities;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ISeccomActivitiesDao {
    List<SeccomActivities> getActivitiesByTimeAndOrgId(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                                       @Param("orgName") String orgName);
}
