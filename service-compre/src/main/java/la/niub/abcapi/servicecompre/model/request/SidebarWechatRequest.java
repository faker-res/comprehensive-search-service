package la.niub.abcapi.servicecompre.model.request;

public class SidebarWechatRequest {

    private String name;

    private Integer code;

    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SidebarWechatRequest{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", type='" + type + '\'' +
                '}';
    }
}
