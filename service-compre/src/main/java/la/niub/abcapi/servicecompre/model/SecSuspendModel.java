package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class SecSuspendModel implements Serializable {
    private Integer id;

    private Long sec_uni_code;

    private Date data_date;

    private Date susp_date;

    private Date stat_date;

    private String susp_time;

    private Long stp_type;

    private String stp_reason;

    private Integer suspend_dates;

    private Integer suspend_tdates;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private static final long serialVersionUID = 1L;

    public SecSuspendModel(Integer id, Long sec_uni_code, Date data_date, Date susp_date, Date stat_date, String susp_time, Long stp_type, String stp_reason, Integer suspend_dates, Integer suspend_tdates, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.data_date = data_date;
        this.susp_date = susp_date;
        this.stat_date = stat_date;
        this.susp_time = susp_time;
        this.stp_type = stp_type;
        this.stp_reason = stp_reason;
        this.suspend_dates = suspend_dates;
        this.suspend_tdates = suspend_tdates;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
    }

    public SecSuspendModel() {
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

    public Date getData_date() {
        return data_date;
    }

    public void setData_date(Date data_date) {
        this.data_date = data_date;
    }

    public Date getSusp_date() {
        return susp_date;
    }

    public void setSusp_date(Date susp_date) {
        this.susp_date = susp_date;
    }

    public Date getStat_date() {
        return stat_date;
    }

    public void setStat_date(Date stat_date) {
        this.stat_date = stat_date;
    }

    public String getSusp_time() {
        return susp_time;
    }

    public void setSusp_time(String susp_time) {
        this.susp_time = susp_time == null ? null : susp_time.trim();
    }

    public Long getStp_type() {
        return stp_type;
    }

    public void setStp_type(Long stp_type) {
        this.stp_type = stp_type;
    }

    public String getStp_reason() {
        return stp_reason;
    }

    public void setStp_reason(String stp_reason) {
        this.stp_reason = stp_reason == null ? null : stp_reason.trim();
    }

    public Integer getSuspend_dates() {
        return suspend_dates;
    }

    public void setSuspend_dates(Integer suspend_dates) {
        this.suspend_dates = suspend_dates;
    }

    public Integer getSuspend_tdates() {
        return suspend_tdates;
    }

    public void setSuspend_tdates(Integer suspend_tdates) {
        this.suspend_tdates = suspend_tdates;
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
        sb.append(", data_date=").append(data_date);
        sb.append(", susp_date=").append(susp_date);
        sb.append(", stat_date=").append(stat_date);
        sb.append(", susp_time=").append(susp_time);
        sb.append(", stp_type=").append(stp_type);
        sb.append(", stp_reason=").append(stp_reason);
        sb.append(", suspend_dates=").append(suspend_dates);
        sb.append(", suspend_tdates=").append(suspend_tdates);
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