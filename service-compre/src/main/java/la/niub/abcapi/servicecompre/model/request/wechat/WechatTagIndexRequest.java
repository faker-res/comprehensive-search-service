package la.niub.abcapi.servicecompre.model.request.wechat;

public class WechatTagIndexRequest {

    private Long codes;

    private String tags;

    public Long getCodes() {
        return codes;
    }

    public void setCodes(Long codes) {
        this.codes = codes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
