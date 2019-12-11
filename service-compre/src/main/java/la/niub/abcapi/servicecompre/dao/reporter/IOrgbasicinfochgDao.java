package la.niub.abcapi.servicecompre.dao.reporter;

import la.niub.abcapi.servicecompre.model.OrgbasicinfochgModel;

import java.util.List;
import java.util.Map;

public interface IOrgbasicinfochgDao {
    public int insert(OrgbasicinfochgModel org_basic_info_chg);
    public int delete(OrgbasicinfochgModel org_basic_info_chg);
    public int update(OrgbasicinfochgModel org_basic_info_chg);
    public List<OrgbasicinfochgModel> find(Map paramMap);
    public int getCount(Map paramMap);
}
