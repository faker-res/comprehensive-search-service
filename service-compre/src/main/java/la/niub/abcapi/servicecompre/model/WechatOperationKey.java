package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class WechatOperationKey implements Serializable {
    private Long wechat_id;

    private Integer date;

    private static final long serialVersionUID = 1L;

    public WechatOperationKey(Long wechat_id, Integer date) {
        this.wechat_id = wechat_id;
        this.date = date;
    }

    public WechatOperationKey() {
        super();
    }

    public Long getWechat_id() {
        return wechat_id;
    }

    public void setWechat_id(Long wechat_id) {
        this.wechat_id = wechat_id;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wechat_id=").append(wechat_id);
        sb.append(", date=").append(date);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}