package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundHoldStkStatModel implements Serializable {
    private Long id;

    private Long stk_uni_code;

    private String stk_code;

    private Date end_date;

    private Integer hold_stk_fund_amt;

    private BigDecimal hold_tot_stk_amt;

    private BigDecimal hold_stk_amt_chg;

    private BigDecimal hold_tot_stk_value;

    private BigDecimal hold_stk_value_nav_ratio;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public FundHoldStkStatModel(Long id, Long stk_uni_code, String stk_code, Date end_date, Integer hold_stk_fund_amt, BigDecimal hold_tot_stk_amt, BigDecimal hold_stk_amt_chg, BigDecimal hold_tot_stk_value, BigDecimal hold_stk_value_nav_ratio, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.stk_uni_code = stk_uni_code;
        this.stk_code = stk_code;
        this.end_date = end_date;
        this.hold_stk_fund_amt = hold_stk_fund_amt;
        this.hold_tot_stk_amt = hold_tot_stk_amt;
        this.hold_stk_amt_chg = hold_stk_amt_chg;
        this.hold_tot_stk_value = hold_tot_stk_value;
        this.hold_stk_value_nav_ratio = hold_stk_value_nav_ratio;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public FundHoldStkStatModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStk_uni_code() {
        return stk_uni_code;
    }

    public void setStk_uni_code(Long stk_uni_code) {
        this.stk_uni_code = stk_uni_code;
    }

    public String getStk_code() {
        return stk_code;
    }

    public void setStk_code(String stk_code) {
        this.stk_code = stk_code == null ? null : stk_code.trim();
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Integer getHold_stk_fund_amt() {
        return hold_stk_fund_amt;
    }

    public void setHold_stk_fund_amt(Integer hold_stk_fund_amt) {
        this.hold_stk_fund_amt = hold_stk_fund_amt;
    }

    public BigDecimal getHold_tot_stk_amt() {
        return hold_tot_stk_amt;
    }

    public void setHold_tot_stk_amt(BigDecimal hold_tot_stk_amt) {
        this.hold_tot_stk_amt = hold_tot_stk_amt;
    }

    public BigDecimal getHold_stk_amt_chg() {
        return hold_stk_amt_chg;
    }

    public void setHold_stk_amt_chg(BigDecimal hold_stk_amt_chg) {
        this.hold_stk_amt_chg = hold_stk_amt_chg;
    }

    public BigDecimal getHold_tot_stk_value() {
        return hold_tot_stk_value;
    }

    public void setHold_tot_stk_value(BigDecimal hold_tot_stk_value) {
        this.hold_tot_stk_value = hold_tot_stk_value;
    }

    public BigDecimal getHold_stk_value_nav_ratio() {
        return hold_stk_value_nav_ratio;
    }

    public void setHold_stk_value_nav_ratio(BigDecimal hold_stk_value_nav_ratio) {
        this.hold_stk_value_nav_ratio = hold_stk_value_nav_ratio;
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
        sb.append(", stk_uni_code=").append(stk_uni_code);
        sb.append(", stk_code=").append(stk_code);
        sb.append(", end_date=").append(end_date);
        sb.append(", hold_stk_fund_amt=").append(hold_stk_fund_amt);
        sb.append(", hold_tot_stk_amt=").append(hold_tot_stk_amt);
        sb.append(", hold_stk_amt_chg=").append(hold_stk_amt_chg);
        sb.append(", hold_tot_stk_value=").append(hold_tot_stk_value);
        sb.append(", hold_stk_value_nav_ratio=").append(hold_stk_value_nav_ratio);
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