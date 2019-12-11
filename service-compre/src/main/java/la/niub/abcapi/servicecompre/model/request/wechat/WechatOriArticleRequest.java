package la.niub.abcapi.servicecompre.model.request.wechat;

public class WechatOriArticleRequest {
    private Long code;

    private String tag;

    public Long getCode() {
        return code;
    }

    public void setCode(Long  code) {
        this.code = code;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
