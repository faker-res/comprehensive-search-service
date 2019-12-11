package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class ComprehensiveSearchesHotTipsModel implements Serializable {
    private Integer id;

    private Integer type;

    private String title;

    private String recommend;

    private String query;

    private String link;

    private Integer state;

    private String source;

    private String image_url;

    private Integer timebefore;

    private static final long serialVersionUID = 1L;

    public ComprehensiveSearchesHotTipsModel(Integer id, Integer type, String title, String recommend, String query, String link, Integer state, String source, String image_url, Integer timebefore) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.recommend = recommend;
        this.query = query;
        this.link = link;
        this.state = state;
        this.source = source;
        this.image_url = image_url;
        this.timebefore = timebefore;
    }

    public ComprehensiveSearchesHotTipsModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query == null ? null : query.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url == null ? null : image_url.trim();
    }

    public Integer getTimebefore() {
        return timebefore;
    }

    public void setTimebefore(Integer timebefore) {
        this.timebefore = timebefore;
    }

    @Override
    public String toString() {
        return "ComprehensiveSearchesHotTipsModel{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", recommend='" + recommend + '\'' +
                ", query='" + query + '\'' +
                ", link='" + link + '\'' +
                ", state=" + state +
                ", source='" + source + '\'' +
                ", image_url='" + image_url + '\'' +
                ", timebefore=" + timebefore +
                '}';
    }
}