package la.niub.abcapi.servicecompre.model;

public class KeyWordQueryModel {

    private String id;

    private String display_Name;

    private String QUERY;

    private String stock_Code;

    private String topic_Url;

    public String getId() { return id; }

    public void setId(String id) { this.id=id; }

    public String getDisplay_Name() { return display_Name; }

    public void setDisplay_Name(String display_Name) { this.display_Name=display_Name; }

    public String getQUERY() { return QUERY; }

    public void setQUERY(String QUERY) { this.QUERY=QUERY; }

    public String getStock_Code() { return stock_Code; }

    public void setStock_Code(String stock_Code) { this.stock_Code=stock_Code; }

    public String getTopic_Url() { return topic_Url; }

    public void setTopic_Url(String topic_Url) { this.topic_Url=topic_Url; }

    @Override
    public String toString() {
        return "KeyWordQueryModel{" +
                "id=" + id + '\'' +
                "display_Name=" + display_Name + '\'' +
                "QUERY=" + QUERY + '\'' +
                "stock_Code=" + stock_Code + '\'' +
                "topic_Url=" + topic_Url +
                '}';
    }
}
