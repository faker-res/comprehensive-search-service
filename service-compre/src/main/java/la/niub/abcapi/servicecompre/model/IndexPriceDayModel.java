package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IndexPriceDayModel implements Serializable {
    private Integer id;

    private Long index_uni_code;

    private Date trade_date;

    private BigDecimal pre_close;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal volume;

    private BigDecimal amount;

    private BigDecimal differ;

    private BigDecimal differ_range;

    private BigDecimal turnover_rate;

    private Integer rise_num;

    private Integer fall_num;

    private Integer fair_num;

    private Integer limit_up_num;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private static final long serialVersionUID = 1L;

    public IndexPriceDayModel(Integer id, Long index_uni_code, Date trade_date, BigDecimal pre_close, BigDecimal open, BigDecimal close, BigDecimal high, BigDecimal low, BigDecimal volume, BigDecimal amount, BigDecimal differ, BigDecimal differ_range, BigDecimal turnover_rate, Integer rise_num, Integer fall_num, Integer fair_num, Integer limit_up_num, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source) {
        this.id = id;
        this.index_uni_code = index_uni_code;
        this.trade_date = trade_date;
        this.pre_close = pre_close;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.amount = amount;
        this.differ = differ;
        this.differ_range = differ_range;
        this.turnover_rate = turnover_rate;
        this.rise_num = rise_num;
        this.fall_num = fall_num;
        this.fair_num = fair_num;
        this.limit_up_num = limit_up_num;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
    }

    public IndexPriceDayModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getIndex_uni_code() {
        return index_uni_code;
    }

    public void setIndex_uni_code(Long index_uni_code) {
        this.index_uni_code = index_uni_code;
    }

    public Date getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date = trade_date;
    }

    public BigDecimal getPre_close() {
        return pre_close;
    }

    public void setPre_close(BigDecimal pre_close) {
        this.pre_close = pre_close;
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

    public BigDecimal getTurnover_rate() {
        return turnover_rate;
    }

    public void setTurnover_rate(BigDecimal turnover_rate) {
        this.turnover_rate = turnover_rate;
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

    public Integer getLimit_up_num() {
        return limit_up_num;
    }

    public void setLimit_up_num(Integer limit_up_num) {
        this.limit_up_num = limit_up_num;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", index_uni_code=").append(index_uni_code);
        sb.append(", trade_date=").append(trade_date);
        sb.append(", pre_close=").append(pre_close);
        sb.append(", open=").append(open);
        sb.append(", close=").append(close);
        sb.append(", high=").append(high);
        sb.append(", low=").append(low);
        sb.append(", volume=").append(volume);
        sb.append(", amount=").append(amount);
        sb.append(", differ=").append(differ);
        sb.append(", differ_range=").append(differ_range);
        sb.append(", turnover_rate=").append(turnover_rate);
        sb.append(", rise_num=").append(rise_num);
        sb.append(", fall_num=").append(fall_num);
        sb.append(", fair_num=").append(fair_num);
        sb.append(", limit_up_num=").append(limit_up_num);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", come_source=").append(come_source);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}