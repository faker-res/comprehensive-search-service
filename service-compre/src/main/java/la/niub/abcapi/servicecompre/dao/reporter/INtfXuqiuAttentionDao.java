package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.NtfXuqiuAttentionModel;

import java.util.List;

public interface INtfXuqiuAttentionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(NtfXuqiuAttentionModel record);

    int insertSelective(NtfXuqiuAttentionModel record);

    NtfXuqiuAttentionModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NtfXuqiuAttentionModel record);

    int updateByPrimaryKey(NtfXuqiuAttentionModel record);

    List<NtfXuqiuAttentionModel> buildRecordsByKyeword(String stockCode);
}