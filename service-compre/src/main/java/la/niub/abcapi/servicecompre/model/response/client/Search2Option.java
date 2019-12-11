package la.niub.abcapi.servicecompre.model.response.client;
import java.util.List;

public class Search2Option {
    private List<Search2OptionItem> item;

    private String type;

    private String showname;

    private Boolean multiValued;

    public List<Search2OptionItem> getItem() {
        return item;
    }

    public void setItem(List<Search2OptionItem> item) {
        this.item = item;
    }

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

    @Override
    public String toString() {
        return "Search2Option{" +
                "item=" + item +
                ", type='" + type + '\'' +
                ", showname='" + showname + '\'' +
                '}';
    }
}
