package la.niub.abcapi.servicecompre.model.request.publiccompany;

public class PublicCompanyFundAnalystRequest {

    private Long index_uni_code;

    private Integer limit = 30;

    public Long getIndex_uni_code() {
        return index_uni_code;
    }

    public void setIndex_uni_code(Long index_uni_code) {
        this.index_uni_code = index_uni_code;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PublicCompanyFundAnalystRequest{" +
                "index_uni_code=" + index_uni_code +
                ", limit=" + limit +
                '}';
    }
}
