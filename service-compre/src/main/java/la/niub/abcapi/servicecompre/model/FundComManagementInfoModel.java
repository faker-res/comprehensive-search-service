package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class FundComManagementInfoModel implements Serializable {
    private Long id;

    private Long org_uni_code;

    private Date announcement_date;

    private Date begin_date;

    private Date end_date;

    private Long peo_uni_code;

    private String name;

    private Long post;

    private Byte sex_par;

    private String birth_day;

    private String high_edu;

    private String data_source;

    private String remark;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String creator;

    private String editor;

    private String back_gro;

    private static final long serialVersionUID = 1L;

    public FundComManagementInfoModel(Long id, Long org_uni_code, Date announcement_date, Date begin_date, Date end_date, Long peo_uni_code, String name, Long post, Byte sex_par, String birth_day, String high_edu, String data_source, String remark, Date createtime, Date updatetime, Byte status, String creator, String editor) {
        this.id = id;
        this.org_uni_code = org_uni_code;
        this.announcement_date = announcement_date;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.peo_uni_code = peo_uni_code;
        this.name = name;
        this.post = post;
        this.sex_par = sex_par;
        this.birth_day = birth_day;
        this.high_edu = high_edu;
        this.data_source = data_source;
        this.remark = remark;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
    }

    public FundComManagementInfoModel(Long id, Long org_uni_code, Date announcement_date, Date begin_date, Date end_date, Long peo_uni_code, String name, Long post, Byte sex_par, String birth_day, String high_edu, String data_source, String remark, Date createtime, Date updatetime, Byte status, String creator, String editor, String back_gro) {
        this.id = id;
        this.org_uni_code = org_uni_code;
        this.announcement_date = announcement_date;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.peo_uni_code = peo_uni_code;
        this.name = name;
        this.post = post;
        this.sex_par = sex_par;
        this.birth_day = birth_day;
        this.high_edu = high_edu;
        this.data_source = data_source;
        this.remark = remark;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.creator = creator;
        this.editor = editor;
        this.back_gro = back_gro;
    }

    public FundComManagementInfoModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrg_uni_code() {
        return org_uni_code;
    }

    public void setOrg_uni_code(Long org_uni_code) {
        this.org_uni_code = org_uni_code;
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

    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getPost() {
        return post;
    }

    public void setPost(Long post) {
        this.post = post;
    }

    public Byte getSex_par() {
        return sex_par;
    }

    public void setSex_par(Byte sex_par) {
        this.sex_par = sex_par;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day == null ? null : birth_day.trim();
    }

    public String getHigh_edu() {
        return high_edu;
    }

    public void setHigh_edu(String high_edu) {
        this.high_edu = high_edu == null ? null : high_edu.trim();
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source == null ? null : data_source.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getBack_gro() {
        return back_gro;
    }

    public void setBack_gro(String back_gro) {
        this.back_gro = back_gro == null ? null : back_gro.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", org_uni_code=").append(org_uni_code);
        sb.append(", announcement_date=").append(announcement_date);
        sb.append(", begin_date=").append(begin_date);
        sb.append(", end_date=").append(end_date);
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", name=").append(name);
        sb.append(", post=").append(post);
        sb.append(", sex_par=").append(sex_par);
        sb.append(", birth_day=").append(birth_day);
        sb.append(", high_edu=").append(high_edu);
        sb.append(", data_source=").append(data_source);
        sb.append(", remark=").append(remark);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", back_gro=").append(back_gro);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}