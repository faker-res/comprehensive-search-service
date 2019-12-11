package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.VNewsHeatFundModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IVNewsHeatFundDao {
    int insert(VNewsHeatFundModel record);

    int insertSelective(VNewsHeatFundModel record);

    List<VNewsHeatFundModel> selectBySecUniCode(@Param("secUniCode") Long secUniCode, @Param("endDate") String endDate);
}