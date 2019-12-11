package la.niub.abcapi.servicecompre.model.response.client.UserChart;

import la.niub.abcapi.servicecompre.model.response.client.datachart.DataChartItem;

import java.io.Serializable;
import java.util.List;

public class UserChartData implements Serializable {

    private List<DataChartItem> item;

    private Integer total_count;

    private String highlight_fields;

    private String unhighlight_fields;

    private String request_id;

    private String score;


    public List<DataChartItem> getItem() {
        return item;
    }

    public void setItem(List<DataChartItem> item) {
        this.item = item;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public String getHighlight_fields() {
        return highlight_fields;
    }

    public void setHighlight_fields(String highlight_fields) {
        this.highlight_fields = highlight_fields;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getUnhighlight_fields() {
        return unhighlight_fields;
    }

    public void setUnhighlight_fields(String unhighlight_fields) {
        this.unhighlight_fields = unhighlight_fields;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "UserChartData{" +
                "item=" + item +
                ", total_count=" + total_count +
                ", highlight_fields='" + highlight_fields + '\'' +
                ", unhighlight_fields='" + unhighlight_fields + '\'' +
                ", request_id='" + request_id + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}