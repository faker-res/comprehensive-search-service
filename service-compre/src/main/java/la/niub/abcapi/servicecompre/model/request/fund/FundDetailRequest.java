package la.niub.abcapi.servicecompre.model.request.fund;

public class FundDetailRequest {

    private String code;

    private Long sec_uni_code;

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

    @Override
    public String toString() {
        return "FundDetailRequest{" +
                "code='" + code + '\'' +
                ", sec_uni_code='" + sec_uni_code + '\'' +
                '}';
    }
}
