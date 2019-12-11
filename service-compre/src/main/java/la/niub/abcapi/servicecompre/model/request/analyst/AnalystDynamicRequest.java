package la.niub.abcapi.servicecompre.model.request.analyst;

import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;

public class AnalystDynamicRequest {

    private String peo_uni_code;

    private String period = FundPeriodEnum.M3.name();

    private Integer limit = 15;

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
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
        return "AnalystDynamicRequest{" +
                "peo_uni_code=" + peo_uni_code +
                ", period='" + period + '\'' +
                ", limit=" + limit +
                '}';
    }
}
