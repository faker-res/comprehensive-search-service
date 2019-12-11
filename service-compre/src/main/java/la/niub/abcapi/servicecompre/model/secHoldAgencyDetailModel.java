package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class secHoldAgencyDetailModel implements Serializable {
    private Integer id;

    private Long sec_uni_code;

    private Date end_date;

    private Long org_uni_code;

    private String org_name;

    private String org_type;

    private String org_type_name;

    private Long hold_liq;

    private BigDecimal hold_value;

    private BigDecimal hold_pct;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private static final long serialVersionUID = 1L;

    public secHoldAgencyDetailModel(Integer id, Long sec_uni_code, Date end_date, Long org_uni_code, String org_name, String org_type, String org_type_name, Long hold_liq, BigDecimal hold_value, BigDecimal hold_pct, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.end_date = end_date;
        this.org_uni_code = org_uni_code;
        this.org_name = org_name;
        this.org_type = org_type;
        this.org_type_name = org_type_name;
        this.hold_liq = hold_liq;
        this.hold_value = hold_value;
        this.hold_pct = hold_pct;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
    }

    public secHoldAgencyDetailModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Long getOrg_uni_code() {
        return org_uni_code;
    }

    public void setOrg_uni_code(Long org_uni_code) {
        this.org_uni_code = org_uni_code;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name == null ? null : org_name.trim();
    }

    public String getOrg_type() {
        return org_type;
    }

    public void setOrg_type(String org_type) {
        this.org_type = org_type == null ? null : org_type.trim();
    }

    public String getOrg_type_name() {
        return org_type_name;
    }

    public void setOrg_type_name(String org_type_name) {
        this.org_type_name = org_type_name == null ? null : org_type_name.trim();
    }

    public Long getHold_liq() {
        return hold_liq;
    }

    public void setHold_liq(Long hold_liq) {
        this.hold_liq = hold_liq;
    }

    public BigDecimal getHold_value() {
        return hold_value;
    }

    public void setHold_value(BigDecimal hold_value) {
        this.hold_value = hold_value;
    }

    public BigDecimal getHold_pct() {
        return hold_pct;
    }

    public void setHold_pct(BigDecimal hold_pct) {
        this.hold_pct = hold_pct;
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
        sb.append(", sec_uni_code=").append(sec_uni_code);
        sb.append(", end_date=").append(end_date);
        sb.append(", org_uni_code=").append(org_uni_code);
        sb.append(", org_name=").append(org_name);
        sb.append(", org_type=").append(org_type);
        sb.append(", org_type_name=").append(org_type_name);
        sb.append(", hold_liq=").append(hold_liq);
        sb.append(", hold_value=").append(hold_value);
        sb.append(", hold_pct=").append(hold_pct);
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