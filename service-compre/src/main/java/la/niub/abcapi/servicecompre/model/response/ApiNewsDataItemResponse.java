package la.niub.abcapi.servicecompre.model.response;

import java.util.List;

public class ApiNewsDataItemResponse {

    private String brief;

    private String first_image_oss;

    private String author;

    private String channel;

    private String title;

    private String url;

    private String source_url;

    private String content;

    private String tags;

    private String id;

    private int time;

    private String source_name;

    private String source_name_s;

    private List<String> tags_mul;

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getFirst_image_oss() {
        return first_image_oss;
    }

    public void setFirst_image_oss(String first_image_oss) {
        this.first_image_oss = first_image_oss;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
