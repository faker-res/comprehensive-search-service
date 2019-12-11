package la.niub.abcapi.servicecompre.model.request.fund;

public class FundOtherRequest {

    private String code;

    private Long sec_uni_code;

    private String start_time;

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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    @Override
    public String toString() {
        return "FundOtherRequest{" +
                "code='" + code + '\'' +
                ", sec_uni_code=" + sec_uni_code +
                ", start_time='" + start_time + '\'' +
                '}';
    }
}
