package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundNavModel implements Serializable {
    private Long id;

    private Long sec_uni_code;

    private Date announcement_date;

    private Date end_date;

    private Long currency_type;

    private BigDecimal unit_nav;

    private BigDecimal cumu_unit_nav;

    private BigDecimal grow_line_value;

    private BigDecimal asset_nav;

    private BigDecimal tot_asset_nav;

    private BigDecimal rise_rate;

    private BigDecimal differ;

    private Byte is_exdiv_date;

    private Byte fund_sign;

    private String memo;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private Byte push_search;

    private Byte push_product;

    private static final long serialVersionUID = 1L;

    public FundNavModel(Long id, Long sec_uni_code, Date announcement_date, Date end_date, Long currency_type, BigDecimal unit_nav, BigDecimal cumu_unit_nav, BigDecimal grow_line_value, BigDecimal asset_nav, BigDecimal tot_asset_nav, BigDecimal rise_rate, BigDecimal differ, Byte is_exdiv_date, Byte fund_sign, String memo, Date createtime, Date updatetime, Byte status, String creator, String editor, Byte push_search, Byte push_product) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.announcement_date = announcement_date;
        this.end_date = end_date;
        this.currency_type = currency_type;
        this.unit_nav = unit_nav;
        this.cumu_unit_nav = cumu_unit_nav;
        this.grow_line_value = grow_line_value;
        this.asset_nav = asset_nav;
        this.tot_asset_nav = tot_asset_nav;
        this.rise_rate = rise_rate;
        this.differ = differ;
        this.is_exdiv_date = is_exdiv_date;
        this.fund_sign = fund_sign;
        this.memo = memo;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
        this.push_search = push_search;
        this.push_product = push_product;
    }

    public FundNavModel() {
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

    public Long getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(Long currency_type) {
        this.currency_type = currency_type;
    }

    public BigDecimal getUnit_nav() {
        return unit_nav;
    }

    public void setUnit_nav(BigDecimal unit_nav) {
        this.unit_nav = unit_nav;
    }

    public BigDecimal getCumu_unit_nav() {
        return cumu_unit_nav;
    }

    public void setCumu_unit_nav(BigDecimal cumu_unit_nav) {
        this.cumu_unit_nav = cumu_unit_nav;
    }

    public BigDecimal getGrow_line_value() {
        return grow_line_value;
    }

    public void setGrow_line_value(BigDecimal grow_line_value) {
        this.grow_line_value = grow_line_value;
    }

    public BigDecimal getAsset_nav() {
        return asset_nav;
    }

    public void setAsset_nav(BigDecimal asset_nav) {
        this.asset_nav = asset_nav;
    }

    public BigDecimal getTot_asset_nav() {
        return tot_asset_nav;
    }

    public void setTot_asset_nav(BigDecimal tot_asset_nav) {
        this.tot_asset_nav = tot_asset_nav;
    }

    public BigDecimal getRise_rate() {
        return rise_rate;
    }

    public void setRise_rate(BigDecimal rise_rate) {
        this.rise_rate = rise_rate;
    }

    public BigDecimal getDiffer() {
        return differ;
    }

    public void setDiffer(BigDecimal differ) {
        this.differ = differ;
    }

    public Byte getIs_exdiv_date() {
        return is_exdiv_date;
    }

    public void setIs_exdiv_date(Byte is_exdiv_date) {
        this.is_exdiv_date = is_exdiv_date;
    }

    public Byte getFund_sign() {
        return fund_sign;
    }

    public void setFund_sign(Byte fund_sign) {
        this.fund_sign = fund_sign;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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

    public Byte getPush_search() {
        return push_search;
    }

    public void setPush_search(Byte push_search) {
        this.push_search = push_search;
    }

    public Byte getPush_product() {
        return push_product;
    }

    public void setPush_product(Byte push_product) {
        this.push_product = push_product;
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
        sb.append(", currency_type=").append(currency_type);
        sb.append(", unit_nav=").append(unit_nav);
        sb.append(", cumu_unit_nav=").append(cumu_unit_nav);
        sb.append(", grow_line_value=").append(grow_line_value);
        sb.append(", asset_nav=").append(asset_nav);
        sb.append(", tot_asset_nav=").append(tot_asset_nav);
        sb.append(", rise_rate=").append(rise_rate);
        sb.append(", differ=").append(differ);
        sb.append(", is_exdiv_date=").append(is_exdiv_date);
        sb.append(", fund_sign=").append(fund_sign);
        sb.append(", memo=").append(memo);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", push_search=").append(push_search);
        sb.append(", push_product=").append(push_product);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}