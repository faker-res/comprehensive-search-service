package la.niub.abcapi.servicecompre.model;

import java.util.Date;

public class ComIndexAnaYoyModel {
    //主键id
    private String id;
    //公司统一编码
    private String com_uni_code;
    //截止日期
    private Date end_date;
    //报告期
    private String report_period;
    //基本每股收益
    private String basic_perstock_income;
    //稀释每股收益
    private String reduce_perstock_income;
    //每股净资产
    private String netassets_ps;
    //每股经营活动产生的现金流量净额
    private String ocf_ps;
    //每股收益-期末股本摊薄
    private String eps_diluted_2;
    //每股营业总收入
    private String gr_ps;
    //每股营业收入
    private String or_ps;
    //每股资本公积
    private String surpluscapital_ps;
    //每股盈余公积
    private String surplusreserve_ps;
    //每股未分配利润
    private String undistributed_ps;
    //每股留存收益
    private String retained_ps;
    //每股现金流量净额
    private String cf_ps;
    //每股息税前利润
    private String ebit_ps;
    //每股息税折旧摊销前利润
    private String ebitdaps;
    //每股收益（TTM）
    private String perstock_incomeTTM;
    //基本每股收益(扣除非经常性损益)
    private String eps_diluted_3;
    //稀释每股收益(扣除非经常性损益)
    private String eps_diluted_4;
    //期末股本摊薄每股收益(扣除非经常性损益)
    private String eps_diluted_5;
    //销售净利率
    private String netprofit_margin;
    //销售毛利率
    private String grossprofit_margin;
    //销售成本率
    private String ratio_sales_cost;
    //销售期间费用率
    private String expense_sales;
    //销售费用/营业总收入
    private String operateexpense_per_gr;
    //管理费用/营业总收入
    private String adminexpense_per_gr;
    //财务费用/营业总收入
    private String finaexpense_per_gr;
    //资产减值损失/营业总收入
    private String impair_per_gr;
    //资产减值损失/营业利润
    private String impair_per_op;
    //净资产收益率（摊薄）
    private String roe_diluted;
    //净资产收益率（平均）
    private String roe_avg;
    //年化净资产收益率
    private String roe_yearly;
    //扣除非经常性损益后全面摊薄净资产收益率
    private String deduct_roe_diluted;
    //扣除非经常性损益后加权平均净资产收益率
    private String deduct_roe_avg;
    //总资产报酬率
    private String roa_2;
    //年化总资产报酬率
    private String roa_2_yearly;
    //总资产净利率
    private String rot_a;
    //年化总资产净利率
    private String ro_a_yearly;
    //营业利润/营业总收入
    private String op_per_gr;
    //息税前利润/营业总收入
    private String ebit_per_gr;
    //营业总成本/营业总收入
    private String gc_per_gr;
    //资本回报率
    private String roic;
    //流动比率
    private String current_ratio;
    //速动比率
    private String quick_ratio;
    //保守速率比率
    private String quick_ratio_keep;
    //归属母公司股东的权益/负债合计
    private String equity_per_debt;
    //经营活动产生的现金流量净额/负债合计
    private String ocf_per_debt;
    //经营活动产生的现金流量净额/流动负债
    private String ocf_per_shortdebt;
    //经营活动产生的现金流量净额/非流动负债
    private String ocf_per_longdebt;
    //息税折旧摊销前利润/负债合计
    private String ebitda_per_debt;
    //现金流量利息保障倍数
    private String ocf_interest;
    //产权比率
    private String debt_equity;
    //长期债务与营运资金比率
    private String longdebt_workingcapital;
    //长期负债占比
    private String longdebt_debt;
    //经营活动产生的现金流量净额/净债务
    private String ocf_per_netdebt;
    //营业利润/流动负债
    private String op_per_currentborrow;
    //营业利润/负债合计
    private String op_per_bondstotal;
    //净负债率
    private String net_debt_ratio;
    //营业外收支净额/利润总额
    private String nonoperateprofit_per_ebt;
    //所得税/利润总额
    private String tax_per_ebt;
    //扣除非经常损益后的净利润/净利润
    private String deductedprofit_per_profit;
    //经营活动净收益/利润总额
    private String operateincome_per_ebt;
    //经营活动净收益/利润总额（TTM）
    private String operateincome_per_ebt_ttm;
    //营业外收支净额/利润总额（TTM）
    private String nonoperateprofit_per_ebt_ttm;
    //营业外收支净额
    private String nonoperateprofit;
    //资产负债率
    private String debt_assets;
    //权益乘数
    private String assets_equity;
    //流动资产/总资产
    private String ca_per_assets;
    //非流动资产/总资产
    private String nca_per_assets;
    //流动负债/负债合计
    private String currentdebt_per_debt;
    //非流动负债/负债合计
    private String longdeb_per_debt;
    //剔除预收款项后的资产负债率
    private String deducteddebt_assets;
    //非流动负债权益比率
    private String longdebt_equity;
    //流动负债权益比率
    private String currentdebt_equity;
    //长期资本负债率
    private String longdebt_longcaptial;
    //应收账款周转天数
    private String ar_turndays;
    //存货周转率
    private String inv_turn;
    //应收账款周转率
    private String ar_turn;
    //流动资产周转率
    private String ca_turn;
    //固定资产周转率
    private String fa_turn;
    //总资产周转率
    private String assets_turn;
    //应付账款周转率
    private String ap_turn;
    //应付账款周转天数
    private String apturn_days;
    //非流动资产周转率
    private String non_currentassets_turn;
    //资产总计(相对年初增长率)
    private String relative_assets;
    //归属母公司股东的权益(相对年初增长率)
    private String relative_equity;
    //期初净资产收益率
    private String initial_roe_diluted;
    //期末净资产收益率
    private String final_roe_diluted;
    //经营活动产生的现金流量净额/营业收入
    private String ocf_per_or;
    //经营性现金净流量/营业总收入
    private String ocf_per_sales;
    //全部资产现金回收率
    private String ocf_assets;
    //经营活动产生的现金流量净额占比
    private String ocf_cf;
    //投资活动产生的现金流量净额占比
    private String icf_cf;
    //筹资活动产生的现金流量净额占比
    private String fcf_cf;
    //销售商品提供劳务收到的现金/营业收入
    private String salescash_per_or;
    //经营活动产生的现金流量净额/经营活动净收益
    private String ocf_per_operateincome;
    //销售商品提供劳务收到的现金/营业收入（TTM）
    private String salescash_per_or_ttm;
    //经营活动产生的现金流量净额/营业收入（TTM）
    private String ocf_per_or_ttm;
    //经营活动产生的现金流量净额/经营活动净收益（TTM）
    private String ocf_per_operateincome_ttm;
    //净资产收益率
    private String roe_avg_dp;
    //归属母公司股东的净利润/净利润
    private String parentnetprofit_per_netprofit;
    //净利润/营业总收入
    private String profit_per_gr_dp;
    //净利润/利润总额
    private String netprofit_profittotal;
    //利润总额/息税前利润
    private String profittotal_per_ebit;
    //毛利
    private String grossprofit;
    //经营活动净收益
    private String operateincome;
    //息税前利润
    private String ebit;
    //归属于上市公司股东的扣除非经常性损益的净利润
    private String deduct_netprofit;
    //营业总收入（TTM）
    private String overall_income_ttm;
    //营业总成本（TTM）
    private String overall_cost_ttm;
    //营业收入（TTM）
    private String main_income_ttm;
    //销售费用（TTM）
    private String sale_cost_ttm;
    //管理费用（TTM）
    private String manage_cost_ttm;
    //财务费用（TTM）
    private String fin_cost_ttm;
    //资产减值损失（TTM）
    private String asset_loss_ttm;
    //经营活动净收益（TTM）
    private String operateincome_ttm;
    //营业利润（TTM）
    private String overall_profit_ttm;
    //营业外收支净额（TTM）
    private String nonoperateprofit_ttm;
    //息税前利润（TTM）
    private String ebit_ttm;
    //利润总额（TTM）
    private String profit_total_ttm;
    //净利润（TTM）
    private String netprofit_ttm;
    //归属母公司股东的净利润（TTM）
    private String parent_netprofit_ttm;
    //当期计提折旧与摊销
    private String current_depre_amor;
    //销售商品提供劳务收到的现金（TTM）
    private String sale_cash_ttm;
    //经营活动现金净流量（TTM）
    private String bussiness_cash_netvalue_ttm;
    //投资活动现金净流量（TTM）
    private String invest_cash_netvalue_ttm;
    //筹资活动现金净流量（TTM）
    private String borrow_cash_netvalue_ttm;
    //现金净流量（TTM）
    private String cash_netvalue_ttm;
    //营运资本
    private String workingcapital;
    //净营运资本
    private String networkingcapital;
    //留存收益
    private String retainedprofit;
    //净债务
    private String netdebt;
    //资产总计（MRQ）
    private String asset_total_mrq;
    //负债总计（MRQ）
    private String bonds_total_mrq;
    //股东权益（MRQ）
    private String equity_total_mrq;
    //归属母公司股东的权益（MRQ）
    private String parent_equity_mrq;
    //归属母公司股东的权益（最新公告）
    private String parent_equitynewreport;
    //营运资本/总资产
    private String X1;
    //留存收益/总资产
    private String X2;
    //息税前利润/总资产
    private String X3;
    //营业收入/总资产
    private String X5;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //状态
    private String status;
    //备注
    private String remark;
    //创建人
    private String creator;
    //编辑人
    private String editor;
    //每股企业自由现金流量
    private String fcff_eps;
    //净资产收益率(加权)
    private String roe_weigh;
    //净资产收益率(增发条件)
    private String roe_addi;
    //营业周期
    private String operation_cycle;
    //存货周转天数
    private String inv_turndays;
    //带息债务
    private String interest_bearing_debt;
    //全部投入资本
    private String total_invested_capital;
    //有形资产净值
    private String net_value;
    //无息流动负债
    private String non_interst;
    //无息非流动负债
    private String non_interst_non;
    //存货增长率/营业收入增长率
    private String inventory_income;
    //营业总收入3年复合增长
    private String overall_income_cagr_3y;
    //营业总收入5年复合增长
    private String overall_income_cagr_5y;
    //净利润3年复合增长
    private String net_profit_cagr_3y;
    //净利润5年复合增长
    private String net_profit_cagr_5y;
    //归属母公司股东的净利润3年复合增长
    private String net_pro_share_cagr_3y;
    //归属母公司股东的净利润5年复合增长
    private String net_pro_share_cagr_5y;
    //每股收益3年复合增长
    private String eps_cagr_3y;
    //每股收益5年复合增长
    private String eps_cagr_5y;
    //净利润现金含量
    private String net_profit_cash_cover;
    //息税折旧摊销前利润
    private String ebitda;
    //价值变动净收益
    private String value_net_change;
    //企业自由现金流量
    private String ffcf;
    //已获利息倍数
    private String ebit_per_int;
    //总资产净利率
    private String rot_a_dp;
    //净利润/营业总收入
    private String profit_per_gr;
    //平均资产总额
    private String average_total_assets;
    //平均所有者权益
    private String average_equity;
    //期初资产总额
    private String initial_total_assets;
    //期末资产总额
    private String final_total_assets;
    //营业利润
    private String op_profit;
    //期初所有者权益
    private String initial_equity;
    //期末所有者权益
    private String final_equity;
    //平均存货余额
    private String average_inventory_balance;
    //期初存货
    private String initial_inventory;
    //期末存货
    private String final_inventory;
    //平均流动资产
    private String average_currentasset;
    //期初流动资产
    private String initial_currentasset;
    //期末流动资产
    private String final_currentasset;
    //平均固定资产
    private String average_fixedassets;
    //期初固定资产
    private String initial_fixedassets;
    //期末固定资产
    private String final_fixedassets;
    //平均应付账款
    private String average_payaccount;
    //期初应付账款
    private String initial_payaccount;
    //期末应付账款
    private String final_payaccount;
    //平均应收账款
    private String average_recaccount;
    //期初应收账款
    private String initial_recaccount;
    //期末应收账款
    private String final_recaccount;
    //期初非流动资产
    private String initial_noncurrent_asset_total;
    //期末非流动资产
    private String final_noncurrent_asset_total;
    //平均非流动资产
    private String average_noncurrent_asset_total;
    //期初基本每股收益
    private String initial_basic_perstock_income;
    //期末基本每股收益
    private String final_basic_perstock_income;
    //期初稀释每股收益
    private String initial_reduce_perstock_income;
    //期末稀释每股收益
    private String final_reduce_perstock_income;
    //期初营业总收入
    private String initial_overall_income;
    //期末营业总收入
    private String final_overall_income;
    //期初营业收入
    private String initial_main_income;
    //期末营业收入
    private String final_main_income;
    //期初每股经营活动产生的现金流量净额
    private String initial_ocf_ps;
    //期末每股经营活动产生的现金流量净额
    private String final_ocf_ps;
    //年初资产总计
    private String be_year_asset_total;
    //年初归属母公司所有者权益
    private String be_year_parent_equity;
    //期初营业利润
    private String initial_overall_profit;
    //期末营业利润
    private String final_overall_profit;
    //期初利润总额
    private String initial_profit_total;
    //期末利润总额
    private String final_profit_total;
    //期初归属于母公司所有者的净利润
    private String initial_parent_netprofit;
    //期末归属于母公司所有者的净利润
    private String final_parent_netprofit;
    //期初归属于上市公司股东的扣除非经常性损益的净利润
    private String initial_deduct_netprofit;
    //期末归属于上市公司股东的扣除非经常性损益的净利润
    private String final_deduct_netprofit;
    //期初经营活动产生的现金流量净额
    private String initial_bussiness_cash_netvalue;
    //期末经营活动产生的现金流量净额
    private String final_bussiness_cash_netvalue;
    //期初资产合计
    private String initial_asset_total;
    //期末资产合计
    private String final_asset_total;
    //期初净资产
    private String initial_netasset;
    //期末净资产
    private String final_netasset;
    //期初负债合计
    private String initial_bonds_total;
    //期末负债合计
    private String final_bonds_total;
    //期初净利润
    private String initial_netprofit;
    //期末净利润
    private String final_netprofit;
    //权益乘数
    private String assets_equity_dp;
    //销售净利率
    private String netprofit_margin_dp;
    //总资产周转率
    private String assets_turn_dp;
    //息税前利润/营业总收入
    private String ebit_per_gr_dp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(String com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getReport_period() {
        return report_period;
    }

    public void setReport_period(String report_period) {
        this.report_period = report_period;
    }

    public String getBasic_perstock_income() {
        return basic_perstock_income;
    }

    public void setBasic_perstock_income(String basic_perstock_income) {
        this.basic_perstock_income = basic_perstock_income;
    }

    public String getReduce_perstock_income() {
        return reduce_perstock_income;
    }

    public void setReduce_perstock_income(String reduce_perstock_income) {
        this.reduce_perstock_income = reduce_perstock_income;
    }

    public String getNetassets_ps() {
        return netassets_ps;
    }

    public void setNetassets_ps(String netassets_ps) {
        this.netassets_ps = netassets_ps;
    }

    public String getOcf_ps() {
        return ocf_ps;
    }

    public void setOcf_ps(String ocf_ps) {
        this.ocf_ps = ocf_ps;
    }

    public String getEps_diluted_2() {
        return eps_diluted_2;
    }

    public void setEps_diluted_2(String eps_diluted_2) {
        this.eps_diluted_2 = eps_diluted_2;
    }

    public String getGr_ps() {
        return gr_ps;
    }

    public void setGr_ps(String gr_ps) {
        this.gr_ps = gr_ps;
    }

    public String getOr_ps() {
        return or_ps;
    }

    public void setOr_ps(String or_ps) {
        this.or_ps = or_ps;
    }

    public String getSurpluscapital_ps() {
        return surpluscapital_ps;
    }

    public void setSurpluscapital_ps(String surpluscapital_ps) {
        this.surpluscapital_ps = surpluscapital_ps;
    }

    public String getSurplusreserve_ps() {
        return surplusreserve_ps;
    }

    public void setSurplusreserve_ps(String surplusreserve_ps) {
        this.surplusreserve_ps = surplusreserve_ps;
    }

    public String getUndistributed_ps() {
        return undistributed_ps;
    }

    public void setUndistributed_ps(String undistributed_ps) {
        this.undistributed_ps = undistributed_ps;
    }

    public String getRetained_ps() {
        return retained_ps;
    }

    public void setRetained_ps(String retained_ps) {
        this.retained_ps = retained_ps;
    }

    public String getCf_ps() {
        return cf_ps;
    }

    public void setCf_ps(String cf_ps) {
        this.cf_ps = cf_ps;
    }

    public String getEbit_ps() {
        return ebit_ps;
    }

    public void setEbit_ps(String ebit_ps) {
        this.ebit_ps = ebit_ps;
    }

    public String getEbitdaps() {
        return ebitdaps;
    }

    public void setEbitdaps(String ebitdaps) {
        this.ebitdaps = ebitdaps;
    }

    public String getPerstock_incomeTTM() {
        return perstock_incomeTTM;
    }

    public void setPerstock_incomeTTM(String perstock_incomeTTM) {
        this.perstock_incomeTTM = perstock_incomeTTM;
    }

    public String getEps_diluted_3() {
        return eps_diluted_3;
    }

    public void setEps_diluted_3(String eps_diluted_3) {
        this.eps_diluted_3 = eps_diluted_3;
    }

    public String getEps_diluted_4() {
        return eps_diluted_4;
    }

    public void setEps_diluted_4(String eps_diluted_4) {
        this.eps_diluted_4 = eps_diluted_4;
    }

    public String getEps_diluted_5() {
        return eps_diluted_5;
    }

    public void setEps_diluted_5(String eps_diluted_5) {
        this.eps_diluted_5 = eps_diluted_5;
    }

    public String getNetprofit_margin() {
        return netprofit_margin;
    }

    public void setNetprofit_margin(String netprofit_margin) {
        this.netprofit_margin = netprofit_margin;
    }

    public String getGrossprofit_margin() {
        return grossprofit_margin;
    }

    public void setGrossprofit_margin(String grossprofit_margin) {
        this.grossprofit_margin = grossprofit_margin;
    }

    public String getRatio_sales_cost() {
        return ratio_sales_cost;
    }

    public void setRatio_sales_cost(String ratio_sales_cost) {
        this.ratio_sales_cost = ratio_sales_cost;
    }

    public String getExpense_sales() {
        return expense_sales;
    }

    public void setExpense_sales(String expense_sales) {
        this.expense_sales = expense_sales;
    }

    public String getOperateexpense_per_gr() {
        return operateexpense_per_gr;
    }

    public void setOperateexpense_per_gr(String operateexpense_per_gr) {
        this.operateexpense_per_gr = operateexpense_per_gr;
    }

    public String getAdminexpense_per_gr() {
        return adminexpense_per_gr;
    }

    public void setAdminexpense_per_gr(String adminexpense_per_gr) {
        this.adminexpense_per_gr = adminexpense_per_gr;
    }

    public String getFinaexpense_per_gr() {
        return finaexpense_per_gr;
    }

    public void setFinaexpense_per_gr(String finaexpense_per_gr) {
        this.finaexpense_per_gr = finaexpense_per_gr;
    }

    public String getImpair_per_gr() {
        return impair_per_gr;
    }

    public void setImpair_per_gr(String impair_per_gr) {
        this.impair_per_gr = impair_per_gr;
    }

    public String getImpair_per_op() {
        return impair_per_op;
    }

    public void setImpair_per_op(String impair_per_op) {
        this.impair_per_op = impair_per_op;
    }

    public String getRoe_diluted() {
        return roe_diluted;
    }

    public void setRoe_diluted(String roe_diluted) {
        this.roe_diluted = roe_diluted;
    }

    public String getRoe_avg() {
        return roe_avg;
    }

    public void setRoe_avg(String roe_avg) {
        this.roe_avg = roe_avg;
    }

    public String getRoe_yearly() {
        return roe_yearly;
    }

    public void setRoe_yearly(String roe_yearly) {
        this.roe_yearly = roe_yearly;
    }

    public String getDeduct_roe_diluted() {
        return deduct_roe_diluted;
    }

    public void setDeduct_roe_diluted(String deduct_roe_diluted) {
        this.deduct_roe_diluted = deduct_roe_diluted;
    }

    public String getDeduct_roe_avg() {
        return deduct_roe_avg;
    }

    public void setDeduct_roe_avg(String deduct_roe_avg) {
        this.deduct_roe_avg = deduct_roe_avg;
    }

    public String getRoa_2() {
        return roa_2;
    }

    public void setRoa_2(String roa_2) {
        this.roa_2 = roa_2;
    }

    public String getRoa_2_yearly() {
        return roa_2_yearly;
    }

    public void setRoa_2_yearly(String roa_2_yearly) {
        this.roa_2_yearly = roa_2_yearly;
    }

    public String getRot_a() {
        return rot_a;
    }

    public void setRot_a(String rot_a) {
        this.rot_a = rot_a;
    }

    public String getRo_a_yearly() {
        return ro_a_yearly;
    }

    public void setRo_a_yearly(String ro_a_yearly) {
        this.ro_a_yearly = ro_a_yearly;
    }

    public String getOp_per_gr() {
        return op_per_gr;
    }

    public void setOp_per_gr(String op_per_gr) {
        this.op_per_gr = op_per_gr;
    }

    public String getEbit_per_gr() {
        return ebit_per_gr;
    }

    public void setEbit_per_gr(String ebit_per_gr) {
        this.ebit_per_gr = ebit_per_gr;
    }

    public String getGc_per_gr() {
        return gc_per_gr;
    }

    public void setGc_per_gr(String gc_per_gr) {
        this.gc_per_gr = gc_per_gr;
    }

    public String getRoic() {
        return roic;
    }

    public void setRoic(String roic) {
        this.roic = roic;
    }

    public String getCurrent_ratio() {
        return current_ratio;
    }

    public void setCurrent_ratio(String current_ratio) {
        this.current_ratio = current_ratio;
    }

    public String getQuick_ratio() {
        return quick_ratio;
    }

    public void setQuick_ratio(String quick_ratio) {
        this.quick_ratio = quick_ratio;
    }

    public String getQuick_ratio_keep() {
        return quick_ratio_keep;
    }

    public void setQuick_ratio_keep(String quick_ratio_keep) {
        this.quick_ratio_keep = quick_ratio_keep;
    }

    public String getEquity_per_debt() {
        return equity_per_debt;
    }

    public void setEquity_per_debt(String equity_per_debt) {
        this.equity_per_debt = equity_per_debt;
    }

    public String getOcf_per_debt() {
        return ocf_per_debt;
    }

    public void setOcf_per_debt(String ocf_per_debt) {
        this.ocf_per_debt = ocf_per_debt;
    }

    public String getOcf_per_shortdebt() {
        return ocf_per_shortdebt;
    }

    public void setOcf_per_shortdebt(String ocf_per_shortdebt) {
        this.ocf_per_shortdebt = ocf_per_shortdebt;
    }

    public String getOcf_per_longdebt() {
        return ocf_per_longdebt;
    }

    public void setOcf_per_longdebt(String ocf_per_longdebt) {
        this.ocf_per_longdebt = ocf_per_longdebt;
    }

    public String getEbitda_per_debt() {
        return ebitda_per_debt;
    }

    public void setEbitda_per_debt(String ebitda_per_debt) {
        this.ebitda_per_debt = ebitda_per_debt;
    }

    public String getOcf_interest() {
        return ocf_interest;
    }

    public void setOcf_interest(String ocf_interest) {
        this.ocf_interest = ocf_interest;
    }

    public String getDebt_equity() {
        return debt_equity;
    }

    public void setDebt_equity(String debt_equity) {
        this.debt_equity = debt_equity;
    }

    public String getLongdebt_workingcapital() {
        return longdebt_workingcapital;
    }

    public void setLongdebt_workingcapital(String longdebt_workingcapital) {
        this.longdebt_workingcapital = longdebt_workingcapital;
    }

    public String getLongdebt_debt() {
        return longdebt_debt;
    }

    public void setLongdebt_debt(String longdebt_debt) {
        this.longdebt_debt = longdebt_debt;
    }

    public String getOcf_per_netdebt() {
        return ocf_per_netdebt;
    }

    public void setOcf_per_netdebt(String ocf_per_netdebt) {
        this.ocf_per_netdebt = ocf_per_netdebt;
    }

    public String getOp_per_currentborrow() {
        return op_per_currentborrow;
    }

    public void setOp_per_currentborrow(String op_per_currentborrow) {
        this.op_per_currentborrow = op_per_currentborrow;
    }

    public String getOp_per_bondstotal() {
        return op_per_bondstotal;
    }

    public void setOp_per_bondstotal(String op_per_bondstotal) {
        this.op_per_bondstotal = op_per_bondstotal;
    }

    public String getNet_debt_ratio() {
        return net_debt_ratio;
    }

    public void setNet_debt_ratio(String net_debt_ratio) {
        this.net_debt_ratio = net_debt_ratio;
    }

    public String getNonoperateprofit_per_ebt() {
        return nonoperateprofit_per_ebt;
    }

    public void setNonoperateprofit_per_ebt(String nonoperateprofit_per_ebt) {
        this.nonoperateprofit_per_ebt = nonoperateprofit_per_ebt;
    }

    public String getTax_per_ebt() {
        return tax_per_ebt;
    }

    public void setTax_per_ebt(String tax_per_ebt) {
        this.tax_per_ebt = tax_per_ebt;
    }

    public String getDeductedprofit_per_profit() {
        return deductedprofit_per_profit;
    }

    public void setDeductedprofit_per_profit(String deductedprofit_per_profit) {
        this.deductedprofit_per_profit = deductedprofit_per_profit;
    }

    public String getOperateincome_per_ebt() {
        return operateincome_per_ebt;
    }

    public void setOperateincome_per_ebt(String operateincome_per_ebt) {
        this.operateincome_per_ebt = operateincome_per_ebt;
    }

    public String getOperateincome_per_ebt_ttm() {
        return operateincome_per_ebt_ttm;
    }

    public void setOperateincome_per_ebt_ttm(String operateincome_per_ebt_ttm) {
        this.operateincome_per_ebt_ttm = operateincome_per_ebt_ttm;
    }

    public String getNonoperateprofit_per_ebt_ttm() {
        return nonoperateprofit_per_ebt_ttm;
    }

    public void setNonoperateprofit_per_ebt_ttm(String nonoperateprofit_per_ebt_ttm) {
        this.nonoperateprofit_per_ebt_ttm = nonoperateprofit_per_ebt_ttm;
    }

    public String getNonoperateprofit() {
        return nonoperateprofit;
    }

    public void setNonoperateprofit(String nonoperateprofit) {
        this.nonoperateprofit = nonoperateprofit;
    }

    public String getDebt_assets() {
        return debt_assets;
    }

    public void setDebt_assets(String debt_assets) {
        this.debt_assets = debt_assets;
    }

    public String getAssets_equity() {
        return assets_equity;
    }

    public void setAssets_equity(String assets_equity) {
        this.assets_equity = assets_equity;
    }

    public String getCa_per_assets() {
        return ca_per_assets;
    }

    public void setCa_per_assets(String ca_per_assets) {
        this.ca_per_assets = ca_per_assets;
    }

    public String getNca_per_assets() {
        return nca_per_assets;
    }

    public void setNca_per_assets(String nca_per_assets) {
        this.nca_per_assets = nca_per_assets;
    }

    public String getCurrentdebt_per_debt() {
        return currentdebt_per_debt;
    }

    public void setCurrentdebt_per_debt(String currentdebt_per_debt) {
        this.currentdebt_per_debt = currentdebt_per_debt;
    }

    public String getLongdeb_per_debt() {
        return longdeb_per_debt;
    }

    public void setLongdeb_per_debt(String longdeb_per_debt) {
        this.longdeb_per_debt = longdeb_per_debt;
    }

    public String getDeducteddebt_assets() {
        return deducteddebt_assets;
    }

    public void setDeducteddebt_assets(String deducteddebt_assets) {
        this.deducteddebt_assets = deducteddebt_assets;
    }

    public String getLongdebt_equity() {
        return longdebt_equity;
    }

    public void setLongdebt_equity(String longdebt_equity) {
        this.longdebt_equity = longdebt_equity;
    }

    public String getCurrentdebt_equity() {
        return currentdebt_equity;
    }

    public void setCurrentdebt_equity(String currentdebt_equity) {
        this.currentdebt_equity = currentdebt_equity;
    }

    public String getLongdebt_longcaptial() {
        return longdebt_longcaptial;
    }

    public void setLongdebt_longcaptial(String longdebt_longcaptial) {
        this.longdebt_longcaptial = longdebt_longcaptial;
    }

    public String getAr_turndays() {
        return ar_turndays;
    }

    public void setAr_turndays(String ar_turndays) {
        this.ar_turndays = ar_turndays;
    }

    public String getInv_turn() {
        return inv_turn;
    }

    public void setInv_turn(String inv_turn) {
        this.inv_turn = inv_turn;
    }

    public String getAr_turn() {
        return ar_turn;
    }

    public void setAr_turn(String ar_turn) {
        this.ar_turn = ar_turn;
    }

    public String getCa_turn() {
        return ca_turn;
    }

    public void setCa_turn(String ca_turn) {
        this.ca_turn = ca_turn;
    }

    public String getFa_turn() {
        return fa_turn;
    }

    public void setFa_turn(String fa_turn) {
        this.fa_turn = fa_turn;
    }

    public String getAssets_turn() {
        return assets_turn;
    }

    public void setAssets_turn(String assets_turn) {
        this.assets_turn = assets_turn;
    }

    public String getAp_turn() {
        return ap_turn;
    }

    public void setAp_turn(String ap_turn) {
        this.ap_turn = ap_turn;
    }

    public String getApturn_days() {
        return apturn_days;
    }

    public void setApturn_days(String apturn_days) {
        this.apturn_days = apturn_days;
    }

    public String getNon_currentassets_turn() {
        return non_currentassets_turn;
    }

    public void setNon_currentassets_turn(String non_currentassets_turn) {
        this.non_currentassets_turn = non_currentassets_turn;
    }

    public String getRelative_assets() {
        return relative_assets;
    }

    public void setRelative_assets(String relative_assets) {
        this.relative_assets = relative_assets;
    }

    public String getRelative_equity() {
        return relative_equity;
    }

    public void setRelative_equity(String relative_equity) {
        this.relative_equity = relative_equity;
    }

    public String getInitial_roe_diluted() {
        return initial_roe_diluted;
    }

    public void setInitial_roe_diluted(String initial_roe_diluted) {
        this.initial_roe_diluted = initial_roe_diluted;
    }

    public String getFinal_roe_diluted() {
        return final_roe_diluted;
    }

    public void setFinal_roe_diluted(String final_roe_diluted) {
        this.final_roe_diluted = final_roe_diluted;
    }

    public String getOcf_per_or() {
        return ocf_per_or;
    }

    public void setOcf_per_or(String ocf_per_or) {
        this.ocf_per_or = ocf_per_or;
    }

    public String getOcf_per_sales() {
        return ocf_per_sales;
    }

    public void setOcf_per_sales(String ocf_per_sales) {
        this.ocf_per_sales = ocf_per_sales;
    }

    public String getOcf_assets() {
        return ocf_assets;
    }

    public void setOcf_assets(String ocf_assets) {
        this.ocf_assets = ocf_assets;
    }

    public String getOcf_cf() {
        return ocf_cf;
    }

    public void setOcf_cf(String ocf_cf) {
        this.ocf_cf = ocf_cf;
    }

    public String getIcf_cf() {
        return icf_cf;
    }

    public void setIcf_cf(String icf_cf) {
        this.icf_cf = icf_cf;
    }

    public String getFcf_cf() {
        return fcf_cf;
    }

    public void setFcf_cf(String fcf_cf) {
        this.fcf_cf = fcf_cf;
    }

    public String getSalescash_per_or() {
        return salescash_per_or;
    }

    public void setSalescash_per_or(String salescash_per_or) {
        this.salescash_per_or = salescash_per_or;
    }

    public String getOcf_per_operateincome() {
        return ocf_per_operateincome;
    }

    public void setOcf_per_operateincome(String ocf_per_operateincome) {
        this.ocf_per_operateincome = ocf_per_operateincome;
    }

    public String getSalescash_per_or_ttm() {
        return salescash_per_or_ttm;
    }

    public void setSalescash_per_or_ttm(String salescash_per_or_ttm) {
        this.salescash_per_or_ttm = salescash_per_or_ttm;
    }

    public String getOcf_per_or_ttm() {
        return ocf_per_or_ttm;
    }

    public void setOcf_per_or_ttm(String ocf_per_or_ttm) {
        this.ocf_per_or_ttm = ocf_per_or_ttm;
    }

    public String getOcf_per_operateincome_ttm() {
        return ocf_per_operateincome_ttm;
    }

    public void setOcf_per_operateincome_ttm(String ocf_per_operateincome_ttm) {
        this.ocf_per_operateincome_ttm = ocf_per_operateincome_ttm;
    }

    public String getRoe_avg_dp() {
        return roe_avg_dp;
    }

    public void setRoe_avg_dp(String roe_avg_dp) {
        this.roe_avg_dp = roe_avg_dp;
    }

    public String getParentnetprofit_per_netprofit() {
        return parentnetprofit_per_netprofit;
    }

    public void setParentnetprofit_per_netprofit(String parentnetprofit_per_netprofit) {
        this.parentnetprofit_per_netprofit = parentnetprofit_per_netprofit;
    }

    public String getProfit_per_gr_dp() {
        return profit_per_gr_dp;
    }

    public void setProfit_per_gr_dp(String profit_per_gr_dp) {
        this.profit_per_gr_dp = profit_per_gr_dp;
    }

    public String getNetprofit_profittotal() {
        return netprofit_profittotal;
    }

    public void setNetprofit_profittotal(String netprofit_profittotal) {
        this.netprofit_profittotal = netprofit_profittotal;
    }

    public String getProfittotal_per_ebit() {
        return profittotal_per_ebit;
    }

    public void setProfittotal_per_ebit(String profittotal_per_ebit) {
        this.profittotal_per_ebit = profittotal_per_ebit;
    }

    public String getGrossprofit() {
        return grossprofit;
    }

    public void setGrossprofit(String grossprofit) {
        this.grossprofit = grossprofit;
    }

    public String getOperateincome() {
        return operateincome;
    }

    public void setOperateincome(String operateincome) {
        this.operateincome = operateincome;
    }

    public String getEbit() {
        return ebit;
    }

    public void setEbit(String ebit) {
        this.ebit = ebit;
    }

    public String getDeduct_netprofit() {
        return deduct_netprofit;
    }

    public void setDeduct_netprofit(String deduct_netprofit) {
        this.deduct_netprofit = deduct_netprofit;
    }

    public String getOverall_income_ttm() {
        return overall_income_ttm;
    }

    public void setOverall_income_ttm(String overall_income_ttm) {
        this.overall_income_ttm = overall_income_ttm;
    }

    public String getOverall_cost_ttm() {
        return overall_cost_ttm;
    }

    public void setOverall_cost_ttm(String overall_cost_ttm) {
        this.overall_cost_ttm = overall_cost_ttm;
    }

    public String getMain_income_ttm() {
        return main_income_ttm;
    }

    public void setMain_income_ttm(String main_income_ttm) {
        this.main_income_ttm = main_income_ttm;
    }

    public String getSale_cost_ttm() {
        return sale_cost_ttm;
    }

    public void setSale_cost_ttm(String sale_cost_ttm) {
        this.sale_cost_ttm = sale_cost_ttm;
    }

    public String getManage_cost_ttm() {
        return manage_cost_ttm;
    }

    public void setManage_cost_ttm(String manage_cost_ttm) {
        this.manage_cost_ttm = manage_cost_ttm;
    }

    public String getFin_cost_ttm() {
        return fin_cost_ttm;
    }

    public void setFin_cost_ttm(String fin_cost_ttm) {
        this.fin_cost_ttm = fin_cost_ttm;
    }

    public String getAsset_loss_ttm() {
        return asset_loss_ttm;
    }

    public void setAsset_loss_ttm(String asset_loss_ttm) {
        this.asset_loss_ttm = asset_loss_ttm;
    }

    public String getOperateincome_ttm() {
        return operateincome_ttm;
    }

    public void setOperateincome_ttm(String operateincome_ttm) {
        this.operateincome_ttm = operateincome_ttm;
    }

    public String getOverall_profit_ttm() {
        return overall_profit_ttm;
    }

    public void setOverall_profit_ttm(String overall_profit_ttm) {
        this.overall_profit_ttm = overall_profit_ttm;
    }

    public String getNonoperateprofit_ttm() {
        return nonoperateprofit_ttm;
    }

    public void setNonoperateprofit_ttm(String nonoperateprofit_ttm) {
        this.nonoperateprofit_ttm = nonoperateprofit_ttm;
    }

    public String getEbit_ttm() {
        return ebit_ttm;
    }

    public void setEbit_ttm(String ebit_ttm) {
        this.ebit_ttm = ebit_ttm;
    }

    public String getProfit_total_ttm() {
        return profit_total_ttm;
    }

    public void setProfit_total_ttm(String profit_total_ttm) {
        this.profit_total_ttm = profit_total_ttm;
    }

    public String getNetprofit_ttm() {
        return netprofit_ttm;
    }

    public void setNetprofit_ttm(String netprofit_ttm) {
        this.netprofit_ttm = netprofit_ttm;
    }

    public String getParent_netprofit_ttm() {
        return parent_netprofit_ttm;
    }

    public void setParent_netprofit_ttm(String parent_netprofit_ttm) {
        this.parent_netprofit_ttm = parent_netprofit_ttm;
    }

    public String getCurrent_depre_amor() {
        return current_depre_amor;
    }

    public void setCurrent_depre_amor(String current_depre_amor) {
        this.current_depre_amor = current_depre_amor;
    }

    public String getSale_cash_ttm() {
        return sale_cash_ttm;
    }

    public void setSale_cash_ttm(String sale_cash_ttm) {
        this.sale_cash_ttm = sale_cash_ttm;
    }

    public String getBussiness_cash_netvalue_ttm() {
        return bussiness_cash_netvalue_ttm;
    }

    public void setBussiness_cash_netvalue_ttm(String bussiness_cash_netvalue_ttm) {
        this.bussiness_cash_netvalue_ttm = bussiness_cash_netvalue_ttm;
    }

    public String getInvest_cash_netvalue_ttm() {
        return invest_cash_netvalue_ttm;
    }

    public void setInvest_cash_netvalue_ttm(String invest_cash_netvalue_ttm) {
        this.invest_cash_netvalue_ttm = invest_cash_netvalue_ttm;
    }

    public String getBorrow_cash_netvalue_ttm() {
        return borrow_cash_netvalue_ttm;
    }

    public void setBorrow_cash_netvalue_ttm(String borrow_cash_netvalue_ttm) {
        this.borrow_cash_netvalue_ttm = borrow_cash_netvalue_ttm;
    }

    public String getCash_netvalue_ttm() {
        return cash_netvalue_ttm;
    }

    public void setCash_netvalue_ttm(String cash_netvalue_ttm) {
        this.cash_netvalue_ttm = cash_netvalue_ttm;
    }

    public String getWorkingcapital() {
        return workingcapital;
    }

    public void setWorkingcapital(String workingcapital) {
        this.workingcapital = workingcapital;
    }

    public String getNetworkingcapital() {
        return networkingcapital;
    }

    public void setNetworkingcapital(String networkingcapital) {
        this.networkingcapital = networkingcapital;
    }

    public String getRetainedprofit() {
        return retainedprofit;
    }

    public void setRetainedprofit(String retainedprofit) {
        this.retainedprofit = retainedprofit;
    }

    public String getNetdebt() {
        return netdebt;
    }

    public void setNetdebt(String netdebt) {
        this.netdebt = netdebt;
    }

    public String getAsset_total_mrq() {
        return asset_total_mrq;
    }

    public void setAsset_total_mrq(String asset_total_mrq) {
        this.asset_total_mrq = asset_total_mrq;
    }

    public String getBonds_total_mrq() {
        return bonds_total_mrq;
    }

    public void setBonds_total_mrq(String bonds_total_mrq) {
        this.bonds_total_mrq = bonds_total_mrq;
    }

    public String getEquity_total_mrq() {
        return equity_total_mrq;
    }

    public void setEquity_total_mrq(String equity_total_mrq) {
        this.equity_total_mrq = equity_total_mrq;
    }

    public String getParent_equity_mrq() {
        return parent_equity_mrq;
    }

    public void setParent_equity_mrq(String parent_equity_mrq) {
        this.parent_equity_mrq = parent_equity_mrq;
    }

    public String getParent_equitynewreport() {
        return parent_equitynewreport;
    }

    public void setParent_equitynewreport(String parent_equitynewreport) {
        this.parent_equitynewreport = parent_equitynewreport;
    }

    public String getX1() {
        return X1;
    }

    public void setX1(String x1) {
        X1 = x1;
    }

    public String getX2() {
        return X2;
    }

    public void setX2(String x2) {
        X2 = x2;
    }

    public String getX3() {
        return X3;
    }

    public void setX3(String x3) {
        X3 = x3;
    }

    public String getX5() {
        return X5;
    }

    public void setX5(String x5) {
        X5 = x5;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getFcff_eps() {
        return fcff_eps;
    }

    public void setFcff_eps(String fcff_eps) {
        this.fcff_eps = fcff_eps;
    }

    public String getRoe_weigh() {
        return roe_weigh;
    }

    public void setRoe_weigh(String roe_weigh) {
        this.roe_weigh = roe_weigh;
    }

    public String getRoe_addi() {
        return roe_addi;
    }

    public void setRoe_addi(String roe_addi) {
        this.roe_addi = roe_addi;
    }

    public String getOperation_cycle() {
        return operation_cycle;
    }

    public void setOperation_cycle(String operation_cycle) {
        this.operation_cycle = operation_cycle;
    }

    public String getInv_turndays() {
        return inv_turndays;
    }

    public void setInv_turndays(String inv_turndays) {
        this.inv_turndays = inv_turndays;
    }

    public String getInterest_bearing_debt() {
        return interest_bearing_debt;
    }

    public void setInterest_bearing_debt(String interest_bearing_debt) {
        this.interest_bearing_debt = interest_bearing_debt;
    }

    public String getTotal_invested_capital() {
        return total_invested_capital;
    }

    public void setTotal_invested_capital(String total_invested_capital) {
        this.total_invested_capital = total_invested_capital;
    }

    public String getNet_value() {
        return net_value;
    }

    public void setNet_value(String net_value) {
        this.net_value = net_value;
    }

    public String getNon_interst() {
        return non_interst;
    }

    public void setNon_interst(String non_interst) {
        this.non_interst = non_interst;
    }

    public String getNon_interst_non() {
        return non_interst_non;
    }

    public void setNon_interst_non(String non_interst_non) {
        this.non_interst_non = non_interst_non;
    }

    public String getInventory_income() {
        return inventory_income;
    }

    public void setInventory_income(String inventory_income) {
        this.inventory_income = inventory_income;
    }

    public String getOverall_income_cagr_3y() {
        return overall_income_cagr_3y;
    }

    public void setOverall_income_cagr_3y(String overall_income_cagr_3y) {
        this.overall_income_cagr_3y = overall_income_cagr_3y;
    }

    public String getOverall_income_cagr_5y() {
        return overall_income_cagr_5y;
    }

    public void setOverall_income_cagr_5y(String overall_income_cagr_5y) {
        this.overall_income_cagr_5y = overall_income_cagr_5y;
    }

    public String getNet_profit_cagr_3y() {
        return net_profit_cagr_3y;
    }

    public void setNet_profit_cagr_3y(String net_profit_cagr_3y) {
        this.net_profit_cagr_3y = net_profit_cagr_3y;
    }

    public String getNet_profit_cagr_5y() {
        return net_profit_cagr_5y;
    }

    public void setNet_profit_cagr_5y(String net_profit_cagr_5y) {
        this.net_profit_cagr_5y = net_profit_cagr_5y;
    }

    public String getNet_pro_share_cagr_3y() {
        return net_pro_share_cagr_3y;
    }

    public void setNet_pro_share_cagr_3y(String net_pro_share_cagr_3y) {
        this.net_pro_share_cagr_3y = net_pro_share_cagr_3y;
    }

    public String getNet_pro_share_cagr_5y() {
        return net_pro_share_cagr_5y;
    }

    public void setNet_pro_share_cagr_5y(String net_pro_share_cagr_5y) {
        this.net_pro_share_cagr_5y = net_pro_share_cagr_5y;
    }

    public String getEps_cagr_3y() {
        return eps_cagr_3y;
    }

    public void setEps_cagr_3y(String eps_cagr_3y) {
        this.eps_cagr_3y = eps_cagr_3y;
    }

    public String getEps_cagr_5y() {
        return eps_cagr_5y;
    }

    public void setEps_cagr_5y(String eps_cagr_5y) {
        this.eps_cagr_5y = eps_cagr_5y;
    }

    public String getNet_profit_cash_cover() {
        return net_profit_cash_cover;
    }

    public void setNet_profit_cash_cover(String net_profit_cash_cover) {
        this.net_profit_cash_cover = net_profit_cash_cover;
    }

    public String getEbitda() {
        return ebitda;
    }

    public void setEbitda(String ebitda) {
        this.ebitda = ebitda;
    }

    public String getValue_net_change() {
        return value_net_change;
    }

    public void setValue_net_change(String value_net_change) {
        this.value_net_change = value_net_change;
    }

    public String getFfcf() {
        return ffcf;
    }

    public void setFfcf(String ffcf) {
        this.ffcf = ffcf;
    }

    public String getEbit_per_int() {
        return ebit_per_int;
    }

    public void setEbit_per_int(String ebit_per_int) {
        this.ebit_per_int = ebit_per_int;
    }

    public String getRot_a_dp() {
        return rot_a_dp;
    }

    public void setRot_a_dp(String rot_a_dp) {
        this.rot_a_dp = rot_a_dp;
    }

    public String getProfit_per_gr() {
        return profit_per_gr;
    }

    public void setProfit_per_gr(String profit_per_gr) {
        this.profit_per_gr = profit_per_gr;
    }

    public String getAverage_total_assets() {
        return average_total_assets;
    }

    public void setAverage_total_assets(String average_total_assets) {
        this.average_total_assets = average_total_assets;
    }

    public String getAverage_equity() {
        return average_equity;
    }

    public void setAverage_equity(String average_equity) {
        this.average_equity = average_equity;
    }

    public String getInitial_total_assets() {
        return initial_total_assets;
    }

    public void setInitial_total_assets(String initial_total_assets) {
        this.initial_total_assets = initial_total_assets;
    }

    public String getFinal_total_assets() {
        return final_total_assets;
    }

    public void setFinal_total_assets(String final_total_assets) {
        this.final_total_assets = final_total_assets;
    }

    public String getOp_profit() {
        return op_profit;
    }

    public void setOp_profit(String op_profit) {
        this.op_profit = op_profit;
    }

    public String getInitial_equity() {
        return initial_equity;
    }

    public void setInitial_equity(String initial_equity) {
        this.initial_equity = initial_equity;
    }

    public String getFinal_equity() {
        return final_equity;
    }

    public void setFinal_equity(String final_equity) {
        this.final_equity = final_equity;
    }

    public String getAverage_inventory_balance() {
        return average_inventory_balance;
    }

    public void setAverage_inventory_balance(String average_inventory_balance) {
        this.average_inventory_balance = average_inventory_balance;
    }

    public String getInitial_inventory() {
        return initial_inventory;
    }

    public void setInitial_inventory(String initial_inventory) {
        this.initial_inventory = initial_inventory;
    }

    public String getFinal_inventory() {
        return final_inventory;
    }

    public void setFinal_inventory(String final_inventory) {
        this.final_inventory = final_inventory;
    }

    public String getAverage_currentasset() {
        return average_currentasset;
    }

    public void setAverage_currentasset(String average_currentasset) {
        this.average_currentasset = average_currentasset;
    }

    public String getInitial_currentasset() {
        return initial_currentasset;
    }

    public void setInitial_currentasset(String initial_currentasset) {
        this.initial_currentasset = initial_currentasset;
    }

    public String getFinal_currentasset() {
        return final_currentasset;
    }

    public void setFinal_currentasset(String final_currentasset) {
        this.final_currentasset = final_currentasset;
    }

    public String getAverage_fixedassets() {
        return average_fixedassets;
    }

    public void setAverage_fixedassets(String average_fixedassets) {
        this.average_fixedassets = average_fixedassets;
    }

    public String getInitial_fixedassets() {
        return initial_fixedassets;
    }

    public void setInitial_fixedassets(String initial_fixedassets) {
        this.initial_fixedassets = initial_fixedassets;
    }

    public String getFinal_fixedassets() {
        return final_fixedassets;
    }

    public void setFinal_fixedassets(String final_fixedassets) {
        this.final_fixedassets = final_fixedassets;
    }

    public String getAverage_payaccount() {
        return average_payaccount;
    }

    public void setAverage_payaccount(String average_payaccount) {
        this.average_payaccount = average_payaccount;
    }

    public String getInitial_payaccount() {
        return initial_payaccount;
    }

    public void setInitial_payaccount(String initial_payaccount) {
        this.initial_payaccount = initial_payaccount;
    }

    public String getFinal_payaccount() {
        return final_payaccount;
    }

    public void setFinal_payaccount(String final_payaccount) {
        this.final_payaccount = final_payaccount;
    }

    public String getAverage_recaccount() {
        return average_recaccount;
    }

    public void setAverage_recaccount(String average_recaccount) {
        this.average_recaccount = average_recaccount;
    }

    public String getInitial_recaccount() {
        return initial_recaccount;
    }

    public void setInitial_recaccount(String initial_recaccount) {
        this.initial_recaccount = initial_recaccount;
    }

    public String getFinal_recaccount() {
        return final_recaccount;
    }

    public void setFinal_recaccount(String final_recaccount) {
        this.final_recaccount = final_recaccount;
    }

    public String getInitial_noncurrent_asset_total() {
        return initial_noncurrent_asset_total;
    }

    public void setInitial_noncurrent_asset_total(String initial_noncurrent_asset_total) {
        this.initial_noncurrent_asset_total = initial_noncurrent_asset_total;
    }

    public String getFinal_noncurrent_asset_total() {
        return final_noncurrent_asset_total;
    }

    public void setFinal_noncurrent_asset_total(String final_noncurrent_asset_total) {
        this.final_noncurrent_asset_total = final_noncurrent_asset_total;
    }

    public String getAverage_noncurrent_asset_total() {
        return average_noncurrent_asset_total;
    }

    public void setAverage_noncurrent_asset_total(String average_noncurrent_asset_total) {
        this.average_noncurrent_asset_total = average_noncurrent_asset_total;
    }

    public String getInitial_basic_perstock_income() {
        return initial_basic_perstock_income;
    }

    public void setInitial_basic_perstock_income(String initial_basic_perstock_income) {
        this.initial_basic_perstock_income = initial_basic_perstock_income;
    }

    public String getFinal_basic_perstock_income() {
        return final_basic_perstock_income;
    }

    public void setFinal_basic_perstock_income(String final_basic_perstock_income) {
        this.final_basic_perstock_income = final_basic_perstock_income;
    }

    public String getInitial_reduce_perstock_income() {
        return initial_reduce_perstock_income;
    }

    public void setInitial_reduce_perstock_income(String initial_reduce_perstock_income) {
        this.initial_reduce_perstock_income = initial_reduce_perstock_income;
    }

    public String getFinal_reduce_perstock_income() {
        return final_reduce_perstock_income;
    }

    public void setFinal_reduce_perstock_income(String final_reduce_perstock_income) {
        this.final_reduce_perstock_income = final_reduce_perstock_income;
    }

    public String getInitial_overall_income() {
        return initial_overall_income;
    }

    public void setInitial_overall_income(String initial_overall_income) {
        this.initial_overall_income = initial_overall_income;
    }

    public String getFinal_overall_income() {
        return final_overall_income;
    }

    public void setFinal_overall_income(String final_overall_income) {
        this.final_overall_income = final_overall_income;
    }

    public String getInitial_main_income() {
        return initial_main_income;
    }

    public void setInitial_main_income(String initial_main_income) {
        this.initial_main_income = initial_main_income;
    }

    public String getFinal_main_income() {
        return final_main_income;
    }

    public void setFinal_main_income(String final_main_income) {
        this.final_main_income = final_main_income;
    }

    public String getInitial_ocf_ps() {
        return initial_ocf_ps;
    }

    public void setInitial_ocf_ps(String initial_ocf_ps) {
        this.initial_ocf_ps = initial_ocf_ps;
    }

    public String getFinal_ocf_ps() {
        return final_ocf_ps;
    }

    public void setFinal_ocf_ps(String final_ocf_ps) {
        this.final_ocf_ps = final_ocf_ps;
    }

    public String getBe_year_asset_total() {
        return be_year_asset_total;
    }

    public void setBe_year_asset_total(String be_year_asset_total) {
        this.be_year_asset_total = be_year_asset_total;
    }

    public String getBe_year_parent_equity() {
        return be_year_parent_equity;
    }

    public void setBe_year_parent_equity(String be_year_parent_equity) {
        this.be_year_parent_equity = be_year_parent_equity;
    }

    public String getInitial_overall_profit() {
        return initial_overall_profit;
    }

    public void setInitial_overall_profit(String initial_overall_profit) {
        this.initial_overall_profit = initial_overall_profit;
    }

    public String getFinal_overall_profit() {
        return final_overall_profit;
    }

    public void setFinal_overall_profit(String final_overall_profit) {
        this.final_overall_profit = final_overall_profit;
    }

    public String getInitial_profit_total() {
        return initial_profit_total;
    }

    public void setInitial_profit_total(String initial_profit_total) {
        this.initial_profit_total = initial_profit_total;
    }

    public String getFinal_profit_total() {
        return final_profit_total;
    }

    public void setFinal_profit_total(String final_profit_total) {
        this.final_profit_total = final_profit_total;
    }

    public String getInitial_parent_netprofit() {
        return initial_parent_netprofit;
    }

    public void setInitial_parent_netprofit(String initial_parent_netprofit) {
        this.initial_parent_netprofit = initial_parent_netprofit;
    }

    public String getFinal_parent_netprofit() {
        return final_parent_netprofit;
    }

    public void setFinal_parent_netprofit(String final_parent_netprofit) {
        this.final_parent_netprofit = final_parent_netprofit;
    }

    public String getInitial_deduct_netprofit() {
        return initial_deduct_netprofit;
    }

    public void setInitial_deduct_netprofit(String initial_deduct_netprofit) {
        this.initial_deduct_netprofit = initial_deduct_netprofit;
    }

    public String getFinal_deduct_netprofit() {
        return final_deduct_netprofit;
    }

    public void setFinal_deduct_netprofit(String final_deduct_netprofit) {
        this.final_deduct_netprofit = final_deduct_netprofit;
    }

    public String getInitial_bussiness_cash_netvalue() {
        return initial_bussiness_cash_netvalue;
    }

    public void setInitial_bussiness_cash_netvalue(String initial_bussiness_cash_netvalue) {
        this.initial_bussiness_cash_netvalue = initial_bussiness_cash_netvalue;
    }

    public String getFinal_bussiness_cash_netvalue() {
        return final_bussiness_cash_netvalue;
    }

    public void setFinal_bussiness_cash_netvalue(String final_bussiness_cash_netvalue) {
        this.final_bussiness_cash_netvalue = final_bussiness_cash_netvalue;
    }

    public String getInitial_asset_total() {
        return initial_asset_total;
    }

    public void setInitial_asset_total(String initial_asset_total) {
        this.initial_asset_total = initial_asset_total;
    }

    public String getFinal_asset_total() {
        return final_asset_total;
    }

    public void setFinal_asset_total(String final_asset_total) {
        this.final_asset_total = final_asset_total;
    }

    public String getInitial_netasset() {
        return initial_netasset;
    }

    public void setInitial_netasset(String initial_netasset) {
        this.initial_netasset = initial_netasset;
    }

    public String getFinal_netasset() {
        return final_netasset;
    }

    public void setFinal_netasset(String final_netasset) {
        this.final_netasset = final_netasset;
    }

    public String getInitial_bonds_total() {
        return initial_bonds_total;
    }

    public void setInitial_bonds_total(String initial_bonds_total) {
        this.initial_bonds_total = initial_bonds_total;
    }

    public String getFinal_bonds_total() {
        return final_bonds_total;
    }

    public void setFinal_bonds_total(String final_bonds_total) {
        this.final_bonds_total = final_bonds_total;
    }

    public String getInitial_netprofit() {
        return initial_netprofit;
    }

    public void setInitial_netprofit(String initial_netprofit) {
        this.initial_netprofit = initial_netprofit;
    }

    public String getFinal_netprofit() {
        return final_netprofit;
    }

    public void setFinal_netprofit(String final_netprofit) {
        this.final_netprofit = final_netprofit;
    }

    public String getAssets_equity_dp() {
        return assets_equity_dp;
    }

    public void setAssets_equity_dp(String assets_equity_dp) {
        this.assets_equity_dp = assets_equity_dp;
    }

    public String getNetprofit_margin_dp() {
        return netprofit_margin_dp;
    }

    public void setNetprofit_margin_dp(String netprofit_margin_dp) {
        this.netprofit_margin_dp = netprofit_margin_dp;
    }

    public String getAssets_turn_dp() {
        return assets_turn_dp;
    }

    public void setAssets_turn_dp(String assets_turn_dp) {
        this.assets_turn_dp = assets_turn_dp;
    }

    public String getEbit_per_gr_dp() {
        return ebit_per_gr_dp;
    }

    public void setEbit_per_gr_dp(String ebit_per_gr_dp) {
        this.ebit_per_gr_dp = ebit_per_gr_dp;
    }
}
