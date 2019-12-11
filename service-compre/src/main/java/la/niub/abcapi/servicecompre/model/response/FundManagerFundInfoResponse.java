package la.niub.abcapi.servicecompre.model.response;

import java.math.BigDecimal;

public class FundManagerFundInfoResponse {
    private String key;
    private String abc_code;
    private Long sec_uni_code;
    private String sec_code;
    private String sec_name;
    private String plate_name;
    private BigDecimal tot_fund_nav;
    private String fund_manager_name;
    private Long  begin_date;
    private Long end_date;
    private BigDecimal annual_yield;
    private BigDecimal benchmark_annual_yield;
    private BigDecimal alpha;
    private BigDecimal ir;
    private BigDecimal ir_pct;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAbc_code() {
        return abc_code;
    }

    public void setAbc_code(String abc_code) {
        this.abc_code = abc_code;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public String getSec_code() {
        return sec_code;
    }

    public void setSec_code(String sec_code) {
        this.sec_code = sec_code;
    }

    public String getSec_name() {
        return sec_name;
    }

    public void setSec_name(String sec_name) {
        this.sec_name = sec_name;
    }

    public String getPlate_name() {
        return plate_name;
    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name;
    }

    public BigDecimal getTot_fund_nav() {
        return tot_fund_nav;
    }

    public void setTot_fund_nav(BigDecimal tot_fund_nav) {
        this.tot_fund_nav = tot_fund_nav;
    }

    public String getFund_manager_name() {
        return fund_manager_name;
    }

    public void setFund_manager_name(String fund_manager_name) {
        this.fund_manager_name = fund_manager_name;
    }

    public Long getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Long begin_date) {
        this.begin_date = begin_date;
    }

    public Long getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Long end_date) {
        this.end_date = end_date;
    }

    public BigDecimal getAnnual_yield() {
        return annual_yield;
    }

    public void setAnnual_yield(BigDecimal annual_yield) {
        this.annual_yield = annual_yield;
    }

    public BigDecimal getBenchmark_annual_yield() {
        return benchmark_annual_yield;
    }

    public void setBenchmark_annual_yield(BigDecimal benchmark_annual_yield) {
        this.benchmark_annual_yield = benchmark_annual_yield;
    }

    public BigDecimal getAlpha() {
        return alpha;
    }

    public void setAlpha(BigDecimal alpha) {
        this.alpha = alpha;
    }

    public BigDecimal getIr() {
        return ir;
    }

    public void setIr(BigDecimal ir) {
        this.ir = ir;
    }

    public BigDecimal getIr_pct() {
        return ir_pct;
    }

    public void setIr_pct(BigDecimal ir_pct) {
        this.ir_pct = ir_pct;
    }
}
