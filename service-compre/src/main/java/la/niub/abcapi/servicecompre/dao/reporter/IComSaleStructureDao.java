package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.ComSaleStructureModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IComSaleStructureDao {

    List<ComSaleStructureModel> getTopReportDate(@Param("comUniCode") Long comUniCode,
                                           @Param("limit") Integer limit);


    List<ComSaleStructureModel> getRecordsByDate(@Param("comUniCode") Long comUniCode,
                                                       @Param("endDate") Date endDate);



}