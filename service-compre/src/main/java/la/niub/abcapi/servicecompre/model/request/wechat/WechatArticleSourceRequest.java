package la.niub.abcapi.servicecompre.model.request.wechat;

public class WechatArticleSourceRequest {

    private Long code;

    private String tags;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "WechatArticleSourceRequest{" +
                "code=" + code +
                ", tags='" + tags + '\'' +
                '}';
    }
}
