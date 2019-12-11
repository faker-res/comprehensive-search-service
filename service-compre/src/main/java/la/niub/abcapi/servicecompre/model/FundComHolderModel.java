package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundComHolderModel implements Serializable {
    private Long id;

    private Long org_uni_code;

    private Date announcement_date;

    private Date begin_date;

    private Date end_date;

    private Long shareholder_code;

    private String shareholder_name;

    private String shareholder_type;

    private BigDecimal hold_shr_vol;

    private BigDecimal hold_shr_prop;

    private String serial_number;

    private String source_id;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public FundComHolderModel(Long id, Long org_uni_code, Date announcement_date, Date begin_date, Date end_date, Long shareholder_code, String shareholder_name, String shareholder_type, BigDecimal hold_shr_vol, BigDecimal hold_shr_prop, String serial_number, String source_id, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.org_uni_code = org_uni_code;
        this.announcement_date = announcement_date;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.shareholder_code = shareholder_code;
        this.shareholder_name = shareholder_name;
        this.shareholder_type = shareholder_type;
        this.hold_shr_vol = hold_shr_vol;
        this.hold_shr_prop = hold_shr_prop;
        this.serial_number = serial_number;
        this.source_id = source_id;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public FundComHolderModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrg_uni_code() {
        return org_uni_code;
    }

    public void setOrg_uni_code(Long org_uni_code) {
        this.org_uni_code = org_uni_code;
    }

    public Date getAnnouncement_date() {
        return announcement_date;
    }

    public void setAnnouncement_date(Date announcement_date) {
        this.announcement_date = announcement_date;
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

    public Long getShareholder_code() {
        return shareholder_code;
    }

    public void setShareholder_code(Long shareholder_code) {
        this.shareholder_code = shareholder_code;
    }

    public String getShareholder_name() {
        return shareholder_name;
    }

    public void setShareholder_name(String shareholder_name) {
        this.shareholder_name = shareholder_name == null ? null : shareholder_name.trim();
    }

    public String getShareholder_type() {
        return shareholder_type;
    }

    public void setShareholder_type(String shareholder_type) {
        this.shareholder_type = shareholder_type == null ? null : shareholder_type.trim();
    }

    public BigDecimal getHold_shr_vol() {
        return hold_shr_vol;
    }

    public void setHold_shr_vol(BigDecimal hold_shr_vol) {
        this.hold_shr_vol = hold_shr_vol;
    }

    public BigDecimal getHold_shr_prop() {
        return hold_shr_prop;
    }

    public void setHold_shr_prop(BigDecimal hold_shr_prop) {
        this.hold_shr_prop = hold_shr_prop;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number == null ? null : serial_number.trim();
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id == null ? null : source_id.trim();
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
        sb.append(", org_uni_code=").append(org_uni_code);
        sb.append(", announcement_date=").append(announcement_date);
        sb.append(", begin_date=").append(begin_date);
        sb.append(", end_date=").append(end_date);
        sb.append(", shareholder_code=").append(shareholder_code);
        sb.append(", shareholder_name=").append(shareholder_name);
        sb.append(", shareholder_type=").append(shareholder_type);
        sb.append(", hold_shr_vol=").append(hold_shr_vol);
        sb.append(", hold_shr_prop=").append(hold_shr_prop);
        sb.append(", serial_number=").append(serial_number);
        sb.append(", source_id=").append(source_id);
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