package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class FundBenchmarkModel implements Serializable {
    private Long id;

    private Long sec_uni_code;

    private Date announcement_date;

    private Date begin_date;

    private Date end_date;

    private String fund_benchmark;

    private String yield_period;

    private Byte is_valid;

    private String memo;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private Byte push_search;

    private Byte push_product;

    private static final long serialVersionUID = 1L;

    public FundBenchmarkModel(Long id, Long sec_uni_code, Date announcement_date, Date begin_date, Date end_date, String fund_benchmark, String yield_period, Byte is_valid, String memo, Date createtime, Date updatetime, Byte status, String creator, String editor, Byte push_search, Byte push_product) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.announcement_date = announcement_date;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.fund_benchmark = fund_benchmark;
        this.yield_period = yield_period;
        this.is_valid = is_valid;
        this.memo = memo;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
        this.push_search = push_search;
        this.push_product = push_product;
    }

    public FundBenchmarkModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSec_uni_code() {
        return sec_uni_code;
    }

    public void setSec_uni_code(Long sec_uni_code) {
        this.sec_uni_code = sec_uni_code;
    }

    public Date getAnnouncement_date() {
        return announcement_date;
    }

    public void setAnnouncement_date(Date announcement_date) {
        this.announcement_date = announcement_date;
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

    public String getFund_benchmark() {
        return fund_benchmark;
    }

    public void setFund_benchmark(String fund_benchmark) {
        this.fund_benchmark = fund_benchmark == null ? null : fund_benchmark.trim();
    }

    public String getYield_period() {
        return yield_period;
    }

    public void setYield_period(String yield_period) {
        this.yield_period = yield_period == null ? null : yield_period.trim();
    }

    public Byte getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(Byte is_valid) {
        this.is_valid = is_valid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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
        sb.append(", sec_uni_code=").append(sec_uni_code);
        sb.append(", announcement_date=").append(announcement_date);
        sb.append(", begin_date=").append(begin_date);
        sb.append(", end_date=").append(end_date);
        sb.append(", fund_benchmark=").append(fund_benchmark);
        sb.append(", yield_period=").append(yield_period);
        sb.append(", is_valid=").append(is_valid);
        sb.append(", memo=").append(memo);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", push_search=").append(push_search);
        sb.append(", push_product=").append(push_product);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}