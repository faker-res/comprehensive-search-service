package la.niub.abcapi.servicecompre.model.dao;

public class AnalystOverviewDaoModel {
    private int id;

    private String orgId;

    private int securitiesAnalyst;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public int getSecuritiesAnalyst() {
        return securitiesAnalyst;
    }

    public void setSecuritiesAnalyst(int securitiesAnalyst) {
        this.securitiesAnalyst = securitiesAnalyst;
    }
}
