package la.niub.abcapi.servicecompre.model.response.wechat;

import la.niub.abcapi.servicecompre.model.bo.wechat.WechatIndexBO;

import java.util.List;

public class WechatIndexResponse {

    private List<WechatIndexBO> total;

    private List<WechatIndexBO> top;

    public List<WechatIndexBO> getTotal() {
        return total;
    }

    public void setTotal(List<WechatIndexBO> total) {
        this.total = total;
    }

    public List<WechatIndexBO> getTop() {
        return top;
    }

    public void setTop(List<WechatIndexBO> top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "WechatIndexResponse{" +
                "total=" + total +
                ", top=" + top +
                '}';
    }
}
