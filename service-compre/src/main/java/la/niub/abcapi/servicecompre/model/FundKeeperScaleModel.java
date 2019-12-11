package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundKeeperScaleModel implements Serializable {
    private Long id;

    private Long fund_keeper_code;

    private String fund_keeper_name;

    private Date end_date;

    private String plate_code;

    private String plate_name;

    private Integer fund_quantity;

    private BigDecimal fund_share;

    private BigDecimal fund_nav;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public FundKeeperScaleModel(Long id, Long fund_keeper_code, String fund_keeper_name, Date end_date, String plate_code, String plate_name, Integer fund_quantity, BigDecimal fund_share, BigDecimal fund_nav, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.fund_keeper_code = fund_keeper_code;
        this.fund_keeper_name = fund_keeper_name;
        this.end_date = end_date;
        this.plate_code = plate_code;
        this.plate_name = plate_name;
        this.fund_quantity = fund_quantity;
        this.fund_share = fund_share;
        this.fund_nav = fund_nav;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public FundKeeperScaleModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getPlate_code() {
        return plate_code;
    }

    public void setPlate_code(String plate_code) {
        this.plate_code = plate_code == null ? null : plate_code.trim();
    }

    public String getPlate_name() {
        return plate_name;
    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name == null ? null : plate_name.trim();
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
        sb.append(", fund_keeper_code=").append(fund_keeper_code);
        sb.append(", fund_keeper_name=").append(fund_keeper_name);
        sb.append(", end_date=").append(end_date);
        sb.append(", plate_code=").append(plate_code);
        sb.append(", plate_name=").append(plate_name);
        sb.append(", fund_quantity=").append(fund_quantity);
        sb.append(", fund_share=").append(fund_share);
        sb.append(", fund_nav=").append(fund_nav);
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