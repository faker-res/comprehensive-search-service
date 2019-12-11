package la.niub.abcapi.servicecompre.model.dao;

public class IndexPriceDayDaoModel {

    private int indexUniCode;

    private float preClose;

    private float open;

    private float close;

    private String tradeDate;

    public int getIndexUniCode() {
        return indexUniCode;
    }

    public void setIndexUniCode(int indexUniCode) {
        this.indexUniCode = indexUniCode;
    }

    public float getPreClose() {
        return preClose;
    }

    public void setPreClose(float preClose) {
        this.preClose = preClose;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
}
