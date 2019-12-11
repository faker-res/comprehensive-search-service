package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Company30MinPriceModel implements Serializable {
    private Long id;

    private String stock_code;

    private Date time;

    private BigDecimal close_price;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal differ;

    private BigDecimal differ_range;

    private BigDecimal volume;

    private BigDecimal amount;

    private BigDecimal turnover_rate;

    private Date update_time;

    private BigDecimal now_hand;

    private BigDecimal now_amount;

    private BigDecimal average;

    private BigDecimal amplitude;

    private static final long serialVersionUID = 1L;

    public Company30MinPriceModel(Long id, String stock_code, Date time, BigDecimal close_price, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal differ, BigDecimal differ_range, BigDecimal volume, BigDecimal amount, BigDecimal turnover_rate, Date update_time) {
        this.id = id;
        this.stock_code = stock_code;
        this.time = time;
        this.close_price = close_price;
        this.open = open;
        this.high = high;
        this.low = low;
        this.differ = differ;
        this.differ_range = differ_range;
        this.volume = volume;
        this.amount = amount;
        this.turnover_rate = turnover_rate;
        this.update_time = update_time;
    }

    public Company30MinPriceModel() {
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

    public BigDecimal getDiffer_range() {
        return differ_range;
    }

    public void setDiffer_range(BigDecimal differ_range) {
        this.differ_range = differ_range;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTurnover_rate() {
        return turnover_rate;
    }

    public void setTurnover_rate(BigDecimal turnover_rate) {
        this.turnover_rate = turnover_rate;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public BigDecimal getNow_hand() {
        return now_hand;
    }

    public void setNow_hand(BigDecimal now_hand) {
        this.now_hand = now_hand;
    }

    public BigDecimal getNow_amount() {
        return now_amount;
    }

    public void setNow_amount(BigDecimal now_amount) {
        this.now_amount = now_amount;
    }

    public BigDecimal getAverage() {
        return average;
    }

    public void setAverage(BigDecimal average) {
        this.average = average;
    }

    public BigDecimal getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(BigDecimal amplitude) {
        this.amplitude = amplitude;
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
        sb.append(", differ_range=").append(differ_range);
        sb.append(", volume=").append(volume);
        sb.append(", amount=").append(amount);
        sb.append(", turnover_rate=").append(turnover_rate);
        sb.append(", update_time=").append(update_time);
        sb.append(", now_hand=").append(now_hand);
        sb.append(", now_amount=").append(now_amount);
        sb.append(", average=").append(average);
        sb.append(", amplitude=").append(amplitude);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}