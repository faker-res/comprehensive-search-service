package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundNavReturnModel implements Serializable {
    private Long id;

    private Long sec_uni_code;

    private Date end_date;

    private BigDecimal unit_nav;

    private BigDecimal cumu_unit_nav;

    private BigDecimal adj_unit_nav;

    private BigDecimal cumu_unit_nav_differ;

    private BigDecimal adj_unit_nav_differ;

    private BigDecimal rise_rate;

    private BigDecimal cumu_unit_nav_rise_rate;

    private Long currency_type;

    private BigDecimal nav_return_l_1w;

    private BigDecimal nav_return_l_1m;

    private BigDecimal nav_return_l_3m;

    private BigDecimal nav_return_l_6m;

    private BigDecimal nav_return_l_1y;

    private BigDecimal nav_return_l_2y;

    private BigDecimal nav_return_l_3y;

    private BigDecimal nav_return_l_5y;

    private BigDecimal nav_return_t_1w;

    private BigDecimal nav_return_t_1m;

    private BigDecimal nav_return_t_1q;

    private BigDecimal nav_return_t_1y;

    private BigDecimal nav_return_found;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public FundNavReturnModel(Long id, Long sec_uni_code, Date end_date, BigDecimal unit_nav, BigDecimal cumu_unit_nav, BigDecimal adj_unit_nav, BigDecimal cumu_unit_nav_differ, BigDecimal adj_unit_nav_differ, BigDecimal rise_rate, BigDecimal cumu_unit_nav_rise_rate, Long currency_type, BigDecimal nav_return_l_1w, BigDecimal nav_return_l_1m, BigDecimal nav_return_l_3m, BigDecimal nav_return_l_6m, BigDecimal nav_return_l_1y, BigDecimal nav_return_l_2y, BigDecimal nav_return_l_3y, BigDecimal nav_return_l_5y, BigDecimal nav_return_t_1w, BigDecimal nav_return_t_1m, BigDecimal nav_return_t_1q, BigDecimal nav_return_t_1y, BigDecimal nav_return_found, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.end_date = end_date;
        this.unit_nav = unit_nav;
        this.cumu_unit_nav = cumu_unit_nav;
        this.adj_unit_nav = adj_unit_nav;
        this.cumu_unit_nav_differ = cumu_unit_nav_differ;
        this.adj_unit_nav_differ = adj_unit_nav_differ;
        this.rise_rate = rise_rate;
        this.cumu_unit_nav_rise_rate = cumu_unit_nav_rise_rate;
        this.currency_type = currency_type;
        this.nav_return_l_1w = nav_return_l_1w;
        this.nav_return_l_1m = nav_return_l_1m;
        this.nav_return_l_3m = nav_return_l_3m;
        this.nav_return_l_6m = nav_return_l_6m;
        this.nav_return_l_1y = nav_return_l_1y;
        this.nav_return_l_2y = nav_return_l_2y;
        this.nav_return_l_3y = nav_return_l_3y;
        this.nav_return_l_5y = nav_return_l_5y;
        this.nav_return_t_1w = nav_return_t_1w;
        this.nav_return_t_1m = nav_return_t_1m;
        this.nav_return_t_1q = nav_return_t_1q;
        this.nav_return_t_1y = nav_return_t_1y;
        this.nav_return_found = nav_return_found;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public FundNavReturnModel() {
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

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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

    public BigDecimal getAdj_unit_nav() {
        return adj_unit_nav;
    }

    public void setAdj_unit_nav(BigDecimal adj_unit_nav) {
        this.adj_unit_nav = adj_unit_nav;
    }

    public BigDecimal getCumu_unit_nav_differ() {
        return cumu_unit_nav_differ;
    }

    public void setCumu_unit_nav_differ(BigDecimal cumu_unit_nav_differ) {
        this.cumu_unit_nav_differ = cumu_unit_nav_differ;
    }

    public BigDecimal getAdj_unit_nav_differ() {
        return adj_unit_nav_differ;
    }

    public void setAdj_unit_nav_differ(BigDecimal adj_unit_nav_differ) {
        this.adj_unit_nav_differ = adj_unit_nav_differ;
    }

    public BigDecimal getRise_rate() {
        return rise_rate;
    }

    public void setRise_rate(BigDecimal rise_rate) {
        this.rise_rate = rise_rate;
    }

    public BigDecimal getCumu_unit_nav_rise_rate() {
        return cumu_unit_nav_rise_rate;
    }

    public void setCumu_unit_nav_rise_rate(BigDecimal cumu_unit_nav_rise_rate) {
        this.cumu_unit_nav_rise_rate = cumu_unit_nav_rise_rate;
    }

    public Long getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(Long currency_type) {
        this.currency_type = currency_type;
    }

    public BigDecimal getNav_return_l_1w() {
        return nav_return_l_1w;
    }

    public void setNav_return_l_1w(BigDecimal nav_return_l_1w) {
        this.nav_return_l_1w = nav_return_l_1w;
    }

    public BigDecimal getNav_return_l_1m() {
        return nav_return_l_1m;
    }

    public void setNav_return_l_1m(BigDecimal nav_return_l_1m) {
        this.nav_return_l_1m = nav_return_l_1m;
    }

    public BigDecimal getNav_return_l_3m() {
        return nav_return_l_3m;
    }

    public void setNav_return_l_3m(BigDecimal nav_return_l_3m) {
        this.nav_return_l_3m = nav_return_l_3m;
    }

    public BigDecimal getNav_return_l_6m() {
        return nav_return_l_6m;
    }

    public void setNav_return_l_6m(BigDecimal nav_return_l_6m) {
        this.nav_return_l_6m = nav_return_l_6m;
    }

    public BigDecimal getNav_return_l_1y() {
        return nav_return_l_1y;
    }

    public void setNav_return_l_1y(BigDecimal nav_return_l_1y) {
        this.nav_return_l_1y = nav_return_l_1y;
    }

    public BigDecimal getNav_return_l_2y() {
        return nav_return_l_2y;
    }

    public void setNav_return_l_2y(BigDecimal nav_return_l_2y) {
        this.nav_return_l_2y = nav_return_l_2y;
    }

    public BigDecimal getNav_return_l_3y() {
        return nav_return_l_3y;
    }

    public void setNav_return_l_3y(BigDecimal nav_return_l_3y) {
        this.nav_return_l_3y = nav_return_l_3y;
    }

    public BigDecimal getNav_return_l_5y() {
        return nav_return_l_5y;
    }

    public void setNav_return_l_5y(BigDecimal nav_return_l_5y) {
        this.nav_return_l_5y = nav_return_l_5y;
    }

    public BigDecimal getNav_return_t_1w() {
        return nav_return_t_1w;
    }

    public void setNav_return_t_1w(BigDecimal nav_return_t_1w) {
        this.nav_return_t_1w = nav_return_t_1w;
    }

    public BigDecimal getNav_return_t_1m() {
        return nav_return_t_1m;
    }

    public void setNav_return_t_1m(BigDecimal nav_return_t_1m) {
        this.nav_return_t_1m = nav_return_t_1m;
    }

    public BigDecimal getNav_return_t_1q() {
        return nav_return_t_1q;
    }

    public void setNav_return_t_1q(BigDecimal nav_return_t_1q) {
        this.nav_return_t_1q = nav_return_t_1q;
    }

    public BigDecimal getNav_return_t_1y() {
        return nav_return_t_1y;
    }

    public void setNav_return_t_1y(BigDecimal nav_return_t_1y) {
        this.nav_return_t_1y = nav_return_t_1y;
    }

    public BigDecimal getNav_return_found() {
        return nav_return_found;
    }

    public void setNav_return_found(BigDecimal nav_return_found) {
        this.nav_return_found = nav_return_found;
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
        sb.append(", end_date=").append(end_date);
        sb.append(", unit_nav=").append(unit_nav);
        sb.append(", cumu_unit_nav=").append(cumu_unit_nav);
        sb.append(", adj_unit_nav=").append(adj_unit_nav);
        sb.append(", cumu_unit_nav_differ=").append(cumu_unit_nav_differ);
        sb.append(", adj_unit_nav_differ=").append(adj_unit_nav_differ);
        sb.append(", rise_rate=").append(rise_rate);
        sb.append(", cumu_unit_nav_rise_rate=").append(cumu_unit_nav_rise_rate);
        sb.append(", currency_type=").append(currency_type);
        sb.append(", nav_return_l_1w=").append(nav_return_l_1w);
        sb.append(", nav_return_l_1m=").append(nav_return_l_1m);
        sb.append(", nav_return_l_3m=").append(nav_return_l_3m);
        sb.append(", nav_return_l_6m=").append(nav_return_l_6m);
        sb.append(", nav_return_l_1y=").append(nav_return_l_1y);
        sb.append(", nav_return_l_2y=").append(nav_return_l_2y);
        sb.append(", nav_return_l_3y=").append(nav_return_l_3y);
        sb.append(", nav_return_l_5y=").append(nav_return_l_5y);
        sb.append(", nav_return_t_1w=").append(nav_return_t_1w);
        sb.append(", nav_return_t_1m=").append(nav_return_t_1m);
        sb.append(", nav_return_t_1q=").append(nav_return_t_1q);
        sb.append(", nav_return_t_1y=").append(nav_return_t_1y);
        sb.append(", nav_return_found=").append(nav_return_found);
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