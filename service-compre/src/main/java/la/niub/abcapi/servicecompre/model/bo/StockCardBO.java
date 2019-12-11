package la.niub.abcapi.servicecompre.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StockCardBO {

    private String abc_code;

    private String sec_uni_code;

    private String com_uni_code;

    private String current_price;

    private String jk;

    private Double pe;

    private String sec_name;

    private Object stock_price_icon;

    private String stock_type;

    private Boolean suspend = false;

    private Double total_market_value;

    private String zd;

    private String zdf;

    private String zg;

    private String zs;

    private Double unit_nav;

    private Double cumu_unit_nav;

    private Double rise_rate;

    private Double income;

    private Double differ;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date end_date;

    private Integer is_money;

    private Double seven_day_yield;

    public String getAbc_code() {
        return abc_code;
    }

    public void setAbc_code(String abc_code) {
        this.abc_code = abc_code;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public Double getPe() {
        return pe;
    }

    public void setPe(Double pe) {
        this.pe = pe;
    }

    public String getSec_name() {
        return sec_name;
    }

    public void setSec_name(String sec_name) {
        this.sec_name = sec_name;
    }

    public Object getStock_price_icon() {
        return stock_price_icon;
    }

    public void setStock_price_icon(Object stock_price_icon) {
        this.stock_price_icon = stock_price_icon;
    }

    public String getStock_type() {
        return stock_type;
    }

    public void setStock_type(String stock_type) {
        this.stock_type = stock_type;
    }

    public Boolean getSuspend() {
        return suspend;
    }

    public void setSuspend(Boolean suspend) {
        this.suspend = suspend;
    }

    public Double getTotal_market_value() {
        return total_market_value;
    }

    public void setTotal_market_value(Double total_market_value) {
        this.total_market_value = total_market_value;
    }

    public String getZd() {
        return zd;
    }

    public void setZd(String zd) {
        this.zd = zd;
    }

    public String getZdf() {
        return zdf;
    }

    public void setZdf(String zdf) {
        this.zdf = zdf;
    }

    public String getZg() {
        return zg;
    }

    public void setZg(String zg) {
        this.zg = zg;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public Double getUnit_nav() {
        return unit_nav;
    }

    public void setUnit_nav(Double unit_nav) {
        this.unit_nav = unit_nav;
    }

    public Double getCumu_unit_nav() {
        return cumu_unit_nav;
    }

    public void setCumu_unit_nav(Double cumu_unit_nav) {
        this.cumu_unit_nav = cumu_unit_nav;
    }

    public Double getRise_rate() {
        return rise_rate;
    }

    public void setRise_rate(Double rise_rate) {
        this.rise_rate = rise_rate;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getDiffer() {
        return differ;
    }

    public void setDiffer(Double differ) {
        this.differ = differ;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Integer getIs_money() {
        return is_money;
    }

    public void setIs_money(Integer is_money) {
        this.is_money = is_money;
    }

    public Double getSeven_day_yield() {
        return seven_day_yield;
    }

    public void setSeven_day_yield(Double seven_day_yield) {
        this.seven_day_yield = seven_day_yield;
    }

    public void setSec_uni_code(String sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public void setCom_uni_code(String com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public String getSec_uni_code() {
        return sec_uni_code;
    }

    public String getCom_uni_code() {
        return com_uni_code;
    }

    @Override
    public String toString() {
        return "StockCardBO{" +
                "abc_code='" + abc_code + '\'' +
                ", current_price='" + current_price + '\'' +
                ", jk='" + jk + '\'' +
                ", pe=" + pe +
                ", sec_name='" + sec_name + '\'' +
                ", stock_price_icon=" + stock_price_icon +
                ", stock_type='" + stock_type + '\'' +
                ", suspend=" + suspend +
                ", total_market_value='" + total_market_value + '\'' +
                ", zd='" + zd + '\'' +
                ", zdf='" + zdf + '\'' +
                ", zg='" + zg + '\'' +
                ", zs='" + zs + '\'' +
                ", unit_nav=" + unit_nav +
                ", cumu_unit_nav=" + cumu_unit_nav +
                ", rise_rate=" + rise_rate +
                ", income=" + income +
                '}';
    }
}
