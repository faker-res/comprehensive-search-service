package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class NtfXuqiuAttentionModel implements Serializable {
    private Integer id;

    private String stock_code;

    private String xq_id;

    private String stock_name;

    private String index_name;

    private Integer index_value;

    private String market;

    private Date stat_date;

    private String come_source;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String editor;

    private String remark;

    private static final long serialVersionUID = 1L;

    public NtfXuqiuAttentionModel(Integer id, String stock_code, String xq_id, String stock_name, String index_name, Integer index_value, String market, Date stat_date, String come_source, Date createtime, Date updatetime, Byte status, String editor, String remark) {
        this.id = id;
        this.stock_code = stock_code;
        this.xq_id = xq_id;
        this.stock_name = stock_name;
        this.index_name = index_name;
        this.index_value = index_value;
        this.market = market;
        this.stat_date = stat_date;
        this.come_source = come_source;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.editor = editor;
        this.remark = remark;
    }

    public NtfXuqiuAttentionModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code == null ? null : stock_code.trim();
    }

    public String getXq_id() {
        return xq_id;
    }

    public void setXq_id(String xq_id) {
        this.xq_id = xq_id == null ? null : xq_id.trim();
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name == null ? null : stock_name.trim();
    }

    public String getIndex_name() {
        return index_name;
    }

    public void setIndex_name(String index_name) {
        this.index_name = index_name == null ? null : index_name.trim();
    }

    public Integer getIndex_value() {
        return index_value;
    }

    public void setIndex_value(Integer index_value) {
        this.index_value = index_value;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market == null ? null : market.trim();
    }

    public Date getStat_date() {
        return stat_date;
    }

    public void setStat_date(Date stat_date) {
        this.stat_date = stat_date;
    }

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source == null ? null : come_source.trim();
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

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stock_code=").append(stock_code);
        sb.append(", xq_id=").append(xq_id);
        sb.append(", stock_name=").append(stock_name);
        sb.append(", index_name=").append(index_name);
        sb.append(", index_value=").append(index_value);
        sb.append(", market=").append(market);
        sb.append(", stat_date=").append(stat_date);
        sb.append(", come_source=").append(come_source);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", editor=").append(editor);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}