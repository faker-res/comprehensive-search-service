package la.niub.abcapi.servicecompre.model.request.fund;

public class FundManagerRequest {

    private String code;

    private Long sec_uni_code;

    private Integer limit = 2;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "FundManagerRequest{" +
                "code='" + code + '\'' +
                ", sec_uni_code=" + sec_uni_code +
                ", limit=" + limit +
                '}';
    }
}
