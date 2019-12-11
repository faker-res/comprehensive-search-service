package la.niub.abcapi.servicecompre.model;

import java.io.Serializable;

public class WechatStatisticsModel implements Serializable {
    private Long wechat_id;

    private Integer original_num;

    private Integer article_num;

    private static final long serialVersionUID = 1L;

    public WechatStatisticsModel(Long wechat_id, Integer original_num, Integer article_num) {
        this.wechat_id = wechat_id;
        this.original_num = original_num;
        this.article_num = article_num;
    }

    public WechatStatisticsModel() {
        super();
    }

    public Long getWechat_id() {
        return wechat_id;
    }

    public void setWechat_id(Long wechat_id) {
        this.wechat_id = wechat_id;
    }

    public Integer getOriginal_num() {
        return original_num;
    }

    public void setOriginal_num(Integer original_num) {
        this.original_num = original_num;
    }

    public Integer getArticle_num() {
        return article_num;
    }

    public void setArticle_num(Integer article_num) {
        this.article_num = article_num;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wechat_id=").append(wechat_id);
        sb.append(", original_num=").append(original_num);
        sb.append(", article_num=").append(article_num);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}