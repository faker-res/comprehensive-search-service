package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class AnalystChanStatusModel implements Serializable {
    private Integer id;

    private String peo_uni_code;

    private String analyst_code;

    private Date certificate_issued_date;

    private Date certificate_validity_date;

    private Long org_uni_code;

    private String current_org;

    private String practice_post;

    private String certificate_status;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private Byte push_search;

    private Byte push_product;

    private String org_sname;

    private Long org_type;

    private static final long serialVersionUID = 1L;

    public AnalystChanStatusModel(Integer id, String peo_uni_code, String analyst_code, Date certificate_issued_date, Date certificate_validity_date, Long org_uni_code, String current_org, String practice_post, String certificate_status, Date createtime, Date updatetime, Byte status, String remark, String creator, String editor, String come_source, Byte push_search, Byte push_product, String org_sname, Long org_type) {
        this.id = id;
        this.peo_uni_code = peo_uni_code;
        this.analyst_code = analyst_code;
        this.certificate_issued_date = certificate_issued_date;
        this.certificate_validity_date = certificate_validity_date;
        this.org_uni_code = org_uni_code;
        this.current_org = current_org;
        this.practice_post = practice_post;
        this.certificate_status = certificate_status;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
        this.push_search = push_search;
        this.push_product = push_product;
        this.org_sname = org_sname;
        this.org_type = org_type;
    }

    public AnalystChanStatusModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code == null ? null : peo_uni_code.trim();
    }

    public String getAnalyst_code() {
        return analyst_code;
    }

    public void setAnalyst_code(String analyst_code) {
        this.analyst_code = analyst_code == null ? null : analyst_code.trim();
    }

    public Date getCertificate_issued_date() {
        return certificate_issued_date;
    }

    public void setCertificate_issued_date(Date certificate_issued_date) {
        this.certificate_issued_date = certificate_issued_date;
    }

    public Date getCertificate_validity_date() {
        return certificate_validity_date;
    }

    public void setCertificate_validity_date(Date certificate_validity_date) {
        this.certificate_validity_date = certificate_validity_date;
    }

    public Long getOrg_uni_code() {
        return org_uni_code;
    }

    public void setOrg_uni_code(Long org_uni_code) {
        this.org_uni_code = org_uni_code;
    }

    public String getCurrent_org() {
        return current_org;
    }

    public void setCurrent_org(String current_org) {
        this.current_org = current_org == null ? null : current_org.trim();
    }

    public String getPractice_post() {
        return practice_post;
    }

    public void setPractice_post(String practice_post) {
        this.practice_post = practice_post == null ? null : practice_post.trim();
    }

    public String getCertificate_status() {
        return certificate_status;
    }

    public void setCertificate_status(String certificate_status) {
        this.certificate_status = certificate_status == null ? null : certificate_status.trim();
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

    public String getOrg_sname() {
        return org_sname;
    }

    public void setOrg_sname(String org_sname) {
        this.org_sname = org_sname == null ? null : org_sname.trim();
    }

    public Long getOrg_type() {
        return org_type;
    }

    public void setOrg_type(Long org_type) {
        this.org_type = org_type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", analyst_code=").append(analyst_code);
        sb.append(", certificate_issued_date=").append(certificate_issued_date);
        sb.append(", certificate_validity_date=").append(certificate_validity_date);
        sb.append(", org_uni_code=").append(org_uni_code);
        sb.append(", current_org=").append(current_org);
        sb.append(", practice_post=").append(practice_post);
        sb.append(", certificate_status=").append(certificate_status);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", come_source=").append(come_source);
        sb.append(", push_search=").append(push_search);
        sb.append(", push_product=").append(push_product);
        sb.append(", org_sname=").append(org_sname);
        sb.append(", org_type=").append(org_type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}