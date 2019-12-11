package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundKeeperHoldStkModel implements Serializable {
    private Long id;

    private Date end_date;

    private Long fund_keeper_code;

    private Long stk_uni_code;

    private String stk_abc_code;

    private String inv_stk_name;

    private Integer hold_stk_fund_amt;

    private BigDecimal hold_stk_amt;

    private BigDecimal hold_stk_amt_chg;

    private BigDecimal hold_cir_stk_amt_ratio;

    private BigDecimal hold_stk_value;

    private BigDecimal hold_stk_value_nav_ratio;

    private BigDecimal hold_stk_value_stk_ratio;

    private BigDecimal half_year_stk_amt_chg;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public FundKeeperHoldStkModel(Long id, Date end_date, Long fund_keeper_code, Long stk_uni_code, String stk_abc_code, String inv_stk_name, Integer hold_stk_fund_amt, BigDecimal hold_stk_amt, BigDecimal hold_stk_amt_chg, BigDecimal hold_cir_stk_amt_ratio, BigDecimal hold_stk_value, BigDecimal hold_stk_value_nav_ratio, BigDecimal hold_stk_value_stk_ratio, BigDecimal half_year_stk_amt_chg, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.end_date = end_date;
        this.fund_keeper_code = fund_keeper_code;
        this.stk_uni_code = stk_uni_code;
        this.stk_abc_code = stk_abc_code;
        this.inv_stk_name = inv_stk_name;
        this.hold_stk_fund_amt = hold_stk_fund_amt;
        this.hold_stk_amt = hold_stk_amt;
        this.hold_stk_amt_chg = hold_stk_amt_chg;
        this.hold_cir_stk_amt_ratio = hold_cir_stk_amt_ratio;
        this.hold_stk_value = hold_stk_value;
        this.hold_stk_value_nav_ratio = hold_stk_value_nav_ratio;
        this.hold_stk_value_stk_ratio = hold_stk_value_stk_ratio;
        this.half_year_stk_amt_chg = half_year_stk_amt_chg;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public FundKeeperHoldStkModel() {
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

    public Long getFund_keeper_code() {
        return fund_keeper_code;
    }

    public void setFund_keeper_code(Long fund_keeper_code) {
        this.fund_keeper_code = fund_keeper_code;
    }

    public Long getStk_uni_code() {
        return stk_uni_code;
    }

    public void setStk_uni_code(Long stk_uni_code) {
        this.stk_uni_code = stk_uni_code;
    }

    public String getStk_abc_code() {
        return stk_abc_code;
    }

    public void setStk_abc_code(String stk_abc_code) {
        this.stk_abc_code = stk_abc_code == null ? null : stk_abc_code.trim();
    }

    public String getInv_stk_name() {
        return inv_stk_name;
    }

    public void setInv_stk_name(String inv_stk_name) {
        this.inv_stk_name = inv_stk_name == null ? null : inv_stk_name.trim();
    }

    public Integer getHold_stk_fund_amt() {
        return hold_stk_fund_amt;
    }

    public void setHold_stk_fund_amt(Integer hold_stk_fund_amt) {
        this.hold_stk_fund_amt = hold_stk_fund_amt;
    }

    public BigDecimal getHold_stk_amt() {
        return hold_stk_amt;
    }

    public void setHold_stk_amt(BigDecimal hold_stk_amt) {
        this.hold_stk_amt = hold_stk_amt;
    }

    public BigDecimal getHold_stk_amt_chg() {
        return hold_stk_amt_chg;
    }

    public void setHold_stk_amt_chg(BigDecimal hold_stk_amt_chg) {
        this.hold_stk_amt_chg = hold_stk_amt_chg;
    }

    public BigDecimal getHold_cir_stk_amt_ratio() {
        return hold_cir_stk_amt_ratio;
    }

    public void setHold_cir_stk_amt_ratio(BigDecimal hold_cir_stk_amt_ratio) {
        this.hold_cir_stk_amt_ratio = hold_cir_stk_amt_ratio;
    }

    public BigDecimal getHold_stk_value() {
        return hold_stk_value;
    }

    public void setHold_stk_value(BigDecimal hold_stk_value) {
        this.hold_stk_value = hold_stk_value;
    }

    public BigDecimal getHold_stk_value_nav_ratio() {
        return hold_stk_value_nav_ratio;
    }

    public void setHold_stk_value_nav_ratio(BigDecimal hold_stk_value_nav_ratio) {
        this.hold_stk_value_nav_ratio = hold_stk_value_nav_ratio;
    }

    public BigDecimal getHold_stk_value_stk_ratio() {
        return hold_stk_value_stk_ratio;
    }

    public void setHold_stk_value_stk_ratio(BigDecimal hold_stk_value_stk_ratio) {
        this.hold_stk_value_stk_ratio = hold_stk_value_stk_ratio;
    }

    public BigDecimal getHalf_year_stk_amt_chg() {
        return half_year_stk_amt_chg;
    }

    public void setHalf_year_stk_amt_chg(BigDecimal half_year_stk_amt_chg) {
        this.half_year_stk_amt_chg = half_year_stk_amt_chg;
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
        sb.append(", end_date=").append(end_date);
        sb.append(", fund_keeper_code=").append(fund_keeper_code);
        sb.append(", stk_uni_code=").append(stk_uni_code);
        sb.append(", stk_abc_code=").append(stk_abc_code);
        sb.append(", inv_stk_name=").append(inv_stk_name);
        sb.append(", hold_stk_fund_amt=").append(hold_stk_fund_amt);
        sb.append(", hold_stk_amt=").append(hold_stk_amt);
        sb.append(", hold_stk_amt_chg=").append(hold_stk_amt_chg);
        sb.append(", hold_cir_stk_amt_ratio=").append(hold_cir_stk_amt_ratio);
        sb.append(", hold_stk_value=").append(hold_stk_value);
        sb.append(", hold_stk_value_nav_ratio=").append(hold_stk_value_nav_ratio);
        sb.append(", hold_stk_value_stk_ratio=").append(hold_stk_value_stk_ratio);
        sb.append(", half_year_stk_amt_chg=").append(half_year_stk_amt_chg);
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