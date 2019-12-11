package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundBasicInfoModel implements Serializable {
    private Long sec_uni_code;

    private Date announcement_date;

    private String fund_name;

    private String fund_sname;

    private String fund_type;

    private String fund_method;

    private String investment_style;

    private Long mana_uni_code;

    private Long cust_uni_code;

    private String exsit_period;

    private Integer operate_period;

    private Long operate_period_unit;

    private BigDecimal unit_par_value;

    private Long issue_mode;

    private String iss_obj_desc;

    private BigDecimal issue_price;

    private BigDecimal issue_fee;

    private Long invst_cur_type;

    private BigDecimal coll_amut_upper;

    private Date iss_beg_date;

    private Date iss_end_date;

    private Byte is_valid;

    private Byte is_stat;

    private Byte is_structured_fund;

    private Byte is_unbr_fund;

    private Byte is_index_fund;

    private String sub_type;

    private String pay_type;

    private BigDecimal sub_limit_lower;

    private BigDecimal sub_limit_upper;

    private Integer coll_period;

    private BigDecimal exist_sub_share;

    private Long exist_sub_num;

    private BigDecimal spon_sub_pla_amt;

    private BigDecimal insu_sub_pla_amt;

    private BigDecimal pub_sub_pla_amt;

    private BigDecimal pub_sub_tot_share;

    private BigDecimal issue_sub_ratio;

    private BigDecimal issue_frozen_capital;

    private BigDecimal issue_success_rate;

    private Long valid_sub_num;

    private BigDecimal valid_sub_share;

    private BigDecimal coll_val_inti;

    private BigDecimal total_coll_share;

    private BigDecimal initiator_sub_share;

    private BigDecimal employee_sub_share;

    private Date registration_date;

    private Date found_annc_date;

    private Date fund_found_date;

    private Date fund_end_date;

    private Date out_app_annc_date;

    private Date out_redem_annc_date;

    private Date out_app_start_date;

    private Date out_redem_start_date;

    private Date in_app_annc_date;

    private Date in_redem_annc_date;

    private Date in_app_start_date;

    private Date in_redem_start_date;

    private BigDecimal app_limit_lower;

    private BigDecimal redem_limit_lower;

    private BigDecimal lowest_holding_share;

    private String sub_conf_date;

    private String redem_conf_date;

    private String redem_pay_date;

    private Date list_annc_date;

    private Date fund_list_date;

    private Long exchange;

    private BigDecimal list_share;

    private Date liquidation_start_date;

    private Date liquidation_end_date;

    private Long nav_pub_type;

    private Integer large_redem_pct;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private Byte push_search;

    private Byte push_product;

    private static final long serialVersionUID = 1L;

    public FundBasicInfoModel(Long sec_uni_code, Date announcement_date, String fund_name, String fund_sname, String fund_type, String fund_method, String investment_style, Long mana_uni_code, Long cust_uni_code, String exsit_period, Integer operate_period, Long operate_period_unit, BigDecimal unit_par_value, Long issue_mode, String iss_obj_desc, BigDecimal issue_price, BigDecimal issue_fee, Long invst_cur_type, BigDecimal coll_amut_upper, Date iss_beg_date, Date iss_end_date, Byte is_valid, Byte is_stat, Byte is_structured_fund, Byte is_unbr_fund, Byte is_index_fund, String sub_type, String pay_type, BigDecimal sub_limit_lower, BigDecimal sub_limit_upper, Integer coll_period, BigDecimal exist_sub_share, Long exist_sub_num, BigDecimal spon_sub_pla_amt, BigDecimal insu_sub_pla_amt, BigDecimal pub_sub_pla_amt, BigDecimal pub_sub_tot_share, BigDecimal issue_sub_ratio, BigDecimal issue_frozen_capital, BigDecimal issue_success_rate, Long valid_sub_num, BigDecimal valid_sub_share, BigDecimal coll_val_inti, BigDecimal total_coll_share, BigDecimal initiator_sub_share, BigDecimal employee_sub_share, Date registration_date, Date found_annc_date, Date fund_found_date, Date fund_end_date, Date out_app_annc_date, Date out_redem_annc_date, Date out_app_start_date, Date out_redem_start_date, Date in_app_annc_date, Date in_redem_annc_date, Date in_app_start_date, Date in_redem_start_date, BigDecimal app_limit_lower, BigDecimal redem_limit_lower, BigDecimal lowest_holding_share, String sub_conf_date, String redem_conf_date, String redem_pay_date, Date list_annc_date, Date fund_list_date, Long exchange, BigDecimal list_share, Date liquidation_start_date, Date liquidation_end_date, Long nav_pub_type, Integer large_redem_pct, Date createtime, Date updatetime, Byte status, String creator, String editor, Byte push_search, Byte push_product) {
        this.sec_uni_code = sec_uni_code;
        this.announcement_date = announcement_date;
        this.fund_name = fund_name;
        this.fund_sname = fund_sname;
        this.fund_type = fund_type;
        this.fund_method = fund_method;
        this.investment_style = investment_style;
        this.mana_uni_code = mana_uni_code;
        this.cust_uni_code = cust_uni_code;
        this.exsit_period = exsit_period;
        this.operate_period = operate_period;
        this.operate_period_unit = operate_period_unit;
        this.unit_par_value = unit_par_value;
        this.issue_mode = issue_mode;
        this.iss_obj_desc = iss_obj_desc;
        this.issue_price = issue_price;
        this.issue_fee = issue_fee;
        this.invst_cur_type = invst_cur_type;
        this.coll_amut_upper = coll_amut_upper;
        this.iss_beg_date = iss_beg_date;
        this.iss_end_date = iss_end_date;
        this.is_valid = is_valid;
        this.is_stat = is_stat;
        this.is_structured_fund = is_structured_fund;
        this.is_unbr_fund = is_unbr_fund;
        this.is_index_fund = is_index_fund;
        this.sub_type = sub_type;
        this.pay_type = pay_type;
        this.sub_limit_lower = sub_limit_lower;
        this.sub_limit_upper = sub_limit_upper;
        this.coll_period = coll_period;
        this.exist_sub_share = exist_sub_share;
        this.exist_sub_num = exist_sub_num;
        this.spon_sub_pla_amt = spon_sub_pla_amt;
        this.insu_sub_pla_amt = insu_sub_pla_amt;
        this.pub_sub_pla_amt = pub_sub_pla_amt;
        this.pub_sub_tot_share = pub_sub_tot_share;
        this.issue_sub_ratio = issue_sub_ratio;
        this.issue_frozen_capital = issue_frozen_capital;
        this.issue_success_rate = issue_success_rate;
        this.valid_sub_num = valid_sub_num;
        this.valid_sub_share = valid_sub_share;
        this.coll_val_inti = coll_val_inti;
        this.total_coll_share = total_coll_share;
        this.initiator_sub_share = initiator_sub_share;
        this.employee_sub_share = employee_sub_share;
        this.registration_date = registration_date;
        this.found_annc_date = found_annc_date;
        this.fund_found_date = fund_found_date;
        this.fund_end_date = fund_end_date;
        this.out_app_annc_date = out_app_annc_date;
        this.out_redem_annc_date = out_redem_annc_date;
        this.out_app_start_date = out_app_start_date;
        this.out_redem_start_date = out_redem_start_date;
        this.in_app_annc_date = in_app_annc_date;
        this.in_redem_annc_date = in_redem_annc_date;
        this.in_app_start_date = in_app_start_date;
        this.in_redem_start_date = in_redem_start_date;
        this.app_limit_lower = app_limit_lower;
        this.redem_limit_lower = redem_limit_lower;
        this.lowest_holding_share = lowest_holding_share;
        this.sub_conf_date = sub_conf_date;
        this.redem_conf_date = redem_conf_date;
        this.redem_pay_date = redem_pay_date;
        this.list_annc_date = list_annc_date;
        this.fund_list_date = fund_list_date;
        this.exchange = exchange;
        this.list_share = list_share;
        this.liquidation_start_date = liquidation_start_date;
        this.liquidation_end_date = liquidation_end_date;
        this.nav_pub_type = nav_pub_type;
        this.large_redem_pct = large_redem_pct;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
        this.push_search = push_search;
        this.push_product = push_product;
    }

    public FundBasicInfoModel() {
        super();
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public Date getAnnouncement_date() {
        return announcement_date;
    }

    public void setAnnouncement_date(Date announcement_date) {
        this.announcement_date = announcement_date;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name == null ? null : fund_name.trim();
    }

    public String getFund_sname() {
        return fund_sname;
    }

    public void setFund_sname(String fund_sname) {
        this.fund_sname = fund_sname == null ? null : fund_sname.trim();
    }

    public String getFund_type() {
        return fund_type;
    }

    public void setFund_type(String fund_type) {
        this.fund_type = fund_type == null ? null : fund_type.trim();
    }

    public String getFund_method() {
        return fund_method;
    }

    public void setFund_method(String fund_method) {
        this.fund_method = fund_method == null ? null : fund_method.trim();
    }

    public String getInvestment_style() {
        return investment_style;
    }

    public void setInvestment_style(String investment_style) {
        this.investment_style = investment_style == null ? null : investment_style.trim();
    }

    public Long getMana_uni_code() {
        return mana_uni_code;
    }

    public void setMana_uni_code(Long mana_uni_code) {
        this.mana_uni_code = mana_uni_code;
    }

    public Long getCust_uni_code() {
        return cust_uni_code;
    }

    public void setCust_uni_code(Long cust_uni_code) {
        this.cust_uni_code = cust_uni_code;
    }

    public String getExsit_period() {
        return exsit_period;
    }

    public void setExsit_period(String exsit_period) {
        this.exsit_period = exsit_period == null ? null : exsit_period.trim();
    }

    public Integer getOperate_period() {
        return operate_period;
    }

    public void setOperate_period(Integer operate_period) {
        this.operate_period = operate_period;
    }

    public Long getOperate_period_unit() {
        return operate_period_unit;
    }

    public void setOperate_period_unit(Long operate_period_unit) {
        this.operate_period_unit = operate_period_unit;
    }

    public BigDecimal getUnit_par_value() {
        return unit_par_value;
    }

    public void setUnit_par_value(BigDecimal unit_par_value) {
        this.unit_par_value = unit_par_value;
    }

    public Long getIssue_mode() {
        return issue_mode;
    }

    public void setIssue_mode(Long issue_mode) {
        this.issue_mode = issue_mode;
    }

    public String getIss_obj_desc() {
        return iss_obj_desc;
    }

    public void setIss_obj_desc(String iss_obj_desc) {
        this.iss_obj_desc = iss_obj_desc == null ? null : iss_obj_desc.trim();
    }

    public BigDecimal getIssue_price() {
        return issue_price;
    }

    public void setIssue_price(BigDecimal issue_price) {
        this.issue_price = issue_price;
    }

    public BigDecimal getIssue_fee() {
        return issue_fee;
    }

    public void setIssue_fee(BigDecimal issue_fee) {
        this.issue_fee = issue_fee;
    }

    public Long getInvst_cur_type() {
        return invst_cur_type;
    }

    public void setInvst_cur_type(Long invst_cur_type) {
        this.invst_cur_type = invst_cur_type;
    }

    public BigDecimal getColl_amut_upper() {
        return coll_amut_upper;
    }

    public void setColl_amut_upper(BigDecimal coll_amut_upper) {
        this.coll_amut_upper = coll_amut_upper;
    }

    public Date getIss_beg_date() {
        return iss_beg_date;
    }

    public void setIss_beg_date(Date iss_beg_date) {
        this.iss_beg_date = iss_beg_date;
    }

    public Date getIss_end_date() {
        return iss_end_date;
    }

    public void setIss_end_date(Date iss_end_date) {
        this.iss_end_date = iss_end_date;
    }

    public Byte getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(Byte is_valid) {
        this.is_valid = is_valid;
    }

    public Byte getIs_stat() {
        return is_stat;
    }

    public void setIs_stat(Byte is_stat) {
        this.is_stat = is_stat;
    }

    public Byte getIs_structured_fund() {
        return is_structured_fund;
    }

    public void setIs_structured_fund(Byte is_structured_fund) {
        this.is_structured_fund = is_structured_fund;
    }

    public Byte getIs_unbr_fund() {
        return is_unbr_fund;
    }

    public void setIs_unbr_fund(Byte is_unbr_fund) {
        this.is_unbr_fund = is_unbr_fund;
    }

    public Byte getIs_index_fund() {
        return is_index_fund;
    }

    public void setIs_index_fund(Byte is_index_fund) {
        this.is_index_fund = is_index_fund;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type == null ? null : sub_type.trim();
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type == null ? null : pay_type.trim();
    }

    public BigDecimal getSub_limit_lower() {
        return sub_limit_lower;
    }

    public void setSub_limit_lower(BigDecimal sub_limit_lower) {
        this.sub_limit_lower = sub_limit_lower;
    }

    public BigDecimal getSub_limit_upper() {
        return sub_limit_upper;
    }

    public void setSub_limit_upper(BigDecimal sub_limit_upper) {
        this.sub_limit_upper = sub_limit_upper;
    }

    public Integer getColl_period() {
        return coll_period;
    }

    public void setColl_period(Integer coll_period) {
        this.coll_period = coll_period;
    }

    public BigDecimal getExist_sub_share() {
        return exist_sub_share;
    }

    public void setExist_sub_share(BigDecimal exist_sub_share) {
        this.exist_sub_share = exist_sub_share;
    }

    public Long getExist_sub_num() {
        return exist_sub_num;
    }

    public void setExist_sub_num(Long exist_sub_num) {
        this.exist_sub_num = exist_sub_num;
    }

    public BigDecimal getSpon_sub_pla_amt() {
        return spon_sub_pla_amt;
    }

    public void setSpon_sub_pla_amt(BigDecimal spon_sub_pla_amt) {
        this.spon_sub_pla_amt = spon_sub_pla_amt;
    }

    public BigDecimal getInsu_sub_pla_amt() {
        return insu_sub_pla_amt;
    }

    public void setInsu_sub_pla_amt(BigDecimal insu_sub_pla_amt) {
        this.insu_sub_pla_amt = insu_sub_pla_amt;
    }

    public BigDecimal getPub_sub_pla_amt() {
        return pub_sub_pla_amt;
    }

    public void setPub_sub_pla_amt(BigDecimal pub_sub_pla_amt) {
        this.pub_sub_pla_amt = pub_sub_pla_amt;
    }

    public BigDecimal getPub_sub_tot_share() {
        return pub_sub_tot_share;
    }

    public void setPub_sub_tot_share(BigDecimal pub_sub_tot_share) {
        this.pub_sub_tot_share = pub_sub_tot_share;
    }

    public BigDecimal getIssue_sub_ratio() {
        return issue_sub_ratio;
    }

    public void setIssue_sub_ratio(BigDecimal issue_sub_ratio) {
        this.issue_sub_ratio = issue_sub_ratio;
    }

    public BigDecimal getIssue_frozen_capital() {
        return issue_frozen_capital;
    }

    public void setIssue_frozen_capital(BigDecimal issue_frozen_capital) {
        this.issue_frozen_capital = issue_frozen_capital;
    }

    public BigDecimal getIssue_success_rate() {
        return issue_success_rate;
    }

    public void setIssue_success_rate(BigDecimal issue_success_rate) {
        this.issue_success_rate = issue_success_rate;
    }

    public Long getValid_sub_num() {
        return valid_sub_num;
    }

    public void setValid_sub_num(Long valid_sub_num) {
        this.valid_sub_num = valid_sub_num;
    }

    public BigDecimal getValid_sub_share() {
        return valid_sub_share;
    }

    public void setValid_sub_share(BigDecimal valid_sub_share) {
        this.valid_sub_share = valid_sub_share;
    }

    public BigDecimal getColl_val_inti() {
        return coll_val_inti;
    }

    public void setColl_val_inti(BigDecimal coll_val_inti) {
        this.coll_val_inti = coll_val_inti;
    }

    public BigDecimal getTotal_coll_share() {
        return total_coll_share;
    }

    public void setTotal_coll_share(BigDecimal total_coll_share) {
        this.total_coll_share = total_coll_share;
    }

    public BigDecimal getInitiator_sub_share() {
        return initiator_sub_share;
    }

    public void setInitiator_sub_share(BigDecimal initiator_sub_share) {
        this.initiator_sub_share = initiator_sub_share;
    }

    public BigDecimal getEmployee_sub_share() {
        return employee_sub_share;
    }

    public void setEmployee_sub_share(BigDecimal employee_sub_share) {
        this.employee_sub_share = employee_sub_share;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public Date getFound_annc_date() {
        return found_annc_date;
    }

    public void setFound_annc_date(Date found_annc_date) {
        this.found_annc_date = found_annc_date;
    }

    public Date getFund_found_date() {
        return fund_found_date;
    }

    public void setFund_found_date(Date fund_found_date) {
        this.fund_found_date = fund_found_date;
    }

    public Date getFund_end_date() {
        return fund_end_date;
    }

    public void setFund_end_date(Date fund_end_date) {
        this.fund_end_date = fund_end_date;
    }

    public Date getOut_app_annc_date() {
        return out_app_annc_date;
    }

    public void setOut_app_annc_date(Date out_app_annc_date) {
        this.out_app_annc_date = out_app_annc_date;
    }

    public Date getOut_redem_annc_date() {
        return out_redem_annc_date;
    }

    public void setOut_redem_annc_date(Date out_redem_annc_date) {
        this.out_redem_annc_date = out_redem_annc_date;
    }

    public Date getOut_app_start_date() {
        return out_app_start_date;
    }

    public void setOut_app_start_date(Date out_app_start_date) {
        this.out_app_start_date = out_app_start_date;
    }

    public Date getOut_redem_start_date() {
        return out_redem_start_date;
    }

    public void setOut_redem_start_date(Date out_redem_start_date) {
        this.out_redem_start_date = out_redem_start_date;
    }

    public Date getIn_app_annc_date() {
        return in_app_annc_date;
    }

    public void setIn_app_annc_date(Date in_app_annc_date) {
        this.in_app_annc_date = in_app_annc_date;
    }

    public Date getIn_redem_annc_date() {
        return in_redem_annc_date;
    }

    public void setIn_redem_annc_date(Date in_redem_annc_date) {
        this.in_redem_annc_date = in_redem_annc_date;
    }

    public Date getIn_app_start_date() {
        return in_app_start_date;
    }

    public void setIn_app_start_date(Date in_app_start_date) {
        this.in_app_start_date = in_app_start_date;
    }

    public Date getIn_redem_start_date() {
        return in_redem_start_date;
    }

    public void setIn_redem_start_date(Date in_redem_start_date) {
        this.in_redem_start_date = in_redem_start_date;
    }

    public BigDecimal getApp_limit_lower() {
        return app_limit_lower;
    }

    public void setApp_limit_lower(BigDecimal app_limit_lower) {
        this.app_limit_lower = app_limit_lower;
    }

    public BigDecimal getRedem_limit_lower() {
        return redem_limit_lower;
    }

    public void setRedem_limit_lower(BigDecimal redem_limit_lower) {
        this.redem_limit_lower = redem_limit_lower;
    }

    public BigDecimal getLowest_holding_share() {
        return lowest_holding_share;
    }

    public void setLowest_holding_share(BigDecimal lowest_holding_share) {
        this.lowest_holding_share = lowest_holding_share;
    }

    public String getSub_conf_date() {
        return sub_conf_date;
    }

    public void setSub_conf_date(String sub_conf_date) {
        this.sub_conf_date = sub_conf_date == null ? null : sub_conf_date.trim();
    }

    public String getRedem_conf_date() {
        return redem_conf_date;
    }

    public void setRedem_conf_date(String redem_conf_date) {
        this.redem_conf_date = redem_conf_date == null ? null : redem_conf_date.trim();
    }

    public String getRedem_pay_date() {
        return redem_pay_date;
    }

    public void setRedem_pay_date(String redem_pay_date) {
        this.redem_pay_date = redem_pay_date == null ? null : redem_pay_date.trim();
    }

    public Date getList_annc_date() {
        return list_annc_date;
    }

    public void setList_annc_date(Date list_annc_date) {
        this.list_annc_date = list_annc_date;
    }

    public Date getFund_list_date() {
        return fund_list_date;
    }

    public void setFund_list_date(Date fund_list_date) {
        this.fund_list_date = fund_list_date;
    }

    public Long getExchange() {
        return exchange;
    }

    public void setExchange(Long exchange) {
        this.exchange = exchange;
    }

    public BigDecimal getList_share() {
        return list_share;
    }

    public void setList_share(BigDecimal list_share) {
        this.list_share = list_share;
    }

    public Date getLiquidation_start_date() {
        return liquidation_start_date;
    }

    public void setLiquidation_start_date(Date liquidation_start_date) {
        this.liquidation_start_date = liquidation_start_date;
    }

    public Date getLiquidation_end_date() {
        return liquidation_end_date;
    }

    public void setLiquidation_end_date(Date liquidation_end_date) {
        this.liquidation_end_date = liquidation_end_date;
    }

    public Long getNav_pub_type() {
        return nav_pub_type;
    }

    public void setNav_pub_type(Long nav_pub_type) {
        this.nav_pub_type = nav_pub_type;
    }

    public Integer getLarge_redem_pct() {
        return large_redem_pct;
    }

    public void setLarge_redem_pct(Integer large_redem_pct) {
        this.large_redem_pct = large_redem_pct;
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

    public Byte getPush_search() {
        return push_search;
    }

    public void setPush_search(Byte push_search) {
        this.push_search = push_search;
    }

    public Byte getPush_product() {
        return push_product;
    }

    public void setPush_product(Byte push_product) {
        this.push_product = push_product;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sec_uni_code=").append(sec_uni_code);
        sb.append(", announcement_date=").append(announcement_date);
        sb.append(", fund_name=").append(fund_name);
        sb.append(", fund_sname=").append(fund_sname);
        sb.append(", fund_type=").append(fund_type);
        sb.append(", fund_method=").append(fund_method);
        sb.append(", investment_style=").append(investment_style);
        sb.append(", mana_uni_code=").append(mana_uni_code);
        sb.append(", cust_uni_code=").append(cust_uni_code);
        sb.append(", exsit_period=").append(exsit_period);
        sb.append(", operate_period=").append(operate_period);
        sb.append(", operate_period_unit=").append(operate_period_unit);
        sb.append(", unit_par_value=").append(unit_par_value);
        sb.append(", issue_mode=").append(issue_mode);
        sb.append(", iss_obj_desc=").append(iss_obj_desc);
        sb.append(", issue_price=").append(issue_price);
        sb.append(", issue_fee=").append(issue_fee);
        sb.append(", invst_cur_type=").append(invst_cur_type);
        sb.append(", coll_amut_upper=").append(coll_amut_upper);
        sb.append(", iss_beg_date=").append(iss_beg_date);
        sb.append(", iss_end_date=").append(iss_end_date);
        sb.append(", is_valid=").append(is_valid);
        sb.append(", is_stat=").append(is_stat);
        sb.append(", is_structured_fund=").append(is_structured_fund);
        sb.append(", is_unbr_fund=").append(is_unbr_fund);
        sb.append(", is_index_fund=").append(is_index_fund);
        sb.append(", sub_type=").append(sub_type);
        sb.append(", pay_type=").append(pay_type);
        sb.append(", sub_limit_lower=").append(sub_limit_lower);
        sb.append(", sub_limit_upper=").append(sub_limit_upper);
        sb.append(", coll_period=").append(coll_period);
        sb.append(", exist_sub_share=").append(exist_sub_share);
        sb.append(", exist_sub_num=").append(exist_sub_num);
        sb.append(", spon_sub_pla_amt=").append(spon_sub_pla_amt);
        sb.append(", insu_sub_pla_amt=").append(insu_sub_pla_amt);
        sb.append(", pub_sub_pla_amt=").append(pub_sub_pla_amt);
        sb.append(", pub_sub_tot_share=").append(pub_sub_tot_share);
        sb.append(", issue_sub_ratio=").append(issue_sub_ratio);
        sb.append(", issue_frozen_capital=").append(issue_frozen_capital);
        sb.append(", issue_success_rate=").append(issue_success_rate);
        sb.append(", valid_sub_num=").append(valid_sub_num);
        sb.append(", valid_sub_share=").append(valid_sub_share);
        sb.append(", coll_val_inti=").append(coll_val_inti);
        sb.append(", total_coll_share=").append(total_coll_share);
        sb.append(", initiator_sub_share=").append(initiator_sub_share);
        sb.append(", employee_sub_share=").append(employee_sub_share);
        sb.append(", registration_date=").append(registration_date);
        sb.append(", found_annc_date=").append(found_annc_date);
        sb.append(", fund_found_date=").append(fund_found_date);
        sb.append(", fund_end_date=").append(fund_end_date);
        sb.append(", out_app_annc_date=").append(out_app_annc_date);
        sb.append(", out_redem_annc_date=").append(out_redem_annc_date);
        sb.append(", out_app_start_date=").append(out_app_start_date);
        sb.append(", out_redem_start_date=").append(out_redem_start_date);
        sb.append(", in_app_annc_date=").append(in_app_annc_date);
        sb.append(", in_redem_annc_date=").append(in_redem_annc_date);
        sb.append(", in_app_start_date=").append(in_app_start_date);
        sb.append(", in_redem_start_date=").append(in_redem_start_date);
        sb.append(", app_limit_lower=").append(app_limit_lower);
        sb.append(", redem_limit_lower=").append(redem_limit_lower);
        sb.append(", lowest_holding_share=").append(lowest_holding_share);
        sb.append(", sub_conf_date=").append(sub_conf_date);
        sb.append(", redem_conf_date=").append(redem_conf_date);
        sb.append(", redem_pay_date=").append(redem_pay_date);
        sb.append(", list_annc_date=").append(list_annc_date);
        sb.append(", fund_list_date=").append(fund_list_date);
        sb.append(", exchange=").append(exchange);
        sb.append(", list_share=").append(list_share);
        sb.append(", liquidation_start_date=").append(liquidation_start_date);
        sb.append(", liquidation_end_date=").append(liquidation_end_date);
        sb.append(", nav_pub_type=").append(nav_pub_type);
        sb.append(", large_redem_pct=").append(large_redem_pct);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", push_search=").append(push_search);
        sb.append(", push_product=").append(push_product);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}