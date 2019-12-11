package la.niub.abcapi.servicecompre.model.response.rentity;
//现金流量表摘要
public class RCompanyCashBrief {
    //销售商品提供劳务收到的现金
    private String sale_cash;
    //经营活动现金净流量
    private String bussiness_cash_netvalue;
    //购建固定无形长期资产支付的现金
    private String subs_pay_cash;
    //投资支付的现金
    private String invest_pay_cash;
    //投资活动现金净流量
    private String invest_cash_netvalue;
    //吸收投资收到的现金
    private String rec_invest_reccash;
    //取得借款收到的现金
    private String rec_borrow_cash;
    //筹资活动现金净流量
    private String borrow_cash_netvalue;
    //现金净增加额
    private String cash_to_netadd;
    //期末现金余额
    private String last_cash;

    public String getSale_cash() {
        return sale_cash;
    }

    public void setSale_cash(String sale_cash) {
        this.sale_cash = sale_cash;
    }

    public String getBussiness_cash_netvalue() {
        return bussiness_cash_netvalue;
    }

    public void setBussiness_cash_netvalue(String bussiness_cash_netvalue) {
        this.bussiness_cash_netvalue = bussiness_cash_netvalue;
    }

    public String getSubs_pay_cash() {
        return subs_pay_cash;
    }

    public void setSubs_pay_cash(String subs_pay_cash) {
        this.subs_pay_cash = subs_pay_cash;
    }

    public String getInvest_pay_cash() {
        return invest_pay_cash;
    }

    public void setInvest_pay_cash(String invest_pay_cash) {
        this.invest_pay_cash = invest_pay_cash;
    }

    public String getInvest_cash_netvalue() {
        return invest_cash_netvalue;
    }

    public void setInvest_cash_netvalue(String invest_cash_netvalue) {
        this.invest_cash_netvalue = invest_cash_netvalue;
    }

    public String getRec_invest_reccash() {
        return rec_invest_reccash;
    }

    public void setRec_invest_reccash(String rec_invest_reccash) {
        this.rec_invest_reccash = rec_invest_reccash;
    }

    public String getRec_borrow_cash() {
        return rec_borrow_cash;
    }

    public void setRec_borrow_cash(String rec_borrow_cash) {
        this.rec_borrow_cash = rec_borrow_cash;
    }

    public String getBorrow_cash_netvalue() {
        return borrow_cash_netvalue;
    }

    public void setBorrow_cash_netvalue(String borrow_cash_netvalue) {
        this.borrow_cash_netvalue = borrow_cash_netvalue;
    }

    public String getCash_to_netadd() {
        return cash_to_netadd;
    }

    public void setCash_to_netadd(String cash_to_netadd) {
        this.cash_to_netadd = cash_to_netadd;
    }

    public String getLast_cash() {
        return last_cash;
    }

    public void setLast_cash(String last_cash) {
        this.last_cash = last_cash;
    }
}
