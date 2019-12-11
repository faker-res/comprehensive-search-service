package la.niub.abcapi.servicecompre.model.response.rentity;
//公司利润摘要
public class RCompanyProfitBrief {
    //营业总收入
    private String overall_income;
    //营业同比
    private String overall_income_y2y;
    //营业总成本
    private String overall_cost;
    //营业利润
    private String overall_profit;
    //营业利润同比
    private String overall_profit_y2y;
    //利润总额
    private String profit_total;
    //利润总额同比
    private String profit_total_y2y;
    //净利润
    private String netprofit;
    //归属母公司股东的净利润
    private String parent_netprofit;
    //归属母公司股东的净利润同比
    private String parent_netprofit_y2y;
    //非经常性损益
    private String total_amount;
    //扣非后归属母公司股东的净利润
    private String deduct_netprofit;
    //扣非后归属母公司股东的净利润同比
    private String deduct_netprofit_y2y;
    //研发费用
    private String rd_total;
    //EBIT
    private String ebit;
    //EBITDA
    private String ebitda;

    public String getOverall_income() {
        return overall_income;
    }

    public void setOverall_income(String overall_income) {
        this.overall_income = overall_income;
    }

    public String getOverall_income_y2y() {
        return overall_income_y2y;
    }

    public void setOverall_income_y2y(String overall_income_y2y) {
        this.overall_income_y2y = overall_income_y2y;
    }

    public String getOverall_cost() {
        return overall_cost;
    }

    public void setOverall_cost(String overall_cost) {
        this.overall_cost = overall_cost;
    }

    public String getOverall_profit() {
        return overall_profit;
    }

    public void setOverall_profit(String overall_profit) {
        this.overall_profit = overall_profit;
    }

    public String getOverall_profit_y2y() {
        return overall_profit_y2y;
    }

    public void setOverall_profit_y2y(String overall_profit_y2y) {
        this.overall_profit_y2y = overall_profit_y2y;
    }

    public String getProfit_total() {
        return profit_total;
    }

    public void setProfit_total(String profit_total) {
        this.profit_total = profit_total;
    }

    public String getProfit_total_y2y() {
        return profit_total_y2y;
    }

    public void setProfit_total_y2y(String profit_total_y2y) {
        this.profit_total_y2y = profit_total_y2y;
    }

    public String getNetprofit() {
        return netprofit;
    }

    public void setNetprofit(String netprofit) {
        this.netprofit = netprofit;
    }

    public String getParent_netprofit() {
        return parent_netprofit;
    }

    public void setParent_netprofit(String parent_netprofit) {
        this.parent_netprofit = parent_netprofit;
    }

    public String getParent_netprofit_y2y() {
        return parent_netprofit_y2y;
    }

    public void setParent_netprofit_y2y(String parent_netprofit_y2y) {
        this.parent_netprofit_y2y = parent_netprofit_y2y;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getDeduct_netprofit() {
        return deduct_netprofit;
    }

    public void setDeduct_netprofit(String deduct_netprofit) {
        this.deduct_netprofit = deduct_netprofit;
    }

    public String getDeduct_netprofit_y2y() {
        return deduct_netprofit_y2y;
    }

    public void setDeduct_netprofit_y2y(String deduct_netprofit_y2y) {
        this.deduct_netprofit_y2y = deduct_netprofit_y2y;
    }

    public String getRd_total() {
        return rd_total;
    }

    public void setRd_total(String rd_total) {
        this.rd_total = rd_total;
    }

    public String getEbit() {
        return ebit;
    }

    public void setEbit(String ebit) {
        this.ebit = ebit;
    }

    public String getEbitda() {
        return ebitda;
    }

    public void setEbitda(String ebitda) {
        this.ebitda = ebitda;
    }
}
