package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SecPrice15MinModel implements Serializable {
    private Long id;

    private Long sec_uni_code;

    private String sec_code;

    private Date trade_date;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal close_price;

    private BigDecimal differ;

    private BigDecimal differ_range;

    private BigDecimal volume;

    private BigDecimal amount;

    private BigDecimal turn;

    private BigDecimal amplitude;

    private Date update_time;

    private static final long serialVersionUID = 1L;

    public SecPrice15MinModel(Long id, Long sec_uni_code, String sec_code, Date trade_date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close_price, BigDecimal differ, BigDecimal differ_range, BigDecimal volume, BigDecimal amount, BigDecimal turn, BigDecimal amplitude, Date update_time) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.sec_code = sec_code;
        this.trade_date = trade_date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close_price = close_price;
        this.differ = differ;
        this.differ_range = differ_range;
        this.volume = volume;
        this.amount = amount;
        this.turn = turn;
        this.amplitude = amplitude;
        this.update_time = update_time;
    }

    public SecPrice15MinModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public String getSec_code() {
        return sec_code;
    }

    public void setSec_code(String sec_code) {
        this.sec_code = sec_code == null ? null : sec_code.trim();
    }

    public Date getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date = trade_date;
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

    public BigDecimal getClose_price() {
        return close_price;
    }

    public void setClose_price(BigDecimal close_price) {
        this.close_price = close_price;
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

    public BigDecimal getTurn() {
        return turn;
    }

    public void setTurn(BigDecimal turn) {
        this.turn = turn;
    }

    public BigDecimal getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(BigDecimal amplitude) {
        this.amplitude = amplitude;
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
        sb.append(", sec_uni_code=").append(sec_uni_code);
        sb.append(", sec_code=").append(sec_code);
        sb.append(", trade_date=").append(trade_date);
        sb.append(", open=").append(open);
        sb.append(", high=").append(high);
        sb.append(", low=").append(low);
        sb.append(", close_price=").append(close_price);
        sb.append(", differ=").append(differ);
        sb.append(", differ_range=").append(differ_range);
        sb.append(", volume=").append(volume);
        sb.append(", amount=").append(amount);
        sb.append(", turn=").append(turn);
        sb.append(", amplitude=").append(amplitude);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}