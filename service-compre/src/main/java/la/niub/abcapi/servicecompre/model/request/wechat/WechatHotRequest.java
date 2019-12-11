package la.niub.abcapi.servicecompre.model.request.wechat;

public class WechatHotRequest {

    private Long code;

    private String type = "TAG";

    private Integer limit = 5;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "WechatHotRequest{" +
                "code=" + code +
                ", type='" + type + '\'' +
                ", limit=" + limit +
                '}';
    }
}
