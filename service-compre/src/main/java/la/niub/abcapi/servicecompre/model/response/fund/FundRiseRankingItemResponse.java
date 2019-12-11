package la.niub.abcapi.servicecompre.model.response.fund;

public class FundRiseRankingItemResponse {
    private String code;
    private Long sec_uni_code;
    private String sec_name;
    private Double rise_rate;

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

    public String getSec_name() {
        return sec_name;
    }

    public void setSec_name(String sec_name) {
        this.sec_name = sec_name;
    }

    public Double getRise_rate() {
        return rise_rate;
    }

    public void setRise_rate(Double rise_rate) {
        this.rise_rate = rise_rate;
    }
}
