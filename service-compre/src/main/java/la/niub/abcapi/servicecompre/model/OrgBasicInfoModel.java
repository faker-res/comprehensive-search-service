package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrgBasicInfoModel implements Serializable {
    private Long org_uni_code;

    private Long com_uni_code;

    private Long org_type;

    private String org_name;

    private String org_sname;

    private String org_ename;

    private String org_espell;

    private BigDecimal org_capital;

    private Long currency_type;

    private Date build_time;

    private Long eco_nature;

    private Long com_nature;

    private String regist_add;

    private String office_add;

    private String contact_add;

    private String org_web;

    private String legal_rp;

    private String gen_manager;

    private String contact_tel;

    private String contact_fax;

    private String email;

    private String official_number;

    private Integer license;

    private Date start_date;

    private Date cutoff_date;

    private Long reason;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private String accountant;

    private String lawyer;

    private static final long serialVersionUID = 1L;

    public String getAccountant() {
        return accountant;
    }

    public void setAccountant(String accountant) {
        this.accountant = accountant;
    }

    public String getLawyer() {
        return lawyer;
    }

    public void setLawyer(String lawyer) {
        this.lawyer = lawyer;
    }

    public OrgBasicInfoModel() {
        super();
    }

    public Long getOrg_uni_code() {
        return org_uni_code;
    }

    public void setOrg_uni_code(Long org_uni_code) {
        this.org_uni_code = org_uni_code;
    }

    public Long getCom_uni_code() {
        return com_uni_code;
    }

    public void setCom_uni_code(Long com_uni_code) {
        this.com_uni_code = com_uni_code;
    }

    public Long getOrg_type() {
        return org_type;
    }

    public void setOrg_type(Long org_type) {
        this.org_type = org_type;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name == null ? null : org_name.trim();
    }

    public String getOrg_sname() {
        return org_sname;
    }

    public void setOrg_sname(String org_sname) {
        this.org_sname = org_sname == null ? null : org_sname.trim();
    }

    public String getOrg_ename() {
        return org_ename;
    }

    public void setOrg_ename(String org_ename) {
        this.org_ename = org_ename == null ? null : org_ename.trim();
    }

    public String getOrg_espell() {
        return org_espell;
    }

    public void setOrg_espell(String org_espell) {
        this.org_espell = org_espell == null ? null : org_espell.trim();
    }

    public BigDecimal getOrg_capital() {
        return org_capital;
    }

    public void setOrg_capital(BigDecimal org_capital) {
        this.org_capital = org_capital;
    }

    public Long getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(Long currency_type) {
        this.currency_type = currency_type;
    }

    public Date getBuild_time() {
        return build_time;
    }

    public void setBuild_time(Date build_time) {
        this.build_time = build_time;
    }

    public Long getEco_nature() {
        return eco_nature;
    }

    public void setEco_nature(Long eco_nature) {
        this.eco_nature = eco_nature;
    }

    public Long getCom_nature() {
        return com_nature;
    }

    public void setCom_nature(Long com_nature) {
        this.com_nature = com_nature;
    }

    public String getRegist_add() {
        return regist_add;
    }

    public void setRegist_add(String regist_add) {
        this.regist_add = regist_add == null ? null : regist_add.trim();
    }

    public String getOffice_add() {
        return office_add;
    }

    public void setOffice_add(String office_add) {
        this.office_add = office_add == null ? null : office_add.trim();
    }

    public String getContact_add() {
        return contact_add;
    }

    public void setContact_add(String contact_add) {
        this.contact_add = contact_add == null ? null : contact_add.trim();
    }

    public String getOrg_web() {
        return org_web;
    }

    public void setOrg_web(String org_web) {
        this.org_web = org_web == null ? null : org_web.trim();
    }

    public String getLegal_rp() {
        return legal_rp;
    }

    public void setLegal_rp(String legal_rp) {
        this.legal_rp = legal_rp == null ? null : legal_rp.trim();
    }

    public String getGen_manager() {
        return gen_manager;
    }

    public void setGen_manager(String gen_manager) {
        this.gen_manager = gen_manager == null ? null : gen_manager.trim();
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel == null ? null : contact_tel.trim();
    }

    public String getContact_fax() {
        return contact_fax;
    }

    public void setContact_fax(String contact_fax) {
        this.contact_fax = contact_fax == null ? null : contact_fax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOfficial_number() {
        return official_number;
    }

    public void setOfficial_number(String official_number) {
        this.official_number = official_number == null ? null : official_number.trim();
    }

    public Integer getLicense() {
        return license;
    }

    public void setLicense(Integer license) {
        this.license = license;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getCutoff_date() {
        return cutoff_date;
    }

    public void setCutoff_date(Date cutoff_date) {
        this.cutoff_date = cutoff_date;
    }

    public Long getReason() {
        return reason;
    }

    public void setReason(Long reason) {
        this.reason = reason;
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

    public String getCome_source() {
        return come_source;
    }

    public void setCome_source(String come_source) {
        this.come_source = come_source == null ? null : come_source.trim();
    }

    public OrgBasicInfoModel(Long org_uni_code, Long com_uni_code, Long org_type, String org_name, String org_sname, String org_ename, String org_espell, BigDecimal org_capital, Long currency_type, Date build_time, Long eco_nature, Long com_nature, String regist_add, String office_add, String contact_add, String org_web, String legal_rp, String gen_manager, String contact_tel, String contact_fax, String email, String official_number, Integer license, Date start_date, Date cutoff_date, Long reason, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source) {
        this.org_uni_code = org_uni_code;
        this.com_uni_code = com_uni_code;
        this.org_type = org_type;
        this.org_name = org_name;
        this.org_sname = org_sname;
        this.org_ename = org_ename;
        this.org_espell = org_espell;
        this.org_capital = org_capital;
        this.currency_type = currency_type;
        this.build_time = build_time;
        this.eco_nature = eco_nature;
        this.com_nature = com_nature;
        this.regist_add = regist_add;
        this.office_add = office_add;
        this.contact_add = contact_add;
        this.org_web = org_web;
        this.legal_rp = legal_rp;
        this.gen_manager = gen_manager;
        this.contact_tel = contact_tel;
        this.contact_fax = contact_fax;
        this.email = email;
        this.official_number = official_number;
        this.license = license;
        this.start_date = start_date;
        this.cutoff_date = cutoff_date;
        this.reason = reason;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
    }

    @Override
    public String toString() {
        return "OrgBasicInfoModel{" +
                "org_uni_code=" + org_uni_code +
                ", com_uni_code=" + com_uni_code +
                ", org_type=" + org_type +
                ", org_name='" + org_name + '\'' +
                ", org_sname='" + org_sname + '\'' +
                ", org_ename='" + org_ename + '\'' +
                ", org_espell='" + org_espell + '\'' +
                ", org_capital=" + org_capital +
                ", currency_type=" + currency_type +
                ", build_time=" + build_time +
                ", eco_nature=" + eco_nature +
                ", com_nature=" + com_nature +
                ", regist_add='" + regist_add + '\'' +
                ", office_add='" + office_add + '\'' +
                ", contact_add='" + contact_add + '\'' +
                ", org_web='" + org_web + '\'' +
                ", legal_rp='" + legal_rp + '\'' +
                ", gen_manager='" + gen_manager + '\'' +
                ", contact_tel='" + contact_tel + '\'' +
                ", contact_fax='" + contact_fax + '\'' +
                ", email='" + email + '\'' +
                ", official_number='" + official_number + '\'' +
                ", license=" + license +
                ", start_date=" + start_date +
                ", cutoff_date=" + cutoff_date +
                ", reason=" + reason +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", creator='" + creator + '\'' +
                ", editor='" + editor + '\'' +
                ", come_source='" + come_source + '\'' +
                ", accountant='" + accountant + '\'' +
                ", lawyer='" + lawyer + '\'' +
                '}';
    }
}