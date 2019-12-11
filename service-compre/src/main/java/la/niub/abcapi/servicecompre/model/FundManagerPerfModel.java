package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundManagerPerfModel implements Serializable {
    private Long id;

    private Long sec_uni_code;

    private String fund_sname;

    private String fund_type;

    private Long peo_uni_code;

    private String fund_manager_name;

    private Long is_incumbent;

    private Date begin_date;

    private Date end_date;

    private BigDecimal nav_growth_rate;

    private BigDecimal benchmark_growth_rate;

    private BigDecimal return_rate;

    private BigDecimal annual_yield;

    private BigDecimal benchmark_annual_yield;

    private String all_rank;

    private BigDecimal all_avg_yield;

    private String class_rank;

    private Long class_type;

    private BigDecimal rank_type_avg_yield;

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

    public FundManagerPerfModel(Long id, Long sec_uni_code, String fund_sname, String fund_type, Long peo_uni_code, String fund_manager_name, Long is_incumbent, Date begin_date, Date end_date, BigDecimal nav_growth_rate, BigDecimal benchmark_growth_rate, BigDecimal return_rate, BigDecimal annual_yield, BigDecimal benchmark_annual_yield, String all_rank, BigDecimal all_avg_yield, String class_rank, Long class_type, BigDecimal rank_type_avg_yield, BigDecimal alpha, BigDecimal ir, BigDecimal ir_pct, BigDecimal sharpe_ratio, BigDecimal treynor_ratio, BigDecimal jenson_ratio, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.fund_sname = fund_sname;
        this.fund_type = fund_type;
        this.peo_uni_code = peo_uni_code;
        this.fund_manager_name = fund_manager_name;
        this.is_incumbent = is_incumbent;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.nav_growth_rate = nav_growth_rate;
        this.benchmark_growth_rate = benchmark_growth_rate;
        this.return_rate = return_rate;
        this.annual_yield = annual_yield;
        this.benchmark_annual_yield = benchmark_annual_yield;
        this.all_rank = all_rank;
        this.all_avg_yield = all_avg_yield;
        this.class_rank = class_rank;
        this.class_type = class_type;
        this.rank_type_avg_yield = rank_type_avg_yield;
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

    public FundManagerPerfModel() {
        super();
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
        this.fund_sname = fund_sname == null ? null : fund_sname.trim();
    }

    public String getFund_type() {
        return fund_type;
    }

    public void setFund_type(String fund_type) {
        this.fund_type = fund_type == null ? null : fund_type.trim();
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
        this.fund_manager_name = fund_manager_name == null ? null : fund_manager_name.trim();
    }

    public Long getIs_incumbent() {
        return is_incumbent;
    }

    public void setIs_incumbent(Long is_incumbent) {
        this.is_incumbent = is_incumbent;
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

    public BigDecimal getReturn_rate() {
        return return_rate;
    }

    public void setReturn_rate(BigDecimal return_rate) {
        this.return_rate = return_rate;
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

    public String getAll_rank() {
        return all_rank;
    }

    public void setAll_rank(String all_rank) {
        this.all_rank = all_rank == null ? null : all_rank.trim();
    }

    public BigDecimal getAll_avg_yield() {
        return all_avg_yield;
    }

    public void setAll_avg_yield(BigDecimal all_avg_yield) {
        this.all_avg_yield = all_avg_yield;
    }

    public String getClass_rank() {
        return class_rank;
    }

    public void setClass_rank(String class_rank) {
        this.class_rank = class_rank == null ? null : class_rank.trim();
    }

    public Long getClass_type() {
        return class_type;
    }

    public void setClass_type(Long class_type) {
        this.class_type = class_type;
    }

    public BigDecimal getRank_type_avg_yield() {
        return rank_type_avg_yield;
    }

    public void setRank_type_avg_yield(BigDecimal rank_type_avg_yield) {
        this.rank_type_avg_yield = rank_type_avg_yield;
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
        this.creator = creator == null ? null : creator.trim();
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sec_uni_code=").append(sec_uni_code);
        sb.append(", fund_sname=").append(fund_sname);
        sb.append(", fund_type=").append(fund_type);
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", fund_manager_name=").append(fund_manager_name);
        sb.append(", is_incumbent=").append(is_incumbent);
        sb.append(", begin_date=").append(begin_date);
        sb.append(", end_date=").append(end_date);
        sb.append(", nav_growth_rate=").append(nav_growth_rate);
        sb.append(", benchmark_growth_rate=").append(benchmark_growth_rate);
        sb.append(", return_rate=").append(return_rate);
        sb.append(", annual_yield=").append(annual_yield);
        sb.append(", benchmark_annual_yield=").append(benchmark_annual_yield);
        sb.append(", all_rank=").append(all_rank);
        sb.append(", all_avg_yield=").append(all_avg_yield);
        sb.append(", class_rank=").append(class_rank);
        sb.append(", class_type=").append(class_type);
        sb.append(", rank_type_avg_yield=").append(rank_type_avg_yield);
        sb.append(", alpha=").append(alpha);
        sb.append(", ir=").append(ir);
        sb.append(", ir_pct=").append(ir_pct);
        sb.append(", sharpe_ratio=").append(sharpe_ratio);
        sb.append(", treynor_ratio=").append(treynor_ratio);
        sb.append(", jenson_ratio=").append(jenson_ratio);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}