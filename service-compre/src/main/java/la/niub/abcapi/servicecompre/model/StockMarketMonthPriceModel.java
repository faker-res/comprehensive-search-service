package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StockMarketMonthPriceModel implements Serializable {
    private Long id;

    private String stock_code;

    private Date time;

    private BigDecimal close_price;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal differ;

    private BigDecimal pre_close;

    private BigDecimal differ_range;

    private Date update_time;

    private static final long serialVersionUID = 1L;

    public StockMarketMonthPriceModel(Long id, String stock_code, Date time, BigDecimal close_price, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal differ, BigDecimal pre_close, BigDecimal differ_range, Date update_time) {
        this.id = id;
        this.stock_code = stock_code;
        this.time = time;
        this.close_price = close_price;
        this.open = open;
        this.high = high;
        this.low = low;
        this.differ = differ;
        this.pre_close = pre_close;
        this.differ_range = differ_range;
        this.update_time = update_time;
    }

    public StockMarketMonthPriceModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code == null ? null : stock_code.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getClose_price() {
        return close_price;
    }

    public void setClose_price(BigDecimal close_price) {
        this.close_price = close_price;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getDiffer() {
        return differ;
    }

    public void setDiffer(BigDecimal differ) {
        this.differ = differ;
    }

    public BigDecimal getPre_close() {
        return pre_close;
    }

    public void setPre_close(BigDecimal pre_close) {
        this.pre_close = pre_close;
    }

    public BigDecimal getDiffer_range() {
        return differ_range;
    }

    public void setDiffer_range(BigDecimal differ_range) {
        this.differ_range = differ_range;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stock_code=").append(stock_code);
        sb.append(", time=").append(time);
        sb.append(", close_price=").append(close_price);
        sb.append(", open=").append(open);
        sb.append(", high=").append(high);
        sb.append(", low=").append(low);
        sb.append(", differ=").append(differ);
        sb.append(", pre_close=").append(pre_close);
        sb.append(", differ_range=").append(differ_range);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}