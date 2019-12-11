package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IndexCaptitalFlowYearModel implements Serializable {
    private Long id;

    private Long index_uni_code;

    private Date begin_date;

    private Date end_date;

    private String index_code;

    private BigDecimal trade_amount;

    private BigDecimal main_in_flow;

    private BigDecimal main_out_flow;

    private BigDecimal main_netin_flow;

    private BigDecimal main_net_rate;

    private BigDecimal sup_in_volume;

    private BigDecimal sup_out_volume;

    private BigDecimal sup_net_rate;

    private BigDecimal sup_in_flow;

    private BigDecimal sup_out_flow;

    private BigDecimal sup_netin_flow;

    private BigDecimal big_in_volume;

    private BigDecimal big_out_volume;

    private BigDecimal big_net_rate;

    private BigDecimal big_in_flow;

    private BigDecimal big_out_flow;

    private BigDecimal big_netin_flow;

    private BigDecimal mid_in_volume;

    private BigDecimal mid_out_volume;

    private BigDecimal mid_net_rate;

    private BigDecimal mid_in_flow;

    private BigDecimal mid_out_flow;

    private BigDecimal mid_netin_flow;

    private BigDecimal sma_in_volume;

    private BigDecimal sma_out_volume;

    private BigDecimal sma_net_rate;

    private BigDecimal sma_in_flow;

    private BigDecimal sma_out_flow;

    private BigDecimal sma_netin_flow;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private String come_source;

    private Byte push_search;

    private Byte push_product;

    private static final long serialVersionUID = 1L;

    public IndexCaptitalFlowYearModel(Long id, Long index_uni_code, Date begin_date, Date end_date, String index_code, BigDecimal trade_amount, BigDecimal main_in_flow, BigDecimal main_out_flow, BigDecimal main_netin_flow, BigDecimal main_net_rate, BigDecimal sup_in_volume, BigDecimal sup_out_volume, BigDecimal sup_net_rate, BigDecimal sup_in_flow, BigDecimal sup_out_flow, BigDecimal sup_netin_flow, BigDecimal big_in_volume, BigDecimal big_out_volume, BigDecimal big_net_rate, BigDecimal big_in_flow, BigDecimal big_out_flow, BigDecimal big_netin_flow, BigDecimal mid_in_volume, BigDecimal mid_out_volume, BigDecimal mid_net_rate, BigDecimal mid_in_flow, BigDecimal mid_out_flow, BigDecimal mid_netin_flow, BigDecimal sma_in_volume, BigDecimal sma_out_volume, BigDecimal sma_net_rate, BigDecimal sma_in_flow, BigDecimal sma_out_flow, BigDecimal sma_netin_flow, Date createtime, Date updatetime, Byte status, String creator, String editor, String come_source, Byte push_search, Byte push_product) {
        this.id = id;
        this.index_uni_code = index_uni_code;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.index_code = index_code;
        this.trade_amount = trade_amount;
        this.main_in_flow = main_in_flow;
        this.main_out_flow = main_out_flow;
        this.main_netin_flow = main_netin_flow;
        this.main_net_rate = main_net_rate;
        this.sup_in_volume = sup_in_volume;
        this.sup_out_volume = sup_out_volume;
        this.sup_net_rate = sup_net_rate;
        this.sup_in_flow = sup_in_flow;
        this.sup_out_flow = sup_out_flow;
        this.sup_netin_flow = sup_netin_flow;
        this.big_in_volume = big_in_volume;
        this.big_out_volume = big_out_volume;
        this.big_net_rate = big_net_rate;
        this.big_in_flow = big_in_flow;
        this.big_out_flow = big_out_flow;
        this.big_netin_flow = big_netin_flow;
        this.mid_in_volume = mid_in_volume;
        this.mid_out_volume = mid_out_volume;
        this.mid_net_rate = mid_net_rate;
        this.mid_in_flow = mid_in_flow;
        this.mid_out_flow = mid_out_flow;
        this.mid_netin_flow = mid_netin_flow;
        this.sma_in_volume = sma_in_volume;
        this.sma_out_volume = sma_out_volume;
        this.sma_net_rate = sma_net_rate;
        this.sma_in_flow = sma_in_flow;
        this.sma_out_flow = sma_out_flow;
        this.sma_netin_flow = sma_netin_flow;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
        this.push_search = push_search;
        this.push_product = push_product;
    }

    public IndexCaptitalFlowYearModel() {
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

    public Date getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getIndex_code() {
        return index_code;
    }

    public void setIndex_code(String index_code) {
        this.index_code = index_code == null ? null : index_code.trim();
    }

    public BigDecimal getTrade_amount() {
        return trade_amount;
    }

    public void setTrade_amount(BigDecimal trade_amount) {
        this.trade_amount = trade_amount;
    }

    public BigDecimal getMain_in_flow() {
        return main_in_flow;
    }

    public void setMain_in_flow(BigDecimal main_in_flow) {
        this.main_in_flow = main_in_flow;
    }

    public BigDecimal getMain_out_flow() {
        return main_out_flow;
    }

    public void setMain_out_flow(BigDecimal main_out_flow) {
        this.main_out_flow = main_out_flow;
    }

    public BigDecimal getMain_netin_flow() {
        return main_netin_flow;
    }

    public void setMain_netin_flow(BigDecimal main_netin_flow) {
        this.main_netin_flow = main_netin_flow;
    }

    public BigDecimal getMain_net_rate() {
        return main_net_rate;
    }

    public void setMain_net_rate(BigDecimal main_net_rate) {
        this.main_net_rate = main_net_rate;
    }

    public BigDecimal getSup_in_volume() {
        return sup_in_volume;
    }

    public void setSup_in_volume(BigDecimal sup_in_volume) {
        this.sup_in_volume = sup_in_volume;
    }

    public BigDecimal getSup_out_volume() {
        return sup_out_volume;
    }

    public void setSup_out_volume(BigDecimal sup_out_volume) {
        this.sup_out_volume = sup_out_volume;
    }

    public BigDecimal getSup_net_rate() {
        return sup_net_rate;
    }

    public void setSup_net_rate(BigDecimal sup_net_rate) {
        this.sup_net_rate = sup_net_rate;
    }

    public BigDecimal getSup_in_flow() {
        return sup_in_flow;
    }

    public void setSup_in_flow(BigDecimal sup_in_flow) {
        this.sup_in_flow = sup_in_flow;
    }

    public BigDecimal getSup_out_flow() {
        return sup_out_flow;
    }

    public void setSup_out_flow(BigDecimal sup_out_flow) {
        this.sup_out_flow = sup_out_flow;
    }

    public BigDecimal getSup_netin_flow() {
        return sup_netin_flow;
    }

    public void setSup_netin_flow(BigDecimal sup_netin_flow) {
        this.sup_netin_flow = sup_netin_flow;
    }

    public BigDecimal getBig_in_volume() {
        return big_in_volume;
    }

    public void setBig_in_volume(BigDecimal big_in_volume) {
        this.big_in_volume = big_in_volume;
    }

    public BigDecimal getBig_out_volume() {
        return big_out_volume;
    }

    public void setBig_out_volume(BigDecimal big_out_volume) {
        this.big_out_volume = big_out_volume;
    }

    public BigDecimal getBig_net_rate() {
        return big_net_rate;
    }

    public void setBig_net_rate(BigDecimal big_net_rate) {
        this.big_net_rate = big_net_rate;
    }

    public BigDecimal getBig_in_flow() {
        return big_in_flow;
    }

    public void setBig_in_flow(BigDecimal big_in_flow) {
        this.big_in_flow = big_in_flow;
    }

    public BigDecimal getBig_out_flow() {
        return big_out_flow;
    }

    public void setBig_out_flow(BigDecimal big_out_flow) {
        this.big_out_flow = big_out_flow;
    }

    public BigDecimal getBig_netin_flow() {
        return big_netin_flow;
    }

    public void setBig_netin_flow(BigDecimal big_netin_flow) {
        this.big_netin_flow = big_netin_flow;
    }

    public BigDecimal getMid_in_volume() {
        return mid_in_volume;
    }

    public void setMid_in_volume(BigDecimal mid_in_volume) {
        this.mid_in_volume = mid_in_volume;
    }

    public BigDecimal getMid_out_volume() {
        return mid_out_volume;
    }

    public void setMid_out_volume(BigDecimal mid_out_volume) {
        this.mid_out_volume = mid_out_volume;
    }

    public BigDecimal getMid_net_rate() {
        return mid_net_rate;
    }

    public void setMid_net_rate(BigDecimal mid_net_rate) {
        this.mid_net_rate = mid_net_rate;
    }

    public BigDecimal getMid_in_flow() {
        return mid_in_flow;
    }

    public void setMid_in_flow(BigDecimal mid_in_flow) {
        this.mid_in_flow = mid_in_flow;
    }

    public BigDecimal getMid_out_flow() {
        return mid_out_flow;
    }

    public void setMid_out_flow(BigDecimal mid_out_flow) {
        this.mid_out_flow = mid_out_flow;
    }

    public BigDecimal getMid_netin_flow() {
        return mid_netin_flow;
    }

    public void setMid_netin_flow(BigDecimal mid_netin_flow) {
        this.mid_netin_flow = mid_netin_flow;
    }

    public BigDecimal getSma_in_volume() {
        return sma_in_volume;
    }

    public void setSma_in_volume(BigDecimal sma_in_volume) {
        this.sma_in_volume = sma_in_volume;
    }

    public BigDecimal getSma_out_volume() {
        return sma_out_volume;
    }

    public void setSma_out_volume(BigDecimal sma_out_volume) {
        this.sma_out_volume = sma_out_volume;
    }

    public BigDecimal getSma_net_rate() {
        return sma_net_rate;
    }

    public void setSma_net_rate(BigDecimal sma_net_rate) {
        this.sma_net_rate = sma_net_rate;
    }

    public BigDecimal getSma_in_flow() {
        return sma_in_flow;
    }

    public void setSma_in_flow(BigDecimal sma_in_flow) {
        this.sma_in_flow = sma_in_flow;
    }

    public BigDecimal getSma_out_flow() {
        return sma_out_flow;
    }

    public void setSma_out_flow(BigDecimal sma_out_flow) {
        this.sma_out_flow = sma_out_flow;
    }

    public BigDecimal getSma_netin_flow() {
        return sma_netin_flow;
    }

    public void setSma_netin_flow(BigDecimal sma_netin_flow) {
        this.sma_netin_flow = sma_netin_flow;
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
        sb.append(", begin_date=").append(begin_date);
        sb.append(", end_date=").append(end_date);
        sb.append(", index_code=").append(index_code);
        sb.append(", trade_amount=").append(trade_amount);
        sb.append(", main_in_flow=").append(main_in_flow);
        sb.append(", main_out_flow=").append(main_out_flow);
        sb.append(", main_netin_flow=").append(main_netin_flow);
        sb.append(", main_net_rate=").append(main_net_rate);
        sb.append(", sup_in_volume=").append(sup_in_volume);
        sb.append(", sup_out_volume=").append(sup_out_volume);
        sb.append(", sup_net_rate=").append(sup_net_rate);
        sb.append(", sup_in_flow=").append(sup_in_flow);
        sb.append(", sup_out_flow=").append(sup_out_flow);
        sb.append(", sup_netin_flow=").append(sup_netin_flow);
        sb.append(", big_in_volume=").append(big_in_volume);
        sb.append(", big_out_volume=").append(big_out_volume);
        sb.append(", big_net_rate=").append(big_net_rate);
        sb.append(", big_in_flow=").append(big_in_flow);
        sb.append(", big_out_flow=").append(big_out_flow);
        sb.append(", big_netin_flow=").append(big_netin_flow);
        sb.append(", mid_in_volume=").append(mid_in_volume);
        sb.append(", mid_out_volume=").append(mid_out_volume);
        sb.append(", mid_net_rate=").append(mid_net_rate);
        sb.append(", mid_in_flow=").append(mid_in_flow);
        sb.append(", mid_out_flow=").append(mid_out_flow);
        sb.append(", mid_netin_flow=").append(mid_netin_flow);
        sb.append(", sma_in_volume=").append(sma_in_volume);
        sb.append(", sma_out_volume=").append(sma_out_volume);
        sb.append(", sma_net_rate=").append(sma_net_rate);
        sb.append(", sma_in_flow=").append(sma_in_flow);
        sb.append(", sma_out_flow=").append(sma_out_flow);
        sb.append(", sma_netin_flow=").append(sma_netin_flow);
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