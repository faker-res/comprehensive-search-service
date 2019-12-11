package la.niub.abcapi.servicecompre.model.bo.card;

public class CardAnalystDynamicBO {

    private Integer id;

    private String source = "";

    private Integer time;

    private String title = "";

    private String category = "";

    private String rating = "";


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "CardAnalystDynamicBO{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", time=" + time +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
