package la.niub.abcapi.servicecompre.model.bo.theme;

import java.math.BigDecimal;

public class ThermodynamicChartBO {

    BigDecimal bankroll_flow;

    BigDecimal indu_performance;

    Integer report_count;

    String stock_code;

    String stock_name;

    Long sec_uni_code;

    public BigDecimal getBankroll_flow() {
        return bankroll_flow;
    }

    public void setBankroll_flow(BigDecimal bankroll_flow) {
        this.bankroll_flow = bankroll_flow;
    }

    public BigDecimal getIndu_performance() {
        return indu_performance;
    }

    public void setIndu_performance(BigDecimal indu_performance) {
        this.indu_performance = indu_performance;
    }

    public Integer getReport_count() {
        return report_count;
    }

    public void setReport_count(Integer report_count) {
        this.report_count = report_count;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }
}
