package la.niub.abcapi.servicecompre.model.bo.wechat;

public class WechatSourceBO {

    private String name;

    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WechatSourceBO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
