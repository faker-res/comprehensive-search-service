package la.niub.abcapi.servicecompre.model.response.client.report;

import java.util.List;

public class ReportAanlystHonorData {

    private String id;

    private List<String> honor;

    private Boolean is_new;

    private String name;

    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getHonor() {
        return honor;
    }

    public void setHonor(List<String> honor) {
        this.honor = honor;
    }

    public Boolean getIs_new() {
        return is_new;
    }

    public void setIs_new(Boolean is_new) {
        this.is_new = is_new;
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

    @Override
    public String toString() {
        return "ReportAanlystHonorData{" +
                "id='" + id + '\'' +
                ", honor=" + honor +
                ", is_new=" + is_new +
                ", name='" + name + '\'' +
                '}';
    }
}
