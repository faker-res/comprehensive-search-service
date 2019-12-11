package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ComProfitModel implements Serializable {
    private Long id;

    private Long com_uni_code;

    private Date end_date;

    private Long report_type;

    private Long principles;

    private Long consolidation;

    private Long currency_code;

    private Long report_format;

    private Date announcement_date;

    private BigDecimal overall_income;

    private BigDecimal main_income;

    private BigDecimal interest_netincome;

    private BigDecimal interest_income;

    private BigDecimal interest_cost;

    private BigDecimal commission_netincome;

    private BigDecimal commission_income;

    private BigDecimal commission_cost;

    private BigDecimal earn_insurance;

    private BigDecimal insurance_bus_income;

    private BigDecimal premium_income;

    private BigDecimal separate_premiums;

    private BigDecimal extraction_unexpired;

    private BigDecimal brokerage_fee_income;

    private BigDecimal netincome_of_investment_banking_fees;

    private BigDecimal netincome_of_asset_management_fees;

    private BigDecimal invest_income;

    private BigDecimal relate_invest_income;

    private BigDecimal gain_loss_income;

    private BigDecimal value_gains;

    private BigDecimal other_income;

    private BigDecimal operating_revenue_special_course;

    private BigDecimal operating_revenue_balance_course;

    private BigDecimal overall_cost;

    private BigDecimal main_cost;

    private BigDecimal operat_expenses;

    private BigDecimal canel_insurance_money;

    private BigDecimal pay_expenses;

    private BigDecimal spread_pay_expenses;

    private BigDecimal insurance_liability;

    private BigDecimal spread_insurance_liability;

    private BigDecimal insurance_cost;

    private BigDecimal reduce_insurance_cost;

    private BigDecimal tax;

    private BigDecimal operating_manage_cost;

    private BigDecimal spread_premium;

    private BigDecimal sale_cost;

    private BigDecimal manage_cost;

    private BigDecimal fin_cost;

    private BigDecimal asset_loss;

    private BigDecimal other_bus_cost;

    private BigDecimal total_cost_of_special_subjects;

    private BigDecimal total_cost_of_balance_subjects;

    private BigDecimal overall_profit;

    private BigDecimal addition_income;

    private BigDecimal addition_cost;

    private BigDecimal interest_dispose;

    private BigDecimal disposal_noncurrent_assets;

    private BigDecimal operating_profit_special_subjects;

    private BigDecimal operating_profit_balance_subjects;

    private BigDecimal profit_total;

    private BigDecimal reduce_tax;

    private BigDecimal profit_total_special_subjects;

    private BigDecimal profit_total_balance_subjects;

    private BigDecimal netprofit;

    private BigDecimal parent_netprofit;

    private BigDecimal minority_loss;

    private BigDecimal netprofit_special_subjects;

    private BigDecimal netprofit_balance_subjects;

    private BigDecimal basic_perstock_income;

    private BigDecimal reduce_perstock_income;

    private BigDecimal other_composite_loss;

    private BigDecimal parent_other_com_income;

    private BigDecimal income_for_holer_other_com_income;

    private BigDecimal all_income_total;

    private BigDecimal income_for_parent;

    private BigDecimal income_for_minority;

    private String special_case_description;

    private BigDecimal other_income_loss;

    private BigDecimal recalculate_change_plan;

    private BigDecimal equity_method;

    private BigDecimal others_1;

    private BigDecimal other_income_profit;

    private BigDecimal share_of_equity_method;

    private BigDecimal sale_financial_assets;

    private BigDecimal sale_financial;

    private BigDecimal cashflow_hedge_gains;

    private BigDecimal effective_portion_cashflow;

    private BigDecimal others_2;

    private String whether_published;

    private String come_source;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public ComProfitModel(Long id, Long com_uni_code, Date end_date, Long report_type, Long principles, Long consolidation, Long currency_code, Long report_format, Date announcement_date, BigDecimal overall_income, BigDecimal main_income, BigDecimal interest_netincome, BigDecimal interest_income, BigDecimal interest_cost, BigDecimal commission_netincome, BigDecimal commission_income, BigDecimal commission_cost, BigDecimal earn_insurance, BigDecimal insurance_bus_income, BigDecimal premium_income, BigDecimal separate_premiums, BigDecimal extraction_unexpired, BigDecimal brokerage_fee_income, BigDecimal netincome_of_investment_banking_fees, BigDecimal netincome_of_asset_management_fees, BigDecimal invest_income, BigDecimal relate_invest_income, BigDecimal gain_loss_income, BigDecimal value_gains, BigDecimal other_income, BigDecimal operating_revenue_special_course, BigDecimal operating_revenue_balance_course, BigDecimal overall_cost, BigDecimal main_cost, BigDecimal operat_expenses, BigDecimal canel_insurance_money, BigDecimal pay_expenses, BigDecimal spread_pay_expenses, BigDecimal insurance_liability, BigDecimal spread_insurance_liability, BigDecimal insurance_cost, BigDecimal reduce_insurance_cost, BigDecimal tax, BigDecimal operating_manage_cost, BigDecimal spread_premium, BigDecimal sale_cost, BigDecimal manage_cost, BigDecimal fin_cost, BigDecimal asset_loss, BigDecimal other_bus_cost, BigDecimal total_cost_of_special_subjects, BigDecimal total_cost_of_balance_subjects, BigDecimal overall_profit, BigDecimal addition_income, BigDecimal addition_cost, BigDecimal interest_dispose, BigDecimal disposal_noncurrent_assets, BigDecimal operating_profit_special_subjects, BigDecimal operating_profit_balance_subjects, BigDecimal profit_total, BigDecimal reduce_tax, BigDecimal profit_total_special_subjects, BigDecimal profit_total_balance_subjects, BigDecimal netprofit, BigDecimal parent_netprofit, BigDecimal minority_loss, BigDecimal netprofit_special_subjects, BigDecimal netprofit_balance_subjects, BigDecimal basic_perstock_income, BigDecimal reduce_perstock_income, BigDecimal other_composite_loss, BigDecimal parent_other_com_income, BigDecimal income_for_holer_other_com_income, BigDecimal all_income_total, BigDecimal income_for_parent, BigDecimal income_for_minority, String special_case_description, BigDecimal other_income_loss, BigDecimal recalculate_change_plan, BigDecimal equity_method, BigDecimal others_1, BigDecimal other_income_profit, BigDecimal share_of_equity_method, BigDecimal sale_financial_assets, BigDecimal sale_financial, BigDecimal cashflow_hedge_gains, BigDecimal effective_portion_cashflow, BigDecimal others_2, String whether_published, String come_source, Date createtime, Date updatetime, String status, String remark, String creator, String editor) {
        this.id = id;
        this.com_uni_code = com_uni_code;
        this.end_date = end_date;
        this.report_type = report_type;
        this.principles = principles;
        this.consolidation = consolidation;
        this.currency_code = currency_code;
        this.report_format = report_format;
        this.announcement_date = announcement_date;
        this.overall_income = overall_income;
        this.main_income = main_income;
        this.interest_netincome = interest_netincome;
        this.interest_income = interest_income;
        this.interest_cost = interest_cost;
        this.commission_netincome = commission_netincome;
        this.commission_income = commission_income;
        this.commission_cost = commission_cost;
        this.earn_insurance = earn_insurance;
        this.insurance_bus_income = insurance_bus_income;
        this.premium_income = premium_income;
        this.separate_premiums = separate_premiums;
        this.extraction_unexpired = extraction_unexpired;
        this.brokerage_fee_income = brokerage_fee_income;
        this.netincome_of_investment_banking_fees = netincome_of_investment_banking_fees;
        this.netincome_of_asset_management_fees = netincome_of_asset_management_fees;
        this.invest_income = invest_income;
        this.relate_invest_income = relate_invest_income;
        this.gain_loss_income = gain_loss_income;
        this.value_gains = value_gains;
        this.other_income = other_income;
        this.operating_revenue_special_course = operating_revenue_special_course;
        this.operating_revenue_balance_course = operating_revenue_balance_course;
        this.overall_cost = overall_cost;
        this.main_cost = main_cost;
        this.operat_expenses = operat_expenses;
        this.canel_insurance_money = canel_insurance_money;
        this.pay_expenses = pay_expenses;
        this.spread_pay_expenses = spread_pay_expenses;
        this.insurance_liability = insurance_liability;
        this.spread_insurance_liability = spread_insurance_liability;
        this.insurance_cost = insurance_cost;
        this.reduce_insurance_cost = reduce_insurance_cost;
        this.tax = tax;
        this.operating_manage_cost = operating_manage_cost;
        this.spread_premium = spread_premium;
        this.sale_cost = sale_cost;
        this.manage_cost = manage_cost;
        this.fin_cost = fin_cost;
        this.asset_loss = asset_loss;
        this.other_bus_cost = other_bus_cost;
        this.total_cost_of_special_subjects = total_cost_of_special_subjects;
        this.total_cost_of_balance_subjects = total_cost_of_balance_subjects;
        this.overall_profit = overall_profit;
        this.addition_income = addition_income;
        this.addition_cost = addition_cost;
        this.interest_dispose = interest_dispose;
        this.disposal_noncurrent_assets = disposal_noncurrent_assets;
        this.operating_profit_special_subjects = operating_profit_special_subjects;
        this.operating_profit_balance_subjects = operating_profit_balance_subjects;
        this.profit_total = profit_total;
        this.reduce_tax = reduce_tax;
        this.profit_total_special_subjects = profit_total_special_subjects;
        this.profit_total_balance_subjects = profit_total_balance_subjects;
        this.netprofit = netprofit;
        this.parent_netprofit = parent_netprofit;
        this.minority_loss = minority_loss;
        this.netprofit_special_subjects = netprofit_special_subjects;
        this.netprofit_balance_subjects = netprofit_balance_subjects;
        this.basic_perstock_income = basic_perstock_income;
        this.reduce_perstock_income = reduce_perstock_income;
        this.other_composite_loss = other_composite_loss;
        this.parent_other_com_income = parent_other_com_income;
        this.income_for_holer_other_com_income = income_for_holer_other_com_income;
        this.all_income_total = all_income_total;
        this.income_for_parent = income_for_parent;
        this.income_for_minority = income_for_minority;
        this.special_case_description = special_case_description;
        this.other_income_loss = other_income_loss;
        this.recalculate_change_plan = recalculate_change_plan;
        this.equity_method = equity_method;
        this.others_1 = others_1;
        this.other_income_profit = other_income_profit;
        this.share_of_equity_method = share_of_equity_method;
        this.sale_financial_assets = sale_financial_assets;
        this.sale_financial = sale_financial;
        this.cashflow_hedge_gains = cashflow_hedge_gains;
        this.effective_portion_cashflow = effective_portion_cashflow;
        this.others_2 = others_2;
        this.whether_published = whether_published;
        this.come_source = come_source;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
    }

    public ComProfitModel() {
        super();
    }

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

    public Long getReport_type() {
        return report_type;
    }

    public void setReport_type(Long report_type) {
        this.report_type = report_type;
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

    public Long getReport_format() {
        return report_format;
    }

    public void setReport_format(Long report_format) {
        this.report_format = report_format;
    }

    public Date getAnnouncement_date() {
        return announcement_date;
    }

    public void setAnnouncement_date(Date announcement_date) {
        this.announcement_date = announcement_date;
    }

    public BigDecimal getOverall_income() {
        return overall_income;
    }

    public void setOverall_income(BigDecimal overall_income) {
        this.overall_income = overall_income;
    }

    public BigDecimal getMain_income() {
        return main_income;
    }

    public void setMain_income(BigDecimal main_income) {
        this.main_income = main_income;
    }

    public BigDecimal getInterest_netincome() {
        return interest_netincome;
    }

    public void setInterest_netincome(BigDecimal interest_netincome) {
        this.interest_netincome = interest_netincome;
    }

    public BigDecimal getInterest_income() {
        return interest_income;
    }

    public void setInterest_income(BigDecimal interest_income) {
        this.interest_income = interest_income;
    }

    public BigDecimal getInterest_cost() {
        return interest_cost;
    }

    public void setInterest_cost(BigDecimal interest_cost) {
        this.interest_cost = interest_cost;
    }

    public BigDecimal getCommission_netincome() {
        return commission_netincome;
    }

    public void setCommission_netincome(BigDecimal commission_netincome) {
        this.commission_netincome = commission_netincome;
    }

    public BigDecimal getCommission_income() {
        return commission_income;
    }

    public void setCommission_income(BigDecimal commission_income) {
        this.commission_income = commission_income;
    }

    public BigDecimal getCommission_cost() {
        return commission_cost;
    }

    public void setCommission_cost(BigDecimal commission_cost) {
        this.commission_cost = commission_cost;
    }

    public BigDecimal getEarn_insurance() {
        return earn_insurance;
    }

    public void setEarn_insurance(BigDecimal earn_insurance) {
        this.earn_insurance = earn_insurance;
    }

    public BigDecimal getInsurance_bus_income() {
        return insurance_bus_income;
    }

    public void setInsurance_bus_income(BigDecimal insurance_bus_income) {
        this.insurance_bus_income = insurance_bus_income;
    }

    public BigDecimal getPremium_income() {
        return premium_income;
    }

    public void setPremium_income(BigDecimal premium_income) {
        this.premium_income = premium_income;
    }

    public BigDecimal getSeparate_premiums() {
        return separate_premiums;
    }

    public void setSeparate_premiums(BigDecimal separate_premiums) {
        this.separate_premiums = separate_premiums;
    }

    public BigDecimal getExtraction_unexpired() {
        return extraction_unexpired;
    }

    public void setExtraction_unexpired(BigDecimal extraction_unexpired) {
        this.extraction_unexpired = extraction_unexpired;
    }

    public BigDecimal getBrokerage_fee_income() {
        return brokerage_fee_income;
    }

    public void setBrokerage_fee_income(BigDecimal brokerage_fee_income) {
        this.brokerage_fee_income = brokerage_fee_income;
    }

    public BigDecimal getNetincome_of_investment_banking_fees() {
        return netincome_of_investment_banking_fees;
    }

    public void setNetincome_of_investment_banking_fees(BigDecimal netincome_of_investment_banking_fees) {
        this.netincome_of_investment_banking_fees = netincome_of_investment_banking_fees;
    }

    public BigDecimal getNetincome_of_asset_management_fees() {
        return netincome_of_asset_management_fees;
    }

    public void setNetincome_of_asset_management_fees(BigDecimal netincome_of_asset_management_fees) {
        this.netincome_of_asset_management_fees = netincome_of_asset_management_fees;
    }

    public BigDecimal getInvest_income() {
        return invest_income;
    }

    public void setInvest_income(BigDecimal invest_income) {
        this.invest_income = invest_income;
    }

    public BigDecimal getRelate_invest_income() {
        return relate_invest_income;
    }

    public void setRelate_invest_income(BigDecimal relate_invest_income) {
        this.relate_invest_income = relate_invest_income;
    }

    public BigDecimal getGain_loss_income() {
        return gain_loss_income;
    }

    public void setGain_loss_income(BigDecimal gain_loss_income) {
        this.gain_loss_income = gain_loss_income;
    }

    public BigDecimal getValue_gains() {
        return value_gains;
    }

    public void setValue_gains(BigDecimal value_gains) {
        this.value_gains = value_gains;
    }

    public BigDecimal getOther_income() {
        return other_income;
    }

    public void setOther_income(BigDecimal other_income) {
        this.other_income = other_income;
    }

    public BigDecimal getOperating_revenue_special_course() {
        return operating_revenue_special_course;
    }

    public void setOperating_revenue_special_course(BigDecimal operating_revenue_special_course) {
        this.operating_revenue_special_course = operating_revenue_special_course;
    }

    public BigDecimal getOperating_revenue_balance_course() {
        return operating_revenue_balance_course;
    }

    public void setOperating_revenue_balance_course(BigDecimal operating_revenue_balance_course) {
        this.operating_revenue_balance_course = operating_revenue_balance_course;
    }

    public BigDecimal getOverall_cost() {
        return overall_cost;
    }

    public void setOverall_cost(BigDecimal overall_cost) {
        this.overall_cost = overall_cost;
    }

    public BigDecimal getMain_cost() {
        return main_cost;
    }

    public void setMain_cost(BigDecimal main_cost) {
        this.main_cost = main_cost;
    }

    public BigDecimal getOperat_expenses() {
        return operat_expenses;
    }

    public void setOperat_expenses(BigDecimal operat_expenses) {
        this.operat_expenses = operat_expenses;
    }

    public BigDecimal getCanel_insurance_money() {
        return canel_insurance_money;
    }

    public void setCanel_insurance_money(BigDecimal canel_insurance_money) {
        this.canel_insurance_money = canel_insurance_money;
    }

    public BigDecimal getPay_expenses() {
        return pay_expenses;
    }

    public void setPay_expenses(BigDecimal pay_expenses) {
        this.pay_expenses = pay_expenses;
    }

    public BigDecimal getSpread_pay_expenses() {
        return spread_pay_expenses;
    }

    public void setSpread_pay_expenses(BigDecimal spread_pay_expenses) {
        this.spread_pay_expenses = spread_pay_expenses;
    }

    public BigDecimal getInsurance_liability() {
        return insurance_liability;
    }

    public void setInsurance_liability(BigDecimal insurance_liability) {
        this.insurance_liability = insurance_liability;
    }

    public BigDecimal getSpread_insurance_liability() {
        return spread_insurance_liability;
    }

    public void setSpread_insurance_liability(BigDecimal spread_insurance_liability) {
        this.spread_insurance_liability = spread_insurance_liability;
    }

    public BigDecimal getInsurance_cost() {
        return insurance_cost;
    }

    public void setInsurance_cost(BigDecimal insurance_cost) {
        this.insurance_cost = insurance_cost;
    }

    public BigDecimal getReduce_insurance_cost() {
        return reduce_insurance_cost;
    }

    public void setReduce_insurance_cost(BigDecimal reduce_insurance_cost) {
        this.reduce_insurance_cost = reduce_insurance_cost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getOperating_manage_cost() {
        return operating_manage_cost;
    }

    public void setOperating_manage_cost(BigDecimal operating_manage_cost) {
        this.operating_manage_cost = operating_manage_cost;
    }

    public BigDecimal getSpread_premium() {
        return spread_premium;
    }

    public void setSpread_premium(BigDecimal spread_premium) {
        this.spread_premium = spread_premium;
    }

    public BigDecimal getSale_cost() {
        return sale_cost;
    }

    public void setSale_cost(BigDecimal sale_cost) {
        this.sale_cost = sale_cost;
    }

    public BigDecimal getManage_cost() {
        return manage_cost;
    }

    public void setManage_cost(BigDecimal manage_cost) {
        this.manage_cost = manage_cost;
    }

    public BigDecimal getFin_cost() {
        return fin_cost;
    }

    public void setFin_cost(BigDecimal fin_cost) {
        this.fin_cost = fin_cost;
    }

    public BigDecimal getAsset_loss() {
        return asset_loss;
    }

    public void setAsset_loss(BigDecimal asset_loss) {
        this.asset_loss = asset_loss;
    }

    public BigDecimal getOther_bus_cost() {
        return other_bus_cost;
    }

    public void setOther_bus_cost(BigDecimal other_bus_cost) {
        this.other_bus_cost = other_bus_cost;
    }

    public BigDecimal getTotal_cost_of_special_subjects() {
        return total_cost_of_special_subjects;
    }

    public void setTotal_cost_of_special_subjects(BigDecimal total_cost_of_special_subjects) {
        this.total_cost_of_special_subjects = total_cost_of_special_subjects;
    }

    public BigDecimal getTotal_cost_of_balance_subjects() {
        return total_cost_of_balance_subjects;
    }

    public void setTotal_cost_of_balance_subjects(BigDecimal total_cost_of_balance_subjects) {
        this.total_cost_of_balance_subjects = total_cost_of_balance_subjects;
    }

    public BigDecimal getOverall_profit() {
        return overall_profit;
    }

    public void setOverall_profit(BigDecimal overall_profit) {
        this.overall_profit = overall_profit;
    }

    public BigDecimal getAddition_income() {
        return addition_income;
    }

    public void setAddition_income(BigDecimal addition_income) {
        this.addition_income = addition_income;
    }

    public BigDecimal getAddition_cost() {
        return addition_cost;
    }

    public void setAddition_cost(BigDecimal addition_cost) {
        this.addition_cost = addition_cost;
    }

    public BigDecimal getInterest_dispose() {
        return interest_dispose;
    }

    public void setInterest_dispose(BigDecimal interest_dispose) {
        this.interest_dispose = interest_dispose;
    }

    public BigDecimal getDisposal_noncurrent_assets() {
        return disposal_noncurrent_assets;
    }

    public void setDisposal_noncurrent_assets(BigDecimal disposal_noncurrent_assets) {
        this.disposal_noncurrent_assets = disposal_noncurrent_assets;
    }

    public BigDecimal getOperating_profit_special_subjects() {
        return operating_profit_special_subjects;
    }

    public void setOperating_profit_special_subjects(BigDecimal operating_profit_special_subjects) {
        this.operating_profit_special_subjects = operating_profit_special_subjects;
    }

    public BigDecimal getOperating_profit_balance_subjects() {
        return operating_profit_balance_subjects;
    }

    public void setOperating_profit_balance_subjects(BigDecimal operating_profit_balance_subjects) {
        this.operating_profit_balance_subjects = operating_profit_balance_subjects;
    }

    public BigDecimal getProfit_total() {
        return profit_total;
    }

    public void setProfit_total(BigDecimal profit_total) {
        this.profit_total = profit_total;
    }

    public BigDecimal getReduce_tax() {
        return reduce_tax;
    }

    public void setReduce_tax(BigDecimal reduce_tax) {
        this.reduce_tax = reduce_tax;
    }

    public BigDecimal getProfit_total_special_subjects() {
        return profit_total_special_subjects;
    }

    public void setProfit_total_special_subjects(BigDecimal profit_total_special_subjects) {
        this.profit_total_special_subjects = profit_total_special_subjects;
    }

    public BigDecimal getProfit_total_balance_subjects() {
        return profit_total_balance_subjects;
    }

    public void setProfit_total_balance_subjects(BigDecimal profit_total_balance_subjects) {
        this.profit_total_balance_subjects = profit_total_balance_subjects;
    }

    public BigDecimal getNetprofit() {
        return netprofit;
    }

    public void setNetprofit(BigDecimal netprofit) {
        this.netprofit = netprofit;
    }

    public BigDecimal getParent_netprofit() {
        return parent_netprofit;
    }

    public void setParent_netprofit(BigDecimal parent_netprofit) {
        this.parent_netprofit = parent_netprofit;
    }

    public BigDecimal getMinority_loss() {
        return minority_loss;
    }

    public void setMinority_loss(BigDecimal minority_loss) {
        this.minority_loss = minority_loss;
    }

    public BigDecimal getNetprofit_special_subjects() {
        return netprofit_special_subjects;
    }

    public void setNetprofit_special_subjects(BigDecimal netprofit_special_subjects) {
        this.netprofit_special_subjects = netprofit_special_subjects;
    }

    public BigDecimal getNetprofit_balance_subjects() {
        return netprofit_balance_subjects;
    }

    public void setNetprofit_balance_subjects(BigDecimal netprofit_balance_subjects) {
        this.netprofit_balance_subjects = netprofit_balance_subjects;
    }

    public BigDecimal getBasic_perstock_income() {
        return basic_perstock_income;
    }

    public void setBasic_perstock_income(BigDecimal basic_perstock_income) {
        this.basic_perstock_income = basic_perstock_income;
    }

    public BigDecimal getReduce_perstock_income() {
        return reduce_perstock_income;
    }

    public void setReduce_perstock_income(BigDecimal reduce_perstock_income) {
        this.reduce_perstock_income = reduce_perstock_income;
    }

    public BigDecimal getOther_composite_loss() {
        return other_composite_loss;
    }

    public void setOther_composite_loss(BigDecimal other_composite_loss) {
        this.other_composite_loss = other_composite_loss;
    }

    public BigDecimal getParent_other_com_income() {
        return parent_other_com_income;
    }

    public void setParent_other_com_income(BigDecimal parent_other_com_income) {
        this.parent_other_com_income = parent_other_com_income;
    }

    public BigDecimal getIncome_for_holer_other_com_income() {
        return income_for_holer_other_com_income;
    }

    public void setIncome_for_holer_other_com_income(BigDecimal income_for_holer_other_com_income) {
        this.income_for_holer_other_com_income = income_for_holer_other_com_income;
    }

    public BigDecimal getAll_income_total() {
        return all_income_total;
    }

    public void setAll_income_total(BigDecimal all_income_total) {
        this.all_income_total = all_income_total;
    }

    public BigDecimal getIncome_for_parent() {
        return income_for_parent;
    }

    public void setIncome_for_parent(BigDecimal income_for_parent) {
        this.income_for_parent = income_for_parent;
    }

    public BigDecimal getIncome_for_minority() {
        return income_for_minority;
    }

    public void setIncome_for_minority(BigDecimal income_for_minority) {
        this.income_for_minority = income_for_minority;
    }

    public String getSpecial_case_description() {
        return special_case_description;
    }

    public void setSpecial_case_description(String special_case_description) {
        this.special_case_description = special_case_description == null ? null : special_case_description.trim();
    }

    public BigDecimal getOther_income_loss() {
        return other_income_loss;
    }

    public void setOther_income_loss(BigDecimal other_income_loss) {
        this.other_income_loss = other_income_loss;
    }

    public BigDecimal getRecalculate_change_plan() {
        return recalculate_change_plan;
    }

    public void setRecalculate_change_plan(BigDecimal recalculate_change_plan) {
        this.recalculate_change_plan = recalculate_change_plan;
    }

    public BigDecimal getEquity_method() {
        return equity_method;
    }

    public void setEquity_method(BigDecimal equity_method) {
        this.equity_method = equity_method;
    }

    public BigDecimal getOthers_1() {
        return others_1;
    }

    public void setOthers_1(BigDecimal others_1) {
        this.others_1 = others_1;
    }

    public BigDecimal getOther_income_profit() {
        return other_income_profit;
    }

    public void setOther_income_profit(BigDecimal other_income_profit) {
        this.other_income_profit = other_income_profit;
    }

    public BigDecimal getShare_of_equity_method() {
        return share_of_equity_method;
    }

    public void setShare_of_equity_method(BigDecimal share_of_equity_method) {
        this.share_of_equity_method = share_of_equity_method;
    }

    public BigDecimal getSale_financial_assets() {
        return sale_financial_assets;
    }

    public void setSale_financial_assets(BigDecimal sale_financial_assets) {
        this.sale_financial_assets = sale_financial_assets;
    }

    public BigDecimal getSale_financial() {
        return sale_financial;
    }

    public void setSale_financial(BigDecimal sale_financial) {
        this.sale_financial = sale_financial;
    }

    public BigDecimal getCashflow_hedge_gains() {
        return cashflow_hedge_gains;
    }

    public void setCashflow_hedge_gains(BigDecimal cashflow_hedge_gains) {
        this.cashflow_hedge_gains = cashflow_hedge_gains;
    }

    public BigDecimal getEffective_portion_cashflow() {
        return effective_portion_cashflow;
    }

    public void setEffective_portion_cashflow(BigDecimal effective_portion_cashflow) {
        this.effective_portion_cashflow = effective_portion_cashflow;
    }

    public BigDecimal getOthers_2() {
        return others_2;
    }

    public void setOthers_2(BigDecimal others_2) {
        this.others_2 = others_2;
    }

    public String getWhether_published() {
        return whether_published;
    }

    public void setWhether_published(String whether_published) {
        this.whether_published = whether_published == null ? null : whether_published.trim();
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
        if(name.equals("overall_income")){
            return this.overall_income;
        }
        if(name.equals("overall_profit")){
            return this.overall_profit;
        }
        if(name.equals("netprofit")){
            return this.netprofit;
        }
        if(name.equals("overall_cost")){
            return this.overall_cost;
        }

        return null;
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
        sb.append(", report_type=").append(report_type);
        sb.append(", principles=").append(principles);
        sb.append(", consolidation=").append(consolidation);
        sb.append(", currency_code=").append(currency_code);
        sb.append(", report_format=").append(report_format);
        sb.append(", announcement_date=").append(announcement_date);
        sb.append(", overall_income=").append(overall_income);
        sb.append(", main_income=").append(main_income);
        sb.append(", interest_netincome=").append(interest_netincome);
        sb.append(", interest_income=").append(interest_income);
        sb.append(", interest_cost=").append(interest_cost);
        sb.append(", commission_netincome=").append(commission_netincome);
        sb.append(", commission_income=").append(commission_income);
        sb.append(", commission_cost=").append(commission_cost);
        sb.append(", earn_insurance=").append(earn_insurance);
        sb.append(", insurance_bus_income=").append(insurance_bus_income);
        sb.append(", premium_income=").append(premium_income);
        sb.append(", separate_premiums=").append(separate_premiums);
        sb.append(", extraction_unexpired=").append(extraction_unexpired);
        sb.append(", brokerage_fee_income=").append(brokerage_fee_income);
        sb.append(", netincome_of_investment_banking_fees=").append(netincome_of_investment_banking_fees);
        sb.append(", netincome_of_asset_management_fees=").append(netincome_of_asset_management_fees);
        sb.append(", invest_income=").append(invest_income);
        sb.append(", relate_invest_income=").append(relate_invest_income);
        sb.append(", gain_loss_income=").append(gain_loss_income);
        sb.append(", value_gains=").append(value_gains);
        sb.append(", other_income=").append(other_income);
        sb.append(", operating_revenue_special_course=").append(operating_revenue_special_course);
        sb.append(", operating_revenue_balance_course=").append(operating_revenue_balance_course);
        sb.append(", overall_cost=").append(overall_cost);
        sb.append(", main_cost=").append(main_cost);
        sb.append(", operat_expenses=").append(operat_expenses);
        sb.append(", canel_insurance_money=").append(canel_insurance_money);
        sb.append(", pay_expenses=").append(pay_expenses);
        sb.append(", spread_pay_expenses=").append(spread_pay_expenses);
        sb.append(", insurance_liability=").append(insurance_liability);
        sb.append(", spread_insurance_liability=").append(spread_insurance_liability);
        sb.append(", insurance_cost=").append(insurance_cost);
        sb.append(", reduce_insurance_cost=").append(reduce_insurance_cost);
        sb.append(", tax=").append(tax);
        sb.append(", operating_manage_cost=").append(operating_manage_cost);
        sb.append(", spread_premium=").append(spread_premium);
        sb.append(", sale_cost=").append(sale_cost);
        sb.append(", manage_cost=").append(manage_cost);
        sb.append(", fin_cost=").append(fin_cost);
        sb.append(", asset_loss=").append(asset_loss);
        sb.append(", other_bus_cost=").append(other_bus_cost);
        sb.append(", total_cost_of_special_subjects=").append(total_cost_of_special_subjects);
        sb.append(", total_cost_of_balance_subjects=").append(total_cost_of_balance_subjects);
        sb.append(", overall_profit=").append(overall_profit);
        sb.append(", addition_income=").append(addition_income);
        sb.append(", addition_cost=").append(addition_cost);
        sb.append(", interest_dispose=").append(interest_dispose);
        sb.append(", disposal_noncurrent_assets=").append(disposal_noncurrent_assets);
        sb.append(", operating_profit_special_subjects=").append(operating_profit_special_subjects);
        sb.append(", operating_profit_balance_subjects=").append(operating_profit_balance_subjects);
        sb.append(", profit_total=").append(profit_total);
        sb.append(", reduce_tax=").append(reduce_tax);
        sb.append(", profit_total_special_subjects=").append(profit_total_special_subjects);
        sb.append(", profit_total_balance_subjects=").append(profit_total_balance_subjects);
        sb.append(", netprofit=").append(netprofit);
        sb.append(", parent_netprofit=").append(parent_netprofit);
        sb.append(", minority_loss=").append(minority_loss);
        sb.append(", netprofit_special_subjects=").append(netprofit_special_subjects);
        sb.append(", netprofit_balance_subjects=").append(netprofit_balance_subjects);
        sb.append(", basic_perstock_income=").append(basic_perstock_income);
        sb.append(", reduce_perstock_income=").append(reduce_perstock_income);
        sb.append(", other_composite_loss=").append(other_composite_loss);
        sb.append(", parent_other_com_income=").append(parent_other_com_income);
        sb.append(", income_for_holer_other_com_income=").append(income_for_holer_other_com_income);
        sb.append(", all_income_total=").append(all_income_total);
        sb.append(", income_for_parent=").append(income_for_parent);
        sb.append(", income_for_minority=").append(income_for_minority);
        sb.append(", special_case_description=").append(special_case_description);
        sb.append(", other_income_loss=").append(other_income_loss);
        sb.append(", recalculate_change_plan=").append(recalculate_change_plan);
        sb.append(", equity_method=").append(equity_method);
        sb.append(", others_1=").append(others_1);
        sb.append(", other_income_profit=").append(other_income_profit);
        sb.append(", share_of_equity_method=").append(share_of_equity_method);
        sb.append(", sale_financial_assets=").append(sale_financial_assets);
        sb.append(", sale_financial=").append(sale_financial);
        sb.append(", cashflow_hedge_gains=").append(cashflow_hedge_gains);
        sb.append(", effective_portion_cashflow=").append(effective_portion_cashflow);
        sb.append(", others_2=").append(others_2);
        sb.append(", whether_published=").append(whether_published);
        sb.append(", come_source=").append(come_source);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}