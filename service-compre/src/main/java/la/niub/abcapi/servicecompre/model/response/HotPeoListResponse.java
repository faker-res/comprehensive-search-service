package la.niub.abcapi.servicecompre.model.response;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class HotPeoListResponse {

    private String id;

    private String name;

    private String avatar;

    private JSONObject tip;

    private List<String> desc;

    public HotPeoListResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public JSONObject getTip() {
        return tip;
    }

    public void setTip(JSONObject tip) {
        this.tip = tip;
    }

    public List<String> getDesc() {
        return desc;
    }

    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "HotPeoListResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", tip='" + tip + '\'' +
                ", desc=" + desc +
                '}';
    }
}
