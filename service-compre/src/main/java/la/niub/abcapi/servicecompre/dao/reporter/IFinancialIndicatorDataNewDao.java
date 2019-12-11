package la.niub.abcapi.servicecompre.dao.reporter;

import org.apache.ibatis.annotations.Param;

public interface IFinancialIndicatorDataNewDao {
    /**
     * 根据data_id获取金融指标数据
     *
     * @param data_id
     * @return
     */
    String getIndicatorValue(@Param("data_id") String data_id);
}