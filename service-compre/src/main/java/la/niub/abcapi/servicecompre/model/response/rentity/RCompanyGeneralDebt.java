package la.niub.abcapi.servicecompre.model.response.rentity;

import java.util.Date;

//资产负债表-一般
public class RCompanyGeneralDebt {
    private String id;
    private String	total_staff;
    private String	end_date;
    private String	report_type;
    private String	cash;
    private String	trading_fin_assets;
    private String	derivat_fin_asset;
    private String	rec_note;
    private String	rec_account;
    private String	prepay;
    private String	rec_dividend;
    private String	rec_interest;
    private String	other_rec_account;
    private String	buy_fin_asset;
    private String	inventory;
    //其中：消耗性生物资产
    private String	biological_asset;
    private String	contract_assets;
    private String	held_for_sale_asset;
    private String	non_current_asset;
    //待摊费用
    private String	deferred_expenses;
    private String	other_current_asset;
    //其他金融类流动资产
    private String	current_assets;
    private String	settlement_provisions;
    private String	disassemble_fund;
    private String	recei_premium;
    private String	recei_dividend_payment;
    private String	recei_dividend_contract;
    private String	current_assets_special_subjects;
    private String	current_asset_of_balance;
    private String	total_current_asset;
    private String	loan_advance;
    private String	fvtpl_other_fa;
    private String	amortized_cost_fa;
    private String	debt_investment;
    private String	other_debt_investment;
    private String	available_sale_asset;
    private String	other_equity_investment;
    private String	held_investment;
    private String	other_noncurrent_fa;
    private String	long_rec_account;
    private String	long_equity_investment;
    private String	invest_house;
    private String	fix_asset;
    private String	fix_asset_dispose;
    private String	building;
    private String	balance_account_asset;
    private String	product_asset;
    private String	oil_asset;
    private String	intangible_asset;
    private String	develop_cost;
    private String	goodwill;
    private String	long_defer_cost;
    private String	tax_asset;
    private String	other_noncurrent_asset;
    private String	noncurrent_asset_special_subjects;
    private String	noncurrent_asset_of_balance;
    private String	total_noncurrent_asset;
    private String	asset_special_subject;
    private String	asset_balance_subject;
    private String	total_asset;
    private String	short_borrow;
    private String	transation_fin_borrow;
    private String	derivat_fin_liabilities;
    private String	pay_note;
    private String	pay_account;
    private String	prepay_account;
    private String	contract_lia;
    private String	payable_fee;
    private String	pay_salary;
    private String	pay_tax_balance;
    private String	pay_interest;
    private String	pay_dividend;
    private String	other_pay_account;
    private String	held_for_sale_lia;
    private String	non_current_borrow;
    //  预提费用
    private String	advance_cost;
    private String	deferred_income_current_lia;
    private String	short_term_bonds_payable;
    private String	other_current_borrow;
    // 其他金融类流动负债
    private String	current_liabilities;
    private String	borrow_central;
    private String	absorb_depo_and_interbank;
    private String	borrow_fund;
    private String	sell_fin_asset;
    private String	pay_account_rein;
    private String	insur_contract_reserves;
    private String	agent_trading_secrity;
    private String	act_underwrite_securities;
    private String	current_lia_special_subjects;
    private String	current_lia_balance_subjects;
    private String	total_current_liabilities;
    private String	long_borrow;
    private String	pay_bonds;
    private String	long_pay_account;
    private String	term_pay_account;
    private String	long_term_pay_for_employees;
    private String	pre_bonds;
    private String	deferred_income;
    private String	non_current_lia_deferred_income;
    private String	other_noncurrent_bonds;
    private String	non_current_lia_special_subjects;
    private String	non_current_lia_balance_subjects;
    private String	total_non_current_liabilities;
    private String	liabilities_special_subjects;
    private String	liabilities_balance_subjects;
    private String	total_liabilities;
    private String	rec_capital;
    private String	other_equity_instruments;
    private String	preferred_stock;
    private String	permanent_debt;
    private String	capital_reserve;
    private String	treasury_stock;
    private String	other_comprehensive_income;
    private String	special_reserve;
    private String	earn_reserve;
    private String	general_normal_preparation;
    private String	nopay_profit;
    private String	translation_reserve;
    //未确认的投资损失
    private String	unrecognized_investment_losses;
    private String	shareholders_equity_special_subject;
    private String	shareholders_equity_balance_subject;
    private String	total_account_parent_equity;
    private String	monority_holder_equity;
    private String	total_account_equity;
    // 负债及股东权益差额(特殊报表科目)
    private String	difference_report;
    // 负债及股东权益差额(合计平衡科目)
    private String	difference_balance;
    private String	total_account_equity_and_lia;
    private Date announcement_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotal_staff() {
        return total_staff;
    }

