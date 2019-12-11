package la.niub.abcapi.servicecompre.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class SecBasicInfoModel implements Serializable {
    private Long sec_uni_code;

    private Long com_uni_code;

    private Long org_uni_code;

    private String sec_name;

    private String sec_ename;

    private String sec_fname;

    private String sec_code;

    private String abc_code;

    private Long sec_type;

    private Long sec_small_type;

    private String sec_short_name;

    private String used_name;

    private Long ipo_status;

    private Long security_type;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ipo_date;

    private Date end_date;

    private Long ipo_sector;

    private String isin_code;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private Long currency_type;

    private String come_source;

    private static final long serialVersionUID = 1L;

    public SecBasicInfoModel(Long sec_uni_code, Long com_uni_code, Long org_uni_code, String sec_name, String sec_ename, String sec_fname, String sec_code, String abc_code, Long sec_type, Long sec_small_type, String sec_short_name, String used_name, Long ipo_status, Long security_type, Date ipo_date, Date end_date, Long ipo_sector, String isin_code, Date createtime, Date updatetime, String status, String remark, String creator, String editor, Long currency_type, String come_source) {
        this.sec_uni_code = sec_uni_code;
        this.com_uni_code = com_uni_code;
        this.org_uni_code = org_uni_code;
        this.sec_name = sec_name;
        this.sec_ename = sec_ename;
        this.sec_fname = sec_fname;
        this.sec_code = sec_code;
        this.abc_code = abc_code;
        this.sec_type = sec_type;
        this.sec_small_type = sec_small_type;
        this.sec_short_name = sec_short_name;
        this.used_name = used_name;
        this.ipo_status = ipo_status;
        this.security_type = security_type;
        this.ipo_date = ipo_date;
        this.end_date = end_date;
        this.ipo_sector = ipo_sector;
        this.isin_code = isin_code;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.currency_type = currency_type;
        this.come_source = come_source;
    }

    public SecBasicInfoModel(String sec_code, String abc_code) {
        this.sec_code = sec_code;
        this.abc_code = abc_code;
    }

    public SecBasicInfoModel() {
        super();
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public Long getOrg_uni_code() {
        return org_uni_code;
    }

    public void setOrg_uni_code(Long org_uni_code) {
        this.org_uni_code = org_uni_code;
    }

    public String getSec_name() {
        return sec_name;
    }

    public void setSec_name(String sec_name) {
        this.sec_name = sec_name == null ? null : sec_name.trim();
    }

    public String getSec_ename() {
        return sec_ename;
    }

    public void setSec_ename(String sec_ename) {
        this.sec_ename = sec_ename == null ? null : sec_ename.trim();
    }

    public String getSec_fname() {
        return sec_fname;
    }

    public void setSec_fname(String sec_fname) {
        this.sec_fname = sec_fname == null ? null : sec_fname.trim();
    }

    public String getSec_code() {
        return sec_code;
    }

    public void setSec_code(String sec_code) {
        this.sec_code = sec_code == null ? null : sec_code.trim();
    }

    public String getAbc_code() {
        return abc_code;
    }

    public void setAbc_code(String abc_code) {
        this.abc_code = abc_code == null ? null : abc_code.trim();
    }

    public Long getSec_type() {
        return sec_type;
    }

    public void setSec_type(Long sec_type) {
        this.sec_type = sec_type;
    }

    public Long getSec_small_type() {
        return sec_small_type;
    }

    public void setSec_small_type(Long sec_small_type) {
        this.sec_small_type = sec_small_type;
    }

    public String getSec_short_name() {
        return sec_short_name;
    }

    public void setSec_short_name(String sec_short_name) {
        this.sec_short_name = sec_short_name == null ? null : sec_short_name.trim();
    }

    public String getUsed_name() {
        return used_name;
    }

    public void setUsed_name(String used_name) {
        this.used_name = used_name == null ? null : used_name.trim();
    }

    public Long getIpo_status() {
        return ipo_status;
    }

    public void setIpo_status(Long ipo_status) {
        this.ipo_status = ipo_status;
    }

    public Long getSecurity_type() {
        return security_type;
    }

    public void setSecurity_type(Long security_type) {
        this.security_type = security_type;
    }

    public Date getIpo_date() {
        return ipo_date;
    }

    public void setIpo_date(Date ipo_date) {
        this.ipo_date = ipo_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Long getIpo_sector() {
        return ipo_sector;
    }

    public void setIpo_sector(Long ipo_sector) {
        this.ipo_sector = ipo_sector;
    }

    public String getIsin_code() {
        return isin_code;
    }

    public void setIsin_code(String isin_code) {
        this.isin_code = isin_code == null ? null : isin_code.trim();
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

    public Long getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(Long currency_type) {
        this.currency_type = currency_type;
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
        sb.append(", sec_uni_code=").append(sec_uni_code);
        sb.append(", com_uni_code=").append(com_uni_code);
        sb.append(", org_uni_code=").append(org_uni_code);
        sb.append(", sec_name=").append(sec_name);
        sb.append(", sec_ename=").append(sec_ename);
        sb.append(", sec_fname=").append(sec_fname);
        sb.append(", sec_code=").append(sec_code);
        sb.append(", abc_code=").append(abc_code);
        sb.append(", sec_type=").append(sec_type);
        sb.append(", sec_small_type=").append(sec_small_type);
        sb.append(", sec_short_name=").append(sec_short_name);
        sb.append(", used_name=").append(used_name);
        sb.append(", ipo_status=").append(ipo_status);
        sb.append(", security_type=").append(security_type);
        sb.append(", ipo_date=").append(ipo_date);
        sb.append(", end_date=").append(end_date);
        sb.append(", ipo_sector=").append(ipo_sector);
        sb.append(", isin_code=").append(isin_code);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", currency_type=").append(currency_type);
        sb.append(", come_source=").append(come_source);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}