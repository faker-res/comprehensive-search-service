package la.niub.abcapi.servicecompre.model.response;

import java.math.BigDecimal;
import java.util.Date;

public class FundManagerCompetitiveStrengthAnalysisItemResponse {
    private String sec_code;
    private BigDecimal annual_yield;
    private BigDecimal sharpe_ratio;
    private BigDecimal ir;
    private BigDecimal treynor_ratio;
    private BigDecimal jenson_ratio;
    private BigDecimal alpha;
    private Long date;

    public String getSec_code() {
        return sec_code;
    }

    public void setSec_code(String sec_code) {
        this.sec_code = sec_code;
    }

    public BigDecimal getAnnual_yield() {
        return annual_yield;
    }

    public void setAnnual_yield(BigDecimal annual_yield) {
        this.annual_yield = annual_yield;
    }

    public BigDecimal getSharpe_ratio() {
        return sharpe_ratio;
    }

    public void setSharpe_ratio(BigDecimal sharpe_ratio) {
        this.sharpe_ratio = sharpe_ratio;
    }

    public BigDecimal getIr() {
        return ir;
    }

    public void setIr(BigDecimal ir) {
        this.ir = ir;
    }

    public BigDecimal getTreynor_ratio() {
        return treynor_ratio;
    }

    public void setTreynor_ratio(BigDecimal treynor_ratio) {
        this.treynor_ratio = treynor_ratio;
    }

    public BigDecimal getJenson_ratio() {
        return jenson_ratio;
    }

    public void setJenson_ratio(BigDecimal jenson_ratio) {
        this.jenson_ratio = jenson_ratio;
    }

    public BigDecimal getAlpha() {
        return alpha;
    }

    public void setAlpha(BigDecimal alpha) {
        this.alpha = alpha;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
