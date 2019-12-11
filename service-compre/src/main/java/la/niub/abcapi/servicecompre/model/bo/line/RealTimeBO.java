package la.niub.abcapi.servicecompre.model.bo.line;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class RealTimeBO {

    private Date time;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date trade_date;

    //最新价
    private Double now;

    //现手
    private Double roundlot;

    //涨跌幅
    private Double differ_range;

    //成交量
    private BigDecimal volume;

    //成交额
    private BigDecimal amount;

    //量比
    private Double volumeratio;

    //委比
    private Double commissiondiff;

    //买价1
    private Double buyprice1;

    //买价2
    private Double buyprice2;

    //买价3
    private Double buyprice3;

    //买价4
    private Double buyprice4;

    //买价5
    private Double buyprice5;

    //买量1
    private Double buyvolume1;

    //买量2
    private Double buyvolume2;

    //买量3
    private Double buyvolume3;

    //买量4
    private Double buyvolume4;

    //买量5
    private Double buyvolume5;

    //卖价1
    private Double sellprice1;

    //卖价2
    private Double sellprice2;

    //卖价3
    private Double sellprice3;

    //卖价4
    private Double sellprice4;

    //卖价5
    private Double sellprice5;

    //卖量1
    private Double sellvolume1;

    //卖量2
    private Double sellvolume2;

    //卖量3
    private Double sellvolume3;

    //卖量4
    private Double sellvolume4;

    //卖量5
    private Double sellvolume5;

    //最高价
    private Double high;

    //最低价
    private Double low;

    //今日开盘价
    private Double open;

    //昨收价
    private Double pre_close;

    //涨跌
    private Double differ;

    //停牌标记(Y:停牌，N:未停牌)
    private String suspension;

    //委差
    private Double commission;

    //外盘
    private Double outvolume;

    //内盘
    private Double involume;

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

    //振幅
    private Double swing;

    //换手率
    private Double change;

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

    public Double getRoundlot() {
        return roundlot;
    }

    public void setRoundlot(Double roundlot) {
        this.roundlot = roundlot;
    }

    public Double getDiffer_range() {
        return differ_range;
    }

    public void setDiffer_range(Double differ_range) {
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

    public Double getVolumeratio() {
        return volumeratio;
    }

    public void setVolumeratio(Double volumeratio) {
        this.volumeratio = volumeratio;
    }

    public Double getCommissiondiff() {
        return commissiondiff;
    }

    public void setCommissiondiff(Double commissiondiff) {
        this.commissiondiff = commissiondiff;
    }

    public Double getBuyprice1() {
        return buyprice1;
    }

    public void setBuyprice1(Double buyprice1) {
        this.buyprice1 = buyprice1;
    }

    public Double getBuyprice2() {
        return buyprice2;
    }

    public void setBuyprice2(Double buyprice2) {
        this.buyprice2 = buyprice2;
    }

    public Double getBuyprice3() {
        return buyprice3;
    }

    public void setBuyprice3(Double buyprice3) {
        this.buyprice3 = buyprice3;
    }

    public Double getBuyprice4() {
        return buyprice4;
    }

    public void setBuyprice4(Double buyprice4) {
        this.buyprice4 = buyprice4;
    }

    public Double getBuyprice5() {
        return buyprice5;
    }

    public void setBuyprice5(Double buyprice5) {
        this.buyprice5 = buyprice5;
    }

    public Double getBuyvolume1() {
        return buyvolume1;
    }

    public void setBuyvolume1(Double buyvolume1) {
        this.buyvolume1 = buyvolume1;
    }

    public Double getBuyvolume2() {
        return buyvolume2;
    }

    public void setBuyvolume2(Double buyvolume2) {
        this.buyvolume2 = buyvolume2;
    }

    public Double getBuyvolume3() {
        return buyvolume3;
    }

    public void setBuyvolume3(Double buyvolume3) {
        this.buyvolume3 = buyvolume3;
    }

    public Double getBuyvolume4() {
        return buyvolume4;
    }

    public void setBuyvolume4(Double buyvolume4) {
        this.buyvolume4 = buyvolume4;
    }

    public Double getBuyvolume5() {
        return buyvolume5;
    }

    public void setBuyvolume5(Double buyvolume5) {
        this.buyvolume5 = buyvolume5;
    }

    public Double getSellprice1() {
        return sellprice1;
    }

    public void setSellprice1(Double sellprice1) {
        this.sellprice1 = sellprice1;
    }

    public Double getSellprice2() {
        return sellprice2;
    }

    public void setSellprice2(Double sellprice2) {
        this.sellprice2 = sellprice2;
    }

    public Double getSellprice3() {
        return sellprice3;
    }

    public void setSellprice3(Double sellprice3) {
        this.sellprice3 = sellprice3;
    }

    public Double getSellprice4() {
        return sellprice4;
    }

    public void setSellprice4(Double sellprice4) {
        this.sellprice4 = sellprice4;
    }

    public Double getSellprice5() {
        return sellprice5;
    }

    public void setSellprice5(Double sellprice5) {
        this.sellprice5 = sellprice5;
    }

    public Double getSellvolume1() {
        return sellvolume1;
    }

    public void setSellvolume1(Double sellvolume1) {
        this.sellvolume1 = sellvolume1;
    }

    public Double getSellvolume2() {
        return sellvolume2;
    }

    public void setSellvolume2(Double sellvolume2) {
        this.sellvolume2 = sellvolume2;
    }

    public Double getSellvolume3() {
        return sellvolume3;
    }

    public void setSellvolume3(Double sellvolume3) {
        this.sellvolume3 = sellvolume3;
    }

    public Double getSellvolume4() {
        return sellvolume4;
    }

    public void setSellvolume4(Double sellvolume4) {
        this.sellvolume4 = sellvolume4;
    }

    public Double getSellvolume5() {
        return sellvolume5;
    }

    public void setSellvolume5(Double sellvolume5) {
        this.sellvolume5 = sellvolume5;
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

    public Double getDiffer() {
        return differ;
    }

    public void setDiffer(Double differ) {
        this.differ = differ;
    }

    public String getSuspension() {
        return suspension;
    }

    public void setSuspension(String suspension) {
        this.suspension = suspension;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getOutvolume() {
        return outvolume;
    }

    public void setOutvolume(Double outvolume) {
        this.outvolume = outvolume;
    }

    public Double getInvolume() {
        return involume;
    }

    public void setInvolume(Double involume) {
        this.involume = involume;
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

    public Double getSwing() {
        return swing;
    }

    public void setSwing(Double swing) {
        this.swing = swing;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    @Override
    public String toString() {
        return "RealTimeBO{" +
                "time=" + time +
                ", trade_date=" + trade_date +
                ", now=" + now +
                ", roundlot=" + roundlot +
                ", differ_range=" + differ_range +
                ", volume=" + volume +
                ", amount=" + amount +
                ", volumeratio=" + volumeratio +
                ", commissiondiff=" + commissiondiff +
                ", buyprice1=" + buyprice1 +
                ", buyprice2=" + buyprice2 +
                ", buyprice3=" + buyprice3 +
                ", buyprice4=" + buyprice4 +
                ", buyprice5=" + buyprice5 +
                ", buyvolume1=" + buyvolume1 +
                ", buyvolume2=" + buyvolume2 +
                ", buyvolume3=" + buyvolume3 +
                ", buyvolume4=" + buyvolume4 +
                ", buyvolume5=" + buyvolume5 +
                ", sellprice1=" + sellprice1 +
                ", sellprice2=" + sellprice2 +
                ", sellprice3=" + sellprice3 +
                ", sellprice4=" + sellprice4 +
                ", sellprice5=" + sellprice5 +
                ", sellvolume1=" + sellvolume1 +
                ", sellvolume2=" + sellvolume2 +
                ", sellvolume3=" + sellvolume3 +
                ", sellvolume4=" + sellvolume4 +
                ", sellvolume5=" + sellvolume5 +
                ", high=" + high +
                ", low=" + low +
                ", open=" + open +
                ", pre_close=" + pre_close +
                ", differ=" + differ +
                ", suspension='" + suspension + '\'' +
                ", commission=" + commission +
                ", outvolume=" + outvolume +
                ", involume=" + involume +
                ", rise_num=" + rise_num +
                ", fall_num=" + fall_num +
                ", fair_num=" + fair_num +
                ", liqmv=" + liqmv +
                ", mv=" + mv +
                ", pe=" + pe +
                ", pb=" + pb +
                ", swing=" + swing +
                ", change=" + change +
                '}';
    }
}
