package la.niub.abcapi.servicecompre.model.response;

import java.util.List;
import java.util.Map;

public class FundCompanyKpiResponse {

    private Long comId;

    private String org_name;

    private String org_sname;

    private Map<Long, List<Map<Integer, FundCompanyKpiItemResponse>>> info;


    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getOrg_sname() {
        return org_sname;
    }

    public void setOrg_sname(String org_sname) {
        this.org_sname = org_sname;
    }

    public Map<Long, List<Map<Integer, FundCompanyKpiItemResponse>>> getInfo() {
        return info;
    }

    public void setInfo(Map<Long, List<Map<Integer, FundCompanyKpiItemResponse>>> info) {
        this.info = info;
    }
}
