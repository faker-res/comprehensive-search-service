package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class TransacModel implements Serializable {
    private Long id;

    private Date end_date;

    private Long sec_mar;

    private String nati_name;

    private String if_trading_day;

    private Date start_trading_time;

    private Date end_trading_time;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private String remarks;

    private static final long serialVersionUID = 1L;

    public TransacModel(Long id, Date end_date, Long sec_mar, String nati_name, String if_trading_day, Date start_trading_time, Date end_trading_time, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source) {
        this.id = id;
        this.end_date = end_date;
        this.sec_mar = sec_mar;
        this.nati_name = nati_name;
        this.if_trading_day = if_trading_day;
        this.start_trading_time = start_trading_time;
        this.end_trading_time = end_trading_time;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
    }

    public TransacModel(Long id, Date end_date, Long sec_mar, String nati_name, String if_trading_day, Date start_trading_time, Date end_trading_time, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source, String remarks) {
        this.id = id;
        this.end_date = end_date;
        this.sec_mar = sec_mar;
        this.nati_name = nati_name;
        this.if_trading_day = if_trading_day;
        this.start_trading_time = start_trading_time;
        this.end_trading_time = end_trading_time;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
        this.remarks = remarks;
    }

    public TransacModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Long getSec_mar() {
        return sec_mar;
    }

    public void setSec_mar(Long sec_mar) {
        this.sec_mar = sec_mar;
    }

    public String getNati_name() {
        return nati_name;
    }

    public void setNati_name(String nati_name) {
        this.nati_name = nati_name == null ? null : nati_name.trim();
    }

    public String getIf_trading_day() {
        return if_trading_day;
    }

    public void setIf_trading_day(String if_trading_day) {
        this.if_trading_day = if_trading_day == null ? null : if_trading_day.trim();
    }

    public Date getStart_trading_time() {
        return start_trading_time;
    }

    public void setStart_trading_time(Date start_trading_time) {
        this.start_trading_time = start_trading_time;
    }

    public Date getEnd_trading_time() {
        return end_trading_time;
    }

    public void setEnd_trading_time(Date end_trading_time) {
        this.end_trading_time = end_trading_time;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", end_date=").append(end_date);
        sb.append(", sec_mar=").append(sec_mar);
        sb.append(", nati_name=").append(nati_name);
        sb.append(", if_trading_day=").append(if_trading_day);
        sb.append(", start_trading_time=").append(start_trading_time);
        sb.append(", end_trading_time=").append(end_trading_time);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", come_source=").append(come_source);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}