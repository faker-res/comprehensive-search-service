package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFundCompanyDao {

    List<String> getWechatSubscriptionNameByComUniCode(@Param("com_uni_code") Long com_uni_code);
}
