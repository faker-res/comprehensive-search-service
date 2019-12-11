package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class secMainCirHolderModel implements Serializable {
    private Integer id;

    private Long com_uni_code;

    private Date end_date;

    private Short share_rank;

    private Date decl_date;

    private String shr_hldr_name;

    private Long org_uni_code;

    private Long peo_uni_code;

    private BigDecimal hold_shr_vol;

    private BigDecimal hold_shr_prop;

    private String hold_shr_add;

    private BigDecimal change_prop;

    private String shr_hldr_type;

    private String hold_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Short getShare_rank() {
        return share_rank;
    }

    public void setShare_rank(Short share_rank) {
        this.share_rank = share_rank;
    }

    public Date getDecl_date() {
        return decl_date;
    }

    public void setDecl_date(Date decl_date) {
        this.decl_date = decl_date;
    }

    public String getShr_hldr_name() {
        return shr_hldr_name;
    }

    public void setShr_hldr_name(String shr_hldr_name) {
        this.shr_hldr_name = shr_hldr_name == null ? null : shr_hldr_name.trim();
    }

    public Long getOrg_uni_code() {
        return org_uni_code;
    }

    public void setOrg_uni_code(Long org_uni_code) {
        this.org_uni_code = org_uni_code;
    }

    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public BigDecimal getHold_shr_vol() {
        return hold_shr_vol;
    }

    public void setHold_shr_vol(BigDecimal hold_shr_vol) {
        this.hold_shr_vol = hold_shr_vol;
    }

    public BigDecimal getHold_shr_prop() {
        return hold_shr_prop;
    }

    public void setHold_shr_prop(BigDecimal hold_shr_prop) {
        this.hold_shr_prop = hold_shr_prop;
    }

    public String getHold_shr_add() {
        return hold_shr_add;
    }

    public void setHold_shr_add(String hold_shr_add) {
        this.hold_shr_add = hold_shr_add == null ? null : hold_shr_add.trim();
    }

    public BigDecimal getChange_prop() {
        return change_prop;
    }

    public void setChange_prop(BigDecimal change_prop) {
        this.change_prop = change_prop;
    }

    public String getShr_hldr_type() {
        return shr_hldr_type;
    }

    public void setShr_hldr_type(String shr_hldr_type) {
        this.shr_hldr_type = shr_hldr_type == null ? null : shr_hldr_type.trim();
    }

    public String getHold_type() {
        return hold_type;
    }

    public void setHold_type(String hold_type) {
        this.hold_type = hold_type == null ? null : hold_type.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", com_uni_code=").append(com_uni_code);
        sb.append(", end_date=").append(end_date);
        sb.append(", share_rank=").append(share_rank);
        sb.append(", decl_date=").append(decl_date);
        sb.append(", shr_hldr_name=").append(shr_hldr_name);
        sb.append(", org_uni_code=").append(org_uni_code);
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", hold_shr_vol=").append(hold_shr_vol);
        sb.append(", hold_shr_prop=").append(hold_shr_prop);
        sb.append(", hold_shr_add=").append(hold_shr_add);
        sb.append(", change_prop=").append(change_prop);
        sb.append(", shr_hldr_type=").append(shr_hldr_type);
        sb.append(", hold_type=").append(hold_type);
        sb.append("]");
        return sb.toString();
    }
}