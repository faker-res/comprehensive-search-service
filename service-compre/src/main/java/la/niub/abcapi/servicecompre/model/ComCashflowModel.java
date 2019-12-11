package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ComCashflowModel implements Serializable {
    private Long id;

    private Long com_uni_code;

    private Date end_date;

    private Long reporttype;

    private Long principles;

    private Long consolidation;

    private Long currency_code;

    private Date announcement_date;

    private Long report_format;

    private BigDecimal sale_cash;

    private BigDecimal custom_to_netvalue;

    private BigDecimal borrow_netvalue;

    private BigDecimal borrow_other_netvalue;

    private BigDecimal trad_sec_cash_netr;

    private BigDecimal rec_insurance_cash;

    private BigDecimal rec_insurance_netvalue;

    private BigDecimal invest_netvalue;

    private BigDecimal taxes_refu;

    private BigDecimal trading_financial_dispose_netcash;

    private BigDecimal char_inte_cash;

    private BigDecimal cash_netvalue;

    private BigDecimal return_cash_netvalue;

    private BigDecimal rec_other_cash;

    private BigDecimal special_items_ocif;

    private BigDecimal adjustment_items_ocif;

    private BigDecimal bussiness_cash_total;

    private BigDecimal buy_for_cash;

    private BigDecimal custom_netvalue;

    private BigDecimal pay_contra_settle_cash;

    private BigDecimal pay_interest_cash;

    private BigDecimal pay_profit_cash;

    private BigDecimal pay_com_cash;

    private BigDecimal trad_sec_net_decr;

    private BigDecimal bank_cash_netvalue;

    private BigDecimal pay_emp_cash;

    private BigDecimal pay_tax;

    private BigDecimal pay_other_cash;

    private BigDecimal special_items_ocof;

    private BigDecimal adjustment_items_ocof;

    private BigDecimal bussiness_cash_output;

    private BigDecimal special_bussiness_cash_net;

    private BigDecimal bussiness_cash_netvalue;

    private BigDecimal rec_invest_cash;

    private BigDecimal invest_rec_cash;

    private BigDecimal dispose_asset_netvalue;

    private BigDecimal subs_net_cash;

    private BigDecimal rec_otherinvest_cash;

    private BigDecimal special_items_cash;

    private BigDecimal adjustmen_items_cash;

    private BigDecimal invest_cash_total;

    private BigDecimal invest_pay_cash;

    private BigDecimal loan_net_addvalue;

    private BigDecimal subs_pay_cash;

    private BigDecimal disp_subs_pay_cash;

    private BigDecimal pay_otherinvest_cash;

    private BigDecimal special_items_icif;

    private BigDecimal adjustmen_items_icif;

    private BigDecimal invest_cash_output;

    private BigDecimal invest_cash_netvalue_special_item;

    private BigDecimal invest_cash_netvalue;

    private BigDecimal rec_invest_reccash;

    private BigDecimal cash_for_holder_invest;

    private BigDecimal rec_borrow_cash;

    private BigDecimal rec_other_relatecash;

    private BigDecimal publish_rec_cash;

    private BigDecimal special_items_fcif;

    private BigDecimal adjustment_items_fcif;

    private BigDecimal borrow_cash_total;

    private BigDecimal pay_debet_cash;

    private BigDecimal interest_pay_cash;

    private BigDecimal profit_for_holder;

    private BigDecimal pay_other_relatecash;

    private BigDecimal special_items_fcof;

    private BigDecimal adjustment_items_fcof;

    private BigDecimal borrow_cash_outtotal;

    private BigDecimal special_borrow_netcash;

    private BigDecimal borrow_cash_netvalue;

    private BigDecimal rate_to_cash;

    private BigDecimal cash_to_netadd;

    private BigDecimal origin_cash;

    private BigDecimal last_cash;

    private BigDecimal net_profit;

    private BigDecimal plus_asset_loss;

    private BigDecimal asset_depr;

    private BigDecimal intangible_asset_discount;

    private BigDecimal long_cost_discount;

    private BigDecimal asset_loss;

    private BigDecimal fix_asset_loss;

    private BigDecimal value_change_loss;

    private BigDecimal fin_cost;

    private BigDecimal invest_loss;

    private BigDecimal exch_gain_loss;

    private BigDecimal deferred_taxes_asset_chg;

    private BigDecimal deferred_taxes_liabl_chg;

    private BigDecimal stock_reduce;

    private BigDecimal rec_project_reduce;

    private BigDecimal pay_project_add;

    private BigDecimal other;

    private BigDecimal special_bussiness_cashnet;

    private BigDecimal balance_account_bussiness_cashnet;

    private BigDecimal indirect_management_cash_netvalue;

    private BigDecimal debt_to_capital;

    private BigDecimal debt_one_year;

    private BigDecimal cash_to_asset;

    private BigDecimal last_cash_value;

    private BigDecimal origin_cash_value;

    private BigDecimal last_cash_equiv_value;

    private BigDecimal origin_cash_equiv_value;

    private BigDecimal cash_equ_net_increase_special;

    private BigDecimal cash_equ_net_increase_balance;

    private BigDecimal indirect_cash_equiv_netvalue;

    private String whether_published;

    private String special_case_description;

    private BigDecimal paid_expenses_reduced;

    private BigDecimal paid_expenses_add;

    private BigDecimal net_increase_in_special;

    private BigDecimal net_increase_in_balance;

    private BigDecimal end_period_special;

    private BigDecimal end_period_balance;

    private BigDecimal net_reduce_net_capital;

    private BigDecimal net_increase_net_capital;

    private String come_source;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getReporttype() {
        return reporttype;
    }

    public void setReporttype(Long reporttype) {
        this.reporttype = reporttype;
    }

    public Long getPrinciples() {
        return principles;
    }

    public void setPrinciples(Long principles) {
        this.principles = principles;
    }

    public Long getConsolidation() {
        return consolidation;
    }

    public void setConsolidation(Long consolidation) {
        this.consolidation = consolidation;
    }

    public Long getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(Long currency_code) {
        this.currency_code = currency_code;
    }

    public Date getAnnouncement_date() {
        return announcement_date;
    }

    public void setAnnouncement_date(Date announcement_date) {
        this.announcement_date = announcement_date;
    }

    public Long getReport_format() {
        return report_format;
    }

    public void setReport_format(Long report_format) {
        this.report_format = report_format;
    }

    public BigDecimal getSale_cash() {
        return sale_cash;
    }

    public void setSale_cash(BigDecimal sale_cash) {
        this.sale_cash = sale_cash;
    }

    public BigDecimal getCustom_to_netvalue() {
        return custom_to_netvalue;
    }

    public void setCustom_to_netvalue(BigDecimal custom_to_netvalue) {
        this.custom_to_netvalue = custom_to_netvalue;
    }

    public BigDecimal getBorrow_netvalue() {
        return borrow_netvalue;
    }

    public void setBorrow_netvalue(BigDecimal borrow_netvalue) {
        this.borrow_netvalue = borrow_netvalue;
    }

    public BigDecimal getBorrow_other_netvalue() {
        return borrow_other_netvalue;
    }

    public void setBorrow_other_netvalue(BigDecimal borrow_other_netvalue) {
        this.borrow_other_netvalue = borrow_other_netvalue;
    }

    public BigDecimal getTrad_sec_cash_netr() {
        return trad_sec_cash_netr;
    }

    public void setTrad_sec_cash_netr(BigDecimal trad_sec_cash_netr) {
        this.trad_sec_cash_netr = trad_sec_cash_netr;
    }

    public BigDecimal getRec_insurance_cash() {
        return rec_insurance_cash;
    }

    public void setRec_insurance_cash(BigDecimal rec_insurance_cash) {
        this.rec_insurance_cash = rec_insurance_cash;
    }

    public BigDecimal getRec_insurance_netvalue() {
        return rec_insurance_netvalue;
    }

    public void setRec_insurance_netvalue(BigDecimal rec_insurance_netvalue) {
        this.rec_insurance_netvalue = rec_insurance_netvalue;
    }

    public BigDecimal getInvest_netvalue() {
        return invest_netvalue;
    }

    public void setInvest_netvalue(BigDecimal invest_netvalue) {
        this.invest_netvalue = invest_netvalue;
    }

    public BigDecimal getTaxes_refu() {
        return taxes_refu;
    }

    public void setTaxes_refu(BigDecimal taxes_refu) {
        this.taxes_refu = taxes_refu;
    }

    public BigDecimal getTrading_financial_dispose_netcash() {
        return trading_financial_dispose_netcash;
    }

    public void setTrading_financial_dispose_netcash(BigDecimal trading_financial_dispose_netcash) {
        this.trading_financial_dispose_netcash = trading_financial_dispose_netcash;
    }

    public BigDecimal getChar_inte_cash() {
        return char_inte_cash;
    }

    public void setChar_inte_cash(BigDecimal char_inte_cash) {
        this.char_inte_cash = char_inte_cash;
    }

    public BigDecimal getCash_netvalue() {
        return cash_netvalue;
    }

    public void setCash_netvalue(BigDecimal cash_netvalue) {
        this.cash_netvalue = cash_netvalue;
    }

    public BigDecimal getReturn_cash_netvalue() {
        return return_cash_netvalue;
    }

    public void setReturn_cash_netvalue(BigDecimal return_cash_netvalue) {
        this.return_cash_netvalue = return_cash_netvalue;
    }

    public BigDecimal getRec_other_cash() {
        return rec_other_cash;
    }

    public void setRec_other_cash(BigDecimal rec_other_cash) {
        this.rec_other_cash = rec_other_cash;
    }

    public BigDecimal getSpecial_items_ocif() {
        return special_items_ocif;
    }

    public void setSpecial_items_ocif(BigDecimal special_items_ocif) {
        this.special_items_ocif = special_items_ocif;
    }

    public BigDecimal getAdjustment_items_ocif() {
        return adjustment_items_ocif;
    }

    public void setAdjustment_items_ocif(BigDecimal adjustment_items_ocif) {
        this.adjustment_items_ocif = adjustment_items_ocif;
    }

    public BigDecimal getBussiness_cash_total() {
        return bussiness_cash_total;
    }

    public void setBussiness_cash_total(BigDecimal bussiness_cash_total) {
        this.bussiness_cash_total = bussiness_cash_total;
    }

    public BigDecimal getBuy_for_cash() {
        return buy_for_cash;
    }

    public void setBuy_for_cash(BigDecimal buy_for_cash) {
        this.buy_for_cash = buy_for_cash;
    }

    public BigDecimal getCustom_netvalue() {
        return custom_netvalue;
    }

    public void setCustom_netvalue(BigDecimal custom_netvalue) {
        this.custom_netvalue = custom_netvalue;
    }

    public BigDecimal getPay_contra_settle_cash() {
        return pay_contra_settle_cash;
    }

    public void setPay_contra_settle_cash(BigDecimal pay_contra_settle_cash) {
        this.pay_contra_settle_cash = pay_contra_settle_cash;
    }

    public BigDecimal getPay_interest_cash() {
        return pay_interest_cash;
    }

    public void setPay_interest_cash(BigDecimal pay_interest_cash) {
        this.pay_interest_cash = pay_interest_cash;
    }

    public BigDecimal getPay_profit_cash() {
        return pay_profit_cash;
    }

    public void setPay_profit_cash(BigDecimal pay_profit_cash) {
        this.pay_profit_cash = pay_profit_cash;
    }

    public BigDecimal getPay_com_cash() {
        return pay_com_cash;
    }

    public void setPay_com_cash(BigDecimal pay_com_cash) {
        this.pay_com_cash = pay_com_cash;
    }

    public BigDecimal getTrad_sec_net_decr() {
        return trad_sec_net_decr;
    }

    public void setTrad_sec_net_decr(BigDecimal trad_sec_net_decr) {
        this.trad_sec_net_decr = trad_sec_net_decr;
    }

    public BigDecimal getBank_cash_netvalue() {
        return bank_cash_netvalue;
    }

    public void setBank_cash_netvalue(BigDecimal bank_cash_netvalue) {
        this.bank_cash_netvalue = bank_cash_netvalue;
    }

    public BigDecimal getPay_emp_cash() {
        return pay_emp_cash;
    }

    public void setPay_emp_cash(BigDecimal pay_emp_cash) {
        this.pay_emp_cash = pay_emp_cash;
    }

    public BigDecimal getPay_tax() {
        return pay_tax;
    }

    public void setPay_tax(BigDecimal pay_tax) {
        this.pay_tax = pay_tax;
    }

    public BigDecimal getPay_other_cash() {
        return pay_other_cash;
    }

    public void setPay_other_cash(BigDecimal pay_other_cash) {
        this.pay_other_cash = pay_other_cash;
    }

    public BigDecimal getSpecial_items_ocof() {
        return special_items_ocof;
    }

    public void setSpecial_items_ocof(BigDecimal special_items_ocof) {
        this.special_items_ocof = special_items_ocof;
    }

    public BigDecimal getAdjustment_items_ocof() {
        return adjustment_items_ocof;
    }

    public void setAdjustment_items_ocof(BigDecimal adjustment_items_ocof) {
        this.adjustment_items_ocof = adjustment_items_ocof;
    }

    public BigDecimal getBussiness_cash_output() {
        return bussiness_cash_output;
    }

    public void setBussiness_cash_output(BigDecimal bussiness_cash_output) {
        this.bussiness_cash_output = bussiness_cash_output;
    }

    public BigDecimal getSpecial_bussiness_cash_net() {
        return special_bussiness_cash_net;
    }

    public void setSpecial_bussiness_cash_net(BigDecimal special_bussiness_cash_net) {
        this.special_bussiness_cash_net = special_bussiness_cash_net;
    }

    public BigDecimal getBussiness_cash_netvalue() {
        return bussiness_cash_netvalue;
    }

    public void setBussiness_cash_netvalue(BigDecimal bussiness_cash_netvalue) {
        this.bussiness_cash_netvalue = bussiness_cash_netvalue;
    }

    public BigDecimal getRec_invest_cash() {
        return rec_invest_cash;
    }

    public void setRec_invest_cash(BigDecimal rec_invest_cash) {
        this.rec_invest_cash = rec_invest_cash;
    }

    public BigDecimal getInvest_rec_cash() {
        return invest_rec_cash;
    }

    public void setInvest_rec_cash(BigDecimal invest_rec_cash) {
        this.invest_rec_cash = invest_rec_cash;
    }

    public BigDecimal getDispose_asset_netvalue() {
        return dispose_asset_netvalue;
    }

    public void setDispose_asset_netvalue(BigDecimal dispose_asset_netvalue) {
        this.dispose_asset_netvalue = dispose_asset_netvalue;
    }

    public BigDecimal getSubs_net_cash() {
        return subs_net_cash;
    }

    public void setSubs_net_cash(BigDecimal subs_net_cash) {
        this.subs_net_cash = subs_net_cash;
    }

    public BigDecimal getRec_otherinvest_cash() {
        return rec_otherinvest_cash;
    }

    public void setRec_otherinvest_cash(BigDecimal rec_otherinvest_cash) {
        this.rec_otherinvest_cash = rec_otherinvest_cash;
    }

    public BigDecimal getSpecial_items_cash() {
        return special_items_cash;
    }

    public void setSpecial_items_cash(BigDecimal special_items_cash) {
        this.special_items_cash = special_items_cash;
    }

    public BigDecimal getAdjustmen_items_cash() {
        return adjustmen_items_cash;
    }

    public void setAdjustmen_items_cash(BigDecimal adjustmen_items_cash) {
        this.adjustmen_items_cash = adjustmen_items_cash;
    }

    public BigDecimal getInvest_cash_total() {
        return invest_cash_total;
    }

    public void setInvest_cash_total(BigDecimal invest_cash_total) {
        this.invest_cash_total = invest_cash_total;
    }

    public BigDecimal getInvest_pay_cash() {
        return invest_pay_cash;
    }

    public void setInvest_pay_cash(BigDecimal invest_pay_cash) {
        this.invest_pay_cash = invest_pay_cash;
    }

    public BigDecimal getLoan_net_addvalue() {
        return loan_net_addvalue;
    }

    public void setLoan_net_addvalue(BigDecimal loan_net_addvalue) {
        this.loan_net_addvalue = loan_net_addvalue;
    }

    public BigDecimal getSubs_pay_cash() {
        return subs_pay_cash;
    }

    public void setSubs_pay_cash(BigDecimal subs_pay_cash) {
        this.subs_pay_cash = subs_pay_cash;
    }

    public BigDecimal getDisp_subs_pay_cash() {
        return disp_subs_pay_cash;
    }

    public void setDisp_subs_pay_cash(BigDecimal disp_subs_pay_cash) {
        this.disp_subs_pay_cash = disp_subs_pay_cash;
    }

    public BigDecimal getPay_otherinvest_cash() {
        return pay_otherinvest_cash;
    }

    public void setPay_otherinvest_cash(BigDecimal pay_otherinvest_cash) {
        this.pay_otherinvest_cash = pay_otherinvest_cash;
    }

    public BigDecimal getSpecial_items_icif() {
        return special_items_icif;
    }

    public void setSpecial_items_icif(BigDecimal special_items_icif) {
        this.special_items_icif = special_items_icif;
    }

    public BigDecimal getAdjustmen_items_icif() {
        return adjustmen_items_icif;
    }

    public void setAdjustmen_items_icif(BigDecimal adjustmen_items_icif) {
        this.adjustmen_items_icif = adjustmen_items_icif;
    }

    public BigDecimal getInvest_cash_output() {
        return invest_cash_output;
    }

    public void setInvest_cash_output(BigDecimal invest_cash_output) {
        this.invest_cash_output = invest_cash_output;
    }

    public BigDecimal getInvest_cash_netvalue_special_item() {
        return invest_cash_netvalue_special_item;
    }

    public void setInvest_cash_netvalue_special_item(BigDecimal invest_cash_netvalue_special_item) {
        this.invest_cash_netvalue_special_item = invest_cash_netvalue_special_item;
    }

    public BigDecimal getInvest_cash_netvalue() {
        return invest_cash_netvalue;
    }

    public void setInvest_cash_netvalue(BigDecimal invest_cash_netvalue) {
        this.invest_cash_netvalue = invest_cash_netvalue;
    }

    public BigDecimal getRec_invest_reccash() {
        return rec_invest_reccash;
    }

    public void setRec_invest_reccash(BigDecimal rec_invest_reccash) {
        this.rec_invest_reccash = rec_invest_reccash;
    }

    public BigDecimal getCash_for_holder_invest() {
        return cash_for_holder_invest;
    }

    public void setCash_for_holder_invest(BigDecimal cash_for_holder_invest) {
        this.cash_for_holder_invest = cash_for_holder_invest;
    }

    public BigDecimal getRec_borrow_cash() {
        return rec_borrow_cash;
    }

    public void setRec_borrow_cash(BigDecimal rec_borrow_cash) {
        this.rec_borrow_cash = rec_borrow_cash;
    }

    public BigDecimal getRec_other_relatecash() {
        return rec_other_relatecash;
    }

    public void setRec_other_relatecash(BigDecimal rec_other_relatecash) {
        this.rec_other_relatecash = rec_other_relatecash;
    }

    public BigDecimal getPublish_rec_cash() {
        return publish_rec_cash;
    }

    public void setPublish_rec_cash(BigDecimal publish_rec_cash) {
        this.publish_rec_cash = publish_rec_cash;
    }

    public BigDecimal getSpecial_items_fcif() {
        return special_items_fcif;
    }

    public void setSpecial_items_fcif(BigDecimal special_items_fcif) {
        this.special_items_fcif = special_items_fcif;
    }

    public BigDecimal getAdjustment_items_fcif() {
        return adjustment_items_fcif;
    }

    public void setAdjustment_items_fcif(BigDecimal adjustment_items_fcif) {
        this.adjustment_items_fcif = adjustment_items_fcif;
    }

    public BigDecimal getBorrow_cash_total() {
        return borrow_cash_total;
    }

    public void setBorrow_cash_total(BigDecimal borrow_cash_total) {
        this.borrow_cash_total = borrow_cash_total;
    }

    public BigDecimal getPay_debet_cash() {
        return pay_debet_cash;
    }

    public void setPay_debet_cash(BigDecimal pay_debet_cash) {
        this.pay_debet_cash = pay_debet_cash;
    }

    public BigDecimal getInterest_pay_cash() {
        return interest_pay_cash;
    }

    public void setInterest_pay_cash(BigDecimal interest_pay_cash) {
        this.interest_pay_cash = interest_pay_cash;
    }

    public BigDecimal getProfit_for_holder() {
        return profit_for_holder;
    }

    public void setProfit_for_holder(BigDecimal profit_for_holder) {
        this.profit_for_holder = profit_for_holder;
    }

    public BigDecimal getPay_other_relatecash() {
        return pay_other_relatecash;
    }

    public void setPay_other_relatecash(BigDecimal pay_other_relatecash) {
        this.pay_other_relatecash = pay_other_relatecash;
    }

    public BigDecimal getSpecial_items_fcof() {
        return special_items_fcof;
    }

    public void setSpecial_items_fcof(BigDecimal special_items_fcof) {
        this.special_items_fcof = special_items_fcof;
    }

    public BigDecimal getAdjustment_items_fcof() {
        return adjustment_items_fcof;
    }

    public void setAdjustment_items_fcof(BigDecimal adjustment_items_fcof) {
        this.adjustment_items_fcof = adjustment_items_fcof;
    }

    public BigDecimal getBorrow_cash_outtotal() {
        return borrow_cash_outtotal;
    }

    public void setBorrow_cash_outtotal(BigDecimal borrow_cash_outtotal) {
        this.borrow_cash_outtotal = borrow_cash_outtotal;
    }

    public BigDecimal getSpecial_borrow_netcash() {
        return special_borrow_netcash;
    }

    public void setSpecial_borrow_netcash(BigDecimal special_borrow_netcash) {
        this.special_borrow_netcash = special_borrow_netcash;
    }

    public BigDecimal getBorrow_cash_netvalue() {
        return borrow_cash_netvalue;
    }

    public void setBorrow_cash_netvalue(BigDecimal borrow_cash_netvalue) {
        this.borrow_cash_netvalue = borrow_cash_netvalue;
    }

    public BigDecimal getRate_to_cash() {
        return rate_to_cash;
    }

    public void setRate_to_cash(BigDecimal rate_to_cash) {
        this.rate_to_cash = rate_to_cash;
    }

    public BigDecimal getCash_to_netadd() {
        return cash_to_netadd;
    }

    public void setCash_to_netadd(BigDecimal cash_to_netadd) {
        this.cash_to_netadd = cash_to_netadd;
    }

    public BigDecimal getOrigin_cash() {
        return origin_cash;
    }

    public void setOrigin_cash(BigDecimal origin_cash) {
        this.origin_cash = origin_cash;
    }

    public BigDecimal getLast_cash() {
        return last_cash;
    }

    public void setLast_cash(BigDecimal last_cash) {
        this.last_cash = last_cash;
    }

    public BigDecimal getNet_profit() {
        return net_profit;
    }

    public void setNet_profit(BigDecimal net_profit) {
        this.net_profit = net_profit;
    }

    public BigDecimal getPlus_asset_loss() {
        return plus_asset_loss;
    }

    public void setPlus_asset_loss(BigDecimal plus_asset_loss) {
        this.plus_asset_loss = plus_asset_loss;
    }

    public BigDecimal getAsset_depr() {
        return asset_depr;
    }

    public void setAsset_depr(BigDecimal asset_depr) {
        this.asset_depr = asset_depr;
    }

    public BigDecimal getIntangible_asset_discount() {
        return intangible_asset_discount;
    }

    public void setIntangible_asset_discount(BigDecimal intangible_asset_discount) {
        this.intangible_asset_discount = intangible_asset_discount;
    }

    public BigDecimal getLong_cost_discount() {
        return long_cost_discount;
    }

    public void setLong_cost_discount(BigDecimal long_cost_discount) {
        this.long_cost_discount = long_cost_discount;
    }

    public BigDecimal getAsset_loss() {
        return asset_loss;
    }

    public void setAsset_loss(BigDecimal asset_loss) {
        this.asset_loss = asset_loss;
    }

    public BigDecimal getFix_asset_loss() {
        return fix_asset_loss;
    }

    public void setFix_asset_loss(BigDecimal fix_asset_loss) {
        this.fix_asset_loss = fix_asset_loss;
    }

    public BigDecimal getValue_change_loss() {
        return value_change_loss;
    }

    public void setValue_change_loss(BigDecimal value_change_loss) {
        this.value_change_loss = value_change_loss;
    }

    public BigDecimal getFin_cost() {
        return fin_cost;
    }

    public void setFin_cost(BigDecimal fin_cost) {
        this.fin_cost = fin_cost;
    }

    public BigDecimal getInvest_loss() {
        return invest_loss;
    }

    public void setInvest_loss(BigDecimal invest_loss) {
        this.invest_loss = invest_loss;
    }

    public BigDecimal getExch_gain_loss() {
        return exch_gain_loss;
    }

    public void setExch_gain_loss(BigDecimal exch_gain_loss) {
        this.exch_gain_loss = exch_gain_loss;
    }

    public BigDecimal getDeferred_taxes_asset_chg() {
        return deferred_taxes_asset_chg;
    }

    public void setDeferred_taxes_asset_chg(BigDecimal deferred_taxes_asset_chg) {
        this.deferred_taxes_asset_chg = deferred_taxes_asset_chg;
    }

    public BigDecimal getDeferred_taxes_liabl_chg() {
        return deferred_taxes_liabl_chg;
    }

    public void setDeferred_taxes_liabl_chg(BigDecimal deferred_taxes_liabl_chg) {
        this.deferred_taxes_liabl_chg = deferred_taxes_liabl_chg;
    }

    public BigDecimal getStock_reduce() {
        return stock_reduce;
    }

    public void setStock_reduce(BigDecimal stock_reduce) {
        this.stock_reduce = stock_reduce;
    }

    public BigDecimal getRec_project_reduce() {
        return rec_project_reduce;
    }

    public void setRec_project_reduce(BigDecimal rec_project_reduce) {
        this.rec_project_reduce = rec_project_reduce;
    }

    public BigDecimal getPay_project_add() {
        return pay_project_add;
    }

    public void setPay_project_add(BigDecimal pay_project_add) {
        this.pay_project_add = pay_project_add;
    }

    public BigDecimal getOther() {
        return other;
    }

    public void setOther(BigDecimal other) {
        this.other = other;
    }

    public BigDecimal getSpecial_bussiness_cashnet() {
        return special_bussiness_cashnet;
    }

    public void setSpecial_bussiness_cashnet(BigDecimal special_bussiness_cashnet) {
        this.special_bussiness_cashnet = special_bussiness_cashnet;
    }

    public BigDecimal getBalance_account_bussiness_cashnet() {
        return balance_account_bussiness_cashnet;
    }

    public void setBalance_account_bussiness_cashnet(BigDecimal balance_account_bussiness_cashnet) {
        this.balance_account_bussiness_cashnet = balance_account_bussiness_cashnet;
    }

    public BigDecimal getIndirect_management_cash_netvalue() {
        return indirect_management_cash_netvalue;
    }

    public void setIndirect_management_cash_netvalue(BigDecimal indirect_management_cash_netvalue) {
        this.indirect_management_cash_netvalue = indirect_management_cash_netvalue;
    }

    public BigDecimal getDebt_to_capital() {
        return debt_to_capital;
    }

    public void setDebt_to_capital(BigDecimal debt_to_capital) {
        this.debt_to_capital = debt_to_capital;
    }

    public BigDecimal getDebt_one_year() {
        return debt_one_year;
    }

    public void setDebt_one_year(BigDecimal debt_one_year) {
        this.debt_one_year = debt_one_year;
    }

    public BigDecimal getCash_to_asset() {
        return cash_to_asset;
    }

    public void setCash_to_asset(BigDecimal cash_to_asset) {
        this.cash_to_asset = cash_to_asset;
    }

    public BigDecimal getLast_cash_value() {
        return last_cash_value;
    }

    public void setLast_cash_value(BigDecimal last_cash_value) {
        this.last_cash_value = last_cash_value;
    }

    public BigDecimal getOrigin_cash_value() {
        return origin_cash_value;
    }

    public void setOrigin_cash_value(BigDecimal origin_cash_value) {
        this.origin_cash_value = origin_cash_value;
    }

    public BigDecimal getLast_cash_equiv_value() {
        return last_cash_equiv_value;
    }

    public void setLast_cash_equiv_value(BigDecimal last_cash_equiv_value) {
        this.last_cash_equiv_value = last_cash_equiv_value;
    }

    public BigDecimal getOrigin_cash_equiv_value() {
        return origin_cash_equiv_value;
    }

    public void setOrigin_cash_equiv_value(BigDecimal origin_cash_equiv_value) {
        this.origin_cash_equiv_value = origin_cash_equiv_value;
    }

    public BigDecimal getCash_equ_net_increase_special() {
        return cash_equ_net_increase_special;
    }

    public void setCash_equ_net_increase_special(BigDecimal cash_equ_net_increase_special) {
        this.cash_equ_net_increase_special = cash_equ_net_increase_special;
    }

    public BigDecimal getCash_equ_net_increase_balance() {
        return cash_equ_net_increase_balance;
    }

    public void setCash_equ_net_increase_balance(BigDecimal cash_equ_net_increase_balance) {
        this.cash_equ_net_increase_balance = cash_equ_net_increase_balance;
    }

    public BigDecimal getIndirect_cash_equiv_netvalue() {
        return indirect_cash_equiv_netvalue;
    }

    public void setIndirect_cash_equiv_netvalue(BigDecimal indirect_cash_equiv_netvalue) {
        this.indirect_cash_equiv_netvalue = indirect_cash_equiv_netvalue;
    }

    public String getWhether_published() {
        return whether_published;
    }

    public void setWhether_published(String whether_published) {
        this.whether_published = whether_published == null ? null : whether_published.trim();
    }

    public String getSpecial_case_description() {
        return special_case_description;
    }

    public void setSpecial_case_description(String special_case_description) {
        this.special_case_description = special_case_description == null ? null : special_case_description.trim();
    }

    public BigDecimal getPaid_expenses_reduced() {
        return paid_expenses_reduced;
    }

    public void setPaid_expenses_reduced(BigDecimal paid_expenses_reduced) {
        this.paid_expenses_reduced = paid_expenses_reduced;
    }

    public BigDecimal getPaid_expenses_add() {
        return paid_expenses_add;
    }

    public void setPaid_expenses_add(BigDecimal paid_expenses_add) {
        this.paid_expenses_add = paid_expenses_add;
    }

    public BigDecimal getNet_increase_in_special() {
        return net_increase_in_special;
    }

    public void setNet_increase_in_special(BigDecimal net_increase_in_special) {
        this.net_increase_in_special = net_increase_in_special;
    }

    public BigDecimal getNet_increase_in_balance() {
        return net_increase_in_balance;
    }

    public void setNet_increase_in_balance(BigDecimal net_increase_in_balance) {
        this.net_increase_in_balance = net_increase_in_balance;
    }

    public BigDecimal getEnd_period_special() {
        return end_period_special;
    }

    public void setEnd_period_special(BigDecimal end_period_special) {
        this.end_period_special = end_period_special;
    }

    public BigDecimal getEnd_period_balance() {
        return end_period_balance;
    }

    public void setEnd_period_balance(BigDecimal end_period_balance) {
        this.end_period_balance = end_period_balance;
    }

    public BigDecimal getNet_reduce_net_capital() {
        return net_reduce_net_capital;
    }

    public void setNet_reduce_net_capital(BigDecimal net_reduce_net_capital) {
        this.net_reduce_net_capital = net_reduce_net_capital;
    }

    public BigDecimal getNet_increase_net_capital() {
        return net_increase_net_capital;
    }

    public void setNet_increase_net_capital(BigDecimal net_increase_net_capital) {
        this.net_increase_net_capital = net_increase_net_capital;
    }

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source == null ? null : come_source.trim();
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
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public BigDecimal getValueByColumn(String name){
        if(name.equals("sale_cash")){
            return this.sale_cash;
        }
        if(name.equals("bussiness_cash_total")){
            return this.bussiness_cash_total;
        }
        if(name.equals("bussiness_cash_output")){
            return this.bussiness_cash_output;
        }
        if(name.equals("bussiness_cash_netvalue")){
            return this.bussiness_cash_netvalue;
        }
        return null;
    }

}