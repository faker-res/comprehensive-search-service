package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class ComprehensiveSearchesTopModel implements Serializable {
    private Integer id;

    private String title;

    private String query;

    private String link;

    private Integer news;

    private Integer change;

    private Integer order;

    private static final long serialVersionUID = 1L;

    public ComprehensiveSearchesTopModel(Integer id, String title, String query, String link, Integer news, Integer change, Integer order) {
        this.id = id;
        this.title = title;
        this.query = query;
        this.link = link;
        this.news = news;
        this.change = change;
        this.order = order;
    }

    public ComprehensiveSearchesTopModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public Integer getNews() {
        return news;
    }

    public void setNews(Integer news) {
        this.news = news;
    }

    public Integer getChange() {
        return change;
    }

    public void setChange(Integer change) {
        this.change = change;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "ComprehensiveSearchesTopModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", query='" + query + '\'' +
                ", link='" + link + '\'' +
                ", news=" + news +
                ", change=" + change +
                ", order=" + order +
                '}';
    }
}