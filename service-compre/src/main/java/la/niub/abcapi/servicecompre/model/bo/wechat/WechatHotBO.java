package la.niub.abcapi.servicecompre.model.bo.wechat;

public class WechatHotBO {

    private Long id;

    private String name;

    private String avatar;

    private String tag;

    private String category;

    private String newsTag;

    private Integer reads;

    private Integer fans;

    private String newestNewsTitle;

    private String newestNewsUrl;

    private String newestNewsId;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNewsTag() {
        return newsTag;
    }

    public void setNewsTag(String newsTag) {
        this.newsTag = newsTag;
    }

    public Integer getReads() {
        return reads;
    }

    public void setReads(Integer reads) {
        this.reads = reads;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public String getNewestNewsTitle() {
        return newestNewsTitle;
    }

    public void setNewestNewsTitle(String newestNewsTitle) {
        this.newestNewsTitle = newestNewsTitle;
    }

    public String getNewestNewsUrl() {
        return newestNewsUrl;
    }

    public void setNewestNewsUrl(String newestNewsUrl) {
        this.newestNewsUrl = newestNewsUrl;
    }

    public String getNewestNewsId() {
        return newestNewsId;
    }

    public void setNewestNewsId(String newestNewsId) {
        this.newestNewsId = newestNewsId;
    }

    @Override
    public String toString() {
        return "WechatHotBO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", tag='" + tag + '\'' +
                ", category='" + category + '\'' +
                ", newsTag='" + newsTag + '\'' +
                ", reads=" + reads +
                ", fans=" + fans +
                ", newestNewsTitle='" + newestNewsTitle + '\'' +
                ", newestNewsUrl='" + newestNewsUrl + '\'' +
                ", newestNewsId='" + newestNewsId + '\'' +
                '}';
    }
}
