package la.niub.abcapi.servicecompre.model.cache;

import java.util.List;

public class ReportCategoryModel {

    private String id;

    private String name;

    private List<ReportCategoryModel> child;

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

    public List<ReportCategoryModel> getChild() {
        return child;
    }

    public void setChild(List<ReportCategoryModel> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "ReportCategoryModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", child=" + child +
                '}';
    }
}
