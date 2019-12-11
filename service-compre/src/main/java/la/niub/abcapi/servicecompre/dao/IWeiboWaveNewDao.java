package la.niub.abcapi.servicecompre.dao;

import la.niub.abcapi.servicecompre.model.nosql.WeiboWaveNewModel;

import java.util.Date;
import java.util.List;

public interface IWeiboWaveNewDao {

    List<WeiboWaveNewModel> getByKey(String keyword, Integer limit);

    List<WeiboWaveNewModel> getByKeyAndDate(String name, Date startDate);
}
