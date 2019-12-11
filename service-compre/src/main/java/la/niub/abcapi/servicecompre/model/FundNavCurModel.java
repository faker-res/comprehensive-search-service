package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundNavCurModel implements Serializable {
    private Long id;

    private Long sec_uni_code;

    private Date announcement_date;

    private Date begin_date;

    private Date end_date;

    private Long special_sign;

    private BigDecimal ten_thousand_returns;

    private BigDecimal seven_day_yield;

    private BigDecimal ten_thousand_cumu_returns;

    private BigDecimal asset_nav;

    private BigDecimal tot_asset_nav;

    private BigDecimal announced_returns;

    private Long announced_returns_unit;

    private Byte is_stat;

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

    public FundNavCurModel(Long id, Long sec_uni_code, Date announcement_date, Date begin_date, Date end_date, Long special_sign, BigDecimal ten_thousand_returns, BigDecimal seven_day_yield, BigDecimal ten_thousand_cumu_returns, BigDecimal asset_nav, BigDecimal tot_asset_nav, BigDecimal announced_returns, Long announced_returns_unit, Byte is_stat, Byte fund_sign, String memo, Date createtime, Date updatetime, Byte status, String creator, String editor, Byte push_search, Byte push_product) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.announcement_date = announcement_date;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.special_sign = special_sign;
        this.ten_thousand_returns = ten_thousand_returns;
        this.seven_day_yield = seven_day_yield;
        this.ten_thousand_cumu_returns = ten_thousand_cumu_returns;
        this.asset_nav = asset_nav;
        this.tot_asset_nav = tot_asset_nav;
        this.announced_returns = announced_returns;
        this.announced_returns_unit = announced_returns_unit;
        this.is_stat = is_stat;
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

    public FundNavCurModel() {
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

    public Long getSpecial_sign() {
        return special_sign;
    }

    public void setSpecial_sign(Long special_sign) {
        this.special_sign = special_sign;
    }

    public BigDecimal getTen_thousand_returns() {
        return ten_thousand_returns;
    }

    public void setTen_thousand_returns(BigDecimal ten_thousand_returns) {
        this.ten_thousand_returns = ten_thousand_returns;
    }

    public BigDecimal getSeven_day_yield() {
        return seven_day_yield;
    }

    public void setSeven_day_yield(BigDecimal seven_day_yield) {
        this.seven_day_yield = seven_day_yield;
    }

    public BigDecimal getTen_thousand_cumu_returns() {
        return ten_thousand_cumu_returns;
    }

    public void setTen_thousand_cumu_returns(BigDecimal ten_thousand_cumu_returns) {
        this.ten_thousand_cumu_returns = ten_thousand_cumu_returns;
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

    public BigDecimal getAnnounced_returns() {
        return announced_returns;
    }

    public void setAnnounced_returns(BigDecimal announced_returns) {
        this.announced_returns = announced_returns;
    }

    public Long getAnnounced_returns_unit() {
        return announced_returns_unit;
    }

    public void setAnnounced_returns_unit(Long announced_returns_unit) {
        this.announced_returns_unit = announced_returns_unit;
    }

    public Byte getIs_stat() {
        return is_stat;
    }

    public void setIs_stat(Byte is_stat) {
        this.is_stat = is_stat;
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
        sb.append(", begin_date=").append(begin_date);
        sb.append(", end_date=").append(end_date);
        sb.append(", special_sign=").append(special_sign);
        sb.append(", ten_thousand_returns=").append(ten_thousand_returns);
        sb.append(", seven_day_yield=").append(seven_day_yield);
        sb.append(", ten_thousand_cumu_returns=").append(ten_thousand_cumu_returns);
        sb.append(", asset_nav=").append(asset_nav);
        sb.append(", tot_asset_nav=").append(tot_asset_nav);
        sb.append(", announced_returns=").append(announced_returns);
        sb.append(", announced_returns_unit=").append(announced_returns_unit);
        sb.append(", is_stat=").append(is_stat);
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