package la.niub.abcapi.servicecompre.model.bo.card;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CardAnalystReportStocksBO {

    private String abc_code;

    private Float current_price;

    private Double zdf;

    private Integer last_time;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    private String sec_name;

    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getAbc_code() {
        return abc_code;
    }

    public void setAbc_code(String abc_code) {
        this.abc_code = abc_code;
    }

    public Integer getLast_time() {
        return last_time;
    }

    public void setLast_time(Integer last_time) {
        this.last_time = last_time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSec_name() {
        return sec_name;
    }

    public void setSec_name(String sec_name) {
        this.sec_name = sec_name;
    }

    public void setCurrent_price(Float current_price) {
        this.current_price = current_price;
    }

    public Float getCurrent_price() {
        return current_price;
    }

    public Double getZdf() {
        return zdf;
    }

    public void setZdf(Double zdf) {
        this.zdf = zdf;
    }

    @Override
    public String toString() {
        return "CardAnalystReportStocksBO{" +
                "abc_code='" + abc_code + '\'' +
                ", current_price=" + current_price +
                ", zdf=" + zdf +
                ", last_time=" + last_time +
                ", sec_name='" + sec_name + '\'' +
                ", total=" + total +
                '}';
    }
}
