package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundManagerPerfChgModel implements Serializable {
    private Long id;

    private Long sec_uni_code;

    private String fund_sname;

    private Date trade_date;

    private Long peo_uni_code;

    private String fund_manager_name;

    private Date begin_date;

    private Date end_date;

    private BigDecimal nav_growth_rate;

    private BigDecimal benchmark_growth_rate;

    private BigDecimal annual_yield;

    private BigDecimal benchmark_annual_yield;

    private BigDecimal alpha;

    private BigDecimal ir;

    private BigDecimal ir_pct;

    private BigDecimal sharpe_ratio;

    private BigDecimal treynor_ratio;

    private BigDecimal jenson_ratio;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public FundManagerPerfChgModel() {
    }

    public FundManagerPerfChgModel(Long id, Long sec_uni_code, String fund_sname, Date trade_date, Long peo_uni_code, String fund_manager_name, Date begin_date, Date end_date, BigDecimal nav_growth_rate, BigDecimal benchmark_growth_rate, BigDecimal annual_yield, BigDecimal benchmark_annual_yield, BigDecimal alpha, BigDecimal ir, BigDecimal ir_pct, BigDecimal sharpe_ratio, BigDecimal treynor_ratio, BigDecimal jenson_ratio, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.fund_sname = fund_sname;
        this.trade_date = trade_date;
        this.peo_uni_code = peo_uni_code;
        this.fund_manager_name = fund_manager_name;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.nav_growth_rate = nav_growth_rate;
        this.benchmark_growth_rate = benchmark_growth_rate;
        this.annual_yield = annual_yield;
        this.benchmark_annual_yield = benchmark_annual_yield;
        this.alpha = alpha;
        this.ir = ir;
        this.ir_pct = ir_pct;
        this.sharpe_ratio = sharpe_ratio;
        this.treynor_ratio = treynor_ratio;
        this.jenson_ratio = jenson_ratio;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public String getFund_sname() {
        return fund_sname;
    }

    public void setFund_sname(String fund_sname) {
        this.fund_sname = fund_sname;
    }

    public Date getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date = trade_date;
    }

    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public String getFund_manager_name() {
        return fund_manager_name;
    }

    public void setFund_manager_name(String fund_manager_name) {
        this.fund_manager_name = fund_manager_name;
    }

    public Date getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public BigDecimal getNav_growth_rate() {
        return nav_growth_rate;
    }

    public void setNav_growth_rate(BigDecimal nav_growth_rate) {
        this.nav_growth_rate = nav_growth_rate;
    }

    public BigDecimal getBenchmark_growth_rate() {
        return benchmark_growth_rate;
    }

    public void setBenchmark_growth_rate(BigDecimal benchmark_growth_rate) {
        this.benchmark_growth_rate = benchmark_growth_rate;
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

    public BigDecimal getSharpe_ratio() {
        return sharpe_ratio;
    }

    public void setSharpe_ratio(BigDecimal sharpe_ratio) {
        this.sharpe_ratio = sharpe_ratio;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
}