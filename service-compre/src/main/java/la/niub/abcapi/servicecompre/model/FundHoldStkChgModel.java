package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundHoldStkChgModel implements Serializable {
    private Long id;

    private Long fund_uni_code;

    private Date end_date;

    private Long stk_uni_code;

    private String stk_code;

    private BigDecimal hold_stk_amt_chg;

    private BigDecimal hold_ratio_for_float_stk;

    private BigDecimal hold_stk_value_stk_ratio;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    // 连表fund_invest_detail数据字段
    private BigDecimal hold_sec_amount;

    private BigDecimal hold_sec_value;

    private BigDecimal sec_value_net_ratio;

    // 连表sec_basic_info数据字段

    private String sec_name;

    private String sec_code;

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

    public String getSec_name() {
        return sec_name;
    }

    public void setSec_name(String sec_name) {
        this.sec_name = sec_name;
    }

    public String getSec_code() {
        return sec_code;
    }

    public void setSec_code(String sec_code) {
        this.sec_code = sec_code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFund_uni_code() {
        return fund_uni_code;
    }

    public void setFund_uni_code(Long fund_uni_code) {
        this.fund_uni_code = fund_uni_code;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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

    public BigDecimal getHold_stk_amt_chg() {
        return hold_stk_amt_chg;
    }

    public void setHold_stk_amt_chg(BigDecimal hold_stk_amt_chg) {
        this.hold_stk_amt_chg = hold_stk_amt_chg;
    }

    public BigDecimal getHold_ratio_for_float_stk() {
        return hold_ratio_for_float_stk;
    }

    public void setHold_ratio_for_float_stk(BigDecimal hold_ratio_for_float_stk) {
        this.hold_ratio_for_float_stk = hold_ratio_for_float_stk;
    }

    public BigDecimal getHold_stk_value_stk_ratio() {
        return hold_stk_value_stk_ratio;
    }

    public void setHold_stk_value_stk_ratio(BigDecimal hold_stk_value_stk_ratio) {
        this.hold_stk_value_stk_ratio = hold_stk_value_stk_ratio;
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

}