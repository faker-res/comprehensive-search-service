package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ComSaleStructureModel implements Serializable {
    private Integer id;

    private Long com_uni_code;

    private Date end_date;

    private Long reporttype;

    private Long project_type;

    private String project_name;

    private String project_norm_name;

    private BigDecimal income;

    private BigDecimal income_rate;

    private BigDecimal cost;

    private BigDecimal cost_rate;

    private BigDecimal gros_prof;

    private BigDecimal gros_prof_marg;

    private BigDecimal gros_prof_rate;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private static final long serialVersionUID = 1L;

    public ComSaleStructureModel(Integer id, Long com_uni_code, Date end_date, Long reporttype, Long project_type, String project_name, String project_norm_name, BigDecimal income, BigDecimal income_rate, BigDecimal cost, BigDecimal cost_rate, BigDecimal gros_prof, BigDecimal gros_prof_marg, BigDecimal gros_prof_rate, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source) {
        this.id = id;
        this.com_uni_code = com_uni_code;
        this.end_date = end_date;
        this.reporttype = reporttype;
        this.project_type = project_type;
        this.project_name = project_name;
        this.project_norm_name = project_norm_name;
        this.income = income;
        this.income_rate = income_rate;
        this.cost = cost;
        this.cost_rate = cost_rate;
        this.gros_prof = gros_prof;
        this.gros_prof_marg = gros_prof_marg;
        this.gros_prof_rate = gros_prof_rate;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
    }

    public ComSaleStructureModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Long getReporttype() {
        return reporttype;
    }

    public void setReporttype(Long reporttype) {
        this.reporttype = reporttype;
    }

    public Long getProject_type() {
        return project_type;
    }

    public void setProject_type(Long project_type) {
        this.project_type = project_type;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name == null ? null : project_name.trim();
    }

    public String getProject_norm_name() {
        return project_norm_name;
    }

    public void setProject_norm_name(String project_norm_name) {
        this.project_norm_name = project_norm_name == null ? null : project_norm_name.trim();
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getIncome_rate() {
        return income_rate;
    }

    public void setIncome_rate(BigDecimal income_rate) {
        this.income_rate = income_rate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getCost_rate() {
        return cost_rate;
    }

    public void setCost_rate(BigDecimal cost_rate) {
        this.cost_rate = cost_rate;
    }

    public BigDecimal getGros_prof() {
        return gros_prof;
    }

    public void setGros_prof(BigDecimal gros_prof) {
        this.gros_prof = gros_prof;
    }

    public BigDecimal getGros_prof_marg() {
        return gros_prof_marg;
    }

    public void setGros_prof_marg(BigDecimal gros_prof_marg) {
        this.gros_prof_marg = gros_prof_marg;
    }

    public BigDecimal getGros_prof_rate() {
        return gros_prof_rate;
    }

    public void setGros_prof_rate(BigDecimal gros_prof_rate) {
        this.gros_prof_rate = gros_prof_rate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source == null ? null : come_source.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", com_uni_code=").append(com_uni_code);
        sb.append(", end_date=").append(end_date);
        sb.append(", reporttype=").append(reporttype);
        sb.append(", project_type=").append(project_type);
        sb.append(", project_name=").append(project_name);
        sb.append(", project_norm_name=").append(project_norm_name);
        sb.append(", income=").append(income);
        sb.append(", income_rate=").append(income_rate);
        sb.append(", cost=").append(cost);
        sb.append(", cost_rate=").append(cost_rate);
        sb.append(", gros_prof=").append(gros_prof);
        sb.append(", gros_prof_marg=").append(gros_prof_marg);
        sb.append(", gros_prof_rate=").append(gros_prof_rate);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", come_source=").append(come_source);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}