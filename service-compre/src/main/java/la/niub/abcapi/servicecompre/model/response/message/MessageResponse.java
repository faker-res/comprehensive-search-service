package la.niub.abcapi.servicecompre.model.response.message;

import java.util.Date;

public class MessageResponse {

    private String id;

    private String type;

    private String title;

    private String source;

    private String url;

    private Date time;

    private Integer read;

    private String author;

    private Boolean honor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getHonor() {
        return honor;
    }

    public void setHonor(Boolean honor) {
        this.honor = honor;
    }

    @Override
    public String toString() {
        return "FundCompanyTestResponse{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                ", time=" + time +
                ", read=" + read +
                ", author='" + author + '\'' +
                ", honor=" + honor +
                '}';
    }
}
