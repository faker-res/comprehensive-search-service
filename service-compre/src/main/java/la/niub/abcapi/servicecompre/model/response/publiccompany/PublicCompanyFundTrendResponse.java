package la.niub.abcapi.servicecompre.model.response.publiccompany;

import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyFundTrendBO;

import java.util.List;

public class PublicCompanyFundTrendResponse {

    //基金总市值
    private List<PublicCompanyFundTrendBO> market_value;

    //基金总额
    private List<PublicCompanyFundTrendBO> amount;

    public List<PublicCompanyFundTrendBO> getMarket_value() {
        return market_value;
    }

    public void setMarket_value(List<PublicCompanyFundTrendBO> market_value) {
        this.market_value = market_value;
    }

    public List<PublicCompanyFundTrendBO> getAmount() {
        return amount;
    }

    public void setAmount(List<PublicCompanyFundTrendBO> amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PublicCompanyFundTrendResponse{" +
                "market_value=" + market_value +
                ", amount=" + amount +
                '}';
    }
}
