package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.SecPriceWeekModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper
public interface ISecPriceWeekDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SecPriceWeekModel record);

    int insertSelective(SecPriceWeekModel record);

    SecPriceWeekModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecPriceWeekModel record);

    int updateByPrimaryKey(SecPriceWeekModel record);
}