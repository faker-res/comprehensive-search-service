package la.niub.abcapi.servicecompre.model.request;

public class FundCompanyManagerRequest {
    private Long comId;

    private int limit;

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
