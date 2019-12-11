package la.niub.abcapi.servicecompre.model.bo.card;

import java.math.BigDecimal;

public class CardStockNewssetStockBO {

    private Float current_price;

    private Double zdf;

    private Double cjl;

    private Double cje;

    private Double jk;

    private Double zg;

    private Double zd;

    private Double zs;

    private Double zsz;

    private Double ltsz;

    private Double syl;

    private Double sjl;

    private Double sxl;

    private BigDecimal zgb;

    private Boolean suspend;

    private Boolean stopSign;

    //换手率
    private Double change;

    //振幅
    private Double swing;

    //总手
    private BigDecimal totalhand;

    //现手
    private Double nowhand;

    //委比
    private Double appointment;

    //量比
    private Double quantity;

    //EPS
    private BigDecimal eps;

    //市盈率(静)
    private BigDecimal pej;

    //流通股
    private Long circulationstock;

    //涨跌
    private Double change_amount;

    public Float getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Float current_price) {
        this.current_price = current_price;
    }

    public Double getZdf() {
        return zdf;
    }

    public void setZdf(Double zdf) {
        this.zdf = zdf;
    }

    public Double getCjl() {
        return cjl;
    }

    public void setCjl(Double cjl) {
        this.cjl = cjl;
    }

    public Double getCje() {
        return cje;
    }

    public void setCje(Double cje) {
        this.cje = cje;
    }

    public Double getJk() {
        return jk;
    }

    public void setJk(Double jk) {
        this.jk = jk;
    }

    public Double getZg() {
        return zg;
    }

    public void setZg(Double zg) {
        this.zg = zg;
    }

    public Double getZd() {
        return zd;
    }

    public void setZd(Double zd) {
        this.zd = zd;
    }

    public Double getZs() {
        return zs;
    }

    public void setZs(Double zs) {
        this.zs = zs;
    }

    public Double getZsz() {
        return zsz;
    }

    public void setZsz(Double zsz) {
        this.zsz = zsz;
    }

    public Double getLtsz() {
        return ltsz;
    }

    public void setLtsz(Double ltsz) {
        this.ltsz = ltsz;
    }

    public Double getSyl() {
        return syl;
    }

    public void setSyl(Double syl) {
        this.syl = syl;
    }

    public Double getSjl() {
        return sjl;
    }

    public void setSjl(Double sjl) {
        this.sjl = sjl;
    }

    public Double getSxl() {
        return sxl;
    }

    public void setSxl(Double sxl) {
        this.sxl = sxl;
    }

    public BigDecimal getZgb() {
        return zgb;
    }

    public void setZgb(BigDecimal zgb) {
        this.zgb = zgb;
    }

    public Boolean getSuspend() {
        return suspend;
    }

    public void setSuspend(Boolean suspend) {
        this.suspend = suspend;
    }

    public Boolean getStopSign() {
        return stopSign;
    }

    public void setStopSign(Boolean stopSign) {
        this.stopSign = stopSign;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getSwing() {
        return swing;
    }

    public void setSwing(Double swing) {
        this.swing = swing;
    }

    public BigDecimal getTotalhand() {
        return totalhand;
    }

    public void setTotalhand(BigDecimal totalhand) {
        this.totalhand = totalhand;
    }

    public Double getNowhand() {
        return nowhand;
    }

    public void setNowhand(Double nowhand) {
        this.nowhand = nowhand;
    }

    public Double getAppointment() {
        return appointment;
    }

    public void setAppointment(Double appointment) {
        this.appointment = appointment;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getEps() {
        return eps;
    }

    public void setEps(BigDecimal eps) {
        this.eps = eps;
    }

    public BigDecimal getPej() {
        return pej;
    }

    public void setPej(BigDecimal pej) {
        this.pej = pej;
    }

    public Long getCirculationstock() {
        return circulationstock;
    }

    public void setCirculationstock(Long circulationstock) {
        this.circulationstock = circulationstock;
    }

    public Double getChange_amount() {
        return change_amount;
    }

    public void setChange_amount(Double change_amount) {
        this.change_amount = change_amount;
    }

    @Override
    public String toString() {
        return "CardStockNewssetStockBO{" +
                "current_price=" + current_price +
                ", zdf=" + zdf +
                ", cjl=" + cjl +
                ", cje=" + cje +
                ", jk=" + jk +
                ", zg=" + zg +
                ", zd=" + zd +
                ", zs=" + zs +
                ", zsz=" + zsz +
                ", ltsz=" + ltsz +
                ", syl=" + syl +
                ", sjl=" + sjl +
                ", sxl=" + sxl +
                ", zgb=" + zgb +
                ", suspend=" + suspend +
                ", stopSign=" + stopSign +
                ", change=" + change +
                ", swing=" + swing +
                ", totalhand=" + totalhand +
                ", nowhand=" + nowhand +
                ", appointment=" + appointment +
                ", quantity=" + quantity +
                ", eps=" + eps +
                ", pej=" + pej +
                ", circulationstock=" + circulationstock +
                ", change_amount=" + change_amount +
                '}';
    }
}
