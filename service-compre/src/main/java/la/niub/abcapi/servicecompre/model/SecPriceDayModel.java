package la.niub.abcapi.servicecompre.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecPriceDayModel implements Serializable {
    private Integer id;

    private Long sec_uni_code;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date trade_date;

    private BigDecimal pre_close_price;

    private BigDecimal open_price;

    private BigDecimal close_price;

    private BigDecimal high_price;

    private BigDecimal low_price;

    private BigDecimal volume;

    private BigDecimal amount;

    private BigDecimal trade_num;

    private BigDecimal differ;

    private BigDecimal differ_range;

    private BigDecimal turn;

    private BigDecimal limit_up_price;

    private BigDecimal limit_down_price;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private BigDecimal power_factor_1;

    private BigDecimal power_factor_2;

    private BigDecimal amplitude;

    private static final long serialVersionUID = 1L;

    public SecPriceDayModel(Integer id, Long sec_uni_code, Date trade_date, BigDecimal pre_close_price, BigDecimal open_price, BigDecimal close_price, BigDecimal high_price, BigDecimal low_price, BigDecimal volume, BigDecimal amount, BigDecimal trade_num, BigDecimal differ, BigDecimal differ_range, BigDecimal turn, BigDecimal limit_up_price, BigDecimal limit_down_price, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source, BigDecimal power_factor_1, BigDecimal power_factor_2, BigDecimal amplitude) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.trade_date = trade_date;
        this.pre_close_price = pre_close_price;
        this.open_price = open_price;
        this.close_price = close_price;
        this.high_price = high_price;
        this.low_price = low_price;
        this.volume = volume;
        this.amount = amount;
        this.trade_num = trade_num;
        this.differ = differ;
        this.differ_range = differ_range;
        this.turn = turn;
        this.limit_up_price = limit_up_price;
        this.limit_down_price = limit_down_price;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
        this.power_factor_1 = power_factor_1;
        this.power_factor_2 = power_factor_2;
        this.amplitude = amplitude;
    }

    public SecPriceDayModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public Date getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date = trade_date;
    }

    public BigDecimal getPre_close_price() {
        return pre_close_price;
    }

    public void setPre_close_price(BigDecimal pre_close_price) {
        this.pre_close_price = pre_close_price;
    }

    public BigDecimal getOpen_price() {
        return open_price;
    }

    public void setOpen_price(BigDecimal open_price) {
        this.open_price = open_price;
    }

    public BigDecimal getClose_price() {
        return close_price;
    }

    public void setClose_price(BigDecimal close_price) {
        this.close_price = close_price;
    }

    public BigDecimal getHigh_price() {
        return high_price;
    }

    public void setHigh_price(BigDecimal high_price) {
        this.high_price = high_price;
    }

    public BigDecimal getLow_price() {
        return low_price;
    }

    public void setLow_price(BigDecimal low_price) {
        this.low_price = low_price;
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

    public BigDecimal getTrade_num() {
        return trade_num;
    }

    public void setTrade_num(BigDecimal trade_num) {
        this.trade_num = trade_num;
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

    public BigDecimal getTurn() {
        return turn;
    }

    public void setTurn(BigDecimal turn) {
        this.turn = turn;
    }

    public BigDecimal getLimit_up_price() {
        return limit_up_price;
    }

    public void setLimit_up_price(BigDecimal limit_up_price) {
        this.limit_up_price = limit_up_price;
    }

    public BigDecimal getLimit_down_price() {
        return limit_down_price;
    }

    public void setLimit_down_price(BigDecimal limit_down_price) {
        this.limit_down_price = limit_down_price;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source == null ? null : come_source.trim();
    }

    public BigDecimal getPower_factor_1() {
        return power_factor_1;
    }

    public void setPower_factor_1(BigDecimal power_factor_1) {
        this.power_factor_1 = power_factor_1;
    }

    public BigDecimal getPower_factor_2() {
        return power_factor_2;
    }

    public void setPower_factor_2(BigDecimal power_factor_2) {
        this.power_factor_2 = power_factor_2;
    }

    public BigDecimal getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(BigDecimal amplitude) {
        this.amplitude = amplitude;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sec_uni_code=").append(sec_uni_code);
        sb.append(", trade_date=").append(trade_date);
        sb.append(", pre_close_price=").append(pre_close_price);
        sb.append(", open_price=").append(open_price);
        sb.append(", close_price=").append(close_price);
        sb.append(", high_price=").append(high_price);
        sb.append(", low_price=").append(low_price);
        sb.append(", volume=").append(volume);
        sb.append(", amount=").append(amount);
        sb.append(", trade_num=").append(trade_num);
        sb.append(", differ=").append(differ);
        sb.append(", differ_range=").append(differ_range);
        sb.append(", turn=").append(turn);
        sb.append(", limit_up_price=").append(limit_up_price);
        sb.append(", limit_down_price=").append(limit_down_price);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", come_source=").append(come_source);
        sb.append(", power_factor_1=").append(power_factor_1);
        sb.append(", power_factor_2=").append(power_factor_2);
        sb.append(", amplitude=").append(amplitude);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}