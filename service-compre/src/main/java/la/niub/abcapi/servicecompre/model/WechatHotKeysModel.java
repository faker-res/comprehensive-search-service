package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class WechatHotKeysModel implements Serializable {
    private Long wechat_id;

    private String hot_keys;

    private static final long serialVersionUID = 1L;

    public WechatHotKeysModel(Long wechat_id, String hot_keys) {
        this.wechat_id = wechat_id;
        this.hot_keys = hot_keys;
    }

    public WechatHotKeysModel() {
        super();
    }

    public Long getWechat_id() {
        return wechat_id;
    }

    public void setWechat_id(Long wechat_id) {
        this.wechat_id = wechat_id;
    }

    public String getHot_keys() {
        return hot_keys;
    }

    public void setHot_keys(String hot_keys) {
        this.hot_keys = hot_keys == null ? null : hot_keys.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wechat_id=").append(wechat_id);
        sb.append(", hot_keys=").append(hot_keys);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}