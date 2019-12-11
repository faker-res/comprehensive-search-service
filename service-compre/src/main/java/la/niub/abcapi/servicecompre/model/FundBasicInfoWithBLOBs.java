package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundBasicInfoWithBLOBs extends FundBasicInfoModel implements Serializable {
    private String invst_target;

    private String invst_rule;

    private String invst_orient;

    private String risk_type;

    private String invst_stra;

    private String decision_rule;

    private String large_redem_treat;

    private String profit_dist_rule;

    private String end_term;

    private String remark;

    private static final long serialVersionUID = 1L;

    public FundBasicInfoWithBLOBs(Long sec_uni_code, Date announcement_date, String fund_name, String fund_sname, String fund_type, String fund_method, String investment_style, Long mana_uni_code, Long cust_uni_code, String exsit_period, Integer operate_period, Long operate_period_unit, BigDecimal unit_par_value, Long issue_mode, String iss_obj_desc, BigDecimal issue_price, BigDecimal issue_fee, Long invst_cur_type, BigDecimal coll_amut_upper, Date iss_beg_date, Date iss_end_date, Byte is_valid, Byte is_stat, Byte is_structured_fund, Byte is_unbr_fund, Byte is_index_fund, String sub_type, String pay_type, BigDecimal sub_limit_lower, BigDecimal sub_limit_upper, Integer coll_period, BigDecimal exist_sub_share, Long exist_sub_num, BigDecimal spon_sub_pla_amt, BigDecimal insu_sub_pla_amt, BigDecimal pub_sub_pla_amt, BigDecimal pub_sub_tot_share, BigDecimal issue_sub_ratio, BigDecimal issue_frozen_capital, BigDecimal issue_success_rate, Long valid_sub_num, BigDecimal valid_sub_share, BigDecimal coll_val_inti, BigDecimal total_coll_share, BigDecimal initiator_sub_share, BigDecimal employee_sub_share, Date registration_date, Date found_annc_date, Date fund_found_date, Date fund_end_date, Date out_app_annc_date, Date out_redem_annc_date, Date out_app_start_date, Date out_redem_start_date, Date in_app_annc_date, Date in_redem_annc_date, Date in_app_start_date, Date in_redem_start_date, BigDecimal app_limit_lower, BigDecimal redem_limit_lower, BigDecimal lowest_holding_share, String sub_conf_date, String redem_conf_date, String redem_pay_date, Date list_annc_date, Date fund_list_date, Long exchange, BigDecimal list_share, Date liquidation_start_date, Date liquidation_end_date, Long nav_pub_type, Integer large_redem_pct, Date createtime, Date updatetime, Byte status, String creator, String editor, Byte push_search, Byte push_product, String invst_target, String invst_rule, String invst_orient, String risk_type, String invst_stra, String decision_rule, String large_redem_treat, String profit_dist_rule, String end_term, String remark) {
        super(sec_uni_code, announcement_date, fund_name, fund_sname, fund_type, fund_method, investment_style, mana_uni_code, cust_uni_code, exsit_period, operate_period, operate_period_unit, unit_par_value, issue_mode, iss_obj_desc, issue_price, issue_fee, invst_cur_type, coll_amut_upper, iss_beg_date, iss_end_date, is_valid, is_stat, is_structured_fund, is_unbr_fund, is_index_fund, sub_type, pay_type, sub_limit_lower, sub_limit_upper, coll_period, exist_sub_share, exist_sub_num, spon_sub_pla_amt, insu_sub_pla_amt, pub_sub_pla_amt, pub_sub_tot_share, issue_sub_ratio, issue_frozen_capital, issue_success_rate, valid_sub_num, valid_sub_share, coll_val_inti, total_coll_share, initiator_sub_share, employee_sub_share, registration_date, found_annc_date, fund_found_date, fund_end_date, out_app_annc_date, out_redem_annc_date, out_app_start_date, out_redem_start_date, in_app_annc_date, in_redem_annc_date, in_app_start_date, in_redem_start_date, app_limit_lower, redem_limit_lower, lowest_holding_share, sub_conf_date, redem_conf_date, redem_pay_date, list_annc_date, fund_list_date, exchange, list_share, liquidation_start_date, liquidation_end_date, nav_pub_type, large_redem_pct, createtime, updatetime, status, creator, editor, push_search, push_product);
        this.invst_target = invst_target;
        this.invst_rule = invst_rule;
        this.invst_orient = invst_orient;
        this.risk_type = risk_type;
        this.invst_stra = invst_stra;
        this.decision_rule = decision_rule;
        this.large_redem_treat = large_redem_treat;
        this.profit_dist_rule = profit_dist_rule;
        this.end_term = end_term;
        this.remark = remark;
    }

    public FundBasicInfoWithBLOBs() {
        super();
    }

    public String getInvst_target() {
        return invst_target;
    }

    public void setInvst_target(String invst_target) {
        this.invst_target = invst_target == null ? null : invst_target.trim();
    }

    public String getInvst_rule() {
        return invst_rule;
    }

    public void setInvst_rule(String invst_rule) {
        this.invst_rule = invst_rule == null ? null : invst_rule.trim();
    }

    public String getInvst_orient() {
        return invst_orient;
    }

    public void setInvst_orient(String invst_orient) {
        this.invst_orient = invst_orient == null ? null : invst_orient.trim();
    }

    public String getRisk_type() {
        return risk_type;
    }

    public void setRisk_type(String risk_type) {
        this.risk_type = risk_type == null ? null : risk_type.trim();
    }

    public String getInvst_stra() {
        return invst_stra;
    }

    public void setInvst_stra(String invst_stra) {
        this.invst_stra = invst_stra == null ? null : invst_stra.trim();
    }

    public String getDecision_rule() {
        return decision_rule;
    }

    public void setDecision_rule(String decision_rule) {
        this.decision_rule = decision_rule == null ? null : decision_rule.trim();
    }

    public String getLarge_redem_treat() {
        return large_redem_treat;
    }

    public void setLarge_redem_treat(String large_redem_treat) {
        this.large_redem_treat = large_redem_treat == null ? null : large_redem_treat.trim();
    }

    public String getProfit_dist_rule() {
        return profit_dist_rule;
    }

    public void setProfit_dist_rule(String profit_dist_rule) {
        this.profit_dist_rule = profit_dist_rule == null ? null : profit_dist_rule.trim();
    }

    public String getEnd_term() {
        return end_term;
    }

    public void setEnd_term(String end_term) {
        this.end_term = end_term == null ? null : end_term.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", invst_target=").append(invst_target);
        sb.append(", invst_rule=").append(invst_rule);
        sb.append(", invst_orient=").append(invst_orient);
        sb.append(", risk_type=").append(risk_type);
        sb.append(", invst_stra=").append(invst_stra);
        sb.append(", decision_rule=").append(decision_rule);
        sb.append(", large_redem_treat=").append(large_redem_treat);
        sb.append(", profit_dist_rule=").append(profit_dist_rule);
        sb.append(", end_term=").append(end_term);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}