package la.niub.abcapi.servicecompre.model.response;

import java.util.List;

public class FinanceVipMoreArticleItemResponse {
    private String id;
    private String title;
    private String url;
    private Long time;
    private List<String> tag;
    private String source_image;
    private String source_name;
    private String wechatid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getSource_image() {
        return source_image;
    }

    public void setSource_image(String source_image) {
        this.source_image = source_image;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getWechatid() {
        return wechatid;
    }

    public void setWechatid(String wechatid) {
        this.wechatid = wechatid;
    }
}
