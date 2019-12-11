package la.niub.abcapi.servicecompre.model.bo.publiccompany;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PublicCompanyDetailBO {

    private String name;

    private String code;

    private Date time;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    //成交额
    private Double amount = 0d;

    //净资金流入
    private Double inflow = 0d;

    //成交量
    private Double volume = 0d;

    //开盘
    private Double open = 0d;

    //最高
    private Double high = 0d;

    //最低
    private Double low = 0d;

    //市盈率
    private Double pe_ratio = 0d;

    //市净率
    private Double pb_ratio = 0d;

    //换手率
    private Double turnover_rate = 0d;

    //5日
    private Double day5 = 0d;

    //20日
    private Double day20 = 0d;

    //价格
    private Double price = 0d;

    //涨跌
    private Double differ = 0d;

    //涨跌幅
    private Double differ_range = 0d;

    //涨
    private Integer rise = 0;

    //跌
    private Integer fall = 0;

    //平
    private Integer fair = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInflow() {
        return inflow;
    }

    public void setInflow(Double inflow) {
        this.inflow = inflow;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
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

    public Double getPe_ratio() {
        return pe_ratio;
    }

    public void setPe_ratio(Double pe_ratio) {
        this.pe_ratio = pe_ratio;
    }

    public Double getPb_ratio() {
        return pb_ratio;
    }

    public void setPb_ratio(Double pb_ratio) {
        this.pb_ratio = pb_ratio;
    }

    public Double getTurnover_rate() {
        return turnover_rate;
    }

    public void setTurnover_rate(Double turnover_rate) {
        this.turnover_rate = turnover_rate;
    }

    public Double getDay5() {
        return day5;
    }

    public void setDay5(Double day5) {
        this.day5 = day5;
    }

    public Double getDay20() {
        return day20;
    }

    public void setDay20(Double day20) {
        this.day20 = day20;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Integer getRise() {
        return rise;
    }

    public void setRise(Integer rise) {
        this.rise = rise;
    }

    public Integer getFall() {
        return fall;
    }

    public void setFall(Integer fall) {
        this.fall = fall;
    }

    public Integer getFair() {
        return fair;
    }

    public void setFair(Integer fair) {
        this.fair = fair;
    }

    @Override
    public String toString() {
        return "PublicCompanyDetailBO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", amount=" + amount +
                ", inflow=" + inflow +
                ", volume=" + volume +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", pe_ratio=" + pe_ratio +
                ", pb_ratio=" + pb_ratio +
                ", turnover_rate=" + turnover_rate +
                ", day5=" + day5 +
                ", day20=" + day20 +
                ", price=" + price +
                ", differ=" + differ +
                ", differ_range=" + differ_range +
                ", rise=" + rise +
                ", fall=" + fall +
                ", fair=" + fair +
                '}';
    }
}
