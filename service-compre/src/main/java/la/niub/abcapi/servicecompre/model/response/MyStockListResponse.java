package la.niub.abcapi.servicecompre.model.response;

public class MyStockListResponse {
    private String stockName;

    private String id;

    private String stockCode;

    private Integer market;

    private String update_at;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Integer getMarket() {
        return market;
    }

    public void setMarket(Integer market) {
        this.market = market;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}
