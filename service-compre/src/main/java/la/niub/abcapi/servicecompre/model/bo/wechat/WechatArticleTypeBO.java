package la.niub.abcapi.servicecompre.model.bo.wechat;

import java.util.Map;

public class WechatArticleTypeBO {

    private Integer total;

    private Integer original;

    private Map<String,Integer> type;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOriginal() {
        return original;
    }

    public void setOriginal(Integer original) {
        this.original = original;
    }

    public Map<String, Integer> getType() {
        return type;
    }

    public void setType(Map<String, Integer> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WechatArticleTypeBO{" +
                "total=" + total +
                ", original=" + original +
                ", type=" + type +
                '}';
    }
}
