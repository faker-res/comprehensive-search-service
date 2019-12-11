package la.niub.abcapi.servicecompre.model.cache;

public class ReportCategoryFlatModel {

    private String id;

    private String name;

    private String logo;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "ReportCategoryFlatModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
