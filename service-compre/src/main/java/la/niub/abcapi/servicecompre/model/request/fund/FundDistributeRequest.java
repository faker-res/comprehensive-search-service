package la.niub.abcapi.servicecompre.model.request.fund;

import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;

public class FundDistributeRequest {

    private String code;

    private Long sec_uni_code;

    private String period = FundPeriodEnum.M3.name();

    private Integer limit = 100;

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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "FundDistributeRequest{" +
                "code='" + code + '\'' +
                ", sec_uni_code=" + sec_uni_code +
                ", period='" + period + '\'' +
                ", limit=" + limit +
                '}';
    }
}
