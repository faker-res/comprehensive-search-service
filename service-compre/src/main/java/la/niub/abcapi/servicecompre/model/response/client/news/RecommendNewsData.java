package la.niub.abcapi.servicecompre.model.response.client.news;

import java.util.List;

public class RecommendNewsData {

    private String title;

    private String article_id;

    private String summary;

    private List<String> small_img_url;

    private String blue_tags;

    private String source;

    private Integer read_persons;

    private Integer timebefore;

    private Integer pageNo;

    private Integer pageSize;

    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getSmall_img_url() {
        return small_img_url;
    }

    public void setSmall_img_url(List<String> small_img_url) {
        this.small_img_url = small_img_url;
    }

    public String getBlue_tags() {
        return blue_tags;
    }

    public void setBlue_tags(String blue_tags) {
        this.blue_tags = blue_tags;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getRead_persons() {
        return read_persons;
    }

    public void setRead_persons(Integer read_persons) {
        this.read_persons = read_persons;
    }

    public Integer getTimebefore() {
        return timebefore;
    }

    public void setTimebefore(Integer timebefore) {
        this.timebefore = timebefore;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "RecommendNewsData{" +
                "title='" + title + '\'' +
                ", article_id='" + article_id + '\'' +
                ", summary='" + summary + '\'' +
                ", small_img_url=" + small_img_url +
                ", blue_tags='" + blue_tags + '\'' +
                ", source='" + source + '\'' +
                ", read_persons=" + read_persons +
                ", timebefore=" + timebefore +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", link='" + link + '\'' +
                '}';
    }
}
