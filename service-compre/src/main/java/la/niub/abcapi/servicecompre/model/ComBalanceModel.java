package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ComBalanceModel implements Serializable {
    private Long id;

    private Long com_uni_code;

    private Date end_date;

    private Long report_type;

    private Long principles;

    private Long consolidation;

    private Long currency_code;

    private Date announcement_date;

    private Long report_format;

    private BigDecimal cash;

    private BigDecimal trading_fin_assets;

    private BigDecimal rec_note;

    private BigDecimal rec_account;

    private BigDecimal prepay;

    private BigDecimal rec_interest;

    private BigDecimal rec_dividend;

    private BigDecimal other_rec_account;

    private BigDecimal inventory;

    private BigDecimal non_current_asset;

    private BigDecimal other_current_asset;

    private BigDecimal current_assets_special_subjects;

    private BigDecimal current_asset_of_balance;

    private BigDecimal total_current_asset;

    private BigDecimal available_sale_asset;

    private BigDecimal held_investment;

    private BigDecimal long_rec_account;

    private BigDecimal long_equity_investment;

    private BigDecimal invest_house;

    private BigDecimal fix_asset;

    private BigDecimal building;

    private BigDecimal balance_account_asset;

    private BigDecimal fix_asset_dispose;

    private BigDecimal product_asset;

    private BigDecimal oil_asset;

    private BigDecimal intangible_asset;

    private BigDecimal develop_cost;

    private BigDecimal goodwill;

    private BigDecimal long_defer_cost;

    private BigDecimal tax_asset;

    private BigDecimal other_noncurrent_asset;

    private BigDecimal noncurrent_asset_special_subjects;

    private BigDecimal noncurrent_asset_of_balance;

    private BigDecimal total_noncurrent_asset;

    private BigDecimal cash_depo_cenbank;

    private BigDecimal depo_other_bank;

    private BigDecimal expensive_mental;

    private BigDecimal disassemble_fund;

    private BigDecimal derivat_fin_asset;

    private BigDecimal buy_fin_asset;

    private BigDecimal loan_advance;

    private BigDecimal other_asset;

    private BigDecimal rec_loan_account;

    private BigDecimal recei_premium;

    private BigDecimal receivable_subrogation;

    private BigDecimal recei_dividend_payment;

    private BigDecimal recei_unearned_r;

    private BigDecimal recei_claims_r;

    private BigDecimal recei_life_r;

    private BigDecimal recei_long_health_r;

    private BigDecimal insurer_impawn_loan;

    private BigDecimal fixed_time_deposit;

    private BigDecimal save_capital_deposit;

    private BigDecimal independ_account_assets;

    private BigDecimal customer_funds_deposit;

    private BigDecimal settlement_provisions;

    private BigDecimal customer_payment;

    private BigDecimal refundable_deposits;

    private BigDecimal transaction_fee;

    private BigDecimal asset_special_subject;

    private BigDecimal asset_balance_subject;

    private BigDecimal total_asset;

    private BigDecimal short_borrow;

    private BigDecimal transation_fin_borrow;

    private BigDecimal pay_note;

    private BigDecimal pay_account;

    private BigDecimal prepay_account;

    private BigDecimal pay_salary;

    private BigDecimal pay_tax_balance;

    private BigDecimal pay_interest;

    private BigDecimal pay_dividend;

    private BigDecimal other_pay_account;

    private BigDecimal non_current_borrow;

    private BigDecimal other_current_borrow;

    private BigDecimal current_lia_special_subjects;

    private BigDecimal current_lia_balance_subjects;

    private BigDecimal total_current_liabilities;

    private BigDecimal long_borrow;

    private BigDecimal pay_bonds;

    private BigDecimal long_pay_account;

    private BigDecimal term_pay_account;

    private BigDecimal pre_bonds;

    private BigDecimal deferred_income;

    private BigDecimal other_noncurrent_bonds;

    private BigDecimal non_current_lia_special_subjects;

    private BigDecimal non_current_lia_balance_subjects;

    private BigDecimal total_non_current_liabilities;

    private BigDecimal borrow_central;

    private BigDecimal peer_other_fin_depo_pay;

    private BigDecimal borrow_fund;

    private BigDecimal derivat_fin_liabilities;

    private BigDecimal sell_fin_asset;

    private BigDecimal absorb_depo;

    private BigDecimal other_liabilities;

    private BigDecimal advance_insurance;

    private BigDecimal payable_fee;

    private BigDecimal pay_account_rein;

    private BigDecimal compensation_payable;

    private BigDecimal policy_dividend_payable;

    private BigDecimal insurer_deposit_investment;

    private BigDecimal unearned_premium_reserve;

    private BigDecimal outstanding_claim_reserve;

    private BigDecimal life_insurance_reserve;

    private BigDecimal long_health_insurance_lr;

    private BigDecimal independ_liabilities;

    private BigDecimal pledged_loan;

    private BigDecimal agent_trading_secrity;

    private BigDecimal act_underwrite_securities;

    private BigDecimal liabilities_special_subjects;

    private BigDecimal liabilities_balance_subjects;

    private BigDecimal total_liabilities;

    private BigDecimal rec_capital;

    private BigDecimal capital_reserve;

    private BigDecimal treasury_stock;

    private BigDecimal special_reserve;

    private BigDecimal earn_reserve;

    private BigDecimal general_normal_preparation;

    private BigDecimal general_risk_preparation;

    private BigDecimal nopay_profit;

    private BigDecimal translation_reserve;

    private BigDecimal shareholders_equity_special_subject;

    private BigDecimal shareholders_equity_balance_subject;

    private BigDecimal total_account_parent_equity;

    private BigDecimal monority_holder_equity;

    private BigDecimal total_account_equity;

    private BigDecimal total_account_equity_and_lia;

    private String whether_published;

    private String special_case_description;

    private BigDecimal total_number_of_shares;

    private BigDecimal recei_dividend_contract;

    private BigDecimal absorb_depo_and_interbank;

    private BigDecimal insur_contract_reserves;

    private BigDecimal deferred_income_current_lia;

    private BigDecimal short_term_bonds_payable;

    private BigDecimal non_current_lia_deferred_income;

    private BigDecimal deposits_received;

    private BigDecimal financial_capital;

    private BigDecimal receivables;

    private BigDecimal short_term_financing;

    private BigDecimal payables;

    private BigDecimal other_comprehensive_income;

    private BigDecimal long_term_pay_for_employees;

    private BigDecimal other_equity_instruments;

    private BigDecimal preferred_stock;

    private BigDecimal permanent_debt;

    private String come_source;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public ComBalanceModel(Long id, Long com_uni_code, Date end_date, Long report_type, Long principles, Long consolidation, Long currency_code, Date announcement_date, Long report_format, BigDecimal cash, BigDecimal trading_fin_assets, BigDecimal rec_note, BigDecimal rec_account, BigDecimal prepay, BigDecimal rec_interest, BigDecimal rec_dividend, BigDecimal other_rec_account, BigDecimal inventory, BigDecimal non_current_asset, BigDecimal other_current_asset, BigDecimal current_assets_special_subjects, BigDecimal current_asset_of_balance, BigDecimal total_current_asset, BigDecimal available_sale_asset, BigDecimal held_investment, BigDecimal long_rec_account, BigDecimal long_equity_investment, BigDecimal invest_house, BigDecimal fix_asset, BigDecimal building, BigDecimal balance_account_asset, BigDecimal fix_asset_dispose, BigDecimal product_asset, BigDecimal oil_asset, BigDecimal intangible_asset, BigDecimal develop_cost, BigDecimal goodwill, BigDecimal long_defer_cost, BigDecimal tax_asset, BigDecimal other_noncurrent_asset, BigDecimal noncurrent_asset_special_subjects, BigDecimal noncurrent_asset_of_balance, BigDecimal total_noncurrent_asset, BigDecimal cash_depo_cenbank, BigDecimal depo_other_bank, BigDecimal expensive_mental, BigDecimal disassemble_fund, BigDecimal derivat_fin_asset, BigDecimal buy_fin_asset, BigDecimal loan_advance, BigDecimal other_asset, BigDecimal rec_loan_account, BigDecimal recei_premium, BigDecimal receivable_subrogation, BigDecimal recei_dividend_payment, BigDecimal recei_unearned_r, BigDecimal recei_claims_r, BigDecimal recei_life_r, BigDecimal recei_long_health_r, BigDecimal insurer_impawn_loan, BigDecimal fixed_time_deposit, BigDecimal save_capital_deposit, BigDecimal independ_account_assets, BigDecimal customer_funds_deposit, BigDecimal settlement_provisions, BigDecimal customer_payment, BigDecimal refundable_deposits, BigDecimal transaction_fee, BigDecimal asset_special_subject, BigDecimal asset_balance_subject, BigDecimal total_asset, BigDecimal short_borrow, BigDecimal transation_fin_borrow, BigDecimal pay_note, BigDecimal pay_account, BigDecimal prepay_account, BigDecimal pay_salary, BigDecimal pay_tax_balance, BigDecimal pay_interest, BigDecimal pay_dividend, BigDecimal other_pay_account, BigDecimal non_current_borrow, BigDecimal other_current_borrow, BigDecimal current_lia_special_subjects, BigDecimal current_lia_balance_subjects, BigDecimal total_current_liabilities, BigDecimal long_borrow, BigDecimal pay_bonds, BigDecimal long_pay_account, BigDecimal term_pay_account, BigDecimal pre_bonds, BigDecimal deferred_income, BigDecimal other_noncurrent_bonds, BigDecimal non_current_lia_special_subjects, BigDecimal non_current_lia_balance_subjects, BigDecimal total_non_current_liabilities, BigDecimal borrow_central, BigDecimal peer_other_fin_depo_pay, BigDecimal borrow_fund, BigDecimal derivat_fin_liabilities, BigDecimal sell_fin_asset, BigDecimal absorb_depo, BigDecimal other_liabilities, BigDecimal advance_insurance, BigDecimal payable_fee, BigDecimal pay_account_rein, BigDecimal compensation_payable, BigDecimal policy_dividend_payable, BigDecimal insurer_deposit_investment, BigDecimal unearned_premium_reserve, BigDecimal outstanding_claim_reserve, BigDecimal life_insurance_reserve, BigDecimal long_health_insurance_lr, BigDecimal independ_liabilities, BigDecimal pledged_loan, BigDecimal agent_trading_secrity, BigDecimal act_underwrite_securities, BigDecimal liabilities_special_subjects, BigDecimal liabilities_balance_subjects, BigDecimal total_liabilities, BigDecimal rec_capital, BigDecimal capital_reserve, BigDecimal treasury_stock, BigDecimal special_reserve, BigDecimal earn_reserve, BigDecimal general_normal_preparation, BigDecimal general_risk_preparation, BigDecimal nopay_profit, BigDecimal translation_reserve, BigDecimal shareholders_equity_special_subject, BigDecimal shareholders_equity_balance_subject, BigDecimal total_account_parent_equity, BigDecimal monority_holder_equity, BigDecimal total_account_equity, BigDecimal total_account_equity_and_lia, String whether_published, String special_case_description, BigDecimal total_number_of_shares, BigDecimal recei_dividend_contract, BigDecimal absorb_depo_and_interbank, BigDecimal insur_contract_reserves, BigDecimal deferred_income_current_lia, BigDecimal short_term_bonds_payable, BigDecimal non_current_lia_deferred_income, BigDecimal deposits_received, BigDecimal financial_capital, BigDecimal receivables, BigDecimal short_term_financing, BigDecimal payables, BigDecimal other_comprehensive_income, BigDecimal long_term_pay_for_employees, BigDecimal other_equity_instruments, BigDecimal preferred_stock, BigDecimal permanent_debt, String come_source, Date createtime, Date updatetime, String status, String remark, String creator, String editor) {
        this.id = id;
        this.com_uni_code = com_uni_code;
        this.end_date = end_date;
        this.report_type = report_type;
        this.principles = principles;
        this.consolidation = consolidation;
        this.currency_code = currency_code;
        this.announcement_date = announcement_date;
        this.report_format = report_format;
        this.cash = cash;
        this.trading_fin_assets = trading_fin_assets;
        this.rec_note = rec_note;
        this.rec_account = rec_account;
        this.prepay = prepay;
        this.rec_interest = rec_interest;
        this.rec_dividend = rec_dividend;
        this.other_rec_account = other_rec_account;
        this.inventory = inventory;
        this.non_current_asset = non_current_asset;
        this.other_current_asset = other_current_asset;
        this.current_assets_special_subjects = current_assets_special_subjects;
        this.current_asset_of_balance = current_asset_of_balance;
        this.total_current_asset = total_current_asset;
        this.available_sale_asset = available_sale_asset;
        this.held_investment = held_investment;
        this.long_rec_account = long_rec_account;
        this.long_equity_investment = long_equity_investment;
        this.invest_house = invest_house;
        this.fix_asset = fix_asset;
        this.building = building;
        this.balance_account_asset = balance_account_asset;
        this.fix_asset_dispose = fix_asset_dispose;
        this.product_asset = product_asset;
        this.oil_asset = oil_asset;
        this.intangible_asset = intangible_asset;
        this.develop_cost = develop_cost;
        this.goodwill = goodwill;
        this.long_defer_cost = long_defer_cost;
        this.tax_asset = tax_asset;
        this.other_noncurrent_asset = other_noncurrent_asset;
        this.noncurrent_asset_special_subjects = noncurrent_asset_special_subjects;
        this.noncurrent_asset_of_balance = noncurrent_asset_of_balance;
        this.total_noncurrent_asset = total_noncurrent_asset;
        this.cash_depo_cenbank = cash_depo_cenbank;
        this.depo_other_bank = depo_other_bank;
        this.expensive_mental = expensive_mental;
        this.disassemble_fund = disassemble_fund;
        this.derivat_fin_asset = derivat_fin_asset;
        this.buy_fin_asset = buy_fin_asset;
        this.loan_advance = loan_advance;
        this.other_asset = other_asset;
        this.rec_loan_account = rec_loan_account;
        this.recei_premium = recei_premium;
        this.receivable_subrogation = receivable_subrogation;
        this.recei_dividend_payment = recei_dividend_payment;
        this.recei_unearned_r = recei_unearned_r;
        this.recei_claims_r = recei_claims_r;
        this.recei_life_r = recei_life_r;
        this.recei_long_health_r = recei_long_health_r;
        this.insurer_impawn_loan = insurer_impawn_loan;
        this.fixed_time_deposit = fixed_time_deposit;
        this.save_capital_deposit = save_capital_deposit;
        this.independ_account_assets = independ_account_assets;
        this.customer_funds_deposit = customer_funds_deposit;
        this.settlement_provisions = settlement_provisions;
        this.customer_payment = customer_payment;
        this.refundable_deposits = refundable_deposits;
        this.transaction_fee = transaction_fee;
        this.asset_special_subject = asset_special_subject;
        this.asset_balance_subject = asset_balance_subject;
        this.total_asset = total_asset;
        this.short_borrow = short_borrow;
        this.transation_fin_borrow = transation_fin_borrow;
        this.pay_note = pay_note;
        this.pay_account = pay_account;
        this.prepay_account = prepay_account;
        this.pay_salary = pay_salary;
        this.pay_tax_balance = pay_tax_balance;
        this.pay_interest = pay_interest;
        this.pay_dividend = pay_dividend;
        this.other_pay_account = other_pay_account;
        this.non_current_borrow = non_current_borrow;
        this.other_current_borrow = other_current_borrow;
        this.current_lia_special_subjects = current_lia_special_subjects;
        this.current_lia_balance_subjects = current_lia_balance_subjects;
        this.total_current_liabilities = total_current_liabilities;
        this.long_borrow = long_borrow;
        this.pay_bonds = pay_bonds;
        this.long_pay_account = long_pay_account;
        this.term_pay_account = term_pay_account;
        this.pre_bonds = pre_bonds;
        this.deferred_income = deferred_income;
        this.other_noncurrent_bonds = other_noncurrent_bonds;
        this.non_current_lia_special_subjects = non_current_lia_special_subjects;
        this.non_current_lia_balance_subjects = non_current_lia_balance_subjects;
        this.total_non_current_liabilities = total_non_current_liabilities;
        this.borrow_central = borrow_central;
        this.peer_other_fin_depo_pay = peer_other_fin_depo_pay;
        this.borrow_fund = borrow_fund;
        this.derivat_fin_liabilities = derivat_fin_liabilities;
        this.sell_fin_asset = sell_fin_asset;
        this.absorb_depo = absorb_depo;
        this.other_liabilities = other_liabilities;
        this.advance_insurance = advance_insurance;
        this.payable_fee = payable_fee;
        this.pay_account_rein = pay_account_rein;
        this.compensation_payable = compensation_payable;
        this.policy_dividend_payable = policy_dividend_payable;
        this.insurer_deposit_investment = insurer_deposit_investment;
        this.unearned_premium_reserve = unearned_premium_reserve;
        this.outstanding_claim_reserve = outstanding_claim_reserve;
        this.life_insurance_reserve = life_insurance_reserve;
        this.long_health_insurance_lr = long_health_insurance_lr;
        this.independ_liabilities = independ_liabilities;
        this.pledged_loan = pledged_loan;
        this.agent_trading_secrity = agent_trading_secrity;
        this.act_underwrite_securities = act_underwrite_securities;
        this.liabilities_special_subjects = liabilities_special_subjects;
        this.liabilities_balance_subjects = liabilities_balance_subjects;
        this.total_liabilities = total_liabilities;
        this.rec_capital = rec_capital;
        this.capital_reserve = capital_reserve;
        this.treasury_stock = treasury_stock;
        this.special_reserve = special_reserve;
        this.earn_reserve = earn_reserve;
        this.general_normal_preparation = general_normal_preparation;
        this.general_risk_preparation = general_risk_preparation;
        this.nopay_profit = nopay_profit;
        this.translation_reserve = translation_reserve;
        this.shareholders_equity_special_subject = shareholders_equity_special_subject;
        this.shareholders_equity_balance_subject = shareholders_equity_balance_subject;
        this.total_account_parent_equity = total_account_parent_equity;
        this.monority_holder_equity = monority_holder_equity;
        this.total_account_equity = total_account_equity;
        this.total_account_equity_and_lia = total_account_equity_and_lia;
        this.whether_published = whether_published;
        this.special_case_description = special_case_description;
        this.total_number_of_shares = total_number_of_shares;
        this.recei_dividend_contract = recei_dividend_contract;
        this.absorb_depo_and_interbank = absorb_depo_and_interbank;
        this.insur_contract_reserves = insur_contract_reserves;
        this.deferred_income_current_lia = deferred_income_current_lia;
        this.short_term_bonds_payable = short_term_bonds_payable;
        this.non_current_lia_deferred_income = non_current_lia_deferred_income;
        this.deposits_received = deposits_received;
        this.financial_capital = financial_capital;
        this.receivables = receivables;
        this.short_term_financing = short_term_financing;
        this.payables = payables;
        this.other_comprehensive_income = other_comprehensive_income;
        this.long_term_pay_for_employees = long_term_pay_for_employees;
        this.other_equity_instruments = other_equity_instruments;
        this.preferred_stock = preferred_stock;
        this.permanent_debt = permanent_debt;
        this.come_source = come_source;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
    }

    public ComBalanceModel() {
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

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getTrading_fin_assets() {
        return trading_fin_assets;
    }

    public void setTrading_fin_assets(BigDecimal trading_fin_assets) {
        this.trading_fin_assets = trading_fin_assets;
    }

    public BigDecimal getRec_note() {
        return rec_note;
    }

    public void setRec_note(BigDecimal rec_note) {
        this.rec_note = rec_note;
    }

    public BigDecimal getRec_account() {
        return rec_account;
    }

    public void setRec_account(BigDecimal rec_account) {
        this.rec_account = rec_account;
    }

    public BigDecimal getPrepay() {
        return prepay;
    }

    public void setPrepay(BigDecimal prepay) {
        this.prepay = prepay;
    }

    public BigDecimal getRec_interest() {
        return rec_interest;
    }

    public void setRec_interest(BigDecimal rec_interest) {
        this.rec_interest = rec_interest;
    }

    public BigDecimal getRec_dividend() {
        return rec_dividend;
    }

    public void setRec_dividend(BigDecimal rec_dividend) {
        this.rec_dividend = rec_dividend;
    }

    public BigDecimal getOther_rec_account() {
        return other_rec_account;
    }

    public void setOther_rec_account(BigDecimal other_rec_account) {
        this.other_rec_account = other_rec_account;
    }

    public BigDecimal getInventory() {
        return inventory;
    }

    public void setInventory(BigDecimal inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getNon_current_asset() {
        return non_current_asset;
    }

    public void setNon_current_asset(BigDecimal non_current_asset) {
        this.non_current_asset = non_current_asset;
    }

    public BigDecimal getOther_current_asset() {
        return other_current_asset;
    }

    public void setOther_current_asset(BigDecimal other_current_asset) {
        this.other_current_asset = other_current_asset;
    }

    public BigDecimal getCurrent_assets_special_subjects() {
        return current_assets_special_subjects;
    }

    public void setCurrent_assets_special_subjects(BigDecimal current_assets_special_subjects) {
        this.current_assets_special_subjects = current_assets_special_subjects;
    }

    public BigDecimal getCurrent_asset_of_balance() {
        return current_asset_of_balance;
    }

    public void setCurrent_asset_of_balance(BigDecimal current_asset_of_balance) {
        this.current_asset_of_balance = current_asset_of_balance;
    }

    public BigDecimal getTotal_current_asset() {
        return total_current_asset;
    }

    public void setTotal_current_asset(BigDecimal total_current_asset) {
        this.total_current_asset = total_current_asset;
    }

    public BigDecimal getAvailable_sale_asset() {
        return available_sale_asset;
    }

    public void setAvailable_sale_asset(BigDecimal available_sale_asset) {
        this.available_sale_asset = available_sale_asset;
    }

    public BigDecimal getHeld_investment() {
        return held_investment;
    }

    public void setHeld_investment(BigDecimal held_investment) {
        this.held_investment = held_investment;
    }

    public BigDecimal getLong_rec_account() {
        return long_rec_account;
    }

    public void setLong_rec_account(BigDecimal long_rec_account) {
        this.long_rec_account = long_rec_account;
    }

    public BigDecimal getLong_equity_investment() {
        return long_equity_investment;
    }

    public void setLong_equity_investment(BigDecimal long_equity_investment) {
        this.long_equity_investment = long_equity_investment;
    }

    public BigDecimal getInvest_house() {
        return invest_house;
    }

    public void setInvest_house(BigDecimal invest_house) {
        this.invest_house = invest_house;
    }

    public BigDecimal getFix_asset() {
        return fix_asset;
    }

    public void setFix_asset(BigDecimal fix_asset) {
        this.fix_asset = fix_asset;
    }

    public BigDecimal getBuilding() {
        return building;
    }

    public void setBuilding(BigDecimal building) {
        this.building = building;
    }

    public BigDecimal getBalance_account_asset() {
        return balance_account_asset;
    }

    public void setBalance_account_asset(BigDecimal balance_account_asset) {
        this.balance_account_asset = balance_account_asset;
    }

    public BigDecimal getFix_asset_dispose() {
        return fix_asset_dispose;
    }

    public void setFix_asset_dispose(BigDecimal fix_asset_dispose) {
        this.fix_asset_dispose = fix_asset_dispose;
    }

    public BigDecimal getProduct_asset() {
        return product_asset;
    }

    public void setProduct_asset(BigDecimal product_asset) {
        this.product_asset = product_asset;
    }

    public BigDecimal getOil_asset() {
        return oil_asset;
    }

    public void setOil_asset(BigDecimal oil_asset) {
        this.oil_asset = oil_asset;
    }

    public BigDecimal getIntangible_asset() {
        return intangible_asset;
    }

    public void setIntangible_asset(BigDecimal intangible_asset) {
        this.intangible_asset = intangible_asset;
    }

    public BigDecimal getDevelop_cost() {
        return develop_cost;
    }

    public void setDevelop_cost(BigDecimal develop_cost) {
        this.develop_cost = develop_cost;
    }

    public BigDecimal getGoodwill() {
        return goodwill;
    }

    public void setGoodwill(BigDecimal goodwill) {
        this.goodwill = goodwill;
    }

    public BigDecimal getLong_defer_cost() {
        return long_defer_cost;
    }

    public void setLong_defer_cost(BigDecimal long_defer_cost) {
        this.long_defer_cost = long_defer_cost;
    }

    public BigDecimal getTax_asset() {
        return tax_asset;
    }

    public void setTax_asset(BigDecimal tax_asset) {
        this.tax_asset = tax_asset;
    }

    public BigDecimal getOther_noncurrent_asset() {
        return other_noncurrent_asset;
    }

    public void setOther_noncurrent_asset(BigDecimal other_noncurrent_asset) {
        this.other_noncurrent_asset = other_noncurrent_asset;
    }

    public BigDecimal getNoncurrent_asset_special_subjects() {
        return noncurrent_asset_special_subjects;
    }

    public void setNoncurrent_asset_special_subjects(BigDecimal noncurrent_asset_special_subjects) {
        this.noncurrent_asset_special_subjects = noncurrent_asset_special_subjects;
    }

    public BigDecimal getNoncurrent_asset_of_balance() {
        return noncurrent_asset_of_balance;
    }

    public void setNoncurrent_asset_of_balance(BigDecimal noncurrent_asset_of_balance) {
        this.noncurrent_asset_of_balance = noncurrent_asset_of_balance;
    }

    public BigDecimal getTotal_noncurrent_asset() {
        return total_noncurrent_asset;
    }

    public void setTotal_noncurrent_asset(BigDecimal total_noncurrent_asset) {
        this.total_noncurrent_asset = total_noncurrent_asset;
    }

    public BigDecimal getCash_depo_cenbank() {
        return cash_depo_cenbank;
    }

    public void setCash_depo_cenbank(BigDecimal cash_depo_cenbank) {
        this.cash_depo_cenbank = cash_depo_cenbank;
    }

    public BigDecimal getDepo_other_bank() {
        return depo_other_bank;
    }

    public void setDepo_other_bank(BigDecimal depo_other_bank) {
        this.depo_other_bank = depo_other_bank;
    }

    public BigDecimal getExpensive_mental() {
        return expensive_mental;
    }

    public void setExpensive_mental(BigDecimal expensive_mental) {
        this.expensive_mental = expensive_mental;
    }

    public BigDecimal getDisassemble_fund() {
        return disassemble_fund;
    }

    public void setDisassemble_fund(BigDecimal disassemble_fund) {
        this.disassemble_fund = disassemble_fund;
    }

    public BigDecimal getDerivat_fin_asset() {
        return derivat_fin_asset;
    }

    public void setDerivat_fin_asset(BigDecimal derivat_fin_asset) {
        this.derivat_fin_asset = derivat_fin_asset;
    }

    public BigDecimal getBuy_fin_asset() {
        return buy_fin_asset;
    }

    public void setBuy_fin_asset(BigDecimal buy_fin_asset) {
        this.buy_fin_asset = buy_fin_asset;
    }

    public BigDecimal getLoan_advance() {
        return loan_advance;
    }

    public void setLoan_advance(BigDecimal loan_advance) {
        this.loan_advance = loan_advance;
    }

    public BigDecimal getOther_asset() {
        return other_asset;
    }

    public void setOther_asset(BigDecimal other_asset) {
        this.other_asset = other_asset;
    }

    public BigDecimal getRec_loan_account() {
        return rec_loan_account;
    }

    public void setRec_loan_account(BigDecimal rec_loan_account) {
        this.rec_loan_account = rec_loan_account;
    }

    public BigDecimal getRecei_premium() {
        return recei_premium;
    }

    public void setRecei_premium(BigDecimal recei_premium) {
        this.recei_premium = recei_premium;
    }

    public BigDecimal getReceivable_subrogation() {
        return receivable_subrogation;
    }

    public void setReceivable_subrogation(BigDecimal receivable_subrogation) {
        this.receivable_subrogation = receivable_subrogation;
    }

    public BigDecimal getRecei_dividend_payment() {
        return recei_dividend_payment;
    }

    public void setRecei_dividend_payment(BigDecimal recei_dividend_payment) {
        this.recei_dividend_payment = recei_dividend_payment;
    }

    public BigDecimal getRecei_unearned_r() {
        return recei_unearned_r;
    }

    public void setRecei_unearned_r(BigDecimal recei_unearned_r) {
        this.recei_unearned_r = recei_unearned_r;
    }

    public BigDecimal getRecei_claims_r() {
        return recei_claims_r;
    }

    public void setRecei_claims_r(BigDecimal recei_claims_r) {
        this.recei_claims_r = recei_claims_r;
    }

    public BigDecimal getRecei_life_r() {
        return recei_life_r;
    }

    public void setRecei_life_r(BigDecimal recei_life_r) {
        this.recei_life_r = recei_life_r;
    }

    public BigDecimal getRecei_long_health_r() {
        return recei_long_health_r;
    }

    public void setRecei_long_health_r(BigDecimal recei_long_health_r) {
        this.recei_long_health_r = recei_long_health_r;
    }

    public BigDecimal getInsurer_impawn_loan() {
        return insurer_impawn_loan;
    }

    public void setInsurer_impawn_loan(BigDecimal insurer_impawn_loan) {
        this.insurer_impawn_loan = insurer_impawn_loan;
    }

    public BigDecimal getFixed_time_deposit() {
        return fixed_time_deposit;
    }

    public void setFixed_time_deposit(BigDecimal fixed_time_deposit) {
        this.fixed_time_deposit = fixed_time_deposit;
    }

    public BigDecimal getSave_capital_deposit() {
        return save_capital_deposit;
    }

    public void setSave_capital_deposit(BigDecimal save_capital_deposit) {
        this.save_capital_deposit = save_capital_deposit;
    }

    public BigDecimal getIndepend_account_assets() {
        return independ_account_assets;
    }

    public void setIndepend_account_assets(BigDecimal independ_account_assets) {
        this.independ_account_assets = independ_account_assets;
    }

    public BigDecimal getCustomer_funds_deposit() {
        return customer_funds_deposit;
    }

    public void setCustomer_funds_deposit(BigDecimal customer_funds_deposit) {
        this.customer_funds_deposit = customer_funds_deposit;
    }

    public BigDecimal getSettlement_provisions() {
        return settlement_provisions;
    }

    public void setSettlement_provisions(BigDecimal settlement_provisions) {
        this.settlement_provisions = settlement_provisions;
    }

    public BigDecimal getCustomer_payment() {
        return customer_payment;
    }

    public void setCustomer_payment(BigDecimal customer_payment) {
        this.customer_payment = customer_payment;
    }

    public BigDecimal getRefundable_deposits() {
        return refundable_deposits;
    }

    public void setRefundable_deposits(BigDecimal refundable_deposits) {
        this.refundable_deposits = refundable_deposits;
    }

    public BigDecimal getTransaction_fee() {
        return transaction_fee;
    }

    public void setTransaction_fee(BigDecimal transaction_fee) {
        this.transaction_fee = transaction_fee;
    }

    public BigDecimal getAsset_special_subject() {
        return asset_special_subject;
    }

    public void setAsset_special_subject(BigDecimal asset_special_subject) {
        this.asset_special_subject = asset_special_subject;
    }

    public BigDecimal getAsset_balance_subject() {
        return asset_balance_subject;
    }

    public void setAsset_balance_subject(BigDecimal asset_balance_subject) {
        this.asset_balance_subject = asset_balance_subject;
    }

    public BigDecimal getTotal_asset() {
        return total_asset;
    }

    public void setTotal_asset(BigDecimal total_asset) {
        this.total_asset = total_asset;
    }

    public BigDecimal getShort_borrow() {
        return short_borrow;
    }

    public void setShort_borrow(BigDecimal short_borrow) {
        this.short_borrow = short_borrow;
    }

    public BigDecimal getTransation_fin_borrow() {
        return transation_fin_borrow;
    }

    public void setTransation_fin_borrow(BigDecimal transation_fin_borrow) {
        this.transation_fin_borrow = transation_fin_borrow;
    }

    public BigDecimal getPay_note() {
        return pay_note;
    }

    public void setPay_note(BigDecimal pay_note) {
        this.pay_note = pay_note;
    }

    public BigDecimal getPay_account() {
        return pay_account;
    }

    public void setPay_account(BigDecimal pay_account) {
        this.pay_account = pay_account;
    }

    public BigDecimal getPrepay_account() {
        return prepay_account;
    }

    public void setPrepay_account(BigDecimal prepay_account) {
        this.prepay_account = prepay_account;
    }

    public BigDecimal getPay_salary() {
        return pay_salary;
    }

    public void setPay_salary(BigDecimal pay_salary) {
        this.pay_salary = pay_salary;
    }

    public BigDecimal getPay_tax_balance() {
        return pay_tax_balance;
    }

    public void setPay_tax_balance(BigDecimal pay_tax_balance) {
        this.pay_tax_balance = pay_tax_balance;
    }

    public BigDecimal getPay_interest() {
        return pay_interest;
    }

    public void setPay_interest(BigDecimal pay_interest) {
        this.pay_interest = pay_interest;
    }

    public BigDecimal getPay_dividend() {
        return pay_dividend;
    }

    public void setPay_dividend(BigDecimal pay_dividend) {
        this.pay_dividend = pay_dividend;
    }

    public BigDecimal getOther_pay_account() {
        return other_pay_account;
    }

    public void setOther_pay_account(BigDecimal other_pay_account) {
        this.other_pay_account = other_pay_account;
    }

    public BigDecimal getNon_current_borrow() {
        return non_current_borrow;
    }

    public void setNon_current_borrow(BigDecimal non_current_borrow) {
        this.non_current_borrow = non_current_borrow;
    }

    public BigDecimal getOther_current_borrow() {
        return other_current_borrow;
    }

    public void setOther_current_borrow(BigDecimal other_current_borrow) {
        this.other_current_borrow = other_current_borrow;
    }

    public BigDecimal getCurrent_lia_special_subjects() {
        return current_lia_special_subjects;
    }

    public void setCurrent_lia_special_subjects(BigDecimal current_lia_special_subjects) {
        this.current_lia_special_subjects = current_lia_special_subjects;
    }

    public BigDecimal getCurrent_lia_balance_subjects() {
        return current_lia_balance_subjects;
    }

    public void setCurrent_lia_balance_subjects(BigDecimal current_lia_balance_subjects) {
        this.current_lia_balance_subjects = current_lia_balance_subjects;
    }

    public BigDecimal getTotal_current_liabilities() {
        return total_current_liabilities;
    }

    public void setTotal_current_liabilities(BigDecimal total_current_liabilities) {
        this.total_current_liabilities = total_current_liabilities;
    }

    public BigDecimal getLong_borrow() {
        return long_borrow;
    }

    public void setLong_borrow(BigDecimal long_borrow) {
        this.long_borrow = long_borrow;
    }

    public BigDecimal getPay_bonds() {
        return pay_bonds;
    }

    public void setPay_bonds(BigDecimal pay_bonds) {
        this.pay_bonds = pay_bonds;
    }

    public BigDecimal getLong_pay_account() {
        return long_pay_account;
    }

    public void setLong_pay_account(BigDecimal long_pay_account) {
        this.long_pay_account = long_pay_account;
    }

    public BigDecimal getTerm_pay_account() {
        return term_pay_account;
    }

    public void setTerm_pay_account(BigDecimal term_pay_account) {
        this.term_pay_account = term_pay_account;
    }

    public BigDecimal getPre_bonds() {
        return pre_bonds;
    }

    public void setPre_bonds(BigDecimal pre_bonds) {
        this.pre_bonds = pre_bonds;
    }

    public BigDecimal getDeferred_income() {
        return deferred_income;
    }

    public void setDeferred_income(BigDecimal deferred_income) {
        this.deferred_income = deferred_income;
    }

    public BigDecimal getOther_noncurrent_bonds() {
        return other_noncurrent_bonds;
    }

    public void setOther_noncurrent_bonds(BigDecimal other_noncurrent_bonds) {
        this.other_noncurrent_bonds = other_noncurrent_bonds;
    }

    public BigDecimal getNon_current_lia_special_subjects() {
        return non_current_lia_special_subjects;
    }

    public void setNon_current_lia_special_subjects(BigDecimal non_current_lia_special_subjects) {
        this.non_current_lia_special_subjects = non_current_lia_special_subjects;
    }

    public BigDecimal getNon_current_lia_balance_subjects() {
        return non_current_lia_balance_subjects;
    }

    public void setNon_current_lia_balance_subjects(BigDecimal non_current_lia_balance_subjects) {
        this.non_current_lia_balance_subjects = non_current_lia_balance_subjects;
    }

    public BigDecimal getTotal_non_current_liabilities() {
        return total_non_current_liabilities;
    }

    public void setTotal_non_current_liabilities(BigDecimal total_non_current_liabilities) {
        this.total_non_current_liabilities = total_non_current_liabilities;
    }

    public BigDecimal getBorrow_central() {
        return borrow_central;
    }

    public void setBorrow_central(BigDecimal borrow_central) {
        this.borrow_central = borrow_central;
    }

    public BigDecimal getPeer_other_fin_depo_pay() {
        return peer_other_fin_depo_pay;
    }

    public void setPeer_other_fin_depo_pay(BigDecimal peer_other_fin_depo_pay) {
        this.peer_other_fin_depo_pay = peer_other_fin_depo_pay;
    }

    public BigDecimal getBorrow_fund() {
        return borrow_fund;
    }

    public void setBorrow_fund(BigDecimal borrow_fund) {
        this.borrow_fund = borrow_fund;
    }

    public BigDecimal getDerivat_fin_liabilities() {
        return derivat_fin_liabilities;
    }

    public void setDerivat_fin_liabilities(BigDecimal derivat_fin_liabilities) {
        this.derivat_fin_liabilities = derivat_fin_liabilities;
    }

    public BigDecimal getSell_fin_asset() {
        return sell_fin_asset;
    }

    public void setSell_fin_asset(BigDecimal sell_fin_asset) {
        this.sell_fin_asset = sell_fin_asset;
    }

    public BigDecimal getAbsorb_depo() {
        return absorb_depo;
    }

    public void setAbsorb_depo(BigDecimal absorb_depo) {
        this.absorb_depo = absorb_depo;
    }

    public BigDecimal getOther_liabilities() {
        return other_liabilities;
    }

    public void setOther_liabilities(BigDecimal other_liabilities) {
        this.other_liabilities = other_liabilities;
    }

    public BigDecimal getAdvance_insurance() {
        return advance_insurance;
    }

    public void setAdvance_insurance(BigDecimal advance_insurance) {
        this.advance_insurance = advance_insurance;
    }

    public BigDecimal getPayable_fee() {
        return payable_fee;
    }

    public void setPayable_fee(BigDecimal payable_fee) {
        this.payable_fee = payable_fee;
    }

    public BigDecimal getPay_account_rein() {
        return pay_account_rein;
    }

    public void setPay_account_rein(BigDecimal pay_account_rein) {
        this.pay_account_rein = pay_account_rein;
    }

    public BigDecimal getCompensation_payable() {
        return compensation_payable;
    }

    public void setCompensation_payable(BigDecimal compensation_payable) {
        this.compensation_payable = compensation_payable;
    }

    public BigDecimal getPolicy_dividend_payable() {
        return policy_dividend_payable;
    }

    public void setPolicy_dividend_payable(BigDecimal policy_dividend_payable) {
        this.policy_dividend_payable = policy_dividend_payable;
    }

    public BigDecimal getInsurer_deposit_investment() {
        return insurer_deposit_investment;
    }

    public void setInsurer_deposit_investment(BigDecimal insurer_deposit_investment) {
        this.insurer_deposit_investment = insurer_deposit_investment;
    }

    public BigDecimal getUnearned_premium_reserve() {
        return unearned_premium_reserve;
    }

    public void setUnearned_premium_reserve(BigDecimal unearned_premium_reserve) {
        this.unearned_premium_reserve = unearned_premium_reserve;
    }

    public BigDecimal getOutstanding_claim_reserve() {
        return outstanding_claim_reserve;
    }

    public void setOutstanding_claim_reserve(BigDecimal outstanding_claim_reserve) {
        this.outstanding_claim_reserve = outstanding_claim_reserve;
    }

    public BigDecimal getLife_insurance_reserve() {
        return life_insurance_reserve;
    }

    public void setLife_insurance_reserve(BigDecimal life_insurance_reserve) {
        this.life_insurance_reserve = life_insurance_reserve;
    }

    public BigDecimal getLong_health_insurance_lr() {
        return long_health_insurance_lr;
    }

    public void setLong_health_insurance_lr(BigDecimal long_health_insurance_lr) {
        this.long_health_insurance_lr = long_health_insurance_lr;
    }

    public BigDecimal getIndepend_liabilities() {
        return independ_liabilities;
    }

    public void setIndepend_liabilities(BigDecimal independ_liabilities) {
        this.independ_liabilities = independ_liabilities;
    }

    public BigDecimal getPledged_loan() {
        return pledged_loan;
    }

    public void setPledged_loan(BigDecimal pledged_loan) {
        this.pledged_loan = pledged_loan;
    }

    public BigDecimal getAgent_trading_secrity() {
        return agent_trading_secrity;
    }

    public void setAgent_trading_secrity(BigDecimal agent_trading_secrity) {
        this.agent_trading_secrity = agent_trading_secrity;
    }

    public BigDecimal getAct_underwrite_securities() {
        return act_underwrite_securities;
    }

    public void setAct_underwrite_securities(BigDecimal act_underwrite_securities) {
        this.act_underwrite_securities = act_underwrite_securities;
    }

    public BigDecimal getLiabilities_special_subjects() {
        return liabilities_special_subjects;
    }

    public void setLiabilities_special_subjects(BigDecimal liabilities_special_subjects) {
        this.liabilities_special_subjects = liabilities_special_subjects;
    }

    public BigDecimal getLiabilities_balance_subjects() {
        return liabilities_balance_subjects;
    }

    public void setLiabilities_balance_subjects(BigDecimal liabilities_balance_subjects) {
        this.liabilities_balance_subjects = liabilities_balance_subjects;
    }

    public BigDecimal getTotal_liabilities() {
        return total_liabilities;
    }

    public void setTotal_liabilities(BigDecimal total_liabilities) {
        this.total_liabilities = total_liabilities;
    }

    public BigDecimal getRec_capital() {
        return rec_capital;
    }

    public void setRec_capital(BigDecimal rec_capital) {
        this.rec_capital = rec_capital;
    }

    public BigDecimal getCapital_reserve() {
        return capital_reserve;
    }

    public void setCapital_reserve(BigDecimal capital_reserve) {
        this.capital_reserve = capital_reserve;
    }

    public BigDecimal getTreasury_stock() {
        return treasury_stock;
    }

    public void setTreasury_stock(BigDecimal treasury_stock) {
        this.treasury_stock = treasury_stock;
    }

    public BigDecimal getSpecial_reserve() {
        return special_reserve;
    }

    public void setSpecial_reserve(BigDecimal special_reserve) {
        this.special_reserve = special_reserve;
    }

    public BigDecimal getEarn_reserve() {
        return earn_reserve;
    }

    public void setEarn_reserve(BigDecimal earn_reserve) {
        this.earn_reserve = earn_reserve;
    }

    public BigDecimal getGeneral_normal_preparation() {
        return general_normal_preparation;
    }

    public void setGeneral_normal_preparation(BigDecimal general_normal_preparation) {
        this.general_normal_preparation = general_normal_preparation;
    }

    public BigDecimal getGeneral_risk_preparation() {
        return general_risk_preparation;
    }

    public void setGeneral_risk_preparation(BigDecimal general_risk_preparation) {
        this.general_risk_preparation = general_risk_preparation;
    }

    public BigDecimal getNopay_profit() {
        return nopay_profit;
    }

    public void setNopay_profit(BigDecimal nopay_profit) {
        this.nopay_profit = nopay_profit;
    }

    public BigDecimal getTranslation_reserve() {
        return translation_reserve;
    }

    public void setTranslation_reserve(BigDecimal translation_reserve) {
        this.translation_reserve = translation_reserve;
    }

    public BigDecimal getShareholders_equity_special_subject() {
        return shareholders_equity_special_subject;
    }

    public void setShareholders_equity_special_subject(BigDecimal shareholders_equity_special_subject) {
        this.shareholders_equity_special_subject = shareholders_equity_special_subject;
    }

    public BigDecimal getShareholders_equity_balance_subject() {
        return shareholders_equity_balance_subject;
    }

    public void setShareholders_equity_balance_subject(BigDecimal shareholders_equity_balance_subject) {
        this.shareholders_equity_balance_subject = shareholders_equity_balance_subject;
    }

    public BigDecimal getTotal_account_parent_equity() {
        return total_account_parent_equity;
    }

    public void setTotal_account_parent_equity(BigDecimal total_account_parent_equity) {
        this.total_account_parent_equity = total_account_parent_equity;
    }

    public BigDecimal getMonority_holder_equity() {
        return monority_holder_equity;
    }

    public void setMonority_holder_equity(BigDecimal monority_holder_equity) {
        this.monority_holder_equity = monority_holder_equity;
    }

    public BigDecimal getTotal_account_equity() {
        return total_account_equity;
    }

    public void setTotal_account_equity(BigDecimal total_account_equity) {
        this.total_account_equity = total_account_equity;
    }

    public BigDecimal getTotal_account_equity_and_lia() {
        return total_account_equity_and_lia;
    }

    public void setTotal_account_equity_and_lia(BigDecimal total_account_equity_and_lia) {
        this.total_account_equity_and_lia = total_account_equity_and_lia;
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

    public BigDecimal getTotal_number_of_shares() {
        return total_number_of_shares;
    }

    public void setTotal_number_of_shares(BigDecimal total_number_of_shares) {
        this.total_number_of_shares = total_number_of_shares;
    }

    public BigDecimal getRecei_dividend_contract() {
        return recei_dividend_contract;
    }

    public void setRecei_dividend_contract(BigDecimal recei_dividend_contract) {
        this.recei_dividend_contract = recei_dividend_contract;
    }

    public BigDecimal getAbsorb_depo_and_interbank() {
        return absorb_depo_and_interbank;
    }

    public void setAbsorb_depo_and_interbank(BigDecimal absorb_depo_and_interbank) {
        this.absorb_depo_and_interbank = absorb_depo_and_interbank;
    }

    public BigDecimal getInsur_contract_reserves() {
        return insur_contract_reserves;
    }

    public void setInsur_contract_reserves(BigDecimal insur_contract_reserves) {
        this.insur_contract_reserves = insur_contract_reserves;
    }

    public BigDecimal getDeferred_income_current_lia() {
        return deferred_income_current_lia;
    }

    public void setDeferred_income_current_lia(BigDecimal deferred_income_current_lia) {
        this.deferred_income_current_lia = deferred_income_current_lia;
    }

    public BigDecimal getShort_term_bonds_payable() {
        return short_term_bonds_payable;
    }

    public void setShort_term_bonds_payable(BigDecimal short_term_bonds_payable) {
        this.short_term_bonds_payable = short_term_bonds_payable;
    }

    public BigDecimal getNon_current_lia_deferred_income() {
        return non_current_lia_deferred_income;
    }

    public void setNon_current_lia_deferred_income(BigDecimal non_current_lia_deferred_income) {
        this.non_current_lia_deferred_income = non_current_lia_deferred_income;
    }

    public BigDecimal getDeposits_received() {
        return deposits_received;
    }

    public void setDeposits_received(BigDecimal deposits_received) {
        this.deposits_received = deposits_received;
    }

    public BigDecimal getFinancial_capital() {
        return financial_capital;
    }

    public void setFinancial_capital(BigDecimal financial_capital) {
        this.financial_capital = financial_capital;
    }

    public BigDecimal getReceivables() {
        return receivables;
    }

    public void setReceivables(BigDecimal receivables) {
        this.receivables = receivables;
    }

    public BigDecimal getShort_term_financing() {
        return short_term_financing;
    }

    public void setShort_term_financing(BigDecimal short_term_financing) {
        this.short_term_financing = short_term_financing;
    }

    public BigDecimal getPayables() {
        return payables;
    }

    public void setPayables(BigDecimal payables) {
        this.payables = payables;
    }

    public BigDecimal getOther_comprehensive_income() {
        return other_comprehensive_income;
    }

    public void setOther_comprehensive_income(BigDecimal other_comprehensive_income) {
        this.other_comprehensive_income = other_comprehensive_income;
    }

    public BigDecimal getLong_term_pay_for_employees() {
        return long_term_pay_for_employees;
    }

    public void setLong_term_pay_for_employees(BigDecimal long_term_pay_for_employees) {
        this.long_term_pay_for_employees = long_term_pay_for_employees;
    }

    public BigDecimal getOther_equity_instruments() {
        return other_equity_instruments;
    }

    public void setOther_equity_instruments(BigDecimal other_equity_instruments) {
        this.other_equity_instruments = other_equity_instruments;
    }

    public BigDecimal getPreferred_stock() {
        return preferred_stock;
    }

    public void setPreferred_stock(BigDecimal preferred_stock) {
        this.preferred_stock = preferred_stock;
    }

    public BigDecimal getPermanent_debt() {
        return permanent_debt;
    }

    public void setPermanent_debt(BigDecimal permanent_debt) {
        this.permanent_debt = permanent_debt;
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
        if(name.equals("total_current_asset")){
            return this.total_current_asset;
        }
        if(name.equals("fix_asset")){
            return this.fix_asset;
        }
        if(name.equals("total_asset")){
            return this.total_asset;
        }
        if(name.equals("total_liabilities")){
            return this.total_liabilities;
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
        sb.append(", announcement_date=").append(announcement_date);
        sb.append(", report_format=").append(report_format);
        sb.append(", cash=").append(cash);
        sb.append(", trading_fin_assets=").append(trading_fin_assets);
        sb.append(", rec_note=").append(rec_note);
        sb.append(", rec_account=").append(rec_account);
        sb.append(", prepay=").append(prepay);
        sb.append(", rec_interest=").append(rec_interest);
        sb.append(", rec_dividend=").append(rec_dividend);
        sb.append(", other_rec_account=").append(other_rec_account);
        sb.append(", inventory=").append(inventory);
        sb.append(", non_current_asset=").append(non_current_asset);
        sb.append(", other_current_asset=").append(other_current_asset);
        sb.append(", current_assets_special_subjects=").append(current_assets_special_subjects);
        sb.append(", current_asset_of_balance=").append(current_asset_of_balance);
        sb.append(", total_current_asset=").append(total_current_asset);
        sb.append(", available_sale_asset=").append(available_sale_asset);
        sb.append(", held_investment=").append(held_investment);
        sb.append(", long_rec_account=").append(long_rec_account);
        sb.append(", long_equity_investment=").append(long_equity_investment);
        sb.append(", invest_house=").append(invest_house);
        sb.append(", fix_asset=").append(fix_asset);
        sb.append(", building=").append(building);
        sb.append(", balance_account_asset=").append(balance_account_asset);
        sb.append(", fix_asset_dispose=").append(fix_asset_dispose);
        sb.append(", product_asset=").append(product_asset);
        sb.append(", oil_asset=").append(oil_asset);
        sb.append(", intangible_asset=").append(intangible_asset);
        sb.append(", develop_cost=").append(develop_cost);
        sb.append(", goodwill=").append(goodwill);
        sb.append(", long_defer_cost=").append(long_defer_cost);
        sb.append(", tax_asset=").append(tax_asset);
        sb.append(", other_noncurrent_asset=").append(other_noncurrent_asset);
        sb.append(", noncurrent_asset_special_subjects=").append(noncurrent_asset_special_subjects);
        sb.append(", noncurrent_asset_of_balance=").append(noncurrent_asset_of_balance);
        sb.append(", total_noncurrent_asset=").append(total_noncurrent_asset);
        sb.append(", cash_depo_cenbank=").append(cash_depo_cenbank);
        sb.append(", depo_other_bank=").append(depo_other_bank);
        sb.append(", expensive_mental=").append(expensive_mental);
        sb.append(", disassemble_fund=").append(disassemble_fund);
        sb.append(", derivat_fin_asset=").append(derivat_fin_asset);
        sb.append(", buy_fin_asset=").append(buy_fin_asset);
        sb.append(", loan_advance=").append(loan_advance);
        sb.append(", other_asset=").append(other_asset);
        sb.append(", rec_loan_account=").append(rec_loan_account);
        sb.append(", recei_premium=").append(recei_premium);
        sb.append(", receivable_subrogation=").append(receivable_subrogation);
        sb.append(", recei_dividend_payment=").append(recei_dividend_payment);
        sb.append(", recei_unearned_r=").append(recei_unearned_r);
        sb.append(", recei_claims_r=").append(recei_claims_r);
        sb.append(", recei_life_r=").append(recei_life_r);
        sb.append(", recei_long_health_r=").append(recei_long_health_r);
        sb.append(", insurer_impawn_loan=").append(insurer_impawn_loan);
        sb.append(", fixed_time_deposit=").append(fixed_time_deposit);
        sb.append(", save_capital_deposit=").append(save_capital_deposit);
        sb.append(", independ_account_assets=").append(independ_account_assets);
        sb.append(", customer_funds_deposit=").append(customer_funds_deposit);
        sb.append(", settlement_provisions=").append(settlement_provisions);
        sb.append(", customer_payment=").append(customer_payment);
        sb.append(", refundable_deposits=").append(refundable_deposits);
        sb.append(", transaction_fee=").append(transaction_fee);
        sb.append(", asset_special_subject=").append(asset_special_subject);
        sb.append(", asset_balance_subject=").append(asset_balance_subject);
        sb.append(", total_asset=").append(total_asset);
        sb.append(", short_borrow=").append(short_borrow);
        sb.append(", transation_fin_borrow=").append(transation_fin_borrow);
        sb.append(", pay_note=").append(pay_note);
        sb.append(", pay_account=").append(pay_account);
        sb.append(", prepay_account=").append(prepay_account);
        sb.append(", pay_salary=").append(pay_salary);
        sb.append(", pay_tax_balance=").append(pay_tax_balance);
        sb.append(", pay_interest=").append(pay_interest);
        sb.append(", pay_dividend=").append(pay_dividend);
        sb.append(", other_pay_account=").append(other_pay_account);
        sb.append(", non_current_borrow=").append(non_current_borrow);
        sb.append(", other_current_borrow=").append(other_current_borrow);
        sb.append(", current_lia_special_subjects=").append(current_lia_special_subjects);
        sb.append(", current_lia_balance_subjects=").append(current_lia_balance_subjects);
        sb.append(", total_current_liabilities=").append(total_current_liabilities);
        sb.append(", long_borrow=").append(long_borrow);
        sb.append(", pay_bonds=").append(pay_bonds);
        sb.append(", long_pay_account=").append(long_pay_account);
        sb.append(", term_pay_account=").append(term_pay_account);
        sb.append(", pre_bonds=").append(pre_bonds);
        sb.append(", deferred_income=").append(deferred_income);
        sb.append(", other_noncurrent_bonds=").append(other_noncurrent_bonds);
        sb.append(", non_current_lia_special_subjects=").append(non_current_lia_special_subjects);
        sb.append(", non_current_lia_balance_subjects=").append(non_current_lia_balance_subjects);
        sb.append(", total_non_current_liabilities=").append(total_non_current_liabilities);
        sb.append(", borrow_central=").append(borrow_central);
        sb.append(", peer_other_fin_depo_pay=").append(peer_other_fin_depo_pay);
        sb.append(", borrow_fund=").append(borrow_fund);
        sb.append(", derivat_fin_liabilities=").append(derivat_fin_liabilities);
        sb.append(", sell_fin_asset=").append(sell_fin_asset);
        sb.append(", absorb_depo=").append(absorb_depo);
        sb.append(", other_liabilities=").append(other_liabilities);
        sb.append(", advance_insurance=").append(advance_insurance);
        sb.append(", payable_fee=").append(payable_fee);
        sb.append(", pay_account_rein=").append(pay_account_rein);
        sb.append(", compensation_payable=").append(compensation_payable);
        sb.append(", policy_dividend_payable=").append(policy_dividend_payable);
        sb.append(", insurer_deposit_investment=").append(insurer_deposit_investment);
        sb.append(", unearned_premium_reserve=").append(unearned_premium_reserve);
        sb.append(", outstanding_claim_reserve=").append(outstanding_claim_reserve);
        sb.append(", life_insurance_reserve=").append(life_insurance_reserve);
        sb.append(", long_health_insurance_lr=").append(long_health_insurance_lr);
        sb.append(", independ_liabilities=").append(independ_liabilities);
        sb.append(", pledged_loan=").append(pledged_loan);
        sb.append(", agent_trading_secrity=").append(agent_trading_secrity);
        sb.append(", act_underwrite_securities=").append(act_underwrite_securities);
        sb.append(", liabilities_special_subjects=").append(liabilities_special_subjects);
        sb.append(", liabilities_balance_subjects=").append(liabilities_balance_subjects);
        sb.append(", total_liabilities=").append(total_liabilities);
        sb.append(", rec_capital=").append(rec_capital);
        sb.append(", capital_reserve=").append(capital_reserve);
        sb.append(", treasury_stock=").append(treasury_stock);
        sb.append(", special_reserve=").append(special_reserve);
        sb.append(", earn_reserve=").append(earn_reserve);
        sb.append(", general_normal_preparation=").append(general_normal_preparation);
        sb.append(", general_risk_preparation=").append(general_risk_preparation);
        sb.append(", nopay_profit=").append(nopay_profit);
        sb.append(", translation_reserve=").append(translation_reserve);
        sb.append(", shareholders_equity_special_subject=").append(shareholders_equity_special_subject);
        sb.append(", shareholders_equity_balance_subject=").append(shareholders_equity_balance_subject);
        sb.append(", total_account_parent_equity=").append(total_account_parent_equity);
        sb.append(", monority_holder_equity=").append(monority_holder_equity);
        sb.append(", total_account_equity=").append(total_account_equity);
        sb.append(", total_account_equity_and_lia=").append(total_account_equity_and_lia);
        sb.append(", whether_published=").append(whether_published);
        sb.append(", special_case_description=").append(special_case_description);
        sb.append(", total_number_of_shares=").append(total_number_of_shares);
        sb.append(", recei_dividend_contract=").append(recei_dividend_contract);
        sb.append(", absorb_depo_and_interbank=").append(absorb_depo_and_interbank);
        sb.append(", insur_contract_reserves=").append(insur_contract_reserves);
        sb.append(", deferred_income_current_lia=").append(deferred_income_current_lia);
        sb.append(", short_term_bonds_payable=").append(short_term_bonds_payable);
        sb.append(", non_current_lia_deferred_income=").append(non_current_lia_deferred_income);
        sb.append(", deposits_received=").append(deposits_received);
        sb.append(", financial_capital=").append(financial_capital);
        sb.append(", receivables=").append(receivables);
        sb.append(", short_term_financing=").append(short_term_financing);
        sb.append(", payables=").append(payables);
        sb.append(", other_comprehensive_income=").append(other_comprehensive_income);
        sb.append(", long_term_pay_for_employees=").append(long_term_pay_for_employees);
        sb.append(", other_equity_instruments=").append(other_equity_instruments);
        sb.append(", preferred_stock=").append(preferred_stock);
        sb.append(", permanent_debt=").append(permanent_debt);
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