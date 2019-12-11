package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface INtfJzInvesteventDetailDao {
    List<String> getInvstNameListByEventId(@Param("eventId") Integer eventId) throws Exception;
}
