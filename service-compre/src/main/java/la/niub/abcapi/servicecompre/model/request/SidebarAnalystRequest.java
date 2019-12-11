package la.niub.abcapi.servicecompre.model.request;

public class SidebarAnalystRequest {

    private String name;

    private String code;

    private String type;

    private String cname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "SidebarAnalystRequest{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
