package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IndexRangeChanModel implements Serializable {
    private Long id;

    private Long index_uni_code;

    private Long period;

    private Date trade_date;

    private BigDecimal differ_range;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private String come_source;

    private Byte push_search;

    private Byte push_product;

    private static final long serialVersionUID = 1L;

    public IndexRangeChanModel(Long id, Long index_uni_code, Long period, Date trade_date, BigDecimal differ_range, Date createtime, Date updatetime, Byte status, String creator, String editor, String come_source, Byte push_search, Byte push_product) {
        this.id = id;
        this.index_uni_code = index_uni_code;
        this.period = period;
        this.trade_date = trade_date;
        this.differ_range = differ_range;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
        this.push_search = push_search;
        this.push_product = push_product;
    }

    public IndexRangeChanModel() {
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

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    public Date getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date = trade_date;
    }

    public BigDecimal getDiffer_range() {
        return differ_range;
    }

    public void setDiffer_range(BigDecimal differ_range) {
        this.differ_range = differ_range;
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
        sb.append(", period=").append(period);
        sb.append(", trade_date=").append(trade_date);
        sb.append(", differ_range=").append(differ_range);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
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