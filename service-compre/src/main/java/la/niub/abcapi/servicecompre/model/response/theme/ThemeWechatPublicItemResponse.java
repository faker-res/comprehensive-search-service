package la.niub.abcapi.servicecompre.model.response.theme;

public class ThemeWechatPublicItemResponse {

    private int id;

    private String name;

    private String avatar;

    private String tag;

    private int reads;

    private String newestNewsTitle;

    private String newestNewsUrl;

    private String newestNewsId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getReads() {
        return reads;
    }

    public void setReads(int reads) {
        this.reads = reads;
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
}
