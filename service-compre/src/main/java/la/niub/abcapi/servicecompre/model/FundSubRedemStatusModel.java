package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class FundSubRedemStatusModel implements Serializable {
    private Long id;

    private Long sec_uni_code;

    private Date announcement_date;

    private Long sub_redem_status;

    private Date status_begin_date;

    private Date status_end_date;

    private String remark;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public FundSubRedemStatusModel(Long id, Long sec_uni_code, Date announcement_date, Long sub_redem_status, Date status_begin_date, Date status_end_date, String remark, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.announcement_date = announcement_date;
        this.sub_redem_status = sub_redem_status;
        this.status_begin_date = status_begin_date;
        this.status_end_date = status_end_date;
        this.remark = remark;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public FundSubRedemStatusModel() {
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

    public Date getAnnouncement_date() {
        return announcement_date;
    }

    public void setAnnouncement_date(Date announcement_date) {
        this.announcement_date = announcement_date;
    }

    public Long getSub_redem_status() {
        return sub_redem_status;
    }

    public void setSub_redem_status(Long sub_redem_status) {
        this.sub_redem_status = sub_redem_status;
    }

    public Date getStatus_begin_date() {
        return status_begin_date;
    }

    public void setStatus_begin_date(Date status_begin_date) {
        this.status_begin_date = status_begin_date;
    }

    public Date getStatus_end_date() {
        return status_end_date;
    }

    public void setStatus_end_date(Date status_end_date) {
        this.status_end_date = status_end_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", announcement_date=").append(announcement_date);
        sb.append(", sub_redem_status=").append(sub_redem_status);
        sb.append(", status_begin_date=").append(status_begin_date);
        sb.append(", status_end_date=").append(status_end_date);
        sb.append(", remark=").append(remark);
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