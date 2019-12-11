package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundKeeperFundModel implements Serializable {
    private Integer id;

    private Date end_date;

    private Long fund_keeper_code;

    private String fund_keeper_name;

    private Integer fund_quantity;

    private BigDecimal fund_share;

    private BigDecimal fund_nav;

    private Integer open_fund_quantity;

    private BigDecimal open_fund_share;

    private BigDecimal open_fund_nav;

    private Integer closed_fund_quantity;

    private BigDecimal closed_fund_share;

    private BigDecimal closed_fund_nav;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public FundKeeperFundModel(Integer id, Date end_date, Long fund_keeper_code, String fund_keeper_name, Integer fund_quantity, BigDecimal fund_share, BigDecimal fund_nav, Integer open_fund_quantity, BigDecimal open_fund_share, BigDecimal open_fund_nav, Integer closed_fund_quantity, BigDecimal closed_fund_share, BigDecimal closed_fund_nav, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.end_date = end_date;
        this.fund_keeper_code = fund_keeper_code;
        this.fund_keeper_name = fund_keeper_name;
        this.fund_quantity = fund_quantity;
        this.fund_share = fund_share;
        this.fund_nav = fund_nav;
        this.open_fund_quantity = open_fund_quantity;
        this.open_fund_share = open_fund_share;
        this.open_fund_nav = open_fund_nav;
        this.closed_fund_quantity = closed_fund_quantity;
        this.closed_fund_share = closed_fund_share;
        this.closed_fund_nav = closed_fund_nav;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public FundKeeperFundModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getFund_keeper_name() {
        return fund_keeper_name;
    }

    public void setFund_keeper_name(String fund_keeper_name) {
        this.fund_keeper_name = fund_keeper_name == null ? null : fund_keeper_name.trim();
    }

    public Integer getFund_quantity() {
        return fund_quantity;
    }

    public void setFund_quantity(Integer fund_quantity) {
        this.fund_quantity = fund_quantity;
    }

    public BigDecimal getFund_share() {
        return fund_share;
    }

    public void setFund_share(BigDecimal fund_share) {
        this.fund_share = fund_share;
    }

    public BigDecimal getFund_nav() {
        return fund_nav;
    }

    public void setFund_nav(BigDecimal fund_nav) {
        this.fund_nav = fund_nav;
    }

    public Integer getOpen_fund_quantity() {
        return open_fund_quantity;
    }

    public void setOpen_fund_quantity(Integer open_fund_quantity) {
        this.open_fund_quantity = open_fund_quantity;
    }

    public BigDecimal getOpen_fund_share() {
        return open_fund_share;
    }

    public void setOpen_fund_share(BigDecimal open_fund_share) {
        this.open_fund_share = open_fund_share;
    }

    public BigDecimal getOpen_fund_nav() {
        return open_fund_nav;
    }

    public void setOpen_fund_nav(BigDecimal open_fund_nav) {
        this.open_fund_nav = open_fund_nav;
    }

    public Integer getClosed_fund_quantity() {
        return closed_fund_quantity;
    }

    public void setClosed_fund_quantity(Integer closed_fund_quantity) {
        this.closed_fund_quantity = closed_fund_quantity;
    }

    public BigDecimal getClosed_fund_share() {
        return closed_fund_share;
    }

    public void setClosed_fund_share(BigDecimal closed_fund_share) {
        this.closed_fund_share = closed_fund_share;
    }

    public BigDecimal getClosed_fund_nav() {
        return closed_fund_nav;
    }

    public void setClosed_fund_nav(BigDecimal closed_fund_nav) {
        this.closed_fund_nav = closed_fund_nav;
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
        sb.append(", fund_keeper_name=").append(fund_keeper_name);
        sb.append(", fund_quantity=").append(fund_quantity);
        sb.append(", fund_share=").append(fund_share);
        sb.append(", fund_nav=").append(fund_nav);
        sb.append(", open_fund_quantity=").append(open_fund_quantity);
        sb.append(", open_fund_share=").append(open_fund_share);
        sb.append(", open_fund_nav=").append(open_fund_nav);
        sb.append(", closed_fund_quantity=").append(closed_fund_quantity);
        sb.append(", closed_fund_share=").append(closed_fund_share);
        sb.append(", closed_fund_nav=").append(closed_fund_nav);
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