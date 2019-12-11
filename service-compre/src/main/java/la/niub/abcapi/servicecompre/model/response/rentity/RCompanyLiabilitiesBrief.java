package la.niub.abcapi.servicecompre.model.response.rentity;
//公司负债摘要
public class RCompanyLiabilitiesBrief {
    //流动资产
    private String total_current_asset;
    //固定资产
    private String fix_asset;
    //长期股权投资
    private String long_equity_investment;
    //资产合计
    private String total_asset;
    //资产合计同比
    private String total_asset_y2y;
    //流动负债
    private String total_current_liabilities;
    //非流动负债
    private String total_non_current_liabilities;
    //负债合计
    private String total_liabilities;
    //负债合计同比
    private String total_liabilities_y2y;
    //股东权益
    private String total_account_equity;
    //归属母公司股东的权益
    private String total_account_parent_equity;
    //归属母公司股东的权益同比
    private String total_account_parent_equity_y2y;
    //资本公积
    private String capital_reserve;
    //盈余公积
    private String earn_reserve;
    //未分配利润
    private String nopay_profit;

    public String getTotal_current_asset() {
        return total_current_asset;
    }

    public void setTotal_current_asset(String total_current_asset) {
        this.total_current_asset = total_current_asset;
    }

    public String getFix_asset() {
        return fix_asset;
    }

    public void setFix_asset(String fix_asset) {
        this.fix_asset = fix_asset;
    }

    public String getLong_equity_investment() {
        return long_equity_investment;
    }

    public void setLong_equity_investment(String long_equity_investment) {
        this.long_equity_investment = long_equity_investment;
    }

    public String getTotal_asset() {
        return total_asset;
    }

    public void setTotal_asset(String total_asset) {
        this.total_asset = total_asset;
    }

    public String getTotal_asset_y2y() {
        return total_asset_y2y;
    }

    public void setTotal_asset_y2y(String total_asset_y2y) {
        this.total_asset_y2y = total_asset_y2y;
    }

    public String getTotal_current_liabilities() {
        return total_current_liabilities;
    }

    public void setTotal_current_liabilities(String total_current_liabilities) {
        this.total_current_liabilities = total_current_liabilities;
    }

    public String getTotal_non_current_liabilities() {
        return total_non_current_liabilities;
    }

    public void setTotal_non_current_liabilities(String total_non_current_liabilities) {
        this.total_non_current_liabilities = total_non_current_liabilities;
    }

    public String getTotal_liabilities() {
        return total_liabilities;
    }

    public void setTotal_liabilities(String total_liabilities) {
        this.total_liabilities = total_liabilities;
    }

    public String getTotal_liabilities_y2y() {
        return total_liabilities_y2y;
    }

    public void setTotal_liabilities_y2y(String total_liabilities_y2y) {
        this.total_liabilities_y2y = total_liabilities_y2y;
    }

    public String getTotal_account_equity() {
        return total_account_equity;
    }

    public void setTotal_account_equity(String total_account_equity) {
        this.total_account_equity = total_account_equity;
    }

    public String getTotal_account_parent_equity() {
        return total_account_parent_equity;
    }

    public void setTotal_account_parent_equity(String total_account_parent_equity) {
        this.total_account_parent_equity = total_account_parent_equity;
    }

    public String getTotal_account_parent_equity_y2y() {
        return total_account_parent_equity_y2y;
    }

    public void setTotal_account_parent_equity_y2y(String total_account_parent_equity_y2y) {
        this.total_account_parent_equity_y2y = total_account_parent_equity_y2y;
    }

    public String getCapital_reserve() {
        return capital_reserve;
    }

    public void setCapital_reserve(String capital_reserve) {
        this.capital_reserve = capital_reserve;
    }

    public String getEarn_reserve() {
        return earn_reserve;
    }

    public void setEarn_reserve(String earn_reserve) {
        this.earn_reserve = earn_reserve;
    }

    public String getNopay_profit() {
        return nopay_profit;
    }

    public void setNopay_profit(String nopay_profit) {
        this.nopay_profit = nopay_profit;
    }
}
