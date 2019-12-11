package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class FinancialUserModel implements Serializable {
    private String user_id;

    private String user_name;

    private String foreign_name;

    private String nationality;

    private String nation;

    private String birthplace;

    private String birthdate;

    private String occupation;

    private String graduate_school;

    private String native_place;

    private String achievement;

    private String head;

    private String other;

    private String domain;

    private String tags;

    private Integer follower;

    private Integer reading;

    private static final long serialVersionUID = 1L;

    public FinancialUserModel(String user_id, String user_name, String foreign_name, String nationality, String nation, String birthplace, String birthdate, String occupation, String graduate_school, String native_place, String achievement, String head, String other, String domain) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.foreign_name = foreign_name;
        this.nationality = nationality;
        this.nation = nation;
        this.birthplace = birthplace;
        this.birthdate = birthdate;
        this.occupation = occupation;
        this.graduate_school = graduate_school;
        this.native_place = native_place;
        this.achievement = achievement;
        this.head = head;
        this.other = other;
        this.domain = domain;
    }

    public FinancialUserModel(String user_id, String user_name, String foreign_name, String nationality, String nation, String birthplace, String birthdate, String occupation, String graduate_school, String native_place, String achievement, String head, String other, String domain, String tags, Integer follower, Integer reading) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.foreign_name = foreign_name;
        this.nationality = nationality;
        this.nation = nation;
        this.birthplace = birthplace;
        this.birthdate = birthdate;
        this.occupation = occupation;
        this.graduate_school = graduate_school;
        this.native_place = native_place;
        this.achievement = achievement;
        this.head = head;
        this.other = other;
        this.domain = domain;
        this.tags = tags;
        this.follower = follower;
        this.reading = reading;
    }

    public FinancialUserModel() {
        super();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    public String getForeign_name() {
        return foreign_name;
    }

    public void setForeign_name(String foreign_name) {
        this.foreign_name = foreign_name == null ? null : foreign_name.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace == null ? null : birthplace.trim();
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate == null ? null : birthdate.trim();
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    public String getGraduate_school() {
        return graduate_school;
    }

    public void setGraduate_school(String graduate_school) {
        this.graduate_school = graduate_school == null ? null : graduate_school.trim();
    }

    public String getNative_place() {
        return native_place;
    }

    public void setNative_place(String native_place) {
        this.native_place = native_place == null ? null : native_place.trim();
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement == null ? null : achievement.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public Integer getReading() {
        return reading;
    }

    public void setReading(Integer reading) {
        this.reading = reading;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", user_id=").append(user_id);
        sb.append(", user_name=").append(user_name);
        sb.append(", foreign_name=").append(foreign_name);
        sb.append(", nationality=").append(nationality);
        sb.append(", nation=").append(nation);
        sb.append(", birthplace=").append(birthplace);
        sb.append(", birthdate=").append(birthdate);
        sb.append(", occupation=").append(occupation);
        sb.append(", graduate_school=").append(graduate_school);
        sb.append(", native_place=").append(native_place);
        sb.append(", achievement=").append(achievement);
        sb.append(", head=").append(head);
        sb.append(", other=").append(other);
        sb.append(", domain=").append(domain);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}