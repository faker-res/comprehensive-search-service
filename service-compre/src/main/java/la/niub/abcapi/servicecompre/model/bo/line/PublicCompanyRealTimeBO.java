package la.niub.abcapi.servicecompre.model.bo.line;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PublicCompanyRealTimeBO {

    private Date time;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date trade_date;

    //最新价
    private Double now;

    //最高价
    private Double high;

    //最低价
    private Double low;

    //今日开盘价
    private Double open;

    //昨收价
    private Double pre_close;

    //现手
    private Double roundlot;

    //成交量
    private BigDecimal volume;

    //成交额
    private BigDecimal amount;

    //涨跌
    private Double differ;

    //涨跌幅
    private Double differ_range;

    //涨速
    private Double differ_speed;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date = trade_date;
    }

    public Double getNow() {
        return now;
    }

    public void setNow(Double now) {
        this.now = now;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getPre_close() {
        return pre_close;
    }

    public void setPre_close(Double pre_close) {
        this.pre_close = pre_close;
    }

    public Double getRoundlot() {
        return roundlot;
    }

    public void setRoundlot(Double roundlot) {
        this.roundlot = roundlot;
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

    public Double getDiffer() {
        return differ;
    }

    public void setDiffer(Double differ) {
        this.differ = differ;
    }

    public Double getDiffer_range() {
        return differ_range;
    }

    public void setDiffer_range(Double differ_range) {
        this.differ_range = differ_range;
    }

    public Double getDiffer_speed() {
        return differ_speed;
    }

    public void setDiffer_speed(Double differ_speed) {
        this.differ_speed = differ_speed;
    }

    @Override
    public String toString() {
        return "PublicCompanyRealTimeBO{" +
                "time=" + time +
                ", trade_date=" + trade_date +
                ", now=" + now +
                ", high=" + high +
                ", low=" + low +
                ", open=" + open +
                ", pre_close=" + pre_close +
                ", roundlot=" + roundlot +
                ", volume=" + volume +
                ", amount=" + amount +
                ", differ=" + differ +
                ", differ_range=" + differ_range +
                ", differ_speed=" + differ_speed +
                '}';
    }
}
