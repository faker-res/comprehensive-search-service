package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ComLedPositionModel implements Serializable {
    private Integer id;

    private Long com_uni_code;

    private Long peo_uni_code;

    private Date decl_date;

    private String led_name;

    private Byte seq_num;

    private Long post_code;

    private Date in_date;

    private Date off_date;

    private String post_type;

    private String post_name;

    private String if_position;

    private BigDecimal qhqm_seq;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private String src_id;

    private static final long serialVersionUID = 1L;

    public ComLedPositionModel(Integer id, Long com_uni_code, Long peo_uni_code, Date decl_date, String led_name, Byte seq_num, Long post_code, Date in_date, Date off_date, String post_type, String post_name, String if_position, BigDecimal qhqm_seq, Date createtime, Date updatetime, Byte status, String remark, String creator, String editor, String come_source, String src_id) {
        this.id = id;
        this.com_uni_code = com_uni_code;
        this.peo_uni_code = peo_uni_code;
        this.decl_date = decl_date;
        this.led_name = led_name;
        this.seq_num = seq_num;
        this.post_code = post_code;
        this.in_date = in_date;
        this.off_date = off_date;
        this.post_type = post_type;
        this.post_name = post_name;
        this.if_position = if_position;
        this.qhqm_seq = qhqm_seq;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
        this.src_id = src_id;
    }

    public ComLedPositionModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public Date getDecl_date() {
        return decl_date;
    }

    public void setDecl_date(Date decl_date) {
        this.decl_date = decl_date;
    }

    public String getLed_name() {
        return led_name;
    }

    public void setLed_name(String led_name) {
        this.led_name = led_name == null ? null : led_name.trim();
    }

    public Byte getSeq_num() {
        return seq_num;
    }

    public void setSeq_num(Byte seq_num) {
        this.seq_num = seq_num;
    }

    public Long getPost_code() {
        return post_code;
    }

    public void setPost_code(Long post_code) {
        this.post_code = post_code;
    }

    public Date getIn_date() {
        return in_date;
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }

    public Date getOff_date() {
        return off_date;
    }

    public void setOff_date(Date off_date) {
        this.off_date = off_date;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type == null ? null : post_type.trim();
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name == null ? null : post_name.trim();
    }

    public String getIf_position() {
        return if_position;
    }

    public void setIf_position(String if_position) {
        this.if_position = if_position == null ? null : if_position.trim();
    }

    public BigDecimal getQhqm_seq() {
        return qhqm_seq;
    }

    public void setQhqm_seq(BigDecimal qhqm_seq) {
        this.qhqm_seq = qhqm_seq;
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

    public String getSrc_id() {
        return src_id;
    }

    public void setSrc_id(String src_id) {
        this.src_id = src_id == null ? null : src_id.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", com_uni_code=").append(com_uni_code);
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", decl_date=").append(decl_date);
        sb.append(", led_name=").append(led_name);
        sb.append(", seq_num=").append(seq_num);
        sb.append(", post_code=").append(post_code);
        sb.append(", in_date=").append(in_date);
        sb.append(", off_date=").append(off_date);
        sb.append(", post_type=").append(post_type);
        sb.append(", post_name=").append(post_name);
        sb.append(", if_position=").append(if_position);
        sb.append(", qhqm_seq=").append(qhqm_seq);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", come_source=").append(come_source);
        sb.append(", src_id=").append(src_id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}