package la.niub.abcapi.servicecompre.model.request;

public class FundManagerFundChartRequest {
    private Long peo_uni_code;
    private String sec_uni_codes;
    private String year;
    private String time;

    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public String getSec_uni_codes() {
        return sec_uni_codes;
    }

    public void setSec_uni_codes(String sec_uni_codes) {
        this.sec_uni_codes = sec_uni_codes;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
