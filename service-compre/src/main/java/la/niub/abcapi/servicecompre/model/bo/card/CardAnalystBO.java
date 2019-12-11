package la.niub.abcapi.servicecompre.model.bo.card;

import java.util.List;

public class CardAnalystBO {

    private CardAnalystBasicInfoBO basic_info;

    private List<CardAnalystDynamicBO> dynamic;

    private List<CardAnalystReportStocksBO> report_stocks;

    public CardAnalystBasicInfoBO getBasic_info() {
        return basic_info;
    }

    public void setBasic_info(CardAnalystBasicInfoBO basic_info) {
        this.basic_info = basic_info;
    }

    public List<CardAnalystDynamicBO> getDynamic() {
        return dynamic;
    }

    public void setDynamic(List<CardAnalystDynamicBO> dynamic) {
        this.dynamic = dynamic;
    }

    public List<CardAnalystReportStocksBO> getReport_stocks() {
        return report_stocks;
    }

    public void setReport_stocks(List<CardAnalystReportStocksBO> report_stocks) {
        this.report_stocks = report_stocks;
    }

    @Override
    public String toString() {
        return "CardAnalystBO{" +
                "basic_info=" + basic_info +
                ", dynamic=" + dynamic +
                ", report_stocks=" + report_stocks +
                '}';
    }
}
