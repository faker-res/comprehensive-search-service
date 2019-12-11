package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;
import java.util.Date;

public class AnalystBasicinfoModel implements Serializable {
    private Long id;

    private String peo_uni_code;

    private Long people_char;

    private String name;

    private Byte sex_par;

    private String birth_day;

    private String country;

    private String poli_status;

    private String university;

    private String high_edu;

    private String image;

    private String back_gro;

    private Date createtime;

    private Date updatetime;

    private Byte status;

    private String remark;

    private String creator;

    private String editor;

    private String come_source;

    private String honor;

    private Byte is_new;

    private String direction;

    private String mobil_phone;

    private String tel_phone;

    private String email;

    private String arg1;

    private String arg2;

    private String arg3;

    private String arg4;

    private String arg5;

    private static final long serialVersionUID = 1L;

    public AnalystBasicinfoModel(Long id, String peo_uni_code, Long people_char, String name, Byte sex_par, String birth_day, String country, String poli_status, String university, String high_edu, String image, String back_gro, Date createtime, Date updatetime, Byte status, String remark, String creator, String editor, String come_source, String honor, Byte is_new, String direction, String mobil_phone, String tel_phone, String email, String arg1, String arg2, String arg3, String arg4, String arg5) {
        this.id = id;
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
        this.back_gro = back_gro;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.status = status;
        this.remark = remark;
        this.creator = creator;
        this.editor = editor;
        this.come_source = come_source;
        this.honor = honor;
        this.is_new = is_new;
        this.direction = direction;
        this.mobil_phone = mobil_phone;
        this.tel_phone = tel_phone;
        this.email = email;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
        this.arg4 = arg4;
        this.arg5 = arg5;
    }

    public AnalystBasicinfoModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeo_uni_code() {
        return peo_uni_code;
    }

    public void setPeo_uni_code(String peo_uni_code) {
        this.peo_uni_code = peo_uni_code == null ? null : peo_uni_code.trim();
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

    public String getBack_gro() {
        return back_gro;
    }

    public void setBack_gro(String back_gro) {
        this.back_gro = back_gro == null ? null : back_gro.trim();
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

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor == null ? null : honor.trim();
    }

    public Byte getIs_new() {
        return is_new;
    }

    public void setIs_new(Byte is_new) {
        this.is_new = is_new;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public String getMobil_phone() {
        return mobil_phone;
    }

    public void setMobil_phone(String mobil_phone) {
        this.mobil_phone = mobil_phone == null ? null : mobil_phone.trim();
    }

    public String getTel_phone() {
        return tel_phone;
    }

    public void setTel_phone(String tel_phone) {
        this.tel_phone = tel_phone == null ? null : tel_phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1 == null ? null : arg1.trim();
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2 == null ? null : arg2.trim();
    }

    public String getArg3() {
        return arg3;
    }

    public void setArg3(String arg3) {
        this.arg3 = arg3 == null ? null : arg3.trim();
    }

    public String getArg4() {
        return arg4;
    }

    public void setArg4(String arg4) {
        this.arg4 = arg4 == null ? null : arg4.trim();
    }

    public String getArg5() {
        return arg5;
    }

    public void setArg5(String arg5) {
        this.arg5 = arg5 == null ? null : arg5.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
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
        sb.append(", back_gro=").append(back_gro);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", editor=").append(editor);
        sb.append(", come_source=").append(come_source);
        sb.append(", honor=").append(honor);
        sb.append(", is_new=").append(is_new);
        sb.append(", direction=").append(direction);
        sb.append(", mobil_phone=").append(mobil_phone);
        sb.append(", tel_phone=").append(tel_phone);
        sb.append(", email=").append(email);
        sb.append(", arg1=").append(arg1);
        sb.append(", arg2=").append(arg2);
        sb.append(", arg3=").append(arg3);
        sb.append(", arg4=").append(arg4);
        sb.append(", arg5=").append(arg5);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}