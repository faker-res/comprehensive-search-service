package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundInvestIndustryModel implements Serializable {
    private Long id;

    private Long sec_uni_code;

    private Date announcement_date;

    private Date end_date;

    private Long indu_standard;

    private String indu_published_code;

    private String indu_published_name;

    private BigDecimal indu_value;

    private BigDecimal indu_value_net_ratio;

    private BigDecimal active_inv_value;

    private BigDecimal active_inv_net_ratio;

    private BigDecimal index_inv_value;

    private BigDecimal index_inv_net_ratio;

    private BigDecimal indu_value_chg_3m;

    private BigDecimal indu_value_chg_ratio_3m;

    private BigDecimal indu_value_chg_6m;

    private BigDecimal indu_value_chg_ratio_6m;

    private BigDecimal indu_value_chg_1y;

    private BigDecimal indu_value_chg_ratio_1y;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public FundInvestIndustryModel(Long id, Long sec_uni_code, Date announcement_date, Date end_date, Long indu_standard, String indu_published_code, String indu_published_name, BigDecimal indu_value, BigDecimal indu_value_net_ratio, BigDecimal active_inv_value, BigDecimal active_inv_net_ratio, BigDecimal index_inv_value, BigDecimal index_inv_net_ratio, BigDecimal indu_value_chg_3m, BigDecimal indu_value_chg_ratio_3m, BigDecimal indu_value_chg_6m, BigDecimal indu_value_chg_ratio_6m, BigDecimal indu_value_chg_1y, BigDecimal indu_value_chg_ratio_1y, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.announcement_date = announcement_date;
        this.end_date = end_date;
        this.indu_standard = indu_standard;
        this.indu_published_code = indu_published_code;
        this.indu_published_name = indu_published_name;
        this.indu_value = indu_value;
        this.indu_value_net_ratio = indu_value_net_ratio;
        this.active_inv_value = active_inv_value;
        this.active_inv_net_ratio = active_inv_net_ratio;
        this.index_inv_value = index_inv_value;
        this.index_inv_net_ratio = index_inv_net_ratio;
        this.indu_value_chg_3m = indu_value_chg_3m;
        this.indu_value_chg_ratio_3m = indu_value_chg_ratio_3m;
        this.indu_value_chg_6m = indu_value_chg_6m;
        this.indu_value_chg_ratio_6m = indu_value_chg_ratio_6m;
        this.indu_value_chg_1y = indu_value_chg_1y;
        this.indu_value_chg_ratio_1y = indu_value_chg_ratio_1y;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public FundInvestIndustryModel() {
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

    public Long getIndu_standard() {
        return indu_standard;
    }

    public void setIndu_standard(Long indu_standard) {
        this.indu_standard = indu_standard;
    }

    public String getIndu_published_code() {
        return indu_published_code;
    }

    public void setIndu_published_code(String indu_published_code) {
        this.indu_published_code = indu_published_code == null ? null : indu_published_code.trim();
    }

    public String getIndu_published_name() {
        return indu_published_name;
    }

    public void setIndu_published_name(String indu_published_name) {
        this.indu_published_name = indu_published_name == null ? null : indu_published_name.trim();
    }

    public BigDecimal getIndu_value() {
        return indu_value;
    }

    public void setIndu_value(BigDecimal indu_value) {
        this.indu_value = indu_value;
    }

    public BigDecimal getIndu_value_net_ratio() {
        return indu_value_net_ratio;
    }

    public void setIndu_value_net_ratio(BigDecimal indu_value_net_ratio) {
        this.indu_value_net_ratio = indu_value_net_ratio;
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

    public BigDecimal getIndu_value_chg_3m() {
        return indu_value_chg_3m;
    }

    public void setIndu_value_chg_3m(BigDecimal indu_value_chg_3m) {
        this.indu_value_chg_3m = indu_value_chg_3m;
    }

    public BigDecimal getIndu_value_chg_ratio_3m() {
        return indu_value_chg_ratio_3m;
    }

    public void setIndu_value_chg_ratio_3m(BigDecimal indu_value_chg_ratio_3m) {
        this.indu_value_chg_ratio_3m = indu_value_chg_ratio_3m;
    }

    public BigDecimal getIndu_value_chg_6m() {
        return indu_value_chg_6m;
    }

    public void setIndu_value_chg_6m(BigDecimal indu_value_chg_6m) {
        this.indu_value_chg_6m = indu_value_chg_6m;
    }

    public BigDecimal getIndu_value_chg_ratio_6m() {
        return indu_value_chg_ratio_6m;
    }

    public void setIndu_value_chg_ratio_6m(BigDecimal indu_value_chg_ratio_6m) {
        this.indu_value_chg_ratio_6m = indu_value_chg_ratio_6m;
    }

    public BigDecimal getIndu_value_chg_1y() {
        return indu_value_chg_1y;
    }

    public void setIndu_value_chg_1y(BigDecimal indu_value_chg_1y) {
        this.indu_value_chg_1y = indu_value_chg_1y;
    }

    public BigDecimal getIndu_value_chg_ratio_1y() {
        return indu_value_chg_ratio_1y;
    }

    public void setIndu_value_chg_ratio_1y(BigDecimal indu_value_chg_ratio_1y) {
        this.indu_value_chg_ratio_1y = indu_value_chg_ratio_1y;
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
        sb.append(", indu_standard=").append(indu_standard);
        sb.append(", indu_published_code=").append(indu_published_code);
        sb.append(", indu_published_name=").append(indu_published_name);
        sb.append(", indu_value=").append(indu_value);
        sb.append(", indu_value_net_ratio=").append(indu_value_net_ratio);
        sb.append(", active_inv_value=").append(active_inv_value);
        sb.append(", active_inv_net_ratio=").append(active_inv_net_ratio);
        sb.append(", index_inv_value=").append(index_inv_value);
        sb.append(", index_inv_net_ratio=").append(index_inv_net_ratio);
        sb.append(", indu_value_chg_3m=").append(indu_value_chg_3m);
        sb.append(", indu_value_chg_ratio_3m=").append(indu_value_chg_ratio_3m);
        sb.append(", indu_value_chg_6m=").append(indu_value_chg_6m);
        sb.append(", indu_value_chg_ratio_6m=").append(indu_value_chg_ratio_6m);
        sb.append(", indu_value_chg_1y=").append(indu_value_chg_1y);
        sb.append(", indu_value_chg_ratio_1y=").append(indu_value_chg_ratio_1y);
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