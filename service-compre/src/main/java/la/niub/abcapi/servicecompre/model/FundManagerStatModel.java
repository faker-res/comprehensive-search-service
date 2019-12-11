package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundManagerStatModel implements Serializable {
    private Long id;

    private Long peo_uni_code;

    private String fund_manager_name;

    private BigDecimal fund_manager_tot_days;

    private Date fund_manager_beg_date;

    private Integer fund_count;

    private Integer comp_count;

    private BigDecimal fund_manager_avg_days;

    private Long present_comp_code;

    private String present_comp_name;

    private Integer present_comp_days;

    private Date present_comp_beg_date;

    private Integer present_comp_fund_count;

    private BigDecimal job_hopping_freq;

    private BigDecimal fund_manage_nav;

    private BigDecimal tenure_avg_annual_yield;

    private BigDecimal avg_ir;

    private BigDecimal avg_ir_pct;

    private BigDecimal tenure_avg_annual_yield_index;

    private BigDecimal hs_300_index_yield;

    private BigDecimal csi_universal_index_yield;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public FundManagerStatModel(Long id, Long peo_uni_code, String fund_manager_name, BigDecimal fund_manager_tot_days, Date fund_manager_beg_date, Integer fund_count, Integer comp_count, BigDecimal fund_manager_avg_days, Long present_comp_code, String present_comp_name, Integer present_comp_days, Date present_comp_beg_date, Integer present_comp_fund_count, BigDecimal job_hopping_freq, BigDecimal fund_manage_nav, BigDecimal tenure_avg_annual_yield, BigDecimal avg_ir, BigDecimal avg_ir_pct, BigDecimal tenure_avg_annual_yield_index, BigDecimal hs_300_index_yield, BigDecimal csi_universal_index_yield, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.peo_uni_code = peo_uni_code;
        this.fund_manager_name = fund_manager_name;
        this.fund_manager_tot_days = fund_manager_tot_days;
        this.fund_manager_beg_date = fund_manager_beg_date;
        this.fund_count = fund_count;
        this.comp_count = comp_count;
        this.fund_manager_avg_days = fund_manager_avg_days;
        this.present_comp_code = present_comp_code;
        this.present_comp_name = present_comp_name;
        this.present_comp_days = present_comp_days;
        this.present_comp_beg_date = present_comp_beg_date;
        this.present_comp_fund_count = present_comp_fund_count;
        this.job_hopping_freq = job_hopping_freq;
        this.fund_manage_nav = fund_manage_nav;
        this.tenure_avg_annual_yield = tenure_avg_annual_yield;
        this.avg_ir = avg_ir;
        this.avg_ir_pct = avg_ir_pct;
        this.tenure_avg_annual_yield_index = tenure_avg_annual_yield_index;
        this.hs_300_index_yield = hs_300_index_yield;
        this.csi_universal_index_yield = csi_universal_index_yield;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public FundManagerStatModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getFund_manager_tot_days() {
        return fund_manager_tot_days;
    }

    public void setFund_manager_tot_days(BigDecimal fund_manager_tot_days) {
        this.fund_manager_tot_days = fund_manager_tot_days;
    }

    public Date getFund_manager_beg_date() {
        return fund_manager_beg_date;
    }

    public void setFund_manager_beg_date(Date fund_manager_beg_date) {
        this.fund_manager_beg_date = fund_manager_beg_date;
    }

    public Integer getFund_count() {
        return fund_count;
    }

    public void setFund_count(Integer fund_count) {
        this.fund_count = fund_count;
    }

    public Integer getComp_count() {
        return comp_count;
    }

    public void setComp_count(Integer comp_count) {
        this.comp_count = comp_count;
    }

    public BigDecimal getFund_manager_avg_days() {
        return fund_manager_avg_days;
    }

    public void setFund_manager_avg_days(BigDecimal fund_manager_avg_days) {
        this.fund_manager_avg_days = fund_manager_avg_days;
    }

    public Long getPresent_comp_code() {
        return present_comp_code;
    }

    public void setPresent_comp_code(Long present_comp_code) {
        this.present_comp_code = present_comp_code;
    }

    public String getPresent_comp_name() {
        return present_comp_name;
    }

    public void setPresent_comp_name(String present_comp_name) {
        this.present_comp_name = present_comp_name == null ? null : present_comp_name.trim();
    }

    public Integer getPresent_comp_days() {
        return present_comp_days;
    }

    public void setPresent_comp_days(Integer present_comp_days) {
        this.present_comp_days = present_comp_days;
    }

    public Date getPresent_comp_beg_date() {
        return present_comp_beg_date;
    }

    public void setPresent_comp_beg_date(Date present_comp_beg_date) {
        this.present_comp_beg_date = present_comp_beg_date;
    }

    public Integer getPresent_comp_fund_count() {
        return present_comp_fund_count;
    }

    public void setPresent_comp_fund_count(Integer present_comp_fund_count) {
        this.present_comp_fund_count = present_comp_fund_count;
    }

    public BigDecimal getJob_hopping_freq() {
        return job_hopping_freq;
    }

    public void setJob_hopping_freq(BigDecimal job_hopping_freq) {
        this.job_hopping_freq = job_hopping_freq;
    }

    public BigDecimal getFund_manage_nav() {
        return fund_manage_nav;
    }

    public void setFund_manage_nav(BigDecimal fund_manage_nav) {
        this.fund_manage_nav = fund_manage_nav;
    }

    public BigDecimal getTenure_avg_annual_yield() {
        return tenure_avg_annual_yield;
    }

    public void setTenure_avg_annual_yield(BigDecimal tenure_avg_annual_yield) {
        this.tenure_avg_annual_yield = tenure_avg_annual_yield;
    }

    public BigDecimal getAvg_ir() {
        return avg_ir;
    }

    public void setAvg_ir(BigDecimal avg_ir) {
        this.avg_ir = avg_ir;
    }

    public BigDecimal getAvg_ir_pct() {
        return avg_ir_pct;
    }

    public void setAvg_ir_pct(BigDecimal avg_ir_pct) {
        this.avg_ir_pct = avg_ir_pct;
    }

    public BigDecimal getTenure_avg_annual_yield_index() {
        return tenure_avg_annual_yield_index;
    }

    public void setTenure_avg_annual_yield_index(BigDecimal tenure_avg_annual_yield_index) {
        this.tenure_avg_annual_yield_index = tenure_avg_annual_yield_index;
    }

    public BigDecimal getHs_300_index_yield() {
        return hs_300_index_yield;
    }

    public void setHs_300_index_yield(BigDecimal hs_300_index_yield) {
        this.hs_300_index_yield = hs_300_index_yield;
    }

    public BigDecimal getCsi_universal_index_yield() {
        return csi_universal_index_yield;
    }

    public void setCsi_universal_index_yield(BigDecimal csi_universal_index_yield) {
        this.csi_universal_index_yield = csi_universal_index_yield;
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
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", fund_manager_name=").append(fund_manager_name);
        sb.append(", fund_manager_tot_days=").append(fund_manager_tot_days);
        sb.append(", fund_manager_beg_date=").append(fund_manager_beg_date);
        sb.append(", fund_count=").append(fund_count);
        sb.append(", comp_count=").append(comp_count);
        sb.append(", fund_manager_avg_days=").append(fund_manager_avg_days);
        sb.append(", present_comp_code=").append(present_comp_code);
        sb.append(", present_comp_name=").append(present_comp_name);
        sb.append(", present_comp_days=").append(present_comp_days);
        sb.append(", present_comp_beg_date=").append(present_comp_beg_date);
        sb.append(", present_comp_fund_count=").append(present_comp_fund_count);
        sb.append(", job_hopping_freq=").append(job_hopping_freq);
        sb.append(", fund_manage_nav=").append(fund_manage_nav);
        sb.append(", tenure_avg_annual_yield=").append(tenure_avg_annual_yield);
        sb.append(", avg_ir=").append(avg_ir);
        sb.append(", avg_ir_pct=").append(avg_ir_pct);
        sb.append(", tenure_avg_annual_yield_index=").append(tenure_avg_annual_yield_index);
        sb.append(", hs_300_index_yield=").append(hs_300_index_yield);
        sb.append(", csi_universal_index_yield=").append(csi_universal_index_yield);
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