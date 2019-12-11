package la.niub.abcapi.servicecompre.model;

import java.math.BigDecimal;

public class FundManagerStockModel {

    private Long com_uni_code;

    private Long sec_uni_code;

    private Long stock_uni_code;

    private String stock_code;

    private String stock_name;

    private BigDecimal now;

    private BigDecimal differ_range;

    private BigDecimal sec_value_net_ratio;

    private BigDecimal hold_sec_value;

    public FundManagerStockModel() {
    }

    public FundManagerStockModel( Long sec_uni_code, Long stock_uni_code, String stock_code, String stock_name, BigDecimal sec_value_net_ratio, BigDecimal hold_sec_value) {
        this.sec_uni_code = sec_uni_code;
        this.stock_uni_code = stock_uni_code;
        this.stock_code = stock_code;
        this.stock_name = stock_name;
        this.sec_value_net_ratio = sec_value_net_ratio;
        this.hold_sec_value = hold_sec_value;
    }

    public FundManagerStockModel(Long com_uni_code, Long sec_uni_code, Long stock_uni_code, String stock_code, String stock_name, BigDecimal now, BigDecimal differ_range, BigDecimal sec_value_net_ratio, BigDecimal hold_sec_value) {
        this.com_uni_code = com_uni_code;
        this.sec_uni_code = sec_uni_code;
        this.stock_uni_code = stock_uni_code;
        this.stock_code = stock_code;
        this.stock_name = stock_name;
        this.now = now;
        this.differ_range = differ_range;
        this.sec_value_net_ratio = sec_value_net_ratio;
        this.hold_sec_value = hold_sec_value;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public Long getStock_uni_code() {
        return stock_uni_code;
    }

    public void setStock_uni_code(Long stock_uni_code) {
        this.stock_uni_code = stock_uni_code;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public BigDecimal getNow() {
        return now;
    }

    public void setNow(BigDecimal now) {
        this.now = now;
    }

    public BigDecimal getDiffer_range() {
        return differ_range;
    }

    public void setDiffer_range(BigDecimal differ_range) {
        this.differ_range = differ_range;
    }

    public BigDecimal getSec_value_net_ratio() {
        return sec_value_net_ratio;
    }

    public void setSec_value_net_ratio(BigDecimal sec_value_net_ratio) {
        this.sec_value_net_ratio = sec_value_net_ratio;
    }

    public BigDecimal getHold_sec_value() {
        return hold_sec_value;
    }

    public void setHold_sec_value(BigDecimal hold_sec_value) {
        this.hold_sec_value = hold_sec_value;
    }
}
