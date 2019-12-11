package la.niub.abcapi.servicecompre.model.bo.card;

import la.niub.abcapi.servicecompre.model.SecPriceDayModel;

import java.util.List;

public class CardAnalystReportStockChartBO {

    private List<CardAnalystDynamicBO> report_stocks;

    private List<SecPriceDayModel> stock_price;

    public List<CardAnalystDynamicBO> getReport_stocks() {
        return report_stocks;
    }

    public void setReport_stocks(List<CardAnalystDynamicBO> report_stocks) {
        this.report_stocks = report_stocks;
    }

    public List<SecPriceDayModel> getStock_price() {
        return stock_price;
    }

    public void setStock_price(List<SecPriceDayModel> stock_price) {
        this.stock_price = stock_price;
    }

    @Override
    public String toString() {
        return "CardAnalystReportStockChartBO{" +
                "report_stocks=" + report_stocks +
                ", stock_price=" + stock_price +
                '}';
    }
}
