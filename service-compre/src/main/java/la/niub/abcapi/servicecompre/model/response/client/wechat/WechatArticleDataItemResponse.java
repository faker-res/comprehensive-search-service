package la.niub.abcapi.servicecompre.model.response.client.wechat;

import java.util.List;

public class WechatArticleDataItemResponse {

    private String id;

    private String url;

    private String title;

    private int time;

    private String source_name;

    private String source_name_s;

    private List<String> tags_mul;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getSource_name_s() {
        return source_name_s;
    }

    public void setSource_name_s(String source_name_s) {
        this.source_name_s = source_name_s;
    }

    public List<String> getTags_mul() {
        return tags_mul;
    }

    public void setTags_mul(List<String> tags_mul) {
        this.tags_mul = tags_mul;
    }
}
