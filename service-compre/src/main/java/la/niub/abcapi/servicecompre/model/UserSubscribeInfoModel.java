package la.niub.abcapi.servicecompre.model;

import java.util.Arrays;
import java.util.Date;

public class UserSubscribeInfoModel {

    private Integer id;

    private String uid;

    private Integer group_id;

    private String target_info;

    private String target;

    private Integer type;

    private Date update_at;

    private byte[] scope;

    private Integer status;

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

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getTarget_info() {
        return target_info;
    }

    public void setTarget_info(String target_info) {
        this.target_info = target_info;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public byte[] getScope() {
        return scope;
    }

    public void setScope(byte[] scope) {
        this.scope = scope;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserSubscribeInfoModel{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", group_id=" + group_id +
                ", target_info='" + target_info + '\'' +
                ", target='" + target + '\'' +
                ", type=" + type +
                ", update_at=" + update_at +
                ", scope=" + Arrays.toString(scope) +
                ", status=" + status +
                '}';
    }
}
