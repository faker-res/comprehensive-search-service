package la.niub.abcapi.servicecompre.model.response.client.wechat;

import java.util.List;

public class WechatArticleDataResponse {
    private int total_count;

    private List<WechatArticleDataItemResponse> item;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<WechatArticleDataItemResponse> getItem() {
        return item;
    }

    public void setItem(List<WechatArticleDataItemResponse> item) {
        this.item = item;
    }

}
