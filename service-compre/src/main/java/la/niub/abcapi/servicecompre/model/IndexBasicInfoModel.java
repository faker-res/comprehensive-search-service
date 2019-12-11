package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IndexBasicInfoModel implements Serializable {
    private Long index_uni_code;

    private Long index_id;

    private String index_name;

    private String index_code;

    private String abc_index_code;

    private Date bench_date;

    private BigDecimal bench_num;

    private Date publish_date;

    private Date end_date;

    private Long chg_period;

    private String index_memo;

    private Long index_type;

    private String pub_org_name;

    private Long pub_org_code;

    private Integer component_sum;

    private String wa_method;

    private Long security_type;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private static final long serialVersionUID = 1L;

    public IndexBasicInfoModel(Long index_uni_code, Long index_id, String index_name, String index_code, String abc_index_code, Date bench_date, BigDecimal bench_num, Date publish_date, Date end_date, Long chg_period, String index_memo, Long index_type, String pub_org_name, Long pub_org_code, Integer component_sum, String wa_method, Long security_type, Date createtime, Date updatetime, String status, String remark, String creator, String editor) {
        this.index_uni_code = index_uni_code;
        this.index_id = index_id;
        this.index_name = index_name;
        this.index_code = index_code;
        this.abc_index_code = abc_index_code;
        this.bench_date = bench_date;
        this.bench_num = bench_num;
        this.publish_date = publish_date;
        this.end_date = end_date;
        this.chg_period = chg_period;
        this.index_memo = index_memo;
        this.index_type = index_type;
        this.pub_org_name = pub_org_name;
        this.pub_org_code = pub_org_code;
        this.component_sum = component_sum;
        this.wa_method = wa_method;
        this.security_type = security_type;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
    }

    public IndexBasicInfoModel() {
        super();
    }

    public Long getIndex_uni_code() {
        return index_uni_code;
    }

    public void setIndex_uni_code(Long index_uni_code) {
        this.index_uni_code = index_uni_code;
    }

    public Long getIndex_id() {
        return index_id;
    }

    public void setIndex_id(Long index_id) {
        this.index_id = index_id;
    }

    public String getIndex_name() {
        return index_name;
    }

    public void setIndex_name(String index_name) {
        this.index_name = index_name == null ? null : index_name.trim();
    }

    public String getIndex_code() {
        return index_code;
    }

    public void setIndex_code(String index_code) {
        this.index_code = index_code == null ? null : index_code.trim();
    }

    public String getAbc_index_code() {
        return abc_index_code;
    }

    public void setAbc_index_code(String abc_index_code) {
        this.abc_index_code = abc_index_code == null ? null : abc_index_code.trim();
    }

    public Date getBench_date() {
        return bench_date;
    }

    public void setBench_date(Date bench_date) {
        this.bench_date = bench_date;
    }

    public BigDecimal getBench_num() {
        return bench_num;
    }

    public void setBench_num(BigDecimal bench_num) {
        this.bench_num = bench_num;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Long getChg_period() {
        return chg_period;
    }

    public void setChg_period(Long chg_period) {
        this.chg_period = chg_period;
    }

    public String getIndex_memo() {
        return index_memo;
    }

    public void setIndex_memo(String index_memo) {
        this.index_memo = index_memo == null ? null : index_memo.trim();
    }

    public Long getIndex_type() {
        return index_type;
    }

    public void setIndex_type(Long index_type) {
        this.index_type = index_type;
    }

    public String getPub_org_name() {
        return pub_org_name;
    }

    public void setPub_org_name(String pub_org_name) {
        this.pub_org_name = pub_org_name == null ? null : pub_org_name.trim();
    }

    public Long getPub_org_code() {
        return pub_org_code;
    }

    public void setPub_org_code(Long pub_org_code) {
        this.pub_org_code = pub_org_code;
    }

    public Integer getComponent_sum() {
        return component_sum;
    }

    public void setComponent_sum(Integer component_sum) {
        this.component_sum = component_sum;
    }

    public String getWa_method() {
        return wa_method;
    }

    public void setWa_method(String wa_method) {
        this.wa_method = wa_method == null ? null : wa_method.trim();
    }

    public Long getSecurity_type() {
        return security_type;
    }

    public void setSecurity_type(Long security_type) {
        this.security_type = security_type;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", index_uni_code=").append(index_uni_code);
        sb.append(", index_id=").append(index_id);
        sb.append(", index_name=").append(index_name);
        sb.append(", index_code=").append(index_code);
        sb.append(", abc_index_code=").append(abc_index_code);
        sb.append(", bench_date=").append(bench_date);
        sb.append(", bench_num=").append(bench_num);
        sb.append(", publish_date=").append(publish_date);
        sb.append(", end_date=").append(end_date);
        sb.append(", chg_period=").append(chg_period);
        sb.append(", index_memo=").append(index_memo);
        sb.append(", index_type=").append(index_type);
        sb.append(", pub_org_name=").append(pub_org_name);
        sb.append(", pub_org_code=").append(pub_org_code);
        sb.append(", component_sum=").append(component_sum);
        sb.append(", wa_method=").append(wa_method);
        sb.append(", security_type=").append(security_type);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}