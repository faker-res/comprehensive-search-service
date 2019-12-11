package la.niub.abcapi.servicecompre.model.request.analyst;

public class AnalystSameFieldRequest {

    private String peo_uni_code;

    private Integer limit = 5;

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "AnalystDynamicRequest{" +
                "peo_uni_code=" + peo_uni_code +
                ", limit=" + limit +
                '}';
    }
}
