package la.niub.abcapi.servicecompre.service;

import java.util.List;
import java.util.Map;

/**
 * 用户相关
 */
public interface IUserService {

    /**
     * 根据用户ID，获取用户订阅股票的数据
     * @param uid
     * @return
     */
    Map<String,String> getUserStock(String uid);

    /**
     * 根据研报id获取分享着
     * @param reportIds
     * @return bool|array
     */
    Map<Integer,String> getUsersByReportIds(List<Integer> reportIds);
}
