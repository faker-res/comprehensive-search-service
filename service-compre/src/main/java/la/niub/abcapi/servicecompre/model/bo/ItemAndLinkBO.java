package la.niub.abcapi.servicecompre.model.bo;

import java.util.List;

public class ItemAndLinkBO<T> {

    private List<T> items;

    private String link;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "ItemAndLinkBO{" +
                "items=" + items +
                ", link='" + link + '\'' +
                '}';
    }
}
