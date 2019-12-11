package la.niub.abcapi.servicecompre.model.bo.line;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PublicCompanyTimeBO {

    private Date time;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date trade_date;

    //最新价
    private Double now;

    //分钟成交股数
    private BigDecimal min_volume;

    //成交量
    private BigDecimal volume;

    //分钟成交额
    private BigDecimal min_amount;

    //成交额
    private BigDecimal amount;

    //涨跌
    private Double differ;

    //涨跌幅
    private Double differ_range;

    //上涨家数
    private Integer rise_num;

    //下跌家数
    private Integer fall_num;

    //持平家数
    private Integer fair_num;

    //流通市值
    private BigDecimal liqmv;

    //总市值
    private BigDecimal mv;

    //市盈率(动)
    private Double pe;

    //市净率
    private Double pb;

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

    public BigDecimal getMin_volume() {
        return min_volume;
    }

    public void setMin_volume(BigDecimal min_volume) {
        this.min_volume = min_volume;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getMin_amount() {
        return min_amount;
    }

    public void setMin_amount(BigDecimal min_amount) {
        this.min_amount = min_amount;
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

    public Integer getRise_num() {
        return rise_num;
    }

    public void setRise_num(Integer rise_num) {
        this.rise_num = rise_num;
    }

    public Integer getFall_num() {
        return fall_num;
    }

    public void setFall_num(Integer fall_num) {
        this.fall_num = fall_num;
    }

    public Integer getFair_num() {
        return fair_num;
    }

    public void setFair_num(Integer fair_num) {
        this.fair_num = fair_num;
    }

    public BigDecimal getLiqmv() {
        return liqmv;
    }

    public void setLiqmv(BigDecimal liqmv) {
        this.liqmv = liqmv;
    }

    public BigDecimal getMv() {
        return mv;
    }

    public void setMv(BigDecimal mv) {
        this.mv = mv;
    }

    public Double getPe() {
        return pe;
    }

    public void setPe(Double pe) {
        this.pe = pe;
    }

    public Double getPb() {
        return pb;
    }

    public void setPb(Double pb) {
        this.pb = pb;
    }

    @Override
    public String toString() {
        return "PublicCompanyTimeBO{" +
                "time=" + time +
                ", trade_date=" + trade_date +
                ", now=" + now +
                ", min_volume=" + min_volume +
                ", volume=" + volume +
                ", min_amount=" + min_amount +
                ", amount=" + amount +
                ", differ=" + differ +
                ", differ_range=" + differ_range +
                ", rise_num=" + rise_num +
                ", fall_num=" + fall_num +
                ", fair_num=" + fair_num +
                ", liqmv=" + liqmv +
                ", mv=" + mv +
                ", pe=" + pe +
                ", pb=" + pb +
                '}';
    }
}
