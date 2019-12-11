package la.niub.abcapi.servicecompre.model.request.client;

public class ClientNewsRequest {
    private String keyword;

    private Integer offset;

    private Integer limit;

    private String prior = "";

    private String channel = "";

    private String core_name = "core_news";

    private String single = "true";

    private String userId = "";

    private String request_id = "";

    private String device_info = "";

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCore_name() {
        return core_name;
    }

    public void setCore_name(String core_name) {
        this.core_name = core_name;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    @Override
    public String toString() {
        return "ClientNewsRequest{" +
                "keyword='" + keyword + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                ", prior='" + prior + '\'' +
                ", channel='" + channel + '\'' +
                ", core_name='" + core_name + '\'' +
                ", single='" + single + '\'' +
                ", userId='" + userId + '\'' +
                ", request_id='" + request_id + '\'' +
                ", device_info='" + device_info + '\'' +
                '}';
    }
}
