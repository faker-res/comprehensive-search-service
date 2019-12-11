package la.niub.abcapi.servicecompre.model.bo.sidebar;

import java.util.List;

public class SidebarStockCompanyBO {

    private String source;

    private String title;

    private String indu_name;

    private List<SidebarStockCompanyResultBO> result;

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

    public List<SidebarStockCompanyResultBO> getResult() {
        return result;
    }

    public void setResult(List<SidebarStockCompanyResultBO> result) {
        this.result = result;
    }

    public String getIndu_name() {
        return indu_name;
    }

    public void setIndu_name(String indu_name) {
        this.indu_name = indu_name;
    }

    @Override
    public String toString() {
        return "SidebarStockCompanyBO{" +
                "source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", result=" + result +
                '}';
    }
}
