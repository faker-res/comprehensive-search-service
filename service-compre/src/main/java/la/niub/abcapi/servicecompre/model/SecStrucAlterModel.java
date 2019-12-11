package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SecStrucAlterModel implements Serializable {
    private Integer id;

    private Long com_uni_code;

    private Date chan_date;

    private Date decl_date;

    private String chan_reason;

    private String chng_cause;

    private BigDecimal total_shares;

    private BigDecimal a_share;

    private BigDecimal b_t_share;

    private BigDecimal ploat_share;

    private BigDecimal a_ploat_share;

    private BigDecimal b_share;

    private BigDecimal h_share;

    private BigDecimal s_share;

    private BigDecimal n_share;

    private BigDecimal oth_float_share;

    private BigDecimal tot_rest;

    private BigDecimal n_ploat_share_a;

    private BigDecimal n_ploat_share_b;

    private BigDecimal tot_state_leg_rest;

    private BigDecimal state_rest;

    private BigDecimal state_leg_rest;

    private BigDecimal dom_leg_rest;

    private BigDecimal dom_natu_rest;

    private BigDecimal fore_rest;

    private BigDecimal fore_leg_res;

    private BigDecimal fore_natu_res;

    private BigDecimal man_res;

    private BigDecimal pref_res;

    private BigDecimal stra_place_res;

    private BigDecimal fund_place_res;

    private BigDecimal tot_nfloat;

    private BigDecimal tot_state_leg_nfloat;

    private BigDecimal state_nfloat;

    private BigDecimal state_leg_nfloat;

    private BigDecimal dom_leg_nfloat;

    private BigDecimal rais_leg_nfloat;

    private BigDecimal inner_staff_nfloat;

    private BigDecimal man_nfloat;

    private BigDecimal trans_nfloat;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String creator;

    private String editor;

    private String come_source;

    private BigDecimal other_demo_sharehold;

    private Long sec_type;

    private static final long serialVersionUID = 1L;

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

    public Date getChan_date() {
        return chan_date;
    }

    public void setChan_date(Date chan_date) {
        this.chan_date = chan_date;
    }

    public Date getDecl_date() {
        return decl_date;
    }

    public void setDecl_date(Date decl_date) {
        this.decl_date = decl_date;
    }

    public String getChan_reason() {
        return chan_reason;
    }

    public void setChan_reason(String chan_reason) {
        this.chan_reason = chan_reason;
    }

    public String getChng_cause() {
        return chng_cause;
    }

    public void setChng_cause(String chng_cause) {
        this.chng_cause = chng_cause;
    }

    public BigDecimal getTotal_shares() {
        return total_shares;
    }

    public void setTotal_shares(BigDecimal total_shares) {
        this.total_shares = total_shares;
    }

    public BigDecimal getA_share() {
        return a_share;
    }

    public void setA_share(BigDecimal a_share) {
        this.a_share = a_share;
    }

    public BigDecimal getB_t_share() {
        return b_t_share;
    }

    public void setB_t_share(BigDecimal b_t_share) {
        this.b_t_share = b_t_share;
    }

    public BigDecimal getPloat_share() {
        return ploat_share;
    }

    public void setPloat_share(BigDecimal ploat_share) {
        this.ploat_share = ploat_share;
    }

    public BigDecimal getA_ploat_share() {
        return a_ploat_share;
    }

    public void setA_ploat_share(BigDecimal a_ploat_share) {
        this.a_ploat_share = a_ploat_share;
    }

    public BigDecimal getB_share() {
        return b_share;
    }

    public void setB_share(BigDecimal b_share) {
        this.b_share = b_share;
    }

    public BigDecimal getH_share() {
        return h_share;
    }

    public void setH_share(BigDecimal h_share) {
        this.h_share = h_share;
    }

    public BigDecimal getS_share() {
        return s_share;
    }

    public void setS_share(BigDecimal s_share) {
        this.s_share = s_share;
    }

    public BigDecimal getN_share() {
        return n_share;
    }

    public void setN_share(BigDecimal n_share) {
        this.n_share = n_share;
    }

    public BigDecimal getOth_float_share() {
        return oth_float_share;
    }

    public void setOth_float_share(BigDecimal oth_float_share) {
        this.oth_float_share = oth_float_share;
    }

    public BigDecimal getTot_rest() {
        return tot_rest;
    }

    public void setTot_rest(BigDecimal tot_rest) {
        this.tot_rest = tot_rest;
    }

    public BigDecimal getN_ploat_share_a() {
        return n_ploat_share_a;
    }

    public void setN_ploat_share_a(BigDecimal n_ploat_share_a) {
        this.n_ploat_share_a = n_ploat_share_a;
    }

    public BigDecimal getN_ploat_share_b() {
        return n_ploat_share_b;
    }

    public void setN_ploat_share_b(BigDecimal n_ploat_share_b) {
        this.n_ploat_share_b = n_ploat_share_b;
    }

    public BigDecimal getTot_state_leg_rest() {
        return tot_state_leg_rest;
    }

    public void setTot_state_leg_rest(BigDecimal tot_state_leg_rest) {
        this.tot_state_leg_rest = tot_state_leg_rest;
    }

    public BigDecimal getState_rest() {
        return state_rest;
    }

    public void setState_rest(BigDecimal state_rest) {
        this.state_rest = state_rest;
    }

    public BigDecimal getState_leg_rest() {
        return state_leg_rest;
    }

    public void setState_leg_rest(BigDecimal state_leg_rest) {
        this.state_leg_rest = state_leg_rest;
    }

    public BigDecimal getDom_leg_rest() {
        return dom_leg_rest;
    }

    public void setDom_leg_rest(BigDecimal dom_leg_rest) {
        this.dom_leg_rest = dom_leg_rest;
    }

    public BigDecimal getDom_natu_rest() {
        return dom_natu_rest;
    }

    public void setDom_natu_rest(BigDecimal dom_natu_rest) {
        this.dom_natu_rest = dom_natu_rest;
    }

    public BigDecimal getFore_rest() {
        return fore_rest;
    }

    public void setFore_rest(BigDecimal fore_rest) {
        this.fore_rest = fore_rest;
    }

    public BigDecimal getFore_leg_res() {
        return fore_leg_res;
    }

    public void setFore_leg_res(BigDecimal fore_leg_res) {
        this.fore_leg_res = fore_leg_res;
    }

    public BigDecimal getFore_natu_res() {
        return fore_natu_res;
    }

    public void setFore_natu_res(BigDecimal fore_natu_res) {
        this.fore_natu_res = fore_natu_res;
    }

    public BigDecimal getMan_res() {
        return man_res;
    }

    public void setMan_res(BigDecimal man_res) {
        this.man_res = man_res;
    }

    public BigDecimal getPref_res() {
        return pref_res;
    }

    public void setPref_res(BigDecimal pref_res) {
        this.pref_res = pref_res;
    }

    public BigDecimal getStra_place_res() {
        return stra_place_res;
    }

    public void setStra_place_res(BigDecimal stra_place_res) {
        this.stra_place_res = stra_place_res;
    }

    public BigDecimal getFund_place_res() {
        return fund_place_res;
    }

    public void setFund_place_res(BigDecimal fund_place_res) {
        this.fund_place_res = fund_place_res;
    }

    public BigDecimal getTot_nfloat() {
        return tot_nfloat;
    }

    public void setTot_nfloat(BigDecimal tot_nfloat) {
        this.tot_nfloat = tot_nfloat;
    }

    public BigDecimal getTot_state_leg_nfloat() {
        return tot_state_leg_nfloat;
    }

    public void setTot_state_leg_nfloat(BigDecimal tot_state_leg_nfloat) {
        this.tot_state_leg_nfloat = tot_state_leg_nfloat;
    }

    public BigDecimal getState_nfloat() {
        return state_nfloat;
    }

    public void setState_nfloat(BigDecimal state_nfloat) {
        this.state_nfloat = state_nfloat;
    }

    public BigDecimal getState_leg_nfloat() {
        return state_leg_nfloat;
    }

    public void setState_leg_nfloat(BigDecimal state_leg_nfloat) {
        this.state_leg_nfloat = state_leg_nfloat;
    }

    public BigDecimal getDom_leg_nfloat() {
        return dom_leg_nfloat;
    }

    public void setDom_leg_nfloat(BigDecimal dom_leg_nfloat) {
        this.dom_leg_nfloat = dom_leg_nfloat;
    }

    public BigDecimal getRais_leg_nfloat() {
        return rais_leg_nfloat;
    }

    public void setRais_leg_nfloat(BigDecimal rais_leg_nfloat) {
        this.rais_leg_nfloat = rais_leg_nfloat;
    }

    public BigDecimal getInner_staff_nfloat() {
        return inner_staff_nfloat;
    }

    public void setInner_staff_nfloat(BigDecimal inner_staff_nfloat) {
        this.inner_staff_nfloat = inner_staff_nfloat;
    }

    public BigDecimal getMan_nfloat() {
        return man_nfloat;
    }

    public void setMan_nfloat(BigDecimal man_nfloat) {
        this.man_nfloat = man_nfloat;
    }

    public BigDecimal getTrans_nfloat() {
        return trans_nfloat;
    }

    public void setTrans_nfloat(BigDecimal trans_nfloat) {
        this.trans_nfloat = trans_nfloat;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source;
    }

    public BigDecimal getOther_demo_sharehold() {
        return other_demo_sharehold;
    }

    public void setOther_demo_sharehold(BigDecimal other_demo_sharehold) {
        this.other_demo_sharehold = other_demo_sharehold;
    }

    public Long getSec_type() {
        return sec_type;
    }

    public void setSec_type(Long sec_type) {
        this.sec_type = sec_type;
    }

    @Override
    public String toString() {
        return "SecStrucAlterModel{" +
                "id=" + id +
                ", com_uni_code=" + com_uni_code +
                ", chan_date=" + chan_date +
                ", decl_date=" + decl_date +
                ", chan_reason='" + chan_reason + '\'' +
                ", chng_cause='" + chng_cause + '\'' +
                ", total_shares=" + total_shares +
                ", a_share=" + a_share +
                ", b_t_share=" + b_t_share +
                ", ploat_share=" + ploat_share +
                ", a_ploat_share=" + a_ploat_share +
                ", b_share=" + b_share +
                ", h_share=" + h_share +
                ", s_share=" + s_share +
                ", n_share=" + n_share +
                ", oth_float_share=" + oth_float_share +
                ", tot_rest=" + tot_rest +
                ", n_ploat_share_a=" + n_ploat_share_a +
                ", n_ploat_share_b=" + n_ploat_share_b +
                ", tot_state_leg_rest=" + tot_state_leg_rest +
                ", state_rest=" + state_rest +
                ", state_leg_rest=" + state_leg_rest +
                ", dom_leg_rest=" + dom_leg_rest +
                ", dom_natu_rest=" + dom_natu_rest +
                ", fore_rest=" + fore_rest +
                ", fore_leg_res=" + fore_leg_res +
                ", fore_natu_res=" + fore_natu_res +
                ", man_res=" + man_res +
                ", pref_res=" + pref_res +
                ", stra_place_res=" + stra_place_res +
                ", fund_place_res=" + fund_place_res +
                ", tot_nfloat=" + tot_nfloat +
                ", tot_state_leg_nfloat=" + tot_state_leg_nfloat +
                ", state_nfloat=" + state_nfloat +
                ", state_leg_nfloat=" + state_leg_nfloat +
                ", dom_leg_nfloat=" + dom_leg_nfloat +
                ", rais_leg_nfloat=" + rais_leg_nfloat +
                ", inner_staff_nfloat=" + inner_staff_nfloat +
                ", man_nfloat=" + man_nfloat +
                ", trans_nfloat=" + trans_nfloat +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", status='" + status + '\'' +
                ", creator='" + creator + '\'' +
                ", editor='" + editor + '\'' +
                ", come_source='" + come_source + '\'' +
                ", other_demo_sharehold=" + other_demo_sharehold +
                ", sec_type=" + sec_type +
                '}';
    }
}