package la.niub.abcapi.servicecompre.model.bo.publiccompany;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PublicCompanyHeatBO {

    private Long index_uni_code;

    private String index_code;

    private String name;

    private Date time;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    //涨跌
    private Double differ;

    //涨跌幅
    private Double differ_range;

    //主力净流入
    private BigDecimal amount;

    //市盈率
    private Double pe;

    //市净率
    private Double pb;

    //研报数量
    private Integer report_num;

    //总市值(元)
    private BigDecimal total_market_value;

    public Long getIndex_uni_code() {
        return index_uni_code;
    }

    public void setIndex_uni_code(Long index_uni_code) {
        this.index_uni_code = index_uni_code;
    }

    public String getIndex_code() {
        return index_code;
    }

    public void setIndex_code(String index_code) {
        this.index_code = index_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public Integer getReport_num() {
        return report_num;
    }

    public void setReport_num(Integer report_num) {
        this.report_num = report_num;
    }

    public BigDecimal getTotal_market_value() {
        return total_market_value;
    }

    public void setTotal_market_value(BigDecimal total_market_value) {
        this.total_market_value = total_market_value;
    }

    @Override
    public String toString() {
        return "PublicCompanyHeatBO{" +
                "index_uni_code=" + index_uni_code +
                ", index_code='" + index_code + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", differ=" + differ +
                ", differ_range=" + differ_range +
                ", amount=" + amount +
                ", pe=" + pe +
                ", pb=" + pb +
                ", report_num=" + report_num +
                ", total_market_value=" + total_market_value +
                '}';
    }
}
