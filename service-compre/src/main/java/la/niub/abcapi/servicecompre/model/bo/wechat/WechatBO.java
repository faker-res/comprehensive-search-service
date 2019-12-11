package la.niub.abcapi.servicecompre.model.bo.wechat;

import java.util.List;

public class WechatBO {

    private Long id;

    private String name;

    private String account;

    private String image;

    private String summary;

    private String authentication;

    private List<String> tags;

    private String qrCode;

    private Integer monthCount;

    private Integer rank;

    private Integer fans;

    private Integer read;

    private String classify;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Integer getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "WechatBO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", image='" + image + '\'' +
                ", summary='" + summary + '\'' +
                ", authentication='" + authentication + '\'' +
                ", tags=" + tags +
                ", qrCode='" + qrCode + '\'' +
                ", monthCount=" + monthCount +
                ", rank=" + rank +
                ", fans=" + fans +
                ", read=" + read +
                '}';
    }
}
