package la.niub.abcapi.servicecompre.model.response.client.news;

import java.util.List;

public class BestNewsData {

    private String report_type;

    private List<String> big_img_url;

    private List<String> small_img_url;

    private String title;

    private String article_id;

    private String summary;

    private Integer read_persons;

    private String blue_tags;

    private String source;

    private Integer style;

    private Integer timebefore;

    private Integer status;

    private Integer join_selected;

    private Integer original;

    private String link;

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public List<String> getBig_img_url() {
        return big_img_url;
    }

    public void setBig_img_url(List<String> big_img_url) {
        this.big_img_url = big_img_url;
    }

    public List<String> getSmall_img_url() {
        return small_img_url;
    }

    public void setSmall_img_url(List<String> small_img_url) {
        this.small_img_url = small_img_url;
    }

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

    public Integer getRead_persons() {
        return read_persons;
    }

    public void setRead_persons(Integer read_persons) {
        this.read_persons = read_persons;
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

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Integer getTimebefore() {
        return timebefore;
    }

    public void setTimebefore(Integer timebefore) {
        this.timebefore = timebefore;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getJoin_selected() {
        return join_selected;
    }

    public void setJoin_selected(Integer join_selected) {
        this.join_selected = join_selected;
    }

    public Integer getOriginal() {
        return original;
    }

    public void setOriginal(Integer original) {
        this.original = original;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "BestNewsData{" +
                "report_type='" + report_type + '\'' +
                ", big_img_url=" + big_img_url +
                ", small_img_url=" + small_img_url +
                ", title='" + title + '\'' +
                ", article_id='" + article_id + '\'' +
                ", summary='" + summary + '\'' +
                ", read_persons=" + read_persons +
                ", blue_tags='" + blue_tags + '\'' +
                ", source='" + source + '\'' +
                ", style=" + style +
                ", timebefore=" + timebefore +
                ", status=" + status +
                ", join_selected=" + join_selected +
                ", original=" + original +
                ", link='" + link + '\'' +
                '}';
    }
}
