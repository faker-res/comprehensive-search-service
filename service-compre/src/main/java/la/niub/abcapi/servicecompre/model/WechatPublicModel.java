package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class WechatPublicModel implements Serializable {
    private Long id;

    private String classify;

    private String public_name;

    private String public_account;

    private String public_image;

    private String public_summary;

    private String public_authentication;

    private String public_tags;

    private String qr_code;

    private Integer month_count;

    private Integer source;

    private Integer createtime;

    private Integer updatetime;

    private String lable;

    private Integer follower;

    private String tags;

    private Integer ranking;

    private Integer reading;

    private static final long serialVersionUID = 1L;

    public WechatPublicModel(Long id, String classify, String public_name, String public_account, String public_image, String public_summary, String public_authentication, String public_tags, String qr_code, Integer month_count, Integer source, Integer createtime, Integer updatetime, String lable, Integer follower, String tags, Integer ranking, Integer reading) {
        this.id = id;
        this.classify = classify;
        this.public_name = public_name;
        this.public_account = public_account;
        this.public_image = public_image;
        this.public_summary = public_summary;
        this.public_authentication = public_authentication;
        this.public_tags = public_tags;
        this.qr_code = qr_code;
        this.month_count = month_count;
        this.source = source;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.lable = lable;
        this.follower = follower;
        this.tags = tags;
        this.ranking = ranking;
        this.reading = reading;
    }

    public WechatPublicModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify == null ? null : classify.trim();
    }

    public String getPublic_name() {
        return public_name;
    }

    public void setPublic_name(String public_name) {
        this.public_name = public_name == null ? null : public_name.trim();
    }

    public String getPublic_account() {
        return public_account;
    }

    public void setPublic_account(String public_account) {
        this.public_account = public_account == null ? null : public_account.trim();
    }

    public String getPublic_image() {
        return public_image;
    }

    public void setPublic_image(String public_image) {
        this.public_image = public_image == null ? null : public_image.trim();
    }

    public String getPublic_summary() {
        return public_summary;
    }

    public void setPublic_summary(String public_summary) {
        this.public_summary = public_summary == null ? null : public_summary.trim();
    }

    public String getPublic_authentication() {
        return public_authentication;
    }

    public void setPublic_authentication(String public_authentication) {
        this.public_authentication = public_authentication == null ? null : public_authentication.trim();
    }

    public String getPublic_tags() {
        return public_tags;
    }

    public void setPublic_tags(String public_tags) {
        this.public_tags = public_tags == null ? null : public_tags.trim();
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code == null ? null : qr_code.trim();
    }

    public Integer getMonth_count() {
        return month_count;
    }

    public void setMonth_count(Integer month_count) {
        this.month_count = month_count;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public Integer getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Integer updatetime) {
        this.updatetime = updatetime;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable == null ? null : lable.trim();
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
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
        sb.append(", id=").append(id);
        sb.append(", classify=").append(classify);
        sb.append(", public_name=").append(public_name);
        sb.append(", public_account=").append(public_account);
        sb.append(", public_image=").append(public_image);
        sb.append(", public_summary=").append(public_summary);
        sb.append(", public_authentication=").append(public_authentication);
        sb.append(", public_tags=").append(public_tags);
        sb.append(", qr_code=").append(qr_code);
        sb.append(", month_count=").append(month_count);
        sb.append(", source=").append(source);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", lable=").append(lable);
        sb.append(", follower=").append(follower);
        sb.append(", tags=").append(tags);
        sb.append(", ranking=").append(ranking);
        sb.append(", reading=").append(reading);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}