package la.niub.abcapi.servicecompre.model.request.wechat;

public class WechatIndexRequest {

    private Long code;

    private String name;

    private String start_time;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    @Override
    public String toString() {
        return "WechatIndexRequest{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", start_time='" + start_time + '\'' +
                '}';
    }
}
