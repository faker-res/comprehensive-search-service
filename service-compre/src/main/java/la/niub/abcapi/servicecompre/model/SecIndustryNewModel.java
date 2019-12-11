package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SecIndustryNewModel implements Serializable {
    private Long id;

    private Long sec_uni_code;

    private String stk_code;

    private Long indu_standard;

    private String indu_standard_name;

    private String if_performed;

    private String first_indu_code;

    private String first_indu_name;

    private String second_indu_code;

    private String second_indu_name;

    private String third_indu_code;

    private String third_indu_name;

    private String fourth_indu_code;

    private String fourth_indu_name;

    private Date sub_end_date;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private Date sub_beg_date;

    private BigDecimal dzh_seq;

    private static final long serialVersionUID = 1L;

    public SecIndustryNewModel(Long id, Long sec_uni_code, String stk_code, Long indu_standard, String indu_standard_name, String if_performed, String first_indu_code, String first_indu_name, String second_indu_code, String second_indu_name, String third_indu_code, String third_indu_name, String fourth_indu_code, String fourth_indu_name, Date sub_end_date, Date createtime, Date updatetime, String status, String remark, String creator, String editor, Date sub_beg_date, BigDecimal dzh_seq) {
        this.id = id;
        this.sec_uni_code = sec_uni_code;
        this.stk_code = stk_code;
        this.indu_standard = indu_standard;
        this.indu_standard_name = indu_standard_name;
        this.if_performed = if_performed;
        this.first_indu_code = first_indu_code;
        this.first_indu_name = first_indu_name;
        this.second_indu_code = second_indu_code;
        this.second_indu_name = second_indu_name;
        this.third_indu_code = third_indu_code;
        this.third_indu_name = third_indu_name;
        this.fourth_indu_code = fourth_indu_code;
        this.fourth_indu_name = fourth_indu_name;
        this.sub_end_date = sub_end_date;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.sub_beg_date = sub_beg_date;
        this.dzh_seq = dzh_seq;
    }

    public SecIndustryNewModel() {
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

    public String getStk_code() {
        return stk_code;
    }

    public void setStk_code(String stk_code) {
        this.stk_code = stk_code == null ? null : stk_code.trim();
    }

    public Long getIndu_standard() {
        return indu_standard;
    }

    public void setIndu_standard(Long indu_standard) {
        this.indu_standard = indu_standard;
    }

    public String getIndu_standard_name() {
        return indu_standard_name;
    }

    public void setIndu_standard_name(String indu_standard_name) {
        this.indu_standard_name = indu_standard_name == null ? null : indu_standard_name.trim();
    }

    public String getIf_performed() {
        return if_performed;
    }

    public void setIf_performed(String if_performed) {
        this.if_performed = if_performed == null ? null : if_performed.trim();
    }

    public String getFirst_indu_code() {
        return first_indu_code;
    }

    public void setFirst_indu_code(String first_indu_code) {
        this.first_indu_code = first_indu_code == null ? null : first_indu_code.trim();
    }

    public String getFirst_indu_name() {
        return first_indu_name;
    }

    public void setFirst_indu_name(String first_indu_name) {
        this.first_indu_name = first_indu_name == null ? null : first_indu_name.trim();
    }

    public String getSecond_indu_code() {
        return second_indu_code;
    }

    public void setSecond_indu_code(String second_indu_code) {
        this.second_indu_code = second_indu_code == null ? null : second_indu_code.trim();
    }

    public String getSecond_indu_name() {
        return second_indu_name;
    }

    public void setSecond_indu_name(String second_indu_name) {
        this.second_indu_name = second_indu_name == null ? null : second_indu_name.trim();
    }

    public String getThird_indu_code() {
        return third_indu_code;
    }

    public void setThird_indu_code(String third_indu_code) {
        this.third_indu_code = third_indu_code == null ? null : third_indu_code.trim();
    }

    public String getThird_indu_name() {
        return third_indu_name;
    }

    public void setThird_indu_name(String third_indu_name) {
        this.third_indu_name = third_indu_name == null ? null : third_indu_name.trim();
    }

    public String getFourth_indu_code() {
        return fourth_indu_code;
    }

    public void setFourth_indu_code(String fourth_indu_code) {
        this.fourth_indu_code = fourth_indu_code == null ? null : fourth_indu_code.trim();
    }

    public String getFourth_indu_name() {
        return fourth_indu_name;
    }

    public void setFourth_indu_name(String fourth_indu_name) {
        this.fourth_indu_name = fourth_indu_name == null ? null : fourth_indu_name.trim();
    }

    public Date getSub_end_date() {
        return sub_end_date;
    }

    public void setSub_end_date(Date sub_end_date) {
        this.sub_end_date = sub_end_date;
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

    public Date getSub_beg_date() {
        return sub_beg_date;
    }

    public void setSub_beg_date(Date sub_beg_date) {
        this.sub_beg_date = sub_beg_date;
    }

    public BigDecimal getDzh_seq() {
        return dzh_seq;
    }

    public void setDzh_seq(BigDecimal dzh_seq) {
        this.dzh_seq = dzh_seq;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sec_uni_code=").append(sec_uni_code);
        sb.append(", stk_code=").append(stk_code);
        sb.append(", indu_standard=").append(indu_standard);
        sb.append(", indu_standard_name=").append(indu_standard_name);
        sb.append(", if_performed=").append(if_performed);
        sb.append(", first_indu_code=").append(first_indu_code);
        sb.append(", first_indu_name=").append(first_indu_name);
        sb.append(", second_indu_code=").append(second_indu_code);
        sb.append(", second_indu_name=").append(second_indu_name);
        sb.append(", third_indu_code=").append(third_indu_code);
        sb.append(", third_indu_name=").append(third_indu_name);
        sb.append(", fourth_indu_code=").append(fourth_indu_code);
        sb.append(", fourth_indu_name=").append(fourth_indu_name);
        sb.append(", sub_end_date=").append(sub_end_date);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", sub_beg_date=").append(sub_beg_date);
        sb.append(", dzh_seq=").append(dzh_seq);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}