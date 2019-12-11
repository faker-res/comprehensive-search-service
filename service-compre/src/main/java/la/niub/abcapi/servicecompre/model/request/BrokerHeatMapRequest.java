package la.niub.abcapi.servicecompre.model.request;

public class BrokerHeatMapRequest {

    private int orgId;

    private int type = 1;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
