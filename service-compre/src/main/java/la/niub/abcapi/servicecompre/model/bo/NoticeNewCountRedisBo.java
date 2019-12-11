package la.niub.abcapi.servicecompre.model.bo;

import java.util.Map;

public class NoticeNewCountRedisBo {
    private int total;

    private Map<String, Integer>data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<String, Integer> getData() {
        return data;
    }

    public void setData(Map<String, Integer> data) {
        this.data = data;
    }
}
