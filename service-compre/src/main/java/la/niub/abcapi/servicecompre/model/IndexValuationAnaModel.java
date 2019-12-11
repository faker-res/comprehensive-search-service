package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IndexValuationAnaModel implements Serializable {
    private Long id;

    private Long index_uni_code;

    private Date account_date;

    private BigDecimal total_market_value;

    private BigDecimal total_shares;

    private BigDecimal freefloat_market_value;

    private BigDecimal float_shares;

    private BigDecimal pe;

    private BigDecimal pelyr;

    private BigDecimal pettm;

    private BigDecimal pb;

    private BigDecimal pbmrq;

    private BigDecimal pcf;

    private BigDecimal pcf_ttm;

    private BigDecimal ps;

    private BigDecimal psttm;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private String come_source;

    private Byte push_search;

    private Byte push_product;

    private static final long serialVersionUID = 1L;

    public IndexValuationAnaModel(Long id, Long index_uni_code, Date account_date, BigDecimal total_market_value, BigDecimal total_shares, BigDecimal freefloat_market_value, BigDecimal float_shares, BigDecimal pe, BigDecimal pelyr, BigDecimal pettm, BigDecimal pb, BigDecimal pbmrq, BigDecimal pcf, BigDecimal pcf_ttm, BigDecimal ps, BigDecimal psttm, Date createtime, Date updatetime, Byte status, String creator, String editor, String come_source, Byte push_search, Byte push_product) {
        this.id = id;
        this.index_uni_code = index_uni_code;
        this.account_date = account_date;
        this.total_market_value = total_market_value;
        this.total_shares = total_shares;
        this.freefloat_market_value = freefloat_market_value;
        this.float_shares = float_shares;
        this.pe = pe;
        this.pelyr = pelyr;
        this.pettm = pettm;
        this.pb = pb;
        this.pbmrq = pbmrq;
        this.pcf = pcf;
        this.pcf_ttm = pcf_ttm;
        this.ps = ps;
        this.psttm = psttm;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
        this.push_search = push_search;
        this.push_product = push_product;
    }

    public IndexValuationAnaModel() {
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

    public Date getAccount_date() {
        return account_date;
    }

    public void setAccount_date(Date account_date) {
        this.account_date = account_date;
    }

    public BigDecimal getTotal_market_value() {
        return total_market_value;
    }

    public void setTotal_market_value(BigDecimal total_market_value) {
        this.total_market_value = total_market_value;
    }

    public BigDecimal getTotal_shares() {
        return total_shares;
    }

    public void setTotal_shares(BigDecimal total_shares) {
        this.total_shares = total_shares;
    }

    public BigDecimal getFreefloat_market_value() {
        return freefloat_market_value;
    }

    public void setFreefloat_market_value(BigDecimal freefloat_market_value) {
        this.freefloat_market_value = freefloat_market_value;
    }

    public BigDecimal getFloat_shares() {
        return float_shares;
    }

    public void setFloat_shares(BigDecimal float_shares) {
        this.float_shares = float_shares;
    }

    public BigDecimal getPe() {
        return pe;
    }

    public void setPe(BigDecimal pe) {
        this.pe = pe;
    }

    public BigDecimal getPelyr() {
        return pelyr;
    }

    public void setPelyr(BigDecimal pelyr) {
        this.pelyr = pelyr;
    }

    public BigDecimal getPettm() {
        return pettm;
    }

    public void setPettm(BigDecimal pettm) {
        this.pettm = pettm;
    }

    public BigDecimal getPb() {
        return pb;
    }

    public void setPb(BigDecimal pb) {
        this.pb = pb;
    }

    public BigDecimal getPbmrq() {
        return pbmrq;
    }

    public void setPbmrq(BigDecimal pbmrq) {
        this.pbmrq = pbmrq;
    }

    public BigDecimal getPcf() {
        return pcf;
    }

    public void setPcf(BigDecimal pcf) {
        this.pcf = pcf;
    }

    public BigDecimal getPcf_ttm() {
        return pcf_ttm;
    }

    public void setPcf_ttm(BigDecimal pcf_ttm) {
        this.pcf_ttm = pcf_ttm;
    }

    public BigDecimal getPs() {
        return ps;
    }

    public void setPs(BigDecimal ps) {
        this.ps = ps;
    }

    public BigDecimal getPsttm() {
        return psttm;
    }

    public void setPsttm(BigDecimal psttm) {
        this.psttm = psttm;
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
        sb.append(", account_date=").append(account_date);
        sb.append(", total_market_value=").append(total_market_value);
        sb.append(", total_shares=").append(total_shares);
        sb.append(", freefloat_market_value=").append(freefloat_market_value);
        sb.append(", float_shares=").append(float_shares);
        sb.append(", pe=").append(pe);
        sb.append(", pelyr=").append(pelyr);
        sb.append(", pettm=").append(pettm);
        sb.append(", pb=").append(pb);
        sb.append(", pbmrq=").append(pbmrq);
        sb.append(", pcf=").append(pcf);
        sb.append(", pcf_ttm=").append(pcf_ttm);
        sb.append(", ps=").append(ps);
        sb.append(", psttm=").append(psttm);
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