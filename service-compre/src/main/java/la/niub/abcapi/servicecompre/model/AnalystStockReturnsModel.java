package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AnalystStockReturnsModel implements Serializable {
    private Integer id;

    private String peo_uni_code;

    private String stockcode;

    private Integer s_year;

    private Date first_report_date;

    private BigDecimal start_price;

    private BigDecimal end_price;

    private BigDecimal stock_returns;

    private Date create_time;

    private Date update_time;

    private static final long serialVersionUID = 1L;

    public AnalystStockReturnsModel(Integer id, String peo_uni_code, String stockcode, Integer s_year, Date first_report_date, BigDecimal start_price, BigDecimal end_price, BigDecimal stock_returns, Date create_time, Date update_time) {
        this.id = id;
        this.peo_uni_code = peo_uni_code;
        this.stockcode = stockcode;
        this.s_year = s_year;
        this.first_report_date = first_report_date;
        this.start_price = start_price;
        this.end_price = end_price;
        this.stock_returns = stock_returns;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public AnalystStockReturnsModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code == null ? null : peo_uni_code.trim();
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode == null ? null : stockcode.trim();
    }

    public Integer getS_year() {
        return s_year;
    }

    public void setS_year(Integer s_year) {
        this.s_year = s_year;
    }

    public Date getFirst_report_date() {
        return first_report_date;
    }

    public void setFirst_report_date(Date first_report_date) {
        this.first_report_date = first_report_date;
    }

    public BigDecimal getStart_price() {
        return start_price;
    }

    public void setStart_price(BigDecimal start_price) {
        this.start_price = start_price;
    }

    public BigDecimal getEnd_price() {
        return end_price;
    }

    public void setEnd_price(BigDecimal end_price) {
        this.end_price = end_price;
    }

    public BigDecimal getStock_returns() {
        return stock_returns;
    }

    public void setStock_returns(BigDecimal stock_returns) {
        this.stock_returns = stock_returns;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", stockcode=").append(stockcode);
        sb.append(", s_year=").append(s_year);
        sb.append(", first_report_date=").append(first_report_date);
        sb.append(", start_price=").append(start_price);
        sb.append(", end_price=").append(end_price);
        sb.append(", stock_returns=").append(stock_returns);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}