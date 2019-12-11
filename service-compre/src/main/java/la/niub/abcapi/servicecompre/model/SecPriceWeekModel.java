package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SecPriceWeekModel implements Serializable {
    private Integer id;

    private Long sec_uni_code;

    private Date end_date;

    private Date time;

    private BigDecimal close_price;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal pre_close_week;

    private BigDecimal differ;

    private BigDecimal differ_range;

    private BigDecimal turn;

    private BigDecimal volume;

    private BigDecimal t_num;

    private BigDecimal amount;

    private BigDecimal amplitude;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private static final long serialVersionUID = 1L;

    public SecPriceWeekModel(Integer id, Long sec_uni_code, Date end_date, Date time, BigDecimal close_price, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal pre_close_week, BigDecimal differ, BigDecimal differ_range, BigDecimal turn, BigDecimal volume, BigDecimal t_num, BigDecimal amount, BigDecimal amplitude, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.end_date = end_date;
        this.time = time;
        this.close_price = close_price;
        this.open = open;
        this.high = high;
        this.low = low;
        this.pre_close_week = pre_close_week;
        this.differ = differ;
        this.differ_range = differ_range;
        this.turn = turn;
        this.volume = volume;
        this.t_num = t_num;
        this.amount = amount;
        this.amplitude = amplitude;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
    }

    public SecPriceWeekModel() {
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

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getClose_price() {
        return close_price;
    }

    public void setClose_price(BigDecimal close_price) {
        this.close_price = close_price;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
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

    public BigDecimal getPre_close_week() {
        return pre_close_week;
    }

    public void setPre_close_week(BigDecimal pre_close_week) {
        this.pre_close_week = pre_close_week;
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

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getT_num() {
        return t_num;
    }

    public void setT_num(BigDecimal t_num) {
        this.t_num = t_num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(BigDecimal amplitude) {
        this.amplitude = amplitude;
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
        sb.append(", sec_uni_code=").append(sec_uni_code);
        sb.append(", end_date=").append(end_date);
        sb.append(", time=").append(time);
        sb.append(", close_price=").append(close_price);
        sb.append(", open=").append(open);
        sb.append(", high=").append(high);
        sb.append(", low=").append(low);
        sb.append(", pre_close_week=").append(pre_close_week);
        sb.append(", differ=").append(differ);
        sb.append(", differ_range=").append(differ_range);
        sb.append(", turn=").append(turn);
        sb.append(", volume=").append(volume);
        sb.append(", t_num=").append(t_num);
        sb.append(", amount=").append(amount);
        sb.append(", amplitude=").append(amplitude);
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