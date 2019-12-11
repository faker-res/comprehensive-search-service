package la.niub.abcapi.servicecompre.model.bo.sidebar;

import java.util.List;

public class SidebarStockExpertBO {

    private String source;

    private String title;

    private List<SidebarStockExpertResultBO> result;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SidebarStockExpertResultBO> getResult() {
        return result;
    }

    public void setResult(List<SidebarStockExpertResultBO> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SidebarStockExpertBO{" +
                "source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", result=" + result +
                '}';
    }
}
