package la.niub.abcapi.servicecompre.model.response.wechat;

import java.util.List;

public class WechatMoreItemResponse {
    private Long key;
    private Long id;
    private String public_name;
    private String public_account;
    private String public_image;
    private String classify;
    private Integer month_count;
    private Integer follower;
    private List<String> tags;
    private WechatNewsResponse news;
    private String qr_code;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublic_name() {
        return public_name;
    }

    public void setPublic_name(String public_name) {
        this.public_name = public_name;
    }

    public String getPublic_account() {
        return public_account;
    }

    public void setPublic_account(String public_account) {
        this.public_account = public_account;
    }

    public String getPublic_image() {
        return public_image;
    }

    public void setPublic_image(String public_image) {
        this.public_image = public_image;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public Integer getMonth_count() {
        return month_count;
    }

    public void setMonth_count(Integer month_count) {
        this.month_count = month_count;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public WechatNewsResponse getNews() {
        return news;
    }

    public void setNews(WechatNewsResponse news) {
        this.news = news;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }
}
