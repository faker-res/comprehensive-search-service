package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.FundNavCurModel;

public interface IFundNavCurDao {
    int deleteByPrimaryKey(Long id);

    int insert(FundNavCurModel record);

    int insertSelective(FundNavCurModel record);

    FundNavCurModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FundNavCurModel record);

    int updateByPrimaryKey(FundNavCurModel record);

    FundNavCurModel selectBySecUniCode(Long sec_uni_code);
}