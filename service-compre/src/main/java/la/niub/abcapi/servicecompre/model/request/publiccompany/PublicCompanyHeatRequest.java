package la.niub.abcapi.servicecompre.model.request.publiccompany;

import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;

public class PublicCompanyHeatRequest {

    private Long index_uni_code;

    private String period = FundPeriodEnum.D1.name();

    private String type;

    private Integer limit = 30;

    public Long getIndex_uni_code() {
        return index_uni_code;
    }

    public void setIndex_uni_code(Long index_uni_code) {
        this.index_uni_code = index_uni_code;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PublicCompanyHeatRequest{" +
                "index_uni_code=" + index_uni_code +
                ", period='" + period + '\'' +
                ", type='" + type + '\'' +
                ", limit=" + limit +
                '}';
    }
}