    public void setTotal_staff(String total_staff) {
        this.total_staff = total_staff;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getTrading_fin_assets() {
        return trading_fin_assets;
    }

    public void setTrading_fin_assets(String trading_fin_assets) {
        this.trading_fin_assets = trading_fin_assets;
    }

    public String getDerivat_fin_asset() {
        return derivat_fin_asset;
    }

    public void setDerivat_fin_asset(String derivat_fin_asset) {
        this.derivat_fin_asset = derivat_fin_asset;
    }

    public String getRec_note() {
        return rec_note;
    }

    public void setRec_note(String rec_note) {
        this.rec_note = rec_note;
    }

    public String getRec_account() {
        return rec_account;
    }

    public void setRec_account(String rec_account) {
        this.rec_account = rec_account;
    }

    public String getPrepay() {
        return prepay;
    }

    public void setPrepay(String prepay) {
        this.prepay = prepay;
    }

    public String getRec_dividend() {
        return rec_dividend;
    }

    public void setRec_dividend(String rec_dividend) {
        this.rec_dividend = rec_dividend;
    }

    public String getRec_interest() {
        return rec_interest;
    }

    public void setRec_interest(String rec_interest) {
        this.rec_interest = rec_interest;
    }

    public String getOther_rec_account() {
        return other_rec_account;
    }

    public void setOther_rec_account(String other_rec_account) {
        this.other_rec_account = other_rec_account;
    }

    public String getBuy_fin_asset() {
        return buy_fin_asset;
    }

    public void setBuy_fin_asset(String buy_fin_asset) {
        this.buy_fin_asset = buy_fin_asset;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getBiological_asset() {
        return biological_asset;
    }

    public void setBiological_asset(String biological_asset) {
        this.biological_asset = biological_asset;
    }

    public String getContract_assets() {
        return contract_assets;
    }

    public void setContract_assets(String contract_assets) {
        this.contract_assets = contract_assets;
    }

    public String getHeld_for_sale_asset() {
        return held_for_sale_asset;
    }

    public void setHeld_for_sale_asset(String held_for_sale_asset) {
        this.held_for_sale_asset = held_for_sale_asset;
    }

    public String getNon_current_asset() {
        return non_current_asset;
    }

    public void setNon_current_asset(String non_current_asset) {
        this.non_current_asset = non_current_asset;
    }

    public String getDeferred_expenses() {
        return deferred_expenses;
    }

    public void setDeferred_expenses(String deferred_expenses) {
        this.deferred_expenses = deferred_expenses;
    }

    public String getOther_current_asset() {
        return other_current_asset;
    }

    public void setOther_current_asset(String other_current_asset) {
        this.other_current_asset = other_current_asset;
    }

    public String getCurrent_assets() {
        return current_assets;
    }

    public void setCurrent_assets(String current_assets) {
        this.current_assets = current_assets;
    }

    public String getSettlement_provisions() {
        return settlement_provisions;
    }

    public void setSettlement_provisions(String settlement_provisions) {
        this.settlement_provisions = settlement_provisions;
    }

    public String getDisassemble_fund() {
        return disassemble_fund;
    }

    public void setDisassemble_fund(String disassemble_fund) {
        this.disassemble_fund = disassemble_fund;
    }

    public String getRecei_premium() {
        return recei_premium;
    }

    public void setRecei_premium(String recei_premium) {
        this.recei_premium = recei_premium;
    }

    public String getRecei_dividend_payment() {
        return recei_dividend_payment;
    }

    public void setRecei_dividend_payment(String recei_dividend_payment) {
        this.recei_dividend_payment = recei_dividend_payment;
    }

    public String getRecei_dividend_contract() {
        return recei_dividend_contract;
    }

    public void setRecei_dividend_contract(String recei_dividend_contract) {
        this.recei_dividend_contract = recei_dividend_contract;
    }

    public String getCurrent_assets_special_subjects() {
        return current_assets_special_subjects;
    }

    public void setCurrent_assets_special_subjects(String current_assets_special_subjects) {
        this.current_assets_special_subjects = current_assets_special_subjects;
    }

    public String getCurrent_asset_of_balance() {
        return current_asset_of_balance;
    }

    public void setCurrent_asset_of_balance(String current_asset_of_balance) {
        this.current_asset_of_balance = current_asset_of_balance;
    }

    public String getTotal_current_asset() {
        return total_current_asset;
    }

    public void setTotal_current_asset(String total_current_asset) {
        this.total_current_asset = total_current_asset;
    }

    public String getLoan_advance() {
        return loan_advance;
    }

    public void setLoan_advance(String loan_advance) {
        this.loan_advance = loan_advance;
    }

    public String getFvtpl_other_fa() {
        return fvtpl_other_fa;
    }

    public void setFvtpl_other_fa(String fvtpl_other_fa) {
        this.fvtpl_other_fa = fvtpl_other_fa;
    }

    public String getAmortized_cost_fa() {
        return amortized_cost_fa;
    }

    public void setAmortized_cost_fa(String amortized_cost_fa) {
        this.amortized_cost_fa = amortized_cost_fa;
    }

    public String getDebt_investment() {
        return debt_investment;
    }

    public void setDebt_investment(String debt_investment) {
        this.debt_investment = debt_investment;
    }

    public String getOther_debt_investment() {
        return other_debt_investment;
    }

    public void setOther_debt_investment(String other_debt_investment) {
        this.other_debt_investment = other_debt_investment;
    }

    public String getAvailable_sale_asset() {
        return available_sale_asset;
    }

    public void setAvailable_sale_asset(String available_sale_asset) {
        this.available_sale_asset = available_sale_asset;
    }

    public String getOther_equity_investment() {
        return other_equity_investment;
    }

    public void setOther_equity_investment(String other_equity_investment) {
        this.other_equity_investment = other_equity_investment;
    }

    public String getHeld_investment() {
        return held_investment;
    }

    public void setHeld_investment(String held_investment) {
        this.held_investment = held_investment;
    }

    public String getOther_noncurrent_fa() {
        return other_noncurrent_fa;
    }

    public void setOther_noncurrent_fa(String other_noncurrent_fa) {
        this.other_noncurrent_fa = other_noncurrent_fa;
    }

    public String getLong_rec_account() {
        return long_rec_account;
    }

    public void setLong_rec_account(String long_rec_account) {
        this.long_rec_account = long_rec_account;
    }

    public String getLong_equity_investment() {
        return long_equity_investment;
    }

    public void setLong_equity_investment(String long_equity_investment) {
        this.long_equity_investment = long_equity_investment;
    }

    public String getInvest_house() {
        return invest_house;
    }

    public void setInvest_house(String invest_house) {
        this.invest_house = invest_house;
    }

    public String getFix_asset() {
        return fix_asset;
    }

    public void setFix_asset(String fix_asset) {
        this.fix_asset = fix_asset;
    }

    public String getFix_asset_dispose() {
        return fix_asset_dispose;
    }

    public void setFix_asset_dispose(String fix_asset_dispose) {
        this.fix_asset_dispose = fix_asset_dispose;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBalance_account_asset() {
        return balance_account_asset;
    }

    public void setBalance_account_asset(String balance_account_asset) {
        this.balance_account_asset = balance_account_asset;
    }

    public String getProduct_asset() {
        return product_asset;
    }

    public void setProduct_asset(String product_asset) {
        this.product_asset = product_asset;
    }

    public String getOil_asset() {
        return oil_asset;
    }

    public void setOil_asset(String oil_asset) {
        this.oil_asset = oil_asset;
    }

    public String getIntangible_asset() {
        return intangible_asset;
    }

    public void setIntangible_asset(String intangible_asset) {
        this.intangible_asset = intangible_asset;
    }

    public String getDevelop_cost() {
        return develop_cost;
    }

    public void setDevelop_cost(String develop_cost) {
        this.develop_cost = develop_cost;
    }

    public String getGoodwill() {
        return goodwill;
    }

    public void setGoodwill(String goodwill) {
        this.goodwill = goodwill;
    }

    public String getLong_defer_cost() {
        return long_defer_cost;
    }

    public void setLong_defer_cost(String long_defer_cost) {
        this.long_defer_cost = long_defer_cost;
    }

    public String getTax_asset() {
        return tax_asset;
    }

    public void setTax_asset(String tax_asset) {
        this.tax_asset = tax_asset;
    }

    public String getOther_noncurrent_asset() {
        return other_noncurrent_asset;
    }

    public void setOther_noncurrent_asset(String other_noncurrent_asset) {
        this.other_noncurrent_asset = other_noncurrent_asset;
    }

    public String getNoncurrent_asset_special_subjects() {
        return noncurrent_asset_special_subjects;
    }

    public void setNoncurrent_asset_special_subjects(String noncurrent_asset_special_subjects) {
        this.noncurrent_asset_special_subjects = noncurrent_asset_special_subjects;
    }

    public String getNoncurrent_asset_of_balance() {
        return noncurrent_asset_of_balance;
    }

    public void setNoncurrent_asset_of_balance(String noncurrent_asset_of_balance) {
        this.noncurrent_asset_of_balance = noncurrent_asset_of_balance;
    }

    public String getTotal_noncurrent_asset() {
        return total_noncurrent_asset;
    }

    public void setTotal_noncurrent_asset(String total_noncurrent_asset) {
        this.total_noncurrent_asset = total_noncurrent_asset;
    }

    public String getAsset_special_subject() {
        return asset_special_subject;
    }

    public void setAsset_special_subject(String asset_special_subject) {
        this.asset_special_subject = asset_special_subject;
    }

    public String getAsset_balance_subject() {
        return asset_balance_subject;
    }

    public void setAsset_balance_subject(String asset_balance_subject) {
        this.asset_balance_subject = asset_balance_subject;
    }

    public String getTotal_asset() {
        return total_asset;
    }

    public void setTotal_asset(String total_asset) {
        this.total_asset = total_asset;
    }

    public String getShort_borrow() {
        return short_borrow;
    }

    public void setShort_borrow(String short_borrow) {
        this.short_borrow = short_borrow;
    }

    public String getTransation_fin_borrow() {
        return transation_fin_borrow;
    }

    public void setTransation_fin_borrow(String transation_fin_borrow) {
        this.transation_fin_borrow = transation_fin_borrow;
    }

    public String getDerivat_fin_liabilities() {
        return derivat_fin_liabilities;
    }

    public void setDerivat_fin_liabilities(String derivat_fin_liabilities) {
        this.derivat_fin_liabilities = derivat_fin_liabilities;
    }

    public String getPay_note() {
        return pay_note;
    }

    public void setPay_note(String pay_note) {
        this.pay_note = pay_note;
    }

    public String getPay_account() {
        return pay_account;
    }

    public void setPay_account(String pay_account) {
        this.pay_account = pay_account;
    }

    public String getPrepay_account() {
        return prepay_account;
    }

    public void setPrepay_account(String prepay_account) {
        this.prepay_account = prepay_account;
    }

    public String getContract_lia() {
        return contract_lia;
    }

    public void setContract_lia(String contract_lia) {
        this.contract_lia = contract_lia;
    }

    public String getPayable_fee() {
        return payable_fee;
    }

    public void setPayable_fee(String payable_fee) {
        this.payable_fee = payable_fee;
    }

    public String getPay_salary() {
        return pay_salary;
    }

    public void setPay_salary(String pay_salary) {
        this.pay_salary = pay_salary;
    }

    public String getPay_tax_balance() {
        return pay_tax_balance;
    }

    public void setPay_tax_balance(String pay_tax_balance) {
        this.pay_tax_balance = pay_tax_balance;
    }

    public String getPay_interest() {
        return pay_interest;
    }

    public void setPay_interest(String pay_interest) {
        this.pay_interest = pay_interest;
    }

    public String getPay_dividend() {
        return pay_dividend;
    }

    public void setPay_dividend(String pay_dividend) {
        this.pay_dividend = pay_dividend;
    }

    public String getOther_pay_account() {
        return other_pay_account;
    }

    public void setOther_pay_account(String other_pay_account) {
        this.other_pay_account = other_pay_account;
    }

    public String getHeld_for_sale_lia() {
        return held_for_sale_lia;
    }

    public void setHeld_for_sale_lia(String held_for_sale_lia) {
        this.held_for_sale_lia = held_for_sale_lia;
    }

    public String getNon_current_borrow() {
        return non_current_borrow;
    }

    public void setNon_current_borrow(String non_current_borrow) {
        this.non_current_borrow = non_current_borrow;
    }

    public String getAdvance_cost() {
        return advance_cost;
    }

    public void setAdvance_cost(String advance_cost) {
        this.advance_cost = advance_cost;
    }

    public String getDeferred_income_current_lia() {
        return deferred_income_current_lia;
    }

    public void setDeferred_income_current_lia(String deferred_income_current_lia) {
        this.deferred_income_current_lia = deferred_income_current_lia;
    }

    public String getShort_term_bonds_payable() {
        return short_term_bonds_payable;
    }

    public void setShort_term_bonds_payable(String short_term_bonds_payable) {
        this.short_term_bonds_payable = short_term_bonds_payable;
    }

    public String getOther_current_borrow() {
        return other_current_borrow;
    }

    public void setOther_current_borrow(String other_current_borrow) {
        this.other_current_borrow = other_current_borrow;
    }

    public String getCurrent_liabilities() {
        return current_liabilities;
    }

    public void setCurrent_liabilities(String current_liabilities) {
        this.current_liabilities = current_liabilities;
    }

    public String getBorrow_central() {
        return borrow_central;
    }

    public void setBorrow_central(String borrow_central) {
        this.borrow_central = borrow_central;
    }

    public String getAbsorb_depo_and_interbank() {
        return absorb_depo_and_interbank;
    }

    public void setAbsorb_depo_and_interbank(String absorb_depo_and_interbank) {
        this.absorb_depo_and_interbank = absorb_depo_and_interbank;
    }

    public String getBorrow_fund() {
        return borrow_fund;
    }

    public void setBorrow_fund(String borrow_fund) {
        this.borrow_fund = borrow_fund;
    }

    public String getSell_fin_asset() {
        return sell_fin_asset;
    }

    public void setSell_fin_asset(String sell_fin_asset) {
        this.sell_fin_asset = sell_fin_asset;
    }

    public String getPay_account_rein() {
        return pay_account_rein;
    }

    public void setPay_account_rein(String pay_account_rein) {
        this.pay_account_rein = pay_account_rein;
    }

    public String getInsur_contract_reserves() {
        return insur_contract_reserves;
    }

    public void setInsur_contract_reserves(String insur_contract_reserves) {
        this.insur_contract_reserves = insur_contract_reserves;
    }

    public String getAgent_trading_secrity() {
        return agent_trading_secrity;
    }

    public void setAgent_trading_secrity(String agent_trading_secrity) {
        this.agent_trading_secrity = agent_trading_secrity;
    }

    public String getAct_underwrite_securities() {
        return act_underwrite_securities;
    }

    public void setAct_underwrite_securities(String act_underwrite_securities) {
        this.act_underwrite_securities = act_underwrite_securities;
    }

    public String getCurrent_lia_special_subjects() {
        return current_lia_special_subjects;
    }

    public void setCurrent_lia_special_subjects(String current_lia_special_subjects) {
        this.current_lia_special_subjects = current_lia_special_subjects;
    }

    public String getCurrent_lia_balance_subjects() {
        return current_lia_balance_subjects;
    }

    public void setCurrent_lia_balance_subjects(String current_lia_balance_subjects) {
        this.current_lia_balance_subjects = current_lia_balance_subjects;
    }

    public String getTotal_current_liabilities() {
        return total_current_liabilities;
    }

    public void setTotal_current_liabilities(String total_current_liabilities) {
        this.total_current_liabilities = total_current_liabilities;
    }

    public String getLong_borrow() {
        return long_borrow;
    }

    public void setLong_borrow(String long_borrow) {
        this.long_borrow = long_borrow;
    }

    public String getPay_bonds() {
        return pay_bonds;
    }

    public void setPay_bonds(String pay_bonds) {
        this.pay_bonds = pay_bonds;
    }

    public String getLong_pay_account() {
        return long_pay_account;
    }

    public void setLong_pay_account(String long_pay_account) {
        this.long_pay_account = long_pay_account;
    }

    public String getTerm_pay_account() {
        return term_pay_account;
    }

    public void setTerm_pay_account(String term_pay_account) {
        this.term_pay_account = term_pay_account;
    }

    public String getLong_term_pay_for_employees() {
        return long_term_pay_for_employees;
    }

    public void setLong_term_pay_for_employees(String long_term_pay_for_employees) {
        this.long_term_pay_for_employees = long_term_pay_for_employees;
    }

    public String getPre_bonds() {
        return pre_bonds;
    }

    public void setPre_bonds(String pre_bonds) {
        this.pre_bonds = pre_bonds;
    }

    public String getDeferred_income() {
        return deferred_income;
    }

    public void setDeferred_income(String deferred_income) {
        this.deferred_income = deferred_income;
    }

    public String getNon_current_lia_deferred_income() {
        return non_current_lia_deferred_income;
    }

    public void setNon_current_lia_deferred_income(String non_current_lia_deferred_income) {
        this.non_current_lia_deferred_income = non_current_lia_deferred_income;
    }

    public String getOther_noncurrent_bonds() {
        return other_noncurrent_bonds;
    }

    public void setOther_noncurrent_bonds(String other_noncurrent_bonds) {
        this.other_noncurrent_bonds = other_noncurrent_bonds;
    }

    public String getNon_current_lia_special_subjects() {
        return non_current_lia_special_subjects;
    }

    public void setNon_current_lia_special_subjects(String non_current_lia_special_subjects) {
        this.non_current_lia_special_subjects = non_current_lia_special_subjects;
    }

    public String getNon_current_lia_balance_subjects() {
        return non_current_lia_balance_subjects;
    }

    public void setNon_current_lia_balance_subjects(String non_current_lia_balance_subjects) {
        this.non_current_lia_balance_subjects = non_current_lia_balance_subjects;
    }

    public String getTotal_non_current_liabilities() {
        return total_non_current_liabilities;
    }

    public void setTotal_non_current_liabilities(String total_non_current_liabilities) {
        this.total_non_current_liabilities = total_non_current_liabilities;
    }

    public String getLiabilities_special_subjects() {
        return liabilities_special_subjects;
    }

    public void setLiabilities_special_subjects(String liabilities_special_subjects) {
        this.liabilities_special_subjects = liabilities_special_subjects;
    }

    public String getLiabilities_balance_subjects() {
        return liabilities_balance_subjects;
    }

    public void setLiabilities_balance_subjects(String liabilities_balance_subjects) {
        this.liabilities_balance_subjects = liabilities_balance_subjects;
    }

    public String getTotal_liabilities() {
        return total_liabilities;
    }

    public void setTotal_liabilities(String total_liabilities) {
        this.total_liabilities = total_liabilities;
    }

    public String getRec_capital() {
        return rec_capital;
    }

    public void setRec_capital(String rec_capital) {
        this.rec_capital = rec_capital;
    }

    public String getOther_equity_instruments() {
        return other_equity_instruments;
    }

    public void setOther_equity_instruments(String other_equity_instruments) {
        this.other_equity_instruments = other_equity_instruments;
    }

    public String getPreferred_stock() {
        return preferred_stock;
    }

    public void setPreferred_stock(String preferred_stock) {
        this.preferred_stock = preferred_stock;
    }

    public String getPermanent_debt() {
        return permanent_debt;
    }

    public void setPermanent_debt(String permanent_debt) {
        this.permanent_debt = permanent_debt;
    }

    public String getCapital_reserve() {
        return capital_reserve;
    }

    public void setCapital_reserve(String capital_reserve) {
        this.capital_reserve = capital_reserve;
    }

    public String getTreasury_stock() {
        return treasury_stock;
    }

    public void setTreasury_stock(String treasury_stock) {
        this.treasury_stock = treasury_stock;
    }

    public String getOther_comprehensive_income() {
        return other_comprehensive_income;
    }

    public void setOther_comprehensive_income(String other_comprehensive_income) {
        this.other_comprehensive_income = other_comprehensive_income;
    }

    public String getSpecial_reserve() {
        return special_reserve;
    }

    public void setSpecial_reserve(String special_reserve) {
        this.special_reserve = special_reserve;
    }

    public String getEarn_reserve() {
        return earn_reserve;
    }

    public void setEarn_reserve(String earn_reserve) {
        this.earn_reserve = earn_reserve;
    }

    public String getGeneral_normal_preparation() {
        return general_normal_preparation;
    }

    public void setGeneral_normal_preparation(String general_normal_preparation) {
        this.general_normal_preparation = general_normal_preparation;
    }

    public String getNopay_profit() {
        return nopay_profit;
    }

    public void setNopay_profit(String nopay_profit) {
        this.nopay_profit = nopay_profit;
    }

    public String getTranslation_reserve() {
        return translation_reserve;
    }

    public void setTranslation_reserve(String translation_reserve) {
        this.translation_reserve = translation_reserve;
    }

    public String getUnrecognized_investment_losses() {
        return unrecognized_investment_losses;
    }

    public void setUnrecognized_investment_losses(String unrecognized_investment_losses) {
        this.unrecognized_investment_losses = unrecognized_investment_losses;
    }

    public String getShareholders_equity_special_subject() {
        return shareholders_equity_special_subject;
    }

    public void setShareholders_equity_special_subject(String shareholders_equity_special_subject) {
        this.shareholders_equity_special_subject = shareholders_equity_special_subject;
    }

    public String getShareholders_equity_balance_subject() {
        return shareholders_equity_balance_subject;
    }

    public void setShareholders_equity_balance_subject(String shareholders_equity_balance_subject) {
        this.shareholders_equity_balance_subject = shareholders_equity_balance_subject;
    }

    public String getTotal_account_parent_equity() {
        return total_account_parent_equity;
    }

    public void setTotal_account_parent_equity(String total_account_parent_equity) {
        this.total_account_parent_equity = total_account_parent_equity;
    }

    public String getMonority_holder_equity() {
        return monority_holder_equity;
    }

    public void setMonority_holder_equity(String monority_holder_equity) {
        this.monority_holder_equity = monority_holder_equity;
    }

    public String getTotal_account_equity() {
        return total_account_equity;
    }

    public void setTotal_account_equity(String total_account_equity) {
        this.total_account_equity = total_account_equity;
    }

    public String getDifference_report() {
        return difference_report;
    }

    public void setDifference_report(String difference_report) {
        this.difference_report = difference_report;
    }

    public String getDifference_balance() {
        return difference_balance;
    }

    public void setDifference_balance(String difference_balance) {
        this.difference_balance = difference_balance;
    }

    public String getTotal_account_equity_and_lia() {
        return total_account_equity_and_lia;
    }

    public void setTotal_account_equity_and_lia(String total_account_equity_and_lia) {
        this.total_account_equity_and_lia = total_account_equity_and_lia;
    }

    public Date getAnnouncement_date() {
        return announcement_date;
    }

    public void setAnnouncement_date(Date announcement_date) {
        this.announcement_date = announcement_date;
    }
}
