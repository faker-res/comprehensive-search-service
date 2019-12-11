package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundInvestInduStatModel implements Serializable {
    private Long id;

    private Long fund_uni_code;

    private Date end_date;

    private Long indu_standard;

    private String indu_published_code;

    private String indu_published_name;

    private BigDecimal indu_fund_tot_nav;

    private BigDecimal indu_stk_tot_value;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public FundInvestInduStatModel(Long id, Long fund_uni_code, Date end_date, Long indu_standard, String indu_published_code, String indu_published_name, BigDecimal indu_fund_tot_nav, BigDecimal indu_stk_tot_value, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.fund_uni_code = fund_uni_code;
        this.end_date = end_date;
        this.indu_standard = indu_standard;
        this.indu_published_code = indu_published_code;
        this.indu_published_name = indu_published_name;
        this.indu_fund_tot_nav = indu_fund_tot_nav;
        this.indu_stk_tot_value = indu_stk_tot_value;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public FundInvestInduStatModel() {
        super();
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

    public BigDecimal getIndu_fund_tot_nav() {
        return indu_fund_tot_nav;
    }

    public void setIndu_fund_tot_nav(BigDecimal indu_fund_tot_nav) {
        this.indu_fund_tot_nav = indu_fund_tot_nav;
    }

    public BigDecimal getIndu_stk_tot_value() {
        return indu_stk_tot_value;
    }

    public void setIndu_stk_tot_value(BigDecimal indu_stk_tot_value) {
        this.indu_stk_tot_value = indu_stk_tot_value;
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
        sb.append(", fund_uni_code=").append(fund_uni_code);
        sb.append(", end_date=").append(end_date);
        sb.append(", indu_standard=").append(indu_standard);
        sb.append(", indu_published_code=").append(indu_published_code);
        sb.append(", indu_published_name=").append(indu_published_name);
        sb.append(", indu_fund_tot_nav=").append(indu_fund_tot_nav);
        sb.append(", indu_stk_tot_value=").append(indu_stk_tot_value);
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