package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class ntfEntityPatentsModel implements Serializable {
    private Integer id;

    private Long com_id;

    private Long com_uni_code;

    private String com_name;

    private String patents_id;

    private String patents_publish_no;

    private String patents_no;

    private String cat_no;

    private String inventor;

    private String applicant_name;

    private Date application_time;

    private Date application_publish_time;

    private String agency;

    private String agent;

    private String address;

    private String come_source;

    private Date createtime;

    private Date updatetime;

    private String editor;

    private String remark;

    private String status;

    private String patents_name;

    private String abstracts;

    private String patents_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCom_id() {
        return com_id;
    }

    public void setCom_id(Long com_id) {
        this.com_id = com_id;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name == null ? null : com_name.trim();
    }

    public String getPatents_id() {
        return patents_id;
    }

    public void setPatents_id(String patents_id) {
        this.patents_id = patents_id == null ? null : patents_id.trim();
    }

    public String getPatents_publish_no() {
        return patents_publish_no;
    }

    public void setPatents_publish_no(String patents_publish_no) {
        this.patents_publish_no = patents_publish_no == null ? null : patents_publish_no.trim();
    }

    public String getPatents_no() {
        return patents_no;
    }

    public void setPatents_no(String patents_no) {
        this.patents_no = patents_no == null ? null : patents_no.trim();
    }

    public String getCat_no() {
        return cat_no;
    }

    public void setCat_no(String cat_no) {
        this.cat_no = cat_no == null ? null : cat_no.trim();
    }

    public String getInventor() {
        return inventor;
    }

    public void setInventor(String inventor) {
        this.inventor = inventor == null ? null : inventor.trim();
    }

    public String getApplicant_name() {
        return applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name == null ? null : applicant_name.trim();
    }

    public Date getApplication_time() {
        return application_time;
    }

    public void setApplication_time(Date application_time) {
        this.application_time = application_time;
    }

    public Date getApplication_publish_time() {
        return application_publish_time;
    }

    public void setApplication_publish_time(Date application_publish_time) {
        this.application_publish_time = application_publish_time;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency == null ? null : agency.trim();
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPatents_name() {
        return patents_name;
    }

    public void setPatents_name(String patents_name) {
        this.patents_name = patents_name;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getPatents_type() {
        return patents_type;
    }

    public void setPatents_type(String patents_type) {
        this.patents_type = patents_type;
    }

    @Override
    public String toString() {
        return "ntfEntityPatentsModel{" +
                "id=" + id +
                ", com_id=" + com_id +
                ", com_uni_code=" + com_uni_code +
                ", com_name='" + com_name + '\'' +
                ", patents_id='" + patents_id + '\'' +
                ", patents_publish_no='" + patents_publish_no + '\'' +
                ", patents_no='" + patents_no + '\'' +
                ", cat_no='" + cat_no + '\'' +
                ", inventor='" + inventor + '\'' +
                ", applicant_name='" + applicant_name + '\'' +
                ", application_time=" + application_time +
                ", application_publish_time=" + application_publish_time +
                ", agency='" + agency + '\'' +
                ", agent='" + agent + '\'' +
                ", address='" + address + '\'' +
                ", come_source='" + come_source + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", editor='" + editor + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", patents_name='" + patents_name + '\'' +
                ", abstracts='" + abstracts + '\'' +
                '}';
    }
}