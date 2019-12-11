package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PeoBasicInfoModel implements Serializable {
    private Long peo_uni_code;

    private Long people_char;

    private String name;

    private Byte sex_par;

    private String birth_day;

    private String country;

    private String poli_status;

    private String university;

    private String high_edu;

    private String image;

    private Date createtime;

    private Date updatetime;

    private String status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private BigDecimal qhqm_seq;

    private String native_place;

    private String occupation;

    private String main_achievement;

    private String spouse;

    private static final long serialVersionUID = 1L;

    public PeoBasicInfoModel(Long peo_uni_code, Long people_char, String name, Byte sex_par, String birth_day, String country, String poli_status, String university, String high_edu, String image, Date createtime, Date updatetime, String status, String remark, String creator, String editor, String come_source, BigDecimal qhqm_seq, String native_place, String occupation, String main_achievement, String spouse) {
        this.peo_uni_code = peo_uni_code;
        this.people_char = people_char;
        this.name = name;
        this.sex_par = sex_par;
        this.birth_day = birth_day;
        this.country = country;
        this.poli_status = poli_status;
        this.university = university;
        this.high_edu = high_edu;
        this.image = image;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
        this.qhqm_seq = qhqm_seq;
        this.native_place = native_place;
        this.occupation = occupation;
        this.main_achievement = main_achievement;
        this.spouse = spouse;
    }

    public PeoBasicInfoModel() {
        super();
    }

    public Long getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(Long peo_uni_code) {
        this.peo_uni_code = peo_uni_code;
    }

    public Long getPeople_char() {
        return people_char;
    }

    public void setPeople_char(Long people_char) {
        this.people_char = people_char;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getPoli_status() {
        return poli_status;
    }

    public void setPoli_status(String poli_status) {
        this.poli_status = poli_status == null ? null : poli_status.trim();
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university == null ? null : university.trim();
    }

    public String getHigh_edu() {
        return high_edu;
    }

    public void setHigh_edu(String high_edu) {
        this.high_edu = high_edu == null ? null : high_edu.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
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

    public BigDecimal getQhqm_seq() {
        return qhqm_seq;
    }

    public void setQhqm_seq(BigDecimal qhqm_seq) {
        this.qhqm_seq = qhqm_seq;
    }

    public String getNative_place() {
        return native_place;
    }

    public void setNative_place(String native_place) {
        this.native_place = native_place == null ? null : native_place.trim();
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    public String getMain_achievement() {
        return main_achievement;
    }

    public void setMain_achievement(String main_achievement) {
        this.main_achievement = main_achievement == null ? null : main_achievement.trim();
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse == null ? null : spouse.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", peo_uni_code=").append(peo_uni_code);
        sb.append(", people_char=").append(people_char);
        sb.append(", name=").append(name);
        sb.append(", sex_par=").append(sex_par);
        sb.append(", birth_day=").append(birth_day);
        sb.append(", country=").append(country);
        sb.append(", poli_status=").append(poli_status);
        sb.append(", university=").append(university);
        sb.append(", high_edu=").append(high_edu);
        sb.append(", image=").append(image);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", come_source=").append(come_source);
        sb.append(", qhqm_seq=").append(qhqm_seq);
        sb.append(", native_place=").append(native_place);
        sb.append(", occupation=").append(occupation);
        sb.append(", main_achievement=").append(main_achievement);
        sb.append(", spouse=").append(spouse);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}