package la.niub.abcapi.servicecompre.model.request.client;

public class ClientReportFieldFacetRequest {
    private String author;

    private String publish;

    private Integer start_time;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Integer getStart_time() {
        return start_time;
    }

    public void setStart_time(Integer start_time) {
        this.start_time = start_time;
    }

    @Override
    public String toString() {
        return "ClientReportFieldFacetRequest{" +
                "author='" + author + '\'' +
                ", publish='" + publish + '\'' +
                ", start_time=" + start_time +
                '}';
    }
}
