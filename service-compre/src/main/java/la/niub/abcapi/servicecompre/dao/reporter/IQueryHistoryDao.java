package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.QueryHistoryModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IQueryHistoryDao {
    int deleteLog(@Param("user_id") String user_id, @Param("ids") List<Long> ids);

    int insertLog(QueryHistoryModel record);

    List<QueryHistoryModel> selectByPages(@Param("user_id") String uid,
                                    @Param("pages") List<String> pages,
                                    @Param("limit") Integer limit,
                                    @Param("last_id") Long lastId,
                                    @Param("keyword") String keyword);


}