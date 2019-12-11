package la.niub.abcapi.servicecompre.model.bo.publiccompany;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PublicCompanyIndexBO {

    private Date trade_date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    //换手率
    private BigDecimal turn;

    //涨跌额
    private BigDecimal differ;

    //涨跌幅
    private BigDecimal differ_range;

    //成交金额
    private BigDecimal amount;

    //成交量
    private BigDecimal volume;

    //最高价
    private BigDecimal high;

    //最低价
    private BigDecimal low;

    //开盘价
    private BigDecimal open;

    //收盘价
    private BigDecimal close;

    public Date getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date = trade_date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTurn() {
        return turn;
    }

    public void setTurn(BigDecimal turn) {
        this.turn = turn;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
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

    @Override
    public String toString() {
        return "PublicCompanyIndexBO{" +
                "trade_date=" + trade_date +
                ", date=" + date +
                ", turn=" + turn +
                ", differ=" + differ +
                ", differ_range=" + differ_range +
                ", amount=" + amount +
                ", volume=" + volume +
                ", high=" + high +
                ", low=" + low +
                ", open=" + open +
                ", close=" + close +
                '}';
    }
}
