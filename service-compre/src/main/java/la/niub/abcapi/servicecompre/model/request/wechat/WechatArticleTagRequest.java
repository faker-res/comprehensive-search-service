package la.niub.abcapi.servicecompre.model.request.wechat;

public class WechatArticleTagRequest {

    private Long code;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "WechatArticleTagRequest{" +
                "code=" + code +
                '}';
    }
}
