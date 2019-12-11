package la.niub.abcapi.servicecompre.model.request.wechat;

public class WechatRequest {

    private Long code;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "WechatRequest{" +
                "code=" + code +
                '}';
    }
}
