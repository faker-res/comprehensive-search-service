package la.niub.abcapi.servicecompre.model;

import java.util.Arrays;
import java.util.Date;

public class UserSubscribeModel {

    private Integer id;

    private String uid;

    private String group_name;

    private Integer type;

    private byte[] content;

    private Date update_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    @Override
    public String toString() {
        return "UserSubscribeModel{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", group_name='" + group_name + '\'' +
                ", type=" + type +
                ", content=" + Arrays.toString(content) +
                ", update_at=" + update_at +
                '}';
    }
}
