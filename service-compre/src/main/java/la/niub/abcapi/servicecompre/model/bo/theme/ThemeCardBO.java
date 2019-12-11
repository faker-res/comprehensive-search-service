package la.niub.abcapi.servicecompre.model.bo.theme;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ThemeCardBO {

    private String index_name;

    private String index_code;

    private Integer rise_num;

    private Integer fall_num;

    private Integer fair_num;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date trade_date;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal volume;

    private BigDecimal amount;

    private BigDecimal differ;

    private BigDecimal differ_range;

    private BigDecimal turnover_rate;

    private BigDecimal pe;

    private BigDecimal pb;

    private BigDecimal main_netin_flow;

    private BigDecimal differ_range_5;

    private BigDecimal differ_range_20;

    List<IndexPriceDayBO> graph;

    public List<IndexPriceDayBO> getGraph() {
        return graph;
    }

    public void setGraph(List<IndexPriceDayBO> graph) {
        this.graph = graph;
    }

    public BigDecimal getTurnover_rate() {
        return turnover_rate;
    }

    public void setTurnover_rate(BigDecimal turnover_rate) {
        this.turnover_rate = turnover_rate;
    }

    public String getIndex_name() {
        return index_name;
    }

    public void setIndex_name(String index_name) {
        this.index_name = index_name;
    }

    public String getIndex_code() {
        return index_code;
    }

    public void setIndex_code(String index_code) {
        this.index_code = index_code;
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

    public Date getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date = trade_date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
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

    public BigDecimal getDiffer() {
        return differ;
    }

    public void setDiffer(BigDecimal differ) {
        this.differ = differ;
    }

    public BigDecimal getDiffer_range() {
        return differ_range;
    }

    public void setDiffer_range(BigDecimal differ_range) {
        this.differ_range = differ_range;
    }

    public BigDecimal getPe() {
        return pe;
    }

    public void setPe(BigDecimal pe) {
        this.pe = pe;
    }

    public BigDecimal getPb() {
        return pb;
    }

    public void setPb(BigDecimal pb) {
        this.pb = pb;
    }

    public BigDecimal getMain_netin_flow() {
        return main_netin_flow;
    }

    public void setMain_netin_flow(BigDecimal main_netin_flow) {
        this.main_netin_flow = main_netin_flow;
    }

    public BigDecimal getDiffer_range_5() {
        return differ_range_5;
    }

    public void setDiffer_range_5(BigDecimal differ_range_5) {
        this.differ_range_5 = differ_range_5;
    }

    public BigDecimal getDiffer_range_20() {
        return differ_range_20;
    }

    public void setDiffer_range_20(BigDecimal differ_range_20) {
        this.differ_range_20 = differ_range_20;
    }
}
