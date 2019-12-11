package la.niub.abcapi.servicecompre.model;

public class ChartConfigModel {

    private String id;

    private String topic_Title;

    private String topic_Sub;

    private String topic_Url;

    private String bg_ImgID;

    private String query;

    public String getId() { return id; }

    public void setId(String id) { this.id=id; }

    public String getTopic_Title() { return topic_Title; }

    public void setTopic_Title(String topic_Title) { this.topic_Title=topic_Title; }

    public String getTopic_Sub() { return topic_Sub; }

    public void setTopic_Sub(String topic_Sub) { this.topic_Sub=topic_Sub; }

    public String getTopic_Url() { return topic_Url; }

    public void setTopic_Url(String topic_Url) { this.topic_Url=topic_Url; }

    public String getBg_ImgID() { return bg_ImgID; }

    public void setBg_ImgID(String bg_ImgID) { this.bg_ImgID=bg_ImgID; }

    public String getQuery() { return query; }

    public void setQuery(String query) { this.query=query; }

    @Override
    public String toString() {
        return "KeyWordQueryModel{" +
                "id=" + id + '\'' +
                "topic_Title=" + topic_Title + '\'' +
                "topic_Sub=" + topic_Sub + '\'' +
                "topic_Url=" + topic_Url + '\'' +
                "bg_ImgID=" + bg_ImgID + '\'' +
                "query=" + query +
                '}';
    }
}
