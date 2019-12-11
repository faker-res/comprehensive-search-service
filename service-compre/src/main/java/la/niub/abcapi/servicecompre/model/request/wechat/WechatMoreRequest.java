package la.niub.abcapi.servicecompre.model.request.wechat;

public class WechatMoreRequest {
    private String tags;
    private String classify;
    private String public_name;
    private String order_field;
    private String order_type;
    private Integer page_num = 1;
    private Integer limit = 12;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getPublic_name() {
        return public_name;
    }

    public void setPublic_name(String public_name) {
        this.public_name = public_name;
    }

    public String getOrder_field() {
        return order_field;
    }

    public void setOrder_field(String order_field) {
        this.order_field = order_field;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public Integer getPage_num() {
        return page_num;
    }

    public void setPage_num(Integer page_num) {
        this.page_num = page_num;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
