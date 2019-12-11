package la.niub.abcapi.servicecompre.model.bo.theme;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class IndexPriceDayBO {

//    private Integer rise_num;

//    private Integer fall_num;

//    private Integer fair_num;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date trade_date;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal volume;

    private BigDecimal amount;

    private BigDecimal differ;

    private BigDecimal differ_range;

//    private BigDecimal turnover_rate;

//    public Integer getRise_num() {
//        return rise_num;
//    }
//
//    public void setRise_num(Integer rise_num) {
//        this.rise_num = rise_num;
//    }
//
//    public Integer getFall_num() {
//        return fall_num;
//    }
//
//    public void setFall_num(Integer fall_num) {
//        this.fall_num = fall_num;
//    }
//
//    public Integer getFair_num() {
//        return fair_num;
//    }
//
//    public void setFair_num(Integer fair_num) {
//        this.fair_num = fair_num;
//    }
//    public BigDecimal getTurnover_rate() {
//        return turnover_rate;
//    }
//
//    public void setTurnover_rate(BigDecimal turnover_rate) {
//        this.turnover_rate = turnover_rate;
//    }
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

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
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


}
