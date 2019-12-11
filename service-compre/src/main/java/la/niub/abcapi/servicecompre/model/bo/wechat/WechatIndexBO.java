package la.niub.abcapi.servicecompre.model.bo.wechat;

import java.util.Map;

public class WechatIndexBO {

    private Map<Long,Integer> total;

    private Map<Long,Integer> top;

    public Map<Long, Integer> getTotal() {
        return total;
    }

    public void setTotal(Map<Long, Integer> total) {
        this.total = total;
    }

    public Map<Long, Integer> getTop() {
        return top;
    }

    public void setTop(Map<Long, Integer> top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "WechatIndexBO{" +
                "total=" + total +
                ", top=" + top +
                '}';
    }
}
