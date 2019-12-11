package la.niub.abcapi.servicecompre.model.response.client.notice;

import java.io.Serializable;
import java.util.List;

public class NoticeData implements Serializable {

    private List<NoticeItem> item;

    private Integer total_count;

    private String request_id;

    private Integer abcscore;

    public List<NoticeItem> getItem() {
        return item;
    }

    public void setItem(List<NoticeItem> item) {
        this.item = item;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public Integer getAbcscore() {
        return abcscore;
    }

    public void setAbcscore(Integer abcscore) {
        this.abcscore = abcscore;
    }

    @Override
    public String toString() {
        return "NoticeData{" +
                "item=" + item +
                ", total_count=" + total_count +
                ", request_id='" + request_id + '\'' +
                ", abcscore=" + abcscore +
                '}';
    }
}
