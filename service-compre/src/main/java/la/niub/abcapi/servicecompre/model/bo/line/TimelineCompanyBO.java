package la.niub.abcapi.servicecompre.model.bo.line;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TimelineCompanyBO {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date trade_date;

    private BigDecimal turn;

    private BigDecimal differ_range;

    private BigDecimal amount;

    private String amount_unit;

    private BigDecimal avg_price;

    private String  avg_price_unit;

    private BigDecimal differ;

    private String differ_unit;

    private BigDecimal open;

    private String open_unit;

    private BigDecimal volume;

    private String volume_unit;

    public Date getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date = trade_date;
    }

    public BigDecimal getTurn() {
        return turn;
    }

    public void setTurn(BigDecimal turn) {
        this.turn = turn;
    }

    public BigDecimal getDiffer_range() {
        return differ_range;
    }

    public void setDiffer_range(BigDecimal differ_range) {
        this.differ_range = differ_range;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAmount_unit() {
        return amount_unit;
    }

    public void setAmount_unit(String amount_unit) {
        this.amount_unit = amount_unit;
    }

    public BigDecimal getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(BigDecimal avg_price) {
        this.avg_price = avg_price;
    }

    public String getAvg_price_unit() {
        return avg_price_unit;
    }

    public void setAvg_price_unit(String avg_price_unit) {
        this.avg_price_unit = avg_price_unit;
    }

    public BigDecimal getDiffer() {
        return differ;
    }

    public void setDiffer(BigDecimal differ) {
        this.differ = differ;
    }

    public String getDiffer_unit() {
        return differ_unit;
    }

    public void setDiffer_unit(String differ_unit) {
        this.differ_unit = differ_unit;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public String getOpen_unit() {
        return open_unit;
    }

    public void setOpen_unit(String open_unit) {
        this.open_unit = open_unit;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getVolume_unit() {
        return volume_unit;
    }

    public void setVolume_unit(String volume_unit) {
        this.volume_unit = volume_unit;
    }

    @Override
    public String toString() {
        return "TimelineCompanyBO{" +
                "trade_date=" + trade_date +
                ", turn=" + turn +
                ", differ_range=" + differ_range +
                ", amount=" + amount +
                ", amount_unit='" + amount_unit + '\'' +
                ", avg_price=" + avg_price +
                ", avg_price_unit='" + avg_price_unit + '\'' +
                ", differ=" + differ +
                ", differ_unit='" + differ_unit + '\'' +
                ", open=" + open +
                ", open_unit='" + open_unit + '\'' +
                ", volume=" + volume +
                ", volume_unit='" + volume_unit + '\'' +
                '}';
    }
}
