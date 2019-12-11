package la.niub.abcapi.servicecompre.model.response;

import java.util.List;

public class ApiNewsDataOptionResponse {
    private String type;
    private String showname;
    private Boolean multiValued;
    private List<ApiNewsDataOptionItemResponse> item;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public Boolean getMultiValued() {
        return multiValued;
    }

    public void setMultiValued(Boolean multiValued) {
        this.multiValued = multiValued;
    }

    public List<ApiNewsDataOptionItemResponse> getItem() {
        return item;
    }

    public void setItem(List<ApiNewsDataOptionItemResponse> item) {
        this.item = item;
    }
}
