package la.niub.abcapi.servicecompre.dao.marketprice;

import la.niub.abcapi.servicecompre.model.SecPrice1MinModel;

public interface ISecPrice1MinDao {
    int deleteByPrimaryKey(Long id);

    int insert(SecPrice1MinModel record);

    int insertSelective(SecPrice1MinModel record);

    SecPrice1MinModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecPrice1MinModel record);

    int updateByPrimaryKey(SecPrice1MinModel record);
}