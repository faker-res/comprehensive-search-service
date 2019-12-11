package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IndexPriceWeekModel implements Serializable {
    private Long id;

    private Long index_uni_code;

    private Date str_time;

    private Date end_date;

    private BigDecimal pre_close;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal differ;

    private BigDecimal differ_range;

    private BigDecimal volume;

    private BigDecimal amount;

    private BigDecimal turnover_rate;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private Byte push_search;

    private Byte push_product;

    private static final long serialVersionUID = 1L;

    public IndexPriceWeekModel(Long id, Long index_uni_code, Date str_time, Date end_date, BigDecimal pre_close, BigDecimal open, BigDecimal close, BigDecimal high, BigDecimal low, BigDecimal differ, BigDecimal differ_range, BigDecimal volume, BigDecimal amount, BigDecimal turnover_rate, Date createtime, Date updatetime, Byte status, String remark, String creator, String editor, String come_source, Byte push_search, Byte push_product) {
        this.id = id;
        this.index_uni_code = index_uni_code;
        this.str_time = str_time;
        this.end_date = end_date;
        this.pre_close = pre_close;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.differ = differ;
        this.differ_range = differ_range;
        this.volume = volume;
        this.amount = amount;
        this.turnover_rate = turnover_rate;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
        this.push_search = push_search;
        this.push_product = push_product;
    }

    public IndexPriceWeekModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIndex_uni_code() {
        return index_uni_code;
    }

    public void setIndex_uni_code(Long index_uni_code) {
        this.index_uni_code = index_uni_code;
    }

    public Date getStr_time() {
        return str_time;
    }

    public void setStr_time(Date str_time) {
        this.str_time = str_time;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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

    public BigDecimal getTurnover_rate() {
        return turnover_rate;
    }

    public void setTurnover_rate(BigDecimal turnover_rate) {
        this.turnover_rate = turnover_rate;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Byte getPush_search() {
        return push_search;
    }

    public void setPush_search(Byte push_search) {
        this.push_search = push_search;
    }

    public Byte getPush_product() {
        return push_product;
    }

    public void setPush_product(Byte push_product) {
        this.push_product = push_product;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", index_uni_code=").append(index_uni_code);
        sb.append(", str_time=").append(str_time);
        sb.append(", end_date=").append(end_date);
        sb.append(", pre_close=").append(pre_close);
        sb.append(", open=").append(open);
        sb.append(", close=").append(close);
        sb.append(", high=").append(high);
        sb.append(", low=").append(low);
        sb.append(", differ=").append(differ);
        sb.append(", differ_range=").append(differ_range);
        sb.append(", volume=").append(volume);
        sb.append(", amount=").append(amount);
        sb.append(", turnover_rate=").append(turnover_rate);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", come_source=").append(come_source);
        sb.append(", push_search=").append(push_search);
        sb.append(", push_product=").append(push_product);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}