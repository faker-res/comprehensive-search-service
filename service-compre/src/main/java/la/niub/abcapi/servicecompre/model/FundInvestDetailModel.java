package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundInvestDetailModel implements Serializable {
    private Long id;

    private Long sec_uni_code;

    private Date announcement_date;

    private Date end_date;

    private Long inv_sec_uni_code;

    private String inv_sec_code;

    private String inv_sec_name;

    private Long inv_sec_type;

    private BigDecimal hold_sec_amount;

    private BigDecimal hold_sec_value;

    private BigDecimal sec_value_net_ratio;

    private BigDecimal active_inv_amount;

    private BigDecimal active_inv_value;

    private BigDecimal active_inv_net_ratio;

    private BigDecimal index_inv_amount;

    private BigDecimal index_inv_value;

    private BigDecimal index_inv_net_ratio;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public FundInvestDetailModel(Long id, Long sec_uni_code, Date announcement_date, Date end_date, Long inv_sec_uni_code, String inv_sec_code, String inv_sec_name, Long inv_sec_type, BigDecimal hold_sec_amount, BigDecimal hold_sec_value, BigDecimal sec_value_net_ratio, BigDecimal active_inv_amount, BigDecimal active_inv_value, BigDecimal active_inv_net_ratio, BigDecimal index_inv_amount, BigDecimal index_inv_value, BigDecimal index_inv_net_ratio, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.announcement_date = announcement_date;
        this.end_date = end_date;
        this.inv_sec_uni_code = inv_sec_uni_code;
        this.inv_sec_code = inv_sec_code;
        this.inv_sec_name = inv_sec_name;
        this.inv_sec_type = inv_sec_type;
        this.hold_sec_amount = hold_sec_amount;
        this.hold_sec_value = hold_sec_value;
        this.sec_value_net_ratio = sec_value_net_ratio;
        this.active_inv_amount = active_inv_amount;
        this.active_inv_value = active_inv_value;
        this.active_inv_net_ratio = active_inv_net_ratio;
        this.index_inv_amount = index_inv_amount;
        this.index_inv_value = index_inv_value;
        this.index_inv_net_ratio = index_inv_net_ratio;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public FundInvestDetailModel() {
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

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Long getInv_sec_uni_code() {
        return inv_sec_uni_code;
    }

    public void setInv_sec_uni_code(Long inv_sec_uni_code) {
        this.inv_sec_uni_code = inv_sec_uni_code;
    }

    public String getInv_sec_code() {
        return inv_sec_code;
    }

    public void setInv_sec_code(String inv_sec_code) {
        this.inv_sec_code = inv_sec_code == null ? null : inv_sec_code.trim();
    }

    public String getInv_sec_name() {
        return inv_sec_name;
    }

    public void setInv_sec_name(String inv_sec_name) {
        this.inv_sec_name = inv_sec_name == null ? null : inv_sec_name.trim();
    }

    public Long getInv_sec_type() {
        return inv_sec_type;
    }

    public void setInv_sec_type(Long inv_sec_type) {
        this.inv_sec_type = inv_sec_type;
    }

    public BigDecimal getHold_sec_amount() {
        return hold_sec_amount;
    }

    public void setHold_sec_amount(BigDecimal hold_sec_amount) {
        this.hold_sec_amount = hold_sec_amount;
    }

    public BigDecimal getHold_sec_value() {
        return hold_sec_value;
    }

    public void setHold_sec_value(BigDecimal hold_sec_value) {
        this.hold_sec_value = hold_sec_value;
    }

    public BigDecimal getSec_value_net_ratio() {
        return sec_value_net_ratio;
    }

    public void setSec_value_net_ratio(BigDecimal sec_value_net_ratio) {
        this.sec_value_net_ratio = sec_value_net_ratio;
    }

    public BigDecimal getActive_inv_amount() {
        return active_inv_amount;
    }

    public void setActive_inv_amount(BigDecimal active_inv_amount) {
        this.active_inv_amount = active_inv_amount;
    }

    public BigDecimal getActive_inv_value() {
        return active_inv_value;
    }

    public void setActive_inv_value(BigDecimal active_inv_value) {
        this.active_inv_value = active_inv_value;
    }

    public BigDecimal getActive_inv_net_ratio() {
        return active_inv_net_ratio;
    }

    public void setActive_inv_net_ratio(BigDecimal active_inv_net_ratio) {
        this.active_inv_net_ratio = active_inv_net_ratio;
    }

    public BigDecimal getIndex_inv_amount() {
        return index_inv_amount;
    }

    public void setIndex_inv_amount(BigDecimal index_inv_amount) {
        this.index_inv_amount = index_inv_amount;
    }

    public BigDecimal getIndex_inv_value() {
        return index_inv_value;
    }

    public void setIndex_inv_value(BigDecimal index_inv_value) {
        this.index_inv_value = index_inv_value;
    }

    public BigDecimal getIndex_inv_net_ratio() {
        return index_inv_net_ratio;
    }

    public void setIndex_inv_net_ratio(BigDecimal index_inv_net_ratio) {
        this.index_inv_net_ratio = index_inv_net_ratio;
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
        sb.append(", end_date=").append(end_date);
        sb.append(", inv_sec_uni_code=").append(inv_sec_uni_code);
        sb.append(", inv_sec_code=").append(inv_sec_code);
        sb.append(", inv_sec_name=").append(inv_sec_name);
        sb.append(", inv_sec_type=").append(inv_sec_type);
        sb.append(", hold_sec_amount=").append(hold_sec_amount);
        sb.append(", hold_sec_value=").append(hold_sec_value);
        sb.append(", sec_value_net_ratio=").append(sec_value_net_ratio);
        sb.append(", active_inv_amount=").append(active_inv_amount);
        sb.append(", active_inv_value=").append(active_inv_value);
        sb.append(", active_inv_net_ratio=").append(active_inv_net_ratio);
        sb.append(", index_inv_amount=").append(index_inv_amount);
        sb.append(", index_inv_value=").append(index_inv_value);
        sb.append(", index_inv_net_ratio=").append(index_inv_net_ratio);
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